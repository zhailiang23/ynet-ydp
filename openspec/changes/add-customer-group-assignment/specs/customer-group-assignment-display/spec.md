# Spec: 归属客户群信息展示

## Capability
customer-group-assignment-display

## Status
proposed

## ADDED Requirements

### Requirement: 在管理信息页面显示归属客户群列表

The system SHALL display customer group assignment information in the management info page.

#### Scenario: 用户查看客户的归属客户群列表

**Given** 用户已登录系统并有权查看客户信息
**And** 客户存在归属客户群关系
**When** 用户打开客户详情页面的"管理信息" Tab
**And** 滚动到页面最下方的"归属客户群"区域
**Then** 系统 SHALL 显示一个包含"归属客户群列表" Tab 的区域
**And** 该区域 SHALL 位于"归属网格关系"区域的正下方
**And** 两个区域之间 SHALL 有 16px 的垂直间距
**And** "归属客户群列表" Tab SHALL 默认激活

#### Scenario: 归属客户群列表表格显示完整字段

**Given** 用户在管理信息页面的"归属客户群列表" Tab
**And** 该客户有归属客户群关系数据
**When** 表格加载完成
**Then** 表格 SHALL 显示以下列:
- 序号 (自动编号,宽度 70px,固定在左侧)
- 客户群编号 (最小宽度 120px,固定在左侧)
- 客户群名称 (最小宽度 180px)
- 客户群分类 (最小宽度 120px,显示字典值)
- 群级别类型 (最小宽度 120px,显示字典值)
- 客户来源 (最小宽度 120px,显示字典值)
- 客户群成员数 (最小宽度 100px)
- 创建日期 (最小宽度 150px,格式: YYYY-MM-DD HH:mm:ss)
- 创建人 (最小宽度 120px)
- 创建机构 (最小宽度 180px,超出显示 tooltip)
- 最近修改日期 (最小宽度 150px,格式: YYYY-MM-DD HH:mm:ss)
- 最近修改人 (最小宽度 120px)
- 最近修改机构 (最小宽度 180px,超出显示 tooltip)

**And** 表格高度 SHALL 为 400px
**And** 表格 SHALL 支持分页,每页默认显示 10 条记录
**And** 表格 SHALL 支持行高亮 hover 效果

#### Scenario: 归属客户群列表为空时显示空状态

**Given** 用户在管理信息页面的"归属客户群列表" Tab
**And** 该客户没有归属客户群关系数据
**When** 表格加载完成
**Then** 表格 SHALL 显示空状态
**And** 空状态 SHALL 显示"暂无数据"提示

### Requirement: 归属客户群列表支持刷新功能

The system SHALL provide refresh functionality for customer group assignment list.

#### Scenario: 用户刷新归属客户群列表

**Given** 用户在管理信息页面的"归属客户群列表" Tab
**When** 用户点击表格工具栏的刷新按钮
**Then** 系统 SHALL 重新加载表格数据
**And** 表格 SHALL 显示加载中状态
**And** 加载完成后 SHALL 显示最新数据

### Requirement: 归属客户群列表显示字典值

The system SHALL display dictionary labels instead of codes for specific fields.

#### Scenario: 客户群分类显示字典值

**Given** 归属客户群记录包含客户群分类代码
**When** 表格显示该记录
**Then** "客户群分类"列 SHALL 显示字典 `aicrm_customer_group_category` 对应的标签文本
**And** 不 SHALL 显示原始代码值

#### Scenario: 群级别类型显示字典值

**Given** 归属客户群记录包含群级别类型代码
**When** 表格显示该记录
**Then** "群级别类型"列 SHALL 显示字典 `aicrm_customer_group_level` 对应的标签文本
**And** 不 SHALL 显示原始代码值

#### Scenario: 客户来源显示字典值

**Given** 归属客户群记录包含客户来源代码
**When** 表格显示该记录
**Then** "客户来源"列 SHALL 显示字典 `aicrm_customer_source` 对应的标签文本
**And** 不 SHALL 显示原始代码值

