-- ----------------------------
-- 为社区网格添加菜单和权限配置
-- ----------------------------

-- 添加社区网格菜单 (type=2 表示菜单)
INSERT INTO `system_menu` (
  `id`, `name`, `permission`, `type`, `sort`, `parent_id`,
  `path`, `icon`, `component`, `component_name`, `status`,
  `visible`, `keep_alive`, `always_show`, `creator`,
  `create_time`, `updater`, `update_time`, `deleted`
) VALUES (
  5255, '社区网格管理', '', 2, 10, 5223,
  'community-grid', '', 'grid/community-grid/index', 'CommunityGrid', 0,
  b'1', b'1', b'1', '1',
  NOW(), '1', NOW(), b'0'
);

-- 添加社区网格查询权限 (type=3 表示按钮/权限)
INSERT INTO `system_menu` (
  `id`, `name`, `permission`, `type`, `sort`, `parent_id`,
  `path`, `icon`, `component`, `component_name`, `status`,
  `visible`, `keep_alive`, `always_show`, `creator`,
  `create_time`, `updater`, `update_time`, `deleted`
) VALUES (
  5256, '社区网格查询', 'grid:community-grid:query', 3, 1, 5255,
  '', '', '', NULL, 0,
  b'1', b'1', b'1', '1',
  NOW(), '1', NOW(), b'0'
);
