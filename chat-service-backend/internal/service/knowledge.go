package service

import (
	"context"
)

type (
	IKnowledge interface {
		// GetAdminKnowledgeBaseId 获取客服的个人知识库 ID
		// 返回值: kbId (知识库 ID, 如果没有返回 0), error
		GetAdminKnowledgeBaseId(ctx context.Context, adminId uint) (int64, error)
	}
)

var (
	localKnowledge IKnowledge
)

func Knowledge() IKnowledge {
	if localKnowledge == nil {
		panic("implement not found for interface knowledge, forgot register?")
	}
	return localKnowledge
}

func RegisterKnowledge(i IKnowledge) {
	localKnowledge = i
}
