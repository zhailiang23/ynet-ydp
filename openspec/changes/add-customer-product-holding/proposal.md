# Proposal: 实现零售客户产品持有信息页面

## 概述

为零售客户详情页面添加"产品持有信息"模块,展示客户持有的各类金融产品。页面采用**左右布局**:左侧显示产品目录树形结构,右侧显示产品持有列表,支持按产品分类过滤,并支持表格和卡片两种视图模式切换。

## 背景

- **后端数据表已存在**:
  - `crm_customer_product_holding`: 客户产品持有信息表
  - `crm_product_category`: 产品分类目录表(树形结构)
- **后端 API 需开发**: Controller、Service、Mapper 等后端代码需要实现
- **前端需开发**: 完整的产品持有信息展示页面

产品持有信息是客户 360 视图的重要组成部分,涵盖存款、贷款、理财、基金、信托、保险等各类金融产品的持有情况。

## 目标

1. 开发后端产品持有信息和产品分类的 CRUD 接口
2. 开发前端产品持有信息页面 (`product-holding.vue`)
3. 实现左右布局:左侧产品目录树,右侧产品列表
4. 支持按产品分类过滤产品持有列表
5. 支持表格和卡片两种视图模式切换
6. 集成到零售客户详情页左侧导航

## 范围

### 包含

**后端开发**:
- 产品持有信息 Controller、Service、Mapper、VO
- 产品分类 Controller、Service、Mapper、VO
- 分页查询、按客户ID查询、按产品分类过滤
- 数据字典配置(持有状态、关联账户类型等)

**前端开发**:
- 产品持有信息页面(左右布局)
- 产品分类树形目录组件
- 产品持有列表(表格模式)
- 产品持有卡片视图
- 视图模式切换功能
- 与后端 API 对接
- 导航菜单集成

### 不包含

- 产品持有信息的新增/编辑功能(后续迭代)
- 产品分类的管理功能(使用现有系统管理功能)
- 产品详情页面(后续迭代)
- 产品关联账户的联动查询(后续迭代)

## 设计参考

### 页面布局参考
- **左右布局**: 类似文件管理器的目录树+内容列表布局
- **产品目录树**: 参考 Ant Design Vue 的 Tree 组件
- **视图切换**: 参考 `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/identity-list.vue`

### 技术方案

**后端**:
- 标准三层架构: Controller -> Service -> Mapper
- 产品分类使用树形结构查询
- 支持按 `category_id` 过滤产品持有列表
- 返回分页数据

**前端**:
- 使用 `a-layout` 实现左右布局
- 左侧使用 `a-tree` 显示产品分类
- 右侧使用 `useVbenVxeGrid` (表格模式) 或自定义卡片布局
- 视图模式状态存储在 localStorage
- 点击产品分类时,右侧列表自动过滤

## 技术栈

**后端**:
- Spring Boot 3.5.5
- MyBatis Plus 3.5.14
- Spring Security (权限控制)

**前端**:
- Vue 3 Composition API
- Ant Design Vue (Layout, Tree, Tabs 组件)
- VxeTable (表格模式)
- TypeScript
- Vben Hooks (字典映射)

## 关键文件

### 新增文件

**后端**:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/controller/admin/customerproductholding/CustomerProductHoldingController.java`
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/controller/admin/productcategory/ProductCategoryController.java`
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/service/customerproductholding/CustomerProductHoldingService.java`
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/service/productcategory/ProductCategoryService.java`
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/dal/dataobject/customerproductholding/CustomerProductHoldingDO.java`
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/dal/dataobject/productcategory/ProductCategoryDO.java`
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/dal/mysql/customerproductholding/CustomerProductHoldingMapper.java`
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/dal/mysql/productcategory/ProductCategoryMapper.java`
- VO 文件 (SaveReqVO, RespVO, PageReqVO 等)

**前端**:
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/product-holding.vue` - 主页面
- `frontend/apps/web-antd/src/api/aicrm/customerproductholding/index.ts` - 产品持有 API
- `frontend/apps/web-antd/src/api/aicrm/productcategory/index.ts` - 产品分类 API

### 修改文件

**前端**:
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue` - 添加导航菜单项

## 依赖

- ✅ 数据库表已存在 (`crm_customer_product_holding`, `crm_product_category`)
- ⚠️ 后端 Controller/Service/Mapper 需要开发
- ⚠️ 前端 API 客户端需要开发
- ⚠️ 字典数据需要配置(持有状态、关联账户类型)

## 验收标准

1. ✅ 后端产品持有信息和产品分类 API 实现并测试通过
2. ✅ 零售客户详情页左侧菜单新增"产品持有信息"入口
3. ✅ 点击进入产品持有页面,显示左右布局
4. ✅ 左侧显示产品分类树形目录
5. ✅ 右侧默认显示所有产品持有列表(表格视图)
6. ✅ 点击左侧产品分类,右侧列表自动过滤
7. ✅ 支持切换到卡片视图
8. ✅ 表格视图支持分页、排序、刷新
9. ✅ 卡片视图显示产品关键信息,布局美观
10. ✅ 字典值正确映射显示(持有状态、币种等)
11. ✅ 数据为空时显示友好的空状态
12. ✅ 视图模式偏好保存到 localStorage

## 风险和缓解

| 风险 | 影响 | 缓解措施 |
|------|------|----------|
| 产品分类树形结构查询复杂 | 性能问题 | 使用递归查询或 MyBatis Plus 的树形工具 |
| 产品持有数据量大 | 加载慢 | 实现分页和懒加载 |
| 产品分类和产品持有的关联查询 | SQL 复杂 | 先简化为两次查询,后续优化 |
| 卡片视图字段选择不当 | 信息展示不完整 | 与产品确认关键字段 |

## 时间估算

**后端开发**:
- 产品持有 CRUD: 3 小时
- 产品分类 CRUD: 2 小时
- 联调和测试: 1 小时

**前端开发**:
- 主页面和布局: 2 小时
- 产品分类树: 2 小时
- 表格视图: 1.5 小时
- 卡片视图: 2 小时
- 导航集成和联调: 1 小时
- 测试和优化: 1.5 小时

**总计: 约 16 小时 (2 个工作日)**

## 后续工作

1. 添加产品持有信息的新增/编辑功能
2. 实现产品详情弹窗
3. 支持产品持有数据导出
4. 实现产品关联账户的联动查询
5. 添加产品持有统计图表
