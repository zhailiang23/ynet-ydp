<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPotentialCustomerApi } from '#/api/aicrm/potentialcustomer';

import { ref } from 'vue';

import { Page, useVbenModal } from '@vben/common-ui';
import { downloadFileFromBlobPart, isEmpty } from '@vben/utils';

import { message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  createPotentialCustomer,
  deletePotentialCustomer,
  deletePotentialCustomerList,
  exportPotentialCustomer,
  getPotentialCustomerPage,
  updatePotentialCustomer,
} from '#/api/aicrm/potentialcustomer';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import PotentialCustomerForm from './modules/potential-customer-form.vue';

const [FormModal, formModalApi] = useVbenModal({
  connectedComponent: PotentialCustomerForm,
  destroyOnClose: true,
});

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 新增潜客 */
function handleCreate() {
  formModalApi.open();
}

/** 编辑潜客 */
async function handleEdit(row: AicrmPotentialCustomerApi.PotentialCustomer) {
  formModalApi.setData(row).open();
}

/** 删除潜客 */
async function handleDelete(row: AicrmPotentialCustomerApi.PotentialCustomer) {
  Modal.confirm({
    title: '确认删除',
    content: `确定要删除潜客"${row.customerName}"吗？`,
    onOk: async () => {
      try {
        await deletePotentialCustomer(row.id!);
        message.success('删除成功');
        handleRefresh();
      } catch (error) {
        message.error('删除失败');
      }
    },
  });
}

/** 导出表格 */
async function handleExport() {
  const hideLoading = message.loading({
    content: '正在导出...',
    duration: 0,
  });
  try {
    const data = await exportPotentialCustomer(await gridApi.formApi.getValues());
    downloadFileFromBlobPart({ fileName: '潜客列表.xls', source: data });
    message.success('导出成功');
  } finally {
    hideLoading();
  }
}

const checkedIds = ref<number[]>([]);

function handleRowCheckboxChange({
  records,
}: {
  records: AicrmPotentialCustomerApi.PotentialCustomer[];
}) {
  checkedIds.value = records.map((item) => item.id!);
}

/** 批量删除潜客 */
function handleDeleteBatch() {
  if (isEmpty(checkedIds.value)) {
    message.warning('请先选择要删除的潜客');
    return;
  }
  Modal.confirm({
    title: '确认批量删除',
    content: `确定要删除选中的 ${checkedIds.value.length} 个潜客吗？`,
    onOk: async () => {
      try {
        await deletePotentialCustomerList(checkedIds.value);
        message.success('批量删除成功');
        handleRefresh();
      } catch (error) {
        message.error('批量删除失败');
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
          return await getPotentialCustomerPage({
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
  } as VxeTableGridOptions<AicrmPotentialCustomerApi.PotentialCustomer>,
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});
</script>

<template>
  <Page auto-content-height>
    <FormModal @success="handleRefresh" />
    <Grid table-title="潜客列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '新增',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:potential-customer:create'],
              onClick: handleCreate,
            },
            {
              label: '批量删除',
              type: 'default',
              icon: ACTION_ICON.DELETE,
              auth: ['aicrm:potential-customer:delete'],
              disabled: isEmpty(checkedIds),
              onClick: handleDeleteBatch,
            },
            {
              label: '导出',
              type: 'default',
              icon: ACTION_ICON.DOWNLOAD,
              auth: ['aicrm:potential-customer:export'],
              onClick: handleExport,
            },
          ]"
        />
      </template>
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '编辑',
              type: 'link',
              icon: ACTION_ICON.EDIT,
              auth: ['aicrm:potential-customer:update'],
              onClick: handleEdit.bind(null, row),
            },
            {
              label: '删除',
              type: 'link',
              icon: ACTION_ICON.DELETE,
              auth: ['aicrm:potential-customer:delete'],
              popConfirm: {
                title: '确认删除？',
                onConfirm: handleDelete.bind(null, row),
              },
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
