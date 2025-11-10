-- =====================================================
-- 智能陪练系统 - 数据库表结构设计
-- =====================================================
-- 模块：智能陪练 (Intelligent Practice)
-- 版本：1.0
-- 创建日期：2025-11-06
-- 说明：包含销售案例、技巧、虚拟客户、剧本、课程、对话等核心表
-- =====================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- =====================================================
-- 1. 销售案例表 (crm_practice_case)
-- =====================================================
-- 功能说明：存储销售案例库，作为陪练剧本的参考素材
-- 关系：1个案例可被多个剧本引用 (1:N)
-- =====================================================

DROP TABLE IF EXISTS `crm_practice_case`;
CREATE TABLE `crm_practice_case` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '案例ID',

  -- 基本信息
  `title` varchar(200) NOT NULL COMMENT '案例标题',
  `content` text NOT NULL COMMENT '案例详细内容（正文）',

  -- 分类信息
  `tags` varchar(500) DEFAULT NULL COMMENT '标签（多个标签逗号分隔）',

  -- 审计字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`) COMMENT '租户隔离'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM智能陪练-销售案例表';

-- 索引说明：
-- 1. idx_tenant_id: 多租户数据隔离


-- =====================================================
-- 2. 销售技巧表 (crm_practice_skill)
-- =====================================================
-- 功能说明：存储销售技巧库，用于陪练中的技巧训练
-- 关系：1个技巧可被多个剧本引用 (1:N)
-- =====================================================

DROP TABLE IF EXISTS `crm_practice_skill`;
CREATE TABLE `crm_practice_skill` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '技巧ID',

  -- 基本信息
  `name` varchar(200) NOT NULL COMMENT '技巧名称',

  -- 分类信息
  `category` varchar(100) NOT NULL COMMENT '技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧',

  -- 实践内容
  `script_template` text DEFAULT NULL COMMENT '话术模板',

  -- 关联信息
  `compliance_rules` text DEFAULT NULL COMMENT '合规规则说明',
  `related_products` text DEFAULT NULL COMMENT '关联产品知识',

  -- 审计字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_category` (`category`) COMMENT '按分类查询',
  KEY `idx_tenant_id` (`tenant_id`) COMMENT '租户隔离'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM智能陪练-销售技巧表';

-- 索引说明：
-- 1. idx_category: 查询特定分类的技巧（套路/技巧）
-- 2. idx_tenant_id: 多租户数据隔离


-- =====================================================
-- 3. 虚拟客户表 (crm_practice_virtual_customer)
-- =====================================================
-- 功能说明：存储虚拟客户角色，用于模拟真实销售场景
-- 关系：1个虚拟客户可被多个剧本使用 (0:N)
--       1个用户可创建多个虚拟客户 (1:N)
-- =====================================================

DROP TABLE IF EXISTS `crm_practice_virtual_customer`;
CREATE TABLE `crm_practice_virtual_customer` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '虚拟客户ID',

  -- 基本信息
  `name` varchar(100) NOT NULL COMMENT '客户姓名',
  `gender` varchar(10) DEFAULT NULL COMMENT '性别：字典 aicrm_gender',
  `age` int DEFAULT NULL COMMENT '年龄',
  `occupation` varchar(100) DEFAULT NULL COMMENT '职业',
  `industry` varchar(100) DEFAULT NULL COMMENT '所属行业',

  -- 性格特征
  `personality_type` varchar(50) DEFAULT NULL COMMENT '性格类型：字典 aicrm_personality_type（如理性型/感性型）',
  `risk_preference` varchar(100) DEFAULT NULL COMMENT '风险偏好',

  -- 背景需求
  `memo` text DEFAULT NULL COMMENT '自定义参数（JSON格式）',

  -- 审计字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者（用户ID）',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_personality_type` (`personality_type`) COMMENT '按性格类型查询',
  KEY `idx_industry` (`industry`) COMMENT '按行业查询',
  KEY `idx_creator` (`creator`) COMMENT '查询某用户创建的虚拟客户',
  KEY `idx_tenant_id` (`tenant_id`) COMMENT '租户隔离'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM智能陪练-虚拟客户表';

