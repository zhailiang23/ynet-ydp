<script setup lang="ts">
import { ref, computed, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { getFinancialProduct } from '@/api/financial'
import type { FinancialProduct } from '@/types/product'

const router = useRouter()
const route = useRoute()

// 获取比对的产品 IDs
const productIds = computed(() => {
  const ids = route.query.ids as string
  return ids ? ids.split(',').map(Number) : []
})

// 产品列表
const products = ref<FinancialProduct[]>([])
const loading = ref(false)

// 客户信息（模拟数据）
const customer = ref({
  name: '王晓明',
  avatar: 'https://lh3.googleusercontent.com/aida-public/AB6AXuAeA1ocafU52z2K0h5-2Yb0w3XR0WDxpdOdUZc6zfMkdgTSicqTbr9XUFQeBVtx-RSUthcY3RF3EJ9Yxek3w8W-rmjh9fo0z4UUelPkHg5kN2h9GT7xDsA7XyUuCaApW0ULxHU8Xqw9p_MubUJYXsePWTS1pIu_L0zTYPrp43V9ajbIgsjL0dJBmGkP5M9OFNBfq_2rPfDkNFMi7HdkWFozVrTgwGrnpbp_1y7FsefjZE2QTYFvMxV92MSEJz3gglP9rUWCfvNPBWtu',
  riskLevel: '稳健型',
  status: 'online',
})

// AI 智能结论
const aiConclusion = ref('根据您选择的产品，AI 正在分析最优配置方案...')

// 加载产品数据
const loadProducts = async () => {
  try {
    loading.value = true
    // 批量获取产品详情
    const requests = productIds.value.map(id => getFinancialProduct(id))
    const results = await Promise.all(requests)
    products.value = results

    // 生成 AI 结论
    if (products.value.length >= 2) {
      const hasReturn = products.value.filter(p => p.expectedReturn != null && p.expectedReturn > 0)
      if (hasReturn.length >= 2) {
        const best = hasReturn.reduce((a, b) =>
          (a.expectedReturn || 0) > (b.expectedReturn || 0) ? a : b
        )
        const safest = products.value.reduce((a, b) => {
          const aRisk = parseInt(a.riskLevel.replace('R', ''))
          const bRisk = parseInt(b.riskLevel.replace('R', ''))
          return aRisk < bRisk ? a : b
        })
        aiConclusion.value = `${best.productName}预期收益表现更优（${best.expectedReturn}%），${safest.productName}风险等级较低（${safest.riskLevel}），更加稳健。建议根据客户风险偏好选择合适的产品。`
      } else {
        aiConclusion.value = `已为您对比 ${products.value.length} 款产品，请根据风险等级、期限和起购金额等因素综合考虑。`
      }
    }
  } catch (error) {
    console.error('加载产品数据失败:', error)
  } finally {
    loading.value = false
  }
}

// 返回上一页
const goBack = () => {
  router.back()
}

// 删除产品
const removeProduct = (productId: number) => {
  const newIds = productIds.value.filter(id => id !== productId)
  if (newIds.length < 2) {
    alert('至少需要保留 2 个产品进行对比')
    return
  }
  router.replace({ query: { ids: newIds.join(',') } })
  products.value = products.value.filter(p => p.id !== productId)
}

// 添加产品
const addProduct = () => {
  if (products.value.length >= 5) {
    alert('最多可对比 5 个产品')
    return
  }
  // TODO: 打开产品选择器
  console.log('添加产品')
}

// 保存对比
const saveComparison = () => {
  console.log('保存对比')
  // TODO: 实现保存功能
}

// 导出对比
const exportComparison = () => {
  console.log('导出对比')
  // TODO: 实现导出功能
}

// 创建组合
const createPortfolio = () => {
  console.log('创建组合')
  // TODO: 实现创建组合功能
}

// 发送给客户
const sendToCustomer = () => {
  console.log('发送给客户')
  // TODO: 实现发送功能
}

// 获取风险等级样式
const getRiskLevelClass = (level: string) => {
  if (level.includes('R5') || level.includes('高')) return 'bg-red-100 dark:bg-red-900/40 text-red-600 dark:text-red-400'
  if (level.includes('R4') || level.includes('中高')) return 'bg-red-100 dark:bg-red-900/40 text-red-600 dark:text-red-400'
  if (level.includes('R3') || level.includes('中')) return 'bg-orange-100 dark:bg-orange-900/40 text-orange-600 dark:text-orange-400'
  if (level.includes('R2') || level.includes('中低')) return 'bg-yellow-100 dark:bg-yellow-900/40 text-yellow-600 dark:text-yellow-400'
  return 'bg-green-100 dark:bg-green-900/40 text-green-600 dark:text-green-400'
}

// 获取风险等级名称
const getRiskLevelName = (level: string) => {
  const map: Record<string, string> = {
    R1: 'PR1 极低风险',
    R2: 'PR2 低风险',
    R3: 'PR3 中等风险',
    R4: 'PR4 中高风险',
    R5: 'PR5 高风险',
  }
  return map[level] || level
}

// 格式化起购金额
const formatMinAmount = (amount: number) => {
  if (amount >= 10000) {
    return `${(amount / 10000).toFixed(0)} 万元`
  }
  return `${amount} 元`
}

// 高亮最优值
const isHighlighted = (value: string | number | undefined, values: (string | number | undefined)[], higherBetter: boolean = true) => {
  if (value == null) return false
  const numValues = values.filter(v => v != null).map(v => parseFloat(String(v)))
  if (numValues.length === 0) return false
  const numValue = parseFloat(String(value))
  const best = higherBetter ? Math.max(...numValues) : Math.min(...numValues)
  return Math.abs(numValue - best) < 0.01
}

// 判断产品标签
const getProductCategory = (product: FinancialProduct, index: number) => {
  if (product.tags && product.tags.includes('AI 推荐')) return '推荐'
  if (index === 1) return '持仓'
  return ''
}

// 产品分类中英文转换
const getCategoryName = (category: string) => {
  const categoryMap: Record<string, string> = {
    'WEALTH': '理财',
    'FUND': '基金',
    'INSURANCE': '保险',
    'BOND': '债券',
    'PRECIOUS_METAL': '贵金属',
  }
  return categoryMap[category] || category
}

// 组件挂载
onMounted(() => {
  if (productIds.value.length < 2) {
    alert('至少需要选择 2 个产品进行对比')
    router.back()
    return
  }
  loadProducts()
})
</script>

<template>
  <div class="relative flex h-full min-h-screen w-full flex-col overflow-hidden pb-24 bg-background-dark">
    <!-- Header -->
    <header class="sticky top-0 z-30 bg-background-dark/95 backdrop-blur-md border-b border-white/5">
      <div class="flex items-center justify-between p-4 h-14">
        <button
          class="flex items-center justify-center size-10 rounded-full hover:bg-surface-dark-highlight transition-colors -ml-2 text-white"
          @click="goBack"
        >
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
          </svg>
        </button>
        <h1 class="text-base font-bold text-white">产品对比</h1>
        <div class="flex items-center -mr-2">
          <button
            class="flex items-center justify-center size-10 rounded-full hover:bg-surface-dark-highlight transition-colors text-gray-300 mr-1"
            title="历史比对"
          >
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
            </svg>
          </button>
          <button
            class="flex items-center justify-center size-10 rounded-full hover:bg-surface-dark-highlight transition-colors text-white"
          >
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 6V4m0 2a2 2 0 100 4m0-4a2 2 0 110 4m-6 8a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4m6 6v10m6-2a2 2 0 100-4m0 4a2 2 0 110-4m0 4v2m0-6V4" />
            </svg>
          </button>
        </div>
      </div>
    </header>

    <!-- Main Content -->
    <main class="flex-1 overflow-y-auto overflow-x-hidden no-scrollbar">
      <!-- Customer Info Card -->
      <div class="px-4 pt-4 pb-2">
        <div class="mb-4 flex items-start gap-3 rounded-2xl bg-surface-dark-highlight p-3 shadow-sm border border-white/5">
          <div class="relative shrink-0">
            <img
              :src="customer.avatar"
              alt="Customer Avatar"
              class="size-10 rounded-full object-cover ring-2 ring-white/10"
            />
            <div v-if="customer.status === 'online'" class="absolute -bottom-0.5 -right-0.5 size-3 rounded-full border-2 border-surface-dark-highlight bg-green-500"></div>
          </div>
          <div class="flex flex-1 flex-col">
            <div class="flex items-center gap-2">
              <span class="text-sm font-bold text-white">{{ customer.name }}</span>
              <span class="rounded bg-blue-500/20 px-1.5 py-0.5 text-[10px] font-medium text-blue-300">{{ customer.riskLevel }}</span>
            </div>
            <p class="mt-1 text-xs leading-snug text-gray-400">
              针对{{ customer.name }}的资产配置建议，对比推荐产品
            </p>
          </div>
        </div>

        <!-- AI Conclusion Card -->
        <div class="relative overflow-hidden rounded-2xl bg-gradient-to-r from-indigo-600 to-primary p-4 text-white shadow-lg">
          <div class="absolute right-0 top-0 -mt-8 -mr-8 size-32 rounded-full bg-white/10 blur-2xl"></div>
          <div class="flex items-start gap-3 relative z-10">
            <div class="flex items-center justify-center size-8 rounded-full bg-white/20 backdrop-blur-sm shrink-0 mt-1">
              <svg class="w-[18px] h-[18px]" fill="currentColor" viewBox="0 0 20 20">
                <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
              </svg>
            </div>
            <div>
              <h3 class="font-bold text-sm mb-1 opacity-90">AI 智能对比结论</h3>
              <p class="text-xs leading-relaxed text-indigo-50">
                {{ aiConclusion }}
              </p>
            </div>
          </div>
        </div>
      </div>

      <!-- Comparison Table -->
      <div class="relative w-full custom-scroll overflow-x-auto">
        <div class="min-w-max">
          <!-- Table Header -->
          <div class="sticky top-0 z-20 flex border-b border-white/5 bg-background-dark shadow-sm">
            <!-- Left Column Header -->
            <div class="sticky left-0 z-30 w-24 shrink-0 bg-background-dark p-3 flex flex-col justify-end items-start border-r border-white/5 sticky-left-shadow">
              <span class="text-xs font-bold text-gray-200">对比指标</span>
              <span class="text-[10px] font-medium text-gray-400 mt-0.5">已选 {{ products.length }}/5</span>
            </div>

            <!-- Product Columns -->
            <div
              v-for="(product, index) in products"
              :key="product.id"
              class="w-36 shrink-0 p-3 flex flex-col gap-1 border-r border-white/5 bg-background-dark relative group"
            >
              <button
                class="absolute top-1.5 right-1.5 size-6 flex items-center justify-center rounded-full bg-white/10 text-gray-400 hover:bg-red-500/20 hover:text-red-400 transition-colors z-10"
                aria-label="删除"
                @click="removeProduct(product.id!)"
              >
                <svg class="w-[14px] h-[14px]" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12" />
                </svg>
              </button>
              <span
                v-if="getProductCategory(product, index)"
                :class="[
                  'px-1.5 py-0.5 rounded text-[10px] w-fit font-medium',
                  getProductCategory(product, index) === '推荐' ? 'bg-blue-900/40 text-primary' : 'bg-white/10 text-gray-500'
                ]"
              >
                {{ getProductCategory(product, index) }}
              </span>
              <h4 class="font-bold text-sm text-white line-clamp-2 mt-1">{{ product.productName }}</h4>
              <span class="text-[10px] text-gray-500 font-mono">{{ product.productCode }}</span>
            </div>

            <!-- Add Product Button -->
            <div v-if="products.length < 5" class="w-36 shrink-0 p-3 flex flex-col items-center justify-center bg-background-dark">
              <button
                class="flex flex-col items-center justify-center gap-1.5 size-full min-h-[90px] rounded-xl border-2 border-dashed border-white/10 bg-white/5 hover:border-primary hover:bg-primary/5 transition-all group"
                @click="addProduct"
              >
                <div class="size-8 rounded-full bg-white/10 shadow-sm flex items-center justify-center group-hover:bg-primary group-hover:text-white transition-colors text-primary">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
                  </svg>
                </div>
                <span class="text-xs text-gray-300 group-hover:text-primary font-bold">添加产品</span>
              </button>
            </div>
            <div class="w-4 shrink-0"></div>
          </div>

          <!-- Table Body -->
          <div class="bg-surface-dark-highlight">
            <!-- 业绩表现 Section -->
            <div class="flex border-b border-white/5 bg-white/5">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-white/5 py-1.5 px-3 flex items-center border-r border-white/5 sticky-left-shadow">
                <svg class="w-[14px] h-[14px] mr-1.5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 7h8m0 0v8m0-8l-8 8-4-4-6 6" />
                </svg>
                <span class="text-xs font-bold text-gray-200">业绩表现</span>
              </div>
              <div class="flex-1"></div>
            </div>

            <!-- 预期收益率 -->
            <div class="flex border-b border-white/5 hover:bg-white/5 transition-colors">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-surface-dark-highlight p-3 flex items-center text-xs text-gray-400 border-r border-white/5 sticky-left-shadow font-medium">
                预期收益率
              </div>
              <div
                v-for="product in products"
                :key="`return-${product.id}`"
                :class="[
                  'w-36 shrink-0 p-3 flex items-center justify-center text-sm font-bold border-r border-white/5',
                  product.expectedReturn && product.expectedReturn > 0 ? 'text-red-500' : 'text-gray-400',
                  isHighlighted(product.expectedReturn, products.map(p => p.expectedReturn), true) ? 'bg-red-50/30' : ''
                ]"
              >
                {{ product.expectedReturn ? `${product.expectedReturn}%` : '-' }}
              </div>
              <div v-if="products.length < 5" class="w-36 shrink-0"></div>
            </div>

            <!-- 产品期限 -->
            <div class="flex border-b border-white/5 hover:bg-white/5 transition-colors">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-surface-dark-highlight p-3 flex items-center text-xs text-gray-400 border-r border-white/5 sticky-left-shadow font-medium">
                产品期限
              </div>
              <div
                v-for="product in products"
                :key="`duration-${product.id}`"
                class="w-36 shrink-0 p-3 flex items-center justify-center text-sm font-medium text-white border-r border-white/5"
              >
                {{ product.duration || '-' }}
              </div>
              <div v-if="products.length < 5" class="w-36 shrink-0"></div>
            </div>

            <!-- 收益类型 -->
            <div class="flex border-b border-white/5 hover:bg-white/5 transition-colors">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-surface-dark-highlight p-3 flex items-center text-xs text-gray-400 border-r border-white/5 sticky-left-shadow font-medium">
                收益类型
              </div>
              <div
                v-for="product in products"
                :key="`type-${product.id}`"
                class="w-36 shrink-0 p-3 flex items-center justify-center text-sm font-medium text-white border-r border-white/5"
              >
                {{ product.returnType || '预期收益型' }}
              </div>
              <div v-if="products.length < 5" class="w-36 shrink-0"></div>
            </div>

            <!-- 基本信息 Section -->
            <div class="flex border-b border-white/5 bg-white/5">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-white/5 py-1.5 px-3 flex items-center border-r border-white/5 sticky-left-shadow">
                <svg class="w-[14px] h-[14px] mr-1.5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
                </svg>
                <span class="text-xs font-bold text-gray-200">基本信息</span>
              </div>
              <div class="flex-1"></div>
            </div>

            <!-- 风险等级 -->
            <div class="flex border-b border-white/5 hover:bg-white/5 transition-colors">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-surface-dark-highlight p-3 flex items-center text-xs text-gray-400 border-r border-white/5 sticky-left-shadow font-medium">
                风险等级
              </div>
              <div
                v-for="product in products"
                :key="`risk-${product.id}`"
                class="w-36 shrink-0 p-3 flex items-center justify-center border-r border-white/5"
              >
                <span :class="['px-2 py-0.5 rounded text-xs font-medium', getRiskLevelClass(product.riskLevel)]">
                  {{ getRiskLevelName(product.riskLevel) }}
                </span>
              </div>
              <div v-if="products.length < 5" class="w-36 shrink-0"></div>
            </div>

            <!-- 产品分类 -->
            <div class="flex border-b border-white/5 hover:bg-white/5 transition-colors">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-surface-dark-highlight p-3 flex items-center text-xs text-gray-400 border-r border-white/5 sticky-left-shadow font-medium">
                产品分类
              </div>
              <div
                v-for="product in products"
                :key="`category-${product.id}`"
                class="w-36 shrink-0 p-3 flex items-center justify-center text-sm font-medium text-white border-r border-white/5"
              >
                {{ product.category ? getCategoryName(product.category) : '-' }}
              </div>
              <div v-if="products.length < 5" class="w-36 shrink-0"></div>
            </div>

            <!-- 销售渠道 -->
            <div class="flex border-b border-white/5 hover:bg-white/5 transition-colors">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-surface-dark-highlight p-3 flex items-center text-xs text-gray-400 border-r border-white/5 sticky-left-shadow font-medium">
                销售渠道
              </div>
              <div
                v-for="product in products"
                :key="`channel-${product.id}`"
                class="w-36 shrink-0 p-3 flex items-center justify-center text-sm font-medium text-white border-r border-white/5"
              >
                {{ product.saleChannel || '自营' }}
              </div>
              <div v-if="products.length < 5" class="w-36 shrink-0"></div>
            </div>

            <!-- 交易规则 Section -->
            <div class="flex border-b border-white/5 bg-white/5">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-white/5 py-1.5 px-3 flex items-center border-r border-white/5 sticky-left-shadow">
                <svg class="w-[14px] h-[14px] mr-1.5 text-primary" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
                </svg>
                <span class="text-xs font-bold text-gray-200">交易规则</span>
              </div>
              <div class="flex-1"></div>
            </div>

            <!-- 起购金额 -->
            <div class="flex border-b border-white/5 hover:bg-white/5 transition-colors">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-surface-dark-highlight p-3 flex items-center text-xs text-gray-400 border-r border-white/5 sticky-left-shadow font-medium">
                起购金额
              </div>
              <div
                v-for="product in products"
                :key="`min-${product.id}`"
                :class="[
                  'w-36 shrink-0 p-3 flex items-center justify-center text-sm font-medium border-r border-white/5',
                  isHighlighted(product.minimumInvestment, products.map(p => p.minimumInvestment), false) ? 'font-bold text-green-600 bg-green-50/30' : 'text-white'
                ]"
              >
                {{ formatMinAmount(product.minimumInvestment) }}
              </div>
              <div v-if="products.length < 5" class="w-36 shrink-0"></div>
            </div>

            <!-- 投资单位 -->
            <div class="flex border-b border-white/5 hover:bg-white/5 transition-colors">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-surface-dark-highlight p-3 flex items-center text-xs text-gray-400 border-r border-white/5 sticky-left-shadow font-medium">
                投资单位
              </div>
              <div
                v-for="product in products"
                :key="`unit-${product.id}`"
                class="w-36 shrink-0 p-3 flex items-center justify-center text-sm font-medium text-white border-r border-white/5"
              >
                {{ product.investmentUnit || '1元' }}
              </div>
              <div v-if="products.length < 5" class="w-36 shrink-0"></div>
            </div>

            <!-- 产品状态 -->
            <div class="flex border-b border-white/5 hover:bg-white/5 transition-colors">
              <div class="sticky left-0 z-10 w-24 shrink-0 bg-surface-dark-highlight p-3 flex items-center text-xs text-gray-400 border-r border-white/5 sticky-left-shadow font-medium">
                产品状态
              </div>
              <div
                v-for="product in products"
                :key="`status-${product.id}`"
                class="w-36 shrink-0 p-3 flex items-center justify-center border-r border-white/5"
              >
                <span :class="[
                  'px-2 py-0.5 rounded text-xs font-medium',
                  product.status === 1 ? 'bg-green-100 dark:bg-green-900/40 text-green-600 dark:text-green-400' : 'bg-gray-100 dark:bg-gray-900/40 text-gray-600 dark:text-gray-400'
                ]">
                  {{ product.status === 1 ? '在售' : '停售' }}
                </span>
              </div>
              <div v-if="products.length < 5" class="w-36 shrink-0"></div>
            </div>

            <div class="h-8"></div>
          </div>
        </div>
      </div>
    </main>

    <!-- Bottom Actions -->
    <div class="fixed bottom-0 left-0 w-full bg-[#151e26] border-t border-white/5 pb-safe z-50 p-4 shadow-[0_-4px_6px_-1px_rgba(0,0,0,0.05)]">
      <div class="flex gap-2">
        <button
          class="flex flex-col items-center justify-center px-2 rounded-xl text-gray-400 hover:bg-white/5 transition-colors gap-0.5 min-w-[48px]"
          @click="saveComparison"
        >
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M5 5a2 2 0 012-2h10a2 2 0 012 2v16l-7-3.5L5 21V5z" />
          </svg>
          <span class="text-[10px]">保存</span>
        </button>
        <button
          class="flex flex-col items-center justify-center px-2 rounded-xl text-gray-400 hover:bg-white/5 transition-colors gap-0.5 min-w-[48px]"
          @click="exportComparison"
        >
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M8.684 13.342C8.886 12.938 9 12.482 9 12c0-.482-.114-.938-.316-1.342m0 2.684a3 3 0 110-2.684m0 2.684l6.632 3.316m-6.632-6l6.632-3.316m0 0a3 3 0 105.367-2.684 3 3 0 00-5.367 2.684zm0 9.316a3 3 0 105.368 2.684 3 3 0 00-5.368-2.684z" />
          </svg>
          <span class="text-[10px]">导出</span>
        </button>
        <button
          class="flex flex-col items-center justify-center px-2 rounded-xl text-gray-400 hover:bg-white/5 transition-colors gap-0.5 min-w-[48px]"
          @click="createPortfolio"
        >
          <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z" />
          </svg>
          <span class="text-[10px]">组合</span>
        </button>
        <button
          class="flex-1 flex items-center justify-center gap-2 bg-primary text-white font-semibold rounded-xl text-sm hover:bg-primary-dark transition-colors shadow-lg shadow-primary/30 h-11"
          @click="sendToCustomer"
        >
          <svg class="w-[18px] h-[18px]" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 19l9 2-9-18-9 18 9-2zm0 0v-8" />
          </svg>
          发送对比报告给客户
        </button>
      </div>
    </div>
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

.sticky-left-shadow::after {
  content: '';
  position: absolute;
  top: 0;
  right: 0;
  bottom: 0;
  width: 5px;
  transform: translateX(100%);
  background: linear-gradient(to right, rgba(0,0,0,0.3), transparent);
  pointer-events: none;
}

.custom-scroll::-webkit-scrollbar {
  height: 4px;
}
.custom-scroll::-webkit-scrollbar-track {
  background: transparent;
}
.custom-scroll::-webkit-scrollbar-thumb {
  background-color: rgba(156, 163, 175, 0.3);
  border-radius: 9999px;
}

.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>
