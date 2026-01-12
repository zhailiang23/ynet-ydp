<template>
  <div class="min-h-screen bg-background-dark text-white flex flex-col pb-24">
    <!-- Top App Bar -->
    <div class="flex items-center bg-[#111a22] p-4 pb-2 justify-between sticky top-0 z-50 border-b border-gray-800">
      <div class="text-white flex size-12 shrink-0 items-center justify-start cursor-pointer" @click="goBack">
        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
        </svg>
      </div>
      <h2 class="text-white text-lg font-bold leading-tight flex-1 text-center">智能任务助手</h2>
      <div class="flex w-12 items-center justify-end">
        <button class="flex cursor-pointer items-center justify-center rounded-lg h-12 bg-transparent text-white">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
          </svg>
        </button>
      </div>
    </div>

    <!-- 加载状态 -->
    <div v-if="loading" class="flex-1 flex items-center justify-center">
      <div class="text-gray-400">加载中...</div>
    </div>

    <!-- 错误状态 -->
    <div v-else-if="error" class="flex-1 flex items-center justify-center">
      <div class="text-red-400">{{ error }}</div>
    </div>

    <!-- 内容区域 -->
    <div v-else>
      <!-- Profile Header -->
      <div class="relative flex w-full flex-col bg-[#111a22] pt-2">
        <div class="flex px-4 py-2">
          <div class="flex w-full flex-col gap-4 items-start">
            <div class="flex gap-4 flex-row items-center w-full">
              <!-- 客户头像 -->
              <div
                class="bg-center bg-no-repeat bg-cover rounded-full h-20 w-20 shrink-0 border-2 border-primary/30"
                :style="{ backgroundImage: `url(https://ui-avatars.com/api/?name=${customer?.customerName}&background=137fec&color=fff&size=80)` }"
              ></div>

              <!-- 客户信息 -->
              <div class="flex flex-col justify-center flex-1">
                <div class="flex items-center gap-2 mb-1">
                  <h1 class="text-white text-xl font-bold leading-tight">{{ customer?.customerName }}</h1>
                  <span class="bg-yellow-500/20 text-yellow-400 text-xs px-2 py-0.5 rounded-full border border-yellow-500/30">
                    {{ getLevelLabel(customer?.customerLevel) }}
                  </span>
                </div>
                <p class="text-[#92adc9] text-sm font-normal leading-normal">
                  AUM: ¥{{ formatMoney(mockData.aum) }} | 风险等级: {{ getRiskLevelLabel(mockData.riskLevel) }}
                </p>
                <div v-if="customer?.isInStore" class="flex items-center gap-1 mt-1 text-primary text-xs font-medium">
                  <svg class="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
                  </svg>
                  <span>刚刚到店</span>
                </div>
              </div>
            </div>
            <h2 class="text-white text-xl font-bold leading-tight mt-2">
              {{ customer?.customerName }} 到店智能任务提醒
            </h2>
          </div>
        </div>
        <div class="h-4 bg-[#111a22]"></div>
      </div>

      <!-- Section 1: 客户在场任务 (紧急) -->
      <div v-if="urgentTasks.length > 0" class="flex flex-col w-full bg-[#111a22]">
        <div class="px-4 pb-2 pt-2 flex items-center gap-2">
          <svg class="w-5 h-5 text-red-500 animate-pulse" fill="currentColor" viewBox="0 0 24 24">
            <path d="M12 2C6.48 2 2 6.48 2 12s4.48 10 10 10 10-4.48 10-10S17.52 2 12 2zm1 15h-2v-2h2v2zm0-4h-2V7h2v6z"/>
          </svg>
          <h3 class="text-white text-lg font-bold leading-tight">客户在场，请立即处理！</h3>
        </div>

        <!-- 紧急任务卡片 -->
        <div v-for="task in urgentTasks" :key="task.id" class="p-4 pt-2">
          <div class="flex flex-col rounded-xl bg-gradient-to-br from-[#1e293b] to-[#0f172a] border border-red-500/30 shadow-lg relative overflow-hidden group">
            <!-- 紧急标识条 -->
            <div class="absolute left-0 top-0 bottom-0 w-1.5 bg-red-500"></div>

            <div class="p-5 flex flex-col gap-3">
              <div class="flex justify-between items-start">
                <div class="flex flex-col gap-1">
                  <div class="flex items-center gap-2">
                    <span class="bg-red-500/20 text-red-400 text-[10px] font-bold px-2 py-0.5 rounded uppercase tracking-wider border border-red-500/20">
                      临柜专属
                    </span>
                    <span class="bg-primary/20 text-primary text-[10px] font-bold px-2 py-0.5 rounded uppercase tracking-wider border border-primary/20">
                      AI 洞察
                    </span>
                  </div>
                  <h4 class="text-white text-xl font-bold mt-1">{{ task.taskTitle }}</h4>
                </div>
                <svg class="w-6 h-6 text-red-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 9v2m0 4h.01m-6.938 4h13.856c1.54 0 2.502-1.667 1.732-3L13.732 4c-.77-1.333-2.694-1.333-3.464 0L3.34 16c-.77 1.333.192 3 1.732 3z" />
                </svg>
              </div>

              <p class="text-[#92adc9] text-sm leading-relaxed">
                {{ task.taskDescription }}
                <br/>
                <span class="text-xs opacity-70">触发原因: {{ task.triggerSource || 'AI 自动生成' }}</span>
              </p>

              <!-- 操作按钮 -->
              <div class="flex gap-3 mt-2 flex-wrap">
                <button
                  @click="goToTaskDetail(task.id)"
                  class="flex-1 min-w-[100px] cursor-pointer items-center justify-center rounded-lg h-10 px-4 bg-primary hover:bg-primary/90 text-white text-sm font-bold shadow-md transition-colors"
                >
                  <div class="flex items-center gap-2">
                    <svg class="w-4.5 h-4.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 5H6a2 2 0 00-2 2v11a2 2 0 002 2h11a2 2 0 002-2v-5m-1.414-9.414a2 2 0 112.828 2.828L11.828 15H9v-2.828l8.586-8.586z" />
                    </svg>
                    <span>去办理</span>
                  </div>
                </button>
                <button class="flex-1 min-w-[80px] cursor-pointer items-center justify-center rounded-lg h-10 px-4 bg-surface-highlight hover:bg-[#2f4055] text-white text-sm font-medium border border-gray-700 transition-colors">
                  <span>已完成</span>
                </button>
                <button class="flex-none w-[40px] cursor-pointer items-center justify-center rounded-lg h-10 bg-transparent text-[#92adc9] hover:bg-white/5 transition-colors" title="忽略">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                  </svg>
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>

      <div class="h-2 bg-[#0b1219]"></div>

      <!-- Section 2: 可远程跟进任务 -->
      <div v-if="remoteTasks.length > 0" class="flex flex-col w-full bg-[#111a22] flex-1">
        <div class="px-4 pb-2 pt-4 flex items-center gap-2">
          <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 18h.01M8 21h8a2 2 0 002-2V5a2 2 0 00-2-2H8a2 2 0 00-2 2v14a2 2 0 002 2z" />
          </svg>
          <h3 class="text-white text-lg font-bold leading-tight">可远程跟进任务</h3>
        </div>

        <div class="flex flex-col gap-3 px-4 pb-4">
          <!-- 远程任务卡片 -->
          <div v-for="task in remoteTasks" :key="task.id" class="bg-surface-dark rounded-xl p-4 border border-gray-800 flex flex-col gap-3">
            <div class="flex justify-between items-start">
              <div>
                <h4 class="text-white text-base font-bold">{{ task.taskTitle }}</h4>
                <div class="flex items-center gap-2 mt-1">
                  <span class="text-xs text-[#92adc9]">优先级: </span>
                  <span
                    class="text-xs font-medium"
                    :class="{
                      'text-red-400': task.priority === '紧急',
                      'text-green-400': task.priority === '高',
                      'text-yellow-400': task.priority === '中',
                      'text-gray-400': task.priority === '低'
                    }"
                  >
                    {{ task.priority }}
                  </span>
                  <span class="text-[#92adc9] text-[10px]">•</span>
                  <span class="text-xs text-[#92adc9]">来源: {{ task.triggerSource || 'AI 生成' }}</span>
                </div>
              </div>
              <div class="bg-primary/10 p-1.5 rounded-lg">
                <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
                </svg>
              </div>
            </div>

            <div class="h-px bg-gray-700/50 w-full"></div>

            <div class="flex gap-3 justify-end">
              <button class="text-[#92adc9] text-sm font-medium hover:text-white flex items-center gap-1">
                <svg class="w-4.5 h-4.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6v6m0 0v6m0-6h6m-6 0H6" />
                </svg>
                添加到待办
              </button>
              <div class="w-px bg-gray-700 h-4 self-center"></div>
              <button
                @click="goToTaskDetail(task.id)"
                class="text-primary text-sm font-bold hover:text-primary/80 flex items-center gap-1"
              >
                <svg class="w-4.5 h-4.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7l5 5m0 0l-5 5m5-5H6" />
                </svg>
                去处理
              </button>
            </div>
          </div>
        </div>
      </div>

      <!-- 无任务提示 -->
      <div v-if="tasks.length === 0" class="flex-1 flex items-center justify-center p-8">
        <div class="text-center text-gray-400">
          <svg class="w-16 h-16 mx-auto mb-4 opacity-50" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2m-6 9l2 2 4-4" />
          </svg>
          <p class="text-lg">暂无任务</p>
        </div>
      </div>
    </div>

    <!-- Sticky Footer -->
    <div class="fixed bottom-0 left-0 w-full bg-[#111a22] border-t border-gray-800 p-4 pb-8 z-40 shadow-[0_-4px_10px_rgba(0,0,0,0.3)]">
      <div class="flex gap-3">
        <button
          @click="goBack"
          class="flex-1 cursor-pointer items-center justify-center rounded-lg h-12 px-4 bg-transparent border border-gray-600 text-white text-sm font-bold hover:bg-gray-800 transition-colors"
        >
          查看客户详情
        </button>
        <button class="flex-[1.5] cursor-pointer items-center justify-center rounded-lg h-12 px-4 bg-primary text-white text-sm font-bold shadow-lg shadow-blue-900/20 hover:bg-blue-600 transition-colors">
          完成本次到店服务
        </button>
      </div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import type { Customer } from '@/types/customer'
