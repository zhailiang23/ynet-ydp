<template>
  <div class="flex flex-col min-h-screen bg-background-light dark:bg-background-dark">
    <!-- Top App Bar -->
    <div class="sticky top-0 z-50 bg-background-light/95 dark:bg-background-dark/95 backdrop-blur-md px-4 py-3 flex items-center justify-between border-b border-gray-200 dark:border-gray-800">
      <button @click="router.back()" class="flex items-center justify-center p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors -ml-2">
        <svg class="w-6 h-6 text-gray-600 dark:text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
        </svg>
      </button>
      <h1 class="text-lg font-bold tracking-tight">收益演示</h1>
      <button class="flex items-center justify-center p-2 rounded-full hover:bg-gray-200 dark:hover:bg-gray-800 transition-colors -mr-2">
        <svg class="w-6 h-6 text-gray-600 dark:text-gray-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10.325 4.317c.426-1.756 2.924-1.756 3.35 0a1.724 1.724 0 002.573 1.066c1.543-.94 3.31.826 2.37 2.37a1.724 1.724 0 001.065 2.572c1.756.426 1.756 2.924 0 3.35a1.724 1.724 0 00-1.066 2.573c.94 1.543-.826 3.31-2.37 2.37a1.724 1.724 0 00-2.572 1.065c-.426 1.756-2.924 1.756-3.35 0a1.724 1.724 0 00-2.573-1.066c-1.543.94-3.31-.826-2.37-2.37a1.724 1.724 0 00-1.065-2.572c-1.756-.426-1.756-2.924 0-3.35a1.724 1.724 0 001.066-2.573c-.94-1.543.826-3.31 2.37-2.37.996.608 2.296.07 2.572-1.065z" />
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z" />
        </svg>
      </button>
    </div>

    <main class="flex-1 overflow-y-auto no-scrollbar pb-32">
      <!-- 收益展示卡片 -->
      <div class="px-4 pt-6 pb-2">
        <div class="relative overflow-hidden rounded-3xl bg-surface-light dark:bg-surface-dark shadow-sm border border-gray-100 dark:border-gray-800">
          <div class="absolute top-0 right-0 w-64 h-64 bg-primary/5 dark:bg-primary/10 rounded-full blur-3xl -mr-16 -mt-16 pointer-events-none"></div>
          <div class="p-6 relative z-10">
            <div class="flex items-center justify-between mb-1">
              <span class="text-sm font-medium text-gray-500 dark:text-gray-400">预估期末总金额</span>
              <div class="flex items-center gap-1 text-xs px-2 py-0.5 rounded-full bg-green-100 dark:bg-green-900/30 text-green-600 dark:text-green-400 font-medium">
                <svg class="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                </svg>
                <span>+{{ profitRate }}%</span>
              </div>
            </div>
            <div class="flex items-baseline gap-1 mb-6">
              <span class="text-3xl font-bold tracking-tight text-slate-900 dark:text-white">¥</span>
              <span class="text-4xl font-extrabold tracking-tight text-slate-900 dark:text-white">{{ formatNumber(Math.floor(totalAmount)) }}</span>
              <span class="text-xl font-medium text-gray-400">.{{ formatDecimal(totalAmount) }}</span>
            </div>

            <!-- 收益曲线图 -->
            <div class="h-48 w-full mb-4 relative">
              <div class="absolute inset-0 flex flex-col justify-between text-[10px] text-gray-400 pointer-events-none">
                <div class="border-b border-dashed border-gray-200 dark:border-gray-800 w-full h-0"></div>
                <div class="border-b border-dashed border-gray-200 dark:border-gray-800 w-full h-0"></div>
                <div class="border-b border-dashed border-gray-200 dark:border-gray-800 w-full h-0"></div>
                <div class="border-b border-dashed border-gray-200 dark:border-gray-800 w-full h-0"></div>
                <div class="border-b border-gray-200 dark:border-gray-800 w-full h-0"></div>
              </div>
              <svg class="absolute inset-0 h-full w-full overflow-visible" preserveAspectRatio="none" viewBox="0 0 100 100">
                <defs>
                  <linearGradient id="chartGradient" x1="0%" x2="0%" y1="0%" y2="100%">
                    <stop offset="0%" stop-color="#137fec" stop-opacity="0.3"></stop>
                    <stop offset="100%" stop-color="#137fec" stop-opacity="0"></stop>
                  </linearGradient>
                </defs>
                <path :d="chartAreaPath" fill="url(#chartGradient)"></path>
                <path :d="chartLinePath" fill="none" stroke="#137fec" stroke-linecap="round" stroke-width="3"></path>
                <circle class="fill-surface-light dark:fill-surface-dark stroke-primary" cx="0" cy="80" r="3" stroke-width="2"></circle>
                <circle class="fill-surface-light dark:fill-surface-dark stroke-primary" :cx="chartMidPoint.x" :cy="chartMidPoint.y" r="3" stroke-width="2"></circle>
                <circle class="fill-primary stroke-white dark:stroke-surface-dark" :cx="chartEndPoint.x" :cy="chartEndPoint.y" r="4" stroke-width="2"></circle>
              </svg>
              <div class="absolute bottom-[-20px] left-0 right-0 flex justify-between text-[10px] text-gray-400">
                <span>现在</span>
                <span>{{ Math.floor(years / 2) }}年</span>
                <span>{{ years }}年</span>
              </div>
            </div>

            <div class="grid grid-cols-2 gap-4 pt-6 border-t border-gray-100 dark:border-gray-800">
              <div>
                <p class="text-xs text-gray-500 dark:text-gray-400 mb-1">累计收益 (利息)</p>
                <p class="text-lg font-bold text-green-600 dark:text-green-400">+ ¥{{ formatNumber(totalProfit) }}</p>
              </div>
              <div>
                <p class="text-xs text-gray-500 dark:text-gray-400 mb-1">本金投入</p>
                <p class="text-lg font-bold text-slate-900 dark:text-white">¥{{ formatNumber(principal) }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- 参数设置 -->
      <div class="px-4 py-4 space-y-4">
        <h2 class="text-sm font-bold text-gray-500 dark:text-gray-400 px-1">参数设置</h2>

        <!-- 初始投资金额 -->
        <div class="bg-surface-light dark:bg-surface-dark p-4 rounded-2xl border border-gray-100 dark:border-gray-800 shadow-sm">
          <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">初始投资金额 (元)</label>
          <div class="flex items-center border-b border-gray-200 dark:border-gray-700 pb-1 focus-within:border-primary transition-colors">
            <span class="text-2xl font-bold text-gray-400 mr-2">¥</span>
            <input
              v-model="principalInput"
              @input="updatePrincipal"
              class="block w-full border-0 bg-transparent p-0 text-2xl font-bold text-slate-900 dark:text-white focus:ring-0 placeholder-gray-300"
              placeholder="0"
              type="text"
            />
          </div>
          <div class="flex gap-2 mt-3">
            <button @click="addPrincipal(10000)" class="px-3 py-1 text-xs font-medium rounded-lg bg-gray-100 dark:bg-gray-800 text-gray-600 dark:text-gray-300 hover:bg-gray-200 dark:hover:bg-gray-700 transition-colors">+1万</button>
            <button @click="addPrincipal(50000)" class="px-3 py-1 text-xs font-medium rounded-lg bg-gray-100 dark:bg-gray-800 text-gray-600 dark:text-gray-300 hover:bg-gray-200 dark:hover:bg-gray-700 transition-colors">+5万</button>
            <button @click="addPrincipal(100000)" class="px-3 py-1 text-xs font-medium rounded-lg bg-gray-100 dark:bg-gray-800 text-gray-600 dark:text-gray-300 hover:bg-gray-200 dark:hover:bg-gray-700 transition-colors">+10万</button>
          </div>
        </div>

        <!-- 年化收益率 -->
        <div class="bg-surface-light dark:bg-surface-dark p-4 rounded-2xl border border-gray-100 dark:border-gray-800 shadow-sm">
          <div class="flex justify-between items-center mb-2">
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300">年化收益率</label>
            <span class="text-xl font-bold text-primary">{{ rate }}%</span>
          </div>
          <input
            v-model.number="rate"
            class="w-full h-2 bg-gray-200 rounded-lg appearance-none cursor-pointer dark:bg-gray-700 accent-primary"
            max="20"
            min="1"
            step="0.1"
            type="range"
          />
          <div class="flex justify-between text-xs text-gray-400 mt-2">
            <span>1%</span>
            <span>10%</span>
            <span>20%</span>
          </div>
        </div>

        <!-- 投资期限 和 复利计算 -->
        <div class="grid grid-cols-2 gap-3">
          <div class="bg-surface-light dark:bg-surface-dark p-4 rounded-2xl border border-gray-100 dark:border-gray-800 shadow-sm flex flex-col justify-between">
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">投资期限</label>
            <div class="flex items-center gap-2">
              <input
                v-model.number="years"
                class="block w-full rounded-lg border-gray-200 dark:border-gray-700 bg-gray-50 dark:bg-gray-800/50 text-slate-900 dark:text-white font-bold text-center py-2 focus:border-primary focus:ring-primary text-lg"
                type="number"
                min="1"
                max="30"
              />
              <div class="flex flex-col gap-0.5">
                <span class="text-xs font-bold text-primary">年</span>
                <span class="text-[10px] text-gray-400">月</span>
              </div>
            </div>
          </div>

          <div class="bg-surface-light dark:bg-surface-dark p-4 rounded-2xl border border-gray-100 dark:border-gray-800 shadow-sm flex flex-col justify-between">
            <label class="block text-sm font-medium text-gray-700 dark:text-gray-300 mb-2">复利计算</label>
            <div class="flex items-center justify-between mt-1">
              <span class="text-xs text-gray-500 dark:text-gray-400">利滚利</span>
              <label class="flex items-center cursor-pointer relative" for="compound-toggle">
                <input
                  v-model="isCompound"
                  class="sr-only peer"
                  id="compound-toggle"
                  type="checkbox"
                />
                <div class="w-11 h-6 bg-gray-200 peer-focus:outline-none peer-focus:ring-4 peer-focus:ring-blue-300 dark:peer-focus:ring-blue-800 rounded-full peer dark:bg-gray-700 peer-checked:after:translate-x-full peer-checked:after:border-white after:content-[''] after:absolute after:top-[2px] after:left-[2px] after:bg-white after:border-gray-300 after:border after:rounded-full after:h-5 after:w-5 after:transition-all dark:border-gray-600 peer-checked:bg-primary"></div>
              </label>
            </div>
          </div>
        </div>

        <p class="text-[10px] text-gray-400 text-center pt-2">
          * 演示数据仅供参考，不代表实际投资承诺。投资有风险，理财需谨慎。
        </p>
      </div>
    </main>

    <!-- 底部操作按钮 -->
    <div class="fixed bottom-0 w-full bg-surface-light dark:bg-surface-dark border-t border-gray-200 dark:border-gray-800 p-4 pb-safe z-50">
      <div class="flex gap-4">
        <button @click="saveDemo" class="flex-1 py-3.5 px-4 rounded-xl border border-gray-200 dark:border-gray-700 text-slate-700 dark:text-gray-200 font-semibold bg-white dark:bg-surface-dark hover:bg-gray-50 dark:hover:bg-gray-800 transition-colors flex items-center justify-center gap-2">
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7H5a2 2 0 00-2 2v9a2 2 0 002 2h14a2 2 0 002-2V9a2 2 0 00-2-2h-3m-1 4l-3 3m0 0l-3-3m3 3V4" />
          </svg>
          保存演示
        </button>
        <button @click="shareResult" class="flex-1 py-3.5 px-4 rounded-xl bg-primary hover:bg-primary-dark text-white font-semibold shadow-lg shadow-primary/30 transition-all active:scale-95 flex items-center justify-center gap-2">
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8.684 13.342C8.886 12.938 9 12.482 9 12c0-.482-.114-.938-.316-1.342m0 2.684a3 3 0 110-2.684m0 2.684l6.632 3.316m-6.632-6l6.632-3.316m0 0a3 3 0 105.367-2.684 3 3 0 00-5.367 2.684zm0 9.316a3 3 0 105.368 2.684 3 3 0 00-5.368-2.684z" />
          </svg>
          分享结果
        </button>
      </div>
      <div class="h-[env(safe-area-inset-bottom)] w-full"></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, watch } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

