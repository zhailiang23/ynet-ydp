-- ----------------------------
-- 客群信息表、客户归属客群表、客户归属客群调整历史表
-- 设计原则:
-- 1. 客户归属客群表只记录关系(customer_id + group_id)，客群信息通过关联查询
-- 2. 历史表记录调整当时的客群信息快照(因为客群信息可能变化)
-- 3. 参考图片: 客户群编号、客户群名称、客户群分类、群成员类型、客户来源、客户群成员数
-- ----------------------------

-- ==================== 客群信息表 ====================
DROP TABLE IF EXISTS `crm_customer_group_info`;
CREATE TABLE `crm_customer_group_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '客群主键',

  -- ==================== 客群基本信息 ====================
  `group_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户群编号',
  `group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户群名称',
  `group_category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '客户群分类（字典: aicrm_customer_group_category）',
  `member_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '群成员类型（字典: aicrm_group_member_type，retail=零售客户，company=对公客户，mixed=混合）',
  `customer_source` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户来源（字典: aicrm_customer_source）',

  -- ==================== 客群管理信息 ====================
  `dept_id` bigint NULL DEFAULT NULL COMMENT '所属部门ID（关联 system_dept.id）',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '客群管理员用户ID（关联 system_users.id）',
  `member_count` int NOT NULL DEFAULT 0 COMMENT '客户群成员数（冗余字段，定期更新）',

  -- ==================== 客群描述 ====================
  `description` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客群描述',

  -- ==================== 客群状态 ====================
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '客群状态（0=停用，1=启用）',
  `sort_order` int NOT NULL DEFAULT 0 COMMENT '排序',

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
  UNIQUE INDEX `uk_group_code`(`group_code` ASC, `deleted` ASC) USING BTREE COMMENT '客户群编号唯一索引',
  INDEX `idx_group_category`(`group_category` ASC) USING BTREE COMMENT '客户群分类索引',
  INDEX `idx_member_type`(`member_type` ASC) USING BTREE COMMENT '群成员类型索引',
  INDEX `idx_dept_id`(`dept_id` ASC) USING BTREE COMMENT '部门ID索引',
  INDEX `idx_manager_user_id`(`manager_user_id` ASC) USING BTREE COMMENT '客群管理员索引',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客群信息表（支持零售、对公、混合客群）';

-- ==================== 客户归属客群表 (精简版 - 只记录关系) ====================
-- 客户归属客群关系表 (零售和对公共用)
-- 只记录客户和客群的关联关系，客群信息通过关联 crm_customer_group_info 表查询
-- 一个客户可以属于多个客群
-- 关系: crm_customer 1对多 crm_customer_group_assignment
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_group_assignment`;
CREATE TABLE `crm_customer_group_assignment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '归属客群关系主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 客群关联 ====================
  `group_id` bigint NOT NULL COMMENT '客群ID（关联 crm_customer_group_info.id）',

  -- ==================== 分配信息 ====================
  `assign_date` date NOT NULL COMMENT '分配日期',
  `assign_operator_id` bigint NULL DEFAULT NULL COMMENT '分配操作人用户ID（关联 system_users.id）',

  -- ==================== 归属状态 ====================
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '归属状态（0=已失效，1=生效中）',

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
  INDEX `idx_group_id`(`group_id` ASC) USING BTREE COMMENT '客群ID索引',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引',
  UNIQUE INDEX `uk_customer_group`(`customer_id` ASC, `group_id` ASC, `deleted` ASC) USING BTREE COMMENT '客户客群归属唯一索引（同一客户同一客群只能有一条有效记录）'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户归属客群关系表（只记录关系，客群信息通过关联查询）';

-- ==================== 客户归属客群调整历史表 (记录快照) ====================
-- 客户归属客群调整历史表 (零售和对公共用)
-- 记录调整当时的客群信息快照（因为客群信息可能会变化）
-- 记录单个客群的调整历史
-- 关系: crm_customer 1对多 crm_customer_group_history
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_group_history`;
CREATE TABLE `crm_customer_group_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '调整历史主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 调整基本信息 ====================
  `adjust_date` date NOT NULL COMMENT '调整日期',
  `adjust_reason` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整原因',

  -- ==================== 调整前客群信息快照 ====================
  `before_group_id` bigint NULL DEFAULT NULL COMMENT '调整前客群ID',
  `before_group_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前客户群编号',
  `before_group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前客户群名称',
  `before_group_category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前客户群分类',
  `before_manager_user_id` bigint NULL DEFAULT NULL COMMENT '调整前客群管理员用户ID',

  -- ==================== 调整后客群信息快照 ====================
  `after_group_id` bigint NULL DEFAULT NULL COMMENT '调整后客群ID',
  `after_group_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整后客户群编号',
  `after_group_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整后客户群名称',
  `after_group_category` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整后客户群分类',
  `after_manager_user_id` bigint NULL DEFAULT NULL COMMENT '调整后客群管理员用户ID',

  -- ==================== 调整操作信息 ====================
  `adjust_operator_id` bigint NULL DEFAULT NULL COMMENT '调整操作人用户ID（关联 system_users.id）',

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
  INDEX `idx_adjust_date`(`adjust_date` ASC) USING BTREE COMMENT '调整日期索引',
  INDEX `idx_before_group_id`(`before_group_id` ASC) USING BTREE COMMENT '调整前客群ID索引',
  INDEX `idx_after_group_id`(`after_group_id` ASC) USING BTREE COMMENT '调整后客群ID索引',
  INDEX `idx_before_group_category`(`before_group_category` ASC) USING BTREE COMMENT '调整前客户群分类索引',
  INDEX `idx_after_group_category`(`after_group_category` ASC) USING BTREE COMMENT '调整后客户群分类索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户归属客群调整历史表（记录调整当时的客群信息快照）';

