package knowledge

import (
	"context"
	"database/sql"
	"fmt"
	"gf-chat/internal/dao"
	"gf-chat/internal/service"

	"github.com/gogf/gf/v2/frame/g"
)

func init() {
	service.RegisterKnowledge(newKnowledge())
}

type sKnowledge struct{}

func newKnowledge() *sKnowledge {
	return &sKnowledge{}
}

// GetAdminKnowledgeBaseId 获取客服的个人知识库 ID
// 查询链路: customer_admins.username -> system_users.nickname -> system_users.id -> knowledge_base.owner_id
func (s *sKnowledge) GetAdminKnowledgeBaseId(ctx context.Context, adminId uint) (int64, error) {
	// Step 1: 从 customer_admins 获取 username
	username, err := dao.CustomerAdmins.Ctx(ctx).
		Where("id", adminId).
		Value("username")
	if err != nil {
		return 0, fmt.Errorf("get admin username failed: %w", err)
	}
	if username.IsEmpty() {
		g.Log().Warningf(ctx, "[Knowledge] Admin not found: adminId=%d", adminId)
		return 0, nil
	}
	usernameStr := username.String()

	// Step 2: 从 system_users 获取 system_user_id (通过 nickname 匹配 username)
	// 注意: Go 和 Java 使用同一个数据库,直接使用 g.DB() 即可
	systemUserIdValue, err := g.DB().Model("system_users").Ctx(ctx).
		Where("nickname", usernameStr).
		Where("deleted", 0).
		Value("id")
	if err == sql.ErrNoRows || systemUserIdValue.IsEmpty() {
		g.Log().Warningf(ctx, "[Knowledge] System user not found for admin: username=%s", usernameStr)
		return 0, nil // 没有对应的系统用户,返回 0 表示无知识库
	}
	if err != nil {
		return 0, fmt.Errorf("get system user id failed: %w", err)
	}
	systemUserId := systemUserIdValue.Int64()

	// Step 3: 查询个人知识库 (owner_id = systemUserId)
	kbIdValue, err := g.DB().Model("knowledge_base").Ctx(ctx).
		Where("owner_id", systemUserId).
		Where("deleted", 0).
		OrderDesc("id"). // 如果有多个,取最新的
		Limit(1).
		Value("id")

	if err == sql.ErrNoRows || kbIdValue.IsEmpty() {
		g.Log().Infof(ctx, "[Knowledge] No personal knowledge base found for system_user_id: %d", systemUserId)
		return 0, nil // 没有个人知识库,返回 0
	}
	if err != nil {
		return 0, fmt.Errorf("get knowledge base id failed: %w", err)
	}
	kbId := kbIdValue.Int64()

	g.Log().Infof(ctx, "[Knowledge] Found knowledge base: adminId=%d, username=%s, systemUserId=%d, kbId=%d",
		adminId, usernameStr, systemUserId, kbId)
	return kbId, nil
}
