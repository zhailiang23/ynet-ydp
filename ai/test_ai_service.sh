#!/bin/bash
# AI统一服务测试脚本

BASE_URL="http://localhost:8001"

echo "======================================"
echo "AI 统一服务测试"
echo "======================================"
echo ""

# 1. 健康检查
echo "1. 健康检查..."
curl -s "$BASE_URL/health" | python3 -m json.tool
echo ""
echo ""

# 2. 测试对话服务 (非流式)
echo "2. 测试对话服务 (非流式)..."
curl -s -X POST "$BASE_URL/chat" \
  -H "Content-Type: application/json" \
  -d '{
    "message": "你好",
    "stream": false,
    "virtual_customer_name": "测试客户"
  }' | python3 -m json.tool
echo ""
echo ""

# 3. 测试剧本生成服务
echo "3. 测试剧本生成服务 (剧本ID: 26)..."
echo "   (此操作可能需要 3-10 分钟,请耐心等待...)"
curl -s -X POST "$BASE_URL/generate-script" \
  -H "Content-Type: application/json" \
  -d '{"script_id": 26}' | python3 -m json.tool | head -80
echo "   ..."
echo ""

echo "======================================"
echo "测试完成!"
echo "======================================"
echo ""
echo "API 文档: $BASE_URL/docs"
echo "日志文件: /tmp/ai_service.log"
