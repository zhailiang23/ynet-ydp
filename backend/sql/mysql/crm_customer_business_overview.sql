-- =====================================================
-- 客户业务概览表 - 6张表
-- =====================================================
-- 1. 贷款业务概览表
-- 2. 存款业务概览表
-- 3. 中间业务概览表
-- 4. 渠道业务概览表
-- 5. 贴现业务概览表
-- 6. 表外业务概览表
-- =====================================================

USE `ruoyi-vue-pro`;

-- =====================================================
-- 1. 贷款业务概览表
-- =====================================================
CREATE TABLE IF NOT EXISTS `crm_customer_overview_loan` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `statistics_date` date NOT NULL COMMENT '统计日期',

  -- 基本信息
  `loan_type` varchar(50) DEFAULT NULL COMMENT '贷款类型',
  `currency` varchar(20) DEFAULT '人民币' COMMENT '币种',
  `loan_amount` decimal(20,2) DEFAULT 0.00 COMMENT '贷款余额',

  -- 账户信息
  `loan_account_count` int DEFAULT 0 COMMENT '贷款账户数',
  `loan_customer_count` int DEFAULT 0 COMMENT '贷款客户数',

  -- 业务余额
  `normal_business_balance` decimal(20,2) DEFAULT 0.00 COMMENT '正常业务余额',
  `overdue_balance` decimal(20,2) DEFAULT 0.00 COMMENT '逾期余额',
  `bad_loan_balance` decimal(20,2) DEFAULT 0.00 COMMENT '不良贷款余额',
  `business_balance` decimal(20,2) DEFAULT 0.00 COMMENT '业务余额',

  -- 平均余额 - 年度
  `balance_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本年余额日均',
  `last_year_balance_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年余额日均',

  -- 平均余额 - 季度
  `balance_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本季度余额日均',
  `last_year_balance_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期季度余额日均',

  -- 平均余额 - 月度
  `balance_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本月余额日均',
  `last_year_balance_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期月余额日均',

  -- 贷款金额
  `loan_amount_total` decimal(20,2) DEFAULT 0.00 COMMENT '贷款金额',
  `last_year_loan_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年贷款余额',
  `business_amount` decimal(20,2) DEFAULT 0.00 COMMENT '业务金额',
  `credit_total_amount` decimal(20,2) DEFAULT 0.00 COMMENT '授信总额',

  -- 平均金额 - 年度
  `amount_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本年金额日均',
  `last_year_amount_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年金额日均',

  -- 平均金额 - 季度
  `amount_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本季度金额日均',
  `last_year_amount_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期季度金额日均',

  -- 平均金额 - 月度
  `amount_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本月金额日均',
  `last_year_amount_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期月金额日均',

  -- 贷款使用率
  `outside_loan_usage` decimal(10,4) DEFAULT 0.0000 COMMENT '表外贷款使用率',
  `inside_loan_usage` decimal(10,4) DEFAULT 0.0000 COMMENT '表内贷款使用率',
  `bill_loan_usage` decimal(10,4) DEFAULT 0.0000 COMMENT '票据贷款使用率',

  -- 收益信息
  `loan_profit` decimal(20,2) DEFAULT 0.00 COMMENT '贷款利润贡献',
  `interest_rate` decimal(10,6) DEFAULT 0.000000 COMMENT '贷款利率',

  -- 风险信息
  `overdue_days` int DEFAULT 0 COMMENT '逾期天数',
  `risk_level` varchar(20) DEFAULT NULL COMMENT '风险等级',

  -- 其他
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',

  -- 系统字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_customer_no` (`customer_no`),
  KEY `idx_statistics_date` (`statistics_date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户贷款业务概览表';

-- =====================================================
-- 2. 存款业务概览表
-- =====================================================
CREATE TABLE IF NOT EXISTS `crm_customer_overview_deposit` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `statistics_date` date NOT NULL COMMENT '统计日期',

  -- 基本信息
  `deposit_type` varchar(50) DEFAULT NULL COMMENT '存款类型',
  `currency` varchar(20) DEFAULT '人民币' COMMENT '币种',
  `account_no` varchar(35) DEFAULT NULL COMMENT '账号',
  `card_no` varchar(30) DEFAULT NULL COMMENT '卡号',

  -- 存款金额
  `deposit_balance` decimal(20,2) DEFAULT 0.00 COMMENT '存款余额',
  `business_amount` decimal(20,2) DEFAULT 0.00 COMMENT '业务金额',
  `original_amount` decimal(20,2) DEFAULT 0.00 COMMENT '原币金额',

  -- 账户信息
  `deposit_account_count` int DEFAULT 0 COMMENT '存款账户数',
  `account_status` varchar(20) DEFAULT NULL COMMENT '账户状态',
  `subject_code` varchar(20) DEFAULT NULL COMMENT '科目代码',
  `product_id` varchar(20) DEFAULT NULL COMMENT '产品ID',

  -- 平均余额 - 年度
  `balance_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本年余额日均',
  `real_balance_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本年实际余额日均',
  `deposit_cumulative_year` decimal(24,6) DEFAULT 0.000000 COMMENT '本年累计存款',

  -- 平均余额 - 季度
  `balance_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本季度余额日均',
  `real_balance_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本季度实际余额日均',
  `deposit_cumulative_quarter` decimal(24,6) DEFAULT 0.000000 COMMENT '本季度累计存款',

  -- 平均余额 - 月度
  `balance_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本月余额日均',
  `real_balance_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本月实际余额日均',
  `deposit_cumulative_month` decimal(24,6) DEFAULT 0.000000 COMMENT '本月累计存款',

  -- 流水信息
  `month_total_in` decimal(24,6) DEFAULT 0.000000 COMMENT '月度总流入',
  `month_total_out` decimal(24,6) DEFAULT 0.000000 COMMENT '月度总流出',
  `buy_amount` decimal(24,6) DEFAULT 0.000000 COMMENT '购买金额',

  -- 利率和收益
  `interest_rate` decimal(10,6) DEFAULT 0.000000 COMMENT '存款利率',
  `ftp_price` decimal(20,2) DEFAULT 0.00 COMMENT 'FTP定价',
  `deposit_profit` decimal(20,2) DEFAULT 0.00 COMMENT '存款利润贡献',

  -- 日期信息
  `open_date` date DEFAULT NULL COMMENT '开户日期',
  `start_interest_date` date DEFAULT NULL COMMENT '起息日期',
  `mature_date` date DEFAULT NULL COMMENT '到期日期',
  `logout_date` date DEFAULT NULL COMMENT '销户日期',

  -- 机构信息
  `org_no` varchar(20) DEFAULT NULL COMMENT '机构编号',
  `org_name` varchar(40) DEFAULT NULL COMMENT '机构名称',

  -- 其他
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',

  -- 系统字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_customer_no` (`customer_no`),
  KEY `idx_statistics_date` (`statistics_date`),
  KEY `idx_account_no` (`account_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户存款业务概览表';

-- =====================================================
-- 3. 中间业务概览表
-- =====================================================
CREATE TABLE IF NOT EXISTS `crm_customer_overview_middlebusiness` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `statistics_date` date NOT NULL COMMENT '统计日期',

  -- 基本信息
  `business_type` varchar(50) DEFAULT NULL COMMENT '业务类型',
  `business_code` varchar(50) DEFAULT NULL COMMENT '业务代码',
  `business_name` varchar(100) DEFAULT NULL COMMENT '业务名称',
  `currency` varchar(20) DEFAULT '人民币' COMMENT '币种',

  -- 业务金额
  `business_amount` decimal(20,2) DEFAULT 0.00 COMMENT '业务金额',
  `transaction_amount` decimal(20,2) DEFAULT 0.00 COMMENT '交易金额',
  `fee_amount` decimal(20,2) DEFAULT 0.00 COMMENT '手续费金额',
  `commission_amount` decimal(20,2) DEFAULT 0.00 COMMENT '佣金金额',

  -- 交易统计
  `transaction_count` int DEFAULT 0 COMMENT '交易笔数',

  -- 平均金额 - 年度
  `amount_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本年金额日均',
  `last_year_amount_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年金额日均',

  -- 平均金额 - 季度
  `amount_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本季度金额日均',
  `last_year_amount_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期季度金额日均',

  -- 平均金额 - 月度
  `amount_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本月金额日均',
  `last_year_amount_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期月金额日均',

  -- 累计金额 - 年度
  `cumulative_year_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本年累计金额',
  `last_year_cumulative_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年累计金额',

  -- 累计金额 - 季度
  `cumulative_quarter_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本季度累计金额',
  `last_year_cumulative_quarter_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期季度累计金额',

  -- 累计金额 - 月度
  `cumulative_month_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本月累计金额',
  `last_year_cumulative_month_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期月累计金额',

  -- 收益贡献
  `profit_contribution` decimal(20,2) DEFAULT 0.00 COMMENT '利润贡献',
  `fee_income` decimal(20,2) DEFAULT 0.00 COMMENT '手续费收入',
  `commission_income` decimal(20,2) DEFAULT 0.00 COMMENT '佣金收入',

  -- 其他
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',

  -- 系统字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_customer_no` (`customer_no`),
  KEY `idx_statistics_date` (`statistics_date`),
  KEY `idx_business_type` (`business_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户中间业务概览表';

-- =====================================================
-- 4. 渠道业务概览表
-- =====================================================
CREATE TABLE IF NOT EXISTS `crm_customer_overview_channel` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `statistics_date` date NOT NULL COMMENT '统计日期',

  -- 渠道信息
  `channel_type` varchar(50) DEFAULT NULL COMMENT '渠道类型',
  `channel_code` varchar(50) DEFAULT NULL COMMENT '渠道代码',
  `channel_name` varchar(100) DEFAULT NULL COMMENT '渠道名称',

  -- 使用统计
  `access_count` int DEFAULT 0 COMMENT '访问次数',
  `login_count` int DEFAULT 0 COMMENT '登录次数',
  `transaction_count` int DEFAULT 0 COMMENT '交易笔数',
  `active_days` int DEFAULT 0 COMMENT '活跃天数',

  -- 交易金额
  `transaction_amount` decimal(20,2) DEFAULT 0.00 COMMENT '交易金额',

  -- 平均交易金额 - 年度
  `amount_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本年交易金额日均',
  `last_year_amount_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年交易金额日均',

  -- 平均交易金额 - 季度
  `amount_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本季度交易金额日均',
  `last_year_amount_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期季度交易金额日均',

  -- 平均交易金额 - 月度
  `amount_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本月交易金额日均',
  `last_year_amount_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期月交易金额日均',

  -- 累计金额 - 年度
  `cumulative_year_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本年累计交易金额',
  `last_year_cumulative_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年累计交易金额',

  -- 累计金额 - 季度
  `cumulative_quarter_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本季度累计交易金额',
  `last_year_cumulative_quarter_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期季度累计交易金额',

  -- 累计金额 - 月度
  `cumulative_month_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本月累计交易金额',
  `last_year_cumulative_month_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期月累计交易金额',

  -- 时间信息
  `first_access_time` datetime DEFAULT NULL COMMENT '首次访问时间',
  `last_access_time` datetime DEFAULT NULL COMMENT '最近访问时间',
  `last_transaction_time` datetime DEFAULT NULL COMMENT '最近交易时间',

  -- 设备信息
  `device_type` varchar(50) DEFAULT NULL COMMENT '设备类型',
  `device_model` varchar(100) DEFAULT NULL COMMENT '设备型号',
  `os_version` varchar(50) DEFAULT NULL COMMENT '操作系统版本',
  `app_version` varchar(50) DEFAULT NULL COMMENT '应用版本',

  -- 偏好信息
  `preferred_business` varchar(200) DEFAULT NULL COMMENT '偏好业务',
  `usage_frequency` varchar(20) DEFAULT NULL COMMENT '使用频率',

  -- 其他
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',

  -- 系统字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_customer_no` (`customer_no`),
  KEY `idx_statistics_date` (`statistics_date`),
  KEY `idx_channel_type` (`channel_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户渠道业务概览表';

-- =====================================================
-- 5. 贴现业务概览表
-- =====================================================
CREATE TABLE IF NOT EXISTS `crm_customer_overview_discount` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `statistics_date` date NOT NULL COMMENT '统计日期',

  -- 基本信息
  `discount_type` varchar(50) DEFAULT NULL COMMENT '贴现类型',
  `bill_no` varchar(50) DEFAULT NULL COMMENT '票据号码',
  `bill_type` varchar(50) DEFAULT NULL COMMENT '票据类型',
  `currency` varchar(20) DEFAULT '人民币' COMMENT '币种',

  -- 金额信息
  `bill_amount` decimal(20,2) DEFAULT 0.00 COMMENT '票据金额',
  `discount_amount` decimal(20,2) DEFAULT 0.00 COMMENT '贴现金额',
  `discount_balance` decimal(20,2) DEFAULT 0.00 COMMENT '贴现余额',
  `interest_amount` decimal(20,2) DEFAULT 0.00 COMMENT '贴息金额',
  `actual_amount` decimal(20,2) DEFAULT 0.00 COMMENT '实付金额',

  -- 笔数统计
  `discount_count` int DEFAULT 0 COMMENT '贴现笔数',

  -- 平均余额 - 年度
  `balance_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本年余额日均',
  `last_year_balance_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年余额日均',

  -- 平均余额 - 季度
  `balance_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本季度余额日均',
  `last_year_balance_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期季度余额日均',

  -- 平均余额 - 月度
  `balance_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本月余额日均',
  `last_year_balance_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期月余额日均',

  -- 累计金额 - 年度
  `cumulative_year_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本年累计贴现金额',
  `last_year_cumulative_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年累计贴现金额',

  -- 累计金额 - 季度
  `cumulative_quarter_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本季度累计贴现金额',
  `last_year_cumulative_quarter_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期季度累计贴现金额',

  -- 累计金额 - 月度
  `cumulative_month_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本月累计贴现金额',
  `last_year_cumulative_month_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期月累计贴现金额',

  -- 利率信息
  `discount_rate` decimal(10,6) DEFAULT 0.000000 COMMENT '贴现利率',
  `discount_days` int DEFAULT 0 COMMENT '贴现天数',

  -- 日期信息
  `bill_issue_date` date DEFAULT NULL COMMENT '票据出票日期',
  `bill_due_date` date DEFAULT NULL COMMENT '票据到期日期',
  `discount_date` date DEFAULT NULL COMMENT '贴现日期',
  `settle_date` date DEFAULT NULL COMMENT '结算日期',

  -- 票据信息
  `drawer` varchar(100) DEFAULT NULL COMMENT '出票人',
  `payee` varchar(100) DEFAULT NULL COMMENT '收款人',
  `acceptor` varchar(100) DEFAULT NULL COMMENT '承兑人',
  `acceptance_bank` varchar(100) DEFAULT NULL COMMENT '承兑银行',

  -- 状态信息
  `discount_status` varchar(20) DEFAULT NULL COMMENT '贴现状态',
  `settlement_status` varchar(20) DEFAULT NULL COMMENT '结算状态',

  -- 收益信息
  `profit_contribution` decimal(20,2) DEFAULT 0.00 COMMENT '利润贡献',

  -- 其他
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',

  -- 系统字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_customer_no` (`customer_no`),
  KEY `idx_statistics_date` (`statistics_date`),
  KEY `idx_bill_no` (`bill_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户贴现业务概览表';

-- =====================================================
-- 6. 表外业务概览表
-- =====================================================
CREATE TABLE IF NOT EXISTS `crm_customer_overview_offbalance` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID',
  `customer_no` varchar(50) DEFAULT NULL COMMENT '客户编号',
  `statistics_date` date NOT NULL COMMENT '统计日期',

  -- 基本信息
  `business_type` varchar(50) DEFAULT NULL COMMENT '业务类型',
  `business_code` varchar(50) DEFAULT NULL COMMENT '业务代码',
  `business_name` varchar(100) DEFAULT NULL COMMENT '业务名称',
  `currency` varchar(20) DEFAULT '人民币' COMMENT '币种',

  -- 金额信息
  `business_amount` decimal(20,2) DEFAULT 0.00 COMMENT '业务金额',
  `business_balance` decimal(20,2) DEFAULT 0.00 COMMENT '业务余额',
  `guarantee_amount` decimal(20,2) DEFAULT 0.00 COMMENT '担保金额',
  `credit_amount` decimal(20,2) DEFAULT 0.00 COMMENT '授信金额',
  `used_amount` decimal(20,2) DEFAULT 0.00 COMMENT '已用金额',
  `available_amount` decimal(20,2) DEFAULT 0.00 COMMENT '可用金额',

  -- 笔数统计
  `business_count` int DEFAULT 0 COMMENT '业务笔数',

  -- 平均余额 - 年度
  `balance_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本年余额日均',
  `last_year_balance_year_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年余额日均',

  -- 平均余额 - 季度
  `balance_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本季度余额日均',
  `last_year_balance_quarter_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期季度余额日均',

  -- 平均余额 - 月度
  `balance_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '本月余额日均',
  `last_year_balance_month_avg` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期月余额日均',

  -- 累计金额 - 年度
  `cumulative_year_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本年累计金额',
  `last_year_cumulative_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年累计金额',

  -- 累计金额 - 季度
  `cumulative_quarter_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本季度累计金额',
  `last_year_cumulative_quarter_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期季度累计金额',

  -- 累计金额 - 月度
  `cumulative_month_amount` decimal(20,2) DEFAULT 0.00 COMMENT '本月累计金额',
  `last_year_cumulative_month_amount` decimal(20,2) DEFAULT 0.00 COMMENT '上年同期月累计金额',

  -- 使用率
  `usage_rate` decimal(10,4) DEFAULT 0.0000 COMMENT '使用率',

  -- 日期信息
  `start_date` date DEFAULT NULL COMMENT '起始日期',
  `end_date` date DEFAULT NULL COMMENT '到期日期',
  `effective_date` date DEFAULT NULL COMMENT '生效日期',

  -- 费率信息
  `fee_rate` decimal(10,6) DEFAULT 0.000000 COMMENT '费率',
  `fee_amount` decimal(20,2) DEFAULT 0.00 COMMENT '手续费金额',

  -- 状态信息
  `business_status` varchar(20) DEFAULT NULL COMMENT '业务状态',
  `risk_level` varchar(20) DEFAULT NULL COMMENT '风险等级',

  -- 收益信息
  `profit_contribution` decimal(20,2) DEFAULT 0.00 COMMENT '利润贡献',
  `fee_income` decimal(20,2) DEFAULT 0.00 COMMENT '手续费收入',

  -- 其他
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',

  -- 系统字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_customer_id` (`customer_id`),
  KEY `idx_customer_no` (`customer_no`),
  KEY `idx_statistics_date` (`statistics_date`),
  KEY `idx_business_type` (`business_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户表外业务概览表';

-- =====================================================
-- 测试数据生成
-- =====================================================

-- 1. 贷款业务概览测试数据
-- 为零售客户(ID: 1-10)和对公客户(ID: 1-10)各生成3-5条记录
INSERT INTO `crm_customer_overview_loan`
(`customer_id`, `customer_no`, `statistics_date`, `loan_type`, `currency`, `loan_amount`,
 `loan_account_count`, `normal_business_balance`, `business_balance`, `balance_year_avg`,
 `balance_month_avg`, `interest_rate`, `loan_profit`, `tenant_id`)
VALUES
-- 零售客户贷款数据
(1, '2006282699', '2022-02-28', '一般流动资金贷款', '人民币', 0.00, 1, 0.00, 0.00, 0.00, 0.00, 0.058000, 0.00, 1),
(1, '2006282699', '2023-06-15', '基本建设项目贷款', '人民币', 593600000.00, 2, 593600000.00, 593600000.00, 580000000.00, 590000000.00, 0.045000, 2670000.00, 1),
(1, '2006282699', '2024-01-20', '项目融资贷款', '人民币', 124900000.00, 1, 124900000.00, 124900000.00, 130000000.00, 125000000.00, 0.042000, 524580.00, 1),
(2, '2006282700', '2023-03-10', '个人住房贷款', '人民币', 2500000.00, 1, 2500000.00, 2500000.00, 2600000.00, 2550000.00, 0.049000, 122500.00, 1),
(2, '2006282700', '2023-09-22', '个人消费贷款', '人民币', 300000.00, 1, 300000.00, 300000.00, 350000.00, 320000.00, 0.063000, 18900.00, 1),
(2, '2006282700', '2024-05-08', '个人经营贷款', '人民币', 800000.00, 1, 800000.00, 800000.00, 750000.00, 800000.00, 0.055000, 44000.00, 1),
(3, '2006282701', '2023-07-15', '个人住房贷款', '人民币', 1800000.00, 1, 1800000.00, 1800000.00, 1850000.00, 1820000.00, 0.048000, 86400.00, 1),
(3, '2006282701', '2024-02-28', '个人汽车贷款', '人民币', 250000.00, 1, 250000.00, 250000.00, 280000.00, 260000.00, 0.058000, 14500.00, 1),
(4, '2006282702', '2023-04-20', '流动资金贷款', '人民币', 5000000.00, 2, 5000000.00, 5000000.00, 5200000.00, 5100000.00, 0.046000, 230000.00, 1),
(4, '2006282702', '2023-11-10', '固定资产贷款', '人民币', 3500000.00, 1, 3500000.00, 3500000.00, 3600000.00, 3550000.00, 0.047000, 164500.00, 1),
(4, '2006282702', '2024-06-05', '项目融资贷款', '人民币', 2000000.00, 1, 2000000.00, 2000000.00, 1800000.00, 2000000.00, 0.044000, 88000.00, 1),
(5, '2006282703', '2023-08-18', '个人住房贷款', '人民币', 3200000.00, 1, 3200000.00, 3200000.00, 3300000.00, 3250000.00, 0.047500, 152000.00, 1),
(5, '2006282703', '2024-03-25', '个人消费贷款', '人民币', 500000.00, 1, 500000.00, 500000.00, 450000.00, 480000.00, 0.065000, 32500.00, 1),
(6, '2006282704', '2023-05-30', '流动资金贷款', '人民币', 4200000.00, 2, 4200000.00, 4200000.00, 4000000.00, 4100000.00, 0.045500, 191100.00, 1),
(6, '2006282704', '2023-12-15', '固定资产贷款', '人民币', 6000000.00, 1, 6000000.00, 6000000.00, 5800000.00, 6000000.00, 0.048000, 288000.00, 1),
(6, '2006282704', '2024-07-20', '项目融资贷款', '人民币', 1500000.00, 1, 1500000.00, 1500000.00, 1600000.00, 1550000.00, 0.043000, 64500.00, 1),
(7, '2006282705', '2023-06-12', '个人住房贷款', '人民币', 2800000.00, 1, 2800000.00, 2800000.00, 2900000.00, 2850000.00, 0.049500, 138600.00, 1),
(7, '2006282705', '2024-01-08', '个人经营贷款', '人民币', 1200000.00, 1, 1200000.00, 1200000.00, 1100000.00, 1150000.00, 0.056000, 67200.00, 1),
(8, '2006282706', '2023-09-05', '流动资金贷款', '人民币', 7500000.00, 2, 7500000.00, 7500000.00, 7200000.00, 7400000.00, 0.046500, 348750.00, 1),
(8, '2006282706', '2024-04-18', '固定资产贷款', '人民币', 4500000.00, 1, 4500000.00, 4500000.00, 4300000.00, 4500000.00, 0.047500, 213750.00, 1),
(9, '2006282707', '2023-07-28', '个人住房贷款', '人民币', 3500000.00, 1, 3500000.00, 3500000.00, 3600000.00, 3550000.00, 0.048500, 169750.00, 1),
(9, '2006282707', '2024-02-14', '个人消费贷款', '人民币', 600000.00, 1, 600000.00, 600000.00, 550000.00, 580000.00, 0.064000, 38400.00, 1),
(9, '2006282707', '2024-08-10', '个人汽车贷款', '人民币', 350000.00, 1, 350000.00, 350000.00, 380000.00, 365000.00, 0.059000, 20650.00, 1),
(10, '2006282708', '2023-10-22', '流动资金贷款', '人民币', 9000000.00, 3, 9000000.00, 9000000.00, 8500000.00, 8800000.00, 0.045000, 405000.00, 1),
(10, '2006282708', '2024-05-30', '固定资产贷款', '人民币', 5500000.00, 1, 5500000.00, 5500000.00, 5300000.00, 5500000.00, 0.048500, 266750.00, 1);

-- 2. 存款业务概览测试数据
INSERT INTO `crm_customer_overview_deposit`
(`customer_id`, `customer_no`, `statistics_date`, `deposit_type`, `currency`, `deposit_balance`,
 `business_amount`, `balance_year_avg`, `balance_month_avg`, `interest_rate`, `ftp_price`,
 `open_date`, `account_status`, `tenant_id`)
VALUES
-- 零售客户存款数据
(1, '2006282699', '2024-01-15', '活期存款', '人民币', 850000.00, 850000.00, 820000.00, 835000.00, 0.003000, 820000.00, '2020-03-10', '正常', 1),
(1, '2006282699', '2024-01-15', '定期存款', '人民币', 5000000.00, 5000000.00, 4800000.00, 4950000.00, 0.015000, 4800000.00, '2023-01-15', '正常', 1),
(1, '2006282699', '2024-01-15', '通知存款', '人民币', 2000000.00, 2000000.00, 1950000.00, 1980000.00, 0.008000, 1950000.00, '2023-06-20', '正常', 1),
(2, '2006282700', '2024-01-15', '活期存款', '人民币', 320000.00, 320000.00, 310000.00, 315000.00, 0.003000, 310000.00, '2019-08-15', '正常', 1),
(2, '2006282700', '2024-01-15', '定期存款', '人民币', 1500000.00, 1500000.00, 1450000.00, 1480000.00, 0.015000, 1450000.00, '2023-03-20', '正常', 1),
(2, '2006282700', '2024-01-15', '结构性存款', '人民币', 800000.00, 800000.00, 750000.00, 780000.00, 0.025000, 750000.00, '2023-09-10', '正常', 1),
(3, '2006282701', '2024-01-15', '活期存款', '人民币', 280000.00, 280000.00, 270000.00, 275000.00, 0.003000, 270000.00, '2020-05-22', '正常', 1),
(3, '2006282701', '2024-01-15', '定期存款', '人民币', 1200000.00, 1200000.00, 1150000.00, 1180000.00, 0.015000, 1150000.00, '2023-05-15', '正常', 1),
(3, '2006282701', '2024-01-15', '零存整取', '人民币', 300000.00, 300000.00, 280000.00, 290000.00, 0.011000, 280000.00, '2022-01-10', '正常', 1),
(4, '2006282702', '2024-01-15', '活期存款', '人民币', 1200000.00, 1200000.00, 1150000.00, 1180000.00, 0.003000, 1150000.00, '2018-11-08', '正常', 1),
(4, '2006282702', '2024-01-15', '定期存款', '人民币', 8000000.00, 8000000.00, 7500000.00, 7800000.00, 0.015000, 7500000.00, '2023-02-10', '正常', 1),
(4, '2006282702', '2024-01-15', '结构性存款', '人民币', 3000000.00, 3000000.00, 2800000.00, 2950000.00, 0.028000, 2800000.00, '2023-08-25', '正常', 1),
(5, '2006282703', '2024-01-15', '活期存款', '人民币', 450000.00, 450000.00, 430000.00, 440000.00, 0.003000, 430000.00, '2019-03-18', '正常', 1),
(5, '2006282703', '2024-01-15', '定期存款', '人民币', 2500000.00, 2500000.00, 2400000.00, 2460000.00, 0.015000, 2400000.00, '2023-04-12', '正常', 1),
(5, '2006282703', '2024-01-15', '通知存款', '人民币', 1000000.00, 1000000.00, 950000.00, 980000.00, 0.008000, 950000.00, '2023-10-05', '正常', 1),
(6, '2006282704', '2024-01-15', '活期存款', '人民币', 1500000.00, 1500000.00, 1450000.00, 1480000.00, 0.003000, 1450000.00, '2018-07-20', '正常', 1),
(6, '2006282704', '2024-01-15', '定期存款', '人民币', 10000000.00, 10000000.00, 9500000.00, 9800000.00, 0.015000, 9500000.00, '2023-01-25', '正常', 1),
(6, '2006282704', '2024-01-15', '结构性存款', '人民币', 5000000.00, 5000000.00, 4800000.00, 4950000.00, 0.030000, 4800000.00, '2023-07-18', '正常', 1),
(7, '2006282705', '2024-01-15', '活期存款', '人民币', 380000.00, 380000.00, 370000.00, 375000.00, 0.003000, 370000.00, '2020-02-14', '正常', 1),
(7, '2006282705', '2024-01-15', '定期存款', '人民币', 1800000.00, 1800000.00, 1750000.00, 1780000.00, 0.015000, 1750000.00, '2023-06-08', '正常', 1),
(7, '2006282705', '2024-01-15', '零存整取', '人民币', 400000.00, 400000.00, 380000.00, 390000.00, 0.011000, 380000.00, '2022-03-15', '正常', 1),
(8, '2006282706', '2024-01-15', '活期存款', '人民币', 2000000.00, 2000000.00, 1900000.00, 1950000.00, 0.003000, 1900000.00, '2017-12-05', '正常', 1),
(8, '2006282706', '2024-01-15', '定期存款', '人民币', 12000000.00, 12000000.00, 11500000.00, 11800000.00, 0.015000, 11500000.00, '2023-03-01', '正常', 1),
(8, '2006282706', '2024-01-15', '结构性存款', '人民币', 6000000.00, 6000000.00, 5800000.00, 5950000.00, 0.032000, 5800000.00, '2023-09-20', '正常', 1),
(9, '2006282707', '2024-01-15', '活期存款', '人民币', 520000.00, 520000.00, 500000.00, 510000.00, 0.003000, 500000.00, '2019-09-10', '正常', 1),
(9, '2006282707', '2024-01-15', '定期存款', '人民币', 3000000.00, 3000000.00, 2900000.00, 2960000.00, 0.015000, 2900000.00, '2023-05-20', '正常', 1),
(9, '2006282707', '2024-01-15', '通知存款', '人民币', 1500000.00, 1500000.00, 1450000.00, 1480000.00, 0.008000, 1450000.00, '2023-11-15', '正常', 1),
(10, '2006282708', '2024-01-15', '活期存款', '人民币', 2500000.00, 2500000.00, 2400000.00, 2460000.00, 0.003000, 2400000.00, '2017-08-28', '正常', 1),
(10, '2006282708', '2024-01-15', '定期存款', '人民币', 15000000.00, 15000000.00, 14500000.00, 14800000.00, 0.015000, 14500000.00, '2023-02-15', '正常', 1),
(10, '2006282708', '2024-01-15', '结构性存款', '人民币', 8000000.00, 8000000.00, 7800000.00, 7950000.00, 0.035000, 7800000.00, '2023-08-10', '正常', 1);

-- 3. 中间业务概览测试数据
INSERT INTO `crm_customer_overview_middlebusiness`
(`customer_id`, `customer_no`, `statistics_date`, `business_type`, `business_name`,
 `business_amount`, `transaction_count`, `fee_amount`, `profit_contribution`, `tenant_id`)
VALUES
-- 零售客户中间业务数据
(1, '2006282699', '2024-01-15', '支付结算', '转账汇款', 5200000.00, 48, 2600.00, 2340.00, 1),
(1, '2006282699', '2024-01-15', '代理业务', '代理保险', 150000.00, 3, 3000.00, 2700.00, 1),
(1, '2006282699', '2024-01-15', '咨询顾问', '理财咨询', 0.00, 5, 5000.00, 4500.00, 1),
(2, '2006282700', '2024-01-15', '支付结算', '转账汇款', 1200000.00, 36, 1800.00, 1620.00, 1),
(2, '2006282700', '2024-01-15', '银行卡业务', '信用卡年费', 0.00, 2, 600.00, 540.00, 1),
(2, '2006282700', '2024-01-15', '代理业务', '代理基金', 80000.00, 4, 1200.00, 1080.00, 1),
(3, '2006282701', '2024-01-15', '支付结算', '转账汇款', 980000.00, 32, 1600.00, 1440.00, 1),
(3, '2006282701', '2024-01-15', '银行卡业务', '借记卡年费', 0.00, 1, 100.00, 90.00, 1),
(3, '2006282701', '2024-01-15', '代理业务', '代理保险', 60000.00, 2, 1200.00, 1080.00, 1),
(4, '2006282702', '2024-01-15', '支付结算', '转账汇款', 8500000.00, 65, 4250.00, 3825.00, 1),
(4, '2006282702', '2024-01-15', '贸易融资', '信用证', 2000000.00, 3, 10000.00, 9000.00, 1),
(4, '2006282702', '2024-01-15', '咨询顾问', '财务顾问', 0.00, 4, 8000.00, 7200.00, 1),
(5, '2006282703', '2024-01-15', '支付结算', '转账汇款', 1800000.00, 42, 2100.00, 1890.00, 1),
(5, '2006282703', '2024-01-15', '代理业务', '代理基金', 120000.00, 5, 1800.00, 1620.00, 1),
(5, '2006282703', '2024-01-15', '银行卡业务', '信用卡年费', 0.00, 2, 600.00, 540.00, 1),
(6, '2006282704', '2024-01-15', '支付结算', '转账汇款', 12000000.00, 78, 6000.00, 5400.00, 1),
(6, '2006282704', '2024-01-15', '贸易融资', '保函业务', 3000000.00, 4, 15000.00, 13500.00, 1),
(6, '2006282704', '2024-01-15', '咨询顾问', '资产管理', 0.00, 6, 12000.00, 10800.00, 1),
(7, '2006282705', '2024-01-15', '支付结算', '转账汇款', 1500000.00, 38, 1900.00, 1710.00, 1),
(7, '2006282705', '2024-01-15', '代理业务', '代理保险', 90000.00, 3, 1800.00, 1620.00, 1),
(7, '2006282705', '2024-01-15', '银行卡业务', '借记卡年费', 0.00, 1, 100.00, 90.00, 1),
(8, '2006282706', '2024-01-15', '支付结算', '转账汇款', 18000000.00, 95, 9000.00, 8100.00, 1),
(8, '2006282706', '2024-01-15', '贸易融资', '信用证', 5000000.00, 5, 25000.00, 22500.00, 1),
(8, '2006282706', '2024-01-15', '咨询顾问', '财务顾问', 0.00, 8, 16000.00, 14400.00, 1),
(9, '2006282707', '2024-01-15', '支付结算', '转账汇款', 2200000.00, 45, 2250.00, 2025.00, 1),
(9, '2006282707', '2024-01-15', '代理业务', '代理基金', 150000.00, 6, 2250.00, 2025.00, 1),
(9, '2006282707', '2024-01-15', '银行卡业务', '信用卡年费', 0.00, 2, 600.00, 540.00, 1),
(10, '2006282708', '2024-01-15', '支付结算', '转账汇款', 25000000.00, 120, 12500.00, 11250.00, 1),
(10, '2006282708', '2024-01-15', '贸易融资', '保函业务', 8000000.00, 6, 40000.00, 36000.00, 1),
(10, '2006282708', '2024-01-15', '咨询顾问', '资产管理', 0.00, 10, 20000.00, 18000.00, 1);

-- 4. 渠道业务概览测试数据
INSERT INTO `crm_customer_overview_channel`
(`customer_id`, `customer_no`, `statistics_date`, `channel_type`, `channel_name`,
 `access_count`, `login_count`, `transaction_count`, `transaction_amount`,
 `active_days`, `usage_frequency`, `tenant_id`)
VALUES
-- 零售客户渠道数据
(1, '2006282699', '2024-01-15', '手机银行', '移动APP', 156, 78, 52, 3200000.00, 25, '高频', 1),
(1, '2006282699', '2024-01-15', '网上银行', '个人网银', 48, 24, 18, 1800000.00, 15, '中频', 1),
(1, '2006282699', '2024-01-15', '柜面渠道', '营业网点', 6, 6, 5, 5000000.00, 5, '低频', 1),
(2, '2006282700', '2024-01-15', '手机银行', '移动APP', 128, 64, 42, 1200000.00, 22, '高频', 1),
(2, '2006282700', '2024-01-15', '网上银行', '个人网银', 36, 18, 15, 800000.00, 12, '中频', 1),
(2, '2006282700', '2024-01-15', 'ATM渠道', '自助终端', 18, 18, 16, 150000.00, 8, '低频', 1),
(3, '2006282701', '2024-01-15', '手机银行', '移动APP', 96, 48, 35, 980000.00, 18, '中频', 1),
(3, '2006282701', '2024-01-15', '网上银行', '个人网银', 28, 14, 12, 600000.00, 10, '中频', 1),
(3, '2006282701', '2024-01-15', '柜面渠道', '营业网点', 4, 4, 3, 1200000.00, 3, '低频', 1),
(4, '2006282702', '2024-01-15', '手机银行', '移动APP', 185, 92, 68, 8500000.00, 28, '高频', 1),
(4, '2006282702', '2024-01-15', '网上银行', '企业网银', 65, 32, 28, 15000000.00, 20, '高频', 1),
(4, '2006282702', '2024-01-15', '柜面渠道', '营业网点', 12, 12, 10, 20000000.00, 8, '中频', 1),
(5, '2006282703', '2024-01-15', '手机银行', '移动APP', 142, 71, 48, 1800000.00, 24, '高频', 1),
(5, '2006282703', '2024-01-15', '网上银行', '个人网银', 42, 21, 16, 1200000.00, 14, '中频', 1),
(5, '2006282703', '2024-01-15', 'ATM渠道', '自助终端', 22, 22, 18, 200000.00, 10, '中频', 1),
(6, '2006282704', '2024-01-15', '手机银行', '移动APP', 198, 99, 75, 12000000.00, 30, '高频', 1),
(6, '2006282704', '2024-01-15', '网上银行', '企业网银', 78, 39, 32, 25000000.00, 22, '高频', 1),
(6, '2006282704', '2024-01-15', '柜面渠道', '营业网点', 15, 15, 12, 30000000.00, 10, '中频', 1),
(7, '2006282705', '2024-01-15', '手机银行', '移动APP', 118, 59, 42, 1500000.00, 20, '高频', 1),
(7, '2006282705', '2024-01-15', '网上银行', '个人网银', 38, 19, 14, 900000.00, 12, '中频', 1),
(7, '2006282705', '2024-01-15', '柜面渠道', '营业网点', 5, 5, 4, 1800000.00, 4, '低频', 1),
(8, '2006282706', '2024-01-15', '手机银行', '移动APP', 215, 108, 88, 18000000.00, 30, '高频', 1),
(8, '2006282706', '2024-01-15', '网上银行', '企业网银', 92, 46, 38, 35000000.00, 25, '高频', 1),
(8, '2006282706', '2024-01-15', '柜面渠道', '营业网点', 18, 18, 15, 40000000.00, 12, '中频', 1),
(9, '2006282707', '2024-01-15', '手机银行', '移动APP', 158, 79, 52, 2200000.00, 26, '高频', 1),
(9, '2006282707', '2024-01-15', '网上银行', '个人网银', 48, 24, 18, 1500000.00, 15, '中频', 1),
(9, '2006282707', '2024-01-15', 'ATM渠道', '自助终端', 25, 25, 20, 250000.00, 12, '中频', 1),
(10, '2006282708', '2024-01-15', '手机银行', '移动APP', 245, 122, 105, 25000000.00, 30, '高频', 1),
(10, '2006282708', '2024-01-15', '网上银行', '企业网银', 108, 54, 45, 45000000.00, 28, '高频', 1),
(10, '2006282708', '2024-01-15', '柜面渠道', '营业网点', 22, 22, 18, 50000000.00, 15, '中频', 1);

-- 5. 贴现业务概览测试数据
INSERT INTO `crm_customer_overview_discount`
(`customer_id`, `customer_no`, `statistics_date`, `discount_type`, `bill_type`,
 `bill_amount`, `discount_amount`, `discount_balance`, `interest_amount`,
 `discount_rate`, `discount_days`, `discount_status`, `tenant_id`)
VALUES
-- 对公客户贴现数据(主要是企业客户)
(4, '2006282702', '2024-01-15', '银行承兑汇票贴现', '银行承兑汇票', 1000000.00, 980000.00, 980000.00, 20000.00, 0.035000, 180, '正常', 1),
(4, '2006282702', '2024-02-20', '商业承兑汇票贴现', '商业承兑汇票', 500000.00, 485000.00, 485000.00, 15000.00, 0.042000, 150, '正常', 1),
(4, '2006282702', '2024-03-10', '银行承兑汇票贴现', '银行承兑汇票', 1500000.00, 1470000.00, 1470000.00, 30000.00, 0.036000, 180, '正常', 1),
(6, '2006282704', '2024-01-20', '银行承兑汇票贴现', '银行承兑汇票', 2000000.00, 1960000.00, 1960000.00, 40000.00, 0.034000, 180, '正常', 1),
(6, '2006282704', '2024-03-15', '商业承兑汇票贴现', '商业承兑汇票', 800000.00, 776000.00, 776000.00, 24000.00, 0.040000, 150, '正常', 1),
(6, '2006282704', '2024-05-08', '银行承兑汇票贴现', '银行承兑汇票', 2500000.00, 2450000.00, 2450000.00, 50000.00, 0.035000, 180, '正常', 1),
(8, '2006282706', '2024-02-10', '银行承兑汇票贴现', '银行承兑汇票', 3000000.00, 2940000.00, 2940000.00, 60000.00, 0.033000, 180, '正常', 1),
(8, '2006282706', '2024-04-15', '商业承兑汇票贴现', '商业承兑汇票', 1200000.00, 1164000.00, 1164000.00, 36000.00, 0.039000, 150, '正常', 1),
(8, '2006282706', '2024-06-20', '银行承兑汇票贴现', '银行承兑汇票', 3500000.00, 3430000.00, 3430000.00, 70000.00, 0.034000, 180, '正常', 1),
(10, '2006282708', '2024-01-25', '银行承兑汇票贴现', '银行承兑汇票', 5000000.00, 4900000.00, 4900000.00, 100000.00, 0.032000, 180, '正常', 1),
(10, '2006282708', '2024-03-20', '商业承兑汇票贴现', '商业承兑汇票', 2000000.00, 1940000.00, 1940000.00, 60000.00, 0.038000, 150, '正常', 1),
(10, '2006282708', '2024-05-15', '银行承兑汇票贴现', '银行承兑汇票', 6000000.00, 5880000.00, 5880000.00, 120000.00, 0.033000, 180, '正常', 1),
(10, '2006282708', '2024-07-10', '银行承兑汇票贴现', '银行承兑汇票', 4500000.00, 4410000.00, 4410000.00, 90000.00, 0.034000, 180, '正常', 1);

-- 6. 表外业务概览测试数据
INSERT INTO `crm_customer_overview_offbalance`
(`customer_id`, `customer_no`, `statistics_date`, `business_type`, `business_name`,
 `credit_amount`, `used_amount`, `available_amount`, `usage_rate`,
 `business_amount`, `fee_rate`, `fee_amount`, `business_status`, `tenant_id`)
VALUES
-- 对公客户表外业务数据
(4, '2006282702', '2024-01-15', '银行承兑汇票', '承兑业务', 5000000.00, 3000000.00, 2000000.00, 0.6000, 3000000.00, 0.0005, 1500.00, '正常', 1),
(4, '2006282702', '2024-01-15', '信用证', '开立信用证', 3000000.00, 2000000.00, 1000000.00, 0.6667, 2000000.00, 0.0008, 1600.00, '正常', 1),
(4, '2006282702', '2024-01-15', '保函', '履约保函', 2000000.00, 1500000.00, 500000.00, 0.7500, 1500000.00, 0.0010, 1500.00, '正常', 1),
(6, '2006282704', '2024-01-15', '银行承兑汇票', '承兑业务', 8000000.00, 5000000.00, 3000000.00, 0.6250, 5000000.00, 0.0005, 2500.00, '正常', 1),
(6, '2006282704', '2024-01-15', '信用证', '开立信用证', 5000000.00, 3000000.00, 2000000.00, 0.6000, 3000000.00, 0.0008, 2400.00, '正常', 1),
(6, '2006282704', '2024-01-15', '保函', '履约保函', 4000000.00, 3000000.00, 1000000.00, 0.7500, 3000000.00, 0.0010, 3000.00, '正常', 1),
(8, '2006282706', '2024-01-15', '银行承兑汇票', '承兑业务', 12000000.00, 8000000.00, 4000000.00, 0.6667, 8000000.00, 0.0005, 4000.00, '正常', 1),
(8, '2006282706', '2024-01-15', '信用证', '开立信用证', 8000000.00, 5000000.00, 3000000.00, 0.6250, 5000000.00, 0.0008, 4000.00, '正常', 1),
(8, '2006282706', '2024-01-15', '保函', '履约保函', 6000000.00, 4500000.00, 1500000.00, 0.7500, 4500000.00, 0.0010, 4500.00, '正常', 1),
(8, '2006282706', '2024-01-15', '备用信用证', '备用信用证', 5000000.00, 3000000.00, 2000000.00, 0.6000, 3000000.00, 0.0012, 3600.00, '正常', 1),
(10, '2006282708', '2024-01-15', '银行承兑汇票', '承兑业务', 20000000.00, 15000000.00, 5000000.00, 0.7500, 15000000.00, 0.0005, 7500.00, '正常', 1),
(10, '2006282708', '2024-01-15', '信用证', '开立信用证', 15000000.00, 10000000.00, 5000000.00, 0.6667, 10000000.00, 0.0008, 8000.00, '正常', 1),
(10, '2006282708', '2024-01-15', '保函', '履约保函', 10000000.00, 8000000.00, 2000000.00, 0.8000, 8000000.00, 0.0010, 8000.00, '正常', 1),
(10, '2006282708', '2024-01-15', '备用信用证', '备用信用证', 8000000.00, 5000000.00, 3000000.00, 0.6250, 5000000.00, 0.0012, 6000.00, '正常', 1),
(10, '2006282708', '2024-01-15', '远期信用证', '远期信用证', 12000000.00, 8000000.00, 4000000.00, 0.6667, 8000000.00, 0.0015, 12000.00, '正常', 1);
-- =====================================================
-- 为业务概览表补充对公客户测试数据
-- 对公客户使用不同的customer_id范围: 1001-1010
-- customer_no格式: C + 9位数字
-- =====================================================

USE `ruoyi-vue-pro`;

-- =====================================================
-- 1. 贷款业务概览 - 对公客户数据
-- =====================================================
INSERT INTO `crm_customer_overview_loan`
(`customer_id`, `customer_no`, `statistics_date`, `loan_type`, `currency`, `loan_amount`,
 `loan_account_count`, `normal_business_balance`, `business_balance`, `balance_year_avg`,
 `balance_month_avg`, `interest_rate`, `loan_profit`, `tenant_id`)
VALUES
-- 对公客户贷款数据 (企业规模更大，金额更高)
(1001, 'C200628001', '2023-01-15', '流动资金贷款', '人民币', 15000000.00, 2, 15000000.00, 15000000.00, 14500000.00, 14800000.00, 0.042000, 630000.00, 1),
(1001, 'C200628001', '2023-07-20', '固定资产贷款', '人民币', 25000000.00, 1, 25000000.00, 25000000.00, 24000000.00, 24500000.00, 0.045000, 1125000.00, 1),
(1001, 'C200628001', '2024-03-10', '项目融资贷款', '人民币', 30000000.00, 1, 30000000.00, 30000000.00, 28000000.00, 29000000.00, 0.048000, 1440000.00, 1),

(1002, 'C200628002', '2023-02-28', '流动资金贷款', '人民币', 8000000.00, 2, 8000000.00, 8000000.00, 7800000.00, 7950000.00, 0.041000, 328000.00, 1),
(1002, 'C200628002', '2023-09-15', '固定资产贷款', '人民币', 12000000.00, 1, 12000000.00, 12000000.00, 11500000.00, 11800000.00, 0.044000, 528000.00, 1),
(1002, 'C200628002', '2024-05-20', '票据融资', '人民币', 5000000.00, 1, 5000000.00, 5000000.00, 4800000.00, 4900000.00, 0.038000, 190000.00, 1),

(1003, 'C200628003', '2023-04-10', '流动资金贷款', '人民币', 20000000.00, 3, 20000000.00, 20000000.00, 19500000.00, 19800000.00, 0.043000, 860000.00, 1),
(1003, 'C200628003', '2023-10-25', '固定资产贷款', '人民币', 35000000.00, 2, 35000000.00, 35000000.00, 34000000.00, 34500000.00, 0.046000, 1610000.00, 1),
(1003, 'C200628003', '2024-06-15', '项目融资贷款', '人民币', 40000000.00, 1, 40000000.00, 40000000.00, 38000000.00, 39000000.00, 0.049000, 1960000.00, 1),

(1004, 'C200628004', '2023-03-20', '流动资金贷款', '人民币', 10000000.00, 2, 10000000.00, 10000000.00, 9800000.00, 9950000.00, 0.040000, 400000.00, 1),
(1004, 'C200628004', '2023-11-10', '固定资产贷款', '人民币', 18000000.00, 1, 18000000.00, 18000000.00, 17500000.00, 17800000.00, 0.045000, 810000.00, 1),

(1005, 'C200628005', '2023-05-15', '流动资金贷款', '人民币', 25000000.00, 3, 25000000.00, 25000000.00, 24500000.00, 24800000.00, 0.044000, 1100000.00, 1),
(1005, 'C200628005', '2024-01-20', '固定资产贷款', '人民币', 45000000.00, 2, 45000000.00, 45000000.00, 44000000.00, 44500000.00, 0.047000, 2115000.00, 1),
(1005, 'C200628005', '2024-07-10', '并购贷款', '人民币', 50000000.00, 1, 50000000.00, 50000000.00, 48000000.00, 49000000.00, 0.050000, 2500000.00, 1),

(1006, 'C200628006', '2023-06-25', '流动资金贷款', '人民币', 12000000.00, 2, 12000000.00, 12000000.00, 11800000.00, 11950000.00, 0.041500, 498000.00, 1),
(1006, 'C200628006', '2024-02-15', '固定资产贷款', '人民币', 22000000.00, 1, 22000000.00, 22000000.00, 21500000.00, 21800000.00, 0.046000, 1012000.00, 1),

(1007, 'C200628007', '2023-07-30', '流动资金贷款', '人民币', 18000000.00, 3, 18000000.00, 18000000.00, 17500000.00, 17800000.00, 0.043500, 783000.00, 1),
(1007, 'C200628007', '2024-03-25', '固定资产贷款', '人民币', 28000000.00, 2, 28000000.00, 28000000.00, 27500000.00, 27800000.00, 0.047500, 1330000.00, 1),

(1008, 'C200628008', '2023-08-18', '流动资金贷款', '人民币', 30000000.00, 4, 30000000.00, 30000000.00, 29500000.00, 29800000.00, 0.045000, 1350000.00, 1),
(1008, 'C200628008', '2024-04-10', '固定资产贷款', '人民币', 38000000.00, 2, 38000000.00, 38000000.00, 37000000.00, 37500000.00, 0.048000, 1824000.00, 1),
(1008, 'C200628008', '2024-08-05', '项目融资贷款', '人民币', 45000000.00, 1, 45000000.00, 45000000.00, 43000000.00, 44000000.00, 0.051000, 2295000.00, 1),

(1009, 'C200628009', '2023-09-20', '流动资金贷款', '人民币', 22000000.00, 3, 22000000.00, 22000000.00, 21500000.00, 21800000.00, 0.044000, 968000.00, 1),
(1009, 'C200628009', '2024-05-15', '固定资产贷款', '人民币', 32000000.00, 2, 32000000.00, 32000000.00, 31500000.00, 31800000.00, 0.048500, 1552000.00, 1),

(1010, 'C200628010', '2023-10-28', '流动资金贷款', '人民币', 35000000.00, 4, 35000000.00, 35000000.00, 34500000.00, 34800000.00, 0.046000, 1610000.00, 1),
(1010, 'C200628010', '2024-06-20', '固定资产贷款', '人民币', 55000000.00, 3, 55000000.00, 55000000.00, 54000000.00, 54500000.00, 0.049000, 2695000.00, 1),
(1010, 'C200628010', '2024-09-10', '并购贷款', '人民币', 60000000.00, 1, 60000000.00, 60000000.00, 58000000.00, 59000000.00, 0.052000, 3120000.00, 1);

-- =====================================================
-- 2. 存款业务概览 - 对公客户数据
-- =====================================================
INSERT INTO `crm_customer_overview_deposit`
(`customer_id`, `customer_no`, `statistics_date`, `deposit_type`, `currency`, `deposit_balance`,
 `business_amount`, `balance_year_avg`, `balance_month_avg`, `interest_rate`, `ftp_price`,
 `open_date`, `account_status`, `tenant_id`)
VALUES
-- 对公客户存款数据 (企业账户，金额更大)
(1001, 'C200628001', '2024-01-15', '单位活期存款', '人民币', 5000000.00, 5000000.00, 4800000.00, 4950000.00, 0.003500, 4800000.00, '2020-01-10', '正常', 1),
(1001, 'C200628001', '2024-01-15', '单位定期存款', '人民币', 20000000.00, 20000000.00, 19500000.00, 19800000.00, 0.018000, 19500000.00, '2023-01-15', '正常', 1),
(1001, 'C200628001', '2024-01-15', '单位协定存款', '人民币', 10000000.00, 10000000.00, 9800000.00, 9950000.00, 0.010000, 9800000.00, '2023-06-20', '正常', 1),

(1002, 'C200628002', '2024-01-15', '单位活期存款', '人民币', 3000000.00, 3000000.00, 2900000.00, 2950000.00, 0.003500, 2900000.00, '2019-08-15', '正常', 1),
(1002, 'C200628002', '2024-01-15', '单位定期存款', '人民币', 12000000.00, 12000000.00, 11500000.00, 11800000.00, 0.018000, 11500000.00, '2023-03-20', '正常', 1),
(1002, 'C200628002', '2024-01-15', '结构性存款', '人民币', 8000000.00, 8000000.00, 7800000.00, 7950000.00, 0.030000, 7800000.00, '2023-09-10', '正常', 1),

(1003, 'C200628003', '2024-01-15', '单位活期存款', '人民币', 8000000.00, 8000000.00, 7800000.00, 7950000.00, 0.003500, 7800000.00, '2018-05-22', '正常', 1),
(1003, 'C200628003', '2024-01-15', '单位定期存款', '人民币', 30000000.00, 30000000.00, 29500000.00, 29800000.00, 0.018000, 29500000.00, '2023-05-15', '正常', 1),
(1003, 'C200628003', '2024-01-15', '单位协定存款', '人民币', 15000000.00, 15000000.00, 14800000.00, 14950000.00, 0.010000, 14800000.00, '2022-01-10', '正常', 1),

(1004, 'C200628004', '2024-01-15', '单位活期存款', '人民币', 4000000.00, 4000000.00, 3900000.00, 3950000.00, 0.003500, 3900000.00, '2019-11-08', '正常', 1),
(1004, 'C200628004', '2024-01-15', '单位定期存款', '人民币', 15000000.00, 15000000.00, 14500000.00, 14800000.00, 0.018000, 14500000.00, '2023-02-10', '正常', 1),
(1004, 'C200628004', '2024-01-15', '结构性存款', '人民币', 10000000.00, 10000000.00, 9800000.00, 9950000.00, 0.032000, 9800000.00, '2023-08-25', '正常', 1),

(1005, 'C200628005', '2024-01-15', '单位活期存款', '人民币', 10000000.00, 10000000.00, 9800000.00, 9950000.00, 0.003500, 9800000.00, '2018-03-18', '正常', 1),
(1005, 'C200628005', '2024-01-15', '单位定期存款', '人民币', 40000000.00, 40000000.00, 39500000.00, 39800000.00, 0.018000, 39500000.00, '2023-04-12', '正常', 1),
(1005, 'C200628005', '2024-01-15', '单位协定存款', '人民币', 20000000.00, 20000000.00, 19800000.00, 19950000.00, 0.010000, 19800000.00, '2023-10-05', '正常', 1),

(1006, 'C200628006', '2024-01-15', '单位活期存款', '人民币', 5000000.00, 5000000.00, 4900000.00, 4950000.00, 0.003500, 4900000.00, '2019-07-20', '正常', 1),
(1006, 'C200628006', '2024-01-15', '单位定期存款', '人民币', 22000000.00, 22000000.00, 21500000.00, 21800000.00, 0.018000, 21500000.00, '2023-01-25', '正常', 1),
(1006, 'C200628006', '2024-01-15', '结构性存款', '人民币', 12000000.00, 12000000.00, 11800000.00, 11950000.00, 0.033000, 11800000.00, '2023-07-18', '正常', 1),

(1007, 'C200628007', '2024-01-15', '单位活期存款', '人民币', 6000000.00, 6000000.00, 5900000.00, 5950000.00, 0.003500, 5900000.00, '2018-02-14', '正常', 1),
(1007, 'C200628007', '2024-01-15', '单位定期存款', '人民币', 25000000.00, 25000000.00, 24500000.00, 24800000.00, 0.018000, 24500000.00, '2023-06-08', '正常', 1),
(1007, 'C200628007', '2024-01-15', '单位协定存款', '人民币', 18000000.00, 18000000.00, 17800000.00, 17950000.00, 0.010000, 17800000.00, '2022-03-15', '正常', 1),

(1008, 'C200628008', '2024-01-15', '单位活期存款', '人民币', 12000000.00, 12000000.00, 11800000.00, 11950000.00, 0.003500, 11800000.00, '2017-12-05', '正常', 1),
(1008, 'C200628008', '2024-01-15', '单位定期存款', '人民币', 45000000.00, 45000000.00, 44500000.00, 44800000.00, 0.018000, 44500000.00, '2023-03-01', '正常', 1),
(1008, 'C200628008', '2024-01-15', '结构性存款', '人民币', 28000000.00, 28000000.00, 27800000.00, 27950000.00, 0.035000, 27800000.00, '2023-09-20', '正常', 1),

(1009, 'C200628009', '2024-01-15', '单位活期存款', '人民币', 7000000.00, 7000000.00, 6900000.00, 6950000.00, 0.003500, 6900000.00, '2018-09-10', '正常', 1),
(1009, 'C200628009', '2024-01-15', '单位定期存款', '人民币', 28000000.00, 28000000.00, 27500000.00, 27800000.00, 0.018000, 27500000.00, '2023-05-20', '正常', 1),
(1009, 'C200628009', '2024-01-15', '单位协定存款', '人民币', 15000000.00, 15000000.00, 14800000.00, 14950000.00, 0.010000, 14800000.00, '2023-11-15', '正常', 1),

(1010, 'C200628010', '2024-01-15', '单位活期存款', '人民币', 15000000.00, 15000000.00, 14800000.00, 14950000.00, 0.003500, 14800000.00, '2017-08-28', '正常', 1),
(1010, 'C200628010', '2024-01-15', '单位定期存款', '人民币', 50000000.00, 50000000.00, 49500000.00, 49800000.00, 0.018000, 49500000.00, '2023-02-15', '正常', 1),
(1010, 'C200628010', '2024-01-15', '结构性存款', '人民币', 35000000.00, 35000000.00, 34800000.00, 34950000.00, 0.036000, 34800000.00, '2023-08-10', '正常', 1);

-- =====================================================
-- 3. 中间业务概览 - 对公客户数据
-- =====================================================
INSERT INTO `crm_customer_overview_middlebusiness`
(`customer_id`, `customer_no`, `statistics_date`, `business_type`, `business_name`,
 `business_amount`, `transaction_count`, `fee_amount`, `profit_contribution`, `tenant_id`)
VALUES
-- 对公客户中间业务数据 (企业业务类型更丰富)
(1001, 'C200628001', '2024-01-15', '支付结算', '企业转账', 50000000.00, 120, 25000.00, 22500.00, 1),
(1001, 'C200628001', '2024-01-15', '贸易融资', '信用证', 20000000.00, 5, 100000.00, 90000.00, 1),
(1001, 'C200628001', '2024-01-15', '咨询顾问', '财务顾问', 0.00, 8, 80000.00, 72000.00, 1),

(1002, 'C200628002', '2024-01-15', '支付结算', '企业转账', 30000000.00, 85, 15000.00, 13500.00, 1),
(1002, 'C200628002', '2024-01-15', '贸易融资', '保函业务', 10000000.00, 3, 50000.00, 45000.00, 1),
(1002, 'C200628002', '2024-01-15', '现金管理', '账户管理', 0.00, 12, 24000.00, 21600.00, 1),

(1003, 'C200628003', '2024-01-15', '支付结算', '企业转账', 80000000.00, 150, 40000.00, 36000.00, 1),
(1003, 'C200628003', '2024-01-15', '贸易融资', '信用证', 30000000.00, 8, 150000.00, 135000.00, 1),
(1003, 'C200628003', '2024-01-15', '咨询顾问', '投资银行', 0.00, 10, 120000.00, 108000.00, 1),

(1004, 'C200628004', '2024-01-15', '支付结算', '企业转账', 25000000.00, 75, 12500.00, 11250.00, 1),
(1004, 'C200628004', '2024-01-15', '贸易融资', '保函业务', 8000000.00, 4, 40000.00, 36000.00, 1),
(1004, 'C200628004', '2024-01-15', '资金监管', '工程监管', 15000000.00, 2, 30000.00, 27000.00, 1),

(1005, 'C200628005', '2024-01-15', '支付结算', '企业转账', 100000000.00, 200, 50000.00, 45000.00, 1),
(1005, 'C200628005', '2024-01-15', '贸易融资', '信用证', 40000000.00, 10, 200000.00, 180000.00, 1),
(1005, 'C200628005', '2024-01-15', '咨询顾问', '财务顾问', 0.00, 15, 150000.00, 135000.00, 1),

(1006, 'C200628006', '2024-01-15', '支付结算', '企业转账', 35000000.00, 90, 17500.00, 15750.00, 1),
(1006, 'C200628006', '2024-01-15', '贸易融资', '保函业务', 12000000.00, 5, 60000.00, 54000.00, 1),
(1006, 'C200628006', '2024-01-15', '现金管理', '账户管理', 0.00, 15, 30000.00, 27000.00, 1),

(1007, 'C200628007', '2024-01-15', '支付结算', '企业转账', 60000000.00, 130, 30000.00, 27000.00, 1),
(1007, 'C200628007', '2024-01-15', '贸易融资', '信用证', 25000000.00, 6, 125000.00, 112500.00, 1),
(1007, 'C200628007', '2024-01-15', '资金监管', '工程监管', 20000000.00, 3, 40000.00, 36000.00, 1),

(1008, 'C200628008', '2024-01-15', '支付结算', '企业转账', 120000000.00, 220, 60000.00, 54000.00, 1),
(1008, 'C200628008', '2024-01-15', '贸易融资', '信用证', 50000000.00, 12, 250000.00, 225000.00, 1),
(1008, 'C200628008', '2024-01-15', '咨询顾问', '投资银行', 0.00, 18, 180000.00, 162000.00, 1),

(1009, 'C200628009', '2024-01-15', '支付结算', '企业转账', 45000000.00, 100, 22500.00, 20250.00, 1),
(1009, 'C200628009', '2024-01-15', '贸易融资', '保函业务', 18000000.00, 6, 90000.00, 81000.00, 1),
(1009, 'C200628009', '2024-01-15', '现金管理', '账户管理', 0.00, 18, 36000.00, 32400.00, 1),

(1010, 'C200628010', '2024-01-15', '支付结算', '企业转账', 150000000.00, 250, 75000.00, 67500.00, 1),
(1010, 'C200628010', '2024-01-15', '贸易融资', '信用证', 60000000.00, 15, 300000.00, 270000.00, 1),
(1010, 'C200628010', '2024-01-15', '咨询顾问', '财务顾问', 0.00, 20, 200000.00, 180000.00, 1);

-- =====================================================
-- 4. 渠道业务概览 - 对公客户数据
-- =====================================================
INSERT INTO `crm_customer_overview_channel`
(`customer_id`, `customer_no`, `statistics_date`, `channel_type`, `channel_name`,
 `access_count`, `login_count`, `transaction_count`, `transaction_amount`,
 `active_days`, `usage_frequency`, `tenant_id`)
VALUES
-- 对公客户渠道数据 (主要使用企业网银)
(1001, 'C200628001', '2024-01-15', '企业网银', '企业网银PC端', 180, 90, 85, 50000000.00, 28, '高频', 1),
(1001, 'C200628001', '2024-01-15', '手机银行', '企业手机银行', 120, 60, 45, 20000000.00, 25, '高频', 1),
(1001, 'C200628001', '2024-01-15', '柜面渠道', '营业网点', 12, 12, 10, 30000000.00, 8, '中频', 1),

(1002, 'C200628002', '2024-01-15', '企业网银', '企业网银PC端', 140, 70, 65, 30000000.00, 25, '高频', 1),
(1002, 'C200628002', '2024-01-15', '手机银行', '企业手机银行', 95, 48, 38, 15000000.00, 22, '高频', 1),
(1002, 'C200628002', '2024-01-15', '柜面渠道', '营业网点', 8, 8, 7, 18000000.00, 6, '低频', 1),

(1003, 'C200628003', '2024-01-15', '企业网银', '企业网银PC端', 220, 110, 105, 80000000.00, 30, '高频', 1),
(1003, 'C200628003', '2024-01-15', '手机银行', '企业手机银行', 150, 75, 58, 35000000.00, 28, '高频', 1),
(1003, 'C200628003', '2024-01-15', '柜面渠道', '营业网点', 15, 15, 12, 45000000.00, 10, '中频', 1),

(1004, 'C200628004', '2024-01-15', '企业网银', '企业网银PC端', 130, 65, 60, 25000000.00, 24, '高频', 1),
(1004, 'C200628004', '2024-01-15', '手机银行', '企业手机银行', 85, 42, 35, 12000000.00, 20, '中频', 1),
(1004, 'C200628004', '2024-01-15', '柜面渠道', '营业网点', 10, 10, 8, 20000000.00, 7, '低频', 1),

(1005, 'C200628005', '2024-01-15', '企业网银', '企业网银PC端', 250, 125, 120, 100000000.00, 30, '高频', 1),
(1005, 'C200628005', '2024-01-15', '手机银行', '企业手机银行', 180, 90, 72, 45000000.00, 30, '高频', 1),
(1005, 'C200628005', '2024-01-15', '柜面渠道', '营业网点', 18, 18, 15, 55000000.00, 12, '中频', 1),

(1006, 'C200628006', '2024-01-15', '企业网银', '企业网银PC端', 160, 80, 75, 35000000.00, 26, '高频', 1),
(1006, 'C200628006', '2024-01-15', '手机银行', '企业手机银行', 110, 55, 42, 18000000.00, 23, '高频', 1),
(1006, 'C200628006', '2024-01-15', '柜面渠道', '营业网点', 11, 11, 9, 22000000.00, 8, '中频', 1),

(1007, 'C200628007', '2024-01-15', '企业网银', '企业网银PC端', 190, 95, 88, 60000000.00, 28, '高频', 1),
(1007, 'C200628007', '2024-01-15', '手机银行', '企业手机银行', 135, 68, 52, 28000000.00, 26, '高频', 1),
(1007, 'C200628007', '2024-01-15', '柜面渠道', '营业网点', 13, 13, 11, 35000000.00, 9, '中频', 1),

(1008, 'C200628008', '2024-01-15', '企业网银', '企业网银PC端', 280, 140, 135, 120000000.00, 30, '高频', 1),
(1008, 'C200628008', '2024-01-15', '手机银行', '企业手机银行', 200, 100, 82, 55000000.00, 30, '高频', 1),
(1008, 'C200628008', '2024-01-15', '柜面渠道', '营业网点', 20, 20, 16, 65000000.00, 14, '中频', 1),

(1009, 'C200628009', '2024-01-15', '企业网银', '企业网银PC端', 170, 85, 80, 45000000.00, 27, '高频', 1),
(1009, 'C200628009', '2024-01-15', '手机银行', '企业手机银行', 120, 60, 48, 22000000.00, 24, '高频', 1),
(1009, 'C200628009', '2024-01-15', '柜面渠道', '营业网点', 12, 12, 10, 28000000.00, 9, '中频', 1),

(1010, 'C200628010', '2024-01-15', '企业网银', '企业网银PC端', 300, 150, 145, 150000000.00, 30, '高频', 1),
(1010, 'C200628010', '2024-01-15', '手机银行', '企业手机银行', 220, 110, 95, 68000000.00, 30, '高频', 1),
(1010, 'C200628010', '2024-01-15', '柜面渠道', '营业网点', 22, 22, 18, 75000000.00, 15, '中频', 1);

-- =====================================================
-- 对公客户测试数据 (客户 ID: 11-20, 客户编号: C20001-C20010)
-- =====================================================

-- 1. 对公客户贷款业务概览数据
INSERT INTO `crm_customer_overview_loan`
(`customer_id`, `customer_no`, `statistics_date`, `loan_type`, `currency`, `loan_amount`,
 `loan_account_count`, `normal_business_balance`, `business_balance`, `balance_year_avg`,
 `balance_month_avg`, `interest_rate`, `loan_profit`, `tenant_id`)
VALUES
(11, 'C20001', '2025-10-31', '流动资金贷款', '人民币', 5000000.00, 1, 5000000.00, 5000000.00, 5000000.00, 5000000.00, 0.043500, 21750.00, 1),
(11, 'C20001', '2025-10-31', '固定资产贷款', '人民币', 8000000.00, 1, 8000000.00, 8000000.00, 8000000.00, 8000000.00, 0.049000, 39200.00, 1),
(12, 'C20002', '2025-10-31', '流动资金贷款', '人民币', 6000000.00, 1, 6000000.00, 6000000.00, 6000000.00, 6000000.00, 0.042500, 25500.00, 1),
(12, 'C20002', '2025-10-31', '贸易融资', '人民币', 3500000.00, 1, 3500000.00, 3500000.00, 3500000.00, 3500000.00, 0.038500, 13475.00, 1),
(13, 'C20003', '2025-10-31', '流动资金贷款', '人民币', 4500000.00, 1, 4500000.00, 4500000.00, 4500000.00, 4500000.00, 0.044000, 19800.00, 1),
(13, 'C20003', '2025-10-31', '固定资产贷款', '人民币', 7000000.00, 1, 7000000.00, 7000000.00, 7000000.00, 7000000.00, 0.048500, 33950.00, 1),
(14, 'C20004', '2025-10-31', '流动资金贷款', '人民币', 3500000.00, 1, 3500000.00, 3500000.00, 3500000.00, 3500000.00, 0.043000, 15050.00, 1),
(14, 'C20004', '2025-10-31', '信用贷款', '人民币', 2000000.00, 1, 2000000.00, 2000000.00, 2000000.00, 2000000.00, 0.052000, 10400.00, 1),
(15, 'C20005', '2025-10-31', '项目贷款', '人民币', 15000000.00, 1, 15000000.00, 15000000.00, 15000000.00, 15000000.00, 0.046500, 69750.00, 1),
(15, 'C20005', '2025-10-31', '流动资金贷款', '人民币', 5500000.00, 1, 5500000.00, 5500000.00, 5500000.00, 5500000.00, 0.045000, 24750.00, 1),
(16, 'C20006', '2025-10-31', '流动资金贷款', '人民币', 2500000.00, 1, 2500000.00, 2500000.00, 2500000.00, 2500000.00, 0.046000, 11500.00, 1),
(16, 'C20006', '2025-10-31', '固定资产贷款', '人民币', 4000000.00, 1, 4000000.00, 4000000.00, 4000000.00, 4000000.00, 0.049500, 19800.00, 1),
(17, 'C20007', '2025-10-31', '流动资金贷款', '人民币', 6500000.00, 1, 6500000.00, 6500000.00, 6500000.00, 6500000.00, 0.043500, 28275.00, 1),
(17, 'C20007', '2025-10-31', '保理业务', '人民币', 3500000.00, 1, 3500000.00, 3500000.00, 3500000.00, 3500000.00, 0.054000, 18900.00, 1),
(18, 'C20008', '2025-10-31', '固定资产贷款', '人民币', 9000000.00, 1, 9000000.00, 9000000.00, 9000000.00, 9000000.00, 0.048000, 43200.00, 1),
(18, 'C20008', '2025-10-31', '流动资金贷款', '人民币', 4500000.00, 1, 4500000.00, 4500000.00, 4500000.00, 4500000.00, 0.044500, 20025.00, 1),
(19, 'C20009', '2025-10-31', '流动资金贷款', '人民币', 5500000.00, 1, 5500000.00, 5500000.00, 5500000.00, 5500000.00, 0.042500, 23375.00, 1),
(19, 'C20009', '2025-10-31', '项目贷款', '人民币', 10000000.00, 1, 10000000.00, 10000000.00, 10000000.00, 10000000.00, 0.047000, 47000.00, 1),
(20, 'C20010', '2025-10-31', '流动资金贷款', '人民币', 7500000.00, 1, 7500000.00, 7500000.00, 7500000.00, 7500000.00, 0.044000, 33000.00, 1),
(20, 'C20010', '2025-10-31', '固定资产贷款', '人民币', 11000000.00, 1, 11000000.00, 11000000.00, 11000000.00, 11000000.00, 0.049000, 53900.00, 1);

-- 2. 对公客户存款业务概览数据
INSERT INTO `crm_customer_overview_deposit`
(`customer_id`, `customer_no`, `statistics_date`, `deposit_type`, `currency`, `deposit_balance`,
 `deposit_account_count`, `balance_year_avg`, `balance_month_avg`, `interest_rate`, `deposit_profit`, `tenant_id`)
VALUES
(11, 'C20001', '2025-10-31', '单位活期存款', '人民币', 3500000.00, 1, 3500000.00, 3500000.00, 0.003500, 12250.00, 1),
(11, 'C20001', '2025-10-31', '单位定期存款', '人民币', 8000000.00, 1, 8000000.00, 8000000.00, 0.017500, 140000.00, 1),
(11, 'C20001', '2025-10-31', '单位通知存款', '人民币', 5000000.00, 1, 5000000.00, 5000000.00, 0.011000, 55000.00, 1),
(12, 'C20002', '2025-10-31', '单位活期存款', '人民币', 4200000.00, 1, 4200000.00, 4200000.00, 0.003500, 14700.00, 1),
(12, 'C20002', '2025-10-31', '单位定期存款', '人民币', 10000000.00, 1, 10000000.00, 10000000.00, 0.018500, 185000.00, 1),
(12, 'C20002', '2025-10-31', '保证金存款', '人民币', 2500000.00, 1, 2500000.00, 2500000.00, 0.007200, 18000.00, 1),
(13, 'C20003', '2025-10-31', '单位活期存款', '人民币', 3800000.00, 1, 3800000.00, 3800000.00, 0.003500, 13300.00, 1),
(13, 'C20003', '2025-10-31', '单位定期存款', '人民币', 7000000.00, 1, 7000000.00, 7000000.00, 0.018000, 126000.00, 1),
(13, 'C20003', '2025-10-31', '单位通知存款', '人民币', 4500000.00, 1, 4500000.00, 4500000.00, 0.011000, 49500.00, 1),
(14, 'C20004', '2025-10-31', '单位活期存款', '人民币', 2800000.00, 1, 2800000.00, 2800000.00, 0.003500, 9800.00, 1),
(14, 'C20004', '2025-10-31', '单位定期存款', '人民币', 5500000.00, 1, 5500000.00, 5500000.00, 0.017500, 96250.00, 1),
(14, 'C20004', '2025-10-31', '保证金存款', '人民币', 1800000.00, 1, 1800000.00, 1800000.00, 0.007200, 12960.00, 1),
(15, 'C20005', '2025-10-31', '单位活期存款', '人民币', 4500000.00, 1, 4500000.00, 4500000.00, 0.003500, 15750.00, 1),
(15, 'C20005', '2025-10-31', '单位定期存款', '人民币', 12000000.00, 1, 12000000.00, 12000000.00, 0.019000, 228000.00, 1),
(15, 'C20005', '2025-10-31', '单位通知存款', '人民币', 6000000.00, 1, 6000000.00, 6000000.00, 0.011000, 66000.00, 1),
(16, 'C20006', '2025-10-31', '单位活期存款', '人民币', 2200000.00, 1, 2200000.00, 2200000.00, 0.003500, 7700.00, 1),
(16, 'C20006', '2025-10-31', '单位定期存款', '人民币', 4000000.00, 1, 4000000.00, 4000000.00, 0.017500, 70000.00, 1),
(16, 'C20006', '2025-10-31', '保证金存款', '人民币', 1500000.00, 1, 1500000.00, 1500000.00, 0.007200, 10800.00, 1),
(17, 'C20007', '2025-10-31', '单位活期存款', '人民币', 5200000.00, 1, 5200000.00, 5200000.00, 0.003500, 18200.00, 1),
(17, 'C20007', '2025-10-31', '单位定期存款', '人民币', 9000000.00, 1, 9000000.00, 9000000.00, 0.018500, 166500.00, 1),
(17, 'C20007', '2025-10-31', '单位通知存款', '人民币', 3500000.00, 1, 3500000.00, 3500000.00, 0.011000, 38500.00, 1),
(18, 'C20008', '2025-10-31', '单位活期存款', '人民币', 6800000.00, 1, 6800000.00, 6800000.00, 0.003500, 23800.00, 1),
(18, 'C20008', '2025-10-31', '单位定期存款', '人民币', 11000000.00, 1, 11000000.00, 11000000.00, 0.019000, 209000.00, 1),
(18, 'C20008', '2025-10-31', '保证金存款', '人民币', 3000000.00, 1, 3000000.00, 3000000.00, 0.007200, 21600.00, 1),
(19, 'C20009', '2025-10-31', '单位活期存款', '人民币', 4800000.00, 1, 4800000.00, 4800000.00, 0.003500, 16800.00, 1),
(19, 'C20009', '2025-10-31', '单位定期存款', '人民币', 8500000.00, 1, 8500000.00, 8500000.00, 0.018000, 153000.00, 1),
(19, 'C20009', '2025-10-31', '单位通知存款', '人民币', 5500000.00, 1, 5500000.00, 5500000.00, 0.011000, 60500.00, 1),
(20, 'C20010', '2025-10-31', '单位活期存款', '人民币', 5800000.00, 1, 5800000.00, 5800000.00, 0.003500, 20300.00, 1),
(20, 'C20010', '2025-10-31', '单位定期存款', '人民币', 13000000.00, 1, 13000000.00, 13000000.00, 0.019500, 253500.00, 1),
(20, 'C20010', '2025-10-31', '单位通知存款', '人民币', 7000000.00, 1, 7000000.00, 7000000.00, 0.011000, 77000.00, 1);

-- 3. 对公客户中间业务概览数据
INSERT INTO `crm_customer_overview_middlebusiness`
(`customer_id`, `customer_no`, `statistics_date`, `business_type`, `business_name`,
 `business_amount`, `transaction_count`, `fee_amount`, `profit_contribution`, `tenant_id`)
VALUES
(11, 'C20001', '2025-10-31', '结算业务', '企业网银', 50000000.00, 1250, 25000.00, 25000.00, 1),
(11, 'C20001', '2025-10-31', '国际业务', '国际结算', 3500000.00, 25, 35000.00, 35000.00, 1),
(11, 'C20001', '2025-10-31', '咨询顾问', '财务顾问', 0.00, 2, 80000.00, 80000.00, 1),
(12, 'C20002', '2025-10-31', '结算业务', '企业网银', 60000000.00, 1500, 30000.00, 30000.00, 1),
(12, 'C20002', '2025-10-31', '国际业务', '信用证', 5600000.00, 40, 56000.00, 56000.00, 1),
(12, 'C20002', '2025-10-31', '现金管理', '资金归集', 100000000.00, 365, 50000.00, 50000.00, 1),
(13, 'C20003', '2025-10-31', '结算业务', '企业网银', 45000000.00, 1100, 22500.00, 22500.00, 1),
(13, 'C20003', '2025-10-31', '票据业务', '承兑汇票', 8000000.00, 20, 40000.00, 40000.00, 1),
(13, 'C20003', '2025-10-31', '国际业务', '国际结算', 2450000.00, 18, 24500.00, 24500.00, 1),
(14, 'C20004', '2025-10-31', '结算业务', '企业网银', 35000000.00, 850, 17500.00, 17500.00, 1),
(14, 'C20004', '2025-10-31', '现金管理', '代收代付', 80000000.00, 300, 40000.00, 40000.00, 1),
(14, 'C20004', '2025-10-31', '票据业务', '贴现业务', 5000000.00, 15, 25000.00, 25000.00, 1),
(15, 'C20005', '2025-10-31', '结算业务', '企业网银', 75000000.00, 1850, 37500.00, 37500.00, 1),
(15, 'C20005', '2025-10-31', '国际业务', '进口开证', 8400000.00, 60, 84000.00, 84000.00, 1),
(15, 'C20005', '2025-10-31', '咨询顾问', 'IPO顾问', 0.00, 1, 150000.00, 150000.00, 1),
(16, 'C20006', '2025-10-31', '结算业务', '企业网银', 28000000.00, 680, 14000.00, 14000.00, 1),
(16, 'C20006', '2025-10-31', '现金管理', '代发工资', 12000000.00, 12, 6000.00, 6000.00, 1),
(16, 'C20006', '2025-10-31', '票据业务', '承兑汇票', 4000000.00, 10, 20000.00, 20000.00, 1),
(17, 'C20007', '2025-10-31', '结算业务', '企业网银', 90000000.00, 2200, 45000.00, 45000.00, 1),
(17, 'C20007', '2025-10-31', '现金管理', '资金归集', 120000000.00, 365, 60000.00, 60000.00, 1),
(17, 'C20007', '2025-10-31', '票据业务', '贴现业务', 6500000.00, 18, 32500.00, 32500.00, 1),
(18, 'C20008', '2025-10-31', '结算业务', '企业网银', 85000000.00, 2100, 42500.00, 42500.00, 1),
(18, 'C20008', '2025-10-31', '国际业务', '出口押汇', 6300000.00, 45, 63000.00, 63000.00, 1),
(18, 'C20008', '2025-10-31', '咨询顾问', '并购顾问', 0.00, 1, 120000.00, 120000.00, 1),
(19, 'C20009', '2025-10-31', '结算业务', '企业网银', 55000000.00, 1350, 27500.00, 27500.00, 1),
(19, 'C20009', '2025-10-31', '现金管理', '代收代付', 70000000.00, 260, 35000.00, 35000.00, 1),
(19, 'C20009', '2025-10-31', '票据业务', '承兑汇票', 9000000.00, 22, 45000.00, 45000.00, 1),
(20, 'C20010', '2025-10-31', '结算业务', '企业网银', 95000000.00, 2350, 47500.00, 47500.00, 1),
(20, 'C20010', '2025-10-31', '国际业务', '国际结算', 10500000.00, 75, 105000.00, 105000.00, 1),
(20, 'C20010', '2025-10-31', '现金管理', '资金归集', 150000000.00, 365, 75000.00, 75000.00, 1);

-- 4. 对公客户渠道业务概览数据
INSERT INTO `crm_customer_overview_channel`
(`customer_id`, `customer_no`, `statistics_date`, `channel_type`, `channel_name`,
 `login_count`, `transaction_count`, `transaction_amount`, `active_days`, `usage_frequency`, `tenant_id`)
VALUES
(11, 'C20001', '2025-10-31', '网银', '企业网银', 285, 650, 50000000.00, 25, '高频', 1),
(11, 'C20001', '2025-10-31', '移动银行', '企业手机银行', 180, 320, 15000000.00, 28, '中频', 1),
(11, 'C20001', '2025-10-31', '柜台', '营业网点', 15, 12, 8000000.00, 12, '低频', 1),
(12, 'C20002', '2025-10-31', '网银', '企业网银', 310, 720, 60000000.00, 26, '高频', 1),
(12, 'C20002', '2025-10-31', '移动银行', '企业手机银行', 205, 385, 18000000.00, 29, '高频', 1),
(12, 'C20002', '2025-10-31', '柜台', '营业网点', 18, 15, 10000000.00, 15, '低频', 1),
(13, 'C20003', '2025-10-31', '网银', '企业网银', 260, 580, 45000000.00, 24, '高频', 1),
(13, 'C20003', '2025-10-31', '移动银行', '企业手机银行', 165, 295, 12000000.00, 27, '中频', 1),
(13, 'C20003', '2025-10-31', '柜台', '营业网点', 12, 10, 7000000.00, 10, '低频', 1),
(14, 'C20004', '2025-10-31', '网银', '企业网银', 230, 510, 35000000.00, 23, '高频', 1),
(14, 'C20004', '2025-10-31', '移动银行', '企业手机银行', 140, 250, 10000000.00, 26, '中频', 1),
(14, 'C20004', '2025-10-31', '柜台', '营业网点', 10, 8, 5500000.00, 8, '低频', 1),
(15, 'C20005', '2025-10-31', '网银', '企业网银', 350, 820, 75000000.00, 27, '高频', 1),
(15, 'C20005', '2025-10-31', '移动银行', '企业手机银行', 240, 480, 22000000.00, 30, '高频', 1),
(15, 'C20005', '2025-10-31', '柜台', '营业网点', 20, 16, 12000000.00, 16, '低频', 1),
(16, 'C20006', '2025-10-31', '网银', '企业网银', 195, 430, 28000000.00, 22, '中频', 1),
(16, 'C20006', '2025-10-31', '移动银行', '企业手机银行', 125, 220, 8000000.00, 25, '中频', 1),
(16, 'C20006', '2025-10-31', '柜台', '营业网点', 8, 6, 4000000.00, 6, '低频', 1),
(17, 'C20007', '2025-10-31', '网银', '企业网银', 395, 920, 90000000.00, 28, '高频', 1),
(17, 'C20007', '2025-10-31', '移动银行', '企业手机银行', 270, 550, 26000000.00, 30, '高频', 1),
(17, 'C20007', '2025-10-31', '柜台', '营业网点', 22, 18, 13000000.00, 18, '低频', 1),
(18, 'C20008', '2025-10-31', '网银', '企业网银', 375, 870, 85000000.00, 27, '高频', 1),
(18, 'C20008', '2025-10-31', '移动银行', '企业手机银行', 255, 510, 24000000.00, 29, '高频', 1),
(18, 'C20008', '2025-10-31', '柜台', '营业网点', 19, 15, 11000000.00, 15, '低频', 1),
(19, 'C20009', '2025-10-31', '网银', '企业网银', 275, 620, 55000000.00, 25, '高频', 1),
(19, 'C20009', '2025-10-31', '移动银行', '企业手机银行', 185, 350, 16000000.00, 28, '中频', 1),
(19, 'C20009', '2025-10-31', '柜台', '营业网点', 14, 11, 8500000.00, 11, '低频', 1),
(20, 'C20010', '2025-10-31', '网银', '企业网银', 420, 980, 95000000.00, 29, '高频', 1),
(20, 'C20010', '2025-10-31', '移动银行', '企业手机银行', 285, 580, 28000000.00, 30, '高频', 1),
(20, 'C20010', '2025-10-31', '柜台', '营业网点', 25, 20, 15000000.00, 20, '低频', 1);