-- ==================== 字典类型和字典数据 ====================

-- 1. 客户群分类字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户群分类', 'aicrm_customer_group_category', 0, '客户群分类', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户群分类';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '高净值客群', 'high_net_worth', 'aicrm_customer_group_category', 0, 'primary', '资产超过一定额度的高净值客户', '1', NOW(), '1', NOW(), b'0'),
(2, '潜力客群', 'potential', 'aicrm_customer_group_category', 0, 'success', '具有发展潜力的客户', '1', NOW(), '1', NOW(), b'0'),
(3, '活跃客群', 'active', 'aicrm_customer_group_category', 0, 'info', '交易活跃的客户', '1', NOW(), '1', NOW(), b'0'),
(4, '沉睡客群', 'dormant', 'aicrm_customer_group_category', 0, 'warning', '长期无交易的客户', '1', NOW(), '1', NOW(), b'0'),
(5, '流失客群', 'lost', 'aicrm_customer_group_category', 0, 'danger', '已流失的客户', '1', NOW(), '1', NOW(), b'0'),
(6, '行业客群', 'industry', 'aicrm_customer_group_category', 0, 'default', '按行业划分的客户群', '1', NOW(), '1', NOW(), b'0'),
(7, '区域客群', 'region', 'aicrm_customer_group_category', 0, 'default', '按区域划分的客户群', '1', NOW(), '1', NOW(), b'0'),
(8, '产品客群', 'product', 'aicrm_customer_group_category', 0, 'default', '持有特定产品的客户群', '1', NOW(), '1', NOW(), b'0'),
(9, '营销客群', 'marketing', 'aicrm_customer_group_category', 0, 'default', '营销活动目标客户群', '1', NOW(), '1', NOW(), b'0'),
(10, '自定义客群', 'custom', 'aicrm_customer_group_category', 0, 'default', '自定义规则的客户群', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 群成员类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM群成员类型', 'aicrm_group_member_type', 0, '群成员类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM群成员类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '零售客户', 'retail', 'aicrm_group_member_type', 0, 'primary', '零售客户群', '1', NOW(), '1', NOW(), b'0'),
(2, '对公客户', 'company', 'aicrm_group_member_type', 0, 'success', '对公客户群', '1', NOW(), '1', NOW(), b'0'),
(3, '混合客群', 'mixed', 'aicrm_group_member_type', 0, 'info', '包含零售和对公客户', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 客户来源字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM客户来源', 'aicrm_customer_source', 0, '客户来源', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM客户来源';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '线下营销', 'offline_marketing', 'aicrm_customer_source', 0, 'primary', '线下活动、地推等', '1', NOW(), '1', NOW(), b'0'),
(2, '线上营销', 'online_marketing', 'aicrm_customer_source', 0, 'success', '网站、APP、微信等', '1', NOW(), '1', NOW(), b'0'),
(3, '客户推荐', 'referral', 'aicrm_customer_source', 0, 'info', '老客户推荐', '1', NOW(), '1', NOW(), b'0'),
(4, '合作伙伴', 'partner', 'aicrm_customer_source', 0, 'warning', '合作机构推荐', '1', NOW(), '1', NOW(), b'0'),
(5, '主动来访', 'walk_in', 'aicrm_customer_source', 0, 'default', '客户主动到访', '1', NOW(), '1', NOW(), b'0'),
(6, '电话咨询', 'phone_inquiry', 'aicrm_customer_source', 0, 'default', '客户电话咨询', '1', NOW(), '1', NOW(), b'0'),
(7, '其他', 'other', 'aicrm_customer_source', 0, 'default', '其他来源', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
