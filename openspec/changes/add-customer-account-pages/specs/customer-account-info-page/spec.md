# Spec: 零售客户账户信息页面

## ADDED Requirements

### Requirement: 账户信息页面基础结构

零售客户详情页面 SHALL 提供"账户信息"模块，展示客户的 8 类账户信息。

#### Scenario: 访问账户信息页面

**Given** 用户正在查看零售客户详情页
**When** 用户点击左侧导航菜单中的"账户信息"
**Then** 系统 SHALL 显示账户信息页面
**And** 页面 SHALL 包含 8 个 Tab 页（存款、贷款、理财、基金、信托、保险、贵金属、信用卡）
**And** 默认 SHALL 激活第一个 Tab（存款账户）

#### Scenario: Tab 页标签显示

**Given** 账户信息页面已加载
**When** 用户查看 Tab 标签
**Then** 系统 SHALL 按以下顺序显示 Tab 标签：
- "存款账户"
- "贷款账户"
- "理财账户"
- "基金账户"
- "信托账户"
- "保险账户"
- "贵金属账户"
- "信用卡账户"

### Requirement: 表格视图模式

每个账户 Tab 页 SHALL 支持表格视图，展示账户列表数据。

#### Scenario: 存款账户表格显示

**Given** 用户在"存款账户" Tab
**And** 视图模式为"表格"
**When** 系统加载存款账户数据
**Then** 系统 SHALL 使用 VxeTable 展示账户列表
**And** 表格 SHALL 包含以下列：
- 序号（固定左侧）
- 账号
- 户名
- 账户类型（字典映射 aicrm_deposit_account_type）
- 账户状态（字典映射 aicrm_deposit_account_status）
- 币种（字典映射 aicrm_currency_type）
- 账户余额
- 开户日期
- 开户机构

#### Scenario: 贷款账户表格显示

**Given** 用户在"贷款账户" Tab
**And** 视图模式为"表格"
**When** 系统加载贷款账户数据
**Then** 系统 SHALL 使用 VxeTable 展示账户列表
**And** 表格 SHALL 包含以下列：
- 序号（固定左侧）
- 贷款账号
- 借款人姓名
- 贷款产品名称
- 账户状态（字典映射 aicrm_loan_account_status）
- 贷款金额
- 贷款余额
- 贷款利率
- 放款日期
- 到期日

#### Scenario: 理财账户表格显示

**Given** 用户在"理财账户" Tab
**And** 视图模式为"表格"
**When** 系统加载理财账户数据
**Then** 系统 SHALL 使用 VxeTable 展示账户列表
**And** 表格 SHALL 包含以下列：
- 序号
- 理财账号
- 户名
- 理财产品名称
- 账户状态（字典映射 aicrm_wealth_account_status）
- 理财类型（字典映射 aicrm_wealth_product_type）
- 购买金额
- 当前市值
- 预期收益率
- 开户日期
- 到期日

#### Scenario: 基金账户表格显示

**Given** 用户在"基金账户" Tab
**And** 视图模式为"表格"
**When** 系统加载基金账户数据
**Then** 系统 SHALL 使用 VxeTable 展示账户列表
**And** 表格 SHALL 包含以下列：
- 序号
- 基金账号
- 户名
- 基金产品名称
- 账户状态（字典映射 aicrm_fund_account_status）
- 基金类型（字典映射 aicrm_fund_type）
- 持有份额
- 当前市值
- 累计收益
- 开户日期

#### Scenario: 信托账户表格显示

**Given** 用户在"信托账户" Tab
**And** 视图模式为"表格"
**When** 系统加载信托账户数据
**Then** 系统 SHALL 使用 VxeTable 展示账户列表
**And** 表格 SHALL 包含以下列：
- 序号
- 信托账号
- 委托人姓名
- 信托产品名称
- 账户状态（字典映射 aicrm_trust_status）
- 信托类型（字典映射 aicrm_trust_type）
- 信托金额
- 当前价值
- 预期收益率
- 成立日期
- 到期日

#### Scenario: 保险账户表格显示

**Given** 用户在"保险账户" Tab
**And** 视图模式为"表格"
**When** 系统加载保险账户数据
**Then** 系统 SHALL 使用 VxeTable 展示账户列表
**And** 表格 SHALL 包含以下列：
- 序号
- 保单号
- 投保人姓名
- 保险产品名称
- 账户状态（字典映射 aicrm_insurance_status）
- 保险类型（字典映射 aicrm_insurance_type）
- 保险金额
- 保费
- 现金价值
- 投保日期
- 到期日期

#### Scenario: 贵金属账户表格显示

**Given** 用户在"贵金属账户" Tab
**And** 视图模式为"表格"
**When** 系统加载贵金属账户数据
**Then** 系统 SHALL 使用 VxeTable 展示账户列表
**And** 表格 SHALL 包含以下列：
- 序号
- 贵金属账号
- 户名
- 贵金属品种（字典映射 aicrm_metal_type）
- 账户状态（字典映射 aicrm_metal_account_status）
- 持有数量
- 持有单位（字典映射 aicrm_metal_unit）
- 当前市值
- 开户日期

