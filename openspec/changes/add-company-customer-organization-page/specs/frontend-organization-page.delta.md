# Delta Spec: 对公客户组织架构信息页面

## 变更摘要

为对公客户360视图添加组织架构信息页面，展示客户的组织架构树形结构。

## ADDED Requirements

### REQ-ORG-PAGE-001: 组织架构页面展示

**描述**: 系统 SHALL 提供对公客户组织架构信息页面，以树形表格形式展示客户的组织架构层级结构。

**约束**:
- MUST 使用 VxeTable 树形表格组件
- MUST 通过 `parentId` 字段构建父子关系
- MUST 支持展开/收起子组织
- MUST 在页面顶部显示标题和刷新按钮

**验收标准**:
- 页面能正确加载并显示组织架构数据
- 树形结构正确展示父子层级关系
- 顶级组织默认展开
- 刷新按钮能重新加载数据

#### Scenario: 查看对公客户组织架构

**Given** 用户打开对公客户详情页
**And** 后端存在该客户的组织架构数据
**When** 用户点击左侧导航的"组织架构信息"
**Then** 系统加载并展示该客户的组织架构树形结构
**And** 顶级组织默认展开
**And** 显示组织名称、编码、类型、负责人等关键信息

---

### REQ-ORG-PAGE-002: 表格列配置

**描述**: 系统 SHALL 在组织架构表格中展示以下列：组织名称、组织编码、组织类型、组织层级、负责人姓名、负责人职位、员工人数、组织状态。

**约束**:
- MUST 组织名称列为树形列（`tree-node: true`）
- MUST 组织类型和组织状态使用字典映射
- MUST 组织层级显示为数字（1、2、3...）
- MUST 员工人数右对齐显示

**验收标准**:
- 所有列正确显示对应的数据
- 树形列支持展开/收起
- 字典值正确映射为中文标签
- 数值列右对齐

#### Scenario: 查看组织详细信息

**Given** 用户已打开组织架构信息页面
**When** 查看表格中的组织记录
**Then** 系统应显示组织名称（树形展开）
**And** 显示组织编码（如 "HQ"、"RD"）
**And** 显示组织类型（如 "总部"、"部门"）
**And** 显示组织层级（如 1、2、3）
**And** 显示负责人姓名和职位
**And** 显示员工人数并右对齐
**And** 显示组织状态（如 "启用"、"停用"）

---

### REQ-ORG-PAGE-003: 树形结构交互

**描述**: 系统 SHALL 支持用户展开和收起组织的子组织。

**约束**:
- MUST 点击组织名称前的展开/收起图标切换子组织显示
- MUST 保持其他组织的展开/收起状态不变
- MUST 无子组织的节点不显示展开/收起图标

**验收标准**:
- 点击展开图标能展开子组织
- 点击收起图标能隐藏子组织
- 展开/收起操作流畅无延迟
- 叶子节点不显示展开图标

#### Scenario: 展开和收起组织

**Given** 用户已打开组织架构信息页面
**And** 存在多层级组织结构
**When** 用户点击总部组织的展开图标
**Then** 系统展开并显示总部的所有子组织（如研发中心、市场部等）
**When** 用户点击收起图标
**Then** 系统隐藏总部的子组织

---

### REQ-ORG-PAGE-004: 数据加载和刷新

**描述**: 系统 SHALL 支持加载和刷新组织架构数据。

**约束**:
- MUST 在页面挂载时自动加载数据
- MUST 点击刷新按钮重新加载数据
- MUST 加载过程中显示 loading 状态
- MUST 加载失败时显示错误提示

**验收标准**:
- 页面打开时自动加载数据
- 刷新按钮能重新加载数据
- 加载过程中显示加载中状态
- 加载失败显示友好的错误提示

#### Scenario: 刷新组织架构数据

**Given** 用户已打开组织架构信息页面
**And** 页面已显示组织架构数据
**When** 用户点击刷新按钮
**Then** 系统重新调用后端 API 加载数据
**And** 显示加载中状态
**And** 加载完成后更新表格显示
**And** 保持之前的展开/收起状态

---

### REQ-ORG-PAGE-005: 字典值映射

**描述**: 系统 SHALL 使用字典映射组织类型和组织状态的值。

**约束**:
- MUST 组织类型使用 `aicrm_org_type` 字典
- MUST 组织状态使用 `aicrm_org_status` 字典
- MUST 如果字典不存在，显示原始值

**验收标准**:
- 组织类型正确映射为中文标签
- 组织状态正确映射为中文标签
- 字典值不存在时显示原始值

#### Scenario: 显示字典映射值

**Given** 系统已配置组织类型和组织状态字典
**And** 用户已打开组织架构信息页面
**When** 查看组织类型列
**Then** 系统应显示 "总部"、"部门"、"车间" 等中文标签
**When** 查看组织状态列
**Then** 系统应显示 "启用"、"停用"、"解散" 等中文标签

---

### REQ-ORG-PAGE-006: 数据格式化

**描述**: 系统 SHALL 格式化组织架构数据的显示。

