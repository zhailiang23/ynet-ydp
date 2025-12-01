-- 个人知识库管理菜单
-- 菜单主体 (ID: 5191)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id, path, icon, component, component_name,
    status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5191, '个人知识库管理', '', 2, 1, 5178, 'personal', 'carbon:user-data',
    'knowledge/personal/index', 'KnowledgePersonal', 0, b'1', b'1', b'1', '', NOW(), '', NOW(), b'0'
);

-- 个人知识库权限按钮
-- 查询 (ID: 5192)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id, path, icon, component, component_name,
    status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5192, '个人知识库查询', 'knowledge:personal:query', 3, 1, 5191, '', '', '', NULL,
    0, b'1', b'1', b'1', '', NOW(), '', NOW(), b'0'
);

-- 创建 (ID: 5193)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id, path, icon, component, component_name,
    status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5193, '个人知识库创建', 'knowledge:personal:create', 3, 2, 5191, '', '', '', NULL,
    0, b'1', b'1', b'1', '', NOW(), '', NOW(), b'0'
);

-- 更新 (ID: 5194)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id, path, icon, component, component_name,
    status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5194, '个人知识库更新', 'knowledge:personal:update', 3, 3, 5191, '', '', '', NULL,
    0, b'1', b'1', b'1', '', NOW(), '', NOW(), b'0'
);

-- 删除 (ID: 5195)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id, path, icon, component, component_name,
    status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5195, '个人知识库删除', 'knowledge:personal:delete', 3, 4, 5191, '', '', '', NULL,
    0, b'1', b'1', b'1', '', NOW(), '', NOW(), b'0'
);

-- 导出 (ID: 5196)
INSERT INTO system_menu (
    id, name, permission, type, sort, parent_id, path, icon, component, component_name,
    status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted
) VALUES (
    5196, '个人知识库导出', 'knowledge:personal:export', 3, 5, 5191, '', '', '', NULL,
    0, b'1', b'1', b'1', '', NOW(), '', NOW(), b'0'
);
