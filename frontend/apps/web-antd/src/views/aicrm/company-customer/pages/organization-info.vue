<script lang="ts" setup>
import type { AicrmCompanyOrganizationApi } from '#/api/aicrm/companyorganization';
import type { VxeTableInstance } from 'vxe-table';

import { computed, onMounted, ref } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { ReloadOutlined } from '@ant-design/icons-vue';
import { message } from 'ant-design-vue';
import { VxeTable, VxeColumn } from 'vxe-table';

import { getCompanyOrganizationPage } from '#/api/aicrm/companyorganization';

const props = defineProps<{
  customer: any;
  title?: string;
}>();

// 数据加载状态
const loading = ref(false);
// 组织架构数据
const organizations = ref<any[]>([]);
// 表格实例
const tableRef = ref<VxeTableInstance>();

// 树形配置
const treeConfig = {
  transform: true,
  rowField: 'id',
  parentField: 'parentId',
  expandAll: true,
  reserve: true,
};

// 加载组织架构数据
async function loadOrganizations() {
  if (!props.customer?.customerId) {
    message.warning('客户ID不存在');
    return;
  }

  loading.value = true;
  try {
    const result = await getCompanyOrganizationPage({
      customerId: props.customer.customerId,
      pageNo: 1,
      pageSize: 1000,
    });

    if (result.list && result.list.length > 0) {
      organizations.value = result.list;
    } else {
      organizations.value = [];
      message.info('暂无组织架构数据');
    }
  } catch (error: any) {
    message.error(error.message || '加载组织架构数据失败');
    organizations.value = [];
  } finally {
    loading.value = false;
  }
}

// 格式化组织类型
function formatOrgType({ cellValue }: any) {
  if (!cellValue) return '-';
  return getDictLabel('aicrm_org_type', cellValue) || cellValue;
}

// 格式化组织状态
function formatOrgStatus({ cellValue }: any) {
  if (!cellValue) return '-';
  const label = getDictLabel('aicrm_org_status', cellValue) || cellValue;

  // 根据状态值返回不同的样式类
  if (cellValue === 'active') {
    return `<span style="color: #52c41a;">● ${label}</span>`;
  } else if (cellValue === 'inactive') {
    return `<span style="color: #faad14;">● ${label}</span>`;
  } else if (cellValue === 'dissolved') {
    return `<span style="color: #ff4d4f;">● ${label}</span>`;
  }
  return label;
}

// 格式化日期
function formatDate({ cellValue }: any) {
  if (!cellValue) return '-';
  try {
    return new Date(cellValue).toLocaleDateString('zh-CN');
  } catch {
    return cellValue;
  }
}

// 格式化员工人数
function formatEmployeeCount({ cellValue }: any) {
  if (cellValue === null || cellValue === undefined) return '-';
  return cellValue.toLocaleString('zh-CN');
}

// 格式化其他字段（处理空值）
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 页面标题
const pageTitle = computed(() => props.title || '组织架构信息');

// 组件挂载时加载数据
onMounted(() => {
  loadOrganizations();
});
</script>

<template>
  <div class="organization-info-container">
    <a-card :title="pageTitle" :bordered="false">
      <template #extra>
        <a-space>
          <a-button type="primary" :loading="loading" @click="loadOrganizations">
            <template #icon>
              <ReloadOutlined />
            </template>
            刷新
          </a-button>
        </a-space>
      </template>

      <vxe-table
        ref="tableRef"
        :data="organizations"
        :loading="loading"
        :tree-config="treeConfig"
        :row-config="{ isHover: true }"
        border
        stripe
        show-overflow
        height="auto"
        max-height="calc(100vh - 300px)"
      >
        <vxe-column
          field="orgName"
          title="组织名称"
          tree-node
          min-width="220"
          :formatter="formatField"
        />
        <vxe-column
          field="orgCode"
          title="组织编码"
          width="120"
          :formatter="formatField"
        />
        <vxe-column
          field="orgType"
          title="组织类型"
          width="120"
          :formatter="formatOrgType"
        />
        <vxe-column
          field="orgLevel"
          title="组织层级"
          width="100"
          align="center"
          :formatter="formatField"
        />
        <vxe-column
          field="leaderName"
          title="负责人姓名"
          width="120"
          :formatter="formatField"
        />
        <vxe-column
          field="leaderPosition"
          title="负责人职位"
          width="120"
          :formatter="formatField"
        />
        <vxe-column
          field="employeeCount"
          title="员工人数"
          width="110"
          align="right"
          :formatter="formatEmployeeCount"
        />
        <vxe-column
          field="orgStatus"
          title="组织状态"
          width="110"
          align="center"
          :formatter="formatOrgStatus"
        />
        <vxe-column
          field="establishedDate"
          title="成立日期"
          width="120"
          :formatter="formatDate"
        />
        <vxe-column
          field="contactPhone"
          title="联系电话"
          width="140"
          :formatter="formatField"
        />
        <vxe-column
          field="contactAddress"
          title="办公地址"
          min-width="200"
          show-overflow
          :formatter="formatField"
        />
      </vxe-table>

      <!-- 空数据提示 -->
      <a-empty
        v-if="!loading && organizations.length === 0"
        description="暂无组织架构数据"
        style="margin-top: 40px"
      />
    </a-card>
  </div>
</template>

<style scoped>
.organization-info-container {
  height: 100%;
}

.organization-info-container :deep(.ant-card-body) {
  padding: 16px;
}

/* VxeTable 样式调整 */
.organization-info-container :deep(.vxe-table) {
  font-size: 13px;
}

.organization-info-container :deep(.vxe-body--row.row--hover) {
  background-color: #f5f5f5;
}

.organization-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: #fafafa;
}

/* 深色模式支持 */
.dark .organization-info-container :deep(.vxe-body--row.row--hover) {
  background-color: rgb(30 32 36);
}

.dark .organization-info-container :deep(.vxe-body--row.row--stripe) {
  background-color: rgb(25 27 31);
}

/* 树形节点图标样式 */
.organization-info-container :deep(.vxe-tree--node-btn) {
  color: #1890ff;
}

.dark .organization-info-container :deep(.vxe-tree--node-btn) {
  color: #4096ff;
}
</style>
