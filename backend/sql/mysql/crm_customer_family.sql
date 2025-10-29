-- ============================================
-- 客户家庭信息表和家庭成员信息表
-- 创建日期: 2025-10-29
-- 说明:
--   1. 家庭信息表与零售客户扩展表形成1对1关系
--   2. 家庭成员信息表与家庭信息表形成1对多关系
--   3. 包含完整的字典数据初始化
-- ============================================

-- ============================================
-- 家庭信息表（1对1关系）
-- ============================================
CREATE TABLE `crm_customer_family` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` BIGINT NOT NULL COMMENT '客户ID（关联零售客户，唯一）',

  -- 家庭基本信息
  `family_member_count` INT DEFAULT NULL COMMENT '家庭人口数',
  `support_member_count` INT DEFAULT NULL COMMENT '供养人口数',
  `labor_member_count` INT DEFAULT NULL COMMENT '劳动力人口数',
  `children_count` INT DEFAULT NULL COMMENT '子女数量',

  -- 家庭财务信息
  `family_annual_income` DECIMAL(18,2) DEFAULT NULL COMMENT '家庭年收入（万元）',
  `family_annual_income_scope` VARCHAR(50) DEFAULT NULL COMMENT '家庭年收入范围（字典：aicrm_family_income_scope）',
  `family_annual_expenditure` DECIMAL(18,2) DEFAULT NULL COMMENT '家庭年支出（万元）',
  `family_annual_expenditure_scope` VARCHAR(50) DEFAULT NULL COMMENT '家庭年支出范围（字典：aicrm_family_expenditure_scope）',
  `family_debt` DECIMAL(18,2) DEFAULT NULL COMMENT '家庭负债（万元）',
  `family_total_assets` DECIMAL(18,2) DEFAULT NULL COMMENT '家庭总资产（万元）',
  `family_assets_info` VARCHAR(200) DEFAULT NULL COMMENT '家庭资产信息',
  `main_income_source` VARCHAR(50) DEFAULT NULL COMMENT '主要收入来源（字典：aicrm_income_source）',

  -- 住房信息
  `residence_status` VARCHAR(50) DEFAULT NULL COMMENT '居住状况（字典：aicrm_residence_status）',
  `house_status` VARCHAR(50) DEFAULT NULL COMMENT '住房状况（字典：aicrm_house_status）',
  `has_home_car` TINYINT(1) DEFAULT NULL COMMENT '是否有房有车（0-否，1-是）',
  `is_house_holder` TINYINT(1) DEFAULT NULL COMMENT '是否户主（0-否，1-是）',
  `house_holder_name` VARCHAR(100) DEFAULT NULL COMMENT '户主姓名',
  `residence_location` VARCHAR(200) DEFAULT NULL COMMENT '所居住宅多(值)，即居住地点描述',

  -- 家庭地址和联系方式
  `family_address` VARCHAR(500) DEFAULT NULL COMMENT '家庭地址',
  `home_tel` VARCHAR(50) DEFAULT NULL COMMENT '家庭电话',

  -- 家庭信用信息
  `is_harmony` TINYINT(1) DEFAULT NULL COMMENT '家庭是否和睦（0-否，1-是）',
  `is_credit_family` TINYINT(1) DEFAULT NULL COMMENT '是否信用家庭（0-否，1-是）',
  `credit_status` VARCHAR(50) DEFAULT NULL COMMENT '信用状况（字典：aicrm_credit_status）',
  `credit_amount` DECIMAL(18,2) DEFAULT NULL COMMENT '授信额度',
  `family_debt_scope` VARCHAR(50) DEFAULT NULL COMMENT '家庭负债范围（字典：aicrm_debt_scope）',
  `debt_status` VARCHAR(50) DEFAULT NULL COMMENT '负债状况（字典：aicrm_debt_status）',
  `family_adverse_records` VARCHAR(500) DEFAULT NULL COMMENT '家庭不良记录',

  -- 其他信息
  `business_and_scale` VARCHAR(500) DEFAULT NULL COMMENT '经营及规模',
  `family_strength` VARCHAR(50) DEFAULT NULL COMMENT '家庭实力（字典：aicrm_family_strength）',
  `remark` VARCHAR(1000) DEFAULT NULL COMMENT '备注',

  -- 审计字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',

  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_customer_id` (`customer_id`, `deleted`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户家庭信息表（零售客户）';

-- ============================================
-- 家庭成员信息表（1对多关系）
-- ============================================
CREATE TABLE `crm_customer_family_member` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` BIGINT NOT NULL COMMENT '客户ID（关联家庭信息表）',

  -- 成员基本信息
  `member_name` VARCHAR(100) NOT NULL COMMENT '成员姓名',
  `relation_type` VARCHAR(50) NOT NULL COMMENT '家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）',
  `gender` TINYINT DEFAULT NULL COMMENT '性别（1-男，2-女）',
  `birthday` DATE DEFAULT NULL COMMENT '生日',
  `age` INT DEFAULT NULL COMMENT '年龄',

  -- 证件信息
  `identity_type` VARCHAR(50) DEFAULT NULL COMMENT '证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）',
  `identity_no` VARCHAR(200) DEFAULT NULL COMMENT '证件号码（加密存储）',

  -- 教育和工作信息
  `education_level` VARCHAR(50) DEFAULT NULL COMMENT '学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）',
  `company` VARCHAR(200) DEFAULT NULL COMMENT '工作单位',
  `position` VARCHAR(100) DEFAULT NULL COMMENT '职务',

  -- 地址和联系方式
  `address` VARCHAR(500) DEFAULT NULL COMMENT '家庭地址',
  `mobile` VARCHAR(50) DEFAULT NULL COMMENT '联系方式（手机号）',
  `tel` VARCHAR(50) DEFAULT NULL COMMENT '固定电话',
  `email` VARCHAR(100) DEFAULT NULL COMMENT '邮箱',

  -- 其他信息
  `is_main_member` TINYINT(1) DEFAULT 0 COMMENT '是否主要成员（0-否，1-是）',
  `remark` VARCHAR(1000) DEFAULT NULL COMMENT '备注',

  -- 老系统关联字段（保留用于数据迁移）
  `member_id` VARCHAR(50) DEFAULT NULL COMMENT '成员ID（老系统）',
  `manager_id` VARCHAR(50) DEFAULT NULL COMMENT '客户经理ID（老系统）',
  `old_cust_id` VARCHAR(50) DEFAULT NULL COMMENT '老系统客户ID',

  -- 审计字段
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',

  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_tenant_id` (`tenant_id`),
  KEY `idx_member_name` (`member_name`),
  KEY `idx_relation_type` (`relation_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户家庭成员信息表（零售客户）';

-- ============================================
-- 字典数据初始化（供家庭信息使用）
-- ============================================

-- 家庭关系字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM家庭关系', 'aicrm_family_relation', 0, '零售客户家庭成员关系', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_family_relation', 'spouse', '配偶', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_relation', 'father', '父亲', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_relation', 'mother', '母亲', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_relation', 'son', '儿子', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_relation', 'daughter', '女儿', 5, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_relation', 'brother', '兄弟', 6, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_relation', 'sister', '姐妹', 7, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_relation', 'grandfather', '祖父', 8, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_relation', 'grandmother', '祖母', 9, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_relation', 'other', '其他', 99, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 家庭年收入范围字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM家庭年收入范围', 'aicrm_family_income_scope', 0, '零售客户家庭年收入范围', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_family_income_scope', '1', '10万以下', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_income_scope', '2', '10-30万', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_income_scope', '3', '30-50万', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_income_scope', '4', '50-100万', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_income_scope', '5', '100-200万', 5, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_income_scope', '6', '200万以上', 6, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 家庭年支出范围字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM家庭年支出范围', 'aicrm_family_expenditure_scope', 0, '零售客户家庭年支出范围', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_family_expenditure_scope', '1', '10万以下', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_expenditure_scope', '2', '10-30万', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_expenditure_scope', '3', '30-50万', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_expenditure_scope', '4', '50-100万', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_expenditure_scope', '5', '100-200万', 5, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_expenditure_scope', '6', '200万以上', 6, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 居住状况字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM居住状况', 'aicrm_residence_status', 0, '零售客户居住状况', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_residence_status', '1', '自有住房', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_residence_status', '2', '租赁住房', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_residence_status', '3', '与父母同住', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_residence_status', '4', '单位宿舍', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_residence_status', '5', '其他', 99, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 住房状况字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM住房状况', 'aicrm_house_status', 0, '零售客户住房状况', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_house_status', '1', '按揭', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_house_status', '2', '自购', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_house_status', '3', '分配', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_house_status', '4', '租赁', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_house_status', '5', '其他', 99, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 收入来源字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM收入来源', 'aicrm_income_source', 0, '零售客户主要收入来源', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_income_source', '1', '工资薪金', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_income_source', '2', '经营所得', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_income_source', '3', '投资收益', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_income_source', '4', '租金收入', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_income_source', '5', '退休金', 5, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_income_source', '6', '其他', 99, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 信用状况字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM信用状况', 'aicrm_credit_status', 0, '零售客户信用状况', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_credit_status', '1', '优秀', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_credit_status', '2', '良好', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_credit_status', '3', '一般', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_credit_status', '4', '较差', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 负债范围字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM负债范围', 'aicrm_debt_scope', 0, '零售客户家庭负债范围', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_debt_scope', '1', '10万以下', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_debt_scope', '2', '10-30万', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_debt_scope', '3', '30-50万', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_debt_scope', '4', '50-100万', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_debt_scope', '5', '100万以上', 5, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 负债状况字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM负债状况', 'aicrm_debt_status', 0, '零售客户负债状况', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_debt_status', '1', '无负债', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_debt_status', '2', '轻度负债', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_debt_status', '3', '中度负债', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_debt_status', '4', '重度负债', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');

-- 家庭实力字典
INSERT INTO `system_dict_type` (`name`, `type`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`, `deleted_time`)
VALUES ('AICRM家庭实力', 'aicrm_family_strength', 0, '零售客户家庭实力评估', 'system', NOW(), 'system', NOW(), b'0', NULL);

INSERT INTO `system_dict_data` (`dict_type`, `value`, `label`, `sort`, `status`, `remark`, `creator`, `create_time`, `updater`, `update_time`, `deleted`) VALUES
('aicrm_family_strength', '1', '强', 1, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_strength', '2', '较强', 2, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_strength', '3', '一般', 3, 0, NULL, 'system', NOW(), 'system', NOW(), b'0'),
('aicrm_family_strength', '4', '较弱', 4, 0, NULL, 'system', NOW(), 'system', NOW(), b'0');
