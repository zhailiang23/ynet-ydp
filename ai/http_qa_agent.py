"""
HTTP 问答 Agent 服务 - 使用 DeepAgents + FastAPI
提供 REST API 接口进行流式对话
支持动态提示词,可根据课程脚本和虚拟客户信息定制
"""
import os
import json
from pathlib import Path
from typing import AsyncGenerator, Optional, Dict, Any
from dataclasses import dataclass

from dotenv import load_dotenv
from fastapi import FastAPI, HTTPException
from fastapi.responses import StreamingResponse
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel
import pymysql

from deepagents import create_deep_agent
from langchain_openai import ChatOpenAI


# ==================== 配置 ====================

# 加载 .env 文件
env_path = Path(__file__).parent / '.env'
if env_path.exists():
    load_dotenv(env_path)
    print(f"✓ 已加载配置文件: {env_path}")

# 模型配置
MODEL_PROVIDER = os.environ.get("MODEL_PROVIDER", "anthropic")  # anthropic 或 deepseek
MODEL_NAME = os.environ.get("MODEL_NAME", "claude-sonnet-4-5-20250929")

# 数据库配置
DB_HOST = os.environ.get("DB_HOST", "127.0.0.1")
DB_PORT = int(os.environ.get("DB_PORT", "3306"))
DB_USER = os.environ.get("DB_USER", "root")
DB_PASSWORD = os.environ.get("DB_PASSWORD", "123456")
DB_NAME = os.environ.get("DB_NAME", "ruoyi-vue-pro")

# 检查对应的 API Key
if MODEL_PROVIDER == "deepseek":
    api_key = os.environ.get("DEEPSEEK_API_KEY")
    api_base = os.environ.get("DEEPSEEK_API_BASE", "https://api.deepseek.com")
    if not api_key:
        raise RuntimeError("错误: 使用 DeepSeek 时请设置环境变量 DEEPSEEK_API_KEY")
else:
    api_key = os.environ.get("ANTHROPIC_API_KEY")
    if not api_key:
        raise RuntimeError("错误: 使用 Anthropic 时请设置环境变量 ANTHROPIC_API_KEY")

print(f"✓ 使用模型提供商: {MODEL_PROVIDER}")
print(f"✓ 使用模型: {MODEL_NAME}")
print(f"✓ 数据库连接: {DB_USER}@{DB_HOST}:{DB_PORT}/{DB_NAME}")


# ==================== 数据库连接 ====================

def get_db_connection():
    """
    创建数据库连接

    Returns:
        pymysql.Connection 对象
    """
    try:
        conn = pymysql.connect(
            host=DB_HOST,
            port=DB_PORT,
            user=DB_USER,
            password=DB_PASSWORD,
            database=DB_NAME,
            charset='utf8mb4'
        )
        return conn
    except Exception as e:
        print(f"❌ 数据库连接失败: {str(e)}")
        raise


def query_script_info(script_id: int) -> dict:
    """
    根据剧本ID查询剧本相关信息

    Args:
        script_id: 剧本ID

    Returns:
        包含剧本、虚拟客户、培训材料等信息的字典
    """
    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            # 查询剧本基本信息
            cursor.execute("""
                SELECT
                    id, script_no, name, description,
                    difficulty_level, marketing_step,
                    case_id, skill_id, virtual_customer_id, material_ids
                FROM crm_practice_script
                WHERE id = %s AND deleted = 0
            """, (script_id,))
            script = cursor.fetchone()

            if not script:
                raise HTTPException(status_code=404, detail=f"剧本ID {script_id} 不存在")

            # 查询虚拟客户信息
            virtual_customer = None
            if script.get('virtual_customer_id'):
                cursor.execute("""
                    SELECT id, name, gender, age, occupation, industry,
                           personality_type, risk_preference, memo
                    FROM crm_practice_virtual_customer
                    WHERE id = %s AND deleted = 0
                """, (script['virtual_customer_id'],))
                virtual_customer = cursor.fetchone()

            # 查询培训材料信息
            materials = []
            if script.get('material_ids'):
                material_ids = script['material_ids'].split(',')
                if material_ids:
                    placeholders = ','.join(['%s'] * len(material_ids))
                    cursor.execute(f"""
                        SELECT id, name, content_extracted
                        FROM crm_practice_material
                        WHERE id IN ({placeholders}) AND deleted = 0
                    """, material_ids)
                    materials = cursor.fetchall()

            return {
                'script': script,
                'virtual_customer': virtual_customer,
                'materials': materials
            }
    finally:
        conn.close()


