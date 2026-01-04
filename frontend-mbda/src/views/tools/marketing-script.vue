<template>
  <div class="relative flex min-h-screen w-full flex-col overflow-hidden pb-24">
    <!-- Top App Bar -->
    <div class="sticky top-0 z-50 bg-background-light/95 dark:bg-background-dark/95 backdrop-blur-md px-4 py-3 flex items-center justify-between border-b border-gray-200 dark:border-gray-800">
      <button @click="router.back()" class="flex items-center justify-center p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors">
        <svg class="w-6 h-6 text-gray-600 dark:text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
        </svg>
      </button>
      <h1 class="text-lg font-bold tracking-tight">智能话术助手</h1>
      <button class="flex items-center justify-center p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors">
        <svg class="w-6 h-6 text-gray-600 dark:text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
        </svg>
      </button>
    </div>

    <div class="px-4 py-6 space-y-6">
      <!-- 选择沟通场景 -->
      <div>
        <h2 class="text-sm font-semibold text-gray-500 dark:text-gray-400 mb-3 uppercase tracking-wider">选择沟通场景</h2>
        <div class="flex gap-3 overflow-x-auto no-scrollbar pb-1">
          <label
            v-for="scenario in scenarios"
            :key="scenario.value"
            class="cursor-pointer group flex-shrink-0"
          >
            <input
              v-model="selectedScenario"
              :value="scenario.value"
              class="peer sr-only"
              name="scenario"
              type="radio"
            />
            <div class="flex flex-col items-center justify-center w-24 h-24 rounded-2xl bg-surface-light dark:bg-surface-dark border-2 border-transparent peer-checked:border-primary peer-checked:bg-primary/5 transition-all shadow-sm">
              <svg class="w-8 h-8 mb-2 group-hover:scale-110 transition-transform" :class="scenario.iconClass" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" :d="scenario.iconPath" />
              </svg>
              <span class="text-xs font-medium text-center">{{ scenario.label }}</span>
            </div>
          </label>
        </div>
      </div>

      <!-- 客户画像 -->
      <div>
        <h2 class="text-sm font-semibold text-gray-500 dark:text-gray-400 mb-3 uppercase tracking-wider">客户画像</h2>
        <div class="grid grid-cols-2 gap-3">
          <label
            v-for="customer in customerTypes"
            :key="customer.value"
            class="cursor-pointer relative"
          >
            <input
              v-model="selectedCustomerType"
              :value="customer.value"
              class="peer sr-only"
              name="customer"
              type="radio"
            />
            <div class="p-3 rounded-xl bg-surface-light dark:bg-surface-dark border border-gray-100 dark:border-gray-800 peer-checked:ring-2 peer-checked:ring-primary peer-checked:bg-primary/5 flex items-center gap-3 transition-all">
              <div class="w-10 h-10 rounded-full flex items-center justify-center" :class="customer.iconBgClass">
                <svg class="w-5 h-5" :class="customer.iconColorClass" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" :d="customer.iconPath" />
                </svg>
              </div>
              <div>
                <div class="font-bold text-sm">{{ customer.label }}</div>
                <div class="text-[10px] text-gray-500 dark:text-gray-400">{{ customer.subtitle }}</div>
              </div>
            </div>
            <div class="absolute top-2 right-2 opacity-0 peer-checked:opacity-100 transition-opacity">
              <svg class="w-5 h-5 text-primary" fill="currentColor" viewBox="0 0 24 24">
                <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm-2 15l-5-5 1.41-1.41L10 14.17l7.59-7.59L19 8l-9 9z" />
              </svg>
            </div>
          </label>
        </div>

        <!-- 自定义客户描述 -->
        <div v-if="selectedCustomerType === 'custom'" class="mt-3">
          <input
            v-model="customCustomerDesc"
            type="text"
            class="w-full px-3 py-2 bg-surface-light dark:bg-surface-dark border border-gray-200 dark:border-gray-700 rounded-lg text-sm placeholder-gray-400 focus:ring-2 focus:ring-primary/50 focus:border-primary"
            placeholder="请描述客户特征，如：退休老人，注重本金安全..."
          />
        </div>
      </div>

      <!-- 关键信息 -->
      <div>
        <h2 class="text-sm font-semibold text-gray-500 dark:text-gray-400 mb-3 uppercase tracking-wider">关键信息 (可选)</h2>
        <div class="bg-surface-light dark:bg-surface-dark rounded-xl p-3 shadow-sm border border-gray-100 dark:border-gray-800">
          <textarea
            v-model="keyInfo"
            class="w-full bg-transparent border-none focus:ring-0 text-sm placeholder-gray-400 resize-none h-20"
            placeholder="输入产品名称（如：稳盈3号）或特定的客户异议点..."
            maxlength="200"
          ></textarea>
          <div class="flex justify-between items-center mt-2 pt-2 border-t border-gray-100 dark:border-gray-800">
            <button class="flex items-center gap-1 text-xs text-primary font-medium hover:bg-primary/10 px-2 py-1 rounded transition-colors">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 11a7 7 0 01-7 7m0 0a7 7 0 01-7-7m7 7v4m0 0H8m4 0h4m-4-8a3 3 0 01-3-3V5a3 3 0 116 0v6a3 3 0 01-3 3z" />
              </svg>
              语音输入
            </button>
            <span class="text-xs text-gray-400">{{ keyInfo.length }}/200</span>
          </div>
        </div>
      </div>

      <!-- 生成按钮 -->
      <button
        @click="handleGenerate"
        :disabled="isGenerating"
        class="w-full bg-primary hover:bg-primary-dark text-white font-bold py-3.5 px-4 rounded-xl shadow-lg shadow-primary/30 flex items-center justify-center gap-2 active:scale-[0.98] transition-all disabled:opacity-50 disabled:cursor-not-allowed"
      >
        <svg v-if="isGenerating" class="w-5 h-5 animate-spin" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
        </svg>
        <svg v-else class="w-5 h-5" fill="currentColor" viewBox="0 0 24 24">
          <path d="M7 2v11h3v9l7-12h-4l4-8z" />
        </svg>
        {{ isGenerating ? '生成中...' : '生成AI话术' }}
      </button>

      <!-- 推荐话术 -->
      <div v-if="scripts.length > 0" class="pt-4">
        <div class="flex items-center justify-between mb-4">
          <h2 class="text-lg font-bold flex items-center gap-2">
            推荐话术
            <span class="bg-green-100 dark:bg-green-900 text-green-700 dark:text-green-300 text-[10px] px-2 py-0.5 rounded-full uppercase">AI 生成</span>
          </h2>
          <div class="flex gap-2">
            <button class="text-xs text-gray-500 flex items-center gap-1 hover:text-primary transition-colors">
              <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6V4m0 2a2 2 0 100 4m0-4a2 2 0 110 4m-6 8a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4m6 6v10m6-2a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4" />
              </svg>
              调整语气
            </button>
          </div>
        </div>

        <div class="space-y-4">
          <div
            v-for="(script, index) in scripts"
            :key="index"
            class="bg-surface-light dark:bg-surface-dark rounded-2xl p-5 shadow-sm border border-gray-100 dark:border-gray-800 relative group"
          >
            <div class="flex items-center gap-2 mb-3">
              <span class="text-[10px] font-bold px-2 py-0.5 rounded border" :class="script.styleClass">
                {{ script.styleBadge }}
              </span>
              <span class="text-gray-400 text-xs">· {{ script.suitableFor }}</span>
            </div>
            <p class="text-sm leading-relaxed text-slate-800 dark:text-slate-200">
              {{ script.content }}
            </p>
            <div class="flex items-center justify-between mt-4 pt-3 border-t border-gray-100 dark:border-gray-800">
              <div class="flex gap-3">
                <button
                  @click="copyScript(script.content)"
                  class="flex items-center gap-1 text-gray-500 hover:text-primary transition-colors text-xs"
                >
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 16H6a2 2 0 01-2-2V6a2 2 0 012-2h8a2 2 0 012 2v2m-6 12h8a2 2 0 002-2v-8a2 2 0 00-2-2h-8a2 2 0 00-2 2v8a2 2 0 002 2z" />
                  </svg>
                  复制
                </button>
                <button class="flex items-center gap-1 text-gray-500 hover:text-red-500 transition-colors text-xs">
                  <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4.318 6.318a4.5 4.5 0 000 6.364L12 20.364l7.682-7.682a4.5 4.5 0 00-6.364-6.364L12 7.636l-1.318-1.318a4.5 4.5 0 00-6.364 0z" />
                  </svg>
                  收藏
                </button>
              </div>
              <button class="flex items-center gap-1 text-gray-500 hover:text-gray-700 dark:hover:text-gray-300 transition-colors text-xs">
                <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8.684 13.342C8.886 12.938 9 12.482 9 12c0-.482-.114-.938-.316-1.342m0 2.684a3 3 0 110-2.684m0 2.684l6.632 3.316m-6.632-6l6.632-3.316m0 0a3 3 0 105.367-2.684 3 3 0 00-5.367 2.684zm0 9.316a3 3 0 105.368 2.684 3 3 0 00-5.368-2.684z" />
                </svg>
                分享
              </button>
            </div>
          </div>
        </div>

        <!-- 换一批按钮 -->
        <div class="flex justify-center mt-6 mb-8">
          <button
            @click="handleGenerate"
            :disabled="isGenerating"
            class="flex items-center gap-2 px-4 py-2 bg-surface-light dark:bg-surface-dark border border-gray-200 dark:border-gray-700 rounded-full text-sm font-medium text-gray-600 dark:text-gray-300 hover:border-primary hover:text-primary transition-all shadow-sm disabled:opacity-50"
          >
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 4v5h.582m15.356 2A8.001 8.001 0 004.582 9m0 0H9m11 11v-5h-.581m0 0a8.003 8.003 0 01-15.357-2m15.357 2H15" />
            </svg>
            换一批话术
          </button>
        </div>
      </div>
    </div>

    <!-- Bottom Navigation -->
    <BottomNav />
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import BottomNav from '@/components/BottomNav.vue'
import { generateMarketingScript, type MarketingScript } from '@/api/marketing-script'

