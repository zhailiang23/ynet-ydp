<script setup lang="ts">
import type { EchartsUIType } from '@vben/plugins/echarts';

import { computed, ref, watch } from 'vue';
import { Card } from 'ant-design-vue';
import { EchartsUI, useEcharts } from '@vben/plugins/echarts';
import type { EChartsOption } from 'echarts';
import type { RetailCustomerOverviewApi } from '#/api/aicrm/retail-customer';

interface Props {
  trendData: RetailCustomerOverviewApi.AssetTrend[];
}

const props = defineProps<Props>();

const chartRef = ref<EchartsUIType>();
const { renderEcharts } = useEcharts(chartRef);

// 构建图表配置
const chartOptions = computed<EChartsOption>(() => {
  const months = props.trendData.map((item) => item.month);
  const totalAssets = props.trendData.map((item) => item.totalAssets);
  const deposits = props.trendData.map((item) => item.deposits);
  const wealth = props.trendData.map((item) => item.wealth);
  const loans = props.trendData.map((item) => item.loans);

  return {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        label: {
          backgroundColor: '#6a7985',
        },
      },
      formatter: (params: any) => {
        let result = `<div style="padding: 8px;">${params[0].axisValue}</div>`;
        params.forEach((item: any) => {
          const value = (Number(item.value) / 10000).toFixed(2);
          result += `<div style="padding: 4px 8px;">
            ${item.marker} ${item.seriesName}: <strong>${value}万</strong>
          </div>`;
        });
        return result;
      },
    },
    legend: {
      data: ['总资产', '存款', '理财', '贷款'],
      top: 10,
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      top: 50,
      containLabel: true,
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: months,
      axisLine: {
        lineStyle: {
          color: '#ddd',
        },
      },
      axisLabel: {
        color: '#666',
      },
    },
    yAxis: {
      type: 'value',
      name: '金额(万元)',
      axisLabel: {
        formatter: (value: number) => {
          return (value / 10000).toFixed(0);
        },
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
        name: '总资产',
        type: 'line',
        smooth: true,
        data: totalAssets,
        itemStyle: {
          color: '#1890ff',
        },
        areaStyle: {
          color: {
            type: 'linear',
            x: 0,
            y: 0,
            x2: 0,
            y2: 1,
            colorStops: [
              { offset: 0, color: 'rgba(24, 144, 255, 0.3)' },
              { offset: 1, color: 'rgba(24, 144, 255, 0)' },
            ],
          },
        },
      },
      {
        name: '存款',
        type: 'line',
        smooth: true,
        data: deposits,
        itemStyle: {
          color: '#52c41a',
        },
      },
      {
        name: '理财',
        type: 'line',
        smooth: true,
        data: wealth,
        itemStyle: {
          color: '#722ed1',
        },
      },
      {
        name: '贷款',
        type: 'line',
        smooth: true,
        data: loans,
        itemStyle: {
          color: '#fa541c',
        },
      },
    ],
  };
});

// 监听数据变化更新图表
watch(
  () => props.trendData,
  () => {
    renderEcharts(chartOptions.value);
  },
  { immediate: true, deep: true },
);
</script>

<template>
  <Card :bordered="false" class="chart-card h-[420px]">
    <template #title>
      <span class="text-sm font-semibold">资产趋势</span>
    </template>
    <EchartsUI ref="chartRef" class="h-full" />
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
