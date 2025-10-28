# Spec: 客户列表查看能力

**能力 ID**: `customer-list-view`
**变更 ID**: `implement-customer-view-basic`
**类型**: Enhancement
**状态**: Proposed

---

## 概述

客户列表查看能力提供了 CRM 系统中客户信息的列表展示和搜索筛选功能。用户可以通过列表快速浏览客户信息，并通过多种条件进行筛选，找到目标客户。

**核心价值**:
- 提供完整的客户信息概览
- 支持多条件快速筛选
- 使用系统字典管理枚举值，便于维护
- 优化数据展示，提升可读性

**注意**: 本能力**仅关注**客户列表的查看和查询功能，不包括创建、编辑、删除、导出等操作功能。

---

## ADDED Requirements

### REQ-CLV-001: 客户列表字段展示

**优先级**: High
**类型**: Functional

客户列表必须展示以下关键字段，以便用户快速了解客户的基本情况和重要信息。

#### Scenario: 展示基本信息字段

**Given**: 用户有权限访问客户列表页面
**When**: 用户访问客户列表页 `/aicrm/customer`
**Then**:
- 列表展示以下基本字段:
  - 客户编号 (customerNo)
  - 客户类型 (customerType) - 使用字典 `crm_customer_type` 转换显示
    - 1 -> "零售客户"
    - 2 -> "对公客户"
  - 客户名称 (customerName)
  - 客户等级 (customerLevel) - 使用字典 `crm_customer_level` 转换显示
  - 客户状态 (customerStatus) - 使用字典 `crm_customer_status` 转换显示为带颜色的徽章
    - 1 (正常): 绿色徽章
    - 2 (冻结): 黄色徽章
    - 3 (注销): 灰色徽章

**注意**: 不展示客户 ID (主键)

#### Scenario: 展示业务信息字段

**Given**: 用户有权限访问客户列表页面
**When**: 用户访问客户列表页
**Then**:
- 列表展示以下业务字段:
  - 客户来源 (customerSource) - 使用字典 `crm_customer_source` 转换显示
  - 客户标签 (customerTag) - 以标签组形式展示（多个标签用逗号分隔）
  - 是否优质客户 (isHighQuality) - 显示为徽章（是: 金色，否: 默认）
  - 是否重要客户 (isImportant) - 显示为徽章（是: 红色，否: 默认）
  - 信用等级 (creditLevel) - 使用字典 `crm_credit_level` 转换显示

#### Scenario: 展示时间信息字段

**Given**: 用户有权限访问客户列表页面
**When**: 用户访问客户列表页
**Then**:
- 列表展示以下时间字段:
  - 创建时间 (createTime) - 格式: `YYYY-MM-DD HH:mm:ss`
  - 更新时间 (updateTime) - 格式: `YYYY-MM-DD HH:mm:ss`

**验收标准**:
- 列表展示至少 10 个不同的字段（不包括客户 ID）
- 所有枚举值通过系统字典服务转换为可读文本
- 布尔值使用徽章形式展示
- 日期时间格式统一
- 列宽自适应，长文本自动省略并支持悬停提示

---

### REQ-CLV-002: 客户列表搜索和筛选

**优先级**: High
**类型**: Functional

客户列表必须提供多种搜索和筛选条件，帮助用户快速定位目标客户。

#### Scenario: 按客户名称搜索

**Given**: 用户在客户列表页
**When**: 用户在搜索框输入客户名称关键词（如 "张三"）
**And**: 点击"搜索"按钮
**Then**:
- 列表只显示客户名称包含 "张三" 的记录（模糊匹配）
- 列表上方显示搜索结果数量
- 搜索条件保留在搜索框中

#### Scenario: 按客户编号搜索

**Given**: 用户在客户列表页
**When**: 用户在"客户编号"输入框输入编号（如 "CUS20231001"）
**And**: 点击"搜索"按钮
**Then**:
- 列表只显示客户编号为 "CUS20231001" 的记录（精确匹配）

#### Scenario: 按客户类型筛选

**Given**: 用户在客户列表页
**When**: 用户在"客户类型"下拉框选择 "零售客户"
**And**: 点击"搜索"按钮
**Then**:
- 下拉框选项从字典 `crm_customer_type` 加载
- 列表只显示客户类型为 "零售客户" (customerType = 1) 的记录

#### Scenario: 按客户状态筛选

**Given**: 用户在客户列表页
**When**: 用户在"客户状态"下拉框选择 "正常"
**And**: 点击"搜索"按钮
**Then**:
- 下拉框选项从字典 `crm_customer_status` 加载
- 列表只显示客户状态为 "正常" (customerStatus = 1) 的记录

#### Scenario: 按客户等级筛选

