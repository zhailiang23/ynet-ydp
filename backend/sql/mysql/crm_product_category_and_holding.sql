-- ----------------------------
-- 产品类目表和客户产品持有情况表
-- 设计原则:
-- 1. 产品类目表采用树形结构，支持多级分类
-- 2. 客户产品持有情况表记录客户与产品的多对多关系
-- 3. 支持零售客户和对公客户
-- ----------------------------

-- ==================== 产品类目表 ====================
DROP TABLE IF EXISTS `crm_product_category`;
CREATE TABLE `crm_product_category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '产品类目主键',

  -- ==================== 类目基本信息 ====================
  `category_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类目编号',
  `category_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '类目名称',
  `parent_id` bigint NULL DEFAULT 0 COMMENT '父类目ID（0表示顶级类目）',
  `category_level` int NOT NULL DEFAULT 1 COMMENT '类目层级（1=一级，2=二级，3=三级...）',
  `category_path` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类目路径（如：/1/2/3/）',

  -- ==================== 产品标识 ====================
  `is_leaf` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否叶子节点（0=分类目录，1=实际产品）',
  `product_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）',

  -- ==================== 产品详细信息（仅叶子节点有效）====================
  `product_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品编号',
  `product_desc` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品描述',
  `currency_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'CNY' COMMENT '币种（字典: aicrm_currency_type）',
  `interest_rate_min` decimal(10,6) NULL DEFAULT NULL COMMENT '最低利率/收益率（%）',
  `interest_rate_max` decimal(10,6) NULL DEFAULT NULL COMMENT '最高利率/收益率（%）',
  `term_min` int NULL DEFAULT NULL COMMENT '最短期限（天）',
  `term_max` int NULL DEFAULT NULL COMMENT '最长期限（天）',
  `min_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '起购金额',
  `max_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '最大金额',
  `risk_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '风险等级（字典: aicrm_risk_level）',

  -- ==================== 适用客户 ====================
  `customer_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）',

  -- ==================== 产品状态 ====================
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态（0=停用，1=启用）',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',

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
  UNIQUE INDEX `uk_category_code`(`category_code` ASC, `deleted` ASC) USING BTREE COMMENT '类目编号唯一索引',
  INDEX `idx_parent_id`(`parent_id` ASC) USING BTREE COMMENT '父类目ID索引',
  INDEX `idx_category_level`(`category_level` ASC) USING BTREE COMMENT '类目层级索引',
  INDEX `idx_is_leaf`(`is_leaf` ASC) USING BTREE COMMENT '是否叶子节点索引',
  INDEX `idx_product_type`(`product_type` ASC) USING BTREE COMMENT '产品类型索引',
  INDEX `idx_product_code`(`product_code` ASC) USING BTREE COMMENT '产品编号索引',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '产品类目表（树形结构）';