// 参数
const principal = ref(1000000) // 本金
const principalInput = ref('1,000,000') // 输入框显示值
const rate = ref(3.5) // 年化收益率
const years = ref(5) // 投资年限
const isCompound = ref(true) // 是否复利

// 计算总金额
const totalAmount = computed(() => {
  if (isCompound.value) {
    // 复利计算：本金 × (1 + 年化收益率)^年限
    return principal.value * Math.pow(1 + rate.value / 100, years.value)
  } else {
    // 单利计算：本金 × (1 + 年化收益率 × 年限)
    return principal.value * (1 + (rate.value / 100) * years.value)
  }
})

// 计算累计收益
const totalProfit = computed(() => {
  return totalAmount.value - principal.value
})

// 计算收益率（相对于本金的百分比）
const profitRate = computed(() => {
  return ((totalProfit.value / principal.value) * 100).toFixed(1)
})

// 计算图表路径
const chartLinePath = computed(() => {
  const startY = 80
  const midY = startY - (totalProfit.value / principal.value) * 30
  const endY = startY - (totalProfit.value / principal.value) * 60
  return `M0,${startY} Q25,${midY - 5} 50,${midY} T100,${endY}`
})

const chartAreaPath = computed(() => {
  return `${chartLinePath.value} L100,100 L0,100 Z`
})

