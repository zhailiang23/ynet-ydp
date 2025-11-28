package service

import (
	"context"
	"gf-chat/internal/model"
	"github.com/tmc/langchaingo/llms"
)

type (
	ILangchain interface {
		Ask(ctx context.Context, msg string, messages ...llms.MessageContent) (resp string, err error)
		// AskWithCustomPrompt 使用自定义提示词模板调用 AI，模板中可包含 {{message}} 和 {{history}} 变量
		AskWithCustomPrompt(ctx context.Context, promptTemplate string, message string, history []*model.CustomerChatMessage) (resp string, err error)
		MessageToContent(messages []*model.CustomerChatMessage) []llms.MessageContent
	}
)

var (
	localLangchain ILangchain
)

func Langchain() ILangchain {
	if localLangchain == nil {
		panic("implement not found for interface langchain, forgot register?")
	}
	return localLangchain
}

func RegisterLangchain(i ILangchain) {
	localLangchain = i
}
