<script lang="ts" setup>
import { onMounted, onUnmounted, ref, watch } from 'vue';
import { useRoute } from 'vue-router';

import { IFrame, Page } from '@vben/common-ui';

defineOptions({ name: 'DtartsVizs' });

const route = useRoute();
const src = ref('');
const relId = ref('');

// 存储relId的localStorage键名
const REL_ID_STORAGE_KEY = 'datart_rel_id';

// 处理来自iframe的消息
const handleIframeMessage = (event: MessageEvent) => {
  if (event.data && event.data.type === 'DATART_REL_ID' && event.data.relId) {
    // 存储relId到localStorage
    localStorage.setItem(REL_ID_STORAGE_KEY, event.data.relId);
    // 更新iframe src，添加relId参数
    setIframeSrc();
  }
};

// 根据路由设置iframe src
const setIframeSrc = () => {
  // 支持本地和测试环境
  const localBaseUrl = 'http://localhost:3000/organizations';
  const testBaseUrl = 'http://192.168.66.96:8080/organizations';

  // 根据当前域名或环境选择baseUrl
  const isLocalEnv =
    window.location.hostname === 'localhost' ||
    window.location.hostname === '127.0.0.1';
  const baseUrl = isLocalEnv ? localBaseUrl : testBaseUrl;
  const storedRelId = localStorage.getItem(REL_ID_STORAGE_KEY);
  if (storedRelId) {
    relId.value = storedRelId;
  }

  // 获取当前登录用户的用户名
  const username = 'demo'; // userStore.userInfo?.username || '';
  const path = route.path;
  let iframePath = '';

  if (path.includes('vizs')) {
    iframePath = '/vizs';
  } else if (path.includes('views')) {
    iframePath = '/views';
  } else if (path.includes('sources')) {
    iframePath = '/sources';
  } else if (path.includes('schedules')) {
    iframePath = '/schedules';
  } else if (path.includes('variables')) {
    iframePath = '/variables';
  } else {
    // 默认加载vizs页面
    iframePath = '/vizs';
  }

  // 构建URL参数
  const params = new URLSearchParams();
  params.append('username', encodeURIComponent(username));
  src.value = relId.value
    ? `${baseUrl}/${relId.value}${iframePath}?${params.toString()}`
    : `${baseUrl}?${params.toString()}`;
};

// 组件挂载时添加事件监听器
onMounted(() => {
  window.addEventListener('message', handleIframeMessage);
});

// 组件卸载时移除事件监听器
onUnmounted(() => {
  window.removeEventListener('message', handleIframeMessage);
});

// 监听路由变化
watch(
  () => route.path,
  () => {
    setIframeSrc();
  },
  { immediate: true },
);
</script>

<template>
  <Page auto-content-height>
    <IFrame :src="src" />
  </Page>
</template>
