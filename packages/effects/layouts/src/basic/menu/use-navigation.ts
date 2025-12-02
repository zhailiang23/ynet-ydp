import type { RouteRecordNormalized } from 'vue-router';

import { useRouter } from 'vue-router';

import { isHttpUrl, openRouteInNewWindow, openWindow } from '@vben/utils';

function useNavigation() {
  const router = useRouter();
  const routeMetaMap = new Map<string, RouteRecordNormalized>();

  // 初始化路由映射
  const initRouteMetaMap = () => {
    const routes = router.getRoutes();
    routes.forEach((route) => {
      routeMetaMap.set(route.path, route);
    });
  };

  initRouteMetaMap();

  // 监听路由变化
  router.afterEach(() => {
    initRouteMetaMap();
  });

  // 检查是否应该在新窗口打开
  const shouldOpenInNewWindow = (path: string): boolean => {
    if (isHttpUrl(path)) {
      return true;
    }
    const route = routeMetaMap.get(path);
    // 如果有外链或者设置了在新窗口打开，返回 true
    return !!(route?.meta?.link || route?.meta?.openInNewWindow);
  };

  const resolveHref = (path: string): string => {
    return router.resolve(path).href;
  };

  const navigation = (path: string) => {
    // 检查路径中是否包含 HTTP URL (可能被错误地添加了前缀)
    // 例如: /twins/http://localhost:10086 -> http://localhost:10086
    const httpMatch = path.match(/(https?:\/\/.+)$/);
    if (httpMatch) {
      // 提取出真正的 HTTP URL 并在新窗口打开 (同步执行,避免被浏览器阻止)
      openWindow(httpMatch[1], { target: '_blank' });
      return;
    }

    // 优先检查路径本身是否是 HTTP URL,如果是则直接在新窗口打开
    if (isHttpUrl(path)) {
      // 同步执行,避免被浏览器阻止弹窗
      openWindow(path, { target: '_blank' });
      return;
    }

    const route = routeMetaMap.get(path);
    const { openInNewWindow = false, query = {}, link } = route?.meta ?? {};

    // 检查是否有外链
    if (link && typeof link === 'string') {
      openWindow(link, { target: '_blank' });
      return;
    }

    if (openInNewWindow) {
      openRouteInNewWindow(resolveHref(path));
    } else {
      // 只有路由导航才需要 async
      router.push({
        path,
        query,
      }).catch((error) => {
        console.error('Navigation failed:', error);
      });
    }
  };

  const willOpenedByWindow = (path: string) => {
    return shouldOpenInNewWindow(path);
  };

  return { navigation, willOpenedByWindow };
}

export { useNavigation };
