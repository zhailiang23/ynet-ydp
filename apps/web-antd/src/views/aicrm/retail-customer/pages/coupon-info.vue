<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerCouponApi } from '#/api/aicrm/customercoupon';

import { onMounted, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerCouponPage } from '#/api/aicrm/customercoupon';

defineOptions({
  name: 'RetailCustomerCouponInfo',
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
const gridOptions: VxeTableGridOptions<AicrmCustomerCouponApi.CustomerCoupon> =
  {
    height: 600,
    columns: [
      { type: 'seq', width: 60, title: '序号', fixed: 'left' },
      {
        field: 'couponNo',
        title: '卡券编号',
        width: 180,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'couponName',
        title: '卡券名称',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'couponType',
        title: '卡券类型',
        width: 120,
        formatter: ({ cellValue }) => getDict('aicrm_coupon_type', cellValue),
      },
      {
        field: 'couponCategory',
        title: '卡券分类',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_coupon_category', cellValue),
      },
      {
        field: 'couponStatus',
        title: '卡券状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_coupon_status', cellValue),
      },
      {
        field: 'discountRate',
        title: '折扣率',
        width: 100,
        align: 'right',
      },
      {
        field: 'discountAmount',
        title: '优惠金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'giftName',
        title: '礼品名称',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'rateDiscount',
        title: '利率优惠',
        width: 100,
        align: 'right',
      },
      {
        field: 'thresholdAmount',
        title: '使用门槛金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'maxDiscountAmount',
        title: '最高优惠金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'issueDate',
        title: '发放日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'effectiveDate',
        title: '生效日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'expiryDate',
        title: '过期日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'useDate',
        title: '使用日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'useTime',
        title: '使用时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'cancelDate',
        title: '作废日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'cancelReason',
        title: '作废原因',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'issueSource',
        title: '发放来源',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_coupon_issue_source', cellValue),
      },
      {
        field: 'issueChannel',
        title: '发放渠道',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_coupon_channel', cellValue),
      },
      {
        field: 'issueActivityName',
        title: '关联活动名称',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'useScenario',
        title: '使用场景',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_coupon_use_scenario', cellValue),
      },
      {
        field: 'useChannel',
        title: '使用渠道',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_coupon_channel', cellValue),
      },
      {
        field: 'useProductName',
        title: '使用产品名称',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'useOrderNo',
        title: '使用订单号',
        width: 180,
        showOverflow: true,
      },
      {
        field: 'useTransactionAmount',
        title: '交易金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'actualDiscountAmount',
        title: '实际优惠金额',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'useLimitTimes',
        title: '可使用次数',
        width: 110,
        align: 'right',
      },
      {
        field: 'usedTimes',
        title: '已使用次数',
        width: 110,
        align: 'right',
      },
      {
        field: 'isTransferable',
        title: '是否可转赠',
        width: 110,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'isStackable',
        title: '是否可叠加',
        width: 110,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'expireRemindDays',
        title: '过期提醒天数',
        width: 120,
        align: 'right',
      },
      {
        field: 'isReminded',
        title: '是否已提醒',
        width: 110,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'remindTime',
        title: '提醒时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'description',
        title: '卡券描述',
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
          await getCustomerCouponPage({
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
    <Grid table-title="客户卡券信息" />
  </div>
</template>
