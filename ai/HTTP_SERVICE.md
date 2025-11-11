# DeepAgents HTTP QA 服务

基于 FastAPI 的 HTTP 问答服务,提供流式和非流式对话接口。

## 快速开始

### 1. 启动服务

```bash
cd ai
source .venv/bin/activate
python http_qa_agent.py
```

服务将在 `http://localhost:8000` 启动。

### 2. 访问 API 文档

打开浏览器访问: http://localhost:8000/docs

这是自动生成的 Swagger UI 文档,可以直接在浏览器中测试 API。

## API 接口

### 1. 健康检查

**GET** `/health`

检查服务是否正常运行。

```bash
curl http://localhost:8000/health
```

响应:
```json
{
  "status": "healthy",
  "agent": "ready",
  "api_key_configured": true
}
```

### 2. 聊天接口

**POST** `/chat`

与 Agent 进行对话,支持流式和非流式响应。

#### 请求体

```json
{
  "message": "你的问题",
  "stream": true  // true=流式, false=非流式
}
```

#### 非流式响应

**请求:**
```bash
curl -X POST http://localhost:8000/chat \
  -H "Content-Type: application/json" \
  -d '{
    "message": "什么是人工智能?",
    "stream": false
  }'
```

**响应:**
```json
{
  "response": "人工智能是..."
}
```

#### 流式响应 (SSE)

**请求:**
```bash
curl -X POST http://localhost:8000/chat \
  -H "Content-Type: application/json" \
  -d '{
    "message": "什么是人工智能?",
    "stream": true
  }'
```

**响应 (Server-Sent Events):**
```
data: {"type": "start", "message": "开始处理..."}

data: {"type": "data", "content": "人工智能"}

data: {"type": "data", "content": "是..."}

data: {"type": "done", "message": "完成"}
```

### SSE 事件类型

流式响应使用 Server-Sent Events (SSE) 格式:

| 事件类型 | 说明 | 数据字段 |
|---------|------|---------|
| `start` | 开始处理 | `message`: 提示信息 |
| `data` | 内容数据 | `content`: 文本片段 |
| `done` | 完成 | `message`: 提示信息 |
| `error` | 错误 | `message`: 错误信息 |

## 使用示例

### Python 客户端

项目已提供测试客户端 `test_http_client.py`。

#### 运行自动测试

```bash
python test_http_client.py
```

这会依次测试:
1. 健康检查
2. 非流式聊天
3. 流式聊天

#### 交互式聊天

```bash
python test_http_client.py interactive
```

进入交互模式,可以实时对话。

### JavaScript/TypeScript 客户端

#### 非流式请求

```javascript
async function chat(message) {
  const response = await fetch('http://localhost:8000/chat', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      message: message,
      stream: false
    })
  });

  const data = await response.json();
  console.log(data.response);
}

chat("什么是人工智能?");
```

#### 流式请求 (SSE)

```javascript
async function streamChat(message) {
  const response = await fetch('http://localhost:8000/chat', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({
      message: message,
      stream: true
    })
  });

  const reader = response.body.getReader();
  const decoder = new TextDecoder();

  while (true) {
    const { done, value } = await reader.read();
    if (done) break;

    const chunk = decoder.decode(value);
    const lines = chunk.split('\n');

    for (const line of lines) {
      if (line.startsWith('data: ')) {
        const data = JSON.parse(line.slice(6));

        switch (data.type) {
          case 'start':
            console.log('[开始]', data.message);
            break;
          case 'data':
            process.stdout.write(data.content);
            break;
          case 'done':
            console.log('\n[完成]', data.message);
            break;
          case 'error':
            console.error('[错误]', data.message);
            break;
        }
      }
    }
  }
}

streamChat("介绍一下 Python");
```

### cURL 示例

#### 非流式

```bash
curl -X POST http://localhost:8000/chat \
  -H "Content-Type: application/json" \
  -d '{
    "message": "用一句话解释机器学习",
    "stream": false
  }'
```

