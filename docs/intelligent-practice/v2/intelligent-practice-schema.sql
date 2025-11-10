-- =============================================
-- 智能陪练模块 - 数据库表结构
-- =============================================

-- =============================================
-- 1. 虚拟客户模块
-- =============================================

-- 虚拟客户模板表
CREATE TABLE `aicrm_practice_virtual_customer_template` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` VARCHAR(100) NOT NULL COMMENT '模板名称',
    `description` TEXT COMMENT '模板描述',
    `age_min` INT DEFAULT NULL COMMENT '年龄范围-最小值',
    `age_max` INT DEFAULT NULL COMMENT '年龄范围-最大值',
    `occupation` VARCHAR(100) DEFAULT NULL COMMENT '职业',
    `risk_preference` VARCHAR(20) DEFAULT NULL COMMENT '风险偏好(conservative-保守型,moderate-稳健型,aggressive-进取型)',
    `income_level` DECIMAL(15, 2) DEFAULT NULL COMMENT '收入水平',
    `asset_scale` DECIMAL(15, 2) DEFAULT NULL COMMENT '资产规模',
    `investment_experience` VARCHAR(20) DEFAULT NULL COMMENT '投资经验(none-无经验,beginner-初级,intermediate-中级,expert-专家)',
    `personality_traits` JSON DEFAULT NULL COMMENT '性格特征(JSON格式)',
    `custom_attributes` JSON DEFAULT NULL COMMENT '自定义属性(JSON格式)',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-虚拟客户模板';

-- =============================================
-- 2. 场景与剧本模块
-- =============================================

-- 对练场景表
CREATE TABLE `aicrm_practice_scene` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` VARCHAR(100) NOT NULL COMMENT '场景名称',
    `scene_type` VARCHAR(50) NOT NULL COMMENT '场景类型(prospecting-拓客,needs_discovery-需求挖掘,objection_handling-异议处理,closing-促成签约,complex-复杂场景)',
    `description` TEXT COMMENT '场景描述',
    `target_skills` JSON DEFAULT NULL COMMENT '目标技能(JSON数组)',
    `keywords` JSON DEFAULT NULL COMMENT '关键词(JSON数组)',
    `difficulty_level` INT DEFAULT 1 COMMENT '难度级别(1-5)',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_scene_type` (`scene_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-对练场景';

