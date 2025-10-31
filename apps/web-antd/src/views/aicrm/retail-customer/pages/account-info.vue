<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAccountCreditcardApi } from '#/api/aicrm/customeraccountcreditcard';
import type { AicrmCustomerAccountDepositApi } from '#/api/aicrm/customeraccountdeposit';
import type { AicrmCustomerAccountFundApi } from '#/api/aicrm/customeraccountfund';
import type { AicrmCustomerAccountInsuranceApi } from '#/api/aicrm/customeraccountinsurance';
import type { AicrmCustomerAccountLoanApi } from '#/api/aicrm/customeraccountloan';
import type { AicrmCustomerAccountMetalApi } from '#/api/aicrm/customeraccountmetal';
import type { AicrmCustomerAccountTrustApi } from '#/api/aicrm/customeraccounttrust';
import type { AicrmCustomerAccountWealthApi } from '#/api/aicrm/customeraccountwealth';

import { computed, onMounted, ref, watch } from 'vue';

import { getDictLabel } from '@vben/hooks';
import { IconifyIcon } from '@vben/icons';

import { Empty, message } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerAccountCreditcardPage } from '#/api/aicrm/customeraccountcreditcard';
import { getCustomerAccountDepositPage } from '#/api/aicrm/customeraccountdeposit';
import { getCustomerAccountFundPage } from '#/api/aicrm/customeraccountfund';
import { getCustomerAccountInsurancePage } from '#/api/aicrm/customeraccountinsurance';
import { getCustomerAccountLoanPage } from '#/api/aicrm/customeraccountloan';
import { getCustomerAccountMetalPage } from '#/api/aicrm/customeraccountmetal';
import { getCustomerAccountTrustPage } from '#/api/aicrm/customeraccounttrust';
import { getCustomerAccountWealthPage } from '#/api/aicrm/customeraccountwealth';

defineOptions({
  name: 'RetailCustomerAccountInfo',
  components: {
    AEmpty: Empty,
  },
});

const props = defineProps<{
  customerId: number;
}>();

// Tab 相关
const activeTab = ref('deposit');

// 视图模式
type ViewMode = 'card' | 'table';
const STORAGE_KEY = 'account-info-view-mode';
const viewMode = ref<ViewMode>(
  (localStorage.getItem(STORAGE_KEY) as ViewMode) || 'table',
);

// 卡片视图数据
const cardViewData = ref<any[]>([]);

// 已加载的 Tab
const loadedTabs = ref(new Set<string>(['deposit']));

// 监听视图模式变化
watch(viewMode, (newMode) => {
  localStorage.setItem(STORAGE_KEY, newMode);
  if (newMode === 'card') {
    loadCardViewData();
  }
});

// 监听 Tab 切换
watch(activeTab, (newTab) => {
  if (!loadedTabs.value.has(newTab)) {
    loadedTabs.value.add(newTab);
  }
  if (viewMode.value === 'card') {
    loadCardViewData();
  }
});

// 切换视图模式
function toggleView() {
  viewMode.value = viewMode.value === 'table' ? 'card' : 'table';
}

// 获取字典标签
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
}

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
  if (value === '9999-12-31') return '长期有效';
  return value;
}

// 格式化卡号脱敏
function maskCardNo(cardNo?: string) {
  if (!cardNo) return '-';
  if (cardNo.length <= 8) return cardNo;
  return `${cardNo.slice(0, 4)} **** **** ${cardNo.slice(-4)}`;
}

// 加载卡片视图数据
async function loadCardViewData() {
  const apiMap: Record<string, any> = {
    deposit: getCustomerAccountDepositPage,
    loan: getCustomerAccountLoanPage,
    wealth: getCustomerAccountWealthPage,
    fund: getCustomerAccountFundPage,
    trust: getCustomerAccountTrustPage,
    insurance: getCustomerAccountInsurancePage,
    metal: getCustomerAccountMetalPage,
    creditcard: getCustomerAccountCreditcardPage,
  };

  const api = apiMap[activeTab.value];
  if (!api) return;

  try {
    const result = await api({
      customerId: props.customerId,
      pageNo: 1,
      pageSize: 100,
    });
    cardViewData.value = result.list || [];
  } catch (error: any) {
    message.error(error.message || '加载数据失败');
    cardViewData.value = [];
  }
}

// 刷新卡片视图
function refreshCardView() {
  loadCardViewData();
}

