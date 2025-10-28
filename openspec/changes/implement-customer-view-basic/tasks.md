# Tasks: 实现客户查看基础功能

本文档定义了 `implement-customer-view-basic` 变更的具体任务清单。

## 任务概览

- **总任务数**: 10
- **预计工时**: 4-6 个工作日
- **依赖关系**: 任务 1-4 必须顺序执行，任务 5-7 可并行，任务 8-10 依赖前置任务完成

---

## 阶段 1: 后端字典和数据准备 (1-2 天)

### Task 1: 创建 CRM 字典常量类

**描述**: 创建 CRM 模块的字典类型常量类

**输出**:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/enums/CrmDictTypeConstants.java`

**内容**:
```java
public interface CrmDictTypeConstants {
    String CUSTOMER_TYPE = "crm_customer_type";     // 客户类型
    String CUSTOMER_STATUS = "crm_customer_status"; // 客户状态
    String CUSTOMER_LEVEL = "crm_customer_level";   // 客户等级
    String CUSTOMER_SOURCE = "crm_customer_source"; // 客户来源
    String CREDIT_LEVEL = "crm_credit_level";       // 信用等级
}
```

**验收标准**:
- [ ] 常量类包含所有必要的字典类型
- [ ] 常量命名符合项目规范
- [ ] 文件路径正确

**依赖**: 无

---

### Task 2: 创建字典数据初始化 SQL 脚本

**描述**: 创建 SQL 脚本，初始化客户相关的所有字典数据

**输出**:
- `backend/sql/mysql/crm_dict_data.sql`

**内容包括**:
1. 插入字典类型 (system_dict_type 表):
   - crm_customer_type (客户类型)
   - crm_customer_status (客户状态)
   - crm_customer_level (客户等级)
   - crm_customer_source (客户来源)
   - crm_credit_level (信用等级)

2. 插入字典数据项 (system_dict_data 表):
   - 客户类型: 1-零售客户, 2-对公客户
   - 客户状态: 1-正常, 2-冻结, 3-注销
   - 客户等级: 普通、VIP、金卡、钻石卡等
   - 客户来源: 网点开发、电话营销、网络营销、老客介绍等
   - 信用等级: AAA、AA、A、BBB、BB、B、C

**验收标准**:
- [ ] SQL 脚本语法正确，可执行
- [ ] 包含所有必需的字典类型和数据项
- [ ] 字典数据值(value)与代码中使用的枚举值一致
- [ ] 字典数据排序(sort)合理

**依赖**: Task 1

---

### Task 3: 优化 CustomerRespVO（列表页使用）

**描述**: 扩展 CustomerRespVO，添加字典注解并移除客户 ID

**修改文件**:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/customer/vo/CustomerRespVO.java`

**修改内容**:
1. **移除**字段:
   - `id` (客户 ID/主键) - 不在列表中展示

2. **新增**字段:
   - `isHighQuality` (Boolean): 是否优质客户
   - `isImportant` (Boolean): 是否重要客户
   - `creditStatus` (String): 信用状态
   - `creditLevel` (String): 信用等级
   - `creditScore` (BigDecimal): 信用评分
   - `customerSource` (String): 客户来源
   - `customerTag` (String): 客户标签
   - `remark` (String): 备注信息
   - `createTime` (LocalDateTime): 创建时间
   - `updateTime` (LocalDateTime): 更新时间

3. **添加字典注解**:
   - `customerType` 添加 `@DictFormat(CrmDictTypeConstants.CUSTOMER_TYPE)`
   - `customerStatus` 添加 `@DictFormat(CrmDictTypeConstants.CUSTOMER_STATUS)`
   - `customerLevel` 添加 `@DictFormat(CrmDictTypeConstants.CUSTOMER_LEVEL)`
   - `customerSource` 添加 `@DictFormat(CrmDictTypeConstants.CUSTOMER_SOURCE)`
   - `creditLevel` 添加 `@DictFormat(CrmDictTypeConstants.CREDIT_LEVEL)`

**验收标准**:
- [ ] VO 包含所有需要展示的业务字段
- [ ] 客户 ID 字段已移除
- [ ] 所有枚举字段都添加了 `@DictFormat` 注解
- [ ] 字段添加了 Swagger 注解 `@Schema`
- [ ] 字段添加了 Excel 导出注解 `@ExcelProperty`

