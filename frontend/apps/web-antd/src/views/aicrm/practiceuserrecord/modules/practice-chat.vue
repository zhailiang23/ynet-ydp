<script lang="ts" setup>
import type { AicrmPracticeConversationApi } from '#/api/aicrm/practiceconversation';

import { computed, nextTick, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { useUserStore } from '@vben/stores';
import { formatDate } from '@vben/utils';

import { Avatar, Button, Input, message, Spin } from 'ant-design-vue';
import { SvgGptIcon } from '@vben/icons';

import {
  createPracticeConversation,
  getConversationListByRecordId,
} from '#/api/aicrm/practiceconversation';
import { getPracticeUserRecord } from '#/api/aicrm/practiceuserrecord';
import { getPracticeCourse } from '#/api/aicrm/practicecourse';
import { getPracticeScript } from '#/api/aicrm/practicescript';
import { getPracticeVirtualCustomer } from '#/api/aicrm/practicevirtualcustomer';

// 对话数据
const recordId = ref<number>();
const conversationList = ref<AicrmPracticeConversationApi.PracticeConversation[]>([]);
const loading = ref(false);
const messageContainer = ref<HTMLDivElement>();

// 动态提示词数据
const courseScript = ref<string>('');
const virtualCustomerName = ref<string>('客户');
const virtualCustomerProfile = ref<string>('');

// 输入框
const inputMessage = ref('');
const sending = ref(false);

// 用户信息
const userStore = useUserStore();
const userAvatar = computed(() => userStore.userInfo?.avatar || '/default-avatar.png');

// AI Agent 地址
const AI_AGENT_URL = 'http://localhost:8000/chat';

/** 加载陪练上下文(课程脚本和虚拟客户信息) */
async function loadPracticeContext() {
  if (!recordId.value) {
    return;
  }

  try {
    // 1. 获取用户陪练记录
    console.log('正在加载陪练记录:', recordId.value);
    const record = await getPracticeUserRecord(recordId.value);
    console.log('陪练记录:', record);

    // 2. 获取课程信息
    if (record.courseId) {
      console.log('正在加载课程信息:', record.courseId);
      const course = await getPracticeCourse(record.courseId);
      console.log('课程信息:', course);

      // 3. 如果课程有关联剧本,获取剧本内容
      if (course.scriptId) {
        console.log('正在加载剧本:', course.scriptId);
        const script = await getPracticeScript(course.scriptId);
        console.log('剧本信息:', script);
        // 优先使用手工编辑内容,否则使用AI生成内容
        courseScript.value = script.contentEdit || script.content || '';
        console.log('剧本内容长度:', courseScript.value.length);
      } else {
        console.warn('课程未关联剧本');
      }
    } else {
      console.warn('陪练记录未关联课程');
    }

    // 4. 获取虚拟客户信息
    if (record.vcustomerId) {
      console.log('正在加载虚拟客户:', record.vcustomerId);
      const vcustomer = await getPracticeVirtualCustomer(record.vcustomerId);
      console.log('虚拟客户信息:', vcustomer);
      virtualCustomerName.value = vcustomer.name || '客户';

      // 构建虚拟客户画像
      const profile = [];
      if (vcustomer.age) profile.push(`${vcustomer.age}岁`);
      if (vcustomer.occupation) profile.push(vcustomer.occupation);
      if (vcustomer.industry) profile.push(`${vcustomer.industry}行业`);
      if (vcustomer.personalityType) profile.push(`性格:${vcustomer.personalityType}`);
      if (vcustomer.riskPreference) profile.push(`风险偏好:${vcustomer.riskPreference}`);
      virtualCustomerProfile.value = profile.join(', ');
      console.log('客户画像:', virtualCustomerProfile.value);
    } else {
      console.warn('陪练记录未关联虚拟客户');
    }

    console.log('陪练上下文加载完成:', {
      courseScript: courseScript.value,
      virtualCustomerName: virtualCustomerName.value,
      virtualCustomerProfile: virtualCustomerProfile.value,
    });
  } catch (error) {
    console.error('加载陪练上下文失败:', error);
    message.error('加载陪练上下文失败: ' + error.message);
  }
}

/** 加载历史对话 */
async function loadConversations() {
  if (!recordId.value) {
    return;
  }

  try {
    loading.value = true;
    conversationList.value = await getConversationListByRecordId(recordId.value);
    await scrollToBottom();
  } catch (error) {
    console.error('加载对话历史失败:', error);
    message.error('加载对话历史失败');
  } finally {
    loading.value = false;
  }
}

/** 滚动到底部 */
async function scrollToBottom() {
  await nextTick();
  if (messageContainer.value) {
    messageContainer.value.scrollTop = messageContainer.value.scrollHeight;
  }
}

/** 发送消息给 AI */
async function sendMessage() {
  console.log('sendMessage 被调用, 输入内容:', inputMessage.value);
  if (!inputMessage.value.trim() || !recordId.value) {
    console.log('输入为空或无记录ID,跳过发送');
    return;
  }

  const userMessage = inputMessage.value;
  inputMessage.value = '';
  console.log('输入框已清空, 当前值:', inputMessage.value);
  sending.value = true;

  try {
    // 1. 保存用户消息到数据库
    const nextSequenceNo = (conversationList.value.length || 0) + 1;
    const userConversation: any = {
      recordId: recordId.value,
      sequenceNo: nextSequenceNo,
      role: 'student',
      messageContent: userMessage,
      messageTime: new Date().toISOString(),
    };

    // 立即显示用户消息(乐观更新)
    conversationList.value.push(userConversation);
    await scrollToBottom();

    // 保存用户消息
    const userConvId = await createPracticeConversation(userConversation);
    userConversation.id = userConvId;

    // 2. 构建历史对话 (转换为 AI 需要的格式)
    const history = conversationList.value.map((conv) => ({
      role: conv.role === 'student' ? 'user' : 'assistant',
      content: conv.messageContent,
    }));

    // 3. 调用 AI Agent (传递动态上下文和历史对话)
    const response = await fetch(AI_AGENT_URL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        message: userMessage,
        stream: false,
        // 动态提示词参数
        course_script: courseScript.value,
        virtual_customer_name: virtualCustomerName.value,
        virtual_customer_profile: virtualCustomerProfile.value,
        // 历史对话
        history: history,
      }),
    });

    if (!response.ok) {
      throw new Error(`AI 响应失败: ${response.statusText}`);
    }

    const data = await response.json();
    const aiResponse = data.response || '抱歉,我无法回答';

    // 4. 保存 AI 响应到数据库
    const aiConversation: any = {
      recordId: recordId.value,
      sequenceNo: nextSequenceNo + 1,
      role: 'virtual_customer',
      messageContent: aiResponse,
      messageTime: new Date().toISOString(),
    };

    conversationList.value.push(aiConversation);
    await scrollToBottom();

    const aiConvId = await createPracticeConversation(aiConversation);
    aiConversation.id = aiConvId;

    message.success('消息发送成功');
  } catch (error) {
    console.error('发送消息失败:', error);
    message.error('发送消息失败,请检查 AI 服务是否启动');
  } finally {
    sending.value = false;
  }
}

