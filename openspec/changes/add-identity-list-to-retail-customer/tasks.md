# Implementation Tasks

## 1. Backend Verification and Preparation

- [ ] 1.1 验证 `CustomerIdentityController.java` 的 `getCustomerIdentityPage` 方法是否支持按 `customerId` 过滤
- [ ] 1.2 验证 `CustomerIdentityPageReqVO` 是否包含 `customerId` 字段
- [ ] 1.3 验证 `CustomerIdentityRespVO` 是否包含所有需要展示的字段(identityType, identityName, identityNo, issueDate, expiryDate, issueAuthority, updateTime, updater)
- [ ] 1.4 验证 `identityType` 字段是否使用 `@DictFormat("customer_identity_type")` 注解
- [ ] 1.5 验证系统字典表中是否存在 `customer_identity_type` 字典类型及完整的字典数据

## 2. Frontend - API Integration

- [ ] 2.1 检查或创建 `frontend/apps/web-antd/src/api/aicrm/customer-identity.ts` 文件
- [ ] 2.2 定义 `getCustomerIdentityPageApi(params)` 函数,调用 `/admin-api/crm/customer-identity/page` 接口
- [ ] 2.3 定义 TypeScript 接口类型:
  - [ ] `CustomerIdentityVO` (响应数据类型)
  - [ ] `CustomerIdentityPageParams` (请求参数类型,包含 customerId, pageNo, pageSize)

## 3. Frontend - Utility Functions

- [ ] 3.1 创建工具函数 `maskIdNumber(idNumber: string): string` 用于证件号码脱敏
  - 保留前 4 位和后 4 位,中间显示为 ****
  - 处理边界情况(空字符串、长度不足 8 位)
- [ ] 3.2 创建工具函数 `getValidityStatus(expiryDate: string)` 用于判断证件有效性
  - 返回有效性文本和徽章类型
  - 处理长期有效(9999-12-31)的特殊情况

## 4. Frontend - Identity List Component

- [ ] 4.1 创建 `frontend/apps/web-antd/src/views/aicrm/retail-customer/components/IdentityList.vue` 组件
- [ ] 4.2 实现列表表格结构(使用 ant-design-vue Table 组件)
- [ ] 4.3 定义列配置,包含以下字段:
  - [ ] 序号 (行号)
  - [ ] 证件类型 (使用字典转换)
  - [ ] 证件名称
  - [ ] 证件号码 (使用脱敏函数)
  - [ ] 签发日期 (格式化为 YYYY-MM-DD)
  - [ ] 失效日期 (格式化为 YYYY-MM-DD)
  - [ ] 有效性 (使用徽章显示)
  - [ ] 发证机关
  - [ ] 证件更新时间 (格式化为 YYYY-MM-DD HH:mm:ss)
  - [ ] 证件更新人
- [ ] 4.4 实现数据加载逻辑:
  - [ ] 在组件挂载时调用 API 获取数据
  - [ ] 传递 customerId 参数(从路由参数或 props 获取)
  - [ ] 实现加载状态显示(骨架屏或加载动画)
- [ ] 4.5 实现分页功能:
  - [ ] 使用 ant-design-vue Pagination 组件
  - [ ] 默认每页 10 条记录
  - [ ] 显示总记录数
  - [ ] 处理页码变化事件
- [ ] 4.6 实现空状态显示:
  - [ ] 当列表为空时显示"暂无证件信息"提示
  - [ ] 使用友好的空状态插图
- [ ] 4.7 实现错误处理:
  - [ ] API 调用失败时显示错误提示
  - [ ] 提供"重试"按钮
  - [ ] 记录错误到前端日志

## 5. Frontend - Menu Integration

- [ ] 5.1 更新 `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue` 文件
- [ ] 5.2 在左侧菜单配置中添加"客户证件信息"菜单项:
  - [ ] 设置菜单标题为"客户证件信息"
  - [ ] 设置菜单图标(使用 IdcardOutlined 或类似图标)
  - [ ] 配置菜单对应的组件为 IdentityList
- [ ] 5.3 配置路由或 Tab 切换逻辑:
  - [ ] 点击菜单时显示 IdentityList 组件
  - [ ] 更新 URL hash 或子路由(可选,如 #identity)
  - [ ] 菜单选中状态正确显示
- [ ] 5.4 确保菜单项在详情页加载时正确显示

## 6. Frontend - Responsive Layout (Optional)

- [ ] 6.1 实现桌面布局(width > 1200px):
  - [ ] 展示所有字段列
  - [ ] 列宽自适应
  - [ ] 长文本省略并支持悬停提示
- [ ] 6.2 实现平板布局(768px - 1200px):
  - [ ] 自动隐藏次要字段列
  - [ ] 提供"列设置"功能(可选)
- [ ] 6.3 实现移动布局(width < 768px):
  - [ ] 切换为卡片视图
  - [ ] 展示关键信息
  - [ ] 支持展开查看完整信息

## 7. Dictionary Data Verification

- [ ] 7.1 确认 `customer_identity_type` 字典类型存在于 `system_dict_type` 表
- [ ] 7.2 确认 `system_dict_data` 表包含所有证件类型数据项:
  - [ ] id_card (身份证)
  - [ ] passport (护照)
  - [ ] household_register (户口簿)
  - [ ] hk_macao_pass (港澳通行证)
  - [ ] taiwan_pass (台湾通行证)
  - [ ] hk_macao_residence (港澳居民居住证)
  - [ ] taiwan_residence (台湾居民居住证)
  - [ ] military_officer (军官证)
  - [ ] soldier (士兵证)
  - [ ] foreign_passport (外国护照)
  - [ ] other (其他)
- [ ] 7.3 验证字典数据在前端能够正确加载和使用

## 8. Testing

- [ ] 8.1 编写前端单元测试:
  - [ ] 测试 `maskIdNumber` 函数
  - [ ] 测试 `getValidityStatus` 函数
  - [ ] 测试 IdentityList 组件渲染
- [ ] 8.2 手动测试:
  - [ ] 测试无证件数据的场景(空状态显示)
  - [ ] 测试单个证件的场景
  - [ ] 测试多个证件的场景(分页)
  - [ ] 测试有效证件的显示
  - [ ] 测试已失效证件的显示
  - [ ] 测试长期有效证件的显示(9999-12-31)
  - [ ] 测试证件号码脱敏显示
  - [ ] 测试菜单点击和组件切换
  - [ ] 测试 API 加载状态
  - [ ] 测试 API 错误处理和重试功能
- [ ] 8.3 集成测试:
  - [ ] 测试端到端流程: 访问详情页 -> 点击菜单 -> 查看证件列表
  - [ ] 测试在不同浏览器中的兼容性(Chrome, Firefox, Safari, Edge)
- [ ] 8.4 响应式测试(可选):
  - [ ] 测试桌面浏览器显示(width > 1200px)
  - [ ] 测试平板设备显示(768px - 1200px)
  - [ ] 测试移动设备显示(width < 768px)

## 9. Documentation and Code Review

- [ ] 9.1 更新代码注释和文档
- [ ] 9.2 确保代码符合项目规范(ESLint, Prettier, TypeScript)
- [ ] 9.3 提交代码前进行自我 Code Review
- [ ] 9.4 更新 API 文档(如果后端有修改)

## 10. Deployment Preparation

- [ ] 10.1 确认所有测试通过
- [ ] 10.2 确认代码已提交到 Git 仓库
- [ ] 10.3 准备部署说明(如有特殊步骤)
- [ ] 10.4 通知相关人员(测试、产品)进行验收
