<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyOrganizationApi } from '#/api/aicrm/companyorganization';

import { computed } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCompanyOrganizationPage } from '#/api/aicrm/companyorganization';

defineOptions({
  name: 'CompanyCustomerOrganizationInfo',
});

const props = defineProps<{
  customer: any;
  customerId?: number;
  title?: string;
}>();

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
    return `${label}`;
  } else if (cellValue === 'inactive') {
    return `${label}`;
  } else if (cellValue === 'dissolved') {
    return `${label}`;
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

// 格式化其他字段(处理空值)
function formatField({ cellValue }: any) {
  return cellValue || '-';
}

// 页面标题
const pageTitle = computed(() => props.title || '组织架构信息');

const [Grid, gridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        field: 'orgName',
        title: '组织名称',
        treeNode: true,
        minWidth: 220,
        formatter: formatField,
      },
      {
        field: 'orgCode',
        title: '组织编码',
        width: 120,
        formatter: formatField,
      },
      {
        field: 'orgType',
        title: '组织类型',
        width: 120,
        formatter: formatOrgType,
      },
      {
        field: 'orgLevel',
        title: '组织层级',
        width: 100,
        align: 'center',
        formatter: formatField,
      },
      {
        field: 'leaderName',
        title: '负责人姓名',
        width: 120,
        formatter: formatField,
      },
      {
        field: 'leaderPosition',
        title: '负责人职位',
        width: 120,
        formatter: formatField,
      },
      {
        field: 'employeeCount',
        title: '员工人数',
        width: 110,
        align: 'right',
        formatter: formatEmployeeCount,
      },
      {
        field: 'orgStatus',
        title: '组织状态',
        width: 110,
        align: 'center',
        formatter: formatOrgStatus,
      },
      {
        field: 'establishedDate',
        title: '成立日期',
        width: 120,
        formatter: formatDate,
      },
      {
        field: 'contactPhone',
        title: '联系电话',
        width: 140,
        formatter: formatField,
      },
      {
        field: 'contactAddress',
        title: '办公地址',
        minWidth: 200,
        showOverflow: 'tooltip',
        formatter: formatField,
      },
    ],
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCompanyOrganizationPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          });
        },
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    treeConfig: {
      transform: true,
      rowField: 'id',
      parentField: 'parentId',
      expandAll: true,
      reserve: true,
    },
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<any>,
});

// 暴露方法供父组件调用
defineExpose({
  refresh: () => {
    gridApi.query();
  },
});
</script>

<template>
  <Grid :table-title="pageTitle" />
</template>

<style scoped>
/* 树形节点图标样式 */
:deep(.vxe-tree--node-btn) {
  color: #1890ff;
}
</style>
