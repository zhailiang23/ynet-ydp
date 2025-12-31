<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import ProductCard from '@/components/ProductCard.vue'
import CarouselCard from '@/components/CarouselCard.vue'
import BottomNav from '@/components/BottomNav.vue'
import { getFinancialProductPage, getCarouselList } from '@/api/financial'
import type { FinancialProduct, CarouselItem } from '@/types/product'

const router = useRouter()

// State
const loading = ref(false)
const products = ref<FinancialProduct[]>([])
const carouselItems = ref<CarouselItem[]>([])
const selectedProductIds = ref<Set<number>>(new Set())
const total = ref(0)

// 分类和筛选
const activeCategory = ref('全部')
const categories = ['全部', '理财', '基金', '保险', '债券', '贵金属']
const categoryMap: Record<string, string> = {
  '理财': 'WEALTH',
  '基金': 'FUND',
  '保险': 'INSURANCE',
  '债券': 'BOND',
  '贵金属': 'PRECIOUS_METAL',
}

// 标签筛选
const activeTags = ref<Set<string>>(new Set(['高收益']))
const availableTags = ['高收益', '低风险', 'AI 推荐', '新品首发', '短期灵活']

// 分页
const pageNo = ref(1)
const pageSize = ref(10)
const hasMore = computed(() => products.value.length < total.value)

// 搜索关键词
const searchKeyword = ref('')

// 加载产品列表
const loadProducts = async (reset = false) => {
  if (loading.value) return

  if (reset) {
    pageNo.value = 1
    products.value = []
  }

  loading.value = true
  try {
    const params: any = {
      pageNo: pageNo.value,
      pageSize: pageSize.value,
      status: 1, // 只显示在售产品
    }

    // 分类筛选
    if (activeCategory.value !== '全部') {
      params.category = categoryMap[activeCategory.value]
    }

    // 标签筛选
    if (activeTags.value.size > 0) {
      params.tags = Array.from(activeTags.value)
    }

    // 搜索关键词
    if (searchKeyword.value) {
      params.keyword = searchKeyword.value
    }

    const result = await getFinancialProductPage(params)

    if (reset) {
      products.value = result.list || []
    } else {
      products.value.push(...(result.list || []))
    }
    total.value = result.total || 0
  } catch (error) {
    console.error('加载产品失败:', error)
  } finally {
    loading.value = false
  }
}

// 加载轮播数据
const loadCarousel = async () => {
  try {
    const items = await getCarouselList()
    // 后端已经过滤了 status=1 的数据，前端无需再过滤
    carouselItems.value = items || []
  } catch (error) {
    console.error('加载轮播失败:', error)
  }
}

// 切换分类
const handleCategoryChange = (category: string) => {
  activeCategory.value = category
  loadProducts(true)
}

// 切换标签
const toggleTag = (tag: string) => {
  if (activeTags.value.has(tag)) {
    activeTags.value.delete(tag)
  } else {
    activeTags.value.add(tag)
  }
  activeTags.value = new Set(activeTags.value) // 触发响应式更新
  loadProducts(true)
}

// 选择/取消选择产品
const toggleSelectProduct = (productId: number) => {
  if (selectedProductIds.value.has(productId)) {
    selectedProductIds.value.delete(productId)
  } else {
    selectedProductIds.value.add(productId)
  }
  selectedProductIds.value = new Set(selectedProductIds.value) // 触发响应式更新
}

// 跳转产品详情
const goToDetail = (product: FinancialProduct) => {
  if (product.id) {
    router.push(`/product/${product.id}`)
  }
}

// 处理轮播点击
const handleCarouselClick = (item: CarouselItem) => {
  if (item.linkType === 'product' && item.linkId) {
    router.push(`/product/${item.linkId}`)
  } else if (item.linkType === 'url' && item.linkUrl) {
    window.open(item.linkUrl, '_blank')
  }
}

// 搜索
const handleSearch = () => {
  loadProducts(true)
}

// 加载更多
const loadMore = () => {
  if (hasMore.value && !loading.value) {
    pageNo.value++
    loadProducts()
  }
}

// 滚动监听（简单实现无限滚动）
const handleScroll = () => {
  const scrollTop = window.scrollY || document.documentElement.scrollTop
  const windowHeight = window.innerHeight
  const documentHeight = document.documentElement.scrollHeight

  if (scrollTop + windowHeight >= documentHeight - 100) {
    loadMore()
  }
}

