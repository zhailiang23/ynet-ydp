<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerContributionApi } from '#/api/aicrm/customercontribution';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerContributionPage } from '#/api/aicrm/customercontribution';

defineOptions({
  name: 'RetailCustomerContributionInfo',
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

// 格式化百分比
function formatPercent(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}%`;
}

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  return value;
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerContributionApi.CustomerContribution> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'statisticsTime',
        title: '统计时间',
        width: 110,
        fixed: 'left',
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'totalContribution',
        title: '综合贡献度',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'depositContribution',
        title: '存款贡献度',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'loanContribution',
        title: '贷款贡献度',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'intermediateContribution',
        title: '中间业务贡献度',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'wealthManagementContribution',
        title: '理财业务贡献度',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'cardContribution',
        title: '卡业务贡献度',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'settlementContribution',
        title: '结算业务贡献度',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'electronicBankingContribution',
        title: '电子银行贡献度',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'contriBillDiscount',
        title: '票据贴现贡献度',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'contriInternation',
        title: '国际业务贡献度',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'contributionRank',
        title: '贡献度排名',
        width: 100,
        align: 'right',
      },
      {
        field: 'contributionLevel',
        title: '贡献度等级',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_contribution_level', cellValue),
      },
      {
        field: 'yearOverYearGrowth',
        title: '同比增长率',
        width: 100,
        formatter: ({ cellValue }) => formatPercent(cellValue),
        align: 'right',
      },
      {
        field: 'monthOverMonthGrowth',
        title: '环比增长率',
        width: 100,
        formatter: ({ cellValue }) => formatPercent(cellValue),
        align: 'right',
      },
      {
        field: 'statisticsPeriod',
        title: '统计周期',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_statistics_period', cellValue),
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
          await getCustomerContributionPage({
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
    <Grid table-title="客户贡献度信息" />
  </div>
</template>
