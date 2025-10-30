# Tasks: 优化管理信息页面布局并添加归属网格功能

## 概述
实现管理信息页面的布局优化和归属网格功能，包括后端接口完善、前端组件开发和样式调整。

## 任务列表

### 阶段 1: 后端接口完善（预计 2 小时）

#### Task 1.1: 扩展 CustomerGridAssignmentRespVO
- **优先级**: High
- **预计时间**: 20 分钟
- **负责人**: Backend Developer
- **描述**: 为 `CustomerGridAssignmentRespVO` 添加关联查询字段
- **验收标准**:
  - [x] 添加 `gridCode` 字段（网格编号）
  - [x] 添加 `gridName` 字段（网格名称）
  - [x] 添加 `gridType` 字段（网格类型）
  - [x] 添加 `gridManagerName` 字段（网格管理员姓名）
  - [x] 添加 `assignOperatorName` 字段（分配操作人姓名）
  - [x] 添加相应的 Swagger 注解和 Excel 注解
- **文件路径**:
  - `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/controller/admin/customergridassignment/vo/CustomerGridAssignmentRespVO.java`

#### Task 1.2: 扩展 CustomerGridHistoryRespVO
- **优先级**: High
- **预计时间**: 20 分钟
- **负责人**: Backend Developer
- **描述**: 为 `CustomerGridHistoryRespVO` 添加操作人和管理员名称字段
- **验收标准**:
  - [x] 添加 `adjustOperatorName` 字段（调整操作人姓名）
  - [x] 添加 `beforeGridManagerName` 字段（调整前网格管理员姓名）
  - [x] 添加 `afterGridManagerName` 字段（调整后网格管理员姓名）
  - [x] 添加相应的 Swagger 注解和 Excel 注解
- **文件路径**:
  - `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/controller/admin/customergridhistory/vo/CustomerGridHistoryRespVO.java`

#### Task 1.3: 修改 CustomerGridAssignmentController
- **优先级**: High
- **预计时间**: 40 分钟
- **负责人**: Backend Developer
- **描述**: 在 `/page` 接口中实现联表查询，填充网格信息和操作人名称
- **验收标准**:
  - [x] 注入 `AdminUserService` 和 `GridInfoService`（如果存在）
  - [x] 在 `getCustomerGridAssignmentPage` 方法中遍历结果列表
  - [x] 根据 `gridId` 查询并填充网格信息（gridCode、gridName、gridType）
  - [x] 根据 `gridId` 关联的 `gridManagerUserId` 填充管理员姓名
  - [x] 根据 `assignOperatorId` 填充分配操作人姓名
  - [x] 处理空值情况
- **文件路径**:
  - `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/controller/admin/customergridassignment/CustomerGridAssignmentController.java`

#### Task 1.4: 修改 CustomerGridHistoryController
- **优先级**: High
- **预计时间**: 30 分钟
- **负责人**: Backend Developer
- **描述**: 在 `/page` 接口中填充操作人和网格管理员名称
- **验收标准**:
  - [x] 注入 `AdminUserService`
  - [x] 在 `getCustomerGridHistoryPage` 方法中遍历结果列表
  - [x] 根据 `adjustOperatorId` 填充调整操作人姓名
  - [x] 根据 `beforeGridManagerUserId` 填充调整前网格管理员姓名
  - [x] 根据 `afterGridManagerUserId` 填充调整后网格管理员姓名
  - [x] 处理空值情况
- **文件路径**:
  - `backend/ynet-module-crm/src/main/java/cn/iocoder/yudao/module/aicrm/controller/admin/customergridhistory/CustomerGridHistoryController.java`

#### Task 1.5: 编译测试后端代码
- **优先级**: High
- **预计时间**: 10 分钟
- **负责人**: Backend Developer
- **描述**: 编译 ynet-module-crm 模块并验证无错误
- **验收标准**:
  - [x] 执行 `mvn clean compile` 成功
  - [x] 无编译错误
  - [x] 无警告信息
- **命令**: `cd backend && mvn clean compile -pl ynet-module-crm -am`

