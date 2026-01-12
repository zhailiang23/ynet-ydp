-- ============================================================
-- 营销活动任务下发菜单配置脚本
-- 说明: 在"移动展业平台"下添加"营销活动下发任务"菜单及其权限
-- 创建时间: 2026-01-05
-- ============================================================

-- 1. 插入营销活动下发任务主菜单
INSERT INTO system_menu (
  name,
  permission,
  type,
  sort,
  parent_id,
  path,
  icon,
  component,
  component_name,
  status,
  visible,
  keep_alive,
  always_show,
  creator,
  create_time,
  updater,
  update_time,
  deleted
) VALUES (
  '营销活动下发任务',
  '',
  2,
  5,
  5321,
  'marketing-task-assignment',
  'ep:promotion',
  'aicrm/marketingtaskassignment/index',
  'MarketingTaskAssignment',
  0,
  b'1',
  b'1',
  b'0',
  '1',
  NOW(),
  '1',
  NOW(),
  b'0'
);

-- 2. 获取刚插入的菜单ID
SET @menu_id = LAST_INSERT_ID();

-- 3. 插入任务下发的5个权限按钮
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, component_name, status, visible, keep_alive, always_show, creator, create_time, updater, update_time, deleted) VALUES
('任务下发查询', 'aicrm:marketing-task-assignment:query', 3, 1, @menu_id, '', '', '', NULL, 0, b'1', b'1', b'0', '1', NOW(), '1', NOW(), b'0'),
('任务下发创建', 'aicrm:marketing-task-assignment:create', 3, 2, @menu_id, '', '', '', NULL, 0, b'1', b'1', b'0', '1', NOW(), '1', NOW(), b'0'),
('任务下发更新', 'aicrm:marketing-task-assignment:update', 3, 3, @menu_id, '', '', '', NULL, 0, b'1', b'1', b'0', '1', NOW(), '1', NOW(), b'0'),
('任务下发删除', 'aicrm:marketing-task-assignment:delete', 3, 4, @menu_id, '', '', '', NULL, 0, b'1', b'1', b'0', '1', NOW(), '1', NOW(), b'0'),
('任务下发导出', 'aicrm:marketing-task-assignment:export', 3, 5, @menu_id, '', '', '', NULL, 0, b'1', b'1', b'0', '1', NOW(), '1', NOW(), b'0');

-- 4. 为超级管理员（role_id=1）分配权限
INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT 1, m.id, '1', NOW(), '1', NOW(), b'0', 1
FROM system_menu m
WHERE m.name = '营销活动下发任务' AND m.parent_id = 5321
UNION ALL
SELECT 1, m.id, '1', NOW(), '1', NOW(), b'0', 1
FROM system_menu m
WHERE m.parent_id = @menu_id;

-- 5. 为 CRM 管理员（role_id=3）分配权限
INSERT INTO system_role_menu (role_id, menu_id, creator, create_time, updater, update_time, deleted, tenant_id)
SELECT 3, m.id, '1', NOW(), '1', NOW(), b'0', 1
FROM system_menu m
WHERE m.name = '营销活动下发任务' AND m.parent_id = 5321
UNION ALL
SELECT 3, m.id, '1', NOW(), '1', NOW(), b'0', 1
FROM system_menu m
WHERE m.parent_id = @menu_id;

-- ============================================================
-- 验证脚本
-- ============================================================

-- 查看营销活动下发任务菜单及其权限按钮
SELECT
  m.id,
  m.name,
  m.permission,
  m.path,
  m.component,
  CASE m.type
    WHEN 1 THEN '目录'
    WHEN 2 THEN '菜单'
    WHEN 3 THEN '按钮'
    ELSE '未知'
  END AS type_name,
  m.sort
FROM system_menu m
WHERE m.name = '营销活动下发任务' AND m.parent_id = 5321
   OR m.parent_id = @menu_id
ORDER BY m.type, m.sort;

-- 查看权限分配情况
SELECT
  rm.role_id,
  r.name AS role_name,
  rm.menu_id,
  m.name AS menu_name,
  m.permission
FROM system_role_menu rm
JOIN system_role r ON rm.role_id = r.id
JOIN system_menu m ON rm.menu_id = m.id
WHERE rm.menu_id IN (
  SELECT id FROM system_menu
  WHERE name = '营销活动下发任务' AND parent_id = 5321
  UNION ALL
  SELECT id FROM system_menu WHERE parent_id = @menu_id
)
ORDER BY rm.role_id, m.type, m.sort;

-- ============================================================
-- 说明
-- ============================================================
-- 1. 父菜单ID: 5321（移动展业平台）
-- 2. 菜单路径: /aicrm/marketing-task-assignment
-- 3. 组件路径: aicrm/marketingtaskassignment/index
-- 4. 权限标识前缀: aicrm:marketing-task-assignment:
-- 5. 权限包括: query、create、update、delete、export
-- 6. 已分配给: 超级管理员（role_id=1）、CRM管理员（role_id=3）
-- ============================================================
