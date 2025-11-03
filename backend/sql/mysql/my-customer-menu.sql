-- 添加"我的客户"菜单,放在客户分配管理(id=5067)下,客户归属管理(id=5068)之后
-- 用于显示当前用户主办和协办的所有客户

INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5087, '我的客户', 'aicrm:customer-assignment:query', 2, 2, 5067,
    'my-customer', '', 'aicrm/my-customer/index', 'MyCustomer',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 验证插入
SELECT id, name, path, component, parent_id, sort
FROM system_menu
WHERE id = 5087 OR parent_id = 5067
ORDER BY parent_id, sort;