---

### 阶段 2: 前端 API 对接（预计 1 小时）

#### Task 2.1: 创建归属网格关系 API 模块
- **优先级**: High
- **预计时间**: 30 分钟
- **负责人**: Frontend Developer
- **描述**: 创建 `customergridassignment` API 模块
- **验收标准**:
  - [x] 创建 `index.ts` 文件
  - [x] 定义 `CustomerGridAssignment` 类型接口
  - [x] 实现 `getCustomerGridAssignmentPage` 方法（GET /aicrm/customer-grid-assignment/page）
  - [x] 实现类型导出供组件使用
- **文件路径**:
  - `frontend/apps/web-antd/src/api/aicrm/customergridassignment/index.ts`

#### Task 2.2: 创建归属网格调整历史 API 模块
- **优先级**: High
- **预计时间**: 30 分钟
- **负责人**: Frontend Developer
- **描述**: 创建 `customergridhistory` API 模块
- **验收标准**:
  - [x] 创建 `index.ts` 文件
  - [x] 定义 `CustomerGridHistory` 类型接口
  - [x] 实现 `getCustomerGridHistoryPage` 方法（GET /aicrm/customer-grid-history/page）
  - [x] 实现类型导出供组件使用
- **文件路径**:
  - `frontend/apps/web-antd/src/api/aicrm/customergridhistory/index.ts`

---

### 阶段 3: 前端组件实现（预计 3 小时）

#### Task 3.1: 优化现有表格高度
- **优先级**: High
- **预计时间**: 10 分钟
- **负责人**: Frontend Developer
- **描述**: 将归属管户关系和调整历史表格高度调整为显示 5 条记录
- **验收标准**:
  - [x] 修改 `AssignmentGrid` 的 `height` 配置从 600 调整为约 260
  - [x] 修改 `HistoryGrid` 的 `height` 配置从 600 调整为约 260
  - [x] 表格能正常显示约 5 条记录（含表头和分页器）
- **文件路径**:
  - `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/management-info.vue`

#### Task 3.2: 重构为双层 Tab 结构
- **优先级**: High
- **预计时间**: 40 分钟
- **负责人**: Frontend Developer
- **描述**: 将单层 Tab 重构为双层嵌套 Tab 结构
- **验收标准**:
  - [x] 外层 Tab 包含"归属管户关系"和"归属网格关系"
  - [x] "归属管户关系" Tab 内部包含"归属管户关系列表"和"归属调整历史"两个子 Tab
  - [x] "归属网格关系" Tab 内部包含"归属网格关系列表"和"归属网格调整历史"两个子 Tab
  - [x] Tab 切换状态正确管理（外层和内层独立状态）
  - [x] 样式与现有 Tab 保持一致
- **文件路径**:
  - `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/management-info.vue`

#### Task 3.3: 实现归属网格关系列表组件
- **优先级**: High
- **预计时间**: 60 分钟
- **负责人**: Frontend Developer
- **描述**: 创建归属网格关系列表 VxeTable 组件
- **验收标准**:
  - [x] 使用 `useVbenVxeGrid` 创建 `GridAssignmentGrid`
  - [x] 配置表格列：序号、网格编号、网格名称、网格类型、网格管理员、分配日期、分配操作人、归属状态、备注
  - [x] 实现网格类型字典格式化（使用 `getDictLabel`）
  - [x] 实现归属状态格式化（0=已失效，1=生效中）
  - [x] 配置表格高度为约 260（显示 5 条记录）
  - [x] 对接 `getCustomerGridAssignmentPage` API
  - [x] 支持刷新功能
- **文件路径**:
  - `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/management-info.vue`

#### Task 3.4: 实现归属网格调整历史列表组件
- **优先级**: High
- **预计时间**: 70 分钟
- **负责人**: Frontend Developer
- **描述**: 创建归属网格调整历史 VxeTable 组件
- **验收标准**:
  - [x] 使用 `useVbenVxeGrid` 创建 `GridHistoryGrid`
  - [x] 配置表格列：序号、调整日期、调整原因、调整前网格信息（4 列）、调整后网格信息（4 列）、调整操作人、备注
  - [x] 实现网格类型字典格式化
  - [x] 配置表格高度为约 260（显示 5 条记录）
  - [x] 对接 `getCustomerGridHistoryPage` API
  - [x] 支持刷新功能
