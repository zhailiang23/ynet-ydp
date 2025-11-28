<script lang="ts" setup>
import type { TwinsChatCollectInfoApi } from '#/api/twins/chatcollectinfo';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import {
  Card,
  Descriptions,
  DescriptionsItem,
  Tag,
  Timeline,
  TimelineItem,
} from 'ant-design-vue';
import {
  CheckCircleOutlined,
  ClockCircleOutlined,
  FileTextOutlined,
  SyncOutlined,
  UserOutlined,
} from '@ant-design/icons-vue';

import { getChatCollectInfo } from '#/api/twins/chatcollectinfo';
import { formatDate } from '@vben/utils';

const [Modal, modalApi] = useVbenModal({
  async onOpenChange(isOpen) {
    if (isOpen) {
      const data = modalApi.getData<TwinsChatCollectInfoApi.ChatCollectInfo>();
      if (data?.id) {
        // 调用后端接口获取完整详情数据
        const detailData = await getChatCollectInfo(data.id);
        // 合并列表数据中的字段(templateName, adminName, username 等)
        collectInfo.value = {
          ...detailData,
          templateName: data.templateName || detailData.templateName,
          adminName: data.adminName || detailData.adminName,
          username: data.username || detailData.username,
        };
      }
    }
  },
});

const collectInfo = ref<TwinsChatCollectInfoApi.ChatCollectInfo | null>(null);

// 格式化日期时间
const formatDateTime = (dateTime: string | number | Date | undefined) => {
  if (!dateTime) return '-';
  return formatDate(dateTime, 'YYYY-MM-DD HH:mm:ss');
};

// 解析留资内容
const parsedContent = computed(() => {
  if (!collectInfo.value?.content) return {};
  try {
    return JSON.parse(collectInfo.value.content);
  } catch {
    return { content: collectInfo.value.content };
  }
});

// 获取状态配置
const getStatusConfig = (status: number) => {
  const configs = {
    0: { color: 'orange', text: '未处理', icon: ClockCircleOutlined },
    1: { color: 'processing', text: '处理中', icon: SyncOutlined },
    2: { color: 'success', text: '处理完成', icon: CheckCircleOutlined },
  };
  return configs[status as keyof typeof configs] || configs[0];
};

// 获取来源文本
const getSourceText = (source?: string) => {
  const sourceMap: Record<string, string> = {
    weapp: '来自小程序',
    app: '来自APP',
    h5: '来自H5',
    web: '来自网页',
    wecom: '来自企业微信',
  };
  return sourceMap[source || ''] || source || '来自聊天';
};

// 构建时间线数据
const timelineItems = computed(() => {
  const items = [];
  const info = collectInfo.value;

  if (info?.createTime) {
    items.push({
      color: 'green',
      label: '创建留资记录',
      time: formatDateTime(info.createTime),
    });
  }

  if (info?.acceptTime) {
    items.push({
      color: 'blue',
      label: '开始处理',
      time: formatDateTime(info.acceptTime),
    });
  } else if (info?.status === 0) {
    items.push({
      color: 'gray',
      label: '开始处理',
      time: '等待处理',
      pending: true,
    });
  }

  if (info?.finishTime) {
    items.push({
      color: 'green',
      label: '处理完成',
      time: formatDateTime(info.finishTime),
    });
  } else if (info?.status !== 2) {
    items.push({
      color: 'gray',
      label: '处理完成',
      time: '等待完成',
      pending: true,
    });
  }

  return items;
});

const statusConfig = computed(() =>
  getStatusConfig(collectInfo.value?.status || 0),
);
</script>

