-- ----------------------------
-- 将 crm_customer_work 表拆分为工作信息表和经营信息表
-- 设计参考: docs/database-design-guide.md 第2.2节 - 零售客户扩展表
-- ----------------------------

-- ==================== 工作信息表 ====================
-- 客户工作信息表 (零售客户特有)
-- 设计参考: docs/database-design-guide.md 第2.2节 - 零售客户扩展表关联方式
-- 关系: crm_retail_customer 1对多 crm_customer_work_info
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_work_info`;
CREATE TABLE `crm_customer_work_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '工作信息主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 工作单位信息 ====================
  `work_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工作类型（字典: aicrm_work_type，employed=在职，freelance=自由职业，retired=退休，unemployed=待业）',
  `employer_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作单位名称',
  `employer_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单位类型（字典: aicrm_employer_type，government=政府机关，institution=事业单位，enterprise=企业，other=其他）',
  `industry` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属行业',

  -- ==================== 职位信息 ====================
  `position` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职位/职务',
  `position_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职级（字典: aicrm_position_level，staff=员工，supervisor=主管，manager=经理，director=总监，executive=高管）',
  `department` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所在部门',

  -- ==================== 工作时间 ====================
  `start_date` date NULL DEFAULT NULL COMMENT '入职日期',
  `end_date` date NULL DEFAULT NULL COMMENT '离职日期（在职则为NULL）',
  `work_years` int NULL DEFAULT NULL COMMENT '工作年限',
  `is_current` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否当前工作',

  -- ==================== 工作地址 ====================
  `work_address_province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作地址-省',
  `work_address_city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作地址-市',
  `work_address_district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作地址-区',
  `work_address_detail` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作地址-详细地址',

  -- ==================== 收入信息 ====================
  `annual_income` decimal(15,2) NULL DEFAULT NULL COMMENT '年收入（元）',
  `monthly_income` decimal(15,2) NULL DEFAULT NULL COMMENT '月收入（元）',
  `income_source` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收入来源说明',
  `has_social_insurance` bit(1) NULL DEFAULT NULL COMMENT '是否有社保',
  `has_housing_fund` bit(1) NULL DEFAULT NULL COMMENT '是否有公积金',

  -- ==================== 联系方式 ====================
  `work_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作电话',
  `work_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作邮箱',

  -- ==================== 核验信息 ====================
  `verification_status` tinyint NOT NULL DEFAULT 0 COMMENT '核验状态（0=未核验 1=核验中 2=核验通过 3=核验失败）',
  `verification_time` datetime NULL DEFAULT NULL COMMENT '核验时间',
  `verification_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '核验备注',

  -- ==================== 附件和备注 ====================
  `attachment_urls` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '附件URL（多个用逗号分隔，如劳动合同、工作证明等）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `extra_data` json NULL COMMENT '扩展数据（JSON格式）',

  -- ==================== 审计字段 ====================
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_work_type`(`work_type` ASC) USING BTREE COMMENT '工作类型索引',
  INDEX `idx_employer_name`(`employer_name` ASC) USING BTREE COMMENT '工作单位名称索引',
  INDEX `idx_is_current`(`is_current` ASC) USING BTREE COMMENT '是否当前工作索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户工作信息表（零售客户特有，1对多关系，支持多工作经历）';

-- ==================== 经营信息表 ====================
-- 客户经营信息表 (零售客户特有)
-- 设计参考: docs/database-design-guide.md 第2.2节 - 零售客户扩展表关联方式
-- 关系: crm_retail_customer 1对多 crm_customer_business_info
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_business_info`;
CREATE TABLE `crm_customer_business_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '经营信息主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 经营主体信息 ====================
  `business_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '经营主体名称（个体户、企业名称等）',
  `business_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营类型（字典: aicrm_business_type，individual=个体工商户，company=企业法人，partnership=合伙企业，other=其他）',
  `business_license_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '营业执照号/统一社会信用代码',
  `business_license_expire_date` date NULL DEFAULT NULL COMMENT '营业执照有效期至',
  `registration_date` date NULL DEFAULT NULL COMMENT '注册日期',

  -- ==================== 经营范围和规模 ====================
  `business_scope` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营范围',
  `industry` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属行业',
  `business_scale` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营规模（字典: aicrm_business_scale，micro=微型，small=小型，medium=中型，large=大型）',
  `business_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营状态（字典: aicrm_business_status，operating=正常经营，suspended=停业，closed=已关闭）',
  `registered_capital` decimal(18,2) NULL DEFAULT NULL COMMENT '注册资本（元）',
  `employee_count` int NULL DEFAULT NULL COMMENT '员工人数',

  -- ==================== 经营地址 ====================
  `business_address_province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营地址-省',
  `business_address_city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营地址-市',
  `business_address_district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营地址-区',
  `business_address_detail` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营地址-详细地址',

  -- ==================== 经营数据 ====================
  `annual_revenue` decimal(18,2) NULL DEFAULT NULL COMMENT '年营业额（元）',
  `monthly_revenue` decimal(18,2) NULL DEFAULT NULL COMMENT '月营业额（元）',
  `annual_profit` decimal(18,2) NULL DEFAULT NULL COMMENT '年利润（元）',
  `production_capacity` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '生产能力/产能说明',

  -- ==================== 经营时间 ====================
  `start_date` date NULL DEFAULT NULL COMMENT '开始经营日期',
  `end_date` date NULL DEFAULT NULL COMMENT '停止经营日期（正常经营则为NULL）',
  `is_current` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否当前经营',

  -- ==================== 联系方式 ====================
  `contact_person` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `contact_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系邮箱',

  -- ==================== 税务信息 ====================
  `tax_registration_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '税务登记号',
  `is_general_taxpayer` bit(1) NULL DEFAULT NULL COMMENT '是否一般纳税人',

  -- ==================== 核验信息 ====================
  `verification_status` tinyint NOT NULL DEFAULT 0 COMMENT '核验状态（0=未核验 1=核验中 2=核验通过 3=核验失败）',
  `verification_time` datetime NULL DEFAULT NULL COMMENT '核验时间',
  `verification_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '核验备注',

  -- ==================== 附件和备注 ====================
  `attachment_urls` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '附件URL（多个用逗号分隔，如营业执照、税务登记证等）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',
  `extra_data` json NULL COMMENT '扩展数据（JSON格式）',

  -- ==================== 审计字段 ====================
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_business_name`(`business_name` ASC) USING BTREE COMMENT '经营主体名称索引',
  INDEX `idx_business_license_no`(`business_license_no` ASC) USING BTREE COMMENT '营业执照号索引',
  INDEX `idx_business_status`(`business_status` ASC) USING BTREE COMMENT '经营状态索引',
  INDEX `idx_is_current`(`is_current` ASC) USING BTREE COMMENT '是否当前经营索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户经营信息表（零售客户特有，1对多关系，支持多个经营主体）';

