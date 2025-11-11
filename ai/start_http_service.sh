#!/bin/bash
# 启动 HTTP QA 服务的快捷脚本

echo "=========================================="
echo "启动 DeepAgents HTTP QA 服务"
echo "=========================================="

# 检查虚拟环境
if [ ! -d ".venv" ]; then
    echo "错误: 虚拟环境不存在"
    echo "请先运行: uv venv && source .venv/bin/activate && uv pip install -r requirements.txt"
    exit 1
fi

# 检查 .env 文件
if [ ! -f ".env" ]; then
    echo "警告: .env 文件不存在"
    echo "请从 .env.example 复制并配置 API Key"
    exit 1
fi

# 激活虚拟环境并启动服务
source .venv/bin/activate
python http_qa_agent.py