**依赖**: Task 1

---

### Task 3.1: 扩展零售客户 RespVO 包含公共字段

**描述**: 扩展 RetailCustomerRespVO，添加所有 RetailCustomerDO 字段和 CustomerDO 公共字段

**修改文件**:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/retailcustomer/vo/RetailCustomerRespVO.java`

**修改内容**:
1. **添加所有 RetailCustomerDO 字段**（约50个字段）:
   - 个人信息: nationality, nation, nativePlace, residenceType, domicilePlace 等
   - 职业信息: occupation, occupationType, maritalStatus, religion, education, degree 等
   - VIP 信息: isVip, isCoreVip, vipLevel, isHighNetWorth, netWorthType 等
   - 收入资产: incomeLevel, assetLevel
   - 信誉信息: reputationStatus, reputationLevel, reputationScore 等
   - 其他扩展字段

2. **添加 CustomerDO 公共字段**（16个字段）:
   - customerNo, customerType, customerName, customerLevel, customerStatus
   - isHighQuality, isImportant
   - creditStatus, creditLevel, creditScore
   - customerSource, customerTag, remark
   - deptId, createTime, updateTime

3. **添加字典注解** (公共字段):
   - `customerType` 添加 `@DictFormat("crm_customer_type")`
   - `customerStatus` 添加 `@DictFormat("crm_customer_status")`
   - `customerLevel` 添加 `@DictFormat("crm_customer_level")`
   - `customerSource` 添加 `@DictFormat("crm_customer_source")`
   - `creditLevel` 添加 `@DictFormat("crm_credit_level")`
   - `gender` 添加 `@DictFormat("system_user_sex")` (已有)

**验收标准**:
- [ ] VO 包含所有 RetailCustomerDO 字段
- [ ] VO 包含所有 CustomerDO 公共字段
- [ ] 不展示主键 ID (RetailCustomerDO.id)
- [ ] customerId 字段保留（用于关联，但前端不展示）
- [ ] 所有枚举字段都添加了 `@DictFormat` 注解
- [ ] 字段添加了 Swagger 注解 `@Schema`
- [ ] 字段添加了 Excel 导出注解 `@ExcelProperty`
- [ ] 总计约66个字段

**依赖**: Task 1

---

### Task 3.2: 扩展对公客户 RespVO 包含公共字段

**描述**: 扩展 CompanyCustomerRespVO，添加所有 CompanyCustomerDO 字段和 CustomerDO 公共字段

**修改文件**:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/companycustomer/vo/CompanyCustomerRespVO.java`

**修改内容**:
1. **确认 CompanyCustomerDO 所有字段已存在**（约54个字段）:
   - 企业证照: licenseType, licenseNo, creditCode, organizationCode, taxNo, loanCardNo
   - 企业基本信息: enterpriseType, enterpriseNature, ownershipType, economicType, enterpriseScale
   - 注册信息: registeredCapital, registeredCapitalCurrency, establishDate, businessTerm
   - 行业分类: industryCategoryL1~L4, industryCode
   - 企业特征: isListed, isSmallEnterprise, isGroupCustomer, isImportExport 等
   - 账户信息: basicAccountBank, basicAccountNo
   - 法人信息: legalPersonName, legalPersonIdType, legalPersonIdNo, legalPersonPhone
   - 资质评级: enterpriseQualification, companyRating, ratingAgency, ratingDate 等

2. **添加 CustomerDO 公共字段**（16个字段）:
   - customerNo, customerType, customerName, customerLevel, customerStatus
   - isHighQuality, isImportant
   - creditStatus, creditLevel, creditScore
   - customerSource, customerTag, remark
   - deptId, createTime, updateTime

3. **添加字典注解** (公共字段):
   - `customerType` 添加 `@DictFormat("crm_customer_type")`
   - `customerStatus` 添加 `@DictFormat("crm_customer_status")`
   - `customerLevel` 添加 `@DictFormat("crm_customer_level")`
   - `customerSource` 添加 `@DictFormat("crm_customer_source")`
   - `creditLevel` 添加 `@DictFormat("crm_credit_level")`

