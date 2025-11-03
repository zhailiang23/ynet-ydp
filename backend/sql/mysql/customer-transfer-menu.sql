-- 客户移交申请菜单
-- 父菜单 ID: 5067 (客户关系管理)
-- 新菜单从 ID 5084 开始

-- 1. 客户移交菜单 (主菜单)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5084, '客户移交', 'aicrm:customer-transfer:query', 2, 8, 5067,
    'transfer', '', 'aicrm/customertransfer/index', 'CustomerTransfer',
    b'0', b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 2. 提交移交申请按钮
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5085, '提交移交申请', 'aicrm:customer-transfer:create', 3, 1, 5084,
    '', '', '', NULL,
    b'0', b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 3. 取消移交申请按钮
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5086, '取消移交申请', 'aicrm:customer-transfer:delete', 3, 2, 5084,
    '', '', '', NULL,
    b'0', b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);
