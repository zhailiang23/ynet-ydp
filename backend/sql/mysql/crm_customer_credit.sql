-- ----------------------------
-- 客户授信信息表
-- 设计原则:
-- 1. 主表记录授信协议的基本信息和额度情况
-- 2. 明细表记录授信使用的详细情况
-- 3. 支持零售客户和对公客户
-- 4. 统计日期字段用于记录授信数据的统计时点
-- ----------------------------

-- ==================== 客户授信信息表（主表） ====================
DROP TABLE IF EXISTS `crm_customer_credit`;
CREATE TABLE `crm_customer_credit` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '授信主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 图片字段 ====================
  `statistics_date` date NOT NULL COMMENT '统计日期',
  `credit_agreement_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授信协议号',
  `credit_product_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授信品种（字典: aicrm_credit_product_type）',
  `currency_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'CNY' COMMENT '授信币种（字典: aicrm_currency_type）',
  `credit_limit` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '授信额度（元）',
  `used_limit` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '已用额度（元）',
  `available_limit` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '可用额度（元）',
  `usage_ratio` decimal(10,6) NULL DEFAULT NULL COMMENT '使用比例（%）',
  `credit_start_date` date NOT NULL COMMENT '授信起始日',
  `credit_end_date` date NOT NULL COMMENT '授信到期日',

  -- ==================== 扩展字段 ====================
  `credit_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）',
  `credit_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '授信状态（字典: aicrm_credit_status，effective=生效中，expired=已到期，cancelled=已撤销，frozen=已冻结）',
  `approve_date` date NULL DEFAULT NULL COMMENT '授信审批日期',
  `approve_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '授信审批金额（元）',
  `interest_rate` decimal(10,6) NULL DEFAULT NULL COMMENT '授信利率（%）',
  `guarantee_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '担保方式（字典: aicrm_guarantee_type）',
  `credit_purpose` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '授信用途',
  `approver_user_id` bigint NULL DEFAULT NULL COMMENT '审批人ID（关联 system_users.id）',
  `approver_dept_id` bigint NULL DEFAULT NULL COMMENT '审批部门ID（关联 system_dept.id）',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '客户经理ID（关联 system_users.id）',
  `branch_id` bigint NULL DEFAULT NULL COMMENT '授信网点ID（关联 system_dept.id）',

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
  UNIQUE INDEX `uk_agreement_no`(`credit_agreement_no` ASC, `deleted` ASC) USING BTREE COMMENT '授信协议号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_credit_status`(`credit_status` ASC) USING BTREE COMMENT '授信状态索引',
  INDEX `idx_statistics_date`(`statistics_date` ASC) USING BTREE COMMENT '统计日期索引',
  INDEX `idx_credit_start_date`(`credit_start_date` ASC) USING BTREE COMMENT '授信起始日索引',
  INDEX `idx_credit_end_date`(`credit_end_date` ASC) USING BTREE COMMENT '授信到期日索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户授信信息表（零售+对公共用）';

-- ==================== 客户授信使用明细表 ====================
DROP TABLE IF EXISTS `crm_customer_credit_detail`;
CREATE TABLE `crm_customer_credit_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '授信明细主键',
  `credit_id` bigint NOT NULL COMMENT '授信ID（关联 crm_customer_credit.id）',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 用信信息 ====================
  `loan_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '贷款编号/借据号',
  `contract_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '合同编号',
  `loan_date` date NOT NULL COMMENT '放款日期',
  `mature_date` date NULL DEFAULT NULL COMMENT '到期日期',
  `loan_amount` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '贷款金额（元）',
  `balance` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '贷款余额（元）',
  `interest_rate` decimal(10,6) NULL DEFAULT NULL COMMENT '执行利率（%）',
  `loan_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '贷款状态（字典: aicrm_loan_status，normal=正常，overdue=逾期，settled=已结清）',
  `settle_date` date NULL DEFAULT NULL COMMENT '结清日期',

  -- ==================== 产品信息 ====================
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '贷款产品名称',
  `product_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品代码',

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
  INDEX `idx_credit_id`(`credit_id` ASC) USING BTREE COMMENT '授信ID索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_loan_no`(`loan_no` ASC) USING BTREE COMMENT '贷款编号索引',
  INDEX `idx_contract_no`(`contract_no` ASC) USING BTREE COMMENT '合同编号索引',
  INDEX `idx_loan_status`(`loan_status` ASC) USING BTREE COMMENT '贷款状态索引',
  INDEX `idx_loan_date`(`loan_date` ASC) USING BTREE COMMENT '放款日期索引',
  INDEX `idx_mature_date`(`mature_date` ASC) USING BTREE COMMENT '到期日期索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户授信使用明细表（零售+对公共用）';

