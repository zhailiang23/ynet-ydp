-- ----------------------------
-- 客户账户信息表（8张表）
-- 设计原则:
-- 1. 严格按照图片字段设计（账号、产品名称、户名、开户日期、销户日期、账户状态、利率、存期、余额）
-- 2. 参考老系统 acrm_f_ci_deposit_act（存款）和 acrm_f_agr_loan（贷款）表
-- 3. 支持零售客户和对公客户
-- 4. 每种账户类型一张独立表
-- ----------------------------

-- ==================== 1. 存款账户信息表 ====================
DROP TABLE IF EXISTS `crm_customer_deposit_account`;
CREATE TABLE `crm_customer_deposit_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '存款账户主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 账户基本信息（图片字段）====================
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名称',
  `account_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '户名',
  `open_date` date NOT NULL COMMENT '开户日期',
  `close_date` date NULL DEFAULT NULL COMMENT '销户日期',
  `account_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户状态（字典: aicrm_account_status，normal=正常，closed=销户，frozen=冻结）',
  `interest_rate` decimal(10,6) NULL DEFAULT NULL COMMENT '利率（%）',
  `deposit_term` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '存期（如：1年、6个月、3个月、活期）',
  `balance` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '余额',

  -- ==================== 老系统字段（acrm_f_ci_deposit_act）====================
  `agr_no` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '协议号',
  `product_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品ID',
  `card_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '卡号',
  `deposit_type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '存款类型（1=活期，2=定期）',
  `currency_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'CNY' COMMENT '币种（字典: aicrm_currency_type）',
  `original_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '原始金额（开户金额）',
  `mature_date` date NULL DEFAULT NULL COMMENT '到期日',
  `start_interest_date` date NULL DEFAULT NULL COMMENT '起息日',

  -- ==================== 管理信息 ====================
  `dept_id` bigint NULL DEFAULT NULL COMMENT '开户机构ID（关联 system_dept.id）',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开户机构名称',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '客户经理用户ID（关联 system_users.id）',

  -- ==================== 统计字段 ====================
  `month_avg_balance` decimal(24,6) NULL DEFAULT NULL COMMENT '月日均余额',
  `quarter_avg_balance` decimal(24,6) NULL DEFAULT NULL COMMENT '季日均余额',
  `year_avg_balance` decimal(24,6) NULL DEFAULT NULL COMMENT '年日均余额',
  `month_total_in` decimal(24,6) NULL DEFAULT NULL COMMENT '月累计转入',
  `month_total_out` decimal(24,6) NULL DEFAULT NULL COMMENT '月累计转出',

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
  UNIQUE INDEX `uk_account_no`(`account_no` ASC, `deleted` ASC) USING BTREE COMMENT '账号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_account_status`(`account_status` ASC) USING BTREE COMMENT '账户状态索引',
  INDEX `idx_product_id`(`product_id` ASC) USING BTREE COMMENT '产品ID索引',
  INDEX `idx_dept_id`(`dept_id` ASC) USING BTREE COMMENT '机构ID索引',
  INDEX `idx_open_date`(`open_date` ASC) USING BTREE COMMENT '开户日期索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户存款账户信息表（零售+对公共用）';