# ==================== 模型实例化 ====================

def create_model_instance():
    """
    根据配置创建模型实例

    Returns:
        LangChain 模型实例
    """
    if MODEL_PROVIDER == "deepseek":
        # 使用 DeepSeek API
        return ChatOpenAI(
            model=MODEL_NAME,
            api_key=api_key,
            base_url=api_base,
            temperature=0.7,
            streaming=True
        )
    else:
        # 使用 Anthropic Claude (默认)
        # deepagents 会自动使用环境变量中的 ANTHROPIC_API_KEY
        return MODEL_NAME  # 返回字符串，让 deepagents 自动处理


# ==================== 动态提示词生成函数 ====================

def create_practice_prompt(
    course_script: str = "",
    virtual_customer_name: str = "客户",
    virtual_customer_profile: str = ""
) -> str:
    """
    根据陪练上下文动态生成提示词 (从数据库读取模板)

    Args:
        course_script: 课程脚本内容
        virtual_customer_name: 虚拟客户姓名
        virtual_customer_profile: 虚拟客户画像

    Returns:
        动态生成的系统提示词
    """
    # 1. 从数据库查询提示词模板
    template = query_prompt_template("USER_GREETING_PROMPT")

    if not template:
        # 如果未找到模板,使用默认提示词
        print("⚠️ 未找到 USER_GREETING_PROMPT 模板,使用默认提示词")
        return _get_default_practice_prompt(course_script, virtual_customer_name, virtual_customer_profile)

    # 2. 组装虚拟客户画像信息
    customer_info = f"姓名: {virtual_customer_name}\n"
    if virtual_customer_profile:
        customer_info += f"{virtual_customer_profile}"
    else:
        customer_info += "普通银行客户"

    # 3. 替换模板变量
    prompt_content = template['content']
    variables = {
        'virtual_customer_profile': customer_info,
        'script_content': course_script if course_script else "根据客户需求提供金融咨询服务"
    }

    # 替换提示词中的变量
    for key, value in variables.items():
        prompt_content = prompt_content.replace(f'{{{{{key}}}}}', str(value))

    return prompt_content


def _get_default_practice_prompt(
    course_script: str = "",
    virtual_customer_name: str = "客户",
    virtual_customer_profile: str = ""
) -> str:
    """
    获取默认的陪练提示词(兜底方案)

    Args:
        course_script: 课程脚本内容
        virtual_customer_name: 虚拟客户姓名
        virtual_customer_profile: 虚拟客户画像

    Returns:
        默认提示词
    """
    base_prompt = f"""你是一位名叫 {virtual_customer_name} 的虚拟客户。

**你的角色定位**：
- 你是来银行咨询的**客户**,不是银行工作人员
- 你正在和银行理财顾问(学员)对话
- 你需要根据以下客户画像和脚本来扮演这个客户角色
- 你应该向理财顾问**提问**、**表达顾虑**、**询问细节**
- 你**不应该**给对方提供专业建议或解释理财产品
"""

    # 添加客户画像
    if virtual_customer_profile:
        base_prompt += f"\n客户画像：\n{virtual_customer_profile}\n"

    # 添加课程脚本
    if course_script:
        base_prompt += f"""
课程脚本指引：
{course_script}

**重要说明**：
- 请严格按照上述脚本中的角色设定和情景进行互动
- **始终保持客户身份**,不要跳出角色变成银行工作人员
- 根据学员(理财顾问)的回答给出自然、真实的客户反应
- 如果学员的回答不够专业或有遗漏,作为客户提出疑问或要求进一步解释
- 使用简洁、口语化的中文回答,模拟真实客户的语言风格

**严格禁止**：
- ❌ 不要说"我可以给您解释"、"我来帮您分析"等银行工作人员的话
- ❌ 不要主动介绍理财产品或提供专业建议
- ❌ 不要扮演理财顾问、银行经理等角色
- ✅ 只能作为客户提问、表达需求、询问细节

**输出格式要求**：
- 只输出对话内容本身
- 不要添加任何动作描写(如 *略微挑起眉毛*)
- 不要添加任何神态描写(如 *语气带着明显的兴趣*)
- 不要添加任何表情描写(如 *身体微微前倾*)
- 不要使用星号(*)或其他符号来标注非对话内容
- 直接以第一人称说话,就像真实的客户在对话
"""
    else:
        # 默认脚本(兜底)
        base_prompt += """
默认场景：
- 你是一位对理财产品感兴趣的普通客户
- 你希望了解适合自己的理财产品
- 你会根据学员的介绍提出问题
- 请用自然、口语化的中文与学员对话

**输出格式要求**：
- 只输出对话内容本身
- 不要添加任何动作、神态、表情描写
- 不要使用星号(*)或其他符号来标注非对话内容
- 直接以第一人称说话
"""

    return base_prompt


