-- 为客户分配管理菜单(id=5067)添加8个子菜单,对应8大核心功能

-- 1. 客户归属管理 (查看和管理客户归属关系)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5068, '客户归属管理', 'aicrm:customer-assignment:query', 2, 1, 5067,
    'assignment', '', 'aicrm/customerassignment/index', 'CustomerAssignment',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 2. 客户分配 (批量手动分配客户)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5069, '客户分配', 'aicrm:customer-assignment:assign', 3, 2, 5068,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 3. 客户移交 (客户经理之间移交)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5070, '客户移交', 'aicrm:customer-assignment:transfer', 3, 3, 5068,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 4. 客户回收 (主管批量回收)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5071, '客户回收', 'aicrm:customer-assignment:reclaim', 3, 4, 5068,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 5. 主办变更 (跨部门主办变更)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5072, '主办变更', 'aicrm:customer-assignment:change-dept', 3, 5, 5068,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 6. 客户托管 (临时托管机制)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5073, '客户托管', 'aicrm:customer-delegation:query', 2, 6, 5067,
    'delegation', '', 'aicrm/customerdelegation/index', 'CustomerDelegation',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 7. 客户认领 (认领无主客户,需BPM审批)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5074, '客户认领', 'aicrm:customer-claim:query', 2, 7, 5067,
    'claim', '', 'aicrm/customerclaim/index', 'CustomerClaim',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 8. 客户退回 (退回给主管,需BPM审批)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5075, '客户退回', 'aicrm:customer-return:query', 2, 8, 5067,
    'return', '', 'aicrm/customerreturn/index', 'CustomerReturn',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 9. 变更历史 (查看所有客户归属变更历史)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5076, '变更历史', 'aicrm:customer-assignment-history:query', 2, 9, 5067,
    'history', '', 'aicrm/customerassignmenthistory/index', 'CustomerAssignmentHistory',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 为客户托管添加操作权限子菜单
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5077, '创建托管', 'aicrm:customer-delegation:create', 3, 1, 5073,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5078, '结束托管', 'aicrm:customer-delegation:end', 3, 2, 5073,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5079, '取消托管', 'aicrm:customer-delegation:cancel', 3, 3, 5073,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 为客户认领添加操作权限子菜单
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5080, '提交认领申请', 'aicrm:customer-claim:create', 3, 1, 5074,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5081, '取消认领申请', 'aicrm:customer-claim:delete', 3, 2, 5074,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

-- 为客户退回添加操作权限子菜单
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5082, '提交退回申请', 'aicrm:customer-return:create', 3, 1, 5075,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);

INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    5083, '取消退回申请', 'aicrm:customer-return:delete', 3, 2, 5075,
    '', '', '', '',
    0, b'1', b'1', b'1',
    '1', NOW(), '1', NOW(), b'0'
);
