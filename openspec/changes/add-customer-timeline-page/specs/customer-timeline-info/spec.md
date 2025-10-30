# Specification: 客户大事记信息页面

**Change ID:** add-customer-timeline-page
**Spec ID:** customer-timeline-info
**Version:** 1.0.0
**Status:** Draft
**Last Updated:** 2025-10-29

## 概述

本规范定义了零售客户360视图中"客户大事记信息"页面的功能需求。该页面用于展示客户的重要事件时间线，包括事件名称、类型、状态、日期等信息。

## ADDED Requirements

### REQ-1: 客户大事记信息页面展示

系统 SHALL 在零售客户详情页面中提供"客户大事记信息"页面,用于以表格形式展示客户的重要事件记录。

#### Scenario: 用户访问客户大事记信息页面

- **WHEN** 用户在零售客户详情页面点击"客户大事记信息"菜单项
- **THEN** 系统应显示客户大事记信息页面
- **AND** 页面应使用 VxeTable 组件展示事件列表
- **AND** 表格高度应为 400px

#### Scenario: 客户没有事件记录

- **WHEN** 客户没有任何事件记录
- **THEN** 系统应显示空数据提示信息
- **AND** 不应显示加载错误

---

### REQ-2: 事件列表数据加载

系统 SHALL 通过 `/aicrm/customer-important-event/page` API 加载客户的重要事件数据,支持分页查询。

#### Scenario: 加载客户事件列表

- **WHEN** 用户打开客户大事记信息页面
- **THEN** 系统应调用 `/aicrm/customer-important-event/page` API
- **AND** 请求参数应包含 customerId (当前客户ID)
- **AND** 请求参数应包含 pageNo (当前页码,从1开始)
- **AND** 请求参数应包含 pageSize (每页条数)
- **AND** 在表格中展示返回的事件记录

#### Scenario: 数据加载响应处理

- **WHEN** API 返回事件列表数据
- **THEN** 响应数据应包含 list 数组 (事件记录列表)
- **AND** 响应数据应包含 total 数字 (总记录数)
- **AND** 表格应根据 total 自动计算总页数

#### Scenario: 分页切换

- **WHEN** 用户点击分页器切换页码
- **THEN** 系统应重新调用 API 加载对应页的数据
- **AND** 响应时间应小于 1 秒

---

### REQ-3: 事件列表表格列配置

系统 SHALL 在表格中展示13个列,包括序号、事件名称、类型、状态、级别、日期、来源、维护人、维护时间、提醒信息、事件内容和备注。

#### Scenario: 展示基本事件信息列

- **WHEN** 系统加载到事件数据
- **THEN** 表格应包含以下基本信息列:
  - 序号 (自动生成,宽度70,固定在左侧)
  - 事件名称 (eventName,最小宽度150)
  - 事件日期 (eventDate,最小宽度120,格式 YYYY-MM-DD,固定在左侧)
  - 事件内容 (eventContent,最小宽度250,超长显示tooltip)

#### Scenario: 展示事件分类信息列

- **WHEN** 系统加载到事件数据
- **THEN** 表格应包含以下分类信息列:
  - 事件类型 (eventType,最小宽度120,使用字典 aicrm_event_type 转换)
  - 事件状态 (eventStatus,最小宽度100,使用字典 aicrm_event_status 转换)
  - 事件级别 (eventLevel,最小宽度100,使用字典 aicrm_event_level 转换)
  - 事件来源 (eventSource,最小宽度120,使用字典 aicrm_event_source 转换)

#### Scenario: 展示维护信息列

- **WHEN** 系统加载到事件数据
- **THEN** 表格应包含以下维护信息列:
  - 维护人 (maintainerName,最小宽度100)
  - 最近维护日期 (maintainTime,最小宽度170,格式 YYYY-MM-DD HH:mm:ss)

#### Scenario: 展示提醒信息列

- **WHEN** 系统加载到事件数据
- **THEN** 表格应包含以下提醒信息列:
  - 是否提醒 (remindFlag,最小宽度100,true显示"是",false显示"否",null显示"-")
  - 提醒时间 (remindTime,最小宽度170,格式 YYYY-MM-DD HH:mm:ss,空值显示"-")

#### Scenario: 展示备注列

- **WHEN** 系统加载到事件数据
- **THEN** 表格应包含备注列 (remark,最小宽度200,超长显示tooltip)

---

### REQ-4: 字典数据转换

系统 SHALL 使用字典服务将事件的类型、状态、级别、来源等枚举值转换为可读的中文标签,不得硬编码枚举值。

#### Scenario: 事件类型字典转换

- **WHEN** 系统展示 eventType 字段
- **THEN** 应使用字典类型 aicrm_event_type 转换显示
- **AND** 字典值存在时显示对应的中文标签
- **AND** 字典值不存在时显示原始值
- **AND** 值为 null/undefined 时显示 "-"

#### Scenario: 事件状态字典转换

