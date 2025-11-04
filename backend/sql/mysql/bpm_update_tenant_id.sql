-- ----------------------------
-- 将所有 BPM 表的 tenant_id 设置为 1
-- ----------------------------

-- 1. bpm_category
UPDATE `bpm_category` SET `tenant_id` = 1 WHERE `tenant_id` = 0;

-- 2. bpm_form
UPDATE `bpm_form` SET `tenant_id` = 1 WHERE `tenant_id` = 0;

-- 3. bpm_process_definition_info
UPDATE `bpm_process_definition_info` SET `tenant_id` = 1 WHERE `tenant_id` = 0;

-- 4. bpm_process_expression
UPDATE `bpm_process_expression` SET `tenant_id` = 1 WHERE `tenant_id` = 0;

-- 5. bpm_process_listener
UPDATE `bpm_process_listener` SET `tenant_id` = 1 WHERE `tenant_id` = 0;

-- 6. bpm_user_group
UPDATE `bpm_user_group` SET `tenant_id` = 1 WHERE `tenant_id` = 0;

-- 7. bpm_oa_leave
UPDATE `bpm_oa_leave` SET `tenant_id` = 1 WHERE `tenant_id` = 0;

-- 8. bpm_process_instance_copy
UPDATE `bpm_process_instance_copy` SET `tenant_id` = 1 WHERE `tenant_id` = 0;
