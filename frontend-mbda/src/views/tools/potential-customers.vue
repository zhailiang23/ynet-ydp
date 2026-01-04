<template>
  <div class="min-h-screen bg-background-light dark:bg-background-dark pb-24">
    <!-- Header -->
    <div class="sticky top-0 z-50 bg-background-light/95 dark:bg-background-dark/95 backdrop-blur-md px-4 py-3 flex items-center justify-between border-b border-gray-200 dark:border-gray-800">
      <button
        class="flex items-center justify-center p-2 -ml-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors"
        @click="router.back()"
      >
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
        </svg>
      </button>
      <h1 class="text-lg font-bold tracking-tight">潜客挖掘</h1>
      <button class="flex items-center justify-center p-2 -mr-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors">
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
      </button>
    </div>

    <!-- AI 智能洞察卡片 -->
    <div class="px-4 pt-4 pb-2">
      <div class="relative overflow-hidden rounded-2xl bg-gradient-to-r from-secondary to-indigo-600 p-4 text-white shadow-glow">
        <div class="absolute right-0 top-0 -mr-4 -mt-4 h-24 w-24 rounded-full bg-white/10 blur-xl"></div>
        <div class="absolute left-0 bottom-0 -ml-4 -mb-4 h-20 w-20 rounded-full bg-white/10 blur-xl"></div>
        <div class="relative z-10 flex items-start justify-between">
          <div>
            <div class="flex items-center gap-2 mb-1">
              <svg class="w-5 h-5 text-amber-300" fill="currentColor" viewBox="0 0 20 20">
                <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
              </svg>
              <span class="text-xs font-bold uppercase tracking-wider bg-white/20 px-2 py-0.5 rounded text-white/90">AI 智能洞察</span>
            </div>
            <h2 class="text-xl font-bold mb-1">已挖掘 {{ total }} 位高潜客户</h2>
            <p class="text-xs text-white/80 leading-relaxed max-w-[85%]">
              基于行内大数据分析，筛选出近期有资金异动或产品到期的高意向客户。
            </p>
          </div>
        </div>
      </div>
    </div>

    <!-- 搜索和筛选 -->
    <div class="sticky top-[60px] z-40 bg-background-light dark:bg-background-dark pb-2 pt-2">
      <!-- 搜索框 -->
      <div class="px-4 mb-3">
        <div class="relative">
          <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
            <svg class="w-5 h-5 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
            </svg>
          </div>
          <input
            v-model="searchKeyword"
            class="block w-full pl-10 pr-3 py-2.5 border-none rounded-xl bg-white dark:bg-surface-dark shadow-card text-sm text-slate-900 dark:text-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-primary/50"
            placeholder="搜索姓名、手机号..."
            type="text"
            @input="handleSearch"
          />
        </div>
      </div>

      <!-- 筛选按钮 -->
      <div class="flex gap-2 px-4 overflow-x-auto no-scrollbar pb-2">
        <button class="flex h-8 shrink-0 items-center justify-between gap-1 pl-3 pr-2 rounded-lg bg-surface-light dark:bg-surface-dark border border-gray-200 dark:border-gray-700 shadow-sm active:bg-gray-50 dark:active:bg-gray-800 transition-colors">
          <span class="text-xs font-medium text-gray-700 dark:text-gray-300">资产范围</span>
          <svg class="w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
          </svg>
        </button>
        <button class="flex h-8 shrink-0 items-center justify-between gap-1 pl-3 pr-2 rounded-lg bg-surface-light dark:bg-surface-dark border border-gray-200 dark:border-gray-700 shadow-sm active:bg-gray-50 dark:active:bg-gray-800 transition-colors">
          <span class="text-xs font-medium text-gray-700 dark:text-gray-300">风险偏好</span>
          <svg class="w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
          </svg>
        </button>
        <button class="flex h-8 shrink-0 items-center justify-between gap-1 pl-3 pr-2 rounded-lg bg-surface-light dark:bg-surface-dark border border-gray-200 dark:border-gray-700 shadow-sm active:bg-gray-50 dark:active:bg-gray-800 transition-colors">
          <span class="text-xs font-medium text-gray-700 dark:text-gray-300">客户等级</span>
          <svg class="w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 9l-7 7-7-7" />
          </svg>
        </button>
      </div>

      <!-- 推荐标签 -->
      <div class="flex gap-2 px-4 overflow-x-auto no-scrollbar pt-1">
        <span class="text-xs font-bold text-gray-400 py-1">推荐标签:</span>
        <button class="text-xs px-2 py-0.5 rounded bg-gray-200 dark:bg-gray-800 text-gray-600 dark:text-gray-400">#理财到期</button>
        <button class="text-xs px-2 py-0.5 rounded bg-gray-200 dark:bg-gray-800 text-gray-600 dark:text-gray-400">#大额异动</button>
        <button class="text-xs px-2 py-0.5 rounded bg-gray-200 dark:bg-gray-800 text-gray-600 dark:text-gray-400">#风险偏好提升</button>
      </div>
    </div>

    <!-- 潜客列表 -->
    <div v-if="loading" class="flex justify-center items-center py-20">
      <div class="w-8 h-8 border-4 border-primary border-t-transparent rounded-full animate-spin"></div>
    </div>

    <div v-else class="flex-1 px-4 pt-2 pb-24 flex flex-col gap-4">
      <div
        v-for="customer in customers"
        :key="customer.id"
        class="bg-surface-light dark:bg-surface-dark rounded-2xl p-4 shadow-card relative overflow-hidden"
        :class="{
          'border-l-4 border-primary': customer.aiMatchScore >= 80,
          'border border-gray-100 dark:border-gray-800': customer.aiMatchScore < 80
        }"
      >
        <!-- 客户基本信息 -->
        <div class="flex justify-between items-start mb-3">
          <div class="flex items-center gap-3">
            <div class="relative">
              <div class="w-12 h-12 rounded-full bg-gray-100 dark:bg-gray-700 flex items-center justify-center overflow-hidden">
                <img v-if="customer.avatar" :src="customer.avatar" alt="avatar" class="w-full h-full object-cover" />
                <svg v-else class="w-8 h-8 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
                </svg>
              </div>
              <div v-if="customer.customerLevel" class="absolute -bottom-1 -right-1 text-[10px] font-bold text-white px-1.5 py-0.5 rounded-full border-2 border-surface-light dark:border-surface-dark" :class="getCustomerLevelBgClass(customer.customerLevel)">
                {{ getCustomerLevelText(customer.customerLevel) }}
              </div>
            </div>
            <div>
              <h3 class="font-bold text-base text-slate-900 dark:text-white flex items-center gap-2">
                {{ customer.customerName }}
                <span v-if="customer.insightTitle" class="text-[10px] font-normal text-gray-500 bg-gray-100 dark:bg-gray-800 px-1.5 py-0.5 rounded">
                  {{ customer.insightTitle }}
                </span>
              </h3>
              <div class="flex gap-2 mt-1">
                <span
                  class="text-[10px] px-1.5 py-0.5 rounded font-medium"
                  :class="getRiskLevelClass(customer.riskLevel)"
                >
                  {{ getRiskLevelText(customer.riskLevel) }}
                </span>
                <span
                  v-if="customer.aiMatchScore >= 80"
                  class="text-[10px] text-primary bg-primary/10 px-1.5 py-0.5 rounded font-medium"
                >
                  高匹配度
                </span>
              </div>
            </div>
          </div>
          <div class="text-right">
            <div class="text-[10px] text-gray-400 uppercase tracking-wider">AUM (万)</div>
            <div class="text-lg font-bold text-slate-900 dark:text-white">{{ formatAUM(customer.aum) }}</div>
          </div>
        </div>

        <!-- AI 分析洞察 -->
        <div
          v-if="customer.analysisConclusion"
          class="rounded-xl p-3 mb-4 border"
          :class="customer.aiMatchScore >= 80
            ? 'bg-gradient-to-br from-blue-50 to-indigo-50 dark:from-slate-800 dark:to-slate-800/50 border-blue-100 dark:border-slate-700'
            : 'bg-gray-50 dark:bg-gray-800/50 border-gray-100 dark:border-gray-800'
          "
        >
          <div class="flex items-start gap-2">
            <svg
              class="w-4 h-4 mt-0.5 shrink-0"
              :class="customer.aiMatchScore >= 80 ? 'text-primary' : 'text-gray-400'"
              fill="none"
              stroke="currentColor"
              viewBox="0 0 24 24"
            >
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z" />
            </svg>
            <div>
              <p
                class="text-xs leading-relaxed"
                :class="customer.aiMatchScore >= 80 ? 'text-slate-700 dark:text-slate-300' : 'text-gray-600 dark:text-gray-400'"
              >
                <span v-if="customer.aiMatchScore >= 80" class="font-bold text-primary">AI 洞察：</span>
                {{ customer.analysisConclusion }}
              </p>
            </div>
          </div>
        </div>

        <!-- 操作按钮 -->
        <div class="flex gap-3">
          <button class="flex-1 py-2.5 rounded-xl border border-gray-200 dark:border-gray-700 text-sm font-semibold text-gray-600 dark:text-gray-300 flex items-center justify-center gap-2 active:bg-gray-50 dark:active:bg-gray-800 transition-colors">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-6 9l2 2 4-4" />
            </svg>
            加入跟进
          </button>
          <button
            class="flex-1 py-2.5 rounded-xl text-sm font-semibold flex items-center justify-center gap-2 transition-all"
            :class="customer.aiMatchScore >= 80
              ? 'bg-primary hover:bg-primary-dark text-white shadow-lg shadow-primary/20 active:scale-[0.98]'
              : 'bg-surface-light dark:bg-surface-dark border border-primary text-primary hover:bg-primary/5 active:scale-[0.98]'
            "
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5.882V19.24a1.76 1.76 0 01-3.417.592l-2.147-6.15M18 13a3 3 0 100-6M5.436 13.683A4.001 4.001 0 017 6h1.832c4.1 0 7.625-1.234 9.168-3v14c-1.543-1.766-5.067-3-9.168-3H7a3.988 3.988 0 01-1.564-.317z" />
            </svg>
            生成触达
          </button>
        </div>
      </div>

      <!-- 空状态 -->
      <div v-if="!loading && customers.length === 0" class="flex flex-col items-center justify-center py-20">
        <svg class="w-16 h-16 text-gray-300 dark:text-gray-700 mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4" />
        </svg>
        <p class="text-gray-400 dark:text-gray-600">暂无潜客数据</p>
      </div>
    </div>

    <!-- 底部批量操作按钮 -->
    <div v-if="selectedCount > 0" class="fixed bottom-6 left-0 right-0 px-6 flex justify-center z-50">
      <button class="bg-surface-dark dark:bg-white text-white dark:text-slate-900 px-6 py-3 rounded-full shadow-xl flex items-center gap-3 font-semibold text-sm hover:scale-105 transition-transform">
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-6 9l2 2 4-4" />
        </svg>
        批量操作 ({{ selectedCount }})
      </button>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getPotentialCustomerPage } from '@/api/potentialcustomer'
