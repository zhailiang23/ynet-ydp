<template>
  <div class="relative flex min-h-screen w-full flex-col overflow-hidden pb-20">
    <!-- Top App Bar -->
    <div class="sticky top-0 z-50 bg-background-light/95 dark:bg-background-dark/95 backdrop-blur-md px-4 py-3 flex items-center justify-between border-b border-gray-200 dark:border-gray-800">
      <button class="flex items-center justify-center p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors">
        <svg class="w-6 h-6 text-gray-600 dark:text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16" />
        </svg>
      </button>
      <h1 class="text-lg font-bold tracking-tight">销售工具箱</h1>
      <button class="flex items-center justify-center p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors relative">
        <svg class="w-6 h-6 text-gray-600 dark:text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
        </svg>
        <span class="absolute top-2 right-2 w-2 h-2 bg-red-500 rounded-full border-2 border-background-light dark:border-background-dark"></span>
      </button>
    </div>

    <!-- Search Bar -->
    <div class="px-4 py-4">
      <div class="relative group">
        <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
          <svg class="w-5 h-5 text-gray-400 group-focus-within:text-primary transition-colors" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
          </svg>
        </div>
        <input
          v-model="searchQuery"
          class="block w-full pl-10 pr-12 py-3 border-none rounded-xl leading-5 bg-surface-light dark:bg-surface-dark text-slate-900 dark:text-white placeholder-gray-400 focus:outline-none focus:ring-2 focus:ring-primary/50 shadow-sm"
          placeholder="搜索工具、模版或客户..."
          type="text"
        />
        <div class="absolute inset-y-0 right-0 pr-2 flex items-center">
          <button class="p-1 rounded-lg hover:bg-gray-200 dark:hover:bg-gray-700 text-gray-400">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 11a7 7 0 01-7 7m0 0a7 7 0 01-7-7m7 7v4m0 0H8m4 0h4m-4-8a3 3 0 01-3-3V5a3 3 0 116 0v6a3 3 0 01-3 3z" />
            </svg>
          </button>
        </div>
      </div>
    </div>

    <!-- Filter Chips (Stages) -->
    <div class="flex gap-3 px-4 overflow-x-auto no-scrollbar pb-2">
      <button
        v-for="stage in stages"
        :key="stage.value"
        class="flex h-9 shrink-0 items-center justify-center px-4 rounded-full transition-all active:scale-95"
        :class="activeStage === stage.value
          ? 'bg-primary text-white shadow-lg shadow-primary/25'
          : 'bg-surface-light dark:bg-surface-dark border border-gray-200 dark:border-gray-700 text-gray-600 dark:text-gray-300 hover:border-primary/50 hover:text-primary'"
        @click="activeStage = stage.value"
      >
        <span class="text-sm" :class="activeStage === stage.value ? 'font-semibold' : 'font-medium'">
          {{ stage.label }}
        </span>
      </button>
    </div>

    <!-- Section 1: Hot AI Tools (Carousel) -->
    <div class="pt-6">
      <div class="flex items-center justify-between px-4 mb-3">
        <h2 class="text-xl font-bold flex items-center gap-2">
          <svg class="w-6 h-6 text-amber-400 fill-current" viewBox="0 0 24 24">
            <path d="M7 2v11h3v9l7-12h-4l4-8z" />
          </svg>
          热门 AI 助手
        </h2>
        <a class="text-sm text-primary font-medium" href="#">查看更多</a>
      </div>
      <div class="flex overflow-x-auto no-scrollbar gap-4 px-4 pb-4">
        <!-- Hot AI Tools Cards -->
        <div
          v-for="tool in hotAITools"
          :key="tool.id"
          class="flex flex-col min-w-[260px] w-[260px] rounded-2xl bg-surface-light dark:bg-surface-dark overflow-hidden shadow-sm border border-gray-100 dark:border-gray-800 cursor-pointer hover:shadow-md transition-shadow"
          @click="navigateToTool(tool)"
        >
          <div
            class="h-32 w-full bg-cover bg-center relative"
            :style="{ backgroundImage: `url(${tool.image})` }"
          >
            <div class="absolute inset-0 bg-gradient-to-t from-surface-dark/90 to-transparent"></div>
            <div class="absolute bottom-3 left-3 text-white">
              <div class="text-xs px-2 py-0.5 rounded text-white inline-block mb-1 font-bold" :class="tool.badgeClass">
                {{ tool.badge }}
              </div>
            </div>
          </div>
          <div class="p-4 flex flex-col gap-2">
            <div class="flex justify-between items-start">
              <h3 class="text-base font-bold text-slate-900 dark:text-white">{{ tool.name }}</h3>
              <svg class="w-6 h-6" :class="tool.iconClass" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" :d="tool.iconPath" />
              </svg>
            </div>
            <p class="text-sm text-gray-500 dark:text-gray-400 line-clamp-2">{{ tool.description }}</p>
            <button
              class="mt-2 w-full py-2 rounded-lg text-sm font-bold transition-colors"
              :class="tool.buttonClass"
            >
              {{ tool.buttonText }}
            </button>
          </div>
        </div>
      </div>
    </div>

    <!-- Section 2: Tools Grid -->
    <div class="pt-2 px-4 pb-6">
      <div class="flex items-center justify-between mb-3">
        <h2 class="text-xl font-bold">常用工具</h2>
        <button class="p-1 rounded-full bg-gray-200 dark:bg-gray-800">
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6V4m0 2a2 2 0 100 4m0-4a2 2 0 110 4m-6 8a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4m6 6v10m6-2a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4" />
          </svg>
        </button>
      </div>
      <div class="grid grid-cols-2 gap-3">
        <!-- Tool Items -->
        <div
          v-for="tool in filteredTools"
          :key="tool.id"
          class="flex flex-col p-4 rounded-2xl bg-surface-light dark:bg-surface-dark border border-gray-100 dark:border-gray-800 active:scale-95 transition-transform cursor-pointer"
          @click="navigateToTool(tool)"
        >
          <div class="flex items-start justify-between mb-3">
            <div
              class="w-10 h-10 rounded-full flex items-center justify-center"
              :class="tool.iconBgClass"
            >
              <svg class="w-6 h-6" :class="tool.iconColorClass" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" :d="tool.iconPath" />
              </svg>
            </div>
            <span class="text-[10px] font-bold uppercase tracking-wider text-gray-400 border border-gray-200 dark:border-gray-700 px-1.5 py-0.5 rounded">
              {{ tool.category }}
            </span>
          </div>
          <h3 class="font-bold text-slate-900 dark:text-white mb-1">{{ tool.name }}</h3>
          <p class="text-xs text-gray-500 dark:text-gray-400 leading-relaxed">{{ tool.description }}</p>
        </div>
      </div>
    </div>

    <!-- Floating Action Button for Quick Insight -->
    <button class="fixed right-4 bottom-24 h-14 w-14 rounded-full bg-primary text-white shadow-lg shadow-primary/40 flex items-center justify-center z-40 active:scale-90 transition-transform">
      <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z" />
      </svg>
    </button>

    <!-- Bottom Navigation -->
    <BottomNav />
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import BottomNav from '@/components/BottomNav.vue'

