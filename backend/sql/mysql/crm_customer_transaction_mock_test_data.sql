-- ----------------------------
-- 客户交易明细信息测试数据（Mock数据）
-- 说明:
-- 1. 为所有20个客户（10个零售客户 + 10个对公客户）创建交易明细记录
-- 2. 每个客户创建3-5条交易记录
-- 3. 涵盖多种交易类型、渠道、现转标志等场景
-- 4. 交易金额和余额符合业务逻辑
-- ----------------------------

-- ==================== 零售客户交易明细 ====================

-- 客户1: 张三 (customer_id=1) - 5条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `sub_account_no`, `account_no`, `currency_code`,
  `tans_no`, `trad_type`, `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`,
  `trad_teller`, `handler`, `advs_acct`, `advs_acct_name`, `contact_type`, `curr_tran_flag`,
  `loan_flag`, `cost`, `accountin_date`, `transaction_status`, `direction`, `remark`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
-- 转账转入
(1, 1, '2025-10-25', '09:15:23', '100001', '西湖支行', '5200', 'transfer', NULL, '6217001234567890123', 'CNY',
 'TXN202510250001', 'transfer', 50000.00, 250000.00, '工资转入', '1', 'online', 'SYS001', '张经理',
 '6217009876543210987', '杭州某科技公司', '工资', NULL, '0', 0.00, '2025-10-25', 'success', 'in', '每月工资',
 '1', NOW(), '1', NOW(), b'0', 1),

