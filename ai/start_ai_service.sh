#!/bin/bash
# AI统一服务启动脚本

cd "$(dirname "$0")"

# 停止旧服务
echo "停止旧的AI服务进程..."
ps aux | grep "python.*script_generator\|python.*http_qa_agent\|python.*ai_service" | grep -v grep | awk '{print $2}' | xargs kill -9 2>/dev/null
lsof -ti :8001 | xargs kill -9 2>/dev/null
sleep 2

# 激活虚拟环境并启动服务
echo "启动AI统一服务..."
source .venv/bin/activate
nohup python ai_service.py > /tmp/ai_service.log 2>&1 &

# 等待启动
sleep 5

# 检查服务状态
if curl -s http://localhost:8001/health > /dev/null 2>&1; then
    echo "✓ AI服务启动成功!"
    echo "  - 健康检查: http://localhost:8001/health"
    echo "  - API文档: http://localhost:8001/docs"
    echo "  - 日志文件: /tmp/ai_service.log"
    echo ""
    echo "提供的服务:"
    echo "  1. 智能对话: POST http://localhost:8001/chat"
    echo "  2. 剧本生成: POST http://localhost:8001/generate-script"
    echo "  3. AI评估: POST http://localhost:8001/evaluate"
else
    echo "✗ AI服务启动失败,请查看日志: /tmp/ai_service.log"
    exit 1
fi