**验收标准**:
- [ ] VO 包含所有 CompanyCustomerDO 字段
- [ ] VO 包含所有 CustomerDO 公共字段
- [ ] 不展示主键 ID (CompanyCustomerDO.id)
- [ ] customerId 字段保留（用于关联，但前端不展示）
- [ ] 所有枚举字段都添加了 `@DictFormat` 注解
- [ ] 字段添加了 Swagger 注解 `@Schema`
- [ ] 字段添加了 Excel 导出注解 `@ExcelProperty`
- [ ] 总计约70个字段

**依赖**: Task 1

---

### Task 3.3: 修改零售客户 Service 实现关联查询

**描述**: 修改 RetailCustomerService 的 getRetailCustomer 方法，关联查询 Customer 表获取公共字段

**修改文件**:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/service/retailcustomer/RetailCustomerServiceImpl.java`

**实现方案**:

**方案一: Service 层手动关联（推荐）**
```java
@Override
public RetailCustomerDO getRetailCustomer(Long id) {
    // 1. 查询 RetailCustomerDO
    RetailCustomerDO retailCustomer = retailCustomerMapper.selectById(id);
    if (retailCustomer == null) {
        return null;
    }

    // 2. 查询关联的 CustomerDO
    CustomerDO customer = customerMapper.selectById(retailCustomer.getCustomerId());

    // 3. 创建完整的 RespVO（这部分在 Controller 层通过 MapStruct 或手动合并）
    return retailCustomer;
}
```

**方案二: Mapper XML JOIN 查询**
- 在 RetailCustomerMapper.xml 中编写 LEFT JOIN 查询
- 返回一个包含两个表所有字段的结果集
- 需要创建一个扩展的 DO 或直接映射到 RespVO

**方案三: 创建专门的详情查询方法**
```java
// 在 RetailCustomerService 中新增方法
RetailCustomerDetailVO getRetailCustomerDetail(Long id);

// 实现中关联查询两个表
```

**修改内容**:
1. 在 `RetailCustomerServiceImpl` 中修改或新增 `getRetailCustomer(Long id)` 方法
2. 关联查询 `CustomerDO` 表
3. 将两个对象的字段合并到 `RetailCustomerRespVO`（可以在 Controller 层使用 MapStruct）
4. 或者创建 MapStruct Mapper 专门处理合并逻辑

**验收标准**:
- [ ] `getRetailCustomer(Long id)` 方法能正确返回包含公共字段的数据
- [ ] 通过 customerId 正确关联查询 CustomerDO
- [ ] 返回的 RespVO 包含 RetailCustomerDO 和 CustomerDO 的所有字段
- [ ] 处理了 Customer 不存在的异常情况
- [ ] 单元测试通过

**依赖**: Task 3.1

---

### Task 3.4: 修改对公客户 Service 实现关联查询

**描述**: 修改 CompanyCustomerService 的 getCompanyCustomer 方法，关联查询 Customer 表获取公共字段

**修改文件**:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/service/companycustomer/CompanyCustomerServiceImpl.java`

**实现方案**: 与 Task 3.3 相同，参考零售客户的实现

**修改内容**:
1. 在 `CompanyCustomerServiceImpl` 中修改或新增 `getCompanyCustomer(Long id)` 方法
2. 关联查询 `CustomerDO` 表
3. 将两个对象的字段合并到 `CompanyCustomerRespVO`

**验收标准**:
- [ ] `getCompanyCustomer(Long id)` 方法能正确返回包含公共字段的数据
- [ ] 通过 customerId 正确关联查询 CustomerDO
- [ ] 返回的 RespVO 包含 CompanyCustomerDO 和 CustomerDO 的所有字段
- [ ] 处理了 Customer 不存在的异常情况
- [ ] 单元测试通过

**依赖**: Task 3.2

---

### Task 4: 扩展 CustomerPageReqVO

**描述**: 扩展 CustomerPageReqVO，支持更多搜索条件

