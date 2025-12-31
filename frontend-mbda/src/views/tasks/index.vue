<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import TaskCard from '@/components/TaskCard.vue'
import BottomNav from '@/components/BottomNav.vue'
import {
  getTaskPage,
  getTaskStats,
  completeTask,
  type Task,
  type TaskStats,
  type TaskPageParams,
  TaskStatus,
  TaskPriority,
} from '@/api/task'

const router = useRouter()

// 状态管理
const activeTab = ref<'ai' | 'manual'>('ai')
const activeFilter = ref<'all' | 'high-priority' | 'high-value' | 'urgent'>('all')
const loading = ref(false)
const tasks = ref<Task[]>([])
const stats = ref<TaskStats>({
  totalValue: '¥500w',
  valueChange: '+12%',
  highPriorityCount: 5,
  completedCount: 12,
})

// 计算 AI 任务和手动任务
const aiTasks = computed(() => tasks.value.filter(t => t.aiGenerated === 1))
const manualTasks = computed(() => tasks.value.filter(t => t.aiGenerated === 0))

// 当前显示的任务列表
const displayTasks = computed(() => {
  let list = activeTab.value === 'ai' ? aiTasks.value : manualTasks.value

  // 应用筛选器
  if (activeFilter.value === 'high-priority') {
    list = list.filter(t => t.priority === 'P0' || t.priority === 'P1')
  } else if (activeFilter.value === 'high-value') {
    list = list.filter(t => t.comprehensiveScore >= 8)
  } else if (activeFilter.value === 'urgent') {
    list = list.filter(t => {
      if (!t.deadline) return false
      const deadline = new Date(t.deadline)
      const now = new Date()
      const diff = deadline.getTime() - now.getTime()
      const hours = Math.floor(diff / (1000 * 60 * 60))
      return hours >= 0 && hours <= 72
    })
  }

  return list
})

// AI 推荐任务（高价值任务）
const recommendedTasks = computed(() =>
  aiTasks.value
    .filter(t => t.comprehensiveScore >= 8)
    .sort((a, b) => b.comprehensiveScore - a.comprehensiveScore)
    .slice(0, 3)
)

// 常规待办
const regularTasks = computed(() =>
  (activeTab.value === 'ai' ? aiTasks.value : manualTasks.value)
    .filter(t => !recommendedTasks.value.find(r => r.id === t.id))
)

// 加载任务列表
const loadTasks = async () => {
  try {
    loading.value = true
    const params: TaskPageParams = {
      pageNo: 1,
      pageSize: 100,
      status: TaskStatus.PENDING,
    }

    const result = await getTaskPage(params)
    tasks.value = result.list || []
  } catch (error) {
    console.error('加载任务列表失败:', error)
    tasks.value = []
  } finally {
    loading.value = false
  }
}

// 加载统计信息
const loadStats = async () => {
  try {
    const result = await getTaskStats()
    stats.value = result
  } catch (error) {
    console.error('加载统计信息失败:', error)
  }
}

// 完成任务
const handleCompleteTask = async (taskId: number) => {
  try {
    await completeTask(taskId)
    // 从列表中移除
    tasks.value = tasks.value.filter(t => t.id !== taskId)
    stats.value.completedCount++
  } catch (error) {
    console.error('完成任务失败:', error)
  }
}

// 联系客户
const handleContact = (task: Task) => {
  console.log('联系客户:', task)
  // TODO: 跳转到拨打电话或发送消息页面
}

// 生成方案
const handleGeneratePlan = (task: Task) => {
  console.log('生成方案:', task)
  // TODO: 跳转到方案生成页面
}

// 点击任务卡片，跳转到详情页
const handleTaskClick = (task: Task) => {
  router.push(`/tasks/${task.id}`)
}

// 切换 Tab
const switchTab = (tab: 'ai' | 'manual') => {
  activeTab.value = tab
}

// 切换筛选器
const switchFilter = (filter: 'all' | 'high-priority' | 'high-value' | 'urgent') => {
  activeFilter.value = filter
}

// 组件挂载
onMounted(() => {
  loadTasks()
  loadStats()
})
</script>

