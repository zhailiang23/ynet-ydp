# Proposal: 实现零售客户账户信息页面

## 概述

为零售客户详情页面添加"账户信息"模块，展示客户的 8 类账户信息（存款、贷款、理财、基金、信托、保险、贵金属、信用卡）。每类账户通过独立的 Tab 页展示，支持表格和卡片两种视图模式切换。

## 背景

后端已实现 8 类客户账户的完整 CRUD 功能和数据表：
- 存款账户信息 (CustomerAccountDeposit)
- 贷款账户信息 (CustomerAccountLoan)
- 理财账户信息 (CustomerAccountWealth)
- 基金账户信息 (CustomerAccountFund)
- 信托账户信息 (CustomerAccountTrust)
- 保险账户信息 (CustomerAccountInsurance)
- 贵金属账户信息 (CustomerAccountMetal)
- 信用卡账户信息 (CustomerAccountCreditcard)

前端需要开发对应的展示页面，与后端 API 对接，并集成到零售客户 360 视图的左侧导航菜单中。

## 目标

1. 开发零售客户账户信息主页面 (`account-info.vue`)
2. 实现 8 个 Tab 页，每个 Tab 对应一类账户
3. 每个 Tab 支持表格和卡片两种视图模式
4. 集成到零售客户详情页左侧导航
5. 支持数据刷新和分页加载

## 范围

### 包含
- 前端账户信息页面开发
- 8 类账户的列表展示（表格模式）
- 8 类账户的卡片视图展示
- 视图模式切换功能
- 与后端 API 对接
- 字典值映射和数据格式化
- 导航菜单集成

### 不包含
- 后端 API 开发（已完成）
- 账户信息的新增/编辑功能（后续迭代）
- 账户详情页面（后续迭代）

## 设计参考

### 页面结构参考
- Tab 页布局：参考 `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/family-info.vue`
- 视图切换功能：参考 `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/identity-list.vue`

### 技术方案
- 使用 `a-tabs` 组件实现 8 个 Tab 页
- 每个 Tab 内部使用独立的 VxeTable 配置
- 支持表格/卡片视图切换，状态存储在 localStorage
- 卡片视图使用 Grid 布局，每行 3 列响应式
- 使用懒加载，仅在切换到 Tab 时加载数据

## 技术栈

- Vue 3 Composition API
- Ant Design Vue (Tabs 组件)
- VxeTable (表格模式)
- TypeScript
- Vben Hooks (字典映射)

## 关键文件

### 新增文件
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/account-info.vue` - 主页面
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/components/AccountCardView.vue` - 账户卡片组件（可选）

### 修改文件
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue` - 添加导航菜单项

## 依赖

- 后端 API 已实现（8 个 controller）
- 前端 API 客户端已实现（8 个 API 模块）
- 字典数据已配置（账户状态、账户类型等）

## 验收标准

1. ✅ 零售客户详情页左侧菜单新增"账户信息"入口
2. ✅ 点击进入账户信息页面，显示 8 个 Tab 页
3. ✅ 每个 Tab 页默认显示表格视图，支持切换到卡片视图
4. ✅ 表格视图支持分页、排序、刷新
5. ✅ 卡片视图显示账户关键信息，布局美观
6. ✅ 字典值正确映射显示（如账户状态、账户类型）
7. ✅ 数据为空时显示友好的空状态
8. ✅ 视图模式偏好保存到 localStorage

## 风险和缓解

| 风险 | 影响 | 缓解措施 |
|------|------|----------|
| 8 个 Tab 页代码重复度高 | 维护成本高 | 使用组合式函数封装通用逻辑 |
| 数据量大时性能问题 | 用户体验差 | 使用懒加载，仅加载当前 Tab 数据 |
| 卡片视图字段选择不当 | 信息展示不完整 | 与产品确认关键字段 |

## 时间估算

- 主页面开发：2 小时
- 8 个 Tab 配置：3 小时
- 卡片视图开发：2 小时
- 导航集成和联调：1 小时
- 测试和优化：2 小时

**总计：约 10 小时（1.25 个工作日）**

## 后续工作

1. 添加账户新增/编辑功能
2. 实现账户详情弹窗
3. 支持账户数据导出
4. 添加账户统计图表
