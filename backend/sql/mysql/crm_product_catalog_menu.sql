-- ----------------------------
-- CRM 产品目录菜单和权限配置
-- ----------------------------

-- 1. 创建"产品目录"菜单 (type=2 菜单)
INSERT INTO `system_menu` (
    `id`, `name`, `permission`, `type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `component_name`, `status`,
    `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`
) VALUES (
    5316, '产品目录', '', 2, 2, 2397,
    'catalog', 'ant-design:appstore-outlined', 'crm/product/catalog/index', 'CrmProductCatalog', 0,
    1, 1, 1, '1', NOW(), '1', NOW(), 0
);

-- 2. 创建按钮权限 (type=3 按钮)
INSERT INTO `system_menu` (
    `id`, `name`, `permission`, `type`, `sort`, `parent_id`,
    `path`, `icon`, `component`, `component_name`, `status`,
    `visible`, `keep_alive`, `always_show`, `creator`, `create_time`, `updater`, `update_time`, `deleted`
) VALUES
    -- 查询权限
    (5317, '产品目录查询', 'crm:product-catalog:query', 3, 1, 5316,
     '', '', '', NULL, 0,
     1, 1, 1, '1', NOW(), '1', NOW(), 0),
    -- 创建权限
    (5318, '产品目录创建', 'crm:product-catalog:create', 3, 2, 5316,
     '', '', '', NULL, 0,
     1, 1, 1, '1', NOW(), '1', NOW(), 0),
    -- 更新权限
    (5319, '产品目录更新', 'crm:product-catalog:update', 3, 3, 5316,
     '', '', '', NULL, 0,
     1, 1, 1, '1', NOW(), '1', NOW(), 0),
    -- 删除权限
    (5320, '产品目录删除', 'crm:product-catalog:delete', 3, 4, 5316,
     '', '', '', NULL, 0,
     1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 3. 为管理员角色 (role_id=1) 分配所有权限
INSERT INTO `system_role_menu` (`role_id`, `menu_id`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`)
SELECT 1, menu_id, '1', NOW(), '1', NOW(), 0, 1
FROM (
    SELECT 5316 AS menu_id UNION ALL
    SELECT 5317 UNION ALL
    SELECT 5318 UNION ALL
    SELECT 5319 UNION ALL
    SELECT 5320
) AS menu_ids
WHERE NOT EXISTS (
    SELECT 1 FROM `system_role_menu`
    WHERE `role_id` = 1 AND `menu_id` = menu_ids.menu_id AND `tenant_id` = 1
);
