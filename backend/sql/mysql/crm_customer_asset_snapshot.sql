-- 客户资产快照表（用于记录客户每月的资产状况）
CREATE TABLE IF NOT EXISTS `crm_customer_asset_snapshot` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '快照主键',
    `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',
    `snapshot_date` date NOT NULL COMMENT '快照日期（通常为每月最后一天）',
    `snapshot_type` varchar(20) NOT NULL DEFAULT 'monthly' COMMENT '快照类型（monthly=月度, quarterly=季度, yearly=年度）',

    -- 资产类数据
    `total_assets` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '总资产',
    `deposit_balance` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '存款余额',
    `wealth_balance` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '理财余额',
    `fund_balance` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '基金市值',
    `insurance_balance` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '保险价值',
    `metal_balance` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '贵金属余额',
    `trust_balance` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '信托余额',
    `other_balance` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '其他资产',

    -- 负债类数据
    `total_liabilities` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '总负债',
    `loan_balance` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '贷款余额',
    `creditcard_balance` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '信用卡欠款',

    -- 净资产
    `net_assets` decimal(24, 6) NOT NULL DEFAULT 0 COMMENT '净资产（总资产-总负债）',

    -- 环比增长（与上期对比）
    `total_assets_growth` decimal(10, 4) NULL COMMENT '总资产环比增长率',
    `deposit_growth` decimal(10, 4) NULL COMMENT '存款环比增长率',
    `wealth_growth` decimal(10, 4) NULL COMMENT '理财环比增长率',

    -- 账户数量统计
    `deposit_account_count` int NOT NULL DEFAULT 0 COMMENT '存款账户数量',
    `wealth_account_count` int NOT NULL DEFAULT 0 COMMENT '理财产品数量',
    `fund_account_count` int NOT NULL DEFAULT 0 COMMENT '基金数量',
    `loan_account_count` int NOT NULL DEFAULT 0 COMMENT '贷款数量',
    `creditcard_count` int NOT NULL DEFAULT 0 COMMENT '信用卡数量',
    `insurance_count` int NOT NULL DEFAULT 0 COMMENT '保险数量',

    -- 备注信息
    `remark` varchar(500) NULL COMMENT '备注',

    -- 标准字段
    `creator` varchar(64) NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

    PRIMARY KEY (`id`),
    KEY `idx_customer_id` (`customer_id`),
    KEY `idx_snapshot_date` (`snapshot_date`),
    KEY `idx_customer_snapshot` (`customer_id`, `snapshot_date`),
    KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM客户资产快照表';

-- 索引说明：
-- 1. idx_customer_id: 查询某个客户的所有快照
-- 2. idx_snapshot_date: 查询某个时间点的所有客户快照
-- 3. idx_customer_snapshot: 组合索引，用于查询某个客户在某个时间段的快照（最常用的查询模式）
-- 4. idx_tenant_id: 多租户隔离
