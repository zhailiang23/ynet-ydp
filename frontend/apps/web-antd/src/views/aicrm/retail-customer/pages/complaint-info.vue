<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerComplaintApi } from '#/api/aicrm/customercomplaint';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerComplaintPage } from '#/api/aicrm/customercomplaint';

defineOptions({
  name: 'RetailCustomerComplaintInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// 获取字典标签
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
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
const gridOptions: VxeTableGridOptions<AicrmCustomerComplaintApi.CustomerComplaint> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'workOrderNo',
        title: '工单编号',
        width: 150,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'complaintTime',
        title: '投诉时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'complaintType',
        title: '投诉类型',
        width: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_complaint_type' },
      },
      },
      {
        field: 'complaintLevel',
        title: '投诉级别',
        width: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_complaint_level' },
      },
      },
      {
        field: 'complaintChannel',
        title: '投诉渠道',
        width: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_complaint_channel' },
      },
      },
      {
        field: 'workOrderStatus',
        title: '工单状态',
        width: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_complaint_status' },
      },
      },
      {
        field: 'complaintContent',
        title: '投诉内容',
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
        field: 'isReopened',
        title: '是否重新打开',
        width: 120,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'reopenTime',
        title: '重新打开时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'reopenReason',
        title: '重新打开原因',
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
          await getCustomerComplaintPage({
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
    <Grid table-title="客户投诉信息" />
  </div>
</template>