<template>
  <div class="relative flex h-full min-h-screen w-full flex-col overflow-hidden pb-24 bg-gray-50 dark:bg-gray-900">
    <!-- Header -->
    <header class="sticky top-0 z-20 bg-gray-50/95 dark:bg-gray-900/95 backdrop-blur-sm px-4 pt-12 pb-2 shadow-sm border-b border-gray-200 dark:border-gray-800">
      <div class="flex items-center justify-between mb-4">
        <div class="flex items-center gap-3">
          <div class="h-10 w-10 overflow-hidden rounded-full border-2 border-white dark:border-gray-700 shadow-sm bg-blue-600 flex items-center justify-center text-white font-bold">
            李
          </div>
          <div>
            <h1 class="text-sm text-gray-500 dark:text-gray-400 font-medium">早上好</h1>
            <h2 class="text-lg font-bold text-gray-900 dark:text-white leading-tight">李经理</h2>
          </div>
        </div>
        <button class="relative rounded-full p-2 text-gray-600 dark:text-gray-300 hover:bg-gray-100 dark:hover:bg-gray-800 transition-colors">
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9"></path>
          </svg>
          <span class="absolute top-2 right-2 h-2.5 w-2.5 rounded-full bg-red-500 border-2 border-white dark:border-gray-900"></span>
        </button>
      </div>

      <!-- Stats Overview -->
      <div class="flex gap-3 overflow-hidden">
        <div class="flex-1 rounded-xl bg-gradient-to-br from-blue-600 to-blue-700 p-4 text-white shadow-lg shadow-blue-500/20">
          <div class="flex items-center gap-2 mb-1">
            <svg class="w-5 h-5 text-white/80" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6"></path>
            </svg>
            <span class="text-xs font-medium text-white/90">今日潜在价值</span>
          </div>
          <div class="flex items-baseline gap-1">
            <span class="text-2xl font-bold tracking-tight">{{ stats.totalValue }}</span>
            <span class="text-xs font-medium bg-white/20 px-1.5 py-0.5 rounded text-white">{{ stats.valueChange }}</span>
          </div>
        </div>
        <div class="flex-1 rounded-xl bg-white dark:bg-gray-800 p-4 border border-gray-100 dark:border-gray-700 shadow-sm">
          <div class="flex items-center gap-2 mb-1">
            <svg class="w-5 h-5 text-orange-500" fill="currentColor" viewBox="0 0 20 20">
              <path fill-rule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clip-rule="evenodd"></path>
            </svg>
            <span class="text-xs font-medium text-gray-500 dark:text-gray-400">高优待办</span>
          </div>
          <div class="flex items-baseline gap-1">
            <span class="text-2xl font-bold text-gray-900 dark:text-white tracking-tight">{{ stats.highPriorityCount }}</span>
            <span class="text-xs text-gray-400">个任务</span>
          </div>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <main class="flex-1 px-4 pt-4">
      <!-- Segmented Control -->
      <div class="mb-5 rounded-lg bg-gray-200 dark:bg-gray-800 p-1 flex">
        <button
          :class="[
            'flex-1 rounded-md py-2 text-sm font-semibold transition-all',
            activeTab === 'ai'
              ? 'bg-white dark:bg-gray-700 shadow-sm text-blue-600'
              : 'text-gray-500 dark:text-gray-400 hover:text-gray-900 dark:hover:text-gray-200'
          ]"
          @click="switchTab('ai')"
        >
          AI 智能建议
        </button>
        <button
          :class="[
            'flex-1 rounded-md py-2 text-sm font-semibold transition-all',
            activeTab === 'manual'
              ? 'bg-white dark:bg-gray-700 shadow-sm text-blue-600'
              : 'text-gray-500 dark:text-gray-400 hover:text-gray-900 dark:hover:text-gray-200'
          ]"
          @click="switchTab('manual')"
        >
          手动待办事项
        </button>
      </div>

      <!-- Filter Chips -->
      <div class="flex gap-2 mb-6 overflow-x-auto no-scrollbar pb-1">
        <button
          :class="[
            'flex shrink-0 items-center gap-1.5 rounded-full px-3 py-1.5 text-xs font-medium shadow-sm transition-colors',
            activeFilter === 'all'
              ? 'bg-blue-600 text-white shadow-blue-500/20'
              : 'bg-white dark:bg-gray-800 border border-gray-200 dark:border-gray-700 text-gray-600 dark:text-gray-300'
          ]"
          @click="switchFilter('all')"
        >
          全部
        </button>
        <button
          :class="[
            'flex shrink-0 items-center gap-1.5 rounded-full px-3 py-1.5 text-xs font-medium transition-colors',
            activeFilter === 'high-priority'
              ? 'bg-blue-600 text-white'
              : 'bg-white dark:bg-gray-800 border border-gray-200 dark:border-gray-700 text-gray-600 dark:text-gray-300'
          ]"
          @click="switchFilter('high-priority')"
        >
          <span class="h-1.5 w-1.5 rounded-full bg-red-500"></span>
          高优先级
        </button>
        <button
          :class="[
            'flex shrink-0 items-center gap-1.5 rounded-full px-3 py-1.5 text-xs font-medium transition-colors',
            activeFilter === 'high-value'
              ? 'bg-blue-600 text-white'
              : 'bg-white dark:bg-gray-800 border border-gray-200 dark:border-gray-700 text-gray-600 dark:text-gray-300'
          ]"
          @click="switchFilter('high-value')"
        >
          <svg class="w-3.5 h-3.5 text-green-500" fill="currentColor" viewBox="0 0 20 20">
            <path d="M8.433 7.418c.155-.103.346-.196.567-.267v1.698a2.305 2.305 0 01-.567-.267C8.07 8.34 8 8.114 8 8c0-.114.07-.34.433-.582zM11 12.849v-1.698c.22.071.412.164.567.267.364.243.433.468.433.582 0 .114-.07.34-.433.582a2.305 2.305 0 01-.567.267z"></path>
            <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-13a1 1 0 10-2 0v.092a4.535 4.535 0 00-1.676.662C6.602 6.234 6 7.009 6 8c0 .99.602 1.765 1.324 2.246.48.32 1.054.545 1.676.662v1.941c-.391-.127-.68-.317-.843-.504a1 1 0 10-1.51 1.31c.562.649 1.413 1.076 2.353 1.253V15a1 1 0 102 0v-.092a4.535 4.535 0 001.676-.662C13.398 13.766 14 12.991 14 12c0-.99-.602-1.765-1.324-2.246A4.535 4.535 0 0011 9.092V7.151c.391.127.68.317.843.504a1 1 0 101.511-1.31c-.563-.649-1.413-1.076-2.354-1.253V5z" clip-rule="evenodd"></path>
          </svg>
          高业务价值
        </button>
        <button
          :class="[
            'flex shrink-0 items-center gap-1.5 rounded-full px-3 py-1.5 text-xs font-medium transition-colors',
            activeFilter === 'urgent'
              ? 'bg-blue-600 text-white'
              : 'bg-white dark:bg-gray-800 border border-gray-200 dark:border-gray-700 text-gray-600 dark:text-gray-300'
          ]"
          @click="switchFilter('urgent')"
        >
          即将逾期
        </button>
      </div>

      <!-- Task List -->
      <div v-if="loading" class="flex justify-center py-12">
        <div class="animate-spin rounded-full h-8 w-8 border-b-2 border-blue-600"></div>
      </div>

      <div v-else class="flex flex-col gap-4">
        <!-- AI 推荐任务 -->
        <template v-if="activeTab === 'ai' && recommendedTasks.length > 0">
          <div class="flex items-center justify-between px-1">
            <h3 class="text-sm font-bold text-gray-400 uppercase tracking-wider">今日推荐 ({{ recommendedTasks.length }})</h3>
            <span class="text-xs text-blue-600 font-medium cursor-pointer">查看全部</span>
          </div>

          <TaskCard
            v-for="task in recommendedTasks"
            :key="`recommended-${task.id}`"
            :task="task"
            :is-a-i="true"
            @contact="handleContact"
            @generate-plan="handleGeneratePlan"
            @click="handleTaskClick"
          />

          <!-- Section Divider -->
          <div class="flex items-center justify-between px-1 mt-2">
            <h3 class="text-sm font-bold text-gray-400 uppercase tracking-wider">常规待办</h3>
          </div>
        </template>

        <!-- 常规任务列表 -->
        <TaskCard
          v-for="task in regularTasks"
          :key="`regular-${task.id}`"
          :task="task"
          :is-a-i="task.aiGenerated === 1"
          @complete="handleCompleteTask"
          @contact="handleContact"
          @generate-plan="handleGeneratePlan"
          @click="handleTaskClick"
        />

        <!-- 空状态 -->
        <div v-if="displayTasks.length === 0" class="text-center py-12">
          <svg class="mx-auto h-12 w-12 text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"></path>
          </svg>
          <p class="mt-2 text-sm text-gray-500">暂无任务</p>
        </div>

        <!-- 已完成统计 -->
        <div class="text-center py-6">
          <p class="text-xs text-gray-400 flex items-center justify-center gap-1">
            <svg class="w-3.5 h-3.5" fill="currentColor" viewBox="0 0 20 20">
              <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
            </svg>
            已完成 {{ stats.completedCount }} 项任务
          </p>
        </div>
      </div>
    </main>

    <!-- Floating Action Button -->
    <button class="fixed bottom-24 right-4 z-30 flex h-14 w-14 items-center justify-center rounded-full bg-blue-600 text-white shadow-xl shadow-blue-500/40 hover:bg-blue-700 transition-transform hover:scale-105 active:scale-95">
      <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24">
        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4"></path>
      </svg>
    </button>

    <!-- Bottom Navigation -->
    <BottomNav />
  </div>
</template>

<style scoped>
/* Hide scrollbar */
.no-scrollbar::-webkit-scrollbar {
  display: none;
}
.no-scrollbar {
  -ms-overflow-style: none;
  scrollbar-width: none;
}
</style>
