<script lang="ts" setup>
import { onMounted } from 'vue';
import { useRouter } from 'vue-router';

import { useUserStore } from '@vben/stores';

const router = useRouter();
const userStore = useUserStore();

onMounted(async () => {
  const userId = userStore.userInfo?.id;
  if (userId) {
    // 重定向到客服对话页面,使用当前用户 ID
    await router.replace({
      name: 'TwinsChat',
      params: { adminId: userId.toString() },
    });
  } else {
    // 如果没有用户信息,重定向到登录页
    await router.push('/login');
  }
});
</script>

<template>
  <div class="flex h-full items-center justify-center">
    <div class="text-center">
      <div class="mb-4 text-lg">正在加载客服面板...</div>
    </div>
  </div>
</template>