def query_prompt_template(code: str) -> Optional[Dict[str, Any]]:
    """查询提示词模板（提示词模板是全局共享的，使用 tenant_id = 0）"""
    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            sql = """
                SELECT code, name, content, variables, description
                FROM crm_practice_prompt_template
                WHERE code = %s AND status = 1 AND deleted = 0 AND tenant_id = 0
            """
            cursor.execute(sql, (code,))
            return cursor.fetchone()
    finally:
        conn.close()


# ==================== FastAPI 应用 ====================

app = FastAPI(
    title="DeepAgents QA API",
    description="基于 DeepAgents 的问答 Agent HTTP 服务",
    version="1.0.0"
)

# 添加 CORS 支持
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # 生产环境应该限制具体域名
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


# ==================== 数据模型 ====================

class ChatRequest(BaseModel):
    """聊天请求"""
    message: str
    stream: bool = True  # 是否流式返回
    # 动态上下文参数(可选)
    course_script: Optional[str] = None  # 课程脚本
    virtual_customer_name: Optional[str] = None  # 虚拟客户姓名
    virtual_customer_profile: Optional[str] = None  # 虚拟客户画像
    # 对话历史(可选)
    history: Optional[list] = None  # 历史对话列表 [{"role": "user"/"assistant", "content": "..."}]

    class Config:
        json_schema_extra = {
            "example": {
                "message": "你好,我想了解一下理财产品",
                "stream": False,
                "course_script": "场景:客户咨询理财产品...",
                "virtual_customer_name": "张女士",
                "virtual_customer_profile": "45岁,有一定理财经验,风险偏好稳健",
                "history": [
                    {"role": "user", "content": "你好"},
                    {"role": "assistant", "content": "你好,有什么可以帮到你的吗?"}
                ]
            }
        }


class ChatResponse(BaseModel):
    """聊天响应（非流式）"""
    response: str

    class Config:
        json_schema_extra = {
            "example": {
                "response": "人工智能是..."
            }
        }


class EvaluateRequest(BaseModel):
    """评估请求"""
    record_id: int  # 练习记录ID
    conversation_history: Optional[list] = None  # 对话历史
    course_name: Optional[str] = None  # 课程名称
    virtual_customer_name: Optional[str] = None  # 虚拟客户名称

    class Config:
        json_schema_extra = {
            "example": {
                "record_id": 1,
                "conversation_history": [
                    {"role": "user", "content": "李女士,根据大数据分析..."},
                    {"role": "assistant", "content": "你们和暴雷的那家机构有什么区别?"}
                ],
                "course_name": "理财产品销售陪练",
                "virtual_customer_name": "李女士"
            }
        }


