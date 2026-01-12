<script lang="ts" setup>
import { onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

import { message } from 'ant-design-vue';

const route = useRoute();
const chatUrl = ref<string>('');
const loading = ref(true);

/** Chat 服务地址 (从环境变量读取) */
const CHAT_BACKEND_URL = import.meta.env.VITE_CHAT_BACKEND_URL || 'http://localhost:8080';
const CHAT_FRONTEND_URL = import.meta.env.VITE_CHAT_FRONTEND_URL || 'http://localhost:8000';

/** 通过 ID 登录获取 token */
async function loginById(adminId: number): Promise<string> {
  const response = await fetch(`${CHAT_BACKEND_URL}/api/backend/login-by-id`, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ id: adminId }),
  });
  const data = await response.json();
  if (data.code !== 0) {
    throw new Error(data.message || '登录失败');
  }
  return data.data.token;
}

onMounted(async () => {
  try {
    const adminId = Number(route.params.adminId);
    if (!adminId) {
      message.error('客服 ID 无效');
      return;
    }

    // 获取 token
    const token = await loginById(adminId);

    // 构建聊天 URL
    chatUrl.value = `${CHAT_FRONTEND_URL}/chat?token=${encodeURIComponent(token)}`;
    loading.value = false;
  } catch (error: any) {
    message.error(error.message || '加载聊天界面失败');
    loading.value = false;
  }
});
</script>

<template>
  <div class="h-full w-full">
    <div v-if="loading" class="flex h-full items-center justify-center">
      <a-spin size="large" tip="正在加载聊天界面..." />
    </div>
    <iframe
      v-else
      :src="chatUrl"
      class="h-full w-full border-0"
      title="客服对话"
    />
  </div>
</template>
