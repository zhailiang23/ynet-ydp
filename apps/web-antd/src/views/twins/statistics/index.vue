<script lang="ts" setup>
import { ref, onMounted } from 'vue';
import { Card, Row, Col, Table, Statistic } from 'ant-design-vue';
import {
  TeamOutlined,
  RobotOutlined,
  MessageOutlined,
  FormOutlined,
  StarOutlined,
  ClockCircleOutlined,
} from '@ant-design/icons-vue';
import { getStatisticsOverview } from '#/api/twins/statistics';
import type { TwinsStatisticsApi } from '#/api/twins/statistics';

defineOptions({
  name: 'TwinsStatistics',
});

// 统计数据
const overviewData = ref<TwinsStatisticsApi.OverviewData>();
const loading = ref(false);

// 员工排行表格列定义
const employeeColumns = [
  {
    title: '排名',
    key: 'rank',
    width: 80,
    customRender: ({ index }: { index: number }) => index + 1,
  },
  {
    title: '姓名',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '部门',
    dataIndex: 'department',
    key: 'department',
  },
  {
    title: '本月对话',
    dataIndex: 'monthSessions',
    key: 'monthSessions',
    align: 'right' as const,
    customRender: ({ text }: { text: number }) => `${text} 对话`,
  },
  {
    title: '本月留资',
    dataIndex: 'monthCollectInfos',
    key: 'monthCollectInfos',
    align: 'right' as const,
    customRender: ({ text }: { text: number }) => `${text} 留资`,
  },
];

// 获取统计数据
const fetchData = async () => {
  loading.value = true;
  try {
    const data = await getStatisticsOverview();
    overviewData.value = data;
  } finally {
    loading.value = false;
  }
};

onMounted(() => {
  fetchData();
});
</script>

