<template>
  <div class="relative flex min-h-screen w-full flex-col overflow-x-hidden pb-24">
    <!-- Header -->
    <header class="sticky top-0 z-20 bg-background-light/95 dark:bg-background-dark/95 backdrop-blur-md border-b border-slate-200 dark:border-white/5">
      <div class="flex items-center justify-between p-4 h-14">
        <button
          class="flex items-center justify-center size-10 rounded-full hover:bg-slate-200 dark:hover:bg-surface-dark-highlight transition-colors -ml-2"
          @click="router.back()"
        >
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
          </svg>
        </button>
        <h1 class="text-base font-bold">每日洞察详情</h1>
        <button class="flex items-center justify-center size-10 rounded-full hover:bg-slate-200 dark:hover:bg-surface-dark-highlight transition-colors -mr-2">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8.684 13.342C8.886 12.938 9 12.482 9 12c0-.482-.114-.938-.316-1.342m0 2.684a3 3 0 110-2.684m0 2.684l6.632 3.316m-6.632-6l6.632-3.316m0 0a3 3 0 105.367-2.684 3 3 0 00-5.367 2.684zm0 9.316a3 3 0 105.368 2.684 3 3 0 00-5.368-2.684z" />
          </svg>
        </button>
      </div>
    </header>

    <main class="flex flex-col gap-5 px-4 pt-4">
      <!-- AI 深度洞察卡片 (静态部分) -->
      <section>
        <div class="relative overflow-hidden rounded-2xl bg-gradient-to-br from-[#137fec] to-[#0a4d91] shadow-xl shadow-primary/20 text-white">
          <div class="absolute top-0 right-0 -mr-16 -mt-16 size-64 rounded-full bg-white/10 blur-3xl"></div>
          <div class="absolute bottom-0 left-0 -ml-12 -mb-12 size-40 rounded-full bg-purple-500/30 blur-3xl"></div>
          <div class="relative p-5 z-10">
            <div class="flex items-center justify-between mb-4">
              <div class="flex items-center gap-2 px-3 py-1 rounded-full bg-white/10 backdrop-blur-md border border-white/10 w-fit">
                <span class="relative flex items-center justify-center text-yellow-300">
                  <svg class="w-4 h-4 fill-current" viewBox="0 0 24 24">
                    <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z"/>
                  </svg>
                </span>
                <p class="text-xs font-bold tracking-wide uppercase">AI 深度洞察</p>
              </div>
              <div class="flex items-center gap-1 text-xs font-medium text-blue-100 bg-blue-900/30 px-2 py-0.5 rounded-lg border border-blue-400/20">
                <svg class="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                </svg>
                转化潜力高
              </div>
            </div>
            <h2 class="text-xl font-bold leading-tight mb-2">科技类基金回调机会</h2>
            <p class="text-blue-100 text-sm leading-relaxed mb-5 opacity-90">
              监测到纳斯达克指数波动，结合客户持仓分析，建议对持有相关标的且风险偏好匹配的客户进行定投推荐或低位补仓。
            </p>
            <div class="grid grid-cols-2 gap-4 border-t border-white/10 pt-4">
              <div>
                <p class="text-xs text-blue-200 mb-0.5">涉及高意向客户</p>
                <p class="text-2xl font-bold">{{ totalCustomers }} <span class="text-sm font-normal text-blue-200">位</span></p>
              </div>
              <div>
                <p class="text-xs text-blue-200 mb-0.5">预计资产撬动</p>
                <p class="text-2xl font-bold">¥{{ totalPotentialValue }} <span class="text-sm font-normal text-blue-200">万</span></p>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- 客户画像分布和AI推荐理由 (静态部分) -->
      <section class="grid grid-cols-1 md:grid-cols-2 gap-4">
        <div class="bg-white dark:bg-surface-dark-highlight p-4 rounded-2xl border border-slate-100 dark:border-white/5 shadow-sm">
          <div class="flex items-center justify-between mb-4">
            <h3 class="font-bold text-sm text-slate-900 dark:text-white">客户画像分布</h3>
            <svg class="w-[18px] h-[18px] text-slate-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 3.055A9.001 9.001 0 1020.945 13H11V3.055z" />
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20.488 9H15V3.512A9.025 9.025 0 0120.488 9z" />
            </svg>
          </div>
          <div class="flex items-center gap-4">
            <div class="relative size-24 shrink-0 rounded-full" style="background: conic-gradient(#3b82f6 0% 60%, #a855f7 60% 90%, #e2e8f0 90% 100%);">
              <div class="absolute inset-3 bg-white dark:bg-surface-dark-highlight rounded-full flex items-center justify-center">
                <span class="text-xs font-bold text-slate-500 text-center">风险<br/>偏好</span>
              </div>
            </div>
            <div class="flex flex-col gap-2 w-full">
              <div class="flex items-center justify-between text-xs">
                <div class="flex items-center gap-1.5">
                  <span class="size-2 rounded-full bg-blue-500"></span>
                  <span class="text-slate-600 dark:text-slate-300">激进型 (3位)</span>
                </div>
                <span class="font-bold">60%</span>
              </div>
              <div class="flex items-center justify-between text-xs">
                <div class="flex items-center gap-1.5">
                  <span class="size-2 rounded-full bg-purple-500"></span>
                  <span class="text-slate-600 dark:text-slate-300">稳健型 (2位)</span>
                </div>
                <span class="font-bold">30%</span>
              </div>
              <div class="flex items-center justify-between text-xs">
                <div class="flex items-center gap-1.5">
                  <span class="size-2 rounded-full bg-slate-200 dark:bg-slate-600"></span>
                  <span class="text-slate-600 dark:text-slate-300">其他 (0位)</span>
                </div>
                <span class="font-bold">10%</span>
              </div>
            </div>
          </div>
        </div>

        <div class="bg-white dark:bg-surface-dark-highlight p-4 rounded-2xl border border-slate-100 dark:border-white/5 shadow-sm">
          <div class="flex items-center justify-between mb-4">
            <h3 class="font-bold text-sm text-slate-900 dark:text-white">AI 推荐理由归因</h3>
            <svg class="w-[18px] h-[18px] text-slate-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
            </svg>
          </div>
          <div class="flex flex-col gap-3">
            <div class="group">
              <div class="flex justify-between text-xs mb-1">
                <span class="text-slate-500 dark:text-slate-400">持仓相关性</span>
                <span class="font-bold text-primary">92分</span>
              </div>
              <div class="h-2 w-full bg-slate-100 dark:bg-slate-700 rounded-full overflow-hidden">
                <div class="h-full bg-primary rounded-full" style="width: 92%"></div>
              </div>
            </div>
            <div class="group">
              <div class="flex justify-between text-xs mb-1">
                <span class="text-slate-500 dark:text-slate-400">资金充裕度</span>
                <span class="font-bold text-purple-500">85分</span>
              </div>
              <div class="h-2 w-full bg-slate-100 dark:bg-slate-700 rounded-full overflow-hidden">
                <div class="h-full bg-purple-500 rounded-full" style="width: 85%"></div>
              </div>
            </div>
            <div class="group">
              <div class="flex justify-between text-xs mb-1">
                <span class="text-slate-500 dark:text-slate-400">近期活跃度</span>
                <span class="font-bold text-orange-500">78分</span>
              </div>
              <div class="h-2 w-full bg-slate-100 dark:bg-slate-700 rounded-full overflow-hidden">
                <div class="h-full bg-orange-500 rounded-full" style="width: 78%"></div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- 高潜力客户名单 (动态部分) -->
      <section>
        <div class="flex items-center justify-between px-1 mb-3">
          <h3 class="text-slate-900 dark:text-white text-lg font-bold">高潜力客户名单</h3>
          <div class="flex gap-2">
            <button class="text-primary text-xs font-semibold bg-primary/10 px-3 py-1.5 rounded-lg hover:bg-primary hover:text-white transition-colors">
              按潜力排序
            </button>
          </div>
        </div>

        <div v-if="loading" class="flex items-center justify-center py-12">
          <div class="animate-spin rounded-full h-8 w-8 border-b-2 border-primary"></div>
        </div>

        <div v-else class="flex flex-col gap-3">
          <div
            v-for="customer in customers"
            :key="customer.id"
            class="bg-white dark:bg-surface-dark-highlight p-4 rounded-2xl border border-slate-100 dark:border-white/5 shadow-sm cursor-pointer hover:shadow-md active:scale-[0.99] transition-all"
            :class="{ 'opacity-80': !customer.isHighQuality }"
            @click="goToCustomerDetail(customer.id)"
          >
            <div class="flex justify-between items-start mb-3">
              <div class="flex gap-3">
                <div class="relative">
                  <div class="bg-gradient-to-br rounded-full size-12 flex items-center justify-center text-white font-bold text-lg ring-2 ring-white/10"
                    :class="{
                      'from-yellow-400 to-yellow-600': customer.customerLevel === 'diamond',
                      'from-purple-400 to-purple-600': customer.customerLevel === 'vip',
                      'from-blue-400 to-blue-600': customer.customerLevel === 'gold',
                      'from-gray-400 to-gray-600': !customer.customerLevel || customer.customerLevel === 'normal',
                      'from-red-400 to-red-600': customer.customerLevel === 'strategic',
                      'from-orange-400 to-orange-600': customer.customerLevel === 'key',
                    }"
                  >
                    {{ customer.customerName?.charAt(0) }}
                  </div>
                  <span
                    v-if="customer.customerLevel"
                    class="absolute -bottom-1 -right-1 flex size-5 items-center justify-center rounded-full text-[9px] font-bold border-2 border-white dark:border-surface-dark-highlight"
                    :class="{
                      'bg-gradient-to-br from-yellow-400 to-yellow-600 text-black': customer.customerLevel === 'diamond',
                      'bg-gradient-to-br from-purple-400 to-purple-600 text-white': customer.customerLevel === 'vip',
                      'bg-gradient-to-br from-blue-400 to-blue-600 text-white': customer.customerLevel === 'gold',
                      'bg-gradient-to-br from-gray-400 to-gray-600 text-white': customer.customerLevel === 'normal',
                      'bg-gradient-to-br from-red-400 to-red-600 text-white': customer.customerLevel === 'strategic',
                      'bg-gradient-to-br from-orange-400 to-orange-600 text-white': customer.customerLevel === 'key',
                    }"
                  >
                    {{ getLevelLabel(customer.customerLevel) }}
                  </span>
                </div>
                <div>
                  <div class="flex items-center gap-2">
                    <h4 class="font-bold text-slate-900 dark:text-white">{{ customer.customerName }}</h4>
                    <span
                      v-if="customer.creditLevel"
                      class="px-1.5 py-0.5 rounded text-[10px] font-medium bg-blue-100 dark:bg-blue-900/40 text-blue-600 dark:text-blue-400"
                    >
                      {{ customer.creditLevel }}
                    </span>
                  </div>
                  <p class="text-xs text-slate-500 dark:text-slate-400 mt-0.5">
                    信用评分: {{ customer.creditScore || 0 }}
                  </p>
                </div>
              </div>
              <div class="flex flex-col items-end">
                <span class="text-[10px] text-slate-400 uppercase tracking-wider font-semibold">信用评分</span>
                <span class="text-lg font-bold text-primary">{{ customer.creditScore || 0 }}</span>
              </div>
            </div>

            <div class="bg-slate-50 dark:bg-black/20 p-3 rounded-xl border border-slate-100 dark:border-white/5 mb-4">
              <div class="flex gap-2 items-start">
                <svg class="w-[18px] h-[18px] text-primary shrink-0 mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9.663 17h4.673M12 3v1m6.364 1.636l-.707.707M21 12h-1M4 12H3m3.343-5.657l-.707-.707m2.828 9.9a5 5 0 117.072 0l-.548.547A3.374 3.374 0 0014 18.469V19a2 2 0 11-4 0v-.531c0-.895-.356-1.754-.988-2.386l-.548-.547z" />
                </svg>
                <p class="text-xs text-slate-600 dark:text-slate-300 leading-relaxed">
                  <span class="font-bold text-slate-900 dark:text-white">备注：</span>
                  {{ customer.remark || '该客户持有相关科技类基金产品，建议关注回调补仓机会' }}
                </p>
              </div>
            </div>

            <div class="flex gap-2 border-t border-slate-100 dark:border-white/5 pt-3">
              <button class="flex-1 flex items-center justify-center gap-1.5 h-9 rounded-lg bg-primary/10 text-primary text-xs font-semibold hover:bg-primary hover:text-white transition-colors">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
                </svg>
                生成建议书
              </button>
              <button class="flex items-center justify-center size-9 rounded-lg bg-green-50 dark:bg-green-900/20 text-green-600 dark:text-green-400 hover:bg-green-500 hover:text-white transition-colors">
                <svg class="w-[18px] h-[18px]" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
                </svg>
              </button>
              <button class="flex items-center justify-center size-9 rounded-lg bg-blue-50 dark:bg-blue-900/20 text-blue-600 dark:text-blue-400 hover:bg-blue-500 hover:text-white transition-colors">
                <svg class="w-[18px] h-[18px]" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z" />
                </svg>
              </button>
            </div>
          </div>

          <button
            v-if="hasMore"
            class="w-full py-4 text-center text-xs text-slate-400 font-medium hover:text-primary transition-colors"
            @click="loadMore"
          >
            查看更多客户
          </button>
        </div>
      </section>
    </main>

    <!-- 底部操作栏 -->
    <div class="fixed bottom-0 left-0 w-full bg-white dark:bg-[#151e26] border-t border-slate-200 dark:border-white/5 z-50 p-4 shadow-[0_-4px_6px_-1px_rgba(0,0,0,0.05)]">
      <div class="flex gap-3">
        <button class="flex items-center justify-center size-12 rounded-xl bg-slate-100 dark:bg-surface-dark-highlight text-slate-600 dark:text-slate-300 hover:bg-slate-200 transition-colors">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
          </svg>
        </button>
        <button class="flex-1 flex items-center justify-center gap-2 bg-primary text-white font-semibold rounded-xl text-sm hover:bg-primary-dark transition-colors shadow-lg shadow-primary/30">
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8" />
          </svg>
          一键触达所有客户
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getCustomerPage } from '@/api/customer'
import type { Customer } from '@/types/customer'

