-- ----------------------------
-- 客群信息表、客户归属客群表、客户归属客群调整历史表 测试数据
-- 数据说明:
-- 1. 创建10条客群记录（零售客群4个 + 对公客群4个 + 混合客群2个）
-- 2. 为所有现有客户创建客群归属关系
-- 3. 创建部分客群归属调整历史记录
-- ----------------------------

-- ==================== 客群信息测试数据（10条）====================

INSERT INTO crm_customer_group_info (
  id, group_code, group_name, group_category, member_type, customer_source,
  dept_id, manager_user_id, member_count, description, status, sort_order,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 零售客群（4个）
(1, 'RG001', '高净值零售客群', 'high_net_worth', 'retail', 'offline_marketing',
 103, 1, 0, '资产超过100万的高净值零售客户', 1, 1,
 '1', NOW(), '1', NOW(), b'0', 0),

(2, 'RG002', '活跃零售客群', 'active', 'retail', 'online_marketing',
 103, 100, 0, '近3个月有交易的活跃零售客户', 1, 2,
 '1', NOW(), '1', NOW(), b'0', 0),

(3, 'RG003', '潜力零售客群', 'potential', 'retail', 'referral',
 103, 103, 0, '具有发展潜力的零售客户', 1, 3,
 '1', NOW(), '1', NOW(), b'0', 0),

(4, 'RG004', '沉睡零售客群', 'dormant', 'retail', 'walk_in',
 103, 104, 0, '超过6个月无交易的零售客户', 1, 4,
 '1', NOW(), '1', NOW(), b'0', 0),

-- 对公客群（4个）
(5, 'CG001', '优质对公客群', 'high_net_worth', 'company', 'partner',
 104, 1, 0, '资产超过1000万的优质对公客户', 1, 5,
 '1', NOW(), '1', NOW(), b'0', 0),

(6, 'CG002', '科技行业客群', 'industry', 'company', 'online_marketing',
 104, 100, 0, '科技、互联网行业的对公客户', 1, 6,
 '1', NOW(), '1', NOW(), b'0', 0),

(7, 'CG003', '制造业客群', 'industry', 'company', 'offline_marketing',
 104, 103, 0, '制造业行业的对公客户', 1, 7,
 '1', NOW(), '1', NOW(), b'0', 0),

(8, 'CG004', '活跃对公客群', 'active', 'company', 'partner',
 104, 104, 0, '近3个月有交易的活跃对公客户', 1, 8,
 '1', NOW(), '1', NOW(), b'0', 0),

-- 混合客群（2个）
(9, 'MG001', '营销活动目标客群', 'marketing', 'mixed', 'offline_marketing',
 103, 1, 0, '2025年第一季度营销活动目标客群', 1, 9,
 '1', NOW(), '1', NOW(), b'0', 0),

(10, 'MG002', '产品持有客群', 'product', 'mixed', 'online_marketing',
 103, 100, 0, '持有理财产品的客户群', 1, 10,
 '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 客户归属客群测试数据（为所有客户分配客群）====================

-- 零售客户（ID 1-10）归属客群
INSERT INTO crm_customer_group_assignment (
  customer_id, group_id, assign_date, assign_operator_id, status,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 客户1：张三 - 高净值零售客群 + 营销活动目标客群
(1, 1, CURDATE(), 1, 1, '1', NOW(), '1', NOW(), b'0', 0),
(1, 9, CURDATE(), 1, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户2：李四 - 高净值零售客群 + 产品持有客群
(2, 1, CURDATE(), 1, 1, '1', NOW(), '1', NOW(), b'0', 0),
(2, 10, CURDATE(), 1, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户3：王五 - 高净值零售客群
(3, 1, CURDATE(), 1, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户4：赵六 - 活跃零售客群
(4, 2, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户5：孙七 - 活跃零售客群 + 产品持有客群
(5, 2, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),
(5, 10, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户6：周八 - 潜力零售客群
(6, 3, CURDATE(), 103, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户7：吴九 - 潜力零售客群 + 营销活动目标客群
(7, 3, CURDATE(), 103, 1, '1', NOW(), '1', NOW(), b'0', 0),
(7, 9, CURDATE(), 103, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户8：郑十 - 沉睡零售客群
(8, 4, CURDATE(), 104, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户9：钱十一 - 沉睡零售客群
(9, 4, CURDATE(), 104, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户10：冯十二 - 活跃零售客群
(10, 2, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 对公客户（ID 11-20）归属客群
-- 客户11：北京科技有限公司 - 科技行业客群 + 优质对公客群 + 营销活动目标客群
(11, 6, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),
(11, 5, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),
(11, 9, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户12：上海贸易股份有限公司 - 优质对公客群 + 产品持有客群
(12, 5, CURDATE(), 1, 1, '1', NOW(), '1', NOW(), b'0', 0),
(12, 10, CURDATE(), 1, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户13：深圳制造有限公司 - 制造业客群 + 活跃对公客群
(13, 7, CURDATE(), 103, 1, '1', NOW(), '1', NOW(), b'0', 0),
(13, 8, CURDATE(), 103, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户14：杭州电商有限公司 - 科技行业客群 + 活跃对公客群
(14, 6, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),
(14, 8, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户15：南京建筑工程有限公司 - 活跃对公客群
(15, 8, CURDATE(), 104, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户16：成都餐饮管理有限公司 - 活跃对公客群 + 营销活动目标客群
(16, 8, CURDATE(), 104, 1, '1', NOW(), '1', NOW(), b'0', 0),
(16, 9, CURDATE(), 104, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户17：武汉物流有限公司 - 活跃对公客群
(17, 8, CURDATE(), 104, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户18：广州医疗器械有限公司 - 制造业客群 + 产品持有客群
(18, 7, CURDATE(), 103, 1, '1', NOW(), '1', NOW(), b'0', 0),
(18, 10, CURDATE(), 103, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户19：西安软件开发有限公司 - 科技行业客群 + 优质对公客群
(19, 6, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),
(19, 5, CURDATE(), 100, 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户20：长沙制造业有限公司 - 制造业客群
(20, 7, CURDATE(), 103, 1, '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 客户归属客群调整历史测试数据（8条）====================

INSERT INTO crm_customer_group_history (
  customer_id, adjust_date, adjust_reason,
  before_group_id, before_group_code, before_group_name, before_group_category, before_manager_user_id,
  after_group_id, after_group_code, after_group_name, after_group_category, after_manager_user_id,
  adjust_operator_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 零售客户调整历史
-- 客户3：王五 从潜力客群升级到高净值客群
(3, DATE_SUB(CURDATE(), INTERVAL 60 DAY), '客户资产增长超过100万，升级为高净值客群',
 3, 'RG003', '潜力零售客群', 'potential', 103,
 1, 'RG001', '高净值零售客群', 'high_net_worth', 1,
 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户4：赵六 从沉睡客群转为活跃客群
(4, DATE_SUB(CURDATE(), INTERVAL 45 DAY), '客户恢复交易，从沉睡客群转为活跃客群',
 4, 'RG004', '沉睡零售客群', 'dormant', 104,
 2, 'RG002', '活跃零售客群', 'active', 100,
 100, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户8：郑十 从活跃客群转为沉睡客群
(8, DATE_SUB(CURDATE(), INTERVAL 30 DAY), '客户超过6个月无交易，转为沉睡客群',
 2, 'RG002', '活跃零售客群', 'active', 100,
 4, 'RG004', '沉睡零售客群', 'dormant', 104,
 100, '1', NOW(), '1', NOW(), b'0', 0),

-- 对公客户调整历史
-- 客户11：北京科技有限公司 升级为优质对公客群
(11, DATE_SUB(CURDATE(), INTERVAL 90 DAY), '客户资产增长超过1000万，升级为优质对公客群',
 6, 'CG002', '科技行业客群', 'industry', 100,
 5, 'CG001', '优质对公客群', 'high_net_worth', 1,
 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户13：深圳制造有限公司 新加入活跃对公客群
(13, DATE_SUB(CURDATE(), INTERVAL 50 DAY), '客户交易活跃度提升，加入活跃对公客群',
 NULL, NULL, NULL, NULL, NULL,
 8, 'CG004', '活跃对公客群', 'active', 104,
 104, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户14：杭州电商有限公司 新加入活跃对公客群
(14, DATE_SUB(CURDATE(), INTERVAL 35 DAY), '客户交易活跃度提升，加入活跃对公客群',
 NULL, NULL, NULL, NULL, NULL,
 8, 'CG004', '活跃对公客群', 'active', 104,
 104, '1', NOW(), '1', NOW(), b'0', 0),

-- 客户19：西安软件开发有限公司 升级为优质对公客群
(19, DATE_SUB(CURDATE(), INTERVAL 70 DAY), '客户资产增长超过1000万，升级为优质对公客群',
 6, 'CG002', '科技行业客群', 'industry', 100,
 5, 'CG001', '优质对公客群', 'high_net_worth', 1,
 1, '1', NOW(), '1', NOW(), b'0', 0),

-- 混合客群调整历史
-- 客户7：吴九 新加入营销活动目标客群
(7, DATE_SUB(CURDATE(), INTERVAL 20 DAY), '纳入2025年第一季度营销活动目标客群',
 NULL, NULL, NULL, NULL, NULL,
 9, 'MG001', '营销活动目标客群', 'marketing', 1,
 1, '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 更新客群成员数量（冗余字段）====================
UPDATE crm_customer_group_info cg
SET member_count = (
  SELECT COUNT(DISTINCT cga.customer_id)
  FROM crm_customer_group_assignment cga
  WHERE cga.group_id = cg.id
    AND cga.deleted = b'0'
    AND cga.status = 1
);

-- ==================== 数据统计验证 ====================

-- 1. 客群信息统计
SELECT
  '客群信息统计' AS category,
  COUNT(*) AS total_group_count,
  SUM(CASE WHEN member_type = 'retail' THEN 1 ELSE 0 END) AS retail_group_count,
  SUM(CASE WHEN member_type = 'company' THEN 1 ELSE 0 END) AS company_group_count,
  SUM(CASE WHEN member_type = 'mixed' THEN 1 ELSE 0 END) AS mixed_group_count,
  SUM(CASE WHEN status = 1 THEN 1 ELSE 0 END) AS active_group_count
FROM crm_customer_group_info
WHERE deleted = b'0';

-- 2. 客户客群归属统计
SELECT
  '客户客群归属统计' AS category,
  COUNT(*) AS total_assignment_count,
  COUNT(DISTINCT customer_id) AS customers_with_group,
  COUNT(DISTINCT group_id) AS used_groups
FROM crm_customer_group_assignment
WHERE deleted = b'0' AND status = 1;

-- 3. 客群调整历史统计
SELECT
  '客群调整历史统计' AS category,
  COUNT(*) AS total_history_count,
  COUNT(DISTINCT customer_id) AS customers_with_history,
  SUM(CASE WHEN before_group_id IS NULL THEN 1 ELSE 0 END) AS new_assignment_count,
  SUM(CASE WHEN before_group_id IS NOT NULL THEN 1 ELSE 0 END) AS adjust_count
FROM crm_customer_group_history
WHERE deleted = b'0';

-- 4. 客群成员分布（按客群统计）
SELECT
  cg.group_code AS 客群编号,
  cg.group_name AS 客群名称,
  cg.group_category AS 客群分类,
  cg.member_type AS 成员类型,
  CONCAT(IFNULL(u.nickname, '未指定'), ' (', IFNULL(u.username, '-'), ')') AS 客群管理员,
  cg.member_count AS 成员数量
FROM crm_customer_group_info cg
LEFT JOIN system_users u ON cg.manager_user_id = u.id
WHERE cg.deleted = b'0'
ORDER BY cg.sort_order;

-- 5. 客户客群归属明细（前20个客户）
SELECT
  c.id AS 客户ID,
  c.customer_name AS 客户姓名,
  c.customer_type AS 客户类型,
  GROUP_CONCAT(cg.group_name ORDER BY cg.id SEPARATOR ' | ') AS 所属客群
FROM crm_customer c
LEFT JOIN crm_customer_group_assignment cga ON c.id = cga.customer_id AND cga.deleted = b'0' AND cga.status = 1
LEFT JOIN crm_customer_group_info cg ON cga.group_id = cg.id AND cg.deleted = b'0'
WHERE c.deleted = b'0'
GROUP BY c.id, c.customer_name, c.customer_type
ORDER BY c.id
LIMIT 20;

-- 6. 表结构对比（验证设计原则）
SELECT
  '表结构对比' AS category,
  '客户归属客群表' AS table_name,
  '只记录group_id，客群信息通过关联查询' AS design_principle,
  'customer_id + group_id + 分配信息(3) = 5个核心字段' AS field_summary
UNION ALL
SELECT
  '表结构对比' AS category,
  '客群调整历史表' AS table_name,
  '记录调整当时的客群信息快照' AS design_principle,
  'before_group_* (5字段) + after_group_* (5字段) = 10个快照字段' AS field_summary;