**修改文件**:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/customer/vo/CustomerPageReqVO.java`

**新增搜索字段**:
- `customerNo` (String): 客户编号（精确匹配）
- `customerName` (String): 客户名称（模糊匹配）
- `customerType` (Integer): 客户类型
- `customerStatus` (Integer): 客户状态
- `customerLevel` (String): 客户等级
- `customerSource` (String): 客户来源
- `isHighQuality` (Boolean): 是否优质客户
- `isImportant` (Boolean): 是否重要客户
- `createTime` (LocalDateTime[]): 创建时间范围

**验收标准**:
- [ ] PageReqVO 包含所有搜索条件字段
- [ ] 字段添加了 Swagger 注解
- [ ] 时间范围使用数组类型

**依赖**: Task 3

---

## 阶段 2: 前端列表增强 (2-3 天)

### Task 5: 更新前端客户 API 类型定义

**描述**: 扩展前端客户 TypeScript 类型定义，与后端 VO 保持一致

**修改文件**:
- `frontend/apps/web-antd/src/api/aicrm/customer/index.ts`

**修改内容**:
1. 移除 `id` 字段（不再展示）
2. 添加 Task 3 中后端新增的所有字段
3. 添加 `createTime` 和 `updateTime` 字段

**验收标准**:
- [ ] TypeScript 类型与后端 VO 字段一致
- [ ] 所有字段都有正确的类型标注
- [ ] 必填字段标记为非可选（不带 `?`）
- [ ] 移除了客户 ID 字段

**依赖**: Task 3

---

### Task 6: 扩展客户列表表格列定义

**描述**: 在客户列表中展示更多字段，使用字典数据转换显示，并添加智能"查看"按钮

**修改文件**:
- `frontend/apps/web-antd/src/views/aicrm/customer/data.ts` (`useGridColumns` 函数)
- `frontend/apps/web-antd/src/views/aicrm/customer/index.vue` (添加"查看"按钮处理逻辑)

**修改内容**:
1. **移除**列:
   - 客户 ID 列

2. **新增或修改**列:
   - 客户编号 (customerNo)
   - 客户类型 (customerType) - 使用字典转换为文本
   - 客户名称 (customerName)
   - 客户等级 (customerLevel) - 使用字典转换
   - 客户状态 (customerStatus) - 使用字典转换为带颜色的徽章
   - 客户来源 (customerSource) - 使用字典转换
   - 客户标签 (customerTag) - 显示为标签组
   - 是否优质客户 (isHighQuality) - 徽章展示
   - 是否重要客户 (isImportant) - 徽章展示
   - 信用等级 (creditLevel) - 使用字典转换
   - 创建时间 (createTime)
   - 更新时间 (updateTime)

3. 添加智能"查看"操作按钮:
   - 根据 customerType 路由到不同详情页
   - customerType=1 → 零售客户详情页
   - customerType=2 → 对公客户详情页

**验收标准**:
- [ ] 列表展示至少 10 个字段（不包括操作列）
- [ ] 客户 ID 列已移除
- [ ] 枚举值通过字典数据转换为文本
- [ ] 布尔值使用徽章展示
- [ ] 日期格式统一（YYYY-MM-DD HH:mm:ss）
- [ ] "查看"按钮权限控制正确
- [ ] "查看"按钮能根据客户类型智能路由

**依赖**: Task 5

---

### Task 7: 增强客户列表搜索表单

**描述**: 添加更多搜索条件，下拉框从字典加载选项

**修改文件**:
- `frontend/apps/web-antd/src/views/aicrm/customer/data.ts` (`useGridFormSchema` 函数)

**新增搜索条件**:
- 客户编号输入框
- 客户名称输入框
- 客户类型下拉选择（从字典 `crm_customer_type` 加载）
- 客户状态下拉选择（从字典 `crm_customer_status` 加载）
- 客户等级下拉选择（从字典 `crm_customer_level` 加载）
- 客户来源下拉选择（从字典 `crm_customer_source` 加载）
- 是否优质客户开关
- 是否重要客户开关
- 创建时间范围选择器

**验收标准**:
- [ ] 所有搜索条件正常工作
- [ ] 下拉选项从系统字典加载（不是硬编码）
- [ ] 日期范围选择器支持快捷选项
- [ ] 搜索条件与后端 PageReqVO 参数匹配
- [ ] 支持重置功能

**依赖**: Task 5

---

## 阶段 3: 客户详情页开发 (1-2 天)

### Task 8: 创建双客户详情页面组件（多 Tab 结构）

**描述**: 新建**两个**客户详情页面（零售客户和对公客户），均采用**多 Tab 页签**架构，本次只实现"基本信息" Tab

**输出**:

**零售客户详情页**:
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue`
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/tabs/BasicInfoTab.vue` (基本信息 Tab 组件)

**对公客户详情页**:
- `frontend/apps/web-antd/src/views/aicrm/company-customer/detail.vue`
- `frontend/apps/web-antd/src/views/aicrm/company-customer/tabs/BasicInfoTab.vue` (基本信息 Tab 组件)

**页面结构**（两个页面相同）:

1. **页面头部**:
   - 客户名称（大标题）
   - 客户类型标签（"零售客户" 或 "对公客户"）
   - 客户状态徽章
   - 操作按钮组（编辑、返回）
   - 面包屑导航

2. **Tab 页签导航**:
   - 使用 Ant Design Vue 的 `<a-tabs>` 组件
   - Tab 列表:
     - ✅ "基本信息" Tab (本次实现)
     - 🚧 "联系人" Tab (占位，禁用或显示"开发中")
     - 🚧 "跟进记录" Tab (占位，禁用或显示"开发中")
     - 🚧 "商机" Tab (占位，禁用或显示"开发中")
     - 🚧 "合同" Tab (占位，禁用或显示"开发中")

3. **"基本信息" Tab 内容** (本次实现):
   - 基本信息卡片（客户编号、类型、名称、等级、状态、部门）
   - 信用信息卡片（信用状态、信用等级、信用评分）
   - 业务信息卡片（客户来源、客户标签、是否优质/重要客户）
   - 其他信息卡片（备注、创建时间、更新时间）

**实现建议**:
- 两个详情页结构相同，但调用不同的 API:
  - 零售客户: `/crm/retail-customer/get?id={id}`
  - 对公客户: `/crm/company-customer/get?id={id}`
- 使用 `<a-tabs>` 组件创建 Tab 页签
- "基本信息" Tab 内容单独封装为 `BasicInfoTab.vue` 组件
- 其他 Tab 可以显示占位内容或设置为禁用状态
- 使用 `<a-descriptions>` 或 `<a-card>` 组件展示信息卡片
- 枚举值通过字典数据转换显示

**验收标准**:
- [ ] 两个详情页面都已创建（零售和对公）
- [ ] Tab 页签导航结构已创建
- [ ] 默认激活"基本信息" Tab
- [ ] "基本信息" Tab 内容完整展示
- [ ] 其他 Tab 有占位结构（禁用或显示"开发中"）
- [ ] 页面布局合理，信息分组清晰
- [ ] 所有字段正确展示
- [ ] 枚举值通过字典数据转换为文本
- [ ] 布尔值使用徽章展示
- [ ] 支持响应式布局
- [ ] 不展示客户 ID
- [ ] 两个详情页调用各自专属的后端 API
- [ ] 权限控制正确（零售: crm:retail-customer:query, 对公: crm:company-customer:query）
- [ ] 代码结构清晰，易于后续扩展其他 Tab

**依赖**: Task 5

---

### Task 9: 添加双客户详情页路由和智能导航

**描述**: 配置双详情页路由，实现列表到详情的**智能导航**（根据客户类型自动路由）

**修改文件**:
- `frontend/apps/web-antd/src/views/aicrm/customer/index.vue`
- 可能需要修改路由配置

**实现**:
1. 在列表的操作列添加"查看"按钮
2. 实现智能路由逻辑:
   ```javascript
   function handleView(row) {
     if (row.customerType === 1) {
       // 零售客户
       router.push(`/aicrm/retail-customer/detail/${row.id}`);
     } else if (row.customerType === 2) {
       // 对公客户
       router.push(`/aicrm/company-customer/detail/${row.id}`);
     }
   }
   ```
3. 配置两个详情页路由:
   - `/aicrm/retail-customer/detail/:id` (权限: `crm:retail-customer:query`)
   - `/aicrm/company-customer/detail/:id` (权限: `crm:company-customer:query`)
4. 详情页添加"返回"按钮

**验收标准**:
- [ ] 点击零售客户的"查看"按钮跳转到零售客户详情页
- [ ] 点击对公客户的"查看"按钮跳转到对公客户详情页
- [ ] 两个详情页 URL 包含客户 ID 参数
- [ ] "返回"按钮能够返回列表页
- [ ] 浏览器前进/后退按钮正常工作
- [ ] 按钮权限控制生效（零售和对公权限分别控制）
- [ ] 路由根据 customerType 自动区分

**依赖**: Task 6, Task 8

---

## 阶段 4: 测试和验收 (1 天)

### Task 10: 完整测试和验收

**描述**: 进行完整的功能测试和验收

**测试清单**:

**后端测试**:
- [ ] 执行字典数据初始化 SQL 脚本
- [ ] 验证字典数据正确插入
- [ ] 测试客户列表查询 API
- [ ] 测试各种搜索条件
- [ ] 测试数据权限过滤
- [ ] 验证 `@DictFormat` 注解生效

**前端测试**:
- [ ] 客户列表正确展示所有字段（不包括客户 ID）
- [ ] 枚举值正确转换为文本
- [ ] 下拉框选项从字典加载
- [ ] 搜索和筛选功能正常
- [ ] "查看"按钮跳转到详情页
- [ ] 详情页展示完整信息
- [ ] 详情页不展示客户 ID
- [ ] 响应式布局在不同设备正常

**性能测试**:
- [ ] 列表加载时间 < 1s（1000 条记录）
- [ ] 搜索响应时间 < 500ms

**兼容性测试**:
- [ ] Chrome 浏览器正常
- [ ] Firefox 浏览器正常
- [ ] Safari 浏览器正常
- [ ] Edge 浏览器正常

**验收标准**:
- [ ] 所有测试项通过
- [ ] 无已知 Bug
- [ ] 代码符合项目规范
- [ ] API 文档已更新

**依赖**: Task 1-9 全部完成

---

## 任务依赖关系图

```
Task 1 (字典常量类)
  ↓
