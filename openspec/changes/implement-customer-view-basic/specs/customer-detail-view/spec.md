# Spec: 客户详情查看能力

**能力 ID**: `customer-detail-view`
**变更 ID**: `implement-customer-view-basic`
**类型**: New Feature
**状态**: Proposed

---

## 概述

客户详情查看能力提供了 CRM 系统中单个客户完整信息的展示功能。根据客户类型不同，提供**两个独立的详情页面**：

### 1. 零售客户详情页

- **路由**: `/aicrm/retail-customer/detail/:id`
- **后端 API**: `/crm/retail-customer/get?id={id}` (RetailCustomerController)
- **权限**: `crm:retail-customer:query`
- **适用对象**: customerType = 1 的零售客户

### 2. 对公客户详情页

- **路由**: `/aicrm/company-customer/detail/:id`
- **后端 API**: `/crm/company-customer/get?id={id}` (CompanyCustomerController)
- **权限**: `crm:company-customer:query`
- **适用对象**: customerType = 2 的对公客户

**架构设计**:
- 两个详情页均采用**多 Tab 页签**结构，支持扩展
- 当前变更只实现第一个 Tab："基本信息"
- 预留其他 Tab 的扩展接口（如：联系人、跟进记录、商机、合同等）
- 两个详情页的 Tab 结构相同，但数据来源和展示字段可能不同

**核心价值**:
- 根据客户类型提供专属的详情视图
- 调用各自专属的后端服务，数据隔离清晰
- 多 Tab 分组展示，结构清晰，易于扩展
- 支持快速编辑和返回
- 优化枚举值和布尔值的展示

**本次变更范围**: 两个详情页都只实现"基本信息" Tab 的内容展示

---

## ADDED Requirements

### REQ-CDV-001: 客户详情页面布局（双详情页 + 多 Tab 结构）

**优先级**: High
**类型**: Functional

根据客户类型，提供两个独立的详情页面，均采用**多 Tab 页签**布局结构。本次变更只实现"基本信息" Tab。

#### Scenario: 零售客户详情页面头部展示

**Given**: 用户有 `crm:retail-customer:query` 权限
**When**: 用户访问零售客户详情页 `/aicrm/retail-customer/detail/:id`
**Then**:
- 页面头部显示:
  - 面包屑导航: "客户管理 > 客户列表 > 零售客户详情"
  - 客户名称 (大标题)
  - 客户类型标签: "零售客户"
  - 客户状态徽章 (正常/冻结/注销)
  - 操作按钮组:
    - "编辑" 按钮 (权限: `crm:retail-customer:update`)
    - "返回" 按钮

#### Scenario: 对公客户详情页面头部展示

**Given**: 用户有 `crm:company-customer:query` 权限
**When**: 用户访问对公客户详情页 `/aicrm/company-customer/detail/:id`
**Then**:
- 页面头部显示:
  - 面包屑导航: "客户管理 > 客户列表 > 对公客户详情"
  - 客户名称 (大标题)
  - 客户类型标签: "对公客户"
  - 客户状态徽章 (正常/冻结/注销)
  - 操作按钮组:
    - "编辑" 按钮 (权限: `crm:company-customer:update`)
    - "返回" 按钮

#### Scenario: Tab 页签结构（两个详情页相同）

**Given**: 用户在零售客户详情页或对公客户详情页
**Then**:
- 页面主体区域显示 Tab 页签导航
- Tab 页签列表包含:
  - **"基本信息"** Tab (当前变更实现)
  - "联系人" Tab (预留，未实现)
  - "跟进记录" Tab (预留，未实现)
  - "商机" Tab (预留，未实现)
  - "合同" Tab (预留，未实现)
- 默认激活"基本信息" Tab
- 未实现的 Tab 点击时显示"功能开发中"提示或禁用状态

**注意**:
- 两个详情页（零售和对公）的 Tab 结构相同
- 本次变更只开发"基本信息" Tab 的内容，其他 Tab 仅创建占位结构，方便后续扩展

#### Scenario: "基本信息" Tab 内容 - 零售客户（本次实现）

**Given**: 用户在零售客户详情页，选中"基本信息" Tab
**Then**:
- "基本信息" Tab 内容区域显示多个信息卡片（来自 RetailCustomerRespVO，包含 RetailCustomerDO + CustomerDO 字段）:

**1. 客户基本信息卡片**（来自 CustomerDO）:
  - 客户编号 (customerNo)
  - 客户类型 (customerType) - 使用字典转换，显示 "零售客户"
  - 客户名称 (customerName)
  - 客户等级 (customerLevel) - 使用字典转换
  - 客户状态 (customerStatus) - 使用字典转换为带颜色的徽章
  - 所属部门 (deptId) - 显示部门名称

**2. 个人信息卡片**（来自 RetailCustomerDO）:
  - 昵称 (nickname)
  - 性别 (gender) - 使用字典转换: 未知/男/女
  - 出生日期 (birthday)
  - 年龄 (age)
  - 国籍 (nationality)
  - 民族 (nation)
  - 籍贯 (nativePlace)
  - 户籍类型 (residenceType)
  - 户口所在地 (domicilePlace)
  - 职业 (occupation)
  - 职业类型 (occupationType)
  - 婚姻状态 (maritalStatus)
  - 宗教信仰 (religion)
  - 受教育程度 (education)
  - 最高学位 (degree)

**3. VIP 信息卡片**（来自 RetailCustomerDO）:
  - 是否VIP客户 (isVip) - 布尔徽章
  - 是否核心VIP (isCoreVip) - 布尔徽章
  - VIP等级 (vipLevel)
  - 是否高净值客户 (isHighNetWorth) - 布尔徽章
  - 净值类型 (netWorthType)

**4. 收入资产信息卡片**（来自 RetailCustomerDO）:
  - 收入水平 (incomeLevel)
  - 资产水平 (assetLevel)

**5. 信用信息卡片**（来自 CustomerDO）:
  - 信用状态 (creditStatus) - 显示为带颜色的标签（良好: 绿色, 一般: 蓝色, 较差: 红色）
  - 信用等级 (creditLevel) - 使用字典转换
  - 信用评分 (creditScore) - 保留 2 位小数

**6. 信誉信息卡片**（来自 RetailCustomerDO）:
  - 信誉状态 (reputationStatus)
  - 信誉级别 (reputationLevel)
  - 信誉评分 (reputationScore)

**7. 业务信息卡片**（来自 CustomerDO）:
  - 客户来源 (customerSource) - 使用字典转换
  - 客户标签 (customerTag) - 显示为标签组
  - 是否优质客户 (isHighQuality) - 徽章展示
  - 是否重要客户 (isImportant) - 徽章展示

**8. 其他信息卡片**:
  - 零售客户类型 (retailCustomerType) - 来自 RetailCustomerDO
  - 是否高端户 (isHighEndCustomer) - 来自 RetailCustomerDO
  - 是否综合户 (isComprehensiveCustomer) - 来自 RetailCustomerDO
  - 客户归属网格编号 (customerGridCode) - 来自 RetailCustomerDO
  - 是否代发工资客户 (isPayrollCustomer) - 来自 RetailCustomerDO
  - 代发工资单位 (payrollCompanyName) - 来自 RetailCustomerDO
  - 成为我行客户时间 (becomeCustomerDate) - 来自 RetailCustomerDO
  - 备注信息 (remark) - 多行文本，来自 CustomerDO
  - 创建时间 (createTime) - 来自 CustomerDO
  - 更新时间 (updateTime) - 来自 CustomerDO

#### Scenario: "基本信息" Tab 内容 - 对公客户（本次实现）

**Given**: 用户在对公客户详情页，选中"基本信息" Tab
**Then**:
- "基本信息" Tab 内容区域显示多个信息卡片（来自 CompanyCustomerRespVO，包含 CompanyCustomerDO + CustomerDO 字段）:

**1. 客户基本信息卡片**（来自 CustomerDO）:
  - 客户编号 (customerNo)
  - 客户类型 (customerType) - 使用字典转换，显示 "对公客户"
  - 客户名称/企业名称 (customerName)
  - 客户等级 (customerLevel) - 使用字典转换
  - 客户状态 (customerStatus) - 使用字典转换为带颜色的徽章
  - 所属部门 (deptId) - 显示部门名称

**2. 企业证照信息卡片**（来自 CompanyCustomerDO）:
  - 证件类型 (licenseType)
  - 证件号码/营业执照号 (licenseNo)
  - 统一社会信用代码 (creditCode)
  - 组织机构代码 (organizationCode)
  - 纳税人识别号 (taxNo)
  - 贷款卡号 (loanCardNo)

