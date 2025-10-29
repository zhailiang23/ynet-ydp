-- ----------------------------
-- 网格信息表、客户归属网格表、网格归属调整历史表 (最终版)
-- 设计原则:
-- 1. 客户归属网格表只记录关系(customer_id + grid_id)，网格信息通过关联查询
-- 2. 历史表记录调整当时的网格信息快照(因为网格信息可能变化)
-- 3. 每条记录只包含一个网格信息
-- ----------------------------

-- ==================== 网格信息表 (不变) ====================
DROP TABLE IF EXISTS `crm_grid_info`;
CREATE TABLE `crm_grid_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '网格主键',

  -- ==================== 网格基本信息 ====================
  `grid_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '网格编号',
  `grid_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '网格名称',
  `grid_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '网格类型（字典: aicrm_grid_type，physical=物理网格，virtual=虚拟网格）',

  -- ==================== 网格管理信息 ====================
  `dept_id` bigint NULL DEFAULT NULL COMMENT '所属部门ID（关联 system_dept.id）',
  `manager_user_id` bigint NULL DEFAULT NULL COMMENT '网格管理员用户ID（关联 system_users.id）',

  -- ==================== 网格地理信息 ====================
  `province` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '省',
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '市',
  `district` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '区/县',
  `address_detail` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '详细地址范围',

  -- ==================== 网格状态 ====================
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '网格状态（0=停用，1=启用）',
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
  UNIQUE INDEX `uk_grid_code`(`grid_code` ASC, `deleted` ASC) USING BTREE COMMENT '网格编号唯一索引',
  INDEX `idx_grid_type`(`grid_type` ASC) USING BTREE COMMENT '网格类型索引',
  INDEX `idx_dept_id`(`dept_id` ASC) USING BTREE COMMENT '部门ID索引',
  INDEX `idx_manager_user_id`(`manager_user_id` ASC) USING BTREE COMMENT '网格管理员索引',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '网格信息表（支持物理网格和虚拟网格）';

-- ==================== 客户归属网格表 (精简版 - 只记录关系) ====================
-- 客户归属网格关系表 (零售和对公共用)
-- 只记录客户和网格的关联关系，网格信息通过关联 crm_grid_info 表查询
-- 每条记录只包含一个网格ID
-- 如果客户同时属于物理网格和虚拟网格，则有两条记录
-- 关系: crm_customer 1对多 crm_customer_grid_assignment
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_grid_assignment`;
CREATE TABLE `crm_customer_grid_assignment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '归属网格关系主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 网格关联 ====================
  `grid_id` bigint NOT NULL COMMENT '网格ID（关联 crm_grid_info.id）',

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
  INDEX `idx_grid_id`(`grid_id` ASC) USING BTREE COMMENT '网格ID索引',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '状态索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引',
  UNIQUE INDEX `uk_customer_grid`(`customer_id` ASC, `grid_id` ASC, `deleted` ASC) USING BTREE COMMENT '客户网格归属唯一索引（同一客户同一网格只能有一条有效记录）'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户归属网格关系表（只记录关系，网格信息通过关联查询）';

-- ==================== 客户归属网格调整历史表 (记录快照) ====================
-- 客户归属网格调整历史表 (零售和对公共用)
-- 记录调整当时的网格信息快照（因为网格信息可能会变化）
-- 记录单个网格的调整历史
-- 关系: crm_customer 1对多 crm_customer_grid_history
-- ----------------------------
DROP TABLE IF EXISTS `crm_customer_grid_history`;
CREATE TABLE `crm_customer_grid_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '调整历史主键',
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',

  -- ==================== 调整基本信息 ====================
  `adjust_date` date NOT NULL COMMENT '调整日期',
  `adjust_reason` varchar(800) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整原因',

  -- ==================== 调整前网格信息快照 ====================
  `before_grid_id` bigint NULL DEFAULT NULL COMMENT '调整前网格ID',
  `before_grid_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前网格编号',
  `before_grid_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前网格名称',
  `before_grid_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前网格类型',
  `before_grid_manager_user_id` bigint NULL DEFAULT NULL COMMENT '调整前网格管理员用户ID',

  -- ==================== 调整后网格信息快照 ====================
  `after_grid_id` bigint NULL DEFAULT NULL COMMENT '调整后网格ID',
  `after_grid_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整后网格编号',
  `after_grid_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整后网格名称',
  `after_grid_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整后网格类型',
  `after_grid_manager_user_id` bigint NULL DEFAULT NULL COMMENT '调整后网格管理员用户ID',

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
  INDEX `idx_before_grid_id`(`before_grid_id` ASC) USING BTREE COMMENT '调整前网格ID索引',
  INDEX `idx_after_grid_id`(`after_grid_id` ASC) USING BTREE COMMENT '调整后网格ID索引',
  INDEX `idx_before_grid_type`(`before_grid_type` ASC) USING BTREE COMMENT '调整前网格类型索引',
  INDEX `idx_after_grid_type`(`after_grid_type` ASC) USING BTREE COMMENT '调整后网格类型索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户归属网格调整历史表（记录调整当时的网格信息快照）';

-- ==================== 字典类型和字典数据 ====================

-- 1. 网格类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM网格类型', 'aicrm_grid_type', 0, '网格类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM网格类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '物理网格', 'physical', 'aicrm_grid_type', 0, 'primary', '按照地理位置划分的物理网格', '1', NOW(), '1', NOW(), b'0'),
(2, '虚拟网格', 'virtual', 'aicrm_grid_type', 0, 'success', '按照客户属性划分的虚拟网格', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
