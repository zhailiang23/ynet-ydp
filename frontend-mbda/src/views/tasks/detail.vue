<script setup lang="ts">
import { ref, onMounted, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { getTask, completeTask, deleteTask, type Task } from '@/api/task'
import { createTaskAction } from '@/api/task-action'

const router = useRouter()
const route = useRoute()

const task = ref<Task | null>(null)
const loading = ref(false)
const remarks = ref('')

// 获取任务ID
const taskId = computed(() => Number(route.params.id))

// 加载任务详情
const loadTaskDetail = async () => {
  try {
    loading.value = true
    task.value = await getTask(taskId.value)
  } catch (error) {
    console.error('加载任务详情失败:', error)
  } finally {
    loading.value = false
  }
}

// 返回上一页
const goBack = () => {
  router.back()
}

// 获取优先级标签
const getPriorityInfo = (priority: string) => {
  const configs: Record<string, { label: string; color: string; icon: string }> = {
    P0: { label: '紧急', color: 'text-red-500', icon: 'priority_high' },
    P1: { label: '重要', color: 'text-primary', icon: 'flag' },
    P2: { label: '普通', color: 'text-gray-500', icon: 'bookmark' },
    P3: { label: '低', color: 'text-gray-400', icon: 'bookmark_border' },
  }
  return configs[priority] || configs.P2
}

// 获取状态标签
const getStatusInfo = (status: number) => {
  const configs: Record<number, { label: string; color: string }> = {
    0: { label: '待处理', color: 'primary' },
    1: { label: '进行中', color: 'blue-500' },
    2: { label: '已完成', color: 'green-500' },
    3: { label: '已取消', color: 'gray-500' },
  }
  return configs[status] || configs[0]
}

// 格式化日期时间
const formatDateTime = (timestamp: number) => {
  if (!timestamp) return ''
  const date = new Date(timestamp)
  const year = date.getFullYear()
  const month = String(date.getMonth() + 1).padStart(2, '0')
  const day = String(date.getDate()).padStart(2, '0')
  const hours = String(date.getHours()).padStart(2, '0')
  const minutes = String(date.getMinutes()).padStart(2, '0')
  return `${year}年${month}月${day}日 ${hours}:${minutes}`
}

// 格式化简短日期
const formatShortDate = (timestamp: number) => {
  if (!timestamp) return ''
  const date = new Date(timestamp)
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hours = String(date.getHours()).padStart(2, '0')
  const minutes = String(date.getMinutes()).padStart(2, '0')
  return `${month}月${day}日 ${hours}:${minutes}`
}

// 完成任务
const handleComplete = async () => {
  if (!task.value) return

  try {
    await completeTask(task.value.id)
    // 刷新任务详情
    await loadTaskDetail()
  } catch (error) {
    console.error('完成任务失败:', error)
  }
}

// 删除任务
const handleDelete = async () => {
  if (!task.value) return

  if (!confirm('确定要删除这个任务吗？')) return

  try {
    await deleteTask(task.value.id)
    // 返回列表页
    router.push('/tasks')
  } catch (error) {
    console.error('删除任务失败:', error)
  }
}

// 延期处理
const handleSnooze = () => {
  console.log('延期处理')
  // TODO: 实现延期功能
}

// 加入日程
const handleAddToCalendar = () => {
  console.log('加入日程')
  // TODO: 实现加入日程功能
}

// 拨打电话
const handleCall = async () => {
  if (!task.value) return

  try {
    // 获取当前时间
    const now = new Date()
    const actionTime = now.toISOString().replace('T', ' ').substring(0, 19)

    // 创建行动记录
    await createTaskAction({
      taskId: task.value.id,
      actionType: 'CALL',
      actionTime,
      actionUserId: 1, // TODO: 使用实际登录用户 ID
      actionUserName: '当前用户', // TODO: 使用实际用户姓名
      remark: `电话联系客户：${task.value.customerName || '未知客户'}`,
    })

    alert('已记录拨打电话行动')
  } catch (error) {
    console.error('记录电话行动失败:', error)
    alert('记录失败，请重试')
  }
}

// 发送短信
const handleSms = async () => {
  if (!task.value) return

  try {
    // 获取当前时间
    const now = new Date()
    const actionTime = now.toISOString().replace('T', ' ').substring(0, 19)

    // 创建行动记录
    await createTaskAction({
      taskId: task.value.id,
      actionType: 'SMS',
      actionTime,
      actionUserId: 1, // TODO: 使用实际登录用户 ID
      actionUserName: '当前用户', // TODO: 使用实际用户姓名
      remark: `短信联系客户：${task.value.customerName || '未知客户'}`,
    })

    alert('已记录发送短信行动')
  } catch (error) {
    console.error('记录短信行动失败:', error)
    alert('记录失败，请重试')
  }
}

// 组件挂载
onMounted(() => {
  loadTaskDetail()
})
</script>

<template>
  <div class="relative flex h-full min-h-screen w-full flex-col overflow-x-hidden pb-[100px] bg-background-dark">
    <!-- TopAppBar -->
    <header class="sticky top-0 z-50 flex items-center justify-between bg-background-dark/95 backdrop-blur-md p-4 pb-2 border-b border-gray-800">
      <div
        class="flex size-10 shrink-0 items-center justify-center rounded-full hover:bg-gray-800 transition-colors cursor-pointer text-white"
        @click="goBack"
      >
        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
        </svg>
      </div>
      <h2 class="text-white text-lg font-bold leading-tight tracking-tight flex-1 text-center pr-10">任务详情</h2>
    </header>

    <!-- Loading -->
    <div v-if="loading" class="flex-1 flex items-center justify-center">
      <div class="animate-spin rounded-full h-12 w-12 border-b-2 border-primary"></div>
    </div>

    <!-- Main Content -->
    <main v-else-if="task" class="flex-1 flex flex-col gap-6 p-4 pt-6">
      <!-- Headline & Status -->
      <section class="flex flex-col gap-4">
        <div class="flex items-start justify-between gap-4">
          <h1 class="text-white tracking-tight text-[26px] font-bold leading-tight">{{ task.title }}</h1>
          <!-- Status Badge -->
          <div
            class="flex shrink-0 items-center justify-center rounded-full px-3 py-1"
            :class="[
              task.status === 0 ? 'bg-primary/10 border border-primary/20' :
              task.status === 1 ? 'bg-blue-500/10 border border-blue-500/20' :
              task.status === 2 ? 'bg-green-500/10 border border-green-500/20' :
              'bg-gray-500/10 border border-gray-500/20'
            ]"
          >
            <span
              class="size-2 rounded-full mr-2"
              :class="[
                task.status === 0 ? 'bg-primary' :
                task.status === 1 ? 'bg-blue-500' :
                task.status === 2 ? 'bg-green-500' :
                'bg-gray-500'
              ]"
            ></span>
            <p
              class="text-xs font-semibold"
              :class="[
                task.status === 0 ? 'text-primary' :
                task.status === 1 ? 'text-blue-500' :
                task.status === 2 ? 'text-green-500' :
                'text-gray-500'
              ]"
            >
              {{ getStatusInfo(task.status).label }}
            </p>
          </div>
        </div>

        <!-- Chips Row -->
        <div class="flex flex-wrap gap-2">
          <div class="flex h-8 shrink-0 items-center justify-center gap-x-1.5 rounded-lg bg-surface-dark-highlight px-3">
            <svg
              class="w-[18px] h-[18px]"
              :class="getPriorityInfo(task.priority).color"
              fill="currentColor"
              viewBox="0 0 20 20"
            >
              <path fill-rule="evenodd" d="M8.257 3.099c.765-1.36 2.722-1.36 3.486 0l5.58 9.92c.75 1.334-.213 2.98-1.742 2.98H4.42c-1.53 0-2.493-1.646-1.743-2.98l5.58-9.92zM11 13a1 1 0 11-2 0 1 1 0 012 0zm-1-8a1 1 0 00-1 1v3a1 1 0 002 0V6a1 1 0 00-1-1z" clip-rule="evenodd" />
            </svg>
            <p class="text-white text-xs font-medium">优先级：{{ getPriorityInfo(task.priority).label }}</p>
          </div>

          <div v-if="task.deadline" class="flex h-8 shrink-0 items-center justify-center gap-x-1.5 rounded-lg bg-surface-dark-highlight px-3">
            <svg class="w-[18px] h-[18px] text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
            </svg>
            <p class="text-white text-xs font-medium">截止：{{ formatShortDate(task.deadline) }}</p>
          </div>

          <div v-if="task.customerName" class="flex h-8 shrink-0 items-center justify-center gap-x-1.5 rounded-lg bg-surface-dark-highlight px-3">
            <svg class="w-[18px] h-[18px] text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16 7a4 4 0 11-8 0 4 4 0 018 0zM12 14a7 7 0 00-7 7h14a7 7 0 00-7-7z" />
            </svg>
            <p class="text-white text-xs font-medium">客户：{{ task.customerName }}</p>
          </div>
        </div>
      </section>

      <!-- Description -->
      <section>
        <h3 class="text-text-secondary text-sm font-medium mb-2">任务描述</h3>
        <p class="text-gray-200 text-base font-normal leading-relaxed">
          {{ task.description || '暂无描述' }}
        </p>

        <!-- AI 建议 -->
        <div v-if="task.aiSuggestion" class="mt-4 p-3 rounded-lg bg-primary/10 border border-primary/20">
          <div class="flex items-center gap-2 mb-2">
            <svg class="w-5 h-5 text-primary" fill="currentColor" viewBox="0 0 20 20">
              <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
            </svg>
            <span class="text-primary text-sm font-semibold">AI 建议</span>
          </div>
          <p class="text-sm text-gray-300 leading-relaxed">{{ task.aiSuggestion }}</p>
        </div>

        <!-- Action Buttons -->
        <div class="mt-4 flex gap-3 flex-wrap">
          <button
            class="flex items-center gap-2 px-4 py-2 rounded-lg bg-surface-dark border border-gray-700 hover:bg-surface-dark-highlight transition-colors"
            @click="handleAddToCalendar"
          >
            <svg class="w-5 h-5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
            </svg>
            <span class="text-sm font-medium text-gray-300">加入日程</span>
          </button>

          <button
            class="flex items-center gap-2 px-4 py-2 rounded-lg bg-surface-dark border border-green-700/50 hover:bg-green-900/20 transition-colors"
            @click="handleCall"
          >
            <svg class="w-5 h-5 text-green-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z" />
            </svg>
            <span class="text-sm font-medium text-green-400">拨打电话</span>
          </button>

          <button
            class="flex items-center gap-2 px-4 py-2 rounded-lg bg-surface-dark border border-blue-700/50 hover:bg-blue-900/20 transition-colors"
            @click="handleSms"
          >
            <svg class="w-5 h-5 text-blue-500" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8 10h.01M12 10h.01M16 10h.01M9 16H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-5l-5 5v-5z" />
            </svg>
            <span class="text-sm font-medium text-blue-400">发送短信</span>
          </button>
        </div>
      </section>

      <!-- Details Card -->
      <section class="rounded-xl bg-surface-dark shadow-sm border border-gray-700 overflow-hidden">
        <div class="px-4 py-3 border-b border-gray-700">
          <h3 class="text-white text-sm font-semibold">详细信息</h3>
        </div>
        <div class="p-4 grid grid-cols-[30%_1fr] gap-y-4 gap-x-4">
          <!-- 任务来源 -->
          <p class="text-text-secondary text-sm">任务来源</p>
          <div class="flex items-center gap-2">
            <svg v-if="task.aiGenerated === 1" class="w-4 h-4 text-gray-400" fill="currentColor" viewBox="0 0 20 20">
              <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
            </svg>
            <p class="text-white text-sm">{{ task.aiGenerated === 1 ? 'AI 自动生成' : '手动创建' }}</p>
          </div>

          <!-- 创建时间 -->
          <p class="text-text-secondary text-sm">创建时间</p>
          <p class="text-white text-sm">{{ formatDateTime(task.createTime) }}</p>

          <!-- 业务价值 -->
          <p v-if="task.expectedRevenue" class="text-text-secondary text-sm">预期收益</p>
          <p v-if="task.expectedRevenue" class="text-white text-sm">¥{{ task.expectedRevenue.toLocaleString() }}</p>

          <!-- 综合评分 -->
          <p class="text-text-secondary text-sm">综合评分</p>
          <div class="flex items-center gap-2">
            <div class="flex-1 h-2 bg-gray-700 rounded-full overflow-hidden">
              <div
                class="h-full bg-gradient-to-r from-orange-400 to-red-500 rounded-full"
                :style="{ width: `${task.comprehensiveScore * 10}%` }"
              ></div>
            </div>
            <span class="text-white text-sm font-medium">{{ task.comprehensiveScore }}/10</span>
          </div>
        </div>
      </section>

      <!-- Remarks -->
      <section class="pb-4">
        <label class="block text-text-secondary text-sm font-medium mb-2" for="remarks">
          添加备注 / 结果记录
        </label>
        <div class="relative">
          <textarea
            id="remarks"
            v-model="remarks"
            class="w-full rounded-xl bg-surface-dark border border-gray-700 p-3 text-white text-sm placeholder:text-gray-500 focus:outline-none focus:ring-2 focus:ring-primary/50 focus:border-transparent resize-none shadow-sm transition-all"
            placeholder="在此记录任务处理过程、结果或上传相关说明..."
            rows="4"
          ></textarea>
          <div class="absolute bottom-3 right-3 flex gap-2">
            <button class="p-1.5 rounded-md hover:bg-gray-700 text-gray-500 transition-colors">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 11a7 7 0 01-7 7m0 0a7 7 0 01-7-7m7 7v4m0 0H8m4 0h4m-4-8a3 3 0 01-3-3V5a3 3 0 116 0v6a3 3 0 01-3 3z" />
              </svg>
            </button>
            <button class="p-1.5 rounded-md hover:bg-gray-700 text-gray-500 transition-colors">
              <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 16l4.586-4.586a2 2 0 012.828 0L16 16m-2-2l1.586-1.586a2 2 0 012.828 0L20 14m-6-6h.01M6 20h12a2 2 0 002-2V6a2 2 0 00-2-2H6a2 2 0 00-2 2v12a2 2 0 002 2z" />
              </svg>
            </button>
          </div>
        </div>
      </section>
    </main>

    <!-- Sticky Footer -->
    <footer class="fixed bottom-0 left-0 right-0 z-50 bg-background-dark border-t border-surface-dark-highlight p-4 pb-8">
      <div class="flex items-center gap-3 w-full max-w-md mx-auto">
        <!-- Delete Button -->
        <button
          class="flex shrink-0 size-12 items-center justify-center rounded-xl border border-red-900/30 bg-red-900/10 text-red-400 hover:bg-red-900/20 active:scale-95 transition-all"
          @click="handleDelete"
        >
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M19 7l-.867 12.142A2 2 0 0116.138 21H7.862a2 2 0 01-1.995-1.858L5 7m5 4v6m4-6v6m1-10V4a1 1 0 00-1-1h-4a1 1 0 00-1 1v3M4 7h16" />
          </svg>
        </button>

        <!-- Snooze Button -->
        <button
          v-if="task && task.status === 0"
          class="flex-1 h-12 rounded-xl border border-gray-700 bg-transparent text-gray-300 font-medium text-sm hover:bg-surface-dark transition-all"
          @click="handleSnooze"
        >
          延期处理
        </button>

        <!-- Complete Button -->
        <button
          v-if="task && task.status === 0"
          class="flex-[2] h-12 rounded-xl bg-primary shadow-lg shadow-primary/25 text-white font-bold text-sm hover:bg-primary/90 active:scale-95 transition-all flex items-center justify-center gap-2"
          @click="handleComplete"
        >
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 13l4 4L19 7" />
          </svg>
          标记已完成
        </button>
      </div>
    </footer>
  </div>
</template>
