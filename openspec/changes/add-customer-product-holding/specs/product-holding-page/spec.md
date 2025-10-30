# Spec: 产品持有信息页面

## 概述

本规范定义零售客户产品持有信息展示页面的功能需求,包括产品分类树形目录、产品持有列表展示、视图模式切换等核心功能。

---

## ADDED Requirements

### Requirement: 后端产品持有信息 API

后端 SHALL 提供产品持有信息的 CRUD 接口,支持按客户ID和产品分类过滤查询。

#### Scenario: 分页查询产品持有列表

**GIVEN** 客户ID为 123
**AND** 产品分类ID为 5 (可选)
**WHEN** 调用 `GET /admin-api/aicrm/customer-product-holding/page?customerId=123&categoryId=5&pageNo=1&pageSize=10`
**THEN** 返回该客户在指定分类下的产品持有列表
**AND** 返回分页信息 (total, list)
**AND** 每条记录包含: id, customerId, productId, productName, accountNo, holdingAmount, holdingStatus, openDate, matureDate, branchName, interestRate, currencyCode

#### Scenario: 查询所有产品持有(不过滤分类)

**GIVEN** 客户ID为 123
**AND** 不传 categoryId 参数
**WHEN** 调用 `GET /admin-api/aicrm/customer-product-holding/page?customerId=123&pageNo=1&pageSize=10`
**THEN** 返回该客户的所有产品持有列表
**AND** 不受产品分类限制

#### Scenario: 获取单个产品持有详情

**GIVEN** 产品持有ID为 456
**WHEN** 调用 `GET /admin-api/aicrm/customer-product-holding/get?id=456`
**THEN** 返回该产品持有的完整信息
**AND** 包含所有字段

---

### Requirement: 后端产品分类 API

后端 SHALL 提供产品分类的树形结构查询接口。

#### Scenario: 查询产品分类树

**GIVEN** 产品分类表有树形结构数据
**WHEN** 调用 `GET /admin-api/aicrm/product-category/tree`
**THEN** 返回树形结构的产品分类列表
**AND** 每个节点包含: id, categoryCode, categoryName, parentId, categoryLevel, children, isLeaf, productType

#### Scenario: 查询产品分类列表(扁平)

**GIVEN** 需要获取所有产品分类
**WHEN** 调用 `GET /admin-api/aicrm/product-category/list`
**THEN** 返回扁平结构的产品分类列表
**AND** 按 sortOrder 排序

---

### Requirement: 页面布局

页面 SHALL 采用左右布局,左侧显示产品分类树,右侧显示产品持有列表。

#### Scenario: 页面加载时显示默认布局

**GIVEN** 用户进入产品持有信息页面
**WHEN** 页面加载完成
**THEN** 左侧显示产品分类树形目录
**AND** 右侧显示该客户的所有产品持有列表(默认表格视图)
**AND** 产品分类树默认展开第一层

#### Scenario: 响应式布局

**GIVEN** 用户在不同屏幕尺寸下查看页面
**WHEN** 屏幕宽度 >= 1200px
**THEN** 左侧产品分类树宽度为 250px
**WHEN** 屏幕宽度 < 1200px
**THEN** 左侧产品分类树可折叠,默认收起

---

### Requirement: 产品分类树

左侧 SHALL 显示产品分类的树形目录,支持展开/折叠和选择。

#### Scenario: 显示产品分类树

**GIVEN** 后端返回产品分类树数据
**WHEN** 页面加载
**THEN** 左侧显示树形目录
**AND** 显示每个分类的名称和图标
**AND** 叶子节点和非叶子节点有不同的图标

#### Scenario: 点击产品分类节点

**GIVEN** 产品分类树已加载
**AND** 右侧显示所有产品列表
**WHEN** 用户点击某个产品分类节点
**THEN** 该节点高亮显示
**AND** 右侧列表自动过滤,只显示该分类下的产品
**AND** 表格标题显示"产品持有列表 - {分类名称}"

#### Scenario: 点击"全部产品"节点

**GIVEN** 产品分类树顶部有"全部产品"节点
**WHEN** 用户点击"全部产品"
**THEN** 右侧列表显示所有产品(不过滤分类)
**AND** 表格标题显示"产品持有列表 - 全部"

#### Scenario: 展开/折叠产品分类

**GIVEN** 产品分类树有多层级结构
**WHEN** 用户点击非叶子节点的展开/折叠图标
**THEN** 该节点的子节点展开或折叠
**AND** 不触发列表过滤

