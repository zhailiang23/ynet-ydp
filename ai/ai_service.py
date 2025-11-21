"""
统一AI服务 - 整合问答Agent和剧本生成
提供两个核心服务:
1. /chat - 智能对话服务(流式/非流式)
2. /generate-script - 剧本生成服务
3. /evaluate - AI评估服务
"""
import os
import json
import re
import pymysql
from pathlib import Path
from typing import AsyncGenerator, Optional, Dict, Any, List
from dataclasses import dataclass

from dotenv import load_dotenv
from fastapi import FastAPI, HTTPException
from fastapi.responses import StreamingResponse
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel, Field

from deepagents import create_deep_agent
from langchain_openai import ChatOpenAI


# ==================== 配置 ====================

# 加载 .env 文件
env_path = Path(__file__).parent / '.env'
if env_path.exists():
    load_dotenv(env_path)
    print(f"✓ 已加载配置文件: {env_path}")

# 模型配置
MODEL_PROVIDER = os.environ.get("MODEL_PROVIDER", "deepseek")
MODEL_NAME = os.environ.get("MODEL_NAME", "deepseek-ai/DeepSeek-V3.1-Terminus")

# 数据库配置
DB_HOST = os.environ.get("DB_HOST", "127.0.0.1")
DB_PORT = int(os.environ.get("DB_PORT", "3306"))
DB_USER = os.environ.get("DB_USER", "root")
DB_PASSWORD = os.environ.get("DB_PASSWORD", "123456")
DB_NAME = os.environ.get("DB_NAME", "ruoyi-vue-pro")

# 检查对应的 API Key
if MODEL_PROVIDER == "deepseek":
    api_key = os.environ.get("DEEPSEEK_API_KEY")
    api_base = os.environ.get("DEEPSEEK_API_BASE", "https://api.siliconflow.cn/v1")
    if not api_key:
        raise RuntimeError("错误: 使用 DeepSeek 时请设置环境变量 DEEPSEEK_API_KEY")
else:
    api_key = os.environ.get("ANTHROPIC_API_KEY")
    api_base = None
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


def query_case_content(case_id: int) -> str:
    """查询案例内容"""
    if not case_id:
        return ""

    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            sql = """
                SELECT title, content, tags
                FROM crm_practice_case
                WHERE id = %s AND deleted = 0 AND tenant_id = 1
            """
            cursor.execute(sql, (case_id,))
            result = cursor.fetchone()
            if result:
                return f"案例标题: {result['title']}\n案例标签: {result.get('tags', '')}\n案例内容: {result.get('content', '')}"
            return ""
    finally:
        conn.close()


def query_material_content(material_id: int) -> str:
    """查询材料内容"""
    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            sql = """
                SELECT name, content, file_type
                FROM crm_practice_material
                WHERE id = %s AND deleted = 0 AND tenant_id = 1
            """
            cursor.execute(sql, (material_id,))
            result = cursor.fetchone()
            if result:
                return f"材料名称: {result['name']}\n文件类型: {result.get('file_type', '')}\n材料内容: {result.get('content', '')}"
            return ""
    finally:
        conn.close()


def query_skill_content(skill_id: int) -> str:
    """查询技能内容"""
    if not skill_id:
        return ""

    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            sql = """
                SELECT name, category, script_template
                FROM crm_practice_skill
                WHERE id = %s AND deleted = 0 AND tenant_id = 1
            """
            cursor.execute(sql, (skill_id,))
            result = cursor.fetchone()
            if result:
                return f"技能名称: {result['name']}\n技能类别: {result.get('category', '')}\n技能模板: {result.get('script_template', '')}"
            return ""
    finally:
        conn.close()


def query_script_info_for_generation(script_id: int) -> Dict[str, Any]:
    """通过剧本ID查询剧本完整信息 (用于剧本生成)"""
    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            sql = """
                SELECT id, name, description, difficulty_level, marketing_step,
                       case_id, skill_id, material_ids
                FROM crm_practice_script
                WHERE id = %s AND deleted = 0 AND tenant_id = 1
            """
            cursor.execute(sql, (script_id,))
            result = cursor.fetchone()
            if not result:
                raise HTTPException(status_code=404, detail=f"剧本ID {script_id} 不存在")
            return result
    finally:
        conn.close()