<template>
  <div class="twins-statistics p-4">
    <!-- 顶部统计卡片 -->
    <Row :gutter="16" class="mb-4">
      <Col :span="6">
        <Card :loading="loading" class="stat-card">
          <Statistic
            title="总员工数"
            :value="overviewData?.totalEmployees || 0"
          >
            <template #prefix>
              <TeamOutlined />
            </template>
            <template #suffix>
              <div class="text-sm text-gray-500 dark:text-gray-400">
                活跃AI: {{ overviewData?.activeAiCount || 0 }}
              </div>
            </template>
          </Statistic>
        </Card>
      </Col>
      <Col :span="6">
        <Card :loading="loading" class="stat-card">
          <Statistic
            title="总对话数"
            :value="overviewData?.totalSessions || 0"
          >
            <template #prefix>
              <MessageOutlined />
            </template>
            <template #suffix>
              <div class="text-sm text-gray-500 dark:text-gray-400">
                本月: {{ overviewData?.monthSessions || 0 }}
              </div>
            </template>
          </Statistic>
        </Card>
      </Col>
      <Col :span="6">
        <Card :loading="loading" class="stat-card">
          <Statistic
            title="总留资数"
            :value="overviewData?.totalCollectInfos || 0"
          >
            <template #prefix>
              <FormOutlined />
            </template>
            <template #suffix>
              <div class="text-sm text-gray-500 dark:text-gray-400">
                本月: {{ overviewData?.monthCollectInfos || 0 }}
              </div>
            </template>
          </Statistic>
        </Card>
      </Col>
      <Col :span="6">
        <Card :loading="loading" class="stat-card">
          <Statistic
            title="客户满意度"
            :value="overviewData?.avgSatisfaction?.toFixed(1) || '0.0'"
          >
            <template #prefix>
              <StarOutlined />
            </template>
            <template #suffix>
              <div class="text-sm text-gray-500 dark:text-gray-400">
                平均响应: {{ overviewData?.avgResponseTime?.toFixed(1) || '0.0' }}s
              </div>
            </template>
          </Statistic>
        </Card>
      </Col>
    </Row>

    <!-- 留资类型统计 -->
    <Card title="留资类型统计" class="mb-4" :loading="loading">
      <div class="text-sm text-gray-500 dark:text-gray-400 mb-4">
        查看各类留资信息的统计和转化率
      </div>
      <Row :gutter="16">
        <Col
          v-for="item in overviewData?.collectInfoTypeStats || []"
          :key="item.templateId"
          :span="8"
        >
          <Card size="small" class="mb-4 collect-info-card">
            <div class="flex items-center justify-between mb-2">
              <div class="flex items-center">
                <FormOutlined class="text-xl mr-2" />
                <span class="font-medium">{{ item.name }}</span>
              </div>
            </div>
            <div class="grid grid-cols-3 gap-2 text-center">
              <div>
                <div class="text-2xl font-bold">{{ item.total }}</div>
                <div class="text-xs text-gray-500 dark:text-gray-400">总数</div>
              </div>
              <div>
                <div class="text-2xl font-bold">{{ item.monthCount }}</div>
                <div class="text-xs text-gray-500 dark:text-gray-400">本月</div>
              </div>
              <div>
                <div class="text-2xl font-bold">{{ item.lastMonthCount }}</div>
                <div class="text-xs text-gray-500 dark:text-gray-400">上月</div>
              </div>
            </div>
            <div class="mt-3">
              <div class="h-2 bg-gray-200 dark:bg-gray-700 rounded-full overflow-hidden">
                <div
                  class="h-full bg-blue-500 transition-all duration-300"
                  :style="{
                    width: `${item.total > 0 ? (item.monthCount / item.total) * 100 : 0}%`,
                  }"
                />
              </div>
            </div>
          </Card>
        </Col>
      </Row>
    </Card>

    <!-- 员工使用排行 -->
    <Card title="员工使用排行" :loading="loading">
      <div class="text-sm text-gray-500 dark:text-gray-400 mb-4">
        按本月对话数量排序
      </div>
      <Table
        :columns="employeeColumns"
        :data-source="overviewData?.employeeRankList || []"
        :pagination="false"
        :row-key="(record) => record.adminId"
        size="middle"
      >
        <template #bodyCell="{ column, record, index }">
          <template v-if="column.key === 'rank'">
            <div
              class="flex items-center justify-center w-8 h-8 rounded-full font-bold"
              :class="{
                'bg-yellow-100 dark:bg-yellow-900 text-yellow-600 dark:text-yellow-300':
                  index === 0,
                'bg-gray-100 dark:bg-gray-700 text-gray-600 dark:text-gray-300':
                  index === 1,
                'bg-orange-100 dark:bg-orange-900 text-orange-600 dark:text-orange-300':
                  index === 2,
                'bg-blue-50 dark:bg-blue-900 text-blue-600 dark:text-blue-300':
                  index > 2,
              }"
            >
              {{ index + 1 }}
            </div>
          </template>
        </template>
      </Table>
    </Card>
  </div>
</template>

<style scoped>
.twins-statistics {
  background-color: var(--vben-background-color);
}

.stat-card {
  border-radius: 8px;
  box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
}

.stat-card:hover {
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  transition: box-shadow 0.3s ease;
}

.collect-info-card {
  border-radius: 8px;
  border: 1px solid var(--vben-border-color);
  transition: all 0.3s ease;
}

.collect-info-card:hover {
  border-color: var(--vben-primary-color);
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

:deep(.ant-statistic-title) {
  color: var(--vben-text-color-secondary);
  font-size: 14px;
  margin-bottom: 4px;
}

:deep(.ant-statistic-content) {
  color: var(--vben-text-color);
  font-size: 24px;
  font-weight: 600;
}

:deep(.ant-table) {
  background-color: transparent;
}

:deep(.ant-table-thead > tr > th) {
  background-color: var(--vben-background-color);
  border-bottom: 1px solid var(--vben-border-color);
}

:deep(.ant-table-tbody > tr > td) {
  border-bottom: 1px solid var(--vben-border-color);
}

:deep(.ant-table-tbody > tr:hover > td) {
  background-color: var(--vben-hover-color);
}
</style>
