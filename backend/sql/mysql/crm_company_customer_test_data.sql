-- CRM对公客户测试数据SQL
-- 为crm_company_customer表的10条记录(id=1到10)填充36个新增字段的测试数据
-- 执行前请确保已执行crm_company_customer_add_fields.sql和crm_company_dict_data.sql

USE `ruoyi-vue-pro`;

-- ============================================
-- ID=1: 北京科技有限公司 - 高科技企业
-- ============================================
UPDATE crm_company_customer SET
    -- 财务信息 (高科技公司,利润率高)
    total_assets = 15000000.00,        -- 资产总额: 1500万
    total_debt = 7500000.00,           -- 负债总额: 750万 (50%)
    annual_income = 25000000.00,       -- 年收入: 2500万
    annual_profit = 3750000.00,        -- 年利润: 375万 (15%高利润率)
    fin_report_type = 'annual',        -- 财务报表类型: 年报

    -- 股权投资
    is_stock_holder = b'1',            -- 是股东
    hold_stock_amt = 5000000.00,       -- 持股金额: 500万
    investment_type = 'venture',       -- 投资类型: 风险投资

    -- 企业组织
    org_form = 'limited_liability',    -- 组织形式: 有限责任公司
    governance_structure = 'board_directors',  -- 治理结构: 董事会制
    holding_type = 'private_holding',  -- 控股类型: 民营控股
    enterprise_belong = '中关村科技园区',  -- 企业归属
    superior_dept = '北京市科技局',    -- 上级部门
    company_organization = '总部-研发中心-市场部',  -- 公司组织架构
    nation_code = 'CN',                -- 国家代码

    -- 业务经营
    main_business = '计算机软件开发、云计算服务、大数据分析、人工智能技术研发及应用',
    minor_business = 'IT咨询服务、系统集成、技术培训',
    business_mode = 'service',         -- 经营模式: 服务型
    business_start_date = '2015-06-15',-- 营业开始日期
    industry_character = 'high_tech',  -- 行业特征: 高新技术
    industry_development_prospect = 'high_growth',  -- 行业发展前景: 高速增长
    area_code = '110000',              -- 地区代码: 北京
    industry_position = '国内云计算服务商前50强,在垂直行业SaaS领域具有较强竞争力',

    -- 企业规模
    employee_scale = '2',              -- 员工规模: 50-300人(小型)
    assets_scale = '中小型企业',       -- 资产规模
    production_capacity = 'high',      -- 生产能力: 80%以上
    enterprise_property = '民营企业',  -- 企业性质

    -- 监管评级
    loan_card_flag = b'1',             -- 有贷款卡
    loan_card_status = 'normal',       -- 贷款卡状态: 正常
    loan_card_audit_date = '2024-03-15',  -- 贷款卡审核日期

    -- 其他特殊
    enterprise_scale_pboc = '小型企业',    -- 人行企业规模
    enterprise_scale_deposit = '一般存款户',  -- 存款企业规模

    -- 系统追溯
    etl_date = '2024-01-01',           -- ETL导入日期
    old_tx_seq_no = 'TXN202401010001', -- 老系统交易序列号
    old_last_update_sys = 'Legacy_CRM_V1',  -- 老系统最后更新系统
    old_cust_id = 'OLD_COMP_001'       -- 老系统客户ID
WHERE id = 1;