-- 索引说明：
-- 1. idx_personality_type: 查询特定性格类型的虚拟客户
-- 2. idx_industry: 查询特定行业的虚拟客户
-- 3. idx_creator: 查询某个用户创建的所有虚拟客户
-- 4. idx_tenant_id: 多租户数据隔离


-- =====================================================
-- 4. 培训文件表 (crm_practice_material)
-- =====================================================
-- 功能说明：存储培训相关的文件资料（PDF、视频、文档等）
-- 关系：1个文件可被多个剧本引用 (1:N)
-- =====================================================

DROP TABLE IF EXISTS `crm_practice_material`;
CREATE TABLE `crm_practice_material` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '文件ID',

  -- 基本信息
  `name` varchar(200) NOT NULL COMMENT '文件名称',
  `file_url` varchar(500) NOT NULL COMMENT '文件URL',
  `file_size` bigint DEFAULT NULL COMMENT '文件大小（字节）',

  -- 分类信息
  `content` text DEFAULT NULL COMMENT '文件内容(纯文本)',
  `content_rich` text DEFAULT NULL COMMENT '文件内容(富文本)',

  -- 审计字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_tenant_id` (`tenant_id`) COMMENT '租户隔离'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM智能陪练-培训文件表';

-- 索引说明：
-- 1. idx_tenant_id: 多租户数据隔离


-- =====================================================
-- 5. 陪练剧本表 (crm_practice_script) - 核心表（支持版本机制）
-- =====================================================
-- 功能说明：陪练剧本配置，关联案例、技巧、客户、文件
-- 版本机制：同一个剧本可以存在多个版本，通过 script_no 关联
-- 关系：多对一关联案例、技巧、虚拟客户、培训文件
--       一对多关联陪练课程 (1:N)
-- =====================================================

DROP TABLE IF EXISTS `crm_practice_script`;
CREATE TABLE `crm_practice_script` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '剧本版本ID（主键）',

  -- 版本控制
  `script_no` varchar(50) NOT NULL COMMENT '剧本编号（标识同一个剧本的不同版本）',
  `version` varchar(20) NOT NULL COMMENT '版本号（如 v1.0, v1.1, v2.0）',
  `is_latest` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否最新版本',
  `parent_version_id` bigint DEFAULT NULL COMMENT '父版本ID（用于追溯版本历史）',
  `version_description` varchar(500) DEFAULT NULL COMMENT '版本说明（本次修改的内容）',
  `status` varchar(20) NOT NULL DEFAULT 'draft' COMMENT '版本状态：字典 aicrm_script_status',

  -- 基本信息
  `name` varchar(200) NOT NULL COMMENT '剧本名称',
  `description` varchar(500) DEFAULT NULL COMMENT '剧本描述',
  `difficulty_level` varchar(20) NOT NULL COMMENT '难度等级：字典 aicrm_difficulty_level',
  `marketing_step` varchar(20) NOT NULL COMMENT '营销环节：字典 aicrm_marketing_step',

  -- 关联信息
  `case_id` bigint DEFAULT NULL COMMENT '关联销售案例ID',
  `skill_id` bigint DEFAULT NULL COMMENT '关联销售技巧ID',
  `virtual_customer_id` bigint DEFAULT NULL COMMENT '关联虚拟客户ID',
  `material_ids` varchar(500) DEFAULT NULL COMMENT '关联培训文件ID列表（多个ID逗号分隔）',

  -- 剧本配置
  `content` text DEFAULT NULL COMMENT '剧本内容（AI生成）',
  `content_edit` text DEFAULT NULL COMMENT '手工调整内容（用户编辑）',

  -- 统计信息
  `usage_count` int DEFAULT 0 COMMENT '使用次数（该版本被使用的次数）',

  -- 审计字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_script_version` (`script_no`, `version`, `tenant_id`) COMMENT '剧本编号+版本号+租户唯一',
  KEY `idx_script_no` (`script_no`) COMMENT '查询某个剧本的所有版本',
  KEY `idx_is_latest` (`is_latest`) COMMENT '查询最新版本',
  KEY `idx_parent_version_id` (`parent_version_id`) COMMENT '查询子版本',
  KEY `idx_case_id` (`case_id`) COMMENT '查询使用某个案例的剧本',
  KEY `idx_skill_id` (`skill_id`) COMMENT '查询使用某个技巧的剧本',
  KEY `idx_virtual_customer_id` (`virtual_customer_id`) COMMENT '查询使用某个虚拟客户的剧本',
  KEY `idx_difficulty_level` (`difficulty_level`) COMMENT '按难度等级查询',
  KEY `idx_marketing_step` (`marketing_step`) COMMENT '按营销环节查询',
  KEY `idx_status` (`status`) COMMENT '按状态查询',
  KEY `idx_tenant_id` (`tenant_id`) COMMENT '租户隔离',
  CONSTRAINT `fk_script_case` FOREIGN KEY (`case_id`) REFERENCES `crm_practice_case` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_script_skill` FOREIGN KEY (`skill_id`) REFERENCES `crm_practice_skill` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_script_customer` FOREIGN KEY (`virtual_customer_id`) REFERENCES `crm_practice_virtual_customer` (`id`) ON DELETE SET NULL,
  CONSTRAINT `fk_script_parent_version` FOREIGN KEY (`parent_version_id`) REFERENCES `crm_practice_script` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM智能陪练-陪练剧本表（支持版本控制）';

