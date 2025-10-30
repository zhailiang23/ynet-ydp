# Spec: 归属客户群 API

## Capability
customer-group-api

## Status
proposed

## ADDED Requirements

### Requirement: 分页查询归属客户群关系接口

The system SHALL provide an API endpoint to query customer group assignments with pagination.

#### Scenario: 查询客户群归属关系列表

**Given** 用户已认证并有权限访问客户群归属关系数据
**When** 前端调用 `GET /admin-api/aicrm/customer-group-assignment/page`
**And** 请求参数包含:
- `pageNo`: int - 页码 (从 1 开始)
- `pageSize`: int - 每页条数
- `customerId`: Long (可选) - 客户ID
- `groupId`: Long (可选) - 客户群ID
- `status`: Integer (可选) - 归属状态
- `assignDate`: LocalDate[] (可选) - 分配日期范围 [开始, 结束]
- `createTime`: LocalDateTime[] (可选) - 创建时间范围

**Then** 接口 SHALL 返回状态码 200
**And** 响应 SHALL 符合统一响应格式:
```json
{
  "code": 0,
  "data": {
    "list": [...],
    "total": 100
  },
  "msg": "操作成功"
}
```
**And** 每条记录 SHALL 包含以下字段:
- id, customerId, groupId
- groupCode, groupName, groupCategory, groupLevelType, customerSource, memberCount
- assignDate, assignOperatorId, status, remark
- createTime, creatorName, creatorDeptName
- updateTime, updaterName, updaterDeptName

#### Scenario: 查询结果按创建时间倒序排列

**Given** 数据库中存在多条客户群归属关系记录
**When** 前端调用分页查询接口
**Then** 查询结果 SHALL 按 `create_time DESC` 排序
**And** 最新创建的记录 SHALL 排在前面

### Requirement: 获取单条归属客户群关系接口

The system SHALL provide an API endpoint to retrieve a single customer group assignment record.

#### Scenario: 根据 ID 获取客户群归属关系详情

**Given** 用户已认证并有权限访问客户群归属关系数据
**And** 数据库中存在 ID 为 123 的客户群归属关系记录
**When** 前端调用 `GET /admin-api/aicrm/customer-group-assignment/get?id=123`
**Then** 接口 SHALL 返回状态码 200
**And** 响应 data SHALL 包含完整的记录信息
**And** 包含所有关联查询的字段 (客户群信息、用户名称、部门名称)

#### Scenario: 查询不存在的记录返回错误

**Given** 用户已认证
**And** 数据库中不存在 ID 为 999 的记录
**When** 前端调用 `GET /admin-api/aicrm/customer-group-assignment/get?id=999`
**Then** 接口 SHALL 返回状态码 400
**And** 响应 SHALL 包含错误码和错误信息
**And** 错误信息 SHALL 为"客户归属客户群关系不存在"

### Requirement: 创建归属客户群关系接口

The system SHALL provide an API endpoint to create customer group assignment records.

#### Scenario: 创建客户群归属关系

**Given** 用户已认证并有创建权限
**When** 前端调用 `POST /admin-api/aicrm/customer-group-assignment/create`
**And** 请求体包含:
```json
{
  "customerId": 1,
  "groupId": 10,
  "assignDate": "2025-10-29",
  "assignOperatorId": 100,
  "status": 1,
  "remark": "测试备注"
}
```
**Then** 接口 SHALL 返回状态码 200
**And** 响应 data SHALL 为新创建记录的 ID
**And** 数据库 SHALL 插入新记录
**And** 新记录的 tenant_id SHALL 自动设置为当前用户的租户ID
**And** 新记录的 creator 和 create_time SHALL 自动设置

#### Scenario: 创建时校验必填字段

**Given** 用户已认证
**When** 前端调用创建接口但缺少必填字段 customerId
**Then** 接口 SHALL 返回状态码 400
**And** 响应 SHALL 包含参数校验错误信息

### Requirement: 更新归属客户群关系接口

The system SHALL provide an API endpoint to update customer group assignment records.

#### Scenario: 更新客户群归属关系

**Given** 用户已认证并有更新权限
**And** 数据库中存在 ID 为 123 的记录
**When** 前端调用 `PUT /admin-api/aicrm/customer-group-assignment/update`
**And** 请求体包含:
```json
{
  "id": 123,
  "status": 0,
  "remark": "已失效"
}
```
**Then** 接口 SHALL 返回状态码 200
**And** 数据库记录 SHALL 更新
**And** 更新记录的 updater 和 update_time SHALL 自动更新