**3. 企业基本信息卡片**（来自 CompanyCustomerDO）:
  - 企业类型 (enterpriseType)
  - 企业性质 (enterpriseNature)
  - 企业控股类型 (ownershipType)
  - 企业经济性质 (economicType)
  - 企业规模 (enterpriseScale)
  - 注册资本 (registeredCapital) - 显示为 "XX 万元"
  - 注册资本币种 (registeredCapitalCurrency)
  - 企业成立日期 (establishDate)
  - 营业期限 (businessTerm)

**4. 行业分类信息卡片**（来自 CompanyCustomerDO）:
  - 行业分类一级 (industryCategoryL1)
  - 行业分类二级 (industryCategoryL2)
  - 行业分类三级 (industryCategoryL3)
  - 行业分类四级 (industryCategoryL4)
  - 行业代码 (industryCode)

**5. 企业特征标识卡片**（来自 CompanyCustomerDO）:
  - 是否上市公司 (isListed) - 布尔徽章
  - 是否小微企业 (isSmallEnterprise) - 布尔徽章
  - 是否集团客户 (isGroupCustomer) - 布尔徽章
  - 是否有进出口权 (isImportExport) - 布尔徽章
  - 是否我行关联方 (isRelatedParty) - 布尔徽章
  - 是否网银签约客户 (isEbankSigned) - 布尔徽章
  - 是否涉农企业 (isAgricultureRelated) - 布尔徽章

**6. 账户信息卡片**（来自 CompanyCustomerDO）:
  - 基本账户开户行 (basicAccountBank)
  - 基本账户账号 (basicAccountNo)

**7. 法定代表人信息卡片**（来自 CompanyCustomerDO）:
  - 法定代表人姓名 (legalPersonName)
  - 法定代表人证件类型 (legalPersonIdType)
  - 法定代表人证件号码 (legalPersonIdNo) - 加密显示（如: 330***********1234）
  - 法定代表人联系电话 (legalPersonPhone) - 加密显示（如: 133****8888）

**8. 企业资质评级卡片**（来自 CompanyCustomerDO）:
  - 企业资质 (enterpriseQualification) - 显示为标签组
  - 管理部门 (managementDept)
  - 监管部门 (superviseDept)
  - 企业评级 (companyRating)
  - 评级机构 (ratingAgency)
  - 评级日期 (ratingDate)

**9. 信用信息卡片**（来自 CustomerDO）:
  - 信用状态 (creditStatus) - 显示为带颜色的标签
  - 信用等级 (creditLevel) - 使用字典转换
  - 信用评分 (creditScore) - 保留 2 位小数

**10. 业务信息卡片**（来自 CustomerDO）:
  - 客户来源 (customerSource) - 使用字典转换
  - 客户标签 (customerTag) - 显示为标签组
  - 是否优质客户 (isHighQuality) - 徽章展示
  - 是否重要客户 (isImportant) - 徽章展示

**11. 其他信息卡片**:
  - 备注信息 (remark) - 多行文本，来自 CustomerDO
  - 创建时间 (createTime) - 来自 CustomerDO
  - 更新时间 (updateTime) - 来自 CustomerDO

#### Scenario: 其他 Tab 占位（预留扩展）

**Given**: 用户在客户详情页，点击其他 Tab（如"联系人"、"跟进记录"等）
**Then**:
- 显示"功能开发中"或"敬请期待"提示
- 或者 Tab 显示为禁用状态，不可点击

**验收标准**:
- Tab 页签导航清晰可见
- "基本信息" Tab 内容完整展示
- 信息卡片分组合理，布局清晰
- 所有字段正确展示
- 枚举值通过字典转换为可读文本
- 布尔值使用徽章展示
- 卡片之间有适当的间距
- 响应式布局，在移动端自动调整
- 预留的 Tab 有明确的占位结构

---

### REQ-CDV-002: 客户详情数据加载（双详情页）

**优先级**: High
**类型**: Functional

两个客户详情页面都必须正确加载并展示指定客户的完整信息，但调用各自专属的后端 API。

#### Scenario: 成功加载零售客户详情

**Given**: 数据库中存在 ID 为 123 的零售客户记录
**When**: 用户访问 `/aicrm/retail-customer/detail/123`
**Then**:
- 调用后端 API `/crm/retail-customer/get?id=123`
- 显示加载动画
- API 返回成功后隐藏加载动画
- 页面展示该零售客户的完整信息
- 所有字段值正确显示

