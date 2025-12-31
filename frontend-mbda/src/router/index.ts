/**
 * Vue Router 配置
 */
import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import { useUserStore } from '@/store/user'

const routes: RouteRecordRaw[] = [
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/auth/login.vue'),
    meta: {
      title: '登录',
      requiresAuth: false,
    },
  },
  {
    path: '/',
    name: 'Home',
    component: () => import('@/views/home/index.vue'),
    meta: {
      title: '银行工作台',
      requiresAuth: false,
    },
  },
  {
    path: '/tasks',
    name: 'TaskList',
    component: () => import('@/views/tasks/index.vue'),
    meta: {
      title: '全部待办',
      requiresAuth: false, // 暂时不需要登录，便于测试
    },
  },
  {
    path: '/tasks/:id',
    name: 'TaskDetail',
    component: () => import('@/views/tasks/detail.vue'),
    meta: {
      title: '任务详情',
      requiresAuth: false,
    },
  },
  {
    path: '/products',
    name: 'ProductList',
    component: () => import('@/views/products/index.vue'),
    meta: {
      title: '产品中心',
      requiresAuth: false, // 移动端产品展示无需登录
    },
  },
  {
    path: '/product/:id',
    name: 'ProductDetail',
    component: () => import('@/views/products/detail.vue'),
    meta: {
      title: '产品详情',
      requiresAuth: false, // 移动端产品详情无需登录
    },
  },
  {
    path: '/products/compare',
    name: 'ProductCompare',
    component: () => import('@/views/products/compare.vue'),
    meta: {
      title: '产品对比',
      requiresAuth: false, // 移动端产品对比无需登录
    },
  },
  {
    path: '/insights/detail',
    name: 'InsightDetail',
    component: () => import('@/views/insights/detail.vue'),
    meta: {
      title: '每日洞察详情',
      requiresAuth: false, // 移动端洞察详情无需登录
    },
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes,
})

// 全局前置守卫
router.beforeEach((to, from, next) => {
  // 设置页面标题
  if (to.meta.title) {
    document.title = `${to.meta.title} - 金融产品展业平台`
  }

  const userStore = useUserStore()

  // 如果是登录页，且已登录，跳转到首页
  if (to.path === '/login' && userStore.token) {
    next('/')
    return
  }

  // 检查是否需要登录
  if (to.meta.requiresAuth) {
    if (!userStore.token) {
      // 未登录，跳转到登录页
      next({
        path: '/login',
        query: { redirect: to.fullPath }, // 保存原始路径，登录后跳转
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