-- ============================================
-- ID=2: 上海贸易股份有限公司 - 大型贸易企业
-- ============================================
UPDATE crm_company_customer SET
    -- 财务信息 (贸易企业,利润率中等)
    total_assets = 85000000.00,        -- 资产总额: 8500万
    total_debt = 42500000.00,          -- 负债总额: 4250万 (50%)
    annual_income = 120000000.00,      -- 年收入: 1.2亿
    annual_profit = 9600000.00,        -- 年利润: 960万 (8%)
    fin_report_type = 'semi_annual',   -- 财务报表类型: 半年报

    -- 股权投资
    is_stock_holder = b'1',            -- 是股东
    hold_stock_amt = 20000000.00,      -- 持股金额: 2000万
    investment_type = 'strategic',     -- 投资类型: 战略投资

    -- 企业组织
    org_form = 'joint_stock',          -- 组织形式: 股份有限公司
    governance_structure = 'board_supervisors',  -- 治理结构: 监事会制
    holding_type = 'private_holding',  -- 控股类型: 民营控股
    enterprise_belong = '上海自贸区',  -- 企业归属
    superior_dept = '上海市商务委',    -- 上级部门
    company_organization = '总部-采购部-销售部-物流部-财务部',
    nation_code = 'CN',

    -- 业务经营
    main_business = '机电产品、电子设备、五金工具进出口贸易,国内贸易代理,供应链管理服务',
    minor_business = '仓储服务、报关报检代理、贸易咨询',
    business_mode = 'trading',         -- 经营模式: 贸易型
    business_start_date = '2008-03-20',
    industry_character = 'traditional',-- 行业特征: 传统行业
    industry_development_prospect = 'stable_growth',  -- 行业发展前景: 稳定增长
    area_code = '310000',              -- 地区代码: 上海
    industry_position = '上海市进出口企业百强,与欧美多家知名品牌建立战略合作关系',

    -- 企业规模
    employee_scale = '3',              -- 员工规模: 300-1000人(中型)
    assets_scale = '中型企业',
    production_capacity = 'medium',    -- 生产能力: 60%-80%
    enterprise_property = '股份制企业',

    -- 监管评级
    loan_card_flag = b'1',
    loan_card_status = 'normal',
    loan_card_audit_date = '2024-02-10',

    -- 其他特殊
    enterprise_scale_pboc = '中型企业',
    enterprise_scale_deposit = '重要存款户',

    -- 系统追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN202401010002',
    old_last_update_sys = 'Legacy_CRM_V1',
    old_cust_id = 'OLD_COMP_002'
WHERE id = 2;

-- ============================================
-- ID=3: 深圳制造有限公司 - 制造业企业
-- ============================================
UPDATE crm_company_customer SET
    -- 财务信息 (制造业,利润率较低)
    total_assets = 45000000.00,        -- 资产总额: 4500万
    total_debt = 27000000.00,          -- 负债总额: 2700万 (60%)
    annual_income = 60000000.00,       -- 年收入: 6000万
    annual_profit = 3600000.00,        -- 年利润: 360万 (6%)
    fin_report_type = 'quarterly',     -- 财务报表类型: 季报

    -- 股权投资
    is_stock_holder = b'0',            -- 非股东
    hold_stock_amt = NULL,
    investment_type = NULL,

    -- 企业组织
    org_form = 'limited_liability',
    governance_structure = 'shareholders',  -- 治理结构: 股东会制
    holding_type = 'private_holding',
    enterprise_belong = '深圳宝安工业园',
    superior_dept = '深圳市工信局',
    company_organization = '总部-生产车间-质检部-销售部',
    nation_code = 'CN',

    -- 业务经营
    main_business = '电子元器件制造、PCB电路板生产、LED显示屏组装、消费电子产品代工',
    minor_business = '产品设计、模具开发、售后维修服务',
    business_mode = 'manufacturing',   -- 经营模式: 生产型
    business_start_date = '2010-08-10',
    industry_character = 'traditional',
    industry_development_prospect = 'stable_growth',
    area_code = '440300',              -- 地区代码: 深圳
    industry_position = '深圳电子制造百强企业,为多家知名品牌提供代工服务',

    -- 企业规模
    employee_scale = '3',              -- 员工规模: 300-1000人
    assets_scale = '中型企业',
    production_capacity = 'high',      -- 生产能力: 80%以上
    enterprise_property = '民营企业',

    -- 监管评级
    loan_card_flag = b'1',
    loan_card_status = 'normal',
    loan_card_audit_date = '2024-01-20',

    -- 其他特殊
    enterprise_scale_pboc = '中型企业',
    enterprise_scale_deposit = '一般存款户',

    -- 系统追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN202401010003',
    old_last_update_sys = 'Legacy_CRM_V1',
    old_cust_id = 'OLD_COMP_003'
WHERE id = 3;

