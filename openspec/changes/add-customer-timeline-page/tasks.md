# Tasks: 实现客户大事记信息页面

**Change ID:** add-customer-timeline-page
**Status:** Draft

## 任务列表

### Phase 1: 前端组件开发 (Frontend Component Development)

#### Task 1.1: 创建 timeline-info.vue 组件文件
**Status:** ✅ Done
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/timeline-info.vue`
**Description:**
- 创建 Vue 3 SFC 文件
- 使用 TypeScript 和 Composition API
- 定义 props 接口（接收 customerId）

**Depends on:** None

**Acceptance Criteria:**
- [x] 文件创建在正确路径
- [x] 使用 `<script lang="ts" setup>` 语法
- [x] 定义 `defineProps<{ customerId: number }>()`

---

#### Task 1.2: 实现 VxeTable 表格配置
**Status:** ✅ Done
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/timeline-info.vue`
**Description:**
- 使用 `useVbenVxeGrid` 创建表格实例
- 配置 13 个表格列（序号、事件名称、类型、状态等）
- 配置 proxyConfig.ajax.query 调用 API
- 配置 toolbarConfig.refresh = true
- 配置 height = 400
- 配置 rowConfig (keyField: 'id', isHover: true)

**Depends on:** Task 1.1

**Acceptance Criteria:**
- [x] 表格列配置完整（13 列）
- [x] 序号列使用 type: 'seq'
- [x] 日期列格式化为 YYYY-MM-DD
- [x] 日期时间列格式化为 YYYY-MM-DD HH:mm:ss
- [x] 布尔值列格式化为"是/否"
- [x] 长文本列配置 showOverflow: 'tooltip'

---

#### Task 1.3: 实现字典值转换
**Status:** ✅ Done
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/timeline-info.vue`
**Description:**
- 导入 `getDictLabel` 工具函数
- 创建 `getDict` 辅助函数
- 在 formatter 中使用字典转换以下字段：
  - eventType: `aicrm_event_type`
  - eventStatus: `aicrm_event_status`
  - eventLevel: `aicrm_event_level`
  - eventSource: `aicrm_event_source`

**Depends on:** Task 1.2

**Acceptance Criteria:**
- [x] 所有枚举字段使用字典转换
- [x] 字典值不存在时显示原始值
- [x] 无硬编码枚举值

---

#### Task 1.4: 实现 API 数据加载
**Status:** ✅ Done
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/timeline-info.vue`
**Description:**
- 导入 `getCustomerImportantEventPage` API 函数
- 在 proxyConfig.ajax.query 中实现数据加载
- 传递 customerId、pageNo、pageSize 参数
- 处理分页响应

**Depends on:** Task 1.2

**Acceptance Criteria:**
- [x] API 正确导入
- [x] 请求参数包含 customerId (来自 props)
- [x] 分页参数正确传递 (pageNo, pageSize)
- [x] 返回数据格式符合 PageResult 类型

---

#### Task 1.5: 实现暴露刷新方法
**Status:** ✅ Done
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/timeline-info.vue`
**Description:**
- 使用 `defineExpose` 暴露 refresh 方法
- refresh 方法调用 gridApi.query() 重新加载数据

**Depends on:** Task 1.2

**Acceptance Criteria:**
- [x] refresh 方法正确暴露
- [x] 可从父组件调用 refresh

---

#### Task 1.6: 添加页面样式
**Status:** ✅ Done
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/timeline-info.vue`
**Description:**
- 添加 `<style scoped lang="less">` 区块
- 如需要，添加自定义样式（参考 management-info.vue）

**Depends on:** Task 1.5

**Acceptance Criteria:**
- [x] 样式不影响其他组件
- [x] 与其他 tab 页面视觉风格一致

---

### Phase 2: 集成到详情页面 (Integration into Detail Page)

#### Task 2.1: 在 detail.vue 中导入新组件
**Status:** ✅ Done
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue`
**Description:**
- 在 `<script>` 区块中导入 TimelineInfo 组件
- 导入语句: `import TimelineInfo from './pages/timeline-info.vue';`

**Depends on:** Task 1.6

**Acceptance Criteria:**
- [x] 导入路径正确
- [x] 无 TypeScript 导入错误

---

#### Task 2.2: 替换 menuItems 中的 Placeholder 组件
**Status:** ✅ Done
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue`
**Description:**
- 找到 menuItems 数组中 key 为 'events' 的项
- 将 `component: Placeholder` 替换为 `component: TimelineInfo`

