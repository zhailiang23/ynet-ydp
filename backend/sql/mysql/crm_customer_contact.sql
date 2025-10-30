-- ----------------------------
-- 客户接触信息表
-- 设计原则:
-- 1. 记录客户经理与客户的接触历史
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：评估时间、接触类型、客户经理、接触目的、接触反馈
-- 4. 保留老系统字段：接触方式、接触时间、客户意向、接触人员、客户人员、接触地址、接触结果、跟进时间、待办事项等
-- ----------------------------

-- ==================== 客户接触信息表 ====================
DROP TABLE IF EXISTS `crm_customer_contact`;
CREATE TABLE `crm_customer_contact` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '接触主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 图片字段 ====================
  `evaluation_time` datetime NOT NULL COMMENT '评估时间',
  `contact_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接触类型（字典: aicrm_contact_type，customer_callback=客户反馈，phone=电话，visit=拜访，email=邮件，wechat=微信，meeting=会议等）',
  `account_manager` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户经理',
  `contact_purpose` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接触目的',
  `contact_feedback` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接触反馈',

  -- ==================== 老系统字段 (cs_cst_vst) ====================
  `cst_vst_id` int NULL DEFAULT NULL COMMENT '接触ID（老系统）',
  `cst_id` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户ID（老系统）',
  `vst_mth_cd` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接触方式代码',
  `vst_tm` datetime NULL DEFAULT NULL COMMENT '接触时间',
  `cst_inint_cd` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户意向代码',
  `vst_psn_lst` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接触人员（我方参与人员）',
  `cst_psn_lst` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户人员（客户方参与人员）',
  `vst_adr` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接触地址',
  `vst_rslt_dsc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接触结果',
  `fua_tm` datetime NULL DEFAULT NULL COMMENT '跟进时间',
  `to_do_dsc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '待办事项',
  `oth_dsc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '其他描述',
  `crt_usr_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建用户ID（老系统）',
  `crt_tm` datetime NULL DEFAULT NULL COMMENT '创建时间（老系统）',
  `udt_usr_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '更新用户ID（老系统）',
  `udt_tm` datetime NULL DEFAULT NULL COMMENT '更新时间（老系统）',

  -- ==================== 扩展字段 ====================
  `contact_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接触编号',
  `contact_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接触状态（字典: aicrm_contact_status，planned=已计划，completed=已完成，cancelled=已取消）',
  `contact_channel` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '接触渠道（字典: aicrm_contact_channel，active=主动接触，passive=被动接触）',
  `contact_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接触结果（字典: aicrm_contact_result，success=成功，partial=部分成功，failed=失败）',
  `customer_intention` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户意向（字典: aicrm_customer_intention，high=意向强烈，medium=意向一般，low=意向较弱，none=无意向）',
  `contact_duration` int NULL DEFAULT NULL COMMENT '接触时长（分钟）',
  `contact_location` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接触地点',
  `contact_subject` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接触主题',
  `contact_summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '接触摘要',
  `next_contact_time` datetime NULL DEFAULT NULL COMMENT '下次接触时间',
  `next_contact_plan` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下次接触计划',
  `is_need_followup` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否需要跟进',
  `followup_user_id` bigint NULL DEFAULT NULL COMMENT '跟进人ID',
  `followup_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '跟进人姓名',
  `followup_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '跟进状态（字典: aicrm_followup_status，pending=待跟进，following=跟进中，completed=已完成）',
  `related_activity_id` bigint NULL DEFAULT NULL COMMENT '关联营销活动ID',
  `related_activity_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联营销活动名称',
  `related_product` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联产品',
  `expected_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '预期金额（元）',
  `actual_amount` decimal(24,6) NULL DEFAULT NULL COMMENT '实际成交金额（元）',
  `is_converted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否已转化',
  `convert_time` datetime NULL DEFAULT NULL COMMENT '转化时间',
  `customer_satisfaction` tinyint NULL DEFAULT NULL COMMENT '客户满意度（1-5星）',
  `handler_user_id` bigint NULL DEFAULT NULL COMMENT '处理人ID',
  `handler_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理人姓名',
  `handler_dept_id` bigint NULL DEFAULT NULL COMMENT '处理部门ID',
  `handler_dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '处理部门名称',
  `attachments` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '附件列表（JSON格式）',

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
  UNIQUE INDEX `uk_contact_no`(`contact_no` ASC) USING BTREE COMMENT '接触编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_contact_type`(`contact_type` ASC) USING BTREE COMMENT '接触类型索引',
  INDEX `idx_contact_status`(`contact_status` ASC) USING BTREE COMMENT '接触状态索引',
  INDEX `idx_evaluation_time`(`evaluation_time` ASC) USING BTREE COMMENT '评估时间索引',
  INDEX `idx_vst_tm`(`vst_tm` ASC) USING BTREE COMMENT '接触时间索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户接触信息表';


