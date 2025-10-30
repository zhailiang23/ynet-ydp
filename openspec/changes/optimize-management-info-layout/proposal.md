# Proposal: 优化管理信息页面布局并添加归属网格功能

## 变更 ID
`optimize-management-info-layout`

## 状态
`proposed`

## 提议日期
2025-10-29

## 作者
Claude Code

## 概述
优化零售/对公客户管理信息页面的布局结构，将归属管户相关表格高度减少到只显示 5 条记录，并在下方添加新的归属网格相关功能 Tab，实现双层 Tab 布局结构。

## 背景与动机

### 当前问题
1. **表格高度过高**：当前归属管户关系列表和归属调整历史表格高度固定为 600px，占据过多垂直空间
2. **功能缺失**：缺少归属网格关系和归属网格调整历史的展示功能
3. **布局单一**：只有单层 Tab 结构，无法同时展示管户和网格两类归属信息

### 业务价值
1. **优化空间利用**：减少表格高度，提升页面信息密度
2. **完整功能覆盖**：增加归属网格相关功能，满足网格化管理需求
3. **结构清晰**：通过双层 Tab 结构，清晰区分管户归属和网格归属两类信息

## 技术方案

### 前端变更

#### 1. 优化现有表格高度
- 将归属管户关系列表表格高度从 600px 调整为约 260px（显示 5 条记录）
- 将归属调整历史表格高度从 600px 调整为约 260px（显示 5 条记录）

#### 2. 实现双层 Tab 布局结构
```
管理信息页面
├── 外层 Tab（归属类型）
│   ├── 归属管户关系（现有）
│   │   ├── 归属管户关系列表（高度优化）
│   │   └── 归属调整历史（高度优化）
│   └── 归属网格关系（新增）
│       ├── 归属网格关系列表（新增）
│       └── 归属网格调整历史（新增）
```

#### 3. 新增归属网格关系列表
- 创建 `GridAssignmentGrid` 组件
- 对接后端 API: `/aicrm/customer-grid-assignment/page`
- 表格字段：
  - 序号
  - 网格编号（gridCode）
  - 网格名称（gridName）
  - 网格类型（gridType）- 字典显示
  - 网格管理员（gridManagerName）- 联表查询用户名称
  - 分配日期（assignDate）
  - 分配操作人（assignOperatorName）- 联表查询用户名称
  - 归属状态（status）- 0=已失效，1=生效中
  - 备注（remark）

#### 4. 新增归属网格调整历史列表
- 创建 `GridHistoryGrid` 组件
- 对接后端 API: `/aicrm/customer-grid-history/page`
- 表格字段：
  - 序号
  - 调整日期（adjustDate）
  - 调整原因（adjustReason）
  - 调整前网格编号（beforeGridCode）
  - 调整前网格名称（beforeGridName）
  - 调整前网格类型（beforeGridType）
  - 调整前网格管理员（beforeGridManagerName）
  - 调整后网格编号（afterGridCode）
  - 调整后网格名称（afterGridName）
  - 调整后网格类型（afterGridType）
  - 调整后网格管理员（afterGridManagerName）
  - 调整操作人（adjustOperatorName）
  - 备注（remark）

### 后端变更

#### 1. 完善归属网格关系接口
- 在 `CustomerGridAssignmentController` 的 `/page` 接口中添加联表查询
- 注入 `AdminUserService` 和 `GridInfoService`
- 填充网格信息（gridCode、gridName、gridType、gridManagerName）
- 填充操作人名称（assignOperatorName）

#### 2. 完善归属网格调整历史接口
- 在 `CustomerGridHistoryController` 的 `/page` 接口中确保返回完整的网格信息快照
- 注入 `AdminUserService`
- 填充调整操作人名称（adjustOperatorName）
- 填充调整前后网格管理员名称（beforeGridManagerName、afterGridManagerName）

#### 3. 扩展 VO 字段
- `CustomerGridAssignmentRespVO` 添加字段：
  - `gridCode: String` - 网格编号
  - `gridName: String` - 网格名称
  - `gridType: String` - 网格类型
  - `gridManagerName: String` - 网格管理员姓名
  - `assignOperatorName: String` - 分配操作人姓名

- `CustomerGridHistoryRespVO` 添加字段：
  - `adjustOperatorName: String` - 调整操作人姓名
  - `beforeGridManagerName: String` - 调整前网格管理员姓名
  - `afterGridManagerName: String` - 调整后网格管理员姓名

### API 设计

#### 前端 API 文件
- 创建 `frontend/apps/web-antd/src/api/aicrm/customergridassignment/index.ts`
- 创建 `frontend/apps/web-antd/src/api/aicrm/customergridhistory/index.ts`

## 实现步骤

### 阶段 1: 后端接口完善
1. 扩展 `CustomerGridAssignmentRespVO` 添加关联字段
2. 扩展 `CustomerGridHistoryRespVO` 添加关联字段
3. 修改 `CustomerGridAssignmentController` 实现联表查询
4. 修改 `CustomerGridHistoryController` 填充用户名称

### 阶段 2: 前端 API 对接
1. 创建归属网格关系 API 模块
2. 创建归属网格调整历史 API 模块

### 阶段 3: 前端组件实现
1. 优化现有表格高度（修改 height 配置）
2. 重构 Tab 结构为双层嵌套
3. 实现归属网格关系列表组件
4. 实现归属网格调整历史列表组件

### 阶段 4: 样式优化
1. 调整双层 Tab 的样式保持一致性
2. 确保表格高度自适应显示 5 条记录
3. 优化移动端响应式布局

## 测试计划

### 单元测试
- 后端 Controller 联表查询逻辑测试
- 前端 API 调用测试
- 格式化函数测试

### 集成测试
- 归属网格关系列表数据加载测试
- 归属网格调整历史数据加载测试
- Tab 切换状态测试
- 分页功能测试

### UI 测试
- 表格高度显示测试（确认显示 5 条记录）
- 双层 Tab 布局测试
- 响应式布局测试
- 数据为空时的空状态测试

## 潜在风险

### 技术风险
1. **性能风险**：多次联表查询可能影响性能
   - 缓解措施：评估查询性能，必要时添加缓存
2. **布局兼容性**：双层 Tab 在不同屏幕尺寸下的显示效果
   - 缓解措施：充分测试响应式布局

### 业务风险
1. **数据一致性**：网格信息可能需要同步更新
   - 缓解措施：确保网格信息变更时正确处理历史记录

## 依赖关系
- 依赖现有的 `CustomerGridAssignmentController` 和 `CustomerGridHistoryController`
- 依赖系统用户服务（`AdminUserService`）
- 可能依赖网格信息服务（`GridInfoService`）

## 向后兼容性
- ✅ 向后兼容：仅扩展 VO 字段和优化布局，不影响现有功能

## 替代方案
1. **方案 1**：使用弹窗展示网格信息（被否决：用户体验较差）
2. **方案 2**：使用独立页面展示网格信息（被否决：页面跳转影响操作连贯性）

## 未解决的问题
- [ ] 网格信息服务（`GridInfoService`）是否已实现？
- [ ] 是否需要为网格类型创建新的字典？
- [ ] 表格的具体高度值需要在实现时根据行高精确计算

## 参考资料
- 现有 `management-info.vue` 组件实现
- 现有 `family-info.vue` 双层 Tab 实现参考
- VxeTable 配置文档
