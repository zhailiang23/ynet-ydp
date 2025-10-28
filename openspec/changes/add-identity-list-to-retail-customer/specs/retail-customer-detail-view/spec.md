# Spec Delta: Retail Customer Detail View - Customer Identity List

**能力 ID**: `retail-customer-detail-view`
**变更 ID**: `add-identity-list-to-retail-customer`
**类型**: Enhancement
**状态**: Proposed

---

## ADDED Requirements

### Requirement: Customer Identity List Display

零售客户详情视图 SHALL 提供客户证件信息列表功能,展示客户的所有证件记录,包括证件类型、证件号码、有效期等关键信息。

**优先级**: High
**类型**: Functional

#### Scenario: Display identity list in retail customer detail view

**GIVEN**: 用户有 `crm:retail-customer:query` 权限
**AND**: 用户访问零售客户详情页 `/aicrm/retail-customer/detail/:id`
**AND**: 该客户有 1-3 条证件记录
**WHEN**: 用户点击左侧菜单的"客户证件信息"菜单项
**THEN**:
- 系统 SHALL 调用 `/admin-api/crm/customer-identity/page?customerId=:id` API 获取证件列表
- 系统 SHALL 展示包含以下字段的列表:
  - 序号 (行号)
  - 证件类型 (identityType) - 使用字典 `customer_identity_type` 转换显示
  - 证件名称 (identityName)
  - 证件号码 (identityNo) - 部分脱敏显示(中间 8 位显示为 ****)
  - 签发日期 (issueDate) - 格式: YYYY-MM-DD
  - 失效日期 (expiryDate) - 格式: YYYY-MM-DD
  - 有效性 (validity) - 根据当前日期与失效日期比较,显示"有效"(绿色徽章)或"已失效"(灰色徽章)
  - 发证机关 (issueAuthority)
  - 证件更新时间 (updateTime) - 格式: YYYY-MM-DD HH:mm:ss
  - 证件更新人 (updater)
- 列表 SHALL 支持分页显示,每页默认 10 条记录
- 列表 SHALL 显示总记录数

#### Scenario: Empty identity list

**GIVEN**: 用户访问零售客户详情页
**AND**: 该客户没有任何证件记录
**WHEN**: 用户点击左侧菜单的"客户证件信息"菜单项
**THEN**:
- 系统 SHALL 显示空状态提示: "暂无证件信息"
- 系统 SHALL 提供友好的空状态插图

#### Scenario: Identity type display with dictionary

**GIVEN**: 系统字典表中存在 `customer_identity_type` 字典类型
**AND**: 字典包含以下数据项:
  - id_card: 身份证
  - passport: 护照
  - household_register: 户口簿
  - hk_macao_pass: 港澳通行证
  - taiwan_pass: 台湾通行证
  - hk_macao_residence: 港澳居民居住证
  - taiwan_residence: 台湾居民居住证
  - military_officer: 军官证
  - soldier: 士兵证
  - foreign_passport: 外国护照
  - other: 其他
**WHEN**: 系统展示证件类型字段
**THEN**:
- 系统 SHALL 使用字典数据转换证件类型代码为可读文本
- 例如: `id_card` 显示为 "身份证", `passport` 显示为 "护照"

#### Scenario: Identity number masking for privacy

**GIVEN**: 证件记录的证件号码为 "110101198001011234"
**WHEN**: 系统展示证件号码字段
**THEN**:
- 系统 SHALL 对证件号码进行部分脱敏
- 脱敏规则: 保留前 4 位和后 4 位,中间部分显示为 ****
- 示例: "110101198001011234" 显示为 "1101********1234"

#### Scenario: Validity status indicator