-- ==================== 字典类型和字典数据 ====================

-- 1. 授信品种字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM授信品种', 'aicrm_credit_product_type', 0, '授信品种类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM授信品种';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '流动资金贷款', 'working_capital', 'aicrm_credit_product_type', 0, 'primary', '流动资金贷款', '1', NOW(), '1', NOW(), b'0'),
(2, '固定资产贷款', 'fixed_asset', 'aicrm_credit_product_type', 0, 'success', '固定资产贷款', '1', NOW(), '1', NOW(), b'0'),
(3, '项目贷款', 'project', 'aicrm_credit_product_type', 0, 'info', '项目贷款', '1', NOW(), '1', NOW(), b'0'),
(4, '贸易融资', 'trade_finance', 'aicrm_credit_product_type', 0, 'warning', '贸易融资', '1', NOW(), '1', NOW(), b'0'),
(5, '票据融资', 'bill_finance', 'aicrm_credit_product_type', 0, 'default', '票据融资', '1', NOW(), '1', NOW(), b'0'),
(6, '银行承兑汇票', 'bank_acceptance', 'aicrm_credit_product_type', 0, 'default', '银行承兑汇票', '1', NOW(), '1', NOW(), b'0'),
(7, '信用证', 'letter_of_credit', 'aicrm_credit_product_type', 0, 'default', '信用证', '1', NOW(), '1', NOW(), b'0'),
(8, '保函', 'guarantee_letter', 'aicrm_credit_product_type', 0, 'default', '保函', '1', NOW(), '1', NOW(), b'0'),
(9, '个人消费贷款', 'personal_consumption', 'aicrm_credit_product_type', 0, 'primary', '个人消费贷款', '1', NOW(), '1', NOW(), b'0'),
(10, '个人经营贷款', 'personal_business', 'aicrm_credit_product_type', 0, 'success', '个人经营贷款', '1', NOW(), '1', NOW(), b'0'),
(11, '个人住房贷款', 'mortgage', 'aicrm_credit_product_type', 0, 'info', '个人住房贷款', '1', NOW(), '1', NOW(), b'0'),
(12, '信用卡', 'credit_card', 'aicrm_credit_product_type', 0, 'warning', '信用卡', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 授信类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM授信类型', 'aicrm_credit_type', 0, '授信类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM授信类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '综合授信', 'comprehensive', 'aicrm_credit_type', 0, 'primary', '综合授信额度', '1', NOW(), '1', NOW(), b'0'),
(2, '单笔授信', 'single', 'aicrm_credit_type', 0, 'success', '单笔授信', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 授信状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM授信状态', 'aicrm_credit_status', 0, '授信状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM授信状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '生效中', 'effective', 'aicrm_credit_status', 0, 'success', '授信生效中', '1', NOW(), '1', NOW(), b'0'),
(2, '已到期', 'expired', 'aicrm_credit_status', 0, 'info', '授信已到期', '1', NOW(), '1', NOW(), b'0'),
(3, '已撤销', 'cancelled', 'aicrm_credit_status', 0, 'warning', '授信已撤销', '1', NOW(), '1', NOW(), b'0'),
(4, '已冻结', 'frozen', 'aicrm_credit_status', 0, 'danger', '授信已冻结', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 担保方式字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM担保方式', 'aicrm_guarantee_type', 0, '担保方式', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM担保方式';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '信用', 'credit', 'aicrm_guarantee_type', 0, 'primary', '信用担保', '1', NOW(), '1', NOW(), b'0'),
(2, '抵押', 'mortgage', 'aicrm_guarantee_type', 0, 'success', '抵押担保', '1', NOW(), '1', NOW(), b'0'),
(3, '质押', 'pledge', 'aicrm_guarantee_type', 0, 'info', '质押担保', '1', NOW(), '1', NOW(), b'0'),
(4, '保证', 'guarantee', 'aicrm_guarantee_type', 0, 'warning', '保证担保', '1', NOW(), '1', NOW(), b'0'),
(5, '组合担保', 'combined', 'aicrm_guarantee_type', 0, 'default', '组合担保', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 贷款状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM贷款状态', 'aicrm_loan_status', 0, '贷款状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM贷款状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '正常', 'normal', 'aicrm_loan_status', 0, 'success', '正常还款', '1', NOW(), '1', NOW(), b'0'),
(2, '逾期', 'overdue', 'aicrm_loan_status', 0, 'danger', '逾期未还', '1', NOW(), '1', NOW(), b'0'),
(3, '已结清', 'settled', 'aicrm_loan_status', 0, 'info', '贷款已结清', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
