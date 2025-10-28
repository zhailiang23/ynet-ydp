-- CRM零售客户测试数据生成脚本
-- 为所有10条零售客户记录填充新增的41个字段
-- 执行前请确保数据库已选择

USE `ruoyi-vue-pro`;

-- ============================================
-- 记录1: 小张 (男性)
-- ============================================
UPDATE crm_retail_customer SET
    -- 个人基本信息
    used_name = '张小明',
    surname = '张',
    pinyin_name = 'Zhang Xiaoming',
    pinyin_abbr = 'ZXM',
    person_title = 'mr',
    birth_locale = '江苏省南京市',
    age_range = '3',
    citizenship = '中国公民',
    household_type = 'non_agricultural',
    political_status = 'party_member',
    health_status = 'healthy',

    -- 教育信息
    graduate_school = '南京大学',
    major = '计算机科学与技术',
    graduation_date = '2010-06-30',

    -- 工作信息
    career_status = 'employed',
    career_start_date = '2010-07-01',
    employer_type = 'state_owned',
    technical_title = '高级工程师',
    annual_income_range = '20-30万',
    tax_payment = '按时足额缴纳',
    salary_account_bank = '中国工商银行',
    resume = '毕业于南京大学计算机系,在国有企业从事软件开发工作10余年',

    -- 资产与投资信息
    fund_holdings = 50000.00,
    total_investment = 200000.00,
    investment_nature = '2',
    stock_holdings = 150000.00,

    -- 客户关系信息
    person_customer_type = '优质客户',
    person_conduct_eval = '信用良好',
    customer_bank_relation = '长期客户',
    company_relation_degree = '高',
    individual_type = '个人',

    -- 登记信息
    start_date = '2020-01-15',
    registration_type = 'initial',
    registration_no = 'REG2020010001',
    registration_start_date = '2020-01-15',
    registration_end_date = '2030-01-14',

    -- 数据迁移追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN20240101001',
    old_cust_id = 'OLD_CUST_001',
    old_last_update_sys = 'Legacy_CRM_V1'
WHERE id = 1;

-- ============================================
-- 记录2: Leo (男性)
-- ============================================
UPDATE crm_retail_customer SET
    -- 个人基本信息
    used_name = 'Leonard Wang',
    surname = '王',
    pinyin_name = 'Wang Lei',
    pinyin_abbr = 'WL',
    person_title = 'mr',
    birth_locale = '上海市',
    age_range = '2',
    citizenship = '中国公民',
    household_type = 'non_agricultural',
    political_status = 'league_member',
    health_status = 'good',

    -- 教育信息
    graduate_school = '复旦大学',
    major = '金融学',
    graduation_date = '2018-06-30',

    -- 工作信息
    career_status = 'employed',
    career_start_date = '2018-08-01',
    employer_type = 'foreign',
    technical_title = NULL,
    annual_income_range = '30-50万',
    tax_payment = '按时足额缴纳',
    salary_account_bank = '中国建设银行',
    resume = '复旦大学金融学硕士,在外资企业从事投资分析工作',

    -- 资产与投资信息
    fund_holdings = 100000.00,
    total_investment = 500000.00,
    investment_nature = '3',
    stock_holdings = 400000.00,

    -- 客户关系信息
    person_customer_type = '高端客户',
    person_conduct_eval = '信用优秀',
    customer_bank_relation = '核心客户',
    company_relation_degree = '很高',
    individual_type = '个人',

    -- 登记信息
    start_date = '2019-03-20',
    registration_type = 'initial',
    registration_no = 'REG2019030002',
    registration_start_date = '2019-03-20',
    registration_end_date = '2029-03-19',

    -- 数据迁移追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN20240101002',
    old_cust_id = 'OLD_CUST_002',
    old_last_update_sys = 'Legacy_CRM_V1'
WHERE id = 2;

