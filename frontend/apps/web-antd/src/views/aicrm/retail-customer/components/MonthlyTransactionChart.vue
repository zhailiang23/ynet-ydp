<script setup lang="ts">
import type { EchartsUIType } from '@vben/plugins/echarts';

import { computed, ref, watch } from 'vue';
import { Card } from 'ant-design-vue';
import { EchartsUI, useEcharts } from '@vben/plugins/echarts';
import type { EChartsOption } from 'echarts';
import type { RetailCustomerOverviewApi } from '#/api/aicrm/retail-customer';

interface Props {
  transactionData: RetailCustomerOverviewApi.MonthlyTransaction[];
}

const props = defineProps<Props>();

const chartRef = ref<EchartsUIType>();
const { renderEcharts } = useEcharts(chartRef);

// 构建图表配置
const chartOptions = computed<EChartsOption>(() => {
  const months = props.transactionData.map((item) => item.month);
  const counts = props.transactionData.map((item) => item.transactionCount);
  const amounts = props.transactionData.map((item) => item.transactionAmount);

  return {
    tooltip: {
      trigger: 'axis',
      axisPointer: {
        type: 'cross',
        crossStyle: {
          color: '#999',
        },
      },
      formatter: (params: any) => {
        let result = `<div style="padding: 8px;">${params[0].axisValue}</div>`;
        params.forEach((item: any) => {
          if (item.seriesName === '交易金额') {
            const value = (Number(item.value) / 10000).toFixed(2);
            result += `<div style="padding: 4px 8px;">
              ${item.marker} ${item.seriesName}: <strong>${value}万</strong>
            </div>`;
          } else {
            result += `<div style="padding: 4px 8px;">
              ${item.marker} ${item.seriesName}: <strong>${item.value}笔</strong>
            </div>`;
          }
        });
        return result;
      },
    },
    legend: {
      data: ['交易笔数', '交易金额'],
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
        rotate: 30,
      },
    },
    yAxis: [
      {
        type: 'value',
        name: '交易笔数',
        position: 'left',
        axisLabel: {
          formatter: '{value} 笔',
          color: '#666',
        },
        axisLine: {
          lineStyle: {
            color: '#1890ff',
          },
        },
        splitLine: {
          lineStyle: {
            color: '#f0f0f0',
          },
        },
      },
      {
        type: 'value',
        name: '金额(万)',
        position: 'right',
        axisLabel: {
          formatter: (value: number) => {
            return (value / 10000).toFixed(0);
          },
          color: '#666',
        },
        axisLine: {
          lineStyle: {
            color: '#52c41a',
          },
        },
        splitLine: {
          show: false,
        },
      },
    ],
    series: [
      {
        name: '交易笔数',
        type: 'line',
        smooth: true,
        data: counts,
        itemStyle: {
          color: '#1890ff',
        },
        lineStyle: {
          width: 2,
        },
        symbol: 'circle',
        symbolSize: 6,
      },
      {
        name: '交易金额',
        type: 'line',
        smooth: true,
        yAxisIndex: 1,
        data: amounts,
        itemStyle: {
          color: '#52c41a',
        },
        lineStyle: {
          width: 2,
        },
        symbol: 'circle',
        symbolSize: 6,
      },
    ],
  };
});

// 监听数据变化更新图表
watch(
  () => props.transactionData,
  () => {
    renderEcharts(chartOptions.value);
  },
  { immediate: true, deep: true },
);
</script>

<template>
  <Card :bordered="false" class="chart-card">
    <template #title>
      <span class="text-sm font-semibold">月度交易趋势</span>
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
