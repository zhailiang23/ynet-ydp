<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerProductHoldingApi } from '#/api/aicrm/customerproductholding';

import { watch } from 'vue';

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
    height: 'auto',
    columns: [
      { type: 'seq', width: 50, title: '序号' },
      {
        field: 'productName',
        title: '产品名称',
        minWidth: 150,
      },
      {
        field: 'accountNo',
        title: '账号',
        minWidth: 150,
      },
      {
        field: 'receiptNo',
        title: '借据编号',
        minWidth: 150,
      },
      {
        field: 'contractNo',
        title: '合同编号',
        minWidth: 150,
      },
      {
        field: 'holdingAmount',
        title: '持有金额',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'originalAmount',
        title: '原始金额',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'interestRate',
        title: '利率(%)',
        minWidth: 100,
        formatter: ({ cellValue }) => formatRate(cellValue),
        align: 'right',
      },
      {
        field: 'currencyCode',
        title: '币种',
        minWidth: 80,
        formatter: ({ cellValue }) => getDict('aicrm_currency_type', cellValue),
      },
      {
        field: 'loanDate',
        title: '放款日期',
        minWidth: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'openDate',
        title: '开户日期',
        minWidth: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'matureDate',
        title: '到期日期',
        minWidth: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'contractDate',
        title: '合同日期',
        minWidth: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'branchName',
        title: '开户网点',
        minWidth: 120,
      },
      {
        field: 'holdingStatus',
        title: '持有状态',
        minWidth: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_holding_status', cellValue),
      },
      {
        field: 'relatedAccountType',
        title: '账户类型',
        minWidth: 100,
        formatter: ({ cellValue }) => {
          const typeMap: Record<string, string> = {
            deposit: '存款账户',
            loan: '贷款账户',
            wealth: '理财账户',
            fund: '基金账户',
            insurance: '保险账户',
            trust: '信托账户',
            metal: '贵金属账户',
            creditcard: '信用卡账户',
          };
          return typeMap[cellValue] || cellValue;
        },
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 150,
      },
    ],
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          try {
            const response = await getCustomerProductHoldingPage({
              pageNo: page.currentPage,
              pageSize: page.pageSize,
              customerId: props.customerId,
            });
            return {
              result: response.list,
              page: {
                total: response.total,
              },
            };
          } catch (error) {
            console.error('加载产品持有信息失败:', error);
            message.error('加载产品持有信息失败');
            return {
              result: [],
              page: { total: 0 },
            };
          }
        },
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
