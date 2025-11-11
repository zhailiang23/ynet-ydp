"""
HTTP 问答 Agent 服务 - 使用 DeepAgents + FastAPI
提供 REST API 接口进行流式对话
支持动态提示词,可根据课程脚本和虚拟客户信息定制
"""
import os
import json
from pathlib import Path
from typing import AsyncGenerator, Optional
from dataclasses import dataclass

from dotenv import load_dotenv
from fastapi import FastAPI, HTTPException
from fastapi.responses import StreamingResponse
from fastapi.middleware.cors import CORSMiddleware
from pydantic import BaseModel

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
    根据陪练上下文动态生成提示词

    Args:
        course_script: 课程脚本内容
        virtual_customer_name: 虚拟客户姓名
        virtual_customer_profile: 虚拟客户画像

    Returns:
        动态生成的系统提示词
    """
    # 基础提示词
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


# ==================== API 端点 ====================

@app.get("/")
async def root():
    """根路径 - API 信息"""
    return {
        "name": "DeepAgents QA API",
        "version": "1.0.0",
        "endpoints": {
            "chat": "/chat (POST)",
            "health": "/health (GET)",
            "docs": "/docs (GET)"
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


# ==================== 辅助函数 ====================

async def stream_agent_response(agent, messages: list) -> AsyncGenerator[str, None]:
    """
    流式生成 Agent 响应
    使用 Server-Sent Events (SSE) 格式

    Args:
        agent: DeepAgent 实例
        messages: 消息列表(包含历史对话和当前消息)
    """
    try:
        # 发送开始事件
        yield f"data: {json.dumps({'type': 'start', 'message': '开始处理...'}, ensure_ascii=False)}\n\n"

        # 使用 agent.stream() 进行流式处理
        accumulated_text = ""

        for chunk in agent.stream(
            {"messages": messages},
            stream_mode="updates"
        ):
            # 提取文本内容
            text = extract_chunk_text(chunk)
            if text:
                accumulated_text += text
                # 发送数据事件
                yield f"data: {json.dumps({'type': 'data', 'content': text}, ensure_ascii=False)}\n\n"

        # 发送完成事件
        yield f"data: {json.dumps({'type': 'done', 'message': '完成'}, ensure_ascii=False)}\n\n"

    except Exception as e:
        # 发送错误事件
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
        port=8000,
        log_level="info"
    )
