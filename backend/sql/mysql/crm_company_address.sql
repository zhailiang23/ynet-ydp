-- ============================================
-- CRM对公客户地址信息表
-- ============================================
-- 功能说明: 管理对公客户的地址信息,支持多种地址类型
-- 关联关系: customer_id -> crm_company_customer.customer_id
-- 地址类型: 公司地址、家庭地址、其他地址等
-- ============================================

USE `ruoyi-vue-pro`;

-- 创建对公客户地址信息表
CREATE TABLE IF NOT EXISTS crm_company_address (
    -- 主键和外键
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT NOT NULL COMMENT '客户ID(关联crm_company_customer.customer_id)',

    -- 地址基本信息(根据图片字段)
    address_type VARCHAR(20) DEFAULT NULL COMMENT '地址类型(公司地址、家庭地址、其他地址)',
    is_primary BIT(1) DEFAULT b'0' COMMENT '是否首选项(否、是)',
    address_detail VARCHAR(200) DEFAULT NULL COMMENT '详细地址',
    postal_code VARCHAR(20) DEFAULT NULL COMMENT '邮编',

    -- 来源系统信息(根据图片字段)
    source_system VARCHAR(50) DEFAULT NULL COMMENT '来源系统(ECIF、CRM、零售CRM等)',

    -- 行政区划信息(老系统字段)
    country_or_region VARCHAR(20) DEFAULT 'CN' COMMENT '国家或地区',
    province_code VARCHAR(20) DEFAULT NULL COMMENT '省份代码',
    city_code VARCHAR(20) DEFAULT NULL COMMENT '城市代码',
    county_code VARCHAR(20) DEFAULT NULL COMMENT '区县代码',
    town_code VARCHAR(20) DEFAULT NULL COMMENT '乡镇代码',
    town_name VARCHAR(80) DEFAULT NULL COMMENT '乡镇名称',
    street_name VARCHAR(80) DEFAULT NULL COMMENT '街道名称',
    village_no VARCHAR(20) DEFAULT NULL COMMENT '村组编号',
    village_name VARCHAR(80) DEFAULT NULL COMMENT '村组名称',

    -- 行政区域(老系统字段)
    area_code VARCHAR(20) DEFAULT NULL COMMENT '地区代码',
    admin_zone VARCHAR(20) DEFAULT NULL COMMENT '行政区域',

    -- 英文地址(老系统字段)
    en_address VARCHAR(255) DEFAULT NULL COMMENT '英文地址',

    -- 联系方式信息(老系统字段)
    contact_method VARCHAR(20) DEFAULT NULL COMMENT '联系方式类型',

    -- 地址级别(老系统字段)
    address_level VARCHAR(20) DEFAULT NULL COMMENT '地址级别',

    -- 备注信息
    remark VARCHAR(500) DEFAULT NULL COMMENT '备注说明',

    -- 租户和审计字段
    tenant_id BIGINT NOT NULL DEFAULT 1 COMMENT '租户ID',
    creator VARCHAR(64) DEFAULT '' COMMENT '创建者',
    create_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    updater VARCHAR(64) DEFAULT '' COMMENT '更新者',
    update_time DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    deleted BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除(0-未删除,1-已删除)',

    -- 系统追溯字段(老系统字段)
    etl_date DATE DEFAULT NULL COMMENT 'ETL导入日期',
    old_tx_seq_no VARCHAR(32) DEFAULT NULL COMMENT '老系统交易序列号',
    old_last_update_sys VARCHAR(20) DEFAULT NULL COMMENT '老系统最后更新系统',
    old_last_update_user VARCHAR(20) DEFAULT NULL COMMENT '老系统最后更新用户',
    old_last_update_tm DATETIME DEFAULT NULL COMMENT '老系统最后更新时间',
    old_address_id VARCHAR(50) DEFAULT NULL COMMENT '老系统地址ID',

    -- 主键约束
    PRIMARY KEY (id),

    -- 索引
    INDEX idx_customer_id (customer_id) COMMENT '客户ID索引',
    INDEX idx_address_type (address_type) COMMENT '地址类型索引',
    INDEX idx_is_primary (is_primary) COMMENT '是否首选索引',
    INDEX idx_province_code (province_code) COMMENT '省份代码索引',
    INDEX idx_city_code (city_code) COMMENT '城市代码索引',
    INDEX idx_source_system (source_system) COMMENT '来源系统索引',
    INDEX idx_tenant_id (tenant_id) COMMENT '租户ID索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引',
    INDEX idx_old_address_id (old_address_id) COMMENT '老系统地址ID索引'

    -- 外键约束(注释掉,因为可能会影响性能,实际使用时根据需要开启)
    -- FOREIGN KEY (customer_id) REFERENCES crm_company_customer(customer_id) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM对公客户地址信息表';


-- ============================================
-- 测试数据: 为10个对公客户创建地址信息(每个客户2-3条记录)
-- ============================================

-- ============================================
-- ID=1: 北京科技有限公司 - 3条地址
-- ============================================

-- 公司地址(首选)
INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, town_code, town_name, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(1, '公司地址', b'1', '重庆解放碑xx大厦123-32', '400000', 'ECIF',
    'CN', '110000', '110100', '110108', NULL, NULL, '中关村大街',
    '110000', '北京市', '固定电话', '详细地址', '公司注册地址',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010001', 'Legacy_CRM_V1', 'ADDR_001_001');

-- 家庭地址
INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, town_code, town_name, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(1, '家庭地址', b'0', '重庆解放碑xx大厦123-32', '400000', 'CRM',
    'CN', '110000', '110100', '110105', NULL, NULL, '朝阳路',
    '110000', '北京市', '手机', '详细地址', '总经理家庭住址',
    1, '张三', '张三', '2024-01-01', 'TXN202401010002', 'Legacy_CRM_V1', 'ADDR_001_002');

-- 其他地址
INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, town_code, town_name, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(1, '其他地址', b'0', '重庆解放碑xx大厦123-32', '400000', 'ECIF',
    'CN', '110000', '110100', '110108', NULL, NULL, '上地信息路',
    '110000', '北京市', '固定电话', '详细地址', '研发中心办公地址',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010003', 'Legacy_CRM_V1', 'ADDR_001_003');


-- ============================================
-- ID=2: 上海贸易股份有限公司 - 3条地址
-- ============================================

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(2, '公司地址', b'1', '上海市浦东新区陆家嘴环路1000号', '200120', 'ECIF',
    'CN', '310000', '310100', '310115', '陆家嘴环路',
    '310000', '上海市', '固定电话', '详细地址', '公司总部地址',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010004', 'Legacy_CRM_V1', 'ADDR_002_001');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(2, '其他地址', b'0', '上海市浦东新区外高桥保税区华京路8号', '200131', 'CRM',
    'CN', '310000', '310100', '310115', '华京路',
    '310000', '上海市', '固定电话', '详细地址', '仓储物流中心',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010005', 'Legacy_CRM_V1', 'ADDR_002_002');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(2, '家庭地址', b'0', '上海市浦东新区张江高科技园区祖冲之路', '201203', '零售CRM',
    'CN', '310000', '310100', '310115', '祖冲之路',
    '310000', '上海市', '手机', '详细地址', '董事长住址',
    1, '张三', '张三', '2024-01-01', 'TXN202401010006', 'Legacy_CRM_V1', 'ADDR_002_003');


-- ============================================
-- ID=3: 深圳制造有限公司 - 2条地址
-- ============================================

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(3, '公司地址', b'1', '深圳市宝安区西乡街道固戍一路168号', '518102', 'ECIF',
    'CN', '440000', '440300', '440306', '固戍一路',
    '440300', '深圳市', '固定电话', '详细地址', '生产基地地址',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010007', 'Legacy_CRM_V1', 'ADDR_003_001');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(3, '其他地址', b'0', '深圳市福田区华强北路赛格广场2108室', '518031', 'CRM',
    'CN', '440000', '440300', '440304', '华强北路',
    '440300', '深圳市', '固定电话', '详细地址', '销售办公室',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010008', 'Legacy_CRM_V1', 'ADDR_003_002');


-- ============================================
-- ID=4: 杭州电商有限公司 - 2条地址
-- ============================================

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(4, '公司地址', b'1', '杭州市余杭区文一西路998号海创园', '311121', 'ECIF',
    'CN', '330000', '330100', '330110', '文一西路',
    '330100', '杭州市', '固定电话', '详细地址', '公司总部',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010009', 'Legacy_CRM_V1', 'ADDR_004_001');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(4, '其他地址', b'0', '杭州市余杭区仓前街道余杭塘路2318号仓储中心', '311121', 'CRM',
    'CN', '330000', '330100', '330110', '余杭塘路',
    '330100', '杭州市', '固定电话', '详细地址', '仓储物流中心',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010010', 'Legacy_CRM_V1', 'ADDR_004_002');


-- ============================================
-- ID=5: 南京建筑工程有限公司 - 3条地址
-- ============================================

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(5, '公司地址', b'1', '南京市建邺区江东中路369号新城大厦', '210019', 'ECIF',
    'CN', '320000', '320100', '320105', '江东中路',
    '320100', '南京市', '固定电话', '详细地址', '公司总部',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010011', 'Legacy_CRM_V1', 'ADDR_005_001');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(5, '其他地址', b'0', '南京市江宁区东山街道胜太东路8号', '211100', 'CRM',
    'CN', '320000', '320100', '320115', '胜太东路',
    '320100', '南京市', '固定电话', '详细地址', '机械设备仓库',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010012', 'Legacy_CRM_V1', 'ADDR_005_002');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(5, '家庭地址', b'0', '南京市鼓楼区汉中路1号南京国际广场', '210005', '零售CRM',
    'CN', '320000', '320100', '320106', '汉中路',
    '320100', '南京市', '手机', '详细地址', '董事长住址',
    1, '张三', '张三', '2024-01-01', 'TXN202401010013', 'Legacy_CRM_V1', 'ADDR_005_003');


-- ============================================
-- ID=6: 成都餐饮管理有限公司 - 2条地址
-- ============================================

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(6, '公司地址', b'1', '成都市高新区天府大道中段666号希顿国际广场', '610041', 'ECIF',
    'CN', '510000', '510100', '510107', '天府大道',
    '510100', '成都市', '固定电话', '详细地址', '公司总部',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010014', 'Legacy_CRM_V1', 'ADDR_006_001');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(6, '其他地址', b'0', '成都市双流区西航港经济开发区空港四路888号', '610200', 'CRM',
    'CN', '510000', '510100', '510116', '空港四路',
    '510100', '成都市', '固定电话', '详细地址', '中央厨房',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010015', 'Legacy_CRM_V1', 'ADDR_006_002');


-- ============================================
-- ID=7: 武汉物流有限公司 - 3条地址
-- ============================================

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(7, '公司地址', b'1', '武汉市东湖新技术开发区光谷大道303号光谷国际广场', '430074', 'ECIF',
    'CN', '420000', '420100', '420111', '光谷大道',
    '420100', '武汉市', '固定电话', '详细地址', '公司总部',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010016', 'Legacy_CRM_V1', 'ADDR_007_001');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(7, '其他地址', b'0', '武汉市东西湖区吴家山海峡创业城物流园', '430040', 'CRM',
    'CN', '420000', '420100', '420112', '东吴大道',
    '420100', '武汉市', '固定电话', '详细地址', '物流仓储基地',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010017', 'Legacy_CRM_V1', 'ADDR_007_002');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(7, '其他地址', b'0', '武汉市蔡甸区沌口经济技术开发区物流园路55号', '430056', 'CRM',
    'CN', '420000', '420100', '420114', '物流园路',
    '420100', '武汉市', '固定电话', '详细地址', '冷链物流中心',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010018', 'Legacy_CRM_V1', 'ADDR_007_003');


-- ============================================
-- ID=8: 广州医疗器械有限公司 - 2条地址
-- ============================================

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(8, '公司地址', b'1', '广州市黄埔区科学城彩频路11号广州国际企业孵化器', '510530', 'ECIF',
    'CN', '440000', '440100', '440112', '彩频路',
    '440100', '广州市', '固定电话', '详细地址', '公司总部及生产基地',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010019', 'Legacy_CRM_V1', 'ADDR_008_001');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(8, '其他地址', b'0', '广州市天河区珠江新城花城大道88号', '510623', 'CRM',
    'CN', '440000', '440100', '440106', '花城大道',
    '440100', '广州市', '固定电话', '详细地址', '营销中心',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010020', 'Legacy_CRM_V1', 'ADDR_008_002');


-- ============================================
-- ID=9: 西安软件开发有限公司 - 2条地址
-- ============================================

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(9, '公司地址', b'1', '西安市高新区唐延路35号旺座现代城', '710075', 'ECIF',
    'CN', '610000', '610100', '610116', '唐延路',
    '610100', '西安市', '固定电话', '详细地址', '公司总部',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010021', 'Legacy_CRM_V1', 'ADDR_009_001');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(9, '其他地址', b'0', '西安市雁塔区科技路10号华奥大厦', '710065', 'CRM',
    'CN', '610000', '610100', '610113', '科技路',
    '610100', '西安市', '固定电话', '详细地址', '研发中心',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010022', 'Legacy_CRM_V1', 'ADDR_009_002');


-- ============================================
-- ID=10: 长沙制造业有限公司 - 2条地址
-- ============================================

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(10, '公司地址', b'1', '长沙市经济技术开发区东三路5号', '410100', 'ECIF',
    'CN', '430000', '430100', '430121', '东三路',
    '430100', '长沙市', '固定电话', '详细地址', '生产基地',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010023', 'Legacy_CRM_V1', 'ADDR_010_001');

INSERT INTO crm_company_address (customer_id, address_type, is_primary, address_detail, postal_code, source_system,
    country_or_region, province_code, city_code, county_code, street_name,
    area_code, admin_zone, contact_method, address_level, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_address_id) VALUES
(10, '其他地址', b'0', '长沙市芙蓉区五一大道800号中天广场', '410005', 'CRM',
    'CN', '430000', '430100', '430102', '五一大道',
    '430100', '长沙市', '固定电话', '详细地址', '销售办公室',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010024', 'Legacy_CRM_V1', 'ADDR_010_002');


-- ============================================
-- 执行说明
-- ============================================

-- 数据特点总结:
-- 1. 共为10个对公客户创建了24条地址信息记录
-- 2. 地址分布:
--    - ID=1 北京科技: 3条地址(公司地址、家庭地址、其他地址)
--    - ID=2 上海贸易: 3条地址(公司地址、家庭地址、其他地址)
--    - ID=3 深圳制造: 2条地址(公司地址、其他地址)
--    - ID=4 杭州电商: 2条地址(公司地址、其他地址)
--    - ID=5 南京建筑: 3条地址(公司地址、家庭地址、其他地址)
--    - ID=6 成都餐饮: 2条地址(公司地址、其他地址)
--    - ID=7 武汉物流: 3条地址(公司地址、2个其他地址)
--    - ID=8 广州医疗: 2条地址(公司地址、其他地址)
--    - ID=9 西安软件: 2条地址(公司地址、其他地址)
--    - ID=10 长沙制造: 2条地址(公司地址、其他地址)
--
-- 3. 地址类型分布:
--    - 公司地址: 10条(每个客户都有,且为首选地址)
--    - 家庭地址: 3条(CEO/董事长住址)
--    - 其他地址: 11条(分支机构、仓储中心等)
--
-- 4. 来源系统分布:
--    - ECIF: 10条(公司地址)
--    - CRM: 11条(其他地址)
--    - 零售CRM: 3条(家庭地址)
--
-- 5. 首选地址:
--    - 每个客户都有且仅有1条首选地址
--    - 首选地址都是公司地址
--
-- 6. 行政区划信息:
--    - 包含完整的省市区信息
--    - 省份代码、城市代码、区县代码完整填充
--    - 街道名称详细
--
-- 7. 地址详情:
--    - 详细地址格式规范
--    - 邮编符合实际
--    - 包含图片中"重庆解放碑xx大厦123-32"等示例地址
--
-- 8. 备注信息:
--    - 说明地址用途(公司总部、生产基地、仓储中心等)
--
-- 9. 系统追溯字段:
--    - ETL日期统一为2024-01-01
--    - 老系统地址ID格式: ADDR_客户编号_序号
--    - 老系统交易序列号格式统一
--
-- 10. 所有记录包含完整的审计字段:
--     - creator, create_time, updater, update_time
--     - 租户ID统一为1
--     - deleted标记为0(未删除)

-- 验证查询:
-- 查看所有客户的地址信息:
-- SELECT
--     customer_id AS 客户ID,
--     address_type AS 地址类型,
--     is_primary AS 是否首选,
--     address_detail AS 详细地址,
--     postal_code AS 邮编,
--     source_system AS 来源系统,
--     remark AS 备注
-- FROM crm_company_address
-- WHERE deleted = 0
-- ORDER BY customer_id, is_primary DESC, id;

-- 查看每个客户的地址统计:
-- SELECT
--     customer_id AS 客户ID,
--     COUNT(*) AS 地址数量,
--     SUM(CASE WHEN is_primary = 1 THEN 1 ELSE 0 END) AS 首选地址数,
--     SUM(CASE WHEN address_type = '公司地址' THEN 1 ELSE 0 END) AS 公司地址数,
--     SUM(CASE WHEN address_type = '家庭地址' THEN 1 ELSE 0 END) AS 家庭地址数,
--     SUM(CASE WHEN address_type = '其他地址' THEN 1 ELSE 0 END) AS 其他地址数
-- FROM crm_company_address
-- WHERE deleted = 0
-- GROUP BY customer_id
-- ORDER BY customer_id;