-- ============================================
-- ID=4: 杭州电商有限公司 - 电商企业
-- ============================================
UPDATE crm_company_customer SET
    -- 财务信息 (电商企业,利润率中等偏高)
    total_assets = 28000000.00,        -- 资产总额: 2800万
    total_debt = 11200000.00,          -- 负债总额: 1120万 (40%)
    annual_income = 50000000.00,       -- 年收入: 5000万
    annual_profit = 5000000.00,        -- 年利润: 500万 (10%)
    fin_report_type = 'monthly',       -- 财务报表类型: 月报

    -- 股权投资
    is_stock_holder = b'1',
    hold_stock_amt = 8000000.00,       -- 持股金额: 800万
    investment_type = 'financial',     -- 投资类型: 财务投资

    -- 企业组织
    org_form = 'limited_liability',
    governance_structure = 'board_directors',
    holding_type = 'private_holding',
    enterprise_belong = '杭州经济技术开发区',
    superior_dept = '杭州市商务局',
    company_organization = '总部-运营中心-客服中心-仓储物流',
    nation_code = 'CN',

    -- 业务经营
    main_business = '家居用品、数码产品线上销售,跨境电商业务,直播电商,社交电商平台运营',
    minor_business = '电商代运营、数字营销服务、供应链金融',
    business_mode = 'ecommerce',       -- 经营模式: 电商型
    business_start_date = '2017-04-15',
    industry_character = 'emerging',   -- 行业特征: 新兴行业
    industry_development_prospect = 'high_growth',
    area_code = '330100',              -- 地区代码: 杭州
    industry_position = '杭州电商企业50强,在家居品类细分市场占有率排名前十',

    -- 企业规模
    employee_scale = '2',              -- 员工规模: 50-300人
    assets_scale = '小型企业',
    production_capacity = 'full',      -- 生产能力: 满负荷
    enterprise_property = '民营企业',

    -- 监管评级
    loan_card_flag = b'1',
    loan_card_status = 'normal',
    loan_card_audit_date = '2024-04-05',

    -- 其他特殊
    enterprise_scale_pboc = '小型企业',
    enterprise_scale_deposit = '一般存款户',

    -- 系统追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN202401010004',
    old_last_update_sys = 'Legacy_CRM_V1',
    old_cust_id = 'OLD_COMP_004'
WHERE id = 4;

-- ============================================
-- ID=5: 南京建筑工程有限公司 - 建筑施工企业
-- ============================================
UPDATE crm_company_customer SET
    -- 财务信息 (建筑企业,资产负债率较高)
    total_assets = 120000000.00,       -- 资产总额: 1.2亿
    total_debt = 72000000.00,          -- 负债总额: 7200万 (60%)
    annual_income = 180000000.00,      -- 年收入: 1.8亿
    annual_profit = 10800000.00,       -- 年利润: 1080万 (6%)
    fin_report_type = 'annual',

    -- 股权投资
    is_stock_holder = b'0',
    hold_stock_amt = NULL,
    investment_type = NULL,

    -- 企业组织
    org_form = 'limited_liability',
    governance_structure = 'board_directors',
    holding_type = 'state_holding',    -- 控股类型: 国有控股
    enterprise_belong = '南京建工集团',
    superior_dept = '南京市国资委',
    company_organization = '总部-工程管理部-项目部-技术部-安全部',
    nation_code = 'CN',

    -- 业务经营
    main_business = '房屋建筑工程施工总承包,市政公用工程施工,装饰装修工程,钢结构工程',
    minor_business = '工程设计、工程监理、建筑材料销售',
    business_mode = 'service',
    business_start_date = '2005-09-20',
    industry_character = 'traditional',
    industry_development_prospect = 'stable_growth',
    area_code = '320100',              -- 地区代码: 南京
    industry_position = '江苏省建筑业百强企业,拥有建筑工程施工总承包一级资质',

    -- 企业规模
    employee_scale = '4',              -- 员工规模: 1000-5000人(大型)
    assets_scale = '大型企业',
    production_capacity = 'medium',
    enterprise_property = '国有企业',

    -- 监管评级
    loan_card_flag = b'1',
    loan_card_status = 'normal',
    loan_card_audit_date = '2024-02-28',

    -- 其他特殊
    enterprise_scale_pboc = '大型企业',
    enterprise_scale_deposit = '重要存款户',

    -- 系统追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN202401010005',
    old_last_update_sys = 'Legacy_CRM_V1',
    old_cust_id = 'OLD_COMP_005'
WHERE id = 5;

