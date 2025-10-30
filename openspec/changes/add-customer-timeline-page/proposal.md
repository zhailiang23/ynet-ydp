# Proposal: 实现客户大事记信息页面

## Why

当前零售客户详情页面中"客户大事记信息"菜单项使用占位组件,尚未实现真实功能。客户大事记是 CRM 系统中重要的客户画像组成部分,用于记录客户生命周期中的重要事件（如生日、结婚、生子、升职、购房等）。客户经理需要快速查看和了解客户的重要事件历史,以便在适当的时机提供个性化服务和关怀,提升客户体验和满意度。

后端表结构 `crm_customer_important_event` 和基础 CRUD 代码已生成完成,但前端展示页面尚未实现,导致该功能无法使用。

## What Changes

- 在零售客户详情页面中实现"客户大事记信息"页面组件
- 使用 VxeTable 展示客户重要事件列表,包含13个列:
  - 基本信息: 序号、事件名称、事件日期、事件内容
  - 分类信息: 事件类型、事件状态、事件级别、事件来源
  - 维护信息: 维护人、最近维护日期
  - 提醒信息: 是否提醒、提醒时间
  - 备注信息: 备注
- 实现以下功能:
  - 分页查询: 支持按页加载事件数据
  - 刷新功能: 工具栏刷新按钮
  - 字典转换: 事件类型、状态、级别、来源使用字典显示
  - 格式化: 日期时间格式化、布尔值格式化
  - 长文本处理: 超长文本截断并显示 tooltip
- 复用现有的后端 API 接口:
  - `/aicrm/customer-important-event/page` - 查询客户事件分页列表
- 在 detail.vue 中将 Placeholder 组件替换为 TimelineInfo 组件
- 参考 management-info.vue 的实现方式,保持界面风格一致

## Impact

### 受影响的 Specs
- `specs/customer-timeline-info/spec.md` (新增)

### 受影响的代码
- **前端**:
  - 新增: `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/timeline-info.vue`
  - 修改: `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue` (引入新组件并替换 Placeholder)
- **后端**:
  - 无需修改,复用现有的 API 接口和服务

### 兼容性
- 无破坏性变更
- 向后兼容,仅新增功能
- 不影响其他菜单项和页面

### 数据依赖
- 字典数据: `aicrm_event_status`, `aicrm_event_type`, `aicrm_event_level`, `aicrm_event_source`
- API 接口: `/aicrm/customer-important-event/page`
- 数据库表: `crm_customer_important_event`

## Risks

### 风险 1: 字典数据缺失
**Impact**: 页面显示原始值而非友好文本
**Mitigation**: 在测试阶段检查字典数据,如缺失则通过 SQL 脚本插入

### 风险 2: API 返回字段与 VO 不匹配
**Impact**: 数据显示错误或类型错误
**Mitigation**: 已确认后端 VO 定义,前端类型定义与后端保持一致,开发过程中进行联调测试
