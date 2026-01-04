<script setup lang="ts">
import { useRouter, useRoute } from 'vue-router'
import { computed } from 'vue'

const router = useRouter()
const route = useRoute()

// 判断当前激活的路由
const isActive = (path: string) => {
  // 首页特殊处理，只匹配根路径
  if (path === '/') {
    return route.path === '/'
  }
  return route.path === path || route.path.startsWith(path + '/')
}

// 导航项配置
const navItems = [
  {
    path: '/',
    label: '首页',
    icon: 'home',
  },
  {
    path: '/products',
    label: '产品',
    icon: 'product',
  },
  {
    path: '/customers',
    label: '客户',
    icon: 'customer',
  },
  {
    path: '/tools',
    label: '工具',
    icon: 'tool',
  },
  {
    path: '/profile',
    label: '我的',
    icon: 'profile',
  },
]

// 跳转到指定路由
const navigateTo = (path: string) => {
  router.push(path)
}
</script>

<template>
  <div class="fixed bottom-0 left-0 right-0 bg-white dark:bg-surface-dark border-t border-gray-200 dark:border-white/10 z-50">
    <div class="flex items-center justify-around h-16">
      <!-- 首页 -->
      <button
        class="flex flex-col items-center justify-center flex-1 transition-colors"
        :class="isActive('/') ? 'text-primary' : 'text-gray-400 hover:text-primary'"
        @click="navigateTo('/')"
      >
        <svg class="w-6 h-6" :fill="isActive('/') ? 'currentColor' : 'none'" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6" />
        </svg>
        <span class="text-xs mt-1" :class="isActive('/') ? 'font-bold' : ''">首页</span>
      </button>

      <!-- 产品 -->
      <button
        class="flex flex-col items-center justify-center flex-1 transition-colors"
        :class="isActive('/products') || isActive('/product') ? 'text-primary' : 'text-gray-400 hover:text-primary'"
        @click="navigateTo('/products')"
      >
        <svg class="w-6 h-6" :fill="isActive('/products') || isActive('/product') ? 'currentColor' : 'none'" viewBox="0 0 20 20">
          <path v-if="isActive('/products') || isActive('/product')" d="M3 12v3c0 1.657 3.134 3 7 3s7-1.343 7-3v-3c0 1.657-3.134 3-7 3s-7-1.343-7-3z" />
          <path v-if="isActive('/products') || isActive('/product')" d="M3 7v3c0 1.657 3.134 3 7 3s7-1.343 7-3V7c0 1.657-3.134 3-7 3S3 8.657 3 7z" />
          <path v-if="isActive('/products') || isActive('/product')" d="M17 5c0 1.657-3.134 3-7 3S3 6.657 3 5s3.134-3 7-3 7 1.343 7 3z" />
          <path v-else stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 12v3c0 1.657 3.134 3 7 3s7-1.343 7-3v-3M3 7v3c0 1.657 3.134 3 7 3s7-1.343 7-3V7M17 5c0 1.657-3.134 3-7 3S3 6.657 3 5s3.134-3 7-3 7 1.343 7 3z" />
        </svg>
        <span class="text-xs mt-1" :class="isActive('/products') || isActive('/product') ? 'font-bold' : ''">产品</span>
      </button>

      <!-- 客户 -->
      <button
        class="flex flex-col items-center justify-center flex-1 transition-colors"
        :class="isActive('/customers') || isActive('/customer') ? 'text-primary' : 'text-gray-400 hover:text-primary'"
        @click="navigateTo('/customers')"
      >
        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
        </svg>
        <span class="text-xs mt-1" :class="isActive('/customers') || isActive('/customer') ? 'font-bold' : ''">客户</span>
      </button>

      <!-- 工具 -->
      <button
        class="flex flex-col items-center justify-center flex-1 transition-colors"
        :class="isActive('/tools') ? 'text-primary' : 'text-gray-400 hover:text-primary'"
        @click="navigateTo('/tools')"
      >
        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
        </svg>
        <span class="text-xs mt-1" :class="isActive('/tools') ? 'font-bold' : ''">工具</span>
      </button>

      <!-- 我的 -->
      <button
        class="flex flex-col items-center justify-center flex-1 transition-colors"
        :class="isActive('/profile') ? 'text-primary' : 'text-gray-400 hover:text-primary'"
        @click="navigateTo('/profile')"
      >
        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
        </svg>
        <span class="text-xs mt-1" :class="isActive('/profile') ? 'font-bold' : ''">我的</span>
      </button>
    </div>
  </div>
</template>
