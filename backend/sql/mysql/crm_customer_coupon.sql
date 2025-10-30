-- ----------------------------
-- 客户卡券信息表
-- 设计原则:
-- 1. 零售客户特有的权益管理模块
-- 2. 记录客户持有的优惠券、折扣券、礼品券等卡券信息
-- 3. 支持卡券全生命周期管理：发放、使用、过期、作废
-- 4. 支持多种卡券类型和使用场景
-- ----------------------------

-- ==================== 客户卡券信息表 ====================
DROP TABLE IF EXISTS `crm_customer_coupon`;
CREATE TABLE `crm_customer_coupon` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '卡券主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表，仅限零售客户）',

  -- ==================== 卡券基本信息 ====================
  `coupon_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '卡券编号（唯一）',
  `coupon_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '卡券代码（券模板编码）',
  `coupon_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '卡券名称',
  `coupon_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '卡券类型（字典: aicrm_coupon_type，discount=折扣券，cash=代金券，gift=礼品券，rate=利率券，fee_waiver=手续费减免券）',
  `coupon_category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '卡券分类（字典: aicrm_coupon_category，deposit=存款类，wealth=理财类，loan=贷款类，payment=支付类，general=通用类）',

  -- ==================== 卡券价值信息 ====================
  `discount_rate` decimal(5,2) NULL DEFAULT NULL COMMENT '折扣率（如8.5表示8.5折，仅折扣券有效）',
  `discount_amount` decimal(18,2) NULL DEFAULT NULL COMMENT '优惠金额（代金券金额或减免金额）',
  `gift_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '礼品名称（仅礼品券有效）',
  `rate_discount` decimal(5,2) NULL DEFAULT NULL COMMENT '利率优惠（如0.5表示利率下调0.5%，仅利率券有效）',
  `threshold_amount` decimal(18,2) NULL DEFAULT 0.00 COMMENT '使用门槛金额（满多少可用，0表示无门槛）',
  `max_discount_amount` decimal(18,2) NULL DEFAULT NULL COMMENT '最高优惠金额（封顶金额）',

  -- ==================== 卡券状态信息 ====================
  `coupon_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'unused' COMMENT '卡券状态（字典: aicrm_coupon_status，unused=未使用，used=已使用，expired=已过期，cancelled=已作废，locked=已锁定）',
  `issue_date` date NOT NULL COMMENT '发放日期',
  `issue_time` datetime NOT NULL COMMENT '发放时间',
  `effective_date` date NOT NULL COMMENT '生效日期',
  `expiry_date` date NOT NULL COMMENT '过期日期',
  `use_date` date NULL DEFAULT NULL COMMENT '使用日期',
  `use_time` datetime NULL DEFAULT NULL COMMENT '使用时间',
  `cancel_date` date NULL DEFAULT NULL COMMENT '作废日期',
  `cancel_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '作废原因',

  -- ==================== 发放来源信息 ====================
  `issue_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发放来源（字典: aicrm_coupon_issue_source，system_gift=系统赠送，activity_reward=活动奖励，birthday_gift=生日礼物，point_exchange=积分兑换，manual_issue=手动发放）',
  `issue_channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '发放渠道（字典: aicrm_coupon_channel，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，counter=柜台，crm=CRM系统）',
  `issue_activity_id` bigint NULL DEFAULT NULL COMMENT '关联活动ID（如果来源于营销活动）',
  `issue_activity_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联活动名称',

  -- ==================== 使用信息 ====================
  `use_scenario` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '使用场景（字典: aicrm_coupon_use_scenario，online_purchase=线上购买，counter_purchase=柜台办理，app_purchase=APP办理，wealth_purchase=理财购买，loan_apply=贷款申请）',
  `use_channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '使用渠道（字典: aicrm_coupon_channel）',
  `use_product_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '使用产品代码',
  `use_product_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '使用产品名称',
  `use_order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '使用订单号',
  `use_transaction_amount` decimal(18,2) NULL DEFAULT NULL COMMENT '交易金额（使用卡券时的交易金额）',
  `actual_discount_amount` decimal(18,2) NULL DEFAULT NULL COMMENT '实际优惠金额',

  -- ==================== 规则限制 ====================
  `applicable_products` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '适用产品（JSON数组，如["PROD001","PROD002"]，null表示通用）',
  `applicable_channels` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '适用渠道（JSON数组，null表示通用）',
  `use_limit_times` int NOT NULL DEFAULT 1 COMMENT '可使用次数（1=单次使用，>1=可多次使用）',
  `used_times` int NOT NULL DEFAULT 0 COMMENT '已使用次数',
  `is_transferable` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否可转赠',
  `is_stackable` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否可叠加使用',

  -- ==================== 提醒信息 ====================
  `expire_remind_days` int NOT NULL DEFAULT 7 COMMENT '过期提醒天数（提前几天提醒）',
  `is_reminded` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已提醒',
  `remind_time` datetime NULL DEFAULT NULL COMMENT '提醒时间',

  -- ==================== 备注信息 ====================
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '卡券描述（使用说明）',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '备注',

  -- ==================== 审计字段 ====================
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_coupon_no`(`coupon_no` ASC) USING BTREE COMMENT '卡券编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_coupon_code`(`coupon_code` ASC) USING BTREE COMMENT '卡券代码索引',
  INDEX `idx_coupon_type`(`coupon_type` ASC) USING BTREE COMMENT '卡券类型索引',
  INDEX `idx_coupon_status`(`coupon_status` ASC) USING BTREE COMMENT '卡券状态索引',
  INDEX `idx_expiry_date`(`expiry_date` ASC) USING BTREE COMMENT '过期日期索引',
  INDEX `idx_issue_date`(`issue_date` ASC) USING BTREE COMMENT '发放日期索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户卡券信息表（零售客户特有）';


