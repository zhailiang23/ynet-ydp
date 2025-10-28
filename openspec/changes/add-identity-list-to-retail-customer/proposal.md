# Add Customer Identity List to Retail Customer Detail View

## Why

零售客户详情视图目前缺少客户证件信息的展示功能。根据业务需求,零售客户可以拥有多个证件(身份证、护照、驾驶证等),需要在客户详情页面的左侧菜单中提供"客户证件信息"入口,并展示完整的证件列表。

## What Changes

- **新增功能**: 在零售客户详情视图(`/aicrm/retail-customer/detail/:id`)中添加客户证件信息列表页面
- **菜单集成**: 将证件信息列表与左侧菜单的"客户证件信息"菜单项链接
- **列表字段**: 展示序号、证件类型、证件名称、证件号码、签发日期、失效日期、有效性、发证机关、证件更新时间、证件更新人等字段
- **数据来源**: 从 `crm_customer_identity` 表读取数据,通过 `/admin-api/crm/customer-identity/page` API 获取

## Impact

### 受影响的能力 (Affected Specs)
- **retail-customer-detail-view**: 零售客户详情查看能力(新增证件信息列表子功能)

### 受影响的代码 (Affected Code)
- **后端**:
  - `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/controller/admin/customeridentity/CustomerIdentityController.java` (已存在,需确认分页接口)
  - `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/service/customeridentity/CustomerIdentityService.java` (已存在)

- **前端**:
  - `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue` (需更新菜单配置和路由)
  - `frontend/apps/web-antd/src/views/aicrm/retail-customer/components/IdentityList.vue` (新增证件列表组件)
  - `frontend/apps/web-antd/src/api/aicrm/customer-identity.ts` (API 定义)

### 数据库影响
- 无需修改数据库表结构 (证件信息表 `crm_customer_identity` 已存在)
- 已有测试数据可供验证

### 依赖关系
- 依赖 `customer-identity` CRUD 功能已实现
- 依赖系统字典服务提供证件类型枚举转换

## Non-Goals (本次变更不包括)

- 不包括证件信息的新增、编辑、删除功能
- 不包括证件照片的上传和预览功能
- 不包括证件信息的核验功能
