<template>
  <div class="min-h-screen bg-background-light dark:bg-background-dark pb-32">
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
      <h1 class="text-lg font-bold tracking-tight">早报生成器</h1>
      <button class="flex items-center justify-center p-2 -mr-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors">
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
      </button>
    </div>

    <!-- 早报定制设置 -->
    <div class="px-4 py-4 bg-surface-light dark:bg-surface-dark mb-4 shadow-sm">
      <div class="flex items-center justify-between mb-3">
        <h2 class="text-sm font-bold text-gray-500 dark:text-gray-400 uppercase tracking-wider flex items-center gap-1">
          <svg class="w-4 h-4 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6V4m0 2a2 2 0 100 4m0-4a2 2 0 110 4m-6 8a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4m6 6v10m6-2a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4" />
          </svg>
          早报定制设置
        </h2>
        <span class="text-xs text-primary font-medium">已选 {{ selectedCategories.length }} 项</span>
      </div>

      <!-- 分类选择 -->
      <div class="flex flex-wrap gap-2">
        <button
          v-for="category in categories"
          :key="category"
          class="flex items-center gap-1 px-3 py-1.5 rounded-lg text-sm font-medium transition-colors"
          :class="
            selectedCategories.includes(category)
              ? 'bg-primary/10 border border-primary text-primary'
              : 'bg-gray-100 dark:bg-gray-800 border border-transparent text-gray-600 dark:text-gray-400 hover:bg-gray-200 dark:hover:bg-gray-700'
          "
          @click="toggleCategory(category)"
        >
          <svg
            v-if="selectedCategories.includes(category)"
            class="w-4 h-4"
            fill="none"
            stroke="currentColor"
            viewBox="0 0 24 24"
          >
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
          </svg>
          {{ category }}
        </button>
      </div>

      <!-- 产品搜索 -->
      <div class="mt-3 relative">
        <input
          v-model="productName"
          class="w-full text-sm bg-gray-50 dark:bg-gray-900 border-gray-200 dark:border-gray-700 rounded-lg px-3 py-2 focus:ring-primary focus:border-primary placeholder-gray-400"
          placeholder="输入您关注的理财产品名称（如：安鑫盈3号）"
          type="text"
        />
        <svg class="absolute right-3 top-2.5 w-4 h-4 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
        </svg>
      </div>
    </div>

    <!-- 生成的早报内容 -->
    <div class="px-4">
      <div class="flex items-center gap-2 mb-3">
        <svg class="w-5 h-5 text-amber-500" fill="currentColor" viewBox="0 0 20 20">
          <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
        </svg>
        <h2 class="text-base font-bold">{{ reportTitle }}</h2>
      </div>

      <!-- 加载状态 -->
      <div v-if="isGenerating" class="bg-surface-light dark:bg-surface-dark rounded-2xl shadow-sm border border-gray-100 dark:border-gray-800 p-10 flex flex-col items-center justify-center">
        <div class="w-12 h-12 border-4 border-primary border-t-transparent rounded-full animate-spin mb-4"></div>
        <p class="text-sm text-gray-500">AI 正在为您生成早报...</p>
      </div>

      <!-- 早报卡片 -->
      <div v-else-if="report" class="bg-surface-light dark:bg-surface-dark rounded-2xl shadow-sm border border-gray-100 dark:border-gray-800 overflow-hidden">
        <!-- 封面 -->
        <div class="h-28 w-full relative bg-gray-800">
          <div
            class="absolute inset-0 bg-cover bg-center opacity-40"
            style="background-image: url('https://lh3.googleusercontent.com/aida-public/AB6AXuA-tu0kfbrM_EZ1v2XKiWvGM73_dmOlu0UqG1i0UTXM-sgXAKJ3p0ysBRtZeJBEex_EjQ7zJxranQir5yRZi4osIV-Y2qGKtA-f4CU07vksGNahrZWmq6wOWElEEZmejPyPAZRZ__K7k23ZPeaiN6jTZM1XmaBhm3rYX6_7Bkq-vGAG2ALHsO0Q1JhVCFlVcE_17-0YD5qowa7IVJigzf3a4YA5aqAAFnI_QRJ0khGJJLDwu1cQ4IEsQOQarhIHZKQl2RHHrUGg83TG')"
          ></div>
          <div class="absolute inset-0 bg-gradient-to-t from-surface-dark to-transparent opacity-80"></div>
          <div class="absolute bottom-0 left-0 p-5 w-full">
            <div class="flex items-center justify-between w-full">
              <div>
                <h3 class="text-white font-bold text-xl tracking-tight">财经早报</h3>
                <p class="text-gray-300 text-xs mt-1 font-medium">AI 智能聚合 · 专属定制</p>
              </div>
              <div class="bg-white/10 backdrop-blur-sm px-2 py-1 rounded-lg border border-white/10">
                <span class="text-white text-xs font-bold">{{ formattedDate }}</span>
              </div>
            </div>
          </div>
        </div>

        <!-- 早报内容 -->
        <div class="p-5 space-y-6">
          <!-- 早安寄语 -->
          <div class="flex gap-3">
            <div class="flex-1 bg-blue-50 dark:bg-blue-900/10 rounded-xl p-3 border border-blue-100 dark:border-blue-900/20">
              <p class="text-sm text-slate-800 dark:text-blue-100 leading-relaxed">
                <span class="font-bold text-primary">早安！</span> {{ report.greeting }}
              </p>
            </div>
          </div>

          <!-- 宏观速递 -->
          <div v-if="report.macroNews.length > 0">
            <div class="flex items-center gap-2 mb-2">
              <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3.055 11H5a2 2 0 012 2v1a2 2 0 002 2 2 2 0 012 2v2.945M8 3.935V5.5A2.5 2.5 0 0010.5 8h.5a2 2 0 012 2 2 2 0 104 0 2 2 0 012-2h1.064M15 20.488V18a2 2 0 012-2h3.064M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
              </svg>
              <h4 class="font-bold text-sm text-slate-900 dark:text-white">宏观速递</h4>
            </div>
            <ul class="space-y-3 pl-1">
              <li
                v-for="(news, index) in report.macroNews"
                :key="index"
                class="relative pl-4 border-l-2 border-gray-200 dark:border-gray-700"
              >
                <h5 class="text-sm font-semibold text-slate-900 dark:text-gray-200">{{ news.title }}</h5>
                <p class="text-xs text-gray-500 dark:text-gray-400 mt-1 leading-relaxed">{{ news.description }}</p>
              </li>
            </ul>
          </div>

          <!-- 市场行情 -->
          <div v-if="report.marketData.length > 0">
            <div class="flex items-center gap-2 mb-2">
              <svg class="w-5 h-5 text-red-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 12l3-3 3 3 4-4M8 21l4-4 4 4M3 4h18M4 4h16v12a1 1 0 01-1 1H5a1 1 0 01-1-1V4z" />
              </svg>
              <h4 class="font-bold text-sm text-slate-900 dark:text-white">市场行情</h4>
            </div>
            <div class="grid grid-cols-3 gap-2">
              <div
                v-for="(market, index) in report.marketData"
                :key="index"
                class="bg-gray-50 dark:bg-gray-800/50 p-2 rounded-lg text-center"
              >
                <div class="text-[10px] text-gray-500 mb-0.5">{{ market.name }}</div>
                <div
                  class="text-sm font-bold"
                  :class="market.isPositive ? 'text-red-500' : 'text-green-500'"
                >
                  {{ market.value }}
                </div>
                <div
                  class="text-[10px] font-medium"
                  :class="market.isPositive ? 'text-red-500' : 'text-green-500'"
                >
                  {{ market.change }}
                </div>
              </div>
            </div>
          </div>

          <!-- 关注产品 -->
          <div v-if="report.productAnalysis">
            <div class="flex items-center justify-between mb-2">
              <div class="flex items-center gap-2">
                <svg class="w-5 h-5 text-purple-500" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                </svg>
                <h4 class="font-bold text-sm text-slate-900 dark:text-white">
                  关注产品：{{ report.productAnalysis.productName }}
                </h4>
              </div>
              <span
                class="text-[10px] px-1.5 py-0.5 rounded font-medium"
                :class="getSentimentClass(report.productAnalysis.sentiment)"
              >
                {{ report.productAnalysis.sentiment }}
              </span>
            </div>
            <div class="bg-gray-50 dark:bg-gray-800/50 p-3 rounded-xl border border-gray-100 dark:border-gray-700">
              <p class="text-xs text-gray-600 dark:text-gray-300 leading-relaxed">
                {{ report.productAnalysis.analysis }}
              </p>
            </div>
          </div>
        </div>

        <!-- 底部操作栏 -->
        <div class="bg-gray-50 dark:bg-gray-900/50 px-5 py-3 border-t border-gray-100 dark:border-gray-800 flex justify-between items-center text-xs text-gray-400">
          <span>AI 生成内容仅供参考</span>
          <div class="flex gap-4">
            <button class="hover:text-primary transition-colors flex items-center gap-1">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 10h4.764a2 2 0 011.789 2.894l-3.5 7A2 2 0 0115.263 21h-4.017c-.163 0-.326-.02-.485-.06L7 20m7-10V5a2 2 0 00-2-2h-.095c-.5 0-.905.405-.905.905 0 .714-.211 1.412-.608 2.006L7 11v9m7-10h-2M7 20H5a2 2 0 01-2-2v-6a2 2 0 012-2h2.5" />
              </svg>
            </button>
            <button class="hover:text-primary transition-colors flex items-center gap-1">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 14H5.236a2 2 0 01-1.789-2.894l3.5-7A2 2 0 018.736 3h4.018a2 2 0 01.485.06l3.76.94m-7 10v5a2 2 0 002 2h.096c.5 0 .905-.405.905-.904 0-.715.211-1.413.608-2.008L17 13V4m-7 10h2m5-10h2a2 2 0 012 2v6a2 2 0 01-2 2h-2.5" />
              </svg>
            </button>
            <button class="hover:text-primary transition-colors flex items-center gap-1" @click="copyReport">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 5H6a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2v-1M8 5a2 2 0 002 2h2a2 2 0 002-2M8 5a2 2 0 012-2h2a2 2 0 012 2m0 0h2a2 2 0 012 2v3m2 4H10m0 0l3-3m-3 3l3 3" />
              </svg>
              复制
            </button>
          </div>
        </div>
      </div>

      <!-- 空状态 -->
      <div v-else class="bg-surface-light dark:bg-surface-dark rounded-2xl shadow-sm border border-gray-100 dark:border-gray-800 p-10 flex flex-col items-center justify-center">
        <svg class="w-16 h-16 text-gray-300 dark:text-gray-700 mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
        </svg>
        <p class="text-sm text-gray-500 mb-2">暂无早报内容</p>
        <p class="text-xs text-gray-400">点击下方"立即生成"按钮开始</p>
      </div>
    </div>

    <!-- 底部操作栏 -->
    <div class="fixed bottom-0 left-0 right-0 bg-surface-light dark:bg-surface-dark border-t border-gray-200 dark:border-gray-800 px-4 py-4 pb-8 z-50">
      <div class="flex items-center gap-3">
        <div class="flex flex-col items-center justify-center gap-1 min-w-[3.5rem] cursor-pointer" role="button">
          <label class="relative inline-flex items-center cursor-pointer">
            <input v-model="autoSubscribe" class="sr-only peer" type="checkbox" />
            <div class="w-9 h-5 bg-gray-200 peer-focus:outline-none rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-4 after:w-4 after:transition-all dark:border-gray-600 peer-checked:bg-primary"></div>
          </label>
          <span class="text-[10px] text-gray-500 font-medium">每日订阅</span>
        </div>
        <button class="flex flex-col items-center justify-center h-12 w-14 rounded-xl bg-gray-100 dark:bg-gray-800 text-slate-700 dark:text-gray-300 active:bg-gray-200 dark:active:bg-gray-700 transition-colors">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8.684 13.342C8.886 12.938 9 12.482 9 12c0-.482-.114-.938-.316-1.342m0 2.684a3 3 0 110-2.684m0 2.684l6.632 3.316m-6.632-6l6.632-3.316m0 0a3 3 0 105.367-2.684 3 3 0 00-5.367 2.684zm0 9.316a3 3 0 105.368 2.684 3 3 0 00-5.368-2.684z" />
          </svg>
          <span class="text-[10px] font-medium -mt-0.5">分享</span>
        </button>
        <button
          class="flex-1 h-12 bg-primary hover:bg-primary/90 active:scale-[0.98] text-white rounded-xl font-bold text-base shadow-lg shadow-primary/25 flex items-center justify-center gap-2 transition-all"
          :disabled="isGenerating || selectedCategories.length === 0"
          @click="handleGenerate"
        >
          <svg
            class="w-5 h-5"
            :class="{ 'animate-spin': isGenerating }"
            fill="currentColor"
            viewBox="0 0 20 20"
          >
            <path fill-rule="evenodd" d="M4 2a1 1 0 011 1v2.101a7.002 7.002 0 0111.601 2.566 1 1 0 11-1.885.666A5.002 5.002 0 005.999 7H9a1 1 0 010 2H4a1 1 0 01-1-1V3a1 1 0 011-1zm.008 9.057a1 1 0 011.276.61A5.002 5.002 0 0014.001 13H11a1 1 0 110-2h5a1 1 0 011 1v5a1 1 0 11-2 0v-2.101a7.002 7.002 0 01-11.601-2.566 1 1 0 01.61-1.276z" clip-rule="evenodd" />
          </svg>
          {{ isGenerating ? '生成中...' : '立即生成' }}
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed } from 'vue'
import { useRouter } from 'vue-router'
import { generateMorningReport, type MorningReportResponse } from '@/api/morning-report'