def query_practice_record_for_evaluation(record_id: int) -> Dict[str, Any]:
    """
    查询练习记录的完整信息用于评估

    Args:
        record_id: 练习记录ID

    Returns:
        包含记录、对话历史、课程、虚拟客户等信息的字典
    """
    conn = get_db_connection()
    try:
        with conn.cursor(pymysql.cursors.DictCursor) as cursor:
            # 1. 查询练习记录基本信息
            cursor.execute("""
                SELECT id, course_id, user_id, vcustomer_id, virtual_customer_id,
                       record_no, status, start_time, end_time, duration
                FROM crm_practice_user_record
                WHERE id = %s AND deleted = 0
            """, (record_id,))
            record = cursor.fetchone()

            if not record:
                raise HTTPException(status_code=404, detail=f"练习记录ID {record_id} 不存在")

            # 2. 查询对话历史
            cursor.execute("""
                SELECT id, sequence_no, role, message_content, message_time
                FROM crm_practice_conversation
                WHERE record_id = %s AND deleted = 0
                ORDER BY sequence_no ASC
            """, (record_id,))
            conversations = cursor.fetchall()

            # 3. 查询课程信息
            course = None
            if record.get('course_id'):
                cursor.execute("""
                    SELECT id, name, description, hard, script_id, standard
                    FROM crm_practice_course
                    WHERE id = %s AND deleted = 0
                """, (record['course_id'],))
                course = cursor.fetchone()

            # 4. 查询虚拟客户信息
            virtual_customer = None
            customer_id = record.get('virtual_customer_id') or record.get('vcustomer_id')
            if customer_id:
                cursor.execute("""
                    SELECT id, name, gender, age, occupation, industry,
                           personality_type, risk_preference, memo
                    FROM crm_practice_virtual_customer
                    WHERE id = %s AND deleted = 0
                """, (customer_id,))
                virtual_customer = cursor.fetchone()

            return {
                'record': record,
                'conversations': conversations,
                'course': course,
                'virtual_customer': virtual_customer
            }
    finally:
        conn.close()


