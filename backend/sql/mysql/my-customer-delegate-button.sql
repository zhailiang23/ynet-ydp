-- 为"我的客户"菜单添加"托管"按钮权限
-- 菜单ID: 5087

INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    '客户托管', 'aicrm:customer-assignment:update', 3, 1, 5087,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 验证插入
SELECT id, name, permission, type, parent_id
FROM system_menu
WHERE parent_id = 5087 OR id = 5087
ORDER BY parent_id, sort;
