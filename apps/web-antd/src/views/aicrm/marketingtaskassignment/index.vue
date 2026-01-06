<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmMarketingTaskAssignmentApi } from '#/api/aicrm/marketingtaskassignment';

import { ref } from 'vue';

import { confirm, Page } from '@vben/common-ui';
import { downloadFileFromBlobPart, isEmpty } from '@vben/utils';

import { message } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteMarketingTaskAssignment,
  deleteMarketingTaskAssignmentList,
  exportMarketingTaskAssignment,
  getMarketingTaskAssignmentPage,
} from '#/api/aicrm/marketingtaskassignment';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import Form from './modules/form.vue';

const formRef = ref();

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 创建营销活动任务下发 */
function handleCreate() {
  formRef.value.modalApi.setData(null).open();
}

/** 编辑营销活动任务下发 */
function handleEdit(row: AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment) {
  formRef.value.modalApi.setData(row).open();
}

/** 删除营销活动任务下发 */
async function handleDelete(row: AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment) {
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deleting', [row.id]),
    duration: 0,
  });
  try {
    await deleteMarketingTaskAssignment(row.id!);
    message.success($t('ui.actionMessage.deleteSuccess', [row.id]));
    handleRefresh();
  } finally {
    hideLoading();
  }
}

/** 批量删除营销活动任务下发 */
async function handleDeleteBatch() {
  await confirm($t('ui.actionMessage.deleteBatchConfirm'));
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deletingBatch'),
    duration: 0,
  });
  try {
    await deleteMarketingTaskAssignmentList(checkedIds.value);
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
  records: AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment[];
}) {
  checkedIds.value = records.map((item) => item.id!);
}

/** 导出表格 */
async function handleExport() {
  const data = await exportMarketingTaskAssignment(await gridApi.formApi.getValues());
  downloadFileFromBlobPart({ fileName: '营销活动任务下发.xls', source: data });
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
          return await getMarketingTaskAssignmentPage({
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
  } as VxeTableGridOptions<AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment>,
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});
</script>

<template>
  <Page auto-content-height>
    <Form ref="formRef" @success="handleRefresh" />
    <Grid table-title="营销活动任务下发列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: $t('ui.actionTitle.create', ['任务下发']),
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:marketing-task-assignment:create'],
              onClick: handleCreate,
            },
            {
              label: $t('ui.actionTitle.export'),
              type: 'primary',
              icon: ACTION_ICON.DOWNLOAD,
              auth: ['aicrm:marketing-task-assignment:export'],
              onClick: handleExport,
            },
            {
              label: $t('ui.actionTitle.deleteBatch'),
              type: 'primary',
              danger: true,
              icon: ACTION_ICON.DELETE,
              auth: ['aicrm:marketing-task-assignment:delete'],
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
              auth: ['aicrm:marketing-task-assignment:update'],
              onClick: handleEdit.bind(null, row),
            },
            {
              label: $t('common.delete'),
              type: 'link',
              danger: true,
              icon: ACTION_ICON.DELETE,
              auth: ['aicrm:marketing-task-assignment:delete'],
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