// 初始化
onMounted(() => {
  loadProducts(true)
  loadCarousel()
  window.addEventListener('scroll', handleScroll)
})

// 清理
onUnmounted(() => {
  window.removeEventListener('scroll', handleScroll)
})
</script>

<template>
  <div class="relative flex flex-col min-h-screen bg-background-light dark:bg-background-dark pb-24">
    <!-- Top App Bar -->
    <div class="sticky top-0 z-50 bg-background-light/95 dark:bg-background-dark/95 backdrop-blur-md transition-colors">
      <!-- Header Row -->
      <div class="flex items-center justify-between px-4 py-3">
        <h2 class="text-gray-900 dark:text-white text-xl font-bold leading-tight tracking-tight">
          产品中心
        </h2>
        <div class="flex items-center gap-3">
          <!-- Scan Button -->
          <button class="flex items-center justify-center text-gray-500 dark:text-white hover:bg-gray-200 dark:hover:bg-white/10 rounded-full w-10 h-10 transition-colors">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v1m6 11h2m-6 0h-2v4m0-11v3m0 0h.01M12 12h4.01M16 20h4M4 12h4m12 0h.01M5 8h2a1 1 0 001-1V5a1 1 0 00-1-1H5a1 1 0 00-1 1v2a1 1 0 001 1zm12 0h2a1 1 0 001-1V5a1 1 0 00-1-1h-2a1 1 0 00-1 1v2a1 1 0 001 1zM5 20h2a1 1 0 001-1v-2a1 1 0 00-1-1H5a1 1 0 00-1 1v2a1 1 0 001 1z" />
            </svg>
          </button>
          <!-- Notification Button -->
          <button class="relative flex items-center justify-center text-gray-500 dark:text-white hover:bg-gray-200 dark:hover:bg-white/10 rounded-full w-10 h-10 transition-colors">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 17h5l-1.405-1.405A2.032 2.032 0 0118 14.158V11a6.002 6.002 0 00-4-5.659V5a2 2 0 10-4 0v.341C7.67 6.165 6 8.388 6 11v3.159c0 .538-.214 1.055-.595 1.436L4 17h5m6 0v1a3 3 0 11-6 0v-1m6 0H9" />
            </svg>
            <span class="absolute top-2 right-2 h-2 w-2 rounded-full bg-red-500 border border-background-dark"></span>
          </button>
        </div>
      </div>

      <!-- Search Bar -->
      <div class="px-4 pb-2">
        <div class="flex w-full items-center rounded-xl bg-white dark:bg-[#233648] shadow-sm border border-gray-100 dark:border-none overflow-hidden h-12">
          <div class="pl-3 pr-2 flex items-center justify-center text-gray-400 dark:text-text-secondary">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
            </svg>
          </div>
          <input
            v-model="searchKeyword"
            class="flex-1 bg-transparent text-gray-900 dark:text-white placeholder:text-gray-400 dark:placeholder:text-text-secondary text-sm font-normal focus:outline-none border-none focus:ring-0 h-full"
            placeholder="搜索产品名称、代码..."
            @keyup.enter="handleSearch"
          />
          <button
            class="pr-3 flex items-center justify-center text-primary"
            @click="handleSearch"
          >
            <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
              <path fill-rule="evenodd" d="M8 4a4 4 0 100 8 4 4 0 000-8zM2 8a6 6 0 1110.89 3.476l4.817 4.817a1 1 0 01-1.414 1.414l-4.816-4.816A6 6 0 012 8z" clip-rule="evenodd" />
            </svg>
          </button>
        </div>
      </div>

      <!-- Category Tabs -->
      <div class="w-full overflow-x-auto no-scrollbar border-b border-gray-200 dark:border-[#324d67]">
        <div class="flex px-4 min-w-full">
          <button
            v-for="category in categories"
            :key="category"
            class="flex flex-col items-center justify-center px-4 py-3 border-b-2 transition-colors whitespace-nowrap"
            :class="activeCategory === category
              ? 'border-primary text-primary'
              : 'border-transparent text-gray-500 dark:text-text-secondary hover:text-gray-700 dark:hover:text-gray-300'"
            @click="handleCategoryChange(category)"
          >
            <p class="text-sm" :class="activeCategory === category ? 'font-bold' : 'font-medium'">
              {{ category }}
            </p>
          </button>
        </div>
      </div>

      <!-- Filter Chips -->
      <div class="w-full overflow-x-auto no-scrollbar bg-background-light dark:bg-background-dark py-3">
        <div class="flex px-4 gap-2">
          <button
            v-for="tag in availableTags"
            :key="tag"
            class="flex items-center justify-center px-3 py-1.5 rounded-lg text-xs font-medium whitespace-nowrap transition-colors"
            :class="activeTags.has(tag)
              ? 'bg-primary text-white shadow-sm'
              : 'bg-white dark:bg-[#233648] text-gray-600 dark:text-text-secondary border border-gray-200 dark:border-transparent'"
            @click="toggleTag(tag)"
          >
            <svg
              v-if="tag.includes('AI')"
              class="w-3.5 h-3.5 mr-1"
              fill="currentColor"
              viewBox="0 0 20 20"
            >
              <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
            </svg>
            {{ tag }}
          </button>
        </div>
      </div>
    </div>

    <!-- Main Content Area -->
    <div class="flex flex-col gap-4 px-4 pt-2">
      <!-- Featured Carousel -->
      <div
        v-if="carouselItems.length > 0"
        class="flex overflow-x-auto no-scrollbar gap-3 pb-2 snap-x"
      >
        <div class="pl-4"></div>
        <CarouselCard
          v-for="item in carouselItems"
          :key="item.id"
          :item="item"
          @click="handleCarouselClick(item)"
        />
        <div class="pr-4"></div>
      </div>

      <!-- Product List -->
      <div class="flex flex-col gap-3">
        <div class="flex justify-between items-end mb-1">
          <h3 class="text-gray-900 dark:text-white font-bold text-base">全部产品</h3>
          <span class="text-gray-500 dark:text-text-secondary text-xs">共 {{ total }} 个产品</span>
        </div>

        <!-- Product Cards -->
        <ProductCard
          v-for="product in products"
          :key="product.id"
          :product="product"
          :selected="selectedProductIds.has(product.id!)"
          @toggle-select="toggleSelectProduct(product.id!)"
          @click="goToDetail(product)"
        />

        <!-- Loading -->
        <div v-if="loading" class="text-center py-4">
          <div class="inline-block animate-spin rounded-full h-8 w-8 border-b-2 border-primary"></div>
          <p class="text-gray-500 dark:text-text-secondary text-sm mt-2">加载中...</p>
        </div>

        <!-- No More -->
        <div v-else-if="!hasMore && products.length > 0" class="text-center py-4">
          <p class="text-gray-500 dark:text-text-secondary text-sm">已加载全部产品</p>
        </div>

        <!-- Empty State -->
        <div v-else-if="products.length === 0 && !loading" class="text-center py-12">
          <svg class="w-16 h-16 mx-auto text-gray-400" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M20 13V6a2 2 0 00-2-2H6a2 2 0 00-2 2v7m16 0v5a2 2 0 01-2 2H6a2 2 0 01-2-2v-5m16 0h-2.586a1 1 0 00-.707.293l-2.414 2.414a1 1 0 01-.707.293h-3.172a1 1 0 01-.707-.293l-2.414-2.414A1 1 0 006.586 13H4" />
          </svg>
          <p class="text-gray-500 dark:text-text-secondary text-sm mt-4">暂无产品数据</p>
        </div>
      </div>
    </div>

    <!-- Floating Compare Button -->
    <div
      v-if="selectedProductIds.size > 0"
      class="fixed bottom-20 right-4 z-40"
    >
      <button
        class="flex items-center gap-2 bg-primary hover:bg-blue-600 text-white px-4 py-3 rounded-full shadow-lg transition-all transform hover:scale-105"
      >
        <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5H7a2 2 0 00-2 2v12a2 2 0 002 2h10a2 2 0 002-2V7a2 2 0 00-2-2h-2M9 5a2 2 0 002 2h2a2 2 0 002-2M9 5a2 2 0 012-2h2a2 2 0 012 2" />
        </svg>
        <span class="font-medium">开始对比 ({{ selectedProductIds.size }})</span>
      </button>
    </div>

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
</style>
