<script setup lang="ts">
import { computed } from 'vue'
import type { Task, TaskPriority, TaskType } from '@/api/task'

interface Props {
  task: Task
  isAI?: boolean
}

const props = withDefaults(defineProps<Props>(), {
  isAI: false,
})

const emit = defineEmits<{
  complete: [id: number]
  contact: [task: Task]
  generatePlan: [task: Task]
  click: [task: Task]
}>()

// 优先级配置
const priorityConfig = computed(() => {
  const configs = {
    P0: {
      label: 'P0 紧急',
      color: 'red',
      stripe: 'bg-red-500',
      badge: 'bg-red-50 dark:bg-red-900/20 text-red-700 dark:text-red-400',
    },
    P1: {
      label: 'P1 重要',
      color: 'yellow',
      stripe: 'bg-yellow-500',
      badge: 'bg-yellow-50 dark:bg-yellow-900/20 text-yellow-700 dark:text-yellow-400',
    },
    P2: {
      label: 'P2 普通',
      color: 'blue',
      stripe: 'bg-blue-400',
      badge: 'bg-blue-50 dark:bg-blue-900/20 text-blue-700 dark:text-blue-400',
    },
    P3: {
      label: 'P3 低',
      color: 'gray',
      stripe: 'bg-gray-300 dark:bg-gray-600',
      badge: 'bg-gray-100 dark:bg-gray-700 text-gray-600 dark:text-gray-300',
    },
  }
  return configs[props.task.priority as keyof typeof configs] || configs.P3
})

// 任务类型配置
const typeConfig = computed(() => {
  const configs: Record<string, { label: string; class: string }> = {
    ai_generated: { label: 'AI生成', class: 'bg-purple-50 dark:bg-purple-900/20 text-purple-700 dark:text-purple-400' },
    manual: { label: '手动创建', class: 'bg-gray-100 dark:bg-gray-700 text-gray-600 dark:text-gray-300' },
    ASSET_UPGRADE: { label: '资产提升', class: 'bg-blue-50 dark:bg-blue-900/20 text-blue-700 dark:text-blue-400' },
    EXPIRE_REMINDER: { label: '到期提醒', class: 'bg-gray-100 dark:bg-gray-700 text-gray-600 dark:text-gray-300' },
    COMPLIANCE: { label: '合规', class: 'bg-gray-100 dark:bg-gray-700 text-gray-600 dark:text-gray-300' },
    MEETING: { label: '会议', class: 'bg-gray-100 dark:bg-gray-700 text-gray-600 dark:text-gray-300' },
    ADMINISTRATIVE: { label: '行政', class: 'bg-gray-100 dark:bg-gray-700 text-gray-600 dark:text-gray-300' },
    OTHER: { label: '其他', class: 'bg-gray-100 dark:bg-gray-700 text-gray-600 dark:text-gray-300' },
  }
  // 使用 category 或 taskType
  const key = props.task.category || props.task.taskType || 'OTHER'
  return configs[key] || configs.OTHER
})

// 格式化时间
const formattedDeadline = computed(() => {
  if (!props.task.deadline) return ''
  const date = new Date(props.task.deadline)
  const now = new Date()
  const diff = date.getTime() - now.getTime()
  const hours = Math.floor(diff / (1000 * 60 * 60))

  if (hours < 0) return '已逾期'
  if (hours < 24) return `${hours} 小时内`
  if (hours < 72) return `${Math.floor(hours / 24)} 天内`

  return date.toLocaleDateString('zh-CN', { month: 'long', day: 'numeric' })
})

// 格式化创建时间
const formattedCreatedAt = computed(() => {
  const date = new Date(props.task.createTime)
  const now = new Date()
  const diff = now.getTime() - date.getTime()
  const hours = Math.floor(diff / (1000 * 60 * 60))

  if (hours < 1) return '刚刚'
  if (hours < 24) return `${hours} 小时前`
  if (hours < 48) return '昨天'

  return date.toLocaleDateString('zh-CN', { month: 'numeric', day: 'numeric' })
})
</script>

