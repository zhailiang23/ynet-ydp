<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerChannelBehaviorApi } from '#/api/aicrm/customerchannelbehavior';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerChannelBehaviorPage } from '#/api/aicrm/customerchannelbehavior';

defineOptions({
  name: 'RetailCustomerChannelBehaviorInfo',
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

// 格式化金额
function formatMoney(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value.toLocaleString('zh-CN', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  })} 元`;
}

// 格式化百分比
function formatPercent(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}%`;
}

// 格式化时长
function formatSeconds(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value}秒`;
}

// 表格配置
const gridOptions: VxeTableGridOptions<AicrmCustomerChannelBehaviorApi.CustomerChannelBehavior> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'behaviorNo',
        title: '行为编号',
        width: 150,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'operationTime',
        title: '操作时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'channelType',
        title: '渠道类型',
        width: 120,
        showOverflow: true,
      },
      {
        field: 'operationAction',
        title: '操作行为',
        width: 100,
        showOverflow: true,
      },
      {
        field: 'operationObject',
        title: '操作对象',
        width: 100,
        showOverflow: true,
      },
      {
        field: 'currentPageName',
        title: '当前页面名称',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'currentPageCode',
        title: '当前页面Code',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'currentPageIdentifier',
        title: '当前页面标识',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'pageStaySeconds',
        title: '页面停留时间',
        width: 120,
        formatter: ({ cellValue }) => formatSeconds(cellValue),
        align: 'right',
      },
      {
        field: 'deviceType',
        title: '设备类型',
        width: 100,
        showOverflow: true,
      },
      {
        field: 'deviceModel',
        title: '设备型号',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'appVersion',
        title: 'APP版本号',
        width: 100,
        showOverflow: true,
      },
      {
        field: 'osVersion',
        title: '操作系统版本',
        width: 120,
        showOverflow: true,
      },
      {
        field: 'browserType',
        title: '浏览器类型',
        width: 120,
        showOverflow: true,
      },
      {
        field: 'browserVersion',
        title: '浏览器版本',
        width: 120,
        showOverflow: true,
      },
      {
        field: 'ipAddress',
        title: 'IP地址',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'ipLocation',
        title: 'IP归属地',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'networkType',
        title: '网络类型',
        width: 100,
        showOverflow: true,
      },
      {
        field: 'sessionId',
        title: '会话ID',
        width: 180,
        showOverflow: true,
      },
      {
        field: 'sessionStartTime',
        title: '会话开始时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'sessionEndTime',
        title: '会话结束时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'previousPageName',
        title: '上一页面名称',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'nextPageName',
        title: '下一页面名称',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'pageUrl',
        title: '页面URL',
        width: 300,
        showOverflow: true,
      },
      {
        field: 'pageTitle',
        title: '页面标题',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'operationResult',
        title: '操作结果',
        width: 100,
        showOverflow: true,
      },
      {
        field: 'operationDetail',
        title: '操作详情',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'businessType',
        title: '业务类型',
        width: 120,
        showOverflow: true,
      },
      {
        field: 'businessModule',
        title: '业务模块',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'businessFunction',
        title: '业务功能',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'isConversion',
        title: '是否转化',
        width: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'conversionType',
        title: '转化类型',
        width: 120,
        showOverflow: true,
      },
      {
        field: 'conversionValue',
        title: '转化价值',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'referSource',
        title: '来源',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'referMedium',
        title: '媒介',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'referCampaign',
        title: '推广活动',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'screenWidth',
        title: '屏幕宽度',
        width: 100,
        align: 'right',
      },
      {
        field: 'screenHeight',
        title: '屏幕高度',
        width: 100,
        align: 'right',
      },
      {
        field: 'viewportWidth',
        title: '视口宽度',
        width: 100,
        align: 'right',
      },
      {
        field: 'viewportHeight',
        title: '视口高度',
        width: 100,
        align: 'right',
      },
      {
        field: 'isNewVisitor',
        title: '是否新访客',
        width: 110,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'visitCount',
        title: '访问次数',
        width: 100,
        align: 'right',
      },
      {
        field: 'bounceRate',
        title: '跳出率',
        width: 100,
        formatter: ({ cellValue }) => formatPercent(cellValue),
        align: 'right',
      },
      {
        field: 'eventCategory',
        title: '事件分类',
        width: 120,
        showOverflow: true,
      },
      {
        field: 'eventLabel',
        title: '事件标签',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'eventValue',
        title: '事件值',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'description',
        title: '说明',
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
          await getCustomerChannelBehaviorPage({
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
    <Grid table-title="线上渠道行为信息" />
  </div>
</template>
