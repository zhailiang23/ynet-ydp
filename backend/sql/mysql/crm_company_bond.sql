-- 对公客户债券信息表
-- 用于记录企业债券发行情况,包括债券名称、代码、发行金额、利率等信息

DROP TABLE IF EXISTS crm_company_bond;
CREATE TABLE crm_company_bond (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    customer_id BIGINT NOT NULL COMMENT '客户ID（关联crm_customer表）',

    -- 债券基本信息
    bond_code VARCHAR(50) NOT NULL COMMENT '债券代码',
    bond_name VARCHAR(200) NOT NULL COMMENT '债券名称',
    bond_type VARCHAR(50) NOT NULL COMMENT '债券类型（国债、地方政府债、金融债、企业债、公司债、中期票据、短期融资券、可转债等）',
    bond_subtype VARCHAR(50) COMMENT '债券子类型（一般企业债、高收益债、绿色债券、资产证券化等）',

    -- 发行信息
    issue_date DATE COMMENT '发行日期',
    issue_amount DECIMAL(18,2) NOT NULL COMMENT '发行金额（元）',
    issue_price DECIMAL(10,4) DEFAULT 100.0000 COMMENT '发行价格（面值百元）',
    par_value DECIMAL(10,2) DEFAULT 100.00 COMMENT '面值（元）',
    issue_scale BIGINT COMMENT '发行规模（张）',

    -- 期限与利率
    maturity_date DATE COMMENT '到期日期',
    term_years DECIMAL(5,2) COMMENT '债券期限（年）',
    coupon_rate DECIMAL(8,4) COMMENT '票面利率（%）',
    interest_type VARCHAR(50) DEFAULT '固定利率' COMMENT '计息方式（固定利率、浮动利率、零息、累进利率等）',
    payment_frequency VARCHAR(50) DEFAULT '年付' COMMENT '付息频率（年付、半年付、季付、月付等）',

    -- 信用评级
    credit_rating VARCHAR(20) COMMENT '债券信用评级（AAA、AA+、AA等）',
    rating_agency VARCHAR(100) COMMENT '评级机构（中诚信、联合资信、大公国际等）',
    rating_date DATE COMMENT '评级日期',

    -- 发行与交易信息
    underwriter VARCHAR(200) COMMENT '主承销商',
    listing_exchange VARCHAR(100) COMMENT '上市交易所（上交所、深交所、银行间市场等）',
    listing_date DATE COMMENT '上市日期',
    bond_status TINYINT DEFAULT 1 COMMENT '债券状态（1=正常 2=暂停交易 3=提前赎回 4=违约 5=已到期）',

    -- 担保与增信
    guarantee_type VARCHAR(50) COMMENT '担保方式（无担保、抵押、质押、保证担保等）',
    guarantor VARCHAR(200) COMMENT '担保方',
    enhancement_measures VARCHAR(500) COMMENT '增信措施',

    -- 可转债特有信息
    is_convertible TINYINT DEFAULT 0 COMMENT '是否可转债（0=否 1=是）',
    conversion_price DECIMAL(18,4) COMMENT '转股价格',
    conversion_start_date DATE COMMENT '转股起始日',
    conversion_end_date DATE COMMENT '转股截止日',
    conversion_stock_code VARCHAR(20) COMMENT '转股代码',

    -- 当前市场信息
    current_price DECIMAL(10,4) COMMENT '当前价格',
    yield_to_maturity DECIMAL(8,4) COMMENT '到期收益率（%）',
    outstanding_amount DECIMAL(18,2) COMMENT '未偿还余额（元）',
    price_update_time DATETIME COMMENT '价格更新时间',

    -- 用途与特色
    use_of_proceeds VARCHAR(500) COMMENT '募集资金用途',
    is_green_bond TINYINT DEFAULT 0 COMMENT '是否绿色债券（0=否 1=是）',
    special_clause VARCHAR(500) COMMENT '特殊条款（回售条款、赎回条款、调整票面利率条款等）',

    -- 数据来源
    data_source VARCHAR(50) DEFAULT '手工录入' COMMENT '数据来源（手工录入、Wind、中债网、中证网等）',

    -- 备注
    remark VARCHAR(500) COMMENT '备注',

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
    INDEX idx_bond_code (bond_code) COMMENT '债券代码索引',
    INDEX idx_bond_type (bond_type) COMMENT '债券类型索引',
    INDEX idx_issue_date (issue_date) COMMENT '发行日期索引',
    INDEX idx_maturity_date (maturity_date) COMMENT '到期日期索引',
    INDEX idx_tenant_id (tenant_id) COMMENT '租户ID索引',
    UNIQUE KEY uk_customer_bond (customer_id, bond_code, deleted) COMMENT '客户+债券代码唯一索引（考虑软删除）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='对公客户债券信息表';

-- 插入测试数据：为前10个客户创建1-3条债券记录

-- 客户1（张三-浦发银行）- 2条记录：金融债
INSERT INTO crm_company_bond (customer_id, bond_code, bond_name, bond_type, bond_subtype,
    issue_date, issue_amount, issue_price, par_value, issue_scale,
    maturity_date, term_years, coupon_rate, interest_type, payment_frequency,
    credit_rating, rating_agency, rating_date,
    underwriter, listing_exchange, listing_date, bond_status,
    guarantee_type, current_price, yield_to_maturity, outstanding_amount,
    price_update_time, use_of_proceeds, is_green_bond, data_source, remark, tenant_id, creator)
VALUES
(1, '190201', '19浦发银行01', '金融债', '商业银行债',
    '2019-03-15', 50000000000.00, 100.0000, 100.00, 500000000,
    '2024-03-15', 5.00, 3.8500, '固定利率', '年付',
    'AAA', '中诚信国际', '2019-03-01',
    '中信证券', '银行间市场', '2019-03-20', 1,
    '无担保', 100.5000, 3.7200, 50000000000.00,
    NOW(), '补充银行资本金，支持信贷业务发展', 0, 'Wind', '国有股份制银行优质金融债', 1, 'admin'),

(1, '210202', '21浦发绿色金融债01', '金融债', '绿色金融债',
    '2021-06-10', 30000000000.00, 100.0000, 100.00, 300000000,
    '2026-06-10', 5.00, 3.2000, '固定利率', '年付',
    'AAA', '联合资信', '2021-05-25',
    '中金公司', '银行间市场', '2021-06-15', 1,
    '无担保', 99.8000, 3.2800, 30000000000.00,
    NOW(), '支持绿色信贷项目，清洁能源和节能环保领域', 1, 'Wind', '首只绿色金融债', 1, 'admin');

-- 客户2（李四-贵州茅台）- 3条记录：公司债
INSERT INTO crm_company_bond (customer_id, bond_code, bond_name, bond_type, bond_subtype,
    issue_date, issue_amount, issue_price, par_value, issue_scale,
    maturity_date, term_years, coupon_rate, interest_type, payment_frequency,
    credit_rating, rating_agency, rating_date,
    underwriter, listing_exchange, listing_date, bond_status,
    guarantee_type, current_price, yield_to_maturity, outstanding_amount,
    price_update_time, use_of_proceeds, is_green_bond, special_clause, data_source, remark, tenant_id, creator)
VALUES
(2, '162802', '16茅台01', '公司债', '一般公司债',
    '2016-10-18', 5000000000.00, 100.0000, 100.00, 50000000,
    '2021-10-18', 5.00, 3.1500, '固定利率', '年付',
    'AAA', '中诚信国际', '2016-10-01',
    '招商证券', '上交所', '2016-10-20', 5,
    '无担保', NULL, NULL, 0.00,
    '2021-10-18 00:00:00', '偿还银行贷款，优化债务结构', 0, NULL, 'Wind', '已到期兑付', 1, 'admin'),

(2, '182801', '18茅台02', '公司债', '一般公司债',
    '2018-08-15', 8000000000.00, 100.0000, 100.00, 80000000,
    '2023-08-15', 5.00, 4.2000, '固定利率', '年付',
    'AAA', '联合资信', '2018-07-30',
    '中信证券', '上交所', '2018-08-20', 5,
    '无担保', NULL, NULL, 0.00,
    '2023-08-15 00:00:00', '补充流动资金，扩大生产规模', 0, NULL, 'Wind', '已到期兑付', 1, 'admin'),

(2, '212802', '21茅台03', '公司债', '一般公司债',
    '2021-12-08', 10000000000.00, 100.0000, 100.00, 100000000,
    '2026-12-08', 5.00, 3.5000, '固定利率', '年付',
    'AAA', '中诚信国际', '2021-11-20',
    '中金公司', '上交所', '2021-12-10', 1,
    '无担保', 101.2000, 3.3500, 10000000000.00,
    NOW(), '项目建设投资，技术改造升级', 0, '附赎回条款：发行人有权在第3年末赎回', 'Wind', '蓝筹企业优质债券', 1, 'admin');

-- 客户3（王五-美的集团）- 1条记录：可转债
INSERT INTO crm_company_bond (customer_id, bond_code, bond_name, bond_type, bond_subtype,
    issue_date, issue_amount, issue_price, par_value, issue_scale,
    maturity_date, term_years, coupon_rate, interest_type, payment_frequency,
    credit_rating, rating_agency, rating_date,
    underwriter, listing_exchange, listing_date, bond_status,
    guarantee_type, is_convertible, conversion_price, conversion_start_date, conversion_end_date, conversion_stock_code,
    current_price, yield_to_maturity, outstanding_amount,
    price_update_time, use_of_proceeds, special_clause, data_source, remark, tenant_id, creator)
VALUES
(3, '113028', '美的转债', '可转债', NULL,
    '2020-04-28', 10000000000.00, 100.0000, 100.00, 100000000,
    '2026-04-28', 6.00, 0.5000, '累进利率', '年付',
    'AA+', '中诚信证券', '2020-04-10',
    '国泰君安', '深交所', '2020-05-15', 1,
    '无担保', 1, 68.50, '2020-11-04', '2026-04-28', '000333',
    135.5000, -1.2000, 8500000000.00,
    NOW(), '智能制造项目投资，研发中心建设', '转股价下修条款：连续30个交易日股价低于转股价85%时可下修', '同花顺', '家电龙头优质可转债', 1, 'admin');

-- 客户4（赵六-兴业银行）- 2条记录：金融债+二级资本债
INSERT INTO crm_company_bond (customer_id, bond_code, bond_name, bond_type, bond_subtype,
    issue_date, issue_amount, issue_price, par_value, issue_scale,
    maturity_date, term_years, coupon_rate, interest_type, payment_frequency,
    credit_rating, rating_agency, rating_date,
    underwriter, listing_exchange, listing_date, bond_status,
    guarantee_type, current_price, yield_to_maturity, outstanding_amount,
    price_update_time, use_of_proceeds, special_clause, data_source, remark, tenant_id, creator)
VALUES
(4, '190302', '19兴业银行01', '金融债', '商业银行债',
    '2019-05-20', 40000000000.00, 100.0000, 100.00, 400000000,
    '2024-05-20', 5.00, 3.9000, '固定利率', '年付',
    'AAA', '联合资信', '2019-05-05',
    '中信建投', '银行间市场', '2019-05-25', 1,
    '无担保', 100.3000, 3.8500, 40000000000.00,
    NOW(), '支持实体经济发展，补充中长期信贷资金', 0, 'Wind', '股份制银行优质债', 1, 'admin'),

(4, '200301', '20兴业银行二级01', '金融债', '二级资本债',
    '2020-08-12', 20000000000.00, 100.0000, 100.00, 200000000,
    '2030-08-12', 10.00, 4.5000, '固定利率', '年付',
    'AA+', '中诚信国际', '2020-07-28',
    '华泰证券', '银行间市场', '2020-08-18', 1,
    '无担保', 98.5000, 4.6500, 20000000000.00,
    NOW(), '补充银行二级资本，提升资本充足率', '附减记条款：触发事件发生时全额减记', 'Wind', '二级资本工具', 1, 'admin');

-- 客户5（孙七-中芯国际）- 3条记录：公司债
INSERT INTO crm_company_bond (customer_id, bond_code, bond_name, bond_type, bond_subtype,
    issue_date, issue_amount, issue_price, par_value, issue_scale,
    maturity_date, term_years, coupon_rate, interest_type, payment_frequency,
    credit_rating, rating_agency, rating_date,
    underwriter, listing_exchange, listing_date, bond_status,
    guarantee_type, current_price, yield_to_maturity, outstanding_amount,
    price_update_time, use_of_proceeds, data_source, remark, tenant_id, creator)
VALUES
(5, '196801', '19中芯01', '公司债', '一般公司债',
    '2019-11-25', 3000000000.00, 100.0000, 100.00, 30000000,
    '2024-11-25', 5.00, 4.1000, '固定利率', '年付',
    'AA+', '大公国际', '2019-11-10',
    '中金公司', '上交所', '2019-11-30', 1,
    '无担保', 99.8000, 4.1500, 3000000000.00,
    NOW(), '芯片产线建设，扩大产能', 'Wind', '科创企业债券', 1, 'admin'),

(5, '206802', '20中芯02', '公司债', '一般公司债',
    '2020-09-18', 5000000000.00, 100.0000, 100.00, 50000000,
    '2025-09-18', 5.00, 3.8000, '固定利率', '年付',
    'AA+', '联合资信', '2020-09-01',
    '海通证券', '上交所', '2020-09-23', 1,
    '无担保', 100.5000, 3.7000, 5000000000.00,
    NOW(), '技术研发投入，先进制程开发', 'Wind', '半导体行业龙头债', 1, 'admin'),

(5, '216803', '21中芯03', '公司债', '绿色公司债',
    '2021-07-15', 4000000000.00, 100.0000, 100.00, 40000000,
    '2026-07-15', 5.00, 3.5000, '固定利率', '年付',
    'AA+', '中诚信证券', '2021-06-30',
    '中信证券', '上交所', '2021-07-20', 1,
    '无担保', 101.0000, 3.4000, 4000000000.00,
    NOW(), '绿色节能技术改造，清洁生产升级', 'Wind', '绿色债券', 1, 'admin');

-- 客户6（周八-恒瑞医药）- 1条记录：公司债
INSERT INTO crm_company_bond (customer_id, bond_code, bond_name, bond_type, bond_subtype,
    issue_date, issue_amount, issue_price, par_value, issue_scale,
    maturity_date, term_years, coupon_rate, interest_type, payment_frequency,
    credit_rating, rating_agency, rating_date,
    underwriter, listing_exchange, listing_date, bond_status,
    guarantee_type, current_price, yield_to_maturity, outstanding_amount,
    price_update_time, use_of_proceeds, data_source, remark, tenant_id, creator)
VALUES
(6, '192701', '19恒瑞01', '公司债', '一般公司债',
    '2019-09-10', 2000000000.00, 100.0000, 100.00, 20000000,
    '2024-09-10', 5.00, 3.6500, '固定利率', '年付',
    'AAA', '中诚信国际', '2019-08-25',
    '中金公司', '上交所', '2019-09-15', 1,
    '无担保', 100.2000, 3.6000, 2000000000.00,
    NOW(), '创新药研发，临床试验投入', 'Wind', '医药龙头优质债', 1, 'admin');

-- 客户7（吴九-工商银行）- 2条记录：金融债
INSERT INTO crm_company_bond (customer_id, bond_code, bond_name, bond_type, bond_subtype,
    issue_date, issue_amount, issue_price, par_value, issue_scale,
    maturity_date, term_years, coupon_rate, interest_type, payment_frequency,
    credit_rating, rating_agency, rating_date,
    underwriter, listing_exchange, listing_date, bond_status,
    guarantee_type, current_price, yield_to_maturity, outstanding_amount,
    price_update_time, use_of_proceeds, is_green_bond, data_source, remark, tenant_id, creator)
VALUES
(7, '190101', '19工商银行01', '金融债', '商业银行债',
    '2019-02-20', 80000000000.00, 100.0000, 100.00, 800000000,
    '2024-02-20', 5.00, 3.7500, '固定利率', '年付',
    'AAA', '中诚信国际', '2019-02-01',
    '中金公司', '银行间市场', '2019-02-25', 1,
    '无担保', 100.4000, 3.7000, 80000000000.00,
    NOW(), '支持中小微企业贷款，普惠金融业务', 0, 'Wind', '大型国有银行金融债', 1, 'admin'),

(7, '200102', '20工商银行绿债01', '金融债', '绿色金融债',
    '2020-11-18', 50000000000.00, 100.0000, 100.00, 500000000,
    '2025-11-18', 5.00, 3.3000, '固定利率', '年付',
    'AAA', '联合资信', '2020-11-01',
    '中信证券', '银行间市场', '2020-11-23', 1,
    '无担保', 99.9000, 3.3200, 50000000000.00,
    NOW(), '支持绿色信贷，清洁能源和环保项目融资', 1, 'Wind', '国有大行绿色债', 1, 'admin');

-- 客户8（郑十-伊利股份）- 3条记录：中期票据+短期融资券
INSERT INTO crm_company_bond (customer_id, bond_code, bond_name, bond_type, bond_subtype,
    issue_date, issue_amount, issue_price, par_value, issue_scale,
    maturity_date, term_years, coupon_rate, interest_type, payment_frequency,
    credit_rating, rating_agency, rating_date,
    underwriter, listing_exchange, listing_date, bond_status,
    guarantee_type, current_price, yield_to_maturity, outstanding_amount,
    price_update_time, use_of_proceeds, data_source, remark, tenant_id, creator)
VALUES
(8, 'MTN001', '19伊利MTN001', '中期票据', NULL,
    '2019-06-12', 3000000000.00, 100.0000, 100.00, 30000000,
    '2022-06-12', 3.00, 3.9500, '固定利率', '年付',
    'AAA', '中诚信国际', '2019-05-28',
    '招商银行', '银行间市场', '2019-06-15', 5,
    '无担保', NULL, NULL, 0.00,
    '2022-06-12 00:00:00', '补充流动资金，奶源基地建设', 'Wind', '已到期兑付', 1, 'admin'),

(8, 'MTN002', '20伊利MTN002', '中期票据', NULL,
    '2020-10-20', 4000000000.00, 100.0000, 100.00, 40000000,
    '2023-10-20', 3.00, 3.6000, '固定利率', '年付',
    'AAA', '联合资信', '2020-10-05',
    '中国银行', '银行间市场', '2020-10-23', 5,
    '无担保', NULL, NULL, 0.00,
    '2023-10-20 00:00:00', '偿还银行贷款，降低融资成本', 'Wind', '已到期兑付', 1, 'admin'),

(8, 'CP001', '24伊利CP001', '短期融资券', NULL,
    '2024-03-15', 2000000000.00, 100.0000, 100.00, 20000000,
    '2025-03-15', 1.00, 2.8500, '固定利率', '到期一次付息',
    'A-1', '大公国际', '2024-03-01',
    '工商银行', '银行间市场', '2024-03-18', 1,
    '无担保', 100.1000, 2.8000, 2000000000.00,
    NOW(), '补充短期流动资金，原材料采购', 'Wind', '短期融资工具', 1, 'admin');

-- 客户9（钱十一-农业银行）- 2条记录：金融债
INSERT INTO crm_company_bond (customer_id, bond_code, bond_name, bond_type, bond_subtype,
    issue_date, issue_amount, issue_price, par_value, issue_scale,
    maturity_date, term_years, coupon_rate, interest_type, payment_frequency,
    credit_rating, rating_agency, rating_date,
    underwriter, listing_exchange, listing_date, bond_status,
    guarantee_type, current_price, yield_to_maturity, outstanding_amount,
    price_update_time, use_of_proceeds, data_source, remark, tenant_id, creator)
VALUES
(9, '190401', '19农业银行01', '金融债', '商业银行债',
    '2019-07-10', 60000000000.00, 100.0000, 100.00, 600000000,
    '2024-07-10', 5.00, 3.8000, '固定利率', '年付',
    'AAA', '中诚信国际', '2019-06-25',
    '中金公司', '银行间市场', '2019-07-15', 1,
    '无担保', 100.2000, 3.7800, 60000000000.00,
    NOW(), '支持三农金融服务，县域经济发展', 'Wind', '大型国有银行', 1, 'admin'),

(9, '210402', '21农业银行绿债01', '金融债', '绿色金融债',
    '2021-09-08', 35000000000.00, 100.0000, 100.00, 350000000,
    '2026-09-08', 5.00, 3.4000, '固定利率', '年付',
    'AAA', '联合资信', '2021-08-20',
    '中信证券', '银行间市场', '2021-09-13', 1,
    '无担保', 99.7000, 3.4500, 35000000000.00,
    NOW(), '绿色农业项目贷款，生态环境保护融资', 'Wind', '三农绿色金融债', 1, 'admin');

-- 客户10（冯十二-中国石油）- 1条记录：企业债
INSERT INTO crm_company_bond (customer_id, bond_code, bond_name, bond_type, bond_subtype,
    issue_date, issue_amount, issue_price, par_value, issue_scale,
    maturity_date, term_years, coupon_rate, interest_type, payment_frequency,
    credit_rating, rating_agency, rating_date,
    underwriter, listing_exchange, listing_date, bond_status,
    guarantee_type, current_price, yield_to_maturity, outstanding_amount,
    price_update_time, use_of_proceeds, data_source, remark, tenant_id, creator)
VALUES
(10, '188801', '18中石油01', '企业债', '一般企业债',
    '2018-12-05', 10000000000.00, 100.0000, 100.00, 100000000,
    '2025-12-05', 7.00, 4.3000, '固定利率', '年付',
    'AAA', '中诚信国际', '2018-11-20',
    '中金公司', '银行间市场', '2018-12-10', 1,
    '无担保', 101.5000, 4.1000, 10000000000.00,
    NOW(), '油气田开发建设，炼化一体化项目', 'Wind', '央企优质债券', 1, 'admin');
