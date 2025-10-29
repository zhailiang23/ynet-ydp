-- ----------------------------
-- 客户账户信息表测试数据（8张表）
-- 数据说明:
-- 1. 为所有现有客户（20个客户：10个零售 + 10个对公）创建账户信息
-- 2. 存款账户：每个客户1-2个账户
-- 3. 贷款账户：对公客户和部分零售客户
-- 4. 理财账户：部分客户
-- 5. 基金账户：零售客户
-- 6. 保险账户：零售客户
-- 7. 信托账户：高净值客户
-- 8. 贵金属账户：部分零售客户
-- 9. 信用卡账户：零售客户
-- ----------------------------

-- ==================== 1. 存款账户测试数据 ====================

INSERT INTO crm_customer_deposit_account (
  customer_id, account_no, product_name, account_name, open_date, close_date, account_status,
  interest_rate, deposit_term, balance, agr_no, product_id, card_no, deposit_type, currency_type,
  original_amount, mature_date, start_interest_date, dept_id, dept_name, manager_user_id,
  month_avg_balance, quarter_avg_balance, year_avg_balance,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 零售客户存款账户（客户1-10）
(1, '2222222222222222', '产品A', '欧阳大海', '2020-02-02', NULL, 'normal',
 2.03, '1年', 80000000000.00, 'AGR001', 'PROD001', 'CARD001', '2', 'CNY',
 80000000000.00, '2021-02-02', '2020-02-02', 103, '南海农商行', 1,
 80000000000.00, 80000000000.00, 80000000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(2, '2222222222222223', '产品A2', '李四', '2020-03-15', NULL, 'normal',
 2.10, '6个月', 50000000000.00, 'AGR002', 'PROD001', 'CARD002', '2', 'CNY',
 50000000000.00, '2020-09-15', '2020-03-15', 103, '南海农商行', 1,
 50000000000.00, 50000000000.00, 50000000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(3, '3333333333333333', '产品B', '张瑞珊', '2020-02-02', '2020-02-02', 'closed',
 0.98, '6个月', 0.00, 'AGR003', 'PROD002', 'CARD003', '2', 'CNY',
 20000000000.00, '2020-08-02', '2020-02-02', 103, '南海农商行', 1,
 0, 0, 0,
 '1', NOW(), '1', NOW(), b'0', 0),

(4, '4444444444444444', '产品C', '刘诗诗', '2020-02-02', NULL, 'normal',
 1.25, '3个月', 10000000000.00, 'AGR004', 'PROD003', 'CARD004', '2', 'CNY',
 10000000000.00, '2020-05-02', '2020-02-02', 103, '南海农商行', 100,
 10000000000.00, 10000000000.00, 10000000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(5, '5555555555555555', '产品D', '刘佳', '2020-02-02', NULL, 'normal',
 3.05, '10年', 10000000000.00, 'AGR005', 'PROD004', 'CARD005', '2', 'CNY',
 10000000000.00, '2030-02-02', '2020-02-02', 103, '南海农商行', 100,
 10000000000.00, 10000000000.00, 10000000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(6, '6666666666666666', '活期存款', '周八', '2021-05-10', NULL, 'normal',
 0.35, '活期', 5000000.00, 'AGR006', 'PROD005', 'CARD006', '1', 'CNY',
 5000000.00, NULL, '2021-05-10', 103, '南海农商行', 103,
 5000000.00, 5000000.00, 5000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(7, '7777777777777777', '定期存款', '吴九', '2021-06-20', NULL, 'normal',
 2.75, '3年', 30000000.00, 'AGR007', 'PROD006', 'CARD007', '2', 'CNY',
 30000000.00, '2024-06-20', '2021-06-20', 103, '南海农商行', 103,
 30000000.00, 30000000.00, 30000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(8, '8888888888888888', '活期存款', '郑十', '2022-01-15', NULL, 'normal',
 0.35, '活期', 3000000.00, 'AGR008', 'PROD005', 'CARD008', '1', 'CNY',
 3000000.00, NULL, '2022-01-15', 104, '南海农商行', 104,
 3000000.00, 3000000.00, 3000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(9, '9999999999999999', '活期存款', '钱十一', '2022-02-20', NULL, 'normal',
 0.35, '活期', 2000000.00, 'AGR009', 'PROD005', 'CARD009', '1', 'CNY',
 2000000.00, NULL, '2022-02-20', 104, '南海农商行', 104,
 2000000.00, 2000000.00, 2000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(10, '1000000000000000', '定期存款', '冯十二', '2021-08-10', NULL, 'normal',
 2.50, '1年', 15000000.00, 'AGR010', 'PROD001', 'CARD010', '2', 'CNY',
 15000000.00, '2022-08-10', '2021-08-10', 103, '南海农商行', 100,
 15000000.00, 15000000.00, 15000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

-- 对公客户存款账户（客户11-20）
(11, 'C1111111111111111', '对公活期账户', '北京科技有限公司', '2020-01-05', NULL, 'normal',
 0.35, '活期', 500000000.00, 'AGRC011', 'PRODC001', NULL, '1', 'CNY',
 500000000.00, NULL, '2020-01-05', 104, '南海农商行', 100,
 500000000.00, 500000000.00, 500000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(12, 'C1111111111111112', '对公定期账户', '上海贸易股份有限公司', '2020-03-15', NULL, 'normal',
 3.00, '2年', 1000000000.00, 'AGRC012', 'PRODC002', NULL, '2', 'CNY',
 1000000000.00, '2022-03-15', '2020-03-15', 104, '南海农商行', 1,
 1000000000.00, 1000000000.00, 1000000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(13, 'C1111111111111113', '对公活期账户', '深圳制造有限公司', '2019-08-20', NULL, 'normal',
 0.35, '活期', 300000000.00, 'AGRC013', 'PRODC001', NULL, '1', 'CNY',
 300000000.00, NULL, '2019-08-20', 104, '南海农商行', 103,
 300000000.00, 300000000.00, 300000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(14, 'C1111111111111114', '对公定期账户', '杭州电商有限公司', '2021-01-10', NULL, 'normal',
 2.75, '1年', 200000000.00, 'AGRC014', 'PRODC002', NULL, '2', 'CNY',
 200000000.00, '2022-01-10', '2021-01-10', 104, '南海农商行', 100,
 200000000.00, 200000000.00, 200000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(15, 'C1111111111111115', '对公活期账户', '南京建筑工程有限公司', '2020-05-25', NULL, 'normal',
 0.35, '活期', 400000000.00, 'AGRC015', 'PRODC001', NULL, '1', 'CNY',
 400000000.00, NULL, '2020-05-25', 104, '南海农商行', 104,
 400000000.00, 400000000.00, 400000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(16, 'C1111111111111116', '对公活期账户', '成都餐饮管理有限公司', '2021-03-10', NULL, 'normal',
 0.35, '活期', 150000000.00, 'AGRC016', 'PRODC001', NULL, '1', 'CNY',
 150000000.00, NULL, '2021-03-10', 104, '南海农商行', 104,
 150000000.00, 150000000.00, 150000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(17, 'C1111111111111117', '对公活期账户', '武汉物流有限公司', '2020-07-15', NULL, 'normal',
 0.35, '活期', 250000000.00, 'AGRC017', 'PRODC001', NULL, '1', 'CNY',
 250000000.00, NULL, '2020-07-15', 104, '南海农商行', 104,
 250000000.00, 250000000.00, 250000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(18, 'C1111111111111118', '对公定期账户', '广州医疗器械有限公司', '2021-02-20', NULL, 'normal',
 3.25, '3年', 600000000.00, 'AGRC018', 'PRODC002', NULL, '2', 'CNY',
 600000000.00, '2024-02-20', '2021-02-20', 104, '南海农商行', 103,
 600000000.00, 600000000.00, 600000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(19, 'C1111111111111119', '对公活期账户', '西安软件开发有限公司', '2020-09-10', NULL, 'normal',
 0.35, '活期', 350000000.00, 'AGRC019', 'PRODC001', NULL, '1', 'CNY',
 350000000.00, NULL, '2020-09-10', 104, '南海农商行', 100,
 350000000.00, 350000000.00, 350000000.00,
 '1', NOW(), '1', NOW(), b'0', 0),

(20, 'C1111111111111120', '对公活期账户', '长沙制造业有限公司', '2021-04-15', NULL, 'normal',
 0.35, '活期', 280000000.00, 'AGRC020', 'PRODC001', NULL, '1', 'CNY',
 280000000.00, NULL, '2021-04-15', 103, '南海农商行', 103,
 280000000.00, 280000000.00, 280000000.00,
 '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 2. 贷款账户测试数据 ====================

INSERT INTO crm_customer_loan_account (
  customer_id, account_no, contract_no, agr_no, product_name, product_id, account_name,
  open_date, close_date, account_status, contract_amount, loan_amount, balance, currency_type,
  interest_rate, loan_term, loan_term_unit, mature_date, repayment_mode,
  loan_purpose, loan_type, guarantee_type, business_type, five_level_class,
  overdue_days, overdue_principal, overdue_interest, overdue_times,
  dept_id, dept_name, manager_user_id,
  month_avg_balance, quarter_avg_balance, year_avg_balance,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 零售客户贷款（消费贷款）
(1, 'LOAN0001', 'CONT0001', 'LAGR001', '个人消费贷款', 'LPROD001', '张三',
 '2021-01-10', NULL, 'normal', 500000.00, 500000.00, 300000.00, 'CNY',
 4.750000000, 36, 'month', '2024-01-10', '等额本息',
 '装修', '消费贷款', '抵押', '个人贷款', 'normal',
 0, 0.0000, 0.0000, 0,
 103, '南海农商行', 1,
 300000.0000, 300000.0000, 300000.0000,
 '1', NOW(), '1', NOW(), b'0', 0),

(4, 'LOAN0002', 'CONT0002', 'LAGR002', '个人经营贷款', 'LPROD002', '赵六',
 '2020-06-15', NULL, 'normal', 1000000.00, 1000000.00, 800000.00, 'CNY',
 5.200000000, 60, 'month', '2025-06-15', '等额本息',
 '小微企业经营', '经营贷款', '担保', '个人贷款', 'normal',
 0, 0.0000, 0.0000, 0,
 103, '南海农商行', 100,
 800000.0000, 800000.0000, 800000.0000,
 '1', NOW(), '1', NOW(), b'0', 0),

-- 对公客户贷款（企业贷款）
(11, 'LOAN0011', 'CONT0011', 'LAGRC011', '流动资金贷款', 'LPROD011', '北京科技有限公司',
 '2020-03-01', NULL, 'normal', 50000000.00, 50000000.00, 40000000.00, 'CNY',
 4.350000000, 12, 'month', '2021-03-01', '按月付息到期还本',
 '补充流动资金', '流动资金贷款', '抵押+保证', '对公贷款', 'normal',
 0, 0.0000, 0.0000, 0,
 104, '南海农商行', 1,
 40000000.0000, 40000000.0000, 40000000.0000,
 '1', NOW(), '1', NOW(), b'0', 0),

(12, 'LOAN0012', 'CONT0012', 'LAGRC012', '固定资产贷款', 'LPROD012', '上海贸易股份有限公司',
 '2019-05-20', NULL, 'normal', 100000000.00, 100000000.00, 85000000.00, 'CNY',
 4.650000000, 60, 'month', '2024-05-20', '等额本息',
 '购置设备', '固定资产贷款', '抵押', '对公贷款', 'normal',
 0, 0.0000, 0.0000, 0,
 104, '南海农商行', 1,
 85000000.0000, 85000000.0000, 85000000.0000,
 '1', NOW(), '1', NOW(), b'0', 0),

(13, 'LOAN0013', 'CONT0013', 'LAGRC013', '流动资金贷款', 'LPROD011', '深圳制造有限公司',
 '2020-08-15', NULL, 'overdue', 30000000.00, 30000000.00, 25000000.00, 'CNY',
 4.500000000, 24, 'month', '2022-08-15', '按季付息到期还本',
 '原材料采购', '流动资金贷款', '保证', '对公贷款', 'concern',
 15, 500000.0000, 25000.0000, 1,
 104, '南海农商行', 103,
 25000000.0000, 25000000.0000, 25000000.0000,
 '1', NOW(), '1', NOW(), b'0', 0),

(15, 'LOAN0014', 'CONT0014', 'LAGRC014', '项目贷款', 'LPROD013', '南京建筑工程有限公司',
 '2021-01-10', NULL, 'normal', 80000000.00, 80000000.00, 70000000.00, 'CNY',
 4.750000000, 36, 'month', '2024-01-10', '按月付息到期还本',
 '工程项目建设', '项目贷款', '抵押+保证', '对公贷款', 'normal',
 0, 0.0000, 0.0000, 0,
 104, '南海农商行', 104,
 70000000.0000, 70000000.0000, 70000000.0000,
 '1', NOW(), '1', NOW(), b'0', 0),

(18, 'LOAN0015', 'CONT0015', 'LAGRC015', '流动资金贷款', 'LPROD011', '广州医疗器械有限公司',
 '2021-03-20', NULL, 'normal', 40000000.00, 40000000.00, 35000000.00, 'CNY',
 4.450000000, 12, 'month', '2022-03-20', '按月付息到期还本',
 '采购原材料', '流动资金贷款', '抵押', '对公贷款', 'normal',
 0, 0.0000, 0.0000, 0,
 104, '南海农商行', 103,
 35000000.0000, 35000000.0000, 35000000.0000,
 '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 3. 理财账户测试数据（精简版）====================

INSERT INTO crm_customer_wealth_account (
  customer_id, account_no, product_code, product_name, account_name,
  open_date, close_date, account_status, product_type, risk_level,
  expected_return_rate, actual_return_rate, wealth_term, currency_type,
  purchase_amount, current_value, accumulated_income, balance,
  value_date, mature_date, dept_id, dept_name, manager_user_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(1, 'WM0001', 'WP001', '稳健理财产品A', '张三',
 '2023-01-15', NULL, 'holding', 'fixed', 'R2',
 3.500000, 3.550000, '180天', 'CNY',
 1000000.00, 1017500.00, 17500.00, 1017500.00,
 '2023-01-16', '2023-07-14', 103, '南海农商行', 1,
 '1', NOW(), '1', NOW(), b'0', 0),

(2, 'WM0002', 'WP002', '进取理财产品B', '李四',
 '2023-03-20', NULL, 'holding', 'floating', 'R3',
 4.800000, 4.900000, '365天', 'CNY',
 2000000.00, 2098000.00, 98000.00, 2098000.00,
 '2023-03-21', '2024-03-20', 103, '南海农商行', 1,
 '1', NOW(), '1', NOW(), b'0', 0),

(11, 'WM0011', 'WP003', '对公理财产品C', '北京科技有限公司',
 '2023-02-10', NULL, 'holding', 'fixed', 'R2',
 3.800000, 3.850000, '90天', 'CNY',
 10000000.00, 10095000.00, 95000.00, 10095000.00,
 '2023-02-11', '2023-05-11', 104, '南海农商行', 100,
 '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 4. 基金账户测试数据（精简版）====================

INSERT INTO crm_customer_fund_account (
  customer_id, account_no, fund_code, product_name, account_name,
  open_date, close_date, account_status, fund_type, fund_company, risk_level, currency_type,
  holding_shares, purchase_amount, current_nav, current_value, accumulated_income, balance,
  cost_price, profit_rate, dept_id, dept_name, manager_user_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(2, 'FUND0001', '000001', '华夏成长混合', '李四',
 '2022-01-10', NULL, 'holding', 'mixed', '华夏基金', 'R3', 'CNY',
 100000.000000, 120000.00, 1.2500, 125000.00, 5000.00, 125000.00,
 1.2000, 4.1700, 103, '南海农商行', 1,
 '1', NOW(), '1', NOW(), b'0', 0),

(5, 'FUND0002', '110022', '易方达消费行业', '孙七',
 '2022-05-20', NULL, 'holding', 'stock', '易方达基金', 'R4', 'CNY',
 50000.000000, 80000.00, 1.6800, 84000.00, 4000.00, 84000.00,
 1.6000, 5.0000, 103, '南海农商行', 100,
 '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 5. 保险账户测试数据（精简版）====================

INSERT INTO crm_customer_insurance_account (
  customer_id, account_no, policy_no, product_name, account_name,
  open_date, close_date, account_status, insurance_type, insurance_company,
  insurance_term, payment_term, payment_frequency,
  insured_amount, premium, paid_premium, cash_value, balance, currency_type,
  insured_name, insured_relation, beneficiary_name,
  effective_date, expire_date, next_payment_date,
  dept_id, dept_name, manager_user_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(1, 'INS0001', 'POL0001', '安心保终身寿险', '张三',
 '2021-06-01', NULL, 'valid', 'life', '中国人寿',
 '终身', '20年', 'year',
 1000000.00, 20000.00, 60000.00, 55000.00, 55000.00, 'CNY',
 '张三', '本人', '张三配偶',
 '2021-06-01', NULL, '2024-06-01',
 103, '南海农商行', 1,
 '1', NOW(), '1', NOW(), b'0', 0),

(3, 'INS0002', 'POL0002', '平安福重大疾病保险', '王五',
 '2020-08-15', NULL, 'valid', 'health', '平安保险',
 '至70岁', '20年', 'year',
 500000.00, 12000.00, 48000.00, 40000.00, 40000.00, 'CNY',
 '王五', '本人', '王五子女',
 '2020-08-15', '2050-08-15', '2024-08-15',
 103, '南海农商行', 1,
 '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 6. 信托账户测试数据（精简版）====================

INSERT INTO crm_customer_trust_account (
  customer_id, account_no, trust_contract_no, product_name, account_name,
  open_date, close_date, account_status, trust_type, trust_company, trust_purpose,
  expected_return_rate, trust_term, currency_type,
  trust_amount, paid_amount, current_value, accumulated_income, balance,
  beneficiary_name, beneficiary_relation,
  effective_date, mature_date, next_distribution_date,
  dept_id, dept_name, manager_user_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(1, 'TRUST0001', 'TC0001', '家族信托计划A', '张三',
 '2022-01-10', NULL, 'valid', 'single', '中信信托', '财富传承',
 5.500000, '10年', 'CNY',
 50000000.00, 50000000.00, 52750000.00, 2750000.00, 52750000.00,
 '张三子女', '子女',
 '2022-01-10', '2032-01-10', '2024-01-10',
 103, '南海农商行', 1,
 '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 7. 贵金属账户测试数据（精简版）====================

INSERT INTO crm_customer_metal_account (
  customer_id, account_no, product_name, account_name,
  open_date, close_date, account_status, metal_type, metal_category, product_code,
  holding_weight, average_cost, current_price, current_value, accumulated_income, profit_rate, balance,
  total_buy_weight, total_buy_amount, total_sell_weight, total_sell_amount, currency_type,
  dept_id, dept_name, manager_user_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(2, 'METAL0001', '黄金积存', '李四',
 '2022-03-15', NULL, 'normal', 'gold', 'paper', 'GOLD001',
 500.000000, 380.000000, 420.000000, 210000.00, 20000.00, 10.5300, 210000.00,
 500.000000, 190000.00, 0.000000, 0.00, 'CNY',
 103, '南海农商行', 1,
 '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 8. 信用卡账户测试数据（精简版）====================

INSERT INTO crm_customer_creditcard_account (
  customer_id, account_no, card_no, product_name, account_name,
  open_date, close_date, account_status, card_type, card_level, card_brand,
  is_main_card, main_card_no, currency_type,
  credit_limit, available_limit, temporary_limit, cash_limit, used_amount, balance,
  billing_day, payment_due_day, current_bill_amount, min_payment_amount, unpaid_amount,
  last_payment_date, last_payment_amount,
  overdue_days, overdue_amount, overdue_interest, overdue_times,
  total_points, available_points, points_expire_date,
  expire_date, activate_date, last_transaction_date,
  dept_id, dept_name, manager_user_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(1, 'CC0001', '6222************1234', '金卡信用卡', '张三',
 '2020-05-15', NULL, 'normal', 'gold', '金卡', 'unionpay',
 b'1', NULL, 'CNY',
 50000.00, 35000.00, 0.00, 10000.00, 15000.00, 15000.00,
 5, 23, 15000.00, 1500.00, 15000.00,
 '2024-12-23', 10000.00,
 0, 0.00, 0.00, 0,
 120000, 120000, '2025-12-31',
 '2025-05-31', '2020-05-20', '2024-12-28',
 103, '南海农商行', 1,
 '1', NOW(), '1', NOW(), b'0', 0),

(2, 'CC0002', '6222************5678', '白金信用卡', '李四',
 '2019-08-10', NULL, 'normal', 'platinum', '白金卡', 'visa',
 b'1', NULL, 'CNY',
 100000.00, 80000.00, 0.00, 20000.00, 20000.00, 20000.00,
 10, 28, 20000.00, 2000.00, 20000.00,
 '2024-12-28', 15000.00,
 0, 0.00, 0.00, 0,
 250000, 250000, '2025-12-31',
 '2024-08-31', '2019-08-15', '2024-12-29',
 103, '南海农商行', 1,
 '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 数据统计验证 ====================

-- 1. 账户类型统计
SELECT
  '账户类型统计' AS category,
  (SELECT COUNT(*) FROM crm_customer_deposit_account WHERE deleted = b'0') AS 存款账户数,
  (SELECT COUNT(*) FROM crm_customer_loan_account WHERE deleted = b'0') AS 贷款账户数,
  (SELECT COUNT(*) FROM crm_customer_wealth_account WHERE deleted = b'0') AS 理财账户数,
  (SELECT COUNT(*) FROM crm_customer_fund_account WHERE deleted = b'0') AS 基金账户数,
  (SELECT COUNT(*) FROM crm_customer_insurance_account WHERE deleted = b'0') AS 保险账户数,
  (SELECT COUNT(*) FROM crm_customer_trust_account WHERE deleted = b'0') AS 信托账户数,
  (SELECT COUNT(*) FROM crm_customer_metal_account WHERE deleted = b'0') AS 贵金属账户数,
  (SELECT COUNT(*) FROM crm_customer_creditcard_account WHERE deleted = b'0') AS 信用卡账户数;

-- 2. 存款账户状态统计
SELECT
  '存款账户状态统计' AS category,
  SUM(CASE WHEN account_status = 'normal' THEN 1 ELSE 0 END) AS 正常账户数,
  SUM(CASE WHEN account_status = 'closed' THEN 1 ELSE 0 END) AS 销户账户数,
  SUM(CASE WHEN account_status = 'frozen' THEN 1 ELSE 0 END) AS 冻结账户数,
  SUM(balance) AS 总余额
FROM crm_customer_deposit_account
WHERE deleted = b'0';

-- 3. 贷款账户状态统计
SELECT
  '贷款账户状态统计' AS category,
  SUM(CASE WHEN account_status = 'normal' THEN 1 ELSE 0 END) AS 正常贷款数,
  SUM(CASE WHEN account_status = 'overdue' THEN 1 ELSE 0 END) AS 逾期贷款数,
  SUM(CASE WHEN account_status = 'settled' THEN 1 ELSE 0 END) AS 已结清贷款数,
  SUM(balance) AS 贷款总余额,
  SUM(overdue_principal) AS 逾期本金总额,
  SUM(overdue_interest) AS 逾期利息总额
FROM crm_customer_loan_account
WHERE deleted = b'0';

-- 4. 客户账户分布统计（前20个客户）
SELECT
  c.id AS 客户ID,
  c.customer_name AS 客户姓名,
  c.customer_type AS 客户类型,
  (SELECT COUNT(*) FROM crm_customer_deposit_account WHERE customer_id = c.id AND deleted = b'0') AS 存款账户数,
  (SELECT COUNT(*) FROM crm_customer_loan_account WHERE customer_id = c.id AND deleted = b'0') AS 贷款账户数,
  (SELECT COUNT(*) FROM crm_customer_wealth_account WHERE customer_id = c.id AND deleted = b'0') AS 理财账户数,
  (SELECT COUNT(*) FROM crm_customer_fund_account WHERE customer_id = c.id AND deleted = b'0') AS 基金账户数,
  (SELECT COUNT(*) FROM crm_customer_insurance_account WHERE customer_id = c.id AND deleted = b'0') AS 保险账户数,
  (SELECT COUNT(*) FROM crm_customer_trust_account WHERE customer_id = c.id AND deleted = b'0') AS 信托账户数,
  (SELECT COUNT(*) FROM crm_customer_metal_account WHERE customer_id = c.id AND deleted = b'0') AS 贵金属账户数,
  (SELECT COUNT(*) FROM crm_customer_creditcard_account WHERE customer_id = c.id AND deleted = b'0') AS 信用卡账户数
FROM crm_customer c
WHERE c.deleted = b'0'
ORDER BY c.id
LIMIT 20;
