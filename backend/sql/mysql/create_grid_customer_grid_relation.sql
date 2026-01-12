-- =====================================================
-- 客户-网格关系表（多对多）
-- =====================================================
CREATE TABLE IF NOT EXISTS `grid_customer_grid_relation` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `customer_id` BIGINT NOT NULL COMMENT '客户ID (关联 grid_customer.id)',
  `grid_id` BIGINT NOT NULL COMMENT '网格ID (关联 grid_info.id)',
  `grid_code` VARCHAR(50) COMMENT '网格编号（冗余字段，提升查询性能）',
  `grid_name` VARCHAR(200) COMMENT '网格名称（冗余字段）',
  `grid_type` VARCHAR(20) COMMENT '网格类型（冗余字段）',
  `assign_date` DATE NOT NULL COMMENT '分配日期',
  `assign_operator_id` BIGINT COMMENT '分配操作人ID',
  `creator` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updater` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否删除',
  `tenant_id` BIGINT NOT NULL DEFAULT 0 COMMENT '租户ID',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uk_customer_grid` (`customer_id`, `grid_id`, `deleted`) COMMENT '防止重复关联',
  INDEX `idx_customer_id` (`customer_id`),
  INDEX `idx_grid_id` (`grid_id`),
  INDEX `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='客户-网格关系表（多对多）';

-- =====================================================
-- 初始数据迁移：将现有 grid_customer.grid_id 关系迁移到关系表
-- =====================================================
INSERT INTO grid_customer_grid_relation (
  customer_id, grid_id, grid_code, grid_name, grid_type,
  assign_date, assign_operator_id,
  creator, create_time, updater, update_time, deleted, tenant_id
)
SELECT
  c.id AS customer_id,
  c.grid_id AS grid_id,
  g.grid_code,
  g.grid_name,
  g.grid_type,
  CURDATE() AS assign_date,
  NULL AS assign_operator_id,
  c.creator,
  c.create_time,
  c.updater,
  c.update_time,
  c.deleted,
  c.tenant_id
FROM grid_customer c
INNER JOIN grid_info g ON c.grid_id = g.id AND g.deleted = 0
WHERE c.grid_id IS NOT NULL
  AND c.customer_type = 'HUINONG_LOAN'
  AND c.deleted = 0;

-- =====================================================
-- 验证迁移结果
-- =====================================================
SELECT
  '迁移前客户数' AS metric,
  COUNT(*) AS count
FROM grid_customer
WHERE grid_id IS NOT NULL
  AND customer_type = 'HUINONG_LOAN'
  AND deleted = 0
UNION ALL
SELECT
  '迁移后关系记录数' AS metric,
  COUNT(*) AS count
FROM grid_customer_grid_relation
WHERE deleted = 0;
