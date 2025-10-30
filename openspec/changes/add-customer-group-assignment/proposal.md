# Proposal: 在管理信息页面添加归属客户群功能

## 变更 ID
`add-customer-group-assignment`

## 状态
`proposed`

## 提议日期
2025-10-29

## 作者
Claude Code

## 概述
在零售/对公客户管理信息页面的最下方添加归属客户群表格,展示客户所属的客户群信息,包括客户群编号、名称、分类、成员类型、来源、成员数、创建信息、最近修改信息等。

**现状**: 数据库表和后端基础代码已存在,需要完善联表查询和实现前端展示。

## 背景与动机

### 当前问题
1. **功能缺失**：管理信息页面缺少归属客户群信息的展示功能
2. **后端未完善**：虽然数据库表和基础代码已存在,但缺少联表查询实现
3. **前端未实现**：缺少前端 API 模块和展示组件
4. **业务需求**：需要在管理信息页面统一查看客户的所有归属关系,包括管户、网格和客户群

### 业务价值
1. **完整信息展示**：在同一页面展示客户的所有归属关系信息
2. **提升用户体验**：无需跳转到其他页面即可查看客户群归属信息
3. **支持群组管理**：便于了解客户所属的群组,支持精细化客户管理

## 技术方案

### 数据模型 (已存在)

#### 客户群表 (crm_customer_group_info) ✅ 已存在
- `id`: bigint - 主键
- `group_code`: varchar(50) - 客户群编号
- `group_name`: varchar(200) - 客户群名称
- `group_category`: varchar(50) - 客户群分类 (字典: aicrm_customer_group_category)
- `member_type`: varchar(20) - 成员类型 (字典: aicrm_member_type) - retail/corporate/mixed
- `customer_source`: varchar(50) - 客户来源 (字典: aicrm_customer_source)
- `dept_id`: bigint - 所属部门ID
- `manager_user_id`: bigint - 群管理员用户ID
- `member_count`: int - 客户群成员数
- `description`: varchar(1000) - 客户群描述
- `status`: tinyint - 状态 (0=已失效, 1=生效中)
- `sort_order`: int - 排序
- `remark`: varchar(500) - 备注
- `creator`: varchar(64) - 创建人
- `create_time`: datetime - 创建时间
- `updater`: varchar(64) - 更新人
- `update_time`: datetime - 更新时间
- `deleted`: bit(1) - 删除标记
- `tenant_id`: bigint - 租户ID

#### 客户群关联表 (crm_customer_group_assignment) ✅ 已存在
- `id`: bigint - 主键
- `customer_id`: bigint - 客户ID
- `group_id`: bigint - 客户群ID
- `assign_date`: date - 分配日期
- `assign_operator_id`: bigint - 分配操作人ID
- `status`: tinyint - 归属状态 (0=已失效, 1=生效中)
- `remark`: varchar(500) - 备注
- `creator`: varchar(64) - 创建人
- `create_time`: datetime - 创建时间
- `updater`: varchar(64) - 更新人
- `update_time`: datetime - 更新时间
- `deleted`: bit(1) - 删除标记
- `tenant_id`: bigint - 租户ID

**注意**: 实际表名为 `crm_customer_group_info` 而非 `crm_customer_group`

### 前端变更

#### 1. 在管理信息页面最下方添加归属客户群表格
- 位置：在归属网格关系 Tab 组的下方
- 表格高度：400px (与其他表格保持一致)
- 实现方式：使用单独的 Tab 组,包含一个 "归属客户群列表" Tab

#### 2. 表格字段设计
表格应显示以下字段 (与截图保持一致):
- 序号 (自动序号)
- 客户群编号 (groupCode)
- 客户群名称 (groupName)
- 客户群分类 (groupCategory) - 使用字典显示
- 群级别类型 (memberType) - 使用字典显示 (retail/corporate/mixed)
- 客户来源 (customerSource) - 使用字典显示
- 客户群成员数 (memberCount)
- 创建日期 (createTime)
- 创建人 (creatorName) - 联表查询用户名称 (通过 creator 字段)
- 创建机构 (deptName) - 联表查询部门名称 (通过 dept_id 字段)
- 最近修改日期 (updateTime)
- 最近修改人 (updaterName) - 联表查询用户名称 (通过 updater 字段)
- 最近修改机构 (updaterDeptName) - 联表查询部门名称 (通过客户群表的 dept_id)

**注意**: 创建机构和最近修改机构都来自 crm_customer_group_info.dept_id 字段

#### 3. 前端文件结构
```
frontend/apps/web-antd/src/
├── api/aicrm/customergroupassignment/
│   └── index.ts (新增 API 模块)
├── views/aicrm/retail-customer/
│   ├── pages/
│   │   └── management-info.vue (修改: 添加归属客户群表格区域)
│   └── components/
│       └── CustomerGroupAssignmentGrid.vue (新增: 归属客户群表格组件)
```

### 后端变更

#### 1. 数据库表 ✅ 已存在
- `crm_customer_group_info` 表已存在
- `crm_customer_group_assignment` 表已存在
- 相关索引已创建
- **现有数据**: 32 条客户群归属关系记录

