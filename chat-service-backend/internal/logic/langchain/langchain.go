package langchain

import (
	"context"
	"fmt"
	"gf-chat/internal/consts"
	"gf-chat/internal/model"
	"gf-chat/internal/service"
	"strings"

	"github.com/duke-git/lancet/v2/slice"
	"github.com/gogf/gf/v2/frame/g"
	"github.com/gogf/gf/v2/os/gctx"
	"github.com/tmc/langchaingo/llms"
	"github.com/tmc/langchaingo/llms/ollama"
	"github.com/tmc/langchaingo/llms/openai"
)

func init() {
	service.RegisterLangchain(newLangchain())
}

type langchain struct {
	llm          llms.Model
	open         bool
	systemPrompt string
}

func newLangchain() *langchain {
	ctx := gctx.New()

	// 读取配置
	open, _ := g.Config().Get(ctx, "langchain.open", false)
	provider, _ := g.Config().Get(ctx, "langchain.provider", "ollama")
	modelName, _ := g.Config().Get(ctx, "langchain.model", "qwen:0.5b")
	systemPrompt, _ := g.Config().Get(ctx, "langchain.systemPrompt", "你是一个专业的客服助手，请友好、专业地回答用户的问题。")

	lc := &langchain{
		open:         open.Bool(),
		systemPrompt: systemPrompt.String(),
	}

	// 如果未开启，不初始化 LLM
	if !lc.open {
		g.Log().Info(ctx, "Langchain AI is disabled")
		return lc
	}

	var err error
	switch provider.String() {
	case "openai":
		// OpenAI 兼容 API（支持硅基流动、DeepSeek 等）
		apiKey, _ := g.Config().Get(ctx, "langchain.apiKey", "")
		baseURL, _ := g.Config().Get(ctx, "langchain.baseURL", "")

		opts := []openai.Option{
			openai.WithModel(modelName.String()),
		}
		if apiKey.String() != "" {
			opts = append(opts, openai.WithToken(apiKey.String()))
		}
		if baseURL.String() != "" {
			opts = append(opts, openai.WithBaseURL(baseURL.String()))
		}

		lc.llm, err = openai.New(opts...)
		if err != nil {
			g.Log().Errorf(ctx, "Failed to create OpenAI LLM: %v", err)
			lc.open = false
			return lc
		}
		g.Log().Infof(ctx, "Langchain AI enabled with OpenAI provider, model: %s, baseURL: %s", modelName.String(), baseURL.String())

	case "ollama":
		fallthrough
	default:
		// Ollama 本地模型
		lc.llm, err = ollama.New(
			ollama.WithModel(modelName.String()),
			ollama.WithSystemPrompt(lc.systemPrompt),
		)
		if err != nil {
			g.Log().Errorf(ctx, "Failed to create Ollama LLM: %v", err)
			lc.open = false
			return lc
		}
		g.Log().Infof(ctx, "Langchain AI enabled with Ollama provider, model: %s", modelName.String())
	}

	return lc
}

func (l *langchain) Ask(ctx context.Context, msg string, messages ...llms.MessageContent) (resp string, err error) {
	if !l.open || l.llm == nil {
		g.Log().Debug(ctx, "Langchain AI is disabled, skipping AI response")
		return "", nil
	}

	// 添加系统提示词
	allMessages := []llms.MessageContent{
		llms.TextParts(llms.ChatMessageTypeSystem, l.systemPrompt),
	}
	allMessages = append(allMessages, messages...)
	allMessages = append(allMessages, llms.TextParts(llms.ChatMessageTypeHuman, msg))

	completion, err := l.llm.GenerateContent(ctx, allMessages,
		llms.WithTemperature(0.7),
		llms.WithTopP(0.9))
	if err != nil {
		g.Log().Errorf(ctx, "Langchain AI error: %v", err)
		return "", err
	}

	if len(completion.Choices) > 0 {
		resp = completion.Choices[0].Content
	}
	return
}

func (l *langchain) MessageToContent(messages []*model.CustomerChatMessage) []llms.MessageContent {
	return slice.Map(messages, func(index int, item *model.CustomerChatMessage) llms.MessageContent {
		role := llms.ChatMessageTypeHuman
		if item.Source == consts.MessageSourceAi || item.Source == consts.MessageSourceAdmin {
			role = llms.ChatMessageTypeAI
		}
		return llms.TextParts(role, item.Content)
	})
}

// AskWithCustomPrompt 使用自定义提示词模板调用 AI
// promptTemplate 中可包含 {{message}} 和 {{history}} 变量
func (l *langchain) AskWithCustomPrompt(ctx context.Context, promptTemplate string, message string, history []*model.CustomerChatMessage) (resp string, err error) {
	if !l.open || l.llm == nil {
		g.Log().Debug(ctx, "Langchain AI is disabled, skipping AI response")
		return "", nil
	}

	// 构建历史对话文本
	var historyText string
	if len(history) > 0 {
		var historyParts []string
		for _, msg := range history {
			role := "用户"
			if msg.Source == consts.MessageSourceAdmin || msg.Source == consts.MessageSourceAi {
				role = "客服"
			}
			historyParts = append(historyParts, fmt.Sprintf("%s: %s", role, msg.Content))
		}
		historyText = strings.Join(historyParts, "\n")
	} else {
		historyText = "无历史对话"
	}

	// 替换模板中的变量
	prompt := promptTemplate
	prompt = strings.ReplaceAll(prompt, "{{message}}", message)
	prompt = strings.ReplaceAll(prompt, "{{history}}", historyText)

	g.Log().Debugf(ctx, "AI prompt after replacement: %s", prompt)

	// 直接使用替换后的提示词作为用户消息
	allMessages := []llms.MessageContent{
		llms.TextParts(llms.ChatMessageTypeHuman, prompt),
	}

	completion, err := l.llm.GenerateContent(ctx, allMessages,
		llms.WithTemperature(0.7),
		llms.WithTopP(0.9))
	if err != nil {
		g.Log().Errorf(ctx, "Langchain AI error: %v", err)
		return "", err
	}

	if len(completion.Choices) > 0 {
		resp = completion.Choices[0].Content
	}
	return
}