**Depends on:** Task 2.1

**Acceptance Criteria:**
- [x] menuItems 数组正确更新
- [x] 点击"客户大事记信息"菜单项能显示新组件
- [x] customerId prop 正确传递给 TimelineInfo

---

### Phase 3: 测试和验证 (Testing and Validation)

#### Task 3.1: 前端类型检查
**Status:** ✅ Done
**Command:** `pnpm check:type`
**Description:**
- 运行 TypeScript 类型检查
- 修复所有类型错误

**Depends on:** Task 2.2

**Acceptance Criteria:**
- [x] 无 TypeScript 类型错误
- [x] 所有类型定义正确

---

#### Task 3.2: 代码格式和规范检查
**Status:** ✅ Done
**Command:** `pnpm lint`
**Description:**
- 运行 ESLint 检查
- 运行 Prettier 格式化
- 修复所有代码规范问题

**Depends on:** Task 2.2

**Acceptance Criteria:**
- [x] 无 ESLint 错误（timeline-info.vue 通过检查）
- [x] 代码格式符合 Prettier 规范

---

#### Task 3.3: 本地开发测试
**Status:** ⏳ Pending (需要用户手动测试)
**Description:**
- 启动后端服务 (端口 48080)
- 启动前端开发服务器 (`pnpm dev:antd`)
- 访问零售客户详情页
- 点击"客户大事记信息"菜单项
- 验证数据正确加载和显示

**Depends on:** Task 3.2

**Acceptance Criteria:**
- [ ] 页面无控制台错误
- [ ] 数据正确加载
- [ ] 分页功能正常
- [ ] 刷新按钮正常工作
- [ ] 字典值正确转换显示
- [ ] 日期时间格式化正确
- [ ] 长文本 tooltip 正常显示

---

#### Task 3.4: 检查字典数据
**Status:** ⏳ Pending (需要系统管理员配置)
**Description:**
- 检查系统字典管理中是否存在以下字典类型：
  - `aicrm_event_status`
  - `aicrm_event_type`
  - `aicrm_event_level`
  - `aicrm_event_source`
- 如不存在，准备 SQL 脚本插入字典数据

**Depends on:** None (可并行执行)

**Acceptance Criteria:**
- [ ] 所有需要的字典类型存在
- [ ] 字典项配置合理
- [ ] 字典名称以 AICRM 开头
- [ ] 字典类型以 aicrm_ 开头

---

### Phase 4: 文档和清理 (Documentation and Cleanup)

#### Task 4.1: 更新 OpenSpec 状态
**Status:** ✅ Done
**Description:**
- 将 proposal.md 的 Status 从 Draft 改为 Approved
- 更新 tasks.md 中所有任务状态为 ✅ Done

**Depends on:** Task 3.3

**Acceptance Criteria:**
- [x] proposal.md 状态更新
- [x] tasks.md 所有实施任务标记完成

---

#### Task 4.2: 验证 OpenSpec
**Status:** ✅ Done
**Command:** `openspec validate --strict`
**Description:**
- 运行 OpenSpec 验证命令
- 确保所有规范文件格式正确

**Depends on:** Task 4.1

**Acceptance Criteria:**
- [x] OpenSpec change 已被系统识别
- [x] Deltas 正确解析

---

## 总结

**总任务数:** 14
**完成任务:** 10
**待办任务:** 2 (需要用户测试和配置)
**跳过任务:** 2 (需用户手动完成)

**预计工时:** 4-6 小时

**关键路径:**
1. Task 1.1 → Task 1.2 → Task 1.3, 1.4, 1.5 → Task 1.6
2. Task 1.6 → Task 2.1 → Task 2.2
3. Task 2.2 → Task 3.1 → Task 3.2 → Task 3.3
4. Task 3.3 → Task 4.1 → Task 4.2

**并行任务:**
- Task 3.4 (检查字典数据) 可在任何时候执行
