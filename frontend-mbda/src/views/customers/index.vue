<script setup lang="ts">
import { ref, computed, onMounted, onUnmounted } from 'vue'
import { useRouter } from 'vue-router'
import { getCustomerPage } from '@/api/customer'
import type { Customer, CustomerType, CustomerStatus } from '@/types/customer'
import BottomNav from '@/components/BottomNav.vue'

const router = useRouter()

// State
const loading = ref(false)
const customers = ref<Customer[]>([])
const total = ref(0)

// 客户类型筛选
const activeType = ref<'all' | CustomerType>('all')
const typeOptions = [
  { label: '全部', value: 'all' as const },
  { label: '零售客户', value: 1 as CustomerType },
  { label: '对公客户', value: 2 as CustomerType },
]

// 客户等级筛选
const activeLevels = ref<Set<string>>(new Set())
const levelOptions = ['normal', 'gold', 'vip', 'diamond']

// 分页
const pageNo = ref(1)
const pageSize = ref(10)
const hasMore = computed(() => customers.value.length < total.value)

// 搜索关键词
const searchKeyword = ref('')

// 加载客户列表
const loadCustomers = async (reset = false) => {
  if (loading.value) return

  if (reset) {
    pageNo.value = 1
    customers.value = []
  }

  loading.value = true
  try {
    const params: any = {
      pageNo: pageNo.value,
      pageSize: pageSize.value,
    }

    // 客户类型筛选
    if (activeType.value !== 'all') {
      params.customerType = activeType.value
    }

    // 客户等级筛选
    if (activeLevels.value.size > 0) {
      // 后端支持单个等级筛选，如果有多个，取第一个
      params.customerLevel = Array.from(activeLevels.value)[0]
    }

    // 搜索关键词
    if (searchKeyword.value) {
      params.customerName = searchKeyword.value
    }

    const result = await getCustomerPage(params)

    if (reset) {
      customers.value = result.list || []
    } else {
      customers.value.push(...(result.list || []))
    }
    total.value = result.total || 0
  } catch (error) {
    console.error('加载客户失败:', error)
  } finally {
    loading.value = false
  }
}

// 切换客户类型
const handleTypeChange = (type: 'all' | CustomerType) => {
  activeType.value = type
  loadCustomers(true)
}

// 切换客户等级
const toggleLevel = (level: string) => {
  // 单选模式
  if (activeLevels.value.has(level)) {
    activeLevels.value.clear()
  } else {
    activeLevels.value.clear()
    activeLevels.value.add(level)
  }
  activeLevels.value = new Set(activeLevels.value) // 触发响应式更新
  loadCustomers(true)
}

// 跳转客户详情
const goToDetail = (customer: Customer) => {
  if (customer.id) {
    router.push(`/customer/${customer.id}`)
  }
}

// 搜索
const handleSearch = () => {
  loadCustomers(true)
}

// 加载更多
const loadMore = () => {
  if (hasMore.value && !loading.value) {
    pageNo.value++
    loadCustomers()
  }
}

// 滚动监听
const handleScroll = () => {
  const scrollTop = window.scrollY || document.documentElement.scrollTop
  const windowHeight = window.innerHeight
  const documentHeight = document.documentElement.scrollHeight

  if (scrollTop + windowHeight >= documentHeight - 100) {
    loadMore()
  }
}

// 获取客户等级标签
const getLevelLabel = (level?: string) => {
  const map: Record<string, string> = {
    // 零售客户等级
    normal: '普通',
    gold: '金卡',
    vip: 'VIP',
    diamond: '钻石',
    // 对公客户等级
    strategic: '战略',
    key: '重点',
    general: '普通',
    important: '重要',
  }
  return level ? map[level] || level : '普通'
}

// 获取客户类型标签
const getTypeLabel = (type: CustomerType) => {
  return type === 1 ? '零售' : '对公'
}

// 获取客户状态标签
const getStatusLabel = (status: CustomerStatus) => {
  const map: Record<CustomerStatus, string> = {
    1: '正常',
    2: '冻结',
    3: '注销',
  }
  return map[status] || '未知'
}

