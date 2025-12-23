<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { LobbyGridApi } from '#/api/grid/lobby-grid';

import { Page, useVbenModal } from '@vben/common-ui';
import { message } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteLobbyGrid, getLobbyGridPage } from '#/api/grid/lobby-grid';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import Form from './modules/form.vue';

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 新增厅堂网格 */
function handleCreate() {
  formModalApi.setData(null).open();
}

/** 编辑厅堂网格 */
function handleEdit(record: LobbyGridApi.LobbyGrid) {
  formModalApi.setData(record).open();
}

/** 删除厅堂网格 */
async function handleDelete(record: LobbyGridApi.LobbyGrid) {
  try {
    await deleteLobbyGrid(record.id);
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
          return await getLobbyGridPage({
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
  } as VxeTableGridOptions<LobbyGridApi.LobbyGrid>,
});
</script>

<template>
  <Page auto-content-height>
    <FormModal @success="handleRefresh" />
    <Grid table-title="厅堂网格列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: $t('ui.actionTitle.create', ['厅堂网格']),
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['grid:lobby-grid:create'],
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
              auth: ['grid:lobby-grid:update'],
              onClick: () => handleEdit(row),
            },
            {
              label: '删除',
              icon: ACTION_ICON.DELETE,
              color: 'error',
              auth: ['grid:lobby-grid:delete'],
              popConfirm: {
                title: `确定要删除厅堂网格【${row.locationName}】吗？`,
                confirm: handleDelete.bind(null, row),
              },
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
