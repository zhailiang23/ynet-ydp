<script lang="ts" setup>
import type { InfraChatRobotApi } from '#/api/infra/chatrobot';

import { computed, onBeforeUnmount, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { closeConversation, getConversationPage, getMessagePage, getUnreadCount, markMessageRead, sendMessage as sendMessageApi } from '#/api/infra/chatrobot';

const emit = defineEmits(['success']);

// 当前机器人信息
const currentRobot = ref<InfraChatRobotApi.ChatRobot>();

// 对话列表数据
const conversations = ref<InfraChatRobotApi.Conversation[]>([]);
const conversationLoading = ref(false);
const conversationTotal = ref(0);
const conversationPageNo = ref(1);
const conversationPageSize = ref(20);

// 当前选中的对话
const selectedConversation = ref<InfraChatRobotApi.Conversation | null>(null);

// 未读消息总数
const totalUnreadCount = ref(0);

// 消息列表数据
const messages = ref<InfraChatRobotApi.Message[]>([]);
const messageLoading = ref(false);
const messageTotal = ref(0);
const messagePageNo = ref(1);
const messagePageSize = ref(50);

// 消息输入框
const messageInput = ref('');
const sendingMessage = ref(false);

// 轮询定时器
let pollingTimer: ReturnType<typeof setInterval> | null = null;

// 搜索和筛选状态
const searchKeyword = ref('');
const conversationTypeFilter = ref<number | null>(null); // null=全部, 1=单聊, 2=群聊
const showUnreadOnly = ref(false);

// 过滤后的对话列表
const filteredConversations = computed(() => {
  let result = conversations.value;

  // 搜索过滤
  if (searchKeyword.value) {
    const keyword = searchKeyword.value.toLowerCase();
    result = result.filter(conv =>
      conv.conversationTitle?.toLowerCase().includes(keyword) ||
      conv.senderName?.toLowerCase().includes(keyword) ||
      conv.lastMessageContent?.toLowerCase().includes(keyword)
    );
  }

  // 对话类型过滤
  if (conversationTypeFilter.value !== null) {
    result = result.filter(conv => conv.conversationType === conversationTypeFilter.value);
  }

  // 未读过滤
  if (showUnreadOnly.value) {
    result = result.filter(conv => conv.unreadCount && conv.unreadCount > 0);
  }

  return result;
});

// 加载对话列表
async function loadConversations() {
  if (!currentRobot.value?.id) return;

  conversationLoading.value = true;
  try {
    const result = await getConversationPage({
      robotId: currentRobot.value.id,
      pageNo: conversationPageNo.value,
      pageSize: conversationPageSize.value,
    });
    conversations.value = result.list || [];
    conversationTotal.value = result.total || 0;
  } finally {
    conversationLoading.value = false;
  }
}

// 加载未读消息数
async function loadUnreadCount() {
  if (!currentRobot.value?.id) return;

  try {
    totalUnreadCount.value = await getUnreadCount(currentRobot.value.id);
  } catch (error) {
    console.error('获取未读数失败:', error);
  }
}

// 加载消息列表
async function loadMessages() {
  // 保存当前对话的引用,避免异步过程中引用被清空
  const conversation = selectedConversation.value;
  if (!conversation?.conversationId) return;

  messageLoading.value = true;
  try {
    const result = await getMessagePage({
      conversationId: conversation.conversationId,
      pageNo: messagePageNo.value,
      pageSize: messagePageSize.value,
    });
    messages.value = result.list || [];
    messageTotal.value = result.total || 0;

    // 标记消息已读
    if (messages.value.length > 0) {
      await markMessageRead(conversation.conversationId);
      // 重新加载对话列表以更新未读数
      await loadConversations();
      await loadUnreadCount();
    }
  } catch (error) {
    console.error('加载消息失败:', error);
    // 只有在还有选中的对话时才显示错误提示(避免在清理时显示无意义的错误)
    if (selectedConversation.value?.conversationId) {
      message.error('加载消息失败');
    }
  } finally {
    messageLoading.value = false;
  }
}

// 选择对话
async function selectConversation(conversation: InfraChatRobotApi.Conversation) {
  selectedConversation.value = conversation;
  messagePageNo.value = 1; // 重置页码
  await loadMessages();
}

// 发送消息
async function sendMessage() {
  if (!messageInput.value.trim() || !selectedConversation.value?.conversationId || !currentRobot.value?.id) {
    return;
  }

  sendingMessage.value = true;
  try {
    await sendMessageApi({
      robotId: currentRobot.value.id,
      conversationId: selectedConversation.value.conversationId,
      content: messageInput.value.trim(),
      messageType: 'text',
    });
    message.success('消息已发送');
    messageInput.value = '';
    // 重新加载消息列表和对话列表
    await Promise.all([loadMessages(), loadConversations()]);
  } catch (error) {
    message.error('消息发送失败');
    console.error('发送消息失败:', error);
  } finally {
    sendingMessage.value = false;
  }
}

// 关闭对话
async function handleCloseConversation() {
  if (!selectedConversation.value?.id) {
    return;
  }

  try {
    await closeConversation(selectedConversation.value.id);
    message.success('对话已关闭');
    // 清空当前选中的对话
    selectedConversation.value = null;
    // 重新加载对话列表
    await loadConversations();
  } catch (error) {
    message.error('关闭对话失败');
    console.error('关闭对话失败:', error);
  }
}

// 开始轮询
function startPolling() {
  // 每3秒轮询一次
  pollingTimer = setInterval(() => {
    // 只在页面可见时轮询
    if (!document.hidden) {
      loadConversations();
      loadUnreadCount();
    }
  }, 3000);
}

// 停止轮询
function stopPolling() {
  if (pollingTimer) {
    clearInterval(pollingTimer);
    pollingTimer = null;
  }
}

// 处理可见性变化
function handleVisibilityChange() {
  if (document.hidden) {
    stopPolling();
  } else {
    startPolling();
  }
}

const [Modal, modalApi] = useVbenModal({
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      // 清理数据
      currentRobot.value = undefined;
      conversations.value = [];
      selectedConversation.value = null;
      totalUnreadCount.value = 0;
      conversationPageNo.value = 1;
      stopPolling();
      document.removeEventListener('visibilitychange', handleVisibilityChange);
      return;
    }

    // 获取传入的机器人数据
    const data = modalApi.getData<{ robot?: InfraChatRobotApi.ChatRobot }>();
    if (data?.robot) {
      currentRobot.value = data.robot;
      // 加载对话列表和未读数
      await Promise.all([loadConversations(), loadUnreadCount()]);
      // 开始轮询
      startPolling();
      // 监听可见性变化
      document.addEventListener('visibilitychange', handleVisibilityChange);
    }
  },
});

