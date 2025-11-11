"""
简单的问答 Agent - 使用 DeepAgents
这是一个最基础的问答代理，展示如何使用 DeepAgents 创建智能问答系统
"""
import os
from pathlib import Path
from dotenv import load_dotenv
from deepagents import create_deep_agent


def main():
    """主函数：创建并运行简单问答 agent"""

    # 加载 .env 文件
    env_path = Path(__file__).parent / '.env'
    if env_path.exists():
        load_dotenv(env_path)
        print(f"✓ 已加载配置文件: {env_path}")

    # 检查 API Key
    api_key = os.environ.get("ANTHROPIC_API_KEY")
    if not api_key:
        print("错误: 请设置环境变量 ANTHROPIC_API_KEY")
        print("使用方法: export ANTHROPIC_API_KEY='your-api-key'")
        return

    # 创建一个简单的问答 agent
    # 使用默认配置，不添加额外的工具或子代理
    agent = create_deep_agent(
        system_prompt="""你是一个友好的问答助手。

你的职责：
- 回答用户的各种问题
- 提供清晰、准确的信息
- 如果不确定答案，请诚实地告知用户
- 使用简洁易懂的语言

请用中文回答问题。
""",
        # 使用 Claude Sonnet 4.5 模型（默认）
        model="claude-sonnet-4-5-20250929"
    )

    print("=" * 60)
    print("简单问答 Agent 已启动")
    print("=" * 60)
    print("提示: 输入 'quit' 或 'exit' 退出程序")
    print("=" * 60)
    print()

    # 开始对话循环
    while True:
        try:
            # 获取用户输入
            user_input = input("你: ").strip()

            # 检查退出命令
            if user_input.lower() in ['quit', 'exit', '退出', 'q']:
                print("\n感谢使用！再见！")
                break

            # 跳过空输入
            if not user_input:
                continue

            # 调用 agent
            print("\nAgent: ", end="", flush=True)

            # 使用 stream 方法流式输出响应
            result = agent.invoke({
                "messages": [{"role": "user", "content": user_input}]
            })

            # 打印 agent 的响应
            if result and "messages" in result:
                last_message = result["messages"][-1]
                if hasattr(last_message, 'content'):
                    print(last_message.content)
                else:
                    print(last_message)

            print()  # 空行分隔

        except KeyboardInterrupt:
            print("\n\n程序被中断。再见！")
            break
        except Exception as e:
            print(f"\n错误: {e}")
            print("请重试或输入 'quit' 退出。\n")


if __name__ == "__main__":
    main()
