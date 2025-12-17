-- 为惠农站点信息添加导入权限
-- 父菜单: 惠农站点信息管理 (id=5230)
-- 现有权限: 查询(5231), 创建(5232), 更新(5233), 删除(5234), 导出(5235)
-- 新增权限: 导入(sort=6)

-- 添加导入权限（type=3 表示按钮）
INSERT INTO system_menu (
    name, permission, type, sort, parent_id,
    path, icon, component, component_name,
    status, visible, keep_alive, always_show,
    creator, create_time, updater, update_time, deleted
) VALUES (
    '惠农站点信息导入',
    'grid:huinong-station:import',
    3,
    6,
    5230,
    '',
    '#',
    '',
    NULL,
    0,
    b'1',
    b'1',
    b'1',
    '1',
    NOW(),
    '1',
    NOW(),
    b'0'
);

-- 验证插入结果
SELECT id, name, permission, type, parent_id, sort
FROM system_menu
WHERE parent_id = 5230
ORDER BY sort;
