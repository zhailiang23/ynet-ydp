# Tasks: 实现零售客户偏好设置页面

**Change ID:** add-customer-preference-page
**Status:** Draft

## 任务列表

### Phase 0: 前置验证 (Pre-validation)

#### Task 0.1: 验证后端 API 可用性
**Status:** ⏳ Pending
**Description:**
- 检查后端是否存在 `CustomerPreferenceController`
- 验证 API 接口路径和返回数据结构
- 检查前端 API 封装 `#/api/aicrm/customerpreference` 是否存在
- 如后端不存在，需先实现后端 CRUD 代码

**Depends on:** None

**Acceptance Criteria:**
- [ ] 后端 Controller 文件存在或已创建
- [ ] API 接口可正常调用
- [ ] 前端 API 封装文件存在
- [ ] 了解数据结构和字段格式

---

#### Task 0.2: 检查数据库表和字典
**Status:** ⏳ Pending
**Description:**
- 检查数据库表 `crm_customer_preference` 是否存在
- 检查表结构和字段定义
- 检查系统字典管理中是否存在所需字典类型：
  - `aicrm_electronic_channel`: 电子渠道类型
  - `aicrm_investment_type`: 投资类型
  - `aicrm_brand_type`: 品牌类型
  - `aicrm_financial_service`: 理财服务
  - `aicrm_contact_method`: 联系方式
  - `aicrm_salon_activity`: 沙龙活动
  - `aicrm_interest_hobby`: 兴趣爱好
  - `aicrm_contact_time`: 联系时间
  - `aicrm_investment_period`: 投资周期

**Depends on:** None (可与 Task 0.1 并行)

**Acceptance Criteria:**
- [ ] 数据库表存在且结构已知
- [ ] 字典类型完整或已准备 SQL 脚本

---

### Phase 1: 前端组件开发 (Frontend Component Development)

#### Task 1.1: 创建 preference-info.vue 组件文件
**Status:** ⏳ Pending
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/preference-info.vue`
**Description:**
- 创建 Vue 3 SFC 文件
- 使用 TypeScript 和 Composition API
- 定义 props 接口（接收 customerId）
- 导入必要的依赖：
  - Ant Design Vue 组件（Collapse、Checkbox、Card、Empty）
  - API 函数（getCustomerPreferencePage）
  - 字典工具（getDictLabel）

**Depends on:** Task 0.1, Task 0.2

**Acceptance Criteria:**
- [x] 文件创建在正确路径
- [x] 使用 `<script lang="ts" setup>` 语法
- [x] 定义 `defineProps<{ customerId: number }>()`
- [x] 必要的 import 语句完整

---

#### Task 1.2: 实现 API 数据加载逻辑
**Status:** ⏳ Pending
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/preference-info.vue`
**Description:**
- 创建 `preferenceData` ref 存储偏好数据
- 创建 `loading` ref 控制加载状态
- 实现 `loadPreferenceData` 异步函数：
  - 调用 `getCustomerPreferencePage` API
  - 传递 `customerId`、`pageNo: 1`、`pageSize: 1` 参数
  - 处理响应数据（取 `result.list[0]`）
  - 错误处理和提示
- 在 `onMounted` 生命周期中调用加载函数

**Depends on:** Task 1.1

**Acceptance Criteria:**
- [x] API 正确导入和调用
- [x] 请求参数包含 customerId (来自 props)
- [x] 数据正确存储到 ref 变量
- [x] 错误处理完善（try-catch + message.error）
- [x] Loading 状态正确控制

---

