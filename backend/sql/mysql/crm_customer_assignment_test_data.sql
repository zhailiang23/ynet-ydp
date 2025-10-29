-- ----------------------------
-- 客户归属关系和调整历史测试数据
-- 为前 10 个客户创建主协办归属关系和调整历史记录
-- 使用 system_dept 和 system_users 表中的真实数据
-- ----------------------------

-- 清理测试数据（如果需要重新生成）
DELETE FROM crm_customer_assignment WHERE customer_id <= 10 AND assignment_type = 2;
DELETE FROM crm_customer_assignment_history WHERE customer_id <= 10;
UPDATE crm_customer_assignment
SET user_id = 1, dept_id = 103, remark = '系统初始化默认归属'
WHERE customer_id <= 10 AND assignment_type = 1;

-- ==================== 第一批：为客户 2-5 添加协办客户经理 ====================

-- 客户 2 增加协办（用户100=芋道，部门104=市场部门）
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `dept_id`, `user_id`,
  `has_view_right`, `has_maintain_right`, `assign_date`, `effective_date`,
  `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  2, 2, 104, 100,
  b'1', b'1', CURDATE(), CURDATE(),
  1, 1, '新增协办客户经理',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 3 增加协办（用户103=源码，部门106）
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `dept_id`, `user_id`,
  `has_view_right`, `has_maintain_right`, `assign_date`, `effective_date`,
  `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  3, 2, 106, 103,
  b'1', b'1', CURDATE(), CURDATE(),
  1, 1, '新增协办客户经理',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 4 增加两个协办（用户104=测试号 和 用户112=新对象）
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `dept_id`, `user_id`,
  `has_view_right`, `has_maintain_right`, `assign_date`, `effective_date`,
  `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES
(4, 2, 107, 104,
 b'1', b'1', CURDATE(), CURDATE(),
 1, 1, '新增协办客户经理1',
 '1', NOW(), '1', NOW(), 1),
(4, 2, 100, 112,
 b'1', b'1', CURDATE(), CURDATE(),
 1, 1, '新增协办客户经理2',
 '1', NOW(), '1', NOW(), 1);

-- 客户 5 增加协办（用户100=芋道，部门104）
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `dept_id`, `user_id`,
  `has_view_right`, `has_maintain_right`, `assign_date`, `effective_date`,
  `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  5, 2, 104, 100,
  b'1', b'1', CURDATE(), CURDATE(),
  1, 1, '新增协办客户经理',
  '1', NOW(), '1', NOW(), 1
);

-- ==================== 第二批：创建归属调整历史记录 ====================

-- 客户 6 的主办客户经理调整历史（从admin调整到芋道）
UPDATE crm_customer_assignment
SET user_id = 100,
    dept_id = 104,
    assign_date = CURDATE(),
    assign_operator_id = 1,
    remark = '主办客户经理调整',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 6 AND assignment_type = 1 AND deleted = b'0';

INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_dept_id`, `before_user_id`,
  `after_assignment_type`, `after_dept_id`, `after_user_id`,
  `assign_operator_id`, `assign_date`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  6, CURDATE(), 'manager_internal', '客户经理离职，重新分配',
  1, 103, 1,
  1, 104, 100,
  1, CURDATE(), '主办客户经理调整',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 7 的跨部门调整历史（从研发部门调整到市场部门）
UPDATE crm_customer_assignment
SET dept_id = 108,
    user_id = 100,
    assign_date = CURDATE(),
    assign_operator_id = 1,
    remark = '跨部门调整',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 7 AND assignment_type = 1 AND deleted = b'0';

INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_dept_id`, `before_user_id`,
  `after_assignment_type`, `after_dept_id`, `after_user_id`,
  `assign_operator_id`, `assign_date`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  7, CURDATE(), 'cross_sub_branch', '客户搬迁至新地址，就近分配',
  1, 103, 1,
  1, 108, 100,
  1, CURDATE(), '跨部门调整',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 8 的主协办转换历史（主办变协办，协办变主办）
-- 步骤1: 删除原主办（软删除）
UPDATE crm_customer_assignment
SET deleted = b'1',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 8 AND assignment_type = 1 AND user_id = 1 AND deleted = b'0';

-- 步骤2: 新增原主办为协办
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `dept_id`, `user_id`,
  `has_view_right`, `has_maintain_right`, `assign_date`, `effective_date`,
  `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  8, 2, 103, 1,
  b'1', b'1', CURDATE(), CURDATE(),
  1, 1, '主办改为协办',
  '1', NOW(), '1', NOW(), 1
);

-- 步骤3: 新增新的主办
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `dept_id`, `user_id`,
  `has_view_right`, `has_maintain_right`, `assign_date`, `effective_date`,
  `assign_operator_id`, `status`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  8, 1, 104, 100,
  b'1', b'1', CURDATE(), CURDATE(),
  1, 1, '协办升级为主办',
  '1', NOW(), '1', NOW(), 1
);

-- 步骤4: 记录调整历史
INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_dept_id`, `before_user_id`,
  `after_assignment_type`, `after_dept_id`, `after_user_id`,
  `assign_operator_id`, `assign_date`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  8, CURDATE(), 'branch_internal', '主协办角色调整',
  1, 103, 1,
  1, 104, 100,
  1, CURDATE(), '主协办角色转换',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 9 的批量调整历史（模拟批量归属调整场景）
