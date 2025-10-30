-- ----------------------------
-- 客户需求信息表
-- 设计原则:
-- 1. 记录客户通过各个渠道提交的需求信息
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：序号、需求渠道、需求内容、提交时间、是否处理
-- 4. 支持需求全生命周期管理：未处理→处理中→已处理→已关闭
-- ----------------------------

-- ==================== 客户需求信息表 ====================
DROP TABLE IF EXISTS `crm_customer_demand`;
CREATE TABLE `crm_customer_demand` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '需求主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 图片字段 ====================
  `sequence_no` int NULL DEFAULT NULL COMMENT '序号',
  `demand_channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '需求渠道（字典: aicrm_demand_channel，enterprise_banking=企业网银，crm=CRM系统，mobile_banking=手机银行，counter=柜面，call_center=呼叫中心，account_manager=客户经理等）',
  `demand_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '需求内容',
  `submit_time` datetime NOT NULL COMMENT '提交时间',
  `is_processed` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否处理（0=未处理，1=已处理）',

  -- ==================== 扩展字段 ====================
  `demand_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '需求编号',
  `demand_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '需求类型（字典: aicrm_demand_type，product_consult=产品咨询，business_handle=业务办理，complaint_suggest=投诉建议，account_query=账户查询，loan_apply=贷款申请，wealth_manage=理财咨询等）',
  `demand_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '需求状态（字典: aicrm_demand_status，pending=未处理，processing=处理中，processed=已处理，closed=已关闭）',
  `demand_priority` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'normal' COMMENT '需求优先级（字典: aicrm_demand_priority，urgent=紧急，high=高，normal=普通，low=低）',
  `demand_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '需求来源（字典: aicrm_demand_source，customer_active=客户主动，marketing_activity=营销活动，data_analysis=数据分析，other=其他）',
  `handler_user_id` bigint NULL DEFAULT NULL COMMENT '处理人ID',
  `handler_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理人姓名',
  `handler_dept_id` bigint NULL DEFAULT NULL COMMENT '处理部门ID',
  `handler_dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理部门名称',
  `process_start_time` datetime NULL DEFAULT NULL COMMENT '开始处理时间',
  `process_end_time` datetime NULL DEFAULT NULL COMMENT '处理完成时间',
  `process_duration` int NULL DEFAULT NULL COMMENT '处理时长（分钟）',
  `process_result` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理结果',
  `customer_satisfaction` tinyint NULL DEFAULT NULL COMMENT '客户满意度（1-5星）',
  `satisfaction_feedback` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '满意度反馈',
  `follow_up_required` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否需要跟进',
  `follow_up_time` datetime NULL DEFAULT NULL COMMENT '跟进时间',
  `follow_up_content` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '跟进内容',
  `related_product` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '相关产品',
  `expected_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '预期金额（元）',
  `actual_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '实际成交金额（元）',
  `is_converted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已转化',
  `convert_time` datetime NULL DEFAULT NULL COMMENT '转化时间',
  `is_closed` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已关闭',
  `close_time` datetime NULL DEFAULT NULL COMMENT '关闭时间',
  `close_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关闭原因',

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
  UNIQUE INDEX `uk_demand_no`(`demand_no` ASC) USING BTREE COMMENT '需求编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_demand_channel`(`demand_channel` ASC) USING BTREE COMMENT '需求渠道索引',
  INDEX `idx_demand_type`(`demand_type` ASC) USING BTREE COMMENT '需求类型索引',
  INDEX `idx_demand_status`(`demand_status` ASC) USING BTREE COMMENT '需求状态索引',
  INDEX `idx_is_processed`(`is_processed` ASC) USING BTREE COMMENT '是否处理索引',
  INDEX `idx_submit_time`(`submit_time` ASC) USING BTREE COMMENT '提交时间索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户需求信息表';


-- ==================== 字典类型和字典数据 ====================

