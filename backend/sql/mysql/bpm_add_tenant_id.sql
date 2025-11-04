-- ----------------------------
-- 为 BPM 模块所有表添加 tenant_id 字段
-- ----------------------------

-- 1. bpm_category 添加 tenant_id
ALTER TABLE `bpm_category` ADD COLUMN `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号' AFTER `deleted`;

-- 2. bpm_form 添加 tenant_id
ALTER TABLE `bpm_form` ADD COLUMN `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号' AFTER `deleted`;

-- 3. bpm_process_definition_info 添加 tenant_id
ALTER TABLE `bpm_process_definition_info` ADD COLUMN `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号' AFTER `deleted`;

-- 4. bpm_process_expression 添加 tenant_id
ALTER TABLE `bpm_process_expression` ADD COLUMN `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号' AFTER `deleted`;

-- 5. bpm_process_listener 添加 tenant_id
ALTER TABLE `bpm_process_listener` ADD COLUMN `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号' AFTER `deleted`;

-- 6. bpm_user_group 添加 tenant_id
ALTER TABLE `bpm_user_group` ADD COLUMN `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号' AFTER `deleted`;

-- 7. bpm_oa_leave 添加 tenant_id
ALTER TABLE `bpm_oa_leave` ADD COLUMN `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号' AFTER `deleted`;

-- 8. bpm_process_instance_copy 添加 tenant_id
ALTER TABLE `bpm_process_instance_copy` ADD COLUMN `tenant_id` bigint NOT NULL DEFAULT 0 COMMENT '租户编号' AFTER `deleted`;
