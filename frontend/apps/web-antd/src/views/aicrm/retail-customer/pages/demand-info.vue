<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerDemandApi } from '#/api/aicrm/customerdemand';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerDemandPage } from '#/api/aicrm/customerdemand';

defineOptions({
  name: 'RetailCustomerDemandInfo',
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

// 格式化满意度
function formatSatisfaction(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}星`;
}

// 格式化时长
function formatDuration(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}分钟`;
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerDemandApi.CustomerDemand> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'demandNo',
        title: '需求编号',
        width: 150,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'submitTime',
        title: '提交时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'demandType',
        title: '需求类型',
        width: 120,
        formatter: ({ cellValue }) => getDict('aicrm_demand_type', cellValue),
      },
      {
        field: 'demandStatus',
        title: '需求状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_demand_status', cellValue),
      },
      {
        field: 'demandPriority',
        title: '需求优先级',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_demand_priority', cellValue),
      },
      {
        field: 'demandChannel',
        title: '需求渠道',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_demand_channel', cellValue),
      },
      {
        field: 'demandSource',
        title: '需求来源',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_demand_source', cellValue),
      },
      {
        field: 'demandContent',
        title: '需求内容',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'handlerUserName',
        title: '处理人',
        width: 100,
      },
      {
        field: 'handlerDeptName',
        title: '处理部门',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'processStartTime',
        title: '开始处理时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'processEndTime',
        title: '处理完成时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'processDuration',
        title: '处理时长',
        width: 100,
        formatter: ({ cellValue }) => formatDuration(cellValue),
        align: 'right',
      },
      {
        field: 'processResult',
        title: '处理结果',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'customerSatisfaction',
        title: '客户满意度',
        width: 100,
        formatter: ({ cellValue }) => formatSatisfaction(cellValue),
        align: 'center',
      },
      {
        field: 'satisfactionFeedback',
        title: '满意度反馈',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'followUpRequired',
        title: '是否需要跟进',
        width: 120,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'followUpTime',
        title: '跟进时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'followUpContent',
        title: '跟进内容',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'relatedProduct',
        title: '相关产品',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'expectedAmount',
        title: '预期金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'actualAmount',
        title: '实际成交金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'isConverted',
        title: '是否已转化',
        width: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'convertTime',
        title: '转化时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'isClosed',
        title: '是否已关闭',
        width: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'closeTime',
        title: '关闭时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'closeReason',
        title: '关闭原因',
        width: 200,
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
          await getCustomerDemandPage({
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
    <Grid table-title="客户需求信息" />
  </div>
</template>
