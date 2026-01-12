<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getFinancialProduct } from '@/api/financial'
import type { FinancialProduct } from '@/types/product'

const route = useRoute()
const router = useRouter()

const loading = ref(true)
const product = ref<FinancialProduct | null>(null)

// 格式化收益率
const formattedReturn = computed(() => {
  return product.value?.expectedReturn ? product.value.expectedReturn.toFixed(2) : '-'
})

// 格式化起购金额
const formattedInvestment = computed(() => {
  if (!product.value) return '-'
  const amount = product.value.minimumInvestment
  if (amount >= 10000) {
    return `${(amount / 10000).toFixed(0)}万${product.value.investmentUnit || '元'}`
  }
  return `${amount}${product.value.investmentUnit || '元'}`
})

// 风险等级文字
const riskLevelText = computed(() => {
  if (!product.value) return ''
  const level = product.value.riskLevel
  const map: Record<string, string> = {
    R1: 'PR1 极低风险',
    R2: 'PR2 低风险',
    R3: 'PR3 中等风险',
    R4: 'PR4 中高风险',
    R5: 'PR5 高风险',
  }
  return map[level] || level
})

// 产品分类文字
const categoryText = computed(() => {
  if (!product.value) return ''
  const categoryMap: Record<string, string> = {
    WEALTH: '理财',
    FUND: '基金',
    INSURANCE: '保险',
    BOND: '债券',
    PRECIOUS_METAL: '贵金属',
  }
  return categoryMap[product.value.category] || product.value.category
})

// 加载产品详情
const loadProduct = async () => {
  const id = Number(route.params.id)
  if (!id) {
    router.back()
    return
  }

  loading.value = true
  try {
    const data = await getFinancialProduct(id)
    product.value = data
  } catch (error) {
    console.error('加载产品详情失败:', error)
    alert('加载产品详情失败')
    router.back()
  } finally {
    loading.value = false
  }
}

// 返回
const goBack = () => {
  router.back()
}

onMounted(() => {
  loadProduct()
})
</script>