-- ============================================
-- ID=6: 成都餐饮管理有限公司 - 连锁餐饮企业
-- ============================================
UPDATE crm_company_customer SET
    -- 财务信息 (餐饮企业,利润率较低)
    total_assets = 18000000.00,        -- 资产总额: 1800万
    total_debt = 9000000.00,           -- 负债总额: 900万 (50%)
    annual_income = 35000000.00,       -- 年收入: 3500万
    annual_profit = 1750000.00,        -- 年利润: 175万 (5%)
    fin_report_type = 'monthly',

    -- 股权投资
    is_stock_holder = b'1',
    hold_stock_amt = 6000000.00,
    investment_type = 'industry',      -- 投资类型: 产业投资

    -- 企业组织
    org_form = 'limited_liability',
    governance_structure = 'shareholders',
    holding_type = 'private_holding',
    enterprise_belong = '成都高新区',
    superior_dept = '成都市市场监管局',
    company_organization = '总部-门店运营部-采购部-品牌部',
    nation_code = 'CN',

    -- 业务经营
    main_business = '川菜连锁餐饮服务,中央厨房配送,餐饮品牌加盟管理,预制菜研发销售',
    minor_business = '餐饮培训、厨具销售、食材供应链服务',
    business_mode = 'service',
    business_start_date = '2012-05-10',
    industry_character = 'traditional',
    industry_development_prospect = 'mature',  -- 行业发展前景: 成熟期
    area_code = '510100',              -- 地区代码: 成都
    industry_position = '成都餐饮连锁企业前20强,在川菜快餐领域拥有较高品牌知名度',

    -- 企业规模
    employee_scale = '3',              -- 员工规模: 300-1000人
    assets_scale = '小型企业',
    production_capacity = 'high',
    enterprise_property = '民营企业',

    -- 监管评级
    loan_card_flag = b'1',
    loan_card_status = 'normal',
    loan_card_audit_date = '2024-03-10',

    -- 其他特殊
    enterprise_scale_pboc = '小型企业',
    enterprise_scale_deposit = '一般存款户',

    -- 系统追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN202401010006',
    old_last_update_sys = 'Legacy_CRM_V1',
    old_cust_id = 'OLD_COMP_006'
WHERE id = 6;

-- ============================================
-- ID=7: 武汉物流有限公司 - 现代物流企业
-- ============================================
UPDATE crm_company_customer SET
    -- 财务信息 (物流企业,利润率中等)
    total_assets = 55000000.00,        -- 资产总额: 5500万
    total_debt = 33000000.00,          -- 负债总额: 3300万 (60%)
    annual_income = 80000000.00,       -- 年收入: 8000万
    annual_profit = 5600000.00,        -- 年利润: 560万 (7%)
    fin_report_type = 'quarterly',

    -- 股权投资
    is_stock_holder = b'0',
    hold_stock_amt = NULL,
    investment_type = NULL,

    -- 企业组织
    org_form = 'limited_liability',
    governance_structure = 'board_directors',
    holding_type = 'private_holding',
    enterprise_belong = '武汉东湖高新区',
    superior_dept = '武汉市交通运输局',
    company_organization = '总部-仓储中心-配送中心-客服中心',
    nation_code = 'CN',

    -- 业务经营
    main_business = '国内公路货物运输,仓储服务,供应链管理,冷链物流,城市配送服务',
    minor_business = '货运代理、包装服务、物流信息咨询',
    business_mode = 'service',
    business_start_date = '2011-07-18',
    industry_character = 'modern_service',  -- 行业特征: 现代服务业
    industry_development_prospect = 'stable_growth',
    area_code = '420100',              -- 地区代码: 武汉
    industry_position = '湖北省物流企业50强,华中地区冷链物流领军企业',

    -- 企业规模
    employee_scale = '3',              -- 员工规模: 300-1000人
    assets_scale = '中型企业',
    production_capacity = 'high',
    enterprise_property = '民营企业',

    -- 监管评级
    loan_card_flag = b'1',
    loan_card_status = 'normal',
    loan_card_audit_date = '2024-01-25',

    -- 其他特殊
    enterprise_scale_pboc = '中型企业',
    enterprise_scale_deposit = '一般存款户',

    -- 系统追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN202401010007',
    old_last_update_sys = 'Legacy_CRM_V1',
    old_cust_id = 'OLD_COMP_007'
WHERE id = 7;

