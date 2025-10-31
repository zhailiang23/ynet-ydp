<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';

import { computed, onMounted, ref, watch } from 'vue';

import { message, Tabs, TabPane } from 'ant-design-vue';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerChannelOverviewPage } from '#/api/aicrm/customerchanneloverview';
import { getCustomerDepositOverviewPage } from '#/api/aicrm/customerdepositoverview';
import { getCustomerDiscountOverviewPage } from '#/api/aicrm/customerdiscountoverview';
import { getCustomerLoanOverviewPage } from '#/api/aicrm/customerloanoverview';
import { getCustomerMiddleBusinessOverviewPage } from '#/api/aicrm/customermiddlebusinessoverview';
import { getCustomerOffbalanceOverviewPage } from '#/api/aicrm/customeroffbalanceoverview';

defineOptions({
  name: 'CustomerBusinessOverview',
  components: {
    ATabs: Tabs,
    ATabPane: TabPane,
  },
});

const props = defineProps<{
  customerId: number;
}>();

// Tab 相关
const activeTab = ref('deposit');

// 已加载的 Tab
const loadedTabs = ref(new Set<string>(['deposit']));

// 监听 Tab 切换
watch(activeTab, (newTab) => {
  if (!loadedTabs.value.has(newTab)) {
    loadedTabs.value.add(newTab);
  }
});

// 格式化金额
function formatMoney(value?: number) {
  if (value === null || value === undefined) return '-';
  return value.toLocaleString('zh-CN', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  });
}

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  return value;
}

// ==================== 存款业务概览 ====================
const [DepositOverviewGrid, depositOverviewGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { field: 'customerNo', title: '客户编号', width: 140 },
      { field: 'statisticsDate', title: '统计日期', width: 120 },
      { field: 'depositType', title: '存款类型', width: 120 },
      { field: 'currency', title: '币种', width: 80 },
      { field: 'accountNo', title: '账号', width: 180 },
      {
        field: 'depositBalance',
        title: '存款余额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'businessAmount',
        title: '业务金额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      { field: 'depositAccountCount', title: '存款账户数', width: 100 },
      { field: 'accountStatus', title: '账户状态', width: 100 },
      { field: 'productId', title: '产品ID', width: 120 },
      {
        field: 'balanceYearAvg',
        title: '本年余额日均',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'interestRate',
        title: '存款利率(%)',
        width: 120,
        formatter: ({ cellValue }) => cellValue !== null && cellValue !== undefined ? cellValue.toFixed(4) : '-',
      },
      {
        field: 'openDate',
        title: '开户日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'matureDate',
        title: '到期日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      { field: 'orgName', title: '机构名称', width: 200 },
      { field: 'remark', title: '备注', minWidth: 150 },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerDepositOverviewPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  },
});

// ==================== 贷款业务概览 ====================
const [LoanOverviewGrid, loanOverviewGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { field: 'customerNo', title: '客户编号', width: 140 },
      { field: 'statisticsDate', title: '统计日期', width: 120 },
      { field: 'loanType', title: '贷款类型', width: 120 },
      { field: 'currency', title: '币种', width: 80 },
      { field: 'accountNo', title: '账号', width: 180 },
      {
        field: 'loanBalance',
        title: '贷款余额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'businessAmount',
        title: '业务金额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      { field: 'loanAccountCount', title: '贷款账户数', width: 100 },
      { field: 'accountStatus', title: '账户状态', width: 100 },
      {
        field: 'loanRate',
        title: '贷款利率(%)',
        width: 120,
        formatter: ({ cellValue }) => cellValue !== null && cellValue !== undefined ? cellValue.toFixed(4) : '-',
      },
      {
        field: 'balanceYearAvg',
        title: '本年余额日均',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'loanDate',
        title: '贷款日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'matureDate',
        title: '到期日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      { field: 'orgName', title: '机构名称', width: 200 },
      { field: 'remark', title: '备注', minWidth: 150 },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerLoanOverviewPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  },
});

// ==================== 中间业务概览 ====================
const [MiddleBusinessOverviewGrid, middleBusinessOverviewGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { field: 'customerNo', title: '客户编号', width: 140 },
      { field: 'statisticsDate', title: '统计日期', width: 120 },
      { field: 'businessType', title: '业务类型', width: 140 },
      { field: 'currency', title: '币种', width: 80 },
      {
        field: 'businessAmount',
        title: '业务金额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'feeIncome',
        title: '手续费收入',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      { field: 'businessCount', title: '业务笔数', width: 100 },
      { field: 'productName', title: '产品名称', width: 180 },
      {
        field: 'yearBusinessAmount',
        title: '本年累计金额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'yearFeeIncome',
        title: '本年累计收入',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'businessDate',
        title: '业务日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      { field: 'orgName', title: '机构名称', width: 200 },
      { field: 'remark', title: '备注', minWidth: 150 },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerMiddleBusinessOverviewPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  },
});

