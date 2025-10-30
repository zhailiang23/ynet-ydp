# Spec: 归属客户群数据模型

## Capability
customer-group-data-model

## Status
proposed

## ADDED Requirements

### Requirement: 客户群表结构定义

The system SHALL define a customer group table with comprehensive information.

#### Scenario: 创建客户群表

**Given** 系统需要存储客户群信息
**When** 创建数据库表 `crm_customer_group`
**Then** 表 SHALL 包含以下字段:
- `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT - 主键
- `group_code` varchar(50) NOT NULL - 客户群编号,唯一索引
- `group_name` varchar(200) NOT NULL - 客户群名称
- `group_category` varchar(20) NOT NULL - 客户群分类 (对私/对公/混合)
- `group_level_type` varchar(20) - 群级别类型 (普通/高端/VIP)
- `customer_source` varchar(20) - 客户来源
- `member_count` int DEFAULT 0 - 客户群成员数
- `status` tinyint NOT NULL DEFAULT 1 - 状态 (0=已失效, 1=生效中)
- `remark` varchar(500) - 备注
- `creator` varchar(64) - 创建人
- `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP - 创建时间
- `creator_dept_id` bigint - 创建机构ID
- `updater` varchar(64) - 更新人
- `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP - 更新时间
- `updater_dept_id` bigint - 最近修改机构ID
- `deleted` bit(1) NOT NULL DEFAULT b'0' - 删除标记
- `tenant_id` bigint NOT NULL DEFAULT 0 - 租户ID

**And** 表 SHALL 创建以下索引:
- UNIQUE INDEX `uk_group_code` ON (`group_code`, `tenant_id`)
- INDEX `idx_status` ON (`status`)
- INDEX `idx_tenant_id` ON (`tenant_id`)
- INDEX `idx_create_time` ON (`create_time`)

**And** 表 SHALL 包含表注释和所有字段注释

### Requirement: 客户群关联表结构定义

The system SHALL define a customer group assignment table to track customer-group relationships.

#### Scenario: 创建客户群关联表

**Given** 系统需要存储客户与客户群的归属关系
**When** 创建数据库表 `crm_customer_group_assignment`
**Then** 表 SHALL 包含以下字段:
- `id` bigint NOT NULL PRIMARY KEY AUTO_INCREMENT - 主键
- `customer_id` bigint NOT NULL - 客户ID
- `group_id` bigint NOT NULL - 客户群ID
- `assign_date` date - 分配日期
- `assign_operator_id` bigint - 分配操作人ID
- `status` tinyint NOT NULL DEFAULT 1 - 归属状态 (0=已失效, 1=生效中)
- `remark` varchar(500) - 备注
- `creator` varchar(64) - 创建人
- `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP - 创建时间
- `updater` varchar(64) - 更新人
- `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP - 更新时间
- `deleted` bit(1) NOT NULL DEFAULT b'0' - 删除标记
- `tenant_id` bigint NOT NULL DEFAULT 0 - 租户ID

**And** 表 SHALL 创建以下索引:
- INDEX `idx_customer_id` ON (`customer_id`)
- INDEX `idx_group_id` ON (`group_id`)
- INDEX `idx_status` ON (`status`)
- INDEX `idx_tenant_id` ON (`tenant_id`)
- INDEX `idx_assign_date` ON (`assign_date`)

**And** 表 SHALL 包含表注释和所有字段注释

### Requirement: 客户群分类字典定义

The system SHALL define dictionary data for customer group categories.

#### Scenario: 创建客户群分类字典

**Given** 系统需要规范客户群分类值
**When** 创建字典 `aicrm_customer_group_category`
**Then** 字典 SHALL 包含以下字典项:
- `private_client` - 对私客户群
- `public_client` - 对公客户群
- `mixed` - 混合客户群

**And** 字典名称 SHALL 为"AICRM 客户群分类"
**And** 字典类型 SHALL 为"aicrm_customer_group_category"
**And** 字典状态 SHALL 为启用 (0)
**And** 字典备注 SHALL 说明该字典用于归属客户群功能

### Requirement: 群级别类型字典定义

The system SHALL define dictionary data for customer group level types.

#### Scenario: 创建群级别类型字典

**Given** 系统需要规范群级别类型值
**When** 创建字典 `aicrm_customer_group_level`
**Then** 字典 SHALL 包含以下字典项:
- `ordinary` - 普通客户群
- `premium` - 高端客户群
- `vip` - VIP客户群

**And** 字典名称 SHALL 为"AICRM 群级别类型"
**And** 字典类型 SHALL 为"aicrm_customer_group_level"
**And** 字典状态 SHALL 为启用 (0)
**And** 字典备注 SHALL 说明该字典用于归属客户群功能

### Requirement: 客户来源字典定义

The system SHALL define dictionary data for customer sources.

#### Scenario: 创建客户来源字典

**Given** 系统需要规范客户来源值
**When** 创建字典 `aicrm_customer_source`
**Then** 字典 SHALL 包含以下字典项:
- `system_allocation` - 系统初始化分配
- `manual_assignment` - 手动分配
- `grid_allocation` - 网格分配
- `other` - 其他

**And** 字典名称 SHALL 为"AICRM 客户来源"
**And** 字典类型 SHALL 为"aicrm_customer_source"
**And** 字典状态 SHALL 为启用 (0)
**And** 字典备注 SHALL 说明该字典用于归属客户群功能

### Requirement: 客户群关联查询返回完整信息

The system SHALL perform database joins to return complete customer group assignment information.

#### Scenario: 查询归属客户群关系时联表获取客户群信息

**Given** 查询客户群关联表记录
**When** 执行分页查询
**Then** 查询 SHALL LEFT JOIN `crm_customer_group` 表
**And** 查询结果 SHALL 包含客户群的以下字段:
- group_code (客户群编号)
- group_name (客户群名称)
- group_category (客户群分类)
- group_level_type (群级别类型)
- customer_source (客户来源)
- member_count (客户群成员数)

#### Scenario: 查询归属客户群关系时联表获取创建人信息

**Given** 查询客户群关联表记录
**And** 记录包含 creator 字段 (创建人用户名)
**When** 执行分页查询
**Then** 查询 SHALL LEFT JOIN `system_users` 表通过 creator 字段
**And** 查询结果 SHALL 包含 creator_name (创建人昵称)

#### Scenario: 查询归属客户群关系时联表获取创建机构信息

**Given** 查询客户群关联表记录
**And** 记录包含 creator_dept_id 字段
**When** 执行分页查询
**Then** 查询 SHALL LEFT JOIN `crm_customer_group` 表
**And** 再 LEFT JOIN `system_dept` 表通过 creator_dept_id 字段
**And** 查询结果 SHALL 包含 creator_dept_name (创建机构名称)

#### Scenario: 查询归属客户群关系时联表获取最近修改人信息

**Given** 查询客户群关联表记录
**And** 记录包含 updater 字段 (更新人用户名)
**When** 执行分页查询
**Then** 查询 SHALL LEFT JOIN `system_users` 表通过 updater 字段
**And** 查询结果 SHALL 包含 updater_name (最近修改人昵称)

#### Scenario: 查询归属客户群关系时联表获取最近修改机构信息

**Given** 查询客户群关联表记录
**And** 记录包含 updater_dept_id 字段
**When** 执行分页查询
**Then** 查询 SHALL LEFT JOIN `crm_customer_group` 表
**And** 再 LEFT JOIN `system_dept` 表通过 updater_dept_id 字段
**And** 查询结果 SHALL 包含 updater_dept_name (最近修改机构名称)

### Requirement: 多租户数据隔离

The system SHALL enforce tenant isolation for customer group data.

#### Scenario: 查询客户群归属关系时过滤租户数据

**Given** 用户登录到租户ID为 100 的租户
**When** 查询归属客户群列表
**Then** 查询条件 SHALL 包含 `tenant_id = 100`
**And** 查询结果 SHALL 只包含该租户的数据
**And** 不 SHALL 包含其他租户的数据

#### Scenario: 创建客户群归属关系时自动设置租户ID

**Given** 用户登录到租户ID为 100 的租户
**When** 创建新的客户群归属关系
**Then** 系统 SHALL 自动设置 `tenant_id = 100`
**And** 不 SHALL 允许用户手动指定其他租户ID

### Requirement: MyBatis Plus 分页查询支持

The system SHALL implement pagination using MyBatis Plus IPage pattern.

#### Scenario: 使用 IPage 进行分页查询

**Given** 需要分页查询客户群归属关系
**When** Mapper 接口定义查询方法
**Then** 方法签名 SHALL 为:
```java
IPage<CustomerGroupAssignmentRespVO> selectPageWithJoin(
    IPage<CustomerGroupAssignmentRespVO> page,
    @Param("reqVO") CustomerGroupAssignmentPageReqVO reqVO
)
```
**And** 方法参数 SHALL 包含 IPage 对象
**And** 方法参数 SHALL 使用 @Param 注解
**And** XML 中的参数引用 SHALL 使用 `reqVO.` 前缀

#### Scenario: Service 层转换 IPage 到 PageResult

**Given** Mapper 返回 IPage 对象
**When** Service 层调用 Mapper 方法
**Then** Service SHALL 创建 `Page<CustomerGroupAssignmentRespVO>` 对象
**And** Service SHALL 调用 Mapper 的 selectPageWithJoin 方法
**And** Service SHALL 将 IPage 结果转换为 PageResult:
```java
return new PageResult<>(result.getRecords(), result.getTotal());
```