#### 流式

```bash
curl -X POST http://localhost:8000/chat \
  -H "Content-Type: application/json" \
  -d '{
    "message": "介绍一下深度学习",
    "stream": true
  }'
```

## 配置

### 端口配置

默认端口是 8000,可以在 `http_qa_agent.py` 中修改:

```python
uvicorn.run(
    app,
    host="0.0.0.0",
    port=8000,  # 修改这里
    log_level="info"
)
```

### CORS 配置

默认允许所有域名访问。生产环境应该限制具体域名:

```python
app.add_middleware(
    CORSMiddleware,
    allow_origins=["https://yourdomain.com"],  # 指定允许的域名
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)
```

### Agent 配置

修改 `http_qa_agent.py` 中的 `create_deep_agent` 配置:

```python
agent = create_deep_agent(
    system_prompt="你的自定义提示词...",
    model="claude-sonnet-4-5-20250929",  # 可以更换模型
    # tools=[...],  # 添加工具
    # subagents=[...]  # 添加子代理
)
```

## 部署

### 开发环境

```bash
python http_qa_agent.py
```

### 生产环境

使用 Gunicorn + Uvicorn workers:

```bash
# 安装 gunicorn
uv pip install gunicorn

# 启动服务 (4 个 worker)
gunicorn http_qa_agent:app \
  --workers 4 \
  --worker-class uvicorn.workers.UvicornWorker \
  --bind 0.0.0.0:8000 \
  --timeout 300 \
  --access-logfile - \
  --error-logfile -
```

### Docker 部署

创建 `Dockerfile`:

```dockerfile
FROM python:3.12-slim

WORKDIR /app

COPY requirements.txt .
RUN pip install -r requirements.txt

COPY . .

EXPOSE 8000

CMD ["python", "http_qa_agent.py"]
```

构建和运行:

```bash
docker build -t deepagents-qa .
docker run -p 8000:8000 --env-file .env deepagents-qa
```

## 性能优化

### 1. 使用连接池

FastAPI 默认使用连接池,无需额外配置。

### 2. 增加 Worker 数量

生产环境使用多个 worker:

```bash
uvicorn http_qa_agent:app --workers 4
```

### 3. 启用 HTTP/2

```bash
uvicorn http_qa_agent:app --http h2
```

### 4. 添加缓存

可以使用 Redis 缓存常见问题的答案。

## 监控

### 1. 添加日志

FastAPI 自动记录请求日志。查看日志:

```bash
# 启动时指定日志级别
uvicorn http_qa_agent:app --log-level debug
```

### 2. 添加指标

可以集成 Prometheus 进行监控:

```bash
pip install prometheus-fastapi-instrumentator
```

在代码中添加:

```python
from prometheus_fastapi_instrumentator import Instrumentator

Instrumentator().instrument(app).expose(app)
```

## 故障排查

### 问题 1: 连接超时

**原因**: Agent 处理时间过长

**解决**: 增加超时时间

```python
uvicorn.run(app, timeout_keep_alive=300)
```

### 问题 2: CORS 错误

**原因**: 跨域配置不正确

**解决**: 检查 CORS 中间件配置

### 问题 3: 流式响应中断

**原因**: 网络不稳定或超时

**解决**:
1. 检查网络连接
2. 增加客户端超时时间
3. 添加重试机制

## 安全建议

1. **API Key 保护**: 不要在代码中硬编码 API Key
2. **速率限制**: 添加请求频率限制
3. **身份验证**: 生产环境添加 API 认证
4. **HTTPS**: 使用 HTTPS 协议
5. **输入验证**: 验证和清理用户输入

## 参考资源

- [FastAPI 文档](https://fastapi.tiangolo.com/)
- [Server-Sent Events 规范](https://html.spec.whatwg.org/multipage/server-sent-events.html)
- [DeepAgents 文档](https://docs.langchain.com/oss/python/deepagents/overview)
