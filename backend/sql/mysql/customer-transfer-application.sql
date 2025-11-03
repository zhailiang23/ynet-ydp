-- 客户移交申请表
-- 用于客户经理之间移交客户,需要通过 BPM 审批流程

CREATE TABLE IF NOT EXISTS `crm_customer_transfer_application` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',
  `applicant_user_id` bigint NOT NULL COMMENT '申请人用户ID（移交方客户经理）',
  `applicant_dept_id` bigint NOT NULL COMMENT '申请人部门ID',
  `to_user_id` bigint NOT NULL COMMENT '接收方客户经理ID',
  `to_dept_id` bigint NOT NULL COMMENT '接收方部门ID',
  `apply_date` date NOT NULL COMMENT '申请日期',
  `transfer_reason` varchar(500) NOT NULL COMMENT '移交原因',
  `process_instance_id` varchar(64) DEFAULT NULL COMMENT 'BPM 流程实例ID（关联 bpm_process_instance.id）',
  `process_status` tinyint NOT NULL DEFAULT 1 COMMENT '审批状态（字典: aicrm_process_status，1=审批中，2=已通过，3=已拒绝，4=已取消）',
  `approver_comment` varchar(500) DEFAULT NULL COMMENT '审批意见',
  `approve_time` datetime DEFAULT NULL COMMENT '审批时间',
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id` (`customer_id`) COMMENT '客户ID索引',
  INDEX `idx_applicant_user_id` (`applicant_user_id`) COMMENT '申请人索引',
  INDEX `idx_to_user_id` (`to_user_id`) COMMENT '接收方索引',
  INDEX `idx_process_status` (`process_status`) COMMENT '审批状态索引',
  INDEX `idx_apply_date` (`apply_date`) COMMENT '申请日期索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户移交申请表';

-- 初始化菜单数据（客户移交申请管理）
-- 注意: 这部分将在后面统一插入到 system_menu 表中