class EvaluateResponse(BaseModel):
    """评估响应"""
    communication_score: int  # 沟通逻辑得分 (0-100)
    professionalism_score: int  # 专业能力得分 (0-100)
    compliance_score: int  # 合规表现得分 (0-100)
    manager_feedback: str  # 客户经理评估
    compliance_issues: list  # 合规问题列表
    quantified_metrics: list  # 量化指标
    improvement_suggestions: list  # 提升建议

    class Config:
        json_schema_extra = {
            "example": {
                "communication_score": 78,
                "professionalism_score": 82,
                "compliance_score": 65,
                "manager_feedback": "本次培训表现良好...",
                "compliance_issues": [
                    {"type": "合规通过", "description": "表现良好", "status": "通过"}
                ],
                "quantified_metrics": [
                    {"dimension": "客户信任建立", "target": "≥80%", "actual": "75%", "deviation": "未达标"}
                ],
                "improvement_suggestions": [
                    {"text": "加强合规意识", "resource": {"label": "合规培训", "url": "#"}}
                ]
            }
        }


class GenerateScriptRequest(BaseModel):
    """剧本生成请求"""
    script_id: int  # 剧本ID

    class Config:
        json_schema_extra = {
            "example": {
                "script_id": 24
            }
        }


class GenerateScriptResponse(BaseModel):
    """剧本生成响应"""
    script_content: str  # 生成的剧本内容

    class Config:
        json_schema_extra = {
            "example": {
                "script_content": "场景：银行理财顾问与客户的对话..."
            }
        }


# ==================== API 端点 ====================

@app.get("/")
async def root():
    """根路径 - API 信息"""
    return {
        "name": "DeepAgents QA API",
        "version": "1.0.0",
        "endpoints": {
            "chat": "/chat (POST) - 智能对话服务",
            "evaluate": "/evaluate (POST) - AI评估服务",
            "health": "/health (GET) - 健康检查",
            "docs": "/docs (GET) - API文档"
        }
    }


@app.get("/health")
async def health():
    """健康检查"""
    return {
        "status": "healthy",
        "agent": "ready",
        "api_key_configured": bool(api_key)
    }


@app.post("/chat")
async def chat(request: ChatRequest):
    """
    聊天接口

    - 支持流式和非流式响应
    - stream=True: 返回 Server-Sent Events (SSE) 流
    - stream=False: 返回 JSON 响应
    - 支持动态提示词: 通过 course_script, virtual_customer_name, virtual_customer_profile 参数定制
    - 支持对话历史: 通过 history 参数传递历史对话
    """
    if not request.message or not request.message.strip():
        raise HTTPException(status_code=400, detail="消息不能为空")

    # 动态生成系统提示词
    system_prompt = create_practice_prompt(
        course_script=request.course_script or "",
        virtual_customer_name=request.virtual_customer_name or "客户",
        virtual_customer_profile=request.virtual_customer_profile or ""
    )

    # 调试日志
    print(f"\n{'='*60}")
    print(f"收到请求:")
    print(f"  消息: {request.message}")
    print(f"  客户名称: {request.virtual_customer_name}")
    print(f"  客户画像: {request.virtual_customer_profile}")
    print(f"  课程脚本长度: {len(request.course_script or '')}")
    print(f"  历史对话数: {len(request.history) if request.history else 0}")
    print(f"{'='*60}\n")

    try:
        # 每次请求创建一个新的 Agent (使用动态生成的提示词)
        model = create_model_instance()
        agent = create_deep_agent(
            model=model,
            tools=[],  # 暂时不需要工具
            system_prompt=system_prompt
        )

        # 构建完整的消息列表 (历史 + 当前消息)
        messages = []
        if request.history:
            messages.extend(request.history)
        messages.append({"role": "user", "content": request.message})

        if request.stream:
            # 流式响应
            return StreamingResponse(
                stream_agent_response(agent, messages),
                media_type="text/event-stream"
            )
        else:
            # 非流式响应
            result = agent.invoke({"messages": messages})
            response_text = extract_response(result)
            return ChatResponse(response=response_text)

    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Agent 处理错误: {str(e)}")