-- ============================================
-- ID=8: 广州医疗器械有限公司 - 医疗器械企业
-- ============================================
UPDATE crm_company_customer SET
    -- 财务信息 (医疗器械,利润率较高)
    total_assets = 65000000.00,        -- 资产总额: 6500万
    total_debt = 26000000.00,          -- 负债总额: 2600万 (40%)
    annual_income = 90000000.00,       -- 年收入: 9000万
    annual_profit = 11700000.00,       -- 年利润: 1170万 (13%)
    fin_report_type = 'semi_annual',

    -- 股权投资
    is_stock_holder = b'1',
    hold_stock_amt = 15000000.00,
    investment_type = 'strategic',

    -- 企业组织
    org_form = 'joint_stock',          -- 组织形式: 股份有限公司
    governance_structure = 'board_supervisors',
    holding_type = 'private_holding',
    enterprise_belong = '广州生物医药产业园',
    superior_dept = '广州市药监局',
    company_organization = '总部-研发中心-生产车间-销售部-售后服务部',
    nation_code = 'CN',

    -- 业务经营
    main_business = '医疗诊断设备研发生产,手术器械制造,医疗耗材销售,医疗设备维修保养',
    minor_business = '医疗器械租赁、技术咨询、医疗信息化解决方案',
    business_mode = 'hybrid',          -- 经营模式: 混合型
    business_start_date = '2009-11-08',
    industry_character = 'high_tech',
    industry_development_prospect = 'high_growth',
    area_code = '440100',              -- 地区代码: 广州
    industry_position = '广东省医疗器械行业骨干企业,拥有二类医疗器械生产许可证',

    -- 企业规模
    employee_scale = '3',              -- 员工规模: 300-1000人
    assets_scale = '中型企业',
    production_capacity = 'full',
    enterprise_property = '股份制企业',

    -- 监管评级
    loan_card_flag = b'1',
    loan_card_status = 'normal',
    loan_card_audit_date = '2024-03-20',

    -- 其他特殊
    enterprise_scale_pboc = '中型企业',
    enterprise_scale_deposit = '重要存款户',

    -- 系统追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN202401010008',
    old_last_update_sys = 'Legacy_CRM_V1',
    old_cust_id = 'OLD_COMP_008'
WHERE id = 8;

-- ============================================
-- ID=9: 西安软件开发有限公司 - 软件企业
-- ============================================
UPDATE crm_company_customer SET
    -- 财务信息 (软件企业,高利润率)
    total_assets = 22000000.00,        -- 资产总额: 2200万
    total_debt = 8800000.00,           -- 负债总额: 880万 (40%)
    annual_income = 32000000.00,       -- 年收入: 3200万
    annual_profit = 4800000.00,        -- 年利润: 480万 (15%)
    fin_report_type = 'annual',

    -- 股权投资
    is_stock_holder = b'1',
    hold_stock_amt = 7000000.00,
    investment_type = 'venture',

    -- 企业组织
    org_form = 'limited_liability',
    governance_structure = 'board_directors',
    holding_type = 'private_holding',
    enterprise_belong = '西安高新技术产业开发区',
    superior_dept = '西安市科技局',
    company_organization = '总部-技术研发部-产品部-实施部',
    nation_code = 'CN',

    -- 业务经营
    main_business = '行业应用软件开发,企业数字化转型解决方案,智慧城市系统集成,区块链技术应用',
    minor_business = '软件测试服务、IT运维外包、技术培训',
    business_mode = 'service',
    business_start_date = '2016-02-22',
    industry_character = 'high_tech',
    industry_development_prospect = 'high_growth',
    area_code = '610100',              -- 地区代码: 西安
    industry_position = '陕西省软件企业50强,在政务信息化领域具有丰富经验',

    -- 企业规模
    employee_scale = '2',              -- 员工规模: 50-300人
    assets_scale = '小型企业',
    production_capacity = 'high',
    enterprise_property = '民营企业',

    -- 监管评级
    loan_card_flag = b'1',
    loan_card_status = 'normal',
    loan_card_audit_date = '2024-04-01',

    -- 其他特殊
    enterprise_scale_pboc = '小型企业',
    enterprise_scale_deposit = '一般存款户',

    -- 系统追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN202401010009',
    old_last_update_sys = 'Legacy_CRM_V1',
    old_cust_id = 'OLD_COMP_009'
