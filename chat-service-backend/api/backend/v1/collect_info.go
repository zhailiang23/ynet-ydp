package v1

import (
	"github.com/gogf/gf/v2/frame/g"
	"github.com/gogf/gf/v2/os/gtime"
)

// CollectInfoListReq 留资信息列表请求
type CollectInfoListReq struct {
	g.Meta `path:"/collect-infos" tags:"留资信息" method:"get" summary:"留资信息列表"`
	Status *int `p:"status" dc:"状态: 0-未处理, 1-处理中, 2-处理完成"`
}

// CollectInfoDetailReq 留资信息详情请求
type CollectInfoDetailReq struct {
	g.Meta `path:"/collect-infos/:id" tags:"留资信息" method:"get" summary:"留资信息详情"`
}

// CollectInfoUpdateReq 更新留资信息状态请求
type CollectInfoUpdateReq struct {
	g.Meta `path:"/collect-infos/:id" tags:"留资信息" method:"put" summary:"更新留资信息状态"`
	Status int    `p:"status" v:"required|in:0,1,2" dc:"状态: 0-未处理, 1-处理中, 2-处理完成"`
	Remark string `p:"remark" dc:"备注"`
}

// CollectInfo 留资信息响应结构
type CollectInfo struct {
	Id                  uint        `json:"id"`
	Content             string      `json:"content"`
	Status              int         `json:"status"`
	StatusText          string      `json:"status_text"`
	CustomerId          uint        `json:"customer_id"`
	UserId              uint        `json:"user_id"`
	Username            string      `json:"username"`
	AdminId             uint        `json:"admin_id"`
	AdminName           string      `json:"admin_name"`
	TemplateId          uint        `json:"template_id"`
	TemplateName        string      `json:"template_name"`
	TemplateDescription string      `json:"template_description"`
	Remark              string      `json:"remark"`
	CreateTime          *gtime.Time `json:"create_time"`
	AcceptTime          *gtime.Time `json:"accept_time"`
	FinishTime          *gtime.Time `json:"finish_time"`
	UpdateTime          *gtime.Time `json:"update_time"`
	Source              string      `json:"source"`
}