#### 2. 后端基础代码 ✅ 已存在
```
backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/
├── controller/admin/customergroupassignment/
│   ├── CustomerGroupAssignmentController.java ✅ 已存在
│   └── vo/
│       ├── CustomerGroupAssignmentPageReqVO.java ✅ 已存在
│       ├── CustomerGroupAssignmentRespVO.java ✅ 已存在 (需扩展字段)
│       └── CustomerGroupAssignmentSaveReqVO.java ✅ 已存在
├── service/customergroupassignment/
│   ├── CustomerGroupAssignmentService.java ✅ 已存在
│   └── CustomerGroupAssignmentServiceImpl.java ✅ 已存在 (需添加联表查询方法)
├── dal/
│   ├── dataobject/customergroupassignment/
│   │   └── CustomerGroupAssignmentDO.java ✅ 已存在
│   └── mysql/customergroupassignment/
│       └── CustomerGroupAssignmentMapper.java ✅ 已存在 (需添加联表查询方法)
└── resources/mapper/customergroupassignment/
    └── CustomerGroupAssignmentMapper.xml ✅ 已存在 (空文件,需添加联表查询 SQL)
```

#### 3. 需要实现联表查询 ⚠️ 待完成
在 `CustomerGroupAssignmentMapper.xml` 中实现联表查询:
- LEFT JOIN `crm_customer_group_info` 获取客户群详细信息
- LEFT JOIN `system_users` u1 通过 `g.creator` 获取创建人名称
- LEFT JOIN `system_users` u2 通过 `g.updater` 获取最近修改人名称
- LEFT JOIN `system_dept` 通过 `g.dept_id` 获取部门名称

SQL 示例:
```sql
SELECT cga.*,
       g.group_code, g.group_name, g.group_category, g.member_type,
       g.customer_source, g.member_count, g.create_time as group_create_time, g.update_time as group_update_time,
       u1.nickname AS creator_name,
       u2.nickname AS updater_name,
       d.name AS dept_name
FROM crm_customer_group_assignment cga
LEFT JOIN crm_customer_group_info g ON cga.group_id = g.id
LEFT JOIN system_users u1 ON g.creator = u1.username
LEFT JOIN system_users u2 ON g.updater = u2.username
LEFT JOIN system_dept d ON g.dept_id = d.id
WHERE cga.deleted = 0
```

#### 4. 需要扩展 VO 字段 ⚠️ 待完成
`CustomerGroupAssignmentRespVO` 需要添加以下字段:
- 客户群信息: `groupCode`, `groupName`, `groupCategory`, `memberType`, `customerSource`, `memberCount`
- 创建人: `creatorName`
- 最近修改人: `updaterName`
- 所属部门: `deptName`

### API 设计

#### 后端接口
- `GET /admin-api/aicrm/customer-group-assignment/page` - 分页查询客户群归属关系
- `GET /admin-api/aicrm/customer-group-assignment/get?id={id}` - 获取单条记录
- `POST /admin-api/aicrm/customer-group-assignment/create` - 创建客户群归属关系
- `PUT /admin-api/aicrm/customer-group-assignment/update` - 更新客户群归属关系
- `DELETE /admin-api/aicrm/customer-group-assignment/delete?id={id}` - 删除客户群归属关系
- `GET /admin-api/aicrm/customer-group-assignment/export-excel` - 导出 Excel

#### 前端 API 函数
```typescript
// frontend/apps/web-antd/src/api/aicrm/customergroupassignment/index.ts
export namespace AicrmCustomerGroupAssignmentApi {
  export interface CustomerGroupAssignment {
    id: number;
    customerId?: number;
    groupId?: number;
    groupCode?: string;
    groupName?: string;
    groupCategory?: string;
    groupLevelType?: string;
    customerSource?: string;
    memberCount?: number;
    assignDate?: string | Dayjs;
    assignOperatorId?: number;
    status?: number;
    remark?: string;
    createTime?: string | Dayjs;
    creatorName?: string;
    creatorDeptName?: string;
    updateTime?: string | Dayjs;
    updaterName?: string;
    updaterDeptName?: string;
  }
}

export function getCustomerGroupAssignmentPage(params: PageParam) {...}
export function getCustomerGroupAssignment(id: number) {...}
export function createCustomerGroupAssignment(data: AicrmCustomerGroupAssignmentApi.CustomerGroupAssignment) {...}
export function updateCustomerGroupAssignment(data: AicrmCustomerGroupAssignmentApi.CustomerGroupAssignment) {...}
export function deleteCustomerGroupAssignment(id: number) {...}
```

### 字典配置

需要确认或创建以下字典:
1. **aicrm_customer_group_category** (客户群分类) - 需确认字典值
   - 示例数据中看到: `high_net_worth` (高净值)
   - 需确认完整的字典项列表

2. **aicrm_member_type** (成员类型) - 需确认字典值
   - retail: 零售客户群
   - corporate: 对公客户群
   - mixed: 混合客户群
   - 示例数据中看到: `retail`

