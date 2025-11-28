<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { TwinsCustomerAdminApi } from '#/api/twins/customerAdmin';

import { ref } from 'vue';
import { useRouter } from 'vue-router';

import { confirm, Page, useVbenModal } from '@vben/common-ui';
import { downloadFileFromBlobPart, isEmpty } from '@vben/utils';

import { message } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteCustomerAdmin,
  deleteCustomerAdminList,
  exportCustomerAdmin,
  getCustomerAdminPage,
} from '#/api/twins/customerAdmin';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import Form from './modules/form.vue';

const router = useRouter();

const [FormModal, formModalApi] = useVbenModal({
  connectedComponent: Form,
  destroyOnClose: true,
});

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 创建客服信息 */
function handleCreate() {
  formModalApi.setData(null).open();
}

/** 编辑客服信息 */
function handleEdit(row: TwinsCustomerAdminApi.CustomerAdmin) {
  formModalApi.setData(row).open();
}

/** 删除客服信息 */
async function handleDelete(row: TwinsCustomerAdminApi.CustomerAdmin) {
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deleting', [row.id]),
    duration: 0,
  });
  try {
    await deleteCustomerAdmin(row.id!);
    message.success($t('ui.actionMessage.deleteSuccess', [row.id]));
    handleRefresh();
  } finally {
    hideLoading();
  }
}

/** 批量删除客服信息 */
async function handleDeleteBatch() {
  await confirm($t('ui.actionMessage.deleteBatchConfirm'));
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deletingBatch'),
    duration: 0,
  });
  try {
    await deleteCustomerAdminList(checkedIds.value);
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
  records: TwinsCustomerAdminApi.CustomerAdmin[];
}) {
  checkedIds.value = records.map((item) => item.id!);
}

/** 导出表格 */
async function handleExport() {
  const data = await exportCustomerAdmin(await gridApi.formApi.getValues());
  downloadFileFromBlobPart({ fileName: '客服信息.xls', source: data });
}

/** 聊天加载状态 */
const chatLoading = ref(false);

/** 打开聊天对话 - 在管理页面的内部 Tab 中打开 */
async function handleOpenChat(row: TwinsCustomerAdminApi.CustomerAdmin) {
  chatLoading.value = true;
  try {
    // 导航到聊天页面
    await router.push({
      name: 'TwinsChat',
      params: { adminId: row.id!.toString() },
    });
  } catch (error: any) {
    message.error(error.message || '打开聊天失败');
  } finally {
    chatLoading.value = false;
  }
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
          return await getCustomerAdminPage({
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
  } as VxeTableGridOptions<TwinsCustomerAdminApi.CustomerAdmin>,
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});
</script>

<template>
  <Page auto-content-height>
    <FormModal @success="handleRefresh" />
    <Grid table-title="客服信息列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: $t('ui.actionTitle.create', ['客服信息']),
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['twins:customer-admin:create'],
              onClick: handleCreate,
            },
            {
              label: $t('ui.actionTitle.export'),
              type: 'primary',
              icon: ACTION_ICON.DOWNLOAD,
              auth: ['twins:customer-admin:export'],
              onClick: handleExport,
            },
            {
              label: $t('ui.actionTitle.deleteBatch'),
              type: 'primary',
              danger: true,
              icon: ACTION_ICON.DELETE,
              auth: ['twins:customer-admin:delete'],
              disabled: isEmpty(checkedIds),
              onClick: handleDeleteBatch,
            },
          ]"
        />
      </template>
      <!-- 操作列 -->
      <template #action="{ row }">
        <TableAction
          :actions="[
            {
              label: '对话',
              type: 'link',
              icon: ACTION_ICON.MESSAGE,
              loading: chatLoading,
              onClick: () => handleOpenChat(row),
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>