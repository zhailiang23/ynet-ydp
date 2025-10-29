# 客户网格管理系统重新设计说明

## 设计原则变更

### 旧设计 ❌
- 客户归属网格表的**一条记录**同时包含物理网格信息和虚拟网格信息
- 字段结构:
  - physical_grid_id, physical_grid_code, physical_grid_name, physical_grid_manager_user_id
  - virtual_grid_id, virtual_grid_code, virtual_grid_name, virtual_grid_manager_user_id
- 问题: 字段冗余，不够灵活

### 新设计 ✅
- 客户归属网格表的**每条记录只包含一个网格信息**
- 字段结构:
  - grid_id, grid_code, grid_name, grid_type, grid_manager_user_id
- 优势:
  - 结构更简洁，扩展性更好
  - 如果客户同时属于物理网格A和虚拟网格B，则有**两条记录**
  - 如果将来需要支持多个虚拟网格，无需修改表结构

## 表结构设计

### 1. 网格信息表 (crm_grid_info) - 不变

与旧设计完全相同，18个业务字段。

### 2. 客户归属网格表 (crm_customer_grid_assignment) - 重新设计

#### 新设计字段 (11个业务字段)
- **网格信息** (5): grid_id, grid_code, grid_name, grid_type, grid_manager_user_id
- **分配信息** (2): assign_date, assign_operator_id
- **状态信息** (1): status

#### 旧设计字段 (20个业务字段)
- ~~physical_grid_id, physical_grid_code, physical_grid_name, physical_grid_manager_user_id~~
- ~~virtual_grid_id, virtual_grid_code, virtual_grid_name, virtual_grid_manager_user_id~~
- 其他字段相同

#### 字段精简效果
- 从 20个业务字段 → 11个业务字段 (精简45%)
- 唯一索引变更: `(customer_id, grid_id, deleted)` 确保同一客户同一网格只有一条有效记录

### 3. 客户归属网格调整历史表 (crm_customer_grid_history) - 重新设计

#### 新设计字段 (13个业务字段)
- **调整信息** (3): adjust_date, adjust_reason, grid_type
- **调整前网格** (4): before_grid_id, before_grid_code, before_grid_name, before_grid_manager_user_id
- **调整后网格** (4): after_grid_id, after_grid_code, after_grid_name, after_grid_manager_user_id
- **操作信息** (1): adjust_operator_id

#### 旧设计字段 (28个业务字段)
- ~~before_physical_grid_* (4字段)~~
- ~~before_virtual_grid_* (4字段)~~
- ~~after_physical_grid_* (4字段)~~
- ~~after_virtual_grid_* (4字段)~~
- 其他字段相同

#### 字段精简效果
- 从 28个业务字段 → 13个业务字段 (精简54%)
- 新增 grid_type 字段标识是物理网格还是虚拟网格的调整
- 每次调整只记录一个网格的变更（物理或虚拟）

## 数据结构对比

### 旧设计 - 客户1的网格归属
```
客户ID=1,
physical_grid_id=1, physical_grid_code=PG001, physical_grid_name=五华区网格,
virtual_grid_id=7, virtual_grid_code=VG001, virtual_grid_name=高净值客户网格
```
- **1条记录**

### 新设计 - 客户1的网格归属
```
记录1: 客户ID=1, grid_id=1, grid_code=PG001, grid_name=五华区网格, grid_type=physical
记录2: 客户ID=1, grid_id=7, grid_code=VG001, grid_name=高净值客户网格, grid_type=virtual
```
- **2条记录**

## 测试数据统计

### 网格信息
- 总网格数: 10个 (物理网格6个 + 虚拟网格4个)
- 与旧设计相同

### 客户归属
- **旧设计**: 15条记录 (15个客户，每个客户1条记录)
- **新设计**: 30条记录 (15个客户，每个客户2条记录) ✅

### 调整历史
- 总历史数: 5条
- 物理网格调整: 3条
- 虚拟网格调整: 2条

## 查询示例对比

### 查询客户的物理网格和虚拟网格

#### 旧设计 (简单)
```sql
SELECT
  customer_id,
  physical_grid_name,
  virtual_grid_name
FROM crm_customer_grid_assignment
WHERE customer_id = 1;
```

#### 新设计 (需要聚合)
```sql
SELECT
  customer_id,
  MAX(CASE WHEN grid_type = 'physical' THEN grid_name END) AS physical_grid_name,
  MAX(CASE WHEN grid_type = 'virtual' THEN grid_name END) AS virtual_grid_name
FROM crm_customer_grid_assignment
WHERE customer_id = 1
GROUP BY customer_id;
```

### 查询某网格的所有客户