/** 处理回车发送 */
function handleKeyPress(e: KeyboardEvent) {
  if (e.key === 'Enter' && !e.shiftKey) {
    e.preventDefault();
    console.log('回车发送,当前输入:', inputMessage.value);
    sendMessage();
  }
}

/** 获取角色名称 */
function getRoleName(role?: string) {
  switch (role) {
    case 'student':
      return '学员';
    case 'virtual_customer':
      return '虚拟客户';
    case 'system':
      return '系统';
    default:
      return '未知';
  }
}

const [Modal, modalApi] = useVbenModal({
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      // 关闭时清空数据
      recordId.value = undefined;
      conversationList.value = [];
      inputMessage.value = '';
      courseScript.value = '';
      virtualCustomerName.value = '客户';
      virtualCustomerProfile.value = '';
      return;
    }
    // 打开时加载数据
    const data = modalApi.getData<{ recordId: number }>();
    if (data?.recordId) {
      recordId.value = data.recordId;
      // 并行加载陪练上下文和历史对话
      await Promise.all([loadPracticeContext(), loadConversations()]);
    }
  },
});
</script>

<template>
  <Modal title="陪练对话" :footer="false" width="800px">
    <div class="flex h-[500px] flex-col">
      <!-- 对话区域 -->
      <div ref="messageContainer" class="flex-1 overflow-y-auto p-4">
        <Spin :spinning="loading">
          <div v-if="conversationList.length === 0" class="text-center text-gray-400">
            暂无对话,开始你的第一次陪练吧!
          </div>
          <div
            v-for="(item, index) in conversationList"
            :key="index"
            class="mb-4 flex"
            :class="item.role === 'student' ? 'flex-row-reverse' : 'flex-row'"
          >
            <!-- 头像 -->
            <div class="flex-shrink-0">
              <Avatar v-if="item.role === 'student'" :src="userAvatar" />
              <SvgGptIcon v-else class="size-8" />
            </div>

            <!-- 消息内容 -->
            <div
              class="mx-3 max-w-[70%]"
              :class="item.role === 'student' ? 'text-right' : 'text-left'"
            >
              <div class="mb-1 text-xs text-gray-400">
                {{ getRoleName(item.role) }} · {{ formatDate(item.messageTime) }}
              </div>
              <div
                class="rounded-lg p-3 shadow-sm"
                :class="
                  item.role === 'student'
                    ? 'bg-blue-500 text-white'
                    : 'bg-gray-100 text-gray-800 dark:bg-gray-700 dark:text-gray-200'
                "
              >
                {{ item.messageContent }}
              </div>
            </div>
          </div>
        </Spin>
      </div>

      <!-- 输入区域 - 固定在底部 -->
      <div class="flex-shrink-0 border-t bg-white p-4 dark:bg-gray-800 dark:border-gray-700">
        <div class="flex gap-2">
          <Input
            v-model:value="inputMessage"
            placeholder="输入你的回答..."
            :disabled="sending"
            size="large"
            @keydown="handleKeyPress"
          />
          <Button type="primary" size="large" :loading="sending" @click="sendMessage">
            发送
          </Button>
        </div>
      </div>
    </div>
  </Modal>
</template>

<style scoped>
/* 自定义滚动条 */
:deep(.ant-spin-container) {
  min-height: 400px;
}
</style>