**Given**: 用户在客户列表页
**When**: 用户在"客户等级"下拉框选择 "VIP"
**And**: 点击"搜索"按钮
**Then**:
- 下拉框选项从字典 `crm_customer_level` 加载
- 列表只显示客户等级为 "VIP" 的记录

#### Scenario: 按客户来源筛选

**Given**: 用户在客户列表页
**When**: 用户在"客户来源"下拉框选择筛选项
**And**: 点击"搜索"按钮
**Then**:
- 下拉框选项从字典 `crm_customer_source` 加载
- 列表只显示匹配的记录

#### Scenario: 按是否优质客户筛选

**Given**: 用户在客户列表页
**When**: 用户开启"是否优质客户"开关
**And**: 点击"搜索"按钮
**Then**:
- 列表只显示 isHighQuality = true 的记录

#### Scenario: 按创建时间范围筛选

**Given**: 用户在客户列表页
**When**: 用户在"创建时间"日期范围选择器选择 "2023-10-01" 到 "2023-10-31"
**And**: 点击"搜索"按钮
**Then**:
- 列表只显示创建时间在 2023-10-01 00:00:00 到 2023-10-31 23:59:59 之间的记录

#### Scenario: 组合多个筛选条件

**Given**: 用户在客户列表页
**When**: 用户同时选择:
  - 客户类型: "对公客户"
  - 客户状态: "正常"
  - 是否重要客户: 开启
  - 创建时间: "2023-01-01" 到 "2023-12-31"
**And**: 点击"搜索"按钮
**Then**:
- 列表只显示同时满足以上所有条件的记录
- 搜索条件以标签形式显示在列表上方
- 每个标签可单独关闭以移除该条件

#### Scenario: 重置搜索条件

**Given**: 用户已应用多个搜索条件
**When**: 用户点击"重置"按钮
**Then**:
- 所有搜索条件清空
- 列表恢复显示所有客户记录
- 搜索表单恢复默认状态

**验收标准**:
- 所有搜索条件正常工作
- 组合搜索结果准确
- 搜索响应时间 < 500ms（1000 条记录内）
- 搜索条件在切换页面后保持
- 重置功能正确清空所有条件
- 所有下拉框选项从系统字典加载，而非硬编码

---

### REQ-CLV-003: 客户列表查看操作

**优先级**: High
**类型**: Functional

客户列表必须提供查看客户详情的功能，并根据客户类型跳转到不同的详情页面。

#### Scenario: 查看零售客户详情

**Given**: 用户有 `crm:retail-customer:query` 权限
**And**: 列表中存在一条零售客户记录（customerType = 1）
**When**: 用户点击该记录的"查看"按钮
**Then**:
- 跳转到零售客户详情页 `/aicrm/retail-customer/detail/:id`
- 详情页调用 `/crm/retail-customer/get` API 获取数据
- 详情页展示零售客户的完整信息

#### Scenario: 查看对公客户详情

**Given**: 用户有 `crm:company-customer:query` 权限
**And**: 列表中存在一条对公客户记录（customerType = 2）
**When**: 用户点击该记录的"查看"按钮
**Then**:
- 跳转到对公客户详情页 `/aicrm/company-customer/detail/:id`
- 详情页调用 `/crm/company-customer/get` API 获取数据
- 详情页展示对公客户的完整信息

#### Scenario: 根据客户类型智能跳转

**Given**: 用户在客户列表页
**When**: 用户点击任意客户记录的"查看"按钮
**Then**:
- 前端根据该记录的 `customerType` 字段判断客户类型
- 如果 `customerType = 1`（零售客户），跳转到 `/aicrm/retail-customer/detail/:id`
- 如果 `customerType = 2`（对公客户），跳转到 `/aicrm/company-customer/detail/:id`

**验收标准**:
- "查看"按钮根据客户类型正确跳转
- 零售客户和对公客户使用不同的详情页和 API
- 权限控制正确（零售和对公客户有独立的查询权限）
- 跳转流畅，响应时间 < 500ms

---

### REQ-CLV-004: 客户列表数据权限

**优先级**: High
**类型**: Security

客户列表必须遵守系统的数据权限规则，确保用户只能查看其有权限的客户数据。

#### Scenario: 按部门过滤客户数据

**Given**: 系统启用了数据权限控制
**And**: 用户 A 属于部门 X，有部门级数据权限
**When**: 用户 A 访问客户列表页
**Then**:
- 列表只显示 deptId = X 或其子部门的客户记录
- 其他部门的客户记录不可见

#### Scenario: 按用户过滤客户数据