WHERE id = 9;

-- ============================================
-- ID=10: 长沙制造业有限公司 - 机械制造企业
-- ============================================
UPDATE crm_company_customer SET
    -- 财务信息 (制造业,利润率较低)
    total_assets = 68000000.00,        -- 资产总额: 6800万
    total_debt = 40800000.00,          -- 负债总额: 4080万 (60%)
    annual_income = 95000000.00,       -- 年收入: 9500万
    annual_profit = 5700000.00,        -- 年利润: 570万 (6%)
    fin_report_type = 'quarterly',

    -- 股权投资
    is_stock_holder = b'0',
    hold_stock_amt = NULL,
    investment_type = NULL,

    -- 企业组织
    org_form = 'limited_liability',
    governance_structure = 'shareholders',
    holding_type = 'private_holding',
    enterprise_belong = '长沙经济技术开发区',
    superior_dept = '长沙市工信局',
    company_organization = '总部-机加工车间-装配车间-质检部-销售部',
    nation_code = 'CN',

    -- 业务经营
    main_business = '工程机械零部件制造,液压系统设备生产,矿山机械制造,机械设备维修改造',
    minor_business = '机械设计、技术咨询、配件销售',
    business_mode = 'manufacturing',
    business_start_date = '2007-12-05',
    industry_character = 'traditional',
    industry_development_prospect = 'stable_growth',
    area_code = '430100',              -- 地区代码: 长沙
    industry_position = '湖南省机械制造行业重点企业,为三一重工等知名企业配套',

    -- 企业规模
    employee_scale = '3',              -- 员工规模: 300-1000人
    assets_scale = '中型企业',
    production_capacity = 'medium',
    enterprise_property = '民营企业',

    -- 监管评级
    loan_card_flag = b'1',
    loan_card_status = 'normal',
    loan_card_audit_date = '2024-02-15',

    -- 其他特殊
    enterprise_scale_pboc = '中型企业',
    enterprise_scale_deposit = '一般存款户',

    -- 系统追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN202401010010',
    old_last_update_sys = 'Legacy_CRM_V1',
    old_cust_id = 'OLD_COMP_010'
WHERE id = 10;

-- ============================================
-- 执行说明
-- ============================================

-- 数据特点总结:
-- 1. 财务数据符合行业特点:
--    - 高科技/软件企业: 利润率13%-15% (ID=1,8,9)
--    - 电商企业: 利润率10% (ID=4)
--    - 贸易/物流企业: 利润率7%-8% (ID=2,7)
--    - 制造/建筑企业: 利润率5%-6% (ID=3,5,10)
--    - 餐饮企业: 利润率5% (ID=6)
--
-- 2. 资产负债率合理分布:
--    - 轻资产企业(科技/软件/电商): 40%-50%
--    - 重资产企业(制造/建筑): 60%
--
-- 3. 企业规模分布:
--    - 大型企业: 1家 (ID=5建筑,1000-5000人)
--    - 中型企业: 6家 (ID=2,3,6,7,8,10,300-1000人)
--    - 小型企业: 3家 (ID=1,4,9,50-300人)
--
-- 4. 行业分布:
--    - 高新技术: 4家 (科技、医疗器械、软件、电商)
--    - 传统行业: 6家 (贸易、制造、建筑、餐饮、物流)
--
-- 5. 地区代码与企业所在地匹配:
--    - 110000(北京) - ID=1
--    - 310000(上海) - ID=2
--    - 440300(深圳) - ID=3
--    - 330100(杭州) - ID=4
--    - 320100(南京) - ID=5
--    - 510100(成都) - ID=6
--    - 420100(武汉) - ID=7
--    - 440100(广州) - ID=8
--    - 610100(西安) - ID=9
--    - 430100(长沙) - ID=10
--
-- 6. 所有字典字段使用value值(非label值)
-- 7. ETL日期统一为2024-01-01
-- 8. 国家代码统一为CN(中国)
-- 9. 系统追溯字段完整填充
-- 10. 业务描述详细且符合企业类型

-- 执行完成后,可通过以下SQL验证:
-- SELECT id, name, total_assets, annual_income, annual_profit,
--        business_mode, employee_scale, area_code
-- FROM crm_company_customer
-- WHERE id BETWEEN 1 AND 10;
