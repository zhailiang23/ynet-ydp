package backend

import (
	"context"
	baseApi "gf-chat/api"
	api "gf-chat/api/backend/v1"
	"gf-chat/internal/dao"
	"gf-chat/internal/model/do"
	"gf-chat/internal/model/entity"
	"gf-chat/internal/service"

	"github.com/gogf/gf/v2/errors/gerror"
	"github.com/gogf/gf/v2/frame/g"
	"github.com/gogf/gf/v2/net/ghttp"
	"github.com/gogf/gf/v2/os/gtime"
)

var CCollectInfo = &cCollectInfo{}

type cCollectInfo struct {
}

// getStatusText 获取状态文本
func getStatusText(status int) string {
	switch status {
	case 0:
		return "未处理"
	case 1:
		return "处理中"
	case 2:
		return "处理完成"
	default:
		return "未知"
	}
}

// Index 获取留资信息列表
func (c cCollectInfo) Index(ctx context.Context, req *api.CollectInfoListReq) (res *baseApi.NormalRes[[]*api.CollectInfo], err error) {
	adminId := service.AdminCtx().GetId(ctx)
	customerId := service.AdminCtx().GetCustomerId(ctx)

	w := g.Map{
		"customer_id": customerId,
		"admin_id":    adminId,
		"deleted":     0,
	}
	if req.Status != nil {
		w["status"] = *req.Status
	}

	var items []*entity.CustomerChatCollectInfo
	err = dao.CustomerChatCollectInfo.Ctx(ctx).Where(w).Order("id desc").Scan(&items)
	if err != nil {
		return nil, err
	}

	// 获取用户信息
	userIds := make([]uint, 0)
	for _, item := range items {
		if item.UserId > 0 {
			userIds = append(userIds, item.UserId)
		}
	}

	userMap := make(map[uint]string)
	if len(userIds) > 0 {
		users, err := service.User().All(ctx, g.Map{"id": userIds}, nil, nil)
		if err == nil {
			for _, u := range users {
				userMap[u.Id] = u.Username
			}
		}
	}

	// 获取客服信息
	adminIds := make([]uint, 0)
	for _, item := range items {
		if item.AdminId > 0 {
			adminIds = append(adminIds, item.AdminId)
		}
	}

	adminMap := make(map[uint]string)
	if len(adminIds) > 0 {
		admins, err := service.Admin().All(ctx, g.Map{"id": adminIds}, nil, nil)
		if err == nil {
			for _, a := range admins {
				adminMap[a.Id] = a.Username
			}
		}
	}

	// 获取模板信息
	templateIds := make([]uint, 0)
	for _, item := range items {
		if item.TemplateId > 0 {
			templateIds = append(templateIds, item.TemplateId)
		}
	}

	type templateInfo struct {
		Name        string
		Description string
	}
	templateMap := make(map[uint]templateInfo)
	if len(templateIds) > 0 {
		var templates []*entity.CustomerChatAutoMessages
		err = dao.CustomerChatAutoMessages.Ctx(ctx).Where(g.Map{"id": templateIds}).Scan(&templates)
		if err == nil {
			for _, t := range templates {
				templateMap[t.Id] = templateInfo{Name: t.Name, Description: t.Content}
			}
		}
	}

	result := make([]*api.CollectInfo, len(items))
	for i, item := range items {
		tpl := templateMap[item.TemplateId]
		result[i] = &api.CollectInfo{
			Id:                  item.Id,
			Content:             item.Content,
			Status:              item.Status,
			StatusText:          getStatusText(item.Status),
			CustomerId:          item.CustomerId,
			UserId:              item.UserId,
			Username:            userMap[item.UserId],
			AdminId:             item.AdminId,
			AdminName:           adminMap[item.AdminId],
			TemplateId:          item.TemplateId,
			TemplateName:        tpl.Name,
			TemplateDescription: tpl.Description,
			Remark:              item.Remark,
			CreateTime:          item.CreateTime,
			AcceptTime:          item.AcceptTime,
			FinishTime:          item.FinishTime,
			UpdateTime:          item.UpdateTime,
			Source:              "", // 暂未存储来源信息
		}
	}

	return baseApi.NewResp(result), nil
}

