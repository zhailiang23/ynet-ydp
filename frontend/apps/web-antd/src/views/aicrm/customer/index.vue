<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerApi } from '#/api/aicrm/customer';

import { ref } from 'vue';
import { useRouter } from 'vue-router';

import { Page, useVbenModal } from '@vben/common-ui';
import { downloadFileFromBlobPart, isEmpty } from '@vben/utils';

import { message } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import { exportCustomer, getCustomerPage } from '#/api/aicrm/customer';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import AssignForm from '../customerassignment/modules/assign-form.vue';
import ClaimForm from '../customerassignment/modules/claim-form.vue';

const router = useRouter();

const [AssignModal, assignModalApi] = useVbenModal({
  connectedComponent: AssignForm,
  destroyOnClose: true,
});

const [ClaimModal, claimModalApi] = useVbenModal({
  connectedComponent: ClaimForm,
  destroyOnClose: true,
});

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

const checkedIds = ref<number[]>([]);
const checkedRecords = ref<AicrmCustomerApi.Customer[]>([]);

function handleRowCheckboxChange({
  records,
}: {
  records: AicrmCustomerApi.Customer[];
}) {
  checkedIds.value = records.map((item) => item.id!);
  checkedRecords.value = records;
}

/** 批量分配客户 */
function handleAssignBatch() {
  if (isEmpty(checkedIds.value)) {
    message.warning('请先选择要分配的客户');
    return;
  }
  assignModalApi.setData({ customerIds: checkedIds.value }).open();
}

/** 批量认领客户 */
function handleClaimBatch() {
  if (isEmpty(checkedIds.value)) {
    message.warning('请先选择要认领的客户');
    return;
  }
  // 过滤出未分配的客户
  const unassignedCustomers = checkedRecords.value.filter(c => c.assignmentStatus === 0);
  if (unassignedCustomers.length === 0) {
    message.warning('选中的客户中没有未分配的客户');
    return;
  }
  claimModalApi.setData({ customerIds: unassignedCustomers.map(c => c.id!) }).open();
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
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});
</script>

<template>
  <Page auto-content-height>
    <AssignModal @success="handleRefresh" />
    <ClaimModal @success="handleRefresh" />
    <Grid table-title="客户列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '批量分配',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:customer-assignment:create'],
              disabled: isEmpty(checkedIds),
              onClick: handleAssignBatch,
            },
            {
              label: '批量认领',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:customer-assignment:create'],
              disabled: isEmpty(checkedIds),
              onClick: handleClaimBatch,
            },
            {
              label: '导出',
              type: 'default',
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