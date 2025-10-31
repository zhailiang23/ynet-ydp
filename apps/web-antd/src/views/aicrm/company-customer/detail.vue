<script lang="ts" setup>
import type { CompanyCustomerApi } from '#/api/aicrm/company-customer';

import type { MenuInfo } from 'ant-design-vue/es/menu/src/interface';

import { computed, onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import { Menu, message } from 'ant-design-vue';

import { getCompanyCustomer } from '#/api/aicrm/company-customer';

import AddressInfo from './pages/address-info.vue';
import BasicInfo from './pages/basic-info.vue';
import BondInfo from './pages/bond-info.vue';
import ContactInfo from './pages/contact-info.vue';
import OrganizationInfo from './pages/organization-info.vue';
import OtherBankInfo from './pages/other-bank-info.vue';
import ProjectInfo from './pages/project-info.vue';
import StockInfo from './pages/stock-info.vue';
import Placeholder from './pages/placeholder.vue';
// 零售客户和对公客户公用的页面
import ManagementInfo from '../retail-customer/pages/management-info.vue';
import BusinessOverview from '../retail-customer/pages/business-overview.vue';
import AccountInfo from '../retail-customer/pages/account-info.vue';
import ProductHolding from '../retail-customer/pages/product-holding.vue';
import CreditInfo from '../retail-customer/pages/credit-info.vue';
import ContractInfo from '../retail-customer/pages/contract-info.vue';
import GuaranteeInfo from '../retail-customer/pages/guarantee-info.vue';
import TransactionInfo from '../retail-customer/pages/transaction-info.vue';
import RatingInfo from '../retail-customer/pages/rating-info.vue';
import ContributionInfo from '../retail-customer/pages/contribution-info.vue';
import MarketingInfo from '../retail-customer/pages/marketing-info.vue';
import DemandInfo from '../retail-customer/pages/demand-info.vue';
import RecommendInfo from '../retail-customer/pages/recommend-info.vue';
import ChannelBehaviorInfo from '../retail-customer/pages/channel-behavior-info.vue';
import TimelineInfo from '../retail-customer/pages/timeline-info.vue';
import CustomerHeader from '../components/customer-header.vue';

const route = useRoute();
const router = useRouter();

const loading = ref(false);
const customer = ref<CompanyCustomerApi.CompanyCustomer>();
const activeSection = ref('overview');

// 对公客户360视图菜单项
const menuItems = [
  { key: 'overview', label: '客户概览', component: Placeholder },
  { key: 'tags', label: '标签画像', component: Placeholder },
  { key: 'graph', label: '知识图谱', component: Placeholder },
  { key: 'basic', label: '客户基本信息', component: BasicInfo },
  { key: 'organization', label: '组织架构信息', component: OrganizationInfo },
  { key: 'address', label: '客户地址信息', component: AddressInfo },
  { key: 'contact', label: '联系人信息', component: ContactInfo },
  { key: 'stock', label: '股票发行人信息', component: StockInfo },
  { key: 'bond', label: '债权发行信息', component: BondInfo },
  { key: 'finance', label: '客户财务信息', component: Placeholder },
  { key: 'project', label: '客户项目信息', component: ProjectInfo },
  { key: 'management', label: '管理信息', component: ManagementInfo },
  { key: 'otherBank', label: '客户他行信息', component: OtherBankInfo },
  { key: 'business', label: '客户业务概览', component: BusinessOverview },
  { key: 'account', label: '账户信息', component: AccountInfo },
  { key: 'product', label: '产品持有信息', component: ProductHolding },
  { key: 'credit', label: '授信信息', component: CreditInfo },
  { key: 'contract', label: '签约信息', component: ContractInfo },
  { key: 'guarantee', label: '担保信息', component: GuaranteeInfo },
  { key: 'transaction', label: '交易明细信息', component: TransactionInfo },
  { key: 'rating', label: '客户评级信息', component: RatingInfo },
  { key: 'contribution', label: '客户贡献度信息', component: ContributionInfo },
  { key: 'marketing', label: '客户营销信息', component: MarketingInfo },
  { key: 'demand', label: '客户需求信息', component: DemandInfo },
  { key: 'recommend', label: '产品推荐', component: RecommendInfo },
  { key: 'behavior', label: '线上渠道行为信息', component: ChannelBehaviorInfo },
  { key: 'contactTrack', label: '客户接触轨迹', component: TimelineInfo },
  { key: 'important', label: '客户重要事件', component: Placeholder },
];

// 当前激活的组件
const currentComponent = computed(() => {
  const item = menuItems.find((m) => m.key === activeSection.value);
  return item?.component || Placeholder;
});

// 当前页面标题
const currentTitle = computed(() => {
  const item = menuItems.find((m) => m.key === activeSection.value);
  return item?.label || '';
});

// 点击菜单项
function handleMenuClick(info: MenuInfo) {
  activeSection.value = String(info.key);
}

// 加载客户详情
async function loadCustomer() {
  const id = Number(route.params.id);
  if (!id) {
    message.error('客户ID无效');
    router.back();
    return;
  }

  loading.value = true;
  try {
    customer.value = await getCompanyCustomer(id);
  } catch (error: any) {
    message.error(error.message || '加载客户详情失败');
    router.back();
  } finally {
    loading.value = false;
  }
}

onMounted(() => {
  loadCustomer();
});
</script>

<template>
  <div v-loading="loading" class="customer-detail-container">
    <!-- 固定页头 -->
    <CustomerHeader
      v-if="customer"
      :customer-name="customer.customerName"
      :customer-type="customer.customerType"
      :customer-level="customer.customerLevel"
      :customer-status="customer.customerStatus"
    />

    <div class="customer-detail-content">
      <!-- 左侧菜单 -->
      <div class="sidebar-menu">
        <Menu
          :selectedKeys="[activeSection]"
          mode="inline"
          @click="handleMenuClick"
        >
          <Menu.Item v-for="item in menuItems" :key="item.key">
            {{ item.label }}
          </Menu.Item>
        </Menu>
      </div>

      <!-- 右侧内容区域 -->
      <div class="content-area">
        <component
          :is="currentComponent"
          v-if="customer && customer.customerId"
          :customer="customer"
          :customer-id="customer.customerId"
          :title="currentTitle"
        />
        <div v-else-if="customer && !customer.customerId" style="padding: 20px; text-align: center; color: #999;">
          该对公客户未关联到客户主表,无法查看详细信息
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 主容器 - 垂直布局 */
.customer-detail-container {
  display: flex;
  flex-direction: column;
  height: calc(100vh - 87px);
  overflow: hidden;
}

/* 内容区域容器 - 水平布局 */
.customer-detail-content {
  display: flex;
  flex: 1;
  overflow: hidden;
}

/* 左侧菜单 */
.sidebar-menu {
  flex-shrink: 0;
  width: 220px;
  overflow-y: auto;
  background-color: #fff;
  border-right: 1px solid #f0f0f0;
}

.dark .sidebar-menu {
  background-color: rgb(20 22 26);
  border-right-color: rgb(255 255 255 / 10%);
}

/* 右侧内容区域 */
.content-area {
  flex: 1;
  padding: 16px;
  overflow-y: auto;
  background-color: #f5f5f5;
}

.dark .content-area {
  background-color: rgb(15 17 21);
}

/* 菜单样式 */
.sidebar-menu :deep(.ant-menu) {
  border-right: none;
}

.sidebar-menu :deep(.ant-menu-item) {
  height: 40px;
  padding-left: 24px !important;
  margin: 0;
  line-height: 40px;
}

.sidebar-menu :deep(.ant-menu-item-selected) {
  background-color: #e6f7ff;
}

.dark .sidebar-menu :deep(.ant-menu-item-selected) {
  background-color: rgb(24 144 255 / 20%);
}

/* 内容区域的表格列宽统一 */
.content-area :deep(.ant-descriptions-view table) {
  width: 100% !important;
  table-layout: fixed !important;
}

.content-area :deep(.ant-descriptions-view table th.ant-descriptions-item-label) {
  width: 12% !important;
}

.content-area :deep(.ant-descriptions-view table td.ant-descriptions-item-content) {
  width: 21.33% !important;
}
</style>
