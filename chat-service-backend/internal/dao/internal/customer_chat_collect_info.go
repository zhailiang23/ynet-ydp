// ==========================================================================
// Code generated and maintained by GoFrame CLI tool. DO NOT EDIT.
// ==========================================================================

package internal

import (
	"context"

	"github.com/gogf/gf/v2/database/gdb"
	"github.com/gogf/gf/v2/frame/g"
)

// CustomerChatCollectInfoDao is the data access object for table customer_chat_collect_info.
type CustomerChatCollectInfoDao struct {
	table   string                          // table is the underlying table name of the DAO.
	group   string                          // group is the database configuration group name of current DAO.
	columns CustomerChatCollectInfoColumns  // columns contains all the column names of Table for convenient usage.
}

// CustomerChatCollectInfoColumns defines and stores column names for table customer_chat_collect_info.
type CustomerChatCollectInfoColumns struct {
	Id         string
	TemplateId string
	Content    string
	Status     string
	AcceptTime string
	FinishTime string
	CustomerId string
	UserId     string
	AdminId    string
	Remark     string
	Creator    string
	CreateTime string
	Updater    string
	UpdateTime string
	Deleted    string
	TenantId   string
}

// customerChatCollectInfoColumns holds the columns for table customer_chat_collect_info.
var customerChatCollectInfoColumns = CustomerChatCollectInfoColumns{
	Id:         "id",
	TemplateId: "template_id",
	Content:    "content",
	Status:     "status",
	AcceptTime: "accept_time",
	FinishTime: "finish_time",
	CustomerId: "customer_id",
	UserId:     "user_id",
	AdminId:    "admin_id",
	Remark:     "remark",
	Creator:    "creator",
	CreateTime: "create_time",
	Updater:    "updater",
	UpdateTime: "update_time",
	Deleted:    "deleted",
	TenantId:   "tenant_id",
}

// NewCustomerChatCollectInfoDao creates and returns a new DAO object for table data access.
func NewCustomerChatCollectInfoDao() *CustomerChatCollectInfoDao {
	return &CustomerChatCollectInfoDao{
		group:   "default",
		table:   "customer_chat_collect_info",
		columns: customerChatCollectInfoColumns,
	}
}

// DB retrieves and returns the underlying raw database management object of current DAO.
func (dao *CustomerChatCollectInfoDao) DB() gdb.DB {
	return g.DB(dao.group)
}

// Table returns the table name of current dao.
func (dao *CustomerChatCollectInfoDao) Table() string {
	return dao.table
}

// Columns returns all column names of current dao.
func (dao *CustomerChatCollectInfoDao) Columns() CustomerChatCollectInfoColumns {
	return dao.columns
}

// Group returns the configuration group name of database of current dao.
func (dao *CustomerChatCollectInfoDao) Group() string {
	return dao.group
}

// Ctx creates and returns the Model for current DAO, It automatically sets the context for current operation.
func (dao *CustomerChatCollectInfoDao) Ctx(ctx context.Context) *gdb.Model {
	return dao.DB().Model(dao.table).Safe().Ctx(ctx)
}

// Transaction wraps the transaction logic using function f.
// It rollbacks the transaction and returns the error from function f if it returns non-nil error.
// It commits the transaction and returns nil if function f returns nil.
//
// Note that, you should not Commit or Rollback the transaction in function f
// as it is automatically handled by this function.
func (dao *CustomerChatCollectInfoDao) Transaction(ctx context.Context, f func(ctx context.Context, tx gdb.TX) error) (err error) {
	return dao.Ctx(ctx).Transaction(ctx, f)
}
