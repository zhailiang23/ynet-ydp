package service

import (
	"context"
)

type (
	ICollectInfo interface {
		// Submit 提交留资信息
		Submit(ctx context.Context, userId uint, customerId uint, content string, templateId uint, adminId uint) (id uint, err error)
	}
)

var (
	localCollectInfo ICollectInfo
)

func CollectInfo() ICollectInfo {
	if localCollectInfo == nil {
		panic("implement not found for interface ICollectInfo, forgot register?")
	}
	return localCollectInfo
}

func RegisterCollectInfo(i ICollectInfo) {
	localCollectInfo = i
}
