# Proposal: 实现零售客户偏好设置页面

**Change ID:** add-customer-preference-page
**Status:** Draft
**Created:** 2025-10-30
**Author:** Claude AI Assistant

## Summary

实现零售客户360视图中的"客户偏好"页面，展示客户在多个维度的偏好设置，包括电子渠道偏好、投资类型偏好、品牌类型偏好、理财服务偏好等。后端数据库表和基础代码已存在，本次变更主要聚焦于前端页面开发和前后端集成。

## Motivation

### 业务价值

客户偏好信息是银行精准营销和个性化服务的重要基础数据：

1. **精准营销**: 根据客户的投资偏好、品牌偏好推送匹配的产品和服务
2. **渠道优化**: 了解客户喜好的电子渠道（手机银行、微信银行等），优化触达方式
3. **服务提升**: 基于客户希望获得的理财服务、联系方式偏好提供个性化服务
4. **客户洞察**: 通过偏好数据分析客户兴趣、投资周期、联系时段等特征

### 当前现状

- 零售客户详情页已有"客户偏好"菜单项，但当前使用 Placeholder 组件占位
- 后端数据库表 `crm_customer_preference` 已存在
- 后端 API 接口（Controller、Service、Mapper）已实现
- 前端 API 封装已完成（`#/api/aicrm/customerpreference`）
- 需要开发前端页面组件展示偏好数据

### 目标用户

- **客户经理**: 查看客户偏好信息，制定个性化营销方案
- **产品经理**: 分析客户偏好分布，优化产品设计
- **运营人员**: 根据偏好数据进行客户分群和精准触达

## Proposed Solution

### 高层设计

创建新的 Vue 3 组件 `preference-info.vue`，使用折叠面板（Collapse）展示 8 个维度的客户偏好信息：

1. **喜好的电子渠道** - 多选复选框（手机银行、电话银行、网上银行、微信银行、短信服务、自助银行）+ 其他文本输入
2. **喜好投资类型** - 折叠面板，显示具体投资类型名称
3. **喜好品牌类型** - 折叠面板，显示具体品牌类型名称
4. **希望得到的理财服务** - 折叠面板，显示具体理财服务内容
5. **希望理财经理的联系方式** - 折叠面板，显示联系方式偏好
6. **希望参加的沙龙活动** - 折叠面板，显示沙龙活动类型
7. **个人兴趣爱好** - 折叠面板，显示兴趣爱好内容
8. **希望联系的时间** - 折叠面板，显示联系时段
9. **投资周期偏好** - 折叠面板，显示投资周期偏好

### 技术方案

#### 前端组件设计

- **组件路径**: `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/preference-info.vue`
- **UI 组件**: Ant Design Vue 的 `a-collapse` 折叠面板 + `a-checkbox-group` 复选框组
- **数据加载**: 调用 `getCustomerPreferencePage` API，传入 `customerId`
- **显示逻辑**:
  - 电子渠道使用复选框组展示已勾选项，支持"其他"文本输入
  - 其他偏好使用折叠面板展示，未展开时只显示标题
- **空状态处理**: 使用 `a-empty` 组件展示"暂无客户偏好信息"

#### 数据字典依赖

需要以下字典类型（假设后端已配置）：

- `aicrm_electronic_channel`: 电子渠道类型
- `aicrm_investment_type`: 投资类型
- `aicrm_brand_type`: 品牌类型
- `aicrm_financial_service`: 理财服务
- `aicrm_contact_method`: 联系方式
- `aicrm_salon_activity`: 沙龙活动
- `aicrm_interest_hobby`: 兴趣爱好
- `aicrm_contact_time`: 联系时间
- `aicrm_investment_period`: 投资周期

#### 集成点

- 在 `detail.vue` 中导入 `PreferenceInfo` 组件
- 替换 menuItems 中 `preference` 项的 `Placeholder` 为 `PreferenceInfo`
- 通过 props 传递 `customerId` 到子组件

### 实现阶段

**Phase 1: 前端组件开发**
1. 创建 `preference-info.vue` 组件文件
2. 实现折叠面板布局和样式
3. 实现电子渠道复选框组
4. 实现 API 数据加载逻辑
5. 实现空状态和错误处理

**Phase 2: 集成和测试**
1. 在 `detail.vue` 中集成新组件
2. 前端类型检查（TypeScript）
3. 代码规范检查（ESLint/Stylelint）
4. 本地功能测试

**Phase 3: 数据字典配置（可选）**
1. 检查系统字典管理中是否存在所需字典类型
2. 如缺失，提供 SQL 脚本插入字典数据

## Impact Analysis

### 影响范围