**GIVEN**: 证件记录的失效日期为 expiryDate
**AND**: 当前日期为 currentDate
**WHEN**: 系统展示有效性字段
**THEN**:
- 如果 currentDate <= expiryDate,系统 SHALL 显示"有效"(绿色徽章)
- 如果 currentDate > expiryDate,系统 SHALL 显示"已失效"(灰色徽章)
- 如果 expiryDate 为 9999-12-31(长期有效),系统 SHALL 显示"长期有效"(蓝色徽章)

---

### Requirement: Menu Integration

零售客户详情视图 SHALL 在左侧菜单中提供"客户证件信息"菜单项,点击后展示证件信息列表。

**优先级**: High
**类型**: Functional

#### Scenario: Add identity menu item to retail customer detail view

**GIVEN**: 用户访问零售客户详情页 `/aicrm/retail-customer/detail/:id`
**WHEN**: 页面加载完成
**THEN**:
- 左侧菜单 SHALL 显示"客户证件信息"菜单项
- 菜单项 SHALL 配置对应的路由和组件
- 菜单项的图标 SHALL 使用身份证相关的图标(如 `IdcardOutlined`)

#### Scenario: Navigate to identity list when menu item clicked

**GIVEN**: 用户在零售客户详情页
**WHEN**: 用户点击左侧菜单的"客户证件信息"菜单项
**THEN**:
- 系统 SHALL 展示证件信息列表组件
- 浏览器 URL 路径 SHOULD 更新为 `/aicrm/retail-customer/detail/:id#identity` (使用 hash 锚点或子路由)
- 菜单项 SHALL 显示为选中状态

---

### Requirement: Data Loading and Error Handling

证件信息列表 SHALL 提供清晰的加载状态和错误处理,确保良好的用户体验。

**优先级**: Medium
**类型**: Functional

#### Scenario: Loading state during data fetch

**GIVEN**: 用户点击"客户证件信息"菜单项
**WHEN**: 系统正在调用 API 获取证件列表数据
**THEN**:
- 系统 SHALL 显示加载状态(骨架屏或加载动画)
- 系统 SHALL 禁用列表交互(如翻页按钮)

#### Scenario: Error handling when API fails

**GIVEN**: 用户点击"客户证件信息"菜单项
**WHEN**: API 调用失败(如网络错误、服务器错误)
**THEN**:
- 系统 SHALL 显示友好的错误提示消息
- 系统 SHALL 提供"重试"按钮
- 错误详情 SHALL 记录到前端日志

#### Scenario: Retry after error

**GIVEN**: API 调用失败并显示错误提示
**WHEN**: 用户点击"重试"按钮
**THEN**:
- 系统 SHALL 重新发起 API 请求
- 系统 SHALL 显示加载状态

---

### Requirement: Responsive Layout

证件信息列表 SHALL 支持响应式布局,在不同设备上提供良好的显示效果。

**优先级**: Low
**类型**: Non-Functional

#### Scenario: Desktop display (width > 1200px)

**GIVEN**: 用户使用桌面浏览器访问证件信息列表
**AND**: 浏览器窗口宽度 > 1200px
**WHEN**: 列表渲染完成
**THEN**:
- 系统 SHALL 展示所有字段列
- 列宽 SHALL 自适应内容
- 长文本 SHALL 自动省略并支持悬停提示

#### Scenario: Tablet display (768px - 1200px)

**GIVEN**: 用户使用平板设备访问证件信息列表
**AND**: 浏览器窗口宽度在 768px - 1200px 之间
**WHEN**: 列表渲染完成
**THEN**:
- 系统 SHALL 自动隐藏次要字段列(如发证机关、证件更新人)
- 系统 SHOULD 提供"列设置"功能,允许用户自定义显示列

#### Scenario: Mobile display (width < 768px)

**GIVEN**: 用户使用手机设备访问证件信息列表
**AND**: 浏览器窗口宽度 < 768px
**WHEN**: 列表渲染完成
**THEN**:
- 系统 SHALL 切换为卡片视图
- 每张卡片 SHALL 展示关键信息:
  - 证件类型
  - 证件号码(脱敏)
  - 有效性状态
  - 失效日期