-- ==================== 客户产品持有情况表 ====================
DROP TABLE IF EXISTS `crm_customer_product_holding`;
CREATE TABLE `crm_customer_product_holding` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '产品持有主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',
  `product_id` bigint NOT NULL COMMENT '产品ID（关联 crm_product_category.id，必须是叶子节点）',

  -- ==================== 持有账户信息（图片字段）====================
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '贷款账号/账号',
  `receipt_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '借据编号',
  `contract_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '合同编号',
  `currency_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'CNY' COMMENT '币种代码',

  -- ==================== 日期信息（图片字段）====================
  `loan_date` date NULL DEFAULT NULL COMMENT '放款日期（贷款产品）',
  `open_date` date NULL DEFAULT NULL COMMENT '开户日期（存款、理财等产品）',
  `mature_date` date NULL DEFAULT NULL COMMENT '到期日期',
  `contract_date` date NULL DEFAULT NULL COMMENT '合同日期',

  -- ==================== 网点信息（图片字段）====================
  `branch_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开户网点名称',
  `branch_id` bigint NULL DEFAULT NULL COMMENT '开户网点ID（关联 system_dept.id）',

  -- ==================== 产品名称（图片字段）====================
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名称',

  -- ==================== 金额信息 ====================
  `holding_amount` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '持有金额/余额',
  `original_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '原始金额（初始投资/贷款金额）',
  `interest_rate` decimal(10,6) NULL DEFAULT NULL COMMENT '利率/收益率（%）',

  -- ==================== 持有状态 ====================
  `holding_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '持有状态（字典: aicrm_holding_status，holding=持有中，matured=已到期，redeemed=已赎回，settled=已结清）',

  -- ==================== 关联账户信息 ====================
  `related_account_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联账户类型（deposit=存款账户，loan=贷款账户，wealth=理财账户等）',
  `related_account_id` bigint NULL DEFAULT NULL COMMENT '关联账户ID（关联对应的账户表主键）',

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
  INDEX `idx_product_id`(`product_id` ASC) USING BTREE COMMENT '产品ID索引',
  INDEX `idx_account_no`(`account_no` ASC) USING BTREE COMMENT '账号索引',
  INDEX `idx_contract_no`(`contract_no` ASC) USING BTREE COMMENT '合同编号索引',
  INDEX `idx_holding_status`(`holding_status` ASC) USING BTREE COMMENT '持有状态索引',
  INDEX `idx_related_account_type`(`related_account_type` ASC) USING BTREE COMMENT '关联账户类型索引',
  INDEX `idx_open_date`(`open_date` ASC) USING BTREE COMMENT '开户日期索引',
  INDEX `idx_mature_date`(`mature_date` ASC) USING BTREE COMMENT '到期日期索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引',
  UNIQUE INDEX `uk_customer_product_account`(`customer_id` ASC, `product_id` ASC, `account_no` ASC, `deleted` ASC) USING BTREE COMMENT '客户产品账号唯一索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户产品持有情况表（客户与产品的多对多关系）';

-- ==================== 字典类型和字典数据 ====================

-- 1. 产品类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM产品类型', 'aicrm_product_type', 0, '产品类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM产品类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '存款产品', 'deposit', 'aicrm_product_type', 0, 'primary', '存款类产品', '1', NOW(), '1', NOW(), b'0'),
(2, '贷款产品', 'loan', 'aicrm_product_type', 0, 'success', '贷款类产品', '1', NOW(), '1', NOW(), b'0'),
(3, '理财产品', 'wealth', 'aicrm_product_type', 0, 'info', '理财类产品', '1', NOW(), '1', NOW(), b'0'),
(4, '基金产品', 'fund', 'aicrm_product_type', 0, 'warning', '基金类产品', '1', NOW(), '1', NOW(), b'0'),
(5, '保险产品', 'insurance', 'aicrm_product_type', 0, 'default', '保险类产品', '1', NOW(), '1', NOW(), b'0'),
(6, '信托产品', 'trust', 'aicrm_product_type', 0, 'default', '信托类产品', '1', NOW(), '1', NOW(), b'0'),
(7, '贵金属产品', 'metal', 'aicrm_product_type', 0, 'default', '贵金属类产品', '1', NOW(), '1', NOW(), b'0'),
(8, '信用卡产品', 'creditcard', 'aicrm_product_type', 0, 'default', '信用卡类产品', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 适用客户类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM产品适用客户类型', 'aicrm_customer_type_scope', 0, '产品适用客户类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM产品适用客户类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '零售客户', 'retail', 'aicrm_customer_type_scope', 0, 'primary', '仅限零售客户', '1', NOW(), '1', NOW(), b'0'),
(2, '对公客户', 'company', 'aicrm_customer_type_scope', 0, 'success', '仅限对公客户', '1', NOW(), '1', NOW(), b'0'),
(3, '全部客户', 'all', 'aicrm_customer_type_scope', 0, 'info', '零售和对公客户均适用', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 持有状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM产品持有状态', 'aicrm_holding_status', 0, '产品持有状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM产品持有状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '持有中', 'holding', 'aicrm_holding_status', 0, 'success', '正在持有', '1', NOW(), '1', NOW(), b'0'),
(2, '已到期', 'matured', 'aicrm_holding_status', 0, 'info', '产品已到期', '1', NOW(), '1', NOW(), b'0'),
(3, '已赎回', 'redeemed', 'aicrm_holding_status', 0, 'warning', '已赎回', '1', NOW(), '1', NOW(), b'0'),
(4, '已结清', 'settled', 'aicrm_holding_status', 0, 'default', '已结清（贷款）', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
