-- 对公客户他行信息表
-- 用于记录企业客户与其他银行的合作信息,帮助客户经理全面掌握客户金融服务情况

DROP TABLE IF EXISTS crm_company_other_bank;
CREATE TABLE crm_company_other_bank (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    customer_id BIGINT NOT NULL COMMENT '客户ID（关联crm_customer表）',

    -- 他行基本信息
    bank_name VARCHAR(100) NOT NULL COMMENT '银行名称',
    bank_type VARCHAR(50) COMMENT '银行类型（国有大行、股份制银行、城商行、农商行、外资银行、政策性银行等）',
    branch_name VARCHAR(200) COMMENT '开户支行名称',
    relationship_manager VARCHAR(100) COMMENT '他行客户经理姓名',
    manager_phone VARCHAR(50) COMMENT '他行客户经理电话',
    manager_email VARCHAR(100) COMMENT '他行客户经理邮箱',

    -- 合作关系
    cooperation_type VARCHAR(50) COMMENT '合作类型（主办行、协办行、一般合作）',
    cooperation_start_date DATE COMMENT '合作开始日期',
    relationship_duration INT COMMENT '合作年限',
    cooperation_status TINYINT DEFAULT 1 COMMENT '合作状态（1=合作中 2=已终止 3=暂停合作）',

    -- 账户信息
    has_settlement_account TINYINT DEFAULT 0 COMMENT '是否有结算账户（0=否 1=是）',
    settlement_account_no VARCHAR(50) COMMENT '结算账户账号',
    account_balance DECIMAL(18,2) COMMENT '账户余额（元）',
    is_main_settlement_bank TINYINT DEFAULT 0 COMMENT '是否主结算行（0=否 1=是）',
    daily_average_balance DECIMAL(18,2) COMMENT '日均存款（元）',

    -- 业务规模
    total_credit_limit DECIMAL(18,2) COMMENT '授信总额度（元）',
    used_credit_limit DECIMAL(18,2) COMMENT '已用授信额度（元）',
    loan_balance DECIMAL(18,2) COMMENT '贷款余额（元）',
    deposit_balance DECIMAL(18,2) COMMENT '存款余额（元）',
    wealth_management_balance DECIMAL(18,2) COMMENT '理财余额（元）',

    -- 业务类型
    business_types VARCHAR(500) COMMENT '业务类型（多选，用逗号分隔：对公存款、流动资金贷款、项目贷款、贸易融资、票据业务、保理业务、供应链金融、投行业务、财务顾问、外汇业务、代发工资等）',
    main_business VARCHAR(200) COMMENT '主要业务',

    -- 贷款信息
    loan_product_name VARCHAR(200) COMMENT '贷款产品名称',
    loan_amount DECIMAL(18,2) COMMENT '贷款金额（元）',
    loan_rate DECIMAL(8,4) COMMENT '贷款利率（%）',
    loan_start_date DATE COMMENT '贷款起始日',
    loan_maturity_date DATE COMMENT '贷款到期日',
    guarantee_type VARCHAR(100) COMMENT '担保方式（信用、抵押、质押、保证等）',
    collateral_info VARCHAR(500) COMMENT '抵押物信息',

    -- 服务评价
    service_satisfaction TINYINT COMMENT '服务满意度（1-5星）',
    pricing_level VARCHAR(20) COMMENT '价格水平（优惠、市场价、偏高）',
    response_speed VARCHAR(20) COMMENT '响应速度（快、一般、慢）',
    customer_comment VARCHAR(1000) COMMENT '客户评价',

    -- 合作优势与劣势
    competitor_advantage VARCHAR(1000) COMMENT '他行优势（为什么客户选择他行）',
    competitor_disadvantage VARCHAR(1000) COMMENT '他行劣势（客户不满意的地方）',
    our_opportunity VARCHAR(1000) COMMENT '我行机会点（可以从哪些方面切入）',

    -- 营销策略
    competitive_strategy VARCHAR(1000) COMMENT '竞争策略（如何争取客户份额）',
    target_business VARCHAR(500) COMMENT '目标业务（希望从他行抢占的业务）',
    marketing_priority TINYINT DEFAULT 3 COMMENT '营销优先级（1=高 2=中 3=低）',

    -- 合同与到期提醒
    contract_no VARCHAR(100) COMMENT '合同编号',
    contract_expiry_date DATE COMMENT '合同到期日',
    is_due_soon TINYINT DEFAULT 0 COMMENT '是否即将到期（0=否 1=是，3个月内到期）',
    follow_up_plan VARCHAR(1000) COMMENT '跟进计划',

    -- 风险提示
    risk_warning VARCHAR(1000) COMMENT '风险提示（如他行抽贷风险、担保风险等）',

    -- 信息来源
    info_source VARCHAR(100) DEFAULT '客户经理调研' COMMENT '信息来源（客户经理调研、客户提供、公开信息、第三方渠道等）',
    info_reliability TINYINT DEFAULT 3 COMMENT '信息可靠性（1=高 2=中 3=低）',
    last_update_date DATE COMMENT '信息最后更新日期',

    -- 备注
    remark VARCHAR(1000) COMMENT '备注',

    -- 多租户
    tenant_id BIGINT NOT NULL DEFAULT 1 COMMENT '租户ID',

    -- 审计字段
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除（0=未删除 1=已删除）',

    -- 索引
    INDEX idx_customer_id (customer_id) COMMENT '客户ID索引',
    INDEX idx_bank_name (bank_name) COMMENT '银行名称索引',
    INDEX idx_cooperation_type (cooperation_type) COMMENT '合作类型索引',
    INDEX idx_cooperation_status (cooperation_status) COMMENT '合作状态索引',
    INDEX idx_is_main_settlement_bank (is_main_settlement_bank) COMMENT '主结算行索引',
    INDEX idx_marketing_priority (marketing_priority) COMMENT '营销优先级索引',
    INDEX idx_contract_expiry_date (contract_expiry_date) COMMENT '合同到期日索引',
    INDEX idx_tenant_id (tenant_id) COMMENT '租户ID索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='对公客户他行信息表';

-- 插入测试数据：为前10个客户各创建3-5条他行信息

-- 客户1（浦发银行）- 3条记录
INSERT INTO crm_company_other_bank (customer_id, bank_name, bank_type, branch_name, relationship_manager, manager_phone,
    cooperation_type, cooperation_start_date, relationship_duration, cooperation_status,
    has_settlement_account, settlement_account_no, account_balance, is_main_settlement_bank, daily_average_balance,
    total_credit_limit, used_credit_limit, loan_balance, deposit_balance, wealth_management_balance,
    business_types, main_business,
    loan_product_name, loan_amount, loan_rate, loan_start_date, loan_maturity_date, guarantee_type,
    service_satisfaction, pricing_level, response_speed, customer_comment,
    competitor_advantage, competitor_disadvantage, our_opportunity, competitive_strategy, target_business, marketing_priority,
    contract_no, contract_expiry_date, is_due_soon, follow_up_plan,
    info_source, info_reliability, last_update_date, remark, tenant_id, creator)
VALUES
(1, '中国工商银行', '国有大行', '上海市分行浦东支行', '张经理', '021-58888888',
    '协办行', '2018-05-15', 7, 1,
    1, '1002****0001', 850000000.00, 0, 820000000.00,
    3000000000.00, 1200000000.00, 1200000000.00, 850000000.00, 200000000.00,
    '对公存款,流动资金贷款,票据业务,代发工资', '流动资金贷款和结算业务',
    '流动资金贷款', 1200000000.00, 3.8500, '2024-01-15', '2025-01-15', '信用',
    4, '市场价', '一般', '服务较为规范，但审批流程相对较慢',
    '国有大行品牌优势，利率相对较低，资金实力雄厚', '审批流程较慢，服务响应不够灵活，创新产品较少', '在票据业务、供应链金融等创新业务上可以发力，提供更灵活的服务方案', '重点推进供应链金融和票据池业务，强调服务效率和创新能力', '供应链金融,票据池业务', 2,
    'ICBC-SPDB-2024-001', '2025-01-15', 0, '贷款到期前2个月开始跟进续贷，推荐票据池和供应链金融产品',
    '客户经理调研', 1, '2025-01-20', '工行为该客户协办行，主要提供基础结算和贷款服务', 1, 'admin'),

(1, '中国建设银行', '国有大行', '上海市分行营业部', '李经理', '021-68888888',
    '一般合作', '2020-03-20', 5, 1,
    1, '1003****0001', 320000000.00, 0, 300000000.00,
    500000000.00, 0, 0, 320000000.00, 0,
    '对公存款,财务顾问', '存款和投行咨询服务',
    NULL, NULL, NULL, NULL, NULL, NULL,
    3, '市场价', '快', '投行业务专业度较高',
    '投行业务经验丰富，债券承销能力强', '存款利率不高，贷款额度偏紧', '可推进综合金融服务，提供贷款和投行一体化方案', '联动投行部门，提供债券承销和财务顾问服务', '债券承销,并购贷款', 3,
    NULL, NULL, 0, '定期拜访，了解投行需求，提供综合金融方案',
    '客户提供', 2, '2025-01-15', '建行主要提供投行咨询服务，存款规模不大', 1, 'admin'),

(1, '招商银行', '股份制银行', '上海分行陆家嘴支行', '王经理', '021-38888888',
    '主办行', '2015-08-10', 10, 1,
    1, '3086****0001', 2300000000.00, 1, 2200000000.00,
    8000000000.00, 5500000000.00, 5500000000.00, 2300000000.00, 800000000.00,
    '对公存款,流动资金贷款,项目贷款,票据业务,供应链金融,外汇业务,代发工资,理财业务', '综合金融服务',
    '银团贷款', 5500000000.00, 3.9500, '2023-06-01', '2026-06-01', '信用',
    5, '优惠', '快', '服务响应快，产品创新能力强，客户经理专业度高',
    '主办行优势明显，服务响应快，创新产品丰富，利率优惠力度大，客户经理关系深厚', '业务规模大，较难切入，客户依赖度高', '可从特定项目融资、绿色金融等细分领域切入，展示我行专业能力', '差异化竞争，重点推进绿色金融和科技金融，提供个性化服务方案，逐步提升业务占比', '绿色金融,科技创新贷款', 1,
    'CMB-SPDB-2023-001', '2026-06-01', 0, '保持高频沟通，寻找项目融资机会，展示我行特色产品',
    '客户经理调研', 1, '2025-01-25', '招行为主办行，业务合作深入，需长期经营才能提升份额', 1, 'admin');

-- 客户2（贵州茅台）- 5条记录
INSERT INTO crm_company_other_bank (customer_id, bank_name, bank_type, branch_name, relationship_manager, manager_phone,
    cooperation_type, cooperation_start_date, relationship_duration, cooperation_status,
    has_settlement_account, settlement_account_no, account_balance, is_main_settlement_bank, daily_average_balance,
    total_credit_limit, used_credit_limit, loan_balance, deposit_balance, wealth_management_balance,
    business_types, main_business,
    loan_product_name, loan_amount, loan_rate, loan_start_date, loan_maturity_date, guarantee_type,
    service_satisfaction, pricing_level, response_speed, customer_comment,
    competitor_advantage, competitor_disadvantage, our_opportunity, competitive_strategy, target_business, marketing_priority,
    contract_no, contract_expiry_date, is_due_soon, follow_up_plan,
    info_source, info_reliability, last_update_date, remark, tenant_id, creator)
VALUES
(2, '中国银行', '国有大行', '贵州省分行遵义支行', '刘经理', '0851-28888888',
    '协办行', '2016-09-01', 9, 1,
    1, '1042****0001', 1500000000.00, 0, 1400000000.00,
    5000000000.00, 3000000000.00, 3000000000.00, 1500000000.00, 500000000.00,
    '对公存款,流动资金贷款,外汇业务,国际结算', '流动资金贷款和外汇业务',
    '流动资金贷款', 3000000000.00, 3.7000, '2024-06-15', '2025-06-15', '信用',
    4, '市场价', '快', '外汇业务专业，国际结算便利',
    '外汇业务优势明显，国际网络健全，跨境业务经验丰富', '贷款利率偏高，创新产品不足', '推进供应链金融和经销商融资业务，发挥我行灵活优势', '重点营销供应链金融产品，帮助客户降低经销商融资成本', '供应链金融,经销商贷款', 1,
    'BOC-MOUTAI-2024-001', '2025-06-15', 0, '贷款到期前跟进续贷，推荐供应链金融方案',
    '客户经理调研', 1, '2025-01-22', '中行外汇业务较强，有一定合作基础', 1, 'admin'),

(2, '中国农业银行', '国有大行', '贵州省分行', '陈经理', '0851-88888888',
    '一般合作', '2019-03-15', 6, 1,
    1, '1039****0001', 680000000.00, 0, 650000000.00,
    2000000000.00, 0, 0, 680000000.00, 0,
    '对公存款,票据业务', '存款和票据业务',
    NULL, NULL, NULL, NULL, NULL, NULL,
    3, '市场价', '一般', '服务较为基础',
    '地方网点多，存款利率有竞争力', '创新能力不足，响应速度慢', '推进现金管理和票据池业务', '提供更专业的现金管理方案，提升服务体验', '现金管理,票据池', 2,
    NULL, NULL, 0, '定期拜访，推荐现金管理产品',
    '客户提供', 2, '2025-01-18', '农行业务合作较浅，主要为存款业务', 1, 'admin'),

(2, '中信银行', '股份制银行', '贵阳分行', '周经理', '0851-86888888',
    '协办行', '2021-05-20', 4, 1,
    1, '3022****0001', 800000000.00, 0, 750000000.00,
    3000000000.00, 1500000000.00, 1500000000.00, 800000000.00, 300000000.00,
    '对公存款,项目贷款,投行业务,理财业务', '项目贷款和投行服务',
    '项目贷款', 1500000000.00, 4.0000, '2023-11-01', '2026-11-01', '抵押',
    4, '市场价', '快', '投行业务较专业，服务响应较快',
    '投行业务能力强，产品创新积极，服务灵活', '资金成本偏高，贷款利率相对较高', '在流动资金贷款和供应链金融上展示价格优势', '以价格和服务优势切入流动资金贷款市场', '流动资金贷款,供应链金融', 2,
    'CITIC-MOUTAI-2023-001', '2026-11-01', 0, '项目贷款到期前沟通续贷，推荐流动资金贷款',
    '客户经理调研', 1, '2025-01-20', '中信在投行业务上有一定优势', 1, 'admin'),

(2, '兴业银行', '股份制银行', '贵阳分行', '赵经理', '0851-85888888',
    '主办行', '2014-06-10', 11, 1,
    1, '3091****0001', 3200000000.00, 1, 3000000000.00,
    12000000000.00, 8000000000.00, 8000000000.00, 3200000000.00, 1000000000.00,
    '对公存款,流动资金贷款,项目贷款,供应链金融,票据业务,投行业务,理财业务,现金管理', '综合金融服务',
    '银团贷款', 8000000000.00, 3.6500, '2022-12-08', '2027-12-08', '信用',
    5, '优惠', '快', '主办行服务全面，利率优惠，客户经理专业负责',
    '主办行优势，合作时间长，信任度高，综合服务能力强，利率优惠力度大', '份额占比大，较难切入核心业务', '可从特定项目融资、绿色金融等专业领域切入', '深耕专业领域，提供差异化产品，逐步建立信任', '绿色信贷,项目融资', 1,
    'CIB-MOUTAI-2022-001', '2027-12-08', 0, '保持常态沟通，寻找项目融资机会',
    '客户经理调研', 1, '2025-01-28', '兴业为主办行，合作关系深厚，需长期经营', 1, 'admin'),

(2, '贵州银行', '城商行', '总行营业部', '杨经理', '0851-96688888',
    '一般合作', '2020-08-25', 5, 1,
    1, '8001****0001', 350000000.00, 0, 320000000.00,
    500000000.00, 0, 0, 350000000.00, 0,
    '对公存款', '存款业务',
    NULL, NULL, NULL, NULL, NULL, NULL,
    3, '优惠', '快', '本地化服务较好',
    '本地银行，服务灵活，存款利率有优势', '规模小，产品单一，综合服务能力弱', '可提供更全面的综合金融服务方案', '展示大行服务能力，提供综合金融方案', '流动资金贷款,现金管理', 3,
    NULL, NULL, 0, '了解客户需求，提供综合服务方案',
    '客户提供', 2, '2025-01-15', '地方性银行，合作较浅', 1, 'admin');

-- 客户3（美的集团）- 4条记录
INSERT INTO crm_company_other_bank (customer_id, bank_name, bank_type, branch_name, relationship_manager, manager_phone,
    cooperation_type, cooperation_start_date, relationship_duration, cooperation_status,
    has_settlement_account, settlement_account_no, account_balance, is_main_settlement_bank, daily_average_balance,
    total_credit_limit, used_credit_limit, loan_balance, deposit_balance, wealth_management_balance,
    business_types, main_business,
    loan_product_name, loan_amount, loan_rate, loan_start_date, loan_maturity_date, guarantee_type,
    service_satisfaction, pricing_level, response_speed, customer_comment,
    competitor_advantage, competitor_disadvantage, our_opportunity, competitive_strategy, target_business, marketing_priority,
    contract_no, contract_expiry_date, is_due_soon, follow_up_plan,
    info_source, info_reliability, last_update_date, remark, tenant_id, creator)
VALUES
(3, '中国工商银行', '国有大行', '广东省分行佛山分行', '孙经理', '0757-82888888',
    '主办行', '2012-05-15', 13, 1,
    1, '1002****0002', 5800000000.00, 1, 5500000000.00,
    25000000000.00, 18000000000.00, 18000000000.00, 5800000000.00, 2000000000.00,
    '对公存款,流动资金贷款,项目贷款,并购贷款,供应链金融,票据业务,外汇业务,投行业务,现金管理', '综合金融服务',
    '银团贷款', 18000000000.00, 3.7500, '2023-03-01', '2026-03-01', '信用',
    5, '优惠', '快', '主办行服务全面，资金实力强，国际业务优势明显',
    '国有大行优势，资金充足，综合服务能力强，国际网络健全，主办行地位稳固', '主办行地位难以撼动，核心业务占比高', '可从创新业务、供应链金融等细分领域切入，提供差异化服务', '深耕供应链金融和设备融资租赁，展示专业能力，逐步提升份额', '供应链金融,融资租赁', 1,
    'ICBC-MIDEA-2023-001', '2026-03-01', 0, '保持高频沟通，寻找供应链金融和融资租赁机会',
    '客户经理调研', 1, '2025-01-25', '工行为主办行，合作深入，需长期经营', 1, 'admin'),

(3, '中国建设银行', '国有大行', '广东省分行佛山分行', '钱经理', '0757-83888888',
    '协办行', '2015-09-20', 10, 1,
    1, '1003****0002', 2800000000.00, 0, 2650000000.00,
    10000000000.00, 6000000000.00, 6000000000.00, 2800000000.00, 800000000.00,
    '对公存款,项目贷款,投行业务,理财业务', '项目贷款和投行服务',
    '项目贷款', 6000000000.00, 3.8000, '2024-05-01', '2027-05-01', '抵押',
    4, '市场价', '一般', '投行业务专业，项目贷款经验丰富',
    '投行业务能力强，项目贷款审批较快', '服务响应速度一般，创新产品不足', '推进流动资金贷款和供应链金融业务', '以服务效率和创新产品切入，争取流动资金贷款市场', '流动资金贷款,供应链金融', 2,
    'CCB-MIDEA-2024-001', '2027-05-01', 0, '项目贷款到期前沟通续贷，推荐流动资金贷款产品',
    '客户经理调研', 1, '2025-01-22', '建行为协办行，主要提供项目贷款', 1, 'admin'),

(3, '招商银行', '股份制银行', '广州分行', '郑经理', '020-38888888',
    '协办行', '2018-03-10', 7, 1,
    1, '3086****0002', 3500000000.00, 0, 3300000000.00,
    12000000000.00, 8000000000.00, 8000000000.00, 3500000000.00, 1200000000.00,
    '对公存款,流动资金贷款,供应链金融,票据业务,外汇业务,理财业务,现金管理', '流动资金贷款和供应链金融',
    '流动资金贷款', 8000000000.00, 3.9000, '2024-03-10', '2025-03-10', '信用',
    5, '市场价', '快', '供应链金融产品丰富，服务响应快，创新能力强',
    '供应链金融专业，服务灵活，创新能力强，客户经理专业度高', '利率相对偏高，资金规模不如国有大行', '可在价格上展示优势，提供更优惠的利率方案', '以价格优势和专业服务切入，争取流动资金贷款续贷', '流动资金贷款续贷,票据池', 1,
    'CMB-MIDEA-2024-001', '2025-03-10', 1, '贷款即将到期，重点跟进续贷，提供优惠利率方案',
    '客户经理调研', 1, '2025-01-20', '招行为协办行，贷款即将到期，是重点营销机会', 1, 'admin'),

(3, '平安银行', '股份制银行', '深圳分行', '吴经理', '0755-22888888',
    '一般合作', '2021-11-05', 4, 1,
    1, '3034****0002', 1200000000.00, 0, 1100000000.00,
    5000000000.00, 2000000000.00, 2000000000.00, 1200000000.00, 300000000.00,
    '对公存款,供应链金融,理财业务', '供应链金融',
    '供应链金融', 2000000000.00, 4.2000, '2024-11-05', '2025-11-05', '应收账款质押',
    3, '偏高', '快', '供应链金融产品创新，但利率偏高',
    '供应链金融产品创新能力强，线上化程度高', '利率偏高，缺乏价格竞争力', '以更优惠的价格切入供应链金融市场', '重点营销供应链金融续贷，提供有竞争力的价格方案', '供应链金融续贷', 1,
    'PAB-MIDEA-2024-001', '2025-11-05', 0, '供应链金融到期前跟进，提供更优惠的利率',
    '客户经理调研', 1, '2025-01-18', '平安供应链金融有特色，但利率较高，有价格切入机会', 1, 'admin');

-- 继续为客户4-10生成他行信息...
-- 客户4（兴业银行）- 3条记录
INSERT INTO crm_company_other_bank (customer_id, bank_name, bank_type, branch_name, relationship_manager, manager_phone,
    cooperation_type, cooperation_start_date, relationship_duration, cooperation_status,
    has_settlement_account, account_balance, is_main_settlement_bank, daily_average_balance,
    total_credit_limit, used_credit_limit, loan_balance, deposit_balance,
    business_types, main_business, loan_product_name, loan_amount, loan_rate, loan_start_date, loan_maturity_date, guarantee_type,
    service_satisfaction, pricing_level, response_speed,
    competitor_advantage, competitor_disadvantage, our_opportunity, competitive_strategy, target_business, marketing_priority,
    info_source, info_reliability, last_update_date, remark, tenant_id, creator)
VALUES
(4, '中国工商银行', '国有大行', '福建省分行福州分行', '冯经理', '0591-87888888',
    '协办行', '2017-04-20', 8, 1,
    1, 1800000000.00, 0, 1700000000.00,
    6000000000.00, 3500000000.00, 3500000000.00, 1800000000.00,
    '对公存款,流动资金贷款,票据业务', '流动资金贷款', '流动资金贷款', 3500000000.00, 3.7500, '2024-04-20', '2025-04-20', '信用',
    4, '市场价', '一般',
    '资金实力强，品牌优势明显', '服务灵活性不足', '提供更灵活的金融服务方案', '展示服务优势，推进综合金融合作', '票据池,现金管理', 2,
    '客户经理调研', 1, '2025-01-20', '工行为协办行', 1, 'admin'),

(4, '招商银行', '股份制银行', '深圳分行', '蒋经理', '0755-83888888',
    '主办行', '2013-06-15', 12, 1,
    1, 4500000000.00, 1, 4300000000.00,
    15000000000.00, 10000000000.00, 10000000000.00, 4500000000.00,
    '对公存款,流动资金贷款,项目贷款,供应链金融,投行业务,现金管理', '综合金融服务', '银团贷款', 10000000000.00, 3.6500, '2023-06-15', '2026-06-15', '信用',
    5, '优惠', '快',
    '主办行优势，服务全面，响应快', '主办行地位稳固', '从专业领域切入', '深耕绿色金融等专业领域', '绿色金融', 1,
    '客户经理调研', 1, '2025-01-25', '招行为主办行', 1, 'admin'),

(4, '中国银行', '国有大行', '福建省分行', '韩经理', '0591-88888888',
    '一般合作', '2020-09-10', 5, 1,
    1, 680000000.00, 0, 650000000.00,
    1000000000.00, 0, 0, 680000000.00,
    '对公存款,外汇业务', '存款和外汇业务', NULL, NULL, NULL, NULL, NULL, NULL,
    3, '市场价', '快',
    '外汇业务专业', '综合服务能力不足', '推进综合金融服务', '提供一站式金融服务', '流动资金贷款', 3,
    '客户提供', 2, '2025-01-15', '中行主要提供外汇服务', 1, 'admin');

-- 客户5（中芯国际）- 5条记录
INSERT INTO crm_company_other_bank (customer_id, bank_name, bank_type, branch_name, relationship_manager, manager_phone,
    cooperation_type, cooperation_start_date, relationship_duration, cooperation_status,
    has_settlement_account, account_balance, is_main_settlement_bank, daily_average_balance,
    total_credit_limit, used_credit_limit, loan_balance, deposit_balance, wealth_management_balance,
    business_types, main_business, loan_product_name, loan_amount, loan_rate, loan_start_date, loan_maturity_date, guarantee_type,
    service_satisfaction, pricing_level, response_speed, customer_comment,
    competitor_advantage, competitor_disadvantage, our_opportunity, competitive_strategy, target_business, marketing_priority,
    contract_expiry_date, is_due_soon, info_source, info_reliability, last_update_date, remark, tenant_id, creator)
VALUES
(5, '中国工商银行', '国有大行', '上海市分行张江支行', '何经理', '021-50888888',
    '主办行', '2010-03-20', 15, 1,
    1, 8500000000.00, 1, 8200000000.00,
    35000000000.00, 28000000000.00, 28000000000.00, 8500000000.00, 3000000000.00,
    '对公存款,项目贷款,并购贷款,外汇业务,投行业务,现金管理', '综合金融服务', '银团贷款', 28000000000.00, 3.5000, '2022-03-20', '2027-03-20', '信用',
    5, '优惠', '快', '主办行服务全面，国家战略项目支持力度大',
    '国有大行资金实力强，国家项目支持力度大，综合服务能力强', '主办行地位难以撼动', '从科技金融等专业领域切入', '深耕科技金融，提供专业服务', '科技贷款,知识产权质押', 1,
    '2027-03-20', 0, '客户经理调研', 1, '2025-01-28', '工行为主办行，国家大基金项目牵头行', 1, 'admin'),

(5, '中国建设银行', '国有大行', '上海市分行', '魏经理', '021-68888888',
    '协办行', '2015-06-15', 10, 1,
    1, 3200000000.00, 0, 3000000000.00,
    12000000000.00, 8000000000.00, 8000000000.00, 3200000000.00, 1000000000.00,
    '对公存款,项目贷款,投行业务', '项目贷款', '项目贷款', 8000000000.00, 3.6500, '2023-06-15', '2026-06-15', '信用',
    4, '市场价', '一般', '项目贷款审批较快',
    '项目贷款经验丰富', '创新能力不足', '推进供应链金融业务', '发展供应链金融', '供应链金融', 2,
    '2026-06-15', 0, '客户经理调研', 1, '2025-01-22', '建行为协办行', 1, 'admin'),

(5, '招商银行', '股份制银行', '上海分行', '姚经理', '021-58888888',
    '协办行', '2018-09-10', 7, 1,
    1, 2800000000.00, 0, 2650000000.00,
    10000000000.00, 6000000000.00, 6000000000.00, 2800000000.00, 800000000.00,
    '对公存款,流动资金贷款,供应链金融,现金管理', '流动资金贷款和供应链金融', '流动资金贷款', 6000000000.00, 3.8500, '2024-09-10', '2025-09-10', '信用',
    4, '市场价', '快', '供应链金融服务专业',
    '供应链金融产品丰富，服务响应快', '利率相对偏高', '价格优势切入', '提供更优惠价格', '流动资金贷款续贷', 1,
    '2025-09-10', 0, '客户经理调研', 1, '2025-01-20', '招行贷款即将到期', 1, 'admin'),

(5, '中国进出口银行', '政策性银行', '上海分行', '邵经理', '021-63888888',
    '协办行', '2020-11-25', 5, 1,
    0, NULL, 0, NULL,
    5000000000.00, 3000000000.00, 3000000000.00, 0, 0,
    '项目贷款,外汇业务', '项目贷款', '项目贷款', 3000000000.00, 3.2000, '2023-11-25', '2028-11-25', '信用',
    4, '优惠', '一般', '政策性银行利率优惠',
    '利率低，政策支持力度大', '产品单一，服务灵活性不足', '提供综合金融服务', '展示综合服务能力', '综合金融服务', 3,
    '2028-11-25', 0, '客户提供', 2, '2025-01-15', '进出口行提供政策性贷款', 1, 'admin'),

(5, '国家开发银行', '政策性银行', '上海市分行', '史经理', '021-63999999',
    '协办行', '2019-05-15', 6, 1,
    0, NULL, 0, NULL,
    8000000000.00, 5000000000.00, 5000000000.00, 0, 0,
    '项目贷款', '项目贷款', '项目贷款', 5000000000.00, 3.1500, '2022-05-15', '2029-05-15', '信用',
    4, '优惠', '慢', '政策性银行，支持国家重点项目',
    '利率最低，长期资金支持', '审批流程慢，服务较为僵化', '提供配套综合金融服务', '展示服务优势', '配套金融服务', 3,
    '2029-05-15', 0, '客户提供', 2, '2025-01-18', '国开行提供长期低息贷款', 1, 'admin');

-- 客户6-10的他行信息（简化版）
-- 客户6（恒瑞医药）- 4条
INSERT INTO crm_company_other_bank (customer_id, bank_name, bank_type, cooperation_type, cooperation_start_date, relationship_duration, cooperation_status,
    has_settlement_account, account_balance, is_main_settlement_bank, daily_average_balance,
    total_credit_limit, used_credit_limit, loan_balance, deposit_balance,
    business_types, main_business, loan_amount, loan_rate, loan_start_date, loan_maturity_date, guarantee_type,
    service_satisfaction, pricing_level, response_speed,
    competitor_advantage, our_opportunity, marketing_priority,
    info_source, info_reliability, last_update_date, remark, tenant_id, creator)
VALUES
(6, '中国工商银行', '国有大行', '主办行', '2011-08-15', 14, 1, 1, 3800000000.00, 1, 3600000000.00, 15000000000.00, 10000000000.00, 10000000000.00, 3800000000.00,
    '综合金融服务', '综合金融服务', 10000000000.00, 3.6500, '2023-08-15', '2026-08-15', '信用', 5, '优惠', '快',
    '主办行优势，服务全面', '专业医药金融服务', 1, '客户经理调研', 1, '2025-01-25', '工行为主办行', 1, 'admin'),
(6, '中国银行', '国有大行', '协办行', '2016-03-20', 9, 1, 1, 1500000000.00, 0, 1400000000.00, 5000000000.00, 3000000000.00, 3000000000.00, 1500000000.00,
    '流动资金贷款,外汇业务', '流动资金贷款', 3000000000.00, 3.8000, '2024-03-20', '2025-03-20', '信用', 4, '市场价', '快',
    '外汇业务专业', '推进供应链金融', 2, '客户经理调研', 1, '2025-01-22', '中行提供外汇服务', 1, 'admin'),
(6, '招商银行', '股份制银行', '协办行', '2019-11-10', 6, 1, 1, 2200000000.00, 0, 2000000000.00, 8000000000.00, 5000000000.00, 5000000000.00, 2200000000.00,
    '流动资金贷款,供应链金融', '流动资金贷款', 5000000000.00, 3.9000, '2024-11-10', '2025-11-10', '信用', 4, '市场价', '快',
    '供应链金融专业', '价格优势切入', 1, '客户经理调研', 1, '2025-01-20', '招行贷款即将到期', 1, 'admin'),
(6, '江苏银行', '城商行', '一般合作', '2021-05-15', 4, 1, 1, 680000000.00, 0, 650000000.00, 2000000000.00, 800000000.00, 800000000.00, 680000000.00,
    '对公存款,票据业务', '票据业务', 800000000.00, 4.0000, '2024-05-15', '2025-05-15', '信用', 3, '市场价', '快',
    '本地化服务好', '提供综合金融服务', 2, '客户提供', 2, '2025-01-18', '本地银行', 1, 'admin');

-- 客户7-10...（继续生成更多测试数据）
-- 为节省篇幅，下面用更简化的方式完成剩余客户
INSERT INTO crm_company_other_bank (customer_id, bank_name, bank_type, cooperation_type, has_settlement_account, account_balance,
    daily_average_balance, total_credit_limit, loan_balance, deposit_balance, business_types, main_business,
    service_satisfaction, marketing_priority, info_source, info_reliability, last_update_date, tenant_id, creator)
VALUES
-- 客户7（工商银行）- 3条
(7, '中国银行', '国有大行', '协办行', 1, 5600000000.00, 5400000000.00, 20000000000.00, 15000000000.00, 5600000000.00, '综合金融服务', '综合金融服务', 4, 2, '客户经理调研', 1, '2025-01-20', 1, 'admin'),
(7, '中国建设银行', '国有大行', '协办行', 1, 4200000000.00, 4000000000.00, 15000000000.00, 10000000000.00, 4200000000.00, '综合金融服务', '综合金融服务', 4, 2, '客户经理调研', 1, '2025-01-22', 1, 'admin'),
(7, '交通银行', '国有大行', '一般合作', 1, 1800000000.00, 1700000000.00, 5000000000.00, 2000000000.00, 1800000000.00, '对公存款,票据业务', '票据业务', 3, 3, '客户提供', 2, '2025-01-15', 1, 'admin'),

-- 客户8（伊利股份）- 5条
(8, '中国农业银行', '国有大行', '主办行', 1, 4800000000.00, 4600000000.00, 18000000000.00, 12000000000.00, 4800000000.00, '综合金融服务', '综合金融服务', 5, 1, '客户经理调研', 1, '2025-01-28', 1, 'admin'),
(8, '中国工商银行', '国有大行', '协办行', 1, 2800000000.00, 2650000000.00, 10000000000.00, 6000000000.00, 2800000000.00, '流动资金贷款', '流动资金贷款', 4, 2, '客户经理调研', 1, '2025-01-22', 1, 'admin'),
(8, '中国银行', '国有大行', '协办行', 1, 1500000000.00, 1400000000.00, 5000000000.00, 3000000000.00, 1500000000.00, '外汇业务', '外汇业务', 4, 2, '客户经理调研', 1, '2025-01-20', 1, 'admin'),
(8, '内蒙古银行', '城商行', '一般合作', 1, 680000000.00, 650000000.00, 2000000000.00, 800000000.00, 680000000.00, '对公存款', '存款业务', 3, 3, '客户提供', 2, '2025-01-15', 1, 'admin'),
(8, '包商银行', '城商行', '一般合作', 0, NULL, NULL, 500000000.00, 0, 0, '票据业务', '票据业务', 2, 3, '客户提供', 2, '2024-12-20', 1, 'admin'),

-- 客户9（农业银行）- 3条
(9, '中国工商银行', '国有大行', '协办行', 1, 6200000000.00, 6000000000.00, 25000000000.00, 18000000000.00, 6200000000.00, '综合金融服务', '综合金融服务', 4, 2, '客户经理调研', 1, '2025-01-25', 1, 'admin'),
(9, '中国建设银行', '国有大行', '协办行', 1, 4800000000.00, 4600000000.00, 20000000000.00, 15000000000.00, 4800000000.00, '综合金融服务', '综合金融服务', 4, 2, '客户经理调研', 1, '2025-01-20', 1, 'admin'),
(9, '中国银行', '国有大行', '协办行', 1, 3200000000.00, 3000000000.00, 12000000000.00, 8000000000.00, 3200000000.00, '外汇业务', '外汇业务', 4, 2, '客户经理调研', 1, '2025-01-22', 1, 'admin'),

-- 客户10（中国石油）- 4条
(10, '中国工商银行', '国有大行', '主办行', 1, 15000000000.00, 14500000000.00, 80000000000.00, 60000000000.00, 15000000000.00, '综合金融服务', '综合金融服务', 5, 1, '客户经理调研', 1, '2025-01-28', 1, 'admin'),
(10, '中国建设银行', '国有大行', '协办行', 1, 8500000000.00, 8200000000.00, 40000000000.00, 30000000000.00, 8500000000.00, '项目贷款', '项目贷款', 4, 2, '客户经理调研', 1, '2025-01-25', 1, 'admin'),
(10, '中国银行', '国有大行', '协办行', 1, 6800000000.00, 6500000000.00, 30000000000.00, 22000000000.00, 6800000000.00, '外汇业务', '外汇业务', 4, 1, '客户经理调研', 1, '2025-01-22', 1, 'admin'),
(10, '国家开发银行', '政策性银行', '协办行', 0, NULL, NULL, 25000000000.00, 20000000000.00, 0, '项目贷款', '项目贷款', 4, 3, '客户提供', 2, '2025-01-20', 1, 'admin');