import type { PotentialCustomer } from '@/types/potentialCustomer'

const router = useRouter()

// 数据状态
const loading = ref(false)
const customers = ref<PotentialCustomer[]>([])
const total = ref(0)
const selectedCount = ref(0)

// 搜索和筛选
const searchKeyword = ref('')
const pageNo = ref(1)
const pageSize = ref(20)

// 获取潜客列表
const loadCustomers = async () => {
  try {
    loading.value = true
    const response = await getPotentialCustomerPage({
      pageNo: pageNo.value,
      pageSize: pageSize.value,
    })
    customers.value = response.list
    total.value = response.total
  } catch (error) {
    console.error('获取潜客列表失败:', error)
  } finally {
    loading.value = false
  }
}

// 搜索处理
let searchTimer: NodeJS.Timeout
const handleSearch = () => {
  clearTimeout(searchTimer)
  searchTimer = setTimeout(() => {
    pageNo.value = 1
    loadCustomers()
  }, 500)
}

// 格式化资产
const formatAUM = (aum: number) => {
  if (aum >= 10000) {
    return (aum / 10000).toFixed(1)
  }
  return aum.toFixed(1)
}

// 获取风险等级样式
const getRiskLevelClass = (riskLevel: string) => {
  const classMap: Record<string, string> = {
    AGGRESSIVE: 'text-red-600 bg-red-100 dark:bg-red-900/30',
    BALANCED: 'text-green-600 bg-green-100 dark:bg-green-900/30',
    CONSERVATIVE: 'text-blue-600 bg-blue-100 dark:bg-blue-900/30',
  }
  return classMap[riskLevel] || 'text-gray-600 bg-gray-100 dark:bg-gray-900/30'
}

