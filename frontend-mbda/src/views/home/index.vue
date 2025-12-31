<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import BottomNav from '@/components/BottomNav.vue'
import { getTaskPage, type Task, type TaskPageParams } from '@/api/task'

const router = useRouter()

// 今日重点待办
const importantTasks = ref<Task[]>([])
const tasksLoading = ref(false)

// 加载重点待办（取前3个高优先级任务）
const loadImportantTasks = async () => {
  try {
    tasksLoading.value = true
    const params: TaskPageParams = {
      pageNo: 1,
      pageSize: 3,
      status: 0, // 待办
      priority: 'P0', // 只取P0紧急任务
    }

    const result = await getTaskPage(params)
    importantTasks.value = result.list || []
  } catch (error) {
    console.error('加载重点待办失败:', error)
    importantTasks.value = []
  } finally {
    tasksLoading.value = false
  }
}

// 获取优先级标签样式
const getPriorityBadge = (priority: string) => {
  const configs: Record<string, { label: string; class: string; borderClass: string }> = {
    P0: {
      label: '紧急',
      class: 'bg-red-50 text-red-600 dark:bg-red-500/20 dark:text-red-400',
      borderClass: 'border-red-500'
    },
    P1: {
      label: '重要',
      class: 'bg-blue-50 text-primary dark:bg-blue-500/20 dark:text-blue-400',
      borderClass: 'border-primary'
    },
    P2: {
      label: '普通',
      class: 'bg-gray-50 text-gray-600 dark:bg-gray-500/20 dark:text-gray-400',
      borderClass: 'border-gray-300 dark:border-gray-600'
    },
  }
  return configs[priority] || configs.P2
}

// 跳转到全部待办
const goToAllTasks = () => {
  router.push('/tasks')
}

// 跳转到任务详情
const goToTaskDetail = (taskId: number) => {
  router.push(`/tasks/${taskId}`)
}

// 联系客户
const handleContact = (task: Task, type: 'call' | 'chat', event: Event) => {
  // 阻止事件冒泡，避免触发卡片点击
  event.stopPropagation()
  console.log(`联系客户 (${type}):`, task)
  // TODO: 实现拨打电话或发送消息
}

// 组件挂载
onMounted(() => {
  loadImportantTasks()
})
</script>