<template>
  <Modal
    class="!w-[1200px] !max-w-[90vw]"
    title="留资处理"
    :closable="true"
    :show-confirm-button="false"
  >
    <div v-if="collectInfo" class="min-h-[600px]">
      <!-- 头部用户信息 -->
      <div class="flex items-center gap-4 mb-6 pb-4 border-b border-gray-200 dark:border-gray-700">
        <div
          class="flex items-center justify-center w-16 h-16 rounded-full bg-gray-200 dark:bg-gray-700"
        >
          <UserOutlined class="text-3xl text-gray-600 dark:text-gray-300" />
        </div>
        <div class="flex-1">
          <div class="flex items-center gap-3 mb-2">
            <span class="text-xl font-medium">
              {{ collectInfo?.username || '未知用户' }}
            </span>
            <span class="text-gray-400 text-sm">
              {{ getSourceText(collectInfo?.source) }}
            </span>
          </div>
          <div class="flex items-center gap-2">
            <FileTextOutlined class="text-blue-500" />
            <span class="text-gray-600 dark:text-gray-400">
              {{ collectInfo?.templateName || '留资信息' }}
            </span>
            <Tag :color="statusConfig.color" class="ml-2">
              <template #icon>
                <component :is="statusConfig.icon" />
              </template>
              {{ statusConfig.text }}
            </Tag>
          </div>
        </div>
      </div>

      <!-- 左右两栏布局 -->
      <div class="grid grid-cols-3 gap-6">
        <!-- 左侧：留资类型 + 详细信息 -->
        <div class="col-span-2 space-y-6">
          <!-- 留资类型卡片 -->
          <Card
            title="留资类型"
            :bordered="false"
            class="bg-gray-50 dark:bg-gray-800/50"
            size="small"
          >
            <div class="grid grid-cols-2 gap-x-8 gap-y-3">
              <div class="flex">
                <span class="text-gray-500 dark:text-gray-400 w-24">类型名称</span>
                <span class="flex-1 text-gray-900 dark:text-gray-100">
                  {{ collectInfo?.templateName || '-' }}
                </span>
              </div>
              <div class="flex">
                <span class="text-gray-500 dark:text-gray-400 w-24">创建时间</span>
                <span class="flex-1 text-gray-900 dark:text-gray-100">
                  {{ formatDateTime(collectInfo?.createTime) }}
                </span>
              </div>
              <div class="flex">
                <span class="text-gray-500 dark:text-gray-400 w-24">处理客服</span>
                <span class="flex-1 text-gray-900 dark:text-gray-100">
                  {{ collectInfo?.adminName || '-' }}
                </span>
              </div>
              <div class="flex">
                <span class="text-gray-500 dark:text-gray-400 w-24">最后更新</span>
                <span class="flex-1 text-gray-900 dark:text-gray-100">
                  {{ formatDateTime(collectInfo?.updateTime || collectInfo?.createTime) }}
                </span>
              </div>
            </div>
          </Card>

          <!-- 详细信息卡片 -->
          <Card
            title="详细信息"
            :bordered="false"
            class="bg-gray-50 dark:bg-gray-800/50"
            size="small"
          >
            <div class="space-y-3">
              <div
                v-for="[key, value] in Object.entries(parsedContent)"
                :key="key"
                class="flex"
              >
                <span class="text-gray-500 dark:text-gray-400 w-28">{{ key }}</span>
                <span class="flex-1 text-gray-900 dark:text-gray-100">{{ value }}</span>
              </div>
              <div v-if="collectInfo?.remark" class="flex">
                <span class="text-gray-500 dark:text-gray-400 w-28">备注</span>
                <span class="flex-1 text-gray-900 dark:text-gray-100">
                  {{ collectInfo.remark }}
                </span>
              </div>
            </div>
          </Card>
        </div>

        <!-- 右侧：状态历史卡片 -->
        <div>
          <Card
            title="状态历史"
            :bordered="false"
            class="bg-gray-50 dark:bg-gray-800/50"
            size="small"
          >
            <Timeline>
              <TimelineItem
                v-for="(item, index) in timelineItems"
                :key="index"
                :color="item.color"
              >
                <div
                  :class="[
                    'font-medium text-sm',
                    item.pending
                      ? 'text-gray-400'
                      : 'text-gray-900 dark:text-gray-100',
                  ]"
                >
                  {{ item.label }}
                </div>
                <div
                  :class="[
                    'text-xs mt-1',
                    item.pending
                      ? 'text-gray-300'
                      : 'text-gray-500 dark:text-gray-400',
                  ]"
                >
                  {{ item.time }}
                </div>
              </TimelineItem>
            </Timeline>
          </Card>
        </div>
      </div>
    </div>
  </Modal>
</template>
