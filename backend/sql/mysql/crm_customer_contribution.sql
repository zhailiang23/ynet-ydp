-- ----------------------------
-- 客户贡献度信息表
-- 设计原则:
-- 1. 记录客户的综合贡献度信息（与客户主表1对1关系）
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：序号、客户综合贡献度、存款贡献度、贷款贡献度、中间业务贡献度、统计时间
-- 4. 参考老系统 ocrm_f_ci_cust_contribution 表设计
-- ----------------------------

-- ==================== 客户贡献度信息表 ====================
DROP TABLE IF EXISTS `crm_customer_contribution`;
CREATE TABLE `crm_customer_contribution` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '贡献度主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表，1对1关系）',

  -- ==================== 图片字段 ====================
  `sequence_no` int NULL DEFAULT NULL COMMENT '序号',
  `total_contribution` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '客户综合贡献度',
  `deposit_contribution` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '存款贡献度',
  `loan_contribution` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '贷款贡献度',
  `intermediate_contribution` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '中间业务贡献度',
  `statistics_time` date NOT NULL COMMENT '统计时间',

  -- ==================== 老系统字段（ocrm_f_ci_cust_contribution）====================
  `cust_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户名称（老系统字段）',
  `org_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机构ID（老系统）',
  `contri_deposit` decimal(24,6) NULL DEFAULT NULL COMMENT '存款贡献度（老系统字段名）',
  `contribution_loan` decimal(24,6) NULL DEFAULT NULL COMMENT '贷款贡献度（老系统字段名）',
  `contribution_mid` decimal(24,6) NULL DEFAULT NULL COMMENT '中间业务贡献度（老系统字段名）',
  `contribution_cust` decimal(24,6) NULL DEFAULT NULL COMMENT '客户综合贡献度（老系统字段名）',
  `contri_bill_discount` decimal(24,6) NULL DEFAULT NULL COMMENT '票据贴现贡献度',
  `contri_internation` decimal(24,6) NULL DEFAULT NULL COMMENT '国际业务贡献度',
  `etl_date` date NULL DEFAULT NULL COMMENT '数据加载日期（老系统）',

  -- ==================== 扩展字段 ====================
  `wealth_management_contribution` decimal(24,6) NULL DEFAULT 0 COMMENT '理财业务贡献度',
  `card_contribution` decimal(24,6) NULL DEFAULT 0 COMMENT '卡业务贡献度',
  `settlement_contribution` decimal(24,6) NULL DEFAULT 0 COMMENT '结算业务贡献度',
  `electronic_banking_contribution` decimal(24,6) NULL DEFAULT 0 COMMENT '电子银行贡献度',
  `contribution_rank` int NULL DEFAULT NULL COMMENT '贡献度排名',
  `contribution_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '贡献度等级（字典: aicrm_contribution_level）',
  `year_over_year_growth` decimal(10,4) NULL DEFAULT NULL COMMENT '同比增长率（%）',
  `month_over_month_growth` decimal(10,4) NULL DEFAULT NULL COMMENT '环比增长率（%）',
  `statistics_period` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'monthly' COMMENT '统计周期（字典: aicrm_statistics_period，daily=日，monthly=月，quarterly=季，yearly=年）',

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
  UNIQUE INDEX `uk_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID唯一索引（1对1关系）',
  INDEX `idx_statistics_time`(`statistics_time` ASC) USING BTREE COMMENT '统计时间索引',
  INDEX `idx_total_contribution`(`total_contribution` ASC) USING BTREE COMMENT '综合贡献度索引',
  INDEX `idx_contribution_level`(`contribution_level` ASC) USING BTREE COMMENT '贡献度等级索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户贡献度信息表（与客户主表1对1关系）';


-- ==================== 字典类型和字典数据 ====================

-- 1. 贡献度等级字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM贡献度等级', 'aicrm_contribution_level', 0, '客户贡献度等级', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM贡献度等级';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '特级贡献', 'super', 'aicrm_contribution_level', 0, 'danger', '特级贡献客户', '1', NOW(), '1', NOW(), b'0'),
(2, '一级贡献', 'level_1', 'aicrm_contribution_level', 0, 'warning', '一级贡献客户', '1', NOW(), '1', NOW(), b'0'),
(3, '二级贡献', 'level_2', 'aicrm_contribution_level', 0, 'primary', '二级贡献客户', '1', NOW(), '1', NOW(), b'0'),
(4, '三级贡献', 'level_3', 'aicrm_contribution_level', 0, 'success', '三级贡献客户', '1', NOW(), '1', NOW(), b'0'),
(5, '四级贡献', 'level_4', 'aicrm_contribution_level', 0, 'info', '四级贡献客户', '1', NOW(), '1', NOW(), b'0'),
(6, '五级贡献', 'level_5', 'aicrm_contribution_level', 0, 'default', '五级贡献客户', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 统计周期字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM统计周期', 'aicrm_statistics_period', 0, '贡献度统计周期', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM统计周期';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '日统计', 'daily', 'aicrm_statistics_period', 0, 'primary', '按日统计', '1', NOW(), '1', NOW(), b'0'),
(2, '月统计', 'monthly', 'aicrm_statistics_period', 0, 'success', '按月统计', '1', NOW(), '1', NOW(), b'0'),
(3, '季度统计', 'quarterly', 'aicrm_statistics_period', 0, 'info', '按季度统计', '1', NOW(), '1', NOW(), b'0'),
(4, '年度统计', 'yearly', 'aicrm_statistics_period', 0, 'warning', '按年度统计', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