UPDATE crm_customer_assignment
SET user_id = 103,
    dept_id = 106,
    assign_date = CURDATE(),
    assign_operator_id = 1,
    remark = '批量调整',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 9 AND assignment_type = 1 AND deleted = b'0';

INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_dept_id`, `before_user_id`,
  `after_assignment_type`, `after_dept_id`, `after_user_id`,
  `assign_operator_id`, `assign_date`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  9, CURDATE(), 'cross_sub_branch', '年度客户归属优化调整',
  1, 103, 1,
  1, 106, 103,
  1, CURDATE(), '批量调整',
  '1', NOW(), '1', NOW(), 1
);

-- 客户 10 的多次调整历史（模拟客户经历多次归属变更）
-- 第一次调整
UPDATE crm_customer_assignment
SET user_id = 104,
    dept_id = 107,
    assign_date = DATE_SUB(CURDATE(), INTERVAL 30 DAY),
    remark = '第一次调整',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 10 AND assignment_type = 1 AND deleted = b'0';

INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_dept_id`, `before_user_id`,
  `after_assignment_type`, `after_dept_id`, `after_user_id`,
  `assign_operator_id`, `assign_date`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  10, DATE_SUB(CURDATE(), INTERVAL 30 DAY), 'branch_internal', '客户经理工作量均衡调整',
  1, 103, 1,
  1, 107, 104,
  1, DATE_SUB(CURDATE(), INTERVAL 30 DAY), '第一次调整',
  '1', DATE_SUB(NOW(), INTERVAL 30 DAY), '1', DATE_SUB(NOW(), INTERVAL 30 DAY), 1
);

-- 第二次调整（最近的调整）
UPDATE crm_customer_assignment
SET user_id = 100,
    dept_id = 104,
    assign_date = CURDATE(),
    remark = '第二次调整',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 10 AND assignment_type = 1 AND deleted = b'0';

INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_dept_id`, `before_user_id`,
  `after_assignment_type`, `after_dept_id`, `after_user_id`,
  `assign_operator_id`, `assign_date`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  10, CURDATE(), 'cross_sub_branch', '客户业务需求变更，重新分配专业客户经理',
  1, 107, 104,
  1, 104, 100,
  1, CURDATE(), '第二次调整',
  '1', NOW(), '1', NOW(), 1
);

-- ==================== 统计查询 ====================

-- 1. 统计每个客户的归属关系数量
SELECT
  '客户归属关系统计' AS category,
  COUNT(*) AS total_assignments,
  SUM(CASE WHEN assignment_type = 1 THEN 1 ELSE 0 END) AS primary_count,
  SUM(CASE WHEN assignment_type = 2 THEN 1 ELSE 0 END) AS secondary_count,
  COUNT(DISTINCT customer_id) AS customer_with_assignment,
  COUNT(DISTINCT user_id) AS unique_managers
FROM crm_customer_assignment
WHERE deleted = b'0';

-- 2. 统计归属调整历史数量
SELECT
  '归属调整历史统计' AS category,
  COUNT(*) AS total_history_records,
  COUNT(DISTINCT customer_id) AS customers_with_history,
  SUM(CASE WHEN transfer_level = 'branch_internal' THEN 1 ELSE 0 END) AS branch_internal_count,
  SUM(CASE WHEN transfer_level = 'cross_sub_branch' THEN 1 ELSE 0 END) AS cross_sub_branch_count,
  SUM(CASE WHEN transfer_level = 'manager_internal' THEN 1 ELSE 0 END) AS manager_internal_count
FROM crm_customer_assignment_history
WHERE deleted = b'0';

-- 3. 查看前10个客户的归属情况（关联部门和用户信息）
SELECT
  c.id AS 客户ID,
  c.customer_name AS 客户姓名,
  CASE a.assignment_type WHEN 1 THEN '主办' WHEN 2 THEN '协办' END AS 归属类型,
  d.name AS 归属部门,
  u.nickname AS 客户经理,
  CASE a.status WHEN 0 THEN '已失效' WHEN 1 THEN '生效中' WHEN 2 THEN '待生效' END AS 状态,
  a.assign_date AS 分配日期
FROM crm_customer c
LEFT JOIN crm_customer_assignment a ON c.id = a.customer_id AND a.deleted = b'0'
LEFT JOIN system_dept d ON a.dept_id = d.id AND d.deleted = b'0'
LEFT JOIN system_users u ON a.user_id = u.id AND u.deleted = b'0'
WHERE c.id <= 10 AND c.deleted = b'0'
ORDER BY c.id, a.assignment_type;
