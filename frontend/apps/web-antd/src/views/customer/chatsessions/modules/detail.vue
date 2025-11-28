<script lang="ts" setup>
import type { CustomerChatSessionsApi } from '#/api/customer/chatsessions';

import { computed, ref, watch } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { formatDateTime } from '@vben/utils';

import {
  Card,
  Col,
  Descriptions,
  DescriptionsItem,
  Image,
  Row,
  Spin,
  Timeline,
  TimelineItem,
} from 'ant-design-vue';

import { getChatSessionDetail } from '#/api/customer/chatsessions';

const [Modal, modalApi] = useVbenModal({
  onOpenChange(isOpen) {
    if (isOpen) {
      const data = modalApi.getData<{ id: number }>();
      if (data?.id) {
        loadDetail(data.id);
      }
    }
  },
});

const loading = ref(false);
const detail = ref<CustomerChatSessionsApi.ChatSessionDetail | null>(null);

async function loadDetail(id: number) {
  loading.value = true;
  try {
    detail.value = await getChatSessionDetail(id);
  } finally {
    loading.value = false;
  }
}

// 会话类型文本
const sessionTypeText = computed(() => {
  if (!detail.value?.session) return '';
  return detail.value.session.type === 0 ? '普通会话' : '转接会话';
});

// 获取消息来源颜色
function getMessageColor(source: number) {
  switch (source) {
    case 0:
      return 'red'; // 用户
    case 1:
      return 'green'; // 客服
    case 2:
      return 'blue'; // 系统
    default:
      return 'gray';
  }
}

// 获取消息位置
function getMessagePosition(source: number) {
  return source === 0 ? 'left' : 'right';
}

// 渲染消息内容
function renderMessageContent(message: CustomerChatSessionsApi.ChatMessage) {
  switch (message.type) {
    case 'image':
      return message.content;
    case 'video':
    case 'audio':
    case 'pdf':
      return message.content;
    default:
      return message.content;
  }
}
</script>

<template>
  <Modal title="会话详情" class="w-4/5" :show-confirm-button="false">
    <Spin :spinning="loading">
      <div v-if="detail" class="session-detail">
        <Row :gutter="16">
          <!-- 左侧消息时间线 -->
          <Col :span="15">
            <Card title="消息记录" :bordered="false" size="small">
              <div class="message-timeline">
                <Timeline mode="alternate">
                  <TimelineItem
                    v-for="msg in detail.messages"
                    :key="msg.id"
                    :color="getMessageColor(msg.source)"
                    :position="getMessagePosition(msg.source)"
                  >
                    <div class="message-item">
                      <div class="message-time text-gray-500 text-xs">
                        {{ formatDateTime(msg.receivedAt) }}
                      </div>
                      <div class="message-content mt-1">
                        <!-- 图片消息 -->
                        <Image
                          v-if="msg.type === 'image'"
                          :src="msg.content"
                          :width="120"
                          :preview="{ src: msg.content }"
                        />
                        <!-- 文件链接 -->
                        <a
                          v-else-if="
                            ['video', 'audio', 'pdf'].includes(msg.type)
                          "
                          :href="msg.content"
                          target="_blank"
                          class="text-blue-500"
                        >
                          [{{ msg.type === 'video' ? '视频' : msg.type === 'audio' ? '音频' : 'PDF' }}文件]
                        </a>
                        <!-- 文本消息 -->
                        <span v-else class="whitespace-pre-wrap">{{
                          msg.content
                        }}</span>
                      </div>
                    </div>
                  </TimelineItem>
                </Timeline>
                <div
                  v-if="!detail.messages?.length"
                  class="text-center text-gray-400 py-8"
                >
                  暂无消息记录
                </div>
              </div>
            </Card>
          </Col>

          <!-- 右侧会话信息 -->
          <Col :span="9">
            <Card title="会话信息" :bordered="false" size="small">
              <Descriptions :column="1" size="small">
                <DescriptionsItem label="用户 ID">
                  {{ detail.session.userId }}
                </DescriptionsItem>
                <DescriptionsItem label="询问时间">
                  {{ formatDateTime(detail.session.queriedAt) }}
                </DescriptionsItem>
                <DescriptionsItem v-if="detail.session.adminId" label="客服 ID">
                  {{ detail.session.adminId }}
                </DescriptionsItem>
                <DescriptionsItem
                  v-if="detail.session.acceptedAt"
                  label="接入时间"
                >
                  {{ formatDateTime(detail.session.acceptedAt) }}
                </DescriptionsItem>
                <DescriptionsItem
                  v-if="detail.session.brokenAt"
                  label="断开时间"
                >
                  {{ formatDateTime(detail.session.brokenAt) }}
                </DescriptionsItem>
                <DescriptionsItem
                  v-if="detail.session.canceledAt"
                  label="取消时间"
                >
                  {{ formatDateTime(detail.session.canceledAt) }}
                </DescriptionsItem>
                <DescriptionsItem label="会话类型">
                  {{ sessionTypeText }}
                </DescriptionsItem>
                <DescriptionsItem
                  v-if="detail.session.rate !== null"
                  label="评分"
                >
                  {{ detail.session.rate }}
                </DescriptionsItem>
              </Descriptions>
            </Card>
          </Col>
        </Row>
      </div>
    </Spin>
  </Modal>
</template>

<style scoped>
.session-detail {
  min-height: 400px;
}

.message-timeline {
  max-height: 500px;
  overflow-y: auto;
  padding: 16px 0;
}

.message-item {
  display: inline-block;
}

.message-content {
  word-break: break-word;
}

:deep(.ant-timeline-item-left .ant-timeline-item-content) {
  text-align: left;
}

:deep(.ant-timeline-item-right .ant-timeline-item-content) {
  text-align: right;
}
</style>