**Given**: 系统启用了数据权限控制
**And**: 用户 B 有用户级数据权限（只能查看自己创建的数据）
**When**: 用户 B 访问客户列表页
**Then**:
- 列表只显示创建人为用户 B 的客户记录
- 其他用户创建的客户记录不可见

#### Scenario: 超级管理员查看所有数据

**Given**: 用户 C 是超级管理员 (admin)
**When**: 用户 C 访问客户列表页
**Then**:
- 列表显示所有客户记录
- 不受数据权限限制

**验收标准**:
- 数据权限过滤在后端实现（MyBatis Plus 拦截器）
- 前端无法通过修改请求参数绕过权限控制
- 数据权限规则与系统其他模块一致
- 权限变更后立即生效（无需重启）

---

### REQ-CLV-005: 客户列表性能要求

**优先级**: Medium
**类型**: Non-Functional

客户列表必须满足性能要求，确保在数据量增长时仍能快速响应。

#### Scenario: 快速加载列表数据

**Given**: 数据库中有 1000 条客户记录
**When**: 用户访问客户列表页
**Then**:
- 列表首屏加载时间 < 1s
- 列表数据渲染流畅，无明显卡顿

#### Scenario: 快速响应搜索请求

**Given**: 数据库中有 10000 条客户记录
**When**: 用户使用多个条件进行搜索
**Then**:
- 搜索响应时间 < 2s
- 搜索结果准确无误

**验收标准**:
- 列表首屏加载时间 < 1s（1000 条记录）
- 列表翻页响应时间 < 500ms
- 搜索响应时间 < 2s（10000 条记录）
- 前端列表支持虚拟滚动（如果单页数据量 > 100）

---

### REQ-CLV-006: 客户列表用户体验

**优先级**: Medium
**类型**: Non-Functional

客户列表必须提供良好的用户体验，界面友好、操作便捷。

#### Scenario: 响应式布局

**Given**: 用户使用不同尺寸的设备访问客户列表
**When**: 用户在桌面浏览器（宽度 > 1200px）访问
**Then**:
- 列表展示所有字段列
- 操作按钮横向排列

**When**: 用户在平板设备（宽度 768px - 1200px）访问
**Then**:
- 列表自动隐藏次要字段列
- 提供"列设置"功能，用户可自定义显示列

**When**: 用户在手机设备（宽度 < 768px）访问
**Then**:
- 列表切换为卡片视图
- 每张卡片展示关键信息
- 操作按钮垂直排列

#### Scenario: 加载状态提示

**Given**: 用户在客户列表页
**When**: 列表数据正在加载
**Then**:
- 显示骨架屏或加载动画
- 禁用搜索按钮和操作按钮

**When**: 列表数据加载完成
**Then**:
- 隐藏加载状态
- 启用搜索按钮和操作按钮
- 显示数据或"暂无数据"提示

#### Scenario: 错误处理

**Given**: 用户在客户列表页
**When**: 后端 API 返回错误（如 500 Internal Server Error）
**Then**:
- 显示友好的错误提示消息
- 提供"重试"按钮
- 错误详情记录到前端日志

**When**: 用户点击"重试"按钮
**Then**:
- 重新发起请求
- 显示加载状态

#### Scenario: 空数据状态

**Given**: 数据库中没有任何客户记录
**When**: 用户访问客户列表页
**Then**:
- 显示空状态插图
- 显示提示文本: "暂无客户数据"

**验收标准**:
- 界面在 Chrome、Firefox、Safari、Edge 浏览器中显示一致
- 响应式布局在不同设备上正常工作
- 加载状态、错误状态、空状态的提示清晰友好
- 所有交互操作有即时反馈（按钮点击、加载状态等）

---

### REQ-CLV-007: 字典数据管理

**优先级**: High
**类型**: Functional

客户列表所需的所有枚举值必须通过系统字典管理，而非硬编码。

#### Scenario: 字典数据初始化

**Given**: 系统首次部署或数据库为空
**When**: 执行数据库初始化脚本
**Then**:
- 系统字典表中创建以下字典类型:
  - `crm_customer_type`: 客户类型
  - `crm_customer_status`: 客户状态
  - `crm_customer_level`: 客户等级
  - `crm_customer_source`: 客户来源
  - `crm_credit_level`: 信用等级

**And**: 每个字典类型包含对应的字典数据项

#### Scenario: 后端使用字典转换

**Given**: CustomerRespVO 定义了需要字典转换的字段
**When**: 后端返回客户列表数据
**Then**:
- 字段使用 `@DictFormat` 注解标注字典类型
  - `customerType` 使用 `@DictFormat("crm_customer_type")`
  - `customerStatus` 使用 `@DictFormat("crm_customer_status")`
  - `customerLevel` 使用 `@DictFormat("crm_customer_level")`
  - `customerSource` 使用 `@DictFormat("crm_customer_source")`
  - `creditLevel` 使用 `@DictFormat("crm_credit_level")`

