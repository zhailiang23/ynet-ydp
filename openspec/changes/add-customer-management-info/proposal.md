# Proposal: 添加客户管理信息页面

## Why

当前零售客户和对公客户的详情页面缺少管理信息的展示功能。客户经理需要快速查看客户的归属管户关系和归属调整历史记录,以便更好地了解客户的管理情况和历史变更轨迹。这些信息对于客户关系管理至关重要,但目前在客户360视图中尚未实现。

## What Changes

- 在零售客户和对公客户详情页面中添加"管理信息"页面组件
- 使用 Tab 结构组织两类管理信息:
  - 归属管户关系列表: 显示当前客户的主办和协办关系
  - 归属管户调整历史: 显示客户归属关系的所有历史变更记录
- 复用现有的后端 API 接口:
  - `/aicrm/customer-assignment/page` - 查询归属关系列表
  - `/aicrm/customer-assignment-history/page` - 查询调整历史
- 参考客户家庭信息页面的实现方式,保持界面风格一致

## Impact

### 受影响的 Specs
- `specs/customer-management-info/spec.md` (新增)

### 受影响的代码
- **前端**:
  - 新增: `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/management-info.vue`
  - 修改: `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue` (引入新组件)
- **后端**:
  - 无需修改,复用现有的 API 接口

### 兼容性
- 无破坏性变更
- 向后兼容,仅新增功能
