-- ============================================
-- 客户画像系统 - 核心客户表建表语句
-- 版本: v1.0
-- 日期: 2025-10-27
-- 数据库: MySQL 8.0+
-- 字符集: utf8mb4
-- 排序规则: utf8mb4_unicode_ci
-- ============================================

-- ============================================
-- 1. CRM_CUSTOMER - 客户主表
-- 说明: 统一管理零售客户和对公客户的共有信息
-- 关键字段: customer_type (1=零售, 2=对公)
-- ============================================
CREATE TABLE `crm_customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '客户ID(主键)',
  `customer_no` VARCHAR(50) NOT NULL COMMENT '客户编号(唯一标识)',
  `customer_type` TINYINT NOT NULL COMMENT '客户类型(1=零售客户, 2=对公客户)',
  `customer_name` VARCHAR(200) NOT NULL COMMENT '客户名称(零售为姓名,对公为企业名称)',
  `customer_level` VARCHAR(50) DEFAULT NULL COMMENT '客户等级(零售:普通/VIP/金卡/钻石卡等, 对公:普通/重点/战略客户等)',
  `customer_status` TINYINT NOT NULL DEFAULT 1 COMMENT '客户状态(1=正常, 2=冻结, 3=注销)',
  `is_high_quality` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否优质客户(0=否, 1=是)',
  `is_important` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否重要客户(0=否, 1=是)',
  `credit_status` VARCHAR(50) DEFAULT NULL COMMENT '信用状态(如:良好、一般、较差)',
  `credit_level` VARCHAR(50) DEFAULT NULL COMMENT '信用等级(如:AAA、AA、A、BBB等)',
  `credit_score` DECIMAL(10,2) DEFAULT NULL COMMENT '信用评分',
  `customer_source` VARCHAR(100) DEFAULT NULL COMMENT '客户来源(如:网点开发、电话营销、网络营销等)',
  `customer_tag` VARCHAR(500) DEFAULT NULL COMMENT '客户标签(多个标签用逗号分隔)',
  `remark` VARCHAR(1000) DEFAULT NULL COMMENT '备注信息',
  -- 租户和权限字段
  `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID(多租户隔离)',
  `dept_id` BIGINT DEFAULT NULL COMMENT '所属部门ID(数据权限)',
  -- 审计字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除(0=未删除, 1=已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_customer_no` (`customer_no`, `deleted`),
  KEY `idx_customer_type` (`customer_type`),
  KEY `idx_customer_level` (`customer_level`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM客户主表(零售+对公共用)';


-- ============================================
-- 2. CRM_RETAIL_CUSTOMER - 零售客户扩展表
-- 说明: 存储零售客户的特有基本信息
-- 关联: customer_id (外键, 关联 crm_customer.id, 1对1关系)
-- ============================================
CREATE TABLE `crm_retail_customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` BIGINT NOT NULL COMMENT '客户ID(外键,关联crm_customer.id,唯一)',

  -- 个人基本信息
  `nickname` VARCHAR(100) DEFAULT NULL COMMENT '昵称',
  `gender` TINYINT DEFAULT NULL COMMENT '性别(0=未知, 1=男, 2=女)',
  `birthday` DATE DEFAULT NULL COMMENT '出生日期',
  `age` INT DEFAULT NULL COMMENT '年龄(可通过出生日期计算)',
  `nationality` VARCHAR(100) DEFAULT '中国' COMMENT '国籍',
  `nation` VARCHAR(50) DEFAULT NULL COMMENT '民族(如:汉族、回族等)',
  `native_place` VARCHAR(200) DEFAULT NULL COMMENT '籍贯',
  `residence_type` VARCHAR(50) DEFAULT NULL COMMENT '户籍类型(如:居民、非居民)',
  `domicile_place` VARCHAR(500) DEFAULT NULL COMMENT '户口所在地(详细地址)',

  -- 职业与教育信息
  `occupation` VARCHAR(100) DEFAULT NULL COMMENT '职业(如:专业技术人员、公务员等)',
  `occupation_type` VARCHAR(100) DEFAULT NULL COMMENT '职业类型分类',
  `marital_status` TINYINT DEFAULT NULL COMMENT '婚姻状态(1=未婚, 2=已婚, 3=离异, 4=丧偶)',
  `religion` VARCHAR(50) DEFAULT NULL COMMENT '宗教信仰',
  `education` VARCHAR(50) DEFAULT NULL COMMENT '受教育程度(如:本科、硕士、博士等)',
  `degree` VARCHAR(50) DEFAULT NULL COMMENT '最高学位(如:学士、硕士、博士)',

  -- VIP与客户分类(零售特有)
  `is_vip` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否VIP客户(0=否, 1=是)',
  `is_core_vip` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否核心VIP客户(0=否, 1=是)',
  `vip_level` VARCHAR(50) DEFAULT NULL COMMENT 'VIP等级(如:金卡、银卡、钻石卡等)',

  -- 财富与风险标识
  `is_high_net_worth` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否高净值客户(0=否, 1=是)',
  `net_worth_type` VARCHAR(100) DEFAULT NULL COMMENT '净值类型(如:个体经营户、工薪阶层等)',
  `income_level` VARCHAR(50) DEFAULT NULL COMMENT '收入水平(如:高、中、低)',
  `asset_level` VARCHAR(50) DEFAULT NULL COMMENT '资产水平(如:高、中、低)',

  -- 信誉相关
  `reputation_status` VARCHAR(50) DEFAULT NULL COMMENT '信誉状态(如:优秀、良好、一般、较差)',
  `reputation_level` VARCHAR(50) DEFAULT NULL COMMENT '信誉级别',
  `reputation_score` DECIMAL(10,2) DEFAULT NULL COMMENT '信誉综合评分',

  -- 客户等级细分(零售特有)
  `retail_customer_type` VARCHAR(50) DEFAULT '正式客户' COMMENT '零售客户类型(如:正式客户、潜在客户等)',
  `is_high_end_customer` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否高端户(0=否, 1=是)',
  `is_comprehensive_customer` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否综合户(0=否, 1=是)',

  -- 客户归属与网格
  `customer_grid_code` VARCHAR(100) DEFAULT NULL COMMENT '客户归属网格编号',

  -- 风险与资质
  `qualification_risk` VARCHAR(50) DEFAULT NULL COMMENT '资质/风险(如:低、中、高)',
  `credit_risk_level` VARCHAR(50) DEFAULT NULL COMMENT '信誉水险等级(如:低、中、高)',
  `credit_risk_rating` VARCHAR(50) DEFAULT NULL COMMENT '信誉水险评级',

  -- 银行业务相关
  `is_payroll_customer` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否我行代发工资客户(0=否, 1=是)',
  `payroll_company_name` VARCHAR(200) DEFAULT NULL COMMENT '代发工资单位名称',
  `become_customer_date` DATE DEFAULT NULL COMMENT '成为我行客户时间(日期)',
  `establish_trust_date` DATE DEFAULT NULL COMMENT '在我行建立信任关系时间(日期)',
  `is_bank_modified` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否本行修改(0=否, 1=是)',
  -- 扩展字段
  `ext_field1` VARCHAR(500) DEFAULT NULL COMMENT '扩展字段1',
  `ext_field2` VARCHAR(500) DEFAULT NULL COMMENT '扩展字段2',
  `ext_field3` VARCHAR(500) DEFAULT NULL COMMENT '扩展字段3',
  -- 审计字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除(0=未删除, 1=已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_customer_id` (`customer_id`, `deleted`),
  KEY `idx_gender` (`gender`),
  KEY `idx_birthday` (`birthday`),
  KEY `idx_occupation` (`occupation`),
  KEY `idx_education` (`education`),
  KEY `idx_is_vip` (`is_vip`),
  KEY `idx_is_core_vip` (`is_core_vip`),
  KEY `idx_vip_level` (`vip_level`),
  KEY `idx_is_high_net_worth` (`is_high_net_worth`),
  KEY `idx_is_high_end_customer` (`is_high_end_customer`),
  KEY `idx_is_comprehensive_customer` (`is_comprehensive_customer`),
  KEY `idx_is_payroll_customer` (`is_payroll_customer`),
  KEY `idx_customer_grid_code` (`customer_grid_code`),
  KEY `idx_qualification_risk` (`qualification_risk`),
  KEY `idx_become_customer_date` (`become_customer_date`),
  CONSTRAINT `fk_retail_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `crm_customer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM零售客户扩展表(零售客户特有基本信息)';


-- ============================================
-- 3. CRM_COMPANY_CUSTOMER - 对公客户扩展表
-- 说明: 存储对公客户的特有基本信息
-- 关联: customer_id (外键, 关联 crm_customer.id, 1对1关系)
-- ============================================
CREATE TABLE `crm_company_customer` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` BIGINT NOT NULL COMMENT '客户ID(外键,关联crm_customer.id,唯一)',
  -- 证件与编码信息
  `license_type` VARCHAR(50) DEFAULT '营业执照' COMMENT '证件类型(通常为营业执照)',
  `license_no` VARCHAR(100) DEFAULT NULL COMMENT '证件号码(营业执照号)',
  `credit_code` VARCHAR(50) DEFAULT NULL COMMENT '统一社会信用代码(18位)',
  `organization_code` VARCHAR(50) DEFAULT NULL COMMENT '组织机构代码',
  `tax_no` VARCHAR(50) DEFAULT NULL COMMENT '纳税人识别号',
  `loan_card_no` VARCHAR(50) DEFAULT NULL COMMENT '贷款卡号',
  -- 企业基本信息
  `enterprise_type` VARCHAR(100) DEFAULT NULL COMMENT '企业类型(如:有限责任公司、股份有限公司等)',
  `enterprise_nature` VARCHAR(100) DEFAULT NULL COMMENT '企业性质(如:国有企业、民营企业、外资企业等)',
  `ownership_type` VARCHAR(100) DEFAULT NULL COMMENT '企业控股类型(如:国有控股、私人控股、外资控股等)',
  `economic_type` VARCHAR(100) DEFAULT NULL COMMENT '企业经济性质(如:其它企业、国有企业等)',
  `enterprise_scale` VARCHAR(50) DEFAULT NULL COMMENT '企业规模(如:大型、中型、小型、微型)',
  `registered_capital` DECIMAL(18,2) DEFAULT NULL COMMENT '注册资本(单位:万元)',
  `registered_capital_currency` VARCHAR(20) DEFAULT 'CNY' COMMENT '注册资本币种(默认人民币)',
  `establish_date` DATE DEFAULT NULL COMMENT '企业成立日期',
  `business_term` VARCHAR(100) DEFAULT NULL COMMENT '营业期限',
  -- 行业分类
  `industry_category_l1` VARCHAR(100) DEFAULT NULL COMMENT '行业分类一级(如:批发业、制造业等)',
  `industry_category_l2` VARCHAR(100) DEFAULT NULL COMMENT '行业分类二级',
  `industry_category_l3` VARCHAR(100) DEFAULT NULL COMMENT '行业分类三级',
  `industry_category_l4` VARCHAR(100) DEFAULT NULL COMMENT '行业分类四级(如:金属及金属矿批发)',
  `industry_code` VARCHAR(50) DEFAULT NULL COMMENT '行业代码(国标行业代码)',
  -- 企业特征标识
  `is_listed` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否上市公司(0=否, 1=是)',
  `is_small_enterprise` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否小微企业(0=否, 1=是)',
  `is_group_customer` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否集团客户(0=否, 1=是)',
  `is_import_export` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否有进出口权(0=否, 1=是)',
  `is_related_party` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否我行关联方(0=否, 1=是)',
  `is_ebank_signed` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否网银签约客户(0=否, 1=是)',
  `is_agriculture_related` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否涉农企业(0=否, 1=是)',
  -- 账户信息
  `basic_account_bank` VARCHAR(200) DEFAULT NULL COMMENT '基本账户开户行',
  `basic_account_no` VARCHAR(50) DEFAULT NULL COMMENT '基本账户账号',
  -- 法定代表人信息
  `legal_person_name` VARCHAR(100) DEFAULT NULL COMMENT '法定代表人/负责人姓名',
  `legal_person_id_type` VARCHAR(50) DEFAULT '身份证' COMMENT '法定代表人证件类型',
  `legal_person_id_no` VARCHAR(100) DEFAULT NULL COMMENT '法定代表人证件号码(加密存储)',
  `legal_person_phone` VARCHAR(20) DEFAULT NULL COMMENT '法定代表人联系电话(加密存储)',
  -- 企业资质与管理
  `enterprise_qualification` VARCHAR(500) DEFAULT NULL COMMENT '企业资质(多个资质用逗号分隔)',
  `management_dept` VARCHAR(200) DEFAULT NULL COMMENT '管理部门',
  `supervise_dept` VARCHAR(200) DEFAULT NULL COMMENT '监管部门',
  -- 评级信息(对公特有)
  `company_rating` VARCHAR(50) DEFAULT NULL COMMENT '企业评级(如:AAA、AA等)',
  `rating_agency` VARCHAR(100) DEFAULT NULL COMMENT '评级机构',
  `rating_date` DATE DEFAULT NULL COMMENT '评级日期',
  -- 扩展字段
  `ext_field1` VARCHAR(500) DEFAULT NULL COMMENT '扩展字段1',
  `ext_field2` VARCHAR(500) DEFAULT NULL COMMENT '扩展字段2',
  `ext_field3` VARCHAR(500) DEFAULT NULL COMMENT '扩展字段3',
  -- 审计字段
  `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID(多租户隔离)',  
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除(0=未删除, 1=已删除)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_customer_id` (`customer_id`, `deleted`),
  UNIQUE KEY `uk_credit_code` (`credit_code`, `deleted`),
  KEY `idx_license_no` (`license_no`),
  KEY `idx_organization_code` (`organization_code`),
  KEY `idx_enterprise_type` (`enterprise_type`),
  KEY `idx_enterprise_scale` (`enterprise_scale`),
  KEY `idx_establish_date` (`establish_date`),
  KEY `idx_industry_l1` (`industry_category_l1`),
  KEY `idx_is_listed` (`is_listed`),
  KEY `idx_is_group` (`is_group_customer`),
  CONSTRAINT `fk_company_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `crm_customer` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM对公客户扩展表(对公客户特有基本信息)';


-- ============================================
-- 字段分类说明
-- ============================================
-- 【主表 crm_customer - 共有字段】
-- ✅ 基础信息: id, customer_no, customer_type, customer_name
-- ✅ 客户分类: customer_level (零售/对公通用), customer_status
-- ✅ 客户标识: is_high_quality, is_important (零售/对公通用)
-- ✅ 信用评价: credit_status, credit_level, credit_score (零售/对公通用)
-- ✅ 营销管理: customer_source, customer_tag, remark
-- ✅ 系统字段: tenant_id, dept_id, creator, create_time, updater, update_time, deleted

-- 【零售扩展表 crm_retail_customer - 零售特有】
-- ✅ VIP分类: is_vip, is_core_vip, vip_level (从主表移入,零售特有)
-- ✅ 个人基础: gender, birthday, nationality, nation, domicile_place 等
-- ✅ 职业教育: occupation, marital_status, education, degree 等
-- ✅ 财富标识: is_high_net_worth, net_worth_type, income_level, asset_level
-- ✅ 信誉评价: reputation_status, reputation_level, reputation_score
-- ✅ 客户分类: retail_customer_type, is_high_end_customer, is_comprehensive_customer
-- ✅ 风险资质: qualification_risk, credit_risk_level, credit_risk_rating
-- ✅ 银行业务: is_payroll_customer, payroll_company_name, become_customer_date 等

-- 【对公扩展表 crm_company_customer - 对公特有】
-- ✅ 证件编码: license_type, license_no, credit_code, organization_code, loan_card_no
-- ✅ 企业基本: enterprise_type, enterprise_nature, ownership_type, registered_capital 等
-- ✅ 行业分类: industry_category_l1~l4, industry_code
-- ✅ 企业特征: is_listed, is_small_enterprise, is_group_customer 等
-- ✅ 账户法人: basic_account_bank, legal_person_name 等

-- ============================================
-- 索引说明
-- ============================================
-- 1. 主键索引: 所有表的 id 或 customer_id
-- 2. 唯一索引: customer_no(客户编号), credit_code(统一社会信用代码)
-- 3. 普通索引:
--    - customer_type: 区分零售和对公,高频查询字段
--    - tenant_id: 多租户隔离,必须建索引
--    - create_time: 支持按时间范围查询
--    - 其他业务字段: 根据查询频率建立

-- ============================================
-- 外键约束说明
-- ============================================
-- 1. crm_retail_customer.customer_id -> crm_customer.id (级联删除)
-- 2. crm_company_customer.customer_id -> crm_customer.id (级联删除)
-- 说明: 当删除主表客户记录时,扩展表数据自动删除

-- ============================================
-- 数据示例
-- ============================================

-- 插入零售客户示例
-- INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, tenant_id)
-- VALUES ('12222222222222', 1, '张三', '普通客户', 1);

