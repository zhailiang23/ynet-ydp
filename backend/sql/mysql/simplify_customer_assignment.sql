-- 简化客户归属关系表
-- 1. 删除 status、effective_date、expiry_date 字段
-- 2. 删除所有 status=0 的无效记录
-- 3. 更新唯一索引

USE `ruoyi-vue-pro`;

-- Step 1: 删除所有无效记录 (status = 0 的记录)
DELETE FROM `crm_customer_assignment` WHERE `status` = 0;

-- Step 2: 删除旧的唯一索引
ALTER TABLE `crm_customer_assignment`
DROP INDEX `uk_customer_assignment`;

-- Step 3: 删除不需要的字段
ALTER TABLE `crm_customer_assignment`
DROP COLUMN `status`,
DROP COLUMN `effective_date`,
DROP COLUMN `expiry_date`;

-- Step 4: 创建新的唯一索引(不包含status字段)
-- 说明: 同一客户的同一类型归属，同一客户经理只能有一条记录
ALTER TABLE `crm_customer_assignment`
ADD UNIQUE INDEX `uk_customer_assignment`(
  `customer_id` ASC,
  `assignment_type` ASC,
  `user_id` ASC,
  `deleted` ASC
) USING BTREE
COMMENT '客户归属唯一索引（同一客户的同一类型归属，同一客户经理只能有一条记录）';

-- Step 5: 删除状态索引(如果存在)
-- 使用 IF EXISTS 语法需要MySQL 5.7.4+，这里用存储过程方式
SET @sql = (SELECT IF(
  (SELECT COUNT(*) FROM information_schema.statistics
   WHERE table_schema = DATABASE()
   AND table_name = 'crm_customer_assignment'
   AND index_name = 'idx_status') > 0,
  'ALTER TABLE crm_customer_assignment DROP INDEX idx_status',
  'SELECT "索引 idx_status 不存在，跳过" AS message'
));

PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- 完成
SELECT '简化客户归属表完成！' AS message;