<template>
  <!-- AI 任务卡片 -->
  <div
    v-if="isAI"
    class="group relative flex flex-col rounded-xl bg-white dark:bg-gray-800 p-4 shadow-sm border border-gray-100 dark:border-gray-700 transition-all active:scale-[0.99] cursor-pointer"
    @click="emit('click', task)"
  >
    <!-- 左侧优先级竖条 -->
    <div :class="['absolute left-0 top-4 bottom-4 w-1 rounded-r', priorityConfig.stripe]"></div>

    <div class="flex items-start justify-between mb-2 pl-3">
      <div class="flex flex-col">
        <div class="flex items-center gap-2 mb-1">
          <!-- AI 标签 -->
          <span class="bg-gradient-to-r from-purple-500 to-indigo-600 text-white text-[10px] px-1.5 py-0.5 rounded font-bold flex items-center gap-1">
            <svg class="w-2.5 h-2.5" fill="currentColor" viewBox="0 0 20 20">
              <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
            </svg>
            AI
          </span>
          <span class="text-xs text-gray-400 font-medium">{{ formattedCreatedAt }}</span>
        </div>
        <h3 class="text-base font-bold text-gray-900 dark:text-white">{{ task.customerName }} - {{ task.title }}</h3>
      </div>
      <div class="flex flex-col items-end">
        <div class="text-xs font-medium text-gray-400 mb-0.5">价值评分</div>
        <div class="text-lg font-bold text-blue-600">{{ task.comprehensiveScore.toFixed(1) }}</div>
      </div>
    </div>

    <p class="text-sm text-gray-500 dark:text-gray-400 pl-3 mb-4 line-clamp-2">
      {{ task.description }}
    </p>

    <div class="flex items-center justify-between pl-3 mt-auto pt-3 border-t border-gray-100 dark:border-gray-700/50">
      <div class="flex gap-2">
        <span :class="['inline-flex items-center rounded px-2 py-1 text-xs font-medium', priorityConfig.badge]">
          {{ priorityConfig.label }}
        </span>
        <span :class="['inline-flex items-center rounded px-2 py-1 text-xs font-medium', typeConfig.class]">
          {{ typeConfig.label }}
        </span>
      </div>
      <button
        v-if="task.priority === 'P0'"
        class="flex items-center gap-1 bg-blue-600 hover:bg-blue-700 text-white px-3 py-1.5 rounded-lg text-xs font-bold transition-colors shadow-md shadow-blue-500/20"
        @click.stop="emit('contact', task)"
      >
        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z"></path>
        </svg>
        去联系
      </button>
      <button
        v-else
        class="flex items-center gap-1 bg-white dark:bg-gray-700 border border-gray-200 dark:border-gray-600 hover:bg-gray-50 dark:hover:bg-gray-600 text-gray-700 dark:text-white px-3 py-1.5 rounded-lg text-xs font-bold transition-colors"
        @click.stop="emit('generatePlan', task)"
      >
        <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"></path>
        </svg>
        生成方案
      </button>
    </div>
  </div>

  <!-- 常规任务卡片 -->
  <div
    v-else
    class="group relative flex flex-col rounded-xl bg-white dark:bg-gray-800 p-4 shadow-sm border border-gray-100 dark:border-gray-700 transition-all active:scale-[0.99] opacity-90 cursor-pointer"
    @click="emit('click', task)"
  >
    <div :class="['absolute left-0 top-4 bottom-4 w-1 rounded-r', priorityConfig.stripe]"></div>
    <div class="flex items-start justify-between pl-3">
      <div class="flex items-center gap-3">
        <!-- 复选框 -->
        <div class="relative flex items-center justify-center">
          <input
            type="checkbox"
            class="peer h-5 w-5 cursor-pointer appearance-none rounded border border-gray-300 dark:border-gray-500 bg-transparent transition-all checked:border-blue-600 checked:bg-blue-600"
            @change.stop="emit('complete', task.id)"
          />
          <svg
            class="pointer-events-none absolute text-white opacity-0 peer-checked:opacity-100 w-4 h-4"
            fill="currentColor"
            viewBox="0 0 20 20"
          >
            <path fill-rule="evenodd" d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z" clip-rule="evenodd"></path>
          </svg>
        </div>
        <div>
          <h3 class="text-sm font-bold text-gray-900 dark:text-white">{{ task.title }}</h3>
          <p class="text-xs text-gray-500 dark:text-gray-400 mt-0.5">
            {{ formattedDeadline ? `截止时间：${formattedDeadline}` : task.description }}
          </p>
        </div>
      </div>
      <span :class="['inline-flex items-center rounded px-2 py-1 text-[10px] font-medium', typeConfig.class]">
        {{ typeConfig.label }}
      </span>
    </div>
  </div>
</template>

<style scoped>
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