const router = useRouter()

// 搜索关键词
const searchQuery = ref('')

// 筛选阶段
const activeStage = ref('all')

const stages = [
  { value: 'all', label: '全部' },
  { value: 'acquisition', label: '拓客' },
  { value: 'communication', label: '沟通' },
  { value: 'proposal', label: '方案' },
  { value: 'closing', label: '促成' },
]

// 热门 AI 工具
const hotAITools = [
  {
    id: 1,
    name: '早报生成器',
    description: '一键聚合财经新闻，生成个性化晨报。',
    image: 'https://lh3.googleusercontent.com/aida-public/AB6AXuA-tu0kfbrM_EZ1v2XKiWvGM73_dmOlu0UqG1i0UTXM-sgXAKJ3p0ysBRtZeJBEex_EjQ7zJxranQir5yRZi4osIV-Y2qGKtA-f4CU07vksGNahrZWmq6wOWElEEZmejPyPAZRZ__K7k23ZPeaiN6jTZM1XmaBhm3rYX6_7Bkq-vGAG2ALHsO0Q1JhVCFlVcE_17-0YD5qowa7IVJigzf3a4YA5aqAAFnI_QRJ0khGJJLDwu1cQ4IEsQOQarhIHZKQl2RHHrUGg83TG',
    badge: '每日必用',
    badgeClass: 'bg-primary/90',
    iconClass: 'text-primary',
    iconPath: 'M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z',
    buttonText: '立即生成',
    buttonClass: 'bg-primary/10 hover:bg-primary/20 text-primary',
    route: '/tools/morning-report',
  },
  {
    id: 2,
    name: '智能话术助手',
    description: '针对不同客群，实时生成应对话术。',
    image: 'https://lh3.googleusercontent.com/aida-public/AB6AXuDuY5rZJ7fiR3zoFUZjDvy5RtQU1AHDnpj5eiSs_lf6RIAu676jKWhqd9ek2qLQGr__QkVfBGE7gZvrgwG_DnMcL00iuRnkWqMPC1sfy3YmqVtRmaQwIqyTHv0zmiIQgMWwjFnijx4987EpsFMLs-FFcRcpfLftcmhr914tb5QDUkpJViEWgyyTQ8JG8lVmauiMsRGfw4HhOI1vRVb8X3b8NpjUp7xNwYa1tUJuBkg2uO_nBye_xlNqoI4KEw9Ey0LLfWlwUL99BH5v',
    badge: '沟通神器',
    badgeClass: 'bg-purple-500/90',
    iconClass: 'text-purple-500',
    iconPath: 'M19 11a7 7 0 01-7 7m0 0a7 7 0 01-7-7m7 7v4m0 0H8m4 0h4m-4-8a3 3 0 01-3-3V5a3 3 0 116 0v6a3 3 0 01-3 3z',
    buttonText: '立即使用',
    buttonClass: 'bg-surface-light dark:bg-[#233648] hover:bg-gray-200 dark:hover:bg-[#2d465e] text-slate-900 dark:text-white border border-gray-200 dark:border-transparent',
    route: '/tools/marketing-script',
  },
  {
    id: 3,
    name: '资产配置书',
    description: '输入客户风险偏好，自动生成配置建议书。',
    image: 'https://lh3.googleusercontent.com/aida-public/AB6AXuCGTI0JhQjMdT4ywgS-HICjWk-JV6GI15ZAAZCdMsJ818g9llW6DEByme2kLLgGqY3sQ-sCdnAtrFYrLn6crYCLdSML_UTtWPKU4A-BnKut04JITsNAHd2j1lauPaUeY0ptlVFRpaNG8gyWOYoXKkK7funmyTYefBeKdjsu4MmV0xI2981846hr0Uo_PBWDNGv_DNtC_CZQjlwnLCc3kMjyGvhNvILQbSvBjAIM3Gqo9LZv3fz0ulI6g83U8XnKDyhdZBefIW2KhW3c',
    badge: '方案制作',
    badgeClass: 'bg-emerald-500/90',
    iconClass: 'text-emerald-500',
    iconPath: 'M11 3.055A9.001 9.001 0 1020.945 13H11V3.055z M20.488 9H15V3.512A9.025 9.025 0 0120.488 9z',
    buttonText: '立即使用',
    buttonClass: 'bg-surface-light dark:bg-[#233648] hover:bg-gray-200 dark:hover:bg-[#2d465e] text-slate-900 dark:text-white border border-gray-200 dark:border-transparent',
  },
]