-- ==================== 字典类型和字典数据 ====================

-- 1. 接触类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户接触类型', 'aicrm_contact_type', 0, '客户接触的方式类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户接触类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '客户反馈', 'customer_callback', 'aicrm_contact_type', 0, 'primary', '客户主动反馈', '1', NOW(), '1', NOW(), b'0'),
(2, '电话', 'phone', 'aicrm_contact_type', 0, 'success', '电话沟通', '1', NOW(), '1', NOW(), b'0'),
(3, '拜访', 'visit', 'aicrm_contact_type', 0, 'info', '上门拜访', '1', NOW(), '1', NOW(), b'0'),
(4, '邮件', 'email', 'aicrm_contact_type', 0, 'default', '邮件沟通', '1', NOW(), '1', NOW(), b'0'),
(5, '微信', 'wechat', 'aicrm_contact_type', 0, 'success', '微信沟通', '1', NOW(), '1', NOW(), b'0'),
(6, '会议', 'meeting', 'aicrm_contact_type', 0, 'warning', '会议交流', '1', NOW(), '1', NOW(), b'0'),
(7, '短信', 'sms', 'aicrm_contact_type', 0, 'info', '短信沟通', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 接触状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户接触状态', 'aicrm_contact_status', 0, '客户接触的状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户接触状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '已计划', 'planned', 'aicrm_contact_status', 0, 'info', '接触已计划', '1', NOW(), '1', NOW(), b'0'),
(2, '已完成', 'completed', 'aicrm_contact_status', 0, 'success', '接触已完成', '1', NOW(), '1', NOW(), b'0'),
(3, '已取消', 'cancelled', 'aicrm_contact_status', 0, 'default', '接触已取消', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 接触渠道字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户接触渠道', 'aicrm_contact_channel', 0, '客户接触的渠道', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户接触渠道';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '主动接触', 'active', 'aicrm_contact_channel', 0, 'primary', '主动联系客户', '1', NOW(), '1', NOW(), b'0'),
(2, '被动接触', 'passive', 'aicrm_contact_channel', 0, 'info', '客户主动联系', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 接触结果字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户接触结果', 'aicrm_contact_result', 0, '客户接触的结果评价', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户接触结果';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '成功', 'success', 'aicrm_contact_result', 0, 'success', '接触成功，达成目标', '1', NOW(), '1', NOW(), b'0'),
(2, '部分成功', 'partial', 'aicrm_contact_result', 0, 'warning', '接触部分达成目标', '1', NOW(), '1', NOW(), b'0'),
(3, '失败', 'failed', 'aicrm_contact_result', 0, 'danger', '接触未达成目标', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 客户意向字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户意向', 'aicrm_customer_intention', 0, '客户的购买意向程度', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户意向';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '意向强烈', 'high', 'aicrm_customer_intention', 0, 'danger', '客户购买意向强烈', '1', NOW(), '1', NOW(), b'0'),
(2, '意向一般', 'medium', 'aicrm_customer_intention', 0, 'warning', '客户有一定意向', '1', NOW(), '1', NOW(), b'0'),
(3, '意向较弱', 'low', 'aicrm_customer_intention', 0, 'info', '客户意向较弱', '1', NOW(), '1', NOW(), b'0'),
(4, '无意向', 'none', 'aicrm_customer_intention', 0, 'default', '客户暂无意向', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 6. 跟进状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM跟进状态', 'aicrm_followup_status', 0, '跟进任务的状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM跟进状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '待跟进', 'pending', 'aicrm_followup_status', 0, 'warning', '待跟进', '1', NOW(), '1', NOW(), b'0'),
(2, '跟进中', 'following', 'aicrm_followup_status', 0, 'primary', '跟进中', '1', NOW(), '1', NOW(), b'0'),
(3, '已完成', 'completed', 'aicrm_followup_status', 0, 'success', '已完成跟进', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
