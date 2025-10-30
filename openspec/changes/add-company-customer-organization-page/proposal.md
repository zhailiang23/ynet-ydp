# Proposal: 实现对公客户组织架构信息页面

## 概述

为对公客户详情页面添加"组织架构信息"模块，展示对公客户的组织架构树形结构。支持树形表格展示、查看组织详细信息、数据刷新等功能。

## 背景

后端已完成组织架构功能的完整实现：
- 数据库表：`crm_company_organization`（支持树形结构，通过 `parent_id` 实现父子关系）
- 后端 API：`CompanyOrganizationController` 提供完整的 CRUD 接口
- 数据对象：`CompanyOrganizationDO` 包含组织的所有属性
- 测试数据：已为 10 个对公客户创建了测试组织架构数据

前端需要开发对应的展示页面，与后端 API 对接，并集成到对公客户 360 视图的左侧导航菜单中。

## 目标

1. 开发对公客户组织架构信息页面 (`organization-info.vue`)
2. 实现树形表格展示组织架构层级结构
3. 展示组织的关键信息（组织名称、类型、负责人、员工人数等）
4. 支持展开/收起子组织
5. 集成到对公客户详情页左侧导航
6. 支持数据刷新和字典值映射

## 范围

### 包含
- 前端组织架构信息页面开发
- 树形表格展示组织层级结构
- 组织信息的展示（基本信息、负责人信息、联系信息等）
- 与后端 API 对接（获取组织列表）
- 字典值映射（组织类型、组织状态等）
- 导航菜单集成
- 数据格式化（日期、布尔值等）

### 不包含
- 后端 API 开发（已完成）
- 组织架构的新增/编辑/删除功能（后续迭代）
- 组织架构图谱可视化（后续迭代）
- 组织详情弹窗（本次实现简单的表格展示）

## 设计参考

### 页面结构参考
- 列表展示：参考 `frontend/apps/web-antd/src/views/aicrm/customer/index.vue` 的 VxeTable 写法
- 信息展示：参考 `frontend/apps/web-antd/src/views/aicrm/retail-customer/pages/basic-info.vue` 的 Descriptions 写法
- 页面布局：参考 `frontend/apps/web-antd/src/views/aicrm/company-customer/pages/basic-info.vue` 的卡片布局

### 技术方案
- 使用 VxeTable 的树形表格功能展示组织层级
- 配置 `tree-config` 属性实现父子关系展示
- 主要列：组织名称、组织编码、组织类型、组织层级、负责人、员工人数、组织状态
- 支持点击展开/收起子组织
- 使用字典映射组织类型和组织状态
- 格式化日期字段（成立日期）

## 数据结构

### 组织架构 DO 主要字段
```typescript
interface CompanyOrganization {
  id: number;                    // 主键ID
  customerId: number;            // 客户ID
  parentId: number | null;       // 父级组织ID（顶级为null）
  orgCode: string;               // 组织编码
  orgName: string;               // 组织名称
  orgFullName: string;           // 组织全称
  orgLevel: number;              // 组织层级（1-一级，2-二级，3-三级...）
  orgType: string;               // 组织类型（总部、分公司、事业部、部门等）
  orgStatus: string;             // 组织状态（active-启用，inactive-停用，dissolved-解散）
  leaderName: string;            // 负责人姓名
  leaderPosition: string;        // 负责人职位
  leaderPhone: string;           // 负责人电话
  leaderEmail: string;           // 负责人邮箱
  employeeCount: number;         // 员工人数
  establishedDate: string;       // 成立日期
  contactPhone: string;          // 联系电话
  contactEmail: string;          // 联系邮箱
  contactAddress: string;        // 办公地址
  businessScope: string;         // 业务范围
  remark: string;                // 备注说明
  sortOrder: number;             // 排序号（同级组织排序）
}
```

## 技术栈

- Vue 3 Composition API
- VxeTable (树形表格)
- TypeScript
- Ant Design Vue (Card 组件)
- Vben Hooks (字典映射)

## 关键文件

### 新增文件
- `frontend/apps/web-antd/src/views/aicrm/company-customer/pages/organization-info.vue` - 组织架构信息页面

### 修改文件
- `frontend/apps/web-antd/src/views/aicrm/company-customer/detail.vue` - 添加导航菜单项并引入组件

## 依赖

- 后端 API：`/aicrm/company-organization/page` - 获取组织列表（已实现）
- 前端 API 客户端：`#/api/aicrm/company-organization` - API 调用方法（需确认是否已存在）
- 字典数据：
  - `aicrm_org_type` - 组织类型字典（需确认是否已配置）
  - `aicrm_org_status` - 组织状态字典（需确认是否已配置）

## 实现步骤

1. **准备工作**
   - 检查前端 API 客户端是否存在
   - 检查字典数据是否已配置
   - 如不存在，先创建 API 客户端和字典数据

2. **开发组织架构信息页面**
   - 创建 `organization-info.vue` 文件
   - 实现树形表格配置
   - 实现数据加载逻辑
   - 实现字典值映射
   - 实现数据格式化

3. **集成到导航菜单**
   - 修改 `detail.vue`，将菜单项 `{ key: 'organization', label: '组织架构信息' }` 的 `component` 从 `Placeholder` 改为导入的 `OrganizationInfo`

4. **测试验证**
   - 验证页面展示正常
   - 验证树形结构展开/收起功能
   - 验证字典值映射正确
   - 验证数据刷新功能

## 用户体验

### 页面布局
- 顶部：页面标题 "组织架构信息" 和操作按钮（刷新）
- 主体：树形表格展示组织层级结构
- 表格列：组织名称（树形展开）、组织编码、组织类型、组织层级、负责人、员工人数、组织状态

### 交互流程
1. 用户点击对公客户详情页左侧导航的"组织架构信息"
2. 系统加载该客户的组织架构数据
3. 以树形表格形式展示，顶级组织默认展开
4. 用户可点击展开/收起子组织
5. 用户可点击刷新按钮重新加载数据

## 时间估算

- API 客户端和字典准备：0.5 小时（如已存在则跳过）
- 组织架构信息页面开发：2 小时
- 导航集成和测试：0.5 小时
- **总计：约 3 小时**

## 风险

1. **字典数据缺失**：如果组织类型和组织状态字典未配置，需要先创建字典数据
   - 缓解：先检查字典，如不存在，通过 SQL 脚本创建

2. **前端 API 客户端缺失**：可能需要先创建 API 客户端
   - 缓解：检查是否存在，如不存在，按照现有模式创建

3. **树形数据构建**：后端返回的是平铺数据，前端需要构建树形结构
   - 缓解：VxeTable 支持通过 `parentId` 自动构建树形结构，无需手动处理

## 后续迭代

1. 组织架构的新增/编辑/删除功能
2. 组织详情弹窗展示完整信息
3. 组织架构图谱可视化（类似组织架构图）
4. 组织人员管理（与员工表关联）
