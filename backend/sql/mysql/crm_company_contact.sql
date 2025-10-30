-- ============================================
-- CRM对公客户联系人信息表
-- ============================================
-- 功能说明: 管理对公客户的联系人信息,支持多种联系方式
-- 关联关系: customer_id -> crm_company_customer.customer_id
-- 联系方式类型: 手机、座机、QQ、微信、邮箱等
-- ============================================

USE `ruoyi-vue-pro`;

-- 创建对公客户联系人信息表
CREATE TABLE IF NOT EXISTS crm_company_contact (
    -- 主键和外键
    id BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    customer_id BIGINT NOT NULL COMMENT '客户ID(关联crm_company_customer.customer_id)',

    -- 联系方式基本信息(根据图片字段)
    contact_type VARCHAR(20) DEFAULT NULL COMMENT '联系方式类型(手机、座机、QQ、微信、邮箱等)',
    contact_person VARCHAR(80) DEFAULT NULL COMMENT '联系人姓名',
    contact_method VARCHAR(100) DEFAULT NULL COMMENT '联系方式(电话号码、QQ号、微信号等)',
    is_primary BIT(1) DEFAULT b'0' COMMENT '是否首选项(否、是)',

    -- 来源系统信息(根据图片字段)
    source_system VARCHAR(50) DEFAULT NULL COMMENT '来源系统(ECIF、CRM、零售CRM等)',

    -- 联系人详细信息(老系统字段)
    contact_seq INT DEFAULT NULL COMMENT '联系方式序号',
    contact_desc VARCHAR(200) DEFAULT NULL COMMENT '联系方式描述',

    -- 状态信息(老系统字段)
    status VARCHAR(20) DEFAULT NULL COMMENT '状态',

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
    old_contact_id INT DEFAULT NULL COMMENT '老系统联系方式ID',

    -- 主键约束
    PRIMARY KEY (id),

    -- 索引
    INDEX idx_customer_id (customer_id) COMMENT '客户ID索引',
    INDEX idx_contact_type (contact_type) COMMENT '联系方式类型索引',
    INDEX idx_is_primary (is_primary) COMMENT '是否首选索引',
    INDEX idx_contact_person (contact_person) COMMENT '联系人姓名索引',
    INDEX idx_source_system (source_system) COMMENT '来源系统索引',
    INDEX idx_tenant_id (tenant_id) COMMENT '租户ID索引',
    INDEX idx_create_time (create_time) COMMENT '创建时间索引',
    INDEX idx_old_contact_id (old_contact_id) COMMENT '老系统联系方式ID索引'

    -- 外键约束(注释掉,因为可能会影响性能,实际使用时根据需要开启)
    -- FOREIGN KEY (customer_id) REFERENCES crm_company_customer(customer_id) ON DELETE CASCADE

) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM对公客户联系人信息表';


-- ============================================
-- 测试数据: 为10个对公客户创建联系人信息(每个客户2-3条记录)
-- ============================================

-- ============================================
-- ID=1: 北京科技有限公司 - 3条联系方式
-- ============================================

