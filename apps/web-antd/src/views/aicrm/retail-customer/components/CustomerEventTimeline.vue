<script setup lang="ts">
import { Card, Timeline, Tag } from 'ant-design-vue';
import {
  ClockCircleOutlined,
  CheckCircleOutlined,
  ExclamationCircleOutlined,
} from '@ant-design/icons-vue';
import type { RetailCustomerOverviewApi } from '#/api/aicrm/retail-customer';

interface Props {
  events: RetailCustomerOverviewApi.CustomerEvent[];
}

defineProps<Props>();

// 事件类型颜色
const getEventColor = (type: string) => {
  const colorMap: Record<string, string> = {
    '到期提醒': 'orange',
    '产品交易': 'blue',
    '营销活动': 'green',
    '风险预警': 'red',
    '服务记录': 'cyan',
  };
  return colorMap[type] || 'default';
};

// 事件类型图标
const getEventIcon = (type: string) => {
  const iconMap: Record<string, any> = {
    '到期提醒': ClockCircleOutlined,
    '产品交易': CheckCircleOutlined,
    '营销活动': CheckCircleOutlined,
    '风险预警': ExclamationCircleOutlined,
    '服务记录': CheckCircleOutlined,
  };
  return iconMap[type] || ClockCircleOutlined;
};
</script>

<template>
  <Card :bordered="false" class="event-timeline-card h-[420px]">
    <template #title>
      <span class="text-sm font-semibold">最近重要事件</span>
    </template>

    <div class="flex-1 flex flex-col">
      <Timeline mode="left" class="flex-1">
        <Timeline.Item
          v-for="event in events"
          :key="event.id"
          :color="getEventColor(event.eventType)"
        >
          <template #dot>
            <component
              :is="getEventIcon(event.eventType)"
              class="text-sm"
            />
          </template>

          <div class="flex items-start justify-between">
            <div class="flex-1">
              <div class="flex items-center mb-1">
                <span class="font-semibold text-sm mr-2 text-gray-800 dark:text-gray-200">
                  {{ event.eventName }}
                </span>
                <Tag :color="getEventColor(event.eventType)" class="text-xs">
                  {{ event.eventType }}
                </Tag>
              </div>

              <div class="text-xs text-gray-600 dark:text-gray-400 mb-1">
                {{ event.eventDescription }}
              </div>

              <div class="text-xs text-gray-400 dark:text-gray-500">
                {{ event.eventDate }}
              </div>
            </div>
          </div>
        </Timeline.Item>
      </Timeline>

      <!-- 查看更多 -->
      <div class="text-center pt-2 border-t border-gray-200 dark:border-gray-700">
        <a
          class="text-xs text-blue-600 dark:text-blue-400 hover:text-blue-700 dark:hover:text-blue-300"
          href="javascript:void(0)"
        >
          查看更多事件 →
        </a>
      </div>
    </div>
  </Card>
</template>

<style scoped>
.event-timeline-card {
  margin-bottom: 12px;
}

.dark .event-timeline-card {
  background-color: rgb(28 30 35);
}

:deep(.ant-card-body) {
  display: flex;
  flex-direction: column;
  height: 100%;
  max-height: 360px;
  padding: 12px 16px 8px;
  overflow: hidden;
}

:deep(.ant-timeline) {
  margin-top: 0;
  margin-bottom: 0;
}

:deep(.ant-timeline-item-content) {
  min-height: 45px;
}

:deep(.ant-timeline-item) {
  padding-bottom: 8px;
}

:deep(.ant-timeline-item:last-child) {
  padding-bottom: 0;
}

:deep(.ant-timeline-item:last-child .ant-timeline-item-tail) {
  display: none;
}

:deep(.ant-timeline-item-tail) {
  border-left-color: #e8e8e8;
}

.dark :deep(.ant-timeline-item-tail) {
  border-left-color: #4a4a4a;
}
</style>