-- 销售剧本表
CREATE TABLE `aicrm_practice_sales_script` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` VARCHAR(100) NOT NULL COMMENT '剧本名称',
    `scene_id` BIGINT DEFAULT NULL COMMENT '关联场景ID',
    `current_version_id` BIGINT DEFAULT NULL COMMENT '当前版本ID',
    `description` TEXT COMMENT '剧本描述',
    `tags` JSON DEFAULT NULL COMMENT '标签(JSON数组)',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_scene_id` (`scene_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-销售剧本';

-- 剧本版本表
CREATE TABLE `aicrm_practice_script_version` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `script_id` BIGINT NOT NULL COMMENT '剧本ID',
    `version_number` VARCHAR(20) NOT NULL COMMENT '版本号',
    `change_log` TEXT COMMENT '变更日志',
    `content` JSON DEFAULT NULL COMMENT '剧本内容(JSON格式)',
    `avg_compliance_score` DECIMAL(5, 2) DEFAULT NULL COMMENT '平均合规得分',
    `usage_count` INT DEFAULT 0 COMMENT '使用次数',
    `is_active` TINYINT DEFAULT 0 COMMENT '是否当前版本(0-否,1-是)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_script_id` (`script_id`),
    INDEX `idx_is_active` (`is_active`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-剧本版本';

-- 剧本分支表
CREATE TABLE `aicrm_practice_script_branch` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `version_id` BIGINT NOT NULL COMMENT '版本ID',
    `branch_name` VARCHAR(100) NOT NULL COMMENT '分支名称',
    `trigger_condition` VARCHAR(200) DEFAULT NULL COMMENT '触发条件',
    `content` JSON DEFAULT NULL COMMENT '分支内容(JSON格式)',
    `parent_branch_id` BIGINT DEFAULT NULL COMMENT '父分支ID',
    `sequence` INT DEFAULT 0 COMMENT '排序',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_version_id` (`version_id`),
    INDEX `idx_parent_branch_id` (`parent_branch_id`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-剧本分支';

-- 案例库表
CREATE TABLE `aicrm_practice_case_library` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `title` VARCHAR(200) NOT NULL COMMENT '案例标题',
    `case_type` VARCHAR(50) NOT NULL COMMENT '案例类型',
    `background` TEXT COMMENT '背景描述',
    `problem` TEXT COMMENT '问题描述',
    `solution` TEXT COMMENT '解决方案',
    `outcome` TEXT COMMENT '结果',
    `lessons_learned` TEXT COMMENT '经验教训',
    `difficulty` VARCHAR(20) DEFAULT 'medium' COMMENT '难度(easy-简单,medium-中等,hard-困难)',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_case_type` (`case_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-案例库';

-- 案例标签表
CREATE TABLE `aicrm_practice_case_tag` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `tag_name` VARCHAR(50) NOT NULL COMMENT '标签名称',
    `tag_type` VARCHAR(20) NOT NULL COMMENT '标签类型(scene-场景,customer-客户,strategy-策略)',
    `color` VARCHAR(20) DEFAULT NULL COMMENT '标签颜色',
    `sort` INT DEFAULT 0 COMMENT '排序',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_tag_type` (`tag_type`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-案例标签';

-- 案例标签关联表
CREATE TABLE `aicrm_practice_case_tag_relation` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `case_id` BIGINT NOT NULL COMMENT '案例ID',
    `tag_id` BIGINT NOT NULL COMMENT '标签ID',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_case_tag` (`case_id`, `tag_id`),
    INDEX `idx_case_id` (`case_id`),
    INDEX `idx_tag_id` (`tag_id`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-案例标签关联';

-- =============================================
-- 3. 销售套路与技巧模块
-- =============================================

-- 销售套路表
CREATE TABLE `aicrm_practice_sales_routine` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` VARCHAR(100) NOT NULL COMMENT '套路名称',
    `description` TEXT COMMENT '描述',
    `steps` JSON DEFAULT NULL COMMENT '步骤(JSON数组)',
    `applicable_scenes` JSON DEFAULT NULL COMMENT '适用场景(JSON数组)',
    `related_compliance_rules` JSON DEFAULT NULL COMMENT '关联合规规则(JSON数组)',
    `related_products` JSON DEFAULT NULL COMMENT '关联产品(JSON数组)',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-销售套路';

-- 销售技巧表
CREATE TABLE `aicrm_practice_sales_skill` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` VARCHAR(100) NOT NULL COMMENT '技巧名称',
    `skill_type` VARCHAR(50) NOT NULL COMMENT '技巧类型',
    `description` TEXT COMMENT '描述',
    `template_content` TEXT COMMENT '话术模板',
    `example` TEXT COMMENT '示例',
    `related_compliance_rules` JSON DEFAULT NULL COMMENT '关联合规规则(JSON数组)',
    `related_knowledge` JSON DEFAULT NULL COMMENT '关联产品知识(JSON数组)',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_skill_type` (`skill_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-销售技巧';

-- 合规规则表
CREATE TABLE `aicrm_practice_compliance_rule` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `rule_code` VARCHAR(50) NOT NULL COMMENT '规则编码',
    `rule_name` VARCHAR(100) NOT NULL COMMENT '规则名称',
    `description` TEXT COMMENT '规则描述',
    `prohibited_terms` JSON DEFAULT NULL COMMENT '禁用术语(JSON数组)',
    `warning_terms` JSON DEFAULT NULL COMMENT '警告术语(JSON数组)',
    `severity` VARCHAR(20) DEFAULT 'medium' COMMENT '严重程度(low-低,medium-中,high-高,critical-严重)',
    `suggestion` TEXT COMMENT '改进建议',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_rule_code` (`rule_code`, `tenant_id`),
    INDEX `idx_severity` (`severity`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-合规规则';

-- =============================================
-- 4. 对练会话模块
-- =============================================

-- 对练会话表
CREATE TABLE `aicrm_practice_session` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `session_code` VARCHAR(50) NOT NULL COMMENT '会话编号',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `scene_id` BIGINT DEFAULT NULL COMMENT '场景ID',
    `script_id` BIGINT DEFAULT NULL COMMENT '剧本ID',
    `script_version_id` BIGINT DEFAULT NULL COMMENT '剧本版本ID',
    `virtual_customer_id` BIGINT DEFAULT NULL COMMENT '虚拟客户模板ID',
    `virtual_customer_profile` JSON DEFAULT NULL COMMENT '虚拟客户画像(JSON格式)',
    `session_status` VARCHAR(20) NOT NULL DEFAULT 'ongoing' COMMENT '会话状态(ongoing-进行中,completed-已完成,abandoned-已放弃)',
    `start_time` DATETIME NOT NULL COMMENT '开始时间',
    `end_time` DATETIME DEFAULT NULL COMMENT '结束时间',
    `duration_seconds` INT DEFAULT NULL COMMENT '持续时长(秒)',
    `overall_score` DECIMAL(5, 2) DEFAULT NULL COMMENT '总分',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_session_code` (`session_code`, `tenant_id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_scene_id` (`scene_id`),
    INDEX `idx_session_status` (`session_status`),
    INDEX `idx_start_time` (`start_time`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-对练会话';

-- 对话记录表
CREATE TABLE `aicrm_practice_dialogue` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `session_id` BIGINT NOT NULL COMMENT '会话ID',
    `speaker_role` VARCHAR(20) NOT NULL COMMENT '发言角色(user-用户,virtual_customer-虚拟客户)',
    `content` TEXT NOT NULL COMMENT '对话内容',
    `emotion` JSON DEFAULT NULL COMMENT '情绪(JSON格式)',
    `dialogue_time` DATETIME NOT NULL COMMENT '对话时间',
    `sequence` INT NOT NULL COMMENT '对话序号',
    `compliance_check_result` JSON DEFAULT NULL COMMENT '合规检查结果(JSON格式)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_session_id` (`session_id`),
    INDEX `idx_dialogue_time` (`dialogue_time`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-对话记录';

-- =============================================
-- 5. 评估与打分模块
-- =============================================

-- 评估维度表
CREATE TABLE `aicrm_practice_evaluation_dimension` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dimension_code` VARCHAR(50) NOT NULL COMMENT '维度编码',
    `dimension_name` VARCHAR(100) NOT NULL COMMENT '维度名称',
    `description` TEXT COMMENT '描述',
    `weight` INT NOT NULL DEFAULT 1 COMMENT '权重',
    `sort` INT DEFAULT 0 COMMENT '排序',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_dimension_code` (`dimension_code`, `tenant_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-评估维度';

-- 评估指标表
CREATE TABLE `aicrm_practice_evaluation_metric` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `dimension_id` BIGINT NOT NULL COMMENT '维度ID',
    `metric_code` VARCHAR(50) NOT NULL COMMENT '指标编码',
    `metric_name` VARCHAR(100) NOT NULL COMMENT '指标名称',
    `description` TEXT COMMENT '描述',
    `weight` INT NOT NULL DEFAULT 1 COMMENT '权重',
    `max_score` DECIMAL(5, 2) NOT NULL DEFAULT 100.00 COMMENT '最高分',
    `scoring_criteria` JSON DEFAULT NULL COMMENT '评分标准(JSON格式)',
    `sort` INT DEFAULT 0 COMMENT '排序',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_metric_code` (`metric_code`, `tenant_id`),
    INDEX `idx_dimension_id` (`dimension_id`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-评估指标';

-- 会话评估表
CREATE TABLE `aicrm_practice_session_evaluation` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `session_id` BIGINT NOT NULL COMMENT '会话ID',
    `total_score` DECIMAL(5, 2) NOT NULL COMMENT '总分',
    `dimension_scores` JSON DEFAULT NULL COMMENT '维度得分(JSON格式)',
    `radar_chart_data` JSON DEFAULT NULL COMMENT '雷达图数据(JSON格式)',
    `strengths` JSON DEFAULT NULL COMMENT '优势(JSON数组)',
    `weaknesses` JSON DEFAULT NULL COMMENT '弱点(JSON数组)',
    `compliance_issues` JSON DEFAULT NULL COMMENT '合规问题(JSON数组)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_session_id` (`session_id`, `tenant_id`),
    INDEX `idx_total_score` (`total_score`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-会话评估';

-- 评估明细表
CREATE TABLE `aicrm_practice_evaluation_detail` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `evaluation_id` BIGINT NOT NULL COMMENT '评估ID',
    `dimension_id` BIGINT NOT NULL COMMENT '维度ID',
    `metric_id` BIGINT NOT NULL COMMENT '指标ID',
    `score` DECIMAL(5, 2) NOT NULL COMMENT '得分',
    `feedback` TEXT COMMENT '反馈',
    `evidence` JSON DEFAULT NULL COMMENT '证据(JSON格式)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_evaluation_id` (`evaluation_id`),
    INDEX `idx_dimension_id` (`dimension_id`),
    INDEX `idx_metric_id` (`metric_id`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-评估明细';

-- 改进建议表
CREATE TABLE `aicrm_practice_improvement_suggestion` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `session_id` BIGINT NOT NULL COMMENT '会话ID',
    `evaluation_id` BIGINT NOT NULL COMMENT '评估ID',
    `suggestion_type` VARCHAR(50) NOT NULL COMMENT '建议类型',
    `priority` VARCHAR(20) DEFAULT 'medium' COMMENT '优先级(low-低,medium-中,high-高)',
    `content` TEXT NOT NULL COMMENT '建议内容',
    `related_case` JSON DEFAULT NULL COMMENT '关联案例(JSON格式)',
    `related_resources` JSON DEFAULT NULL COMMENT '关联资源(JSON数组)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_session_id` (`session_id`),
    INDEX `idx_evaluation_id` (`evaluation_id`),
    INDEX `idx_priority` (`priority`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-改进建议';

-- 学习资源表
CREATE TABLE `aicrm_practice_learning_resource` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `resource_type` VARCHAR(20) NOT NULL COMMENT '资源类型(course-课程,template-模板,article-文章,video-视频)',
    `title` VARCHAR(200) NOT NULL COMMENT '标题',
    `description` TEXT COMMENT '描述',
    `content` LONGTEXT COMMENT '内容',
    `url` VARCHAR(500) DEFAULT NULL COMMENT '链接',
    `tags` JSON DEFAULT NULL COMMENT '标签(JSON数组)',
    `related_skills` JSON DEFAULT NULL COMMENT '关联技能(JSON数组)',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_resource_type` (`resource_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-学习资源';

-- 成长轨迹表
CREATE TABLE `aicrm_practice_growth_track` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id` BIGINT NOT NULL COMMENT '用户ID',
    `session_id` BIGINT NOT NULL COMMENT '会话ID',
    `score` DECIMAL(5, 2) NOT NULL COMMENT '得分',
    `dimension_scores` JSON DEFAULT NULL COMMENT '各维度得分(JSON格式)',
    `high_frequency_issues` JSON DEFAULT NULL COMMENT '高频问题类型(JSON数组)',
    `practice_date` DATE NOT NULL COMMENT '练习日期',
    `period_type` VARCHAR(20) DEFAULT 'daily' COMMENT '周期类型(daily-每日,weekly-每周,monthly-每月,quarterly-每季度)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_session_id` (`session_id`),
    INDEX `idx_practice_date` (`practice_date`),
    INDEX `idx_period_type` (`period_type`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-成长轨迹';

-- =============================================
-- 6. 工作流编排模块
-- =============================================

-- 工作流配置表
CREATE TABLE `aicrm_practice_workflow_config` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `workflow_name` VARCHAR(100) NOT NULL COMMENT '工作流名称',
    `workflow_type` VARCHAR(50) NOT NULL COMMENT '工作流类型',
    `description` TEXT COMMENT '描述',
    `config_content` JSON DEFAULT NULL COMMENT '配置内容(JSON格式)',
    `hiagent_workflow_id` VARCHAR(100) DEFAULT NULL COMMENT 'HiAgent工作流ID',
    `is_editable` TINYINT DEFAULT 1 COMMENT '是否可编辑(0-否,1-是)',
    `status` TINYINT NOT NULL DEFAULT 1 COMMENT '状态(0-禁用,1-启用)',
    `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_workflow_type` (`workflow_type`),
    INDEX `idx_status` (`status`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-工作流配置';

-- =============================================
-- 7. 系统管理模块
-- =============================================

-- 操作日志表
CREATE TABLE `aicrm_practice_operation_log` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键',
    `module` VARCHAR(50) NOT NULL COMMENT '模块',
    `operation_type` VARCHAR(50) NOT NULL COMMENT '操作类型',
    `operation_desc` VARCHAR(200) DEFAULT NULL COMMENT '操作描述',
    `user_id` BIGINT NOT NULL COMMENT '操作用户ID',
    `username` VARCHAR(64) DEFAULT NULL COMMENT '操作用户名',
    `request_params` JSON DEFAULT NULL COMMENT '请求参数(JSON格式)',
    `response_result` JSON DEFAULT NULL COMMENT '响应结果(JSON格式)',
    `ip_address` VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
    `user_agent` VARCHAR(500) DEFAULT NULL COMMENT '用户代理',
    `operation_time` DATETIME NOT NULL COMMENT '操作时间',
    `execution_time` INT DEFAULT NULL COMMENT '执行时长(毫秒)',
    `success` TINYINT NOT NULL DEFAULT 1 COMMENT '是否成功(0-失败,1-成功)',
    `error_msg` TEXT COMMENT '错误信息',
    `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_module` (`module`),
    INDEX `idx_operation_type` (`operation_type`),
    INDEX `idx_operation_time` (`operation_time`),
    INDEX `idx_success` (`success`),
    INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='智能陪练-操作日志';