@app.post("/generate-script", response_model=GenerateScriptResponse)
async def generate_script(request: GenerateScriptRequest):
    """
    剧本生成接口

    根据剧本ID从数据库查询相关信息,使用AI生成剧本内容
    """
    print(f"\n{'='*60}")
    print(f"收到剧本生成请求:")
    print(f"  剧本ID: {request.script_id}")
    print(f"{'='*60}\n")

    try:
        # 查询剧本相关信息
        script_info = query_script_info(request.script_id)
        script = script_info['script']
        virtual_customer = script_info['virtual_customer']
        materials = script_info['materials']

        print(f"剧本信息:")
        print(f"  剧本名称: {script.get('name')}")
        print(f"  难度等级: {script.get('difficulty_level')}")
        print(f"  营销环节: {script.get('marketing_step')}")
        print(f"  虚拟客户: {virtual_customer.get('name') if virtual_customer else '无'}")
        print(f"  培训材料数: {len(materials)}")

        # 构建剧本生成提示词
        script_prompt = f"""你是一位资深的银行培训专家,负责编写客户经理角色扮演训练剧本。

**剧本基本信息**:
- 剧本名称: {script.get('name')}
- 剧本描述: {script.get('description') or ''}
- 难度等级: {script.get('difficulty_level')}
- 营销环节: {script.get('marketing_step')}

**虚拟客户信息**:
"""
        if virtual_customer:
            script_prompt += f"- 客户姓名: {virtual_customer.get('name')}\n"
            if virtual_customer.get('gender'):
                script_prompt += f"- 性别: {virtual_customer.get('gender')}\n"
            if virtual_customer.get('age'):
                script_prompt += f"- 年龄: {virtual_customer.get('age')}\n"
            if virtual_customer.get('occupation'):
                script_prompt += f"- 职业: {virtual_customer.get('occupation')}\n"
            if virtual_customer.get('industry'):
                script_prompt += f"- 行业: {virtual_customer.get('industry')}\n"
            if virtual_customer.get('personality_type'):
                script_prompt += f"- 性格类型: {virtual_customer.get('personality_type')}\n"
            if virtual_customer.get('risk_preference'):
                script_prompt += f"- 风险偏好: {virtual_customer.get('risk_preference')}\n"
            if virtual_customer.get('memo'):
                script_prompt += f"- 备注:\n{virtual_customer.get('memo')}\n"
        else:
            script_prompt += "- 暂无虚拟客户信息\n"

        # 添加培训材料
        if materials:
            script_prompt += "\n**参考培训材料**:\n"
            for i, material in enumerate(materials, 1):
                script_prompt += f"{i}. {material.get('name')}:\n"
                if material.get('content_extracted'):
                    # 限制材料内容长度,避免prompt过长
                    content = material.get('content_extracted')
                    if len(content) > 1000:
                        content = content[:1000] + "..."
                    script_prompt += f"{content}\n\n"

        script_prompt += """
**任务要求**:
请根据以上信息,生成一份完整的角色扮演训练剧本。剧本应包括:

1. **场景设定**: 描述对话发生的具体场景(时间、地点、背景)
2. **客户角色**: 描述虚拟客户的性格特点、需求动机、顾虑点
3. **训练目标**: 明确说明学员通过这次训练应该掌握的技能
4. **对话流程**: 设计5-8轮完整的对话,包括:
   - 开场白(学员如何开启对话)
   - 客户初始反应和问题
   - 学员的应对策略和话术
   - 客户的进一步疑问或异议
   - 学员的解决方案
   - 结束语
5. **关键考核点**: 列出3-5个重点考核指标

**输出格式**:
请以纯文本格式输出,使用清晰的标题和段落结构,不要使用Markdown格式。
"""

        # 调用AI模型生成剧本
        model = create_model_instance()
        agent = create_deep_agent(
            model=model,
            tools=[],
            system_prompt="你是一位资深的银行培训专家,擅长编写角色扮演训练剧本。你的剧本真实、实用,能够有效提升客户经理的业务能力。"
        )

        result = agent.invoke({"messages": [{"role": "user", "content": script_prompt}]})
        script_content = extract_response(result)

        print(f"\n剧本生成成功,内容长度: {len(script_content)} 字符")

        return GenerateScriptResponse(script_content=script_content)

    except HTTPException:
        raise
    except Exception as e:
        import traceback
        print(f"剧本生成错误: {str(e)}")
        print(traceback.format_exc())
        raise HTTPException(status_code=500, detail=f"剧本生成错误: {str(e)}")


