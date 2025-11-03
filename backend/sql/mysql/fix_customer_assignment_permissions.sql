-- 修复客户归属管理权限配置
-- 为超级管理员角色添加客户归属管理相关的菜单权限

-- 为超级管理员角色(role_id=1)添加客户归属管理相关的菜单权限
INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT 1, id, 'admin', NOW(), 'admin', NOW(), 0, 1
FROM system_menu
WHERE id IN (
    5068,  -- 客户归属管理 (aicrm:customer-assignment:query)
    5069,  -- 客户分配 (aicrm:customer-assignment:assign)
    5070,  -- 客户移交 (aicrm:customer-assignment:transfer)
    5071,  -- 客户回收 (aicrm:customer-assignment:reclaim)
    5072,  -- 主办变更 (aicrm:customer-assignment:change-dept)
    5076   -- 变更历史 (aicrm:customer-assignment-history:query)
)
ON DUPLICATE KEY UPDATE update_time = NOW();

-- 验证权限配置
SELECT
    r.id as role_id,
    r.name as role_name,
    m.id as menu_id,
    m.name as menu_name,
    m.permission,
    m.type as menu_type
FROM system_role_menu rm
JOIN system_role r ON rm.role_id = r.id
JOIN system_menu m ON rm.menu_id = m.id
WHERE rm.role_id = 1
  AND m.id IN (5068, 5069, 5070, 5071, 5072, 5076)
ORDER BY m.id;
