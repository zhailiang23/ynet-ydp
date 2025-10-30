-- ----------------------------
-- 客户积分消费明细表（积分交易流水表）
-- 设计原则:
-- 1. 记录客户积分的所有变动明细
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：订单编号、交易日期、兑换渠道、礼品兑换方式、订单消耗积分、订单状态、订单审批状态、兑换用户
-- 4. 支持积分获取、消费、兑换、失效等所有交易类型
-- ----------------------------

-- ==================== 客户积分消费明细表 ====================
DROP TABLE IF EXISTS `crm_customer_points_transaction`;
CREATE TABLE `crm_customer_points_transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '交易主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',
  `points_account_id` bigint NOT NULL COMMENT '积分账户ID（关联 crm_customer_points 表）',

  -- ==================== 图片字段（订单明细） ====================
  `order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单编号',
  `transaction_date` date NOT NULL COMMENT '交易日期',
  `exchange_channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）',
  `gift_exchange_method` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）',
  `order_consumed_points` bigint NOT NULL COMMENT '订单消耗积分',
  `order_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）',
  `order_approval_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）',
  `exchange_user` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '兑换用户',

  -- ==================== 扩展字段 ====================
  `transaction_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '交易流水号',
  `transaction_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）',
  `transaction_time` datetime NOT NULL COMMENT '交易时间',
  `points_change` bigint NOT NULL COMMENT '积分变动（正数为增加，负数为减少）',
  `points_before` bigint NOT NULL COMMENT '交易前积分',
  `points_after` bigint NOT NULL COMMENT '交易后积分',
  `transaction_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）',
  `transaction_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交易描述',
  `business_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务类型（字典: aicrm_points_business_type）',
  `business_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '业务单号',
  `gift_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '礼品编码',
  `gift_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '礼品名称',
  `gift_quantity` int NULL DEFAULT NULL COMMENT '礼品数量',
  `gift_value` decimal(24,6) NULL DEFAULT NULL COMMENT '礼品价值',
  `delivery_address` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配送地址',
  `delivery_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '配送状态（字典: aicrm_delivery_status，pending=待发货，shipped=已发货，delivered=已送达，rejected=拒收）',
  `delivery_time` datetime NULL DEFAULT NULL COMMENT '配送时间',
  `receiver_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货人姓名',
  `receiver_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '收货人电话',
  `expire_date` date NULL DEFAULT NULL COMMENT '过期日期（对于有效期的积分）',
  `refund_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '退款原因',
  `refund_time` datetime NULL DEFAULT NULL COMMENT '退款时间',
  `refund_points` bigint NULL DEFAULT NULL COMMENT '退款积分',
  `approval_user_id` bigint NULL DEFAULT NULL COMMENT '审批人ID',
  `approval_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批人姓名',
  `approval_time` datetime NULL DEFAULT NULL COMMENT '审批时间',
  `approval_remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批备注',
  `operator_user_id` bigint NULL DEFAULT NULL COMMENT '操作人ID',
  `operator_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作人姓名',
  `operator_dept_id` bigint NULL DEFAULT NULL COMMENT '操作部门ID',
  `operator_dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '操作部门名称',
  `is_reversed` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已冲正',
  `reverse_transaction_id` bigint NULL DEFAULT NULL COMMENT '冲正交易ID',
  `reverse_time` datetime NULL DEFAULT NULL COMMENT '冲正时间',

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
  UNIQUE INDEX `uk_transaction_no`(`transaction_no` ASC) USING BTREE COMMENT '交易流水号唯一索引',
  UNIQUE INDEX `uk_order_no`(`order_no` ASC) USING BTREE COMMENT '订单编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_points_account_id`(`points_account_id` ASC) USING BTREE COMMENT '积分账户ID索引',
  INDEX `idx_transaction_type`(`transaction_type` ASC) USING BTREE COMMENT '交易类型索引',
  INDEX `idx_transaction_date`(`transaction_date` ASC) USING BTREE COMMENT '交易日期索引',
  INDEX `idx_transaction_time`(`transaction_time` ASC) USING BTREE COMMENT '交易时间索引',
  INDEX `idx_order_status`(`order_status` ASC) USING BTREE COMMENT '订单状态索引',
  INDEX `idx_order_approval_status`(`order_approval_status` ASC) USING BTREE COMMENT '订单审批状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户积分消费明细表（积分交易流水表）';