const chartMidPoint = computed(() => {
  const startY = 80
  const midY = startY - (totalProfit.value / principal.value) * 30
  return { x: 50, y: midY }
})

const chartEndPoint = computed(() => {
  const startY = 80
  const endY = startY - (totalProfit.value / principal.value) * 60
  return { x: 100, y: endY }
})

// 格式化数字（添加千分位）
const formatNumber = (num: number): string => {
  return Math.floor(num).toLocaleString('zh-CN')
}

// 获取小数部分
const formatDecimal = (num: number): string => {
  const decimal = (num - Math.floor(num)).toFixed(2).substring(2)
  return decimal
}

// 更新本金
const updatePrincipal = (event: Event) => {
  const input = (event.target as HTMLInputElement).value
  // 移除非数字字符
  const numStr = input.replace(/[^\d]/g, '')
  const num = parseInt(numStr) || 0
  principal.value = num
  // 更新输入框显示
  principalInput.value = formatNumber(num)
}

// 增加本金
const addPrincipal = (amount: number) => {
  principal.value += amount
  principalInput.value = formatNumber(principal.value)
}

// 保存演示
const saveDemo = () => {
  const data = {
    principal: principal.value,
    rate: rate.value,
    years: years.value,
    isCompound: isCompound.value,
    totalAmount: totalAmount.value,
    totalProfit: totalProfit.value,
  }
  localStorage.setItem('profit-calculator-demo', JSON.stringify(data))
  alert('演示已保存到本地')
}

