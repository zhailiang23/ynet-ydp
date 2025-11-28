<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { TwinsChatCollectInfoApi } from '#/api/twins/chatcollectinfo';

import { Page, useVbenModal } from '@vben/common-ui';
import { downloadFileFromBlobPart } from '@vben/utils';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  exportChatCollectInfo,
  getChatCollectInfoPage,
} from '#/api/twins/chatcollectinfo';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import ViewModal from './modules/view.vue';

const [ViewModalInstance, viewModalApi] = useVbenModal({
  connectedComponent: ViewModal,
  destroyOnClose: true,
});

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 查看客户留资信息 */
function handleView(row: TwinsChatCollectInfoApi.ChatCollectInfo) {
  viewModalApi.setData(row).open();
}

/** 导出表格 */
async function handleExport() {
  const data = await exportChatCollectInfo(await gridApi.formApi.getValues());
  downloadFileFromBlobPart({ fileName: '客户留资信息.xls', source: data });
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
          return await getChatCollectInfoPage({
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
  } as VxeTableGridOptions<TwinsChatCollectInfoApi.ChatCollectInfo>,
});
</script>

<template>
  <Page auto-content-height>
    <ViewModalInstance />
    <Grid table-title="客户留资信息列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: $t('ui.actionTitle.export'),
              type: 'primary',
              icon: ACTION_ICON.DOWNLOAD,
              auth: ['twins:chat-collect-info:export'],
              onClick: handleExport,
            },
          ]"
        />
      </template>
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '查看',
              type: 'link',
              icon: ACTION_ICON.VIEW,
              auth: ['twins:chat-collect-info:query'],
              onClick: handleView.bind(null, row),
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>