<template>
  <div class="relative flex h-full min-h-screen w-full flex-col overflow-x-hidden pb-24 bg-background-dark">
    <!-- Header -->
    <header class="sticky top-0 z-20 bg-background-dark/95 backdrop-blur-md border-b border-white/5">
      <div class="flex items-center justify-between p-4 pb-2">
        <div class="flex items-center gap-3">
          <div class="bg-gradient-to-br from-primary to-blue-600 rounded-full size-10 flex items-center justify-center text-white font-bold text-lg border-2 border-primary/20">
            李
          </div>
          <div class="flex flex-col">
            <span class="text-xs text-text-secondary font-medium">早上好，</span>
            <h2 class="text-white text-base font-bold leading-tight">李经理</h2>
          </div>
        </div>
        <div class="flex items-center gap-1">
          <button class="flex items-center justify-center rounded-full size-10 text-text-secondary hover:bg-surface-dark-highlight transition-colors">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13.875 18.825A10.05 10.05 0 0112 19c-4.478 0-8.268-2.943-9.543-7a9.97 9.97 0 011.563-3.029m5.858.908a3 3 0 114.243 4.243M9.878 9.878l4.242 4.242M9.88 9.88l-3.29-3.29m7.532 7.532l3.29 3.29M3 3l3.59 3.59m0 0A9.953 9.953 0 0112 5c4.478 0 8.268 2.943 9.543 7a10.025 10.025 0 01-4.132 5.411m0 0L21 21" />
            </svg>
          </button>
          <button class="relative flex items-center justify-center rounded-full size-10 text-text-secondary hover:bg-surface-dark-highlight transition-colors">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
            </svg>
            <span class="absolute top-2.5 right-2.5 size-2 bg-red-500 rounded-full border border-background-dark"></span>
          </button>
        </div>
      </div>

      <!-- AI Search Bar -->
      <div class="px-4 pb-4">
        <label class="flex flex-col h-12 w-full group">
          <div class="flex w-full flex-1 items-stretch rounded-2xl h-full overflow-hidden shadow-sm ring-1 ring-white/10 group-focus-within:ring-primary transition-all">
            <div class="text-primary flex border-none bg-surface-dark-highlight items-center justify-center pl-4">
              <svg class="w-6 h-6" fill="currentColor" viewBox="0 0 20 20">
                <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
              </svg>
            </div>
            <input
              class="flex w-full min-w-0 flex-1 resize-none overflow-hidden text-white focus:outline-0 focus:ring-0 border-none bg-surface-dark-highlight focus:border-none h-full placeholder:text-text-secondary px-3 pl-2 text-sm font-normal leading-normal"
              placeholder="向 AI 咨询客户或产品情况..."
            />
            <div class="text-text-secondary flex border-none bg-surface-dark-highlight items-center justify-center pr-4 cursor-pointer hover:text-primary transition-colors">
              <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 11a7 7 0 01-7 7m0 0a7 7 0 01-7-7m7 7v4m0 0H8m4 0h4m-4-8a3 3 0 01-3-3V5a3 3 0 116 0v6a3 3 0 01-3 3z" />
              </svg>
            </div>
          </div>
        </label>
      </div>
    </header>

    <main class="flex flex-col gap-6 px-4 pt-2">
      <!-- AI 每日洞察 -->
      <section>
        <div class="relative overflow-hidden rounded-2xl bg-gradient-to-br from-primary to-[#0a4d91] shadow-xl shadow-primary/20 text-white">
          <div class="absolute top-0 right-0 -mr-12 -mt-12 size-48 rounded-full bg-white/10 blur-3xl"></div>
          <div class="absolute bottom-0 left-0 -ml-12 -mb-12 size-40 rounded-full bg-purple-500/30 blur-3xl"></div>
          <div class="relative p-5 z-10">
            <div class="flex items-center justify-between mb-3">
              <div class="flex items-center gap-2 px-3 py-1 rounded-full bg-white/10 backdrop-blur-md border border-white/10 w-fit">
                <span class="relative flex items-center justify-center text-yellow-300">
                  <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                  </svg>
                </span>
                <p class="text-xs font-bold tracking-wide uppercase">AI 每日洞察</p>
              </div>
              <span class="text-[10px] text-white/70 font-medium">更新于 08:30</span>
            </div>
            <div class="flex gap-4">
              <div class="flex-1 flex flex-col justify-center">
                <h3 class="text-lg font-bold leading-snug mb-2">
                  识别出 <span class="text-yellow-300 text-xl">5 个</span> 高潜力营销线索
                </h3>
                <p class="text-blue-100 text-xs leading-relaxed mb-3">
                  基于昨日市场波动分析，建议重点关注持有科技类基金的客户。另有 3 位客户大额存单今日到期。
                </p>
                <button
                  class="flex items-center gap-1.5 text-xs font-semibold bg-white text-primary px-4 py-2 rounded-lg w-fit hover:bg-blue-50 transition-colors shadow-sm"
                  @click="router.push('/insights/detail')"
                >
                  查看深度分析
                  <svg class="w-3.5 h-3.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                  </svg>
                </button>
              </div>
              <div class="hidden xs:flex items-center justify-center shrink-0 w-20 h-20 bg-white/10 rounded-xl border border-white/10 backdrop-blur-sm">
                <svg class="w-10 h-10 text-white/80" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
                </svg>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- 到店客户服务 -->
      <section class="flex flex-col gap-3">
        <button class="relative w-full overflow-hidden rounded-2xl bg-surface-dark-highlight text-white shadow-md p-4 flex items-center justify-between group active:scale-[0.99] transition-transform border border-white/5">
          <div class="absolute left-0 top-0 bottom-0 w-1.5 bg-primary rounded-l-2xl"></div>
          <div class="relative flex items-center gap-3.5 pl-2">
            <div class="flex items-center justify-center size-12 rounded-xl bg-primary/20 text-blue-400">
              <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 21V5a2 2 0 00-2-2H7a2 2 0 00-2 2v16m14 0h2m-2 0h-5m-9 0H3m2 0h5M9 7h1m-1 4h1m4-4h1m-1 4h1m-5 10v-5a1 1 0 011-1h2a1 1 0 011 1v5m-4 0h4" />
              </svg>
            </div>
            <div class="flex flex-col items-start gap-1">
              <h3 class="text-base font-bold tracking-tight">到店客户服务</h3>
              <p class="text-text-secondary text-xs font-medium">客户识别 · 快速录入 · 业务办理</p>
            </div>
          </div>
          <div class="relative flex items-center justify-center size-8 rounded-full bg-white/10 text-gray-300 group-hover:bg-primary group-hover:text-white transition-colors">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
            </svg>
          </div>
        </button>

        <!-- 快捷功能 -->
        <div class="grid grid-cols-4 gap-3">
          <div class="flex flex-col items-center gap-2 group cursor-pointer active:scale-95 transition-transform">
            <div class="flex items-center justify-center size-14 rounded-2xl bg-surface-dark-highlight text-primary shadow-sm border border-white/5 group-hover:border-primary/50 transition-colors">
              <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7h12m0 0l-4-4m4 4l-4 4m0 6H4m0 0l4 4m-4-4l4-4" />
              </svg>
            </div>
            <span class="text-xs font-medium text-gray-300 text-center">产品对比</span>
          </div>
          <div class="flex flex-col items-center gap-2 group cursor-pointer active:scale-95 transition-transform">
            <div class="flex items-center justify-center size-14 rounded-2xl bg-surface-dark-highlight text-purple-500 shadow-sm border border-white/5 group-hover:border-purple-500/50 transition-colors">
              <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
              </svg>
            </div>
            <span class="text-xs font-medium text-gray-300 text-center">方案生成</span>
          </div>
          <div class="flex flex-col items-center gap-2 group cursor-pointer active:scale-95 transition-transform">
            <div class="flex items-center justify-center size-14 rounded-2xl bg-surface-dark-highlight text-orange-500 shadow-sm border border-white/5 group-hover:border-orange-500/50 transition-colors">
              <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 20H5a2 2 0 01-2-2V6a2 2 0 012-2h10a2 2 0 012 2v1m2 13a2 2 0 01-2-2V7m2 13a2 2 0 002-2V9a2 2 0 00-2-2h-2m-4-3H9M7 16h6M7 8h6v4H7V8z" />
              </svg>
            </div>
            <span class="text-xs font-medium text-gray-300 text-center">市场资讯</span>
          </div>
          <div class="flex flex-col items-center gap-2 group cursor-pointer active:scale-95 transition-transform">
            <div class="flex items-center justify-center size-14 rounded-2xl bg-surface-dark-highlight text-gray-400 shadow-sm border border-white/5 group-hover:border-gray-400 transition-colors">
              <svg class="w-7 h-7" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v1m6 11h2m-6 0h-2v4m0-11v3m0 0h.01M12 12h4.01M16 20h4M4 12h4m12 0h.01M5 8h2a1 1 0 001-1V5a1 1 0 00-1-1H5a1 1 0 00-1 1v2a1 1 0 001 1zm12 0h2a1 1 0 001-1V5a1 1 0 00-1-1h-2a1 1 0 00-1 1v2a1 1 0 001 1zM5 20h2a1 1 0 001-1v-2a1 1 0 00-1-1H5a1 1 0 00-1 1v2a1 1 0 001 1z" />
              </svg>
            </div>
            <span class="text-xs font-medium text-gray-300 text-center">扫一扫</span>
          </div>
        </div>
      </section>

      <!-- 业绩统计 -->
      <section>
        <div class="flex gap-3 overflow-x-auto no-scrollbar pb-1 -mx-4 px-4">
          <div class="flex min-w-[150px] flex-1 flex-col gap-1 rounded-2xl p-4 bg-surface-dark-highlight border border-white/5 shadow-sm">
            <div class="flex justify-between items-start">
              <p class="text-text-secondary text-xs font-medium">管理资产 (AUM)</p>
              <svg class="w-[18px] h-[18px] text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 3.055A9.001 9.001 0 1020.945 13H11V3.055z" />
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20.488 9H15V3.512A9.025 9.025 0 0120.488 9z" />
              </svg>
            </div>
            <p class="text-white tracking-tight text-xl font-bold mt-2">¥1.2亿</p>
            <p class="text-emerald-500 text-xs font-bold flex items-center gap-0.5 mt-1">
              <svg class="w-3 h-3" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M12 7a1 1 0 110-2h5a1 1 0 011 1v5a1 1 0 11-2 0V8.414l-4.293 4.293a1 1 0 01-1.414 0L8 10.414l-4.293 4.293a1 1 0 01-1.414-1.414l5-5a1 1 0 011.414 0L11 10.586 14.586 7H12z" clip-rule="evenodd" />
              </svg>
              +2.5%
            </p>
          </div>
          <div class="flex min-w-[150px] flex-1 flex-col gap-1 rounded-2xl p-4 bg-surface-dark-highlight border border-white/5 shadow-sm">
            <div class="flex justify-between items-start">
              <p class="text-text-secondary text-xs font-medium">资金净流入</p>
              <svg class="w-[18px] h-[18px] text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M17 9V7a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2m2 4h10a2 2 0 002-2v-6a2 2 0 00-2-2H9a2 2 0 00-2 2v6a2 2 0 002 2zm7-5a2 2 0 11-4 0 2 2 0 014 0z" />
              </svg>
            </div>
            <p class="text-white tracking-tight text-xl font-bold mt-2">+¥500万</p>
            <p class="text-emerald-500 text-xs font-bold flex items-center gap-0.5 mt-1">
              <svg class="w-3 h-3" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M12 7a1 1 0 110-2h5a1 1 0 011 1v5a1 1 0 11-2 0V8.414l-4.293 4.293a1 1 0 01-1.414 0L8 10.414l-4.293 4.293a1 1 0 01-1.414-1.414l5-5a1 1 0 011.414 0L11 10.586 14.586 7H12z" clip-rule="evenodd" />
              </svg>
              +1.2%
            </p>
          </div>
          <div class="flex min-w-[150px] flex-1 flex-col gap-1 rounded-2xl p-4 bg-surface-dark-highlight border border-white/5 shadow-sm">
            <div class="flex justify-between items-start">
              <p class="text-text-secondary text-xs font-medium">业绩完成率</p>
              <svg class="w-[18px] h-[18px] text-orange-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 21v-4m0 0V5a2 2 0 012-2h6.5l1 1H21l-3 6 3 6h-8.5l-1-1H5a2 2 0 00-2 2zm9-13.5V9" />
              </svg>
            </div>
            <p class="text-white tracking-tight text-xl font-bold mt-2">85%</p>
            <div class="w-full bg-gray-700 h-1.5 rounded-full mt-3 overflow-hidden">
              <div class="bg-gradient-to-r from-orange-400 to-red-500 h-1.5 rounded-full" style="width: 85%"></div>
            </div>
          </div>
        </div>
      </section>

      <!-- 今日重点待办 (动态) -->
      <section class="flex flex-col gap-3">
        <div class="flex items-center justify-between px-1">
          <div class="flex items-center gap-2">
            <h3 class="text-white text-lg font-bold">今日重点待办</h3>
            <span class="bg-red-500/20 text-red-400 text-[10px] font-bold px-1.5 py-0.5 rounded-md">
              {{ importantTasks.length }}
            </span>
          </div>
          <button
            class="flex items-center text-primary text-xs font-semibold hover:text-primary-dark transition-colors"
            @click="goToAllTasks"
          >
            查看全部待办
            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
            </svg>
          </button>
        </div>

        <!-- 加载中 -->
        <div v-if="tasksLoading" class="text-center py-8">
          <div class="inline-block animate-spin rounded-full h-8 w-8 border-b-2 border-primary"></div>
        </div>

        <!-- 任务列表 -->
        <div
          v-else-if="importantTasks.length > 0"
          v-for="task in importantTasks"
          :key="task.id"
          :class="[
            'flex items-center justify-between gap-3 p-3.5 rounded-2xl bg-surface-dark-highlight border-l-4 shadow-sm hover:shadow-md transition-shadow cursor-pointer',
            getPriorityBadge(task.priority).borderClass
          ]"
          @click="goToTaskDetail(task.id)"
        >
          <div class="flex items-center gap-3">
            <div class="relative shrink-0">
              <div class="bg-gradient-to-br from-primary to-blue-600 rounded-full size-11 flex items-center justify-center text-white font-bold text-sm">
                {{ task.customerName?.charAt(0) || '客' }}
              </div>
              <div
                v-if="task.priority === 'P0'"
                class="absolute -bottom-1 -right-1 bg-red-500 text-white flex items-center justify-center size-4 rounded-full border-2 border-surface-dark-highlight"
              >
                <span class="text-[10px] font-bold">!</span>
              </div>
              <div
                v-else-if="task.priority === 'P1'"
                class="absolute -bottom-1 -right-1 bg-primary text-white flex items-center justify-center size-4 rounded-full border-2 border-surface-dark-highlight"
              >
                <svg class="w-2.5 h-2.5" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
                </svg>
              </div>
            </div>
            <div class="flex flex-col gap-0.5">
              <div class="flex items-center gap-2">
                <p class="text-white text-sm font-bold">{{ task.customerName || '客户' }}</p>
                <span :class="['text-[10px] px-1.5 py-0.5 rounded border font-medium', getPriorityBadge(task.priority).class]">
                  {{ getPriorityBadge(task.priority).label }}
                </span>
              </div>
              <p class="text-gray-400 text-xs line-clamp-1">{{ task.description }}</p>
            </div>
          </div>
          <div class="flex gap-2 shrink-0">
            <button
              v-if="task.priority === 'P0'"
              class="flex items-center justify-center size-9 rounded-full bg-green-500/20 text-green-400 hover:bg-green-500 hover:text-white transition-colors"
              @click="handleContact(task, 'call', $event)"
            >
              <svg class="w-[18px] h-[18px]" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
              </svg>
            </button>
            <button
              class="flex items-center justify-center size-9 rounded-full bg-white/5 text-gray-400 hover:bg-primary hover:text-white transition-colors"
              @click="handleContact(task, 'chat', $event)"
            >
              <svg class="w-[18px] h-[18px]" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z" />
              </svg>
            </button>
          </div>
        </div>

        <!-- 空状态 -->
        <div v-else class="text-center py-8">
          <svg class="w-16 h-16 mx-auto text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
          </svg>
          <p class="mt-2 text-sm text-gray-500 dark:text-gray-400">暂无重点待办</p>
        </div>
      </section>
    </main>

    <!-- Bottom Navigation -->
    <BottomNav />
  </div>
</template>

<style scoped>
.no-scrollbar::-webkit-scrollbar {
  display: none;
}
.no-scrollbar {
  -ms-overflow-style: none;
  scrollbar-width: none;
}
.line-clamp-1 {
  display: -webkit-box;
  -webkit-line-clamp: 1;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
