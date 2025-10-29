-- ----------------------------
-- 客户归属关系和调整历史测试数据
-- 为前 10 个客户创建主协办归属关系和调整历史记录
-- ----------------------------

-- 清理测试数据（如果需要重新生成）
-- DELETE FROM crm_customer_assignment WHERE customer_id <= 10;
-- DELETE FROM crm_customer_assignment_history WHERE customer_id <= 10;

-- ==================== 第一批：为客户 2-5 添加协办客户经理 ====================

-- 客户 2 增加协办（客户经理2）
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `institution_code`, `institution_name`,
  `manager_id`, `manager_name`, `has_view_right`, `has_maintain_right`,
  `assign_date`, `effective_date`, `assign_operator_id`, `assign_operator_name`,
  `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  2, 2, '04000', '商业衣商行', '101', '张三',
  b'1', b'1', CURDATE(), CURDATE(), '1', '系统', 1,
  '新增协办客户经理', '1', NOW(), '1', NOW(), 1
);

-- 客户 3 增加协办（客户经理3）
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `institution_code`, `institution_name`,
  `manager_id`, `manager_name`, `has_view_right`, `has_maintain_right`,
  `assign_date`, `effective_date`, `assign_operator_id`, `assign_operator_name`,
  `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  3, 2, '04000', '商业衣商行', '102', '李四',
  b'1', b'1', CURDATE(), CURDATE(), '1', '系统', 1,
  '新增协办客户经理', '1', NOW(), '1', NOW(), 1
);

-- 客户 4 增加两个协办（客户经理4和客户经理5）
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `institution_code`, `institution_name`,
  `manager_id`, `manager_name`, `has_view_right`, `has_maintain_right`,
  `assign_date`, `effective_date`, `assign_operator_id`, `assign_operator_name`,
  `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES
(4, 2, '04000', '商业衣商行', '103', '王五',
 b'1', b'1', CURDATE(), CURDATE(), '1', '系统', 1,
 '新增协办客户经理1', '1', NOW(), '1', NOW(), 1),
(4, 2, '04000', '商业衣商行', '104', '赵六',
 b'1', b'1', CURDATE(), CURDATE(), '1', '系统', 1,
 '新增协办客户经理2', '1', NOW(), '1', NOW(), 1);

-- 客户 5 增加协办（客户经理6）
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `institution_code`, `institution_name`,
  `manager_id`, `manager_name`, `has_view_right`, `has_maintain_right`,
  `assign_date`, `effective_date`, `assign_operator_id`, `assign_operator_name`,
  `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  5, 2, '04000', '商业衣商行', '105', '孙七',
  b'1', b'1', CURDATE(), CURDATE(), '1', '系统', 1,
  '新增协办客户经理', '1', NOW(), '1', NOW(), 1
);

-- ==================== 第二批：创建归属调整历史记录 ====================

-- 客户 6 的主办客户经理调整历史（从芋道调整到张三）
UPDATE crm_customer_assignment
SET manager_id = '101',
    manager_name = '张三',
    assign_date = CURDATE(),
    assign_operator_id = '1',
    assign_operator_name = '管理员',
    remark = '主办客户经理调整',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 6 AND assignment_type = 1 AND deleted = b'0';

INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_institution_code`, `before_institution_name`,
  `before_manager_id`, `before_manager_name`,
  `after_assignment_type`, `after_institution_code`, `after_institution_name`,
  `after_manager_id`, `after_manager_name`,
  `assign_operator_id`, `assign_operator_name`, `assign_date`,
  `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  6, CURDATE(), 'manager_internal', '客户经理离职，重新分配',
  1, '0043977', '商业银行', '1', '芋道',
  1, '0043977', '商业银行', '101', '张三',
  '1', '管理员', CURDATE(),
  '主办客户经理调整', '1', NOW(), '1', NOW(), 1
);