- **前端文件**:
  - 新增: `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/preference-info.vue` (~200 行代码)
  - 修改: `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue` (~5 行代码)

- **后端文件**: 无需修改（假设后端 API 已存在）

- **数据库**: 无需修改（假设表和字典数据已存在）

### 风险评估

| 风险 | 概率 | 影响 | 缓解措施 |
|------|------|------|----------|
| 后端 API 不存在或不完整 | 中 | 高 | 先验证后端接口可用性，如需要则补充后端代码 |
| 数据字典未配置 | 中 | 中 | 提供 SQL 脚本，由管理员配置字典数据 |
| 样式与现有页面不一致 | 低 | 低 | 参考 `family-info.vue` 等页面的样式 |
| 电子渠道复选框逻辑复杂 | 低 | 低 | 使用成熟的 Ant Design 组件 |

### 依赖关系

**前置依赖**:
- 后端 API 接口已实现（`CustomerPreferenceController`）
- 数据库表 `crm_customer_preference` 已存在
- 前端 API 封装已完成（`#/api/aicrm/customerpreference`）

**后续影响**:
- 无，本次变更是独立功能模块，不影响其他页面

## Alternatives Considered

### 方案 1: 使用表单编辑模式（未采纳）

使用可编辑的表单展示和修改客户偏好。

**优点**:
- 支持直接编辑偏好数据

**缺点**:
- 需要增加权限控制和数据验证逻辑
- 用户需求明确为"查看"而非"编辑"
- 增加开发复杂度

**为什么未采纳**: 当前需求是展示客户偏好，不涉及编辑功能。编辑功能可在后续迭代中添加。

### 方案 2: 使用表格展示（未采纳）

使用 VxeTable 表格展示偏好数据。

**优点**:
- 与其他页面（如客户大事记）风格统一

**缺点**:
- 偏好数据不是列表型数据，不适合表格展示
- 电子渠道的多选复选框在表格中难以展示

**为什么未采纳**: 偏好数据更适合分组展示而非表格形式，折叠面板能更好地组织多维度信息。

## Success Criteria

### 功能完整性

- ✅ 页面能正确加载客户偏好数据
- ✅ 电子渠道以复选框形式展示，已勾选项显示为选中状态
- ✅ 其他偏好维度使用折叠面板展示
- ✅ 空状态正确显示"暂无客户偏好信息"
- ✅ 数据字典值正确转换为可读文本

### 代码质量

- ✅ TypeScript 类型检查通过（无类型错误）
- ✅ ESLint 检查通过（无 lint 错误）
- ✅ Stylelint 检查通过（无样式错误）
- ✅ 代码遵循项目规范

### 用户体验

- ✅ 页面布局美观，与截图设计一致
- ✅ 折叠面板交互流畅
- ✅ 加载状态和错误状态有友好提示
- ✅ 响应式布局，适配不同屏幕尺寸

### 性能

- ✅ 页面首次加载时间 < 2 秒
- ✅ 无不必要的重复 API 请求

## Open Questions

1. **后端 API 完整性确认**
   - Q: 后端 `CustomerPreferenceController` 是否已完整实现？
   - Q: API 返回的数据结构是什么？
   - A: 需要在实施阶段验证，如不存在则需先补充后端代码

2. **数据字典确认**
   - Q: 系统字典管理中是否已配置所有所需字典类型？
   - Q: 字典项的值（value）和标签（label）是什么？
   - A: 需要检查数据库 `system_dict_type` 和 `system_dict_data` 表，缺失则提供 SQL 脚本

3. **电子渠道字段存储格式**
   - Q: 电子渠道是存储为逗号分隔字符串（如 "1,2,3"）还是 JSON 数组？
   - Q: "其他"文本内容如何存储？
   - A: 需要查看数据库表结构和后端代码确认

4. **编辑功能需求**
   - Q: 当前版本是否需要支持编辑客户偏好？
   - A: 根据用户描述，当前只需查看功能，编辑功能可后续迭代

## Timeline

| 阶段 | 预计工时 | 完成标准 |
|------|----------|----------|
| Phase 1: 前端组件开发 | 3-4 小时 | 组件功能完整，代码质量合格 |
| Phase 2: 集成和测试 | 1-2 小时 | 功能测试通过，代码检查通过 |
| Phase 3: 数据字典配置 | 0.5-1 小时 | 字典数据配置完成（如需要） |
| **总计** | **4.5-7 小时** | 功能上线，用户验收通过 |

## References

- 项目文档: `openspec/project.md`
- 参考页面: `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/family-info.vue`
- 参考页面: `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/timeline-info.vue`
- Ant Design Vue Collapse: https://antdv.com/components/collapse
- Ant Design Vue Checkbox: https://antdv.com/components/checkbox
