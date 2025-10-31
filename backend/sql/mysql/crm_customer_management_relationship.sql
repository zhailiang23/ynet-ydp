-- ========================================
-- 客户管理关系模块 - 数据库表结构
-- 参考: docs/customer-management-relationship-technical-design.md
-- ========================================

-- ----------------------------
-- 1. 为现有表添加新字段
-- ----------------------------

-- 1.1 crm_customer_assignment 表新增托管相关字段
ALTER TABLE `crm_customer_assignment`
ADD COLUMN `is_delegated` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否托管状态' AFTER `status`,
ADD COLUMN `delegate_from_user_id` bigint NULL DEFAULT NULL COMMENT '托管来源客户经理ID（关联 system_users.id）' AFTER `is_delegated`,
ADD COLUMN `delegate_start_date` date NULL DEFAULT NULL COMMENT '托管开始日期' AFTER `delegate_from_user_id`,
ADD COLUMN `delegate_end_date` date NULL DEFAULT NULL COMMENT '托管结束日期' AFTER `delegate_start_date`,
ADD COLUMN `delegate_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '托管原因' AFTER `delegate_end_date`;

-- 1.2 crm_customer_assignment_history 表新增字段
ALTER TABLE `crm_customer_assignment_history`
ADD COLUMN `operation_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '操作类型（字典: aicrm_assignment_operation_type）' AFTER `transfer_reason`,
ADD COLUMN `is_delegate_operation` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否托管操作' AFTER `operation_type`,
ADD COLUMN `process_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '关联的审批流程实例ID（如有审批）' AFTER `is_delegate_operation`;

-- ----------------------------
-- 2. 创建新业务表
-- ----------------------------

-- 2.1 客户认领申请表
DROP TABLE IF EXISTS `crm_customer_claim_application`;
CREATE TABLE `crm_customer_claim_application` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer）',
  `applicant_user_id` bigint NOT NULL COMMENT '申请人(客户经理)ID（关联 system_users.id）',
  `applicant_dept_id` bigint NOT NULL COMMENT '申请人部门ID（关联 system_dept.id）',
  `apply_date` date NOT NULL COMMENT '申请日期',
  `apply_reason` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '申请理由',

  -- 工作流审批相关
  `process_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'BPM流程实例ID',
  `process_status` tinyint NOT NULL DEFAULT 1 COMMENT '流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）',

  -- 审计字段
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_applicant_user_id`(`applicant_user_id` ASC) USING BTREE COMMENT '申请人索引',
  INDEX `idx_applicant_dept_id`(`applicant_dept_id` ASC) USING BTREE COMMENT '申请人部门索引',
  INDEX `idx_process_instance_id`(`process_instance_id` ASC) USING BTREE COMMENT '流程实例ID索引',
  INDEX `idx_process_status`(`process_status` ASC) USING BTREE COMMENT '流程状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户认领申请表';

-- 2.2 客户退回申请表
DROP TABLE IF EXISTS `crm_customer_return_application`;
CREATE TABLE `crm_customer_return_application` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '申请ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer）',
  `applicant_user_id` bigint NOT NULL COMMENT '申请人(客户经理)ID（关联 system_users.id）',
  `return_to_user_id` bigint NOT NULL COMMENT '退回给主管ID（关联 system_users.id）',
  `apply_date` date NOT NULL COMMENT '申请日期',
  `return_reason` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '退回原因',

  -- 工作流审批相关
  `process_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'BPM流程实例ID',
  `process_status` tinyint NOT NULL DEFAULT 1 COMMENT '流程状态（1=审批中，2=已通过，3=已拒绝，4=已取消）',

  -- 审计字段
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_applicant_user_id`(`applicant_user_id` ASC) USING BTREE COMMENT '申请人索引',
  INDEX `idx_return_to_user_id`(`return_to_user_id` ASC) USING BTREE COMMENT '退回目标主管索引',
  INDEX `idx_process_instance_id`(`process_instance_id` ASC) USING BTREE COMMENT '流程实例ID索引',
  INDEX `idx_process_status`(`process_status` ASC) USING BTREE COMMENT '流程状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户退回申请表';

-- 2.3 客户托管记录表
DROP TABLE IF EXISTS `crm_customer_delegation`;
CREATE TABLE `crm_customer_delegation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '托管ID',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer）',
  `from_user_id` bigint NOT NULL COMMENT '托管方客户经理ID（关联 system_users.id）',
  `to_user_id` bigint NOT NULL COMMENT '受托方客户经理ID（关联 system_users.id）',
  `start_date` date NOT NULL COMMENT '托管开始日期',
  `end_date` date NULL DEFAULT NULL COMMENT '托管结束日期（计划）',
  `actual_end_date` date NULL DEFAULT NULL COMMENT '实际结束日期',
  `delegation_reason` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '托管原因',
  `delegation_status` tinyint NOT NULL DEFAULT 1 COMMENT '托管状态（0=已结束，1=托管中，2=已取消）',

  -- 审计字段
  `creator` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID索引',
  INDEX `idx_from_user_id`(`from_user_id` ASC) USING BTREE COMMENT '托管方索引',
  INDEX `idx_to_user_id`(`to_user_id` ASC) USING BTREE COMMENT '受托方索引',
  INDEX `idx_status`(`delegation_status` ASC) USING BTREE COMMENT '托管状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户托管记录表';