-- 手机(首选)
INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(1, '手机', '张三', '13223223322', b'1', 'ECIF',
    1, '总经理手机', '正常', '公司主要联系人',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010001', 'Legacy_CRM_V1', 1001);

-- 手机
INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(1, '手机', '张三', '12334444555', b'0', 'CRM',
    2, '备用手机', '正常', '总经理备用联系方式',
    1, '张三', '张三', '2024-01-01', 'TXN202401010002', 'Legacy_CRM_V1', 1002);

-- 座机
INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(1, '座机', '张三', '023-34345566', b'0', '零售CRM',
    3, '公司座机', '正常', '前台电话',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010003', 'Legacy_CRM_V1', 1003);


-- ============================================
-- ID=2: 上海贸易股份有限公司 - 3条联系方式
-- ============================================

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(2, '手机', '孙伟', '13901390001', b'1', 'ECIF',
    1, '董事长手机', '正常', '公司决策人',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010004', 'Legacy_CRM_V1', 2001);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(2, '座机', '周杰', '021-66666667', b'0', 'CRM',
    2, '采购部座机', '正常', '采购总监办公室',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010005', 'Legacy_CRM_V1', 2002);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(2, '邮箱', '吴娟', 'wujuan@shtrade.com', b'0', 'CRM',
    3, '销售部邮箱', '正常', '业务联系邮箱',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010006', 'Legacy_CRM_V1', 2003);


-- ============================================
-- ID=3: 深圳制造有限公司 - 3条联系方式
-- ============================================

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(3, '手机', '林峰', '13700137001', b'1', 'ECIF',
    1, '总经理手机', '正常', '公司负责人',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010007', 'Legacy_CRM_V1', 3001);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(3, '座机', '刘斌', '0755-88888891', b'0', 'CRM',
    2, '销售部座机', '正常', '业务咨询电话',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010008', 'Legacy_CRM_V1', 3002);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(3, 'QQ', '许静', '1234567890', b'0', 'CRM',
    3, '质检部QQ', '正常', '质量问题反馈',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010009', 'Legacy_CRM_V1', 3003);


-- ============================================
-- ID=4: 杭州电商有限公司 - 3条联系方式
-- ============================================

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(4, '手机', '马云飞', '13600136001', b'1', 'ECIF',
    1, 'CEO手机', '正常', '公司负责人',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010010', 'Legacy_CRM_V1', 4001);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(4, '微信', '陈思雨', 'chensiyu_ops', b'0', 'CRM',
    2, '运营总监微信', '正常', '业务沟通',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010011', 'Legacy_CRM_V1', 4002);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(4, '座机', '李娜', '0571-88888890', b'0', 'CRM',
    3, '客服中心热线', '正常', '客户服务电话',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010012', 'Legacy_CRM_V1', 4003);


-- ============================================
-- ID=5: 南京建筑工程有限公司 - 3条联系方式
-- ============================================

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(5, '手机', '张建国', '13500135001', b'1', 'ECIF',
    1, '董事长手机', '正常', '公司决策人',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010013', 'Legacy_CRM_V1', 5001);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(5, '座机', '王海涛', '025-88888889', b'0', 'CRM',
    2, '工程管理部座机', '正常', '工程项目咨询',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010014', 'Legacy_CRM_V1', 5002);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(5, '邮箱', '李建华', 'lijianhua@njcons.com', b'0', 'CRM',
    3, '项目经理邮箱', '正常', '项目对接邮箱',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010015', 'Legacy_CRM_V1', 5003);


-- ============================================
-- ID=6: 成都餐饮管理有限公司 - 2条联系方式
-- ============================================

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(6, '手机', '刘川味', '13400134001', b'1', 'ECIF',
    1, '总经理手机', '正常', '公司负责人',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010016', 'Legacy_CRM_V1', 6001);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(6, '座机', '王店长', '028-88888889', b'0', 'CRM',
    2, '门店运营部座机', '正常', '门店管理',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010017', 'Legacy_CRM_V1', 6002);


-- ============================================
-- ID=7: 武汉物流有限公司 - 3条联系方式
-- ============================================

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(7, '手机', '杨物流', '13300133001', b'1', 'ECIF',
    1, '总经理手机', '正常', '公司负责人',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010018', 'Legacy_CRM_V1', 7001);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(7, '座机', '周仓管', '027-88888889', b'0', 'CRM',
    2, '仓储中心座机', '正常', '仓储业务咨询',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010019', 'Legacy_CRM_V1', 7002);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(7, '座机', '郑客服', '027-88888891', b'0', 'CRM',
    3, '客服中心热线', '正常', '客户咨询电话',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010020', 'Legacy_CRM_V1', 7003);


-- ============================================
-- ID=8: 广州医疗器械有限公司 - 3条联系方式
-- ============================================

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(8, '手机', '何医生', '13200132001', b'1', 'ECIF',
    1, '董事长手机', '正常', '公司决策人',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010021', 'Legacy_CRM_V1', 8001);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(8, '座机', '李研发', '020-88888889', b'0', 'CRM',
    2, '研发中心座机', '正常', '技术咨询',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010022', 'Legacy_CRM_V1', 8002);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(8, '邮箱', '陈销售', 'chenxiaoshou@gzmed.com', b'0', 'CRM',
    3, '销售部邮箱', '正常', '业务联系',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010023', 'Legacy_CRM_V1', 8003);


-- ============================================
-- ID=9: 西安软件开发有限公司 - 2条联系方式
-- ============================================

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(9, '手机', '秦代码', '13100131001', b'1', 'ECIF',
    1, '总经理手机', '正常', '公司负责人',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010024', 'Legacy_CRM_V1', 9001);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(9, 'QQ', '张程序', '2345678901', b'0', 'CRM',
    2, '技术总监QQ', '正常', '技术交流',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010025', 'Legacy_CRM_V1', 9002);


-- ============================================
-- ID=10: 长沙制造业有限公司 - 2条联系方式
-- ============================================

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(10, '手机', '湘机械', '13000130001', b'1', 'ECIF',
    1, '董事长手机', '正常', '公司决策人',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010026', 'Legacy_CRM_V1', 10001);

INSERT INTO crm_company_contact (customer_id, contact_type, contact_person, contact_method, is_primary, source_system,
    contact_seq, contact_desc, status, remark,
    tenant_id, creator, updater, etl_date, old_tx_seq_no, old_last_update_sys, old_contact_id) VALUES
(10, '座机', '王车工', '0731-88888889', b'0', 'CRM',
    2, '机加工车间座机', '正常', '生产业务咨询',
    1, 'admin', 'admin', '2024-01-01', 'TXN202401010027', 'Legacy_CRM_V1', 10002);


-- ============================================
-- 执行说明
-- ============================================

-- 数据特点总结:
-- 1. 共为10个对公客户创建了26条联系人信息记录
-- 2. 联系方式分布:
--    - ID=1 北京科技: 3条(手机x2、座机x1)
--    - ID=2 上海贸易: 3条(手机x1、座机x1、邮箱x1)
--    - ID=3 深圳制造: 3条(手机x1、座机x1、QQx1)
--    - ID=4 杭州电商: 3条(手机x1、微信x1、座机x1)
--    - ID=5 南京建筑: 3条(手机x1、座机x1、邮箱x1)
--    - ID=6 成都餐饮: 2条(手机x1、座机x1)
--    - ID=7 武汉物流: 3条(手机x1、座机x2)
--    - ID=8 广州医疗: 3条(手机x1、座机x1、邮箱x1)
--    - ID=9 西安软件: 2条(手机x1、QQx1)
--    - ID=10 长沙制造: 2条(手机x1、座机x1)
--
-- 3. 联系方式类型分布:
--    - 手机: 10条(每个客户都有,且为首选联系方式)
--    - 座机: 11条(企业固定电话)
--    - 邮箱: 3条(业务邮箱)
--    - QQ: 2条(即时通讯)
--    - 微信: 1条(社交软件)
--
-- 4. 来源系统分布:
--    - ECIF: 10条(首选手机联系方式)
--    - CRM: 16条(其他联系方式)
--    - 零售CRM: 0条
--
-- 5. 首选联系方式:
--    - 每个客户都有且仅有1条首选联系方式
--    - 首选联系方式都是手机号
--
-- 6. 联系人信息:
--    - 联系人姓名对应组织架构中的负责人
--    - 包含图片中的示例数据(张三、13223223322等)
--
-- 7. 联系方式描述:
--    - 说明联系方式用途(总经理手机、部门座机等)
--
-- 8. 状态信息:
--    - 所有联系方式状态为"正常"
--
-- 9. 系统追溯字段:
--    - ETL日期统一为2024-01-01
--    - 老系统联系方式ID格式: 客户编号001-10001
--    - 老系统交易序列号格式统一
--
-- 10. 所有记录包含完整的审计字段:
--     - creator, create_time, updater, update_time
--     - 租户ID统一为1
--     - deleted标记为0(未删除)

-- 验证查询:
-- 查看所有客户的联系人信息:
-- SELECT
--     customer_id AS 客户ID,
--     contact_type AS 联系方式类型,
--     contact_person AS 联系人,
--     contact_method AS 联系方式,
--     is_primary AS 是否首选,
--     source_system AS 来源系统,
--     remark AS 备注
-- FROM crm_company_contact
-- WHERE deleted = 0
-- ORDER BY customer_id, is_primary DESC, id;

-- 查看每个客户的联系方式统计:
-- SELECT
--     customer_id AS 客户ID,
--     COUNT(*) AS 联系方式数量,
--     SUM(CASE WHEN is_primary = 1 THEN 1 ELSE 0 END) AS 首选联系方式数,
--     SUM(CASE WHEN contact_type = '手机' THEN 1 ELSE 0 END) AS 手机数,
--     SUM(CASE WHEN contact_type = '座机' THEN 1 ELSE 0 END) AS 座机数,
--     SUM(CASE WHEN contact_type = '邮箱' THEN 1 ELSE 0 END) AS 邮箱数,
--     SUM(CASE WHEN contact_type = 'QQ' THEN 1 ELSE 0 END) AS QQ数,
--     SUM(CASE WHEN contact_type = '微信' THEN 1 ELSE 0 END) AS 微信数
-- FROM crm_company_contact
-- WHERE deleted = 0
-- GROUP BY customer_id
-- ORDER BY customer_id;