-- ATM取现
(1, 1, '2025-10-26', '14:30:12', '100001', '西湖支行', '1001', 'cash', NULL, '6217001234567890123', 'CNY',
 'TXN202510260023', 'withdraw', 5000.00, 245000.00, 'ATM取现', '1', 'atm', 'ATM001', NULL,
 NULL, NULL, NULL, '现钞', '0', 5.00, '2025-10-26', 'success', 'out', 'ATM取现手续费5元',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 手机银行消费
(1, 1, '2025-10-27', '20:45:33', '100001', '西湖支行', '8001', 'transfer', NULL, '6217001234567890123', 'CNY',
 'TXN202510270156', 'consume', 1288.00, 243712.00, '网上购物', '1', 'mobile', 'SYS002', NULL,
 '6228480402564890018', '京东商城', '消费', NULL, '0', 0.00, '2025-10-27', 'success', 'out', '手机银行支付',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 利息结算
(1, 1, '2025-10-28', '00:01:00', '100001', '西湖支行', '9001', 'transfer', NULL, '6217001234567890123', 'CNY',
 'TXN202510280001', 'interest', 125.50, 243837.50, '活期利息', '1', 'counter', 'SYS000', NULL,
 NULL, NULL, NULL, NULL, '0', 0.00, '2025-10-28', 'success', 'in', '季度利息结算',
 '1', NOW(), '1', NOW(), b'0', 1),

-- 转账转出
(1, 1, '2025-10-29', '16:20:45', '100001', '西湖支行', '5200', 'transfer', NULL, '6217001234567890123', 'CNY',
 'TXN202510290089', 'transfer', 20000.00, 223837.50, '转账给家人', '1', 'mobile', 'SYS002', '张三',
 '6217001111222233344', '李四', '亲属', NULL, '0', 2.00, '2025-10-29', 'success', 'out', '手续费2元',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户2: 李四 (customer_id=2) - 4条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `trad_teller`, `transaction_status`,
  `direction`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(2, 2, '2025-10-24', '10:30:00', '100002', '滨江支行', '1002', 'cash', '6217001234567890124', 'CNY',
 'TXN202510240045', 'deposit', 100000.00, 280000.00, '柜面存款', '1', 'counter', 'T001', 'success', 'in',
 '1', NOW(), '1', NOW(), b'0', 1),

(2, 2, '2025-10-26', '15:20:30', '100002', '滨江支行', '8002', 'transfer', '6217001234567890124', 'CNY',
 'TXN202510260078', 'consume', 3500.00, 276500.00, '超市消费', '1', 'pos', 'POS123', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1),

(2, 2, '2025-10-28', '11:45:20', '100002', '滨江支行', '5201', 'transfer', '6217001234567890124', 'CNY',
 'TXN202510280123', 'transfer', 50000.00, 226500.00, '理财转出', '1', 'online', 'SYS003', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1),

(2, 2, '2025-10-29', '09:10:00', '100002', '滨江支行', '1003', 'cash', '6217001234567890124', 'CNY',
 'TXN202510290012', 'withdraw', 10000.00, 216500.00, '柜面取现', '1', 'counter', 'T002', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户3: 王五 (customer_id=3) - 3条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(3, 3, '2025-10-25', '14:20:00', '100003', '萧山支行', '5202', 'transfer', '6217001234567890125', 'CNY',
 'TXN202510250067', 'deposit', 30000.00, 150000.00, '转账存入', '1', 'mobile', 'success', 'in',
 '1', NOW(), '1', NOW(), b'0', 1),

(3, 3, '2025-10-27', '16:30:45', '100003', '萧山支行', '8003', 'transfer', '6217001234567890125', 'CNY',
 'TXN202510270189', 'consume', 899.00, 149101.00, '网上购物', '1', 'wechat', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1),

(3, 3, '2025-10-29', '10:15:30', '100003', '萧山支行', '1004', 'cash', '6217001234567890125', 'CNY',
 'TXN202510290034', 'withdraw', 20000.00, 129101.00, 'ATM取现', '1', 'atm', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户4: 赵六 (customer_id=4) - 5条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `advs_acct`, `advs_acct_name`, `cost`, `accountin_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(4, 4, '2025-10-23', '09:00:00', '100004', '余杭支行', '1005', 'cash', '6217001234567890126', 'CNY',
 'TXN202510230001', 'deposit', 200000.00, 500000.00, '大额存款', '1', 'counter', 'success', 'in',
 NULL, NULL, 0.00, '2025-10-23',
 '1', NOW(), '1', NOW(), b'0', 1),

(4, 4, '2025-10-24', '11:20:00', '100004', '余杭支行', '7001', 'transfer', '6217001234567890126', 'CNY',
 'TXN202510240056', 'repayment', 15000.00, 485000.00, '信用卡还款', '1', 'mobile', 'success', 'out',
 '6228480402564890019', '信用卡中心', 0.00, '2025-10-24',
 '1', NOW(), '1', NOW(), b'0', 1),

(4, 4, '2025-10-26', '14:30:00', '100004', '余杭支行', '8004', 'transfer', '6217001234567890126', 'CNY',
 'TXN202510260123', 'consume', 2500.00, 482500.00, '餐饮消费', '1', 'alipay', 'success', 'out',
 NULL, NULL, 0.00, '2025-10-26',
 '1', NOW(), '1', NOW(), b'0', 1),

(4, 4, '2025-10-28', '00:01:00', '100004', '余杭支行', '9002', 'transfer', '6217001234567890126', 'CNY',
 'TXN202510280002', 'interest', 245.80, 482745.80, '定期利息', '1', 'counter', 'success', 'in',
 NULL, NULL, 0.00, '2025-10-28',
 '1', NOW(), '1', NOW(), b'0', 1),

(4, 4, '2025-10-29', '15:45:00', '100004', '余杭支行', '5203', 'transfer', '6217001234567890126', 'CNY',
 'TXN202510290167', 'transfer', 100000.00, 382745.80, '转账购买理财', '1', 'online', 'success', 'out',
 '6228480402564890020', '理财账户', 10.00, '2025-10-29',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户5: 孙七 (customer_id=5) - 4条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(5, 5, '2025-10-24', '08:30:00', '100005', '拱墅支行', '5204', 'transfer', '6217001234567890127', 'CNY',
 'TXN202510240023', 'deposit', 80000.00, 180000.00, '工资存入', '1', 'online', 'success', 'in',
 '1', NOW(), '1', NOW(), b'0', 1),

(5, 5, '2025-10-26', '12:15:00', '100005', '拱墅支行', '1006', 'cash', '6217001234567890127', 'CNY',
 'TXN202510260089', 'withdraw', 5000.00, 175000.00, '柜面取现', '1', 'counter', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1),

(5, 5, '2025-10-27', '18:20:00', '100005', '拱墅支行', '8005', 'transfer', '6217001234567890127', 'CNY',
 'TXN202510270234', 'consume', 1580.00, 173420.00, '加油站消费', '1', 'pos', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1),

(5, 5, '2025-10-29', '09:30:00', '100005', '拱墅支行', '5205', 'transfer', '6217001234567890127', 'CNY',
 'TXN202510290045', 'transfer', 30000.00, 143420.00, '转账支付', '1', 'mobile', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户6: 周八 (customer_id=6) - 3条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(6, 6, '2025-10-25', '10:00:00', '100006', '钱塘支行', '1007', 'cash', '6217001234567890128', 'CNY',
 'TXN202510250034', 'deposit', 50000.00, 120000.00, '现金存款', '1', 'counter', 'success', 'in',
 '1', NOW(), '1', NOW(), b'0', 1),

(6, 6, '2025-10-27', '14:45:00', '100006', '钱塘支行', '8006', 'transfer', '6217001234567890128', 'CNY',
 'TXN202510270178', 'consume', 699.00, 119301.00, '电商消费', '1', 'wechat', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1),

(6, 6, '2025-10-29', '16:00:00', '100006', '钱塘支行', '1008', 'cash', '6217001234567890128', 'CNY',
 'TXN202510290189', 'withdraw', 10000.00, 109301.00, 'ATM取现', '1', 'atm', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户7: 吴九 (customer_id=7) - 5条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `cost`, `accountin_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(7, 7, '2025-10-23', '11:00:00', '100007', '临平支行', '5206', 'transfer', '6217001234567890129', 'CNY',
 'TXN202510230078', 'deposit', 150000.00, 450000.00, '转账存入', '1', 'online', 'success', 'in',
 0.00, '2025-10-23',
 '1', NOW(), '1', NOW(), b'0', 1),

(7, 7, '2025-10-25', '09:30:00', '100007', '临平支行', '7002', 'transfer', '6217001234567890129', 'CNY',
 'TXN202510250023', 'repayment', 8000.00, 442000.00, '贷款还款', '1', 'mobile', 'success', 'out',
 0.00, '2025-10-25',
 '1', NOW(), '1', NOW(), b'0', 1),

(7, 7, '2025-10-27', '13:20:00', '100007', '临平支行', '8007', 'transfer', '6217001234567890129', 'CNY',
 'TXN202510270134', 'consume', 4200.00, 437800.00, '家电购买', '1', 'alipay', 'success', 'out',
 0.00, '2025-10-27',
 '1', NOW(), '1', NOW(), b'0', 1),

(7, 7, '2025-10-28', '00:01:00', '100007', '临平支行', '9003', 'transfer', '6217001234567890129', 'CNY',
 'TXN202510280003', 'interest', 189.50, 437989.50, '活期利息', '1', 'counter', 'success', 'in',
 0.00, '2025-10-28',
 '1', NOW(), '1', NOW(), b'0', 1),

(7, 7, '2025-10-29', '15:00:00', '100007', '临平支行', '1009', 'cash', '6217001234567890129', 'CNY',
 'TXN202510290156', 'withdraw', 20000.00, 417989.50, '柜面取现', '1', 'counter', 'success', 'out',
 10.00, '2025-10-29',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户8: 郑十 (customer_id=8) - 4条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(8, 8, '2025-10-24', '10:45:00', '100008', '上城支行', '1010', 'cash', '6217001234567890130', 'CNY',
 'TXN202510240067', 'deposit', 60000.00, 160000.00, '现金存款', '1', 'counter', 'success', 'in',
 '1', NOW(), '1', NOW(), b'0', 1),

(8, 8, '2025-10-26', '16:30:00', '100008', '上城支行', '8008', 'transfer', '6217001234567890130', 'CNY',
 'TXN202510260198', 'consume', 1200.00, 158800.00, '餐饮消费', '1', 'pos', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1),

(8, 8, '2025-10-28', '11:00:00', '100008', '上城支行', '5207', 'transfer', '6217001234567890130', 'CNY',
 'TXN202510280134', 'transfer', 50000.00, 108800.00, '转账支付', '1', 'online', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1),

(8, 8, '2025-10-29', '14:20:00', '100008', '上城支行', '1011', 'cash', '6217001234567890130', 'CNY',
 'TXN202510290145', 'withdraw', 8000.00, 100800.00, 'ATM取现', '1', 'atm', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户9: 陈十一 (customer_id=9) - 3条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(9, 9, '2025-10-25', '09:15:00', '100009', '下城支行', '5208', 'transfer', '6217001234567890131', 'CNY',
 'TXN202510250045', 'deposit', 40000.00, 90000.00, '转账存入', '1', 'mobile', 'success', 'in',
 '1', NOW(), '1', NOW(), b'0', 1),

(9, 9, '2025-10-27', '15:30:00', '100009', '下城支行', '8009', 'transfer', '6217001234567890131', 'CNY',
 'TXN202510270189', 'consume', 580.00, 89420.00, '网购消费', '1', 'wechat', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1),

(9, 9, '2025-10-29', '10:45:00', '100009', '下城支行', '1012', 'cash', '6217001234567890131', 'CNY',
 'TXN202510290078', 'withdraw', 5000.00, 84420.00, '柜面取现', '1', 'counter', 'success', 'out',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户10: 黄十二 (customer_id=10) - 5条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `cost`, `accountin_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(10, 10, '2025-10-23', '08:00:00', '100010', '江干支行', '5209', 'transfer', '6217001234567890132', 'CNY',
 'TXN202510230012', 'deposit', 120000.00, 620000.00, '大额转入', '1', 'online', 'success', 'in',
 0.00, '2025-10-23',
 '1', NOW(), '1', NOW(), b'0', 1),

(10, 10, '2025-10-25', '10:30:00', '100010', '江干支行', '7003', 'transfer', '6217001234567890132', 'CNY',
 'TXN202510250067', 'repayment', 12000.00, 608000.00, '车贷还款', '1', 'mobile', 'success', 'out',
 0.00, '2025-10-25',
 '1', NOW(), '1', NOW(), b'0', 1),

(10, 10, '2025-10-27', '14:00:00', '100010', '江干支行', '8010', 'transfer', '6217001234567890132', 'CNY',
 'TXN202510270145', 'consume', 5800.00, 602200.00, '百货消费', '1', 'alipay', 'success', 'out',
 0.00, '2025-10-27',
 '1', NOW(), '1', NOW(), b'0', 1),

(10, 10, '2025-10-28', '00:01:00', '100010', '江干支行', '9004', 'transfer', '6217001234567890132', 'CNY',
 'TXN202510280004', 'interest', 312.50, 602512.50, '定期利息', '1', 'counter', 'success', 'in',
 0.00, '2025-10-28',
 '1', NOW(), '1', NOW(), b'0', 1),

(10, 10, '2025-10-29', '16:30:00', '100010', '江干支行', '1013', 'cash', '6217001234567890132', 'CNY',
 'TXN202510290201', 'withdraw', 30000.00, 572512.50, 'ATM取现', '1', 'atm', 'success', 'out',
 15.00, '2025-10-29',
 '1', NOW(), '1', NOW(), b'0', 1);

-- ==================== 对公客户交易明细 ====================

-- 客户11: 杭州科技有限公司 (customer_id=11) - 4条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `advs_acct`, `advs_acct_name`, `cost`, `accountin_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(11, 11, '2025-10-23', '09:30:00', '100001', '西湖支行', '5300', 'transfer', '331001234567890001', 'CNY',
 'TXN202510230089', 'deposit', 5000000.00, 15000000.00, '货款回笼', '1', 'online', 'success', 'in',
 '331009876543210001', '某客户公司', 50.00, '2025-10-23',
 '1', NOW(), '1', NOW(), b'0', 1),

(11, 11, '2025-10-25', '11:00:00', '100001', '西湖支行', '5301', 'transfer', '331001234567890001', 'CNY',
 'TXN202510250078', 'transfer', 800000.00, 14200000.00, '支付供应商货款', '1', 'online', 'success', 'out',
 '331001111222233345', '供应商A公司', 80.00, '2025-10-25',
 '1', NOW(), '1', NOW(), b'0', 1),

(11, 11, '2025-10-27', '14:30:00', '100001', '西湖支行', '7101', 'transfer', '331001234567890001', 'CNY',
 'TXN202510270156', 'repayment', 500000.00, 13700000.00, '流动资金贷款还款', '1', 'online', 'success', 'out',
 NULL, NULL, 0.00, '2025-10-27',
 '1', NOW(), '1', NOW(), b'0', 1),

(11, 11, '2025-10-29', '10:00:00', '100001', '西湖支行', '9101', 'transfer', '331001234567890001', 'CNY',
 'TXN202510290067', 'interest', 8500.00, 13708500.00, '企业账户利息', '1', 'counter', 'success', 'in',
 NULL, NULL, 0.00, '2025-10-29',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户12: 浙江贸易股份有限公司 (customer_id=12) - 5条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `advs_acct`, `advs_acct_name`, `cost`, `accountin_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(12, 12, '2025-10-23', '10:15:00', '100002', '滨江支行', '5302', 'transfer', '331001234567890002', 'CNY',
 'TXN202510230123', 'deposit', 8000000.00, 28000000.00, '出口货款到账', '1', 'online', 'success', 'in',
 '331009876543210002', '境外客户', 200.00, '2025-10-23',
 '1', NOW(), '1', NOW(), b'0', 1),

(12, 12, '2025-10-24', '15:30:00', '100002', '滨江支行', '5303', 'transfer', '331001234567890002', 'CNY',
 'TXN202510240189', 'transfer', 1200000.00, 26800000.00, '采购原材料', '1', 'online', 'success', 'out',
 '331002222333344456', '供应商B公司', 120.00, '2025-10-24',
 '1', NOW(), '1', NOW(), b'0', 1),

(12, 12, '2025-10-26', '09:45:00', '100002', '滨江支行', '7102', 'transfer', '331001234567890002', 'CNY',
 'TXN202510260056', 'repayment', 1000000.00, 25800000.00, '票据贴现还款', '1', 'counter', 'success', 'out',
 NULL, NULL, 0.00, '2025-10-26',
 '1', NOW(), '1', NOW(), b'0', 1),

(12, 12, '2025-10-28', '13:20:00', '100002', '滨江支行', '8201', 'transfer', '331001234567890002', 'CNY',
 'TXN202510280167', 'fee', 5000.00, 25795000.00, '账户管理费', '1', 'counter', 'success', 'out',
 NULL, NULL, 0.00, '2025-10-28',
 '1', NOW(), '1', NOW(), b'0', 1),

(12, 12, '2025-10-29', '11:00:00', '100002', '滨江支行', '9102', 'transfer', '331001234567890002', 'CNY',
 'TXN202510290089', 'interest', 12800.00, 25807800.00, '对公活期利息', '1', 'counter', 'success', 'in',
 NULL, NULL, 0.00, '2025-10-29',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户13: 宁波制造业集团 (customer_id=13) - 3条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `cost`, `accountin_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(13, 13, '2025-10-24', '09:00:00', '100003', '萧山支行', '5304', 'transfer', '331001234567890003', 'CNY',
 'TXN202510240023', 'deposit', 12000000.00, 52000000.00, '销售回款', '1', 'online', 'success', 'in',
 300.00, '2025-10-24',
 '1', NOW(), '1', NOW(), b'0', 1),

(13, 13, '2025-10-26', '14:00:00', '100003', '萧山支行', '5305', 'transfer', '331001234567890003', 'CNY',
 'TXN202510260145', 'transfer', 3000000.00, 49000000.00, '设备采购款', '1', 'online', 'success', 'out',
 300.00, '2025-10-26',
 '1', NOW(), '1', NOW(), b'0', 1),

(13, 13, '2025-10-29', '10:30:00', '100003', '萧山支行', '7103', 'transfer', '331001234567890003', 'CNY',
 'TXN202510290078', 'repayment', 2000000.00, 47000000.00, '固定资产贷款还款', '1', 'counter', 'success', 'out',
 0.00, '2025-10-29',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户14: 温州建设工程公司 (customer_id=14) - 4条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `advs_acct`, `advs_acct_name`, `cost`, `accountin_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(14, 14, '2025-10-23', '11:30:00', '100004', '余杭支行', '5306', 'transfer', '331001234567890004', 'CNY',
 'TXN202510230156', 'deposit', 18000000.00, 38000000.00, '工程款到账', '1', 'online', 'success', 'in',
 '331009876543210004', '业主方', 450.00, '2025-10-23',
 '1', NOW(), '1', NOW(), b'0', 1),

(14, 14, '2025-10-25', '13:45:00', '100004', '余杭支行', '5307', 'transfer', '331001234567890004', 'CNY',
 'TXN202510250134', 'transfer', 5000000.00, 33000000.00, '支付分包款', '1', 'online', 'success', 'out',
 '331003333444455567', '分包商C公司', 250.00, '2025-10-25',
 '1', NOW(), '1', NOW(), b'0', 1),

(14, 14, '2025-10-27', '10:00:00', '100004', '余杭支行', '5308', 'transfer', '331001234567890004', 'CNY',
 'TXN202510270067', 'transfer', 2000000.00, 31000000.00, '材料采购', '1', 'online', 'success', 'out',
 '331004444555566678', '建材公司', 100.00, '2025-10-27',
 '1', NOW(), '1', NOW(), b'0', 1),

(14, 14, '2025-10-29', '15:20:00', '100004', '余杭支行', '7104', 'transfer', '331001234567890004', 'CNY',
 'TXN202510290178', 'repayment', 3000000.00, 28000000.00, '项目贷款还款', '1', 'counter', 'success', 'out',
 NULL, NULL, 0.00, '2025-10-29',
 '1', NOW(), '1', NOW(), b'0', 1);

-- 客户15: 绍兴纺织实业公司 (customer_id=15) - 5条记录
INSERT INTO `crm_customer_transaction_mock` (
  `customer_id`, `account_id`, `transaction_date`, `transaction_time`, `branch_no`, `branch_name`,
  `original_tran_code`, `cash_flag`, `account_no`, `currency_code`, `tans_no`, `trad_type`,
  `trad_money`, `acct_bal`, `trad_abs`, `review`, `trad_chn`, `transaction_status`, `direction`,
  `advs_acct`, `advs_acct_name`, `cost`, `accountin_date`,
  `creator`, `create_time`, `updater`, `update_time`, `deleted`, `tenant_id`
) VALUES
(15, 15, '2025-10-23', '08:30:00', '100005', '拱墅支行', '5309', 'transfer', '331001234567890005', 'CNY',
 'TXN202510230045', 'deposit', 6000000.00, 26000000.00, '出口退税', '1', 'online', 'success', 'in',
 NULL, NULL, 0.00, '2025-10-23',
 '1', NOW(), '1', NOW(), b'0', 1),

(15, 15, '2025-10-24', '12:00:00', '100005', '拱墅支行', '5310', 'transfer', '331001234567890005', 'CNY',
 'TXN202510240134', 'transfer', 800000.00, 25200000.00, '支付人工工资', '1', 'online', 'success', 'out',
 NULL, NULL, 10.00, '2025-10-24',
 '1', NOW(), '1', NOW(), b'0', 1),

(15, 15, '2025-10-26', '10:30:00', '100005', '拱墅支行', '5311', 'transfer', '331001234567890005', 'CNY',
 'TXN202510260078', 'transfer', 1500000.00, 23700000.00, '原材料采购', '1', 'online', 'success', 'out',
 '331005555666677789', '棉花公司', 75.00, '2025-10-26',
 '1', NOW(), '1', NOW(), b'0', 1),

(15, 15, '2025-10-28', '14:45:00', '100005', '拱墅支行', '7105', 'transfer', '331001234567890005', 'CNY',
 'TXN202510280178', 'repayment', 1000000.00, 22700000.00, '流动资金贷款还款', '1', 'counter', 'success', 'out',
 NULL, NULL, 0.00, '2025-10-28',
 '1', NOW(), '1', NOW(), b'0', 1),

(15, 15, '2025-10-29', '09:15:00', '100005', '拱墅支行', '9103', 'transfer', '331001234567890005', 'CNY',
 'TXN202510290023', 'interest', 11200.00, 22711200.00, '对公账户利息', '1', 'counter', 'success', 'in',
 NULL, NULL, 0.00, '2025-10-29',
 '1', NOW(), '1', NOW(), b'0', 1);
