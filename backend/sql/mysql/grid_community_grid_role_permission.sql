-- ----------------------------
-- 为超级管理员角色分配社区网格菜单权限
-- ----------------------------

-- 为超级管理员(role_id=1)分配社区网格管理菜单
INSERT INTO `system_role_menu` (`role_id`, `menu_id`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`)
VALUES (1, 5255, '1', NOW(), '1', NOW(), b'0', 1);

-- 为超级管理员(role_id=1)分配社区网格查询权限
INSERT INTO `system_role_menu` (`role_id`, `menu_id`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`)
VALUES (1, 5256, '1', NOW(), '1', NOW(), b'0', 1);
