-- 对公客户股票发行人信息表
-- 用于记录上市公司的股票发行情况,包括股票代码、上市地、发行信息等

DROP TABLE IF EXISTS crm_company_stock;
CREATE TABLE crm_company_stock (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '主键ID',
    customer_id BIGINT NOT NULL COMMENT '客户ID（关联crm_customer表）',

    -- 股票基本信息
    stock_code VARCHAR(20) NOT NULL COMMENT '股票代码',
    stock_name VARCHAR(100) NOT NULL COMMENT '股票简称',
    stock_type VARCHAR(50) DEFAULT 'A股' COMMENT '股票类型（A股、B股、H股、港股、美股、科创板等）',

    -- 上市信息
    listing_exchange VARCHAR(50) NOT NULL COMMENT '上市交易所（上交所、深交所、港交所、纳斯达克、纽交所等）',
    listing_date DATE COMMENT '上市日期',
    listing_status TINYINT DEFAULT 1 COMMENT '上市状态（1=正常上市 2=暂停上市 3=终止上市 4=退市）',

    -- 发行信息
    issue_price DECIMAL(18,4) COMMENT '发行价格',
    issue_quantity BIGINT COMMENT '发行数量（股）',
    issue_amount DECIMAL(18,2) COMMENT '发行总额（元）',
    issue_pe_ratio DECIMAL(10,2) COMMENT '发行市盈率',

    -- 当前市场信息
    current_price DECIMAL(18,4) COMMENT '当前股价',
    market_value DECIMAL(18,2) COMMENT '总市值（元）',
    circulating_market_value DECIMAL(18,2) COMMENT '流通市值（元）',
    total_shares BIGINT COMMENT '总股本（股）',
    circulating_shares BIGINT COMMENT '流通股本（股）',

    -- 行业分类
    industry_category VARCHAR(100) COMMENT '行业分类（申万行业、证监会行业等）',
    sector VARCHAR(100) COMMENT '所属板块',

    -- 监管与评级
    is_st TINYINT DEFAULT 0 COMMENT '是否ST股（0=否 1=是）',
    is_star TINYINT DEFAULT 0 COMMENT '是否*ST股（0=否 1=是）',
    stock_rating VARCHAR(50) COMMENT '股票评级（买入、增持、中性、减持、卖出）',

    -- 更新信息
    price_update_time DATETIME COMMENT '价格更新时间',
    data_source VARCHAR(50) DEFAULT '手工录入' COMMENT '数据来源（手工录入、Wind、同花顺、东方财富等）',

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
    INDEX idx_stock_code (stock_code) COMMENT '股票代码索引',
    INDEX idx_listing_exchange (listing_exchange) COMMENT '交易所索引',
    INDEX idx_tenant_id (tenant_id) COMMENT '租户ID索引',
    UNIQUE KEY uk_customer_stock (customer_id, stock_code, deleted) COMMENT '客户+股票代码唯一索引（考虑软删除）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='对公客户股票发行人信息表';

-- 插入测试数据：为前10个客户创建1-3条股票发行记录

-- 客户1（张三）- 2条记录
INSERT INTO crm_company_stock (customer_id, stock_code, stock_name, stock_type, listing_exchange, listing_date, listing_status,
    issue_price, issue_quantity, issue_amount, issue_pe_ratio,
    current_price, market_value, circulating_market_value, total_shares, circulating_shares,
    industry_category, sector, is_st, is_star, stock_rating, price_update_time, data_source, remark, tenant_id, creator)
VALUES
(1, '600000', '浦发银行', 'A股', '上交所', '1999-11-10', 1,
    9.50, 2800000000, 26600000000.00, 15.50,
    8.35, 245000000000.00, 240000000000.00, 29352481740, 28800000000,
    '银行', '金融板块', 0, 0, '增持', NOW(), 'Wind', '全国性股份制商业银行', 1, 'admin'),

(1, '601318', '中国平安', 'A股', '上交所', '2007-03-01', 1,
    33.80, 1800000000, 60840000000.00, 28.50,
    55.60, 1015000000000.00, 980000000000.00, 18280356126, 17650000000,
    '保险', '金融板块', 0, 0, '买入', NOW(), '同花顺', '综合性金融服务集团', 1, 'admin');

-- 客户2（李四）- 3条记录
INSERT INTO crm_company_stock (customer_id, stock_code, stock_name, stock_type, listing_exchange, listing_date, listing_status,
    issue_price, issue_quantity, issue_amount, issue_pe_ratio,
    current_price, market_value, circulating_market_value, total_shares, circulating_shares,
    industry_category, sector, is_st, is_star, stock_rating, price_update_time, data_source, remark, tenant_id, creator)
VALUES
(2, '600519', '贵州茅台', 'A股', '上交所', '2001-08-27', 1,
    31.39, 250000000, 7847500000.00, 35.20,
    1780.00, 2234000000000.00, 2200000000000.00, 1256197800, 1235000000,
    '白酒', '消费板块', 0, 0, '买入', NOW(), 'Wind', '高端白酒龙头企业', 1, 'admin'),

(2, '000858', '五粮液', 'A股', '深交所', '1998-04-27', 1,
    8.88, 320000000, 2841600000.00, 22.50,
    165.50, 641000000000.00, 630000000000.00, 3868710699, 3800000000,
    '白酒', '消费板块', 0, 0, '增持', NOW(), '东方财富', '浓香型白酒代表企业', 1, 'admin'),

(2, '600036', '招商银行', 'A股', '上交所', '2002-04-09', 1,
    8.95, 2500000000, 22375000000.00, 18.50,
    38.50, 965000000000.00, 950000000000.00, 25219946125, 24800000000,
    '银行', '金融板块', 0, 0, '买入', NOW(), 'Wind', '零售银行标杆企业', 1, 'admin');

-- 客户3（王五）- 1条记录
INSERT INTO crm_company_stock (customer_id, stock_code, stock_name, stock_type, listing_exchange, listing_date, listing_status,
    issue_price, issue_quantity, issue_amount, issue_pe_ratio,
    current_price, market_value, circulating_market_value, total_shares, circulating_shares,
    industry_category, sector, is_st, is_star, stock_rating, price_update_time, data_source, remark, tenant_id, creator)
VALUES
(3, '000333', '美的集团', 'A股', '深交所', '2013-09-18', 1,
    23.00, 660000000, 15180000000.00, 20.50,
    68.50, 480000000000.00, 470000000000.00, 7004512000, 6850000000,
    '家用电器', '消费板块', 0, 0, '增持', NOW(), '同花顺', '全球家电制造龙头', 1, 'admin');

-- 客户4（赵六）- 2条记录（包含港股）
INSERT INTO crm_company_stock (customer_id, stock_code, stock_name, stock_type, listing_exchange, listing_date, listing_status,
    issue_price, issue_quantity, issue_amount, issue_pe_ratio,
    current_price, market_value, circulating_market_value, total_shares, circulating_shares,
    industry_category, sector, is_st, is_star, stock_rating, price_update_time, data_source, remark, tenant_id, creator)
VALUES
(4, '601166', '兴业银行', 'A股', '上交所', '2007-02-05', 1,
    15.98, 2000000000, 31960000000.00, 25.50,
    18.50, 460000000000.00, 450000000000.00, 24862420000, 24300000000,
    '银行', '金融板块', 0, 0, '中性', NOW(), 'Wind', '绿色金融特色银行', 1, 'admin'),

(4, '00700', '腾讯控股', 'H股', '港交所', '2004-06-16', 1,
    3.70, 4216230000, 15600051000.00, 45.50,
    350.00, 3360000000000.00, 3300000000000.00, 9600000000, 9400000000,
    '互联网', '科技板块', 0, 0, '买入', NOW(), '同花顺', '中国互联网巨头', 1, 'admin');

-- 客户5（孙七）- 3条记录（包含科创板）
INSERT INTO crm_company_stock (customer_id, stock_code, stock_name, stock_type, listing_exchange, listing_date, listing_status,
    issue_price, issue_quantity, issue_amount, issue_pe_ratio,
    current_price, market_value, circulating_market_value, total_shares, circulating_shares,
    industry_category, sector, is_st, is_star, stock_rating, price_update_time, data_source, remark, tenant_id, creator)
VALUES
(5, '688981', '中芯国际', '科创板', '上交所', '2020-07-16', 1,
    27.46, 1682997813, 46208765699.98, 120.50,
    55.80, 460000000000.00, 440000000000.00, 8252672330, 7900000000,
    '半导体', '科技板块', 0, 0, '买入', NOW(), '东方财富', '中国芯片制造龙头', 1, 'admin'),

(5, '600900', '长江电力', 'A股', '上交所', '2003-11-18', 1,
    5.28, 3000000000, 15840000000.00, 18.50,
    24.50, 550000000000.00, 540000000000.00, 22470877859, 22000000000,
    '电力', '公用事业板块', 0, 0, '增持', NOW(), 'Wind', '中国最大水电上市公司', 1, 'admin'),

(5, '000001', '平安银行', 'A股', '深交所', '1991-04-03', 1,
    38.00, 1945676697, 73935715286.00, 35.50,
    13.50, 262000000000.00, 258000000000.00, 19405918198, 19100000000,
    '银行', '金融板块', 0, 0, '中性', NOW(), '同花顺', '股份制商业银行', 1, 'admin');

-- 客户6（周八）- 1条记录
INSERT INTO crm_company_stock (customer_id, stock_code, stock_name, stock_type, listing_exchange, listing_date, listing_status,
    issue_price, issue_quantity, issue_amount, issue_pe_ratio,
    current_price, market_value, circulating_market_value, total_shares, circulating_shares,
    industry_category, sector, is_st, is_star, stock_rating, price_update_time, data_source, remark, tenant_id, creator)
VALUES
(6, '600276', '恒瑞医药', 'A股', '上交所', '2000-10-18', 1,
    8.88, 150000000, 1332000000.00, 42.50,
    48.50, 323000000000.00, 318000000000.00, 6660000000, 6550000000,
    '医药', '医疗健康板块', 0, 0, '买入', NOW(), 'Wind', '创新药龙头企业', 1, 'admin');

-- 客户7（吴九）- 2条记录（包含美股）
INSERT INTO crm_company_stock (customer_id, stock_code, stock_name, stock_type, listing_exchange, listing_date, listing_status,
    issue_price, issue_quantity, issue_amount, issue_pe_ratio,
    current_price, market_value, circulating_market_value, total_shares, circulating_shares,
    industry_category, sector, is_st, is_star, stock_rating, price_update_time, data_source, remark, tenant_id, creator)
VALUES
(7, '601398', '工商银行', 'A股', '上交所', '2006-10-27', 1,
    3.12, 35000000000, 109200000000.00, 18.50,
    5.50, 1960000000000.00, 1920000000000.00, 356406580991, 349000000000,
    '银行', '金融板块', 0, 0, '增持', NOW(), 'Wind', '全球第一大银行', 1, 'admin'),

(7, 'BABA', '阿里巴巴', '美股', '纽交所', '2014-09-19', 1,
    68.00, 320000000, 21760000000.00, 55.50,
    88.00, 220000000000.00, 215000000000.00, 2500000000, 2440000000,
    '互联网', '科技板块', 0, 0, '买入', NOW(), '东方财富', '中国电商巨头', 1, 'admin');

-- 客户8（郑十）- 3条记录
INSERT INTO crm_company_stock (customer_id, stock_code, stock_name, stock_type, listing_exchange, listing_date, listing_status,
    issue_price, issue_quantity, issue_amount, issue_pe_ratio,
    current_price, market_value, circulating_market_value, total_shares, circulating_shares,
    industry_category, sector, is_st, is_star, stock_rating, price_update_time, data_source, remark, tenant_id, creator)
VALUES
(8, '600887', '伊利股份', 'A股', '上交所', '1996-03-12', 1,
    4.35, 350000000, 1522500000.00, 28.50,
    32.50, 210000000000.00, 206000000000.00, 6460000000, 6340000000,
    '乳制品', '消费板块', 0, 0, '增持', NOW(), '同花顺', '乳业龙头企业', 1, 'admin'),

(8, '601668', '中国建筑', 'A股', '上交所', '2009-07-29', 1,
    4.18, 12000000000, 50160000000.00, 15.50,
    5.80, 270000000000.00, 265000000000.00, 46551724138, 45700000000,
    '建筑', '基建板块', 0, 0, '中性', NOW(), 'Wind', '全球最大建筑企业', 1, 'admin'),

(8, '000002', '万科A', 'A股', '深交所', '1991-01-29', 1,
    14.58, 1100000000, 16038000000.00, 25.50,
    10.50, 122000000000.00, 120000000000.00, 11616452000, 11430000000,
    '房地产', '地产板块', 0, 0, '减持', NOW(), '东方财富', '房地产龙头企业', 1, 'admin');

-- 客户9（钱十一）- 2条记录
INSERT INTO crm_company_stock (customer_id, stock_code, stock_name, stock_type, listing_exchange, listing_date, listing_status,
    issue_price, issue_quantity, issue_amount, issue_pe_ratio,
    current_price, market_value, circulating_market_value, total_shares, circulating_shares,
    industry_category, sector, is_st, is_star, stock_rating, price_update_time, data_source, remark, tenant_id, creator)
VALUES
(9, '601288', '农业银行', 'A股', '上交所', '2010-07-15', 1,
    2.68, 25100000000, 67268000000.00, 12.50,
    3.85, 1370000000000.00, 1340000000000.00, 356061632738, 348000000000,
    '银行', '金融板块', 0, 0, '中性', NOW(), 'Wind', '三农金融服务领导者', 1, 'admin'),

(9, '600030', '中信证券', 'A股', '上交所', '2003-01-06', 1,
    4.80, 1666666666, 8000000000.00, 30.50,
    24.50, 364000000000.00, 358000000000.00, 14861656607, 14600000000,
    '证券', '金融板块', 0, 0, '买入', NOW(), '同花顺', '中国最大券商', 1, 'admin');

-- 客户10（冯十二）- 1条记录
INSERT INTO crm_company_stock (customer_id, stock_code, stock_name, stock_type, listing_exchange, listing_date, listing_status,
    issue_price, issue_quantity, issue_amount, issue_pe_ratio,
    current_price, market_value, circulating_market_value, total_shares, circulating_shares,
    industry_category, sector, is_st, is_star, stock_rating, price_update_time, data_source, remark, tenant_id, creator)
VALUES
(10, '601857', '中国石油', 'A股', '上交所', '2007-11-05', 1,
    16.70, 4000000000, 66800000000.00, 22.50,
    6.80, 1247000000000.00, 1220000000000.00, 183350000000, 179400000000,
    '石油石化', '能源板块', 0, 0, '中性', NOW(), 'Wind', '亚洲最大油气生产商', 1, 'admin');
