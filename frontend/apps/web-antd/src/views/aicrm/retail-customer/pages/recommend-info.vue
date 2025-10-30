<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerProductRecommendationApi } from '#/api/aicrm/customerproductrecommendation';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerProductRecommendationPage } from '#/api/aicrm/customerproductrecommendation';

defineOptions({
  name: 'RetailCustomerRecommendInfo',
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
  return `${value.toLocaleString('zh-CN', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  })} 元`;
}

// 格式化百分比
function formatPercent(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}%`;
}

// 格式化评分
function formatScore(value?: number) {
  if (value === null || value === undefined) return '-';
  return value.toFixed(2);
}

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  return value;
}

// 格式化时间
function formatDateTime(value?: string) {
  if (!value) return '-';
  return value;
}

// 格式化布尔值
function formatBoolean(value?: boolean) {
  if (value === null || value === undefined) return '-';
  return value ? '是' : '否';
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerProductRecommendationApi.CustomerProductRecommendation> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'recommendationNo',
        title: '推荐编号',
        width: 150,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'productName',
        title: '产品名称',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'productCode',
        title: '产品编号',
        width: 150,
        showOverflow: true,
      },
      {
        field: 'productType',
        title: '产品类型',
        width: 120,
        formatter: ({ cellValue }) => getDict('aicrm_product_type', cellValue),
      },
      {
        field: 'recommendationType',
        title: '推荐类型',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_recommendation_type', cellValue),
      },
      {
        field: 'recommendationSource',
        title: '推荐来源',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_recommendation_source', cellValue),
      },
      {
        field: 'recommendationScore',
        title: '推荐评分',
        width: 100,
        formatter: ({ cellValue }) => formatScore(cellValue),
        align: 'right',
      },
      {
        field: 'matchDegree',
        title: '匹配度',
        width: 100,
        formatter: ({ cellValue }) => getDict('aicrm_match_degree', cellValue),
      },
      {
        field: 'recommendationStatus',
        title: '推荐状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_recommendation_status', cellValue),
      },
      {
        field: 'recommendationDate',
        title: '推荐日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'recommendationReason',
        title: '推荐理由',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'validStartDate',
        title: '有效开始日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'validEndDate',
        title: '有效结束日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'isHotSale',
        title: '是否热销产品',
        width: 120,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'isMatchCustomer',
        title: '是否匹配客户',
        width: 120,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'pushTime',
        title: '推送时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'pushChannel',
        title: '推送渠道',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_push_channel', cellValue),
      },
      {
        field: 'viewTime',
        title: '查看时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'viewCount',
        title: '查看次数',
        width: 100,
        align: 'right',
      },
      {
        field: 'clickTime',
        title: '点击时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'clickCount',
        title: '点击次数',
        width: 100,
        align: 'right',
      },
      {
        field: 'applyTime',
        title: '申请时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'purchaseTime',
        title: '购买时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'purchaseAmount',
        title: '购买金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'rejectTime',
        title: '拒绝时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'rejectReason',
        title: '拒绝原因',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'recommenderUserName',
        title: '推荐人',
        width: 100,
      },
      {
        field: 'recommenderDeptName',
        title: '推荐部门',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'interestRate',
        title: '利率/收益率',
        width: 110,
        formatter: ({ cellValue }) => formatPercent(cellValue),
        align: 'right',
      },
      {
        field: 'termDays',
        title: '期限(天)',
        width: 100,
        align: 'right',
      },
      {
        field: 'minAmount',
        title: '起购金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'maxAmount',
        title: '最大金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'riskLevel',
        title: '风险等级',
        width: 100,
        formatter: ({ cellValue }) => getDict('aicrm_risk_level', cellValue),
      },
      {
        field: 'productFeatures',
        title: '产品特点',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'productAdvantage',
        title: '产品优势',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'marketingTheme',
        title: '营销主题',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'conversionRate',
        title: '转化率',
        width: 100,
        formatter: ({ cellValue }) => formatPercent(cellValue),
        align: 'right',
      },
      {
        field: 'isEffective',
        title: '是否有效',
        width: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'effectivenessScore',
        title: '有效性评分',
        width: 100,
        formatter: ({ cellValue }) => formatScore(cellValue),
        align: 'right',
      },
      {
        field: 'feedbackContent',
        title: '反馈内容',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'feedbackTime',
        title: '反馈时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'priority',
        title: '优先级',
        width: 80,
        align: 'right',
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
          await getCustomerProductRecommendationPage({
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
    <Grid table-title="产品推荐信息" />
  </div>
</template>