-- 客户 7 的跨机构调整历史（从总行调整到支行）
UPDATE crm_customer_assignment
SET institution_code = '04000',
    institution_name = '商业衣商行',
    manager_id = '102',
    manager_name = '李四',
    assign_date = CURDATE(),
    assign_operator_id = '1',
    assign_operator_name = '管理员',
    remark = '跨机构调整',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 7 AND assignment_type = 1 AND deleted = b'0';

INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_institution_code`, `before_institution_name`,
  `before_manager_id`, `before_manager_name`,
  `after_assignment_type`, `after_institution_code`, `after_institution_name`,
  `after_manager_id`, `after_manager_name`,
  `assign_operator_id`, `assign_operator_name`, `assign_date`,
  `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  7, CURDATE(), 'cross_sub_branch', '客户搬迁至新地址，就近分配',
  1, '0043977', '商业银行', '1', '芋道',
  1, '04000', '商业衣商行', '102', '李四',
  '1', '管理员', CURDATE(),
  '跨机构调整', '1', NOW(), '1', NOW(), 1
);

-- 客户 8 的主协办转换历史（主办变协办，协办变主办）
-- 步骤1: 删除原主办（软删除）
UPDATE crm_customer_assignment
SET deleted = b'1',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 8 AND assignment_type = 1 AND manager_id = '1' AND deleted = b'0';

-- 步骤2: 新增原主办为协办
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `institution_code`, `institution_name`,
  `manager_id`, `manager_name`, `has_view_right`, `has_maintain_right`,
  `assign_date`, `effective_date`, `assign_operator_id`, `assign_operator_name`,
  `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  8, 2, '0043977', '商业银行', '1', '芋道',
  b'1', b'1', CURDATE(), CURDATE(), '1', '管理员', 1,
  '主办改为协办', '1', NOW(), '1', NOW(), 1
);

-- 步骤3: 新增新的主办
INSERT INTO `crm_customer_assignment` (
  `customer_id`, `assignment_type`, `institution_code`, `institution_name`,
  `manager_id`, `manager_name`, `has_view_right`, `has_maintain_right`,
  `assign_date`, `effective_date`, `assign_operator_id`, `assign_operator_name`,
  `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  8, 1, '04000', '商业衣商行', '103', '王五',
  b'1', b'1', CURDATE(), CURDATE(), '1', '管理员', 1,
  '协办升级为主办', '1', NOW(), '1', NOW(), 1
);

-- 步骤4: 记录调整历史
INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_institution_code`, `before_institution_name`,
  `before_manager_id`, `before_manager_name`,
  `after_assignment_type`, `after_institution_code`, `after_institution_name`,
  `after_manager_id`, `after_manager_name`,
  `assign_operator_id`, `assign_operator_name`, `assign_date`,
  `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  8, CURDATE(), 'branch_internal', '主协办角色调整',
  1, '0043977', '商业银行', '1', '芋道',
  1, '04000', '商业衣商行', '103', '王五',
  '1', '管理员', CURDATE(),
  '主协办角色转换', '1', NOW(), '1', NOW(), 1
);

-- 客户 9 的批量调整历史（模拟批量归属调整场景）
UPDATE crm_customer_assignment
SET manager_id = '104',
    manager_name = '赵六',
    institution_code = '04000',
    institution_name = '商业衣商行',
    assign_date = CURDATE(),
    assign_operator_id = '1',
    assign_operator_name = '管理员',
    remark = '批量调整',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 9 AND assignment_type = 1 AND deleted = b'0';

INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_institution_code`, `before_institution_name`,
  `before_manager_id`, `before_manager_name`,
  `after_assignment_type`, `after_institution_code`, `after_institution_name`,
  `after_manager_id`, `after_manager_name`,
  `assign_operator_id`, `assign_operator_name`, `assign_date`,
  `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  9, CURDATE(), 'cross_sub_branch', '年度客户归属优化调整',
  1, '0043977', '商业银行', '1', '芋道',
  1, '04000', '商业衣商行', '104', '赵六',
  '1', '管理员', CURDATE(),
  '批量调整', '1', NOW(), '1', NOW(), 1
);