#### Scenario: 前端加载字典数据

**Given**: 用户访问客户列表页
**When**: 页面初始化
**Then**:
- 前端从 `/system/dict-data/list-all-simple` 接口加载所有字典数据
- 字典数据缓存到前端状态管理（如 Pinia）
- 筛选条件下拉框选项从字典数据填充
- 列表字段值根据字典数据转换显示

#### Scenario: 字典数据可维护性

**Given**: 管理员需要新增或修改字典数据
**When**: 管理员通过系统管理 -> 字典管理 修改字典
**Then**:
- 字典修改后，前端自动或手动刷新字典缓存
- 列表展示和筛选条件自动使用最新的字典数据
- 无需修改代码或重新部署

**验收标准**:
- 所有枚举值字段都使用 `@DictFormat` 注解
- 前端不包含硬编码的枚举值映射
- 字典数据初始化 SQL 脚本完整
- 字典修改后，前端能够及时同步最新数据

---

## 技术实现说明

### 后端实现

**涉及文件**:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/customer/CustomerController.java`
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/customer/vo/CustomerRespVO.java`
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/customer/vo/CustomerPageReqVO.java`
- `backend/sql/mysql/crm_dict_data.sql`（新增字典数据脚本）

**关键点**:
- CustomerRespVO 必须包含所有展示字段，但**不包括**客户 ID
- 所有枚举字段使用 `@DictFormat` 注解标注字典类型
- CustomerPageReqVO 必须支持所有搜索条件
- 使用 MyBatis Plus 进行分页查询
- 使用 MyBatis Plus 拦截器实现数据权限
- 使用 MapStruct 进行 DO 到 VO 的转换

**字典类型定义**:
```java
// 在 CRM 模块新增字典类型常量
public interface CrmDictTypeConstants {
    String CUSTOMER_TYPE = "crm_customer_type";     // 客户类型
    String CUSTOMER_STATUS = "crm_customer_status"; // 客户状态
    String CUSTOMER_LEVEL = "crm_customer_level";   // 客户等级
    String CUSTOMER_SOURCE = "crm_customer_source"; // 客户来源
    String CREDIT_LEVEL = "crm_credit_level";       // 信用等级
}
```

### 前端实现

**涉及文件**:
- `frontend/apps/web-antd/src/views/aicrm/customer/index.vue` - 列表页面
- `frontend/apps/web-antd/src/views/aicrm/customer/data.ts` - 列表列定义和搜索表单
- `frontend/apps/web-antd/src/api/aicrm/customer/index.ts` - API 接口定义

**关键点**:
- 使用 vxe-table 组件库构建列表
- 使用 Ant Design Vue 组件库构建 UI
- 使用 TypeScript 定义类型
- 使用 Pinia 管理字典数据缓存
- 列表字段值通过字典数据转换显示
- 筛选条件下拉框从字典数据加载选项
- **不展示**客户 ID 列

### 数据库脚本

**新增文件**:
- `backend/sql/mysql/crm_dict_data.sql`

**脚本内容**:
- 插入字典类型记录（system_dict_type 表）
- 插入字典数据记录（system_dict_data 表）
- 包含所有客户相关的字典类型和数据项

---

## 相关能力

- **customer-detail-view**: 客户详情查看能力（本变更的另一个能力）
  - 从列表跳转到详情页

---

## 测试要求

### 单元测试

**后端**:
- 测试 `CustomerService.getCustomerPage()` 方法
- 测试各种搜索条件组合
- 测试分页逻辑
- 测试数据权限过滤
- 测试字典注解是否正确生效

**前端**:
- 测试列表组件渲染
- 测试搜索表单提交
- 测试字典数据加载和转换
- 测试"查看"按钮点击

### 集成测试

- 测试完整的用户流程: 访问列表 -> 搜索 -> 查看详情
- 测试权限控制: 不同权限用户看到的数据不同
- 测试字典数据修改后前端能够同步

### E2E 测试

- 测试端到端流程，覆盖主要场景
- 测试在不同浏览器中的兼容性

---

## 文档和培训

- 更新 API 文档（Swagger）
- 更新用户使用手册（如有）
- 字典管理员培训材料

---

## 版本历史

- **v1.0** (2025-10-28): 初始版本，定义客户列表查看的核心需求
- **v1.1** (2025-10-28): 根据反馈修改
  - 移除创建、编辑、删除、导出等操作功能
  - 移除客户 ID 展示
  - 改用系统字典服务管理枚举值
  - 新增字典数据管理需求
