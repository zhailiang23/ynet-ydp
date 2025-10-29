-- ----------------------------
-- 重新设计工作信息表和经营信息表
-- 每张表只包含其核心业务字段,删除通用字段
-- 设计参考: docs/database-design-guide.md 第2.2节 - 零售客户扩展表
-- ----------------------------

-- ==================== 工作信息表 (精简版) ====================
-- 客户工作信息表 (只包含工作相关的核心字段)
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_work_info`;
CREATE TABLE `crm_customer_work_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '工作信息主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 核心工作信息 ====================
  `employer_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作单位名称',
  `employer_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '单位类型（字典: aicrm_employer_type）',
  `industry` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属行业',

  `position` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职位/职务',
  `position_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '职级（字典: aicrm_position_level）',
  `department` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所在部门',

  `work_years` int NULL DEFAULT NULL COMMENT '工作年限',

  -- ==================== 收入信息 ====================
  `annual_income` decimal(15,2) NULL DEFAULT NULL COMMENT '年收入（元）',
  `monthly_income` decimal(15,2) NULL DEFAULT NULL COMMENT '月收入（元）',

  -- ==================== 社保公积金 ====================
  `has_social_insurance` bit(1) NULL DEFAULT NULL COMMENT '是否有社保',
  `has_housing_fund` bit(1) NULL DEFAULT NULL COMMENT '是否有公积金',

  -- ==================== 联系方式 ====================
  `work_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作电话',
  `work_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '工作邮箱',

  -- ==================== 备注 ====================
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',

  -- ==================== 审计字段 ====================
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_employer_name`(`employer_name` ASC) USING BTREE COMMENT '工作单位名称索引',
  INDEX `idx_employer_type`(`employer_type` ASC) USING BTREE COMMENT '单位类型索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户工作信息表（精简版，只包含工作相关核心字段）';