import type { Task } from '@/api/task'
import { getCustomer } from '@/api/customer'
import { getTasksByCustomerId } from '@/api/task'

const route = useRoute()
const router = useRouter()

const customer = ref<Customer | null>(null)
const tasks = ref<Task[]>([])
const loading = ref(true)
const error = ref('')

const customerId = computed(() => Number(route.params.id))

// 模拟数据
const mockData = computed(() => ({
  aum: customer.value?.creditScore ? Number(customer.value.creditScore) * 100 : 0,
  riskLevel: customer.value?.creditLevel === 'AAA' || customer.value?.creditLevel === 'AA' ? 'AGGRESSIVE' : customer.value?.creditLevel === 'A' ? 'BALANCED' : 'CONSERVATIVE'
}))

// 紧急任务（客户在场）
const urgentTasks = computed(() =>
  tasks.value.filter(task => task.priority === '紧急' || task.isUrgent)
)

// 远程任务
const remoteTasks = computed(() =>
  tasks.value.filter(task => task.priority !== '紧急' && !task.isUrgent)
)

// 格式化金额
function formatMoney(value: number | undefined): string {
  if (!value) return '0'
  if (value >= 10000) {
    return `${(value / 10000).toFixed(1)}万`
  }
  return value.toLocaleString()
}