-- ==================== 字典类型和字典数据 ====================

-- 1. 兑换渠道字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM兑换渠道', 'aicrm_exchange_channel', 0, '积分兑换的渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM兑换渠道';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, 'CRM系统', 'crm', 'aicrm_exchange_channel', 0, 'primary', 'CRM系统兑换', '1', NOW(), '1', NOW(), b'0'),
(2, '网银', 'website', 'aicrm_exchange_channel', 0, 'success', '网上银行兑换', '1', NOW(), '1', NOW(), b'0'),
(3, '手机银行', 'mobile_banking', 'aicrm_exchange_channel', 0, 'info', '手机银行APP兑换', '1', NOW(), '1', NOW(), b'0'),
(4, '微信银行', 'wechat', 'aicrm_exchange_channel', 0, 'success', '微信银行兑换', '1', NOW(), '1', NOW(), b'0'),
(5, '柜台', 'counter', 'aicrm_exchange_channel', 0, 'warning', '线下柜台兑换', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 礼品兑换方式字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM礼品兑换方式', 'aicrm_gift_exchange_method', 0, '礼品的兑换方式', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM礼品兑换方式';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '线上兑换', 'online', 'aicrm_gift_exchange_method', 0, 'primary', '线上直接兑换', '1', NOW(), '1', NOW(), b'0'),
(2, '线下兑换', 'offline', 'aicrm_gift_exchange_method', 0, 'success', '线下柜台兑换', '1', NOW(), '1', NOW(), b'0'),
(3, '邮寄', 'mail', 'aicrm_gift_exchange_method', 0, 'info', '邮寄到指定地址', '1', NOW(), '1', NOW(), b'0'),
(4, '自提', 'pickup', 'aicrm_gift_exchange_method', 0, 'warning', '到指定地点自提', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 订单状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM订单状态', 'aicrm_order_status', 0, '积分兑换订单的状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM订单状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '待处理', '10', 'aicrm_order_status', 0, 'info', '订单待处理', '1', NOW(), '1', NOW(), b'0'),
(2, '处理中', '20', 'aicrm_order_status', 0, 'primary', '订单处理中', '1', NOW(), '1', NOW(), b'0'),
(3, '已完成', '30', 'aicrm_order_status', 0, 'success', '订单已完成', '1', NOW(), '1', NOW(), b'0'),
(4, '已取消', '40', 'aicrm_order_status', 0, 'warning', '订单已取消', '1', NOW(), '1', NOW(), b'0'),
(5, '已退款', '50', 'aicrm_order_status', 0, 'danger', '订单已退款', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 订单审批状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM订单审批状态', 'aicrm_order_approval_status', 0, '订单的审批状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM订单审批状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '待审批', '1', 'aicrm_order_approval_status', 0, 'warning', '待审批', '1', NOW(), '1', NOW(), b'0'),
(2, '审批通过', '2', 'aicrm_order_approval_status', 0, 'success', '审批已通过', '1', NOW(), '1', NOW(), b'0'),
(3, '审批拒绝', '3', 'aicrm_order_approval_status', 0, 'danger', '审批被拒绝', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 交易类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM积分交易类型', 'aicrm_points_transaction_type', 0, '积分交易的类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM积分交易类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '存款获取', 'earn_deposit', 'aicrm_points_transaction_type', 0, 'success', '存款获得积分', '1', NOW(), '1', NOW(), b'0'),
(2, '理财获取', 'earn_wealth', 'aicrm_points_transaction_type', 0, 'success', '购买理财获得积分', '1', NOW(), '1', NOW(), b'0'),
(3, '交易获取', 'earn_transaction', 'aicrm_points_transaction_type', 0, 'success', '交易获得积分', '1', NOW(), '1', NOW(), b'0'),
(4, '活动获取', 'earn_activity', 'aicrm_points_transaction_type', 0, 'success', '参加活动获得积分', '1', NOW(), '1', NOW(), b'0'),
(5, '赠送获取', 'earn_gift', 'aicrm_points_transaction_type', 0, 'success', '赠送获得积分', '1', NOW(), '1', NOW(), b'0'),
(6, '兑换消费', 'consume_redeem', 'aicrm_points_transaction_type', 0, 'warning', '兑换礼品消费积分', '1', NOW(), '1', NOW(), b'0'),
(7, '扣减', 'consume_deduct', 'aicrm_points_transaction_type', 0, 'danger', '扣减积分', '1', NOW(), '1', NOW(), b'0'),
(8, '过期失效', 'expire', 'aicrm_points_transaction_type', 0, 'default', '积分过期失效', '1', NOW(), '1', NOW(), b'0'),
(9, '退款', 'refund', 'aicrm_points_transaction_type', 0, 'info', '退款返还积分', '1', NOW(), '1', NOW(), b'0'),
(10, '调整', 'adjust', 'aicrm_points_transaction_type', 0, 'primary', '人工调整积分', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 6. 交易状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM交易状态', 'aicrm_transaction_status', 0, '交易处理状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM交易状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '待处理', 'pending', 'aicrm_transaction_status', 0, 'info', '交易待处理', '1', NOW(), '1', NOW(), b'0'),
(2, '处理中', 'processing', 'aicrm_transaction_status', 0, 'primary', '交易处理中', '1', NOW(), '1', NOW(), b'0'),
(3, '成功', 'success', 'aicrm_transaction_status', 0, 'success', '交易成功', '1', NOW(), '1', NOW(), b'0'),
(4, '失败', 'failed', 'aicrm_transaction_status', 0, 'danger', '交易失败', '1', NOW(), '1', NOW(), b'0'),
(5, '已取消', 'cancelled', 'aicrm_transaction_status', 0, 'default', '交易已取消', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 7. 配送状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM配送状态', 'aicrm_delivery_status', 0, '礼品配送状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM配送状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '待发货', 'pending', 'aicrm_delivery_status', 0, 'info', '待发货', '1', NOW(), '1', NOW(), b'0'),
(2, '已发货', 'shipped', 'aicrm_delivery_status', 0, 'primary', '已发货', '1', NOW(), '1', NOW(), b'0'),
(3, '已送达', 'delivered', 'aicrm_delivery_status', 0, 'success', '已送达', '1', NOW(), '1', NOW(), b'0'),
(4, '拒收', 'rejected', 'aicrm_delivery_status', 0, 'danger', '拒收', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 8. 积分业务类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM积分业务类型', 'aicrm_points_business_type', 0, '积分关联的业务类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM积分业务类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '存款业务', 'deposit', 'aicrm_points_business_type', 0, 'primary', '存款业务', '1', NOW(), '1', NOW(), b'0'),
(2, '理财业务', 'wealth', 'aicrm_points_business_type', 0, 'success', '理财业务', '1', NOW(), '1', NOW(), b'0'),
(3, '贷款业务', 'loan', 'aicrm_points_business_type', 0, 'warning', '贷款业务', '1', NOW(), '1', NOW(), b'0'),
(4, '转账业务', 'transfer', 'aicrm_points_business_type', 0, 'info', '转账业务', '1', NOW(), '1', NOW(), b'0'),
(5, '礼品兑换', 'gift_redeem', 'aicrm_points_business_type', 0, 'danger', '礼品兑换', '1', NOW(), '1', NOW(), b'0'),
(6, '营销活动', 'marketing', 'aicrm_points_business_type', 0, 'default', '营销活动', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
