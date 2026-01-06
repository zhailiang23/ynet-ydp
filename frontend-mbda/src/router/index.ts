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
  {
    path: '/customers',
    name: 'CustomerList',
    component: () => import('@/views/customers/index.vue'),
    meta: {
      title: '客户列表',
      requiresAuth: false, // 移动端客户列表无需登录
    },
  },
  {
    path: '/customer/:id',
    name: 'CustomerDetail',
    component: () => import('@/views/customer/detail.vue'),
    meta: {
      title: 'AI 客户洞察',
      requiresAuth: false, // 移动端客户详情无需登录
    },
  },
  {
    path: '/customer/:id/tasks',
    name: 'CustomerTasks',
    component: () => import('@/views/customer/tasks.vue'),
    meta: {
      title: '智能任务助手',
      requiresAuth: false, // 移动端客户任务无需登录
    },
  },
  {
    path: '/tools',
    name: 'Tools',
    component: () => import('@/views/tools/index.vue'),
    meta: {
      title: '销售工具箱',
      requiresAuth: false, // 移动端工具箱无需登录
    },
  },
  {
    path: '/tools/poster-generator',
    name: 'PosterGenerator',
    component: () => import('@/views/tools/poster-generator.vue'),
    meta: {
      title: '海报生成器',
      requiresAuth: false, // 移动端海报生成器无需登录
    },
  },
  {
    path: '/tools/potential-customers',
    name: 'PotentialCustomers',
    component: () => import('@/views/tools/potential-customers.vue'),
    meta: {
      title: '潜客挖掘',
      requiresAuth: false, // 移动端潜客挖掘无需登录
    },
  },
  {
    path: '/tools/morning-report',
    name: 'MorningReport',
    component: () => import('@/views/tools/morning-report.vue'),
    meta: {
      title: '早报生成器',
      requiresAuth: false, // 移动端早报生成器无需登录
    },
  },
  {
    path: '/tools/marketing-script',
    name: 'MarketingScript',
    component: () => import('@/views/tools/marketing-script.vue'),
    meta: {
      title: '智能话术助手',
      requiresAuth: false, // 移动端话术助手无需登录
    },
  },
  {
    path: '/tools/profit-calculator',
    name: 'ProfitCalculator',
    component: () => import('@/views/tools/profit-calculator.vue'),
    meta: {
      title: '收益演示',
      requiresAuth: false, // 移动端收益演示无需登录
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