-- ==================== 数据迁移 ====================
-- 从 crm_customer_work 表迁移工作信息数据（work_type='employed'）
INSERT INTO crm_customer_work_info (
  customer_id, work_type, employer_name, employer_type, industry,
  position, start_date, end_date, work_years, is_current,
  work_address_province, work_address_city, work_address_district, work_address_detail,
  annual_income, monthly_income, income_source,
  work_phone, verification_status, verification_time, verification_remark,
  attachment_urls, remark, extra_data,
  creator, create_time, updater, update_time, deleted, tenant_id
)
SELECT
  customer_id, work_type, employer_name, employer_type, industry,
  position, start_date, end_date, work_years, is_current,
  work_address_province, work_address_city, work_address_district, work_address_detail,
  annual_income, monthly_income, income_source,
  work_phone, verification_status, verification_time, verification_remark,
  attachment_urls, remark, extra_data,
  creator, create_time, updater, update_time, deleted, tenant_id
FROM crm_customer_work
WHERE work_type IN ('employed', 'freelance', 'retired', 'unemployed')
  OR business_license_no IS NULL OR business_license_no = '';

-- 从 crm_customer_work 表迁移经营信息数据（有 business_license_no 的记录）
INSERT INTO crm_customer_business_info (
  customer_id, business_name, business_license_no, business_scope,
  industry, business_scale, business_status, production_capacity,
  business_address_province, business_address_city, business_address_district, business_address_detail,
  annual_revenue, monthly_revenue,
  start_date, end_date, is_current,
  contact_person, contact_phone,
  verification_status, verification_time, verification_remark,
  attachment_urls, remark, extra_data,
  creator, create_time, updater, update_time, deleted, tenant_id
)
SELECT
  customer_id,
  COALESCE(employer_name, '未命名经营主体') AS business_name,
  business_license_no,
  NULL AS business_scope,
  industry,
  business_scale,
  business_status,
  production_capacity,
  work_address_province AS business_address_province,
  work_address_city AS business_address_city,
  work_address_district AS business_address_district,
  work_address_detail AS business_address_detail,
  annual_income AS annual_revenue,
  monthly_income AS monthly_revenue,
  start_date,
  end_date,
  is_current,
  contact_person,
  contact_phone,
  verification_status,
  verification_time,
  verification_remark,
  attachment_urls,
  remark,
  extra_data,
  creator,
  create_time,
  updater,
  update_time,
  deleted,
  tenant_id
