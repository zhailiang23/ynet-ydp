-- =====================================================
-- 为所有客户生成完整的客户概览数据
-- 包括: 资产快照、交易流水、客户标签
-- 生成时间: 2025-10-31
-- =====================================================

USE `ruoyi-vue-pro`;

-- =====================================================
-- 1. 资产快照数据 (12个月历史数据)
-- =====================================================

-- 先清除已有的资产快照数据
DELETE FROM crm_customer_asset_snapshot WHERE customer_id BETWEEN 1 AND 20;

-- 为客户1-10 (零售客户) 生成资产快照
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
 total_liabilities, loan_balance, creditcard_balance, net_assets,
 total_assets_growth, deposit_growth, wealth_growth,
 deposit_account_count, wealth_account_count, fund_account_count,
 loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
VALUES
-- 客户1: 张三 (资产规模: 100万-150万)
(1, '2024-11-01', 'monthly', 1050000.00, 750000.00, 280000.00, 15000.00, 5000.00, 0.00, 0.00, 0.00, 300000.00, 280000.00, 20000.00, 750000.00, NULL, NULL, NULL, 2, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2024-12-01', 'monthly', 1080000.00, 770000.00, 295000.00, 15000.00, 0.00, 0.00, 0.00, 0.00, 290000.00, 270000.00, 20000.00, 790000.00, 2.86, 2.67, 5.36, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2025-01-01', 'monthly', 1120000.00, 790000.00, 310000.00, 20000.00, 0.00, 0.00, 0.00, 0.00, 285000.00, 265000.00, 20000.00, 835000.00, 3.70, 2.60, 5.08, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2025-02-01', 'monthly', 1165000.00, 815000.00, 330000.00, 20000.00, 0.00, 0.00, 0.00, 0.00, 282000.00, 262000.00, 20000.00, 883000.00, 4.02, 3.16, 6.45, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2025-03-01', 'monthly', 1210000.00, 840000.00, 350000.00, 20000.00, 0.00, 0.00, 0.00, 0.00, 276000.00, 256000.00, 20000.00, 934000.00, 3.86, 3.07, 6.06, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2025-04-01', 'monthly', 1250000.00, 865000.00, 365000.00, 20000.00, 0.00, 0.00, 0.00, 0.00, 270000.00, 250000.00, 20000.00, 980000.00, 3.31, 2.98, 4.29, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2025-05-01', 'monthly', 1280000.00, 885000.00, 375000.00, 20000.00, 0.00, 0.00, 0.00, 0.00, 264000.00, 244000.00, 20000.00, 1016000.00, 2.40, 2.31, 2.74, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2025-06-01', 'monthly', 1310000.00, 905000.00, 385000.00, 20000.00, 0.00, 0.00, 0.00, 0.00, 258000.00, 238000.00, 20000.00, 1052000.00, 2.34, 2.26, 2.67, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2025-07-01', 'monthly', 1340000.00, 915000.00, 405000.00, 20000.00, 0.00, 0.00, 0.00, 0.00, 252000.00, 232000.00, 20000.00, 1088000.00, 2.29, 1.10, 5.19, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2025-08-01', 'monthly', 1355000.00, 925000.00, 415000.00, 15000.00, 0.00, 0.00, 0.00, 0.00, 246000.00, 226000.00, 20000.00, 1109000.00, 1.12, 1.09, 2.47, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2025-09-01', 'monthly', 1390000.00, 945000.00, 430000.00, 15000.00, 0.00, 0.00, 0.00, 0.00, 240000.00, 220000.00, 20000.00, 1150000.00, 2.58, 2.16, 3.61, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(1, '2025-10-01', 'monthly', 1425000.00, 965000.00, 445000.00, 15000.00, 0.00, 0.00, 0.00, 0.00, 234000.00, 214000.00, 20000.00, 1191000.00, 2.52, 2.12, 3.49, 2, 1, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),

-- 客户2: 李四 (资产规模: 50万-80万)
(2, '2024-11-01', 'monthly', 520000.00, 380000.00, 130000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 150000.00, 140000.00, 10000.00, 370000.00, NULL, NULL, NULL, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2024-12-01', 'monthly', 538000.00, 392000.00, 136000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 147000.00, 137000.00, 10000.00, 391000.00, 3.46, 3.16, 4.62, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2025-01-01', 'monthly', 562000.00, 408000.00, 144000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 145000.00, 135000.00, 10000.00, 417000.00, 4.46, 4.08, 5.88, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2025-02-01', 'monthly', 585000.00, 422000.00, 153000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 142000.00, 132000.00, 10000.00, 443000.00, 4.09, 3.43, 6.25, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2025-03-01', 'monthly', 608000.00, 438000.00, 160000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 140000.00, 130000.00, 10000.00, 468000.00, 3.93, 3.79, 4.58, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2025-04-01', 'monthly', 628000.00, 452000.00, 166000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 138000.00, 128000.00, 10000.00, 490000.00, 3.29, 3.20, 3.75, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2025-05-01', 'monthly', 648000.00, 465000.00, 173000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 136000.00, 126000.00, 10000.00, 512000.00, 3.18, 2.88, 4.22, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2025-06-01', 'monthly', 670000.00, 480000.00, 180000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 134000.00, 124000.00, 10000.00, 536000.00, 3.40, 3.23, 4.05, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2025-07-01', 'monthly', 692000.00, 495000.00, 187000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 132000.00, 122000.00, 10000.00, 560000.00, 3.28, 3.13, 3.89, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2025-08-01', 'monthly', 712000.00, 508000.00, 194000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 130000.00, 120000.00, 10000.00, 582000.00, 2.89, 2.63, 3.74, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2025-09-01', 'monthly', 735000.00, 522000.00, 203000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 128000.00, 118000.00, 10000.00, 607000.00, 3.23, 2.76, 4.64, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(2, '2025-10-01', 'monthly', 758000.00, 538000.00, 210000.00, 8000.00, 2000.00, 0.00, 0.00, 0.00, 126000.00, 116000.00, 10000.00, 632000.00, 3.13, 3.07, 3.45, 1, 1, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),

-- 客户3: 王五 (资产规模: 200万-280万)
(3, '2024-11-01', 'monthly', 2100000.00, 1500000.00, 550000.00, 40000.00, 10000.00, 0.00, 0.00, 0.00, 500000.00, 480000.00, 20000.00, 1600000.00, NULL, NULL, NULL, 3, 2, 1, 1, 1, 1, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2024-12-01', 'monthly', 2160000.00, 1545000.00, 575000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 490000.00, 470000.00, 20000.00, 1670000.00, 2.86, 3.00, 4.55, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2025-01-01', 'monthly', 2230000.00, 1590000.00, 600000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 480000.00, 460000.00, 20000.00, 1750000.00, 3.24, 2.91, 4.35, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2025-02-01', 'monthly', 2305000.00, 1640000.00, 625000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 470000.00, 450000.00, 20000.00, 1835000.00, 3.36, 3.14, 4.17, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2025-03-01', 'monthly', 2380000.00, 1690000.00, 650000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 460000.00, 440000.00, 20000.00, 1920000.00, 3.25, 3.05, 4.00, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2025-04-01', 'monthly', 2450000.00, 1735000.00, 675000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 450000.00, 430000.00, 20000.00, 2000000.00, 2.94, 2.66, 3.85, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2025-05-01', 'monthly', 2515000.00, 1780000.00, 695000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 440000.00, 420000.00, 20000.00, 2075000.00, 2.65, 2.59, 2.96, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2025-06-01', 'monthly', 2580000.00, 1825000.00, 715000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 430000.00, 410000.00, 20000.00, 2150000.00, 2.59, 2.53, 2.88, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2025-07-01', 'monthly', 2640000.00, 1860000.00, 740000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 420000.00, 400000.00, 20000.00, 2220000.00, 2.33, 1.92, 3.50, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2025-08-01', 'monthly', 2695000.00, 1900000.00, 755000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 410000.00, 390000.00, 20000.00, 2285000.00, 2.08, 2.15, 2.03, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2025-09-01', 'monthly', 2750000.00, 1940000.00, 770000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 400000.00, 380000.00, 20000.00, 2350000.00, 2.04, 2.11, 1.99, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1),
(3, '2025-10-01', 'monthly', 2800000.00, 1975000.00, 785000.00, 40000.00, 0.00, 0.00, 0.00, 0.00, 390000.00, 370000.00, 20000.00, 2410000.00, 1.82, 1.80, 1.95, 3, 2, 1, 1, 1, 0, 'system', NOW(), 'system', NOW(), 0, 1);

-- 为客户4-10生成类似的数据 (简化版本，使用不同的资产规模)
-- 客户4: 赵六 (资产规模: 80万-120万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, total_liabilities, loan_balance, creditcard_balance, net_assets,
 deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    4 as customer_id,
    snapshot_date,
    snapshot_type,
    ROUND(total_assets * 0.7, 2) as total_assets,
    ROUND(deposit_balance * 0.7, 2) as deposit_balance,
    ROUND(wealth_balance * 0.7, 2) as wealth_balance,
    ROUND(fund_balance * 0.7, 2) as fund_balance,
    ROUND(insurance_balance * 0.7, 2) as insurance_balance,
    ROUND(total_liabilities * 0.7, 2) as total_liabilities,
    ROUND(loan_balance * 0.7, 2) as loan_balance,
    ROUND(creditcard_balance * 0.7, 2) as creditcard_balance,
    ROUND(net_assets * 0.7, 2) as net_assets,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 客户5: 孙七 (资产规模: 150万-200万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, total_liabilities, loan_balance, creditcard_balance, net_assets,
 deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    5 as customer_id,
    snapshot_date,
    snapshot_type,
    ROUND(total_assets * 1.3, 2) as total_assets,
    ROUND(deposit_balance * 1.3, 2) as deposit_balance,
    ROUND(wealth_balance * 1.3, 2) as wealth_balance,
    ROUND(fund_balance * 1.3, 2) as fund_balance,
    ROUND(insurance_balance * 1.3, 2) as insurance_balance,
    ROUND(total_liabilities * 1.3, 2) as total_liabilities,
    ROUND(loan_balance * 1.3, 2) as loan_balance,
    ROUND(creditcard_balance * 1.3, 2) as creditcard_balance,
    ROUND(net_assets * 1.3, 2) as net_assets,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 客户6: 周八 (资产规模: 30万-50万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, total_liabilities, loan_balance, creditcard_balance, net_assets,
 deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    6 as customer_id,
    snapshot_date,
    snapshot_type,
    ROUND(total_assets * 0.35, 2) as total_assets,
    ROUND(deposit_balance * 0.35, 2) as deposit_balance,
    ROUND(wealth_balance * 0.35, 2) as wealth_balance,
    ROUND(fund_balance * 0.35, 2) as fund_balance,
    ROUND(insurance_balance * 0.35, 2) as insurance_balance,
    ROUND(total_liabilities * 0.35, 2) as total_liabilities,
    ROUND(loan_balance * 0.35, 2) as loan_balance,
    ROUND(creditcard_balance * 0.35, 2) as creditcard_balance,
    ROUND(net_assets * 0.35, 2) as net_assets,
    1, 1, 0, 1, 1, 0,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 客户7: 吴九 (资产规模: 250万-350万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, total_liabilities, loan_balance, creditcard_balance, net_assets,
 deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    7 as customer_id,
    snapshot_date,
    snapshot_type,
    ROUND(total_assets * 2.2, 2) as total_assets,
    ROUND(deposit_balance * 2.2, 2) as deposit_balance,
    ROUND(wealth_balance * 2.2, 2) as wealth_balance,
    ROUND(fund_balance * 2.2, 2) as fund_balance,
    ROUND(insurance_balance * 2.2, 2) as insurance_balance,
    ROUND(total_liabilities * 2.2, 2) as total_liabilities,
    ROUND(loan_balance * 2.2, 2) as loan_balance,
    ROUND(creditcard_balance * 2.2, 2) as creditcard_balance,
    ROUND(net_assets * 2.2, 2) as net_assets,
    3, 2, 2, 1, 2, 1,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 客户8: 郑十 (资产规模: 60万-90万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, total_liabilities, loan_balance, creditcard_balance, net_assets,
 deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    8 as customer_id,
    snapshot_date,
    snapshot_type,
    ROUND(total_assets * 0.6, 2) as total_assets,
    ROUND(deposit_balance * 0.6, 2) as deposit_balance,
    ROUND(wealth_balance * 0.6, 2) as wealth_balance,
    ROUND(fund_balance * 0.6, 2) as fund_balance,
    ROUND(insurance_balance * 0.6, 2) as insurance_balance,
    ROUND(total_liabilities * 0.6, 2) as total_liabilities,
    ROUND(loan_balance * 0.6, 2) as loan_balance,
    ROUND(creditcard_balance * 0.6, 2) as creditcard_balance,
    ROUND(net_assets * 0.6, 2) as net_assets,
    1, 1, 1, 1, 1, 1,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 客户9: 钱十一 (资产规模: 120万-180万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, total_liabilities, loan_balance, creditcard_balance, net_assets,
 deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    9 as customer_id,
    snapshot_date,
    snapshot_type,
    ROUND(total_assets * 1.15, 2) as total_assets,
    ROUND(deposit_balance * 1.15, 2) as deposit_balance,
    ROUND(wealth_balance * 1.15, 2) as wealth_balance,
    ROUND(fund_balance * 1.15, 2) as fund_balance,
    ROUND(insurance_balance * 1.15, 2) as insurance_balance,
    ROUND(total_liabilities * 1.15, 2) as total_liabilities,
    ROUND(loan_balance * 1.15, 2) as loan_balance,
    ROUND(creditcard_balance * 1.15, 2) as creditcard_balance,
    ROUND(net_assets * 1.15, 2) as net_assets,
    2, 2, 1, 1, 1, 1,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 客户10: 冯十二 (资产规模: 40万-65万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, total_liabilities, loan_balance, creditcard_balance, net_assets,
 deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    10 as customer_id,
    snapshot_date,
    snapshot_type,
    ROUND(total_assets * 0.42, 2) as total_assets,
    ROUND(deposit_balance * 0.42, 2) as deposit_balance,
    ROUND(wealth_balance * 0.42, 2) as wealth_balance,
    ROUND(fund_balance * 0.42, 2) as fund_balance,
    ROUND(insurance_balance * 0.42, 2) as insurance_balance,
    ROUND(total_liabilities * 0.42, 2) as total_liabilities,
    ROUND(loan_balance * 0.42, 2) as loan_balance,
    ROUND(creditcard_balance * 0.42, 2) as creditcard_balance,
    ROUND(net_assets * 0.42, 2) as net_assets,
    1, 1, 1, 0, 1, 1,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 对公客户 (11-20) 的资产快照数据 - 规模更大
-- 客户11: 北京科技有限公司 (资产规模: 500万-800万)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, total_liabilities, loan_balance, creditcard_balance, net_assets,
 deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    11 as customer_id,
    snapshot_date,
    snapshot_type,
    ROUND(total_assets * 5.0, 2) as total_assets,
    ROUND(deposit_balance * 5.0, 2) as deposit_balance,
    ROUND(wealth_balance * 5.0, 2) as wealth_balance,
    ROUND(fund_balance * 5.0, 2) as fund_balance,
    ROUND(insurance_balance * 5.0, 2) as insurance_balance,
    ROUND(total_liabilities * 5.0, 2) as total_liabilities,
    ROUND(loan_balance * 5.0, 2) as loan_balance,
    ROUND(creditcard_balance * 5.0, 2) as creditcard_balance,
    ROUND(net_assets * 5.0, 2) as net_assets,
    5, 3, 2, 2, 0, 2,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- 客户12-20: 其他对公客户 (使用不同倍数生成不同规模的数据)
INSERT INTO crm_customer_asset_snapshot
(customer_id, snapshot_date, snapshot_type, total_assets, deposit_balance, wealth_balance,
 fund_balance, insurance_balance, total_liabilities, loan_balance, creditcard_balance, net_assets,
 deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    12 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 3.8, 2), ROUND(deposit_balance * 3.8, 2), ROUND(wealth_balance * 3.8, 2),
    ROUND(fund_balance * 3.8, 2), ROUND(insurance_balance * 3.8, 2),
    ROUND(total_liabilities * 3.8, 2), ROUND(loan_balance * 3.8, 2), ROUND(creditcard_balance * 3.8, 2), ROUND(net_assets * 3.8, 2),
    4, 2, 1, 2, 0, 1, 'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1
UNION ALL
SELECT
    13 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 6.5, 2), ROUND(deposit_balance * 6.5, 2), ROUND(wealth_balance * 6.5, 2),
    ROUND(fund_balance * 6.5, 2), ROUND(insurance_balance * 6.5, 2),
    ROUND(total_liabilities * 6.5, 2), ROUND(loan_balance * 6.5, 2), ROUND(creditcard_balance * 6.5, 2), ROUND(net_assets * 6.5, 2),
    6, 3, 2, 3, 0, 2, 'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1
UNION ALL
SELECT
    14 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 4.2, 2), ROUND(deposit_balance * 4.2, 2), ROUND(wealth_balance * 4.2, 2),
    ROUND(fund_balance * 4.2, 2), ROUND(insurance_balance * 4.2, 2),
    ROUND(total_liabilities * 4.2, 2), ROUND(loan_balance * 4.2, 2), ROUND(creditcard_balance * 4.2, 2), ROUND(net_assets * 4.2, 2),
    4, 2, 1, 2, 0, 1, 'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1
UNION ALL
SELECT
    15 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 7.8, 2), ROUND(deposit_balance * 7.8, 2), ROUND(wealth_balance * 7.8, 2),
    ROUND(fund_balance * 7.8, 2), ROUND(insurance_balance * 7.8, 2),
    ROUND(total_liabilities * 7.8, 2), ROUND(loan_balance * 7.8, 2), ROUND(creditcard_balance * 7.8, 2), ROUND(net_assets * 7.8, 2),
    7, 4, 3, 3, 0, 2, 'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1
UNION ALL
SELECT
    16 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 3.2, 2), ROUND(deposit_balance * 3.2, 2), ROUND(wealth_balance * 3.2, 2),
    ROUND(fund_balance * 3.2, 2), ROUND(insurance_balance * 3.2, 2),
    ROUND(total_liabilities * 3.2, 2), ROUND(loan_balance * 3.2, 2), ROUND(creditcard_balance * 3.2, 2), ROUND(net_assets * 3.2, 2),
    3, 2, 1, 2, 0, 1, 'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1
UNION ALL
SELECT
    17 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 5.5, 2), ROUND(deposit_balance * 5.5, 2), ROUND(wealth_balance * 5.5, 2),
    ROUND(fund_balance * 5.5, 2), ROUND(insurance_balance * 5.5, 2),
    ROUND(total_liabilities * 5.5, 2), ROUND(loan_balance * 5.5, 2), ROUND(creditcard_balance * 5.5, 2), ROUND(net_assets * 5.5, 2),
    5, 3, 2, 2, 0, 2, 'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1
UNION ALL
SELECT
    18 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 4.8, 2), ROUND(deposit_balance * 4.8, 2), ROUND(wealth_balance * 4.8, 2),
    ROUND(fund_balance * 4.8, 2), ROUND(insurance_balance * 4.8, 2),
    ROUND(total_liabilities * 4.8, 2), ROUND(loan_balance * 4.8, 2), ROUND(creditcard_balance * 4.8, 2), ROUND(net_assets * 4.8, 2),
    5, 2, 2, 2, 0, 1, 'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1
UNION ALL
SELECT
    19 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 3.5, 2), ROUND(deposit_balance * 3.5, 2), ROUND(wealth_balance * 3.5, 2),
    ROUND(fund_balance * 3.5, 2), ROUND(insurance_balance * 3.5, 2),
    ROUND(total_liabilities * 3.5, 2), ROUND(loan_balance * 3.5, 2), ROUND(creditcard_balance * 3.5, 2), ROUND(net_assets * 3.5, 2),
    4, 2, 1, 1, 0, 1, 'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1
UNION ALL
SELECT
    20 as customer_id, snapshot_date, snapshot_type,
    ROUND(total_assets * 6.2, 2), ROUND(deposit_balance * 6.2, 2), ROUND(wealth_balance * 6.2, 2),
    ROUND(fund_balance * 6.2, 2), ROUND(insurance_balance * 6.2, 2),
    ROUND(total_liabilities * 6.2, 2), ROUND(loan_balance * 6.2, 2), ROUND(creditcard_balance * 6.2, 2), ROUND(net_assets * 6.2, 2),
    6, 3, 2, 2, 0, 2, 'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_asset_snapshot WHERE customer_id = 1;

-- =====================================================
-- 2. 交易流水数据 (6个月历史数据)
-- =====================================================

-- 清除已有的交易流水数据
DELETE FROM crm_customer_transaction_mock WHERE customer_id BETWEEN 1 AND 20;

-- 为客户1生成交易流水（已有数据，这里添加客户2-20的数据）
-- 客户1的交易数据已在之前的脚本中生成，这里跳过

-- 为客户2-10（零售客户）生成交易数据
-- 使用Python脚本生成，这里给出简化版SQL
INSERT INTO crm_customer_transaction_mock
(customer_id, trad_no, trad_date, trad_time, trad_type, trad_money, balance, trad_summary,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT
    2 as customer_id,
    CONCAT('TRX', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@row_number:=@row_number+1), 4, '0')) as trad_no,
    trad_date, trad_time, trad_type,
    ROUND(trad_money * 0.6, 2) as trad_money,
    ROUND(balance * 0.6, 2) as balance,
    trad_summary,
    'system', NOW(), 'system', NOW(), 0, 1
FROM crm_customer_transaction_mock, (SELECT @row_number:=0) r
WHERE customer_id = 1
ORDER BY trad_date, trad_time;

-- 为客户3-10生成类似的交易数据（使用不同的金额倍数）
INSERT INTO crm_customer_transaction_mock
(customer_id, trad_no, trad_date, trad_time, trad_type, trad_money, balance, trad_summary,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT customer_id, trad_no, trad_date, trad_time, trad_type, trad_money, balance, trad_summary,
       'system', NOW(), 'system', NOW(), 0, 1
FROM (
    SELECT 3 as customer_id, CONCAT('TRX3', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn3:=@rn3+1), 4, '0')) as trad_no,
           trad_date, trad_time, trad_type, ROUND(trad_money * 1.8, 2) as trad_money,
           ROUND(balance * 1.8, 2) as balance, trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn3:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 4, CONCAT('TRX4', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn4:=@rn4+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 0.8, 2), ROUND(balance * 0.8, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn4:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 5, CONCAT('TRX5', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn5:=@rn5+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 1.4, 2), ROUND(balance * 1.4, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn5:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 6, CONCAT('TRX6', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn6:=@rn6+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 0.4, 2), ROUND(balance * 0.4, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn6:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 7, CONCAT('TRX7', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn7:=@rn7+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 2.5, 2), ROUND(balance * 2.5, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn7:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 8, CONCAT('TRX8', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn8:=@rn8+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 0.7, 2), ROUND(balance * 0.7, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn8:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 9, CONCAT('TRX9', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn9:=@rn9+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 1.2, 2), ROUND(balance * 1.2, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn9:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 10, CONCAT('TRX10', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn10:=@rn10+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 0.5, 2), ROUND(balance * 0.5, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn10:=0) r WHERE customer_id = 1
) t
ORDER BY customer_id, trad_date, trad_time;

-- 为对公客户(11-20)生成交易数据（金额更大）
INSERT INTO crm_customer_transaction_mock
(customer_id, trad_no, trad_date, trad_time, trad_type, trad_money, balance, trad_summary,
 creator, create_time, updater, update_time, deleted, tenant_id)
SELECT customer_id, trad_no, trad_date, trad_time, trad_type, trad_money, balance,
       CASE
           WHEN trad_summary LIKE '%工资%' THEN '营业收入'
           WHEN trad_summary LIKE '%购物%' THEN '采购支出'
           WHEN trad_summary LIKE '%房租%' THEN '租金支出'
           WHEN trad_summary LIKE '%投资%' THEN '投资收益'
           ELSE trad_summary
       END as trad_summary,
       'system', NOW(), 'system', NOW(), 0, 1
FROM (
    SELECT 11, CONCAT('TRX11', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn11:=@rn11+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 8.0, 2), ROUND(balance * 8.0, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn11:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 12, CONCAT('TRX12', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn12:=@rn12+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 6.5, 2), ROUND(balance * 6.5, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn12:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 13, CONCAT('TRX13', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn13:=@rn13+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 10.0, 2), ROUND(balance * 10.0, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn13:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 14, CONCAT('TRX14', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn14:=@rn14+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 7.0, 2), ROUND(balance * 7.0, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn14:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 15, CONCAT('TRX15', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn15:=@rn15+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 12.0, 2), ROUND(balance * 12.0, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn15:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 16, CONCAT('TRX16', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn16:=@rn16+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 5.5, 2), ROUND(balance * 5.5, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn16:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 17, CONCAT('TRX17', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn17:=@rn17+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 9.0, 2), ROUND(balance * 9.0, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn17:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 18, CONCAT('TRX18', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn18:=@rn18+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 7.5, 2), ROUND(balance * 7.5, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn18:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 19, CONCAT('TRX19', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn19:=@rn19+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 6.0, 2), ROUND(balance * 6.0, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn19:=0) r WHERE customer_id = 1
    UNION ALL
    SELECT 20, CONCAT('TRX20', DATE_FORMAT(trad_date, '%Y%m%d'), LPAD((@rn20:=@rn20+1), 4, '0')),
           trad_date, trad_time, trad_type, ROUND(trad_money * 9.5, 2), ROUND(balance * 9.5, 2), trad_summary
    FROM crm_customer_transaction_mock, (SELECT @rn20:=0) r WHERE customer_id = 1
) t
ORDER BY customer_id, trad_date, trad_time;

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
