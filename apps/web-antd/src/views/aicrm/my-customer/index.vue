<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';

import { ref } from 'vue';
import { useRouter } from 'vue-router';

import { Page } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import { getMyCustomerPage } from '#/api/aicrm/customerassignment';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import DelegateForm from './modules/delegate-form.vue';
import TransferForm from './modules/transfer-form.vue';

const router = useRouter();
const delegateFormRef = ref();
const transferFormRef = ref();

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 查看客户详情 - 根据客户类型智能路由 */
function handleViewDetail(row: AicrmCustomerAssignmentApi.MyCustomerResp) {
  const { customerType, customerId } = row;

  // customerType: 1=零售客户, 2=对公客户
  if (customerType === 1) {
    // 零售客户详情页
    router.push(`/aicrm/retail-customer/detail/${customerId}`);
  } else if (customerType === 2) {
    // 对公客户详情页
    router.push(`/aicrm/company-customer/detail/${customerId}`);
  } else {
    message.warning('未知的客户类型');
  }
}

/** 批量托管客户 */
function handleBatchDelegate() {
  const checkboxRecords = gridApi.grid.getCheckboxRecords();
  if (checkboxRecords.length === 0) {
    message.warning('请先选择要托管的客户');
    return;
  }

  // 只能托管主办客户
  const nonPrimaryCustomers = checkboxRecords.filter(record => record.assignmentType !== 1);
  if (nonPrimaryCustomers.length > 0) {
    message.warning('只能托管主办客户，请重新选择');
    return;
  }

  const customerIds = checkboxRecords.map(record => record.customerId);
  delegateFormRef.value?.modalApi.setData({ customerIds });
  delegateFormRef.value?.modalApi.open();
}

/** 单个客户托管 */
function handleSingleDelegate(row: AicrmCustomerAssignmentApi.MyCustomerResp) {
  // 只能托管主办客户
  if (row.assignmentType !== 1) {
    message.warning('只能托管主办客户');
    return;
  }

  delegateFormRef.value?.modalApi.setData({ customerIds: [row.customerId] });
  delegateFormRef.value?.modalApi.open();
}

/** 托管成功回调 */
function handleDelegateSuccess() {
  gridApi.query();
  message.success('托管客户成功');
}

/** 批量移交客户 */
function handleBatchTransfer() {
  const checkboxRecords = gridApi.grid.getCheckboxRecords();
  if (checkboxRecords.length === 0) {
    message.warning('请先选择要移交的客户');
    return;
  }

  // 只能移交主办客户
  const nonPrimaryCustomers = checkboxRecords.filter(record => record.assignmentType !== 1);
  if (nonPrimaryCustomers.length > 0) {
    message.warning('只能移交主办客户,请重新选择');
    return;
  }

  const customerIds = checkboxRecords.map(record => record.customerId);
  transferFormRef.value?.modalApi.setData({ customerIds });
  transferFormRef.value?.modalApi.open();
}

/** 单个客户移交 */
function handleSingleTransfer(row: AicrmCustomerAssignmentApi.MyCustomerResp) {
  // 只能移交主办客户
  if (row.assignmentType !== 1) {
    message.warning('只能移交主办客户');
    return;
  }

  transferFormRef.value?.modalApi.setData({ customerIds: [row.customerId] });
  transferFormRef.value?.modalApi.open();
}

/** 移交成功回调 */
function handleTransferSuccess() {
  gridApi.query();
  message.success('移交客户成功');
}

const [Grid, gridApi] = useVbenVxeGrid({
  formOptions: {
    schema: useGridFormSchema(),
  },
  gridOptions: {
    checkboxConfig: {
      reserve: true,
    },
    columns: useGridColumns(),
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }, formValues) => {
          return await getMyCustomerPage({
            pageNo: page.currentPage,
            pageSize: page.pageSize,
            ...formValues,
          });
        },
      },
    },
    rowConfig: {
      keyField: 'assignmentId',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
      search: true,
    },
  } as VxeTableGridOptions<AicrmCustomerAssignmentApi.MyCustomerResp>,
});
</script>

<template>
  <Page auto-content-height>
    <DelegateForm ref="delegateFormRef" @success="handleDelegateSuccess" />
    <TransferForm ref="transferFormRef" @success="handleTransferSuccess" />

    <Grid table-title="我的客户">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '批量托管',
              type: 'primary',
              icon: 'ant-design:swap-outlined',
              auth: ['aicrm:customer-assignment:update'],
              onClick: handleBatchDelegate,
            },
            {
              label: '批量移交',
              type: 'primary',
              icon: 'ant-design:export-outlined',
              auth: ['aicrm:customer-assignment:update'],
              onClick: handleBatchTransfer,
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
              auth: ['aicrm:customer:query'],
              onClick: handleViewDetail.bind(null, row),
            },
            {
              label: '托管',
              type: 'link',
              icon: 'ant-design:swap-outlined',
              auth: ['aicrm:customer-assignment:update'],
              ifShow: row.assignmentType === 1,
              onClick: handleSingleDelegate.bind(null, row),
            },
            {
              label: '移交',
              type: 'link',
              icon: 'ant-design:export-outlined',
              auth: ['aicrm:customer-assignment:update'],
              ifShow: row.assignmentType === 1,
              onClick: handleSingleTransfer.bind(null, row),
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
