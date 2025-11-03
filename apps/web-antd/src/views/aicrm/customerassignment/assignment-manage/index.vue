<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerApi } from '#/api/aicrm/customer';

import { ref } from 'vue';

import { Page } from '@vben/common-ui';
import { useUserStore } from '@vben/stores';

import { Button as AButton, message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerPage } from '#/api/aicrm/customer';
import { assignCustomers, reclaimCustomers } from '#/api/aicrm/customerassignment';
import { $t } from '#/locales';

import AssignModal from './assign-modal.vue';
import ViewAssignmentModal from './view-assignment-modal.vue';
import { useGridColumns, useGridFormSchema } from './data';

const userStore = useUserStore();

const assignModalRef = ref();
const viewAssignmentModalRef = ref();

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 查看客户归属关系 */
function handleViewDetail(row: AicrmCustomerApi.Customer) {
  // 打开查看对话框
  viewAssignmentModalRef.value?.modalApi.setData(row);
  viewAssignmentModalRef.value?.modalApi.open();
}

/** 批量分配客户 */
function handleBatchAssign() {
  const selectedRows = gridApi.grid.getCheckboxRecords();
  if (selectedRows.length === 0) {
    message.warning('请选择要分配的客户');
    return;
  }

  // 打开分配对话框
  assignModalRef.value?.modalApi.setData({
    customerIds: selectedRows.map((row: AicrmCustomerApi.Customer) => row.id),
  });
  assignModalRef.value?.modalApi.open();
}

/** 批量认领客户 */
function handleBatchClaim() {
  const selectedRows = gridApi.grid.getCheckboxRecords();
  if (selectedRows.length === 0) {
    message.warning('请选择要认领的客户');
    return;
  }

  // 检查是否所有选中的客户都是未分配状态
  const unassignedRows = selectedRows.filter((row: AicrmCustomerApi.Customer) => row.assignmentStatus === 0);
  if (unassignedRows.length === 0) {
    message.warning('所选客户中没有未分配的客户，无法认领');
    return;
  }

  if (unassignedRows.length < selectedRows.length) {
    message.warning(`只能认领未分配的客户，已自动过滤 ${selectedRows.length - unassignedRows.length} 个已分配的客户`);
  }

  // 获取当前用户信息
  const currentUser = userStore.userInfo;
  if (!currentUser?.deptId) {
    message.error('当前用户未设置部门，无法认领客户');
    return;
  }

  // 打开确认对话框
  Modal.confirm({
    title: '批量认领客户',
    content: `确定要认领 ${unassignedRows.length} 个客户吗？您将成为这些客户的主办，客户将归属到您的部门。`,
    onOk: async () => {
      try {
        await assignCustomers({
          customerIds: unassignedRows.map((row: AicrmCustomerApi.Customer) => row.id),
          assignmentType: 1, // 主办
          deptId: currentUser.deptId,
          userId: currentUser.id,
          hasViewRight: true,
          hasMaintainRight: true,
          remark: '批量认领',
        });
        message.success(`成功认领 ${unassignedRows.length} 个客户`);
        handleRefresh();
      } catch (error) {
        console.error('批量认领失败:', error);
      }
    },
  });
}

/** 批量回收客户 */
function handleBatchReclaim() {
  const selectedRows = gridApi.grid.getCheckboxRecords();
  if (selectedRows.length === 0) {
    message.warning('请选择要回收的客户');
    return;
  }

  // 检查是否所有选中的客户都是已分配状态
  const assignedRows = selectedRows.filter((row: AicrmCustomerApi.Customer) => row.assignmentStatus === 1);
  if (assignedRows.length === 0) {
    message.warning('所选客户中没有已分配的客户，无法回收');
    return;
  }

  if (assignedRows.length < selectedRows.length) {
    message.warning(`只能回收已分配的客户，已自动过滤 ${selectedRows.length - assignedRows.length} 个未分配的客户`);
  }

  // 打开确认对话框
  Modal.confirm({
    title: '批量回收客户',
    content: `确定要回收 ${assignedRows.length} 个客户吗？`,
    onOk: async () => {
      try {
        await reclaimCustomers({
          customerIds: assignedRows.map((row: AicrmCustomerApi.Customer) => row.id),
          reclaimReason: '批量回收',
        });
        message.success(`成功回收 ${assignedRows.length} 个客户`);
        handleRefresh();
      } catch (error) {
        console.error('批量回收失败:', error);
      }
    },
  });
}

