<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerProductHoldingApi } from '#/api/aicrm/customerproductholding';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerProductHoldingPage } from '#/api/aicrm/customerproductholding';

defineOptions({
  name: 'RetailCustomerProductHolding',
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

// 格式化利率
function formatRate(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}%`;
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerProductHoldingApi.CustomerProductHolding> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'productName',
        title: '产品名称',
        width: 180,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'relatedAccountType',
        title: '账户类型',
        width: 100,
        formatter: ({ cellValue }) => {
          const typeMap: Record<string, string> = {
            deposit: '存款',
            loan: '贷款',
            wealth: '理财',
            fund: '基金',
            insurance: '保险',
            trust: '信托',
            metal: '贵金属',
            creditcard: '信用卡',
          };
          return typeMap[cellValue] || cellValue;
        },
      },
      {
        field: 'accountNo',
        title: '账号',
        width: 160,
        showOverflow: true,
      },
      {
        field: 'contractNo',
        title: '合同编号',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'holdingAmount',
        title: '持有金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'interestRate',
        title: '利率(%)',
        width: 90,
        formatter: ({ cellValue }) => formatRate(cellValue),
        align: 'right',
      },
      {
        field: 'currencyCode',
        title: '币种',
        width: 70,
        formatter: ({ cellValue }) => getDict('aicrm_currency_type', cellValue),
      },
      {
        field: 'openDate',
        title: '开户日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'matureDate',
        title: '到期日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'holdingStatus',
        title: '持有状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_holding_status', cellValue),
      },
      {
        field: 'branchName',
        title: '开户网点',
        width: 140,
        showOverflow: true,
      },
    ],
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerProductHoldingPage({
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
  <div class="p-4">
    <Grid />
  </div>
</template>
