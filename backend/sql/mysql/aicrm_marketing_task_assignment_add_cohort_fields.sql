-- 营销活动任务下发表 - 添加客群相关字段
-- Author: Claude
-- Date: 2025-01-07

USE `ynet-iplatform`;

-- 添加派发类型字段
ALTER TABLE aicrm_marketing_task_assignment
ADD COLUMN assignment_type VARCHAR(20) NOT NULL DEFAULT 'customer' COMMENT '派发类型（customer=客户, cohort=客群）' AFTER poster_url;

-- 添加客群ID集合字段
ALTER TABLE aicrm_marketing_task_assignment
ADD COLUMN assigned_cohort_ids VARCHAR(2000) NULL COMMENT '任务派发对象 - 客群ID集合（存储多个客群ID，逗号分隔）' AFTER assigned_user_count;

-- 添加客群派发数量字段
ALTER TABLE aicrm_marketing_task_assignment
ADD COLUMN assigned_cohort_count INT NOT NULL DEFAULT 0 COMMENT '派发数量 - 客群（冗余字段，便于统计）' AFTER assigned_cohort_ids;

-- 修改 assigned_user_ids 字段为可空（因为选择客群时不需要客户）
ALTER TABLE aicrm_marketing_task_assignment
MODIFY COLUMN assigned_user_ids VARCHAR(2000) NULL COMMENT '任务派发对象 - 客户ID集合（存储多个用户ID，逗号分隔）';

-- 为派发类型字段添加索引
ALTER TABLE aicrm_marketing_task_assignment
ADD INDEX idx_assignment_type (assignment_type);
