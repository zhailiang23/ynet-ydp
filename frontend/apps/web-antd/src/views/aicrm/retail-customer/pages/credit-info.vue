<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerCreditApi } from '#/api/aicrm/customercredit';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerCreditPage } from '#/api/aicrm/customercredit';

defineOptions({
  name: 'RetailCustomerCreditInfo',
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
  if (value === '9999-12-31') return '长期有效';
  return value;
}

// 格式化百分比
function formatPercent(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value.toFixed(2)}%`;
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerCreditApi.CustomerCredit> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'creditAgreementNo',
        title: '授信协议号',
        width: 160,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'creditProductType',
        title: '授信品种',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_credit_product_type', cellValue),
      },
      {
        field: 'creditType',
        title: '授信类型',
        width: 100,
        formatter: ({ cellValue }) => getDict('aicrm_credit_type', cellValue),
      },
      {
        field: 'creditLimit',
        title: '授信额度',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'usedLimit',
        title: '已用额度',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'availableLimit',
        title: '可用额度',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'usageRatio',
        title: '使用比例',
        width: 100,
        formatter: ({ cellValue }) => formatPercent(cellValue),
        align: 'right',
      },
      {
        field: 'currencyCode',
        title: '币种',
        width: 70,
        formatter: ({ cellValue }) => getDict('aicrm_currency_type', cellValue),
      },
      {
        field: 'creditStartDate',
        title: '授信起始日',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'creditEndDate',
        title: '授信到期日',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'creditStatus',
        title: '授信状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_credit_status', cellValue),
      },
      {
        field: 'approveDate',
        title: '审批日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'approveAmount',
        title: '审批金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'interestRate',
        title: '授信利率(%)',
        width: 100,
        formatter: ({ cellValue }) => formatPercent(cellValue),
        align: 'right',
      },
      {
        field: 'guaranteeType',
        title: '担保方式',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_guarantee_type', cellValue),
      },
      {
        field: 'creditPurpose',
        title: '授信用途',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'statisticsDate',
        title: '统计日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerCreditPage({
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
    <Grid table-title="客户授信信息" />
  </div>
</template>
