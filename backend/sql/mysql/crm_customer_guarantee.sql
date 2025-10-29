-- ----------------------------
-- 客户担保信息表
-- 设计原则:
-- 1. 抵押物信息表记录不动产、动产等抵押物信息
-- 2. 质押物信息表记录存单、股权等质押物信息
-- 3. 担保人信息表记录保证人的担保信息
-- 4. 支持零售客户和对公客户
-- ----------------------------

-- ==================== 客户抵押物信息表 ====================
DROP TABLE IF EXISTS `crm_customer_guarantee_mortgage`;
CREATE TABLE `crm_customer_guarantee_mortgage` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '抵押物主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',
  `credit_id` bigint NULL DEFAULT NULL COMMENT '授信ID（关联 crm_customer_credit.id）',

  -- ==================== 图片字段 ====================
  `collateral_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '押品编号',
  `collateral_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '押品名称',
  `collateral_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '押品类型（字典: aicrm_mortgage_type）',
  `certificate_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '权证号',
  `guarantee_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '担保类型（字典: aicrm_guarantee_method，mortgage=抵押）',
  `mortgagor_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '抵押人姓名/名称',
  `mortgagor_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '抵押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
  `relation_with_borrower` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '与被担保人关系（字典: aicrm_relation_type）',
  `guarantee_amount` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '担保本金限权金额（万元）',
  `management_branch_id` bigint NULL DEFAULT NULL COMMENT '押品管理机构ID（关联 system_dept.id）',
  `management_branch_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '押品管理机构名称',

  -- ==================== 扩展字段 ====================
  `mortgagor_id_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '抵押人证件类型（字典: aicrm_identity_type）',
  `mortgagor_id_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '抵押人证件号码（加密）',
  `collateral_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '抵押物地址',
  `collateral_area` decimal(18,6) NULL DEFAULT NULL COMMENT '抵押物面积（平方米）',
  `evaluation_value` decimal(24,6) NULL DEFAULT NULL COMMENT '评估价值（万元）',
  `evaluation_date` date NULL DEFAULT NULL COMMENT '评估日期',
  `evaluation_agency` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评估机构',
  `mortgage_rate` decimal(10,6) NULL DEFAULT NULL COMMENT '抵押率（%）',
  `mortgage_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '抵押状态（字典: aicrm_mortgage_status，effective=有效，released=已解押，invalid=无效）',
  `mortgage_date` date NULL DEFAULT NULL COMMENT '抵押登记日期',
  `release_date` date NULL DEFAULT NULL COMMENT '解押日期',

  -- ==================== 备注信息 ====================
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',

  -- ==================== 审计字段 ====================
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_collateral_no`(`collateral_no` ASC, `deleted` ASC) USING BTREE COMMENT '押品编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_credit_id`(`credit_id` ASC) USING BTREE COMMENT '授信ID索引',
  INDEX `idx_collateral_type`(`collateral_type` ASC) USING BTREE COMMENT '押品类型索引',
  INDEX `idx_mortgage_status`(`mortgage_status` ASC) USING BTREE COMMENT '抵押状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户抵押物信息表（零售+对公共用）';

