# Proposal: 实现客户查看基础功能

## Why

当前 CRM 客户管理模块具备基本的 CRUD 功能，但客户信息查看体验不佳：列表只展示部分字段且枚举值显示为数字，缺少专门的详情页无法全面查看客户信息。为了提升用户体验和业务效率，需要增强客户查看功能，提供完整的信息展示和便捷的导航。

## What Changes

- 扩展客户列表展示字段（从 6 个增加到 10+ 个关键字段）
- **移除客户 ID 展示**（列表和详情页都不显示主键）
- 使用系统字典管理所有枚举值（客户类型、状态、等级、来源、信用等级）
- **新增双详情页面架构**，根据客户类型提供专属详情页:
  - 零售客户详情页（调用 RetailCustomerController）
  - 对公客户详情页（调用 CompanyCustomerController）
  - 两个详情页均采用多 Tab 结构，当前只实现"基本信息" Tab
- 增强列表搜索功能，支持多条件筛选（类型、状态、等级、来源、时间范围等）
- 添加列表到详情的**智能导航**（根据 customerType 自动路由到对应详情页）
- 完善后端 VO 返回所有业务字段（信用信息、业务标签等）
- 创建字典数据初始化 SQL 脚本

**注意**: 本次变更**仅关注**查看和查询功能，不包括创建、编辑、删除、导出等操作功能（这些功能已存在或将在其他变更中实现）。

## Impact

**受影响的规范:**
- `customer-list-view` (新增): 客户列表查看能力
- `customer-detail-view` (新增): 客户详情查看能力

**受影响的代码:**

后端:
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/enums/CrmDictTypeConstants.java` (新增字典常量类)
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/customer/vo/CustomerRespVO.java` (移除 ID，新增字段，添加 @DictFormat 注解)
- `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/crm/controller/admin/customer/vo/CustomerPageReqVO.java` (扩展搜索条件)
- `backend/sql/mysql/crm_dict_data.sql` (新增字典数据脚本)

前端:
- `frontend/apps/web-antd/src/views/aicrm/customer/index.vue` (修改列表，移除 ID 列，添加智能路由逻辑)
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue` (新增零售客户详情页)
- `frontend/apps/web-antd/src/views/aicrm/retail-customer/tabs/BasicInfoTab.vue` (零售客户基本信息 Tab)
- `frontend/apps/web-antd/src/views/aicrm/company-customer/detail.vue` (新增对公客户详情页)
- `frontend/apps/web-antd/src/views/aicrm/company-customer/tabs/BasicInfoTab.vue` (对公客户基本信息 Tab)
- `frontend/apps/web-antd/src/views/aicrm/customer/data.ts` (扩展列定义，使用字典)
- `frontend/apps/web-antd/src/api/aicrm/customer/index.ts` (更新类型定义，移除 ID)

**用户影响:**
- 用户可以在列表中快速了解更多客户信息
- 用户可以通过专属详情页查看客户完整信息（零售和对公分别展示）
- 列表"查看"按钮自动识别客户类型并跳转到对应详情页
- 搜索和筛选更加灵活高效
- 枚举值显示为可读文本，不再是数字
- 管理员可通过字典管理功能维护枚举值，无需修改代码

**技术影响:**
- 前后端数据传输量略有增加（增加字段）
- 详情页新增**两个**路由（零售客户和对公客户）
- 新增字典数据初始化 SQL 脚本
- 列表页增加智能路由逻辑（根据 customerType 判断）
- 零售和对公客户使用各自专属的后端 API 和权限控制
- 无破坏性变更，向后兼容

**预计工时:** 4-6 个工作日

**风险:**
- 现有客户数据可能存在空值，需要前端妥善处理
- 字典数据需要与代码中的枚举值保持一致

## Related Documents

- 项目文档: `openspec/project.md`
- 任务清单: `openspec/changes/implement-customer-view-basic/tasks.md`
- 详细规范:
  - `openspec/changes/implement-customer-view-basic/specs/customer-list-view/spec.md`
  - `openspec/changes/implement-customer-view-basic/specs/customer-detail-view/spec.md`
