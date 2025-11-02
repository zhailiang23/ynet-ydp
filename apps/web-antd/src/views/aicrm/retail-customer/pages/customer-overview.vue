<script setup lang="ts">
import type { RetailCustomerApi } from '#/api/aicrm/retail-customer';
import type { CompanyCustomerApi } from '#/api/aicrm/company-customer';

import { computed, onMounted, ref } from 'vue';
import { Card, Space, Tag } from 'ant-design-vue';
import { getRetailCustomerOverview, type RetailCustomerOverviewApi } from '#/api/aicrm/retail-customer';
import FinancialMetricsCards from '../components/FinancialMetricsCards.vue';
import AssetTrendChart from '../components/AssetTrendChart.vue';
import AssetStructureChart from '../components/AssetStructureChart.vue';
import MonthlyTransactionChart from '../components/MonthlyTransactionChart.vue';
import ProductHoldingChart from '../components/ProductHoldingChart.vue';
import CustomerProfileCards from '../components/CustomerProfileCards.vue';
import CustomerEventTimeline from '../components/CustomerEventTimeline.vue';

interface Props {
  customer: RetailCustomerApi.RetailCustomer | CompanyCustomerApi.CompanyCustomer;
  customerId: number;
  customerType?: 'retail' | 'company'; // 客户类型：零售或对公
}

const props = withDefaults(defineProps<Props>(), {
  customerType: 'retail', // 默认为零售客户
});

const loading = ref(false);
const overviewData = ref<RetailCustomerOverviewApi.Overview | null>(null);

// 获取customerId (兼容零售和对公客户)
const targetCustomerId = computed(() => {
  return props.customer?.customerId || props.customerId;
});

// 加载概况数据
const loadOverviewData = async () => {
  if (!targetCustomerId.value) {
    console.log('缺少 customerId,跳过加载');
    return;
  }

  loading.value = true;
  try {
    // 目前零售和对公都使用相同的概况API
    // 如果将来对公有独立的API，可以在这里根据 customerType 调用不同的API
    const data = await getRetailCustomerOverview({
      customerId: targetCustomerId.value,
    });
    overviewData.value = data;
  } catch (error) {
    console.error(`加载${props.customerType === 'company' ? '对公' : '零售'}客户概况失败:`, error);
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  loadOverviewData();
});
</script>

<template>
  <div v-loading="loading" class="customer-overview">
    <div v-if="overviewData" class="space-y-3">
      <!-- 财务指标卡片 -->
      <FinancialMetricsCards
        v-if="overviewData.financialMetrics"
        :metrics="overviewData.financialMetrics"
      />

      <!-- 资产趋势 + 最近事件时间线 (2:1布局) - 仅当有数据时显示 -->
      <div v-if="overviewData.assetTrend?.length || overviewData.recentEvents?.length" class="grid grid-cols-1 lg:grid-cols-3 gap-3">
        <div v-if="overviewData.assetTrend?.length" class="lg:col-span-2">
          <AssetTrendChart :trend-data="overviewData.assetTrend" />
        </div>
        <div v-if="overviewData.recentEvents?.length" :class="overviewData.assetTrend?.length ? 'lg:col-span-1' : 'lg:col-span-3'">
          <CustomerEventTimeline :events="overviewData.recentEvents" />
        </div>
      </div>

      <!-- 资产结构、月度交易、产品持有 (仅显示有数据的图表) -->
      <div class="grid grid-cols-1 lg:grid-cols-3 gap-3">
        <AssetStructureChart
          v-if="overviewData.assetStructure"
          :structure="overviewData.assetStructure"
        />
        <MonthlyTransactionChart
          v-if="overviewData.monthlyTransactions?.length"
          :transaction-data="overviewData.monthlyTransactions"
        />
        <ProductHoldingChart
          v-if="overviewData.productHoldingTrend?.length"
          :holding-data="overviewData.productHoldingTrend"
        />
      </div>

      <!-- 客户画像卡片 -->
      <CustomerProfileCards
        v-if="overviewData.rating && overviewData.contribution && overviewData.productStat"
        :contribution="overviewData.contribution"
        :product-stat="overviewData.productStat"
        :rating="overviewData.rating"
      />
    </div>

    <!-- 暂无数据提示 -->
    <Card v-else-if="!loading" :bordered="false">
      <div class="text-center py-12 text-gray-500 dark:text-gray-400">
        <div class="text-lg mb-2">暂无概况数据</div>
        <div class="text-sm">该客户的概况信息尚未生成</div>
      </div>
    </Card>
  </div>
</template>

<style scoped>
.customer-overview {
  background-color: #f5f5f5;
}

.dark .customer-overview {
  background-color: rgb(15 17 21);
}
</style>
