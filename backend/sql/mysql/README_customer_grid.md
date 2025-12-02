# 客户网格管理系统设计说明

## 概述

根据门店网格关系列表和门店网格调整历史的图片设计，实现了客户网格管理系统，支持物理网格和虚拟网格的双重管理模式。

## 表结构设计

### 1. 网格信息表 (crm_grid_info)

存储物理网格和虚拟网格的基本信息。

**核心字段** (18个业务字段):
- **基本信息**: grid_code(网格编号), grid_name(网格名称), grid_type(网格类型)
- **管理信息**: dept_id(所属部门ID), manager_user_id(网格管理员用户ID)
- **地理信息**: province, city, district, address_detail
- **状态信息**: status(启用/停用), sort_order(排序)

**设计特点**:
- 支持物理网格(按地理位置划分)和虚拟网格(按客户属性划分)
- 网格管理员关联 system_users 表，避免数据冗余
- 所属部门关联 system_dept 表
- 物理网格有地理位置信息，虚拟网格地理字段为NULL

### 2. 客户归属网格表 (crm_customer_grid_assignment)

存储客户与网格的归属关系，一个客户同时归属于一个物理网格和一个虚拟网格。

**核心字段** (20个业务字段):
- **物理网格信息**: physical_grid_id, physical_grid_code, physical_grid_name, physical_grid_manager_user_id
- **虚拟网格信息**: virtual_grid_id, virtual_grid_code, virtual_grid_name, virtual_grid_manager_user_id
- **分配信息**: assign_date(分配日期), assign_operator_id(分配操作人)
- **状态信息**: status(生效中/已失效)

**设计特点**:
- 同时记录网格ID和网格编号/名称，方便查询和展示
- 同时记录网格管理员ID，符合图片中的字段设计
- 每个客户只能有一条有效记录(唯一索引: customer_id + deleted)
- 支持历史记录保留(软删除)

### 3. 客户归属网格调整历史表 (crm_customer_grid_history)

记录客户网格归属的所有变更历史。

**核心字段** (28个业务字段):
- **调整信息**: adjust_date(调整日期), adjust_reason(调整原因)
- **调整前物理网格**: before_physical_grid_id/code/name/manager_user_id
- **调整前虚拟网格**: before_virtual_grid_id/code/name/manager_user_id
- **调整后物理网格**: after_physical_grid_id/code/name/manager_user_id
- **调整后虚拟网格**: after_virtual_grid_id/code/name/manager_user_id
- **操作信息**: adjust_operator_id(调整操作人)

**设计特点**:
- 完整记录调整前后的网格信息，包括网格管理员
- 支持单独调整物理网格或虚拟网格
- 支持同时调整物理网格和虚拟网格

## 测试数据

### 网格信息 (10条记录)

**物理网格 (6个)**:
1. PG001 - 五华区网格 (管理员: 易诚源码)
2. PG002 - 盘龙区网格 (管理员: 易诚)
3. PG003 - 西山区网格 (管理员: 源码)
4. PG004 - 官渡区网格 (管理员: 测试号)
5. PG005 - 呈贡区网格 (管理员: 新对象)
6. PG006 - 高新区网格 (管理员: 易诚)

**虚拟网格 (4个)**:
1. VG001 - 高净值客户网格 (年收入100万+或资产500万+)
2. VG002 - 企业主网格 (拥有经营实体的客户)
3. VG003 - 公职人员网格 (政府机关、事业单位工作人员)
4. VG004 - 科技行业网格 (科技、互联网行业从业者)

### 客户网格归属 (15条记录)

所有现有客户都已分配网格归属：

| 客户范围 | 物理网格 | 虚拟网格 | 客户数 |
|----------|----------|----------|--------|
| 客户1-3 | 五华区网格 | 高净值客户网格 | 3 |
| 客户4-5 | 盘龙区网格 | 公职人员网格 | 2 |
| 客户6-7 | 西山区网格 | 企业主网格 | 2 |
| 客户8-10 | 官渡区网格 | 企业主网格 | 3 |
| 客户11-12 | 盘龙区网格 | 科技行业网格 | 2 |
| 客户13 | 五华区网格 | 高净值客户网格 | 1 |
| 客户14 | 高新区网格 | 科技行业网格 | 1 |
| 客户15 | 官渡区网格 | 企业主网格 | 1 |

### 网格调整历史 (5条记录)

