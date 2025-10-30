# Tasks: 实现零售客户产品持有信息页面

## Phase 1: 后端产品持有信息 API 开发 (3小时)

- [ ] 创建 CustomerProductHoldingDO 实体类
- [ ] 创建 CustomerProductHoldingMapper 接口和 XML
- [ ] 创建 CustomerProductHoldingService 接口和实现类
- [ ] 创建 CustomerProductHoldingController
- [ ] 创建 VO 类 (SaveReqVO, RespVO, PageReqVO)
- [ ] 实现分页查询接口 (支持 customerId 和 categoryId 过滤)
- [ ] 实现获取单个产品持有详情接口
- [ ] 添加权限注解 (@PreAuthorize)
- [ ] 编写单元测试
- [ ] 使用 Postman/curl 测试 API

## Phase 2: 后端产品分类 API 开发 (2小时)

- [ ] 创建 ProductCategoryDO 实体类
- [ ] 创建 ProductCategoryMapper 接口和 XML
- [ ] 创建 ProductCategoryService 接口和实现类
- [ ] 创建 ProductCategoryController
- [ ] 创建 VO 类 (RespVO, TreeNodeVO)
- [ ] 实现树形结构查询接口 (/tree)
- [ ] 实现扁平列表查询接口 (/list)
- [ ] 添加权限注解
- [ ] 测试树形结构数据返回

## Phase 3: 字典数据配置 (30分钟)

- [ ] 创建产品持有状态字典 (aicrm_product_holding_status)
  - 01: 正常
  - 02: 冻结
  - 03: 关闭
- [ ] 创建币种代码字典 (aicrm_currency_code)
  - CNY: 人民币
  - USD: 美元
  - EUR: 欧元
  - HKD: 港币
- [ ] 创建关联账户类型字典 (aicrm_related_account_type)
- [ ] 验证字典数据在系统中可用

## Phase 4: 前端 API 客户端开发 (1小时)

- [ ] 创建产品持有 API 文件 `frontend/apps/web-antd/src/api/aicrm/customerproductholding/index.ts`
- [ ] 定义产品持有类型接口 (CustomerProductHolding)
- [ ] 实现 `getCustomerProductHoldingPage` 方法
- [ ] 实现 `getCustomerProductHolding` 方法
- [ ] 创建产品分类 API 文件 `frontend/apps/web-antd/src/api/aicrm/productcategory/index.ts`
- [ ] 定义产品分类类型接口 (ProductCategory, ProductCategoryTreeNode)
- [ ] 实现 `getProductCategoryTree` 方法
- [ ] 实现 `getProductCategoryList` 方法

## Phase 5: 页面基础结构 (2小时)

- [ ] 创建 `product-holding.vue` 文件
- [ ] 实现左右布局结构 (使用 a-layout-sider 和 a-layout-content)
- [ ] 配置 Sider 样式 (宽度 250px,可折叠)
- [ ] 添加页面标题和工具栏
- [ ] 添加视图切换按钮组 (表格/卡片)
- [ ] 实现视图模式状态管理 (ref + localStorage)
- [ ] 添加页面基础样式

## Phase 6: 产品分类树实现 (2小时)

- [ ] 在左侧 Sider 中添加 a-tree 组件
- [ ] 实现产品分类树数据加载 (getProductCategoryTree)
- [ ] 添加"全部产品"根节点
- [ ] 配置树的图标 (文件夹/文件图标)
- [ ] 实现树节点点击事件 (selectedCategoryId)
- [ ] 实现树的展开/折叠功能
- [ ] 添加树的搜索功能 (可选)
- [ ] 优化树的样式 (缩进、高亮等)

## Phase 7: 表格视图实现 (1.5小时)

- [ ] 配置 VxeTable Grid (使用 useVbenVxeGrid)
- [ ] 定义表格列配置 (13 列)
- [ ] 实现表格数据加载 (getCustomerProductHoldingPage)
- [ ] 实现分类过滤逻辑 (watch selectedCategoryId)
- [ ] 添加字典映射 (持有状态、币种)
- [ ] 实现金额格式化 (formatMoney)
- [ ] 实现日期格式化 (formatDate)
- [ ] 实现利率格式化 (formatRate)
- [ ] 配置表格分页
- [ ] 配置表格排序
- [ ] 添加刷新按钮

## Phase 8: 卡片视图实现 (2小时)

- [ ] 创建卡片容器和 Grid 布局
- [ ] 实现卡片数据加载 (复用表格 API)
- [ ] 设计卡片内容布局 (产品名称、账号、金额等)
- [ ] 实现账号脱敏显示 (maskAccountNo)
- [ ] 实现持有状态标签 (带颜色)
- [ ] 添加卡片样式 (圆角、阴影、hover 效果)
- [ ] 实现卡片响应式布局 (3/2/1 列)
- [ ] 实现卡片视图分页
- [ ] 添加卡片视图刷新按钮
- [ ] 优化卡片空状态展示

## Phase 9: 数据格式化和工具函数 (1小时)

- [ ] 实现 formatMoney 函数 (千分位 + 小数位)
- [ ] 实现 formatDate 函数 (统一日期格式)
- [ ] 实现 formatRate 函数 (百分比格式)
- [ ] 实现 maskAccountNo 函数 (账号脱敏)
- [ ] 实现 getDict 函数 (字典值映射)
- [ ] 实现 getStatusColor 函数 (状态标签颜色)
- [ ] 测试所有格式化函数