// 分享结果
const shareResult = async () => {
  const text = `收益演示结果：
初始投资：¥${formatNumber(principal.value)}
年化收益率：${rate.value}%
投资期限：${years.value}年
计算方式：${isCompound.value ? '复利' : '单利'}
预估总金额：¥${formatNumber(Math.floor(totalAmount.value))}
累计收益：¥${formatNumber(totalProfit.value)}
收益率：+${profitRate.value}%`

  try {
    await navigator.clipboard.writeText(text)
    alert('已复制到剪贴板，可以分享给客户了！')
  } catch (error) {
    console.error('复制失败:', error)
    alert('复制失败，请手动复制')
  }
}

// 监听本金变化，自动更新输入框显示
watch(principal, (newVal) => {
  if (document.activeElement?.tagName !== 'INPUT') {
    principalInput.value = formatNumber(newVal)
  }
})
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

/* Range input styling */
input[type='range'] {
  -webkit-appearance: none;
  background: transparent;
}

input[type='range']::-webkit-slider-thumb {
  -webkit-appearance: none;
  height: 20px;
  width: 20px;
  border-radius: 50%;
  background: #ffffff;
  border: 2px solid #137fec;
  cursor: pointer;
  margin-top: -8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

input[type='range']::-webkit-slider-runnable-track {
  width: 100%;
  height: 4px;
  cursor: pointer;
  background: #e2e8f0;
  border-radius: 2px;
}

.dark input[type='range']::-webkit-slider-runnable-track {
  background: #334155;
}
</style>