1. **客户3**: 从盘龙区网格调整到五华区网格 (客户搬迁)
2. **客户7**: 虚拟网格从企业主网格升级到高净值客户网格 (资产增长)
3. **客户10**: 从呈贡区网格调整到官渡区网格 (工厂迁址)
4. **客户12**: 虚拟网格从公职人员网格调整到科技行业网格 (注册个体工商户)
5. **客户14**: 从盘龙区网格调整到高新区网格 (工作地点变更)

## 统计数据

### 网格分布统计
- 总网格数: 10个
- 物理网格: 6个
- 虚拟网格: 4个
- 启用网格: 10个

### 客户归属统计
- 总归属关系数: 15条
- 已分配网格客户数: 15个
- 使用中的物理网格: 5个
- 使用中的虚拟网格: 4个

### 各网格客户分布

**物理网格**:
- 五华区网格: 4个客户
- 盘龙区网格: 4个客户
- 西山区网格: 2个客户
- 官渡区网格: 4个客户
- 呈贡区网格: 0个客户 (暂无分配)
- 高新区网格: 1个客户

**虚拟网格**:
- 高净值客户网格: 4个客户
- 企业主网格: 6个客户
- 公职人员网格: 2个客户
- 科技行业网格: 3个客户

## 字段对比

### 与图片字段的对应关系

图片中的字段 -> 数据库表字段：

**门店网格关系列表**:
- 门店物理网格编号 -> physical_grid_code
- 门店物理网格名称 -> physical_grid_name
- 门店虚拟网格编号 -> virtual_grid_code
- 门店虚拟网格名称 -> virtual_grid_name
- 物理网格管理员编号 -> physical_grid_manager_user_id
- 物理网格管理员名称 -> 通过 system_users 关联查询
- 虚拟网格管理员编号 -> virtual_grid_manager_user_id
- 虚拟网格管理员名称 -> 通过 system_users 关联查询

**门店网格调整历史**:
- 所有字段完整对应到 crm_customer_grid_history 表
- 包含调整前后的完整网格信息和管理员信息

## 设计亮点

1. **双重网格管理**: 同时支持物理网格(地理位置)和虚拟网格(客户属性)
2. **完整历史记录**: 记录所有网格调整的前后状态
3. **关联系统表**: 网格管理员和部门关联系统表，避免冗余
4. **灵活查询**: 同时记录ID和编号/名称，满足不同查询需求
5. **状态管理**: 支持网格启用/停用，归属关系生效/失效
6. **多租户支持**: 所有表都包含 tenant_id 字段

## 相关SQL文件

1. **crm_customer_grid.sql** - 网格相关表结构定义(含字典数据)
2. **crm_customer_grid_test_data.sql** - 测试数据(10个网格，15个客户归属，5条历史)

## 查询示例

### 查询客户的网格归属信息
```sql
SELECT
  c.customer_name AS 客户姓名,
  a.physical_grid_name AS 物理网格,
  u1.nickname AS 物理网格管理员,
  a.virtual_grid_name AS 虚拟网格,
  u2.nickname AS 虚拟网格管理员
FROM crm_customer c
LEFT JOIN crm_customer_grid_assignment a ON c.id = a.customer_id AND a.deleted = b'0'
LEFT JOIN system_users u1 ON a.physical_grid_manager_user_id = u1.id
LEFT JOIN system_users u2 ON a.virtual_grid_manager_user_id = u2.id
WHERE c.deleted = b'0';
```

### 查询网格的客户数量
```sql
SELECT
  g.grid_name AS 网格名称,
  g.grid_type AS 网格类型,
  COUNT(CASE WHEN g.grid_type = 'physical' THEN a.physical_grid_id
             ELSE a.virtual_grid_id END) AS 客户数量
FROM crm_grid_info g
LEFT JOIN crm_customer_grid_assignment a ON
  (g.grid_type = 'physical' AND g.id = a.physical_grid_id) OR
  (g.grid_type = 'virtual' AND g.id = a.virtual_grid_id)
WHERE g.deleted = b'0'
GROUP BY g.id;
```

### 查询客户的网格调整历史
```sql
SELECT
  c.customer_name AS 客户姓名,
  h.adjust_date AS 调整日期,
  h.before_physical_grid_name AS 调整前物理网格,
  h.after_physical_grid_name AS 调整后物理网格,
  h.adjust_reason AS 调整原因
FROM crm_customer_grid_history h
LEFT JOIN crm_customer c ON h.customer_id = c.id
WHERE h.deleted = b'0'
ORDER BY h.adjust_date DESC;
```
