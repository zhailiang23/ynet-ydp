package collectinfo

import (
	"context"
	"gf-chat/internal/dao"
	"gf-chat/internal/model/do"
	"gf-chat/internal/service"

	"github.com/gogf/gf/v2/os/gtime"
)

func init() {
	service.RegisterCollectInfo(newCollectInfo())
}

func newCollectInfo() *sCollectInfo {
	return &sCollectInfo{}
}

type sCollectInfo struct {
}

// Submit 提交留资信息
func (s *sCollectInfo) Submit(ctx context.Context, userId uint, customerId uint, content string, templateId uint, adminId uint) (id uint, err error) {
	// 插入留资记录
	result, err := dao.CustomerChatCollectInfo.Ctx(ctx).Data(do.CustomerChatCollectInfo{
		Content:    content,
		Status:     0, // 默认未处理
		CustomerId: customerId,
		UserId:     userId,
		TemplateId: templateId,
		AdminId:    adminId,
		CreateTime: gtime.Now(),
	}).Insert()

	if err != nil {
		return 0, err
	}

	lastId, err := result.LastInsertId()
	if err != nil {
		return 0, err
	}

	return uint(lastId), nil
}