const router = useRouter()

// 可选分类
const categories = ['宏观经济', '行业动态', '特定产品', '黄金/外汇', '保险市场']

// 选中的分类
const selectedCategories = ref<string[]>(['宏观经济', '行业动态', '特定产品'])

// 产品名称
const productName = ref('')

// 早报数据
const report = ref<MorningReportResponse | null>(null)

// 生成状态
const isGenerating = ref(false)

// 自动订阅
const autoSubscribe = ref(true)

// 格式化日期
const formattedDate = computed(() => {
  const now = new Date()
  const weekDays = ['周日', '周一', '周二', '周三', '周四', '周五', '周六']
  const month = now.getMonth() + 1
  const day = now.getDate()
  const weekDay = weekDays[now.getDay()]
  return `${weekDay} · ${month}.${day}`
})

// 报告标题
const reportTitle = computed(() => {
  if (!report.value) return '今日早报'
  const now = new Date()
  return `今日已生成 (${now.getMonth() + 1}月${now.getDate()}日)`
})

// 切换分类
const toggleCategory = (category: string) => {
  const index = selectedCategories.value.indexOf(category)
  if (index > -1) {
    selectedCategories.value.splice(index, 1)
  } else {
    selectedCategories.value.push(category)
  }
}

// 生成早报
const handleGenerate = async () => {
  if (selectedCategories.value.length === 0) {
    alert('请至少选择一个分类')
    return
  }

  try {
    isGenerating.value = true
    const result = await generateMorningReport({
      categories: selectedCategories.value,
      productName: productName.value || undefined,
    })
    report.value = result
  } catch (error: any) {
    console.error('生成早报失败:', error)
    alert('生成早报失败: ' + (error.message || '请稍后重试'))
  } finally {
    isGenerating.value = false
  }
}

