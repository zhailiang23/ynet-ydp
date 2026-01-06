-- 为 aicrm_task 表添加任务负责人字段
-- 该字段关联到 system_users 表

-- 添加 responsible_user_id 字段
ALTER TABLE `aicrm_task`
ADD COLUMN `responsible_user_id` bigint NULL COMMENT '任务负责人ID（关联 system_users.id）' AFTER `customer_name`;

-- 添加索引以提升查询性能
ALTER TABLE `aicrm_task`
ADD KEY `idx_responsible_user_id` (`responsible_user_id`);

-- 添加外键约束（可选，根据项目规范决定是否启用）
-- ALTER TABLE `aicrm_task`
-- ADD CONSTRAINT `fk_task_responsible_user` FOREIGN KEY (`responsible_user_id`) REFERENCES `system_users` (`id`) ON DELETE SET NULL ON UPDATE CASCADE;