#### 旧设计 (需要UNION)
```sql
SELECT customer_id FROM crm_customer_grid_assignment WHERE physical_grid_id = 1
UNION
SELECT customer_id FROM crm_customer_grid_assignment WHERE virtual_grid_id = 1;
```

#### 新设计 (简单)
```sql
SELECT customer_id
FROM crm_customer_grid_assignment
WHERE grid_id = 1;
```

## 设计优势

### 新设计的优势 ✅
1. **结构简洁**: 字段精简45-54%
2. **扩展性强**: 支持客户属于多个虚拟网格，无需修改表结构
3. **查询灵活**: 按网格查询客户更简单
4. **职责单一**: 每条记录只表示一个网格归属关系

### 旧设计的优势
1. **查询简单**: 一次查询即可获取物理和虚拟网格
2. **理解直观**: 一条记录表示完整的网格归属状态

## 适用场景

### 新设计适合
- ✅ 客户可能属于多个虚拟网格的场景
- ✅ 网格类型可能增加的场景（如中间网格、特殊网格等）
- ✅ 需要按网格统计客户的场景
- ✅ 表结构需要保持简洁的场景

### 旧设计适合
- 客户**必定**只属于1个物理网格和1个虚拟网格的场景
- 查询性能要求极高的场景（减少JOIN和GROUP BY）
- 网格类型固定不变的场景

## 数据验证

### 验证每个客户有两条记录

```sql
SELECT
  customer_id,
  COUNT(*) AS 网格数量,
  SUM(CASE WHEN grid_type = 'physical' THEN 1 ELSE 0 END) AS 物理网格数,
  SUM(CASE WHEN grid_type = 'virtual' THEN 1 ELSE 0 END) AS 虚拟网格数
FROM crm_customer_grid_assignment
WHERE deleted = b'0' AND status = 1
GROUP BY customer_id
HAVING 网格数量 = 2;
```

预期结果: 所有15个客户都应该有2条记录(1个物理网格 + 1个虚拟网格)

### 验证网格归属记录总数

```sql
SELECT
  '归属记录统计' AS 类型,
  COUNT(*) AS 总记录数,
  COUNT(*) / 2 AS 客户数量,
  SUM(CASE WHEN grid_type = 'physical' THEN 1 ELSE 0 END) AS 物理网格记录数,
  SUM(CASE WHEN grid_type = 'virtual' THEN 1 ELSE 0 END) AS 虚拟网格记录数
FROM crm_customer_grid_assignment
WHERE deleted = b'0' AND status = 1;
```

预期结果:
- 总记录数: 30
- 客户数量: 15
- 物理网格记录数: 15
- 虚拟网格记录数: 15

## 迁移建议

如果已使用旧设计，迁移到新设计的SQL:

```sql
-- 第一步: 迁移物理网格记录
INSERT INTO crm_customer_grid_assignment_new (
  customer_id, grid_id, grid_code, grid_name, grid_type, grid_manager_user_id,
  assign_date, assign_operator_id, status, remark,
  creator, create_time, updater, update_time, tenant_id
)
SELECT
  customer_id, physical_grid_id, physical_grid_code, physical_grid_name,
  'physical', physical_grid_manager_user_id,
  assign_date, assign_operator_id, status, remark,
  creator, create_time, updater, update_time, tenant_id
FROM crm_customer_grid_assignment_old
WHERE physical_grid_id IS NOT NULL;

-- 第二步: 迁移虚拟网格记录
INSERT INTO crm_customer_grid_assignment_new (
  customer_id, grid_id, grid_code, grid_name, grid_type, grid_manager_user_id,
  assign_date, assign_operator_id, status, remark,
  creator, create_time, updater, update_time, tenant_id
)
SELECT
  customer_id, virtual_grid_id, virtual_grid_code, virtual_grid_name,
  'virtual', virtual_grid_manager_user_id,
  assign_date, assign_operator_id, status, remark,
  creator, create_time, updater, update_time, tenant_id
FROM crm_customer_grid_assignment_old
WHERE virtual_grid_id IS NOT NULL;
```

## 相关文件

1. ✅ **crm_customer_grid_redesign.sql** - 重新设计的表结构
2. ✅ **crm_customer_grid_redesign_test_data.sql** - 重新设计的测试数据
3. ~~crm_customer_grid.sql~~ (旧版设计)
4. ~~crm_customer_grid_test_data.sql~~ (旧版测试数据)

## 总结

新设计采用**更通用、更灵活**的方式，每条记录只包含一个网格信息。虽然在查询客户的完整网格信息时需要多一次聚合操作，但换来的是：
- ✅ 更简洁的表结构 (字段精简45-54%)
- ✅ 更好的扩展性 (支持多虚拟网格)
- ✅ 更灵活的查询 (按网格查询更简单)
- ✅ 更清晰的语义 (每条记录职责单一)

这是一个**更符合数据库设计范式**的方案。