/** 分配单个客户 */
function handleAssignSingle(row: AicrmCustomerApi.Customer) {
  // 允许为已分配的客户再次分配(添加新的管理者)
  // 打开分配对话框 - 使用setData传递数据
  assignModalRef.value?.modalApi.setData({
    customerIds: [row.id],
  });
  assignModalRef.value?.modalApi.open();
}

/** 认领客户 */
function handleClaim(row: AicrmCustomerApi.Customer) {
  // 检查客户是否未分配
  if (row.assignmentStatus !== 0) {
    message.warning('只能认领未分配的客户');
    return;
  }

  // 获取当前用户信息
  const currentUser = userStore.userInfo;
  if (!currentUser?.deptId) {
    message.error('当前用户未设置部门,无法认领客户');
    return;
  }

  // 打开确认对话框
  Modal.confirm({
    title: '认领客户',
    content: `确定要认领客户"${row.customerName}"吗? 您将成为该客户的主办,客户将归属到您的部门。`,
    onOk: async () => {
      try {
        await assignCustomers({
          customerIds: [row.id],
          assignmentType: 1, // 主办
          deptId: currentUser.deptId,
          userId: currentUser.id,
          hasViewRight: true,
          hasMaintainRight: true,
          remark: '快速认领',
        });
        message.success('认领成功');
        handleRefresh();
      } catch (error) {
        console.error('认领失败:', error);
      }
    },
  });
}

/** 回收客户 */
function handleReclaim(row: AicrmCustomerApi.Customer) {
  // 检查是否已分配
  if (row.assignmentStatus === 0) {
    message.warning('该客户未分配,无需回收');
    return;
  }

  // 打开确认对话框
  Modal.confirm({
    title: '回收客户',
    content: `确定要回收客户"${row.customerName}"吗?`,
    onOk: async () => {
      try {
        await reclaimCustomers({
          customerIds: [row.id],
          reclaimReason: '手动回收',
        });
        message.success('回收成功');
        handleRefresh();
      } catch (error) {
        console.error('回收失败:', error);
      }
    },
  });
}

const [Grid, gridApi] = useVbenVxeGrid({
  formOptions: {
    schema: useGridFormSchema(),
  },
  gridOptions: {
    columns: useGridColumns(),
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }, formValues) => {
          return await getCustomerPage({
            pageNo: page.currentPage,
            pageSize: page.pageSize,
            ...formValues,
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
      search: true,
    },
    checkboxConfig: {
      highlight: true,
      reserve: true,
    },
  } as VxeTableGridOptions<AicrmCustomerApi.Customer>,
});
</script>

<template>
  <Page auto-content-height>
    <Grid table-title="客户归属管理">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '批量分配',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:customer-assignment:assign'],
              onClick: handleBatchAssign,
            },
            {
              label: '批量认领',
              type: 'primary',
              icon: ACTION_ICON.DOWNLOAD,
              auth: ['aicrm:customer-assignment:assign'],
              onClick: handleBatchClaim,
            },
            {
              label: '批量回收',
              type: 'default',
              icon: ACTION_ICON.DELETE,
              auth: ['aicrm:customer-assignment:reclaim'],
              onClick: handleBatchReclaim,
            },
          ]"
        />
      </template>

      <template #actions="{ row }">
        <div class="flex gap-2">
          <a-button type="link" size="small" @click="handleViewDetail(row)">
            查看
          </a-button>
          <a-button
            type="link"
            size="small"
            @click="handleAssignSingle(row)"
          >
            分配
          </a-button>
          <a-button
            v-if="row.assignmentStatus === 0"
            type="link"
            size="small"
            @click="handleClaim(row)"
          >
            认领
          </a-button>
          <a-button
            v-if="row.assignmentStatus === 1"
            type="link"
            size="small"
            danger
            @click="handleReclaim(row)"
          >
            回收
          </a-button>
        </div>
      </template>
    </Grid>

    <!-- 分配对话框 -->
    <AssignModal ref="assignModalRef" @success="handleRefresh" />

    <!-- 查看归属关系对话框 -->
    <ViewAssignmentModal ref="viewAssignmentModalRef" />
  </Page>
</template>