-- 1. 需求渠道字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户需求渠道', 'aicrm_demand_channel', 0, '客户提交需求的渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户需求渠道';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '企业网银', 'enterprise_banking', 'aicrm_demand_channel', 0, 'primary', '企业网银渠道', '1', NOW(), '1', NOW(), b'0'),
(2, 'CRM系统', 'crm', 'aicrm_demand_channel', 0, 'success', 'CRM系统渠道', '1', NOW(), '1', NOW(), b'0'),
(3, '手机银行', 'mobile_banking', 'aicrm_demand_channel', 0, 'info', '手机银行APP', '1', NOW(), '1', NOW(), b'0'),
(4, '网上银行', 'online_banking', 'aicrm_demand_channel', 0, 'primary', '网上银行', '1', NOW(), '1', NOW(), b'0'),
(5, '柜面', 'counter', 'aicrm_demand_channel', 0, 'warning', '银行柜台', '1', NOW(), '1', NOW(), b'0'),
(6, '呼叫中心', 'call_center', 'aicrm_demand_channel', 0, 'info', '客服热线', '1', NOW(), '1', NOW(), b'0'),
(7, '客户经理', 'account_manager', 'aicrm_demand_channel', 0, 'danger', '客户经理渠道', '1', NOW(), '1', NOW(), b'0'),
(8, '微信公众号', 'wechat', 'aicrm_demand_channel', 0, 'success', '微信公众号', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 需求类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户需求类型', 'aicrm_demand_type', 0, '客户需求的业务类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户需求类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '产品咨询', 'product_consult', 'aicrm_demand_type', 0, 'primary', '产品咨询需求', '1', NOW(), '1', NOW(), b'0'),
(2, '业务办理', 'business_handle', 'aicrm_demand_type', 0, 'success', '业务办理需求', '1', NOW(), '1', NOW(), b'0'),
(3, '投诉建议', 'complaint_suggest', 'aicrm_demand_type', 0, 'warning', '投诉建议', '1', NOW(), '1', NOW(), b'0'),
(4, '账户查询', 'account_query', 'aicrm_demand_type', 0, 'info', '账户查询', '1', NOW(), '1', NOW(), b'0'),
(5, '贷款申请', 'loan_apply', 'aicrm_demand_type', 0, 'danger', '贷款申请', '1', NOW(), '1', NOW(), b'0'),
(6, '理财咨询', 'wealth_manage', 'aicrm_demand_type', 0, 'primary', '理财咨询', '1', NOW(), '1', NOW(), b'0'),
(7, '开户申请', 'account_open', 'aicrm_demand_type', 0, 'success', '开户申请', '1', NOW(), '1', NOW(), b'0'),
(8, '信用卡申请', 'credit_card', 'aicrm_demand_type', 0, 'info', '信用卡申请', '1', NOW(), '1', NOW(), b'0'),
(9, '其他需求', 'other', 'aicrm_demand_type', 0, 'default', '其他类型需求', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 需求状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户需求状态', 'aicrm_demand_status', 0, '客户需求的处理状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户需求状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '未处理', 'pending', 'aicrm_demand_status', 0, 'warning', '需求未处理', '1', NOW(), '1', NOW(), b'0'),
(2, '处理中', 'processing', 'aicrm_demand_status', 0, 'primary', '需求处理中', '1', NOW(), '1', NOW(), b'0'),
(3, '已处理', 'processed', 'aicrm_demand_status', 0, 'success', '需求已处理', '1', NOW(), '1', NOW(), b'0'),
(4, '已关闭', 'closed', 'aicrm_demand_status', 0, 'default', '需求已关闭', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 需求优先级字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户需求优先级', 'aicrm_demand_priority', 0, '客户需求的优先级', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户需求优先级';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '紧急', 'urgent', 'aicrm_demand_priority', 0, 'danger', '紧急需求，需立即处理', '1', NOW(), '1', NOW(), b'0'),
(2, '高', 'high', 'aicrm_demand_priority', 0, 'warning', '高优先级需求', '1', NOW(), '1', NOW(), b'0'),
(3, '普通', 'normal', 'aicrm_demand_priority', 0, 'primary', '普通需求', '1', NOW(), '1', NOW(), b'0'),
(4, '低', 'low', 'aicrm_demand_priority', 0, 'info', '低优先级需求', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 需求来源字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户需求来源', 'aicrm_demand_source', 0, '客户需求的来源', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户需求来源';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '客户主动', 'customer_active', 'aicrm_demand_source', 0, 'primary', '客户主动提出', '1', NOW(), '1', NOW(), b'0'),
(2, '营销活动', 'marketing_activity', 'aicrm_demand_source', 0, 'success', '营销活动挖掘', '1', NOW(), '1', NOW(), b'0'),
(3, '数据分析', 'data_analysis', 'aicrm_demand_source', 0, 'info', '数据分析发现', '1', NOW(), '1', NOW(), b'0'),
(4, '客户回访', 'customer_callback', 'aicrm_demand_source', 0, 'warning', '客户回访获取', '1', NOW(), '1', NOW(), b'0'),
(5, '其他', 'other', 'aicrm_demand_source', 0, 'default', '其他来源', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