#### Scenario: 成功加载对公客户详情

**Given**: 数据库中存在 ID 为 456 的对公客户记录
**When**: 用户访问 `/aicrm/company-customer/detail/456`
**Then**:
- 调用后端 API `/crm/company-customer/get?id=456`
- 显示加载动画
- API 返回成功后隐藏加载动画
- 页面展示该对公客户的完整信息
- 所有字段值正确显示

#### Scenario: 客户不存在

**Given**: 数据库中不存在指定 ID 的客户记录
**When**: 用户访问不存在的客户详情页
**Then**:
- 调用对应的后端 API
- API 返回 404 错误或空数据
- 显示"客户不存在"的错误页面
- 提供"返回列表"按钮

#### Scenario: 加载失败

**Given**: 用户在客户详情页
**When**: 后端 API 返回错误 (如 500 服务器错误)
**Then**:
- 显示友好的错误提示消息
- 提供"重试"按钮
- 提供"返回列表"按钮

**When**: 用户点击"重试"按钮
**Then**:
- 重新发起 API 请求
- 显示加载动画

**验收标准**:
- 详情页加载时间 < 500ms
- 正确处理客户不存在的情况
- 正确处理网络错误和服务器错误
- 加载状态提示友好

---

### REQ-CDV-003: 客户详情页面导航

**优先级**: High
**类型**: Functional

客户详情页面必须提供便捷的导航功能，支持快速返回列表或跳转到编辑页面。

#### Scenario: 返回客户列表

**Given**: 用户在客户详情页
**When**: 用户点击页面头部的"返回"按钮
**Then**:
- 跳转回客户列表页 `/aicrm/customer`
- 如果是从列表页跳转来的，保留之前的搜索条件和分页状态

**When**: 用户点击面包屑导航的"客户列表"
**Then**:
- 跳转回客户列表页 `/aicrm/customer`

#### Scenario: 快速编辑客户

**Given**: 用户有 `aicrm:customer:update` 权限
**And**: 用户在客户详情页
**When**: 用户点击页面头部的"编辑"按钮
**Then**:
- 打开客户编辑表单模态框
- 表单自动填充当前客户的信息

**When**: 用户修改信息并保存成功
**Then**:
- 关闭编辑表单模态框
- 详情页数据自动刷新
- 显示"保存成功"提示消息

#### Scenario: 浏览器前进后退

**Given**: 用户在客户详情页
**When**: 用户点击浏览器的"后退"按钮
**Then**:
- 返回到上一个页面 (通常是客户列表)
- 浏览器历史记录正确

**When**: 用户点击浏览器的"前进"按钮
**Then**:
- 再次进入客户详情页
- 页面重新加载客户数据

**验收标准**:
- 所有导航功能正常工作
- 返回列表时保留搜索条件 (使用路由查询参数)
- 编辑成功后详情页自动刷新
- 浏览器前进后退按钮正常工作

---

### REQ-CDV-004: 客户详情权限控制（双详情页）

**优先级**: High
**类型**: Security

两个客户详情页面都必须遵守系统的权限规则，确保用户只能查看其有权限的客户详情。

#### Scenario: 有权限查看零售客户详情

**Given**: 用户有 `crm:retail-customer:query` 权限
**And**: 用户有权限访问该零售客户的数据 (数据权限)
**When**: 用户访问零售客户详情页
**Then**:
- 页面正常显示零售客户信息

#### Scenario: 有权限查看对公客户详情

**Given**: 用户有 `crm:company-customer:query` 权限
**And**: 用户有权限访问该对公客户的数据 (数据权限)
**When**: 用户访问对公客户详情页
**Then**:
- 页面正常显示对公客户信息

#### Scenario: 无查询权限

**Given**: 用户没有对应的客户查询权限
  - 零售客户: 没有 `crm:retail-customer:query` 权限
  - 对公客户: 没有 `crm:company-customer:query` 权限
**When**: 用户尝试访问对应的客户详情页
**Then**:
- 页面显示"无权限访问"错误
- 提供"返回首页"按钮

#### Scenario: 无数据权限

**Given**: 用户有对应的客户查询权限
**But**: 用户无权限访问该客户的数据 (数据权限)
  - 例如: 客户属于其他部门，且用户只有部门级数据权限