-- ==================== 2. 贷款账户信息表 ====================
DROP TABLE IF EXISTS `crm_customer_loan_account`;
CREATE TABLE `crm_customer_loan_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '贷款账户主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 账户基本信息 ====================
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '贷款账号',
  `contract_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '合同号',
  `agr_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '协议号',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '贷款产品名称',
  `product_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品ID',
  `account_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '借款人姓名',
  `open_date` date NOT NULL COMMENT '放款日期（开户日期）',
  `close_date` date NULL DEFAULT NULL COMMENT '结清日期（销户日期）',
  `account_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户状态（字典: aicrm_loan_account_status，normal=正常，overdue=逾期，settled=已结清，writeoff=核销）',

  -- ==================== 贷款金额信息 ====================
  `contract_amount` decimal(24,6) NOT NULL COMMENT '合同金额（授信额度）',
  `loan_amount` decimal(24,6) NOT NULL COMMENT '贷款金额（实际发放金额）',
  `balance` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '贷款余额',
  `currency_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'CNY' COMMENT '币种（字典: aicrm_currency_type）',

  -- ==================== 贷款利率和期限 ====================
  `interest_rate` decimal(13,9) NOT NULL COMMENT '贷款利率（年化%）',
  `loan_term` decimal(22,0) NOT NULL COMMENT '贷款期限（月）',
  `loan_term_unit` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'month' COMMENT '期限单位（字典: aicrm_loan_term_unit，day=天，month=月，year=年）',
  `mature_date` date NOT NULL COMMENT '到期日',
  `repayment_mode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '还款方式（字典: aicrm_repayment_mode）',

  -- ==================== 贷款用途和担保 ====================
  `loan_purpose` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '贷款用途',
  `loan_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '贷款类型（字典: aicrm_loan_type）',
  `guarantee_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '担保方式（字典: aicrm_guarantee_type）',
  `business_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务类型',

  -- ==================== 五级分类和逾期信息 ====================
  `five_level_class` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '五级分类（字典: aicrm_five_level_class，normal=正常，concern=关注，secondary=次级，doubtful=可疑，loss=损失）',
  `overdue_days` int NULL DEFAULT 0 COMMENT '逾期天数',
  `overdue_principal` decimal(24,4) NULL DEFAULT NULL COMMENT '逾期本金',
  `overdue_interest` decimal(24,4) NULL DEFAULT NULL COMMENT '逾期利息',
  `overdue_times` int NULL DEFAULT 0 COMMENT '累计逾期次数',

  -- ==================== 管理信息 ====================
  `dept_id` bigint NULL DEFAULT NULL COMMENT '放款机构ID（关联 system_dept.id）',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '放款机构名称',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '客户经理用户ID（关联 system_users.id）',

  -- ==================== 统计字段 ====================
  `month_avg_balance` decimal(24,4) NULL DEFAULT NULL COMMENT '月日均余额',
  `quarter_avg_balance` decimal(24,4) NULL DEFAULT NULL COMMENT '季日均余额',
  `year_avg_balance` decimal(24,4) NULL DEFAULT NULL COMMENT '年日均余额',
  `total_repaid_amount` decimal(24,4) NULL DEFAULT NULL COMMENT '累计还款金额',
  `total_repaid_interest` decimal(24,4) NULL DEFAULT NULL COMMENT '累计还款利息',

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
  UNIQUE INDEX `uk_account_no`(`account_no` ASC, `deleted` ASC) USING BTREE COMMENT '账号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_contract_no`(`contract_no` ASC) USING BTREE COMMENT '合同号索引',
  INDEX `idx_account_status`(`account_status` ASC) USING BTREE COMMENT '账户状态索引',
  INDEX `idx_five_level_class`(`five_level_class` ASC) USING BTREE COMMENT '五级分类索引',
  INDEX `idx_dept_id`(`dept_id` ASC) USING BTREE COMMENT '机构ID索引',
  INDEX `idx_open_date`(`open_date` ASC) USING BTREE COMMENT '放款日期索引',
  INDEX `idx_mature_date`(`mature_date` ASC) USING BTREE COMMENT '到期日索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户贷款账户信息表（零售+对公共用）';

