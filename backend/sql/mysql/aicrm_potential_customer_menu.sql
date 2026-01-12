-- ========================================
-- 潜客管理菜单和权限配置
-- ========================================

-- 1. 添加"潜客管理"主菜单（在"客户经营平台"下）
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name, status,
    visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5339, '潜客管理', '', 2, 33, 5066,
    'potential-customer', 'ep:user-filled', 'aicrm/potentialcustomer/index', 'AicrmPotentialCustomer', 0,
    true, true, true, '1', NOW(), '1', NOW(), false
);

-- 2. 添加"潜客查询"权限
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name, status,
    visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5340, '潜客查询', 'aicrm:potential-customer:query', 3, 1, 5339,
    '', '', '', NULL, 0,
    true, true, true, '1', NOW(), '1', NOW(), false
);

-- 3. 添加"潜客创建"权限
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name, status,
    visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5341, '潜客创建', 'aicrm:potential-customer:create', 3, 2, 5339,
    '', '', '', NULL, 0,
    true, true, true, '1', NOW(), '1', NOW(), false
);

-- 4. 添加"潜客更新"权限
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name, status,
    visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5342, '潜客更新', 'aicrm:potential-customer:update', 3, 3, 5339,
    '', '', '', NULL, 0,
    true, true, true, '1', NOW(), '1', NOW(), false
);

-- 5. 添加"潜客删除"权限
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name, status,
    visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5343, '潜客删除', 'aicrm:potential-customer:delete', 3, 4, 5339,
    '', '', '', NULL, 0,
    true, true, true, '1', NOW(), '1', NOW(), false
);

-- 6. 添加"潜客导出"权限
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name, status,
    visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5344, '潜客导出', 'aicrm:potential-customer:export', 3, 5, 5339,
    '', '', '', NULL, 0,
    true, true, true, '1', NOW(), '1', NOW(), false
);

-- 7. 给超级管理员角色（id=1）分配所有潜客管理权限
INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
    (1, 5339, '1', NOW(), '1', NOW(), false, 1),  -- 潜客管理
    (1, 5340, '1', NOW(), '1', NOW(), false, 1),  -- 潜客查询
    (1, 5341, '1', NOW(), '1', NOW(), false, 1),  -- 潜客创建
    (1, 5342, '1', NOW(), '1', NOW(), false, 1),  -- 潜客更新
    (1, 5343, '1', NOW(), '1', NOW(), false, 1),  -- 潜客删除
    (1, 5344, '1', NOW(), '1', NOW(), false, 1);  -- 潜客导出

-- 查询验证
SELECT id, parent_id, name, permission, type, sort, path, component
FROM system_menu
WHERE id >= 5339
ORDER BY id;