#### Task 1.3: 实现电子渠道复选框组
**Status:** ⏳ Pending
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/preference-info.vue`
**Description:**
- 使用 `a-collapse` 折叠面板，`a-collapse-panel` 标题为"喜好的电子渠道"
- 使用 `a-checkbox-group` 展示电子渠道选项：
  - 手机银行
  - 电话银行
  - 网上银行
  - 微信银行
  - 短信服务
  - 自助银行
- 根据后端数据设置已勾选项（`v-model` 绑定）
- 添加"其他"输入框展示其他渠道文本
- 所有复选框设置为 `disabled` 只读状态

**Depends on:** Task 1.2

**Acceptance Criteria:**
- [x] 复选框组正确渲染
- [x] 已勾选项根据后端数据显示
- [x] 其他渠道文本正确显示
- [x] 复选框为只读状态
- [x] 样式与设计稿一致

---

#### Task 1.4: 实现其他偏好维度折叠面板
**Status:** ⏳ Pending
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/preference-info.vue`
**Description:**
- 使用 `a-collapse` 创建 8 个折叠面板：
  1. 喜好投资类型
  2. 喜好品牌类型
  3. 希望得到的理财服务
  4. 希望理财经理的联系方式
  5. 希望参加的沙龙活动
  6. 个人兴趣爱好
  7. 希望联系的时间
  8. 投资周期偏好
- 每个面板内容区使用字典转换显示可读文本
- 实现 `getDict` 辅助函数（使用 `getDictLabel`）
- 处理空值显示（显示 "-" 或 "暂无"）

**Depends on:** Task 1.2

**Acceptance Criteria:**
- [x] 8 个折叠面板正确渲染
- [x] 字典值正确转换为可读文本
- [x] 空值显示友好
- [x] 折叠/展开交互正常

---

#### Task 1.5: 实现空状态和错误处理
**Status:** ⏳ Pending
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/preference-info.vue`
**Description:**
- 使用 `v-if` / `v-else` 条件渲染：
  - 如果有数据，显示折叠面板
  - 如果无数据，显示 `a-empty` 组件（描述：暂无客户偏好信息）
- Loading 状态显示 Spin 加载动画
- API 错误时使用 `message.error` 提示

**Depends on:** Task 1.2, Task 1.3, Task 1.4

**Acceptance Criteria:**
- [x] 有数据时正确显示
- [x] 无数据时显示空状态
- [x] Loading 状态有加载动画
- [x] 错误提示友好

---

#### Task 1.6: 实现暴露刷新方法
**Status:** ⏳ Pending
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/preference-info.vue`
**Description:**
- 使用 `defineExpose` 暴露 refresh 方法
- refresh 方法调用 `loadPreferenceData()` 重新加载数据

**Depends on:** Task 1.2

**Acceptance Criteria:**
- [x] refresh 方法正确暴露
- [x] 可从父组件调用 refresh

---

#### Task 1.7: 添加页面样式
**Status:** ⏳ Pending
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/preference-info.vue`
**Description:**
- 添加 `<style scoped lang="less">` 区块
- 实现以下样式：
  - `.preference-info-page`: 主容器样式
  - 折叠面板样式调整（如需要）
  - 复选框组样式
  - "其他"输入框样式
- 参考 `family-info.vue` 的样式风格
- 确保样式与其他 tab 页面一致

**Depends on:** Task 1.3, Task 1.4, Task 1.5

**Acceptance Criteria:**
- [x] 样式不影响其他组件
- [x] 与其他 tab 页面视觉风格一致
- [x] 符合设计稿要求

---

### Phase 2: 集成到详情页面 (Integration into Detail Page)

#### Task 2.1: 在 detail.vue 中导入新组件
**Status:** ⏳ Pending
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue`
**Description:**
- 在 `<script>` 区块中导入 PreferenceInfo 组件
- 导入语句: `import PreferenceInfo from './pages/preference-info.vue';`

**Depends on:** Task 1.7

**Acceptance Criteria:**
- [x] 导入路径正确
- [x] 无 TypeScript 导入错误

---

#### Task 2.2: 替换 menuItems 中的 Placeholder 组件
**Status:** ⏳ Pending
**File:** `frontend/apps/web-antd/src/views/aicrm/retail-customer/detail.vue`
**Description:**
- 找到 menuItems 数组中 key 为 'preference' 的项（约第 38 行）
- 将 `component: Placeholder` 替换为 `component: PreferenceInfo`

**Depends on:** Task 2.1

**Acceptance Criteria:**
- [x] menuItems 数组正确更新
- [x] 点击"客户偏好"菜单项能显示新组件
- [x] customerId prop 正确传递给 PreferenceInfo

