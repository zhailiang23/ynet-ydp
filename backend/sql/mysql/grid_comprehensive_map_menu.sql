-- ============================================================
-- 网格营销综合地图菜单脚本
-- ============================================================

-- 1. 插入综合地图菜单（主菜单）
INSERT INTO system_menu (
    id,
    name,
    permission,
    type,
    sort,
    parent_id,
    path,
    icon,
    component,
    component_name,
    status,
    visible,
    keep_alive,
    always_show,
    creator,
    create_time,
    updater,
    update_time,
    deleted
) VALUES (
    5314,
    '网格营销地图',
    '',
    2,  -- 菜单类型
    30,
    5223,  -- 父菜单：网格营销
    'grid-map',
    'ant-design:global-outlined',
    'grid/grid-map/index',
    'GridMap',
    0,  -- 状态：正常
    true,
    true,
    true,
    '1',
    NOW(),
    '1',
    NOW(),
    false
);

-- 2. 插入综合地图查询按钮权限
INSERT INTO system_menu (
    id,
    name,
    permission,
    type,
    sort,
    parent_id,
    path,
    icon,
    component,
    component_name,
    status,
    visible,
    keep_alive,
    always_show,
    creator,
    create_time,
    updater,
    update_time,
    deleted
) VALUES (
    5315,
    '网格营销地图查询',
    'grid:map:query',
    3,  -- 按钮类型
    1,
    5314,  -- 父菜单：网格营销地图
    '',
    '',
    '',
    '',
    0,  -- 状态：正常
    true,
    true,
    true,
    '1',
    NOW(),
    '1',
    NOW(),
    false
);

-- 3. 为管理员角色（role_id=1）赋予菜单权限
INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
    (1, 5314, '1', NOW(), '1', NOW(), 0, 1),
    (1, 5315, '1', NOW(), '1', NOW(), 0, 1);

-- ============================================================
-- 执行说明：
-- 1. 菜单 ID 5314：网格营销地图（主菜单）
-- 2. 菜单 ID 5315：网格营销地图查询权限（按钮）
-- 3. 已为管理员角色（role_id=1）自动赋权
--
-- 如需为其他角色赋权，请执行：
-- INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
-- VALUES
--     (YOUR_ROLE_ID, 5314, '1', NOW(), '1', NOW(), 0, 1),
--     (YOUR_ROLE_ID, 5315, '1', NOW(), '1', NOW(), 0, 1);
-- ============================================================