// ==================== 渠道业务概览 ====================
const [ChannelOverviewGrid, channelOverviewGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { field: 'customerNo', title: '客户编号', width: 140 },
      { field: 'statisticsDate', title: '统计日期', width: 120 },
      { field: 'channelType', title: '渠道类型', width: 120 },
      { field: 'channelName', title: '渠道名称', width: 140 },
      { field: 'businessType', title: '业务类型', width: 140 },
      {
        field: 'transactionAmount',
        title: '交易金额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      { field: 'transactionCount', title: '交易笔数', width: 100 },
      {
        field: 'yearTransactionAmount',
        title: '本年累计金额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      { field: 'yearTransactionCount', title: '本年累计笔数', width: 120 },
      {
        field: 'transactionDate',
        title: '交易日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      { field: 'orgName', title: '机构名称', width: 200 },
      { field: 'remark', title: '备注', minWidth: 150 },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerChannelOverviewPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  },
});

// ==================== 贴现业务概览 ====================
const [DiscountOverviewGrid, discountOverviewGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { field: 'customerNo', title: '客户编号', width: 140 },
      { field: 'statisticsDate', title: '统计日期', width: 120 },
      { field: 'discountType', title: '贴现类型', width: 120 },
      { field: 'billNo', title: '票据号码', width: 200 },
      { field: 'currency', title: '币种', width: 80 },
      {
        field: 'billAmount',
        title: '票据金额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'discountAmount',
        title: '贴现金额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'discountRate',
        title: '贴现利率(%)',
        width: 120,
        formatter: ({ cellValue }) => cellValue !== null && cellValue !== undefined ? cellValue.toFixed(4) : '-',
      },
      {
        field: 'discountInterest',
        title: '贴现利息',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'discountDate',
        title: '贴现日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'matureDate',
        title: '到期日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      { field: 'discountStatus', title: '贴现状态', width: 100 },
      { field: 'orgName', title: '机构名称', width: 200 },
      { field: 'remark', title: '备注', minWidth: 150 },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerDiscountOverviewPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  },
});

// ==================== 表外业务概览 ====================
const [OffbalanceOverviewGrid, offbalanceOverviewGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { field: 'customerNo', title: '客户编号', width: 140 },
      { field: 'statisticsDate', title: '统计日期', width: 120 },
      { field: 'businessType', title: '业务类型', width: 140 },
      { field: 'contractNo', title: '合同号', width: 200 },
      { field: 'currency', title: '币种', width: 80 },
      {
        field: 'contractAmount',
        title: '合同金额',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'usedAmount',
        title: '已用额度',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'availableAmount',
        title: '可用额度',
        width: 140,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      { field: 'businessStatus', title: '业务状态', width: 100 },
      {
        field: 'startDate',
        title: '起始日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'endDate',
        title: '结束日期',
        width: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      { field: 'productName', title: '产品名称', width: 180 },
      { field: 'orgName', title: '机构名称', width: 200 },
      { field: 'remark', title: '备注', minWidth: 150 },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerOffbalanceOverviewPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  },
});

// 获取对应 Grid API
function getGridApi(tabKey: string) {
  const apiMap: Record<string, any> = {
    deposit: depositOverviewGridApi,
    loan: loanOverviewGridApi,
    middlebusiness: middleBusinessOverviewGridApi,
    channel: channelOverviewGridApi,
    discount: discountOverviewGridApi,
    offbalance: offbalanceOverviewGridApi,
  };
  return apiMap[tabKey];
}

// 刷新当前 Tab 的表格
function refreshCurrentTab() {
  const gridApi = getGridApi(activeTab.value);
  if (gridApi) {
    gridApi.value?.reload();
  }
}

onMounted(() => {
  // 初始加载第一个 Tab 的数据
  refreshCurrentTab();
});

// 暴露刷新方法
defineExpose({
  refresh: refreshCurrentTab,
});
</script>

<template>
  <div class="business-overview-container">
    <ATabs v-model:activeKey="activeTab" type="card">
      <!-- 存款业务概览 -->
      <ATabPane key="deposit" tab="存款业务">
        <div v-if="loadedTabs.has('deposit')" class="tab-content">
          <DepositOverviewGrid />
        </div>
      </ATabPane>

      <!-- 贷款业务概览 -->
      <ATabPane key="loan" tab="贷款业务">
        <div v-if="loadedTabs.has('loan')" class="tab-content">
          <LoanOverviewGrid />
        </div>
      </ATabPane>

      <!-- 中间业务概览 -->
      <ATabPane key="middlebusiness" tab="中间业务">
        <div v-if="loadedTabs.has('middlebusiness')" class="tab-content">
          <MiddleBusinessOverviewGrid />
        </div>
      </ATabPane>

      <!-- 渠道业务概览 -->
      <ATabPane key="channel" tab="渠道业务">
        <div v-if="loadedTabs.has('channel')" class="tab-content">
          <ChannelOverviewGrid />
        </div>
      </ATabPane>

      <!-- 贴现业务概览 -->
      <ATabPane key="discount" tab="贴现业务">
        <div v-if="loadedTabs.has('discount')" class="tab-content">
          <DiscountOverviewGrid />
        </div>
      </ATabPane>

      <!-- 表外业务概览 -->
      <ATabPane key="offbalance" tab="表外业务">
        <div v-if="loadedTabs.has('offbalance')" class="tab-content">
          <OffbalanceOverviewGrid />
        </div>
      </ATabPane>
    </ATabs>
  </div>
</template>

<style scoped lang="less">
.business-overview-container {
  // 移除 padding，让内容充满整个区域

  // 去掉 tab 的边框，与 account-info 保持一致
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

  .tab-content {
    height: 100%;
  }
}

// Dark 模式样式
.dark .business-overview-container {
  :deep(.ant-tabs) {
    .ant-tabs-tab {
      &.ant-tabs-tab-active {
        background: rgb(28 30 35) !important;
      }
    }
  }
}
</style>