-- SET @customer_id = LAST_INSERT_ID();
-- INSERT INTO crm_retail_customer (customer_id, nickname, gender, birthday, nationality, nation, domicile_place,
--   occupation, marital_status, education, degree, is_vip, is_core_vip, vip_level,
--   is_high_net_worth, net_worth_type, retail_customer_type,
--   is_high_end_customer, is_comprehensive_customer, customer_grid_code, qualification_risk, credit_risk_level,
--   credit_risk_rating, is_payroll_customer, payroll_company_name, become_customer_date, establish_trust_date, is_bank_modified)
-- VALUES (@customer_id, '无', 2, '1990-09-01', '中国', '汉族', '广州',
--   '专业技术人员', 2, '本科', '本科', 0, 0, '普通客户',
--   0, '个体经营户', '正式客户',
--   0, 0, '国示机构等包括本综合', '低', '低', '低', 1, '北京宇信科技', '2020-02-02', '2020-02-02', 0);

-- 插入对公客户示例
-- INSERT INTO crm_customer (customer_no, customer_type, customer_name, customer_level, customer_status, tenant_id)
-- VALUES ('66003810316', 2, '沧州市XX有限公司', '普通客户', 1, 1);

-- SET @customer_id = LAST_INSERT_ID();
-- INSERT INTO crm_company_customer (customer_id, license_type, license_no, credit_code, organization_code,
--   loan_card_no, basic_account_bank, basic_account_no, enterprise_scale, establish_date, ownership_type,
--   economic_type, industry_category_l1, industry_category_l4, is_listed, is_small_enterprise,
--   is_import_export, is_group_customer, is_related_party, legal_person_id_no, legal_person_id_type)
-- VALUES (@customer_id, '营业执照', '911302XXXX', '911302XXXX', '58360088-3',
--   '130225000XXXX', 'XX股份有限公司东唐港支行', '5075100', '小', '2015-11-11', '私人控股企业',
--   '其它企业', '批发业', '金属及金属矿批发', 0, 1, 0, 0, 0, '130202****21061X', '身份证');

