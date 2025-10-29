-- ----------------------------
-- 产品类目表和客户产品持有情况表测试数据
-- 数据说明:
-- 1. 产品类目采用三级树形结构：一级类目（8个） -> 二级类目 -> 三级产品
-- 2. 为所有现有客户（20个）创建产品持有数据
-- 3. 产品持有数据关联已创建的账户信息
-- ----------------------------

-- ==================== 产品类目测试数据（树形结构）====================

-- 一级类目：8个产品大类
INSERT INTO crm_product_category (
  id, category_code, category_name, parent_id, category_level, category_path,
  is_leaf, product_type, customer_type, status, sort_order,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(1, 'CAT001', '个人产品', 0, 1, '/1/', b'0', NULL, 'retail', 1, 1, '1', NOW(), '1', NOW(), b'0', 0),
(2, 'CAT002', '个人储蓄产品', 0, 1, '/2/', b'0', NULL, 'retail', 1, 2, '1', NOW(), '1', NOW(), b'0', 0),
(3, 'CAT003', '个人定期存款', 0, 1, '/3/', b'0', NULL, 'retail', 1, 3, '1', NOW(), '1', NOW(), b'0', 0),
(4, 'CAT004', '个人授信产品', 0, 1, '/4/', b'0', NULL, 'retail', 1, 4, '1', NOW(), '1', NOW(), b'0', 0),
(5, 'CAT005', '个人消费贷款', 0, 1, '/5/', b'0', NULL, 'retail', 1, 5, '1', NOW(), '1', NOW(), b'0', 0),
(6, 'CAT006', '个人中间业务', 0, 1, '/6/', b'0', NULL, 'retail', 1, 6, '1', NOW(), '1', NOW(), b'0', 0),
(7, 'CAT007', '理财', 0, 1, '/7/', b'0', NULL, 'all', 1, 7, '1', NOW(), '1', NOW(), b'0', 0),
(8, 'CAT008', '对公产品', 0, 1, '/8/', b'0', NULL, 'company', 1, 8, '1', NOW(), '1', NOW(), b'0', 0);

-- 二级类目：个人产品细分
INSERT INTO crm_product_category (
  id, category_code, category_name, parent_id, category_level, category_path,
  is_leaf, product_type, customer_type, status, sort_order,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 个人产品（ID=1）下的二级类目
(11, 'CAT001_01', '存款类', 1, 2, '/1/11/', b'0', 'deposit', 'retail', 1, 1, '1', NOW(), '1', NOW(), b'0', 0),
(12, 'CAT001_02', '贷款类', 1, 2, '/1/12/', b'0', 'loan', 'retail', 1, 2, '1', NOW(), '1', NOW(), b'0', 0),
(13, 'CAT001_03', '理财类', 1, 2, '/1/13/', b'0', 'wealth', 'retail', 1, 3, '1', NOW(), '1', NOW(), b'0', 0),

-- 个人储蓄产品（ID=2）下的二级类目
(21, 'CAT002_01', '活期存款', 2, 2, '/2/21/', b'0', 'deposit', 'retail', 1, 1, '1', NOW(), '1', NOW(), b'0', 0),
(22, 'CAT002_02', '定期存款', 2, 2, '/2/22/', b'0', 'deposit', 'retail', 1, 2, '1', NOW(), '1', NOW(), b'0', 0),

-- 对公产品（ID=8）下的二级类目
(81, 'CAT008_01', '对公存款', 8, 2, '/8/81/', b'0', 'deposit', 'company', 1, 1, '1', NOW(), '1', NOW(), b'0', 0),
(82, 'CAT008_02', '对公贷款', 8, 2, '/8/82/', b'0', 'loan', 'company', 1, 2, '1', NOW(), '1', NOW(), b'0', 0),
(83, 'CAT008_03', '对公理财', 8, 2, '/8/83/', b'0', 'wealth', 'company', 1, 3, '1', NOW(), '1', NOW(), b'0', 0);

-- 三级类目：实际产品（叶子节点）
INSERT INTO crm_product_category (
  id, category_code, category_name, parent_id, category_level, category_path,
  is_leaf, product_type, product_code, product_desc, currency_type,
  interest_rate_min, interest_rate_max, term_min, term_max, min_amount, max_amount, risk_level,
  customer_type, status, sort_order,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 个人存款产品（图片中的产品）
(101, 'PROD001', '产品A', 22, 3, '/2/22/101/', b'1', 'deposit', 'PROD001', '1年期定期存款产品', 'CNY',
 2.03, 2.03, 365, 365, 0.00, NULL, 'R1',
 'retail', 1, 1, '1', NOW(), '1', NOW(), b'0', 0),

(102, 'PROD002', '产品A2', 22, 3, '/2/22/102/', b'1', 'deposit', 'PROD002', '6个月定期存款产品', 'CNY',
 2.10, 2.10, 180, 180, 0.00, NULL, 'R1',
 'retail', 1, 2, '1', NOW(), '1', NOW(), b'0', 0),

(103, 'PROD003', '产品B', 22, 3, '/2/22/103/', b'1', 'deposit', 'PROD003', '6个月定期存款产品B', 'CNY',
 0.98, 0.98, 180, 180, 0.00, NULL, 'R1',
 'retail', 1, 3, '1', NOW(), '1', NOW(), b'0', 0),

(104, 'PROD004', '产品C', 22, 3, '/2/22/104/', b'1', 'deposit', 'PROD004', '3个月定期存款产品', 'CNY',
 1.25, 1.25, 90, 90, 0.00, NULL, 'R1',
 'retail', 1, 4, '1', NOW(), '1', NOW(), b'0', 0),

(105, 'PROD005', '产品D', 22, 3, '/2/22/105/', b'1', 'deposit', 'PROD005', '10年期定期存款产品', 'CNY',
 3.05, 3.05, 3650, 3650, 0.00, NULL, 'R1',
 'retail', 1, 5, '1', NOW(), '1', NOW(), b'0', 0),

(106, 'PROD006', '活期存款', 21, 3, '/2/21/106/', b'1', 'deposit', 'PROD006', '活期存款产品', 'CNY',
 0.35, 0.35, 1, NULL, 0.00, NULL, 'R1',
 'retail', 1, 1, '1', NOW(), '1', NOW(), b'0', 0),

(107, 'PROD007', '定期存款', 22, 3, '/2/22/107/', b'1', 'deposit', 'PROD007', '3年期定期存款产品', 'CNY',
 2.75, 2.75, 1095, 1095, 0.00, NULL, 'R1',
 'retail', 1, 6, '1', NOW(), '1', NOW(), b'0', 0),

-- 个人贷款产品（图片中的"优易贷"产品）
(201, 'LPROD001', '优易贷', 12, 3, '/1/12/201/', b'1', 'loan', 'LPROD001', '个人消费贷款产品', 'CNY',
 4.75, 5.50, 180, 1825, 10000.00, 1000000.00, 'R2',
 'retail', 1, 1, '1', NOW(), '1', NOW(), b'0', 0),

(202, 'LPROD002', '个人经营贷款', 12, 3, '/1/12/202/', b'1', 'loan', 'LPROD002', '小微企业主经营贷款', 'CNY',
 5.20, 6.00, 365, 1825, 50000.00, 2000000.00, 'R3',
 'retail', 1, 2, '1', NOW(), '1', NOW(), b'0', 0),

-- 个人理财产品
(301, 'WP001', '稳健理财产品A', 13, 3, '/1/13/301/', b'1', 'wealth', 'WP001', '稳健型理财产品', 'CNY',
 3.50, 4.00, 90, 365, 10000.00, NULL, 'R2',
 'retail', 1, 1, '1', NOW(), '1', NOW(), b'0', 0),

(302, 'WP002', '进取理财产品B', 13, 3, '/1/13/302/', b'1', 'wealth', 'WP002', '进取型理财产品', 'CNY',
 4.80, 5.50, 180, 730, 50000.00, NULL, 'R3',
 'retail', 1, 2, '1', NOW(), '1', NOW(), b'0', 0),

-- 对公存款产品
(401, 'PRODC001', '对公活期账户', 81, 3, '/8/81/401/', b'1', 'deposit', 'PRODC001', '对公活期存款账户', 'CNY',
 0.35, 0.35, 1, NULL, 0.00, NULL, 'R1',
 'company', 1, 1, '1', NOW(), '1', NOW(), b'0', 0),

(402, 'PRODC002', '对公定期账户', 81, 3, '/8/81/402/', b'1', 'deposit', 'PRODC002', '对公定期存款账户', 'CNY',
 2.75, 3.25, 90, 1095, 0.00, NULL, 'R1',
 'company', 1, 2, '1', NOW(), '1', NOW(), b'0', 0),

-- 对公贷款产品
(501, 'LPROD011', '流动资金贷款', 82, 3, '/8/82/501/', b'1', 'loan', 'LPROD011', '企业流动资金贷款', 'CNY',
 4.35, 5.00, 180, 730, 500000.00, 100000000.00, 'R2',
 'company', 1, 1, '1', NOW(), '1', NOW(), b'0', 0),

(502, 'LPROD012', '固定资产贷款', 82, 3, '/8/82/502/', b'1', 'loan', 'LPROD012', '企业固定资产贷款', 'CNY',
 4.65, 5.20, 365, 3650, 1000000.00, 500000000.00, 'R2',
 'company', 1, 2, '1', NOW(), '1', NOW(), b'0', 0),

(503, 'LPROD013', '项目贷款', 82, 3, '/8/82/503/', b'1', 'loan', 'LPROD013', '企业项目贷款', 'CNY',
 4.75, 5.30, 365, 3650, 2000000.00, 1000000000.00, 'R3',
 'company', 1, 3, '1', NOW(), '1', NOW(), b'0', 0),

-- 对公理财产品
(601, 'WP003', '对公理财产品C', 83, 3, '/8/83/601/', b'1', 'wealth', 'WP003', '对公稳健理财产品', 'CNY',
 3.80, 4.20, 90, 365, 100000.00, NULL, 'R2',
 'company', 1, 1, '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 客户产品持有情况测试数据 ====================

-- 基于已创建的账户数据，建立客户与产品的关联关系

-- 零售客户存款产品持有
INSERT INTO crm_customer_product_holding (
  customer_id, product_id, account_no, receipt_no, contract_no, currency_code,
  loan_date, open_date, mature_date, contract_date, branch_name, branch_id,
  product_name, holding_amount, original_amount, interest_rate, holding_status,
  related_account_type, related_account_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 客户1：张三 - 产品A（1年定期存款）
(1, 101, '2222222222222222', NULL, 'HT010430-60323001', '人民币',
 NULL, '2016-03-23', '2017-03-22', '2020-02-02', '连城支行', 103,
 '产品A', 80000000000.00, 80000000000.00, 2.03, 'holding',
 'deposit', 1,
 '1', NOW(), '1', NOW(), b'0', 0),

-- 客户2：李四 - 产品A2（6个月定期存款）
(2, 102, '2222222222222223', NULL, 'HT010431-60323002', '人民币',
 NULL, '2020-03-15', '2020-09-15', '2020-03-15', '连城支行', 103,
 '产品A2', 50000000000.00, 50000000000.00, 2.10, 'holding',
 'deposit', 2,
 '1', NOW(), '1', NOW(), b'0', 0),

-- 客户3：王五 - 产品B（6个月定期存款，已销户）
(3, 103, '3333333333333333', NULL, 'HT010430-70321001', '人民币',
 NULL, '2020-02-02', '2020-08-02', '2020-02-02', '连城支行', 103,
 '产品B', 0.00, 20000000000.00, 0.98, 'matured',
 'deposit', 3,
 '1', NOW(), '1', NOW(), b'0', 0),

-- 客户4：赵六 - 产品C（3个月定期存款）
(4, 104, '4444444444444444', NULL, 'HT010430-70321002', '人民币',
 NULL, '2020-02-02', '2020-05-02', '2020-02-02', '连城支行', 103,
 '产品C', 10000000000.00, 10000000000.00, 1.25, 'holding',
 'deposit', 4,
 '1', NOW(), '1', NOW(), b'0', 0),

-- 客户5：孙七 - 产品D（10年定期存款）
(5, 105, '5555555555555555', NULL, 'HT010430-70321003', '人民币',
 NULL, '2020-02-02', '2030-02-02', '2020-02-02', '连城支行', 103,
 '产品D', 10000000000.00, 10000000000.00, 3.05, 'holding',
 'deposit', 5,
 '1', NOW(), '1', NOW(), b'0', 0),

-- 客户6-10：活期存款产品
(6, 106, '6666666666666666', NULL, NULL, '人民币',
 NULL, '2021-05-10', NULL, '2021-05-10', '连城支行', 103,
 '活期存款', 5000000.00, 5000000.00, 0.35, 'holding',
 'deposit', 6,
 '1', NOW(), '1', NOW(), b'0', 0),

(7, 107, '7777777777777777', NULL, NULL, '人民币',
 NULL, '2021-06-20', '2024-06-20', '2021-06-20', '连城支行', 103,
 '定期存款', 30000000.00, 30000000.00, 2.75, 'holding',
 'deposit', 7,
 '1', NOW(), '1', NOW(), b'0', 0),

(8, 106, '8888888888888888', NULL, NULL, '人民币',
 NULL, '2022-01-15', NULL, '2022-01-15', '连城支行', 104,
 '活期存款', 3000000.00, 3000000.00, 0.35, 'holding',
 'deposit', 8,
 '1', NOW(), '1', NOW(), b'0', 0),

(9, 106, '9999999999999999', NULL, NULL, '人民币',
 NULL, '2022-02-20', NULL, '2022-02-20', '连城支行', 104,
 '活期存款', 2000000.00, 2000000.00, 0.35, 'holding',
 'deposit', 9,
 '1', NOW(), '1', NOW(), b'0', 0),

(10, 101, '1000000000000000', NULL, NULL, '人民币',
 NULL, '2021-08-10', '2022-08-10', '2021-08-10', '连城支行', 103,
 '定期存款', 15000000.00, 15000000.00, 2.50, 'holding',
 'deposit', 10,
 '1', NOW(), '1', NOW(), b'0', 0);

-- 零售客户贷款产品持有
INSERT INTO crm_customer_product_holding (
  customer_id, product_id, account_no, receipt_no, contract_no, currency_code,
  loan_date, open_date, mature_date, contract_date, branch_name, branch_id,
  product_name, holding_amount, original_amount, interest_rate, holding_status,
  related_account_type, related_account_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
-- 客户1：张三 - 个人消费贷款（图片中的"优易贷"）
(1, 201, 'LOAN0001', 'JJ0104320-■■■■-3001', 'CONT0001', '人民币',
 '2021-01-10', '2021-01-10', '2024-01-10', '2017-03-21', '连城支行', 103,
 '优易贷', 300000.00, 500000.00, 4.75, 'holding',
 'loan', 1,
 '1', NOW(), '1', NOW(), b'0', 0),

-- 客户4：赵六 - 个人经营贷款
(4, 202, 'LOAN0002', 'JJ0104320-■■■■-1001', 'CONT0002', '人民币',
 '2020-06-15', '2020-06-15', '2025-06-15', '2020-06-15', '连城支行', 103,
 '个人经营贷款', 800000.00, 1000000.00, 5.20, 'holding',
 'loan', 2,
 '1', NOW(), '1', NOW(), b'0', 0);

-- 零售客户理财产品持有
INSERT INTO crm_customer_product_holding (
  customer_id, product_id, account_no, receipt_no, contract_no, currency_code,
  loan_date, open_date, mature_date, contract_date, branch_name, branch_id,
  product_name, holding_amount, original_amount, interest_rate, holding_status,
  related_account_type, related_account_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(1, 301, 'WM0001', NULL, NULL, '人民币',
 NULL, '2023-01-15', '2023-07-14', '2023-01-15', '连城支行', 103,
 '稳健理财产品A', 1017500.00, 1000000.00, 3.55, 'holding',
 'wealth', 1,
 '1', NOW(), '1', NOW(), b'0', 0),

(2, 302, 'WM0002', NULL, NULL, '人民币',
 NULL, '2023-03-20', '2024-03-20', '2023-03-20', '连城支行', 103,
 '进取理财产品B', 2098000.00, 2000000.00, 4.90, 'holding',
 'wealth', 2,
 '1', NOW(), '1', NOW(), b'0', 0);

-- 对公客户存款产品持有
INSERT INTO crm_customer_product_holding (
  customer_id, product_id, account_no, receipt_no, contract_no, currency_code,
  loan_date, open_date, mature_date, contract_date, branch_name, branch_id,
  product_name, holding_amount, original_amount, interest_rate, holding_status,
  related_account_type, related_account_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(11, 401, 'C1111111111111111', NULL, NULL, '人民币',
 NULL, '2020-01-05', NULL, '2020-01-05', '连城支行', 104,
 '对公活期账户', 500000000.00, 500000000.00, 0.35, 'holding',
 'deposit', 11,
 '1', NOW(), '1', NOW(), b'0', 0),

(12, 402, 'C1111111111111112', NULL, NULL, '人民币',
 NULL, '2020-03-15', '2022-03-15', '2020-03-15', '连城支行', 104,
 '对公定期账户', 1000000000.00, 1000000000.00, 3.00, 'holding',
 'deposit', 12,
 '1', NOW(), '1', NOW(), b'0', 0),

(13, 401, 'C1111111111111113', NULL, NULL, '人民币',
 NULL, '2019-08-20', NULL, '2019-08-20', '连城支行', 104,
 '对公活期账户', 300000000.00, 300000000.00, 0.35, 'holding',
 'deposit', 13,
 '1', NOW(), '1', NOW(), b'0', 0),

(14, 402, 'C1111111111111114', NULL, NULL, '人民币',
 NULL, '2021-01-10', '2022-01-10', '2021-01-10', '连城支行', 104,
 '对公定期账户', 200000000.00, 200000000.00, 2.75, 'holding',
 'deposit', 14,
 '1', NOW(), '1', NOW(), b'0', 0),

(15, 401, 'C1111111111111115', NULL, NULL, '人民币',
 NULL, '2020-05-25', NULL, '2020-05-25', '连城支行', 104,
 '对公活期账户', 400000000.00, 400000000.00, 0.35, 'holding',
 'deposit', 15,
 '1', NOW(), '1', NOW(), b'0', 0),

(16, 401, 'C1111111111111116', NULL, NULL, '人民币',
 NULL, '2021-03-10', NULL, '2021-03-10', '连城支行', 104,
 '对公活期账户', 150000000.00, 150000000.00, 0.35, 'holding',
 'deposit', 16,
 '1', NOW(), '1', NOW(), b'0', 0),

(17, 401, 'C1111111111111117', NULL, NULL, '人民币',
 NULL, '2020-07-15', NULL, '2020-07-15', '连城支行', 104,
 '对公活期账户', 250000000.00, 250000000.00, 0.35, 'holding',
 'deposit', 17,
 '1', NOW(), '1', NOW(), b'0', 0),

(18, 402, 'C1111111111111118', NULL, NULL, '人民币',
 NULL, '2021-02-20', '2024-02-20', '2021-02-20', '连城支行', 104,
 '对公定期账户', 600000000.00, 600000000.00, 3.25, 'holding',
 'deposit', 18,
 '1', NOW(), '1', NOW(), b'0', 0),

(19, 401, 'C1111111111111119', NULL, NULL, '人民币',
 NULL, '2020-09-10', NULL, '2020-09-10', '连城支行', 104,
 '对公活期账户', 350000000.00, 350000000.00, 0.35, 'holding',
 'deposit', 19,
 '1', NOW(), '1', NOW(), b'0', 0),

(20, 401, 'C1111111111111120', NULL, NULL, '人民币',
 NULL, '2021-04-15', NULL, '2021-04-15', '连城支行', 103,
 '对公活期账户', 280000000.00, 280000000.00, 0.35, 'holding',
 'deposit', 20,
 '1', NOW(), '1', NOW(), b'0', 0);

-- 对公客户贷款产品持有
INSERT INTO crm_customer_product_holding (
  customer_id, product_id, account_no, receipt_no, contract_no, currency_code,
  loan_date, open_date, mature_date, contract_date, branch_name, branch_id,
  product_name, holding_amount, original_amount, interest_rate, holding_status,
  related_account_type, related_account_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(11, 501, 'LOAN0011', NULL, 'CONT0011', '人民币',
 '2020-03-01', '2020-03-01', '2021-03-01', '2020-03-01', '连城支行', 104,
 '流动资金贷款', 40000000.00, 50000000.00, 4.35, 'holding',
 'loan', 11,
 '1', NOW(), '1', NOW(), b'0', 0),

(12, 502, 'LOAN0012', NULL, 'CONT0012', '人民币',
 '2019-05-20', '2019-05-20', '2024-05-20', '2019-05-20', '连城支行', 104,
 '固定资产贷款', 85000000.00, 100000000.00, 4.65, 'holding',
 'loan', 12,
 '1', NOW(), '1', NOW(), b'0', 0),

(13, 501, 'LOAN0013', NULL, 'CONT0013', '人民币',
 '2020-08-15', '2020-08-15', '2022-08-15', '2020-08-15', '连城支行', 104,
 '流动资金贷款', 25000000.00, 30000000.00, 4.50, 'holding',
 'loan', 13,
 '1', NOW(), '1', NOW(), b'0', 0),

(15, 503, 'LOAN0014', NULL, 'CONT0014', '人民币',
 '2021-01-10', '2021-01-10', '2024-01-10', '2021-01-10', '连城支行', 104,
 '项目贷款', 70000000.00, 80000000.00, 4.75, 'holding',
 'loan', 15,
 '1', NOW(), '1', NOW(), b'0', 0),

(18, 501, 'LOAN0015', NULL, 'CONT0015', '人民币',
 '2021-03-20', '2021-03-20', '2022-03-20', '2021-03-20', '连城支行', 104,
 '流动资金贷款', 35000000.00, 40000000.00, 4.45, 'holding',
 'loan', 18,
 '1', NOW(), '1', NOW(), b'0', 0);

-- 对公客户理财产品持有
INSERT INTO crm_customer_product_holding (
  customer_id, product_id, account_no, receipt_no, contract_no, currency_code,
  loan_date, open_date, mature_date, contract_date, branch_name, branch_id,
  product_name, holding_amount, original_amount, interest_rate, holding_status,
  related_account_type, related_account_id,
  creator, create_time, updater, update_time, deleted, tenant_id
) VALUES
(11, 601, 'WM0011', NULL, NULL, '人民币',
 NULL, '2023-02-10', '2023-05-11', '2023-02-10', '连城支行', 104,
 '对公理财产品C', 10095000.00, 10000000.00, 3.85, 'holding',
 'wealth', 11,
 '1', NOW(), '1', NOW(), b'0', 0);

-- ==================== 数据统计验证 ====================

-- 1. 产品类目统计
SELECT
  '产品类目统计' AS category,
  COUNT(*) AS 总类目数,
  SUM(CASE WHEN category_level = 1 THEN 1 ELSE 0 END) AS 一级类目数,
  SUM(CASE WHEN category_level = 2 THEN 1 ELSE 0 END) AS 二级类目数,
  SUM(CASE WHEN category_level = 3 THEN 1 ELSE 0 END) AS 三级类目数,
  SUM(CASE WHEN is_leaf = b'1' THEN 1 ELSE 0 END) AS 实际产品数,
  SUM(CASE WHEN is_leaf = b'0' THEN 1 ELSE 0 END) AS 分类目录数
FROM crm_product_category
WHERE deleted = b'0';

-- 2. 产品类型分布
SELECT
  '产品类型分布' AS category,
  SUM(CASE WHEN product_type = 'deposit' THEN 1 ELSE 0 END) AS 存款产品数,
  SUM(CASE WHEN product_type = 'loan' THEN 1 ELSE 0 END) AS 贷款产品数,
  SUM(CASE WHEN product_type = 'wealth' THEN 1 ELSE 0 END) AS 理财产品数,
  SUM(CASE WHEN product_type = 'fund' THEN 1 ELSE 0 END) AS 基金产品数,
  SUM(CASE WHEN product_type = 'insurance' THEN 1 ELSE 0 END) AS 保险产品数,
  SUM(CASE WHEN product_type = 'trust' THEN 1 ELSE 0 END) AS 信托产品数,
  SUM(CASE WHEN product_type = 'metal' THEN 1 ELSE 0 END) AS 贵金属产品数,
  SUM(CASE WHEN product_type = 'creditcard' THEN 1 ELSE 0 END) AS 信用卡产品数
FROM crm_product_category
WHERE deleted = b'0' AND is_leaf = b'1';

-- 3. 客户产品持有统计
SELECT
  '客户产品持有统计' AS category,
  COUNT(*) AS 总持有记录数,
  COUNT(DISTINCT customer_id) AS 持有产品的客户数,
  COUNT(DISTINCT product_id) AS 被持有的产品数,
  SUM(CASE WHEN holding_status = 'holding' THEN 1 ELSE 0 END) AS 持有中记录数,
  SUM(CASE WHEN holding_status = 'matured' THEN 1 ELSE 0 END) AS 已到期记录数,
  SUM(CASE WHEN holding_status = 'settled' THEN 1 ELSE 0 END) AS 已结清记录数
FROM crm_customer_product_holding
WHERE deleted = b'0';

-- 4. 客户产品持有明细（前20个客户）
SELECT
  c.id AS 客户ID,
  c.customer_name AS 客户姓名,
  c.customer_type AS 客户类型,
  COUNT(DISTINCT ph.product_id) AS 持有产品数,
  SUM(CASE WHEN pc.product_type = 'deposit' THEN 1 ELSE 0 END) AS 存款产品数,
  SUM(CASE WHEN pc.product_type = 'loan' THEN 1 ELSE 0 END) AS 贷款产品数,
  SUM(CASE WHEN pc.product_type = 'wealth' THEN 1 ELSE 0 END) AS 理财产品数,
  GROUP_CONCAT(DISTINCT pc.category_name ORDER BY pc.id SEPARATOR ' | ') AS 持有产品列表
FROM crm_customer c
LEFT JOIN crm_customer_product_holding ph ON c.id = ph.customer_id AND ph.deleted = b'0'
LEFT JOIN crm_product_category pc ON ph.product_id = pc.id AND pc.deleted = b'0'
WHERE c.deleted = b'0'
GROUP BY c.id, c.customer_name, c.customer_type
ORDER BY c.id
LIMIT 20;

-- 5. 产品持有金额统计（按产品类型）
SELECT
  '产品持有金额统计' AS category,
  pc.product_type AS 产品类型,
  COUNT(*) AS 持有记录数,
  SUM(ph.holding_amount) AS 持有总金额,
  AVG(ph.holding_amount) AS 平均持有金额
FROM crm_customer_product_holding ph
JOIN crm_product_category pc ON ph.product_id = pc.id
WHERE ph.deleted = b'0' AND ph.holding_status = 'holding'
GROUP BY pc.product_type
ORDER BY SUM(ph.holding_amount) DESC;