// 当前 Tab 标题
const currentTabTitle = computed(() => {
  const titleMap: Record<string, string> = {
    deposit: '存款账户列表',
    loan: '贷款账户列表',
    wealth: '理财账户列表',
    fund: '基金账户列表',
    trust: '信托账户列表',
    insurance: '保险账户列表',
    metal: '贵金属账户列表',
    creditcard: '信用卡账户列表',
  };
  return titleMap[activeTab.value] || '账户列表';
});

// ========== 存款账户 ==========
const [DepositGrid, depositGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { type: 'seq', title: '序号', width: 70, fixed: 'left' },
      { field: 'accountNo', title: '账号', minWidth: 180, fixed: 'left' },
      { field: 'accountName', title: '户名', minWidth: 120 },
      {
        field: 'accountType',
        title: '账户类型',
        minWidth: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_deposit_account_type' },
      },
      },
      {
        field: 'accountStatus',
        title: '账户状态',
        minWidth: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_deposit_account_status' },
      },
      },
      {
        field: 'currencyType',
        title: '币种',
        minWidth: 80,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_currency_type' },
      },
      },
      {
        field: 'balance',
        title: '账户余额',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'openDate',
        title: '开户日期',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      { field: 'deptName', title: '开户机构', minWidth: 150 },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerAccountDepositPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  } as VxeTableGridOptions<AicrmCustomerAccountDepositApi.CustomerAccountDeposit>,
});

// ========== 贷款账户 ==========
const [LoanGrid, _loanGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { type: 'seq', title: '序号', width: 70, fixed: 'left' },
      { field: 'accountNo', title: '贷款账号', minWidth: 180, fixed: 'left' },
      { field: 'accountName', title: '借款人姓名', minWidth: 120 },
      { field: 'productName', title: '贷款产品名称', minWidth: 150 },
      {
        field: 'accountStatus',
        title: '账户状态',
        minWidth: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_loan_account_status' },
      },
      },
      {
        field: 'loanAmount',
        title: '贷款金额',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'balance',
        title: '贷款余额',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      { field: 'interestRate', title: '贷款利率(%)', minWidth: 100 },
      {
        field: 'openDate',
        title: '放款日期',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'matureDate',
        title: '到期日',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerAccountLoanPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  } as VxeTableGridOptions<AicrmCustomerAccountLoanApi.CustomerAccountLoan>,
});

// ========== 理财账户 ==========
const [WealthGrid, _wealthGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { type: 'seq', title: '序号', width: 70, fixed: 'left' },
      { field: 'accountNo', title: '理财账号', minWidth: 180, fixed: 'left' },
      { field: 'accountName', title: '户名', minWidth: 120 },
      { field: 'productName', title: '理财产品名称', minWidth: 150 },
      {
        field: 'accountStatus',
        title: '账户状态',
        minWidth: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_wealth_account_status' },
      },
      },
      {
        field: 'productType',
        title: '理财类型',
        minWidth: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_wealth_product_type' },
      },
      },
      {
        field: 'purchaseAmount',
        title: '购买金额',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'currentValue',
        title: '当前市值',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      { field: 'expectedReturnRate', title: '预期收益率(%)', minWidth: 120 },
      {
        field: 'openDate',
        title: '开户日期',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'matureDate',
        title: '到期日',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerAccountWealthPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  } as VxeTableGridOptions<AicrmCustomerAccountWealthApi.CustomerAccountWealth>,
});

// ========== 基金账户 ==========
const [FundGrid, _fundGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { type: 'seq', title: '序号', width: 70, fixed: 'left' },
      { field: 'accountNo', title: '基金账号', minWidth: 180, fixed: 'left' },
      { field: 'accountName', title: '户名', minWidth: 120 },
      { field: 'productName', title: '基金产品名称', minWidth: 150 },
      {
        field: 'accountStatus',
        title: '账户状态',
        minWidth: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_fund_account_status' },
      },
      },
      {
        field: 'fundType',
        title: '基金类型',
        minWidth: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_fund_type' },
      },
      },
      { field: 'balance', title: '持有份额', minWidth: 120 },
      {
        field: 'currentValue',
        title: '当前市值',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'accumulatedIncome',
        title: '累计收益',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'openDate',
        title: '开户日期',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerAccountFundPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  } as VxeTableGridOptions<AicrmCustomerAccountFundApi.CustomerAccountFund>,
});