-- ============================================
-- 查询示例
-- ============================================

-- 查询所有零售客户(含扩展信息)
-- SELECT c.*, r.*
-- FROM crm_customer c
-- LEFT JOIN crm_retail_customer r ON c.id = r.customer_id
-- WHERE c.customer_type = 1 AND c.deleted = 0;

-- 查询所有对公客户(含扩展信息)
-- SELECT c.*, cc.*
-- FROM crm_customer c
-- LEFT JOIN crm_company_customer cc ON c.id = cc.customer_id
-- WHERE c.customer_type = 2 AND c.deleted = 0;

-- 按客户类型统计客户数量
-- SELECT customer_type, COUNT(*) as customer_count
-- FROM crm_customer
-- WHERE deleted = 0
-- GROUP BY customer_type;

-- 查询代发工资客户列表(含代发单位)
-- SELECT c.customer_no, c.customer_name, r.payroll_company_name, r.become_customer_date
-- FROM crm_customer c
-- JOIN crm_retail_customer r ON c.id = r.customer_id
-- WHERE c.customer_type = 1 AND r.is_payroll_customer = 1 AND c.deleted = 0;

-- 查询高端户客户列表
-- SELECT c.customer_no, c.customer_name, c.customer_level, r.customer_grid_code
-- FROM crm_customer c
-- JOIN crm_retail_customer r ON c.id = r.customer_id
-- WHERE c.customer_type = 1 AND r.is_high_end_customer = 1 AND c.deleted = 0;