// Detail 获取留资信息详情
func (c cCollectInfo) Detail(ctx context.Context, req *api.CollectInfoDetailReq) (res *baseApi.NormalRes[*api.CollectInfo], err error) {
	adminId := service.AdminCtx().GetId(ctx)
	customerId := service.AdminCtx().GetCustomerId(ctx)
	id := ghttp.RequestFromCtx(ctx).GetRouter("id")

	var item *entity.CustomerChatCollectInfo
	err = dao.CustomerChatCollectInfo.Ctx(ctx).Where(do.CustomerChatCollectInfo{
		Id:         id,
		CustomerId: customerId,
		AdminId:    adminId,
		Deleted:    0,
	}).Scan(&item)
	if err != nil {
		return nil, err
	}
	if item == nil {
		return nil, gerror.New("记录不存在")
	}

	// 获取用户信息
	username := ""
	if item.UserId > 0 {
		user, err := service.User().First(ctx, g.Map{"id": item.UserId})
		if err == nil && user != nil {
			username = user.Username
		}
	}

	// 获取客服信息
	adminName := ""
	if item.AdminId > 0 {
		admin, err := service.Admin().First(ctx, g.Map{"id": item.AdminId})
		if err == nil && admin != nil {
			adminName = admin.Username
		}
	}

	// 获取模板信息
	templateName := ""
	templateDescription := ""
	if item.TemplateId > 0 {
		var template *entity.CustomerChatAutoMessages
		err = dao.CustomerChatAutoMessages.Ctx(ctx).Where(g.Map{"id": item.TemplateId}).Scan(&template)
		if err == nil && template != nil {
			templateName = template.Name
			templateDescription = template.Content
		}
	}

	return baseApi.NewResp(&api.CollectInfo{
		Id:                  item.Id,
		Content:             item.Content,
		Status:              item.Status,
		StatusText:          getStatusText(item.Status),
		CustomerId:          item.CustomerId,
		UserId:              item.UserId,
		Username:            username,
		AdminId:             item.AdminId,
		AdminName:           adminName,
		TemplateId:          item.TemplateId,
		TemplateName:        templateName,
		TemplateDescription: templateDescription,
		Remark:              item.Remark,
		CreateTime:          item.CreateTime,
		AcceptTime:          item.AcceptTime,
		FinishTime:          item.FinishTime,
		UpdateTime:          item.UpdateTime,
		Source:              "", // 暂未存储来源信息
	}), nil
}

// Update 更新留资信息状态
func (c cCollectInfo) Update(ctx context.Context, req *api.CollectInfoUpdateReq) (res *baseApi.NilRes, err error) {
	adminId := service.AdminCtx().GetId(ctx)
	customerId := service.AdminCtx().GetCustomerId(ctx)
	id := ghttp.RequestFromCtx(ctx).GetRouter("id")

	// 查找记录
	var item *entity.CustomerChatCollectInfo
	err = dao.CustomerChatCollectInfo.Ctx(ctx).Where(do.CustomerChatCollectInfo{
		Id:         id,
		CustomerId: customerId,
		AdminId:    adminId,
	}).Scan(&item)
	if err != nil {
		return nil, err
	}
	if item == nil {
		return nil, gerror.New("记录不存在")
	}

	// 更新状态
	updateData := g.Map{
		"status":      req.Status,
		"remark":      req.Remark,
		"update_time": gtime.Now(),
	}
	// 状态 1 表示处理中，记录 accept_time
	if req.Status == 1 {
		updateData["accept_time"] = gtime.Now()
	}
	// 状态 2 表示处理完成，记录 finish_time
	if req.Status == 2 {
		updateData["finish_time"] = gtime.Now()
	}

	_, err = dao.CustomerChatCollectInfo.Ctx(ctx).Where("id", id).Data(updateData).Update()
	if err != nil {
		return nil, err
	}

	return baseApi.NewNilResp(), nil
}