-- ==================== 3. 理财账户信息表 ====================
DROP TABLE IF EXISTS `crm_customer_wealth_account`;
CREATE TABLE `crm_customer_wealth_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '理财账户主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 账户基本信息 ====================
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '理财账号',
  `product_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '理财产品代码',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '理财产品名称',
  `account_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '户名',
  `open_date` date NOT NULL COMMENT '开户日期（购买日期）',
  `close_date` date NULL DEFAULT NULL COMMENT '销户日期（赎回/到期日期）',
  `account_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户状态（字典: aicrm_wealth_account_status，holding=持有中，matured=已到期，redeemed=已赎回）',

  -- ==================== 理财产品信息 ====================
  `product_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）',
  `risk_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '风险等级（字典: aicrm_risk_level，R1-R5）',
  `expected_return_rate` decimal(10,6) NULL DEFAULT NULL COMMENT '预期收益率（年化%）',
  `actual_return_rate` decimal(10,6) NULL DEFAULT NULL COMMENT '实际收益率（年化%）',
  `wealth_term` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '理财期限（如：90天、180天、1年）',
  `currency_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'CNY' COMMENT '币种',

  -- ==================== 金额信息 ====================
  `purchase_amount` decimal(24,6) NOT NULL COMMENT '购买金额',
  `current_value` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '当前市值',
  `accumulated_income` decimal(24,6) NULL DEFAULT 0 COMMENT '累计收益',
  `balance` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '持有份额/余额',

  -- ==================== 日期信息 ====================
  `value_date` date NULL DEFAULT NULL COMMENT '起息日',
  `mature_date` date NULL DEFAULT NULL COMMENT '到期日',
  `next_open_date` date NULL DEFAULT NULL COMMENT '下次开放日（开放式理财）',

  -- ==================== 管理信息 ====================
  `dept_id` bigint NULL DEFAULT NULL COMMENT '销售机构ID',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '销售机构名称',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '理财顾问用户ID',

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
  INDEX `idx_account_no`(`account_no` ASC) USING BTREE COMMENT '账号索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_product_code`(`product_code` ASC) USING BTREE COMMENT '产品代码索引',
  INDEX `idx_account_status`(`account_status` ASC) USING BTREE COMMENT '账户状态索引',
  INDEX `idx_open_date`(`open_date` ASC) USING BTREE COMMENT '购买日期索引',
  INDEX `idx_mature_date`(`mature_date` ASC) USING BTREE COMMENT '到期日索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户理财账户信息表（零售+对公共用）';

-- ==================== 4. 基金账户信息表 ====================
DROP TABLE IF EXISTS `crm_customer_fund_account`;
CREATE TABLE `crm_customer_fund_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '基金账户主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 账户基本信息 ====================
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '基金账号',
  `fund_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '基金代码',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '基金名称',
  `account_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '户名',
  `open_date` date NOT NULL COMMENT '开户日期（首次申购日期）',
  `close_date` date NULL DEFAULT NULL COMMENT '销户日期（全部赎回日期）',
  `account_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）',

  -- ==================== 基金产品信息 ====================
  `fund_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）',
  `fund_company` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '基金公司',
  `risk_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '风险等级（字典: aicrm_risk_level）',
  `currency_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'CNY' COMMENT '币种',

  -- ==================== 金额和份额信息 ====================
  `holding_shares` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '持有份额',
  `purchase_amount` decimal(24,6) NOT NULL COMMENT '累计申购金额',
  `current_nav` decimal(10,4) NULL DEFAULT NULL COMMENT '当前净值',
  `current_value` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '当前市值',
  `accumulated_income` decimal(24,6) NULL DEFAULT 0 COMMENT '累计收益',
  `balance` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '账户余额（现金部分）',

  -- ==================== 收益信息 ====================
  `cost_price` decimal(10,4) NULL DEFAULT NULL COMMENT '成本价',
  `profit_rate` decimal(10,4) NULL DEFAULT NULL COMMENT '收益率（%）',
  `today_income` decimal(24,6) NULL DEFAULT NULL COMMENT '今日收益',
  `yesterday_income` decimal(24,6) NULL DEFAULT NULL COMMENT '昨日收益',

  -- ==================== 管理信息 ====================
  `dept_id` bigint NULL DEFAULT NULL COMMENT '销售机构ID',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '销售机构名称',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '基金顾问用户ID',

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
  INDEX `idx_account_no`(`account_no` ASC) USING BTREE COMMENT '账号索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_fund_code`(`fund_code` ASC) USING BTREE COMMENT '基金代码索引',
  INDEX `idx_account_status`(`account_status` ASC) USING BTREE COMMENT '账户状态索引',
  INDEX `idx_fund_type`(`fund_type` ASC) USING BTREE COMMENT '基金类型索引',
  INDEX `idx_open_date`(`open_date` ASC) USING BTREE COMMENT '开户日期索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户基金账户信息表（零售+对公共用）';

