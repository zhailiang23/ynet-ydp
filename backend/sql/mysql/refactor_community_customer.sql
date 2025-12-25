-- =====================================================
-- 社区客户管理模块重构：将 grid_community_customer 表字段合并到 grid_customer
-- =====================================================

USE `ynet-iplatform`;

-- 1. 为 grid_customer 表添加社区客户专属字段
ALTER TABLE `grid_customer`
ADD COLUMN `family_members` INT NULL COMMENT '家庭成员数' AFTER `id_card`,
ADD COLUMN `housing_type` VARCHAR(50) NULL COMMENT '住房类型' AFTER `family_members`,
ADD COLUMN `monthly_income` DECIMAL(10,2) NULL COMMENT '月收入' AFTER `housing_type`;

-- 2. 数据迁移：将 grid_community_customer 的数据迁移到 grid_customer
UPDATE grid_customer gc
INNER JOIN grid_community_customer gcc ON gc.id = gcc.customer_id
SET
    gc.family_members = gcc.family_members,
    gc.housing_type = gcc.housing_type,
    gc.monthly_income = gcc.monthly_income
WHERE gc.customer_type = 'COMMUNITY'
  AND gc.deleted = 0
  AND gcc.deleted = 0;

-- 3. 为社区客户创建客户-网格关系记录
-- 注意：这里假设社区客户已经有 grid_id 字段，我们将其迁移到 grid_customer_grid_relation 表
INSERT INTO grid_customer_grid_relation (
    customer_id,
    grid_id,
    grid_code,
    grid_name,
    grid_type,
    assign_date,
    assign_operator_id,
    creator,
    create_time,
    updater,
    update_time,
    deleted,
    tenant_id
)
SELECT
    gc.id AS customer_id,
    gc.grid_id AS grid_id,
    gi.grid_code,
    gi.grid_name,
    gi.grid_type,
    CURDATE() AS assign_date,
    gc.creator AS assign_operator_id,
    gc.creator,
    gc.create_time,
    gc.updater,
    gc.update_time,
    0 AS deleted,
    gc.tenant_id
FROM grid_customer gc
INNER JOIN grid_info gi ON gc.grid_id = gi.id AND gi.deleted = 0
WHERE gc.customer_type = 'COMMUNITY'
  AND gc.grid_id IS NOT NULL
  AND gc.deleted = 0
  -- 避免重复插入（如果已经存在关系记录）
  AND NOT EXISTS (
      SELECT 1
      FROM grid_customer_grid_relation cgr
      WHERE cgr.customer_id = gc.id
        AND cgr.grid_id = gc.grid_id
        AND cgr.deleted = 0
  );

-- 4. 验证迁移结果
SELECT
    '迁移前：grid_community_customer 记录数' AS metric,
    COUNT(*) AS count
FROM grid_community_customer
WHERE deleted = 0
UNION ALL
SELECT
    '迁移后：grid_customer 中有社区字段的记录数' AS metric,
    COUNT(*) AS count
FROM grid_customer
WHERE customer_type = 'COMMUNITY'
  AND deleted = 0
  AND (family_members IS NOT NULL OR housing_type IS NOT NULL OR monthly_income IS NOT NULL)
UNION ALL
SELECT
    '客户-网格关系记录数（COMMUNITY类型）' AS metric,
    COUNT(*) AS count
FROM grid_customer_grid_relation cgr
INNER JOIN grid_info gi ON cgr.grid_id = gi.id
WHERE gi.grid_type = 'COMMUNITY'
  AND cgr.deleted = 0;

-- 5. 查看字段是否添加成功
SELECT COLUMN_NAME, COLUMN_TYPE, COLUMN_COMMENT
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = 'ynet-iplatform'
  AND TABLE_NAME = 'grid_customer'
  AND COLUMN_NAME IN ('family_members', 'housing_type', 'monthly_income');
