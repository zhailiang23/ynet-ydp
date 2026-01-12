<script lang="ts" setup>
import type { RouteLocationNormalized } from 'vue-router';

import { computed, ref } from 'vue';
import { useRoute } from 'vue-router';

import { preferences, usePreferences } from '@vben/preferences';
import { useTabbarStore } from '@vben/stores';

import { VbenSpinner } from '@vben-core/shadcn-ui';

defineOptions({ name: 'IFrameRouterView' });

const spinningList = ref<boolean[]>([]);
const tabbarStore = useTabbarStore();
const route = useRoute();

// 获取主题信息
const { theme } = usePreferences();

const enableTabbar = computed(() => preferences.tabbar.enable);

const iframeRoutes = computed(() => {
  if (!enableTabbar.value) {
    return route.meta.iframeSrc ? [route] : [];
  }
  return tabbarStore.getTabs.filter((tab) => !!tab.meta?.iframeSrc);
});

const tabNames = computed(
  () => new Set(iframeRoutes.value.map((item) => item.name as string)),
);

const showIframe = computed(() => iframeRoutes.value.length > 0);

function routeShow(tabItem: RouteLocationNormalized) {
  return tabItem.name === route.name;
}

function canRender(tabItem: RouteLocationNormalized) {
  const { meta, name } = tabItem;

  if (!name || !tabbarStore.renderRouteView) {
    return false;
  }

  if (!enableTabbar.value) {
    return routeShow(tabItem);
  }

  // 跟随 keepAlive 状态,与其他tab页保持一致
  if (
    !meta?.keepAlive &&
    tabNames.value.has(name as string) &&
    name !== route.name
  ) {
    return false;
  }
  return tabbarStore.getTabs.some((tab) => tab.name === name);
}

function hideLoading(index: number) {
  spinningList.value[index] = false;
}

function showSpinning(index: number) {
  const curSpinning = spinningList.value[index];
  // 首次加载时显示loading
  return curSpinning === undefined ? true : curSpinning;
}

// 构建带有主题参数的 iframe URL
const getIframeSrc = computed(() => {
  return (item: RouteLocationNormalized) => {
    const baseSrc = item.meta?.iframeSrc;
    if (!baseSrc) {
      return '';
    }

    try {
      const url = new URL(baseSrc);
      url.searchParams.set('theme', theme.value);
      url.searchParams.set('hideTitle', 'true');
      return url.toString();
    } catch {
      // 如果 URL 解析失败，返回原始 URL
      return baseSrc;
    }
  };
});
</script>
<template>
  <template v-if="showIframe">
    <template v-for="(item, index) in iframeRoutes" :key="item.fullPath">
      <div
        v-if="canRender(item)"
        v-show="routeShow(item)"
        class="relative size-full"
      >
        <VbenSpinner :spinning="showSpinning(index)" />
        <iframe
          :src="getIframeSrc(item)"
          class="size-full"
          @load="hideLoading(index)"
        ></iframe>
      </div>
    </template>
  </template>
</template>