#### Scenario: 信用卡账户表格显示

**Given** 用户在"信用卡账户" Tab
**And** 视图模式为"表格"
**When** 系统加载信用卡账户数据
**Then** 系统 SHALL 使用 VxeTable 展示账户列表
**And** 表格 SHALL 包含以下列：
- 序号
- 卡号（脱敏显示）
- 持卡人姓名
- 卡片类型（字典映射 aicrm_creditcard_type）
- 卡片状态（字典映射 aicrm_creditcard_status）
- 信用额度
- 已用额度
- 可用额度
- 开卡日期
- 到期日

### Requirement: 卡片视图模式

每个账户 Tab 页 SHALL 支持卡片视图，以卡片形式展示账户信息。

#### Scenario: 切换到卡片视图

**Given** 用户在任意账户 Tab
**And** 当前视图模式为"表格"
**When** 用户点击"卡片视图"切换按钮
**Then** 系统 SHALL 切换到卡片视图
**And** 系统 SHALL 将视图模式偏好保存到 localStorage
**And** 系统 SHALL 加载当前 Tab 的账户数据（最多 100 条）
**And** 卡片 SHALL 以 Grid 布局展示（每行 3 列，响应式）

#### Scenario: 存款账户卡片显示

**Given** 用户在"存款账户" Tab
**And** 视图模式为"卡片"
**When** 系统显示存款账户卡片
**Then** 每张卡片 SHALL 显示：
- 账号（标题）
- 户名
- 账户类型
- 账户状态（带状态标签）
- 账户余额（大字号突出）
- 币种
- 开户日期

#### Scenario: 贷款账户卡片显示

**Given** 用户在"贷款账户" Tab
**And** 视图模式为"卡片"
**When** 系统显示贷款账户卡片
**Then** 每张卡片 SHALL 显示：
- 贷款账号（标题）
- 借款人姓名
- 贷款产品名称
- 账户状态（带状态标签）
- 贷款余额（大字号突出）
- 贷款利率
- 到期日

#### Scenario: 卡片视图空状态

**Given** 用户在任意账户 Tab
**And** 视图模式为"卡片"
**And** 该账户类型没有数据
**When** 系统加载数据完成
**Then** 系统 SHALL 显示空状态组件
**And** 空状态 SHALL 提示"暂无账户数据"

### Requirement: 视图模式切换

账户信息页面 SHALL 提供视图模式切换功能。

#### Scenario: 视图切换按钮

**Given** 用户在任意账户 Tab
**When** 用户查看页面右上角
**Then** 系统 SHALL 显示视图切换按钮组
**And** 按钮组 SHALL 包含两个按钮：
- 表格视图图标按钮
- 卡片视图图标按钮
**And** 当前激活的视图模式按钮 SHALL 高亮显示

#### Scenario: 视图模式偏好持久化

**Given** 用户切换了视图模式
**When** 用户刷新页面或下次访问
**Then** 系统 SHALL 自动恢复上次选择的视图模式
**And** 视图模式偏好 SHALL 存储在 localStorage
**And** 存储键 SHALL 为 `account-info-view-mode`

### Requirement: 数据加载和刷新

账户信息页面 SHALL 支持数据的懒加载和手动刷新。

#### Scenario: Tab 页懒加载

**Given** 用户首次访问账户信息页面
**When** 页面加载完成
**Then** 系统 SHALL 仅加载当前激活 Tab 的数据
**And** 其他 Tab 的数据 SHALL 在用户切换到该 Tab 时加载

#### Scenario: 切换 Tab 加载数据

**Given** 用户在账户信息页面
**When** 用户切换到一个未加载数据的 Tab
**Then** 系统 SHALL 显示加载状态
**And** 系统 SHALL 调用对应的后端 API 加载数据
**And** 加载完成后 SHALL 展示数据

#### Scenario: 手动刷新数据

**Given** 用户在任意账户 Tab
**And** 视图模式为"表格"
**When** 用户点击表格工具栏的"刷新"按钮
**Then** 系统 SHALL 重新加载当前 Tab 的数据
**And** 系统 SHALL 保持当前的分页和筛选条件

### Requirement: 数据格式化和字典映射

账户信息页面 SHALL 正确格式化数据并映射字典值。

#### Scenario: 金额格式化

**Given** 账户数据包含金额字段
**When** 系统显示金额
**Then** 金额 SHALL 格式化为千分位显示
**And** SHALL 保留 2 位小数
**And** 负数 SHALL 显示为红色

#### Scenario: 日期格式化

**Given** 账户数据包含日期字段
**When** 系统显示日期
**Then** 日期 SHALL 格式化为 YYYY-MM-DD 格式
**And** 空值 SHALL 显示为 "-"

#### Scenario: 字典值映射

**Given** 账户数据包含字典类型字段（如账户状态、账户类型）
**When** 系统显示该字段
**Then** 系统 SHALL 使用 `getDictLabel` 函数获取字典标签
**And** 字典不存在时 SHALL 显示原始值

#### Scenario: 卡号脱敏显示

