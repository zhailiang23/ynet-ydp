-- ----------------------------
-- 客户提醒信息表
-- 设计原则:
-- 1. 记录系统自动生成或手动创建的客户提醒信息
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：序号、提醒类别名称、提醒生成日期、提醒到期日期、提醒内容
-- 4. 支持多种提醒类型：账户变动、授信到期、客户接触、客户生日、贷款到期等
-- ----------------------------

-- ==================== 客户提醒信息表 ====================
DROP TABLE IF EXISTS `crm_customer_reminder`;
CREATE TABLE `crm_customer_reminder` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '提醒主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 图片字段 ====================
  `sequence_no` int NULL DEFAULT NULL COMMENT '序号',
  `reminder_category_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提醒类别名称',
  `reminder_generate_date` date NOT NULL COMMENT '提醒生成日期',
  `reminder_due_date` date NOT NULL COMMENT '提醒到期日期',
  `reminder_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提醒内容',

  -- ==================== 扩展字段 ====================
  `reminder_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提醒编号',
  `reminder_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提醒类型（字典: aicrm_reminder_type，account_balance=账户余额变动，credit_expire=授信到期，customer_contact=客户接触，customer_birthday=客户生日，loan_expire=贷款到期，product_expire=产品到期，payment_remind=还款提醒等）',
  `reminder_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'normal' COMMENT '提醒级别（字典: aicrm_reminder_level，urgent=紧急，important=重要，normal=普通，info=提示）',
  `reminder_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提醒状态（字典: aicrm_reminder_status，pending=待处理，processing=处理中，completed=已完成，expired=已过期，cancelled=已取消）',
  `reminder_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '提醒来源（字典: aicrm_reminder_source，system_auto=系统自动，manual_create=手动创建，rule_trigger=规则触发，data_analysis=数据分析）',
  `is_sent` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已发送',
  `send_time` datetime NULL DEFAULT NULL COMMENT '发送时间',
  `send_channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '发送渠道（字典: aicrm_reminder_channel，sms=短信，email=邮件，wechat=微信，system=系统消息，phone=电话）',
  `recipient_user_id` bigint NULL DEFAULT NULL COMMENT '接收人ID',
  `recipient_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接收人姓名',
  `recipient_dept_id` bigint NULL DEFAULT NULL COMMENT '接收部门ID',
  `recipient_dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接收部门名称',
  `handler_user_id` bigint NULL DEFAULT NULL COMMENT '处理人ID',
  `handler_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理人姓名',
  `handle_time` datetime NULL DEFAULT NULL COMMENT '处理时间',
  `handle_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理结果',
  `is_repeat` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否重复提醒',
  `repeat_rule` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '重复规则（如：每天、每周、每月等）',
  `next_remind_time` datetime NULL DEFAULT NULL COMMENT '下次提醒时间',
  `is_read` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已读',
  `read_time` datetime NULL DEFAULT NULL COMMENT '阅读时间',
  `priority` int NOT NULL DEFAULT 0 COMMENT '优先级（数字越大优先级越高）',
  `related_business_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联业务类型（字典: aicrm_business_type）',
  `related_business_id` bigint NULL DEFAULT NULL COMMENT '关联业务ID',
  `expire_time` datetime NULL DEFAULT NULL COMMENT '过期时间',
  `is_expired` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已过期',
  `is_cancelled` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已取消',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `cancel_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '取消原因',

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
  UNIQUE INDEX `uk_reminder_no`(`reminder_no` ASC) USING BTREE COMMENT '提醒编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_reminder_type`(`reminder_type` ASC) USING BTREE COMMENT '提醒类型索引',
  INDEX `idx_reminder_status`(`reminder_status` ASC) USING BTREE COMMENT '提醒状态索引',
  INDEX `idx_reminder_generate_date`(`reminder_generate_date` ASC) USING BTREE COMMENT '提醒生成日期索引',
  INDEX `idx_reminder_due_date`(`reminder_due_date` ASC) USING BTREE COMMENT '提醒到期日期索引',
  INDEX `idx_is_sent`(`is_sent` ASC) USING BTREE COMMENT '是否已发送索引',
  INDEX `idx_is_read`(`is_read` ASC) USING BTREE COMMENT '是否已读索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户提醒信息表';


-- ==================== 字典类型和字典数据 ====================