**When**: 用户访问客户详情页
**Then**:
- 后端 API 返回 403 错误或空数据
- 页面显示"无权限访问该客户"错误
- 提供"返回列表"按钮

#### Scenario: 编辑按钮权限控制

**Given**: 用户有对应的客户查询权限
**But**: 用户没有对应的客户更新权限
  - 零售客户: 没有 `crm:retail-customer:update` 权限
  - 对公客户: 没有 `crm:company-customer:update` 权限
**When**: 用户访问对应的客户详情页
**Then**:
- 页面正常显示客户信息
- "编辑"按钮不显示或显示为禁用状态

**验收标准**:
- 权限控制在前端和后端同时实现
- 前端根据用户权限显示/隐藏操作按钮
- 后端 API 验证用户权限和数据权限
- 权限不足时显示友好的错误提示

---

### REQ-CDV-005: 客户详情数据展示优化

**优先级**: Medium
**类型**: Functional

客户详情页面必须优化数据展示方式，提升用户体验和可读性。

#### Scenario: 空值处理

**Given**: 用户在客户详情页
**And**: 某些字段的值为空 (null 或空字符串)
**Then**:
- 空值字段显示为 "-" 或 "暂无"
- 不显示 "null"、"undefined" 等技术术语

#### Scenario: 长文本处理

**Given**: 用户在客户详情页
**And**: 备注信息 (remark) 字段内容很长 (超过 500 字符)
**Then**:
- 默认显示前 200 字符
- 显示"展开"按钮

**When**: 用户点击"展开"按钮
**Then**:
- 显示完整的备注内容
- "展开"按钮变为"收起"按钮

#### Scenario: 标签展示

**Given**: 用户在客户详情页
**And**: 客户标签 (customerTag) 字段包含多个标签 (逗号分隔)
**Then**:
- 每个标签显示为独立的标签组件
- 不同标签使用不同颜色 (随机或按标签类型)
- 标签之间有适当间距

#### Scenario: 数值格式化

**Given**: 用户在客户详情页
**And**: 信用评分 (creditScore) 字段有值
**Then**:
- 评分显示为数字，保留 2 位小数
- 如果评分 >= 90，显示为绿色
- 如果评分 60-89，显示为蓝色
- 如果评分 < 60，显示为红色

#### Scenario: 日期时间格式化

**Given**: 用户在客户详情页
**And**: 创建时间和更新时间字段有值
**Then**:
- 日期时间格式统一为: `YYYY-MM-DD HH:mm:ss`
- 显示相对时间提示 (悬停时显示)
  - 例如: "2 小时前"、"3 天前"

**验收标准**:
- 所有空值正确处理，不显示技术术语
- 长文本支持展开/收起
- 标签展示美观，易于识别
- 数值格式化准确，颜色编码合理
- 日期时间格式统一，相对时间提示友好

---

### REQ-CDV-006: 客户详情页面性能

**优先级**: Medium
**类型**: Non-Functional

客户详情页面必须满足性能要求，确保快速加载和流畅交互。

#### Scenario: 快速加载详情数据

**Given**: 用户访问客户详情页
**When**: 页面首次加载
**Then**:
- 详情数据加载时间 < 500ms
- 页面渲染流畅，无明显卡顿

#### Scenario: 快速刷新详情数据

**Given**: 用户在客户详情页
**And**: 用户编辑并保存了客户信息
**When**: 编辑表单关闭后
**Then**:
- 详情页自动刷新数据
- 刷新时间 < 300ms
- 刷新过程中显示加载状态

**验收标准**:
- 详情页首次加载时间 < 500ms
- 详情页刷新时间 < 300ms
- 页面渲染流畅，无性能瓶颈
- 支持浏览器缓存优化 (合理使用 HTTP 缓存)

---

### REQ-CDV-007: 客户详情响应式设计

**优先级**: Medium
**类型**: Non-Functional

客户详情页面必须支持响应式设计，在不同设备上提供良好的用户体验。

#### Scenario: 桌面端显示

**Given**: 用户使用桌面浏览器访问 (宽度 > 1200px)
**When**: 用户访问客户详情页
**Then**:
- 信息卡片以两列布局显示 (左右各两列)
- 每列宽度占 50%
- 卡片之间有适当间距

#### Scenario: 平板端显示

**Given**: 用户使用平板设备访问 (宽度 768px - 1200px)
**When**: 用户访问客户详情页
**Then**:
- 信息卡片以单列布局显示
- 卡片宽度占 100%
- 卡片之间间距适当缩小