-- ==================== 字典类型和字典数据 ====================

-- 1. 卡券类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM卡券类型', 'aicrm_coupon_type', 0, '客户卡券的类型分类', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM卡券类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '折扣券', 'discount', 'aicrm_coupon_type', 0, 'primary', '享受折扣优惠（如9折、8.5折）', '1', NOW(), '1', NOW(), b'0'),
(2, '代金券', 'cash', 'aicrm_coupon_type', 0, 'success', '抵扣现金使用（如满100减20）', '1', NOW(), '1', NOW(), b'0'),
(3, '礼品券', 'gift', 'aicrm_coupon_type', 0, 'warning', '兑换指定礼品', '1', NOW(), '1', NOW(), b'0'),
(4, '利率券', 'rate', 'aicrm_coupon_type', 0, 'danger', '享受利率优惠（存款加息或贷款降息）', '1', NOW(), '1', NOW(), b'0'),
(5, '手续费减免券', 'fee_waiver', 'aicrm_coupon_type', 0, 'info', '减免手续费', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 卡券分类字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM卡券分类', 'aicrm_coupon_category', 0, '卡券的业务分类', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM卡券分类';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '存款类', 'deposit', 'aicrm_coupon_category', 0, 'primary', '适用于存款业务', '1', NOW(), '1', NOW(), b'0'),
(2, '理财类', 'wealth', 'aicrm_coupon_category', 0, 'success', '适用于理财业务', '1', NOW(), '1', NOW(), b'0'),
(3, '贷款类', 'loan', 'aicrm_coupon_category', 0, 'warning', '适用于贷款业务', '1', NOW(), '1', NOW(), b'0'),
(4, '支付类', 'payment', 'aicrm_coupon_category', 0, 'danger', '适用于支付场景', '1', NOW(), '1', NOW(), b'0'),
(5, '通用类', 'general', 'aicrm_coupon_category', 0, 'info', '可用于多种业务', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 卡券状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM卡券状态', 'aicrm_coupon_status', 0, '卡券的使用状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM卡券状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '未使用', 'unused', 'aicrm_coupon_status', 0, 'primary', '卡券可正常使用', '1', NOW(), '1', NOW(), b'0'),
(2, '已使用', 'used', 'aicrm_coupon_status', 0, 'success', '卡券已使用完毕', '1', NOW(), '1', NOW(), b'0'),
(3, '已过期', 'expired', 'aicrm_coupon_status', 0, 'warning', '卡券已过期', '1', NOW(), '1', NOW(), b'0'),
(4, '已作废', 'cancelled', 'aicrm_coupon_status', 0, 'danger', '卡券已作废', '1', NOW(), '1', NOW(), b'0'),
(5, '已锁定', 'locked', 'aicrm_coupon_status', 0, 'info', '卡券已锁定（订单处理中）', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 卡券发放来源字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM卡券发放来源', 'aicrm_coupon_issue_source', 0, '卡券的发放来源', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM卡券发放来源';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '系统赠送', 'system_gift', 'aicrm_coupon_issue_source', 0, 'primary', '系统自动发放', '1', NOW(), '1', NOW(), b'0'),
(2, '活动奖励', 'activity_reward', 'aicrm_coupon_issue_source', 0, 'success', '参与活动获得', '1', NOW(), '1', NOW(), b'0'),
(3, '生日礼物', 'birthday_gift', 'aicrm_coupon_issue_source', 0, 'warning', '生日当月赠送', '1', NOW(), '1', NOW(), b'0'),
(4, '积分兑换', 'point_exchange', 'aicrm_coupon_issue_source', 0, 'danger', '使用积分兑换', '1', NOW(), '1', NOW(), b'0'),
(5, '手动发放', 'manual_issue', 'aicrm_coupon_issue_source', 0, 'info', '人工手动发放', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 卡券渠道字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM卡券渠道', 'aicrm_coupon_channel', 0, '卡券的发放和使用渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM卡券渠道';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '手机银行', 'mobile_banking', 'aicrm_coupon_channel', 0, 'primary', '手机银行APP', '1', NOW(), '1', NOW(), b'0'),
(2, '网上银行', 'web_banking', 'aicrm_coupon_channel', 0, 'success', '网上银行网站', '1', NOW(), '1', NOW(), b'0'),
(3, '微信银行', 'wechat', 'aicrm_coupon_channel', 0, 'warning', '微信公众号或小程序', '1', NOW(), '1', NOW(), b'0'),
(4, '柜台', 'counter', 'aicrm_coupon_channel', 0, 'danger', '银行网点柜台', '1', NOW(), '1', NOW(), b'0'),
(5, 'CRM系统', 'crm', 'aicrm_coupon_channel', 0, 'info', 'CRM管理系统', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 6. 卡券使用场景字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM卡券使用场景', 'aicrm_coupon_use_scenario', 0, '卡券的使用场景', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM卡券使用场景';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '线上购买', 'online_purchase', 'aicrm_coupon_use_scenario', 0, 'primary', '线上渠道购买产品', '1', NOW(), '1', NOW(), b'0'),
(2, '柜台办理', 'counter_purchase', 'aicrm_coupon_use_scenario', 0, 'success', '柜台办理业务', '1', NOW(), '1', NOW(), b'0'),
(3, 'APP办理', 'app_purchase', 'aicrm_coupon_use_scenario', 0, 'warning', '手机APP办理', '1', NOW(), '1', NOW(), b'0'),
(4, '理财购买', 'wealth_purchase', 'aicrm_coupon_use_scenario', 0, 'danger', '购买理财产品', '1', NOW(), '1', NOW(), b'0'),
(5, '贷款申请', 'loan_apply', 'aicrm_coupon_use_scenario', 0, 'info', '申请贷款业务', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
