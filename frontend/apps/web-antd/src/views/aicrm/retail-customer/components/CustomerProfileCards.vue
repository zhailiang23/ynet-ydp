<script setup lang="ts">
import { Card, Row, Col, Progress, Descriptions } from 'ant-design-vue';
import type { RetailCustomerOverviewApi } from '#/api/aicrm/retail-customer';

interface Props {
  rating: RetailCustomerOverviewApi.CustomerRating;
  contribution: RetailCustomerOverviewApi.CustomerContribution;
  productStat: RetailCustomerOverviewApi.ProductHoldingStat;
}

defineProps<Props>();

// 贡献度等级颜色
const getContributionColor = (level: string) => {
  switch (level) {
    case '高':
      return '#52c41a';
    case '中':
      return '#1890ff';
    case '低':
      return '#faad14';
    default:
      return '#999';
  }
};

// 评分颜色
const getScoreColor = (score: number) => {
  if (score >= 80) return 'success';
  if (score >= 60) return 'normal';
  if (score >= 40) return 'exception';
  return 'exception';
};
</script>

<template>
  <div class="profile-cards">
    <Row :gutter="[12, 12]">
      <!-- 客户评级 -->
      <Col :lg="8" :md="24" :sm="24" :xl="8" :xs="24">
        <Card :bordered="false" class="profile-card" title="客户评级">
          <Descriptions :column="1" size="small">
            <Descriptions.Item label="价值等级">
              <span class="font-semibold text-blue-600 dark:text-blue-400">
                {{ rating.valueLevel }}
              </span>
            </Descriptions.Item>
            <Descriptions.Item label="服务等级">
              <span class="font-semibold text-purple-600 dark:text-purple-400">
                {{ rating.serviceLevel }}
              </span>
            </Descriptions.Item>
            <Descriptions.Item label="风险等级">
              <span class="font-semibold text-green-600 dark:text-green-400">
                {{ rating.riskLevel }}
              </span>
            </Descriptions.Item>
          </Descriptions>

          <div class="mt-4">
            <div class="text-sm text-gray-500 dark:text-gray-400 mb-2">评级分数</div>
            <Progress
              :percent="rating.ratingScore"
              :status="getScoreColor(rating.ratingScore)"
              :stroke-color="{
                '0%': '#108ee9',
                '100%': '#87d068'
              }"
            />
          </div>
        </Card>
      </Col>

      <!-- 客户贡献度 -->
      <Col :lg="8" :md="24" :sm="24" :xl="8" :xs="24">
        <Card :bordered="false" class="profile-card" title="客户贡献度">
          <div class="mb-4">
            <div class="text-sm text-gray-500 dark:text-gray-400 mb-2">综合贡献度</div>
            <div
              :style="{ color: getContributionColor(contribution.overallLevel) }"
              class="text-2xl font-bold"
            >
              {{ contribution.overallLevel }}
            </div>
          </div>

          <div class="space-y-3">
            <div>
              <div class="flex justify-between mb-1">
                <span class="text-sm text-gray-600 dark:text-gray-300">存款贡献</span>
                <span class="text-sm font-medium">{{ contribution.depositScore }}分</span>
              </div>
              <Progress
                :percent="contribution.depositScore"
                :show-info="false"
                stroke-color="#1890ff"
              />
            </div>

            <div>
              <div class="flex justify-between mb-1">
                <span class="text-sm text-gray-600 dark:text-gray-300">贷款贡献</span>
                <span class="text-sm font-medium">{{ contribution.loanScore }}分</span>
              </div>
              <Progress
                :percent="contribution.loanScore"
                :show-info="false"
                stroke-color="#52c41a"
              />
            </div>

            <div>
              <div class="flex justify-between mb-1">
                <span class="text-sm text-gray-600 dark:text-gray-300">中间业务</span>
                <span class="text-sm font-medium">{{ contribution.middleBusinessScore }}分</span>
              </div>
              <Progress
                :percent="contribution.middleBusinessScore"
                :show-info="false"
                stroke-color="#722ed1"
              />
            </div>
          </div>
        </Card>
      </Col>

      <!-- 产品持有统计 -->
      <Col :lg="8" :md="24" :sm="24" :xl="8" :xs="24">
        <Card :bordered="false" class="profile-card" title="产品持有统计">
          <Descriptions :column="1" size="small">
            <Descriptions.Item label="存款账户">
              <span class="font-semibold text-blue-600 dark:text-blue-400">
                {{ productStat.depositAccountCount }} 个
              </span>
            </Descriptions.Item>
            <Descriptions.Item label="理财产品">
              <span class="font-semibold text-purple-600 dark:text-purple-400">
                {{ productStat.wealthProductCount }} 个
              </span>
            </Descriptions.Item>
            <Descriptions.Item label="基金">
              <span class="font-semibold text-green-600 dark:text-green-400">
                {{ productStat.fundCount }} 个
              </span>
            </Descriptions.Item>
            <Descriptions.Item label="信用卡">
              <span class="font-semibold text-orange-600 dark:text-orange-400">
                {{ productStat.creditcardCount }} 张
              </span>
            </Descriptions.Item>
            <Descriptions.Item label="信托">
              <span class="font-semibold text-cyan-600 dark:text-cyan-400">
                {{ productStat.trustCount }} 个
              </span>
            </Descriptions.Item>
            <Descriptions.Item label="保险">
              <span class="font-semibold text-red-600 dark:text-red-400">
                {{ productStat.insuranceCount }} 份
              </span>
            </Descriptions.Item>
          </Descriptions>
        </Card>
      </Col>
    </Row>
  </div>
</template>

<style scoped>
.profile-cards {
  margin-bottom: 12px;
}

.profile-card {
  height: 100%;
}

.dark .profile-card {
  background-color: rgb(28 30 35);
}

:deep(.ant-descriptions-item-label) {
  color: var(--text-secondary);
}

:deep(.ant-descriptions-item-content) {
  color: var(--text-primary);
}
</style>
