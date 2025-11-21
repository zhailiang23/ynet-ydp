# AI 统一服务文档

## 概述

`ai_service.py` 是一个统一的 AI 服务,整合了原来的两个独立服务:
- `http_qa_agent.py` - 智能对话服务
- `script_generator.py` - 剧本生成服务

## 服务架构

```
┌──────────────────────────────────────┐
│      AI Service (FastAPI)            │
│      Port: 8001                      │
├──────────────────────────────────────┤
│  服务端点:                           │
│  1. /chat - 智能对话 (流式/非流式)   │
│  2. /generate-script - 剧本生成      │
│  3. /evaluate - AI评估               │
│  4. /health - 健康检查               │
│  5. /docs - API文档                  │
└──────────────────────────────────────┘
         │
         ├─► DeepSeek V3.1-Terminus
         └─► Database (MySQL)
```

## 启动服务

### 方式一: 使用启动脚本 (推荐)

```bash
cd /Users/zhailiang/Documents/code/ynet-ydp/ai
./start_ai_service.sh
```

### 方式二: 手动启动

```bash
cd /Users/zhailiang/Documents/code/ynet-ydp/ai
source .venv/bin/activate
python ai_service.py
```

### 方式三: 后台运行

```bash
cd /Users/zhailiang/Documents/code/ynet-ydp/ai
source .venv/bin/activate
nohup python ai_service.py > /tmp/ai_service.log 2>&1 &
```

## 环境配置

在 `.env` 文件中配置:

```bash
# 模型配置
MODEL_PROVIDER=deepseek                          # 模型提供商 (deepseek 或 anthropic)
MODEL_NAME=deepseek-ai/DeepSeek-V3.1-Terminus   # 模型名称
DEEPSEEK_API_KEY=your-api-key-here               # DeepSeek API密钥
DEEPSEEK_API_BASE=https://api.siliconflow.cn/v1 # API Base URL

# 数据库配置
DB_HOST=127.0.0.1
DB_PORT=3306
DB_USER=root
DB_PASSWORD=123456
DB_NAME=ruoyi-vue-pro
```

## API 端点说明

### 1. 健康检查

**请求:**
```bash
GET http://localhost:8001/health
```

**响应:**
```json
{
  "status": "healthy",
  "model_provider": "deepseek",
  "model_name": "deepseek-ai/DeepSeek-V3.1-Terminus",
  "api_key_configured": true
}
```

### 2. 智能对话

**请求:**
```bash
POST http://localhost:8001/chat
Content-Type: application/json

{
  "message": "你好,我想了解理财产品",
  "stream": false,
  "virtual_customer_name": "李女士",
  "virtual_customer_profile": "45岁,有一定理财经验",
  "course_script": "场景:客户咨询理财产品...",
  "history": [
    {"role": "user", "content": "你好"},
    {"role": "assistant", "content": "你好,有什么可以帮到你?"}
  ]
}
```

**响应 (stream=false):**
```json
{
  "response": "作为客户的回答内容..."
}
```

**响应 (stream=true):**
Server-Sent Events (SSE) 流式响应

### 3. 剧本生成

**请求:**
```bash
POST http://localhost:8001/generate-script
Content-Type: application/json

{
  "script_id": 26
}
```

**响应:**
```json
{
  "success": true,
  "message": "剧本生成成功",
  "script_content": {
    "courseDetail": {
      "title": "家庭保险规划专业销售培训剧本",
      "description": "...",
      "coreFramework": {...}
    }
  }
}
```

### 4. AI评估

**请求:**
```bash
POST http://localhost:8001/evaluate
Content-Type: application/json

{
  "record_id": 1,
  "conversation_history": [
    {"role": "user", "content": "李女士,根据大数据分析..."},
    {"role": "assistant", "content": "你们和暴雷的那家机构有什么区别?"}
  ],
  "course_name": "理财产品销售陪练",
  "virtual_customer_name": "李女士"
}
```

**响应:**
```json
{
  "communication_score": 78,
  "professionalism_score": 82,
  "compliance_score": 65,
  "manager_feedback": "本次培训表现良好...",
  "compliance_issues": [...],
  "quantified_metrics": [...],
  "improvement_suggestions": [...]
}
```