-- ==================== 客户质押物信息表 ====================
DROP TABLE IF EXISTS `crm_customer_guarantee_pledge`;
CREATE TABLE `crm_customer_guarantee_pledge` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '质押物主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',
  `credit_id` bigint NULL DEFAULT NULL COMMENT '授信ID（关联 crm_customer_credit.id）',

  -- ==================== 图片字段 ====================
  `collateral_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '押品编号',
  `collateral_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '押品类型（字典: aicrm_pledge_type）',
  `guarantee_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '担保类型（字典: aicrm_guarantee_method，pledge=质押）',
  `pledgor_id_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '质押人证件类型（字典: aicrm_identity_type）',
  `pledgor_id_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '质押人证件号码（加密）',
  `pledgor_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '质押人姓名/名称',
  `pledgor_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '质押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
  `relation_with_borrower` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '与被担保人关系（字典: aicrm_relation_type）',
  `guarantee_amount` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '担保本金限权金额（万元）',
  `pledge_rate` decimal(10,6) NULL DEFAULT NULL COMMENT '质押率（%）',

  -- ==================== 扩展字段 ====================
  `collateral_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '质押物名称',
  `collateral_value` decimal(24,6) NULL DEFAULT NULL COMMENT '质押物价值（万元）',
  `pledge_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '质押状态（字典: aicrm_pledge_status，effective=有效，released=已解押，invalid=无效）',
  `pledge_date` date NULL DEFAULT NULL COMMENT '质押登记日期',
  `release_date` date NULL DEFAULT NULL COMMENT '解押日期',
  `management_branch_id` bigint NULL DEFAULT NULL COMMENT '管理机构ID（关联 system_dept.id）',
  `management_branch_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '管理机构名称',

  -- ==================== 备注信息 ====================
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',

  -- ==================== 审计字段 ====================
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_collateral_no`(`collateral_no` ASC, `deleted` ASC) USING BTREE COMMENT '押品编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_credit_id`(`credit_id` ASC) USING BTREE COMMENT '授信ID索引',
  INDEX `idx_collateral_type`(`collateral_type` ASC) USING BTREE COMMENT '押品类型索引',
  INDEX `idx_pledge_status`(`pledge_status` ASC) USING BTREE COMMENT '质押状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户质押物信息表（零售+对公共用）';