-- ==================== 经营信息表 (精简版) ====================
-- 客户经营信息表 (只包含经营相关的核心字段)
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_business_info`;
CREATE TABLE `crm_customer_business_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '经营信息主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 核心经营主体信息 ====================
  `business_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '经营主体名称',
  `business_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营类型（字典: aicrm_business_type）',
  `business_license_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '营业执照号/统一社会信用代码',

  -- ==================== 经营范围和规模 ====================
  `business_scope` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营范围',
  `industry` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '所属行业',
  `business_scale` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营规模（字典: aicrm_business_scale）',
  `business_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经营状态（字典: aicrm_business_status）',

  `registered_capital` decimal(18,2) NULL DEFAULT NULL COMMENT '注册资本（元）',
  `employee_count` int NULL DEFAULT NULL COMMENT '员工人数',

  -- ==================== 经营数据 ====================
  `annual_revenue` decimal(18,2) NULL DEFAULT NULL COMMENT '年营业额（元）',
  `monthly_revenue` decimal(18,2) NULL DEFAULT NULL COMMENT '月营业额（元）',
  `annual_profit` decimal(18,2) NULL DEFAULT NULL COMMENT '年利润（元）',

  -- ==================== 税务信息 ====================
  `tax_registration_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '税务登记号',
  `is_general_taxpayer` bit(1) NULL DEFAULT NULL COMMENT '是否一般纳税人',

  -- ==================== 备注 ====================
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',

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
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户经营信息表（精简版，只包含经营相关核心字段）';

-- ==================== 字典类型和字典数据 ====================

-- 1. 单位类型字典 (aicrm_employer_type)
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM单位类型', 'aicrm_employer_type', 0, '工作单位类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM单位类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '政府机关', 'government', 'aicrm_employer_type', 0, 'danger', '政府机关、公务员', '1', NOW(), '1', NOW(), b'0'),
(2, '事业单位', 'institution', 'aicrm_employer_type', 0, 'warning', '事业单位、学校、医院等', '1', NOW(), '1', NOW(), b'0'),
(3, '国有企业', 'state_owned', 'aicrm_employer_type', 0, 'primary', '国有企业', '1', NOW(), '1', NOW(), b'0'),
(4, '民营企业', 'private', 'aicrm_employer_type', 0, 'success', '民营企业', '1', NOW(), '1', NOW(), b'0'),
(5, '外资企业', 'foreign', 'aicrm_employer_type', 0, 'info', '外资企业、合资企业', '1', NOW(), '1', NOW(), b'0'),
(6, '其他', 'other', 'aicrm_employer_type', 0, 'default', '其他类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 职级字典 (aicrm_position_level)
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM职级', 'aicrm_position_level', 0, '员工职级', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM职级';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '普通员工', 'staff', 'aicrm_position_level', 0, 'default', '普通员工', '1', NOW(), '1', NOW(), b'0'),
(2, '主管', 'supervisor', 'aicrm_position_level', 0, 'info', '主管级别', '1', NOW(), '1', NOW(), b'0'),
(3, '经理', 'manager', 'aicrm_position_level', 0, 'primary', '经理级别', '1', NOW(), '1', NOW(), b'0'),
(4, '总监', 'director', 'aicrm_position_level', 0, 'warning', '总监级别', '1', NOW(), '1', NOW(), b'0'),
(5, '高管', 'executive', 'aicrm_position_level', 0, 'danger', '高级管理人员', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 经营类型字典 (aicrm_business_type)
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM经营类型', 'aicrm_business_type', 0, '经营主体类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM经营类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '个体工商户', 'individual', 'aicrm_business_type', 0, 'success', '个体工商户', '1', NOW(), '1', NOW(), b'0'),
(2, '企业法人', 'company', 'aicrm_business_type', 0, 'primary', '有限公司、股份公司等', '1', NOW(), '1', NOW(), b'0'),
(3, '合伙企业', 'partnership', 'aicrm_business_type', 0, 'warning', '合伙企业', '1', NOW(), '1', NOW(), b'0'),
(4, '其他', 'other', 'aicrm_business_type', 0, 'default', '其他类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 经营规模字典 (aicrm_business_scale)
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM经营规模', 'aicrm_business_scale', 0, '企业经营规模', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM经营规模';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '微型', 'micro', 'aicrm_business_scale', 0, 'info', '微型企业', '1', NOW(), '1', NOW(), b'0'),
(2, '小型', 'small', 'aicrm_business_scale', 0, 'success', '小型企业', '1', NOW(), '1', NOW(), b'0'),
(3, '中型', 'medium', 'aicrm_business_scale', 0, 'primary', '中型企业', '1', NOW(), '1', NOW(), b'0'),
(4, '大型', 'large', 'aicrm_business_scale', 0, 'warning', '大型企业', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 经营状态字典 (aicrm_business_status)
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM经营状态', 'aicrm_business_status', 0, '经营状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM经营状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '正常经营', 'normal', 'aicrm_business_status', 0, 'success', '正常经营中', '1', NOW(), '1', NOW(), b'0'),
(2, '停业', 'suspended', 'aicrm_business_status', 0, 'warning', '暂停营业', '1', NOW(), '1', NOW(), b'0'),
(3, '已关闭', 'closed', 'aicrm_business_status', 0, 'danger', '已关闭/注销', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- ==================== 统计查询 ====================

-- 显示表结构对比
SELECT
  '精简后的字段统计' AS category,
  '工作信息表' AS table_name,
  14 AS field_count,
  '删除了: work_type, start_date, end_date, is_current, 地址字段(4), income_source, 核验字段(3), 附件, extra_data' AS removed_fields
UNION ALL
SELECT
  '精简后的字段统计' AS category,
  '经营信息表' AS table_name,
  17 AS field_count,
  '删除了: business_license_expire_date, registration_date, production_capacity, 时间字段(3), 地址字段(4), 联系方式(3), 核验字段(3), 附件, extra_data' AS removed_fields;
