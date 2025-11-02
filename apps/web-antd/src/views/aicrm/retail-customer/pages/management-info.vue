<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';
import type { AicrmCustomerAssignmentHistoryApi } from '#/api/aicrm/customerassignmenthistory';
import type { AicrmCustomerGridAssignmentApi } from '#/api/aicrm/customergridassignment';
import type { AicrmCustomerGridHistoryApi } from '#/api/aicrm/customergridhistory';
import type { AicrmCustomerGroupAssignmentApi } from '#/api/aicrm/customergroupassignment';

import { ref } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerAssignmentPage } from '#/api/aicrm/customerassignment';
import { getCustomerAssignmentHistoryPage } from '#/api/aicrm/customerassignmenthistory';
import { getCustomerGridAssignmentPage } from '#/api/aicrm/customergridassignment';
import { getCustomerGridHistoryPage } from '#/api/aicrm/customergridhistory';
import { getCustomerGroupAssignmentPage } from '#/api/aicrm/customergroupassignment';

defineOptions({
  name: 'RetailCustomerManagementInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// 归属管户关系 Tab (列表 vs 历史)
const accountTab = ref('list');
// 归属网格关系 Tab (列表 vs 历史)
const gridTab = ref('list');

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
        cellRender: {
          name: 'CellDict',
          props: { type: 'aicrm_assignment_type' },
        },
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
        cellRender: {
          name: 'CellDict',
          props: { type: 'aicrm_assignment_status' },
        },
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 200,
        showOverflow: 'tooltip',
      },
    ],
    height: 400,
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
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_transfer_level' },
      },
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
        cellRender: {
          name: 'CellDict',
          props: { type: 'aicrm_assignment_type' },
        },
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
        cellRender: {
          name: 'CellDict',
          props: { type: 'aicrm_assignment_type' },
        },
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
    height: 400,
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

// 归属网格关系列表 VxeTable 配置
const [GridAssignmentGrid, gridAssignmentGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 70,
        fixed: 'left',
      },
      {
        field: 'gridCode',
        title: '网格编号',
        minWidth: 120,
      },
      {
        field: 'gridName',
        title: '网格名称',
        minWidth: 150,
      },
      {
        field: 'gridType',
        title: '网格类型',
        minWidth: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_grid_type' },
      },
      },
      {
        field: 'gridManagerName',
        title: '网格管理员',
        minWidth: 120,
      },
      {
        field: 'assignDate',
        title: '分配日期',
        minWidth: 120,
      },
      {
        field: 'assignOperatorName',
        title: '分配操作人',
        minWidth: 120,
      },
      {
        field: 'status',
        title: '归属状态',
        minWidth: 100,
        cellRender: {
          name: 'CellDict',
          props: { type: 'aicrm_assignment_status' },
        },
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 200,
        showOverflow: 'tooltip',
      },
    ],
    height: 400,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerGridAssignmentPage({
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
  } as VxeTableGridOptions<AicrmCustomerGridAssignmentApi.CustomerGridAssignment>,
});

// 归属网格调整历史列表 VxeTable 配置
const [GridHistoryGrid, gridHistoryGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 70,
        fixed: 'left',
      },
      {
        field: 'adjustDate',
        title: '调整日期',
        minWidth: 120,
        fixed: 'left',
      },
      {
        field: 'adjustReason',
        title: '调整原因',
        minWidth: 200,
        showOverflow: 'tooltip',
      },
      {
        field: 'beforeGridCode',
        title: '调整前网格编号',
        minWidth: 130,
      },
      {
        field: 'beforeGridName',
        title: '调整前网格名称',
        minWidth: 150,
      },
      {
        field: 'beforeGridType',
        title: '调整前网格类型',
        minWidth: 130,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_grid_type' },
      },
      },
      {
        field: 'beforeGridManagerName',
        title: '调整前网格管理员',
        minWidth: 130,
      },
      {
        field: 'afterGridCode',
        title: '调整后网格编号',
        minWidth: 130,
      },
      {
        field: 'afterGridName',
        title: '调整后网格名称',
        minWidth: 150,
      },
      {
        field: 'afterGridType',
        title: '调整后网格类型',
        minWidth: 130,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_grid_type' },
      },
      },
      {
        field: 'afterGridManagerName',
        title: '调整后网格管理员',
        minWidth: 130,
      },
      {
        field: 'adjustOperatorName',
        title: '调整操作人',
        minWidth: 120,
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 200,
        showOverflow: 'tooltip',
      },
    ],
    height: 400,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerGridHistoryPage({
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
  } as VxeTableGridOptions<AicrmCustomerGridHistoryApi.CustomerGridHistory>,
});

