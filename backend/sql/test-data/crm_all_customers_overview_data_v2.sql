-- =====================================================
-- 为所有客户生成完整的客户概览数据 V2
-- 包括: 资产快照、交易流水、客户标签
-- 生成时间: 2025-10-31
-- =====================================================

USE `ruoyi-vue-pro`;

-- =====================================================
-- 1. 资产快照数据 (12个月历史数据)
-- =====================================================

-- 先清除客户2-20的资产快照数据
DELETE FROM crm_customer_asset_snapshot WHERE customer_id BETWEEN 2 AND 20;

-- 为客户2-10 (零售客户) 生成资产快照 - 基于客户1的数据
-- 客户2: 李四 (50万-80万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
 total_liabilities, loan_balance, creditcard_balance, net_assets,
 total_assets_growth, deposit_growth, wealth_growth,
 deposit_account_count, wealth_account_count, fund_account_count,
 loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    2 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 0.52, 2), ROUND(deposit_balance * 0.52, 2), ROUND(wealth_balance * 0.52, 2),
    ROUND(fund_balance * 0.52, 2), ROUND(insurance_balance * 0.52, 2),
    metal_balance, trust_balance, other_balance,
    ROUND(total_liabilities * 0.52, 2), ROUND(loan_balance * 0.52, 2), ROUND(creditcard_balance * 0.52, 2),
    ROUND(net_assets * 0.52, 2),
    total_assets_growth, deposit_growth, wealth_growth,
    1, 1, 1, 1, 1, insurance_count,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 客户3: 王五 (200万-280万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
 total_liabilities, loan_balance, creditcard_balance, net_assets,
 total_assets_growth, deposit_growth, wealth_growth,
 deposit_account_count, wealth_account_count, fund_account_count,
 loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    3 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 1.96, 2), ROUND(deposit_balance * 1.96, 2), ROUND(wealth_balance * 1.96, 2),
    ROUND(fund_balance * 1.96, 2), ROUND(insurance_balance * 1.96, 2),
    metal_balance, trust_balance, other_balance,
    ROUND(total_liabilities * 1.96, 2), ROUND(loan_balance * 1.96, 2), ROUND(creditcard_balance * 1.96, 2),
    ROUND(net_assets * 1.96, 2),
    total_assets_growth, deposit_growth, wealth_growth,
    3, 2, 1, 1, 1, insurance_count,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 客户4: 赵六 (80万-120万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
 total_liabilities, loan_balance, creditcard_balance, net_assets,
 total_assets_growth, deposit_growth, wealth_growth,
 deposit_account_count, wealth_account_count, fund_account_count,
 loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    4, snapshot_date, snapshot_type,
    ROUND(total_assets * 0.7, 2), ROUND(deposit_balance * 0.7, 2), ROUND(wealth_balance * 0.7, 2),
    ROUND(fund_balance * 0.7, 2), ROUND(insurance_balance * 0.7, 2),
    metal_balance, trust_balance, other_balance,
    ROUND(total_liabilities * 0.7, 2), ROUND(loan_balance * 0.7, 2), ROUND(creditcard_balance * 0.7, 2),
    ROUND(net_assets * 0.7, 2),
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 客户5-10 (使用不同倍数)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
 total_liabilities, loan_balance, creditcard_balance, net_assets,
 total_assets_growth, deposit_growth, wealth_growth,
 deposit_account_count, wealth_account_count, fund_account_count,
 loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
       fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
       total_liabilities, loan_balance, creditcard_balance, net_assets,
       total_assets_growth, deposit_growth, wealth_growth,
       deposit_account_count, wealth_account_count, fund_account_count,
       loan_account_count, creditcard_count, insurance_count,
       'system', NOW(), 'system', NOW(), 0, 1
FROM (
    SELECT 5, snapshot_date, snapshot_type,
           ROUND(total_assets * 1.3, 2), ROUND(deposit_balance * 1.3, 2), ROUND(wealth_balance * 1.3, 2),
           ROUND(fund_balance * 1.3, 2), ROUND(insurance_balance * 1.3, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 1.3, 2), ROUND(loan_balance * 1.3, 2), ROUND(creditcard_balance * 1.3, 2),
           ROUND(net_assets * 1.3, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 6, snapshot_date, snapshot_type,
           ROUND(total_assets * 0.35, 2), ROUND(deposit_balance * 0.35, 2), ROUND(wealth_balance * 0.35, 2),
           ROUND(fund_balance * 0.35, 2), ROUND(insurance_balance * 0.35, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 0.35, 2), ROUND(loan_balance * 0.35, 2), ROUND(creditcard_balance * 0.35, 2),
           ROUND(net_assets * 0.35, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           1, 1, 0, 1, 1, 0
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 7, snapshot_date, snapshot_type,
           ROUND(total_assets * 2.2, 2), ROUND(deposit_balance * 2.2, 2), ROUND(wealth_balance * 2.2, 2),
           ROUND(fund_balance * 2.2, 2), ROUND(insurance_balance * 2.2, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 2.2, 2), ROUND(loan_balance * 2.2, 2), ROUND(creditcard_balance * 2.2, 2),
           ROUND(net_assets * 2.2, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           3, 2, 2, 1, 2, insurance_count
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 8, snapshot_date, snapshot_type,
           ROUND(total_assets * 0.6, 2), ROUND(deposit_balance * 0.6, 2), ROUND(wealth_balance * 0.6, 2),
           ROUND(fund_balance * 0.6, 2), ROUND(insurance_balance * 0.6, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 0.6, 2), ROUND(loan_balance * 0.6, 2), ROUND(creditcard_balance * 0.6, 2),
           ROUND(net_assets * 0.6, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 9, snapshot_date, snapshot_type,
           ROUND(total_assets * 1.15, 2), ROUND(deposit_balance * 1.15, 2), ROUND(wealth_balance * 1.15, 2),
           ROUND(fund_balance * 1.15, 2), ROUND(insurance_balance * 1.15, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 1.15, 2), ROUND(loan_balance * 1.15, 2), ROUND(creditcard_balance * 1.15, 2),
           ROUND(net_assets * 1.15, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           2, 2, 1, 1, 1, insurance_count
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 10, snapshot_date, snapshot_type,
           ROUND(total_assets * 0.42, 2), ROUND(deposit_balance * 0.42, 2), ROUND(wealth_balance * 0.42, 2),
           ROUND(fund_balance * 0.42, 2), ROUND(insurance_balance * 0.42, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 0.42, 2), ROUND(loan_balance * 0.42, 2), ROUND(creditcard_balance * 0.42, 2),
           ROUND(net_assets * 0.42, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           1, 1, 1, 0, 1, insurance_count
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
) t;

-- 对公客户 (11-20) 的资产快照数据 - 规模更大
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
 total_liabilities, loan_balance, creditcard_balance, net_assets,
 total_assets_growth, deposit_growth, wealth_growth,
 deposit_account_count, wealth_account_count, fund_account_count,
 loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
       fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
       total_liabilities, loan_balance, creditcard_balance, net_assets,
       total_assets_growth, deposit_growth, wealth_growth,
       deposit_account_count, wealth_account_count, fund_account_count,
       loan_account_count, creditcard_count, insurance_count,
       'system', NOW(), 'system', NOW(), 0, 1
FROM (
    SELECT 11, snapshot_date, snapshot_type,
           ROUND(total_assets * 5.0, 2), ROUND(deposit_balance * 5.0, 2), ROUND(wealth_balance * 5.0, 2),
           ROUND(fund_balance * 5.0, 2), ROUND(insurance_balance * 5.0, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 5.0, 2), ROUND(loan_balance * 5.0, 2), ROUND(creditcard_balance * 5.0, 2),
           ROUND(net_assets * 5.0, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           5, 3, 2, 2, 0, 2
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 12, snapshot_date, snapshot_type,
           ROUND(total_assets * 3.8, 2), ROUND(deposit_balance * 3.8, 2), ROUND(wealth_balance * 3.8, 2),
           ROUND(fund_balance * 3.8, 2), ROUND(insurance_balance * 3.8, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 3.8, 2), ROUND(loan_balance * 3.8, 2), ROUND(creditcard_balance * 3.8, 2),
           ROUND(net_assets * 3.8, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           4, 2, 1, 2, 0, 1
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 13, snapshot_date, snapshot_type,
           ROUND(total_assets * 6.5, 2), ROUND(deposit_balance * 6.5, 2), ROUND(wealth_balance * 6.5, 2),
           ROUND(fund_balance * 6.5, 2), ROUND(insurance_balance * 6.5, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 6.5, 2), ROUND(loan_balance * 6.5, 2), ROUND(creditcard_balance * 6.5, 2),
           ROUND(net_assets * 6.5, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           6, 3, 2, 3, 0, 2
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 14, snapshot_date, snapshot_type,
           ROUND(total_assets * 4.2, 2), ROUND(deposit_balance * 4.2, 2), ROUND(wealth_balance * 4.2, 2),
           ROUND(fund_balance * 4.2, 2), ROUND(insurance_balance * 4.2, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 4.2, 2), ROUND(loan_balance * 4.2, 2), ROUND(creditcard_balance * 4.2, 2),
           ROUND(net_assets * 4.2, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           4, 2, 1, 2, 0, 1
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 15, snapshot_date, snapshot_type,
           ROUND(total_assets * 7.8, 2), ROUND(deposit_balance * 7.8, 2), ROUND(wealth_balance * 7.8, 2),
           ROUND(fund_balance * 7.8, 2), ROUND(insurance_balance * 7.8, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 7.8, 2), ROUND(loan_balance * 7.8, 2), ROUND(creditcard_balance * 7.8, 2),
           ROUND(net_assets * 7.8, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           7, 4, 3, 3, 0, 2
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 16, snapshot_date, snapshot_type,
           ROUND(total_assets * 3.2, 2), ROUND(deposit_balance * 3.2, 2), ROUND(wealth_balance * 3.2, 2),
           ROUND(fund_balance * 3.2, 2), ROUND(insurance_balance * 3.2, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 3.2, 2), ROUND(loan_balance * 3.2, 2), ROUND(creditcard_balance * 3.2, 2),
           ROUND(net_assets * 3.2, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           3, 2, 1, 2, 0, 1
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 17, snapshot_date, snapshot_type,
           ROUND(total_assets * 5.5, 2), ROUND(deposit_balance * 5.5, 2), ROUND(wealth_balance * 5.5, 2),
           ROUND(fund_balance * 5.5, 2), ROUND(insurance_balance * 5.5, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 5.5, 2), ROUND(loan_balance * 5.5, 2), ROUND(creditcard_balance * 5.5, 2),
           ROUND(net_assets * 5.5, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           5, 3, 2, 2, 0, 2
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 18, snapshot_date, snapshot_type,
           ROUND(total_assets * 4.8, 2), ROUND(deposit_balance * 4.8, 2), ROUND(wealth_balance * 4.8, 2),
           ROUND(fund_balance * 4.8, 2), ROUND(insurance_balance * 4.8, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 4.8, 2), ROUND(loan_balance * 4.8, 2), ROUND(creditcard_balance * 4.8, 2),
           ROUND(net_assets * 4.8, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           5, 2, 2, 2, 0, 1
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 19, snapshot_date, snapshot_type,
           ROUND(total_assets * 3.5, 2), ROUND(deposit_balance * 3.5, 2), ROUND(wealth_balance * 3.5, 2),
           ROUND(fund_balance * 3.5, 2), ROUND(insurance_balance * 3.5, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 3.5, 2), ROUND(loan_balance * 3.5, 2), ROUND(creditcard_balance * 3.5, 2),
           ROUND(net_assets * 3.5, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           4, 2, 1, 1, 0, 1
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
    UNION ALL
    SELECT 20, snapshot_date, snapshot_type,
           ROUND(total_assets * 6.2, 2), ROUND(deposit_balance * 6.2, 2), ROUND(wealth_balance * 6.2, 2),
           ROUND(fund_balance * 6.2, 2), ROUND(insurance_balance * 6.2, 2),
           metal_balance, trust_balance, other_balance,
           ROUND(total_liabilities * 6.2, 2), ROUND(loan_balance * 6.2, 2), ROUND(creditcard_balance * 6.2, 2),
           ROUND(net_assets * 6.2, 2),
           total_assets_growth, deposit_growth, wealth_growth,
           6, 3, 2, 2, 0, 2
    FROM crm_customer_asset_snapshot WHERE customer_id = 1
) t;

-- =====================================================
-- 2. 交易流水数据 (基于客户1的数据复制)
-- =====================================================

-- 清除客户2-20的交易流水数据
DELETE FROM crm_customer_transaction_mock WHERE customer_id BETWEEN 2 AND 20;

-- 为客户2-10（零售客户）生成交易数据
INSERT INTO crm_customer_transaction_mock
(customer_id, account_no, transaction_date, transaction_time, tans_no, trad_type,
 trad_money, acct_bal, trad_abs, direction,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT customer_id, CONCAT('ACC', LPAD(customer_id, 6, '0')),
       transaction_date, transaction_time,
       CONCAT('TRX', customer_id, DATE_FORMAT(transaction_date, '%Y%m%d'), LPAD(@row_num := @row_num + 1, 4, '0')),
       trad_type, trad_money, acct_bal, trad_abs, direction,
       'system', NOW(), 'system', NOW(), 0, 1
FROM (
    SELECT 2, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 0.6, 2), ROUND(acct_bal * 0.6, 2), trad_abs, direction, (@row_num := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 3, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 1.8, 2), ROUND(acct_bal * 1.8, 2), trad_abs, direction, (@row_num := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 4, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 0.8, 2), ROUND(acct_bal * 0.8, 2), trad_abs, direction, (@row_num := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 5, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 1.4, 2), ROUND(acct_bal * 1.4, 2), trad_abs, direction, (@row_num := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 6, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 0.4, 2), ROUND(acct_bal * 0.4, 2), trad_abs, direction, (@row_num := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 7, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 2.5, 2), ROUND(acct_bal * 2.5, 2), trad_abs, direction, (@row_num := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 8, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 0.7, 2), ROUND(acct_bal * 0.7, 2), trad_abs, direction, (@row_num := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 9, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 1.2, 2), ROUND(acct_bal * 1.2, 2), trad_abs, direction, (@row_num := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 10, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 0.5, 2), ROUND(acct_bal * 0.5, 2), trad_abs, direction, (@row_num := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
) t
ORDER BY customer_id, transaction_date, transaction_time;

-- 为对公客户(11-20)生成交易数据（金额更大，摘要修改为企业相关）
INSERT INTO crm_customer_transaction_mock
(customer_id, account_no, transaction_date, transaction_time, tans_no, trad_type,
 trad_money, acct_bal, trad_abs, direction,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT customer_id, CONCAT('ACC', LPAD(customer_id, 6, '0')),
       transaction_date, transaction_time,
       CONCAT('TRX', customer_id, DATE_FORMAT(transaction_date, '%Y%m%d'), LPAD(@row_num2 := @row_num2 + 1, 4, '0')),
       trad_type, trad_money, acct_bal,
       CASE
           WHEN trad_abs LIKE '%工资%' THEN '营业收入'
           WHEN trad_abs LIKE '%购物%' THEN '采购支出'
           WHEN trad_abs LIKE '%房租%' THEN '租金支出'
           WHEN trad_abs LIKE '%投资%' THEN '投资收益'
           WHEN trad_abs LIKE '%ATM%' THEN '提现'
           ELSE trad_abs
       END as trad_abs,
       direction,
       'system', NOW(), 'system', NOW(), 0, 1
FROM (
    SELECT 11, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 8.0, 2), ROUND(acct_bal * 8.0, 2), trad_abs, direction, (@row_num2 := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 12, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 6.5, 2), ROUND(acct_bal * 6.5, 2), trad_abs, direction, (@row_num2 := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 13, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 10.0, 2), ROUND(acct_bal * 10.0, 2), trad_abs, direction, (@row_num2 := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 14, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 7.0, 2), ROUND(acct_bal * 7.0, 2), trad_abs, direction, (@row_num2 := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 15, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 12.0, 2), ROUND(acct_bal * 12.0, 2), trad_abs, direction, (@row_num2 := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 16, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 5.5, 2), ROUND(acct_bal * 5.5, 2), trad_abs, direction, (@row_num2 := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 17, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 9.0, 2), ROUND(acct_bal * 9.0, 2), trad_abs, direction, (@row_num2 := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 18, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 7.5, 2), ROUND(acct_bal * 7.5, 2), trad_abs, direction, (@row_num2 := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 19, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 6.0, 2), ROUND(acct_bal * 6.0, 2), trad_abs, direction, (@row_num2 := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
    UNION ALL
    SELECT 20, transaction_date, transaction_time, trad_type,
           ROUND(trad_money * 9.5, 2), ROUND(acct_bal * 9.5, 2), trad_abs, direction, (@row_num2 := 0)
    FROM crm_customer_transaction_mock WHERE customer_id = 1
) t
ORDER BY customer_id, transaction_date, transaction_time;

-- =====================================================
-- 3. 客户标签数据
-- =====================================================

-- 为所有客户添加标签
UPDATE crm_customer SET customer_tag = '潜力客户,工薪族' WHERE id = 1;
UPDATE crm_customer SET customer_tag = '普通客户,工薪族' WHERE id = 2;
UPDATE crm_customer SET customer_tag = '优质客户,高净值' WHERE id = 3;
UPDATE crm_customer SET customer_tag = '普通客户,工薪族' WHERE id = 4;
UPDATE crm_customer SET customer_tag = '潜力客户,中产阶级' WHERE id = 5;
UPDATE crm_customer SET customer_tag = '新客户,年轻人' WHERE id = 6;
UPDATE crm_customer SET customer_tag = 'VIP客户,高净值,企业主' WHERE id = 7;
UPDATE crm_customer SET customer_tag = '普通客户,工薪族' WHERE id = 8;
UPDATE crm_customer SET customer_tag = '潜力客户,中产阶级' WHERE id = 9;
UPDATE crm_customer SET customer_tag = '新客户,工薪族' WHERE id = 10;

-- 对公客户标签
UPDATE crm_customer SET customer_tag = '优质企业,科技行业,A级信用' WHERE id = 11;
UPDATE crm_customer SET customer_tag = '优质企业,贸易行业,A级信用' WHERE id = 12;
UPDATE crm_customer SET customer_tag = 'VIP企业,制造业,AAA级信用' WHERE id = 13;
UPDATE crm_customer SET customer_tag = '优质企业,电商行业,A级信用' WHERE id = 14;
UPDATE crm_customer SET customer_tag = 'VIP企业,建筑行业,AAA级信用' WHERE id = 15;
UPDATE crm_customer SET customer_tag = '普通企业,餐饮行业,B级信用' WHERE id = 16;
UPDATE crm_customer SET customer_tag = '优质企业,物流行业,A级信用' WHERE id = 17;
UPDATE crm_customer SET customer_tag = '优质企业,医疗行业,A级信用' WHERE id = 18;
UPDATE crm_customer SET customer_tag = '普通企业,软件行业,B级信用' WHERE id = 19;
UPDATE crm_customer SET customer_tag = 'VIP企业,制造业,AAA级信用' WHERE id = 20;

-- =====================================================
-- 数据生成完成
-- =====================================================
SELECT '✅ 所有客户的概览数据已生成完成！' as message;
SELECT CONCAT('✅ 资产快照记录数: ', COUNT(*)) as message FROM crm_customer_asset_snapshot WHERE customer_id BETWEEN 1 AND 20;
SELECT CONCAT('✅ 交易流水记录数: ', COUNT(*)) as message FROM crm_customer_transaction_mock WHERE customer_id BETWEEN 1 AND 20;
SELECT '✅ 客户标签已全部更新' as message;
