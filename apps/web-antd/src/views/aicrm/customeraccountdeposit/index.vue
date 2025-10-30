<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAccountDepositApi } from '#/api/aicrm/customeraccountdeposit';

import { ref } from 'vue';

import { confirm, Page, useVbenModal } from '@vben/common-ui';
import { downloadFileFromBlobPart, isEmpty } from '@vben/utils';

import { message } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteCustomerAccountDeposit,
  deleteCustomerAccountDepositList,
  exportCustomerAccountDeposit,
  getCustomerAccountDepositPage,
} from '#/api/aicrm/customeraccountdeposit';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import Form from './modules/form.vue';

const [FormModal, formModalApi] = useVbenModal({
  connectedComponent: Form,
  destroyOnClose: true,
});

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 创建客户存款账户信息表（零售+对公共用） */
function handleCreate() {
  formModalApi.setData(null).open();
}

/** 编辑客户存款账户信息表（零售+对公共用） */
function handleEdit(row: AicrmCustomerAccountDepositApi.CustomerAccountDeposit) {
  formModalApi.setData(row).open();
}

/** 删除客户存款账户信息表（零售+对公共用） */
async function handleDelete(row: AicrmCustomerAccountDepositApi.CustomerAccountDeposit) {
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deleting', [row.id]),
    duration: 0,
  });
  try {
    await deleteCustomerAccountDeposit(row.id!);
    message.success($t('ui.actionMessage.deleteSuccess', [row.id]));
    handleRefresh();
  } finally {
    hideLoading();
  }
}

/** 批量删除客户存款账户信息表（零售+对公共用） */
async function handleDeleteBatch() {
  await confirm($t('ui.actionMessage.deleteBatchConfirm'));
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deletingBatch'),
    duration: 0,
  });
  try {
    await deleteCustomerAccountDepositList(checkedIds.value);
    checkedIds.value = [];
    message.success($t('ui.actionMessage.deleteSuccess'));
    handleRefresh();
  } finally {
    hideLoading();
  }
}

const checkedIds = ref<number[]>([]);
function handleRowCheckboxChange({
  records,
}: {
  records: AicrmCustomerAccountDepositApi.CustomerAccountDeposit[];
}) {
  checkedIds.value = records.map((item) => item.id!);
}

/** 导出表格 */
async function handleExport() {
  const data = await exportCustomerAccountDeposit(await gridApi.formApi.getValues());
  downloadFileFromBlobPart({ fileName: '客户存款账户信息表（零售+对公共用）.xls', source: data });
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
          return await getCustomerAccountDepositPage({
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
  } as VxeTableGridOptions<AicrmCustomerAccountDepositApi.CustomerAccountDeposit>,
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});
</script>

<template>
  <Page auto-content-height>
    <FormModal @success="handleRefresh" />
    <Grid table-title="客户存款账户信息表（零售+对公共用）列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: $t('ui.actionTitle.create', ['客户存款账户信息表（零售+对公共用）']),
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:customer-account-deposit:create'],
              onClick: handleCreate,
            },
            {
              label: $t('ui.actionTitle.export'),
              type: 'primary',
              icon: ACTION_ICON.DOWNLOAD,
              auth: ['aicrm:customer-account-deposit:export'],
              onClick: handleExport,
            },
            {
              label: $t('ui.actionTitle.deleteBatch'),
              type: 'primary',
              danger: true,
              icon: ACTION_ICON.DELETE,
              auth: ['aicrm:customer-account-deposit:delete'],
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
              auth: ['aicrm:customer-account-deposit:update'],
              onClick: handleEdit.bind(null, row),
            },
            {
              label: $t('common.delete'),
              type: 'link',
              danger: true,
              icon: ACTION_ICON.DELETE,
              auth: ['aicrm:customer-account-deposit:delete'],
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