---

### Phase 3: 测试和验证 (Testing and Validation)

#### Task 3.1: 前端类型检查
**Status:** ⏳ Pending
**Command:** `pnpm check:type`
**Description:**
- 运行 TypeScript 类型检查
- 修复所有类型错误

**Depends on:** Task 2.2

**Acceptance Criteria:**
- [ ] 无 TypeScript 类型错误
- [ ] 所有类型定义正确

---

#### Task 3.2: 代码格式和规范检查
**Status:** ⏳ Pending
**Command:** `pnpm lint`
**Description:**
- 运行 ESLint 检查
- 运行 Prettier 格式化
- 运行 Stylelint 检查
- 修复所有代码规范问题

**Depends on:** Task 2.2

**Acceptance Criteria:**
- [ ] 无 ESLint 错误（preference-info.vue 通过检查）
- [ ] 无 Stylelint 错误
- [ ] 代码格式符合 Prettier 规范

---

#### Task 3.3: 本地开发测试
**Status:** ⏳ Pending (需要用户手动测试)
**Description:**
- 启动后端服务 (端口 48080)
- 启动前端开发服务器 (`pnpm dev:antd`)
- 访问零售客户详情页
- 点击"客户偏好"菜单项
- 验证数据正确加载和显示

**Depends on:** Task 3.2

**Acceptance Criteria:**
- [ ] 页面无控制台错误
- [ ] 数据正确加载
- [ ] 电子渠道复选框正确显示已勾选项
- [ ] 折叠面板交互正常
- [ ] 字典值正确转换显示
- [ ] 空状态正常显示

---

#### Task 3.4: 准备字典数据 SQL 脚本（如需要）
**Status:** ⏳ Pending (需要系统管理员配置)
**Description:**
- 如 Task 0.2 发现字典类型缺失，准备 SQL 脚本
- SQL 脚本内容：
  - 插入 `system_dict_type` 表（字典类型）
  - 插入 `system_dict_data` 表（字典项）
- 字典命名规范：
  - 字典名称以 "AICRM" 开头
  - 字典类型以 "aicrm_" 开头

**Depends on:** Task 0.2

**Acceptance Criteria:**
- [ ] SQL 脚本格式正确
- [ ] 字典类型和字典项完整
- [ ] 遵循命名规范

---

### Phase 4: 文档和清理 (Documentation and Cleanup)

#### Task 4.1: 更新 OpenSpec 状态
**Status:** ⏳ Pending
**Description:**
- 将 proposal.md 的 Status 从 Draft 改为 Approved
- 更新 tasks.md 中所有任务状态为 ✅ Done

**Depends on:** Task 3.3

**Acceptance Criteria:**
- [ ] proposal.md 状态更新
- [ ] tasks.md 所有实施任务标记完成

---

#### Task 4.2: 验证 OpenSpec
**Status:** ⏳ Pending
**Command:** `openspec validate add-customer-preference-page --strict`
**Description:**
- 运行 OpenSpec 验证命令
- 确保所有规范文件格式正确

**Depends on:** Task 4.1

**Acceptance Criteria:**
- [ ] OpenSpec change 已被系统识别
- [ ] Deltas 正确解析（如有）

---

## 总结

**总任务数:** 16
**完成任务:** 0
**待办任务:** 16
**跳过任务:** 0

**预计工时:** 4.5-7 小时

**关键路径:**
1. Task 0.1, 0.2 (验证) → Task 1.1 → Task 1.2 → Task 1.3, 1.4 → Task 1.5, 1.6, 1.7
2. Task 1.7 → Task 2.1 → Task 2.2
3. Task 2.2 → Task 3.1, 3.2 → Task 3.3
4. Task 3.3 → Task 4.1 → Task 4.2

**并行任务:**
- Task 0.1 和 Task 0.2 可并行执行
- Task 1.3 和 Task 1.4 可并行执行（都依赖 Task 1.2）
- Task 1.5、1.6、1.7 可并行执行（依赖前置任务完成）
- Task 3.1 和 Task 3.2 可并行执行
- Task 3.4 (字典配置) 可在任何时候执行
