<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerReminderApi } from '#/api/aicrm/customerreminder';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerReminderPage } from '#/api/aicrm/customerreminder';

defineOptions({
  name: 'RetailCustomerReminderInfo',
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
const gridOptions: VxeTableGridOptions<AicrmCustomerReminderApi.CustomerReminder> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'reminderNo',
        title: '提醒编号',
        width: 150,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'reminderGenerateDate',
        title: '生成日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'reminderDueDate',
        title: '到期日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'reminderType',
        title: '提醒类型',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_reminder_type', cellValue),
      },
      {
        field: 'reminderLevel',
        title: '提醒级别',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_reminder_level', cellValue),
      },
      {
        field: 'reminderStatus',
        title: '提醒状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_reminder_status', cellValue),
      },
      {
        field: 'reminderSource',
        title: '提醒来源',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_reminder_source', cellValue),
      },
      {
        field: 'reminderCategoryName',
        title: '提醒类别',
        width: 120,
        showOverflow: true,
      },
      {
        field: 'reminderContent',
        title: '提醒内容',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'isSent',
        title: '是否已发送',
        width: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'sendTime',
        title: '发送时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'sendChannel',
        title: '发送渠道',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_reminder_channel', cellValue),
      },
      {
        field: 'recipientUserName',
        title: '接收人',
        width: 100,
      },
      {
        field: 'recipientDeptName',
        title: '接收部门',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'handlerUserName',
        title: '处理人',
        width: 100,
      },
      {
        field: 'handleTime',
        title: '处理时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'handleResult',
        title: '处理结果',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'isRepeat',
        title: '是否重复提醒',
        width: 120,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'repeatRule',
        title: '重复规则',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'nextRemindTime',
        title: '下次提醒时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'isRead',
        title: '是否已读',
        width: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'readTime',
        title: '阅读时间',
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
        field: 'relatedBusinessType',
        title: '关联业务类型',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_business_type', cellValue),
      },
      {
        field: 'expireTime',
        title: '过期时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'isExpired',
        title: '是否已过期',
        width: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
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
          await getCustomerReminderPage({
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
    <Grid table-title="客户提醒信息" />
  </div>
</template>
