-- 修复任务评分因子菜单配置
-- 将菜单挂在正确的位置：智能陪练 -> 任务管理 -> 评分因子

-- 1. 先删除错误位置的旧菜单（包括权限按钮）
DELETE FROM system_role_menu WHERE menu_id IN (5357, 5358, 5359, 5360, 5361);
DELETE FROM system_menu WHERE id IN (5357, 5358, 5359, 5360, 5361);

-- 2. 在"智能陪练"(id=5089)下创建"任务管理"二级菜单
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, component_name, status,
    visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    '任务管理', '', 2, 8, 5089,
    'task', 'ep:document', 'aicrm/task/index', 'AicrmTask', 0,
    1, 1, 1, '1', NOW(), '1', NOW(), 0
);

-- 获取刚创建的任务管理菜单ID
SET @task_menu_id = LAST_INSERT_ID();

-- 3. 在"任务管理"下创建"评分因子"三级菜单
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, component_name, status,
    visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    '评分因子', '', 2, 1, @task_menu_id,
    'scoring-factor', 'ep:data-analysis', 'task/scoring-factor/index', 'TaskScoringFactor', 0,
    1, 1, 1, '1', NOW(), '1', NOW(), 0
);

-- 获取刚创建的评分因子菜单ID
SET @scoring_factor_menu_id = LAST_INSERT_ID();

-- 4. 创建"评分因子查询"权限按钮
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, status,
    visible, keep_alive, creator, create_time, updater, update_time, deleted
) VALUES (
    '评分因子查询', 'task:scoring-factor:query', 3, 1, @scoring_factor_menu_id,
    '', '', '', 0,
    1, 1, '1', NOW(), '1', NOW(), 0
);

-- 5. 创建"评分因子创建"权限按钮
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, status,
    visible, keep_alive, creator, create_time, updater, update_time, deleted
) VALUES (
    '评分因子创建', 'task:scoring-factor:create', 3, 2, @scoring_factor_menu_id,
    '', '', '', 0,
    1, 1, '1', NOW(), '1', NOW(), 0
);

-- 6. 创建"评分因子更新"权限按钮
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, status,
    visible, keep_alive, creator, create_time, updater, update_time, deleted
) VALUES (
    '评分因子更新', 'task:scoring-factor:update', 3, 3, @scoring_factor_menu_id,
    '', '', '', 0,
    1, 1, '1', NOW(), '1', NOW(), 0
);

-- 7. 创建"评分因子删除"权限按钮
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, status,
    visible, keep_alive, creator, create_time, updater, update_time, deleted
) VALUES (
    '评分因子删除', 'task:scoring-factor:delete', 3, 4, @scoring_factor_menu_id,
    '', '', '', 0,
    1, 1, '1', NOW(), '1', NOW(), 0
);

-- 8. 将所有权限分配给超级管理员角色（角色 ID = 1）
-- 包括任务管理菜单
INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES (1, @task_menu_id, '1', NOW(), '1', NOW(), 0, 1);

-- 包括评分因子菜单和所有权限按钮
INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT 1, id, '1', NOW(), '1', NOW(), 0, 1
FROM system_menu
WHERE (permission LIKE 'task:scoring-factor:%' OR id = @scoring_factor_menu_id)
  AND deleted = 0;

-- 查询结果验证
SELECT '=== 新创建的菜单结构 ===' as info;
SELECT m1.id as parent_id, m1.name as parent_name,
       m2.id as menu_id, m2.name as menu_name, m2.path, m2.component,
       m3.id as button_id, m3.name as button_name, m3.permission
FROM system_menu m1
LEFT JOIN system_menu m2 ON m2.parent_id = m1.id AND m2.type = 2
LEFT JOIN system_menu m3 ON m3.parent_id = m2.id AND m3.type = 3
WHERE m1.id = @task_menu_id
ORDER BY m2.id, m3.id;
