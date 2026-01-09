-- 任务评分因子权限配置
-- 依赖：任务管理菜单 ID = 5334

-- 1. 插入"评分因子"菜单（三级菜单，挂在任务管理下）
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, component_name, status,
    visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    '评分因子', '', 2, 3, 5334,
    'scoring-factor', 'ep:data-analysis', 'task/scoring-factor/index', 'TaskScoringFactor', 0,
    1, 1, 1, '1', NOW(), '1', NOW(), 0
);

-- 记录刚插入的菜单 ID（需要在后续权限中使用）
SET @menu_id = LAST_INSERT_ID();

-- 2. 插入"评分因子查询"权限（三级按钮）
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, status,
    visible, keep_alive, creator, create_time, updater, update_time, deleted
) VALUES (
    '评分因子查询', 'task:scoring-factor:query', 3, 1, @menu_id,
    '', '', '', 0,
    1, 1, '1', NOW(), '1', NOW(), 0
);

-- 3. 插入"评分因子创建"权限（三级按钮）
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, status,
    visible, keep_alive, creator, create_time, updater, update_time, deleted
) VALUES (
    '评分因子创建', 'task:scoring-factor:create', 3, 2, @menu_id,
    '', '', '', 0,
    1, 1, '1', NOW(), '1', NOW(), 0
);

-- 4. 插入"评分因子更新"权限（三级按钮）
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, status,
    visible, keep_alive, creator, create_time, updater, update_time, deleted
) VALUES (
    '评分因子更新', 'task:scoring-factor:update', 3, 3, @menu_id,
    '', '', '', 0,
    1, 1, '1', NOW(), '1', NOW(), 0
);

-- 5. 插入"评分因子删除"权限（三级按钮）
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, status,
    visible, keep_alive, creator, create_time, updater, update_time, deleted
) VALUES (
    '评分因子删除', 'task:scoring-factor:delete', 3, 4, @menu_id,
    '', '', '', 0,
    1, 1, '1', NOW(), '1', NOW(), 0
);

-- 6. 将所有权限分配给超级管理员角色（角色 ID = 1）
INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT 1, id, '1', NOW(), '1', NOW(), 0, 1
FROM system_menu
WHERE permission LIKE 'task:scoring-factor:%'
   OR (name = '评分因子' AND type = 2);