def query_script_info_for_chat(script_id: int) -> dict:
    """
    根据剧本ID查询剧本相关信息 (用于对话)

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


def create_llm():
    """创建 LLM 实例 (用于剧本生成,不使用streaming)"""
    if MODEL_PROVIDER == "deepseek":
        return ChatOpenAI(
            model=MODEL_NAME,
            api_key=api_key,
            base_url=api_base,
            temperature=0.7
        )
    else:
        # Anthropic Claude
        from langchain_anthropic import ChatAnthropic
        return ChatAnthropic(
            model=MODEL_NAME,
            api_key=api_key,
            temperature=0.7
        )


# ==================== 提示词生成 ====================

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


def build_script_prompt(script_id: int) -> str:
    """组装剧本生成提示词"""
    # 1. 通过剧本ID查询剧本信息
    script_info = query_script_info_for_generation(script_id)

    # 2. 查询提示词模板
    template = query_prompt_template("SCRIPT_CREATE_SYSTEM_PROMPT")
    if not template:
        raise HTTPException(status_code=500, detail="未找到剧本生成提示词模板")

    prompt_content = template['content']

    # 3. 查询相关数据
    case_id = script_info.get('case_id')
    skill_id = script_info.get('skill_id')
    material_ids = script_info.get('material_ids')

    case_content = query_case_content(case_id) if case_id else ""
    skill_content = query_skill_content(skill_id) if skill_id else ""

    # 获取第一个材料ID
    material_content = ""
    if material_ids:
        first_material_id = int(material_ids.split(',')[0].strip())
        material_content = query_material_content(first_material_id)

    # 4. 替换变量
    variables = {
        'case_content': case_content if case_content else "无关联案例",
        'material_content': material_content if material_content else "无关联材料",
        'skill_content': skill_content if skill_content else "无关联技能",
        'marketing_step': script_info.get('marketing_step', ''),
        'difficulty_level': script_info.get('difficulty_level', ''),
        'script_description': script_info.get('description', '根据提供的信息生成合适的剧本')
    }

    # 替换提示词中的变量
    for key, value in variables.items():
        prompt_content = prompt_content.replace(f'{{{{{key}}}}}', str(value))

    return prompt_content


# ==================== LLM 调用 ====================

def generate_script_content(prompt: str) -> Dict[str, Any]:
    """调用大模型生成剧本"""
    llm = create_llm()

    # 调用 LLM 生成剧本
    response = llm.invoke(prompt)
    content = response.content

    # 尝试解析 JSON
    try:
        # 移除 markdown 代码块标记
        if content.startswith("```json"):
            content = content[7:]
        if content.startswith("```"):
            content = content[3:]
        if content.endswith("```"):
            content = content[:-3]
        content = content.strip()

        # 解析 JSON
        script_data = json.loads(content)
        return script_data
    except json.JSONDecodeError as e:
        print(f"JSON 解析失败: {e}")
        print(f"原始内容: {content[:500]}...")
        # 如果解析失败,返回原始文本
        return {
            "raw_content": content,
            "parse_error": str(e)
        }


# ==================== FastAPI 应用 ====================

app = FastAPI(
    title="AI Service API",
    description="统一AI服务: 问答对话 + 剧本生成 + AI评估",
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


class ChatResponse(BaseModel):
    """聊天响应（非流式）"""
    response: str


class ScriptGenerateRequest(BaseModel):
    """剧本生成请求参数"""
    script_id: int = Field(..., description="剧本ID(必填,通过ID查询所有其他信息)")


class ScriptGenerateResponse(BaseModel):
    """剧本生成响应"""
    success: bool
    message: str
    script_content: Optional[Dict[str, Any]] = None


class EvaluateRequest(BaseModel):
    """评估请求"""
    record_id: int  # 练习记录ID (必填,其他信息从数据库查询)


class EvaluateResponse(BaseModel):
    """评估响应 (完整的结构化评估结果)"""
    # 基础信息
    record_id: int
    user_id: int
    course_id: int
    record_no: str
    start_time: str
    end_time: Optional[str]
    duration: Optional[int]

    # 维度评分
    communication_score: int  # 沟通逻辑得分
    professionalism_score: int  # 专业能力得分
    compliance_score: int  # 合规表现得分
    need_capture_score: Optional[int] = None  # 需求捕捉精度
    solution_rationality_score: Optional[int] = None  # 方案合理性

    # 总分和完成率
    total_score: float
    completion_rate: float

    # AI 评估总结
    ai_summary: str
    strengths: str  # 优势
    weaknesses: str  # 劣势
    recommendations: str  # 建议

    # 详细评估数据
    dimension_scores: Dict[str, Any]  # 各维度详细评分
    compliance_issues: List[Dict[str, Any]]  # 合规问题列表
    quantified_metrics: List[Dict[str, Any]]  # 量化指标
    improvement_suggestions: List[Dict[str, Any]]  # 提升建议

    # 可选的扩展数据
    growth_data: Optional[Dict[str, Any]] = None  # 成长数据
    manager_feedback: Optional[str] = None  # 经理反馈


# ==================== API 端点 ====================

@app.get("/")
async def root():
    """根路径 - API 信息"""
    return {
        "name": "AI Service API",
        "version": "1.0.0",
        "services": {
            "chat": "/chat (POST) - 智能对话服务 (流式/非流式)",
            "generate_script": "/generate-script (POST) - 剧本生成服务",
            "evaluate": "/evaluate (POST) - AI评估服务",
        },
        "endpoints": {
            "health": "/health (GET) - 健康检查",
            "docs": "/docs (GET) - API文档"
        }
    }


@app.get("/health")
async def health():
    """健康检查"""
    return {
        "status": "healthy",
        "model_provider": MODEL_PROVIDER,
        "model_name": MODEL_NAME,
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
    print(f"收到对话请求:")
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


@app.post("/generate-script", response_model=ScriptGenerateResponse)
async def generate_script_endpoint(request: ScriptGenerateRequest):
    """
    生成剧本

    参数:
    - script_id: 剧本ID(必填,通过ID查询所有其他信息)

    返回:
    - success: 是否成功
    - message: 提示消息
    - script_content: 生成的剧本内容(JSON格式)
    """
    print(f"\n{'='*60}")
    print(f"收到剧本生成请求:")
    print(f"  剧本ID: {request.script_id}")
    print(f"{'='*60}\n")

    try:
        # 1. 组装提示词
        prompt = build_script_prompt(request.script_id)

        print(f"提示词组装完成,长度: {len(prompt)} 字符")

        # 2. 调用大模型生成剧本
        script_content = generate_script_content(prompt)

        print(f"剧本生成成功")

        return ScriptGenerateResponse(
            success=True,
            message="剧本生成成功",
            script_content=script_content
        )

    except HTTPException:
        raise
    except Exception as e:
        import traceback
        print(f"剧本生成错误: {str(e)}")
        print(traceback.format_exc())
        return ScriptGenerateResponse(
            success=False,
            message=f"剧本生成失败: {str(e)}",
            script_content=None
        )


@app.post("/evaluate", response_model=EvaluateResponse)
async def evaluate(request: EvaluateRequest):
    """
    评估练习结果接口 (从数据库读取完整信息)

    1. 通过 record_id 从数据库查询练习记录、对话历史、课程、虚拟客户等信息
    2. 使用数据库提示词模板生成评估
    3. 返回完整的结构化评估结果
    """
    print(f"\n{'='*60}")
    print(f"收到评估请求:")
    print(f"  记录ID: {request.record_id}")
    print(f"{'='*60}\n")

    try:
        # 1. 从数据库查询完整的练习记录信息
        practice_data = query_practice_record_for_evaluation(request.record_id)
        record = practice_data['record']
        conversations = practice_data['conversations']
        course = practice_data['course']
        virtual_customer = practice_data['virtual_customer']

        print(f"查询到练习记录:")
        print(f"  记录编号: {record.get('record_no')}")
        print(f"  课程名称: {course.get('name') if course else '无'}")
        print(f"  虚拟客户: {virtual_customer.get('name') if virtual_customer else '无'}")
        print(f"  对话轮数: {len(conversations)}")

        # 2. 组装对话历史文本
        conversation_text = "\n".join([
            f"{'学员' if conv.get('role') == 'user' else '客户'}: {conv.get('message_content', '')}"
            for conv in conversations
        ])

        # 如果没有对话记录,返回默认的低分评估
        if not conversation_text or len(conversations) == 0:
            print("⚠️ 该练习记录没有对话内容,返回默认评估")
            return _build_evaluation_response(
                record=record,
                course=course,
                evaluation_json={
                    "evaluationResult": {
                        "dimensionScores": {
                            "communication_score": 0,
                            "negotiation_score": 0,
                            "closing_score": 0,
                            "objection_handling_score": 0,
                            "product_knowledge_score": 0
                        },
                        "aiSummary": "本次培训未进行实际对话练习,无法进行有效评估",
                        "strengths": "无",
                        "weaknesses": "未进行对话练习",
                        "recommendations": "请完成对话练习后再进行评估"
                    }
                }
            )

        # 3. 从数据库查询评估提示词模板
        template = query_prompt_template("EVALUATION_SYSTEM_PROMPT")

        if not template:
            print("⚠️ 未找到 EVALUATION_SYSTEM_PROMPT 模板,使用默认提示词")
            evaluation_prompt = _build_default_evaluation_prompt(
                conversation_text,
                course.get('name') if course else '未知课程',
                virtual_customer.get('name') if virtual_customer else '客户'
            )
        else:
            # 替换模板变量
            prompt_content = template['content']
            variables = {
                'conversation_history': conversation_text,
                'available_courses': "暂无可推荐课程"  # TODO: 从数据库查询可用课程
            }

            for key, value in variables.items():
                prompt_content = prompt_content.replace(f'{{{{{key}}}}}', str(value))

            evaluation_prompt = prompt_content

        # 4. 调用 AI 模型生成评估
        print("正在调用 AI 模型生成评估...")
        model = create_model_instance()
        agent = create_deep_agent(
            model=model,
            tools=[],
            system_prompt="你是一位资深的银行培训经理,擅长评估客户经理的销售能力。你总是返回严格的JSON格式。"
        )

        result = agent.invoke({"messages": [{"role": "user", "content": evaluation_prompt}]})
        response_text = extract_response(result)

        print(f"AI 返回评估结果,长度: {len(response_text)} 字符")
        print(f"AI 原始响应内容:\n{response_text}\n")

        # 5. 解析 AI 返回的 JSON
        evaluation_json = _parse_evaluation_json(response_text)

        # 6. 组装完整的评估响应
        evaluation_result = _build_evaluation_response(
            record=record,
            course=course,
            evaluation_json=evaluation_json
        )

        print(f"✓ 评估完成: 总分 {evaluation_result.total_score}")

        return evaluation_result

    except HTTPException:
        raise
    except Exception as e:
        import traceback
        print(f"评估处理错误: {str(e)}")
        print(traceback.format_exc())
        raise HTTPException(status_code=500, detail=f"评估处理错误: {str(e)}")


def _parse_evaluation_json(response_text: str) -> Dict[str, Any]:
    """
    解析 AI 返回的评估 JSON

    Args:
        response_text: AI 返回的原始文本

    Returns:
        解析后的 JSON 字典
    """
    # 尝试提取 JSON (可能包含在代码块中)
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

    return json.loads(json_str)


def _build_evaluation_response(
    record: Dict[str, Any],
    course: Optional[Dict[str, Any]],
    evaluation_json: Dict[str, Any]
) -> EvaluateResponse:
    """
    构建完整的评估响应对象

    Args:
        record: 练习记录信息
        course: 课程信息
        evaluation_json: AI 生成的评估 JSON

    Returns:
        EvaluateResponse 对象
    """
    # 提取评估结果中的核心数据
    eval_result = evaluation_json.get('evaluationResult', {})
    basic_info = eval_result.get('basicInfo', {})
    dimension_scores = eval_result.get('dimensionScores', {})
    extended_data = eval_result.get('extendedData', {})

    # 计算总分 (如果 AI 返回的 JSON 中没有)
    total_score = basic_info.get('totalScore', 0)
    if not total_score or total_score == 0:
        # 根据各维度得分计算总分
        comm_score = dimension_scores.get('communication_score', 0)
        prof_score = dimension_scores.get('professionalism_score', 0) or dimension_scores.get('product_knowledge_score', 0)
        comp_score = dimension_scores.get('compliance_score', 0)

        total_score = round((comm_score * 0.25 + prof_score * 0.25 + comp_score * 0.25) / 0.75 * 100, 2)

    # 构建响应
    return EvaluateResponse(
        # 基础信息
        record_id=record.get('id'),
        user_id=record.get('user_id'),
        course_id=record.get('course_id'),
        record_no=record.get('record_no'),
        start_time=str(record.get('start_time')) if record.get('start_time') else "",
        end_time=str(record.get('end_time')) if record.get('end_time') else None,
        duration=record.get('duration'),

        # 维度评分
        communication_score=dimension_scores.get('communication_score', 0),
        professionalism_score=dimension_scores.get('professionalism_score', 0) or dimension_scores.get('product_knowledge_score', 0),
        compliance_score=dimension_scores.get('compliance_score', 0),
        need_capture_score=dimension_scores.get('need_capture_score'),
        solution_rationality_score=dimension_scores.get('solution_rationality_score'),

        # 总分和完成率
        total_score=total_score,
        completion_rate=basic_info.get('completionRate', 100.0),

        # AI 评估总结
        ai_summary=eval_result.get('aiSummary', ''),
        strengths=eval_result.get('strengths', ''),
        weaknesses=eval_result.get('weaknesses', ''),
        recommendations=eval_result.get('recommendations', ''),

        # 详细评估数据
        dimension_scores=extended_data.get('skillScores', dimension_scores),
        compliance_issues=extended_data.get('complianceCheck', {}).get('issues', []),
        quantified_metrics=extended_data.get('quantitativeIndicators', []),
        improvement_suggestions=extended_data.get('improvementSuggestions', []),

        # 可选数据
        growth_data=eval_result.get('growthData'),
        manager_feedback=extended_data.get('managerFeedback', {}).get('overallPerformance')
    )


def _build_default_evaluation_prompt(
    conversation_text: str,
    course_name: str,
    virtual_customer_name: str
) -> str:
    """
    构建默认的评估提示词(兜底方案)

    Args:
        conversation_text: 对话历史文本
        course_name: 课程名称
        virtual_customer_name: 虚拟客户名称

    Returns:
        默认评估提示词
    """
    return f"""你是一位资深的银行培训经理,负责评估客户经理的练习表现。

