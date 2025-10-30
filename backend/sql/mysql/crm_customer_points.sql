-- ----------------------------
-- 客户积分信息表
-- 设计原则:
-- 1. 记录客户的积分账户信息
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：可用积分、历史累计赠送积分、历史累计扣减积分、历史累计失效积分、即将失效积分、即将失效积分日期
-- 4. 支持积分获取、消费、失效等完整生命周期管理
-- ----------------------------

-- ==================== 客户积分信息表 ====================
DROP TABLE IF EXISTS `crm_customer_points`;
CREATE TABLE `crm_customer_points` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '积分主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 图片字段（客户积分明细） ====================
  `available_points` bigint NOT NULL DEFAULT 0 COMMENT '可用积分',
  `history_total_gift_points` bigint NOT NULL DEFAULT 0 COMMENT '历史累计赠送积分',
  `history_total_deduct_points` bigint NOT NULL DEFAULT 0 COMMENT '历史累计扣减积分',
  `history_total_expire_points` bigint NOT NULL DEFAULT 0 COMMENT '历史累计失效积分',
  `upcoming_expire_points` bigint NOT NULL DEFAULT 0 COMMENT '即将失效积分',
  `upcoming_expire_date` date NULL DEFAULT NULL COMMENT '即将失效积分日期',

  -- ==================== 扩展字段 ====================
  `points_account_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '积分账户编号',
  `points_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '积分等级（字典: aicrm_points_level，bronze=铜卡，silver=银卡，gold=金卡，platinum=白金卡，diamond=钻石卡）',
  `total_earned_points` bigint NOT NULL DEFAULT 0 COMMENT '累计获得积分（包含赠送和交易）',
  `total_used_points` bigint NOT NULL DEFAULT 0 COMMENT '累计使用积分',
  `frozen_points` bigint NOT NULL DEFAULT 0 COMMENT '冻结积分',
  `history_total_transaction_points` bigint NOT NULL DEFAULT 0 COMMENT '历史累计交易积分',
  `history_total_redeem_points` bigint NOT NULL DEFAULT 0 COMMENT '历史累计兑换积分',
  `points_balance` bigint NOT NULL DEFAULT 0 COMMENT '积分余额（可用+冻结）',
  `account_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'active' COMMENT '账户状态（字典: aicrm_points_account_status，active=正常，frozen=冻结，closed=关闭）',
  `open_date` date NOT NULL COMMENT '开户日期',
  `last_transaction_date` date NULL DEFAULT NULL COMMENT '最后交易日期',
  `last_transaction_time` datetime NULL DEFAULT NULL COMMENT '最后交易时间',
  `last_gift_date` date NULL DEFAULT NULL COMMENT '最后赠送日期',
  `last_redeem_date` date NULL DEFAULT NULL COMMENT '最后兑换日期',
  `points_valid_months` int NOT NULL DEFAULT 36 COMMENT '积分有效期（月）',
  `is_auto_expire` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否自动失效',
  `auto_expire_remind_days` int NOT NULL DEFAULT 30 COMMENT '自动失效提醒天数',
  `year_earned_points` bigint NOT NULL DEFAULT 0 COMMENT '本年度获得积分',
  `year_used_points` bigint NOT NULL DEFAULT 0 COMMENT '本年度使用积分',
  `month_earned_points` bigint NOT NULL DEFAULT 0 COMMENT '本月获得积分',
  `month_used_points` bigint NOT NULL DEFAULT 0 COMMENT '本月使用积分',

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
  UNIQUE INDEX `uk_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID唯一索引（一个客户一个积分账户）',
  UNIQUE INDEX `uk_points_account_no`(`points_account_no` ASC) USING BTREE COMMENT '积分账户编号唯一索引',
  INDEX `idx_points_level`(`points_level` ASC) USING BTREE COMMENT '积分等级索引',
  INDEX `idx_account_status`(`account_status` ASC) USING BTREE COMMENT '账户状态索引',
  INDEX `idx_upcoming_expire_date`(`upcoming_expire_date` ASC) USING BTREE COMMENT '即将失效日期索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户积分信息表';


-- ==================== 字典类型和字典数据 ====================

-- 1. 积分等级字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM积分等级', 'aicrm_points_level', 0, '客户积分等级分类', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM积分等级';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '铜卡', 'bronze', 'aicrm_points_level', 0, 'default', '积分0-9999', '1', NOW(), '1', NOW(), b'0'),
(2, '银卡', 'silver', 'aicrm_points_level', 0, 'info', '积分10000-49999', '1', NOW(), '1', NOW(), b'0'),
(3, '金卡', 'gold', 'aicrm_points_level', 0, 'warning', '积分50000-99999', '1', NOW(), '1', NOW(), b'0'),
(4, '白金卡', 'platinum', 'aicrm_points_level', 0, 'primary', '积分100000-499999', '1', NOW(), '1', NOW(), b'0'),
(5, '钻石卡', 'diamond', 'aicrm_points_level', 0, 'danger', '积分500000以上', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 积分账户状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM积分账户状态', 'aicrm_points_account_status', 0, '积分账户的状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM积分账户状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '正常', 'active', 'aicrm_points_account_status', 0, 'success', '账户正常使用', '1', NOW(), '1', NOW(), b'0'),
(2, '冻结', 'frozen', 'aicrm_points_account_status', 0, 'warning', '账户已冻结', '1', NOW(), '1', NOW(), b'0'),
(3, '关闭', 'closed', 'aicrm_points_account_status', 0, 'danger', '账户已关闭', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
