-- ----------------------------
-- 客户投诉信息表
-- 设计原则:
-- 1. 记录客户通过各个渠道提交的投诉信息
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：序号、工单编号、ECIF客户号、客户姓名、工单处理状态、最近处理时间
-- 4. 支持多渠道投诉：网银、手机银行、柜面、呼叫中心、客户经理
-- ----------------------------

-- ==================== 客户投诉信息表 ====================
DROP TABLE IF EXISTS `crm_customer_complaint`;
CREATE TABLE `crm_customer_complaint` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '投诉主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 图片字段 ====================
  `sequence_no` int NULL DEFAULT NULL COMMENT '序号',
  `work_order_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工单编号',
  `ecif_customer_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'ECIF客户号',
  `customer_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户姓名',
  `work_order_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '工单处理状态（字典: aicrm_complaint_status）',
  `last_process_time` datetime NULL DEFAULT NULL COMMENT '最近处理时间',

  -- ==================== 扩展字段 ====================
  `complaint_channel` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投诉渠道（字典: aicrm_complaint_channel，online_banking=网银，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理）',
  `complaint_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投诉类型（字典: aicrm_complaint_type）',
  `complaint_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '投诉内容',
  `complaint_time` datetime NOT NULL COMMENT '投诉时间',
  `complaint_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'normal' COMMENT '投诉级别（字典: aicrm_complaint_level，urgent=紧急，important=重要，normal=普通）',
  `handler_user_id` bigint NULL DEFAULT NULL COMMENT '处理人ID',
  `handler_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理人姓名',
  `handler_dept_id` bigint NULL DEFAULT NULL COMMENT '处理部门ID',
  `handler_dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理部门名称',
  `process_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理结果',
  `process_start_time` datetime NULL DEFAULT NULL COMMENT '开始处理时间',
  `process_end_time` datetime NULL DEFAULT NULL COMMENT '处理完成时间',
  `process_duration` int NULL DEFAULT NULL COMMENT '处理时长（分钟）',
  `customer_satisfaction` tinyint NULL DEFAULT NULL COMMENT '客户满意度（1-5星）',
  `satisfaction_feedback` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '满意度反馈',
  `is_closed` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已关闭',
  `close_time` datetime NULL DEFAULT NULL COMMENT '关闭时间',
  `is_reopened` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否重新打开',
  `reopen_time` datetime NULL DEFAULT NULL COMMENT '重新打开时间',
  `reopen_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '重新打开原因',

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
  UNIQUE INDEX `uk_work_order_no`(`work_order_no` ASC) USING BTREE COMMENT '工单编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_work_order_status`(`work_order_status` ASC) USING BTREE COMMENT '工单状态索引',
  INDEX `idx_complaint_channel`(`complaint_channel` ASC) USING BTREE COMMENT '投诉渠道索引',
  INDEX `idx_complaint_time`(`complaint_time` ASC) USING BTREE COMMENT '投诉时间索引',
  INDEX `idx_complaint_level`(`complaint_level` ASC) USING BTREE COMMENT '投诉级别索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户投诉信息表';


-- ==================== 字典类型和字典数据 ====================