// 初始化
onMounted(() => {
  loadCustomers(true)
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
          客户列表
        </h2>
        <div class="flex items-center gap-3">
          <!-- Add Button -->
          <button class="flex items-center justify-center text-gray-500 dark:text-white hover:bg-gray-200 dark:hover:bg-white/10 rounded-full w-10 h-10 transition-colors">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4" />
            </svg>
          </button>
          <!-- Filter Button -->
          <button class="flex items-center justify-center text-gray-500 dark:text-white hover:bg-gray-200 dark:hover:bg-white/10 rounded-full w-10 h-10 transition-colors">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 4a1 1 0 011-1h16a1 1 0 011 1v2.586a1 1 0 01-.293.707l-6.414 6.414a1 1 0 00-.293.707V17l-4 4v-6.586a1 1 0 00-.293-.707L3.293 7.293A1 1 0 013 6.586V4z" />
            </svg>
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
            placeholder="搜索客户名称..."
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

      <!-- Type Tabs -->
      <div class="w-full overflow-x-auto no-scrollbar border-b border-gray-200 dark:border-[#324d67]">
        <div class="flex px-4 min-w-full">
          <button
            v-for="option in typeOptions"
            :key="option.value"
            class="flex-shrink-0 px-4 py-3 text-sm font-medium transition-colors relative"
            :class="{
              'text-primary dark:text-primary': activeType === option.value,
              'text-gray-500 dark:text-text-secondary hover:text-gray-700 dark:hover:text-white': activeType !== option.value,
            }"
            @click="handleTypeChange(option.value)"
          >
            {{ option.label }}
            <div
              v-if="activeType === option.value"
              class="absolute bottom-0 left-1/2 -translate-x-1/2 h-0.5 w-8 bg-primary rounded-full"
            />
          </button>
        </div>
      </div>

      <!-- Level Filter Tags -->
      <div class="px-4 py-3 flex gap-2 overflow-x-auto no-scrollbar">
        <button
          v-for="level in levelOptions"
          :key="level"
          class="flex-shrink-0 px-3 py-1.5 rounded-full text-xs font-medium transition-all border"
          :class="{
            'bg-primary text-white border-primary': activeLevels.has(level),
            'bg-white dark:bg-[#233648] text-gray-700 dark:text-text-secondary border-gray-200 dark:border-[#324d67] hover:border-primary dark:hover:border-primary': !activeLevels.has(level),
          }"
          @click="toggleLevel(level)"
        >
          {{ getLevelLabel(level) }}
        </button>
      </div>
    </div>

    <!-- Customer List -->
    <div class="flex-1 px-4 py-4">
      <div v-if="loading && customers.length === 0" class="flex justify-center py-12">
        <div class="animate-spin rounded-full h-8 w-8 border-b-2 border-primary"></div>
      </div>

      <div v-else-if="customers.length === 0" class="flex flex-col items-center justify-center py-12 text-gray-400 dark:text-text-secondary">
        <svg class="w-16 h-16 mb-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
          <path stroke-linecap="round" stroke-linejoin="round" stroke-width="1.5" d="M17 20h5v-2a3 3 0 00-5.356-1.857M17 20H7m10 0v-2c0-.656-.126-1.283-.356-1.857M7 20H2v-2a3 3 0 015.356-1.857M7 20v-2c0-.656.126-1.283.356-1.857m0 0a5.002 5.002 0 019.288 0M15 7a3 3 0 11-6 0 3 3 0 016 0zm6 3a2 2 0 11-4 0 2 2 0 014 0zM7 10a2 2 0 11-4 0 2 2 0 014 0z" />
        </svg>
        <p class="text-sm">暂无客户数据</p>
      </div>

      <div v-else class="flex flex-col gap-3">
        <div
          v-for="customer in customers"
          :key="customer.id"
          class="bg-white dark:bg-surface-dark rounded-xl p-4 shadow-sm border border-gray-100 dark:border-white/5 cursor-pointer hover:shadow-md active:scale-[0.99] transition-all"
          @click="goToDetail(customer)"
        >
          <!-- Header: Name and Level Badge -->
          <div class="flex items-start justify-between mb-3">
            <div class="flex items-center gap-3 flex-1 min-w-0">
              <!-- Avatar -->
              <div class="relative shrink-0">
                <div
                  class="bg-gradient-to-br rounded-full h-12 w-12 flex items-center justify-center text-white font-bold text-lg ring-2 ring-white/10"
                  :class="{
                    'from-yellow-400 to-yellow-600': customer.customerLevel === 'diamond',
                    'from-purple-400 to-purple-600': customer.customerLevel === 'vip',
                    'from-blue-400 to-blue-600': customer.customerLevel === 'gold',
                    'from-gray-400 to-gray-600': !customer.customerLevel || customer.customerLevel === 'normal',
                  }"
                >
                  {{ customer.customerName?.charAt(0) }}
                </div>
                <!-- Level Badge -->
                <div
                  v-if="customer.customerLevel"
                  class="absolute -bottom-0.5 -right-0.5 text-[9px] font-bold px-1.5 py-0.5 rounded-full border-2 border-white dark:border-surface-dark"
                  :class="{
                    'bg-gradient-to-br from-yellow-400 to-yellow-600 text-black': customer.customerLevel === 'diamond',
                    'bg-gradient-to-br from-purple-400 to-purple-600 text-white': customer.customerLevel === 'vip',
                    'bg-gradient-to-br from-blue-400 to-blue-600 text-white': customer.customerLevel === 'gold',
                    'bg-gradient-to-br from-gray-400 to-gray-600 text-white': customer.customerLevel === 'normal',
                  }"
                >
                  {{ getLevelLabel(customer.customerLevel) }}
                </div>
              </div>

              <!-- Name and Info -->
              <div class="flex-1 min-w-0">
                <div class="flex items-center gap-2">
                  <h3 class="text-gray-900 dark:text-white font-semibold text-base truncate">
                    {{ customer.customerName }}
                  </h3>
                  <!-- Type Badge -->
                  <span class="shrink-0 px-2 py-0.5 text-[10px] font-medium rounded-full"
                    :class="{
                      'bg-blue-100 dark:bg-blue-900/30 text-blue-700 dark:text-blue-400': customer.customerType === 1,
                      'bg-green-100 dark:bg-green-900/30 text-green-700 dark:text-green-400': customer.customerType === 2,
                    }"
                  >
                    {{ getTypeLabel(customer.customerType) }}
                  </span>
                </div>
                <p class="text-gray-500 dark:text-text-secondary text-xs mt-1">
                  {{ customer.customerNo }}
                </p>
              </div>
            </div>

            <!-- Status Badge -->
            <span
              class="shrink-0 ml-2 px-2 py-1 text-[10px] font-medium rounded-full"
              :class="{
                'bg-green-100 dark:bg-green-900/30 text-green-700 dark:text-green-400': customer.customerStatus === 1,
                'bg-yellow-100 dark:bg-yellow-900/30 text-yellow-700 dark:text-yellow-400': customer.customerStatus === 2,
                'bg-red-100 dark:bg-red-900/30 text-red-700 dark:text-red-400': customer.customerStatus === 3,
              }"
            >
              {{ getStatusLabel(customer.customerStatus) }}
            </span>
          </div>

          <!-- Tags -->
          <div class="flex flex-wrap gap-1.5 mb-3">
            <span
              v-if="customer.isHighQuality"
              class="inline-flex items-center gap-1 px-2 py-1 bg-amber-100 dark:bg-amber-900/30 text-amber-700 dark:text-amber-400 text-[10px] font-medium rounded"
            >
              <svg class="w-3 h-3" fill="currentColor" viewBox="0 0 20 20">
                <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z" />
              </svg>
              优质
            </span>
            <span
              v-if="customer.isImportant"
              class="inline-flex items-center gap-1 px-2 py-1 bg-red-100 dark:bg-red-900/30 text-red-700 dark:text-red-400 text-[10px] font-medium rounded"
            >
              <svg class="w-3 h-3" fill="currentColor" viewBox="0 0 20 20">
                <path fill-rule="evenodd" d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z" clip-rule="evenodd" />
              </svg>
              重要
            </span>
            <span
              v-if="customer.creditLevel"
              class="inline-flex items-center gap-1 px-2 py-1 bg-blue-100 dark:bg-blue-900/30 text-blue-700 dark:text-blue-400 text-[10px] font-medium rounded"
            >
              信用: {{ customer.creditLevel }}
            </span>
            <span
              v-if="customer.customerSource"
              class="inline-flex items-center px-2 py-1 bg-gray-100 dark:bg-gray-800/50 text-gray-700 dark:text-gray-400 text-[10px] font-medium rounded"
            >
              来源: {{ customer.customerSource }}
            </span>
          </div>

          <!-- Credit Score -->
          <div v-if="customer.creditScore" class="flex items-center gap-2 text-xs text-gray-600 dark:text-text-secondary">
            <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12l2 2 4-4M7.835 4.697a3.42 3.42 0 001.946-.806 3.42 3.42 0 014.438 0 3.42 3.42 0 001.946.806 3.42 3.42 0 013.138 3.138 3.42 3.42 0 00.806 1.946 3.42 3.42 0 010 4.438 3.42 3.42 0 00-.806 1.946 3.42 3.42 0 01-3.138 3.138 3.42 3.42 0 00-1.946.806 3.42 3.42 0 01-4.438 0 3.42 3.42 0 00-1.946-.806 3.42 3.42 0 01-3.138-3.138 3.42 3.42 0 00-.806-1.946 3.42 3.42 0 010-4.438 3.42 3.42 0 00.806-1.946 3.42 3.42 0 013.138-3.138z" />
            </svg>
            <span>信用评分: {{ customer.creditScore }}</span>
          </div>

          <!-- Arrow Icon -->
          <div class="absolute top-4 right-4 text-gray-400 dark:text-text-secondary">
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
            </svg>
          </div>
        </div>

        <!-- Loading More Indicator -->
        <div v-if="loading && customers.length > 0" class="flex justify-center py-4">
          <div class="animate-spin rounded-full h-6 w-6 border-b-2 border-primary"></div>
        </div>

        <!-- No More Data -->
        <div v-if="!loading && !hasMore && customers.length > 0" class="text-center py-4 text-sm text-gray-400 dark:text-text-secondary">
          已加载全部客户
        </div>
      </div>
    </div>

    <!-- Bottom Navigation -->
    <BottomNav />
  </div>
</template>

<style scoped>
/* Hide scrollbar for Chrome, Safari and Opera */
.no-scrollbar::-webkit-scrollbar {
  display: none;
}

/* Hide scrollbar for IE, Edge and Firefox */
.no-scrollbar {
  -ms-overflow-style: none;  /* IE and Edge */
  scrollbar-width: none;  /* Firefox */
}
</style>