-- ============================================
-- 记录3: 小王 (女性)
-- ============================================
UPDATE crm_retail_customer SET
    -- 个人基本信息
    used_name = NULL,
    surname = '王',
    pinyin_name = 'Wang Fang',
    pinyin_abbr = 'WF',
    person_title = 'ms',
    birth_locale = '北京市',
    age_range = '4',
    citizenship = '中国公民',
    household_type = 'non_agricultural',
    political_status = 'mass',
    health_status = 'healthy',

    -- 教育信息
    graduate_school = '北京师范大学',
    major = '教育学',
    graduation_date = '2005-06-30',

    -- 工作信息
    career_status = 'employed',
    career_start_date = '2005-09-01',
    employer_type = 'public_institution',
    technical_title = '高级教师',
    annual_income_range = '15-20万',
    tax_payment = '按时足额缴纳',
    salary_account_bank = '中国农业银行',
    resume = '北师大教育学硕士,从事中学教育工作15年',

    -- 资产与投资信息
    fund_holdings = 30000.00,
    total_investment = 100000.00,
    investment_nature = '1',
    stock_holdings = 50000.00,

    -- 客户关系信息
    person_customer_type = '普通客户',
    person_conduct_eval = '信用良好',
    customer_bank_relation = '稳定客户',
    company_relation_degree = '中',
    individual_type = '个人',

    -- 登记信息
    start_date = '2018-05-10',
    registration_type = 'initial',
    registration_no = 'REG2018050003',
    registration_start_date = '2018-05-10',
    registration_end_date = '2028-05-09',

    -- 数据迁移追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN20240101003',
    old_cust_id = 'OLD_CUST_003',
    old_last_update_sys = 'Legacy_CRM_V1'
WHERE id = 3;

-- ============================================
-- 记录4: 赵总 (男性)
-- ============================================
UPDATE crm_retail_customer SET
    -- 个人基本信息
    used_name = NULL,
    surname = '赵',
    pinyin_name = 'Zhao Jianguo',
    pinyin_abbr = 'ZJG',
    person_title = 'mr',
    birth_locale = '浙江省杭州市',
    age_range = '5',
    citizenship = '中国公民',
    household_type = 'non_agricultural',
    political_status = 'party_member',
    health_status = 'good',

    -- 教育信息
    graduate_school = '浙江大学',
    major = '工商管理',
    graduation_date = '1995-06-30',

    -- 工作信息
    career_status = 'employed',
    career_start_date = '1995-08-01',
    employer_type = 'private',
    technical_title = NULL,
    annual_income_range = '100万以上',
    tax_payment = '按时足额缴纳',
    salary_account_bank = '招商银行',
    resume = '浙大MBA,创办多家企业,从事实业投资25年',

    -- 资产与投资信息
    fund_holdings = 500000.00,
    total_investment = 5000000.00,
    investment_nature = '4',
    stock_holdings = 3000000.00,

    -- 客户关系信息
    person_customer_type = 'VIP客户',
    person_conduct_eval = '信用优秀',
    customer_bank_relation = '战略客户',
    company_relation_degree = '极高',
    individual_type = '个体工商户',

    -- 登记信息
    start_date = '2015-01-08',
    registration_type = 'initial',
    registration_no = 'REG2015010004',
    registration_start_date = '2015-01-08',
    registration_end_date = '2025-01-07',

    -- 数据迁移追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN20240101004',
    old_cust_id = 'OLD_CUST_004',
    old_last_update_sys = 'Legacy_CRM_V1'
WHERE id = 4;

-- ============================================
-- 记录5: 小孙 (女性)
-- ============================================
UPDATE crm_retail_customer SET
    -- 个人基本信息
    used_name = NULL,
    surname = '孙',
    pinyin_name = 'Sun Li',
    pinyin_abbr = 'SL',
    person_title = 'ms',
    birth_locale = '广东省深圳市',
    age_range = '2',
    citizenship = '中国公民',
    household_type = 'non_agricultural',
    political_status = 'league_member',
    health_status = 'healthy',

    -- 教育信息
    graduate_school = '深圳大学',
    major = '电子商务',
    graduation_date = '2019-06-30',

    -- 工作信息
    career_status = 'employed',
    career_start_date = '2019-07-15',
    employer_type = 'private',
    technical_title = NULL,
    annual_income_range = '10-15万',
    tax_payment = '按时足额缴纳',
    salary_account_bank = '平安银行',
    resume = '深圳大学电子商务专业,在互联网公司从事运营工作',

    -- 资产与投资信息
    fund_holdings = 20000.00,
    total_investment = 50000.00,
    investment_nature = '2',
    stock_holdings = 30000.00,

    -- 客户关系信息
    person_customer_type = '潜力客户',
    person_conduct_eval = '信用良好',
    customer_bank_relation = '新客户',
    company_relation_degree = '低',
    individual_type = '个人',

    -- 登记信息
    start_date = '2021-06-18',
    registration_type = 'initial',
    registration_no = 'REG2021060005',
    registration_start_date = '2021-06-18',
    registration_end_date = '2031-06-17',

    -- 数据迁移追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN20240101005',
    old_cust_id = 'OLD_CUST_005',
    old_last_update_sys = 'Legacy_CRM_V1'
WHERE id = 5;