- 卡片 SHALL 支持展开查看完整信息

---

## Technical Implementation Notes

### 前端实现要点

**涉及文件**:
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue` - 详情页主组件,需更新 tabs/menu 配置
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/components/IdentityList.vue` - 新增证件列表组件
- `frontend/apps/web-antd/src/api/aicrm/customer-identity.ts` - API 定义(如已存在则复用)

**关键技术点**:
1. 使用 vxe-table 或 ant-design-vue Table 组件
2. 证件号码脱敏: 使用工具函数 `maskIdNumber(identityNo)` 处理
3. 有效性判断: 使用 `dayjs` 比较日期
4. 字典数据: 从 Pinia store 获取 `customer_identity_type` 字典
5. 分页: 使用 ant-design-vue Pagination 组件

**证件号码脱敏示例**:
```typescript
function maskIdNumber(idNumber: string): string {
  if (!idNumber || idNumber.length < 8) return idNumber;
  const start = idNumber.slice(0, 4);
  const end = idNumber.slice(-4);
  return `${start}****${end}`;
}
```

**有效性判断示例**:
```typescript
import dayjs from 'dayjs';

function getValidityStatus(expiryDate: string): { text: string; type: 'success' | 'default' | 'processing' } {
  if (expiryDate === '9999-12-31') {
    return { text: '长期有效', type: 'processing' };
  }
  const isValid = dayjs().isBefore(dayjs(expiryDate));
  return isValid
    ? { text: '有效', type: 'success' }
    : { text: '已失效', type: 'default' };
}
```

### 后端实现要点

**涉及文件**:
- `CustomerIdentityController.java` - 已存在,确认 `getCustomerIdentityPage` 方法支持按 customerId 过滤
- `CustomerIdentityService.java` / `CustomerIdentityServiceImpl.java` - 已存在

**验证项**:
1. 确认 `CustomerIdentityPageReqVO` 包含 `customerId` 字段
2. 确认 `CustomerIdentityRespVO` 包含所有需要展示的字段
3. 确认 `@DictFormat` 注解正确标注 `identityType` 字段
4. 确认分页查询支持按 customerId 过滤

### 数据字典配置

确认系统字典表中存在以下配置:

**字典类型**: `customer_identity_type`
**字典数据项**:
- `id_card`: 身份证
- `passport`: 护照
- `household_register`: 户口簿
- `hk_macao_pass`: 港澳通行证
- `taiwan_pass`: 台湾通行证
- `hk_macao_residence`: 港澳居民居住证
- `taiwan_residence`: 台湾居民居住证
- `military_officer`: 军官证
- `soldier`: 士兵证
- `foreign_passport`: 外国护照
- `other`: 其他

(该字典数据应该在 `backend/sql/mysql/crm_dict_data.sql` 中已定义)

---

## Related Capabilities

- **customer-identity-management**: 客户证件信息管理能力(CRUD 操作)
- **retail-customer-detail-view**: 零售客户详情查看能力(本变更扩展此能力)

---

## Testing Requirements

### 单元测试

**前端**:
- 测试 IdentityList 组件渲染
- 测试证件号码脱敏函数
- 测试有效性判断函数
- 测试 API 调用和数据加载

**后端**:
- 测试 `getCustomerIdentityPage` 方法按 customerId 过滤
- 测试分页逻辑
- 测试字典注解转换

### 集成测试

- 测试完整流程: 访问详情页 -> 点击菜单 -> 查看证件列表
- 测试不同数据场景: 无证件、单个证件、多个证件
- 测试有效性显示: 有效证件、已失效证件、长期有效证件

### E2E 测试

- 使用 Playwright 测试端到端流程
- 测试在不同浏览器中的兼容性
- 测试响应式布局

---

## Version History

- **v1.0** (2025-10-28): 初始版本,定义零售客户详情视图中客户证件信息列表的核心需求
