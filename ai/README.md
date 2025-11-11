# 简单问答 Agent - DeepAgents 示例

这是一个使用 [DeepAgents](https://docs.langchain.com/oss/python/deepagents/overview) 构建的最简单的问答智能代理。

## 什么是 DeepAgents？

DeepAgents 是 LangChain 推出的一个独立库，用于构建能够处理复杂多步骤任务的智能代理。它基于 LangGraph 构建，受到 Claude Code、Deep Research 和 Manus 等应用的启发。

核心特性：
- **规划能力**: 内置 TodoList 工具用于任务规划
- **文件系统**: 用于上下文管理和长期记忆
- **子代理**: 可以创建专门的子代理处理特定任务

## 项目结构

```
ai/
├── simple_qa_agent.py      # 命令行问答 Agent
├── http_qa_agent.py        # HTTP 服务版本 (FastAPI)
├── test_http_client.py     # HTTP 客户端测试工具
├── requirements.txt        # Python 依赖
├── .env.example           # 环境变量示例
├── README.md              # 本文档
└── HTTP_SERVICE.md        # HTTP 服务详细文档
```

## 安装依赖

### 前置要求

- Python >= 3.10
- uv (Python 包管理器)

### 安装步骤

```bash
# 进入 ai 目录
cd ai

# 使用 uv 创建虚拟环境并安装依赖
uv venv
source .venv/bin/activate  # Linux/Mac
# 或
# .venv\Scripts\activate  # Windows

# 安装依赖
uv pip install -r requirements.txt
```

## 配置

### 1. 设置 Anthropic API Key

你需要一个 Anthropic (Claude) API Key。

```bash
# 方式一：设置环境变量
export ANTHROPIC_API_KEY='your-api-key-here'

# 方式二：创建 .env 文件（推荐）
cp .env.example .env
# 编辑 .env 文件，填入你的 API Key
```

### 2. 获取 API Key

1. 访问 [Anthropic Console](https://console.anthropic.com/)
2. 注册/登录账号
3. 在 API Keys 页面创建新的 API Key
4. 复制 API Key 并保存

## 运行

### 命令行模式

```bash
# 确保已激活虚拟环境并设置 API Key
python simple_qa_agent.py
```

### HTTP 服务模式

```bash
# 启动 HTTP 服务
python http_qa_agent.py
```

服务将在 `http://localhost:8000` 启动。

详细使用说明请查看 [HTTP_SERVICE.md](HTTP_SERVICE.md)

## 使用示例

```
============================================================
简单问答 Agent 已启动
============================================================
提示: 输入 'quit' 或 'exit' 退出程序
============================================================

你: 什么是人工智能？

Agent: 人工智能（Artificial Intelligence，简称AI）是计算机科学的一个分支...

你: quit

感谢使用！再见！
```

## 代码说明

### 核心代码

```python
from deepagents import create_deep_agent

# 创建一个简单的问答 agent
agent = create_deep_agent(
    system_prompt="你是一个友好的问答助手...",
    model="claude-sonnet-4-5-20250929"
)

# 调用 agent
result = agent.invoke({
    "messages": [{"role": "user", "content": "你的问题"}]
})
```

### 关键参数

- `system_prompt`: 系统提示词，定义 agent 的行为和职责
- `model`: 使用的 LLM 模型，默认为 Claude Sonnet 4.5
- `tools`: 可选，提供给 agent 的工具列表（本示例未使用）
- `subagents`: 可选，子代理列表（本示例未使用）

## 扩展功能

这是一个最简单的示例。DeepAgents 支持更多高级功能：

### 1. 添加工具

```python
from langchain.tools import tool

@tool
def get_weather(city: str) -> str:
    """获取指定城市的天气"""
    return f"{city} 的天气是晴天"

agent = create_deep_agent(
    system_prompt="...",
    tools=[get_weather]
)
```

### 2. 添加子代理

```python
agent = create_deep_agent(
    system_prompt="...",
    subagents=[
        {
            "name": "researcher",
            "description": "用于深度研究的子代理",
            "system_prompt": "你是一个研究专家...",
            "tools": [search_tool]
        }
    ]
)
```

### 3. 使用文件系统

```python
from deepagents.backends import FilesystemBackend

agent = create_deep_agent(
    system_prompt="...",
    backend=FilesystemBackend(root_dir="./agent_memory")
)
```

## 常见问题

### Q: 如何更换模型？

A: 修改 `model` 参数：

```python
# 使用 OpenAI GPT-4
from langchain_openai import ChatOpenAI

agent = create_deep_agent(
    model=ChatOpenAI(model="gpt-4")
)
```

### Q: 如何启用流式输出？

A: 使用 `stream` 方法：

```python
for chunk in agent.stream({"messages": [{"role": "user", "content": "你好"}]}):
    print(chunk)
```

### Q: 如何保存对话历史？

A: DeepAgents 使用 LangGraph 的状态管理，可以配置持久化后端：

```python
from deepagents.backends import StoreBackend

agent = create_deep_agent(
    backend=lambda rt: StoreBackend(rt)
)
```

## 参考资源

- [DeepAgents 官方文档](https://docs.langchain.com/oss/python/deepagents/overview)
- [DeepAgents 快速开始](https://docs.langchain.com/oss/python/deepagents/quickstart)
- [LangChain 文档](https://docs.langchain.com/)
- [Anthropic Claude API](https://docs.anthropic.com/)

## 许可证

MIT