-- 1. 投诉工单状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM投诉工单状态', 'aicrm_complaint_status', 0, '投诉工单处理状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM投诉工单状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '待受理', 'pending', 'aicrm_complaint_status', 0, 'info', '投诉已提交，待受理', '1', NOW(), '1', NOW(), b'0'),
(2, '处理中', 'processing', 'aicrm_complaint_status', 0, 'primary', '投诉正在处理', '1', NOW(), '1', NOW(), b'0'),
(3, '待反馈', 'pending_feedback', 'aicrm_complaint_status', 0, 'warning', '等待客户反馈', '1', NOW(), '1', NOW(), b'0'),
(4, '已完成', 'completed', 'aicrm_complaint_status', 0, 'success', '投诉已处理完成', '1', NOW(), '1', NOW(), b'0'),
(5, '已关闭', 'closed', 'aicrm_complaint_status', 0, 'default', '投诉已关闭', '1', NOW(), '1', NOW(), b'0'),
(6, '已撤销', 'cancelled', 'aicrm_complaint_status', 0, 'info', '客户撤销投诉', '1', NOW(), '1', NOW(), b'0'),
(7, '已转办', 'transferred', 'aicrm_complaint_status', 0, 'warning', '投诉已转其他部门', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 投诉渠道字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM投诉渠道', 'aicrm_complaint_channel', 0, '客户投诉渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM投诉渠道';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '网银', 'online_banking', 'aicrm_complaint_channel', 0, 'primary', '网上银行渠道', '1', NOW(), '1', NOW(), b'0'),
(2, '手机银行', 'mobile_banking', 'aicrm_complaint_channel', 0, 'success', '手机银行APP', '1', NOW(), '1', NOW(), b'0'),
(3, '柜面', 'counter', 'aicrm_complaint_channel', 0, 'warning', '银行柜台', '1', NOW(), '1', NOW(), b'0'),
(4, '呼叫中心', 'call_center', 'aicrm_complaint_channel', 0, 'info', '客服热线', '1', NOW(), '1', NOW(), b'0'),
(5, '客户经理', 'account_manager', 'aicrm_complaint_channel', 0, 'danger', '客户经理渠道', '1', NOW(), '1', NOW(), b'0'),
(6, '微信公众号', 'wechat', 'aicrm_complaint_channel', 0, 'success', '微信公众号', '1', NOW(), '1', NOW(), b'0'),
(7, '官网留言', 'website', 'aicrm_complaint_channel', 0, 'primary', '官网在线留言', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 投诉类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM投诉类型', 'aicrm_complaint_type', 0, '客户投诉类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM投诉类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '服务态度', 'service_attitude', 'aicrm_complaint_type', 0, 'warning', '服务态度问题', '1', NOW(), '1', NOW(), b'0'),
(2, '业务办理', 'business_process', 'aicrm_complaint_type', 0, 'primary', '业务办理问题', '1', NOW(), '1', NOW(), b'0'),
(3, '系统故障', 'system_failure', 'aicrm_complaint_type', 0, 'danger', '系统技术故障', '1', NOW(), '1', NOW(), b'0'),
(4, '收费争议', 'fee_dispute', 'aicrm_complaint_type', 0, 'warning', '收费争议问题', '1', NOW(), '1', NOW(), b'0'),
(5, '产品问题', 'product_issue', 'aicrm_complaint_type', 0, 'info', '产品功能问题', '1', NOW(), '1', NOW(), b'0'),
(6, '隐私泄露', 'privacy_leak', 'aicrm_complaint_type', 0, 'danger', '隐私信息泄露', '1', NOW(), '1', NOW(), b'0'),
(7, '欺诈风险', 'fraud_risk', 'aicrm_complaint_type', 0, 'danger', '欺诈风险问题', '1', NOW(), '1', NOW(), b'0'),
(8, '等待时间', 'waiting_time', 'aicrm_complaint_type', 0, 'info', '等待时间过长', '1', NOW(), '1', NOW(), b'0'),
(9, '其他', 'other', 'aicrm_complaint_type', 0, 'default', '其他类型投诉', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 投诉级别字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM投诉级别', 'aicrm_complaint_level', 0, '投诉紧急程度', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM投诉级别';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '紧急', 'urgent', 'aicrm_complaint_level', 0, 'danger', '紧急投诉，需立即处理', '1', NOW(), '1', NOW(), b'0'),
(2, '重要', 'important', 'aicrm_complaint_level', 0, 'warning', '重要投诉，优先处理', '1', NOW(), '1', NOW(), b'0'),
(3, '普通', 'normal', 'aicrm_complaint_level', 0, 'primary', '普通投诉，正常处理', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
