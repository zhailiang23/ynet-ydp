<script setup lang="ts">
import type { FinancialProduct } from '@/types/product'
import { computed } from 'vue'

interface Props {
  product: FinancialProduct
  selected?: boolean
}

const props = withDefaults(defineProps<Props>(), {
  selected: false,
})

const emit = defineEmits<{
  (e: 'toggle-select'): void
  (e: 'click'): void
}>()

// 格式化收益率
const formattedReturn = computed(() => {
  return props.product.expectedReturn ? props.product.expectedReturn.toFixed(2) : '-'
})

// 格式化起购金额
const formattedInvestment = computed(() => {
  const amount = props.product.minimumInvestment
  if (amount >= 10000) {
    return `${(amount / 10000).toFixed(0)}万起`
  }
  return `${amount}元起`
})

// 风险等级文字
const riskLevelText = computed(() => {
  const level = props.product.riskLevel
  const map: Record<string, string> = {
    R1: 'PR1 极低风险',
    R2: 'PR2 低风险',
    R3: 'PR3 中等风险',
    R4: 'PR4 中高风险',
    R5: 'PR5 高风险',
  }
  return map[level] || level
})
</script>

<template>
  <div
    class="bg-white dark:bg-surface-dark rounded-xl p-4 shadow-sm border transition-all cursor-pointer"
    :class="selected ? 'border-primary/30' : 'border-gray-100 dark:border-none'"
    @click="emit('click')"
  >
    <!-- Selected Indicator -->
    <div
      v-if="selected"
      class="absolute top-0 right-0 w-12 h-12 bg-primary/10 rounded-bl-3xl flex items-start justify-end p-2"
    >
      <svg
        class="w-5 h-5 text-primary"
        fill="currentColor"
        viewBox="0 0 20 20"
      >
        <path
          fill-rule="evenodd"
          d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z"
          clip-rule="evenodd"
        />
      </svg>
    </div>

    <!-- Header -->
    <div class="flex justify-between items-start mb-3">
      <div class="flex flex-col gap-1">
        <div class="flex items-center gap-2">
          <span class="text-gray-900 dark:text-white font-bold text-base">
            {{ product.productName }}
          </span>
          <!-- 热销标签 -->
          <span
            v-if="product.isHot"
            class="bg-red-500/10 text-red-500 dark:text-red-400 text-[10px] px-1.5 py-0.5 rounded font-medium"
          >
            热销
          </span>
          <!-- 新品标签 -->
          <span
            v-if="product.isNew"
            class="bg-green-500/10 text-green-500 dark:text-green-400 text-[10px] px-1.5 py-0.5 rounded font-medium"
          >
            新品
          </span>
        </div>
        <span class="text-gray-400 dark:text-gray-500 text-xs">
          {{ riskLevelText }} | {{ product.saleChannel || '自营' }}
        </span>
      </div>
      <!-- Checkbox -->
      <button
        class="text-gray-400 hover:text-primary transition-colors"
        @click.stop="emit('toggle-select')"
      >
        <svg
          v-if="!selected"
          class="w-6 h-6"
          fill="none"
          stroke="currentColor"
          viewBox="0 0 24 24"
        >
          <rect x="3" y="3" width="18" height="18" rx="2" stroke-width="2" />
        </svg>
        <svg
          v-else
          class="w-6 h-6 text-primary"
          fill="currentColor"
          viewBox="0 0 20 20"
        >
          <path
            fill-rule="evenodd"
            d="M16.707 5.293a1 1 0 010 1.414l-8 8a1 1 0 01-1.414 0l-4-4a1 1 0 011.414-1.414L8 12.586l7.293-7.293a1 1 0 011.414 0z"
            clip-rule="evenodd"
          />
        </svg>
      </button>
    </div>

    <!-- Metrics -->
    <div class="grid grid-cols-3 gap-2 items-end">
      <!-- 收益率 -->
      <div class="col-span-1 flex flex-col">
        <span class="text-primary text-xl font-bold tracking-tight">
          {{ formattedReturn }}<span class="text-sm">%</span>
        </span>
        <span class="text-gray-400 dark:text-text-secondary text-[10px] mt-0.5">
          {{ product.returnType || '预期收益率' }}
        </span>
      </div>

      <!-- 期限 -->
      <div class="col-span-1 flex flex-col items-center border-l border-gray-100 dark:border-white/5">
        <span class="text-gray-900 dark:text-white text-sm font-semibold">
          {{ product.duration || '-' }}
        </span>
        <span class="text-gray-400 dark:text-text-secondary text-[10px] mt-0.5">期限</span>
      </div>

      <!-- 起购金额 -->
      <div class="col-span-1 flex flex-col items-end border-l border-gray-100 dark:border-white/5 pl-2">
        <span class="text-gray-900 dark:text-white text-sm font-semibold">
          {{ formattedInvestment }}
        </span>
        <span class="text-gray-400 dark:text-text-secondary text-[10px] mt-0.5">起购金额</span>
      </div>
    </div>

    <!-- AI Insight (如果有 AI 匹配度) -->
    <div
      v-if="product.aiMatchScore && product.aiMatchScore > 0"
      class="mt-3 pt-3 border-t border-dashed border-gray-200 dark:border-white/10 flex items-center gap-2"
    >
      <svg
        class="w-3.5 h-3.5 text-purple-400"
        fill="currentColor"
        viewBox="0 0 20 20"
      >
        <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
      </svg>
      <p class="text-xs text-gray-500 dark:text-gray-400">
        AI 匹配度 <span class="text-primary font-bold">{{ product.aiMatchScore }}%</span>
      </p>
    </div>
  </div>
</template>
