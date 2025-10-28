<script lang="ts" setup>
import type { CompanyCustomerApi } from '#/api/aicrm/company-customer';

import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import { getDictLabel } from '@vben/hooks';

import { Descriptions, Empty, Tabs, message } from 'ant-design-vue';

import { getCompanyCustomer } from '#/api/aicrm/company-customer';

const route = useRoute();
const router = useRouter();

const loading = ref(false);
const customer = ref<CompanyCustomerApi.CompanyCustomer>();
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
  <div v-loading="loading" class="customer-detail">
    <div class="tabs-header-sticky">
      <Tabs v-model:activeKey="activeTab">
        <Tabs.TabPane key="basic" tab="基本信息" />
        <Tabs.TabPane key="contact" tab="联系人信息" disabled />
        <Tabs.TabPane key="business" tab="业务往来" disabled />
        <Tabs.TabPane key="contract" tab="合同管理" disabled />
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

          <!-- 卡片 2: 企业执照信息 -->
          <a-card title="企业执照信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="企业名称" :span="3">
                {{ customer.enterpriseName || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="统一社会信用代码">
                {{ customer.unifiedSocialCreditCode || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="营业执照号">
                {{ customer.businessLicenseNo || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="组织机构代码">
                {{ customer.organizationCode || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="税务登记号" :span="3">
                {{ customer.taxRegistrationNo || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 3: 注册信息 -->
          <a-card title="注册信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="注册资本">
                {{ formatMoney(customer.registeredCapital) }}
              </Descriptions.Item>
              <Descriptions.Item label="实缴资本">
                {{ formatMoney(customer.paidInCapital) }}
              </Descriptions.Item>
              <Descriptions.Item label="企业类型">
                {{ customer.enterpriseType || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="成立日期">
                {{ formatDate(customer.establishmentDate) }}
              </Descriptions.Item>
              <Descriptions.Item label="登记机关">
                {{ customer.registrationAuthority || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="核准日期">
                {{ formatDate(customer.approvalDate) }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 4: 行业与经营 -->
          <a-card title="行业与经营" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="行业类别">
                {{ customer.industryCategory || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="员工人数" :span="2">
                {{ customer.employeeCount || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="经营范围" :span="3">
                {{ customer.businessScope || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 5: 地址信息 -->
          <a-card title="地址信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="注册地址" :span="3">
                {{ customer.registeredAddress || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="经营地址" :span="2">
                {{ customer.businessAddress || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="邮政编码">
                {{ customer.postalCode || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 6: 联系方式 -->
          <a-card title="联系方式" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="联系电话">
                {{ customer.contactPhone || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="传真号码">
                {{ customer.faxNumber || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="电子邮箱">
                {{ customer.email || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="公司网址" :span="3">
                {{ customer.website || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 7: 法定代表人信息 -->
          <a-card title="法定代表人信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="法定代表人">
                {{ customer.legalRepresentative || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="法人身份证号">
                {{ customer.legalRepIdCardNo || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="法人联系电话">
                {{ customer.legalRepPhone || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="实际控制人">
                {{ customer.actualController || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="主要股东" :span="2">
                {{ customer.mainShareholders || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 8: 财务信息 -->
          <a-card title="财务信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="年营业额">
                {{ formatMoney(customer.annualRevenue) }}
              </Descriptions.Item>
              <Descriptions.Item label="净利润">
                {{ formatMoney(customer.netProfit) }}
              </Descriptions.Item>
              <Descriptions.Item label="总资产">
                {{ formatMoney(customer.totalAssets) }}
              </Descriptions.Item>
              <Descriptions.Item label="总负债" :span="3">
                {{ formatMoney(customer.totalLiabilities) }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 9: 上市信息 -->
          <a-card title="上市信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="上市状态">
                {{ customer.listedStatus || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="股票代码" :span="2">
                {{ customer.stockCode || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 10: 关联企业信息 -->
          <a-card title="关联企业信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="是否集团公司">
                {{ formatBoolean(customer.isGroupCompany) }}
              </Descriptions.Item>
              <Descriptions.Item label="母公司名称" :span="2">
                {{ customer.parentCompany || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="子公司列表" :span="3">
                {{ customer.subsidiaries || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="主要业务伙伴" :span="3">
                {{ customer.businessPartners || '-' }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 11: 银行与合作信息 -->
          <a-card title="银行与合作信息" :bordered="false">
            <Descriptions :column="3" bordered size="small">
              <Descriptions.Item label="开户银行">
                {{ customer.bankName || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="银行账号" :span="2">
                {{ customer.bankAccount || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="纳税人类型">
                {{ customer.taxClassification || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="发票类型">
                {{ customer.invoiceType || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="付款条件">
                {{ customer.paymentTerms || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="合作模式">
                {{ customer.cooperationMode || '-' }}
              </Descriptions.Item>
              <Descriptions.Item label="有长期合同" :span="2">
                {{ formatBoolean(customer.hasLongTermContract) }}
              </Descriptions.Item>
            </Descriptions>
          </a-card>

          <!-- 卡片 12: 系统信息 -->
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
      <div v-if="activeTab === 'business'" class="empty-tab">
        <Empty description="功能开发中..." />
      </div>
      <div v-if="activeTab === 'contract'" class="empty-tab">
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
  padding: 0 16px 16px 16px;
  margin-top: 87px;
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