### Requirement: 归属客户群列表显示关联数据

The system SHALL display associated user and department names through database joins.

#### Scenario: 显示创建人名称

**Given** 归属客户群记录包含创建人ID
**And** 创建人ID在系统用户表中存在
**When** 表格显示该记录
**Then** "创建人"列 SHALL 显示该用户的昵称 (nickname)
**And** 不 SHALL 显示用户ID

#### Scenario: 显示创建机构名称

**Given** 归属客户群记录包含创建机构ID
**And** 创建机构ID在系统部门表中存在
**When** 表格显示该记录
**Then** "创建机构"列 SHALL 显示该部门的名称
**And** 不 SHALL 显示部门ID

#### Scenario: 显示最近修改人名称

**Given** 归属客户群记录包含最近修改人ID
**And** 最近修改人ID在系统用户表中存在
**When** 表格显示该记录
**Then** "最近修改人"列 SHALL 显示该用户的昵称 (nickname)
**And** 不 SHALL 显示用户ID

#### Scenario: 显示最近修改机构名称

**Given** 归属客户群记录包含最近修改机构ID
**And** 最近修改机构ID在系统部门表中存在
**When** 表格显示该记录
**Then** "最近修改机构"列 SHALL 显示该部门的名称
**And** 不 SHALL 显示部门ID

#### Scenario: 关联数据不存在时的处理

**Given** 归属客户群记录的关联ID在关联表中不存在
**When** 表格显示该记录
**Then** 对应的关联字段 SHALL 显示"-"
**And** 不 SHALL 显示错误或空白

### Requirement: 归属客户群列表支持分页

The system SHALL provide pagination functionality for customer group assignment list.

#### Scenario: 用户切换页码

**Given** 归属客户群列表总数超过每页显示数量
**When** 用户点击分页组件的下一页按钮
**Then** 系统 SHALL 加载下一页数据
**And** 表格 SHALL 显示新的数据记录
**And** 分页组件 SHALL 显示正确的当前页码

#### Scenario: 用户修改每页显示数量

**Given** 用户在归属客户群列表页面
**When** 用户在分页组件中选择新的每页显示数量
**Then** 系统 SHALL 重新加载数据
**And** 表格 SHALL 按新的每页数量显示记录
**And** 当前页码 SHALL 重置为第 1 页

### Requirement: Tab 样式与其他 Tab 保持一致

The customer group assignment tabs SHALL maintain consistent styling with other tab sections.

#### Scenario: Tab 样式与归属网格关系 Tab 一致

**Given** 用户在管理信息页面
**When** 用户查看"归属客户群"区域的 Tab
**Then** Tab 的样式 SHALL 与"归属网格关系"区域的 Tab 保持一致
**And** 激活态 Tab SHALL 显示白色背景
**And** 非激活态 Tab SHALL 显示透明背景
**And** Tab 导航栏底部 SHALL 没有边框线
**And** Tab 内容区域 SHALL 没有边框

#### Scenario: Tab 区域容器样式一致

**Given** 用户在管理信息页面
**When** 用户查看"归属客户群"区域
**Then** 该区域的容器样式 SHALL 使用 `tab-section` 类
**And** 该区域 SHALL 与其他 Tab 区域使用相同的 CSS 样式
**And** Tab 组件 SHALL 使用 `section-tabs` 类

### Requirement: 归属客户群列表按客户ID过滤

The system SHALL filter customer group assignments by customer ID.

#### Scenario: 查询特定客户的归属客户群

**Given** 用户在客户详情页面的管理信息 Tab
**And** 当前客户ID为 123
**When** 系统加载归属客户群列表
**Then** 后端接口 SHALL 接收参数 `customerId=123`
**And** 查询结果 SHALL 只包含该客户的归属客户群关系
**And** 不 SHALL 包含其他客户的归属客户群关系