- **WHEN** 系统展示 eventStatus 字段
- **THEN** 应使用字典类型 aicrm_event_status 转换显示
- **AND** 字典值存在时显示对应的中文标签 (如"未发生"、"进行中"、"已完成")
- **AND** 字典值不存在时显示原始值
- **AND** 值为 null/undefined 时显示 "-"

#### Scenario: 事件级别字典转换

- **WHEN** 系统展示 eventLevel 字段
- **THEN** 应使用字典类型 aicrm_event_level 转换显示
- **AND** 字典值存在时显示对应的中文标签 (如"重要"、"一般"、"普通")
- **AND** 字典值不存在时显示原始值
- **AND** 值为 null/undefined 时显示 "-"

#### Scenario: 事件来源字典转换

- **WHEN** 系统展示 eventSource 字段
- **THEN** 应使用字典类型 aicrm_event_source 转换显示
- **AND** 字典值存在时显示对应的中文标签 (如"客户告知"、"系统识别"、"客户经理录入")
- **AND** 字典值不存在时显示原始值
- **AND** 值为 null/undefined 时显示 "-"

---

### REQ-5: 日期时间格式化

系统 SHALL 将事件的日期和时间字段格式化为可读的格式,空值应显示 "-"。

#### Scenario: 事件日期格式化

- **WHEN** 系统展示 eventDate 字段
- **THEN** 应格式化为 YYYY-MM-DD 格式 (如 2025-10-29)
- **AND** 值为 null/undefined 时显示 "-"

#### Scenario: 日期时间字段格式化

- **WHEN** 系统展示 maintainTime 或 remindTime 字段
- **THEN** 应格式化为 YYYY-MM-DD HH:mm:ss 格式 (如 2025-10-29 14:30:00)
- **AND** 值为 null/undefined 时显示 "-"

---

### REQ-6: 布尔值格式化

系统 SHALL 将布尔类型字段 (remindFlag) 转换为中文 "是" 或 "否",空值显示 "-"。

#### Scenario: 是否提醒字段显示

- **WHEN** 系统展示 remindFlag 字段
- **THEN** 值为 true 时应显示 "是"
- **AND** 值为 false 时应显示 "否"
- **AND** 值为 null/undefined 时应显示 "-"

---

### REQ-7: 长文本处理

系统 SHALL 对超长文本字段 (事件内容、备注) 进行截断显示,并在鼠标悬停时通过 tooltip 显示完整内容。

#### Scenario: 长文本截断和 tooltip

- **WHEN** eventContent 或 remark 字段内容超过列宽
- **THEN** 应截断显示文本
- **AND** 在截断位置显示省略号
- **AND** 用户鼠标悬停时显示 tooltip
- **AND** tooltip 应包含完整的文本内容

---

### REQ-8: 表格工具栏功能

系统 SHALL 在表格工具栏中提供刷新按钮,允许用户重新加载数据。

#### Scenario: 刷新表格数据

- **WHEN** 用户点击表格工具栏的刷新按钮
- **THEN** 系统应重新调用 API 加载当前页的数据
- **AND** 保持当前的分页状态
- **AND** 刷新时间应小于 2 秒

---

### REQ-9: 组件集成到零售客户详情页

系统 SHALL 将客户大事记信息组件集成到零售客户详情页的菜单项中,替换原有的占位组件。

#### Scenario: 导入和注册组件

- **WHEN** 系统加载零售客户详情页 (retail-customer/detail.vue)
- **THEN** 应导入 TimelineInfo 组件 (从 './pages/timeline-info.vue')
- **AND** 在 menuItems 数组中将 key 为 'events' 的项的 component 从 Placeholder 替换为 TimelineInfo

#### Scenario: 传递 customerId 属性

- **WHEN** 用户点击"客户大事记信息"菜单项
- **THEN** 系统应渲染 TimelineInfo 组件
- **AND** 应将当前客户的 customerId 作为 prop 传递给 TimelineInfo 组件
- **AND** TimelineInfo 组件应使用该 customerId 加载事件数据

---

### REQ-10: 组件暴露刷新方法

系统 SHALL 在客户大事记信息组件中暴露 refresh 方法,允许父组件主动触发数据刷新。

#### Scenario: 暴露 refresh 方法

- **WHEN** TimelineInfo 组件被创建
- **THEN** 应使用 defineExpose 暴露 refresh 方法
- **AND** refresh 方法应调用表格的 query 方法重新加载数据

#### Scenario: 父组件调用 refresh

- **WHEN** 父组件调用 TimelineInfo 组件的 refresh 方法
- **THEN** 表格应重新加载当前客户的事件数据
- **AND** 保持当前的分页状态

---

## 性能要求

### PERF-1: 页面加载性能

系统 MUST 确保客户大事记信息页面的加载和响应性能满足用户体验要求。

#### Scenario: 初始加载性能

- **WHEN** 用户首次打开客户大事记信息页面
- **THEN** 数据加载和渲染时间应小于 2 秒
- **AND** 表格应正确显示所有列
- **AND** 不应出现布局抖动

#### Scenario: 分页切换性能

- **WHEN** 用户点击分页器切换页码
- **THEN** 页面切换响应时间应小于 1 秒
- **AND** 切换过程应流畅,无卡顿