<template>
  <div class="min-h-screen bg-background-light dark:bg-background-dark">
    <!-- Loading -->
    <div v-if="loading" class="flex items-center justify-center h-screen">
      <div class="inline-block animate-spin rounded-full h-12 w-12 border-b-2 border-primary"></div>
    </div>

    <!-- Content -->
    <div v-else-if="product" class="pb-24">
      <!-- Header -->
      <div class="sticky top-0 z-50 bg-background-light/95 dark:bg-background-dark/95 backdrop-blur-md border-b border-gray-200 dark:border-white/10">
        <div class="flex items-center px-4 py-3">
          <button
            class="flex items-center justify-center text-gray-500 dark:text-white hover:bg-gray-200 dark:hover:bg-white/10 rounded-full w-10 h-10 transition-colors"
            @click="goBack"
          >
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
            </svg>
          </button>
          <h2 class="flex-1 text-center text-gray-900 dark:text-white text-lg font-bold">
            产品详情
          </h2>
          <div class="w-10"></div>
        </div>
      </div>

      <!-- Banner Image (如果有) -->
      <div
        v-if="product.bannerImage"
        class="w-full h-48 bg-cover bg-center"
        :style="{ backgroundImage: `url(${product.bannerImage})` }"
      >
        <div class="w-full h-full bg-gradient-to-b from-transparent to-background-light dark:to-background-dark"></div>
      </div>

      <div class="px-4 pt-4">
        <!-- Product Header -->
        <div class="bg-white dark:bg-surface-dark rounded-xl p-4 shadow-sm border border-gray-100 dark:border-none mb-4">
          <div class="flex items-start gap-2 mb-3">
            <h1 class="flex-1 text-gray-900 dark:text-white font-bold text-xl leading-tight">
              {{ product.productName }}
            </h1>
            <span
              v-if="product.isHot"
              class="bg-red-500/10 text-red-500 dark:text-red-400 text-xs px-2 py-1 rounded font-medium"
            >
              热销
            </span>
            <span
              v-if="product.isNew"
              class="bg-green-500/10 text-green-500 dark:text-green-400 text-xs px-2 py-1 rounded font-medium"
            >
              新品
            </span>
          </div>

          <div class="flex items-center gap-2 text-sm text-gray-500 dark:text-gray-400 mb-4">
            <span>{{ categoryText }}</span>
            <span>·</span>
            <span>{{ product.productCode }}</span>
          </div>

          <!-- Metrics -->
          <div class="grid grid-cols-3 gap-4 py-4 border-t border-gray-100 dark:border-white/5">
            <div class="flex flex-col items-center">
              <span class="text-primary text-2xl font-bold">
                {{ formattedReturn }}<span class="text-base">%</span>
              </span>
              <span class="text-gray-400 dark:text-text-secondary text-xs mt-1">
                {{ product.returnType || '预期收益率' }}
              </span>
            </div>
            <div class="flex flex-col items-center border-l border-gray-100 dark:border-white/5">
              <span class="text-gray-900 dark:text-white text-lg font-semibold">
                {{ product.duration || '-' }}
              </span>
              <span class="text-gray-400 dark:text-text-secondary text-xs mt-1">产品期限</span>
            </div>
            <div class="flex flex-col items-center border-l border-gray-100 dark:border-white/5">
              <span class="text-gray-900 dark:text-white text-lg font-semibold">
                {{ formattedInvestment }}
              </span>
              <span class="text-gray-400 dark:text-text-secondary text-xs mt-1">起购金额</span>
            </div>
          </div>

          <!-- Tags -->
          <div v-if="product.tags && product.tags.length > 0" class="flex flex-wrap gap-2 pt-4 border-t border-gray-100 dark:border-white/5">
            <span
              v-for="tag in product.tags"
              :key="tag"
              class="tag tag-blue"
            >
              {{ tag }}
            </span>
          </div>
        </div>

        <!-- Product Info -->
        <div class="bg-white dark:bg-surface-dark rounded-xl p-4 shadow-sm border border-gray-100 dark:border-none mb-4">
          <h3 class="text-gray-900 dark:text-white font-bold text-base mb-4">产品信息</h3>

          <div class="space-y-3">
            <div class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
              <span class="text-gray-500 dark:text-gray-400 text-sm">风险等级</span>
              <span class="text-gray-900 dark:text-white text-sm font-medium">{{ riskLevelText }}</span>
            </div>
            <div class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
              <span class="text-gray-500 dark:text-gray-400 text-sm">销售渠道</span>
              <span class="text-gray-900 dark:text-white text-sm font-medium">{{ product.saleChannel || '自营' }}</span>
            </div>
            <div v-if="product.durationDays" class="flex justify-between py-2 border-b border-gray-100 dark:border-white/5">
              <span class="text-gray-500 dark:text-gray-400 text-sm">期限天数</span>
              <span class="text-gray-900 dark:text-white text-sm font-medium">{{ product.durationDays }} 天</span>
            </div>
            <div v-if="product.createTime" class="flex justify-between py-2">
              <span class="text-gray-500 dark:text-gray-400 text-sm">创建时间</span>
              <span class="text-gray-900 dark:text-white text-sm font-medium">
                {{ new Date(product.createTime).toLocaleDateString() }}
              </span>
            </div>
          </div>
        </div>

        <!-- Description -->
        <div
          v-if="product.description"
          class="bg-white dark:bg-surface-dark rounded-xl p-4 shadow-sm border border-gray-100 dark:border-none mb-4"
        >
          <h3 class="text-gray-900 dark:text-white font-bold text-base mb-3">产品描述</h3>
          <p class="text-gray-600 dark:text-gray-300 text-sm leading-relaxed whitespace-pre-wrap">
            {{ product.description }}
          </p>
        </div>

        <!-- Features -->
        <div
          v-if="product.features"
          class="bg-white dark:bg-surface-dark rounded-xl p-4 shadow-sm border border-gray-100 dark:border-none mb-4"
        >
          <h3 class="text-gray-900 dark:text-white font-bold text-base mb-3">产品特色</h3>
          <p class="text-gray-600 dark:text-gray-300 text-sm leading-relaxed whitespace-pre-wrap">
            {{ product.features }}
          </p>
        </div>

        <!-- AI Insight -->
        <div
          v-if="product.aiMatchScore && product.aiMatchScore > 0"
          class="bg-gradient-to-r from-purple-500/10 to-blue-500/10 dark:from-purple-500/20 dark:to-blue-500/20 rounded-xl p-4 border border-purple-200 dark:border-purple-500/30"
        >
          <div class="flex items-center gap-2 mb-2">
            <svg class="w-5 h-5 text-purple-500" fill="currentColor" viewBox="0 0 20 20">
              <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
            </svg>
            <h3 class="text-gray-900 dark:text-white font-bold text-base">AI 智能推荐</h3>
          </div>
          <p class="text-gray-700 dark:text-gray-200 text-sm">
            该产品的 AI 匹配度为 <span class="text-primary font-bold text-lg">{{ product.aiMatchScore }}%</span>，
            非常适合当前客户的风险偏好和投资需求。
          </p>
          <p v-if="product.aiKeywords" class="text-gray-600 dark:text-gray-300 text-xs mt-2">
            关键词：{{ product.aiKeywords }}
          </p>
        </div>
      </div>

      <!-- Fixed Bottom Actions -->
      <div class="fixed bottom-0 left-0 right-0 bg-white dark:bg-surface-dark border-t border-gray-200 dark:border-white/10 p-4 z-50">
        <div class="flex gap-3">
          <button
            class="flex-1 bg-gray-100 dark:bg-gray-700 text-gray-900 dark:text-white hover:bg-gray-200 dark:hover:bg-gray-600 px-6 py-3 rounded-lg font-medium transition-colors"
          >
            收藏
          </button>
          <button
            class="flex-1 bg-primary hover:bg-blue-600 text-white px-6 py-3 rounded-lg font-medium transition-colors"
          >
            立即购买
          </button>
        </div>
      </div>
    </div>
  </div>
</template>
