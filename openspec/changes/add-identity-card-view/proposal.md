# Add Card View Mode to Retail Customer Identity List

## Why

零售客户证件信息列表当前仅提供表格视图,在展示多个证件时缺乏视觉吸引力和直观性。卡片视图可以提供更好的数据组织和视觉呈现,特别适合证件信息这种包含多个属性的数据展示场景。

业务需求:
- 提供更直观的证件信息展示方式
- 支持用户根据使用习惯在表格视图和卡片视图间自由切换
- 改善用户体验,特别是在查看多个证件时的浏览体验

## What Changes

- **新增视图切换功能**: 在证件列表页面工具栏添加视图模式切换按钮(表格视图/卡片视图)
- **新增卡片视图组件**: 实现证件信息的卡片展示,参考 dashboard analytics 页面的卡片样式
- **视图状态管理**: 保存用户选择的视图模式偏好(使用本地存储)
- **响应式布局**: 卡片视图支持响应式网格布局 (移动端 1 列,平板 2 列,桌面 3-4 列)

### 卡片展示的信息
- 证件类型 (作为卡片标题,带图标)
- 证件号码 (脱敏显示)
- 签发日期和失效日期
- 有效性状态 (标签样式)
- 发证机关
- 是否为默认证件 (标签显示)

### UI 组件
- 复用 `@vben-core/shadcn-ui` 的 Card 组件
- 使用 Ant Design Vue 的图标和标签组件
- 保持与现有页面风格一致

## Impact

### 受影响的能力 (Affected Specs)
- **retail-customer-identity-view**: 零售客户证件信息查看能力(新增卡片视图模式)

### 受影响的代码 (Affected Code)
- **前端**:
  - `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/identity-list.vue` (添加视图切换逻辑)
  - `frontend/apps/web-antd/src/views/aicrm/retail-customer/components/IdentityCardView.vue` (新增卡片视图组件)
  - `frontend/apps/web-antd/src/views/aicrm/utils/identity.ts` (复用脱敏工具函数)

### 数据库影响
- 无需修改数据库表结构

### 依赖关系
- 依赖现有的证件列表 API (`getCustomerIdentityPage`)
- 依赖系统字典服务提供证件类型枚举转换
- 依赖现有的 Card 组件库 (`@vben-core/shadcn-ui`)

## Non-Goals (本次变更不包括)

- 不修改证件信息的数据模型或 API
- 不包括卡片视图下的批量操作功能
- 不包括证件照片的展示功能
- 不包括证件信息的导出功能(表格视图已有)