-- 客户 10 的多次调整历史（模拟客户经历多次归属变更）
-- 第一次调整
UPDATE crm_customer_assignment
SET manager_id = '105',
    manager_name = '孙七',
    assign_date = DATE_SUB(CURDATE(), INTERVAL 30 DAY),
    remark = '第一次调整',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 10 AND assignment_type = 1 AND deleted = b'0';

INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_institution_code`, `before_institution_name`,
  `before_manager_id`, `before_manager_name`,
  `after_assignment_type`, `after_institution_code`, `after_institution_name`,
  `after_manager_id`, `after_manager_name`,
  `assign_operator_id`, `assign_operator_name`, `assign_date`,
  `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  10, DATE_SUB(CURDATE(), INTERVAL 30 DAY), 'branch_internal', '客户经理工作量均衡调整',
  1, '0043977', '商业银行', '1', '芋道',
  1, '0043977', '商业银行', '105', '孙七',
  '1', '管理员', DATE_SUB(CURDATE(), INTERVAL 30 DAY),
  '第一次调整', '1', DATE_SUB(NOW(), INTERVAL 30 DAY), '1', DATE_SUB(NOW(), INTERVAL 30 DAY), 1
);

-- 第二次调整（最近的调整）
UPDATE crm_customer_assignment
SET manager_id = '101',
    manager_name = '张三',
    institution_code = '04000',
    institution_name = '商业衣商行',
    assign_date = CURDATE(),
    remark = '第二次调整',
    updater = '1',
    update_time = NOW()
WHERE customer_id = 10 AND assignment_type = 1 AND deleted = b'0';

INSERT INTO `crm_customer_assignment_history` (
  `customer_id`, `transfer_date`, `transfer_level`, `transfer_reason`,
  `before_assignment_type`, `before_institution_code`, `before_institution_name`,
  `before_manager_id`, `before_manager_name`,
  `after_assignment_type`, `after_institution_code`, `after_institution_name`,
  `after_manager_id`, `after_manager_name`,
  `assign_operator_id`, `assign_operator_name`, `assign_date`,
  `remark`, `creator`, `create_time`, `updater`, `update_time`, `tenant_id`
) VALUES (
  10, CURDATE(), 'cross_sub_branch', '客户业务需求变更，重新分配专业客户经理',
  1, '0043977', '商业银行', '105', '孙七',
  1, '04000', '商业衣商行', '101', '张三',
  '1', '管理员', CURDATE(),
  '第二次调整', '1', NOW(), '1', NOW(), 1
);

-- ==================== 统计查询 ====================

-- 1. 统计每个客户的归属关系数量
SELECT
  '客户归属关系统计' AS category,
  COUNT(*) AS total_assignments,
  SUM(CASE WHEN assignment_type = 1 THEN 1 ELSE 0 END) AS primary_count,
  SUM(CASE WHEN assignment_type = 2 THEN 1 ELSE 0 END) AS secondary_count,
  COUNT(DISTINCT customer_id) AS customer_with_assignment,
  COUNT(DISTINCT manager_id) AS unique_managers
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

-- 3. 查看前10个客户的归属情况
SELECT
  c.id AS 客户ID,
  c.customer_name AS 客户姓名,
  a.assignment_type AS 归属类型,
  a.institution_name AS 归属机构,
  a.manager_name AS 客户经理,
  a.status AS 状态,
  a.assign_date AS 分配日期
FROM crm_customer c
LEFT JOIN crm_customer_assignment a ON c.id = a.customer_id AND a.deleted = b'0'
WHERE c.id <= 10 AND c.deleted = b'0'
ORDER BY c.id, a.assignment_type;