-- ==================== 5. 保险账户信息表 ====================
DROP TABLE IF EXISTS `crm_customer_insurance_account`;
CREATE TABLE `crm_customer_insurance_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '保险账户主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 账户基本信息 ====================
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '保单号',
  `policy_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '保险单号',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '保险产品名称',
  `account_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投保人姓名',
  `open_date` date NOT NULL COMMENT '投保日期（开户日期）',
  `close_date` date NULL DEFAULT NULL COMMENT '退保日期（销户日期）',
  `account_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）',

  -- ==================== 保险产品信息 ====================
  `insurance_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）',
  `insurance_company` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '保险公司',
  `insurance_term` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '保险期限（如：终身、20年、至70岁）',
  `payment_term` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '缴费期限（如：趸交、5年、10年、20年）',
  `payment_frequency` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）',

  -- ==================== 保险金额信息 ====================
  `insured_amount` decimal(24,6) NOT NULL COMMENT '保险金额（保额）',
  `premium` decimal(24,6) NOT NULL COMMENT '保费（年交保费）',
  `paid_premium` decimal(24,6) NULL DEFAULT 0 COMMENT '已交保费',
  `cash_value` decimal(24,6) NULL DEFAULT 0 COMMENT '现金价值',
  `balance` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '账户价值（万能险、投连险）',
  `currency_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'CNY' COMMENT '币种',

  -- ==================== 被保险人信息 ====================
  `insured_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '被保险人姓名',
  `insured_relation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '与投保人关系（字典: aicrm_insured_relation）',
  `beneficiary_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人姓名',

  -- ==================== 日期信息 ====================
  `effective_date` date NULL DEFAULT NULL COMMENT '生效日期',
  `expire_date` date NULL DEFAULT NULL COMMENT '到期日期',
  `next_payment_date` date NULL DEFAULT NULL COMMENT '下次缴费日期',

  -- ==================== 管理信息 ====================
  `dept_id` bigint NULL DEFAULT NULL COMMENT '销售机构ID',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '销售机构名称',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '保险顾问用户ID',

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
  UNIQUE INDEX `uk_account_no`(`account_no` ASC, `deleted` ASC) USING BTREE COMMENT '保单号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_policy_no`(`policy_no` ASC) USING BTREE COMMENT '保险单号索引',
  INDEX `idx_account_status`(`account_status` ASC) USING BTREE COMMENT '账户状态索引',
  INDEX `idx_insurance_type`(`insurance_type` ASC) USING BTREE COMMENT '保险类型索引',
  INDEX `idx_open_date`(`open_date` ASC) USING BTREE COMMENT '投保日期索引',
  INDEX `idx_expire_date`(`expire_date` ASC) USING BTREE COMMENT '到期日期索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户保险账户信息表（零售+对公共用）';