@app.post("/evaluate", response_model=EvaluateResponse)
async def evaluate(request: EvaluateRequest):
    """
    评估练习结果接口 (使用数据库提示词模板)

    使用大模型分析对话历史,生成评估结果JSON
    """
    print(f"\n{'='*60}")
    print(f"收到评估请求:")
    print(f"  记录ID: {request.record_id}")
    print(f"  课程名称: {request.course_name}")
    print(f"  客户名称: {request.virtual_customer_name}")
    print(f"  对话轮数: {len(request.conversation_history) if request.conversation_history else 0}")
    print(f"{'='*60}\n")

    try:
        # 1. 从数据库查询评估提示词模板
        template = query_prompt_template("EVALUATION_SYSTEM_PROMPT")

        if not template:
            # 如果未找到模板,使用默认评估提示词
            print("⚠️ 未找到 EVALUATION_SYSTEM_PROMPT 模板,使用默认提示词")
            evaluation_prompt = _get_default_evaluation_prompt(request)
        else:
            # 2. 组装对话历史
            conversation_text = ""
            if request.conversation_history and len(request.conversation_history) > 0:
                conversation_text = "\n".join([
                    f"{'学员' if msg.get('role') == 'user' else '客户'}: {msg.get('content', '')}"
                    for msg in request.conversation_history[:20]  # 只取前20轮对话
                ])

            # 3. 替换模板变量
            prompt_content = template['content']
            variables = {
                'conversation_history': conversation_text if conversation_text else "无对话记录",
                'available_courses': "暂无可推荐课程"  # TODO: 从数据库查询可用课程
            }

            # 替换提示词中的变量
            for key, value in variables.items():
                prompt_content = prompt_content.replace(f'{{{{{key}}}}}', str(value))

            evaluation_prompt = prompt_content

        # 创建模型实例
        model = create_model_instance()
        agent = create_deep_agent(
            model=model,
            tools=[],
            system_prompt="你是一位资深的银行培训经理,擅长评估客户经理的销售能力。你总是返回严格的JSON格式。"
        )

        # 调用模型生成评估结果
        result = agent.invoke({"messages": [{"role": "user", "content": evaluation_prompt}]})
        response_text = extract_response(result)

        print(f"AI 返回的原始文本:\n{response_text}\n")

        # 解析 JSON 响应
        # 尝试提取 JSON (可能包含在代码块中)
        import re
        json_match = re.search(r'```json\s*(.*?)\s*```', response_text, re.DOTALL)
        if json_match:
            json_str = json_match.group(1)
        else:
            # 尝试直接解析
            json_match = re.search(r'\{.*\}', response_text, re.DOTALL)
            if json_match:
                json_str = json_match.group(0)
            else:
                json_str = response_text

        evaluation_data = json.loads(json_str)

        # 验证并返回
        return EvaluateResponse(**evaluation_data)

    except json.JSONDecodeError as e:
        print(f"JSON 解析失败: {str(e)}")
        print(f"原始响应: {response_text}")
        raise HTTPException(status_code=500, detail=f"评估结果解析失败: {str(e)}")
    except Exception as e:
        import traceback
        print(f"评估处理错误: {str(e)}")
        print(traceback.format_exc())
        raise HTTPException(status_code=500, detail=f"评估处理错误: {str(e)}")


# ==================== 辅助函数 ====================

async def stream_agent_response(agent, messages: list) -> AsyncGenerator[str, None]:
    """
    流式生成 Agent 响应
    使用 Server-Sent Events (SSE) 格式,支持逐 token 输出

    Args:
        agent: DeepAgent 实例
        messages: 消息列表(包含历史对话和当前消息)
    """
    try:
        # 发送开始事件
        yield f"data: {json.dumps({'type': 'start', 'message': '开始处理...'}, ensure_ascii=False)}\n\n"

        # 使用 stream_mode="messages" 获取 token 级别的流式输出
        async for event in agent.astream_events(
            {"messages": messages},
            version="v2"
        ):
            # 处理 LLM token 流
            if event["event"] == "on_chat_model_stream":
                chunk = event.get("data", {}).get("chunk")
                if chunk and hasattr(chunk, "content"):
                    token = chunk.content
                    if token:
                        # 发送单个 token
                        yield f"data: {json.dumps({'type': 'data', 'content': token}, ensure_ascii=False)}\n\n"

        # 发送完成事件
        yield f"data: {json.dumps({'type': 'done', 'message': '完成'}, ensure_ascii=False)}\n\n"

    except Exception as e:
        # 发送错误事件
        import traceback
        error_detail = f"{str(e)}\n{traceback.format_exc()}"
        print(f"流式响应错误: {error_detail}")
        yield f"data: {json.dumps({'type': 'error', 'message': str(e)}, ensure_ascii=False)}\n\n"