// 常用工具
const commonTools = [
  {
    id: 1,
    name: '海报生成',
    description: '节日、热点营销海报一键制作',
    category: '拓客',
    categoryValue: 'acquisition',
    route: '/tools/poster-generator',
    iconBgClass: 'bg-blue-100 dark:bg-blue-900/30',
    iconColorClass: 'text-blue-600 dark:text-blue-400',
    iconPath: 'M11 5.882V19.24a1.76 1.76 0 01-3.417.592l-2.147-6.15M18 13a3 3 0 100-6M5.436 13.683A4.001 4.001 0 017 6h1.832c4.1 0 7.625-1.234 9.168-3v14c-1.543-1.766-5.067-3-9.168-3H7a3.988 3.988 0 01-1.564-.317z',
  },
  {
    id: 2,
    name: '潜客挖掘',
    description: 'AI 分析行内大数据筛选高潜客户',
    category: '拓客',
    categoryValue: 'acquisition',
    route: '/tools/potential-customers',
    iconBgClass: 'bg-indigo-100 dark:bg-indigo-900/30',
    iconColorClass: 'text-indigo-600 dark:text-indigo-400',
    iconPath: 'M3 4a1 1 0 011-1h16a1 1 0 011 1v2.586a1 1 0 01-.293.707l-6.414 6.414a1 1 0 00-.293.707V17l-4 4v-6.586a1 1 0 00-.293-.707L3.293 7.293A1 1 0 013 6.586V4z',
  },
  {
    id: 3,
    name: '产品对比',
    description: '多款理财/保险产品优劣势分析',
    category: '方案',
    categoryValue: 'proposal',
    route: '/products/compare',
    iconBgClass: 'bg-orange-100 dark:bg-orange-900/30',
    iconColorClass: 'text-orange-600 dark:text-orange-400',
    iconPath: 'M8 7h12m0 0l-4-4m4 4l-4 4m0 6H4m0 0l4 4m-4-4l4-4',
  },
  {
    id: 4,
    name: '收益演示',
    description: '可视化计算投资回报与复利效应',
    category: '促成',
    categoryValue: 'closing',
    route: '/tools/profit-calculator',
    iconBgClass: 'bg-green-100 dark:bg-green-900/30',
    iconColorClass: 'text-green-600 dark:text-green-400',
    iconPath: 'M9 7h6m0 10v-3m-3 3h.01M9 17h.01M9 14h.01M12 14h.01M15 11h.01M12 11h.01M9 11h.01M7 21h10a2 2 0 002-2V5a2 2 0 00-2-2H7a2 2 0 00-2 2v14a2 2 0 002 2z',
  },
  {
    id: 5,
    name: '合规质检',
    description: '生成合规风险提示文本',
    category: '促成',
    categoryValue: 'closing',
    iconBgClass: 'bg-red-100 dark:bg-red-900/30',
    iconColorClass: 'text-red-600 dark:text-red-400',
    iconPath: 'M3 6l3 1m0 0l-3 9a5.002 5.002 0 006.001 0M6 7l3 9M6 7l6-2m6 2l3-1m-3 1l-3 9a5.002 5.002 0 006.001 0M18 7l3 9m-3-9l-6-2m0-2v2m0 16V5m0 16H9m3 0h3',
  },
  {
    id: 6,
    name: '异议处理',
    description: '客户抗拒点实时拆解与应答',
    category: '沟通',
    categoryValue: 'communication',
    iconBgClass: 'bg-purple-100 dark:bg-purple-900/30',
    iconColorClass: 'text-purple-600 dark:text-purple-400',
    iconPath: 'M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z',
  },
]

// 根据筛选条件过滤工具
const filteredTools = computed(() => {
  if (activeStage.value === 'all') {
    return commonTools
  }
  return commonTools.filter(tool => tool.categoryValue === activeStage.value)
})

// 导航到工具页面
const navigateToTool = (tool: any) => {
  if (tool.route) {
    router.push(tool.route)
  } else {
    console.log('工具页面开发中:', tool.name)
  }
}
</script>

<style scoped>
/* Custom scrollbar hiding */
.no-scrollbar::-webkit-scrollbar {
  display: none;
}
.no-scrollbar {
  -ms-overflow-style: none;
  scrollbar-width: none;
}
</style>