-- ============================================
-- 记录6: Linda (女性)
-- ============================================
UPDATE crm_retail_customer SET
    -- 个人基本信息
    used_name = 'Linda Chen',
    surname = '陈',
    pinyin_name = 'Chen Linda',
    pinyin_abbr = 'CL',
    person_title = 'ms',
    birth_locale = '香港特别行政区',
    age_range = '3',
    citizenship = '中国公民',
    household_type = 'non_agricultural',
    political_status = 'mass',
    health_status = 'good',

    -- 教育信息
    graduate_school = '香港大学',
    major = '国际贸易',
    graduation_date = '2012-06-30',

    -- 工作信息
    career_status = 'employed',
    career_start_date = '2012-09-01',
    employer_type = 'foreign',
    technical_title = NULL,
    annual_income_range = '50-80万',
    tax_payment = '按时足额缴纳',
    salary_account_bank = '汇丰银行',
    resume = '香港大学国际贸易硕士,从事跨境贸易管理工作',

    -- 资产与投资信息
    fund_holdings = 200000.00,
    total_investment = 800000.00,
    investment_nature = '3',
    stock_holdings = 600000.00,

    -- 客户关系信息
    person_customer_type = '高端客户',
    person_conduct_eval = '信用优秀',
    customer_bank_relation = '核心客户',
    company_relation_degree = '高',
    individual_type = '个人',

    -- 登记信息
    start_date = '2017-08-22',
    registration_type = 'initial',
    registration_no = 'REG2017080006',
    registration_start_date = '2017-08-22',
    registration_end_date = '2027-08-21',

    -- 数据迁移追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN20240101006',
    old_cust_id = 'OLD_CUST_006',
    old_last_update_sys = 'Legacy_CRM_V1'
WHERE id = 6;

-- ============================================
-- 记录7: 小吴 (男性)
-- ============================================
UPDATE crm_retail_customer SET
    -- 个人基本信息
    used_name = NULL,
    surname = '吴',
    pinyin_name = 'Wu Qiang',
    pinyin_abbr = 'WQ',
    person_title = 'mr',
    birth_locale = '四川省成都市',
    age_range = '3',
    citizenship = '中国公民',
    household_type = 'agricultural',
    political_status = 'mass',
    health_status = 'healthy',

    -- 教育信息
    graduate_school = '四川大学',
    major = '机械工程',
    graduation_date = '2013-06-30',

    -- 工作信息
    career_status = 'employed',
    career_start_date = '2013-07-10',
    employer_type = 'private',
    technical_title = '工程师',
    annual_income_range = '12-18万',
    tax_payment = '按时足额缴纳',
    salary_account_bank = '中国银行',
    resume = '四川大学机械工程专业,在制造企业从事设计工作',

    -- 资产与投资信息
    fund_holdings = 15000.00,
    total_investment = 80000.00,
    investment_nature = '1',
    stock_holdings = 40000.00,

    -- 客户关系信息
    person_customer_type = '普通客户',
    person_conduct_eval = '信用良好',
    customer_bank_relation = '稳定客户',
    company_relation_degree = '中',
    individual_type = '个人',

    -- 登记信息
    start_date = '2019-11-05',
    registration_type = 'initial',
    registration_no = 'REG2019110007',
    registration_start_date = '2019-11-05',
    registration_end_date = '2029-11-04',

    -- 数据迁移追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN20240101007',
    old_cust_id = 'OLD_CUST_007',
    old_last_update_sys = 'Legacy_CRM_V1'
WHERE id = 7;

-- ============================================
-- 记录8: 郑总 (男性)
-- ============================================
UPDATE crm_retail_customer SET
    -- 个人基本信息
    used_name = NULL,
    surname = '郑',
    pinyin_name = 'Zheng Minghui',
    pinyin_abbr = 'ZMH',
    person_title = 'mr',
    birth_locale = '福建省厦门市',
    age_range = '5',
    citizenship = '中国公民',
    household_type = 'non_agricultural',
    political_status = 'party_member',
    health_status = 'healthy',

    -- 教育信息
    graduate_school = '厦门大学',
    major = '经济学',
    graduation_date = '2000-06-30',

    -- 工作信息
    career_status = 'employed',
    career_start_date = '2000-08-01',
    employer_type = 'private',
    technical_title = NULL,
    annual_income_range = '80-100万',
    tax_payment = '按时足额缴纳',
    salary_account_bank = '兴业银行',
    resume = '厦门大学经济学博士,创办贸易公司,从事进出口业务20年',

    -- 资产与投资信息
    fund_holdings = 300000.00,
    total_investment = 3000000.00,
    investment_nature = '3',
    stock_holdings = 2000000.00,

    -- 客户关系信息
    person_customer_type = 'VIP客户',
    person_conduct_eval = '信用优秀',
    customer_bank_relation = '重要客户',
    company_relation_degree = '很高',
    individual_type = '个体工商户',

    -- 登记信息
    start_date = '2016-04-12',
    registration_type = 'initial',
    registration_no = 'REG2016040008',
    registration_start_date = '2016-04-12',
    registration_end_date = '2026-04-11',

    -- 数据迁移追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN20240101008',
    old_cust_id = 'OLD_CUST_008',
    old_last_update_sys = 'Legacy_CRM_V1'
