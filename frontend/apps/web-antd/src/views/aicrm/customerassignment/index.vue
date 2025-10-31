<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';

import { ref } from 'vue';

import { confirm, Page, useVbenModal } from '@vben/common-ui';
import { downloadFileFromBlobPart, isEmpty } from '@vben/utils';

import { message } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  assignCustomers,
  changeDept,
  deleteCustomerAssignment,
  deleteCustomerAssignmentList,
  exportCustomerAssignment,
  getCustomerAssignmentPage,
  reclaimCustomers,
  transferCustomers,
} from '#/api/aicrm/customerassignment';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import AssignForm from './modules/assign-form.vue';
import ChangeDeptForm from './modules/change-dept-form.vue';
import Form from './modules/form.vue';
import ReclaimForm from './modules/reclaim-form.vue';
import TransferForm from './modules/transfer-form.vue';

const [FormModal, formModalApi] = useVbenModal({
  connectedComponent: Form,
  destroyOnClose: true,
});

const [AssignModal, assignModalApi] = useVbenModal({
  connectedComponent: AssignForm,
  destroyOnClose: true,
});

const [TransferModal, transferModalApi] = useVbenModal({
  connectedComponent: TransferForm,
  destroyOnClose: true,
});

const [ReclaimModal, reclaimModalApi] = useVbenModal({
  connectedComponent: ReclaimForm,
  destroyOnClose: true,
});

const [ChangeDeptModal, changeDeptModalApi] = useVbenModal({
  connectedComponent: ChangeDeptForm,
  destroyOnClose: true,
});

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 创建客户归属关系表（零售+对公共用，支持主协办模式） */
function handleCreate() {
  formModalApi.setData(null).open();
}

/** 编辑客户归属关系表（零售+对公共用，支持主协办模式） */
function handleEdit(row: AicrmCustomerAssignmentApi.CustomerAssignment) {
  formModalApi.setData(row).open();
}

/** 删除客户归属关系表（零售+对公共用，支持主协办模式） */
async function handleDelete(row: AicrmCustomerAssignmentApi.CustomerAssignment) {
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deleting', [row.id]),
    duration: 0,
  });
  try {
    await deleteCustomerAssignment(row.id!);
    message.success($t('ui.actionMessage.deleteSuccess', [row.id]));
    handleRefresh();
  } finally {
    hideLoading();
  }
}

/** 批量删除客户归属关系表（零售+对公共用，支持主协办模式） */
async function handleDeleteBatch() {
  await confirm($t('ui.actionMessage.deleteBatchConfirm'));
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deletingBatch'),
    duration: 0,
  });
  try {
    await deleteCustomerAssignmentList(checkedIds.value);
    checkedIds.value = [];
    message.success($t('ui.actionMessage.deleteSuccess'));
    handleRefresh();
  } finally {
    hideLoading();
  }
}

const checkedIds = ref<number[]>([]);
const checkedRecords = ref<AicrmCustomerAssignmentApi.CustomerAssignment[]>([]);

function handleRowCheckboxChange({
  records,
}: {
  records: AicrmCustomerAssignmentApi.CustomerAssignment[];
}) {
  checkedIds.value = records.map((item) => item.id!);
  checkedRecords.value = records;
}

/** 批量分配客户 */
function handleAssignBatch() {
  if (isEmpty(checkedIds.value)) {
    message.warning('请先选择要分配的客户');
    return;
  }
  assignModalApi.setData({ customerIds: checkedRecords.value.map(r => r.customerId!) }).open();
}

/** 批量移交客户 */
function handleTransferBatch() {
  if (isEmpty(checkedIds.value)) {
    message.warning('请先选择要移交的客户');
    return;
  }
  transferModalApi.setData({ customerIds: checkedRecords.value.map(r => r.customerId!) }).open();
}

/** 批量回收客户 */
function handleReclaimBatch() {
  if (isEmpty(checkedIds.value)) {
    message.warning('请先选择要回收的客户');
    return;
  }
  reclaimModalApi.setData({ customerIds: checkedRecords.value.map(r => r.customerId!) }).open();
}

/** 批量变更主办部门 */
function handleChangeDeptBatch() {
  if (isEmpty(checkedIds.value)) {
    message.warning('请先选择要变更主办部门的客户');
    return;
  }
  changeDeptModalApi.setData({ customerIds: checkedRecords.value.map(r => r.customerId!) }).open();
}

/** 导出表格 */
async function handleExport() {
  const data = await exportCustomerAssignment(await gridApi.formApi.getValues());
  downloadFileFromBlobPart({ fileName: '客户归属关系表（零售+对公共用，支持主协办模式）.xls', source: data });
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
          return await getCustomerAssignmentPage({
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
  } as VxeTableGridOptions<AicrmCustomerAssignmentApi.CustomerAssignment>,
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});
</script>

<template>
  <Page auto-content-height>
    <FormModal @success="handleRefresh" />
    <AssignModal @success="handleRefresh" />
    <TransferModal @success="handleRefresh" />
    <ReclaimModal @success="handleRefresh" />
    <ChangeDeptModal @success="handleRefresh" />
    <Grid table-title="客户归属关系表（零售+对公共用，支持主协办模式）列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: $t('ui.actionTitle.create', ['客户归属关系表（零售+对公共用，支持主协办模式）']),
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:customer-assignment:create'],
              onClick: handleCreate,
            },
            {
              label: '批量分配',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:customer-assignment:assign'],
              disabled: isEmpty(checkedIds),
              onClick: handleAssignBatch,
            },
            {
              label: '批量移交',
              type: 'primary',
              icon: ACTION_ICON.EDIT,
              auth: ['aicrm:customer-assignment:transfer'],
              disabled: isEmpty(checkedIds),
              onClick: handleTransferBatch,
            },
            {
              label: '批量回收',
              type: 'primary',
              icon: ACTION_ICON.DELETE,
              auth: ['aicrm:customer-assignment:reclaim'],
              disabled: isEmpty(checkedIds),
              onClick: handleReclaimBatch,
            },
            {
              label: '变更主办部门',
              type: 'primary',
              icon: ACTION_ICON.EDIT,
              auth: ['aicrm:customer-assignment:change-dept'],
              disabled: isEmpty(checkedIds),
              onClick: handleChangeDeptBatch,
            },
            {
              label: $t('ui.actionTitle.export'),
              type: 'default',
              icon: ACTION_ICON.DOWNLOAD,
              auth: ['aicrm:customer-assignment:export'],
              onClick: handleExport,
            },
            {
              label: $t('ui.actionTitle.deleteBatch'),
              type: 'default',
              danger: true,
              icon: ACTION_ICON.DELETE,
              auth: ['aicrm:customer-assignment:delete'],
              disabled: isEmpty(checkedIds),
              onClick: handleDeleteBatch,
            },
          ]"
        />
      </template>
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: $t('common.edit'),
              type: 'link',
              icon: ACTION_ICON.EDIT,
              auth: ['aicrm:customer-assignment:update'],
              onClick: handleEdit.bind(null, row),
            },
            {
              label: $t('common.delete'),
              type: 'link',
              danger: true,
              icon: ACTION_ICON.DELETE,
              auth: ['aicrm:customer-assignment:delete'],
              popConfirm: {
                title: $t('ui.actionMessage.deleteConfirm', [row.id]),
                confirm: handleDelete.bind(null, row),
              },
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>