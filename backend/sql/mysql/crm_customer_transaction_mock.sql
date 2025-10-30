-- ----------------------------
-- 客户交易明细信息表（Mock数据）
-- 设计原则:
-- 1. 记录客户账户的交易明细信息
-- 2. 支持按账户维度查询交易流水
-- 3. 支持零售客户和对公客户
-- 4. 参考老系统 acrm_f_evt_save_trad_tans 表设计
-- 5. 表名加 _mock 后缀表示为模拟数据
-- ----------------------------

-- ==================== 客户交易明细信息表 ====================
DROP TABLE IF EXISTS `crm_customer_transaction_mock`;
CREATE TABLE `crm_customer_transaction_mock` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '交易主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',
  `account_id` bigint NULL DEFAULT NULL COMMENT '账户ID（关联账户表）',

  -- ==================== 图片字段 ====================
  `transaction_date` date NOT NULL COMMENT '交易日期',
  `transaction_time` time NOT NULL COMMENT '交易时间',
  `branch_no` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交易机构编号',
  `branch_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交易机构名称',
  `original_tran_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '原交易代码',
  `cash_flag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '现转标志（字典: aicrm_cash_flag）',
  `sub_account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '子账户编号',
  `account_no` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账户编号',
  `currency_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'CNY' COMMENT '币种（字典: aicrm_currency_type）',

  -- ==================== 老系统字段 ====================
  `tans_no` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交易流水号',
  `trad_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交易类型（字典: aicrm_transaction_type）',
  `trad_money` decimal(24,6) NOT NULL DEFAULT 0 COMMENT '交易金额',
  `acct_bal` decimal(24,6) NULL DEFAULT NULL COMMENT '账户余额',
  `trad_abs` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交易摘要',
  `review` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审核标志',
  `trad_chn` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交易渠道（字典: aicrm_transaction_channel）',
  `trad_teller` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '交易柜员',
  `handler` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '经办人',
  `advs_acct` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对方账号',
  `advs_acct_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '对方户名',
  `contact_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '往来类型',
  `curr_tran_flag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '钞汇标志',
  `loan_flag` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '贷款标志',
  `cost` decimal(24,6) NULL DEFAULT NULL COMMENT '手续费',
  `accountin_date` date NULL DEFAULT NULL COMMENT '记账日期',

  -- ==================== 扩展字段 ====================
  `transaction_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'success' COMMENT '交易状态（字典: aicrm_transaction_status，success=成功，failed=失败，pending=处理中）',
  `direction` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '交易方向（字典: aicrm_transaction_direction，in=转入，out=转出）',

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
  INDEX `idx_account_id`(`account_id` ASC) USING BTREE COMMENT '账户ID索引',
  INDEX `idx_account_no`(`account_no` ASC) USING BTREE COMMENT '账户编号索引',
  INDEX `idx_transaction_date`(`transaction_date` ASC) USING BTREE COMMENT '交易日期索引',
  INDEX `idx_transaction_datetime`(`transaction_date` ASC, `transaction_time` ASC) USING BTREE COMMENT '交易时间索引',
  INDEX `idx_tans_no`(`tans_no` ASC) USING BTREE COMMENT '交易流水号索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户交易明细信息表（Mock数据）';

-- ==================== 字典类型和字典数据 ====================

-- 1. 现转标志字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM现转标志', 'aicrm_cash_flag', 0, '现金或转账标志', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM现转标志';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '转账', 'transfer', 'aicrm_cash_flag', 0, 'primary', '转账交易', '1', NOW(), '1', NOW(), b'0'),
(2, '现金', 'cash', 'aicrm_cash_flag', 0, 'success', '现金交易', '1', NOW(), '1', NOW(), b'0'),
(3, '现汇', 'current', 'aicrm_cash_flag', 0, 'info', '现汇交易', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 交易类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM交易类型', 'aicrm_transaction_type', 0, '交易类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM交易类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '存款', 'deposit', 'aicrm_transaction_type', 0, 'success', '存款交易', '1', NOW(), '1', NOW(), b'0'),
(2, '取款', 'withdraw', 'aicrm_transaction_type', 0, 'warning', '取款交易', '1', NOW(), '1', NOW(), b'0'),
(3, '转账', 'transfer', 'aicrm_transaction_type', 0, 'primary', '转账交易', '1', NOW(), '1', NOW(), b'0'),
(4, '消费', 'consume', 'aicrm_transaction_type', 0, 'info', '消费交易', '1', NOW(), '1', NOW(), b'0'),
(5, '还款', 'repayment', 'aicrm_transaction_type', 0, 'default', '还款交易', '1', NOW(), '1', NOW(), b'0'),
(6, '利息', 'interest', 'aicrm_transaction_type', 0, 'default', '利息结算', '1', NOW(), '1', NOW(), b'0'),
(7, '手续费', 'fee', 'aicrm_transaction_type', 0, 'default', '手续费扣除', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 交易渠道字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM交易渠道', 'aicrm_transaction_channel', 0, '交易渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM交易渠道';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '柜面', 'counter', 'aicrm_transaction_channel', 0, 'primary', '柜面渠道', '1', NOW(), '1', NOW(), b'0'),
(2, 'ATM', 'atm', 'aicrm_transaction_channel', 0, 'success', 'ATM渠道', '1', NOW(), '1', NOW(), b'0'),
(3, '网上银行', 'online', 'aicrm_transaction_channel', 0, 'info', '网上银行', '1', NOW(), '1', NOW(), b'0'),
(4, '手机银行', 'mobile', 'aicrm_transaction_channel', 0, 'warning', '手机银行', '1', NOW(), '1', NOW(), b'0'),
(5, '微信', 'wechat', 'aicrm_transaction_channel', 0, 'default', '微信渠道', '1', NOW(), '1', NOW(), b'0'),
(6, '支付宝', 'alipay', 'aicrm_transaction_channel', 0, 'default', '支付宝渠道', '1', NOW(), '1', NOW(), b'0'),
(7, 'POS', 'pos', 'aicrm_transaction_channel', 0, 'default', 'POS机', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 交易状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM交易状态', 'aicrm_transaction_status', 0, '交易状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM交易状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '成功', 'success', 'aicrm_transaction_status', 0, 'success', '交易成功', '1', NOW(), '1', NOW(), b'0'),
(2, '失败', 'failed', 'aicrm_transaction_status', 0, 'danger', '交易失败', '1', NOW(), '1', NOW(), b'0'),
(3, '处理中', 'pending', 'aicrm_transaction_status', 0, 'warning', '交易处理中', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 交易方向字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM交易方向', 'aicrm_transaction_direction', 0, '交易方向', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM交易方向';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '转入', 'in', 'aicrm_transaction_direction', 0, 'success', '资金转入', '1', NOW(), '1', NOW(), b'0'),
(2, '转出', 'out', 'aicrm_transaction_direction', 0, 'warning', '资金转出', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
