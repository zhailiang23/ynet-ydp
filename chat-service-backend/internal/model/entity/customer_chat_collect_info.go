// =================================================================================
// Code generated and maintained by GoFrame CLI tool. DO NOT EDIT.
// =================================================================================

package entity

import (
	"github.com/gogf/gf/v2/os/gtime"
)

// CustomerChatCollectInfo is the golang structure for table customer_chat_collect_info.
type CustomerChatCollectInfo struct {
	Id         uint        `json:"id"          orm:"id"          `
	TemplateId uint        `json:"template_id" orm:"template_id" `
	Content    string      `json:"content"     orm:"content"     `
	Status     int         `json:"status"      orm:"status"      `
	AcceptTime *gtime.Time `json:"accept_time" orm:"accept_time" `
	FinishTime *gtime.Time `json:"finish_time" orm:"finish_time" `
	CustomerId uint        `json:"customer_id" orm:"customer_id" `
	UserId     uint        `json:"user_id"     orm:"user_id"     `
	AdminId    uint        `json:"admin_id"    orm:"admin_id"    `
	Remark     string      `json:"remark"      orm:"remark"      `
	Creator    string      `json:"creator"     orm:"creator"     `
	CreateTime *gtime.Time `json:"create_time" orm:"create_time" `
	Updater    string      `json:"updater"     orm:"updater"     `
	UpdateTime *gtime.Time `json:"update_time" orm:"update_time" `
	Deleted    int         `json:"deleted"     orm:"deleted"     `
	TenantId   int64       `json:"tenant_id"   orm:"tenant_id"   `
}