**评估场景**:
- 课程名称: {course_name}
- 虚拟客户: {virtual_customer_name}
- 对话记录: {'已提供' if conversation_text else '无'}

**对话记录**:
{conversation_text}

**任务**: 请根据对话内容,生成一份完整的评估报告,必须严格按照以下JSON格式返回。

请注意输出格式必须严格遵循 evaluationResult 的结构,包含 basicInfo, dimensionScores, aiSummary, strengths, weaknesses, recommendations 等完整字段。
"""


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


# ==================== 启动入口 ====================

if __name__ == "__main__":
    import uvicorn

    print("=" * 60)
    print("AI 统一服务启动中...")
    print("=" * 60)
    print("提供的服务:")
    print("  1. 智能对话服务 (流式/非流式)")
    print("  2. 剧本生成服务")
    print("  3. AI评估服务")
    print("=" * 60)
    print("访问地址:")
    print("  - API 文档: http://localhost:8000/docs")
    print("  - 健康检查: http://localhost:8000/health")
    print("  - 对话接口: http://localhost:8000/chat")
    print("  - 剧本生成: http://localhost:8000/generate-script")
    print("  - AI评估: http://localhost:8000/evaluate")
    print("=" * 60)

    uvicorn.run(
        app,
        host="0.0.0.0",
        port=8000,
        log_level="info"
    )