-- 查询VIP客户列表
-- SELECT c.customer_no, c.customer_name, c.customer_level, r.vip_level
-- FROM crm_customer c
-- JOIN crm_retail_customer r ON c.id = r.customer_id
-- WHERE c.customer_type = 1 AND r.is_vip = 1 AND c.deleted = 0;

-- 按风险等级统计零售客户
-- SELECT r.qualification_risk, COUNT(*) as customer_count
-- FROM crm_retail_customer r
-- JOIN crm_customer c ON r.customer_id = c.id
-- WHERE c.deleted = 0
-- GROUP BY r.qualification_risk;

-- ============================================
-- 表设计说明总结
-- ============================================
-- 1. 设计模式: "统一主表 + 差异扩展"
--    - crm_customer: 存储零售和对公客户的共有信息
--      * 共有字段: 客户编号、客户类型、客户名称、客户等级、客户状态
--      * 共有字段: 是否优质客户、是否重要客户
--      * 共有字段: 信用状态、信用等级、信用评分
--      * 共有字段: 客户来源、客户标签、备注
--    - crm_retail_customer: 存储零售客户的特有信息 (包含以下分类)
--      * 个人基本信息: 性别、生日、国籍、民族、户籍等
--      * 职业教育: 职业、婚姻状况、学历、学位等
--      * VIP分类: 是否VIP、是否核心VIP、VIP等级 (零售特有)
--      * 财富标识: 是否高净值、净值类型、收入水平、资产水平
--      * 信誉评价: 信誉状态、信誉级别、信誉评分
--      * 客户分类: 零售客户类型、是否高端户、是否综合户
--      * 客户归属: 客户归属网格编号
--      * 风险资质: 资质/风险、信誉水险等级、信誉水险评级
--      * 银行业务: 是否代发工资、代发工资单位、成为客户日期、建立信任日期
--    - crm_company_customer: 存储对公客户的特有信息
--      * 证件编码: 证件类型、证件号、统一信用代码、组织机构代码、贷款卡号
--      * 企业基本信息: 企业类型、企业性质、控股类型、经济性质、企业规模、注册资本
--      * 行业分类: 行业一级、二级、三级、四级分类
--      * 企业特征: 是否上市、是否小企业、是否集团、是否网银签约、是否关联方
--      * 账户法人: 基本账户开户行、账号、法定代表人信息
--
-- 2. 关联关系:
--    - crm_customer (1) : (0..1) crm_retail_customer
--    - crm_customer (1) : (0..1) crm_company_customer
--    - 扩展表使用独立主键 id (自增)
--    - 扩展表的 customer_id 为外键,关联主表 id (1对1唯一约束)
--
-- 3. 核心特性:
--    - 多租户隔离: tenant_id 字段
--    - 软删除: deleted 字段
--    - 审计日志: creator, create_time, updater, update_time
--    - 数据权限: dept_id 字段
--
-- 4. 字段命名规范:
--    - 布尔字段: is_xxx (如 is_vip, is_listed)
--    - 日期字段: xxx_date 或 xxx_time
--    - 金额字段: 使用 DECIMAL(18,2)
--    - 所有字段添加 COMMENT 注释
--
-- 5. 性能优化:
--    - 高频查询字段建立索引
--    - 外键约束支持级联删除
--    - 字符集使用 utf8mb4 支持完整 Unicode
--
-- 6. 安全考虑:
--    - 敏感字段(证件号、手机号)需要加密存储
--    - 唯一索引包含 deleted 字段,支持软删除后的数据重复
--
-- ============================================
-- 建表语句结束
-- ============================================