#### Scenario: 移动端显示

**Given**: 用户使用手机访问 (宽度 < 768px)
**When**: 用户访问客户详情页
**Then**:
- 信息卡片以单列布局显示
- 卡片宽度占 100%
- 页面头部操作按钮变为下拉菜单
- 字段标签和值垂直排列

**验收标准**:
- 响应式布局在不同设备上正常工作
- 移动端操作按钮易于点击 (按钮大小 >= 44x44px)
- 文字大小在移动端清晰可读 (字体大小 >= 14px)
- 页面在横屏和竖屏模式下均正常显示

---

## 技术实现说明

### 后端实现

**涉及文件**:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/retailcustomer/RetailCustomerController.java`
  - 已有 `getRetailCustomer(Long id)` 方法，返回 `RetailCustomerRespVO`
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/companycustomer/CompanyCustomerController.java`
  - 已有 `getCompanyCustomer(Long id)` 方法，返回 `CompanyCustomerRespVO`

**关键点**:
- 零售客户详情调用 `RetailCustomerController.getRetailCustomer()` API
- 对公客户详情调用 `CompanyCustomerController.getCompanyCustomer()` API
- 两个控制器使用各自专属的 RespVO 类型
- 使用 MapStruct 进行 DO 到 VO 的转换
- 数据权限在 Service 层通过 MyBatis Plus 拦截器实现
- 权限验证通过 `@PreAuthorize` 注解实现

### 前端实现

**新增文件**:
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue` - 零售客户详情页面
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/tabs/BasicInfoTab.vue` - 零售客户基本信息 Tab
- `frontend/apps/web-antd/src/views/aicrm/company-customer/detail.vue` - 对公客户详情页面
- `frontend/apps/web-antd/src/views/aicrm/company-customer/tabs/BasicInfoTab.vue` - 对公客户基本信息 Tab

**修改文件**:
- `frontend/apps/web-antd/src/views/aicrm/customer/index.vue` - 修改"查看"按钮逻辑，根据 customerType 路由到不同详情页
- `frontend/apps/web-antd/src/api/aicrm/retail-customer/index.ts` - 添加零售客户 API 类型定义
- `frontend/apps/web-antd/src/api/aicrm/company-customer/index.ts` - 添加对公客户 API 类型定义

**关键点**:
- 两个详情页使用相同的 Tab 结构，但调用不同的 API
- 使用 Ant Design Vue 的 `Tabs`、`Card`、`Descriptions` 组件
- 使用 `Tag` 组件展示标签和徽章
- 使用 Vue Router 进行页面导航
- 使用 Composition API 编写组件逻辑
- 枚举值转换使用字典服务
- 响应式布局使用 Tailwind CSS 或 Ant Design Vue 的栅格系统

### 路由配置

**零售客户详情路由**:
- **路径**: `/aicrm/retail-customer/detail/:id`
- **元信息**:
  - `title`: "零售客户详情"
  - `requiresAuth`: true
  - `permissions`: `['crm:retail-customer:query']`

**对公客户详情路由**:
- **路径**: `/aicrm/company-customer/detail/:id`
- **元信息**:
  - `title`: "对公客户详情"
  - `requiresAuth`: true
  - `permissions`: `['crm:company-customer:query']`

---

## 相关能力

- **customer-list-view**: 客户列表查看能力 (本变更的另一个能力)
  - 从列表跳转到详情页
- **customer-edit**: 客户编辑能力 (已存在)
  - 从详情页打开编辑表单

---

## 测试要求

### 单元测试

**前端**:
- 测试详情页组件渲染
- 测试数据加载逻辑
- 测试导航按钮点击
- 测试空值和异常处理
- 测试枚举值转换

### 集成测试

- 测试完整的用户流程: 列表 -> 详情 -> 编辑 -> 保存 -> 详情刷新
- 测试权限控制: 不同权限用户看到的按钮和数据不同

### E2E 测试

- 测试端到端流程，覆盖主要场景
- 测试在不同浏览器中的兼容性
- 测试响应式布局在不同设备上的表现

---

## 用户文档

需要更新以下文档:

- 用户使用手册: 添加"如何查看客户详情"章节
- 培训材料: 包含客户详情页面的截图和说明

---

## 版本历史

- **v1.0** (2025-10-28): 初始版本，定义客户详情查看的核心需求
