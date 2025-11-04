-- ----------------------------
-- BPM 工作流模块数据库表结构
-- ----------------------------

-- ----------------------------
-- 1. bpm_category - BPM 流程分类表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `bpm_category` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '分类编号',
    `name` varchar(255) NOT NULL COMMENT '分类名',
    `code` varchar(64) NOT NULL COMMENT '分类标志',
    `description` varchar(500) DEFAULT NULL COMMENT '分类描述',
    `status` tinyint NOT NULL COMMENT '分类状态（0=正常，1=禁用）',
    `sort` int NOT NULL DEFAULT 0 COMMENT '分类排序',
    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_code` (`code`) USING BTREE COMMENT '分类标志唯一索引',
    KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='BPM 流程分类表';

-- ----------------------------
-- 2. bpm_form - BPM 工作流表单定义表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `bpm_form` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name` varchar(64) NOT NULL COMMENT '表单名',
    `status` tinyint NOT NULL COMMENT '状态（0=正常，1=禁用）',
    `conf` text NOT NULL COMMENT '表单的配置（form-generator 生成的 JSON）',
    `fields` json DEFAULT NULL COMMENT '表单项的数组（form-generator 生成的 fields JSON）',
    `remark` varchar(500) DEFAULT NULL COMMENT '备注',
    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='BPM 工作流表单定义表';

