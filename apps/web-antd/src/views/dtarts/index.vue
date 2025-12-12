<script lang="ts" setup>
import { ref, watch } from 'vue';
import { useRoute } from 'vue-router';

import { IFrame, Page } from '@vben/common-ui';

defineOptions({ name: 'DtartsVizs' });

const route = useRoute();
const src = ref('');

// 根据路由设置iframe src
const setIframeSrc = () => {
  // 支持本地和测试环境
  const localBaseUrl =
    'http://localhost:3000/organizations/127d315a9e1f499590edc151db7ba353';
  const testBaseUrl =
    'http://192.168.66.96:8080/organizations/127d315a9e1f499590edc151db7ba353';

  // 根据当前域名或环境选择baseUrl
  const isLocalEnv =
    window.location.hostname === 'localhost' ||
    window.location.hostname === '127.0.0.1';
  const baseUrl = isLocalEnv ? localBaseUrl : testBaseUrl;

  const path = route.path;

  if (path.includes('vizs')) {
    src.value = `${baseUrl}/vizs`;
  } else if (path.includes('views')) {
    src.value = `${baseUrl}/views`;
  } else if (path.includes('sources')) {
    src.value = `${baseUrl}/sources`;
  } else if (path.includes('schedules')) {
    src.value = `${baseUrl}/schedules`;
  } else if (path.includes('variables')) {
    src.value = `${baseUrl}/variables`;
  } else {
    // 默认加载vizs页面
    src.value = `${baseUrl}/vizs`;
  }
};

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
