<script setup lang="ts">
import type { EchartsUIType } from '@vben/plugins/echarts';

import { computed, ref, watch } from 'vue';
import { Card } from 'ant-design-vue';
import { EchartsUI, useEcharts } from '@vben/plugins/echarts';
import type { EChartsOption } from 'echarts';
import type { RetailCustomerOverviewApi } from '#/api/aicrm/retail-customer';

interface Props {
  structure: RetailCustomerOverviewApi.AssetStructure;
}

const props = defineProps<Props>();

const chartRef = ref<EchartsUIType>();
const { renderEcharts } = useEcharts(chartRef);

// 构建图表配置
const chartOptions = computed<EChartsOption>(() => {
  const data = [
    { name: '存款', value: props.structure.depositAmount },
    { name: '理财', value: props.structure.wealthAmount },
    { name: '基金', value: props.structure.fundAmount },
    { name: '保险', value: props.structure.insuranceAmount },
    { name: '贵金属', value: props.structure.metalAmount },
    { name: '信托', value: props.structure.trustAmount },
    { name: '其他', value: props.structure.otherAmount },
  ].filter((item) => item.value > 0);

  return {
    tooltip: {
      trigger: 'item',
      formatter: (params: any) => {
        const value = (Number(params.value) / 10000).toFixed(2);
        return `${params.marker} ${params.name}: <strong>${value}万</strong> (${params.percent}%)`;
      },
    },
    legend: {
      orient: 'vertical',
      right: 10,
      top: 'center',
      textStyle: {
        color: '#666',
      },
    },
    series: [
      {
        name: '资产结构',
        type: 'pie',
        radius: ['40%', '70%'],
        center: ['40%', '50%'],
        avoidLabelOverlap: false,
        itemStyle: {
          borderRadius: 10,
          borderColor: '#fff',
          borderWidth: 2,
        },
        label: {
          show: false,
          position: 'center',
        },
        emphasis: {
          label: {
            show: true,
            fontSize: 20,
            fontWeight: 'bold',
            formatter: (params: any) => {
              const value = (Number(params.value) / 10000).toFixed(2);
              return `{name|${params.name}}\n{value|${value}万}`;
            },
            rich: {
              name: {
                fontSize: 14,
                color: '#666',
                padding: [0, 0, 5, 0],
              },
              value: {
                fontSize: 20,
                fontWeight: 'bold',
                color: '#333',
              },
            },
          },
        },
        labelLine: {
          show: false,
        },
        data,
        color: ['#1890ff', '#722ed1', '#52c41a', '#faad14', '#13c2c2', '#fa541c', '#eb2f96'],
      },
    ],
  };
});

// 监听数据变化更新图表
watch(
  () => props.structure,
  () => {
    renderEcharts(chartOptions.value);
  },
  { immediate: true, deep: true },
);
</script>

<template>
  <Card :bordered="false" class="chart-card">
    <template #title>
      <span class="text-sm font-semibold">资产结构分布</span>
    </template>
    <EchartsUI ref="chartRef" class="h-60" />
  </Card>
</template>

<style scoped>
.chart-card {
  margin-bottom: 12px;
}

.dark .chart-card {
  background-color: rgb(28 30 35);
}
</style>