**Given** 信用卡账户数据包含卡号
**When** 系统显示卡号
**Then** 卡号 SHALL 脱敏显示
**And** 脱敏规则 SHALL 为：显示前 4 位和后 4 位，中间用 * 替代
**And** 示例：`6225 **** **** 1234`

### Requirement: 分页和排序

表格视图 SHALL 支持分页和排序功能。

#### Scenario: 分页控制

**Given** 用户在表格视图
**And** 账户数据超过 20 条
**When** 系统加载数据
**Then** 系统 SHALL 显示分页控件
**And** 默认每页 SHALL 显示 20 条数据
**And** 用户 SHALL 可以切换每页显示 10/20/50/100 条

#### Scenario: 表格排序

**Given** 用户在表格视图
**When** 用户点击可排序列的列头
**Then** 系统 SHALL 按该列进行排序
**And** 再次点击 SHALL 切换排序方向（升序/降序）

### Requirement: 导航菜单集成

零售客户详情页 SHALL 在左侧导航菜单中添加"账户信息"入口。

#### Scenario: 导航菜单显示

**Given** 用户在零售客户详情页
**When** 用户查看左侧导航菜单
**Then** 菜单 SHALL 在"客户业务概览"位置显示"账户信息"菜单项

#### Scenario: 点击导航菜单

**Given** 用户在零售客户详情页
**When** 用户点击"账户信息"菜单项
**Then** 右侧内容区 SHALL 切换到账户信息页面
**And** 该菜单项 SHALL 高亮显示

### Requirement: 错误处理

账户信息页面 SHALL 优雅处理各种错误情况。

#### Scenario: API 请求失败

**Given** 用户在任意账户 Tab
**When** 后端 API 请求失败
**Then** 系统 SHALL 显示错误提示消息
**And** 消息内容 SHALL 包含错误原因
**And** 表格/卡片区域 SHALL 显示空状态

#### Scenario: 数据为空

**Given** 用户在任意账户 Tab
**When** 该账户类型没有数据
**Then** 表格 SHALL 显示"暂无数据"
**Or** 卡片视图 SHALL 显示空状态组件

## Implementation Notes

### 文件结构
```
frontend/apps/web-antd/src/views/aicrm/retail-customer/
├── pages/
│   └── account-info.vue              # 主页面（新增）
├── components/
│   └── AccountCardView.vue           # 账户卡片组件（可选新增）
└── detail.vue                        # 详情页（修改）
```

### 技术要点

1. **懒加载实现**
   - 使用 `watch` 监听 `activeTab` 变化
   - 记录已加载的 Tab，避免重复加载
   - 首次加载时默认加载第一个 Tab

2. **视图模式切换**
   - 使用 `ref<'table' | 'card'>` 存储视图模式
   - localStorage 键：`account-info-view-mode`
   - 切换视图时重新加载数据（卡片视图加载最多 100 条）

3. **VxeTable 配置**
   - 每个账户类型独立配置 `useVbenVxeGrid`
   - 设置 `proxyConfig.ajax.query` 调用对应 API
   - 配置 `rowConfig.keyField` 为 'id'
   - 启用工具栏刷新按钮

4. **卡片布局**
   - 使用 CSS Grid 布局：`grid-template-columns: repeat(3, 1fr)`
   - 响应式：`@media (max-width: 1200px)` 改为 2 列
   - 响应式：`@media (max-width: 768px)` 改为 1 列

5. **API 调用**
   - 存款：`getCustomerAccountDepositPage({ customerId, pageNo, pageSize })`
   - 贷款：`getCustomerAccountLoanPage({ customerId, pageNo, pageSize })`
   - 理财：`getCustomerAccountWealthPage({ customerId, pageNo, pageSize })`
   - 基金：`getCustomerAccountFundPage({ customerId, pageNo, pageSize })`
   - 信托：`getCustomerAccountTrustPage({ customerId, pageNo, pageSize })`
   - 保险：`getCustomerAccountInsurancePage({ customerId, pageNo, pageSize })`
   - 贵金属：`getCustomerAccountMetalPage({ customerId, pageNo, pageSize })`
   - 信用卡：`getCustomerAccountCreditcardPage({ customerId, pageNo, pageSize })`

### 参考代码

**Tab 页结构参考** (`family-info.vue`):
```vue
<a-tabs v-model:activeKey="activeTab" type="card">
  <a-tab-pane key="deposit" tab="存款账户">
    <DepositGrid v-if="activeTab === 'deposit'" />
  </a-tab-pane>
  <!-- 其他 Tab... -->
</a-tabs>
```

**视图切换参考** (`identity-list.vue`):
```vue
<div class="view-toggle">
  <a-button-group>
    <a-button :type="viewMode === 'table' ? 'primary' : 'default'" @click="viewMode = 'table'">
      <IconifyIcon icon="ant-design:table-outlined" />
    </a-button>
    <a-button :type="viewMode === 'card' ? 'primary' : 'default'" @click="viewMode = 'card'">
      <IconifyIcon icon="ant-design:appstore-outlined" />
    </a-button>
  </a-button-group>
</div>
```
