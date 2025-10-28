<script lang="ts" setup>
import type { RetailCustomerApi } from '#/api/aicrm/retail-customer';

import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import { getDictLabel } from '@vben/hooks';

import { Descriptions, Empty, Tabs, message } from 'ant-design-vue';

import { getRetailCustomer } from '#/api/aicrm/retail-customer';

const route = useRoute();
const router = useRouter();

const loading = ref(false);
const customer = ref<RetailCustomerApi.RetailCustomer>();
const activeTab = ref('basic');

// 获取字典标签的包装函数
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
}

// 格式化布尔值
function formatBoolean(value?: boolean) {
  if (value === null || value === undefined) return '-';
  return value ? '是' : '否';
}

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  return new Date(value).toLocaleDateString('zh-CN');
}

// 格式化日期时间
function formatDateTime(value?: string) {
  if (!value) return '-';
  return new Date(value).toLocaleString('zh-CN');
}

// 格式化金额
function formatMoney(value?: number) {
  if (value === null || value === undefined) return '-';
  return `¥${value.toLocaleString('zh-CN')}`;
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
  <div v-loading="loading" class="customer-detail">
    <div class="tabs-header-sticky">
      <Tabs v-model:activeKey="activeTab">
        <Tabs.TabPane key="basic" tab="基本信息" />
        <Tabs.TabPane key="contact" tab="联系方式" disabled />
        <Tabs.TabPane key="family" tab="家庭信息" disabled />
        <Tabs.TabPane key="business" tab="业务往来" disabled />
      </Tabs>
    </div>
    <div class="tabs-content-wrapper">
      <!-- Tab 1: 基本信息 -->
      <div v-if="activeTab === 'basic' && customer" class="space-y-6">
          <!-- 卡片 1: 客户共有信息 -->
          <a-card title="客户共有信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="客户编号">
                {{ customer.customerNo || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="客户类型">
                {{ getDict('crm_customer_type', customer.customerType) }}
              </Descriptions.Item>
              <Descriptions.Item label="客户名称">
                {{ customer.customerName || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="客户等级">
                {{ getDict('crm_customer_level', customer.customerLevel) }}
              </Descriptions.Item>
              <Descriptions.Item label="客户状态">
                {{ getDict('crm_customer_status', customer.customerStatus) }}
              </Descriptions.Item>
              <Descriptions.Item label="优质客户">
                {{ formatBoolean(customer.isHighQuality) }}
              </Descriptions.Item>
              <Descriptions.Item label="重要客户">
                {{ formatBoolean(customer.isImportant) }}
              </Descriptions.Item>
              <Descriptions.Item label="信用状态">
                {{ customer.creditStatus || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="信用等级">
                {{ getDict('crm_credit_level', customer.creditLevel) }}
              </Descriptions.Item>
              <Descriptions.Item label="信用评分">
                {{ customer.creditScore || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="客户来源">
                {{ getDict('crm_customer_source', customer.customerSource) }}
              </Descriptions.Item>
              <Descriptions.Item label="客户标签" :span="2">
                {{ customer.customerTag || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="备注信息" :span="3">
                {{ customer.remark || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 2: 个人基本信息 -->
          <a-card title="个人基本信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="昵称/别名">
                {{ customer.nickname || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="性别">
                {{
                  customer.gender === 1
                    ? '男'
                    : customer.gender === 2
                      ? '女'
                      : customer.gender === 3
                        ? '其他'
                        : '-'
                }}
              </Descriptions.Item>
              <Descriptions.Item label="出生日期">
                {{ formatDate(customer.birthday) }}
              </Descriptions.Item>
              <Descriptions.Item label="证件类型">
                {{ customer.idCardType || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="证件号码">
                {{ customer.idCardNo || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="国籍">
                {{ customer.nationality || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="籍贯">
                {{ customer.nativePlace || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="婚姻状况">
                {{ customer.maritalStatus || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="学历">
                {{ customer.education || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 3: 职业信息 -->
          <a-card title="职业信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="职业">
                {{ customer.occupation || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="工作单位">
                {{ customer.employerName || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="职位">
                {{ customer.position || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 4: VIP信息 -->
          <a-card title="VIP信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="是否VIP">
                {{ formatBoolean(customer.isVip) }}
              </Descriptions.Item>
              <Descriptions.Item label="VIP等级">
                {{ customer.vipLevel || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="积分">
                {{ customer.vipPoints || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="VIP开始日期">
                {{ formatDate(customer.vipStartDate) }}
              </Descriptions.Item>
              <Descriptions.Item label="VIP到期日期" :span="2">
                {{ formatDate(customer.vipEndDate) }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 5: 收入信息 -->
          <a-card title="收入信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="年收入">
                {{ formatMoney(customer.annualIncome) }}
              </Descriptions.Item>
              <Descriptions.Item label="月收入">
                {{ formatMoney(customer.monthlyIncome) }}
              </Descriptions.Item>
              <Descriptions.Item label="收入来源">
                {{ customer.sourceOfIncome || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 6: 资产信息 -->
          <a-card title="资产信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="资产总额">
                {{ formatMoney(customer.assets) }}
              </Descriptions.Item>
              <Descriptions.Item label="负债总额">
                {{ formatMoney(customer.liabilities) }}
              </Descriptions.Item>
              <Descriptions.Item label="有房产">
                {{ formatBoolean(customer.hasHouse) }}
              </Descriptions.Item>
              <Descriptions.Item label="有车">
                {{ formatBoolean(customer.hasCar) }}
              </Descriptions.Item>
              <Descriptions.Item label="有保险" :span="2">
                {{ formatBoolean(customer.hasInsurance) }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 7: 信誉信息 -->
          <a-card title="信誉信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="有贷款记录">
                {{ formatBoolean(customer.hasLoanRecord) }}
              </Descriptions.Item>
              <Descriptions.Item label="有逾期记录">
                {{ formatBoolean(customer.hasOverdueRecord) }}
              </Descriptions.Item>
              <Descriptions.Item label="黑名单标志">
                {{ formatBoolean(customer.blacklistFlag) }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 8: 系统信息 -->
          <a-card title="系统信息" :bordered="false">
            <Descriptions :column="2" bordered size="small">
              <Descriptions.Item label="创建时间">
                {{ formatDateTime(customer.createTime) }}
              </Descriptions.Item>
              <Descriptions.Item label="更新时间">
                {{ formatDateTime(customer.updateTime) }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>
        </div>

      <!-- 预留其他 Tab -->
      <div v-if="activeTab === 'contact'" class="empty-tab">
        <Empty description="功能开发中..." />
      </div>
      <div v-if="activeTab === 'family'" class="empty-tab">
        <Empty description="功能开发中..." />
      </div>
      <div v-if="activeTab === 'business'" class="empty-tab">
        <Empty description="功能开发中..." />
      </div>
    </div>
  </div>
</template>

<style>
.customer-detail {
  padding: 0;
}

.tabs-header-sticky {
  position: fixed;
  top: 87px;
  left: 224px;
  right: 0;
  z-index: 200;
  background-color: #fff;
  padding: 0 16px;
  height: 46px;
  overflow: hidden;
}

/* Dark mode 背景 */
.dark .tabs-header-sticky {
  background-color: rgb(20, 22, 26);
}

/* Tabs 容器样式 */
.tabs-header-sticky :deep(.ant-tabs) {
  height: auto;
}

.tabs-header-sticky :deep(.ant-tabs-nav) {
  margin-bottom: 0 !important;
}

.tabs-header-sticky :deep(.ant-tabs-nav-wrap) {
  margin-bottom: 0 !important;
}

.tabs-header-sticky :deep(.ant-tabs-nav::before) {
  border-bottom: none !important;
}

/* Tab 项基础样式 */
.tabs-header-sticky :deep(.ant-tabs-tab) {
  padding: 6px 16px !important;
  margin: 0 4px !important;
  border: none !important;
  background: transparent !important;
  transition: all 0.2s;
}

.tabs-header-sticky :deep(.ant-tabs-tab-btn) {
  color: rgba(0, 0, 0, 0.65);
}

/* 激活状态 - Light mode */
.tabs-header-sticky :deep(.ant-tabs-tab-active) {
  background-color: #f5f5f5 !important;
  border-radius: 7px 7px 0 0 !important;
}

.tabs-header-sticky :deep(.ant-tabs-tab-active .ant-tabs-tab-btn) {
  color: #1890ff !important;
}

/* Hover 效果 - Light mode */
.tabs-header-sticky :deep(.ant-tabs-tab:hover:not(.ant-tabs-tab-active) .ant-tabs-tab-btn) {
  color: rgba(0, 0, 0, 0.85);
}

/* Dark mode 适配 */
.dark .tabs-header-sticky :deep(.ant-tabs-tab) {
  background: transparent !important;
}

.dark .tabs-header-sticky :deep(.ant-tabs-tab .ant-tabs-tab-btn) {
  color: #fff !important;
}

.dark .tabs-header-sticky :deep(.ant-tabs-tab-active) {
  background-color: rgb(46, 48, 51) !important;
}

.dark .tabs-header-sticky :deep(.ant-tabs-tab-active .ant-tabs-tab-btn) {
  color: #fff !important;
  font-weight: normal !important;
}

.dark .tabs-header-sticky :deep(.ant-tabs-tab-disabled .ant-tabs-tab-btn) {
  color: #fff !important;
}

.dark .tabs-header-sticky :deep(.ant-tabs-tab:hover:not(.ant-tabs-tab-active) .ant-tabs-tab-btn) {
  color: #fff !important;
}

/* 禁用状态 */
.tabs-header-sticky :deep(.ant-tabs-tab-disabled) {
  opacity: 0.5;
  cursor: not-allowed !important;
}

/* 隐藏下划线 */
.tabs-header-sticky :deep(.ant-tabs-ink-bar) {
  display: none !important;
}

/* 隐藏内容区 */
.tabs-header-sticky :deep(.ant-tabs-content) {
  display: none !important;
}

/* 内容区域 */
.tabs-content-wrapper {
  padding: 16px 16px 16px 16px;
  margin-top: 45px;
}

.tabs-content-wrapper .space-y-6 > *:first-child {
  margin-top: 0 !important;
}

.empty-tab {
  display: flex;
  align-items: center;
  justify-content: center;
  min-height: 300px;
}

.space-y-6 > * + * {
  margin-top: 1.5rem;
}

/* 统一表格列宽 */
.tabs-content-wrapper :deep(.ant-descriptions-view table) {
  table-layout: fixed !important;
  width: 100% !important;
}

.tabs-content-wrapper :deep(.ant-descriptions-view table th.ant-descriptions-item-label) {
  width: 12% !important;
}

.tabs-content-wrapper :deep(.ant-descriptions-view table td.ant-descriptions-item-content) {
  width: 21.33% !important;
}
</style>

<style>
/* 全局样式 - 强制统一表格列宽 */
.customer-detail .tabs-content-wrapper .ant-descriptions-view table {
  table-layout: fixed !important;
  width: 100% !important;
}

.customer-detail .tabs-content-wrapper .ant-descriptions-view table th.ant-descriptions-item-label {
  width: 12% !important;
}

.customer-detail .tabs-content-wrapper .ant-descriptions-view table td.ant-descriptions-item-content {
  width: 21.33% !important;
}
</style>