- **文件路径**:
  - `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/management-info.vue`

---

### 阶段 4: 样式优化和测试（预计 1.5 小时）

#### Task 4.1: 优化双层 Tab 样式
- **优先级**: Medium
- **预计时间**: 30 分钟
- **负责人**: Frontend Developer
- **描述**: 调整双层 Tab 的样式，确保视觉层次清晰
- **验收标准**:
  - [x] 外层 Tab 和内层 Tab 样式有明显区分
  - [x] 保持与 `family-info.vue` 双层 Tab 样式一致
  - [x] Tab 间距和边距合理
  - [x] 暗黑模式下样式正常
- **文件路径**:
  - `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/management-info.vue`

#### Task 4.2: 响应式布局测试
- **优先级**: Medium
- **预计时间**: 20 分钟
- **负责人**: Frontend Developer
- **描述**: 测试不同屏幕尺寸下的显示效果
- **验收标准**:
  - [x] 大屏（>1400px）显示正常
  - [x] 中屏（768px-1400px）显示正常
  - [x] 小屏（<768px）表格可横向滚动
  - [x] Tab 切换流畅

#### Task 4.3: 功能集成测试
- **优先级**: High
- **预计时间**: 40 分钟
- **负责人**: QA / Full Stack Developer
- **描述**: 端到端功能测试
- **验收标准**:
  - [x] 归属网格关系列表数据加载正常
  - [x] 归属网格调整历史数据加载正常
  - [x] 网格管理员名称正确显示（非 ID）
  - [x] 分配操作人名称正确显示（非 ID）
  - [x] 调整操作人名称正确显示（非 ID）
  - [x] 网格类型字典正确显示
  - [x] 归属状态格式化正确
  - [x] Tab 切换状态正确
  - [x] 分页功能正常
  - [x] 刷新功能正常
  - [x] 空数据状态显示正常

---

## 任务依赖关系

```
Task 1.1 ─┬─> Task 1.3 ──┐
Task 1.2 ─┴─> Task 1.4 ──┴─> Task 1.5 ──> Task 2.1 ──┐
                                          Task 2.2 ──┴─> Task 3.1 ──> Task 3.2 ──┬─> Task 3.3 ──┐
                                                                                  └─> Task 3.4 ──┴─> Task 4.1 ──> Task 4.2 ──> Task 4.3
```

## 里程碑

1. **M1: 后端完成** - 完成阶段 1 所有任务（预计 2 小时）
2. **M2: API 对接完成** - 完成阶段 2 所有任务（预计 +1 小时）
3. **M3: 前端功能完成** - 完成阶段 3 所有任务（预计 +3 小时）
4. **M4: 测试通过** - 完成阶段 4 所有任务（预计 +1.5 小时）

**总预计时间**: 7.5 小时

## 风险和阻塞项

### 潜在阻塞
1. **网格信息服务不存在**: 如果 `GridInfoService` 不存在，需要直接在 Controller 中通过 Mapper 查询
2. **网格类型字典缺失**: 可能需要创建 `aicrm_grid_type` 字典
3. **表格高度计算**: VxeTable 的实际行高可能需要调整才能准确显示 5 条记录

### 缓解措施
- Task 1.3 中如果 `GridInfoService` 不存在，使用 Mapper 直接查询
- 提前确认网格类型字典是否存在，不存在则通过 SQL 脚本创建
- Task 3.1 中通过浏览器开发者工具测量实际行高后精确设置 height 值

## 完成定义 (Definition of Done)

- [x] 所有代码已提交到版本控制系统
- [x] 后端编译无错误和警告
- [x] 前端 TypeScript 类型检查通过
- [x] 所有功能测试用例通过
- [x] 响应式布局测试通过
- [x] 代码审查通过
- [x] 文档更新（如需要）
