-- ----------------------------
-- 客户评级信息表
-- 设计原则:
-- 1. 记录客户的当前评级信息（与客户主表1对1关系）
-- 2. 支持零售客户和对公客户
-- 3. 严格按照图片字段设计：序号、审批状态、评级日期、价值等级、服务等级、剔除风险前服务等级、风险影响因子内容、评级方式
-- 4. 参考老系统 acrm_f_ci_grade 表设计
-- ----------------------------

-- ==================== 客户评级信息表 ====================
DROP TABLE IF EXISTS `crm_customer_rating`;
CREATE TABLE `crm_customer_rating` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评级主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表，1对1关系）',

  -- ==================== 图片字段 ====================
  `sequence_no` int NULL DEFAULT NULL COMMENT '序号',
  `approval_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批状态（字典: aicrm_rating_approval_status）',
  `rating_date` date NOT NULL COMMENT '评级日期',
  `value_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '价值等级（字典: aicrm_value_level）',
  `service_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务等级（字典: aicrm_service_level）',
  `service_level_before_risk` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '剔除风险前服务等级（字典: aicrm_service_level）',
  `risk_factors` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '风险影响因子内容',
  `rating_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评级方式（字典: aicrm_rating_method，system=系统评级，manual=人工评级）',

  -- ==================== 老系统字段（acrm_f_ci_grade）====================
  `cust_grade_id` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评级ID（老系统）',
  `effective_date` date NULL DEFAULT NULL COMMENT '生效日期',
  `expired_date` date NULL DEFAULT NULL COMMENT '失效日期',
  `evaluate_date` date NULL DEFAULT NULL COMMENT '评估日期',
  `cust_grade` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户等级（老系统字段）',
  `cust_grade_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户等级类型',
  `org_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机构编码',
  `org_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机构名称',
  `cust_cnt` int NULL DEFAULT NULL COMMENT '客户数量',

  -- ==================== 扩展字段 ====================
  `rating_score` decimal(10,2) NULL DEFAULT NULL COMMENT '评级分数',
  `rating_model_version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评级模型版本',
  `risk_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '风险等级（字典: aicrm_risk_level）',
  `next_rating_date` date NULL DEFAULT NULL COMMENT '下次评级日期',

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
  UNIQUE INDEX `uk_customer_id`(`customer_id` ASC) USING BTREE COMMENT '客户ID唯一索引（1对1关系）',
  INDEX `idx_rating_date`(`rating_date` ASC) USING BTREE COMMENT '评级日期索引',
  INDEX `idx_approval_status`(`approval_status` ASC) USING BTREE COMMENT '审批状态索引',
  INDEX `idx_value_level`(`value_level` ASC) USING BTREE COMMENT '价值等级索引',
  INDEX `idx_service_level`(`service_level` ASC) USING BTREE COMMENT '服务等级索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户评级信息表（与客户主表1对1关系）';


-- ==================== 客户评级调整历史表 ====================
DROP TABLE IF EXISTS `crm_customer_rating_history`;
CREATE TABLE `crm_customer_rating_history` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '历史主键',

  -- ==================== 客户关联信息 ====================
  `customer_id` bigint NOT NULL COMMENT '客户ID（关联 crm_customer 主表）',
  `rating_id` bigint NULL DEFAULT NULL COMMENT '评级ID（关联当前评级表）',

  -- ==================== 图片字段 ====================
  `sequence_no` int NULL DEFAULT NULL COMMENT '序号',
  `approval_status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '审批状态（字典: aicrm_rating_approval_status）',
  `rating_date` date NOT NULL COMMENT '评级日期',
  `value_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '价值等级（字典: aicrm_value_level）',
  `service_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '服务等级（字典: aicrm_service_level）',
  `service_level_before_risk` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '剔除风险前服务等级（字典: aicrm_service_level）',
  `risk_factors` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '风险影响因子内容',
  `rating_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评级方式（字典: aicrm_rating_method）',

  -- ==================== 老系统字段 ====================
  `cust_grade_id` varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评级ID（老系统）',
  `effective_date` date NULL DEFAULT NULL COMMENT '生效日期',
  `expired_date` date NULL DEFAULT NULL COMMENT '失效日期',
  `evaluate_date` date NULL DEFAULT NULL COMMENT '评估日期',
  `cust_grade` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户等级（老系统字段）',
  `cust_grade_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '客户等级类型',
  `org_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机构编码',
  `org_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '机构名称',
  `cust_cnt` int NULL DEFAULT NULL COMMENT '客户数量',

  -- ==================== 扩展字段 ====================
  `rating_score` decimal(10,2) NULL DEFAULT NULL COMMENT '评级分数',
  `rating_model_version` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '评级模型版本',
  `risk_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '风险等级（字典: aicrm_risk_level）',
  `change_reason` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整原因',
  `change_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整类型（字典: aicrm_rating_change_type，upgrade=升级，downgrade=降级，maintain=维持）',
  `previous_value_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前价值等级',
  `previous_service_level` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '调整前服务等级',

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
  INDEX `idx_rating_id`(`rating_id` ASC) USING BTREE COMMENT '评级ID索引',
  INDEX `idx_rating_date`(`rating_date` ASC) USING BTREE COMMENT '评级日期索引',
  INDEX `idx_change_type`(`change_type` ASC) USING BTREE COMMENT '调整类型索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '客户评级调整历史表';


-- ==================== 字典类型和字典数据 ====================

-- 1. 审批状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM审批状态', 'aicrm_rating_approval_status', 0, '评级审批状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM审批状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '待审批', 'pending', 'aicrm_rating_approval_status', 0, 'warning', '待审批', '1', NOW(), '1', NOW(), b'0'),
(2, '已通过', 'approved', 'aicrm_rating_approval_status', 0, 'success', '已通过', '1', NOW(), '1', NOW(), b'0'),
(3, '已拒绝', 'rejected', 'aicrm_rating_approval_status', 0, 'danger', '已拒绝', '1', NOW(), '1', NOW(), b'0'),
(4, '已撤回', 'withdrawn', 'aicrm_rating_approval_status', 0, 'info', '已撤回', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 价值等级字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM价值等级', 'aicrm_value_level', 0, '客户价值等级', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM价值等级';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '财富客户', 'wealth', 'aicrm_value_level', 0, 'danger', '财富客户（最高等级）', '1', NOW(), '1', NOW(), b'0'),
(2, '贵宾客户', 'vip', 'aicrm_value_level', 0, 'warning', '贵宾客户', '1', NOW(), '1', NOW(), b'0'),
(3, '优质客户', 'quality', 'aicrm_value_level', 0, 'primary', '优质客户', '1', NOW(), '1', NOW(), b'0'),
(4, '普通客户', 'normal', 'aicrm_value_level', 0, 'success', '普通客户', '1', NOW(), '1', NOW(), b'0'),
(5, '潜力客户', 'potential', 'aicrm_value_level', 0, 'info', '潜力客户', '1', NOW(), '1', NOW(), b'0'),
(6, '基础客户', 'basic', 'aicrm_value_level', 0, 'default', '基础客户', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 3. 服务等级字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM服务等级', 'aicrm_service_level', 0, '客户服务等级', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM服务等级';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '5星', 'five_star', 'aicrm_service_level', 0, 'danger', '五星级服务', '1', NOW(), '1', NOW(), b'0'),
(2, '4星', 'four_star', 'aicrm_service_level', 0, 'warning', '四星级服务', '1', NOW(), '1', NOW(), b'0'),
(3, '3星', 'three_star', 'aicrm_service_level', 0, 'primary', '三星级服务', '1', NOW(), '1', NOW(), b'0'),
(4, '2星', 'two_star', 'aicrm_service_level', 0, 'success', '二星级服务', '1', NOW(), '1', NOW(), b'0'),
(5, '1星', 'one_star', 'aicrm_service_level', 0, 'info', '一星级服务', '1', NOW(), '1', NOW(), b'0'),
(6, '无星', 'no_star', 'aicrm_service_level', 0, 'default', '无星级', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 4. 评级方式字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM评级方式', 'aicrm_rating_method', 0, '评级方式', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM评级方式';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '系统评级', 'system', 'aicrm_rating_method', 0, 'primary', '系统自动评级', '1', NOW(), '1', NOW(), b'0'),
(2, '人工评级', 'manual', 'aicrm_rating_method', 0, 'success', '人工手动评级', '1', NOW(), '1', NOW(), b'0'),
(3, '混合评级', 'hybrid', 'aicrm_rating_method', 0, 'info', '系统+人工混合评级', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 5. 风险等级字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM风险等级', 'aicrm_risk_level', 0, '客户风险等级', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM风险等级';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '低风险', 'low', 'aicrm_risk_level', 0, 'success', '低风险客户', '1', NOW(), '1', NOW(), b'0'),
(2, '中低风险', 'medium_low', 'aicrm_risk_level', 0, 'info', '中低风险客户', '1', NOW(), '1', NOW(), b'0'),
(3, '中风险', 'medium', 'aicrm_risk_level', 0, 'primary', '中风险客户', '1', NOW(), '1', NOW(), b'0'),
(4, '中高风险', 'medium_high', 'aicrm_risk_level', 0, 'warning', '中高风险客户', '1', NOW(), '1', NOW(), b'0'),
(5, '高风险', 'high', 'aicrm_risk_level', 0, 'danger', '高风险客户', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 6. 评级调整类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM评级调整类型', 'aicrm_rating_change_type', 0, '评级调整类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM评级调整类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '升级', 'upgrade', 'aicrm_rating_change_type', 0, 'success', '等级升级', '1', NOW(), '1', NOW(), b'0'),
(2, '降级', 'downgrade', 'aicrm_rating_change_type', 0, 'danger', '等级降级', '1', NOW(), '1', NOW(), b'0'),
(3, '维持', 'maintain', 'aicrm_rating_change_type', 0, 'info', '等级维持', '1', NOW(), '1', NOW(), b'0'),
(4, '初始评级', 'initial', 'aicrm_rating_change_type', 0, 'primary', '首次评级', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
