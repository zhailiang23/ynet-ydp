-- 社区管理菜单和权限配置

-- 1. 社区管理菜单（类型2=菜单）
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES ('社区管理', '', 2, 6, 5223, 'community', '', 'grid/community/index', 'Community', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 获取刚插入的菜单ID（假设为5260）
SET @menu_id = LAST_INSERT_ID();

-- 2. 社区查询权限（类型3=按钮）
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES ('社区查询', 'grid:community:query', 3, 1, @menu_id, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 3. 社区创建权限（类型3=按钮）
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES ('社区创建', 'grid:community:create', 3, 2, @menu_id, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 4. 社区更新权限（类型3=按钮）
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES ('社区更新', 'grid:community:update', 3, 3, @menu_id, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 5. 社区删除权限（类型3=按钮）
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted)
VALUES ('社区删除', 'grid:community:delete', 3, 4, @menu_id, '', '', '', '', 0, 1, 1, 1, '1', NOW(), '1', NOW(), 0);

-- 6. 将菜单权限分配给超级管理员角色（role_id=1）
-- 查询刚创建的5个菜单ID
SET @query_id = @menu_id + 1;
SET @create_id = @menu_id + 2;
SET @update_id = @menu_id + 3;
SET @delete_id = @menu_id + 4;

-- 插入角色菜单关联
INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
  (1, @menu_id, '1', NOW(), '1', NOW(), 0, 1),
  (1, @query_id, '1', NOW(), '1', NOW(), 0, 1),
  (1, @create_id, '1', NOW(), '1', NOW(), 0, 1),
  (1, @update_id, '1', NOW(), '1', NOW(), 0, 1),
  (1, @delete_id, '1', NOW(), '1', NOW(), 0, 1);