-- ==================== 6. 信托账户信息表 ====================
DROP TABLE IF EXISTS `crm_customer_trust_account`;
CREATE TABLE `crm_customer_trust_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '信托账户主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 账户基本信息 ====================
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '信托账号',
  `trust_contract_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '信托合同号',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '信托产品名称',
  `account_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '委托人姓名',
  `open_date` date NOT NULL COMMENT '成立日期（开户日期）',
  `close_date` date NULL DEFAULT NULL COMMENT '终止日期（销户日期）',
  `account_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）',

  -- ==================== 信托产品信息 ====================
  `trust_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）',
  `trust_company` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '信托公司',
  `trust_purpose` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '信托目的',
  `expected_return_rate` decimal(10,6) NULL DEFAULT NULL COMMENT '预期收益率（年化%）',
  `trust_term` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '信托期限（如：2年、3年、5年）',
  `currency_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'CNY' COMMENT '币种',

  -- ==================== 金额信息 ====================
  `trust_amount` decimal(24,6) NOT NULL COMMENT '信托金额',
  `paid_amount` decimal(24,6) NULL DEFAULT 0 COMMENT '已支付金额',
  `current_value` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '当前价值',
  `accumulated_income` decimal(24,6) NULL DEFAULT 0 COMMENT '累计收益',
  `balance` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '账户余额',

  -- ==================== 受益人信息 ====================
  `beneficiary_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '受益人姓名',
  `beneficiary_relation` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '与委托人关系',

  -- ==================== 日期信息 ====================
  `effective_date` date NULL DEFAULT NULL COMMENT '生效日期',
  `mature_date` date NULL DEFAULT NULL COMMENT '到期日',
  `next_distribution_date` date NULL DEFAULT NULL COMMENT '下次分配日',

  -- ==================== 管理信息 ====================
  `dept_id` bigint NULL DEFAULT NULL COMMENT '销售机构ID',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '销售机构名称',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '信托顾问用户ID',

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
  UNIQUE INDEX `uk_account_no`(`account_no` ASC, `deleted` ASC) USING BTREE COMMENT '信托账号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_trust_contract_no`(`trust_contract_no` ASC) USING BTREE COMMENT '信托合同号索引',
  INDEX `idx_account_status`(`account_status` ASC) USING BTREE COMMENT '账户状态索引',
  INDEX `idx_trust_type`(`trust_type` ASC) USING BTREE COMMENT '信托类型索引',
  INDEX `idx_open_date`(`open_date` ASC) USING BTREE COMMENT '成立日期索引',
  INDEX `idx_mature_date`(`mature_date` ASC) USING BTREE COMMENT '到期日索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户信托账户信息表（零售+对公共用）';

-- ==================== 7. 贵金属账户信息表 ====================
DROP TABLE IF EXISTS `crm_customer_metal_account`;
CREATE TABLE `crm_customer_metal_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '贵金属账户主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 账户基本信息 ====================
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '贵金属账号',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '产品名称',
  `account_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '户名',
  `open_date` date NOT NULL COMMENT '开户日期',
  `close_date` date NULL DEFAULT NULL COMMENT '销户日期',
  `account_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）',

  -- ==================== 贵金属产品信息 ====================
  `metal_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）',
  `metal_category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）',
  `product_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '产品代码',

  -- ==================== 持仓信息 ====================
  `holding_weight` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '持有重量（克）',
  `average_cost` decimal(18,6) NULL DEFAULT NULL COMMENT '平均成本（元/克）',
  `current_price` decimal(18,6) NULL DEFAULT NULL COMMENT '当前价格（元/克）',
  `current_value` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '当前市值（元）',
  `accumulated_income` decimal(24,6) NULL DEFAULT 0 COMMENT '累计收益（元）',
  `profit_rate` decimal(10,4) NULL DEFAULT NULL COMMENT '收益率（%）',
  `balance` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '账户余额（元）',

  -- ==================== 交易信息 ====================
  `total_buy_weight` decimal(24,6) NULL DEFAULT 0 COMMENT '累计买入重量（克）',
  `total_buy_amount` decimal(24,6) NULL DEFAULT 0 COMMENT '累计买入金额（元）',
  `total_sell_weight` decimal(24,6) NULL DEFAULT 0 COMMENT '累计卖出重量（克）',
  `total_sell_amount` decimal(24,6) NULL DEFAULT 0 COMMENT '累计卖出金额（元）',
  `currency_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'CNY' COMMENT '币种',

  -- ==================== 管理信息 ====================
  `dept_id` bigint NULL DEFAULT NULL COMMENT '开户机构ID',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开户机构名称',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '客户经理用户ID',

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
  UNIQUE INDEX `uk_account_no`(`account_no` ASC, `deleted` ASC) USING BTREE COMMENT '账号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_account_status`(`account_status` ASC) USING BTREE COMMENT '账户状态索引',
  INDEX `idx_metal_type`(`metal_type` ASC) USING BTREE COMMENT '贵金属类型索引',
  INDEX `idx_metal_category`(`metal_category` ASC) USING BTREE COMMENT '产品类别索引',
  INDEX `idx_open_date`(`open_date` ASC) USING BTREE COMMENT '开户日期索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户贵金属账户信息表（零售+对公共用）';