const router = useRouter()

// 沟通场景
const scenarios = [
  {
    value: 'product_recommend',
    label: '产品推荐',
    iconClass: 'text-primary',
    iconPath: 'M11 5.882V19.24a1.76 1.76 0 01-3.417.592l-2.147-6.15M18 13a3 3 0 100-6M5.436 13.683A4.001 4.001 0 017 6h1.832c4.1 0 7.625-1.234 9.168-3v14c-1.543-1.766-5.067-3-9.168-3H7a3.988 3.988 0 01-1.564-.317z',
  },
  {
    value: 'objection_handling',
    label: '异议处理',
    iconClass: 'text-orange-500',
    iconPath: 'M8.228 9c.549-1.165 2.03-2 3.772-2 2.21 0 4 1.343 4 3 0 1.4-1.278 2.575-3.006 2.907-.542.104-.994.54-.994 1.093m0 3h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z',
  },
  {
    value: 'customer_maintenance',
    label: '客户维护',
    iconClass: 'text-green-500',
    iconPath: 'M12 4.354a4 4 0 110 5.292M15 21H3v-1a6 6 0 0112 0v1zm0 0h6v-1a6 6 0 00-9-5.197M13 7a4 4 0 11-8 0 4 4 0 018 0z',
  },
  {
    value: 'invite_meeting',
    label: '邀约见面',
    iconClass: 'text-purple-500',
    iconPath: 'M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z',
  },
]

