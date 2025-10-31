-- 为客户ID=1生成12个月的资产快照测试数据
-- 基于当前实际余额：存款 80,000,000,000，理财 1,017,500，贷款 300,000
-- 为了测试效果，将数据调整为合理范围

-- 2024年11月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2024-11-30', 'monthly',
 1050000.00, 750000.00, 280000.00, 15000.00, 5000.00, 0, 0, 0,
 320000.00, 300000.00, 20000.00,
 730000.00,
 NULL, NULL, NULL,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2024年12月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2024-12-31', 'monthly',
 1080000.00, 770000.00, 295000.00, 15000.00, 0, 0, 0, 0,
 315000.00, 295000.00, 20000.00,
 765000.00,
 0.0286, 0.0267, 0.0536,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2025年1月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2025-01-31', 'monthly',
 1120000.00, 790000.00, 310000.00, 18000.00, 2000.00, 0, 0, 0,
 310000.00, 290000.00, 20000.00,
 810000.00,
 0.0370, 0.0260, 0.0508,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2025年2月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2025-02-28', 'monthly',
 1145000.00, 805000.00, 325000.00, 15000.00, 0, 0, 0, 0,
 308000.00, 288000.00, 20000.00,
 837000.00,
 0.0223, 0.0190, 0.0484,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2025年3月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2025-03-31', 'monthly',
 1180000.00, 825000.00, 340000.00, 15000.00, 0, 0, 0, 0,
 305000.00, 285000.00, 20000.00,
 875000.00,
 0.0306, 0.0248, 0.0462,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2025年4月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2025-04-30', 'monthly',
 1215000.00, 845000.00, 355000.00, 15000.00, 0, 0, 0, 0,
 303000.00, 283000.00, 20000.00,
 912000.00,
 0.0297, 0.0242, 0.0441,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2025年5月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2025-05-31', 'monthly',
 1250000.00, 865000.00, 370000.00, 15000.00, 0, 0, 0, 0,
 301000.00, 281000.00, 20000.00,
 949000.00,
 0.0288, 0.0237, 0.0423,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2025年6月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2025-06-30', 'monthly',
 1285000.00, 885000.00, 385000.00, 15000.00, 0, 0, 0, 0,
 299000.00, 279000.00, 20000.00,
 986000.00,
 0.0280, 0.0231, 0.0405,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2025年7月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2025-07-31', 'monthly',
 1320000.00, 905000.00, 400000.00, 15000.00, 0, 0, 0, 0,
 297000.00, 277000.00, 20000.00,
 1023000.00,
 0.0272, 0.0226, 0.0390,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2025年8月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2025-08-31', 'monthly',
 1355000.00, 925000.00, 415000.00, 15000.00, 0, 0, 0, 0,
 295000.00, 275000.00, 20000.00,
 1060000.00,
 0.0265, 0.0221, 0.0375,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2025年9月快照
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2025-09-30', 'monthly',
 1390000.00, 945000.00, 430000.00, 15000.00, 0, 0, 0, 0,
 293000.00, 273000.00, 20000.00,
 1097000.00,
 0.0258, 0.0216, 0.0361,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 2025年10月快照（最新）
INSERT INTO crm_customer_asset_snapshot (
    customer_id, snapshot_date, snapshot_type,
    total_assets, deposit_balance, wealth_balance, fund_balance, insurance_balance, metal_balance, trust_balance, other_balance,
    total_liabilities, loan_balance, creditcard_balance,
    net_assets,
    total_assets_growth, deposit_growth, wealth_growth,
    deposit_account_count, wealth_account_count, fund_account_count, loan_account_count, creditcard_count, insurance_count,
    create_time, update_time, deleted, tenant_id
) VALUES
(1, '2025-10-31', 'monthly',
 1425000.00, 965000.00, 445000.00, 15000.00, 0, 0, 0, 0,
 291000.00, 271000.00, 20000.00,
 1134000.00,
 0.0252, 0.0212, 0.0349,
 1, 1, 0, 1, 0, 0,
 NOW(), NOW(), 0, 1);

-- 统计说明：
-- 1. 总资产从 105万 增长到 142.5万（增长约 36%）
-- 2. 存款从 75万 增长到 96.5万（增长约 29%）
-- 3. 理财从 28万 增长到 44.5万（增长约 59%）
-- 4. 贷款从 30万 减少到 27.1万（减少约 10%）
-- 5. 净资产从 73万 增长到 113.4万（增长约 55%）
-- 6. 展示了稳健的资产增长趋势