3. **aicrm_customer_source** (客户来源) - 需确认字典值
   - 示例数据中看到: `offline_marketing` (线下营销)
   - 需确认完整的字典项列表

**注意**: 需要检查这些字典是否已在 `system_dict_type` 和 `system_dict_data` 表中存在

## 实现步骤 (基于现状调整)

### 阶段 1: 字典数据确认 ✅ 可跳过或快速确认
1. 检查 `aicrm_customer_group_category` 字典是否存在及其字典项
2. 检查 `aicrm_member_type` 字典是否存在及其字典项
3. 检查 `aicrm_customer_source` 字典是否存在及其字典项
4. 如缺失则创建相应字典数据

### 阶段 2: 后端联表查询实现 ⚠️ 核心任务
1. 扩展 `CustomerGroupAssignmentRespVO` 添加关联字段
   - groupCode, groupName, groupCategory, memberType, customerSource, memberCount
   - creatorName, updaterName, deptName
2. 在 `CustomerGroupAssignmentMapper` 接口中添加 `selectPageWithJoin` 方法
3. 在 `CustomerGroupAssignmentMapper.xml` 中实现联表查询 SQL
4. 在 `CustomerGroupAssignmentService` 接口中添加 `getCustomerGroupAssignmentPageWithJoin` 方法
5. 在 `CustomerGroupAssignmentServiceImpl` 中实现该方法
6. 修改 `CustomerGroupAssignmentController` 调用新的联表查询方法
7. 编译测试后端代码

### 阶段 3: 后端接口测试
1. 启动后端服务
2. 使用 Swagger 或 Postman 测试分页查询接口
3. 验证返回数据包含所有关联字段
4. 验证字典值、用户名称、部门名称正确显示

### 阶段 4: 前端 API 对接
1. 创建 `frontend/apps/web-antd/src/api/aicrm/customergroupassignment/index.ts`
2. 定义 `CustomerGroupAssignment` TypeScript 接口
3. 实现 API 函数 (getCustomerGroupAssignmentPage 等)

### 阶段 5: 前端组件实现
1. 创建 `CustomerGroupAssignmentGrid.vue` 组件
2. 配置 VxeTable 表格 (13个字段)
3. 实现字典格式化函数
4. 在 `management-info.vue` 最下方添加新的 Tab 区域
5. 设置表格高度为 400px

### 阶段 6: 样式优化和完整测试
1. 调整 Tab 样式与其他区域保持一致
2. 测试数据加载和分页功能
3. 测试字典值显示
4. 测试响应式布局
5. 端到端功能测试

## 测试计划

### 单元测试
- 后端 Service 层业务逻辑测试
- 后端 Mapper 联表查询测试
- 前端 API 调用测试

### 集成测试
- 归属客户群列表数据加载测试
- 分页功能测试
- 字典值显示测试
- 联表查询性能测试

### UI 测试
- 表格高度和布局测试
- 数据为空时的空状态测试
- 长文本字段显示测试
- 响应式布局测试

## 潜在风险

### 技术风险
1. **性能风险**：多次联表查询可能影响性能
   - 缓解措施：
     - 在关联字段上添加索引
     - 评估查询性能,必要时添加缓存
     - 使用 MyBatis Plus 的 LEFT JOIN 语法优化 SQL
2. **数据一致性**：客户群成员数需要定期同步
   - 缓解措施：使用数据库触发器或定时任务更新成员数

### 业务风险
1. **历史数据迁移**：需要从老系统迁移客户群归属数据
   - 缓解措施：编写数据迁移脚本,充分测试后执行

## 依赖关系
- 依赖系统用户服务 (`AdminUserService`) - 查询用户名称
- 依赖系统部门服务 (`DeptService`) - 查询部门名称
- 依赖字典服务 - 显示字典值
- 参考 `optimize-management-info-layout` 变更的实现方式

## 向后兼容性
- ✅ 向后兼容：新增功能,不影响现有功能

## 替代方案
1. **方案 1**：使用独立页面展示客户群归属信息（被否决：增加页面跳转,用户体验较差）
2. **方案 2**：使用弹窗展示客户群归属信息（被否决：信息量大,弹窗不适合）

## 未解决的问题
- [ ] 字典数据是否已完整创建？需要确认 `aicrm_customer_group_category`、`aicrm_member_type`、`aicrm_customer_source` 字典
- [ ] 客户群成员数如何实时更新？使用触发器还是定时任务？
- [ ] 创建机构和最近修改机构的逻辑是否正确？都使用 `crm_customer_group_info.dept_id` 字段吗？
- [ ] `system_users` 表的关联字段应该用 `username` 还是 `id`？需要确认 `creator` 和 `updater` 字段存储的是什么值

## 参考资料
- 老版本 CRM 数据库表: `zy_cust_group`、`zy_cust_group_correlation`
- 现有 `management-info.vue` 组件实现
- 现有 `CustomerGridAssignmentMapper.xml` 联表查询实现
- VxeTable 配置文档
- MyBatis Plus Join 文档