// 客户类型
const customerTypes = [
  {
    value: 'high_net_worth',
    label: '高净值客户',
    subtitle: '注重资产保值',
    iconBgClass: 'bg-indigo-100 dark:bg-indigo-900/30',
    iconColorClass: 'text-indigo-600 dark:text-indigo-400',
    iconPath: 'M5 3v4M3 5h4M6 17v4m-2-2h4m5-16l2.286 6.857L21 12l-5.714 2.143L13 21l-2.286-6.857L5 12l5.714-2.143L13 3z',
  },
  {
    value: 'young_professional',
    label: '年轻白领',
    subtitle: '追求灵活收益',
    iconBgClass: 'bg-pink-100 dark:bg-pink-900/30',
    iconColorClass: 'text-pink-600 dark:text-pink-400',
    iconPath: 'M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z',
  },
  {
    value: 'entrepreneur',
    label: '企业主',
    subtitle: '关注现金流',
    iconBgClass: 'bg-blue-100 dark:bg-blue-900/30',
    iconColorClass: 'text-blue-600 dark:text-blue-400',
    iconPath: 'M21 13.255A23.931 23.931 0 0112 15c-3.183 0-6.22-.62-9-1.745M16 6V4a2 2 0 00-2-2h-4a2 2 0 00-2 2v2m4 6h.01M5 20h14a2 2 0 002-2V8a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z',
  },
  {
    value: 'custom',
    label: '自定义',
    subtitle: '输入特征',
    iconBgClass: 'bg-gray-100 dark:bg-gray-800',
    iconColorClass: 'text-gray-600 dark:text-gray-400',
    iconPath: 'M12 4v16m8-8H4',
  },
]

// 表单数据
const selectedScenario = ref('product_recommend')
const selectedCustomerType = ref('high_net_worth')
const customCustomerDesc = ref('')
const keyInfo = ref('')

// 生成的话术
const scripts = ref<MarketingScript[]>([])
const isGenerating = ref(false)

// 生成话术
const handleGenerate = async () => {
  if (selectedCustomerType.value === 'custom' && !customCustomerDesc.value.trim()) {
    alert('请输入自定义客户描述')
    return
  }

  try {
    isGenerating.value = true
    const result = await generateMarketingScript({
      scenario: selectedScenario.value,
      customerType: selectedCustomerType.value,
      customCustomerDesc: customCustomerDesc.value || undefined,
      keyInfo: keyInfo.value || undefined,
    })
    scripts.value = result.scripts
  } catch (error: any) {
    console.error('生成话术失败:', error)
    alert('生成话术失败: ' + (error.message || '请稍后重试'))
  } finally {
    isGenerating.value = false
  }
}

// 复制话术
const copyScript = async (content: string) => {
  try {
    await navigator.clipboard.writeText(content)
    alert('已复制到剪贴板')
  } catch (error) {
    console.error('复制失败:', error)
    alert('复制失败，请手动复制')
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