---

## 数据约束

### DATA-1: 字典数据依赖

系统 MUST 确保以下字典类型在系统字典管理中存在且配置正确:
- `aicrm_event_status`: AICRM 事件状态
- `aicrm_event_type`: AICRM 事件类型
- `aicrm_event_level`: AICRM 事件级别
- `aicrm_event_source`: AICRM 事件来源

#### Scenario: 字典数据存在性检查

- **WHEN** 系统启动或部署时
- **THEN** 应检查上述4个字典类型是否存在
- **AND** 每个字典类型应至少包含一个字典项
- **AND** 字典名称应以 "AICRM" 开头
- **AND** 字典类型应以 "aicrm_" 开头

---

## 技术约束

### TECH-1: 前端技术栈

系统 MUST 使用以下技术实现客户大事记信息页面:
- Vue 3.5.17 (Composition API)
- TypeScript 5.8.3
- VxeTable (通过 @vben/vxe-table adapter)
- Ant Design Vue 4.2.6

#### Scenario: 组件技术规范

- **WHEN** 开发 TimelineInfo 组件
- **THEN** 必须使用 `<script lang="ts" setup>` 语法
- **AND** 必须使用 TypeScript 类型定义所有变量和函数
- **AND** 必须使用 useVbenVxeGrid composable 创建表格
- **AND** 必须遵循项目 ESLint 和 Prettier 规范

---

## 代码规范

### CODE-1: 代码风格一致性

系统 MUST 确保新增代码与现有代码风格保持一致,特别是参考 management-info.vue 的实现方式。

#### Scenario: 参考现有实现

- **WHEN** 实现 timeline-info.vue 组件
- **THEN** 应参考 frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/management-info.vue 的代码结构
- **AND** 表格配置应与 management-info.vue 中的表格保持相似结构
- **AND** 辅助函数 (getDict, formatBoolean) 应与 management-info.vue 保持一致
- **AND** 表格高度应为 400px (与其他 tab 页保持一致)

---

## 验收标准

### ACC-1: 功能完整性

系统 MUST 满足以下功能验收标准。

#### Scenario: 功能验收检查清单

- **WHEN** 完成客户大事记信息页面开发
- **THEN** 应满足以下所有验收标准:
  - [ ] 页面能正确加载并显示客户大事记列表
  - [ ] 13个表格列全部正确显示
  - [ ] 分页功能正常工作
  - [ ] 刷新按钮能重新加载数据
  - [ ] 字典值正确转换显示 (事件类型、状态、级别、来源)
  - [ ] 日期时间格式化正确
  - [ ] 是否提醒字段显示"是/否"
  - [ ] 长文本内容 (事件内容、备注) 支持 tooltip
  - [ ] 无 TypeScript 类型错误
  - [ ] 无 ESLint 错误
  - [ ] 在零售客户详情页点击"客户大事记信息"菜单项能正确显示页面
  - [ ] customerId prop 正确传递
  - [ ] 数据正确过滤到当前客户

---

## 附录

### A1: API 接口定义

**接口路径**: `/aicrm/customer-important-event/page`
**请求方法**: GET
**请求参数**:
```typescript
{
  customerId: number;   // 客户ID
  pageNo: number;       // 页码,从 1 开始
  pageSize: number;     // 每页条数
}
```

**响应格式**:
```typescript
{
  list: CustomerImportantEventRespVO[];  // 事件列表
  total: number;                          // 总记录数
}
```

### A2: 数据模型

**CustomerImportantEventRespVO**:
```typescript
{
  id: number;                    // 主键ID
  customerId: number;            // 客户ID
  eventName: string;             // 事件名称
  eventStatus: string;           // 事件状态 (字典值)
  eventDate: string;             // 事件日期 (YYYY-MM-DD)
  eventContent?: string;         // 事件内容
  maintainerId?: number;         // 维护人ID
  maintainerName?: string;       // 维护人姓名
  maintainTime?: string;         // 最近维护日期 (YYYY-MM-DD HH:mm:ss)
  eventType?: string;            // 事件类型 (字典值)
  eventLevel?: string;           // 事件级别 (字典值)
  eventSource?: string;          // 事件来源 (字典值)
  remindFlag?: boolean;          // 是否提醒
  remindTime?: string;           // 提醒时间 (YYYY-MM-DD HH:mm:ss)
  attachmentUrl?: string;        // 附件地址
  remark?: string;               // 备注
  createTime: string;            // 创建时间 (YYYY-MM-DD HH:mm:ss)
}
```

### A3: 文件路径清单

**新增文件**:
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/timeline-info.vue`

**修改文件**:
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue`

**依赖文件** (已存在):
- `frontend/apps/web-antd/src/api/aicrm/customerimportantevent/index.ts`
- `backend/ynet-module-crm/.../customerimportantevent/CustomerImportantEventController.java`

---

## 变更历史

| 版本 | 日期 | 作者 | 变更说明 |
|-----|------|------|---------|
| 1.0.0 | 2025-10-29 | AI Assistant | 初始版本,定义客户大事记信息页面需求规范 |
