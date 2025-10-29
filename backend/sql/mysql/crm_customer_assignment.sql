-- ----------------------------
-- 客户归属关系表 (零售和对公共用)
-- 设计参考: docs/database-design-guide.md 第2.1节 - 共有信息统一表结构
-- 关系: crm_customer 1对多 crm_customer_assignment
-- 参考老系统: ocrm_f_ci_belong_custmgr
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_assignment`;
CREATE TABLE `crm_customer_assignment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '归属关系主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 归属机构信息 ====================
  `assignment_type` tinyint NOT NULL COMMENT '归属类型（1=主办，2=协办）',
  `institution_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '归属机构编码',
  `institution_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '归属机构名称',

  -- ==================== 归属客户经理信息 ====================
  `manager_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户经理ID',
  `manager_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户经理姓名',

  -- ==================== 归属权限信息 ====================
  `has_view_right` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否有查看权限',
  `has_maintain_right` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否有维护权限',

  -- ==================== 归属时间信息 ====================
  `assign_date` date NOT NULL COMMENT '分配日期',
  `effective_date` date NULL DEFAULT NULL COMMENT '生效日期',
  `expiry_date` date NULL DEFAULT NULL COMMENT '失效日期（NULL表示长期有效）',

  -- ==================== 分配操作信息 ====================
  `assign_operator_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分配操作人ID',
  `assign_operator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '分配操作人姓名',

  -- ==================== 归属状态 ====================
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '归属状态（0=已失效，1=生效中，2=待生效）',

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
  INDEX `idx_manager_id`(`manager_id` ASC) USING BTREE COMMENT '客户经理ID索引',
  INDEX `idx_institution_code`(`institution_code` ASC) USING BTREE COMMENT '机构编码索引',
  INDEX `idx_assignment_type`(`assignment_type` ASC) USING BTREE COMMENT '归属类型索引',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引',
  UNIQUE INDEX `uk_customer_assignment`(`customer_id` ASC, `assignment_type` ASC, `manager_id` ASC, `deleted` ASC) USING BTREE COMMENT '客户归属唯一索引（同一客户的同一类型归属，同一客户经理只能有一条有效记录）'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户归属关系表（零售+对公共用，支持主协办模式）';

-- ----------------------------
-- 客户归属调整历史表 (零售和对公共用)
-- 设计参考: docs/database-design-guide.md 第3.3节 - 历史记录表
-- 关系: crm_customer 1对多 crm_customer_assignment_history
-- 参考老系统: ocrm_f_ci_belong_hist
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_assignment_history`;
CREATE TABLE `crm_customer_assignment_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '调整历史主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 调整基本信息 ====================
  `transfer_date` date NOT NULL COMMENT '调整日期',
  `transfer_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整级别（字典: aicrm_transfer_level，如：支行内调整、跨支行调整、跨分行调整）',
  `transfer_reason` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整原因',

  -- ==================== 调整前归属信息 ====================
  `before_assignment_type` tinyint NULL DEFAULT NULL COMMENT '调整前归属类型（1=主办，2=协办）',
  `before_institution_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前机构编码',
  `before_institution_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前机构名称',
  `before_manager_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前客户经理ID',
  `before_manager_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前客户经理姓名',

  -- ==================== 调整后归属信息 ====================
  `after_assignment_type` tinyint NULL DEFAULT NULL COMMENT '调整后归属类型（1=主办，2=协办）',
  `after_institution_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整后机构编码',
  `after_institution_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整后机构名称',
  `after_manager_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整后客户经理ID',
  `after_manager_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整后客户经理姓名',

  -- ==================== 调整操作信息 ====================
  `assign_operator_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整操作人ID',
  `assign_operator_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整操作人姓名',
  `assign_date` date NULL DEFAULT NULL COMMENT '分配日期',

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
  INDEX `idx_transfer_date`(`transfer_date` ASC) USING BTREE COMMENT '调整日期索引',
  INDEX `idx_before_manager_id`(`before_manager_id` ASC) USING BTREE COMMENT '调整前客户经理索引',
  INDEX `idx_after_manager_id`(`after_manager_id` ASC) USING BTREE COMMENT '调整后客户经理索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户归属调整历史表（零售+对公共用，记录所有归属变更历史）';

-- ----------------------------
-- 为现有所有客户创建默认归属关系
-- 默认分配给系统管理员（ID=1），机构为总行（0043977）
-- ----------------------------
INSERT INTO `crm_customer_assignment` (
  `customer_id`,
  `assignment_type`,
  `institution_code`,
  `institution_name`,
  `manager_id`,
  `manager_name`,
  `has_view_right`,
  `has_maintain_right`,
  `assign_date`,
  `effective_date`,
  `assign_operator_id`,
  `assign_operator_name`,
  `status`,
  `remark`,
  `creator`,
  `create_time`,
  `updater`,
  `update_time`,
  `tenant_id`
)
SELECT
  c.id AS customer_id,
  1 AS assignment_type,  -- 主办
  '0043977' AS institution_code,
  '商业银行' AS institution_name,
  '1' AS manager_id,
  '芋道' AS manager_name,
  b'1' AS has_view_right,
  b'1' AS has_maintain_right,
  CURDATE() AS assign_date,
  CURDATE() AS effective_date,
  '1' AS assign_operator_id,
  '系统' AS assign_operator_name,
  1 AS status,  -- 生效中
  '系统初始化默认归属' AS remark,
  '1' AS creator,
  NOW() AS create_time,
  '1' AS updater,
  NOW() AS update_time,
  c.tenant_id
FROM crm_customer c
LEFT JOIN crm_customer_assignment a ON c.id = a.customer_id AND a.assignment_type = 1 AND a.deleted = b'0'
WHERE a.id IS NULL  -- 只为没有主办归属的客户创建
  AND c.deleted = b'0';

-- ----------------------------
-- 字典类型和字典数据
-- ----------------------------

-- 1. 调整级别字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM归属调整级别', 'aicrm_transfer_level', 0, '客户归属调整级别', '1', NOW(), '1', NOW(), b'0');

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '客户经理内部调整', 'manager_internal', 'aicrm_transfer_level', 0, 'success', '同一客户经理内部调整', '1', NOW(), '1', NOW(), b'0'),
(2, '网点内调整', 'branch_internal', 'aicrm_transfer_level', 0, 'primary', '同一网点内不同客户经理', '1', NOW(), '1', NOW(), b'0'),
(3, '跨网点调整', 'cross_branch', 'aicrm_transfer_level', 0, 'info', '跨网点调整', '1', NOW(), '1', NOW(), b'0'),
(4, '跨支行调整', 'cross_sub_branch', 'aicrm_transfer_level', 0, 'warning', '跨支行调整', '1', NOW(), '1', NOW(), b'0'),
(5, '跨分行调整', 'cross_branch_bank', 'aicrm_transfer_level', 0, 'danger', '跨分行调整', '1', NOW(), '1', NOW(), b'0');
