<script setup lang="ts">
import { computed } from 'vue';
import { Card, Row, Col, Statistic } from 'ant-design-vue';
import {
  ArrowUpOutlined,
  ArrowDownOutlined,
  DollarOutlined,
  BankOutlined,
  FundOutlined,
} from '@ant-design/icons-vue';
import type { RetailCustomerOverviewApi } from '#/api/aicrm/retail-customer';

interface Props {
  metrics: RetailCustomerOverviewApi.FinancialMetrics;
}

const props = defineProps<Props>();

// 格式化金额
const formatAmount = (amount: number) => {
  if (amount >= 10000) {
    return `${(amount / 10000).toFixed(2)}万`;
  }
  return amount.toFixed(2);
};

// 格式化百分比
const formatPercent = (value: number) => {
  return `${(value * 100).toFixed(2)}%`;
};

// 增长率样式
const getGrowthColor = (growth: number) => {
  return growth >= 0 ? '#3f8600' : '#cf1322';
};

// 卡片数据
const metricsData = computed(() => [
  {
    title: '总资产',
    value: props.metrics.totalAssets,
    icon: DollarOutlined,
    color: '#1890ff',
    growth: props.metrics.totalAssetsGrowth,
  },
  {
    title: '净资产',
    value: props.metrics.netAssets,
    icon: FundOutlined,
    color: '#52c41a',
  },
  {
    title: '总负债',
    value: props.metrics.totalLiabilities,
    icon: BankOutlined,
    color: '#faad14',
  },
  {
    title: '存款余额',
    value: props.metrics.depositBalance,
    icon: BankOutlined,
    color: '#13c2c2',
    growth: props.metrics.depositGrowth,
  },
  {
    title: '理财金额',
    value: props.metrics.wealthBalance,
    icon: FundOutlined,
    color: '#722ed1',
    growth: props.metrics.wealthGrowth,
  },
  {
    title: '贷款余额',
    value: props.metrics.loanBalance,
    icon: DollarOutlined,
    color: '#fa541c',
  },
  {
    title: '可用余额',
    value: props.metrics.availableBalance || 0,
    icon: DollarOutlined,
    color: '#eb2f96',
  },
  {
    title: '信用额度',
    value: props.metrics.creditLimit || 0,
    icon: BankOutlined,
    color: '#2f54eb',
  },
]);
</script>

<template>
  <div class="financial-metrics">
    <Row :gutter="[12, 12]">
      <Col
        v-for="(item, index) in metricsData"
        :key="index"
        :lg="6"
        :md="12"
        :sm="24"
        :xl="6"
        :xs="24"
      >
        <Card
          :bordered="false"
          :body-style="{ padding: '12px 16px', height: '100%' }"
          class="metric-card h-full"
          hoverable
        >
          <div class="flex items-start justify-between h-full">
            <div class="flex-1 flex flex-col">
              <div class="text-xs text-gray-500 dark:text-gray-400 mb-1.5">
                {{ item.title }}
              </div>
              <Statistic
                :value="item.value"
                :value-style="{
                  fontSize: '20px',
                  fontWeight: 'bold',
                  color: 'var(--text-primary)'
                }"
              >
                <template #formatter="{ value }">
                  {{ formatAmount(Number(value)) }}
                </template>
              </Statistic>

              <!-- 增长率 -->
              <div
                v-if="item.growth !== undefined"
                :style="{ color: getGrowthColor(item.growth) }"
                class="text-sm mt-2 flex items-center"
              >
                <ArrowUpOutlined v-if="item.growth >= 0" class="mr-1" />
                <ArrowDownOutlined v-else class="mr-1" />
                <span>{{ formatPercent(Math.abs(item.growth)) }}</span>
              </div>
              <!-- 占位，保持高度一致 -->
              <div v-else class="text-sm mt-2" style="height: 22px"></div>
            </div>

            <!-- 图标 -->
            <div
              :style="{
                backgroundColor: item.color + '20',
                color: item.color
              }"
              class="rounded-full p-3"
            >
              <component :is="item.icon" class="text-2xl" />
            </div>
          </div>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<style scoped>
.financial-metrics {
  margin-bottom: 16px;
}

.metric-card {
  transition: all 0.3s ease;
}

.metric-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  transform: translateY(-2px);
}

.dark .metric-card {
  background-color: rgb(28 30 35);
}

.dark .metric-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.4);
}
</style>