-- ==================== 8. 信用卡账户信息表 ====================
DROP TABLE IF EXISTS `crm_customer_creditcard_account`;
CREATE TABLE `crm_customer_creditcard_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '信用卡账户主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表，仅限零售客户）',

  -- ==================== 账户基本信息 ====================
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '信用卡账号',
  `card_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '信用卡号（加密存储）',
  `product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '信用卡产品名称',
  `account_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '持卡人姓名',
  `open_date` date NOT NULL COMMENT '开卡日期',
  `close_date` date NULL DEFAULT NULL COMMENT '销卡日期',
  `account_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户状态（字典: aicrm_creditcard_status，normal=正常，frozen=冻结，overdue=逾期，closed=已销卡）',

  -- ==================== 信用卡信息 ====================
  `card_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '卡片类型（字典: aicrm_card_type，standard=普卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
  `card_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '卡等级',
  `card_brand` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '卡组织（字典: aicrm_card_brand，visa=VISA，mastercard=万事达，unionpay=银联）',
  `is_main_card` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否主卡（0=附属卡，1=主卡）',
  `main_card_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '主卡卡号（附属卡关联）',
  `currency_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'CNY' COMMENT '币种',

  -- ==================== 额度信息 ====================
  `credit_limit` decimal(24,6) NOT NULL COMMENT '信用额度',
  `available_limit` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '可用额度',
  `temporary_limit` decimal(24,6) NULL DEFAULT 0 COMMENT '临时额度',
  `cash_limit` decimal(24,6) NULL DEFAULT 0 COMMENT '取现额度',
  `used_amount` decimal(24,6) NULL DEFAULT 0 COMMENT '已用额度',
  `balance` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '当前欠款余额',

  -- ==================== 账单信息 ====================
  `billing_day` int NULL DEFAULT NULL COMMENT '账单日（每月几号）',
  `payment_due_day` int NULL DEFAULT NULL COMMENT '还款日（每月几号）',
  `current_bill_amount` decimal(24,6) NULL DEFAULT 0 COMMENT '本期账单金额',
  `min_payment_amount` decimal(24,6) NULL DEFAULT 0 COMMENT '最低还款额',
  `unpaid_amount` decimal(24,6) NULL DEFAULT 0 COMMENT '未还金额',
  `last_payment_date` date NULL DEFAULT NULL COMMENT '上次还款日期',
  `last_payment_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '上次还款金额',

  -- ==================== 逾期信息 ====================
  `overdue_days` int NULL DEFAULT 0 COMMENT '逾期天数',
  `overdue_amount` decimal(24,6) NULL DEFAULT 0 COMMENT '逾期金额',
  `overdue_interest` decimal(24,6) NULL DEFAULT 0 COMMENT '逾期利息',
  `overdue_times` int NULL DEFAULT 0 COMMENT '累计逾期次数',

  -- ==================== 积分信息 ====================
  `total_points` bigint NULL DEFAULT 0 COMMENT '累计积分',
  `available_points` bigint NULL DEFAULT 0 COMMENT '可用积分',
  `points_expire_date` date NULL DEFAULT NULL COMMENT '积分到期日',

  -- ==================== 日期信息 ====================
  `expire_date` date NULL DEFAULT NULL COMMENT '卡片有效期',
  `activate_date` date NULL DEFAULT NULL COMMENT '激活日期',
  `last_transaction_date` date NULL DEFAULT NULL COMMENT '最后交易日期',

  -- ==================== 管理信息 ====================
  `dept_id` bigint NULL DEFAULT NULL COMMENT '发卡机构ID',
  `dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发卡机构名称',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '客户经理用户ID',

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
  UNIQUE INDEX `uk_account_no`(`account_no` ASC, `deleted` ASC) USING BTREE COMMENT '账号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_card_no`(`card_no` ASC) USING BTREE COMMENT '信用卡号索引',
  INDEX `idx_account_status`(`account_status` ASC) USING BTREE COMMENT '账户状态索引',
  INDEX `idx_card_type`(`card_type` ASC) USING BTREE COMMENT '卡片类型索引',
  INDEX `idx_open_date`(`open_date` ASC) USING BTREE COMMENT '开卡日期索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户信用卡账户信息表（仅限零售客户）';