---

### Requirement: 表格视图模式

右侧产品持有列表 SHALL 支持表格视图模式,使用 VxeTable 展示。

#### Scenario: 表格列定义

**GIVEN** 用户在表格视图模式
**WHEN** 表格加载数据
**THEN** 表格显示以下列:
- 序号 (固定左侧)
- 账号 (accountNo)
- 凭据编号 (receiptNo)
- 合同编号 (contractNo)
- 币种代码 (currencyCode) - 字典映射
- 放款日期 (loanDate)
- 开户日期 (openDate)
- 到期日期 (matureDate)
- 开户网点名称 (branchName)
- 产品名称 (productName)
- 持有金额 (holdingAmount) - 格式化为千分位
- 持有状态 (holdingStatus) - 字典映射,带状态标签
- 利率 (interestRate) - 格式化为百分比

#### Scenario: 表格分页

**GIVEN** 产品持有记录超过 10 条
**WHEN** 用户在表格底部操作分页
**THEN** 支持切换页码
**AND** 支持修改每页显示条数 (10/20/50/100)
**AND** 分页数据从后端加载

#### Scenario: 表格排序

**GIVEN** 用户在表格视图
**WHEN** 用户点击可排序列的表头
**THEN** 表格数据按该列升序或降序排序
**AND** 可排序列包括: openDate, matureDate, holdingAmount

#### Scenario: 表格刷新

**GIVEN** 用户在表格视图
**WHEN** 用户点击工具栏的刷新按钮
**THEN** 重新加载当前页的数据
**AND** 保持当前的分类过滤条件

---

### Requirement: 卡片视图模式

右侧产品持有列表 SHALL 支持卡片视图模式,以卡片形式展示产品。

#### Scenario: 卡片布局

**GIVEN** 用户切换到卡片视图
**WHEN** 卡片视图加载
**THEN** 使用 Grid 布局展示卡片
**AND** 屏幕宽度 >= 1400px 时,每行显示 3 列
**AND** 屏幕宽度 >= 1000px 且 < 1400px 时,每行显示 2 列
**AND** 屏幕宽度 < 1000px 时,每行显示 1 列

#### Scenario: 卡片内容

**GIVEN** 用户在卡片视图
**WHEN** 卡片渲染
**THEN** 每张卡片显示:
- 产品名称 (大标题)
- 账号 (脱敏显示后4位)
- 持有状态 (状态标签)
- 持有金额 (突出显示,千分位格式)
- 币种代码
- 开户日期
- 到期日期
- 利率 (如有)
- 开户网点

#### Scenario: 卡片样式

**GIVEN** 用户在卡片视图
**WHEN** 渲染卡片
**THEN** 每张卡片有:
- 圆角边框
- 阴影效果
- Hover 时阴影加深
- 持有状态标签带颜色(正常=绿色,冻结=红色,关闭=灰色)

#### Scenario: 卡片视图分页

**GIVEN** 产品持有记录很多
**WHEN** 用户在卡片视图底部
**THEN** 显示分页器
**AND** 支持切换页码
**AND** 每页显示固定数量的卡片 (建议 9/12/15)

---

### Requirement: 视图模式切换

用户 SHALL 能够在表格和卡片两种视图模式之间切换。

#### Scenario: 切换到卡片视图

**GIVEN** 用户当前在表格视图
**WHEN** 用户点击工具栏的卡片图标按钮
**THEN** 视图切换为卡片模式
**AND** 显示卡片布局
**AND** 卡片图标按钮变为高亮状态
**AND** 视图模式偏好保存到 localStorage

#### Scenario: 切换到表格视图

**GIVEN** 用户当前在卡片视图
**WHEN** 用户点击工具栏的表格图标按钮
**THEN** 视图切换为表格模式
**AND** 显示表格布局
**AND** 表格图标按钮变为高亮状态
**AND** 视图模式偏好保存到 localStorage

#### Scenario: 记住视图模式偏好

**GIVEN** 用户上次使用卡片视图
**WHEN** 用户重新进入产品持有页面
**THEN** 默认显示卡片视图
**AND** 从 localStorage 读取视图模式偏好

---

### Requirement: 数据格式化

页面 SHALL 对数据进行格式化展示,提升可读性。

#### Scenario: 金额格式化

**GIVEN** 产品持有金额为 123456.78
**WHEN** 在表格或卡片中显示
**THEN** 显示为 "123,456.78"
**AND** 保留 2 位小数