#### Scenario: 更新不存在的记录返回错误

**Given** 用户已认证
**And** 数据库中不存在 ID 为 999 的记录
**When** 前端调用更新接口
**Then** 接口 SHALL 返回状态码 400
**And** 错误信息 SHALL 为"客户归属客户群关系不存在"

### Requirement: 删除归属客户群关系接口

The system SHALL provide an API endpoint to delete customer group assignment records (soft delete).

#### Scenario: 删除客户群归属关系

**Given** 用户已认证并有删除权限
**And** 数据库中存在 ID 为 123 的记录
**When** 前端调用 `DELETE /admin-api/aicrm/customer-group-assignment/delete?id=123`
**Then** 接口 SHALL 返回状态码 200
**And** 数据库记录的 deleted 字段 SHALL 设置为 1 (逻辑删除)
**And** 记录 SHALL 不再出现在查询结果中

#### Scenario: 删除不存在的记录返回错误

**Given** 用户已认证
**And** 数据库中不存在 ID 为 999 的记录
**When** 前端调用删除接口
**Then** 接口 SHALL 返回状态码 400
**And** 错误信息 SHALL 为"客户归属客户群关系不存在"

### Requirement: 导出 Excel 接口

The system SHALL provide an API endpoint to export customer group assignments to Excel.

#### Scenario: 导出客户群归属关系到 Excel

**Given** 用户已认证并有导出权限
**When** 前端调用 `GET /admin-api/aicrm/customer-group-assignment/export-excel`
**And** 请求参数包含查询条件 (与分页查询接口相同)
**Then** 接口 SHALL 返回 Excel 文件
**And** Content-Type SHALL 为 `application/vnd.ms-excel`
**And** Excel 文件 SHALL 包含所有查询结果 (不分页)
**And** Excel 列 SHALL 与表格显示列对应

### Requirement: API 权限控制

The system SHALL enforce permission checks on customer group assignment API endpoints.

#### Scenario: 查询接口需要查询权限

**Given** 用户已认证但没有 `aicrm:customer-group-assignment:query` 权限
**When** 前端调用分页查询或详情查询接口
**Then** 接口 SHALL 返回状态码 403
**And** 响应 SHALL 包含权限不足的错误信息

#### Scenario: 创建接口需要创建权限

**Given** 用户已认证但没有 `aicrm:customer-group-assignment:create` 权限
**When** 前端调用创建接口
**Then** 接口 SHALL 返回状态码 403
**And** 响应 SHALL 包含权限不足的错误信息

#### Scenario: 更新接口需要更新权限

**Given** 用户已认证但没有 `aicrm:customer-group-assignment:update` 权限
**When** 前端调用更新接口
**Then** 接口 SHALL 返回状态码 403
**And** 响应 SHALL 包含权限不足的错误信息

#### Scenario: 删除接口需要删除权限

**Given** 用户已认证但没有 `aicrm:customer-group-assignment:delete` 权限
**When** 前端调用删除接口
**Then** 接口 SHALL 返回状态码 403
**And** 响应 SHALL 包含权限不足的错误信息

#### Scenario: 导出接口需要导出权限

**Given** 用户已认证但没有 `aicrm:customer-group-assignment:export` 权限
**When** 前端调用导出接口
**Then** 接口 SHALL 返回状态码 403
**And** 响应 SHALL 包含权限不足的错误信息

### Requirement: API 文档完整性

The system SHALL provide complete API documentation for customer group assignment endpoints.

#### Scenario: Swagger 文档包含所有接口

**Given** 后端服务已启动
**When** 访问 Swagger UI 页面
**Then** 文档 SHALL 包含客户群归属关系的所有接口
**And** 每个接口 SHALL 包含:
- 接口路径和 HTTP 方法
- 请求参数说明
- 响应格式说明
- 错误码说明
**And** 文档 SHALL 标注权限要求

#### Scenario: VO 类包含完整的字段注释

**Given** 定义了 CustomerGroupAssignmentRespVO 类
**When** 查看类定义
**Then** 每个字段 SHALL 包含 `@Schema` 注解
**And** 注解 SHALL 包含 description 属性说明字段含义
**And** 日期字段 SHALL 包含 example 示例值
