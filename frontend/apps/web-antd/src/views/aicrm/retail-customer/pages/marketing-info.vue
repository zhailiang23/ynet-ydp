<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerMarketingActivityApi } from '#/api/aicrm/customermarketingactivity';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerMarketingActivityPage } from '#/api/aicrm/customermarketingactivity';

defineOptions({
  name: 'RetailCustomerMarketingInfo',
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

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  return value;
}

// 格式化布尔值
function formatBoolean(value?: boolean) {
  if (value === null || value === undefined) return '-';
  return value ? '是' : '否';
}

// 格式化评分
function formatScore(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}星`;
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerMarketingActivityApi.CustomerMarketingActivity> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'activityNo',
        title: '活动编号',
        width: 150,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'activityName',
        title: '活动名称',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'activityType',
        title: '活动类型',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_activity_type', cellValue),
      },
      {
        field: 'activityForm',
        title: '活动形式',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_activity_form', cellValue),
      },
      {
        field: 'activityStatus',
        title: '活动状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_activity_status', cellValue),
      },
      {
        field: 'approvalStatus',
        title: '审批状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_approval_status', cellValue),
      },
      {
        field: 'startDt',
        title: '开始日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'endDt',
        title: '结束日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'activityLocation',
        title: '活动地点',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'activityBudget',
        title: '活动预算',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'activityCost',
        title: '实际费用',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'targetCustomerCount',
        title: '目标客户数',
        width: 100,
        align: 'right',
      },
      {
        field: 'actualCustomerCount',
        title: '实际参与数',
        width: 100,
        align: 'right',
      },
      {
        field: 'handlerUserName',
        title: '负责人',
        width: 100,
      },
      {
        field: 'handlerDeptName',
        title: '负责部门',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'approverUserName',
        title: '审批人',
        width: 100,
      },
      {
        field: 'approvalTime',
        title: '审批时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'expectedEffect',
        title: '预期效果',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'actualEffect',
        title: '实际效果',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'activityScore',
        title: '活动评分',
        width: 100,
        formatter: ({ cellValue }) => formatScore(cellValue),
        align: 'center',
      },
      {
        field: 'customerFeedback',
        title: '客户反馈',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'isSendSms',
        title: '是否发送短信',
        width: 120,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'smsSendTime',
        title: '短信发送时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'isCancelled',
        title: '是否已取消',
        width: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'cancelTime',
        title: '取消时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'cancelReason',
        title: '取消原因',
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
          await getCustomerMarketingActivityPage({
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
    <Grid table-title="客户营销活动信息" />
  </div>
</template>
