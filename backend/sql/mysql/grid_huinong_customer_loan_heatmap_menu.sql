-- ========================================
-- 惠农贷款目标客户热力图 - 菜单配置
-- ========================================
-- 功能：在系统菜单中添加"惠农贷款目标客户热力图"菜单项
-- 位置：网格管理 -> 惠农贷款目标客户热力图
-- 路由：/grid/huinong-customer-loan-heatmap
-- ========================================

-- 步骤1：查询父菜单ID（网格管理）
-- 执行以下查询，找到"网格管理"的菜单ID
SELECT id, name, path, type
FROM system_menu
WHERE (name = '网格管理' OR name LIKE '%Grid%' OR path = 'grid')
  AND type IN (1, 2)  -- 1=目录, 2=菜单
  AND deleted = 0
ORDER BY type, id;

-- 如果没有"网格管理"父菜单，需要先创建父菜单：
-- INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, visible, keep_alive, creator, create_time, updater, update_time, deleted)
-- VALUES ('网格管理', '', 1, 100, 0, 'grid', 'carbon:grid', NULL, 0, TRUE, TRUE, '1', NOW(), '1', NOW(), FALSE);

-- ========================================
-- 步骤2：插入惠农贷款目标客户热力图菜单
-- ========================================
-- 注意：请将下面的 @PARENT_MENU_ID 替换为步骤1查询到的实际ID

SET @PARENT_MENU_ID = (
    SELECT id FROM system_menu
    WHERE (name = '网格管理' OR path = 'grid')
      AND type IN (1, 2)
      AND deleted = 0
    LIMIT 1
);

-- 插入菜单记录
INSERT INTO system_menu (
    name,                                                  -- 菜单名称
    permission,                                            -- 权限标识
    type,                                                  -- 菜单类型：1=目录 2=菜单 3=按钮
    sort,                                                  -- 排序
    parent_id,                                             -- 父菜单ID
    path,                                                  -- 路由路径
    icon,                                                  -- 图标
    component,                                             -- 组件路径
    component_name,                                        -- 组件名称
    status,                                                -- 状态：0=正常 1=停用
    visible,                                               -- 是否显示
    keep_alive,                                            -- 是否缓存
    always_show,                                           -- 是否总是显示
    creator,                                               -- 创建者
    create_time,                                           -- 创建时间
    updater,                                               -- 更新者
    update_time,                                           -- 更新时间
    deleted,                                               -- 是否删除
    tenant_id                                              -- 租户ID
) VALUES (
    '惠农贷款目标客户热力图',                              -- 菜单名称
    'grid:huinong-customer-loan:query',                    -- 权限标识（使用已有的查询权限）
    2,                                                     -- type: 2=菜单
    20,                                                    -- sort: 排序值20（在其他热力图之后）
    @PARENT_MENU_ID,                                       -- parent_id: 使用查询到的父菜单ID
    'huinong-customer-loan-heatmap',                       -- path: 路由路径（与前端路由配置一致）
    'ant-design:fire-outlined',                            -- icon: 火焰图标
    'grid/huinongcustomerloan/heatmap',                    -- component: 组件路径
    NULL,                                                  -- component_name: 不指定
    0,                                                     -- status: 0=启用
    TRUE,                                                  -- visible: 显示在菜单
    TRUE,                                                  -- keep_alive: 启用路由缓存
    FALSE,                                                 -- always_show: 不强制显示
    '1',                                                   -- creator: 系统管理员
    NOW(),                                                 -- create_time: 当前时间
    '1',                                                   -- updater: 系统管理员
    NOW(),                                                 -- update_time: 当前时间
    FALSE,                                                 -- deleted: 未删除
    1                                                      -- tenant_id: 默认租户
);

-- ========================================
-- 步骤3：验证菜单已成功添加
-- ========================================
SELECT
    m.id AS 菜单ID,
    p.name AS 父菜单,
    m.name AS 菜单名称,
    m.path AS 路由路径,
    m.component AS 组件路径,
    m.icon AS 图标,
    m.sort AS 排序,
    m.visible AS 是否显示,
    m.status AS 状态,
    m.permission AS 权限标识
FROM system_menu m
LEFT JOIN system_menu p ON m.parent_id = p.id
WHERE m.name = '惠农贷款目标客户热力图'
  AND m.deleted = 0;

-- ========================================
-- 步骤4：查看网格管理下的所有菜单（可选）
-- ========================================
SELECT
    m.id,
    m.name,
    m.path,
    m.component,
    m.icon,
    m.sort,
    m.type,
    m.visible
FROM system_menu m
WHERE m.parent_id = @PARENT_MENU_ID
  AND m.deleted = 0
ORDER BY m.sort, m.id;

-- ========================================
-- 执行说明
-- ========================================
-- 1. 在 MySQL 客户端中执行此脚本
-- 2. 如果没有"网格管理"父菜单，取消注释"步骤1"中的INSERT语句先创建父菜单
-- 3. 执行后刷新前端页面，在"网格管理"菜单下会出现新的菜单项
-- 4. 如果菜单未显示，检查用户权限是否包含 'grid:huinong-customer-loan:query'
-- ========================================