#### Scenario: 日期格式化

**GIVEN** 开户日期为 "2024-01-15"
**WHEN** 在表格或卡片中显示
**THEN** 显示为 "2024-01-15"
**AND** 如果日期为 null,显示为 "-"

#### Scenario: 利率格式化

**GIVEN** 利率为 0.0325
**WHEN** 在表格或卡片中显示
**THEN** 显示为 "3.25%"
**AND** 保留 2 位小数

#### Scenario: 账号脱敏

**GIVEN** 账号为 "6222021234567890"
**WHEN** 在卡片视图中显示
**THEN** 显示为 "****7890"
**AND** 仅显示后 4 位

#### Scenario: 字典值映射

**GIVEN** holdingStatus 为 "01"
**WHEN** 在表格或卡片中显示
**THEN** 显示为对应的中文名称 (如"正常")
**AND** 使用字典服务进行映射

---

### Requirement: 空状态处理

页面 SHALL 在无数据时显示友好的空状态提示。

#### Scenario: 无产品持有记录

**GIVEN** 客户没有任何产品持有记录
**WHEN** 页面加载
**THEN** 右侧显示空状态组件
**AND** 显示图标和提示文字 "暂无产品持有信息"

#### Scenario: 分类过滤后无数据

**GIVEN** 用户选择某个产品分类
**AND** 该分类下没有产品持有记录
**WHEN** 列表刷新
**THEN** 显示空状态组件
**AND** 提示文字为 "该分类下暂无产品"

---

### Requirement: 加载状态

页面 SHALL 在数据加载过程中显示加载状态。

#### Scenario: 初始加载

**GIVEN** 用户进入产品持有页面
**WHEN** 数据正在加载
**THEN** 显示 loading 动画
**AND** 禁用操作按钮

#### Scenario: 分类切换加载

**GIVEN** 用户点击产品分类节点
**WHEN** 列表数据正在加载
**THEN** 右侧显示 loading 状态
**AND** 保持左侧树的可交互性

---

### Requirement: 错误处理

页面 SHALL 妥善处理 API 错误并显示友好提示。

#### Scenario: API 请求失败

**GIVEN** 后端服务异常
**WHEN** 调用产品持有列表 API 失败
**THEN** 显示错误提示消息
**AND** 消息内容为具体的错误原因
**AND** 提供"重试"按钮

#### Scenario: 产品分类树加载失败

**GIVEN** 产品分类 API 请求失败
**WHEN** 页面初始化
**THEN** 左侧显示错误提示
**AND** 提供"重新加载"按钮
**AND** 右侧仍可显示全部产品列表

---

### Requirement: 导航集成

产品持有信息页面 SHALL 集成到零售客户详情页的导航菜单中。

#### Scenario: 添加导航菜单项

**GIVEN** 用户在零售客户详情页
**WHEN** 查看左侧导航菜单
**THEN** 显示"产品持有信息"菜单项
**AND** 菜单项位于"账户信息"和"产品持有信息"之间

#### Scenario: 点击导航菜单

**GIVEN** 用户在零售客户详情页
**WHEN** 用户点击"产品持有信息"菜单项
**THEN** 右侧内容区切换到产品持有信息页面
**AND** 菜单项高亮显示
**AND** customerId 正确传递给产品持有页面

---

## Implementation Notes

1. **产品分类树查询优化**: 使用缓存减少树形结构的重复查询
2. **分页策略**: 表格和卡片视图使用相同的分页API,仅 pageSize 不同
3. **图标选择**:
   - 表格视图图标: `ant-design:table-outlined`
   - 卡片视图图标: `ant-design:appstore-outlined`
   - 产品分类图标: `ant-design:folder-outlined` (非叶子), `ant-design:file-text-outlined` (叶子)
4. **字典配置**:
   - `aicrm_product_holding_status`: 产品持有状态 (01=正常, 02=冻结, 03=关闭)
   - `aicrm_currency_code`: 币种代码 (CNY=人民币, USD=美元)
   - `aicrm_related_account_type`: 关联账户类型
5. **性能优化**:
   - 产品分类树仅加载一次,缓存在组件内
   - 列表数据按需加载,支持虚拟滚动(可选)
6. **Accessibility**:
   - 树形目录支持键盘导航
   - 所有按钮有 aria-label
   - 卡片有合理的 hover 和 focus 样式
