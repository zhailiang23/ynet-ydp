<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerPointsApi } from '#/api/aicrm/customerpoints';
import type { AicrmCustomerPointsTransactionApi } from '#/api/aicrm/customerpointstransaction';

import { ref, onMounted } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerPointsPage } from '#/api/aicrm/customerpoints';
import { getCustomerPointsTransactionPage } from '#/api/aicrm/customerpointstransaction';

defineOptions({
  name: 'RetailCustomerRightsPointsInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// Tab 相关
const activeTab = ref('points');

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

// 格式化金额
function formatMoney(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value.toLocaleString('zh-CN', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  })} 元`;
}

// ========== 积分信息列表配置 ==========
const [PointsGrid, pointsGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 60,
        fixed: 'left',
      },
      {
        field: 'pointsAccountNo',
        title: '积分账户编号',
        width: 160,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'pointsLevel',
        title: '积分等级',
        width: 100,
        formatter: ({ cellValue }) => getDict('aicrm_points_level', cellValue),
      },
      {
        field: 'availablePoints',
        title: '可用积分',
        width: 100,
        align: 'right',
      },
      {
        field: 'pointsBalance',
        title: '积分余额',
        width: 100,
        align: 'right',
      },
      {
        field: 'frozenPoints',
        title: '冻结积分',
        width: 100,
        align: 'right',
      },
      {
        field: 'totalEarnedPoints',
        title: '累计获得积分',
        width: 120,
        align: 'right',
      },
      {
        field: 'totalUsedPoints',
        title: '累计使用积分',
        width: 120,
        align: 'right',
      },
      {
        field: 'historyTotalGiftPoints',
        title: '历史累计赠送',
        width: 120,
        align: 'right',
      },
      {
        field: 'historyTotalDeductPoints',
        title: '历史累计扣减',
        width: 120,
        align: 'right',
      },
      {
        field: 'historyTotalExpirePoints',
        title: '历史累计失效',
        width: 120,
        align: 'right',
      },
      {
        field: 'upcomingExpirePoints',
        title: '即将失效积分',
        width: 120,
        align: 'right',
      },
      {
        field: 'upcomingExpireDate',
        title: '即将失效日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'yearEarnedPoints',
        title: '本年度获得',
        width: 110,
        align: 'right',
      },
      {
        field: 'yearUsedPoints',
        title: '本年度使用',
        width: 110,
        align: 'right',
      },
      {
        field: 'monthEarnedPoints',
        title: '本月获得',
        width: 100,
        align: 'right',
      },
      {
        field: 'monthUsedPoints',
        title: '本月使用',
        width: 100,
        align: 'right',
      },
      {
        field: 'accountStatus',
        title: '账户状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_points_account_status', cellValue),
      },
      {
        field: 'openDate',
        title: '开户日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'lastTransactionDate',
        title: '最后交易日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'lastGiftDate',
        title: '最后赠送日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'lastRedeemDate',
        title: '最后兑换日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'pointsValidMonths',
        title: '积分有效期(月)',
        width: 130,
        align: 'right',
      },
      {
        field: 'isAutoExpire',
        title: '是否自动失效',
        width: 120,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'autoExpireRemindDays',
        title: '自动失效提醒天数',
        width: 150,
        align: 'right',
      },
      {
        field: 'remark',
        title: '备注',
        width: 140,
        showOverflow: true,
      },
    ],
    height: 600,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerPointsPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerPointsApi.CustomerPoints>,
});

// ========== 积分消费历史列表配置 ==========
const [TransactionGrid, transactionGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 60,
        fixed: 'left',
      },
      {
        field: 'transactionNo',
        title: '交易流水号',
        width: 180,
        fixed: 'left',
        showOverflow: true,
      },
      {
        field: 'transactionDate',
        title: '交易日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'transactionTime',
        title: '交易时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'transactionType',
        title: '交易类型',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_points_transaction_type', cellValue),
      },
      {
        field: 'pointsChange',
        title: '积分变动',
        width: 100,
        align: 'right',
      },
      {
        field: 'pointsBefore',
        title: '交易前积分',
        width: 110,
        align: 'right',
      },
      {
        field: 'pointsAfter',
        title: '交易后积分',
        width: 110,
        align: 'right',
      },
      {
        field: 'transactionStatus',
        title: '交易状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_transaction_status', cellValue),
      },
      {
        field: 'transactionDesc',
        title: '交易描述',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'orderNo',
        title: '订单编号',
        width: 180,
        showOverflow: true,
      },
      {
        field: 'orderConsumedPoints',
        title: '订单消耗积分',
        width: 120,
        align: 'right',
      },
      {
        field: 'orderStatus',
        title: '订单状态',
        width: 100,
        formatter: ({ cellValue }) => getDict('aicrm_order_status', cellValue),
      },
      {
        field: 'orderApprovalStatus',
        title: '订单审批状态',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_order_approval_status', cellValue),
      },
      {
        field: 'exchangeChannel',
        title: '兑换渠道',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_exchange_channel', cellValue),
      },
      {
        field: 'giftExchangeMethod',
        title: '礼品兑换方式',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_gift_exchange_method', cellValue),
      },
      {
        field: 'giftName',
        title: '礼品名称',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'giftQuantity',
        title: '礼品数量',
        width: 100,
        align: 'right',
      },
      {
        field: 'giftValue',
        title: '礼品价值',
        width: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
        align: 'right',
      },
      {
        field: 'deliveryAddress',
        title: '配送地址',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'deliveryStatus',
        title: '配送状态',
        width: 100,
        formatter: ({ cellValue }) =>
          getDict('aicrm_delivery_status', cellValue),
      },
      {
        field: 'deliveryTime',
        title: '配送时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'receiverName',
        title: '收货人姓名',
        width: 100,
      },
      {
        field: 'receiverPhone',
        title: '收货人电话',
        width: 120,
        showOverflow: true,
      },
      {
        field: 'businessType',
        title: '业务类型',
        width: 120,
        formatter: ({ cellValue }) =>
          getDict('aicrm_points_business_type', cellValue),
      },
      {
        field: 'businessNo',
        title: '业务单号',
        width: 180,
        showOverflow: true,
      },
      {
        field: 'exchangeUser',
        title: '兑换用户',
        width: 100,
      },
      {
        field: 'expireDate',
        title: '过期日期',
        width: 110,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'refundReason',
        title: '退款原因',
        width: 200,
        showOverflow: true,
      },
      {
        field: 'refundTime',
        title: '退款时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'refundPoints',
        title: '退款积分',
        width: 100,
        align: 'right',
      },
      {
        field: 'approvalUserName',
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
        field: 'operatorUserName',
        title: '操作人',
        width: 100,
      },
      {
        field: 'operatorDeptName',
        title: '操作部门',
        width: 140,
        showOverflow: true,
      },
      {
        field: 'isReversed',
        title: '是否已冲正',
        width: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'reverseTime',
        title: '冲正时间',
        width: 160,
        formatter: ({ cellValue }) => formatDateTime(cellValue),
      },
      {
        field: 'remark',
        title: '备注',
        width: 140,
        showOverflow: true,
      },
    ],
    height: 600,
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerPointsTransactionPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerPointsTransactionApi.CustomerPointsTransaction>,
});

// 组件挂载时加载初始 tab 数据
onMounted(() => {
  setTimeout(() => {
    pointsGridApi.query();
  }, 100);
});

// 暴露刷新方法
defineExpose({
  refresh: () => {
    if (activeTab.value === 'points') {
      pointsGridApi.query();
    } else if (activeTab.value === 'transaction') {
      transactionGridApi.query();
    }
  },
});
</script>

<template>
  <div class="rights-points-info-page">
    <a-tabs v-model:activeKey="activeTab" type="card">
      <!-- 积分信息 Tab -->
      <a-tab-pane key="points" tab="积分信息">
        <PointsGrid table-title="客户积分信息" />
      </a-tab-pane>

      <!-- 积分消费历史 Tab -->
      <a-tab-pane key="transaction" tab="积分消费历史">
        <TransactionGrid table-title="积分消费历史" />
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<style scoped lang="less">
.rights-points-info-page {
  // 去掉 tab 的边框
  :deep(.ant-tabs) {
    .ant-tabs-nav {
      margin-bottom: 0;

      &::before {
        border-bottom: none;
      }
    }

    .ant-tabs-content-holder {
      border: none;
    }

    .ant-tabs-tab {
      border: none !important;
      background: transparent !important;

      &.ant-tabs-tab-active {
        background: #fff !important;
      }
    }
  }
}
</style>
