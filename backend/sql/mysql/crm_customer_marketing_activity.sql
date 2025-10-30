-- ----------------------------
-- 客户营销活动信息表
-- 设计原则:
-- 1. 记录客户参与的各类营销活动信息
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：序号、活动名称、活动编号、活动形式、活动状态、审批状态、活动订购时间、活动结束时间、是否发送客户短信、创建时间/创建人/更新时间/更新人
-- 4. 支持活动全生命周期管理：草稿→待执行→执行中→已完成→已关闭
-- 5. 支持审批流程：待提交→审批中→已通过→已驳回
-- ----------------------------

-- ==================== 客户营销活动信息表 ====================
DROP TABLE IF EXISTS `crm_customer_marketing_activity`;
CREATE TABLE `crm_customer_marketing_activity` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '营销活动主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 图片字段 ====================
  `sequence_no` int NULL DEFAULT NULL COMMENT '序号',
  `activity_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动名称',
  `activity_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动编号',
  `activity_form` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动形式（字典: aicrm_activity_form，parent_child=亲子沙龙，salon=线下沙龙，visit=客户拜访，phone=电话营销等）',
  `activity_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动状态（字典: aicrm_activity_status，draft=草稿，pending=待执行，executing=执行中，completed=已完成，closed=已关闭）',
  `approval_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '审批状态（字典: aicrm_approval_status，pending_submit=待提交，approving=审批中，approved=已通过，rejected=已驳回）',
  `activity_order_time` datetime NULL DEFAULT NULL COMMENT '活动订购时间',
  `activity_end_time` datetime NULL DEFAULT NULL COMMENT '活动结束时间',
  `is_send_sms` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否发送客户短信',

  -- ==================== 老系统字段 (ocrm_f_mm_mkt_busi_oppor) ====================
  `oppor_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机会ID（老系统）',
  `oppor_nm` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机会名称（老系统）',
  `start_dt` date NULL DEFAULT NULL COMMENT '开始日期',
  `end_dt` date NULL DEFAULT NULL COMMENT '结束日期',
  `oppor_stcd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机会状态代码',
  `auto_aclt_rlcd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '自动分配规则代码',
  `oppor_bsn_tpcd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机会业务类型代码',
  `oppor_alct_obj_tpcd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机会分配对象类型代码',
  `oppor_cust_tpcd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机会客户类型代码',
  `oppor_srccd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机会来源代码',
  `oppor_crt_mthcd` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机会创建方式代码',
  `crt_org_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '创建机构ID',
  `oppor_dsc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '机会描述',

  -- ==================== 扩展字段 ====================
  `activity_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '活动类型（字典: aicrm_activity_type，product_promotion=产品推广，customer_maintain=客户维护，market_research=市场调研，brand_publicity=品牌宣传等）',
  `activity_location` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '活动地点',
  `activity_budget` decimal(24,6) NULL DEFAULT NULL COMMENT '活动预算（元）',
  `activity_cost` decimal(24,6) NULL DEFAULT NULL COMMENT '活动实际费用（元）',
  `target_customer_count` int NULL DEFAULT NULL COMMENT '目标客户数量',
  `actual_customer_count` int NULL DEFAULT NULL COMMENT '实际参与客户数量',
  `expected_effect` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '预期效果',
  `actual_effect` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '实际效果',
  `handler_user_id` bigint NULL DEFAULT NULL COMMENT '负责人ID',
  `handler_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '负责人姓名',
  `handler_dept_id` bigint NULL DEFAULT NULL COMMENT '负责部门ID',
  `handler_dept_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '负责部门名称',
  `approver_user_id` bigint NULL DEFAULT NULL COMMENT '审批人ID',
  `approver_user_name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批人姓名',
  `approval_time` datetime NULL DEFAULT NULL COMMENT '审批时间',
  `approval_comment` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批意见',
  `sms_send_time` datetime NULL DEFAULT NULL COMMENT '短信发送时间',
  `sms_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '短信内容',
  `activity_score` tinyint NULL DEFAULT NULL COMMENT '活动评分（1-5星）',
  `customer_feedback` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户反馈',
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
  UNIQUE INDEX `uk_activity_no`(`activity_no` ASC) USING BTREE COMMENT '活动编号唯一索引',
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_activity_status`(`activity_status` ASC) USING BTREE COMMENT '活动状态索引',
  INDEX `idx_approval_status`(`approval_status` ASC) USING BTREE COMMENT '审批状态索引',
  INDEX `idx_activity_type`(`activity_type` ASC) USING BTREE COMMENT '活动类型索引',
  INDEX `idx_activity_form`(`activity_form` ASC) USING BTREE COMMENT '活动形式索引',
  INDEX `idx_activity_order_time`(`activity_order_time` ASC) USING BTREE COMMENT '活动订购时间索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户营销活动信息表';


-- ==================== 字典类型和字典数据 ====================

-- 1. 活动形式字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM营销活动形式', 'aicrm_activity_form', 0, '营销活动的形式类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM营销活动形式';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '亲子沙龙', 'parent_child', 'aicrm_activity_form', 0, 'success', '亲子沙龙活动', '1', NOW(), '1', NOW(), b'0'),
(2, '线下沙龙', 'salon', 'aicrm_activity_form', 0, 'primary', '线下沙龙活动', '1', NOW(), '1', NOW(), b'0'),
(3, '客户拜访', 'visit', 'aicrm_activity_form', 0, 'info', '上门客户拜访', '1', NOW(), '1', NOW(), b'0'),
(4, '电话营销', 'phone', 'aicrm_activity_form', 0, 'warning', '电话营销活动', '1', NOW(), '1', NOW(), b'0'),
(5, '短信营销', 'sms', 'aicrm_activity_form', 0, 'default', '短信营销活动', '1', NOW(), '1', NOW(), b'0'),
(6, '微信营销', 'wechat', 'aicrm_activity_form', 0, 'success', '微信营销活动', '1', NOW(), '1', NOW(), b'0'),
(7, '线上活动', 'online', 'aicrm_activity_form', 0, 'primary', '线上营销活动', '1', NOW(), '1', NOW(), b'0'),
(8, '会议营销', 'conference', 'aicrm_activity_form', 0, 'info', '会议营销活动', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 活动状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM营销活动状态', 'aicrm_activity_status', 0, '营销活动的执行状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM营销活动状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '草稿', 'draft', 'aicrm_activity_status', 0, 'info', '活动草稿，未提交', '1', NOW(), '1', NOW(), b'0'),
(2, '待执行', 'pending', 'aicrm_activity_status', 0, 'warning', '活动已批准，待执行', '1', NOW(), '1', NOW(), b'0'),
(3, '执行中', 'executing', 'aicrm_activity_status', 0, 'primary', '活动正在执行', '1', NOW(), '1', NOW(), b'0'),
(4, '已完成', 'completed', 'aicrm_activity_status', 0, 'success', '活动已完成', '1', NOW(), '1', NOW(), b'0'),
(5, '已关闭', 'closed', 'aicrm_activity_status', 0, 'default', '活动已关闭', '1', NOW(), '1', NOW(), b'0'),
(6, '已取消', 'cancelled', 'aicrm_activity_status', 0, 'danger', '活动已取消', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 审批状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM审批状态', 'aicrm_approval_status', 0, '通用审批状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM审批状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '待提交', 'pending_submit', 'aicrm_approval_status', 0, 'info', '待提交审批', '1', NOW(), '1', NOW(), b'0'),
(2, '审批中', 'approving', 'aicrm_approval_status', 0, 'warning', '正在审批中', '1', NOW(), '1', NOW(), b'0'),
(3, '已通过', 'approved', 'aicrm_approval_status', 0, 'success', '审批已通过', '1', NOW(), '1', NOW(), b'0'),
(4, '已驳回', 'rejected', 'aicrm_approval_status', 0, 'danger', '审批已驳回', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 活动类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM营销活动类型', 'aicrm_activity_type', 0, '营销活动的业务类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM营销活动类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '产品推广', 'product_promotion', 'aicrm_activity_type', 0, 'primary', '新产品推广活动', '1', NOW(), '1', NOW(), b'0'),
(2, '客户维护', 'customer_maintain', 'aicrm_activity_type', 0, 'success', '客户关系维护', '1', NOW(), '1', NOW(), b'0'),
(3, '市场调研', 'market_research', 'aicrm_activity_type', 0, 'info', '市场调研活动', '1', NOW(), '1', NOW(), b'0'),
(4, '品牌宣传', 'brand_publicity', 'aicrm_activity_type', 0, 'warning', '品牌宣传活动', '1', NOW(), '1', NOW(), b'0'),
(5, '节日营销', 'holiday_marketing', 'aicrm_activity_type', 0, 'danger', '节日营销活动', '1', NOW(), '1', NOW(), b'0'),
(6, '交叉销售', 'cross_selling', 'aicrm_activity_type', 0, 'primary', '交叉销售活动', '1', NOW(), '1', NOW(), b'0'),
(7, '客户回访', 'customer_callback', 'aicrm_activity_type', 0, 'success', '客户回访活动', '1', NOW(), '1', NOW(), b'0'),
(8, '增值服务', 'value_added_service', 'aicrm_activity_type', 0, 'info', '增值服务活动', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
