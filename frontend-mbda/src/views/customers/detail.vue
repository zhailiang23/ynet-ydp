<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getCustomer } from '@/api/customer'
import type { Customer } from '@/types/customer'
import BottomNav from '@/components/BottomNav.vue'

const route = useRoute()
const router = useRouter()

const loading = ref(false)
const error = ref('')
const customer = ref<Customer | null>(null)
const activeTab = ref('overview')

// Tab 配置
const tabs = [
  { key: 'overview', label: '概览', icon: 'M3 12l2-2m0 0l7-7 7 7M5 10v10a1 1 0 001 1h3m10-11l2 2m-2-2v10a1 1 0 01-1 1h-3m-6 0a1 1 0 001-1v-4a1 1 0 011-1h2a1 1 0 011 1v4a1 1 0 001 1m-6 0h6' },
  { key: 'basic', label: '基本信息', icon: 'M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z' },
  { key: 'business', label: '业务信息', icon: 'M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z' },
  { key: 'value', label: '客户价值', icon: 'M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z' },
]

// 获取客户等级标签
const getLevelLabel = (level?: string) => {
  const map: Record<string, string> = {
    // 零售客户等级
    normal: '普通',
    gold: '金卡',
    vip: 'VIP',
    diamond: '钻石',
    // 对公客户等级
    strategic: '战略',
    key: '重点',
    general: '普通',
    important: '重要',
  }
  return level ? map[level] || level : '普通'
}

// 获取客户状态标签
const getStatusLabel = (status?: number) => {
  const map: Record<number, string> = {
    1: '正常',
    2: '冻结',
    3: '注销',
  }
  return status ? map[status] || '未知' : '未知'
}

// 获取客户类型标签
const getTypeLabel = (type?: number) => {
  return type === 1 ? '零售客户' : type === 2 ? '对公客户' : '未知'
}

// 格式化金额
const formatMoney = (value?: number | string) => {
  if (!value) return '0.00'
  const num = typeof value === 'string' ? parseFloat(value) : value
  return num.toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 })
}

// 格式化日期
const formatDate = (date?: string) => {
  if (!date) return '-'
  return new Date(date).toLocaleDateString('zh-CN')
}

// 返回上一页
const goBack = () => {
  router.back()
}

// 加载客户详情
const loadCustomer = async () => {
  const id = Number(route.params.id)
  if (!id) {
    error.value = '客户ID无效'
    return
  }

  loading.value = true
  error.value = ''

  try {
    customer.value = await getCustomer(id)
  } catch (err: any) {
    error.value = err.message || '加载客户详情失败'
    console.error('加载客户详情失败:', err)
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  loadCustomer()
})
</script>