-- ----------------------------
-- 3. bpm_process_definition_info - BPM 流程定义扩展信息表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `bpm_process_definition_info` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
    `process_definition_id` varchar(64) NOT NULL COMMENT '流程定义的编号（关联 Flowable ProcessDefinition.id）',
    `model_id` varchar(64) NOT NULL COMMENT '流程模型的编号（关联 Flowable Model.id）',
    `model_type` tinyint NOT NULL COMMENT '流程模型的类型（10=BPMN，20=SIMPLE）',
    `category` varchar(64) NOT NULL COMMENT '流程分类的编码（关联 bpm_category.code）',
    `icon` varchar(255) DEFAULT NULL COMMENT '图标',
    `description` varchar(500) DEFAULT NULL COMMENT '描述',
    `form_type` tinyint NOT NULL COMMENT '表单类型（10=正常表单，20=自定义表单）',
    `form_id` bigint DEFAULT NULL COMMENT '动态表单编号（关联 bpm_form.id，formType=10 时有效）',
    `form_conf` text DEFAULT NULL COMMENT '表单的配置（冗余 bpm_form.conf，formType=10 时有效）',
    `form_fields` json DEFAULT NULL COMMENT '表单项的数组（冗余 bpm_form.fields，formType=10 时有效）',
    `form_custom_create_path` varchar(255) DEFAULT NULL COMMENT '自定义表单的提交路径（formType=20 时有效）',
    `form_custom_view_path` varchar(255) DEFAULT NULL COMMENT '自定义表单的查看路径（formType=20 时有效）',
    `simple_model` text DEFAULT NULL COMMENT 'SIMPLE 设计器模型数据（JSON 格式）',
    `visible` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否可见（在发起流程列表中）',
    `sort` bigint NOT NULL DEFAULT 0 COMMENT '排序值',
    `start_user_ids` varchar(2000) DEFAULT NULL COMMENT '可发起用户编号数组（JSON 格式，逗号分隔）',
    `start_dept_ids` varchar(2000) DEFAULT NULL COMMENT '可发起部门编号数组（JSON 格式，逗号分隔）',
    `manager_user_ids` varchar(2000) DEFAULT NULL COMMENT '可管理用户编号数组（JSON 格式，逗号分隔）',
    `allow_cancel_running_process` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否允许撤销审批中的申请',
    `allow_withdraw_task` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否允许审批人撤回任务',
    `process_id_rule` json DEFAULT NULL COMMENT '流程 ID 规则（JSON 格式）',
    `auto_approval_type` tinyint DEFAULT NULL COMMENT '自动审批类型（0=禁用，1=自动通过，2=自动拒绝）',
    `title_setting` json DEFAULT NULL COMMENT '标题设置（JSON 格式）',
    `summary_setting` json DEFAULT NULL COMMENT '摘要设置（JSON 格式）',
    `process_before_trigger_setting` json DEFAULT NULL COMMENT '流程前置通知设置（JSON 格式）',
    `process_after_trigger_setting` json DEFAULT NULL COMMENT '流程后置通知设置（JSON 格式）',
    `task_before_trigger_setting` json DEFAULT NULL COMMENT '任务前置通知设置（JSON 格式）',
    `task_after_trigger_setting` json DEFAULT NULL COMMENT '任务后置通知设置（JSON 格式）',
    `print_template_setting` json DEFAULT NULL COMMENT '自定义打印模板设置（JSON 格式）',
    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_process_definition_id` (`process_definition_id`) USING BTREE,
    KEY `idx_model_id` (`model_id`) USING BTREE,
    KEY `idx_category` (`category`) USING BTREE,
    KEY `idx_visible` (`visible`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='BPM 流程定义扩展信息表';

-- ----------------------------
-- 4. bpm_process_expression - BPM 流程表达式表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `bpm_process_expression` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
    `name` varchar(64) NOT NULL COMMENT '表达式名字',
    `status` tinyint NOT NULL COMMENT '表达式状态（0=正常，1=禁用）',
    `expression` varchar(1000) NOT NULL COMMENT '表达式',
    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='BPM 流程表达式表';

-- ----------------------------
-- 5. bpm_process_listener - BPM 流程监听器表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `bpm_process_listener` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键 ID，自增',
    `name` varchar(64) NOT NULL COMMENT '监听器名字',
    `status` tinyint NOT NULL COMMENT '状态（0=正常，1=禁用）',
    `type` varchar(20) NOT NULL COMMENT '监听类型（execution=执行监听器，task=任务监听器）',
    `event` varchar(20) NOT NULL COMMENT '监听事件（execution:start/end，task:create/assignment/complete/delete/update/timeout）',
    `value_type` varchar(30) NOT NULL COMMENT '值类型（class=Java类，delegateExpression=委托表达式，expression=表达式）',
    `value` varchar(500) NOT NULL COMMENT '值',
    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_status` (`status`) USING BTREE,
    KEY `idx_type` (`type`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='BPM 流程监听器表';

-- ----------------------------
-- 6. bpm_user_group - BPM 用户组表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `bpm_user_group` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号，自增',
    `name` varchar(64) NOT NULL COMMENT '组名',
    `description` varchar(500) DEFAULT NULL COMMENT '描述',
    `status` tinyint NOT NULL COMMENT '状态（0=正常，1=禁用）',
    `user_ids` json DEFAULT NULL COMMENT '成员用户编号数组（JSON 格式）',
    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_status` (`status`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='BPM 用户组表';

-- ----------------------------
-- 7. bpm_oa_leave - OA 请假申请表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `bpm_oa_leave` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '请假表单主键',
    `user_id` bigint NOT NULL COMMENT '申请人的用户编号（关联 system_users.id）',
    `type` varchar(32) NOT NULL COMMENT '请假类型',
    `reason` varchar(500) NOT NULL COMMENT '原因',
    `start_time` datetime NOT NULL COMMENT '开始时间',
    `end_time` datetime NOT NULL COMMENT '结束时间',
    `day` bigint NOT NULL COMMENT '请假天数',
    `status` tinyint NOT NULL COMMENT '审批结果（1=审批中，2=审批通过，3=审批不通过，4=已取消）',
    `process_instance_id` varchar(64) NOT NULL COMMENT '对应的流程实例编号（关联 Flowable ProcessInstance.id）',
    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_status` (`status`) USING BTREE,
    KEY `idx_process_instance_id` (`process_instance_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='OA 请假申请表';

-- ----------------------------
-- 8. bpm_process_instance_copy - 流程抄送表
-- ----------------------------
CREATE TABLE IF NOT EXISTS `bpm_process_instance_copy` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '编号',
    `start_user_id` bigint NOT NULL COMMENT '发起人 ID（冗余 ProcessInstance.startUserId）',
    `process_instance_name` varchar(255) NOT NULL COMMENT '流程名（冗余 ProcessInstance.name）',
    `process_instance_id` varchar(64) NOT NULL COMMENT '流程实例的编号（关联 Flowable ProcessInstance.id）',
    `process_definition_id` varchar(64) NOT NULL COMMENT '流程实例的流程定义编号（关联 Flowable ProcessInstance.processDefinitionId）',
    `category` varchar(64) NOT NULL COMMENT '流程分类（冗余 ProcessInstance.category）',
    `activity_id` varchar(64) NOT NULL COMMENT '流程活动的编号（BPMN XML 节点编号）',
    `activity_name` varchar(255) DEFAULT NULL COMMENT '流程活动的名字',
    `task_id` varchar(64) DEFAULT NULL COMMENT '流程活动的编号（关联 Flowable HistoricTaskInstance.id）',
    `user_id` bigint NOT NULL COMMENT '用户编号（被抄送的用户编号，关联 system_users.id）',
    `reason` varchar(500) DEFAULT NULL COMMENT '抄送意见',
    `creator` varchar(64) DEFAULT '' COMMENT '创建者',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updater` varchar(64) DEFAULT '' COMMENT '更新者',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
    `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `idx_user_id` (`user_id`) USING BTREE,
    KEY `idx_process_instance_id` (`process_instance_id`) USING BTREE,
    KEY `idx_task_id` (`task_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='流程抄送表';
