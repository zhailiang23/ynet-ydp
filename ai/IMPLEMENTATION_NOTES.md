# AI Agent 实现说明

## 问题

之前的实现使用了 `langchain.agents.create_agent` 和 `langchain.agents.middleware.dynamic_prompt`,这些是 **LangChain 标准 API**,而不是 **DeepAgents** 的 API。

## 解决方案

### 使用 DeepAgents 的正确方式

DeepAgents 不直接支持 middleware 形式的动态提示词,但我们可以通过以下方式实现:

**方案: 每次请求时动态创建 Agent**

```python
from deepagents import create_deep_agent

def create_practice_prompt(course_script, virtual_customer_name, virtual_customer_profile):
    """动态生成系统提示词"""
    return f"你是{virtual_customer_name}..."

@app.post("/chat")
async def chat(request: ChatRequest):
    # 1. 动态生成系统提示词
    system_prompt = create_practice_prompt(
        request.course_script,
        request.virtual_customer_name,
        request.virtual_customer_profile
    )

    # 2. 使用动态提示词创建 Agent
    agent = create_deep_agent(
        model="claude-sonnet-4-5-20250929",
        tools=[],
        system_prompt=system_prompt  # 每次请求使用不同的提示词
    )

    # 3. 调用 Agent
    result = agent.invoke({"messages": messages})
```

### 关键要点

1. **DeepAgents API**: 使用 `from deepagents import create_deep_agent`
2. **动态提示词**: 每次请求时动态生成 `system_prompt` 并创建新的 Agent
3. **对话历史**: 通过 `messages` 参数传递完整的历史对话
4. **性能考虑**: DeepAgents 的 Agent 创建是轻量级的,每次请求创建新 Agent 不会有明显性能问题

### 文件对比

- `http_qa_agent.py` (当前): 使用 DeepAgents ✅
- `http_qa_agent_langchain.py.bak` (备份): 使用 LangChain 标准 API ❌

## 测试结果

所有功能都正常工作:

### 1. 对话历史记忆 ✅
```bash
# 有历史: AI 能引用之前提到的 3.5% 收益率
curl -X POST http://localhost:8000/chat -d @test_history.json
# 响应: "你刚才说是年化收益率3.5%对吧?..."

# 无历史: AI 表示不知道之前的对话
curl -X POST http://localhost:8000/chat -d @test_no_history.json
# 响应: "不好意思,我还没听您说是哪个产品呢..."
```

### 2. 动态提示词 ✅
- 根据 `course_script` 动态生成场景
- 根据 `virtual_customer_name` 设置角色名称
- 根据 `virtual_customer_profile` 定制客户画像

### 3. 角色一致性 ✅
- AI 始终保持客户身份
- 不会跳出角色变成银行工作人员

### 4. 输出格式 ✅
- 纯对话内容,无动作描写
- 不使用星号 (*) 标注

## 总结

使用 DeepAgents 的正确方式是:
1. 使用 `create_deep_agent()` 创建 Agent
2. 通过动态生成 `system_prompt` 实现提示词定制
3. 通过 `messages` 参数传递对话历史

这种方式既保持了 DeepAgents 的架构,又实现了动态提示词和对话历史记忆的功能。
