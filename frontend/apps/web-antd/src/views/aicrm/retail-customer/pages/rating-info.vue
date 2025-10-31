<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerRatingApi } from '#/api/aicrm/customerrating';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerRatingPage } from '#/api/aicrm/customerrating';

defineOptions({
  name: 'RetailCustomerRatingInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// 获取字典标签
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
}

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  return value;
}

// 格式化分数
function formatScore(value?: number) {
  if (value === null || value === undefined) return '-';
  return value.toFixed(2);
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerRatingApi.CustomerRating> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'ratingDate',
        title: '评级日期',
        width: 110,
        fixed: 'left',
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'valueLevel',
        title: '价值等级',
        width: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_value_level' },
      },
      },
      {
        field: 'serviceLevel',
        title: '服务等级',
        width: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_service_level' },
      },
      },
      {
        field: 'riskLevel',
        title: '风险等级',
        width: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_risk_level' },
      },
      },
      {
        field: 'ratingScore',
        title: '评级分数',
        width: 100,
        formatter: ({ cellValue }) => formatScore(cellValue),
        align: 'right',
      },
      {
        field: 'ratingMethod',
        title: '评级方式',
        width: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_rating_method' },
      },
      },
      {
        field: 'approvalStatus',
        title: '审批状态',
        width: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_rating_approval_status' },
      },
      },
      {
        field: 'effectiveDate',
        title: '生效日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'expiredDate',
        title: '失效日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'nextRatingDate',
        title: '下次评级日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'serviceLevelBeforeRisk',
        title: '剔除风险前服务等级',
        width: 160,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_service_level' },
      },
      },
      {
        field: 'riskFactors',
        title: '风险影响因子',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'ratingModelVersion',
        title: '评级模型版本',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'evaluateDate',
        title: '评估日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'orgName',
        title: '机构名称',
        width: 140,
        showOverflow: true,
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
          await getCustomerRatingPage({
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
    <Grid table-title="客户评级信息" />
  </div>
</template>