<template>
  <div class="min-h-screen bg-background-light dark:bg-background-dark pb-20">
    <!-- 顶部导航栏 -->
    <div class="sticky top-0 z-50 flex items-center bg-white dark:bg-surface-dark backdrop-blur-md p-4 justify-between border-b border-gray-200 dark:border-white/5">
      <div class="text-gray-900 dark:text-white flex size-12 shrink-0 items-center justify-start cursor-pointer" @click="goBack">
        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
        </svg>
      </div>
      <h2 class="text-gray-900 dark:text-white text-lg font-bold leading-tight flex-1 text-center pr-12">客户详情</h2>
    </div>

    <!-- 加载状态 -->
    <div v-if="loading" class="flex items-center justify-center py-20">
      <div class="animate-spin rounded-full h-8 w-8 border-b-2 border-primary"></div>
    </div>

    <!-- 错误状态 -->
    <div v-else-if="error" class="flex flex-col items-center justify-center py-20 px-4">
      <svg class="w-16 h-16 text-red-400 mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4m0 4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
      </svg>
      <p class="text-red-400 text-sm">{{ error }}</p>
    </div>

    <!-- 客户详情内容 -->
    <div v-else-if="customer">
      <!-- 客户头部信息卡片 -->
      <div class="bg-white dark:bg-surface-dark p-4 border-b border-gray-200 dark:border-white/5">
        <div class="flex gap-4 items-start">
          <!-- 头像 -->
          <div class="relative shrink-0">
            <div
              class="bg-gradient-to-br rounded-full h-16 w-16 flex items-center justify-center text-white font-bold text-2xl ring-2 ring-white/10"
              :class="{
                'from-yellow-400 to-yellow-600': customer.customerLevel === 'diamond',
                'from-purple-400 to-purple-600': customer.customerLevel === 'vip',
                'from-blue-400 to-blue-600': customer.customerLevel === 'gold',
                'from-gray-400 to-gray-600': !customer.customerLevel || customer.customerLevel === 'normal' || customer.customerLevel === 'general',
                'from-red-400 to-red-600': customer.customerLevel === 'strategic',
                'from-orange-400 to-orange-600': customer.customerLevel === 'key' || customer.customerLevel === 'important',
              }"
            >
              {{ customer.customerName?.charAt(0) }}
            </div>
            <!-- 等级角标 -->
            <div
              v-if="customer.customerLevel"
              class="absolute -bottom-1 -right-1 text-[9px] font-bold px-1.5 py-0.5 rounded-full border-2 border-white dark:border-surface-dark"
              :class="{
                'bg-gradient-to-br from-yellow-400 to-yellow-600 text-black': customer.customerLevel === 'diamond',
                'bg-gradient-to-br from-purple-400 to-purple-600 text-white': customer.customerLevel === 'vip',
                'bg-gradient-to-br from-blue-400 to-blue-600 text-white': customer.customerLevel === 'gold',
                'bg-gradient-to-br from-gray-400 to-gray-600 text-white': customer.customerLevel === 'normal' || customer.customerLevel === 'general',
                'bg-gradient-to-br from-red-400 to-red-600 text-white': customer.customerLevel === 'strategic',
                'bg-gradient-to-br from-orange-400 to-orange-600 text-white': customer.customerLevel === 'key' || customer.customerLevel === 'important',
              }"
            >
              {{ getLevelLabel(customer.customerLevel) }}
            </div>
          </div>

          <!-- 客户信息 -->
          <div class="flex-1 min-w-0">
            <div class="flex items-center gap-2 mb-1">
              <h1 class="text-gray-900 dark:text-white text-xl font-bold truncate">{{ customer.customerName }}</h1>
              <span class="shrink-0 px-2 py-0.5 text-[10px] font-medium rounded-full"
                :class="{
                  'bg-blue-100 dark:bg-blue-900/30 text-blue-700 dark:text-blue-400': customer.customerType === 1,
                  'bg-green-100 dark:bg-green-900/30 text-green-700 dark:text-green-400': customer.customerType === 2,
                }"
              >
                {{ getTypeLabel(customer.customerType) }}
              </span>
            </div>
            <p class="text-gray-500 dark:text-text-secondary text-sm mb-2">{{ customer.customerNo }}</p>

            <!-- 快捷标签 -->
            <div class="flex flex-wrap gap-1.5">
              <span
                v-if="customer.isHighQuality"
                class="inline-flex items-center gap-1 px-2 py-0.5 bg-amber-100 dark:bg-amber-900/30 text-amber-700 dark:text-amber-400 text-[10px] font-medium rounded"
              >
                <svg class="w-3 h-3" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                </svg>
                优质
              </span>
              <span
                v-if="customer.isImportant"
                class="inline-flex items-center gap-1 px-2 py-0.5 bg-red-100 dark:bg-red-900/30 text-red-700 dark:text-red-400 text-[10px] font-medium rounded"
              >
                <svg class="w-3 h-3" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z" clip-rule="evenodd" />
                </svg>
                重要
              </span>
              <span
                class="px-2 py-0.5 text-[10px] font-medium rounded"
                :class="{
                  'bg-green-100 dark:bg-green-900/30 text-green-700 dark:text-green-400': customer.customerStatus === 1,
                  'bg-yellow-100 dark:bg-yellow-900/30 text-yellow-700 dark:text-yellow-400': customer.customerStatus === 2,
                  'bg-red-100 dark:bg-red-900/30 text-red-700 dark:text-red-400': customer.customerStatus === 3,
                }"
              >
                {{ getStatusLabel(customer.customerStatus) }}
              </span>
            </div>
          </div>
        </div>
      </div>

      <!-- Tab 导航 -->
      <div class="sticky top-[73px] z-40 bg-white dark:bg-surface-dark border-b border-gray-200 dark:border-white/5">
        <div class="flex overflow-x-auto no-scrollbar">
          <button
            v-for="tab in tabs"
            :key="tab.key"
            class="flex-1 min-w-[80px] px-4 py-3 text-sm font-medium transition-colors relative whitespace-nowrap"
            :class="{
              'text-primary dark:text-primary': activeTab === tab.key,
              'text-gray-600 dark:text-text-secondary hover:text-gray-900 dark:hover:text-white': activeTab !== tab.key,
            }"
            @click="activeTab = tab.key"
          >
            <div class="flex flex-col items-center gap-1">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" :d="tab.icon" />
              </svg>
              <span>{{ tab.label }}</span>
            </div>
            <div
              v-if="activeTab === tab.key"
              class="absolute bottom-0 left-0 right-0 h-0.5 bg-primary"
            />
          </button>
        </div>
      </div>

      <!-- 内容区域 -->
      <div class="p-4">
        <!-- 概览 -->
        <div v-if="activeTab === 'overview'" class="space-y-4">
          <div class="bg-white dark:bg-surface-dark rounded-xl p-4 border border-gray-200 dark:border-white/5">
            <h3 class="text-gray-900 dark:text-white font-semibold mb-3 flex items-center gap-2">
              <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
              </svg>
              客户概览
            </h3>
            <div class="grid grid-cols-2 gap-3">
              <div class="bg-gray-50 dark:bg-black/20 rounded-lg p-3">
                <p class="text-gray-500 dark:text-text-secondary text-xs mb-1">客户类型</p>
                <p class="text-gray-900 dark:text-white font-medium">{{ getTypeLabel(customer.customerType) }}</p>
              </div>
              <div class="bg-gray-50 dark:bg-black/20 rounded-lg p-3">
                <p class="text-gray-500 dark:text-text-secondary text-xs mb-1">客户等级</p>
                <p class="text-gray-900 dark:text-white font-medium">{{ getLevelLabel(customer.customerLevel) }}</p>
              </div>
              <div class="bg-gray-50 dark:bg-black/20 rounded-lg p-3">
                <p class="text-gray-500 dark:text-text-secondary text-xs mb-1">客户状态</p>
                <p class="text-gray-900 dark:text-white font-medium">{{ getStatusLabel(customer.customerStatus) }}</p>
              </div>
              <div class="bg-gray-50 dark:bg-black/20 rounded-lg p-3">
                <p class="text-gray-500 dark:text-text-secondary text-xs mb-1">分配状态</p>
                <p class="text-gray-900 dark:text-white font-medium">{{ customer.assignmentStatus === 1 ? '已分配' : '未分配' }}</p>
              </div>
            </div>
          </div>

          <!-- 信用信息 -->
          <div v-if="customer.creditScore || customer.creditLevel" class="bg-white dark:bg-surface-dark rounded-xl p-4 border border-gray-200 dark:border-white/5">
            <h3 class="text-gray-900 dark:text-white font-semibold mb-3 flex items-center gap-2">
              <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4M7.835 4.697a3.42 3.42 0 001.946-.806 3.42 3.42 0 014.438 0 3.42 3.42 0 001.946.806 3.42 3.42 0 013.138 3.138 3.42 3.42 0 00.806 1.946 3.42 3.42 0 010 4.438 3.42 3.42 0 00-.806 1.946 3.42 3.42 0 01-3.138 3.138 3.42 3.42 0 00-1.946.806 3.42 3.42 0 01-4.438 0 3.42 3.42 0 00-1.946-.806 3.42 3.42 0 01-3.138-3.138 3.42 3.42 0 00-.806-1.946 3.42 3.42 0 010-4.438 3.42 3.42 0 00.806-1.946 3.42 3.42 0 013.138-3.138z" />
              </svg>
              信用信息
            </h3>
            <div class="grid grid-cols-2 gap-3">
              <div v-if="customer.creditLevel" class="bg-gray-50 dark:bg-black/20 rounded-lg p-3">
                <p class="text-gray-500 dark:text-text-secondary text-xs mb-1">信用等级</p>
                <p class="text-gray-900 dark:text-white font-medium">{{ customer.creditLevel }}</p>
              </div>
              <div v-if="customer.creditScore" class="bg-gray-50 dark:bg-black/20 rounded-lg p-3">
                <p class="text-gray-500 dark:text-text-secondary text-xs mb-1">信用评分</p>
                <p class="text-gray-900 dark:text-white font-medium font-mono">{{ formatMoney(customer.creditScore) }}</p>
              </div>
              <div v-if="customer.creditStatus" class="bg-gray-50 dark:bg-black/20 rounded-lg p-3 col-span-2">
                <p class="text-gray-500 dark:text-text-secondary text-xs mb-1">信用状态</p>
                <p class="text-gray-900 dark:text-white font-medium">{{ customer.creditStatus }}</p>
              </div>
            </div>
          </div>
        </div>

        <!-- 基本信息 -->
        <div v-if="activeTab === 'basic'" class="space-y-4">
          <div class="bg-white dark:bg-surface-dark rounded-xl p-4 border border-gray-200 dark:border-white/5">
            <h3 class="text-gray-900 dark:text-white font-semibold mb-3">客户基本信息</h3>
            <div class="space-y-3">
              <div class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
                <span class="text-gray-500 dark:text-text-secondary text-sm">客户编号</span>
                <span class="text-gray-900 dark:text-white text-sm font-medium">{{ customer.customerNo }}</span>
              </div>
              <div class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
                <span class="text-gray-500 dark:text-text-secondary text-sm">客户名称</span>
                <span class="text-gray-900 dark:text-white text-sm font-medium">{{ customer.customerName }}</span>
              </div>
              <div class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
                <span class="text-gray-500 dark:text-text-secondary text-sm">客户类型</span>
                <span class="text-gray-900 dark:text-white text-sm font-medium">{{ getTypeLabel(customer.customerType) }}</span>
              </div>
              <div class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
                <span class="text-gray-500 dark:text-text-secondary text-sm">客户等级</span>
                <span class="text-gray-900 dark:text-white text-sm font-medium">{{ getLevelLabel(customer.customerLevel) }}</span>
              </div>
              <div class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
                <span class="text-gray-500 dark:text-text-secondary text-sm">客户状态</span>
                <span class="text-gray-900 dark:text-white text-sm font-medium">{{ getStatusLabel(customer.customerStatus) }}</span>
              </div>
              <div v-if="customer.customerSource" class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
                <span class="text-gray-500 dark:text-text-secondary text-sm">客户来源</span>
                <span class="text-gray-900 dark:text-white text-sm font-medium">{{ customer.customerSource }}</span>
              </div>
              <div v-if="customer.customerTag" class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
                <span class="text-gray-500 dark:text-text-secondary text-sm">客户标签</span>
                <span class="text-gray-900 dark:text-white text-sm font-medium">{{ customer.customerTag }}</span>
              </div>
              <div class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
                <span class="text-gray-500 dark:text-text-secondary text-sm">创建时间</span>
                <span class="text-gray-900 dark:text-white text-sm font-medium">{{ formatDate(customer.createTime) }}</span>
              </div>
              <div v-if="customer.remark" class="py-2">
                <p class="text-gray-500 dark:text-text-secondary text-sm mb-1">备注信息</p>
                <p class="text-gray-900 dark:text-white text-sm">{{ customer.remark }}</p>
              </div>
            </div>
          </div>
        </div>

        <!-- 业务信息 -->
        <div v-if="activeTab === 'business'" class="space-y-4">
          <div class="bg-white dark:bg-surface-dark rounded-xl p-4 border border-gray-200 dark:border-white/5">
            <div class="text-center py-12">
              <svg class="w-16 h-16 text-gray-400 dark:text-text-secondary mx-auto mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
              </svg>
              <p class="text-gray-500 dark:text-text-secondary text-sm">业务信息正在开发中...</p>
            </div>
          </div>
        </div>

        <!-- 客户价值 -->
        <div v-if="activeTab === 'value'" class="space-y-4">
          <div class="bg-white dark:bg-surface-dark rounded-xl p-4 border border-gray-200 dark:border-white/5">
            <div class="text-center py-12">
              <svg class="w-16 h-16 text-gray-400 dark:text-text-secondary mx-auto mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M12 8c-1.657 0-3 .895-3 2s1.343 2 3 2 3 .895 3 2-1.343 2-3 2m0-8c1.11 0 2.08.402 2.599 1M12 8V7m0 1v8m0 0v1m0-1c-1.11 0-2.08-.402-2.599-1M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              <p class="text-gray-500 dark:text-text-secondary text-sm">客户价值信息正在开发中...</p>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Bottom Navigation -->
    <BottomNav />
  </div>
</template>

<style scoped>
/* Hide scrollbar for Chrome, Safari and Opera */
.no-scrollbar::-webkit-scrollbar {
  display: none;
}

/* Hide scrollbar for IE, Edge and Firefox */
.no-scrollbar {
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}
</style>
