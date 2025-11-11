"""
HTTP QA Agent 测试客户端
演示如何调用流式和非流式 API
"""
import requests
import json


API_BASE_URL = "http://localhost:8000"


def test_health():
    """测试健康检查接口"""
    print("=" * 60)
    print("测试健康检查接口")
    print("=" * 60)

    response = requests.get(f"{API_BASE_URL}/health")
    print(f"状态码: {response.status_code}")
    print(f"响应: {json.dumps(response.json(), indent=2, ensure_ascii=False)}")
    print()


def test_non_stream_chat():
    """测试非流式聊天"""
    print("=" * 60)
    print("测试非流式聊天")
    print("=" * 60)

    message = "用一句话解释什么是人工智能"
    print(f"发送消息: {message}")

    response = requests.post(
        f"{API_BASE_URL}/chat",
        json={
            "message": message,
            "stream": False
        }
    )

    print(f"状态码: {response.status_code}")
    if response.status_code == 200:
        result = response.json()
        print(f"响应: {result['response']}")
    else:
        print(f"错误: {response.text}")
    print()


def test_stream_chat():
    """测试流式聊天"""
    print("=" * 60)
    print("测试流式聊天 (SSE)")
    print("=" * 60)

    message = "介绍一下 Python 编程语言的特点"
    print(f"发送消息: {message}")
    print("流式响应:")
    print("-" * 60)

    response = requests.post(
        f"{API_BASE_URL}/chat",
        json={
            "message": message,
            "stream": True
        },
        stream=True
    )

    if response.status_code == 200:
        for line in response.iter_lines():
            if line:
                line_str = line.decode('utf-8')
                # SSE 格式: data: {...}
                if line_str.startswith('data: '):
                    data_str = line_str[6:]  # 去掉 "data: " 前缀
                    try:
                        data = json.loads(data_str)
                        event_type = data.get('type')

                        if event_type == 'start':
                            print(f"[开始] {data.get('message')}")
                        elif event_type == 'data':
                            content = data.get('content', '')
                            print(content, end='', flush=True)
                        elif event_type == 'done':
                            print(f"\n[完成] {data.get('message')}")
                        elif event_type == 'error':
                            print(f"\n[错误] {data.get('message')}")
                    except json.JSONDecodeError:
                        pass
    else:
        print(f"错误: {response.status_code} - {response.text}")

    print("-" * 60)
    print()


def interactive_chat():
    """交互式聊天"""
    print("=" * 60)
    print("交互式流式聊天")
    print("=" * 60)
    print("提示: 输入 'quit' 或 'exit' 退出")
    print("=" * 60)
    print()

    while True:
        try:
            user_input = input("你: ").strip()

            if user_input.lower() in ['quit', 'exit', '退出', 'q']:
                print("\n再见！")
                break

            if not user_input:
                continue

            print("Agent: ", end='', flush=True)

            response = requests.post(
                f"{API_BASE_URL}/chat",
                json={
                    "message": user_input,
                    "stream": True
                },
                stream=True
            )

            if response.status_code == 200:
                for line in response.iter_lines():
                    if line:
                        line_str = line.decode('utf-8')
                        if line_str.startswith('data: '):
                            data_str = line_str[6:]
                            try:
                                data = json.loads(data_str)
                                event_type = data.get('type')

                                if event_type == 'data':
                                    content = data.get('content', '')
                                    print(content, end='', flush=True)
                                elif event_type == 'error':
                                    print(f"\n错误: {data.get('message')}")
                            except json.JSONDecodeError:
                                pass
            else:
                print(f"\n错误: {response.status_code} - {response.text}")

            print("\n")

        except KeyboardInterrupt:
            print("\n\n再见！")
            break
        except Exception as e:
            print(f"\n错误: {e}")


if __name__ == "__main__":
    import sys

    if len(sys.argv) > 1 and sys.argv[1] == "interactive":
        # 交互模式
        interactive_chat()
    else:
        # 运行所有测试
        test_health()
        test_non_stream_chat()
        test_stream_chat()

        print("\n" + "=" * 60)
        print("提示: 运行 'python test_http_client.py interactive' 进入交互模式")
        print("=" * 60)
