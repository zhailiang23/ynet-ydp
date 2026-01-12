<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { ZerodaiGridApi } from '#/api/grid/zerodai-grid';

import { Page, useVbenModal } from '@vben/common-ui';
import { message } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteZerodaiGrid, getZerodaiGridPage } from '#/api/grid/zerodai-grid';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import Form from './modules/form.vue';

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 新增零贷网格 */
function handleCreate() {
  formModalApi.setData(null).open();
}

/** 编辑零贷网格 */
function handleEdit(record: ZerodaiGridApi.ZerodaiGrid) {
  formModalApi.setData(record).open();
}

/** 删除零贷网格 */
async function handleDelete(record: ZerodaiGridApi.ZerodaiGrid) {
  try {
    await deleteZerodaiGrid(record.id);
    message.success('删除成功');
    gridApi.query();
  } catch (error) {
    console.error('删除失败:', error);
    message.error('删除失败');
  }
}

/** 表单Modal */
const [FormModal, formModalApi] = useVbenModal({
  connectedComponent: Form,
  destroyOnClose: true,
  onConfirm: () => {
    gridApi.query();
  },
});

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
          return await getZerodaiGridPage({
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
  } as VxeTableGridOptions<ZerodaiGridApi.ZerodaiGrid>,
});
</script>

<template>
  <Page auto-content-height>
    <FormModal @success="handleRefresh" />
    <Grid table-title="零贷网格列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: $t('ui.actionTitle.create', ['零贷网格']),
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['grid:zerodai-grid:create'],
              onClick: handleCreate,
            },
          ]"
        />
      </template>
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '编辑',
              icon: ACTION_ICON.EDIT,
              auth: ['grid:zerodai-grid:update'],
              onClick: () => handleEdit(row),
            },
            {
              label: '删除',
              icon: ACTION_ICON.DELETE,
              color: 'error',
              auth: ['grid:zerodai-grid:delete'],
              popConfirm: {
                title: `确定要删除网格【${row.gridName}】吗？`,
                confirm: handleDelete.bind(null, row),
              },
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