-- 1. 提醒类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户提醒类型', 'aicrm_reminder_type', 0, '客户提醒的业务类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户提醒类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '账户余额变动（转出）', 'account_balance', 'aicrm_reminder_type', 0, 'warning', '账户余额变动提醒', '1', NOW(), '1', NOW(), b'0'),
(2, '授信到期提醒', 'credit_expire', 'aicrm_reminder_type', 0, 'danger', '授信到期提醒', '1', NOW(), '1', NOW(), b'0'),
(3, '客户接触', 'customer_contact', 'aicrm_reminder_type', 0, 'info', '客户接触提醒', '1', NOW(), '1', NOW(), b'0'),
(4, '客户生日', 'customer_birthday', 'aicrm_reminder_type', 0, 'success', '客户生日提醒', '1', NOW(), '1', NOW(), b'0'),
(5, '贷款到期', 'loan_expire', 'aicrm_reminder_type', 0, 'danger', '贷款到期提醒', '1', NOW(), '1', NOW(), b'0'),
(6, '产品到期', 'product_expire', 'aicrm_reminder_type', 0, 'warning', '产品到期提醒', '1', NOW(), '1', NOW(), b'0'),
(7, '还款提醒', 'payment_remind', 'aicrm_reminder_type', 0, 'danger', '还款提醒', '1', NOW(), '1', NOW(), b'0'),
(8, '理财到期', 'wealth_expire', 'aicrm_reminder_type', 0, 'primary', '理财产品到期提醒', '1', NOW(), '1', NOW(), b'0'),
(9, '重要事件', 'important_event', 'aicrm_reminder_type', 0, 'warning', '重要事件提醒', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 提醒级别字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM提醒级别', 'aicrm_reminder_level', 0, '提醒的重要程度', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM提醒级别';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '紧急', 'urgent', 'aicrm_reminder_level', 0, 'danger', '紧急提醒，需立即处理', '1', NOW(), '1', NOW(), b'0'),
(2, '重要', 'important', 'aicrm_reminder_level', 0, 'warning', '重要提醒，优先处理', '1', NOW(), '1', NOW(), b'0'),
(3, '普通', 'normal', 'aicrm_reminder_level', 0, 'primary', '普通提醒', '1', NOW(), '1', NOW(), b'0'),
(4, '提示', 'info', 'aicrm_reminder_level', 0, 'info', '信息提示', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 提醒状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM提醒状态', 'aicrm_reminder_status', 0, '提醒的处理状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM提醒状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '待处理', 'pending', 'aicrm_reminder_status', 0, 'warning', '提醒待处理', '1', NOW(), '1', NOW(), b'0'),
(2, '处理中', 'processing', 'aicrm_reminder_status', 0, 'primary', '提醒处理中', '1', NOW(), '1', NOW(), b'0'),
(3, '已完成', 'completed', 'aicrm_reminder_status', 0, 'success', '提醒已完成', '1', NOW(), '1', NOW(), b'0'),
(4, '已过期', 'expired', 'aicrm_reminder_status', 0, 'default', '提醒已过期', '1', NOW(), '1', NOW(), b'0'),
(5, '已取消', 'cancelled', 'aicrm_reminder_status', 0, 'info', '提醒已取消', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 提醒来源字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM提醒来源', 'aicrm_reminder_source', 0, '提醒的生成来源', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM提醒来源';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '系统自动', 'system_auto', 'aicrm_reminder_source', 0, 'primary', '系统自动生成', '1', NOW(), '1', NOW(), b'0'),
(2, '手动创建', 'manual_create', 'aicrm_reminder_source', 0, 'success', '手动创建', '1', NOW(), '1', NOW(), b'0'),
(3, '规则触发', 'rule_trigger', 'aicrm_reminder_source', 0, 'info', '规则触发生成', '1', NOW(), '1', NOW(), b'0'),
(4, '数据分析', 'data_analysis', 'aicrm_reminder_source', 0, 'warning', '数据分析生成', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 提醒渠道字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM提醒渠道', 'aicrm_reminder_channel', 0, '提醒的发送渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM提醒渠道';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '短信', 'sms', 'aicrm_reminder_channel', 0, 'primary', '短信通知', '1', NOW(), '1', NOW(), b'0'),
(2, '邮件', 'email', 'aicrm_reminder_channel', 0, 'success', '邮件通知', '1', NOW(), '1', NOW(), b'0'),
(3, '微信', 'wechat', 'aicrm_reminder_channel', 0, 'success', '微信通知', '1', NOW(), '1', NOW(), b'0'),
(4, '系统消息', 'system', 'aicrm_reminder_channel', 0, 'info', '系统内消息', '1', NOW(), '1', NOW(), b'0'),
(5, '电话', 'phone', 'aicrm_reminder_channel', 0, 'warning', '电话通知', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
