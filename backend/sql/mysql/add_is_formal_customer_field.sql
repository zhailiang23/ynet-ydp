-- ========================================
-- 为 grid_customer 表添加"是否正式客户"字段
-- ========================================
-- 执行日期: 2025-12-24
-- 说明: 为所有客户类型添加"是否正式客户"标识（通用字段）

-- 1. 添加字段
ALTER TABLE grid_customer
ADD COLUMN is_formal_customer TINYINT(1) DEFAULT 0 COMMENT '是否正式客户 (0=否, 1=是, 所有客户类型通用)' AFTER overdue_status;

-- 2. 验证字段已添加
SELECT
    COLUMN_NAME,
    COLUMN_TYPE,
    IS_NULLABLE,
    COLUMN_DEFAULT,
    COLUMN_COMMENT
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_SCHEMA = DATABASE()
  AND TABLE_NAME = 'grid_customer'
  AND COLUMN_NAME = 'is_formal_customer';

-- 3. 查看各类型客户数量统计
SELECT
    customer_type,
    COUNT(*) AS total_customers,
    SUM(CASE WHEN is_formal_customer = 1 THEN 1 ELSE 0 END) AS formal_customers,
    SUM(CASE WHEN is_formal_customer = 0 THEN 1 ELSE 0 END) AS non_formal_customers
FROM grid_customer
WHERE deleted = 0
GROUP BY customer_type;

-- 回滚脚本（如需回退）
-- ALTER TABLE grid_customer DROP COLUMN is_formal_customer;
