-- 统一更新客户分配状态
-- 根据 crm_customer_assignment 表是否有记录来更新 crm_customer.assignment_status

-- 1. 更新所有有归属关系的客户为已分配状态（1）
UPDATE crm_customer c
SET c.assignment_status = 1
WHERE EXISTS (
    SELECT 1
    FROM crm_customer_assignment ca
    WHERE ca.customer_id = c.id
      AND ca.deleted = 0
);

-- 2. 更新所有没有归属关系的客户为未分配状态（0）
UPDATE crm_customer c
SET c.assignment_status = 0
WHERE NOT EXISTS (
    SELECT 1
    FROM crm_customer_assignment ca
    WHERE ca.customer_id = c.id
      AND ca.deleted = 0
);

-- 3. 同步客户表的部门ID（使用主办的部门ID）
UPDATE crm_customer c
    LEFT JOIN (
    SELECT ca.customer_id, ca.dept_id
    FROM crm_customer_assignment ca
    WHERE ca.assignment_type = 1  -- 主办
      AND ca.deleted = 0
) main_assignment ON c.id = main_assignment.customer_id
SET c.dept_id = main_assignment.dept_id
WHERE c.assignment_status = 1;

-- 4. 清空未分配客户的部门ID
UPDATE crm_customer c
SET c.dept_id = NULL
WHERE c.assignment_status = 0;

-- 查询统计信息
SELECT
    assignment_status,
    CASE assignment_status
        WHEN 0 THEN '未分配'
        WHEN 1 THEN '已分配'
        ELSE '未知'
        END AS status_name,
    COUNT(*) AS customer_count
FROM crm_customer
WHERE deleted = 0
GROUP BY assignment_status
ORDER BY assignment_status;
