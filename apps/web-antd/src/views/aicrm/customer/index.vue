<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerApi } from '#/api/aicrm/customer';

import { useRouter } from 'vue-router';

import { Page } from '@vben/common-ui';
import { downloadFileFromBlobPart } from '@vben/utils';

import { message } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import { exportCustomer, getCustomerPage } from '#/api/aicrm/customer';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';

const router = useRouter();

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 查看客户详情 - 根据客户类型智能路由 */
function handleViewDetail(row: AicrmCustomerApi.Customer) {
  const { customerType, id } = row;

  // customerType: 1=零售客户, 2=对公客户
  if (customerType === 1) {
    // 零售客户详情页
    router.push(`/aicrm/retail-customer/detail/${id}`);
  } else if (customerType === 2) {
    // 对公客户详情页
    router.push(`/aicrm/company-customer/detail/${id}`);
  } else {
    message.warning('未知的客户类型');
  }
}

/** 导出表格 */
async function handleExport() {
  const hideLoading = message.loading({
    content: '正在导出...',
    duration: 0,
  });
  try {
    const data = await exportCustomer(await gridApi.formApi.getValues());
    downloadFileFromBlobPart({ fileName: 'CRM客户列表.xls', source: data });
    message.success('导出成功');
  } finally {
    hideLoading();
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
          return await getCustomerPage({
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
  } as VxeTableGridOptions<AicrmCustomerApi.Customer>,
});
</script>

<template>
  <Page auto-content-height>
    <Grid table-title="客户列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '导出',
              type: 'primary',
              icon: ACTION_ICON.DOWNLOAD,
              auth: ['aicrm:customer:export'],
              onClick: handleExport,
            },
          ]"
        />
      </template>
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '查看详情',
              type: 'link',
              icon: ACTION_ICON.VIEW,
              auth: ['aicrm:customer:query'],
              onClick: handleViewDetail.bind(null, row),
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>