-- 索引说明：
-- 1. uk_script_version: 确保同一租户下剧本编号+版本号唯一
-- 2. idx_script_no: 查询某个剧本的所有版本
-- 3. idx_is_latest: 快速查询最新版本的剧本
-- 4. idx_parent_version_id: 追溯版本历史
-- 5. idx_case_id: 查询使用某个销售案例的所有剧本
-- 6. idx_skill_id: 查询使用某个销售技巧的所有剧本
-- 7. idx_virtual_customer_id: 查询使用某个虚拟客户的所有剧本
-- 8. idx_difficulty_level: 按难度筛选剧本
-- 9. idx_marketing_step: 按营销环节筛选剧本
-- 10. idx_status: 按状态查询
-- 11. idx_tenant_id: 多租户数据隔离
-- 外键约束：
-- 1. fk_script_case: 关联销售案例（删除案例时设置为NULL）
-- 2. fk_script_skill: 关联销售技巧（删除技巧时设置为NULL）
-- 3. fk_script_customer: 关联虚拟客户（删除客户时设置为NULL）
-- 4. fk_script_parent_version: 关联父版本（删除父版本时设置为NULL）

-- 版本管理说明：
-- 1. 创建新剧本时，自动生成 script_no（如 SCR202511060001），版本号为 v1.0，is_latest=1
-- 2. 修改现有版本时，直接更新该版本记录，版本号不变
-- 3. 保存为新版本时：
--    a. 将旧版本的 is_latest 设置为 0
--    b. 创建新版本记录，parent_version_id 指向旧版本，is_latest=1
--    c. 版本号递增（如 v1.0 -> v1.1 或 v1.0 -> v2.0）
-- 4. 课程表关联具体的版本ID（script_id），保证课程使用的剧本版本固定
-- 5. 删除剧本时，只软删除（deleted=1），不物理删除，保留版本历史


-- =====================================================
-- 6. 陪练课程表 (crm_practice_course)
-- =====================================================
-- 功能说明：基于陪练剧本创建的课程，可供多个用户参与
-- 关系：多对一关联陪练剧本 (N:1)
--       一对多关联用户陪练记录 (1:N)
-- =====================================================