// 获取情绪标签样式
const getSentimentClass = (sentiment: string) => {
  const classMap: Record<string, string> = {
    利好: 'bg-purple-100 dark:bg-purple-900/30 text-purple-600 dark:text-purple-300',
    中性: 'bg-gray-100 dark:bg-gray-800 text-gray-600 dark:text-gray-300',
    利空: 'bg-red-100 dark:bg-red-900/30 text-red-600 dark:text-red-300',
  }
  return classMap[sentiment] || classMap['中性']
}

// 复制早报
const copyReport = () => {
  if (!report.value) return

  let text = `财经早报 - ${formattedDate.value}\n\n`
  text += `${report.value.greeting}\n\n`

  if (report.value.macroNews.length > 0) {
    text += '【宏观速递】\n'
    report.value.macroNews.forEach((news, i) => {
      text += `${i + 1}. ${news.title}\n${news.description}\n\n`
    })
  }

  if (report.value.marketData.length > 0) {
    text += '【市场行情】\n'
    report.value.marketData.forEach(market => {
      text += `${market.name}: ${market.value} (${market.change})\n`
    })
    text += '\n'
  }

  if (report.value.productAnalysis) {
    text += `【关注产品：${report.value.productAnalysis.productName}】\n`
    text += `${report.value.productAnalysis.analysis}\n`
  }

  navigator.clipboard.writeText(text).then(() => {
    alert('早报内容已复制到剪贴板')
  })
}
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