// ========== 信托账户 ==========
const [TrustGrid, _trustGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { type: 'seq', title: '序号', width: 70, fixed: 'left' },
      { field: 'accountNo', title: '信托账号', minWidth: 180, fixed: 'left' },
      { field: 'accountName', title: '委托人姓名', minWidth: 120 },
      { field: 'productName', title: '信托产品名称', minWidth: 150 },
      {
        field: 'accountStatus',
        title: '账户状态',
        minWidth: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_trust_status' },
      },
      },
      {
        field: 'trustType',
        title: '信托类型',
        minWidth: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_trust_type' },
      },
      },
      {
        field: 'trustAmount',
        title: '信托金额',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'currentValue',
        title: '当前价值',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      { field: 'expectedReturnRate', title: '预期收益率(%)', minWidth: 120 },
      {
        field: 'openDate',
        title: '成立日期',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'matureDate',
        title: '到期日',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerAccountTrustPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  } as VxeTableGridOptions<AicrmCustomerAccountTrustApi.CustomerAccountTrust>,
});

// ========== 保险账户 ==========
const [InsuranceGrid, _insuranceGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { type: 'seq', title: '序号', width: 70, fixed: 'left' },
      { field: 'policyNo', title: '保单号', minWidth: 180, fixed: 'left' },
      { field: 'accountName', title: '投保人姓名', minWidth: 120 },
      { field: 'productName', title: '保险产品名称', minWidth: 150 },
      {
        field: 'accountStatus',
        title: '账户状态',
        minWidth: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_insurance_status' },
      },
      },
      {
        field: 'insuranceType',
        title: '保险类型',
        minWidth: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_insurance_type' },
      },
      },
      {
        field: 'insuredAmount',
        title: '保险金额',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'premium',
        title: '保费',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'cashValue',
        title: '现金价值',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'openDate',
        title: '投保日期',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'expireDate',
        title: '到期日期',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerAccountInsurancePage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  } as VxeTableGridOptions<AicrmCustomerAccountInsuranceApi.CustomerAccountInsurance>,
});

// ========== 贵金属账户 ==========
const [MetalGrid, _metalGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { type: 'seq', title: '序号', width: 70, fixed: 'left' },
      { field: 'accountNo', title: '贵金属账号', minWidth: 180, fixed: 'left' },
      { field: 'accountName', title: '户名', minWidth: 120 },
      {
        field: 'metalType',
        title: '贵金属品种',
        minWidth: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_metal_type' },
      },
      },
      {
        field: 'accountStatus',
        title: '账户状态',
        minWidth: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_metal_account_status' },
      },
      },
      { field: 'holdingQuantity', title: '持有数量', minWidth: 120 },
      {
        field: 'holdingUnit',
        title: '持有单位',
        minWidth: 80,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_metal_unit' },
      },
      },
      {
        field: 'currentValue',
        title: '当前市值',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'openDate',
        title: '开户日期',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerAccountMetalPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  } as VxeTableGridOptions<AicrmCustomerAccountMetalApi.CustomerAccountMetal>,
});

// ========== 信用卡账户 ==========
const [CreditcardGrid, _creditcardGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      { type: 'seq', title: '序号', width: 70, fixed: 'left' },
      {
        field: 'cardNo',
        title: '卡号',
        minWidth: 180,
        fixed: 'left',
        formatter: ({ cellValue }) => maskCardNo(cellValue),
      },
      { field: 'accountName', title: '持卡人姓名', minWidth: 120 },
      {
        field: 'cardType',
        title: '卡片类型',
        minWidth: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_creditcard_type' },
      },
      },
      {
        field: 'cardStatus',
        title: '卡片状态',
        minWidth: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_creditcard_status' },
      },
      },
      {
        field: 'creditLimit',
        title: '信用额度',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'usedLimit',
        title: '已用额度',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'availableLimit',
        title: '可用额度',
        minWidth: 120,
        formatter: ({ cellValue }) => formatMoney(cellValue),
      },
      {
        field: 'openDate',
        title: '开卡日期',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
      {
        field: 'expireDate',
        title: '到期日',
        minWidth: 120,
        formatter: ({ cellValue }) => formatDate(cellValue),
      },
    ],
    height: 600,
    proxyConfig: {
      ajax: {
        query: async ({ page }) =>
          await getCustomerAccountCreditcardPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          }),
      },
    },
    toolbarConfig: { refresh: true },
  } as VxeTableGridOptions<AicrmCustomerAccountCreditcardApi.CustomerAccountCreditcard>,
});

// 组件挂载时加载第一个 tab 的数据
onMounted(() => {
  // 表格视图且是存款账户tab时，需要手动触发查询
  if (viewMode.value === 'table' && activeTab.value === 'deposit') {
    // 使用 nextTick 确保 DOM 渲染完成
    setTimeout(() => {
      depositGridApi.query();
    }, 100);
  } else if (viewMode.value === 'card') {
    loadCardViewData();
  }
});