const router = useRouter()

// 客户列表
const customers = ref<Customer[]>([])
const loading = ref(false)
const pageNo = ref(1)
const pageSize = ref(10)
const total = ref(0)

// 计算总客户数
const totalCustomers = computed(() => total.value)

// 计算总潜在价值（万元）- 使用信用评分模拟
const totalPotentialValue = computed(() => {
  const totalValue = customers.value.reduce((sum, c) => sum + Number(c.creditScore || 0), 0)
  return (totalValue / 100).toFixed(0)
})

// 是否还有更多数据
const hasMore = computed(() => customers.value.length < total.value)

// 加载客户列表
const loadCustomers = async () => {
  try {
    loading.value = true
    const res = await getCustomerPage({
      pageNo: pageNo.value,
      pageSize: pageSize.value,
    })
    if (pageNo.value === 1) {
      customers.value = res.list
    } else {
      customers.value.push(...res.list)
    }
    total.value = res.total
  } catch (error) {
    console.error('加载客户列表失败:', error)
  } finally {
    loading.value = false
  }
}

// 加载更多
const loadMore = () => {
  pageNo.value++
  loadCustomers()
}

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

// 跳转到客户详情页
const goToCustomerDetail = (customerId: number) => {
  router.push(`/customer/${customerId}`)
}

// 页面挂载时加载数据
onMounted(() => {
  loadCustomers()
})
</script>
