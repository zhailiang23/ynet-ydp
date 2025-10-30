<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerTransactionMockApi } from '#/api/aicrm/customertransactionmock';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerTransactionMockPage } from '#/api/aicrm/customertransactionmock';

defineOptions({
  name: 'RetailCustomerTransactionInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// 获取字典标签
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
}

// 格式化金额
function formatMoney(value?: number) {
  if (value === null || value === undefined) return '-';
  return value.toLocaleString('zh-CN', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  });
}

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  return value;
}

// 格式化时间
function formatTime(value?: string) {
  if (!value) return '-';
  return value;
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerTransactionMockApi.CustomerTransactionMock> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'transactionDate',
        title: '交易日期',
        width: 110,
        fixed: 'left',
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'transactionTime',
        title: '交易时间',
        width: 100,
        formatter: ({ cellValue }) => formatTime(cellValue),
      },
      {
        field: 'tansNo',
        title: '交易流水号',
        width: 180,
        showOverflow: true,
      },
      {
        field: 'accountNo',
        title: '账户编号',
        width: 180,
        showOverflow: true,
      },
      {
        field: 'tradType',
        title: '交易类型',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_transaction_type', cellValue),
      },
      {
        field: 'direction',
        title: '交易方向',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_transaction_direction', cellValue),
      },
      {
        field: 'tradMoney',
        title: '交易金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'acctBal',
        title: '账户余额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'currencyCode',
        title: '币种',
        width: 70,
        formatter: ({ cellValue }) => getDict('aicrm_currency_type', cellValue),
      },
      {
        field: 'transactionStatus',
        title: '交易状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_transaction_status', cellValue),
      },
      {
        field: 'tradAbs',
        title: '交易摘要',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'tradChn',
        title: '交易渠道',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_transaction_channel', cellValue),
      },
      {
        field: 'branchName',
        title: '交易机构',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'advsAcct',
        title: '对方账号',
        width: 180,
        showOverflow: true,
      },
      {
        field: 'advsAcctName',
        title: '对方户名',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'cost',
        title: '手续费',
        width: 100,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'cashFlag',
        title: '现转标志',
        width: 100,
        formatter: ({ cellValue }) => getDict('aicrm_cash_flag', cellValue),
      },
      {
        field: 'tradTeller',
        title: '交易柜员',
        width: 100,
        showOverflow: true,
      },
      {
        field: 'handler',
        title: '经办人',
        width: 100,
        showOverflow: true,
      },
      {
        field: 'accountinDate',
        title: '记账日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'remark',
        title: '备注',
        width: 140,
        showOverflow: true,
      },
    ],
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerTransactionMockPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    pagerConfig: {
      pageSize: 10,
      pageSizes: [10, 20, 50, 100],
    },
    toolbarConfig: {
      refresh: true,
      export: false,
      print: false,
    },
  };

const [Grid, gridApi] = useVbenVxeGrid({ gridOptions });

// 组件挂载时加载数据
onMounted(() => {
  setTimeout(() => {
    gridApi.query();
  }, 100);
});

// 监听 customerId 变化，重新加载数据
watch(
  () => props.customerId,
  () => {
    gridApi.query();
  },
);
</script>

<template>
  <div>
    <Grid table-title="客户交易明细信息" />
  </div>
</template>