// 监听 customerId 变化，重新加载数据
watch(
  () => props.customerId,
  () => {
    // 清空已加载的 tabs
    loadedTabs.value = new Set(['deposit']);
    // 重新加载当前 tab
    if (viewMode.value === 'table') {
      depositGridApi.query();
    } else {
      loadCardViewData();
    }
  },
);
</script>

<template>
  <div class="account-info-page">
    <a-tabs v-model:active-key="activeTab" type="card">
      <!-- 存款账户 -->
      <a-tab-pane key="deposit" tab="存款账户">
        <transition name="fade" mode="out-in">
          <!-- 表格视图 -->
          <DepositGrid
            v-if="viewMode === 'table' && loadedTabs.has('deposit')"
            key="table"
            :table-title="currentTabTitle"
          >
            <template #toolbar-tools>
              <a-tooltip title="切换到卡片视图">
                <a-button shape="circle" @click="toggleView">
                  <template #icon>
                    <IconifyIcon icon="ant-design:appstore-outlined" />
                  </template>
                </a-button>
              </a-tooltip>
            </template>
          </DepositGrid>

          <!-- 卡片视图 -->
          <div
            v-else-if="viewMode === 'card'"
            key="card"
            class="card-view-container"
          >
            <div class="card-view-header">
              <h3>{{ currentTabTitle }}</h3>
              <div class="card-view-tools">
                <a-tooltip title="切换到表格视图">
                  <a-button shape="circle" @click="toggleView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:table-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
                <a-tooltip title="刷新">
                  <a-button shape="circle" @click="refreshCardView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:reload-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
              </div>
            </div>
            <div class="card-view-content">
              <div v-if="cardViewData.length > 0" class="card-grid">
                <div
                  v-for="item in cardViewData"
                  :key="item.id"
                  class="account-card"
                >
                  <div class="card-header">
                    <span class="card-title">{{ item.accountNo }}</span>
                    <a-tag
                      :color="
                        item.accountStatus === 'active' ? 'green' : 'default'
                      "
                    >
                      {{
                        getDict(
                          'aicrm_deposit_account_status',
                          item.accountStatus,
                        )
                      }}
                    </a-tag>
                  </div>
                  <div class="card-body">
                    <div class="card-field">
                      <span class="field-label">户名：</span>
                      <span class="field-value">{{
                        item.accountName || '-'
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">账户类型：</span>
                      <span class="field-value"><DictTag type="aicrm_deposit_account_type" :value="item.accountType" /></span>
                    </div>
                    <div class="card-field highlight">
                      <span class="field-label">账户余额：</span>
                      <span class="field-value amount">{{
                        formatMoney(item.balance)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">币种：</span>
                      <span class="field-value"><DictTag type="aicrm_currency_type" :value="item.currencyType" /></span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">开户日期：</span>
                      <span class="field-value">{{
                        formatDate(item.openDate)
                      }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <a-empty v-else description="暂无存款账户数据" />
            </div>
          </div>
        </transition>
      </a-tab-pane>

      <!-- 贷款账户 -->
      <a-tab-pane key="loan" tab="贷款账户">
        <transition name="fade" mode="out-in">
          <LoanGrid
            v-if="viewMode === 'table' && loadedTabs.has('loan')"
            key="table"
            :table-title="currentTabTitle"
          >
            <template #toolbar-tools>
              <a-tooltip title="切换到卡片视图">
                <a-button shape="circle" @click="toggleView">
                  <template #icon>
                    <IconifyIcon icon="ant-design:appstore-outlined" />
                  </template>
                </a-button>
              </a-tooltip>
            </template>
          </LoanGrid>

          <div
            v-else-if="viewMode === 'card'"
            key="card"
            class="card-view-container"
          >
            <div class="card-view-header">
              <h3>{{ currentTabTitle }}</h3>
              <div class="card-view-tools">
                <a-tooltip title="切换到表格视图">
                  <a-button shape="circle" @click="toggleView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:table-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
                <a-tooltip title="刷新">
                  <a-button shape="circle" @click="refreshCardView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:reload-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
              </div>
            </div>
            <div class="card-view-content">
              <div v-if="cardViewData.length > 0" class="card-grid">
                <div
                  v-for="item in cardViewData"
                  :key="item.id"
                  class="account-card"
                >
                  <div class="card-header">
                    <span class="card-title">{{ item.accountNo }}</span>
                    <a-tag
                      :color="item.accountStatus === 'normal' ? 'green' : 'red'"
                    >
                      <DictTag type="aicrm_loan_account_status" :value="item.accountStatus" />
                    </a-tag>
                  </div>
                  <div class="card-body">
                    <div class="card-field">
                      <span class="field-label">借款人：</span>
                      <span class="field-value">{{
                        item.accountName || '-'
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">产品名称：</span>
                      <span class="field-value">{{
                        item.productName || '-'
                      }}</span>
                    </div>
                    <div class="card-field highlight">
                      <span class="field-label">贷款余额：</span>
                      <span class="field-value amount">{{
                        formatMoney(item.balance)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">贷款利率：</span>
                      <span class="field-value">{{ item.interestRate }}%</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">到期日：</span>
                      <span class="field-value">{{
                        formatDate(item.matureDate)
                      }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <a-empty v-else description="暂无贷款账户数据" />
            </div>
          </div>
        </transition>
      </a-tab-pane>

      <!-- 理财账户 -->
      <a-tab-pane key="wealth" tab="理财账户">
        <transition name="fade" mode="out-in">
          <WealthGrid
            v-if="viewMode === 'table' && loadedTabs.has('wealth')"
            key="table"
            :table-title="currentTabTitle"
          >
            <template #toolbar-tools>
              <a-tooltip title="切换到卡片视图">
                <a-button shape="circle" @click="toggleView">
                  <template #icon>
                    <IconifyIcon icon="ant-design:appstore-outlined" />
                  </template>
                </a-button>
              </a-tooltip>
            </template>
          </WealthGrid>

          <div
            v-else-if="viewMode === 'card'"
            key="card"
            class="card-view-container"
          >
            <div class="card-view-header">
              <h3>{{ currentTabTitle }}</h3>
              <div class="card-view-tools">
                <a-tooltip title="切换到表格视图">
                  <a-button shape="circle" @click="toggleView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:table-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
                <a-tooltip title="刷新">
                  <a-button shape="circle" @click="refreshCardView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:reload-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
              </div>
            </div>
            <div class="card-view-content">
              <div v-if="cardViewData.length > 0" class="card-grid">
                <div
                  v-for="item in cardViewData"
                  :key="item.id"
                  class="account-card"
                >
                  <div class="card-header">
                    <span class="card-title">{{ item.accountNo }}</span>
                    <a-tag
                      :color="
                        item.accountStatus === 'holding' ? 'green' : 'default'
                      "
                    >
                      {{
                        getDict(
                          'aicrm_wealth_account_status',
                          item.accountStatus,
                        )
                      }}
                    </a-tag>
                  </div>
                  <div class="card-body">
                    <div class="card-field">
                      <span class="field-label">户名：</span>
                      <span class="field-value">{{
                        item.accountName || '-'
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">产品名称：</span>
                      <span class="field-value">{{
                        item.productName || '-'
                      }}</span>
                    </div>
                    <div class="card-field highlight">
                      <span class="field-label">当前市值：</span>
                      <span class="field-value amount">{{
                        formatMoney(item.currentValue)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">预期收益率：</span>
                      <span class="field-value"
                        >{{ item.expectedReturnRate }}%</span
                      >
                    </div>
                    <div class="card-field">
                      <span class="field-label">到期日：</span>
                      <span class="field-value">{{
                        formatDate(item.matureDate)
                      }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <a-empty v-else description="暂无理财账户数据" />
            </div>
          </div>
        </transition>
      </a-tab-pane>

      <!-- 基金账户 -->
      <a-tab-pane key="fund" tab="基金账户">
        <transition name="fade" mode="out-in">
          <FundGrid
            v-if="viewMode === 'table' && loadedTabs.has('fund')"
            key="table"
            :table-title="currentTabTitle"
          >
            <template #toolbar-tools>
              <a-tooltip title="切换到卡片视图">
                <a-button shape="circle" @click="toggleView">
                  <template #icon>
                    <IconifyIcon icon="ant-design:appstore-outlined" />
                  </template>
                </a-button>
              </a-tooltip>
            </template>
          </FundGrid>

          <div
            v-else-if="viewMode === 'card'"
            key="card"
            class="card-view-container"
          >
            <div class="card-view-header">
              <h3>{{ currentTabTitle }}</h3>
              <div class="card-view-tools">
                <a-tooltip title="切换到表格视图">
                  <a-button shape="circle" @click="toggleView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:table-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
                <a-tooltip title="刷新">
                  <a-button shape="circle" @click="refreshCardView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:reload-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
              </div>
            </div>
            <div class="card-view-content">
              <div v-if="cardViewData.length > 0" class="card-grid">
                <div
                  v-for="item in cardViewData"
                  :key="item.id"
                  class="account-card"
                >
                  <div class="card-header">
                    <span class="card-title">{{ item.accountNo }}</span>
                    <a-tag
                      :color="
                        item.accountStatus === 'holding' ? 'green' : 'default'
                      "
                    >
                      <DictTag type="aicrm_fund_account_status" :value="item.accountStatus" />
                    </a-tag>
                  </div>
                  <div class="card-body">
                    <div class="card-field">
                      <span class="field-label">户名：</span>
                      <span class="field-value">{{
                        item.accountName || '-'
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">产品名称：</span>
                      <span class="field-value">{{
                        item.productName || '-'
                      }}</span>
                    </div>
                    <div class="card-field highlight">
                      <span class="field-label">当前市值：</span>
                      <span class="field-value amount">{{
                        formatMoney(item.currentValue)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">累计收益：</span>
                      <span class="field-value">{{
                        formatMoney(item.accumulatedIncome)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">持有份额：</span>
                      <span class="field-value">{{ item.balance }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <a-empty v-else description="暂无基金账户数据" />
            </div>
          </div>
        </transition>
      </a-tab-pane>

      <!-- 信托账户 -->
      <a-tab-pane key="trust" tab="信托账户">
        <transition name="fade" mode="out-in">
          <TrustGrid
            v-if="viewMode === 'table' && loadedTabs.has('trust')"
            key="table"
            :table-title="currentTabTitle"
          >
            <template #toolbar-tools>
              <a-tooltip title="切换到卡片视图">
                <a-button shape="circle" @click="toggleView">
                  <template #icon>
                    <IconifyIcon icon="ant-design:appstore-outlined" />
                  </template>
                </a-button>
              </a-tooltip>
            </template>
          </TrustGrid>

          <div
            v-else-if="viewMode === 'card'"
            key="card"
            class="card-view-container"
          >
            <div class="card-view-header">
              <h3>{{ currentTabTitle }}</h3>
              <div class="card-view-tools">
                <a-tooltip title="切换到表格视图">
                  <a-button shape="circle" @click="toggleView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:table-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
                <a-tooltip title="刷新">
                  <a-button shape="circle" @click="refreshCardView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:reload-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
              </div>
            </div>
            <div class="card-view-content">
              <div v-if="cardViewData.length > 0" class="card-grid">
                <div
                  v-for="item in cardViewData"
                  :key="item.id"
                  class="account-card"
                >
                  <div class="card-header">
                    <span class="card-title">{{ item.accountNo }}</span>
                    <a-tag
                      :color="
                        item.accountStatus === 'valid' ? 'green' : 'default'
                      "
                    >
                      <DictTag type="aicrm_trust_status" :value="item.accountStatus" />
                    </a-tag>
                  </div>
                  <div class="card-body">
                    <div class="card-field">
                      <span class="field-label">委托人：</span>
                      <span class="field-value">{{
                        item.accountName || '-'
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">产品名称：</span>
                      <span class="field-value">{{
                        item.productName || '-'
                      }}</span>
                    </div>
                    <div class="card-field highlight">
                      <span class="field-label">当前价值：</span>
                      <span class="field-value amount">{{
                        formatMoney(item.currentValue)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">预期收益率：</span>
                      <span class="field-value"
                        >{{ item.expectedReturnRate }}%</span
                      >
                    </div>
                    <div class="card-field">
                      <span class="field-label">到期日：</span>
                      <span class="field-value">{{
                        formatDate(item.matureDate)
                      }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <a-empty v-else description="暂无信托账户数据" />
            </div>
          </div>
        </transition>
      </a-tab-pane>

      <!-- 保险账户 -->
      <a-tab-pane key="insurance" tab="保险账户">
        <transition name="fade" mode="out-in">
          <InsuranceGrid
            v-if="viewMode === 'table' && loadedTabs.has('insurance')"
            key="table"
            :table-title="currentTabTitle"
          >
            <template #toolbar-tools>
              <a-tooltip title="切换到卡片视图">
                <a-button shape="circle" @click="toggleView">
                  <template #icon>
                    <IconifyIcon icon="ant-design:appstore-outlined" />
                  </template>
                </a-button>
              </a-tooltip>
            </template>
          </InsuranceGrid>

          <div
            v-else-if="viewMode === 'card'"
            key="card"
            class="card-view-container"
          >
            <div class="card-view-header">
              <h3>{{ currentTabTitle }}</h3>
              <div class="card-view-tools">
                <a-tooltip title="切换到表格视图">
                  <a-button shape="circle" @click="toggleView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:table-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
                <a-tooltip title="刷新">
                  <a-button shape="circle" @click="refreshCardView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:reload-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
              </div>
            </div>
            <div class="card-view-content">
              <div v-if="cardViewData.length > 0" class="card-grid">
                <div
                  v-for="item in cardViewData"
                  :key="item.id"
                  class="account-card"
                >
                  <div class="card-header">
                    <span class="card-title">{{ item.policyNo }}</span>
                    <a-tag
                      :color="
                        item.accountStatus === 'valid' ? 'green' : 'default'
                      "
                    >
                      <DictTag type="aicrm_insurance_status" :value="item.accountStatus" />
                    </a-tag>
                  </div>
                  <div class="card-body">
                    <div class="card-field">
                      <span class="field-label">投保人：</span>
                      <span class="field-value">{{
                        item.accountName || '-'
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">产品名称：</span>
                      <span class="field-value">{{
                        item.productName || '-'
                      }}</span>
                    </div>
                    <div class="card-field highlight">
                      <span class="field-label">保险金额：</span>
                      <span class="field-value amount">{{
                        formatMoney(item.insuredAmount)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">保费：</span>
                      <span class="field-value">{{
                        formatMoney(item.premium)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">现金价值：</span>
                      <span class="field-value">{{
                        formatMoney(item.cashValue)
                      }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <a-empty v-else description="暂无保险账户数据" />
            </div>
          </div>
        </transition>
      </a-tab-pane>

      <!-- 贵金属账户 -->
      <a-tab-pane key="metal" tab="贵金属账户">
        <transition name="fade" mode="out-in">
          <MetalGrid
            v-if="viewMode === 'table' && loadedTabs.has('metal')"
            key="table"
            :table-title="currentTabTitle"
          >
            <template #toolbar-tools>
              <a-tooltip title="切换到卡片视图">
                <a-button shape="circle" @click="toggleView">
                  <template #icon>
                    <IconifyIcon icon="ant-design:appstore-outlined" />
                  </template>
                </a-button>
              </a-tooltip>
            </template>
          </MetalGrid>

          <div
            v-else-if="viewMode === 'card'"
            key="card"
            class="card-view-container"
          >
            <div class="card-view-header">
              <h3>{{ currentTabTitle }}</h3>
              <div class="card-view-tools">
                <a-tooltip title="切换到表格视图">
                  <a-button shape="circle" @click="toggleView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:table-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
                <a-tooltip title="刷新">
                  <a-button shape="circle" @click="refreshCardView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:reload-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
              </div>
            </div>
            <div class="card-view-content">
              <div v-if="cardViewData.length > 0" class="card-grid">
                <div
                  v-for="item in cardViewData"
                  :key="item.id"
                  class="account-card"
                >
                  <div class="card-header">
                    <span class="card-title">{{ item.accountNo }}</span>
                    <a-tag
                      :color="
                        item.accountStatus === 'active' ? 'green' : 'default'
                      "
                    >
                      {{
                        getDict(
                          'aicrm_metal_account_status',
                          item.accountStatus,
                        )
                      }}
                    </a-tag>
                  </div>
                  <div class="card-body">
                    <div class="card-field">
                      <span class="field-label">户名：</span>
                      <span class="field-value">{{
                        item.accountName || '-'
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">品种：</span>
                      <span class="field-value"><DictTag type="aicrm_metal_type" :value="item.metalType" /></span>
                    </div>
                    <div class="card-field highlight">
                      <span class="field-label">当前市值：</span>
                      <span class="field-value amount">{{
                        formatMoney(item.currentValue)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">持有数量：</span>
                      <span class="field-value"
                        >{{ item.holdingQuantity }}
                        <DictTag type="aicrm_metal_unit" :value="item.holdingUnit" /></span
                      >
                    </div>
                    <div class="card-field">
                      <span class="field-label">开户日期：</span>
                      <span class="field-value">{{
                        formatDate(item.openDate)
                      }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <a-empty v-else description="暂无贵金属账户数据" />
            </div>
          </div>
        </transition>
      </a-tab-pane>

      <!-- 信用卡账户 -->
      <a-tab-pane key="creditcard" tab="信用卡账户">
        <transition name="fade" mode="out-in">
          <CreditcardGrid
            v-if="viewMode === 'table' && loadedTabs.has('creditcard')"
            key="table"
            :table-title="currentTabTitle"
          >
            <template #toolbar-tools>
              <a-tooltip title="切换到卡片视图">
                <a-button shape="circle" @click="toggleView">
                  <template #icon>
                    <IconifyIcon icon="ant-design:appstore-outlined" />
                  </template>
                </a-button>
              </a-tooltip>
            </template>
          </CreditcardGrid>

          <div
            v-else-if="viewMode === 'card'"
            key="card"
            class="card-view-container"
          >
            <div class="card-view-header">
              <h3>{{ currentTabTitle }}</h3>
              <div class="card-view-tools">
                <a-tooltip title="切换到表格视图">
                  <a-button shape="circle" @click="toggleView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:table-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
                <a-tooltip title="刷新">
                  <a-button shape="circle" @click="refreshCardView">
                    <template #icon>
                      <IconifyIcon icon="ant-design:reload-outlined" />
                    </template>
                  </a-button>
                </a-tooltip>
              </div>
            </div>
            <div class="card-view-content">
              <div v-if="cardViewData.length > 0" class="card-grid">
                <div
                  v-for="item in cardViewData"
                  :key="item.id"
                  class="account-card"
                >
                  <div class="card-header">
                    <span class="card-title">{{
                      maskCardNo(item.cardNo)
                    }}</span>
                    <a-tag
                      :color="
                        item.cardStatus === 'active' ? 'green' : 'default'
                      "
                    >
                      <DictTag type="aicrm_creditcard_status" :value="item.cardStatus" />
                    </a-tag>
                  </div>
                  <div class="card-body">
                    <div class="card-field">
                      <span class="field-label">持卡人：</span>
                      <span class="field-value">{{
                        item.accountName || '-'
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">卡片类型：</span>
                      <span class="field-value"><DictTag type="aicrm_creditcard_type" :value="item.cardType" /></span>
                    </div>
                    <div class="card-field highlight">
                      <span class="field-label">信用额度：</span>
                      <span class="field-value amount">{{
                        formatMoney(item.creditLimit)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">可用额度：</span>
                      <span class="field-value">{{
                        formatMoney(item.availableLimit)
                      }}</span>
                    </div>
                    <div class="card-field">
                      <span class="field-label">到期日：</span>
                      <span class="field-value">{{
                        formatDate(item.expireDate)
                      }}</span>
                    </div>
                  </div>
                </div>
              </div>
              <a-empty v-else description="暂无信用卡账户数据" />
            </div>
          </div>
        </transition>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>

<style scoped lang="less">
.account-info-page {
  // 移除 padding，让内容充满整个区域

  // 去掉 tab 的边框，与 family-info 保持一致
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

.card-view-container {
  height: 100%;
  display: flex;
  flex-direction: column;
  background: #fff;
}

.card-view-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 16px;
  border-bottom: 1px solid #f0f0f0;

  h3 {
    margin: 0;
    font-size: 16px;
    font-weight: 600;
  }

  .card-view-tools {
    display: flex;
    gap: 8px;
  }
}

.card-view-content {
  flex: 1;
  overflow: auto;
  padding: 16px;
}

.card-grid {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  @media (max-width: 1200px) {
    grid-template-columns: repeat(2, 1fr);
  }

  @media (max-width: 768px) {
    grid-template-columns: 1fr;
  }
}

.account-card {
  padding: 16px;
  background: #fff;
  border: 1px solid #f0f0f0;
  border-radius: 8px;
  transition: all 0.3s;

  &:hover {
    box-shadow: 0 2px 8px rgb(0 0 0 / 10%);
    transform: translateY(-2px);
  }

  .card-header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding-bottom: 12px;
    margin-bottom: 12px;
    border-bottom: 1px solid #f0f0f0;

    .card-title {
      font-size: 16px;
      font-weight: 500;
      color: #1890ff;
    }
  }

  .card-body {
    .card-field {
      display: flex;
      padding: 6px 0;
      line-height: 1.8;

      .field-label {
        flex-shrink: 0;
        width: 100px;
        color: #666;
      }

      .field-value {
        flex: 1;
        color: #333;

        &.amount {
          font-size: 18px;
          font-weight: 600;
          color: #1890ff;
        }
      }

      &.highlight {
        padding: 8px 0;
        margin: 8px 0;
        background: #f6f8fa;
        border-radius: 4px;

        .field-label,
        .field-value {
          padding-left: 12px;
        }
      }
    }
  }
}

.dark .account-card {
  background: rgb(20 22 26);
  border-color: rgb(255 255 255 / 10%);

  .card-header {
    border-bottom-color: rgb(255 255 255 / 10%);
  }

  .card-body {
    .card-field {
      .field-label {
        color: rgb(255 255 255 / 60%);
      }

      .field-value {
        color: rgb(255 255 255 / 85%);
      }

      &.highlight {
        background: rgb(255 255 255 / 5%);
      }
    }
  }
}

// 淡入淡出动画
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.2s ease;
}

.fade-enter-from,
.fade-leave-to {
  opacity: 0;
}
</style>
