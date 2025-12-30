/**
 * Vue Router 配置
 */
import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import { useUserStore } from '@/store/user'

const routes: RouteRecordRaw[] = [
  {
    path: '/',
    name: 'ProductList',
    component: () => import('@/views/products/index.vue'),
    meta: {
      title: '金融产品',
      requiresAuth: false, // 暂时不需要登录
    },
  },
  {
    path: '/product/:id',
    name: 'ProductDetail',
    component: () => import('@/views/products/detail.vue'),
    meta: {
      title: '产品详情',
      requiresAuth: false,
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
    document.title = `${to.meta.title} - ${import.meta.env.VITE_APP_TITLE}`
  }

  // 检查是否需要登录
  if (to.meta.requiresAuth) {
    const userStore = useUserStore()
    if (!userStore.token) {
      // 未登录，跳转到登录页（暂时不实现）
      console.warn('需要登录')
      next()
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
