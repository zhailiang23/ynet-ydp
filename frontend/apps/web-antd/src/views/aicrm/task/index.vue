<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmTaskApi } from '#/api/aicrm/task';

import { ref } from 'vue';

import { Page, useVbenModal } from '@vben/common-ui';
import { isEmpty } from '@vben/utils';

import { message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteTask,
  deleteTaskList,
  getTaskPage,
} from '#/api/aicrm/task';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import TaskForm from './modules/task-form.vue';

const [TaskModal, taskModalApi] = useVbenModal({
  connectedComponent: TaskForm,
  destroyOnClose: true,
});

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 新增任务 */
function handleCreate() {
  taskModalApi.setData({}).open();
}

/** 编辑任务 */
function handleEdit(row: AicrmTaskApi.Task) {
  taskModalApi.setData(row).open();
}

/** 删除任务 */
function handleDelete(row: AicrmTaskApi.Task) {
  Modal.confirm({
    title: '确认删除',
    content: `确定要删除任务"${row.title}"吗？`,
    onOk: async () => {
      await deleteTask(row.id!);
      message.success('删除成功');
      handleRefresh();
    },
  });
}

const checkedIds = ref<number[]>([]);

function handleRowCheckboxChange({
  records,
}: {
  records: AicrmTaskApi.Task[];
}) {
  checkedIds.value = records.map((item) => item.id!);
}

/** 批量删除任务 */
function handleDeleteBatch() {
  if (isEmpty(checkedIds.value)) {
    message.warning('请先选择要删除的任务');
    return;
  }
  Modal.confirm({
    title: '确认批量删除',
    content: `确定要删除选中的 ${checkedIds.value.length} 个任务吗？`,
    onOk: async () => {
      await deleteTaskList(checkedIds.value);
      message.success('批量删除成功');
      handleRefresh();
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
          return await getTaskPage({
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
  } as VxeTableGridOptions<AicrmTaskApi.Task>,
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});
</script>

<template>
  <Page auto-content-height>
    <TaskModal @success="handleRefresh" />
    <Grid table-title="AI智能任务">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '新增',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['task:task:create'],
              onClick: handleCreate,
            },
            {
              label: '批量删除',
              type: 'default',
              icon: ACTION_ICON.DELETE,
              auth: ['task:task:delete'],
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
              label: '编辑',
              type: 'link',
              icon: ACTION_ICON.EDIT,
              auth: ['task:task:update'],
              onClick: handleEdit.bind(null, row),
            },
            {
              label: '删除',
              type: 'link',
              icon: ACTION_ICON.DELETE,
              auth: ['task:task:delete'],
              danger: true,
              onClick: handleDelete.bind(null, row),
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
