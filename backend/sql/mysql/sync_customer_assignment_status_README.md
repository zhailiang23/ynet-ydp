# 客户分配状态同步脚本说明

## 概述

该SQL脚本用于统一更新 `crm_customer` 表的 `assignment_status` 字段，确保其与 `crm_customer_assignment` 表的数据保持一致。

## 文件位置

`backend/sql/mysql/sync_customer_assignment_status.sql`

## 脚本功能

该脚本执行以下4个步骤：

### 1. 更新已分配客户状态
将所有在 `crm_customer_assignment` 表中有归属关系记录的客户的 `assignment_status` 设置为 `1`（已分配）。

```sql
UPDATE crm_customer c
SET c.assignment_status = 1
WHERE EXISTS (
    SELECT 1
    FROM crm_customer_assignment ca
    WHERE ca.customer_id = c.id
      AND ca.deleted = 0
);
```

### 2. 更新未分配客户状态
将所有在 `crm_customer_assignment` 表中没有归属关系记录的客户的 `assignment_status` 设置为 `0`（未分配）。

```sql
UPDATE crm_customer c
SET c.assignment_status = 0
WHERE NOT EXISTS (
    SELECT 1
    FROM crm_customer_assignment ca
    WHERE ca.customer_id = c.id
      AND ca.deleted = 0
);
```

### 3. 同步客户部门ID
将已分配客户的 `dept_id` 设置为其主办归属关系的部门ID。

```sql
UPDATE crm_customer c
    LEFT JOIN (
    SELECT ca.customer_id, ca.dept_id
    FROM crm_customer_assignment ca
    WHERE ca.assignment_type = 1  -- 主办
      AND ca.deleted = 0
) main_assignment ON c.id = main_assignment.customer_id
SET c.dept_id = main_assignment.dept_id
WHERE c.assignment_status = 1;
```

### 4. 清空未分配客户的部门ID
将未分配客户的 `dept_id` 设置为 `NULL`。

```sql
UPDATE crm_customer c
SET c.dept_id = NULL
WHERE c.assignment_status = 0;
```

### 5. 显示统计信息
执行完成后，脚本会显示分配状态的统计信息：

```
assignment_status | status_name | customer_count
0                 | 未分配      | 1
1                 | 已分配      | 19
```

## 使用场景

该脚本适用于以下场景：

1. **数据修复**：当 `crm_customer` 表和 `crm_customer_assignment` 表的数据不一致时
2. **系统升级后**：在修改归属关系逻辑后，需要重新同步历史数据
3. **定期维护**：作为定期数据维护任务的一部分
4. **数据导入后**：在批量导入客户或归属关系数据后

## 执行方式

### 方式一：命令行执行

```bash
mysql -h127.0.0.1 -uroot -p123456 ruoyi-vue-pro < backend/sql/mysql/sync_customer_assignment_status.sql
```

### 方式二：MySQL客户端执行

```sql
USE ruoyi-vue-pro;
SOURCE /path/to/backend/sql/mysql/sync_customer_assignment_status.sql;
```

### 方式三：复制粘贴执行

1. 打开 MySQL 客户端或 Navicat 等工具
2. 选择 `ruoyi-vue-pro` 数据库
3. 复制脚本内容并执行

## 注意事项

1. **备份数据**：执行前建议备份 `crm_customer` 表数据
2. **测试环境验证**：建议先在测试环境执行，确认无误后再在生产环境执行
3. **影响范围**：该脚本会更新所有客户记录的 `assignment_status` 和 `dept_id` 字段
4. **软删除**：脚本只考虑未软删除的归属关系记录（`deleted = 0`）
5. **事务**：脚本中的SQL语句独立执行，建议在事务中执行以保证原子性

## 验证结果

执行后可通过以下SQL验证结果：

```sql
-- 1. 检查是否有状态不一致的客户
SELECT
    c.id,
    c.customer_name,
    c.assignment_status,
    COUNT(ca.id) AS assignment_count
FROM crm_customer c
         LEFT JOIN crm_customer_assignment ca
                   ON c.id = ca.customer_id AND ca.deleted = 0
WHERE c.deleted = 0
GROUP BY c.id, c.customer_name, c.assignment_status
HAVING (c.assignment_status = 1 AND assignment_count = 0)
    OR (c.assignment_status = 0 AND assignment_count > 0);

-- 2. 检查是否有部门ID不一致的客户
SELECT
    c.id,
    c.customer_name,
    c.dept_id AS customer_dept_id,
    ca.dept_id AS assignment_dept_id
FROM crm_customer c
         INNER JOIN crm_customer_assignment ca
                    ON c.id = ca.customer_id
                        AND ca.assignment_type = 1  -- 主办
                        AND ca.deleted = 0
WHERE c.deleted = 0
  AND c.assignment_status = 1
  AND (c.dept_id != ca.dept_id OR c.dept_id IS NULL);
```

如果查询结果为空，则表示数据同步成功。

## 执行结果示例

```
assignment_status	status_name	customer_count
0	未分配	1
1	已分配	19

执行成功！共更新 20 条客户记录。
```

## 相关文件

- SQL脚本：`backend/sql/mysql/sync_customer_assignment_status.sql`
- 业务逻辑：`backend/ynet-module-crm/src/main/java/com.ynet.iplatform/module/aicrm/service/customerassignment/CustomerAssignmentServiceImpl.java`
- 数据模型：`backend/ynet-module-crm/src/main/java/com.ynet.iplatform/module/aicrm/dal/dataobject/customer/CustomerDO.java`

## 更新日志

- **2025-11-03**: 创建脚本，支持统一同步客户分配状态和部门ID