def extract_response(result) -> str:
    """从 agent 结果中提取响应文本"""
    if result and "messages" in result:
        last_message = result["messages"][-1]
        if hasattr(last_message, 'content'):
            return last_message.content
        elif isinstance(last_message, dict) and 'content' in last_message:
            return last_message['content']
        else:
            return str(last_message)
    return "无法提取响应"


def extract_chunk_text(chunk) -> str:
    """从流式 chunk 中提取文本"""
    try:
        # LangGraph stream 返回的格式
        if isinstance(chunk, dict):
            for node_name, node_data in chunk.items():
                if isinstance(node_data, dict) and "messages" in node_data:
                    messages = node_data["messages"]
                    if messages:
                        last_msg = messages[-1]
                        if hasattr(last_msg, 'content'):
                            return last_msg.content
                        elif isinstance(last_msg, dict) and 'content' in last_msg:
                            return last_msg['content']
        return ""
    except Exception:
        return ""


def _get_default_evaluation_prompt(request: EvaluateRequest) -> str:
    """
    获取默认的评估提示词(兜底方案)

    Args:
        request: 评估请求

    Returns:
        默认评估提示词
    """
    evaluation_prompt = f"""你是一位资深的银行培训经理,负责评估客户经理的练习表现。

**评估场景**:
- 课程名称: {request.course_name or '理财产品销售培训'}
- 虚拟客户: {request.virtual_customer_name or '客户'}
- 对话记录: {'已提供' if request.conversation_history else '无'}

**任务**: 请根据对话内容,生成一份完整的评估报告。报告必须严格按照以下JSON格式返回,不要包含任何其他文本:

{{
  "communication_score": <60-95之间的随机整数>,
  "professionalism_score": <60-95之间的随机整数>,
  "compliance_score": <60-95之间的随机整数>,
  "manager_feedback": "<根据三项得分生成80-120字的综合评价>",
  "compliance_issues": [
    {{
      "type": "<问题类型>",
      "description": "<问题描述>",
      "status": "<'通过'或'需校准'>"
    }}
  ],
  "quantified_metrics": [
    {{
      "dimension": "<评估维度>",
      "target": "<目标值,如'≥80%'>",
      "actual": "<实际值,如'75%'>",
      "deviation": "<偏差分析,如'未达标'或'达标'>"
    }}
  ],
  "improvement_suggestions": [
    {{
      "text": "<具体建议文本>",
      "resource": {{
        "label": "<资源名称>",
        "url": "#"
      }}
    }}
  ]
}}

请直接返回JSON,不要包含任何其他解释文字。"""

    # 如果有对话历史,添加到提示词中
    if request.conversation_history and len(request.conversation_history) > 0:
        conversation_text = "\n".join([
            f"{'学员' if msg.get('role') == 'user' else '客户'}: {msg.get('content', '')}"
            for msg in request.conversation_history[:20]  # 只取前20轮对话
        ])
        evaluation_prompt += f"\n\n**对话记录**:\n{conversation_text}"

    return evaluation_prompt


# ==================== 启动入口 ====================

if __name__ == "__main__":
    import uvicorn

    print("=" * 60)
    print("DeepAgents HTTP QA 服务启动中...")
    print("=" * 60)
    print("访问地址:")
    print("  - API 文档: http://localhost:8000/docs")
    print("  - 健康检查: http://localhost:8000/health")
    print("  - 聊天接口: http://localhost:8000/chat")
    print("=" * 60)

    uvicorn.run(
        app,
        host="0.0.0.0",
        port=8001,
        log_level="info"
    )