Task 2 (字典数据 SQL) ───┐
  ↓                      │
Task 3 (优化 RespVO) ────┤
  ↓                      │
Task 4 (扩展 ReqVO) ─────┤
  ↓                      │
Task 5 (前端类型定义) ───┤
  ↓                      ↓
Task 6 (列表列定义) ─┬─→ Task 10 (测试验收)
  ↓                  │    ↑
Task 7 (搜索表单) ───┤    │
  ↓                  │    │
Task 8 (详情页) ─────┤    │
  ↓                  │    │
Task 9 (路由导航) ───┴────┘
```

## 并行执行建议

以下任务可以并行执行以加快进度:

**并行组 1** (阶段 2):
- Task 6: 扩展客户列表表格列定义
- Task 7: 增强客户列表搜索表单

**并行组 2** (阶段 3):
- Task 8: 创建客户详情页面组件（可与 Task 6、7 部分并行）

## 里程碑

- **里程碑 1**: 后端字典和数据准备完成（Task 1-4 完成）
- **里程碑 2**: 前端列表增强完成（Task 5-7 完成）
- **里程碑 3**: 客户详情页完成（Task 8-9 完成）
- **里程碑 4**: 测试和验收完成（Task 10 完成）

## 备注

**重要变更**:
1. **不展示客户 ID**: 列表和详情页都不显示客户 ID（主键）
2. **使用系统字典**: 所有枚举值通过系统字典管理，不硬编码
3. **仅关注查看功能**: 不包括创建、编辑、删除、导出等操作功能
4. **双详情页架构**: 根据客户类型（零售/对公）提供两个独立的详情页面
   - 零售客户详情: `/aicrm/retail-customer/detail/:id` (调用 RetailCustomerController)
   - 对公客户详情: `/aicrm/company-customer/detail/:id` (调用 CompanyCustomerController)
   - 列表"查看"按钮根据 customerType 智能路由
5. **多 Tab 架构**: 两个详情页均采用多 Tab 结构，当前只实现"基本信息" Tab

**注意事项**:
- 所有任务完成后，需要将本变更部署到测试环境进行用户验收
- 如果发现新的问题或需求，应创建新的 Issue 或变更提案
- 字典数据 SQL 脚本需要在数据库初始化时执行
- 零售客户和对公客户使用各自专属的权限控制
- 两个详情页的 Tab 结构相同，但调用的 API 不同
