// =================================================================================
// Code generated and maintained by GoFrame CLI tool. DO NOT EDIT.
// =================================================================================

package do

import (
	"github.com/gogf/gf/v2/frame/g"
	"github.com/gogf/gf/v2/os/gtime"
)

// CustomerChatCollectInfo is the golang structure of table customer_chat_collect_info for DAO operations like Where/Data.
type CustomerChatCollectInfo struct {
	g.Meta     `orm:"table:customer_chat_collect_info, do:true"`
	Id         interface{}
	TemplateId interface{}
	Content    interface{}
	Status     interface{}
	AcceptTime *gtime.Time
	FinishTime *gtime.Time
	CustomerId interface{}
	UserId     interface{}
	AdminId    interface{}
	Remark     interface{}
	Creator    interface{}
	CreateTime *gtime.Time
	Updater    interface{}
	UpdateTime *gtime.Time
	Deleted    interface{}
	TenantId   interface{}
}
