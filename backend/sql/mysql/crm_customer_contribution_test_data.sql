-- ----------------------------
-- 客户贡献度信息测试数据
-- 说明:
-- 1. 为所有20个客户（10个零售客户 + 10个对公客户）创建贡献度信息
-- 2. 每个客户创建1条当前贡献度信息（1对1关系）
-- 3. 贡献度数据与客户等级、资产规模相匹配
-- 4. 综合贡献度 = 存款贡献度 + 贷款贡献度 + 中间业务贡献度
-- 5. 统计时间为2025年10月
-- ----------------------------

-- ==================== 零售客户贡献度信息 ====================

-- 客户1: 张三 (customer_id=1) - 财富客户，高贡献度
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `contri_deposit`,
  `contribution_loan`, `contribution_mid`, `contribution_cust`, `contri_bill_discount`,
  `contri_internation`, `etl_date`, `wealth_management_contribution`, `card_contribution`,
  `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`,
  `contribution_level`, `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(1, 1, 4788.74, 1107.49, 3681.25, 0, '2025-10-28', '张三', '100001', 1107.49, 3681.25, 0, 4788.74,
 0, 0, '2025-10-28', 850.30, 120.50, 80.20, 45.80, 1, 'super', 15.80, 3.50, 'monthly',
 '财富客户，综合贡献度排名第一', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户2: 李四 (customer_id=2) - 贵宾客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `card_contribution`, `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`,
  `contribution_level`, `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(2, 1, 3250.80, 980.50, 2270.30, 0, '2025-10-28', '李四', '100002', 620.40, 95.30, 60.50, 38.20,
 3, 'level_1', 12.50, 2.80, 'monthly', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户3: 王五 (customer_id=3) - 优质客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `card_contribution`, `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`,
  `contribution_level`, `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(3, 1, 1850.60, 520.30, 1330.30, 0, '2025-10-27', '王五', '100003', 380.20, 68.50, 45.30, 28.60,
 8, 'level_2', 8.60, 1.50, 'monthly', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户4: 赵六 (customer_id=4) - 财富客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `card_contribution`, `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`,
  `contribution_level`, `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(4, 1, 4520.90, 1050.80, 3470.10, 0, '2025-10-26', '赵六', '100004', 780.50, 110.30, 75.40, 42.50,
 2, 'super', 14.20, 3.10, 'monthly', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户5: 孙七 (customer_id=5) - 优质客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `card_contribution`, `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`,
  `contribution_level`, `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(5, 1, 1920.40, 540.20, 1380.20, 0, '2025-10-25', '孙七', '100005', 410.50, 72.80, 48.20, 30.50,
 7, 'level_2', 9.30, 1.80, 'monthly', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户6: 周八 (customer_id=6) - 普通客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `card_contribution`, `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`,
  `contribution_level`, `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(6, 1, 1280.50, 380.60, 899.90, 0, '2025-10-24', '周八', '100006', 280.30, 52.40, 35.60, 22.10,
 12, 'level_3', 6.50, 1.20, 'monthly', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户7: 吴九 (customer_id=7) - 贵宾客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `card_contribution`, `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`,
  `contribution_level`, `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(7, 1, 3680.70, 920.50, 2760.20, 0, '2025-10-23', '吴九', '100007', 680.40, 105.20, 68.30, 40.60,
 4, 'level_1', 13.40, 2.90, 'monthly', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户8: 郑十 (customer_id=8) - 普通客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `card_contribution`, `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`,
  `contribution_level`, `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(8, 1, 1150.80, 350.30, 800.50, 0, '2025-10-22', '郑十', '100008', 250.40, 48.60, 32.50, 20.30,
 14, 'level_3', 5.80, 0.90, 'monthly', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户9: 陈十一 (customer_id=9) - 基础客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `card_contribution`, `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`,
  `contribution_level`, `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(9, 1, 680.40, 210.50, 469.90, 0, '2025-10-21', '陈十一', '100009', 150.20, 35.80, 24.10, 15.40,
 18, 'level_4', 3.20, 0.50, 'monthly', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户10: 黄十二 (customer_id=10) - 财富客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `card_contribution`, `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`,
  `contribution_level`, `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(10, 1, 5120.60, 1180.40, 3940.20, 0, '2025-10-20', '黄十二', '100010', 920.50, 135.80, 88.60, 52.30,
 0, 'super', 16.50, 4.20, 'monthly', '1', NOW(), '1', NOW(), b'0', 1);

-- ==================== 对公客户贡献度信息 ====================

-- 客户11: 杭州科技有限公司 (customer_id=11) - 财富客户，高贡献度
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `contri_bill_discount`,
  `contri_internation`, `wealth_management_contribution`, `card_contribution`, `settlement_contribution`,
  `electronic_banking_contribution`, `contribution_rank`, `contribution_level`, `year_over_year_growth`,
  `month_over_month_growth`, `statistics_period`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(11, 1, 18500.80, 4200.50, 12300.30, 2000.00, '2025-10-19', '杭州科技有限公司', '100001',
 850.40, 420.30, 1200.60, 0, 380.50, 220.40, 5, 'super', 22.50, 5.80, 'monthly',
 '科技企业，业务增长迅速', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户12: 浙江贸易股份有限公司 (customer_id=12) - 贵宾客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `contri_bill_discount`,
  `contri_internation`, `wealth_management_contribution`, `settlement_contribution`,
  `electronic_banking_contribution`, `contribution_rank`, `contribution_level`, `year_over_year_growth`,
  `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(12, 1, 28600.90, 6500.30, 19100.60, 3000.00, '2025-10-18', '浙江贸易股份有限公司', '100002',
 1200.50, 1850.60, 1800.40, 520.30, 340.50, 0, 'level_1', 25.80, 6.50, 'monthly',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户13: 宁波制造业集团 (customer_id=13) - 财富客户，最高贡献度
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `contri_bill_discount`,
  `contri_internation`, `wealth_management_contribution`, `settlement_contribution`,
  `electronic_banking_contribution`, `contribution_rank`, `contribution_level`, `year_over_year_growth`,
  `month_over_month_growth`, `statistics_period`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(13, 1, 52000.50, 11800.20, 36200.30, 4000.00, '2025-10-17', '宁波制造业集团', '100003',
 1800.60, 680.50, 3500.80, 750.40, 480.60, 0, 'super', 28.60, 7.20, 'monthly',
 '制造业龙头企业，贡献度极高', '1', NOW(), '1', NOW(), b'0', 1);

-- 客户14: 温州建设工程公司 (customer_id=14) - 优质客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `contri_bill_discount`,
  `wealth_management_contribution`, `settlement_contribution`, `electronic_banking_contribution`,
  `contribution_rank`, `contribution_level`, `year_over_year_growth`, `month_over_month_growth`,
  `statistics_period`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(14, 1, 38000.40, 8600.50, 27400.90, 2000.00, '2025-10-16', '温州建设工程公司', '100004',
 950.30, 1500.60, 420.80, 280.50, 9, 'level_2', 18.50, 4.50, 'monthly',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户15: 绍兴纺织实业公司 (customer_id=15) - 贵宾客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `contri_bill_discount`,
  `contri_internation`, `wealth_management_contribution`, `settlement_contribution`,
  `electronic_banking_contribution`, `contribution_rank`, `contribution_level`, `year_over_year_growth`,
  `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(15, 1, 26000.70, 5900.40, 18100.30, 2000.00, '2025-10-15', '绍兴纺织实业公司', '100005',
 880.50, 650.40, 1600.30, 450.60, 310.20, 6, 'level_1', 20.30, 5.20, 'monthly',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户16: 金华物流运输公司 (customer_id=16) - 优质客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`, `contribution_level`,
  `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(16, 1, 18000.60, 4100.30, 12900.30, 1000.00, '2025-10-14', '金华物流运输公司', '100006',
 1200.40, 380.20, 260.30, 10, 'level_2', 16.80, 4.10, 'monthly',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户17: 台州电子科技公司 (customer_id=17) - 普通客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`, `contribution_level`,
  `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(17, 1, 14000.50, 3200.20, 9800.30, 1000.00, '2025-10-13', '台州电子科技公司', '100007',
 950.40, 320.50, 220.30, 11, 'level_3', 14.20, 3.60, 'monthly',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户18: 嘉兴食品加工厂 (customer_id=18) - 普通客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `wealth_management_contribution`,
  `settlement_contribution`, `electronic_banking_contribution`, `contribution_rank`, `contribution_level`,
  `year_over_year_growth`, `month_over_month_growth`, `statistics_period`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(18, 1, 12000.80, 2800.50, 8200.30, 1000.00, '2025-10-12', '嘉兴食品加工厂', '100008',
 850.30, 290.40, 200.50, 13, 'level_3', 12.50, 3.10, 'monthly',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户19: 湖州化工材料公司 (customer_id=19) - 优质客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `contri_bill_discount`,
  `wealth_management_contribution`, `settlement_contribution`, `electronic_banking_contribution`,
  `contribution_rank`, `contribution_level`, `year_over_year_growth`, `month_over_month_growth`,
  `statistics_period`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(19, 1, 13000.90, 3000.40, 8500.50, 1500.00, '2025-10-11', '湖州化工材料公司', '100009',
 680.30, 1100.50, 350.60, 240.80, 12, 'level_2', 15.60, 3.80, 'monthly',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户20: 衢州矿业开发公司 (customer_id=20) - 贵宾客户
INSERT INTO `crm_customer_contribution` (
  `customer_id`, `sequence_no`, `total_contribution`, `deposit_contribution`, `loan_contribution`,
  `intermediate_contribution`, `statistics_time`, `cust_name`, `org_id`, `contri_bill_discount`,
  `wealth_management_contribution`, `settlement_contribution`, `electronic_banking_contribution`,
  `contribution_rank`, `contribution_level`, `year_over_year_growth`, `month_over_month_growth`,
  `statistics_period`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(20, 1, 30000.80, 6800.60, 21200.20, 2000.00, '2025-10-10', '衢州矿业开发公司', '100010',
 950.40, 2200.50, 580.30, 380.60, 5, 'level_1', 24.50, 6.20, 'monthly',
 '1', NOW(), '1', NOW(), b'0', 1);


-- ==================== 数据验证查询 ====================

-- 1. 统计每个客户的贡献度信息
SELECT
  customer_id,
  cust_name,
  total_contribution,
  deposit_contribution,
  loan_contribution,
  intermediate_contribution,
  contribution_level,
  contribution_rank,
  statistics_time
FROM crm_customer_contribution
ORDER BY total_contribution DESC;

-- 2. 统计各贡献度等级的客户数量
SELECT
  contribution_level,
  COUNT(*) as customer_count,
  SUM(total_contribution) as total_amount
FROM crm_customer_contribution
GROUP BY contribution_level
ORDER BY SUM(total_contribution) DESC;

-- 3. 统计零售客户和对公客户的贡献度对比
SELECT
  CASE WHEN customer_id <= 10 THEN '零售客户' ELSE '对公客户' END as customer_type,
  COUNT(*) as customer_count,
  SUM(total_contribution) as total_contribution,
  AVG(total_contribution) as avg_contribution,
  SUM(deposit_contribution) as total_deposit,
  SUM(loan_contribution) as total_loan,
  SUM(intermediate_contribution) as total_intermediate
FROM crm_customer_contribution
GROUP BY CASE WHEN customer_id <= 10 THEN '零售客户' ELSE '对公客户' END;

-- 4. 查询贡献度排名前10的客户
SELECT
  customer_id,
  cust_name,
  total_contribution,
  contribution_level,
  contribution_rank,
  year_over_year_growth
FROM crm_customer_contribution
ORDER BY total_contribution DESC
LIMIT 10;

-- 5. 查询各业务线的贡献度汇总
SELECT
  SUM(deposit_contribution) as total_deposit_contribution,
  SUM(loan_contribution) as total_loan_contribution,
  SUM(intermediate_contribution) as total_intermediate_contribution,
  SUM(wealth_management_contribution) as total_wealth_contribution,
  SUM(card_contribution) as total_card_contribution,
  SUM(settlement_contribution) as total_settlement_contribution,
  SUM(electronic_banking_contribution) as total_ebanking_contribution,
  SUM(contri_bill_discount) as total_bill_discount,
  SUM(contri_internation) as total_international
FROM crm_customer_contribution;

-- 6. 查询同比增长率最高的前5名客户
SELECT
  customer_id,
  cust_name,
  total_contribution,
  year_over_year_growth,
  month_over_month_growth,
  contribution_level
FROM crm_customer_contribution
ORDER BY year_over_year_growth DESC
LIMIT 5;

-- 7. 验证1对1关系（每个客户只有一条贡献度记录）
SELECT
  customer_id,
  COUNT(*) as record_count
FROM crm_customer_contribution
GROUP BY customer_id
HAVING COUNT(*) > 1;