// 归属客户群列表 VxeTable 配置
const [GroupAssignmentGrid, groupAssignmentGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 70,
        fixed: 'left',
      },
      {
        field: 'groupCode',
        title: '客户群编号',
        minWidth: 150,
      },
      {
        field: 'groupName',
        title: '客户群名称',
        minWidth: 180,
      },
      {
        field: 'groupCategory',
        title: '客户群分类',
        minWidth: 130,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_customer_group_category' },
      },
      },
      {
        field: 'memberType',
        title: '群级别类型',
        minWidth: 130,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_group_member_type' },
      },
      },
      {
        field: 'customerSource',
        title: '客户来源',
        minWidth: 130,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_customer_source' },
      },
      },
      {
        field: 'memberCount',
        title: '客户群成员数',
        minWidth: 130,
      },
      {
        field: 'groupCreateTime',
        title: '创建日期',
        minWidth: 150,
      },
      {
        field: 'creatorName',
        title: '创建人',
        minWidth: 120,
      },
      {
        field: 'deptName',
        title: '创建机构',
        minWidth: 150,
      },
      {
        field: 'groupUpdateTime',
        title: '最近修改日期',
        minWidth: 150,
      },
      {
        field: 'updaterName',
        title: '最近修改人',
        minWidth: 120,
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 200,
        showOverflow: 'tooltip',
      },
    ],
    height: 400,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerGroupAssignmentPage({
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
  } as VxeTableGridOptions<AicrmCustomerGroupAssignmentApi.CustomerGroupAssignment>,
});

// 暴露刷新方法
defineExpose({
  refresh: () => {
    // 刷新当前激活的所有表格
    if (accountTab.value === 'list') {
      assignmentGridApi.query();
    } else {
      historyGridApi.query();
    }
    if (gridTab.value === 'list') {
      gridAssignmentGridApi.query();
    } else {
      gridHistoryGridApi.query();
    }
    groupAssignmentGridApi.query();
  },
});
</script>

<template>
  <div class="management-info-page">
    <!-- 归属管户关系区域 -->
    <div class="tab-section">
      <a-tabs v-model:activeKey="accountTab" type="card" class="section-tabs">
        <a-tab-pane key="list" tab="归属管户关系列表">
          <AssignmentGrid table-title="归属管户关系列表" />
        </a-tab-pane>
        <a-tab-pane key="history" tab="归属管户调整历史">
          <HistoryGrid table-title="归属管户调整历史" />
        </a-tab-pane>
      </a-tabs>
    </div>

    <!-- 归属网格关系区域 -->
    <div class="tab-section">
      <a-tabs v-model:activeKey="gridTab" type="card" class="section-tabs">
        <a-tab-pane key="list" tab="归属网格关系列表">
          <GridAssignmentGrid table-title="归属网格关系列表" />
        </a-tab-pane>
        <a-tab-pane key="history" tab="归属网格调整历史">
          <GridHistoryGrid table-title="归属网格调整历史" />
        </a-tab-pane>
      </a-tabs>
    </div>

    <!-- 归属客户群区域 -->
    <div class="tab-section">
      <GroupAssignmentGrid table-title="归属客户群列表" />
    </div>
  </div>
</template>

<style scoped lang="less">
.management-info-page {
  display: flex;
  flex-direction: column;
  gap: 16px;

  .tab-section {
    :deep(.section-tabs) {
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
}

// Dark 模式样式
.dark .management-info-page {
  .tab-section {
    :deep(.section-tabs) {
      .ant-tabs-tab {
        &.ant-tabs-tab-active {
          background: rgb(28 30 35) !important;
        }
      }
    }
  }
}
</style>