DROP TABLE IF EXISTS `crm_practice_course`;
CREATE TABLE `crm_practice_course` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '课程ID',

  -- 基本信息
  `name` varchar(200) NOT NULL COMMENT '课程名称',
  `description` varchar(500) DEFAULT NULL COMMENT '课程描述',

  -- 关联信息
  `script_id` bigint NOT NULL COMMENT '关联陪练剧本ID',

  -- 课程配置
  `standard` bigint DEFAULT NULL COMMENT '课程类型 1.标准.0.个人',
  `hard` bigint DEFAULT NULL COMMENT '课程复杂度 1.复杂.0.简单',
  `status` varchar(20) NOT NULL DEFAULT 'active' COMMENT '课程状态：字典 aicrm_course_status',

  -- 审计字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_script_id` (`script_id`) COMMENT '查询某个剧本的所有课程',
  KEY `idx_status` (`status`) COMMENT '按状态查询',
  KEY `idx_tenant_id` (`tenant_id`) COMMENT '租户隔离',
  CONSTRAINT `fk_course_script` FOREIGN KEY (`script_id`) REFERENCES `crm_practice_script` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM智能陪练-陪练课程表';

-- 索引说明：
-- 1. idx_script_id: 查询使用某个剧本的所有课程
-- 2. idx_status: 查询不同状态的课程
-- 外键约束：
-- 1. fk_course_script: 关联剧本（删除剧本时级联删除课程）


-- =====================================================
-- 7. 用户陪练记录表 (crm_practice_user_record)
-- =====================================================
-- 功能说明：记录用户参与陪练课程的详细信息
-- 关系：多对一关联陪练课程 (N:1)
--       多对一关联用户 (N:1)
--       一对多关联陪练对话 (1:N)
-- =====================================================

DROP TABLE IF EXISTS `crm_practice_user_record`;
CREATE TABLE `crm_practice_user_record` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '记录ID',

  -- 关联信息
  `course_id` bigint NOT NULL COMMENT '关联陪练课程ID',
  `user_id` bigint NOT NULL COMMENT '参与用户ID',
  `vcustomer_id` bigint NOT NULL COMMENT '虚拟用户ID',

  -- 记录信息
  `record_no` varchar(50) NOT NULL COMMENT '记录编号（唯一）',
  `status` varchar(20) NOT NULL DEFAULT 'in_progress' COMMENT '记录状态：字典 aicrm_record_status',
  `start_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `duration` int DEFAULT NULL COMMENT '实际时长（分钟）',

  -- 评估结果
  `total_score` decimal(10, 2) DEFAULT NULL COMMENT '总评分',
  `dimension_scores` text DEFAULT NULL COMMENT '各维度得分（JSON格式）',
  `completion_rate` decimal(10, 2) DEFAULT NULL COMMENT '完成进度（%）',

  -- AI 反馈
  `ai_summary` text DEFAULT NULL COMMENT 'AI 总结评价',
  `strengths` text DEFAULT NULL COMMENT '优点总结（JSON格式）',
  `weaknesses` text DEFAULT NULL COMMENT '待改进点（JSON格式）',
  `recommendations` text DEFAULT NULL COMMENT '改进建议（JSON格式）',

  -- 审计字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_record_no` (`record_no`) COMMENT '记录编号唯一',
  KEY `idx_course_id` (`course_id`) COMMENT '查询某个课程的所有记录',
  KEY `idx_user_id` (`user_id`) COMMENT '查询某个用户的所有记录',
  KEY `idx_status` (`status`) COMMENT '按状态查询',
  KEY `idx_start_time` (`start_time`) COMMENT '按开始时间查询',
  KEY `idx_tenant_id` (`tenant_id`) COMMENT '租户隔离',
  CONSTRAINT `fk_record_course` FOREIGN KEY (`course_id`) REFERENCES `crm_practice_course` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM智能陪练-用户陪练记录表';

-- 索引说明：
-- 1. uk_record_no: 记录编号全局唯一
-- 2. idx_course_id: 查询某个课程的所有参与记录
-- 3. idx_user_id: 查询某个用户的所有陪练记录
-- 4. idx_status: 查询不同状态的记录
-- 5. idx_start_time: 按时间范围查询记录
-- 外键约束：
-- 1. fk_record_course: 关联课程（删除课程时级联删除记录）


-- =====================================================
-- 8. 陪练对话表 (crm_practice_conversation)
-- =====================================================
-- 功能说明：记录陪练过程中的详细对话内容和AI评价
-- 关系：多对一关联用户陪练记录 (N:1)
-- =====================================================

DROP TABLE IF EXISTS `crm_practice_conversation`;
CREATE TABLE `crm_practice_conversation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '对话ID',

  -- 关联信息
  `record_id` bigint NOT NULL COMMENT '关联用户陪练记录ID',

  -- 对话基本信息
  `sequence_no` int NOT NULL COMMENT '对话序号（从1开始）',
  `role` varchar(20) NOT NULL COMMENT '发言角色：字典 aicrm_conversation_role（student/virtual_customer/system）',
  `message_content` text NOT NULL COMMENT '消息内容',
  `message_time` datetime NOT NULL COMMENT '消息时间',

  -- AI 评分反馈
  `instant_score` decimal(10, 2) DEFAULT NULL COMMENT '即时评分（针对学员发言）',
  `speech_analysis` text DEFAULT NULL COMMENT '话术分析（JSON格式）',
  `skill_evaluation` text DEFAULT NULL COMMENT '技巧运用评价（JSON格式）',

  -- 情绪与意图
  `emotion_tag` varchar(50) DEFAULT NULL COMMENT '对话情绪标签：字典 aicrm_emotion_tag',
  `sales_intent` varchar(100) DEFAULT NULL COMMENT '销售意图识别：字典 aicrm_sales_intent',
  `customer_reaction` varchar(100) DEFAULT NULL COMMENT '客户反应：字典 aicrm_customer_reaction',

  -- 改进建议
  `improvement_suggestions` text DEFAULT NULL COMMENT 'AI改进建议（JSON格式）',
  `recommended_scripts` text DEFAULT NULL COMMENT '推荐话术（JSON格式）',

  -- 审计字段
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`),
  KEY `idx_record_id_sequence` (`record_id`, `sequence_no`) COMMENT '按记录+序号查询对话',
  KEY `idx_message_time` (`message_time`) COMMENT '按时间查询',
  KEY `idx_role` (`role`) COMMENT '按角色查询',
  KEY `idx_tenant_id` (`tenant_id`) COMMENT '租户隔离',
  CONSTRAINT `fk_conversation_record` FOREIGN KEY (`record_id`) REFERENCES `crm_practice_user_record` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='CRM智能陪练-陪练对话表';

-- 索引说明：
-- 1. idx_record_id_sequence: 查询某个记录的所有对话（按序号排序）
-- 2. idx_message_time: 按时间范围查询对话
-- 3. idx_role: 查询特定角色的所有发言
-- 外键约束：
-- 1. fk_conversation_record: 关联用户陪练记录（删除记录时级联删除对话）


-- =====================================================
-- 表关系总结
-- =====================================================
-- 1. crm_practice_case (1) → crm_practice_script (N)
-- 2. crm_practice_skill (1) → crm_practice_script (N)
-- 3. crm_practice_virtual_customer (0) → crm_practice_script (N)
-- 4. crm_practice_material (1) → crm_practice_script (N)
-- 5. crm_practice_script (1) → crm_practice_course (N)
-- 6. crm_practice_course (1) → crm_practice_user_record (N)
-- 7. 用户表 (1) → crm_practice_user_record (N)
-- 8. crm_practice_user_record (1) → crm_practice_conversation (N)
-- 9. 用户表 (1) → crm_practice_virtual_customer (N)
-- =====================================================

SET FOREIGN_KEY_CHECKS = 1;
