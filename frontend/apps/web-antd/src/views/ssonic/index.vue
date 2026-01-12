<script lang="ts" setup>
import { onMounted, onUnmounted, ref, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';

import { IFrame, Page } from '@vben/common-ui';

defineOptions({ name: 'SsonicIndex' });

const router = useRouter();

const route = useRoute();
const src = ref('');

// 处理来自iframe的postMessage消息
const handleIframeMessage = (event: MessageEvent) => {
  // 验证消息来源的域名，防止恶意消息
  const allowedOrigins = ['http://localhost:9000', 'http://192.168.66.96:9082'];
  if (
    allowedOrigins.includes(event.origin) &&
    event.data &&
    event.data.type === 'ROUTE_CHANGE'
  ) {
    /* console.log('iframe内部路由变化--');
    console.log('fullPath:', event.data.fullPath);
    console.log('pathname:', event.data.pathname); */

    // 解析src.value的URL，获取路径部分
    let srcPathname = '';
    try {
      srcPathname = new URL(src.value).pathname;
    } catch (error) {
      console.error('解析src.value失败:', error);
    }

    if (
      event.data.pathname.startsWith('/webapp/model/domain') &&
      srcPathname.startsWith('/webapp/metric')
    ) {
      // 根据event.data.pathname中包含的路径决定跳转路径
      router.push('/ssonic/model');
    }
  }
};

// 根据路由设置iframe src
const setIframeSrc = () => {
  // 直接使用硬编码方式支持本地和测试环境
  const localBaseUrl = 'http://localhost:9000/webapp';
  const fatBaseUrl = 'http://192.168.66.96:9082/webapp';

  // 根据当前域名或环境选择baseUrl
  const isLocalEnv =
    window.location.hostname === 'localhost' ||
    window.location.hostname === '127.0.0.1';
  const baseUrl = isLocalEnv ? localBaseUrl : fatBaseUrl;

  // 获取用户名
  const username = localStorage.getItem('user') || 'admin';
  // 构建URL参数
  const params = new URLSearchParams({ username });

  const path = route.path;
  // 使用映射对象确定目标路径，提高可维护性
  const targetPathMap: Record<string, string> = {
    database: 'database',
    metric: 'metric',
    model: 'model',
  };

  // 查找匹配的目标路径，默认使用metric
  const targetPath =
    Object.keys(targetPathMap).find((key) => path.includes(key)) || 'metric';
  src.value = `${baseUrl}/${targetPath}?${params.toString()}`;
};

// 监听路由变化
watch(
  () => route.path,
  () => {
    setIframeSrc();
  },
  { immediate: true },
);

// 组件挂载后添加postMessage监听器
onMounted(() => {
  window.addEventListener('message', handleIframeMessage);
});

// 组件卸载时移除postMessage监听器
onUnmounted(() => {
  window.removeEventListener('message', handleIframeMessage);
});
</script>

<template>
  <Page auto-content-height>
    <IFrame :src="src" />
  </Page>
</template>
