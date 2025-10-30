<script lang="ts" setup>
import type { RetailCustomerApi } from '#/api/aicrm/retail-customer';

import type { MenuInfo } from 'ant-design-vue/es/menu/src/interface';

import { computed, onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import { Menu, message } from 'ant-design-vue';

import { getRetailCustomer } from '#/api/aicrm/retail-customer';

import BasicInfo from './pages/basic-info.vue';
import IdentityList from './pages/identity-list.vue';
import FamilyInfo from './pages/family-info.vue';
import ManagementInfo from './pages/management-info.vue';
import Placeholder from './pages/placeholder.vue';

const route = useRoute();
const router = useRouter();

const loading = ref(false);
const customer = ref<RetailCustomerApi.RetailCustomer>();
const activeSection = ref('basic');

// 零售客户360视图菜单项
const menuItems = [
  { key: 'overview', label: '客户概况', component: Placeholder },
  { key: 'tags', label: '标签画像', component: Placeholder },
  { key: 'graph', label: '知识图谱', component: Placeholder },
  { key: 'basic', label: '客户基本信息', component: BasicInfo },
  { key: 'certificate', label: '客户证件信息', component: IdentityList },
  { key: 'work', label: '客户工作或经营信息', component: Placeholder },
  { key: 'family', label: '客户家庭信息', component: FamilyInfo },
  { key: 'management', label: '管理信息', component: ManagementInfo },
  { key: 'events', label: '客户大事记信息', component: Placeholder },
  { key: 'preference', label: '客户偏好', component: Placeholder },
  { key: 'business', label: '客户业务概览', component: Placeholder },
  { key: 'account', label: '账户信息', component: Placeholder },
  { key: 'product', label: '产品持有信息', component: Placeholder },
  { key: 'guarantee', label: '担保信息', component: Placeholder },
  { key: 'credit', label: '客户授信信息', component: Placeholder },
  { key: 'contract', label: '签约信息', component: Placeholder },
  { key: 'transaction', label: '交易明细信息', component: Placeholder },
  { key: 'rating', label: '客户评级信息', component: Placeholder },
  { key: 'contribution', label: '客户贡献度信息', component: Placeholder },
  { key: 'creditInfo', label: '客户信用信息', component: Placeholder },
  { key: 'complaint', label: '客户投诉信息', component: Placeholder },
  { key: 'marketing', label: '客户营销信息', component: Placeholder },
  { key: 'demand', label: '客户需求信息', component: Placeholder },
  { key: 'reminder', label: '客户提醒信息', component: Placeholder },
  { key: 'contact', label: '客户接触轨迹', component: Placeholder },
  { key: 'recommend', label: '产品推荐', component: Placeholder },
  { key: 'rights', label: '客户权益积分信息', component: Placeholder },
  { key: 'behavior', label: '线上渠道行为信息', component: Placeholder },
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
    customer.value = await getRetailCustomer(id);
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
        v-if="customer"
        :customer="customer"
        :customer-id="customer.id"
        :title="currentTitle"
      />
    </div>
  </div>
</template>

<style scoped>
/* 主容器 - Flexbox 布局 */
.customer-detail-container {
  display: flex;
  height: calc(100vh - 87px);
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
