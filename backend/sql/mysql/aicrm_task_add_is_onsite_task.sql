-- 为 aicrm_task 表添加 is_onsite_task 字段（是否到店任务）
ALTER TABLE `aicrm_task`
ADD COLUMN `is_onsite_task` tinyint NOT NULL DEFAULT '0' COMMENT '是否到店任务（0=否 1=是）' AFTER `is_urgent`;

-- 根据现有数据更新 is_onsite_task 字段
-- 任务分类为 ASSET_UPGRADE、EXPIRE_REMINDER、COMPLIANCE、BUSINESS_ACTIVATION 时设为 1（到店任务）
UPDATE `aicrm_task`
SET `is_onsite_task` = CASE
    WHEN `category` IN ('ASSET_UPGRADE', 'EXPIRE_REMINDER', 'COMPLIANCE', 'BUSINESS_ACTIVATION') THEN 1
    ELSE 0
END;