WHERE id = 8;

-- ============================================
-- 记录9: Amy (女性)
-- ============================================
UPDATE crm_retail_customer SET
    -- 个人基本信息
    used_name = 'Amy Liu',
    surname = '刘',
    pinyin_name = 'Liu Yuting',
    pinyin_abbr = 'LYT',
    person_title = 'miss',
    birth_locale = '辽宁省大连市',
    age_range = '1',
    citizenship = '中国公民',
    household_type = 'non_agricultural',
    political_status = 'league_member',
    health_status = 'healthy',

    -- 教育信息
    graduate_school = '大连理工大学',
    major = '软件工程',
    graduation_date = '2022-06-30',

    -- 工作信息
    career_status = 'employed',
    career_start_date = '2022-07-20',
    employer_type = 'private',
    technical_title = NULL,
    annual_income_range = '8-12万',
    tax_payment = '按时足额缴纳',
    salary_account_bank = '交通银行',
    resume = '大连理工大学软件工程本科,在互联网公司从事前端开发',

    -- 资产与投资信息
    fund_holdings = 5000.00,
    total_investment = 20000.00,
    investment_nature = '2',
    stock_holdings = 15000.00,

    -- 客户关系信息
    person_customer_type = '潜力客户',
    person_conduct_eval = '信用良好',
    customer_bank_relation = '新客户',
    company_relation_degree = '低',
    individual_type = '个人',

    -- 登记信息
    start_date = '2023-02-14',
    registration_type = 'initial',
    registration_no = 'REG2023020009',
    registration_start_date = '2023-02-14',
    registration_end_date = '2033-02-13',

    -- 数据迁移追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN20240101009',
    old_cust_id = 'OLD_CUST_009',
    old_last_update_sys = 'Legacy_CRM_V1'
WHERE id = 9;

-- ============================================
-- 记录10: 小冯 (男性)
-- ============================================
UPDATE crm_retail_customer SET
    -- 个人基本信息
    used_name = NULL,
    surname = '冯',
    pinyin_name = 'Feng Hao',
    pinyin_abbr = 'FH',
    person_title = 'mr',
    birth_locale = '湖南省长沙市',
    age_range = '4',
    citizenship = '中国公民',
    household_type = 'non_agricultural',
    political_status = 'party_member',
    health_status = 'good',

    -- 教育信息
    graduate_school = '湖南大学',
    major = '土木工程',
    graduation_date = '2008-06-30',

    -- 工作信息
    career_status = 'employed',
    career_start_date = '2008-08-01',
    employer_type = 'state_owned',
    technical_title = '高级工程师',
    annual_income_range = '18-25万',
    tax_payment = '按时足额缴纳',
    salary_account_bank = '中国建设银行',
    resume = '湖南大学土木工程硕士,在建筑设计院从事结构设计工作',

    -- 资产与投资信息
    fund_holdings = 40000.00,
    total_investment = 150000.00,
    investment_nature = '1',
    stock_holdings = 80000.00,

    -- 客户关系信息
    person_customer_type = '优质客户',
    person_conduct_eval = '信用良好',
    customer_bank_relation = '长期客户',
    company_relation_degree = '中',
    individual_type = '个人',

    -- 登记信息
    start_date = '2018-09-25',
    registration_type = 'initial',
    registration_no = 'REG2018090010',
    registration_start_date = '2018-09-25',
    registration_end_date = '2028-09-24',

    -- 数据迁移追溯
    etl_date = '2024-01-01',
    old_tx_seq_no = 'TXN20240101010',
    old_cust_id = 'OLD_CUST_010',
    old_last_update_sys = 'Legacy_CRM_V1'
WHERE id = 10;

-- ============================================
-- 说明
-- ============================================

-- 1. 所有新增字段都已填充合理的测试数据
-- 2. 数据基于每个客户的性别、昵称等现有信息生成
-- 3. 字典字段使用了正确的字典值(如person_title, household_type, political_status等)
-- 4. 金额字段使用了DECIMAL类型的值
-- 5. 日期字段使用了标准的日期格式
-- 6. ETL追溯字段为所有记录提供了数据来源信息

-- 执行完成后,可以在前端页面查看这些测试数据的展示效果