## Phase 10: 空状态和加载状态 (30分钟)

- [ ] 添加空状态组件 (a-empty)
- [ ] 配置空状态图标和文字
- [ ] 实现 loading 状态显示
- [ ] 优化 loading 动画效果
- [ ] 测试空数据情况
- [ ] 测试加载状态切换

## Phase 11: 错误处理 (30分钟)

- [ ] 实现 API 错误捕获和提示
- [ ] 添加重试功能
- [ ] 实现产品分类树加载失败处理
- [ ] 实现列表加载失败处理
- [ ] 测试各种错误场景
- [ ] 优化错误提示文案

## Phase 12: 导航集成 (1小时)

- [ ] 修改 `detail.vue`,导入 `product-holding.vue` 组件
- [ ] 在 `menuItems` 数组中添加"产品持有信息"菜单项
- [ ] 配置菜单项的 key 为 'product',label 为 '产品持有信息'
- [ ] 将菜单项插入到"账户信息"和"产品持有信息"之间
- [ ] 测试菜单点击和页面切换
- [ ] 测试菜单高亮状态
- [ ] 验证 customerId 正确传递

## Phase 13: 样式优化 (1小时)

- [ ] 优化左右布局间距和边距
- [ ] 优化产品分类树样式
- [ ] 优化表格列宽和对齐
- [ ] 优化卡片布局和间距
- [ ] 优化状态标签样式
- [ ] 优化按钮图标和提示
- [ ] 实现暗色主题适配
- [ ] 测试响应式布局

## Phase 14: 全量测试 (1.5小时)

- [ ] 测试产品分类树加载和展开
- [ ] 测试点击分类节点,列表过滤
- [ ] 测试"全部产品"节点
- [ ] 测试表格视图数据展示
- [ ] 测试表格分页、排序、刷新
- [ ] 测试卡片视图数据展示
- [ ] 测试视图模式切换
- [ ] 测试视图模式偏好持久化
- [ ] 测试空状态展示
- [ ] 测试错误处理
- [ ] 测试响应式布局 (不同屏幕尺寸)
- [ ] 测试数据格式化 (金额、日期、利率等)
- [ ] 测试字典映射
- [ ] 性能测试 (大数据量)
- [ ] 浏览器兼容性测试
- [ ] 修复发现的问题

## Phase 15: 数据准备和测试数据 (1小时)

- [ ] 检查 crm_customer_product_holding 表的测试数据
- [ ] 检查 crm_product_category 表的测试数据
- [ ] 确保测试数据的 tenant_id 正确 (应为 1)
- [ ] 创建多层级的产品分类测试数据
- [ ] 创建不同状态的产品持有测试数据
- [ ] 验证产品持有和产品分类的关联关系

## Phase 16: 文档和交付 (30分钟)

- [ ] 更新 OpenSpec tasks.md 状态
- [ ] 截图记录产品分类树和列表展示效果
- [ ] 编写功能说明文档
- [ ] 提交代码到 Git
- [ ] 推送到远程仓库
- [ ] 通知测试人员进行验收测试

## 依赖关系

- Phase 1-3 (后端开发) 必须先完成,Phase 4-16 依赖后端 API
- Phase 4 (API 客户端) 必须在 Phase 5-16 之前完成
- Phase 5-6 可以并行开发
- Phase 7-8 可以并行开发 (表格和卡片视图)
- Phase 9-11 依赖 Phase 7-8
- Phase 12 依赖 Phase 5-11
- Phase 13-14 依赖所有前序阶段
- Phase 15 可以在开发过程中并行准备

## 验收标准

所有任务完成后,需满足以下验收标准:

1. ✅ 后端产品持有和产品分类 API 全部实现并测试通过
2. ✅ 零售客户详情页左侧菜单显示"产品持有信息"入口
3. ✅ 点击进入产品持有页面,显示左右布局
4. ✅ 左侧显示产品分类树形目录,支持展开/折叠
5. ✅ 右侧默认显示所有产品持有列表 (表格视图)
6. ✅ 点击产品分类,右侧列表自动过滤
7. ✅ 支持切换到卡片视图
8. ✅ 表格视图支持分页、排序、刷新
9. ✅ 卡片视图布局美观,响应式适配
10. ✅ 所有字典值正确映射显示
11. ✅ 金额、日期、利率格式化正确
12. ✅ 账号正确脱敏显示
13. ✅ 数据为空时显示友好的空状态
14. ✅ 视图模式偏好保存到 localStorage
15. ✅ API 请求失败时显示错误提示
16. ✅ 产品分类树和列表联动正常
17. ✅ 代码通过 ESLint 和 TypeScript 检查
18. ✅ 无控制台错误和警告

## 预计工时

- Phase 1: 3.0 小时
- Phase 2: 2.0 小时
- Phase 3: 0.5 小时
- Phase 4: 1.0 小时
- Phase 5: 2.0 小时
- Phase 6: 2.0 小时
- Phase 7: 1.5 小时
- Phase 8: 2.0 小时
- Phase 9: 1.0 小时
- Phase 10: 0.5 小时
- Phase 11: 0.5 小时
- Phase 12: 1.0 小时
- Phase 13: 1.0 小时
- Phase 14: 1.5 小时
- Phase 15: 1.0 小时
- Phase 16: 0.5 小时

**总计: 21.0 小时 (约 2.5 个工作日)**
