<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerContactApi } from '#/api/aicrm/customercontact';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerContactPage } from '#/api/aicrm/customercontact';

defineOptions({
  name: 'RetailCustomerContactInfo',
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

// 格式化时长
function formatDuration(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}分钟`;
}

// 格式化满意度
function formatSatisfaction(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}星`;
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerContactApi.CustomerContact> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'contactNo',
        title: '接触编号',
        width: 150,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'vstTm',
        title: '接触时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'contactType',
        title: '接触类型',
        width: 120,
        formatter: ({ cellValue }) => getDict('aicrm_contact_type', cellValue),
      },
      {
        field: 'contactStatus',
        title: '接触状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_contact_status', cellValue),
      },
      {
        field: 'contactChannel',
        title: '接触渠道',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_contact_channel', cellValue),
      },
      {
        field: 'contactResult',
        title: '接触结果',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_contact_result', cellValue),
      },
      {
        field: 'customerIntention',
        title: '客户意向',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_customer_intention', cellValue),
      },
      {
        field: 'contactSubject',
        title: '接触主题',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'contactPurpose',
        title: '接触目的',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'contactSummary',
        title: '接触摘要',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'contactFeedback',
        title: '接触反馈',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'contactDuration',
        title: '接触时长',
        width: 100,
        formatter: ({ cellValue }) => formatDuration(cellValue),
        align: 'right',
      },
      {
        field: 'contactLocation',
        title: '接触地点',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'accountManager',
        title: '客户经理',
        width: 100,
      },
      {
        field: 'vstPsnLst',
        title: '接触人员',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'cstPsnLst',
        title: '客户人员',
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
        field: 'isNeedFollowup',
        title: '是否需要跟进',
        width: 120,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'followupUserName',
        title: '跟进人',
        width: 100,
      },
      {
        field: 'followupStatus',
        title: '跟进状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_followup_status', cellValue),
      },
      {
        field: 'fuaTm',
        title: '跟进时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'nextContactTime',
        title: '下次接触时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'nextContactPlan',
        title: '下次接触计划',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'relatedActivityName',
        title: '关联营销活动',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'relatedProduct',
        title: '关联产品',
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
        field: 'customerSatisfaction',
        title: '客户满意度',
        width: 100,
        formatter: ({ cellValue }) => formatSatisfaction(cellValue),
        align: 'center',
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
          await getCustomerContactPage({
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
    <Grid table-title="客户接触轨迹信息" />
  </div>
</template>
