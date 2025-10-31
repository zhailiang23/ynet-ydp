-- ----------------------------
-- 客户管理关系模块 - 菜单和权限配置
-- ----------------------------

-- 注意：请根据实际情况调整父菜单ID和排序号

-- 1. 客户托管记录管理菜单
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, component_name, keep_alive, always_show, visible, create_by)
VALUES ('客户托管记录', '', 2, 50, 0, 'customer-delegation', 'ep:user-filled', 'aicrm/customerdelegation/index', 0, 'CustomerDelegation', 1, 1, 1, 'admin');

-- 获取刚插入的客户托管记录菜单ID
SET @delegation_menu_id = LAST_INSERT_ID();

-- 客户托管记录 - 子权限
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, create_by)
VALUES
('查询客户托管记录', 'aicrm:customer-delegation:query', 3, 1, @delegation_menu_id, '', '', '', 0, 'admin'),
('创建客户托管', 'aicrm:customer-delegation:create', 3, 2, @delegation_menu_id, '', '', '', 0, 'admin'),
('结束客户托管', 'aicrm:customer-delegation:update', 3, 3, @delegation_menu_id, '', '', '', 0, 'admin'),
('取消客户托管', 'aicrm:customer-delegation:delete', 3, 4, @delegation_menu_id, '', '', '', 0, 'admin');

-- 2. 客户认领申请管理菜单
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, component_name, keep_alive, always_show, visible, create_by)
VALUES ('客户认领申请', '', 2, 51, 0, 'customer-claim', 'ep:plus', 'aicrm/customerclaim/index', 0, 'CustomerClaim', 1, 1, 1, 'admin');

-- 获取刚插入的客户认领申请菜单ID
SET @claim_menu_id = LAST_INSERT_ID();

-- 客户认领申请 - 子权限
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, create_by)
VALUES
('查询客户认领申请', 'aicrm:customer-claim:query', 3, 1, @claim_menu_id, '', '', '', 0, 'admin'),
('提交客户认领申请', 'aicrm:customer-claim:create', 3, 2, @claim_menu_id, '', '', '', 0, 'admin'),
('取消客户认领申请', 'aicrm:customer-claim:delete', 3, 3, @claim_menu_id, '', '', '', 0, 'admin');

-- 3. 客户退回申请管理菜单
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, component_name, keep_alive, always_show, visible, create_by)
VALUES ('客户退回申请', '', 2, 52, 0, 'customer-return', 'ep:back', 'aicrm/customerreturn/index', 0, 'CustomerReturn', 1, 1, 1, 'admin');

-- 获取刚插入的客户退回申请菜单ID
SET @return_menu_id = LAST_INSERT_ID();

-- 客户退回申请 - 子权限
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, create_by)
VALUES
('查询客户退回申请', 'aicrm:customer-return:query', 3, 1, @return_menu_id, '', '', '', 0, 'admin'),
('提交客户退回申请', 'aicrm:customer-return:create', 3, 2, @return_menu_id, '', '', '', 0, 'admin'),
('取消客户退回申请', 'aicrm:customer-return:delete', 3, 3, @return_menu_id, '', '', '', 0, 'admin');

-- 4. 为客户归属关系管理添加批量操作权限
-- 注意：需要先查询客户归属关系管理的菜单ID，这里假设你已经有这个菜单
-- SELECT id FROM system_menu WHERE component = 'aicrm/customerassignment/index';
-- 假设客户归属关系管理菜单ID为 @assignment_menu_id，请根据实际情况修改

-- 方式1：如果你知道客户归属关系管理的菜单ID，直接使用
-- SET @assignment_menu_id = <实际的菜单ID>;

-- 方式2：动态查询（推荐）
SELECT id INTO @assignment_menu_id FROM system_menu WHERE component = 'aicrm/customerassignment/index' LIMIT 1;

-- 如果找到了客户归属关系管理菜单，则添加批量操作权限
INSERT INTO system_menu (name, permission, type, sort, parent_id, path, icon, component, status, create_by)
SELECT * FROM (
    SELECT '批量分配客户' as name, 'aicrm:customer-assignment:assign' as permission, 3 as type, 10 as sort, @assignment_menu_id as parent_id, '' as path, '' as icon, '' as component, 0 as status, 'admin' as create_by
    UNION ALL
    SELECT '批量移交客户', 'aicrm:customer-assignment:transfer', 3, 11, @assignment_menu_id, '', '', '', 0, 'admin'
    UNION ALL
    SELECT '批量回收客户', 'aicrm:customer-assignment:reclaim', 3, 12, @assignment_menu_id, '', '', '', 0, 'admin'
    UNION ALL
    SELECT '变更主办部门', 'aicrm:customer-assignment:change-dept', 3, 13, @assignment_menu_id, '', '', '', 0, 'admin'
) AS new_permissions
WHERE @assignment_menu_id IS NOT NULL;

-- ----------------------------
-- 说明
-- ----------------------------
-- 1. parent_id = 0 表示顶级菜单，请根据实际的菜单结构修改为正确的父菜单ID
-- 2. type: 1=目录, 2=菜单, 3=按钮权限
-- 3. status: 0=正常, 1=停用
-- 4. component: 前端组件路径，相对于 views 目录
-- 5. keep_alive: 1=缓存, 0=不缓存
-- 6. always_show: 1=总是显示, 0=当只有一个子菜单时自动折叠
-- 7. visible: 1=显示, 0=隐藏
-- 8. 图标使用 Element Plus 图标，格式: ep:icon-name

-- 如果需要将这些菜单放在特定的父菜单下（如"客户管理"目录下），请：
-- 1. 查询父菜单ID: SELECT id FROM system_menu WHERE name = '客户管理';
-- 2. 修改上面的 parent_id = 0 为实际的父菜单ID

-- 给超级管理员角色分配所有新权限（假设超级管理员角色ID为1）
INSERT INTO system_role_menu (role_id, menu_id, creator)
SELECT 1, id, 'admin'
FROM system_menu
WHERE permission IN (
    'aicrm:customer-delegation:query',
    'aicrm:customer-delegation:create',
    'aicrm:customer-delegation:update',
    'aicrm:customer-delegation:delete',
    'aicrm:customer-claim:query',
    'aicrm:customer-claim:create',
    'aicrm:customer-claim:delete',
    'aicrm:customer-return:query',
    'aicrm:customer-return:create',
    'aicrm:customer-return:delete',
    'aicrm:customer-assignment:assign',
    'aicrm:customer-assignment:transfer',
    'aicrm:customer-assignment:reclaim',
    'aicrm:customer-assignment:change-dept'
)
AND NOT EXISTS (
    SELECT 1 FROM system_role_menu
    WHERE role_id = 1 AND menu_id = system_menu.id
);
