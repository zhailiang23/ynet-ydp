<script setup lang="ts">
import type { EchartsUIType } from '@vben/plugins/echarts';

import { computed, ref, watch } from 'vue';
import { Card } from 'ant-design-vue';
import { EchartsUI, useEcharts } from '@vben/plugins/echarts';
import type { EChartsOption } from 'echarts';
import type { RetailCustomerOverviewApi } from '#/api/aicrm/retail-customer';

interface Props {
  holdingData: RetailCustomerOverviewApi.ProductHoldingTrend[];
}

const props = defineProps<Props>();

const chartRef = ref<EchartsUIType>();
const { renderEcharts } = useEcharts(chartRef);

// 构建图表配置
const chartOptions = computed<EChartsOption>(() => {
  const categories = props.holdingData.map((item) => item.productType);
  const counts = props.holdingData.map((item) => item.productCount);

  return {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'shadow',
      },
      formatter: (params: any) => {
        const item = params[0];
        return `${item.marker} ${item.name}: <strong>${item.value} 个</strong>`;
      },
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      top: 30,
      containLabel: true,
    },
    xAxis: {
      type: 'category',
      data: categories,
      axisLine: {
        lineStyle: {
          color: '#ddd',
        },
      },
      axisLabel: {
        color: '#666',
        interval: 0,
        rotate: 30,
      },
    },
    yAxis: {
      type: 'value',
      name: '持有数量',
      axisLabel: {
        formatter: '{value} 个',
        color: '#666',
      },
      axisLine: {
        lineStyle: {
          color: '#ddd',
        },
      },
      splitLine: {
        lineStyle: {
          color: '#f0f0f0',
        },
      },
    },
    series: [
      {
        name: '产品数量',
        type: 'bar',
        data: counts,
        itemStyle: {
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [
              { offset: 0, color: '#1890ff' },
              { offset: 1, color: '#36cfc9' },
            ],
          },
          borderRadius: [4, 4, 0, 0],
        },
        emphasis: {
          itemStyle: {
            color: {
              type: 'linear',
              x: 0,
              y: 0,
              x2: 0,
              y2: 1,
              colorStops: [
                { offset: 0, color: '#40a9ff' },
                { offset: 1, color: '#5cdbd3' },
              ],
            },
          },
        },
        label: {
          show: true,
          position: 'top',
          formatter: '{c} 个',
          color: '#666',
          fontSize: 11,
        },
      },
    ],
  };
});

// 监听数据变化更新图表
watch(
  () => props.holdingData,
  () => {
    renderEcharts(chartOptions.value);
  },
  { immediate: true, deep: true },
);
</script>

<template>
  <Card :bordered="false" class="chart-card">
    <template #title>
      <span class="text-sm font-semibold">产品持有分布</span>
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
