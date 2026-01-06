-- ============================================================
-- 营销活动任务下发表
-- 说明: 为客户经理下发营销活动推广任务
-- 创建时间: 2026-01-05
-- ============================================================

-- ==================== 营销活动任务下发表 ====================
DROP TABLE IF EXISTS `aicrm_marketing_task_assignment`;
CREATE TABLE `aicrm_marketing_task_assignment` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '任务下发主键',

  -- ==================== 基本信息 ====================
  `task_name` varchar(200) NOT NULL COMMENT '任务名称',
  `marketing_activity_id` bigint NOT NULL COMMENT '关联营销活动ID（关联 crm_customer_marketing_activity 表）',

  -- ==================== 时间信息 ====================
  `start_time` datetime NOT NULL COMMENT '任务开始时间',
  `end_time` datetime NOT NULL COMMENT '任务结束时间',

  -- ==================== 任务目标 ====================
  `target_type` varchar(20) NOT NULL COMMENT '任务目标类型（字典: aicrm_task_target_type，view=浏览量，promotion=推广量）',
  `target_value` int NOT NULL COMMENT '目标值',

  -- ==================== 任务内容 ====================
  `task_script` text COMMENT '任务话术',
  `poster_url` varchar(500) COMMENT '推广海报URL',

  -- ==================== 派发对象 ====================
  `assigned_user_ids` varchar(2000) NOT NULL COMMENT '任务派发对象（存储多个用户ID，逗号分隔，例如：1,2,3）',
  `assigned_user_count` int NOT NULL DEFAULT 0 COMMENT '派发人数（冗余字段，便于统计）',

  -- ==================== 状态信息 ====================
  `status` varchar(20) NOT NULL DEFAULT 'active' COMMENT '任务状态（字典: aicrm_task_assignment_status，active=进行中，completed=已完成，cancelled=已取消）',

  -- ==================== 备注信息 ====================
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',

  -- ==================== 审计字段 ====================
  `creator` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` bit(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号',

  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_marketing_activity_id`(`marketing_activity_id` ASC) USING BTREE COMMENT '营销活动ID索引',
  INDEX `idx_status`(`status` ASC) USING BTREE COMMENT '状态索引',
  INDEX `idx_start_time`(`start_time` ASC) USING BTREE COMMENT '开始时间索引',
  INDEX `idx_end_time`(`end_time` ASC) USING BTREE COMMENT '结束时间索引',
  INDEX `idx_tenant_id`(`tenant_id` ASC) USING BTREE COMMENT '租户ID索引'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '营销活动任务下发表';


-- ==================== 字典类型和字典数据 ====================

-- 1. 任务目标类型字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM任务目标类型', 'aicrm_task_target_type', 0, '营销任务的目标类型', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM任务目标类型';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '浏览量', 'view', 'aicrm_task_target_type', 0, 'primary', '任务目标为浏览量', '1', NOW(), '1', NOW(), b'0'),
(2, '推广量', 'promotion', 'aicrm_task_target_type', 0, 'success', '任务目标为推广量', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);

-- 2. 任务下发状态字典
INSERT INTO system_dict_type (id, name, type, status, remark, creator, create_time, updater, update_time, deleted)
VALUES (NULL, 'AICRM任务下发状态', 'aicrm_task_assignment_status', 0, '营销任务下发状态', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE name = 'AICRM任务下发状态';

INSERT INTO system_dict_data (sort, label, value, dict_type, status, color_type, remark, creator, create_time, updater, update_time, deleted) VALUES
(1, '进行中', 'active', 'aicrm_task_assignment_status', 0, 'primary', '任务进行中', '1', NOW(), '1', NOW(), b'0'),
(2, '已完成', 'completed', 'aicrm_task_assignment_status', 0, 'success', '任务已完成', '1', NOW(), '1', NOW(), b'0'),
(3, '已取消', 'cancelled', 'aicrm_task_assignment_status', 0, 'danger', '任务已取消', '1', NOW(), '1', NOW(), b'0')
ON DUPLICATE KEY UPDATE label = VALUES(label);