**约束**:
- MUST 空值显示为 "-"
- MUST 日期字段格式化为 YYYY-MM-DD
- MUST 数值字段使用千分位分隔符（如适用）

**验收标准**:
- 空值字段显示为 "-"
- 日期格式统一且易读
- 数值格式清晰

#### Scenario: 查看格式化的数据

**Given** 用户已打开组织架构信息页面
**When** 查看组织记录
**Then** 空值字段应显示为 "-"
**And** 成立日期应显示为 "2015-06-15" 格式
**And** 员工人数应清晰显示（如 "150"）

---

### REQ-ORG-PAGE-007: 导航菜单集成

**描述**: 系统 SHALL 将组织架构信息页面集成到对公客户详情页的左侧导航菜单中。

**约束**:
- MUST 导航菜单项命名为 "组织架构信息"
- MUST 点击菜单项切换到组织架构页面
- MUST 激活状态下高亮显示菜单项

**验收标准**:
- 左侧导航显示 "组织架构信息" 菜单项
- 点击菜单项正确切换页面
- 当前页面的菜单项高亮显示

#### Scenario: 通过导航菜单打开组织架构信息

**Given** 用户在对公客户详情页
**When** 用户点击左侧导航的 "组织架构信息" 菜单项
**Then** 系统切换到组织架构信息页面
**And** "组织架构信息" 菜单项高亮显示
**And** 页面加载并显示该客户的组织架构数据

---

### REQ-ORG-PAGE-008: 响应式布局

**描述**: 系统 SHALL 支持响应式布局，适配不同屏幕尺寸。

**约束**:
- MUST 在小屏幕下自动调整表格列宽
- MUST 保持表格可读性
- MUST 支持深色模式

**验收标准**:
- 在不同屏幕尺寸下表格正常显示
- 深色模式下样式正确
- 表格列宽自适应

#### Scenario: 在不同屏幕下查看组织架构

**Given** 用户已打开组织架构信息页面
**When** 调整浏览器窗口大小
**Then** 系统自动调整表格布局
**And** 保持内容可读性
**When** 切换到深色模式
**Then** 系统应用深色主题样式

---

## MODIFIED Requirements

无

## REMOVED Requirements

无

## RENAMED Requirements

无

## 技术实现

### 组件结构
```vue
<template>
  <a-card title="组织架构信息" :bordered="false">
    <template #extra>
      <a-button @click="loadOrganizations">刷新</a-button>
    </template>

    <vxe-table
      :data="organizations"
      :loading="loading"
      :tree-config="{ children: 'children', expandAll: true }"
    >
      <vxe-column field="orgName" title="组织名称" tree-node />
      <vxe-column field="orgCode" title="组织编码" />
      <vxe-column field="orgType" title="组织类型" :formatter="formatOrgType" />
      <vxe-column field="orgLevel" title="组织层级" />
      <vxe-column field="leaderName" title="负责人姓名" />
      <vxe-column field="leaderPosition" title="负责人职位" />
      <vxe-column field="employeeCount" title="员工人数" align="right" />
      <vxe-column field="orgStatus" title="组织状态" :formatter="formatOrgStatus" />
    </vxe-table>
  </a-card>
</template>
```

### API 调用
```typescript
// 加载组织架构数据
const loadOrganizations = async () => {
  loading.value = true;
  try {
    const result = await getCompanyOrganizationPage({
      customerId: props.customer.customerId,
      pageNo: 1,
      pageSize: 1000
    });
    organizations.value = buildTree(result.list);
  } catch (error) {
    message.error('加载组织架构数据失败');
  } finally {
    loading.value = false;
  }
};
```

### 树形数据构建
```typescript
// 构建树形结构
const buildTree = (list: any[]) => {
  const map = new Map();
  const tree: any[] = [];

  // 创建节点映射
  list.forEach(item => {
    map.set(item.id, { ...item, children: [] });
  });

  // 构建树形结构
  list.forEach(item => {
    const node = map.get(item.id);
    if (item.parentId === null) {
      tree.push(node);
    } else {
      const parent = map.get(item.parentId);
      if (parent) {
        parent.children.push(node);
      }
    }
  });

  return tree;
};
```

### 字典映射
```typescript
import { getDictLabel } from '@vben/hooks';

// 格式化组织类型
const formatOrgType = ({ cellValue }: any) => {
  return getDictLabel('aicrm_org_type', cellValue) || cellValue;
};

// 格式化组织状态
const formatOrgStatus = ({ cellValue }: any) => {
  return getDictLabel('aicrm_org_status', cellValue) || cellValue;
};
```

## 影响范围

### 新增文件
- `frontend/apps/web-antd/src/views/aicrm/company-customer/pages/organization-info.vue`

### 修改文件
- `frontend/apps/web-antd/src/views/aicrm/company-customer/detail.vue`

### API 依赖
- `GET /aicrm/company-organization/page` - 获取组织列表

### 字典依赖
- `aicrm_org_type` - 组织类型字典
- `aicrm_org_status` - 组织状态字典
