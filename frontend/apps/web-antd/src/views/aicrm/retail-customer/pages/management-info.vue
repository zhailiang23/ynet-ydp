<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';
import type { AicrmCustomerAssignmentHistoryApi } from '#/api/aicrm/customerassignmenthistory';

import { ref } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerAssignmentPage } from '#/api/aicrm/customerassignment';
import { getCustomerAssignmentHistoryPage } from '#/api/aicrm/customerassignmenthistory';

defineOptions({
  name: 'RetailCustomerManagementInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// Tab 相关
const activeTab = ref('assignment');

// 格式化布尔值
function formatBoolean(value?: boolean) {
  if (value === null || value === undefined) return '-';
  return value ? '是' : '否';
}

// 获取字典标签
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  const label = getDictLabel(dictType, value);
  return label || value;
}

// 格式化归属类型
function formatAssignmentType(value?: number) {
  if (value === 1) return '主办';
  if (value === 2) return '协办';
  return '-';
}

// 格式化归属状态
function formatStatus(value?: number) {
  if (value === 0) return '已失效';
  if (value === 1) return '生效中';
  if (value === 2) return '待生效';
  return '-';
}

// 归属关系列表 VxeTable 配置
const [AssignmentGrid, assignmentGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 70,
        fixed: 'left',
      },
      {
        field: 'assignmentType',
        title: '归属类型',
        minWidth: 100,
        formatter: ({ cellValue }) => formatAssignmentType(cellValue),
      },
      {
        field: 'deptName',
        title: '归属部门',
        minWidth: 150,
      },
      {
        field: 'userName',
        title: '客户经理',
        minWidth: 120,
      },
      {
        field: 'hasViewRight',
        title: '查看权限',
        minWidth: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'hasMaintainRight',
        title: '维护权限',
        minWidth: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'assignDate',
        title: '分配日期',
        minWidth: 120,
      },
      {
        field: 'effectiveDate',
        title: '生效日期',
        minWidth: 120,
      },
      {
        field: 'expiryDate',
        title: '失效日期',
        minWidth: 120,
        formatter: ({ cellValue }) => cellValue || '长期有效',
      },
      {
        field: 'status',
        title: '归属状态',
        minWidth: 100,
        formatter: ({ cellValue }) => formatStatus(cellValue),
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 200,
        showOverflow: 'tooltip',
      },
    ],
    height: 600,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerAssignmentPage({
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
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerAssignmentApi.CustomerAssignment>,
});

// 调整历史列表 VxeTable 配置
const [HistoryGrid, historyGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 70,
        fixed: 'left',
      },
      {
        field: 'transferDate',
        title: '调整日期',
        minWidth: 120,
        fixed: 'left',
      },
      {
        field: 'transferLevel',
        title: '调整级别',
        minWidth: 120,
        formatter: ({ cellValue }) => getDict('aicrm_transfer_level', cellValue),
      },
      {
        field: 'transferReason',
        title: '调整原因',
        minWidth: 200,
        showOverflow: 'tooltip',
      },
      {
        field: 'beforeAssignmentType',
        title: '调整前归属类型',
        minWidth: 130,
        formatter: ({ cellValue }) => formatAssignmentType(cellValue),
      },
      {
        field: 'beforeDeptName',
        title: '调整前部门',
        minWidth: 150,
      },
      {
        field: 'beforeUserName',
        title: '调整前客户经理',
        minWidth: 130,
      },
      {
        field: 'afterAssignmentType',
        title: '调整后归属类型',
        minWidth: 130,
        formatter: ({ cellValue }) => formatAssignmentType(cellValue),
      },
      {
        field: 'afterDeptName',
        title: '调整后部门',
        minWidth: 150,
      },
      {
        field: 'afterUserName',
        title: '调整后客户经理',
        minWidth: 130,
      },
      {
        field: 'assignDate',
        title: '分配日期',
        minWidth: 120,
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 200,
        showOverflow: 'tooltip',
      },
    ],
    height: 600,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerAssignmentHistoryPage({
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
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerAssignmentHistoryApi.CustomerAssignmentHistory>,
});

// 暴露刷新方法
defineExpose({
  refresh: () => {
    if (activeTab.value === 'assignment') {
      assignmentGridApi.query();
    } else {
      historyGridApi.query();
    }
  },
});
</script>

<template>
  <div class="management-info-page">
    <a-tabs v-model:activeKey="activeTab" type="card">
      <!-- 归属管户关系列表 Tab -->
      <a-tab-pane key="assignment" tab="归属管户关系">
        <AssignmentGrid table-title="归属管户关系列表" />
      </a-tab-pane>

      <!-- 归属调整历史 Tab -->
      <a-tab-pane key="history" tab="归属调整历史">
        <HistoryGrid table-title="归属调整历史" />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<style scoped lang="less">
.management-info-page {
  // Tab 样式，与 family-info 保持一致
  :deep(.ant-tabs) {
    .ant-tabs-nav {
      margin-bottom: 0;

      &::before {
        border-bottom: none;
      }
    }

    .ant-tabs-content-holder {
      border: none;
    }

    .ant-tabs-tab {
      border: none !important;
      background: transparent !important;

      &.ant-tabs-tab-active {
        background: #fff !important;
      }
    }
  }
}
</style>