-- ==================== 字典类型和字典数据 ====================

-- 1. 账户状态字典（存款账户）
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM账户状态', 'aicrm_account_status', 0, '存款账户状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM账户状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '正常', 'normal', 'aicrm_account_status', 0, 'success', '账户正常', '1', NOW(), '1', NOW(), b'0'),
(2, '销户', 'closed', 'aicrm_account_status', 0, 'info', '账户已销户', '1', NOW(), '1', NOW(), b'0'),
(3, '冻结', 'frozen', 'aicrm_account_status', 0, 'warning', '账户冻结', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 币种字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM币种', 'aicrm_currency_type', 0, '币种类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM币种';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '人民币', 'CNY', 'aicrm_currency_type', 0, 'primary', '人民币', '1', NOW(), '1', NOW(), b'0'),
(2, '美元', 'USD', 'aicrm_currency_type', 0, 'success', '美元', '1', NOW(), '1', NOW(), b'0'),
(3, '欧元', 'EUR', 'aicrm_currency_type', 0, 'info', '欧元', '1', NOW(), '1', NOW(), b'0'),
(4, '日元', 'JPY', 'aicrm_currency_type', 0, 'warning', '日元', '1', NOW(), '1', NOW(), b'0'),
(5, '港币', 'HKD', 'aicrm_currency_type', 0, 'default', '港币', '1', NOW(), '1', NOW(), b'0'),
(6, '英镑', 'GBP', 'aicrm_currency_type', 0, 'default', '英镑', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 贷款账户状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM贷款账户状态', 'aicrm_loan_account_status', 0, '贷款账户状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM贷款账户状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '正常', 'normal', 'aicrm_loan_account_status', 0, 'success', '贷款正常', '1', NOW(), '1', NOW(), b'0'),
(2, '逾期', 'overdue', 'aicrm_loan_account_status', 0, 'danger', '贷款逾期', '1', NOW(), '1', NOW(), b'0'),
(3, '已结清', 'settled', 'aicrm_loan_account_status', 0, 'info', '贷款已结清', '1', NOW(), '1', NOW(), b'0'),
(4, '核销', 'writeoff', 'aicrm_loan_account_status', 0, 'warning', '贷款核销', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 五级分类字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM贷款五级分类', 'aicrm_five_level_class', 0, '贷款五级分类', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM贷款五级分类';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '正常', 'normal', 'aicrm_five_level_class', 0, 'success', '正常类', '1', NOW(), '1', NOW(), b'0'),
(2, '关注', 'concern', 'aicrm_five_level_class', 0, 'primary', '关注类', '1', NOW(), '1', NOW(), b'0'),
(3, '次级', 'secondary', 'aicrm_five_level_class', 0, 'warning', '次级类', '1', NOW(), '1', NOW(), b'0'),
(4, '可疑', 'doubtful', 'aicrm_five_level_class', 0, 'danger', '可疑类', '1', NOW(), '1', NOW(), b'0'),
(5, '损失', 'loss', 'aicrm_five_level_class', 0, 'danger', '损失类', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 更多字典数据（理财、基金、保险、信托、贵金属、信用卡）根据需要添加...