// 获取客户等级标签
function getLevelLabel(level: string | undefined): string {
  const labels: Record<string, string> = {
    diamond: '钻石客户',
    vip: 'VIP客户',
    gold: '黄金客户',
    normal: '普通客户',
    strategic: '战略客户',
    key: '重点客户',
    general: '普通客户',
    important: '重要客户',
    private: '私行客户'
  }
  return labels[level || ''] || '普通客户'
}

// 获取风险偏好标签
function getRiskLevelLabel(level: string | undefined): string {
  const labels: Record<string, string> = {
    CONSERVATIVE: '保守型',
    BALANCED: '稳健型',
    AGGRESSIVE: '激进型',
    R1: 'R1',
    R2: 'R2',
    R3: 'R3',
    R4: 'R4',
    R5: 'R5'
  }
  return labels[level || ''] || '未知'
}

// 返回上一页
function goBack() {
  router.back()
}

// 跳转到任务详情
function goToTaskDetail(taskId: number) {
  router.push(`/tasks/${taskId}`)
}

// 加载客户信息和任务列表
async function loadData() {
  try {
    loading.value = true
    error.value = ''

    // 并行加载客户信息和任务列表
    const [customerData, tasksData] = await Promise.all([
      getCustomer(customerId.value),
      getTasksByCustomerId(customerId.value)
    ])

    customer.value = customerData
    tasks.value = tasksData
  } catch (err: any) {
    console.error('加载数据失败:', err)
    error.value = err.message || '加载数据失败'
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  loadData()
})
</script>

<style scoped>
/* 自定义样式 */
</style>