## 服务管理

### 查看服务状态

```bash
# 检查进程
ps aux | grep ai_service

# 检查端口
lsof -ti :8001

# 健康检查
curl http://localhost:8001/health
```

### 停止服务

```bash
# 查找并停止进程
ps aux | grep ai_service | grep -v grep | awk '{print $2}' | xargs kill -9

# 或直接停止端口
lsof -ti :8001 | xargs kill -9
```

### 查看日志

```bash
# 实时查看日志
tail -f /tmp/ai_service.log

# 查看最近日志
tail -100 /tmp/ai_service.log
```

## 性能说明

- **对话服务**: 支持流式输出,响应速度快 (< 1秒开始输出)
- **剧本生成**: 耗时较长 (3-10分钟),取决于剧本复杂度和模型速度
- **AI评估**: 中等耗时 (10-30秒),取决于对话历史长度

## 超时配置

服务端默认无超时限制,客户端超时配置:

- **Java HTTP Client**: 600000ms (10分钟) - `ScriptGeneratorClient.java:40`
- **Spring MVC**: 900000ms (15分钟) - `application.yaml`
- **Next.js Proxy**: 900000ms (15分钟) - `next.config.mjs`
- **Frontend Fetch**: 900000ms (15分钟) - `personalized-course.ts`

## 故障排查

### 问题: 服务启动失败

1. 检查端口是否被占用: `lsof -ti :8001`
2. 检查虚拟环境是否激活: `which python` 应该指向 `.venv/bin/python`
3. 检查依赖是否安装: `pip list | grep -E "fastapi|deepagents|pymysql"`
4. 查看错误日志: `cat /tmp/ai_service.log`

### 问题: 数据库连接失败

1. 检查MySQL是否运行: `lsof -ti :3306`
2. 检查数据库配置: `.env` 文件中的 DB_* 配置
3. 测试连接: `mysql -h 127.0.0.1 -u root -p123456 ruoyi-vue-pro`

### 问题: 剧本生成超时

1. 检查AI服务日志: `tail -f /tmp/ai_service.log`
2. 检查网络连接: `curl https://api.siliconflow.cn/v1/models`
3. 检查API密钥是否有效: `.env` 中的 `DEEPSEEK_API_KEY`

## 文件说明

- `ai_service.py` - 统一AI服务主程序 (新)
- `start_ai_service.sh` - 启动脚本 (新)
- `http_qa_agent.py` - 智能对话服务 (已整合,保留备份)
- `script_generator.py` - 剧本生成服务 (已整合,保留备份)
- `.env` - 环境配置文件
- `requirements.txt` - Python依赖

## 迁移指南

### 从旧服务迁移到新服务

旧的服务配置:
```bash
# 旧: 两个独立进程
python script_generator.py  # 端口 8001
python http_qa_agent.py     # 端口 8000
```

新的服务配置:
```bash
# 新: 一个统一进程
python ai_service.py        # 端口 8001
```

### API兼容性

**完全兼容**,无需修改客户端代码:
- `/generate-script` - 保持不变
- `/chat` - 保持不变
- `/evaluate` - 保持不变
- `/health` - 保持不变

### 后端配置更新

`ScriptGeneratorClient.java` 中的URL保持不变:
```java
@Value("${script.generator.url:http://localhost:8001}")
private String generatorUrl;
```

`application-local.yaml` 中保持不变:
```yaml
script:
  generator:
    url: http://localhost:8001
```

## 最佳实践

1. **生产环境部署**: 使用 systemd 或 supervisor 管理进程
2. **日志管理**: 使用 logrotate 定期清理日志
3. **监控**: 定期调用 `/health` 端点进行健康检查
4. **备份**: 定期备份 `.env` 配置文件
5. **更新**: 更新依赖前先测试兼容性

## 联系方式

如有问题,请查看:
1. 日志文件: `/tmp/ai_service.log`
2. API文档: http://localhost:8001/docs
3. 项目文档: `/Users/zhailiang/Documents/code/ynet-ydp/CLAUDE.md`