FROM crm_customer_work
WHERE business_license_no IS NOT NULL AND business_license_no != '';

-- ==================== 字典数据补充 ====================

-- 1. 单位类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM单位类型', 'aicrm_employer_type', 0, '客户工作单位类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '政府机关', 'government', 'aicrm_employer_type', 0, 'primary', '政府机关', '1', NOW(), '1', NOW(), b'0'),
(2, '事业单位', 'institution', 'aicrm_employer_type', 0, 'success', '事业单位', '1', NOW(), '1', NOW(), b'0'),
(3, '国有企业', 'state_owned', 'aicrm_employer_type', 0, 'info', '国有企业', '1', NOW(), '1', NOW(), b'0'),
(4, '民营企业', 'private', 'aicrm_employer_type', 0, 'warning', '民营企业', '1', NOW(), '1', NOW(), b'0'),
(5, '外资企业', 'foreign', 'aicrm_employer_type', 0, 'danger', '外资企业', '1', NOW(), '1', NOW(), b'0'),
(6, '其他', 'other', 'aicrm_employer_type', 0, 'default', '其他类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label=label;

-- 2. 职级字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM职级', 'aicrm_position_level', 0, '客户职位级别', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '普通员工', 'staff', 'aicrm_position_level', 0, 'default', '普通员工', '1', NOW(), '1', NOW(), b'0'),
(2, '主管', 'supervisor', 'aicrm_position_level', 0, 'primary', '主管级别', '1', NOW(), '1', NOW(), b'0'),
(3, '经理', 'manager', 'aicrm_position_level', 0, 'success', '经理级别', '1', NOW(), '1', NOW(), b'0'),
(4, '总监', 'director', 'aicrm_position_level', 0, 'info', '总监级别', '1', NOW(), '1', NOW(), b'0'),
(5, '高管', 'executive', 'aicrm_position_level', 0, 'warning', '高管级别', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label=label;

-- 3. 经营类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM经营类型', 'aicrm_business_type', 0, '客户经营类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name=name;

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '个体工商户', 'individual', 'aicrm_business_type', 0, 'primary', '个体工商户', '1', NOW(), '1', NOW(), b'0'),
(2, '企业法人', 'company', 'aicrm_business_type', 0, 'success', '企业法人', '1', NOW(), '1', NOW(), b'0'),
(3, '合伙企业', 'partnership', 'aicrm_business_type', 0, 'info', '合伙企业', '1', NOW(), '1', NOW(), b'0'),
(4, '其他', 'other', 'aicrm_business_type', 0, 'default', '其他类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label=label;

-- ==================== 统计查询 ====================
SELECT '数据迁移统计' AS category,
  (SELECT COUNT(*) FROM crm_customer_work WHERE deleted = b'0') AS 原表记录数,
  (SELECT COUNT(*) FROM crm_customer_work_info WHERE deleted = b'0') AS 工作信息表记录数,
  (SELECT COUNT(*) FROM crm_customer_business_info WHERE deleted = b'0') AS 经营信息表记录数;
