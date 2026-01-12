<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { CommunityApi } from '#/api/grid/community';

import { Page, useVbenModal } from '@vben/common-ui';
import { message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteCommunity, getCommunityPage } from '#/api/grid/community';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import Form from './modules/form.vue';

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 新增社区 */
function handleCreate() {
  formModalApi.setData(null).open();
}

/** 编辑社区 */
function handleEdit(record: CommunityApi.Community) {
  formModalApi.setData(record).open();
}

/** 删除社区 */
async function handleDelete(record: CommunityApi.Community) {
  Modal.confirm({
    title: '确认删除',
    content: `确定要删除社区【${record.communityName}】吗？`,
    okText: '确定',
    cancelText: '取消',
    async onOk() {
      try {
        await deleteCommunity(record.id!);
        message.success('删除成功');
        gridApi.query();
      } catch (error) {
        console.error('删除失败:', error);
        message.error('删除失败');
      }
    },
  });
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
          return await getCommunityPage({
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
  } as VxeTableGridOptions<CommunityApi.Community>,
});
</script>

<template>
  <Page auto-content-height>
    <FormModal @success="handleRefresh" />
    <Grid table-title="社区信息列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: $t('ui.actionTitle.create', ['社区信息']),
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['grid:community:create'],
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
              auth: ['grid:community:update'],
              onClick: () => handleEdit(row),
            },
            {
              label: '删除',
              icon: ACTION_ICON.DELETE,
              color: 'error',
              auth: ['grid:community:delete'],
              popConfirm: {
                title: `确定要删除社区【${row.communityName}】吗？`,
                confirm: handleDelete.bind(null, row),
              },
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
