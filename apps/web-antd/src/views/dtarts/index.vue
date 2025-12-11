<script lang="ts" setup>
import { ref, watch, onMounted } from 'vue';
import { useRoute } from 'vue-router';

import { DocAlert, IFrame, Page } from '@vben/common-ui';
import { useAccessStore } from '@vben/stores';

defineOptions({ name: 'DtartsVizs' });

const route = useRoute();
const src = ref('');

// 根据路由设置iframe src
const setIframeSrc = () => {
  // 直接使用硬编码方式支持本地环境
  const localBaseUrl = 'http://localhost:3000/organizations/127d315a9e1f499590edc151db7ba353';
  
  const path = route.path;

  if (path.includes('vizs')) {
    src.value = `${localBaseUrl}/vizs`;
  } else if (path.includes('views')) {
    src.value = `${localBaseUrl}/views`;
  } else if (path.includes('sources')) {
    src.value = `${localBaseUrl}/sources`;
  } else if (path.includes('schedules')) {
    src.value = `${localBaseUrl}/schedules`;
  } else if (path.includes('variables')) {
    src.value = `${localBaseUrl}/variables`;
  } else {
    // 默认加载vizs页面
    src.value = `${localBaseUrl}/vizs`;
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