-- ==================== 客户担保人信息表 ====================
DROP TABLE IF EXISTS `crm_customer_guarantor`;
CREATE TABLE `crm_customer_guarantor` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '担保人主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（被担保人，关联 crm_customer 主表）',
  `credit_id` bigint NULL DEFAULT NULL COMMENT '授信ID（关联 crm_customer_credit.id）',

  -- ==================== 图片字段 ====================
  `contract_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '合同号',
  `contract_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '合同类型（字典: aicrm_guarantor_contract_type）',
  `contract_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '合同状态（字典: aicrm_guarantor_contract_status，effective=有效，expired=已到期，cancelled=已解除）',
  `sign_date` date NOT NULL COMMENT '签约日期',
  `guarantee_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '担保类型（字典: aicrm_guarantee_method，guarantee=保证）',
  `guarantor_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '担保人编号',
  `guarantor_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '担保人姓名/名称',
  `currency_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'CNY' COMMENT '币种（字典: aicrm_currency_type）',
  `guarantee_total_amount` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '担保总金额（万元）',
  `business_start_date` date NULL DEFAULT NULL COMMENT '业务起始日期',
  `business_end_date` date NULL DEFAULT NULL COMMENT '业务截止日期',

  -- ==================== 扩展字段 ====================
  `guarantor_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
  `guarantor_id_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '担保人证件类型（字典: aicrm_identity_type）',
  `guarantor_id_no` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '担保人证件号码（加密）',
  `relation_with_borrower` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '与被担保人关系（字典: aicrm_relation_type）',
  `guarantee_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '担保方式（字典: aicrm_guarantee_style，joint=连带责任，general=一般保证）',
  `used_amount` decimal(24,6) NULL DEFAULT 0 COMMENT '已用担保金额（万元）',
  `available_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '可用担保金额（万元）',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '客户经理ID（关联 system_users.id）',
  `branch_id` bigint NULL DEFAULT NULL COMMENT '管理机构ID（关联 system_dept.id）',

  -- ==================== 备注信息 ====================
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
  INDEX `idx_credit_id`(`credit_id` ASC) USING BTREE COMMENT '授信ID索引',
  INDEX `idx_contract_no`(`contract_no` ASC) USING BTREE COMMENT '合同号索引',
  INDEX `idx_guarantor_no`(`guarantor_no` ASC) USING BTREE COMMENT '担保人编号索引',
  INDEX `idx_contract_status`(`contract_status` ASC) USING BTREE COMMENT '合同状态索引',
  INDEX `idx_sign_date`(`sign_date` ASC) USING BTREE COMMENT '签约日期索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户担保人信息表（零售+对公共用）';

-- ==================== 字典类型和字典数据 ====================

-- 1. 抵押物类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM抵押物类型', 'aicrm_mortgage_type', 0, '抵押物类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM抵押物类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '住宅', 'residence', 'aicrm_mortgage_type', 0, 'primary', '住宅房产', '1', NOW(), '1', NOW(), b'0'),
(2, '商铺', 'shop', 'aicrm_mortgage_type', 0, 'success', '商业用房', '1', NOW(), '1', NOW(), b'0'),
(3, '写字楼', 'office', 'aicrm_mortgage_type', 0, 'info', '办公用房', '1', NOW(), '1', NOW(), b'0'),
(4, '厂房', 'factory', 'aicrm_mortgage_type', 0, 'warning', '工业厂房', '1', NOW(), '1', NOW(), b'0'),
(5, '土地使用权', 'land', 'aicrm_mortgage_type', 0, 'default', '土地使用权', '1', NOW(), '1', NOW(), b'0'),
(6, '在建工程', 'construction', 'aicrm_mortgage_type', 0, 'default', '在建工程', '1', NOW(), '1', NOW(), b'0'),
(7, '机器设备', 'equipment', 'aicrm_mortgage_type', 0, 'default', '机器设备', '1', NOW(), '1', NOW(), b'0'),
(8, '车辆', 'vehicle', 'aicrm_mortgage_type', 0, 'default', '车辆', '1', NOW(), '1', NOW(), b'0'),
(9, '其他', 'other', 'aicrm_mortgage_type', 0, 'default', '其他抵押物', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 质押物类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM质押物类型', 'aicrm_pledge_type', 0, '质押物类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM质押物类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '存单', 'deposit_receipt', 'aicrm_pledge_type', 0, 'primary', '银行存单', '1', NOW(), '1', NOW(), b'0'),
(2, '国债', 'treasury_bond', 'aicrm_pledge_type', 0, 'success', '国债', '1', NOW(), '1', NOW(), b'0'),
(3, '股权', 'equity', 'aicrm_pledge_type', 0, 'info', '股权', '1', NOW(), '1', NOW(), b'0'),
(4, '应收账款', 'receivable', 'aicrm_pledge_type', 0, 'warning', '应收账款', '1', NOW(), '1', NOW(), b'0'),
(5, '保单', 'policy', 'aicrm_pledge_type', 0, 'default', '保险保单', '1', NOW(), '1', NOW(), b'0'),
(6, '仓单', 'warehouse_receipt', 'aicrm_pledge_type', 0, 'default', '仓储仓单', '1', NOW(), '1', NOW(), b'0'),
(7, '知识产权', 'intellectual_property', 'aicrm_pledge_type', 0, 'default', '知识产权', '1', NOW(), '1', NOW(), b'0'),
(8, '其他', 'other', 'aicrm_pledge_type', 0, 'default', '其他质押物', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 担保方式字典（已有 aicrm_guarantee_type，这里补充更细分的）
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM担保方法', 'aicrm_guarantee_method', 0, '担保方法分类', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM担保方法';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '抵押', 'mortgage', 'aicrm_guarantee_method', 0, 'primary', '抵押担保', '1', NOW(), '1', NOW(), b'0'),
(2, '质押', 'pledge', 'aicrm_guarantee_method', 0, 'success', '质押担保', '1', NOW(), '1', NOW(), b'0'),
(3, '保证', 'guarantee', 'aicrm_guarantee_method', 0, 'info', '保证担保', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 担保人类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM担保人类型', 'aicrm_guarantor_type', 0, '担保人类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM担保人类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '个人', 'person', 'aicrm_guarantor_type', 0, 'primary', '个人担保', '1', NOW(), '1', NOW(), b'0'),
(2, '企业', 'company', 'aicrm_guarantor_type', 0, 'success', '企业担保', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 抵押状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM抵押状态', 'aicrm_mortgage_status', 0, '抵押状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM抵押状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '有效', 'effective', 'aicrm_mortgage_status', 0, 'success', '抵押有效', '1', NOW(), '1', NOW(), b'0'),
(2, '已解押', 'released', 'aicrm_mortgage_status', 0, 'info', '已解除抵押', '1', NOW(), '1', NOW(), b'0'),
(3, '无效', 'invalid', 'aicrm_mortgage_status', 0, 'danger', '抵押无效', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 6. 质押状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM质押状态', 'aicrm_pledge_status', 0, '质押状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM质押状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '有效', 'effective', 'aicrm_pledge_status', 0, 'success', '质押有效', '1', NOW(), '1', NOW(), b'0'),
(2, '已解押', 'released', 'aicrm_pledge_status', 0, 'info', '已解除质押', '1', NOW(), '1', NOW(), b'0'),
(3, '无效', 'invalid', 'aicrm_pledge_status', 0, 'danger', '质押无效', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 7. 担保合同状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM担保合同状态', 'aicrm_guarantor_contract_status', 0, '担保合同状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM担保合同状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '有效', 'effective', 'aicrm_guarantor_contract_status', 0, 'success', '合同有效', '1', NOW(), '1', NOW(), b'0'),
(2, '已到期', 'expired', 'aicrm_guarantor_contract_status', 0, 'info', '合同已到期', '1', NOW(), '1', NOW(), b'0'),
(3, '已解除', 'cancelled', 'aicrm_guarantor_contract_status', 0, 'warning', '合同已解除', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 8. 担保合同类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM担保合同类型', 'aicrm_guarantor_contract_type', 0, '担保合同类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM担保合同类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '最高额保证', 'maximum_guarantee', 'aicrm_guarantor_contract_type', 0, 'primary', '最高额保证合同', '1', NOW(), '1', NOW(), b'0'),
(2, '一般保证', 'general_guarantee', 'aicrm_guarantor_contract_type', 0, 'success', '一般保证合同', '1', NOW(), '1', NOW(), b'0'),
(3, '连带责任保证', 'joint_guarantee', 'aicrm_guarantor_contract_type', 0, 'info', '连带责任保证合同', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 9. 担保方式字典（保证的具体方式）
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM保证方式', 'aicrm_guarantee_style', 0, '保证担保方式', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM保证方式';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '连带责任', 'joint', 'aicrm_guarantee_style', 0, 'danger', '连带责任保证', '1', NOW(), '1', NOW(), b'0'),
(2, '一般保证', 'general', 'aicrm_guarantee_style', 0, 'primary', '一般保证', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 10. 关系类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM关系类型', 'aicrm_relation_type', 0, '与被担保人关系类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM关系类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '本人', 'self', 'aicrm_relation_type', 0, 'primary', '本人', '1', NOW(), '1', NOW(), b'0'),
(2, '配偶', 'spouse', 'aicrm_relation_type', 0, 'success', '配偶', '1', NOW(), '1', NOW(), b'0'),
(3, '父母', 'parent', 'aicrm_relation_type', 0, 'info', '父母', '1', NOW(), '1', NOW(), b'0'),
(4, '子女', 'child', 'aicrm_relation_type', 0, 'warning', '子女', '1', NOW(), '1', NOW(), b'0'),
(5, '兄弟姐妹', 'sibling', 'aicrm_relation_type', 0, 'default', '兄弟姐妹', '1', NOW(), '1', NOW(), b'0'),
(6, '关联企业', 'related_company', 'aicrm_relation_type', 0, 'default', '关联企业', '1', NOW(), '1', NOW(), b'0'),
(7, '股东', 'shareholder', 'aicrm_relation_type', 0, 'default', '股东', '1', NOW(), '1', NOW(), b'0'),
(8, '第三方', 'third_party', 'aicrm_relation_type', 0, 'default', '第三方', '1', NOW(), '1', NOW(), b'0'),
(9, '其他', 'other', 'aicrm_relation_type', 0, 'default', '其他关系', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