// 获取风险等级文本
const getRiskLevelText = (riskLevel: string) => {
  const textMap: Record<string, string> = {
    AGGRESSIVE: '激进型',
    BALANCED: '稳健型',
    CONSERVATIVE: '保守型',
  }
  return textMap[riskLevel] || riskLevel
}

// 获取客户等级文本
const getCustomerLevelText = (level: string) => {
  const textMap: Record<string, string> = {
    vip: 'VIP',
    gold: '金卡',
    silver: '银卡',
    diamond: '钻石',
    normal: '普通',
    A: 'A',
    B: 'B',
    C: 'C',
    D: 'D',
  }
  return textMap[level] || level.toUpperCase()
}

// 获取客户等级背景色
const getCustomerLevelBgClass = (level: string) => {
  const classMap: Record<string, string> = {
    vip: 'bg-amber-400',
    gold: 'bg-yellow-500',
    silver: 'bg-gray-400',
    diamond: 'bg-purple-500',
    normal: 'bg-gray-500',
    A: 'bg-red-500',
    B: 'bg-orange-500',
    C: 'bg-blue-500',
    D: 'bg-green-500',
  }
  return classMap[level] || 'bg-gray-400'
}

// 组件挂载时加载数据
onMounted(() => {
  loadCustomers()
})
</script>

<style scoped>
.no-scrollbar::-webkit-scrollbar {
  display: none;
}
.no-scrollbar {
  -ms-overflow-style: none;
  scrollbar-width: none;
}
</style>