onBeforeUnmount(() => {
  stopPolling();
  document.removeEventListener('visibilitychange', handleVisibilityChange);
});

defineExpose({ modalApi });
</script>

<template>
  <Modal
    :title="`${currentRobot?.name || '机器人'} - 对话管理`"
    class="conversation-modal !w-[1200px] !h-[85vh] !max-h-[85vh]"
    :footer="false"
  >
    <div class="flex h-[calc(85vh-100px)] overflow-hidden rounded-lg border border-gray-200 dark:border-gray-700">
      <!-- 左侧对话列表 (400px) -->
      <div class="flex h-full w-[400px] flex-col overflow-hidden border-r border-gray-200 dark:border-gray-700">
        <!-- 搜索和筛选区 -->
        <div class="border-b border-gray-200 p-4 dark:border-gray-700">
          <a-input
            v-model:value="searchKeyword"
            placeholder="搜索对话..."
            allow-clear
            class="mb-3"
          >
            <template #prefix>
              <i class="i-ant-design:search-outlined" />
            </template>
          </a-input>
          <div class="flex gap-2">
            <a-button
              size="small"
              :type="conversationTypeFilter === null && !showUnreadOnly ? 'primary' : 'default'"
              @click="conversationTypeFilter = null; showUnreadOnly = false"
            >
              全部
            </a-button>
            <a-button
              size="small"
              :type="conversationTypeFilter === 1 ? 'primary' : 'default'"
              @click="conversationTypeFilter = 1; showUnreadOnly = false"
            >
              单聊
            </a-button>
            <a-button
              size="small"
              :type="conversationTypeFilter === 2 ? 'primary' : 'default'"
              @click="conversationTypeFilter = 2; showUnreadOnly = false"
            >
              群聊
            </a-button>
            <a-button
              size="small"
              :type="showUnreadOnly ? 'primary' : 'default'"
              @click="showUnreadOnly = !showUnreadOnly; conversationTypeFilter = null"
            >
              未读
              <a-badge v-if="totalUnreadCount > 0" :count="totalUnreadCount" :offset="[10, 0]" />
            </a-button>
          </div>
        </div>

        <!-- 对话列表 -->
        <div class="flex-1 overflow-y-auto">
          <a-spin :spinning="conversationLoading">
            <div v-if="filteredConversations.length === 0" class="p-8 text-center text-gray-400">
              暂无对话
            </div>
            <div
              v-for="conversation in filteredConversations"
              :key="conversation.id"
              :class="[
                'cursor-pointer border-b border-gray-100 p-4 transition-colors hover:bg-gray-50',
                'dark:border-gray-800 dark:hover:bg-gray-800/50',
                selectedConversation?.id === conversation.id && 'bg-blue-50 dark:bg-blue-950/30',
              ]"
              @click="selectConversation(conversation)"
            >
              <div class="mb-2 flex items-center justify-between">
                <div class="flex items-center gap-2">
                  <span class="font-medium text-gray-900 dark:text-gray-100">
                    {{ conversation.conversationTitle || '未命名对话' }}
                  </span>
                  <a-tag v-if="conversation.conversationType === 1" size="small" color="blue">
                    单聊
                  </a-tag>
                  <a-tag v-else size="small" color="green">
                    群聊
                  </a-tag>
                </div>
                <span class="text-xs text-gray-400">
                  {{ conversation.lastMessageTime ? new Date(conversation.lastMessageTime).toLocaleString('zh-CN', { month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit' }) : '' }}
                </span>
              </div>
              <div class="flex items-center justify-between">
                <div class="flex-1 truncate text-sm text-gray-600 dark:text-gray-400">
                  {{ conversation.lastMessageContent || '暂无消息' }}
                </div>
                <a-badge
                  v-if="conversation.unreadCount && conversation.unreadCount > 0"
                  :count="conversation.unreadCount"
                  :overflow-count="99"
                  class="ml-2"
                />
              </div>
            </div>
          </a-spin>
        </div>

        <!-- 分页 -->
        <div class="border-t border-gray-200 p-2 dark:border-gray-700">
          <a-pagination
            v-model:current="conversationPageNo"
            v-model:page-size="conversationPageSize"
            :total="conversationTotal"
            :show-total="(total) => `共 ${total} 个对话`"
            :show-size-changer="false"
            size="small"
            simple
            @change="loadConversations"
          />
        </div>
      </div>

      <!-- 右侧消息区域 (800px) -->
      <div class="flex h-full flex-1 flex-col overflow-hidden">
        <div v-if="!selectedConversation" class="flex h-full items-center justify-center text-gray-400">
          <div class="text-center">
            <i class="i-ant-design:message-outlined mb-4 text-6xl" />
            <p>请选择一个对话开始聊天</p>
          </div>
        </div>
        <div v-else class="flex h-full flex-col">
          <!-- 对话标题栏 -->
          <div class="flex items-center justify-between border-b border-gray-200 p-4 dark:border-gray-700">
            <div>
              <h3 class="text-lg font-medium text-gray-900 dark:text-gray-100">
                {{ selectedConversation.conversationTitle || '未命名对话' }}
              </h3>
              <p class="text-sm text-gray-500 dark:text-gray-400">
                {{ selectedConversation.senderName || '未知用户' }}
                <span v-if="selectedConversation.totalMessageCount">
                  · {{ selectedConversation.totalMessageCount }} 条消息
                </span>
              </p>
            </div>
            <a-button type="text" danger size="small" @click="handleCloseConversation">
              <template #icon>
                <i class="i-ant-design:close-circle-outlined" />
              </template>
              关闭对话
            </a-button>
          </div>

          <!-- 消息列表区域 -->
          <div class="flex-1 overflow-y-auto bg-gray-50 p-4 dark:bg-gray-900/50">
            <a-spin :spinning="messageLoading">
              <div v-if="messages.length === 0" class="flex h-full items-center justify-center text-gray-400">
                <p>暂无消息</p>
              </div>
              <div v-else class="space-y-4">
                <div
                  v-for="msg in messages"
                  :key="msg.id"
                  :class="[
                    'flex',
                    msg.source === 1 ? 'justify-end' : 'justify-start',
                  ]"
                >
                  <div
                    :class="[
                      'max-w-[70%] rounded-lg px-4 py-2',
                      msg.source === 1
                        ? 'bg-blue-500 text-white'
                        : msg.source === 2
                        ? 'bg-yellow-100 text-yellow-800 dark:bg-yellow-900/30 dark:text-yellow-200'
                        : 'bg-white text-gray-900 dark:bg-gray-800 dark:text-gray-100',
                    ]"
                  >
                    <div class="mb-1 text-xs opacity-70">
                      {{ msg.senderName || '未知用户' }}
                      · {{ msg.sendTime ? new Date(msg.sendTime).toLocaleString('zh-CN') : '' }}
                    </div>
                    <div class="break-words">{{ msg.content }}</div>
                  </div>
                </div>
              </div>
            </a-spin>
          </div>

          <!-- 输入框区域 -->
          <div class="border-t border-gray-200 p-4 dark:border-gray-700">
            <div class="mb-2 flex gap-2">
              <a-button type="text" size="small">
                <template #icon>
                  <i class="i-ant-design:smile-outlined" />
                </template>
              </a-button>
              <a-button type="text" size="small">
                <template #icon>
                  <i class="i-ant-design:picture-outlined" />
                </template>
              </a-button>
            </div>
            <div class="flex gap-2">
              <a-textarea
                v-model:value="messageInput"
                placeholder="输入消息..."
                :rows="3"
                :max-length="1000"
                show-count
                class="flex-1"
                @keydown.ctrl.enter="sendMessage"
              />
              <a-button type="primary" @click="sendMessage">
                发送
              </a-button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </Modal>
</template>

<style>
/* 覆盖 Vben Modal 的默认内容容器滚动行为 - 使用全局样式确保优先级 */
.conversation-modal .relative.min-h-40.flex-1.overflow-y-auto {
  overflow: hidden !important;
  padding: 0 !important;
  height: calc(85vh - 100px) !important;
  flex: none !important;
  min-height: calc(85vh - 100px) !important;
}
</style>
