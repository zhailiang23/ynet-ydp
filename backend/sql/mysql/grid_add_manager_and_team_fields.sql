-- ----------------------------
-- 为 grid_info 表添加责任人和团队人数字段
-- ----------------------------

ALTER TABLE `grid_info`
ADD COLUMN `manager_user_id` bigint NULL DEFAULT NULL COMMENT '责任人用户ID（关联 system_users.id）' AFTER `org_id`,
ADD COLUMN `team_count` int NULL DEFAULT NULL COMMENT '团队人数' AFTER `manager_user_id`;

-- 添加索引
ALTER TABLE `grid_info`
ADD INDEX `idx_manager_user_id`(`manager_user_id` ASC) USING BTREE COMMENT '责任人索引';
