<script setup lang="ts">
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { searchCustomer } from '@/api/customer'

const router = useRouter()

// 表单数据
const phone = ref('')
const idNumber = ref('')

// 加载状态
const loading = ref(false)

// 返回上一页
const handleBack = () => {
  router.back()
}

// 查看历史记录
const handleHistory = () => {
  console.log('查看历史记录')
  // TODO: 跳转到历史记录页面
}

// 扫描证件/银行卡
const handleScan = () => {
  console.log('启动扫描')
  // TODO: 调用摄像头扫描功能
}

// 拍照识别
const handleCamera = () => {
  console.log('启动拍照')
  // TODO: 调用摄像头拍照功能
}

// 立即查询
const handleSearch = async () => {
  // 验证输入
  if (!phone.value && !idNumber.value) {
    alert('请输入手机号或证件号码')
    return
  }

  try {
    loading.value = true

    // 调用客户识别 API
    const customer = await searchCustomer({
      mobile: phone.value || undefined,
      idCardNo: idNumber.value || undefined,
    })

    if (customer && customer.id) {
      // 找到客户，跳转到客户详情页
      router.push({
        name: 'CustomerDetail',
        params: { id: customer.id },
      })
    } else {
      // 未找到客户，显示创建提示
      const shouldCreate = confirm(
        '未找到该客户信息，是否创建新客户档案？'
      )

      if (shouldCreate) {
        // TODO: 跳转到创建客户页面，并预填手机号和证件号
        alert('创建客户功能开发中...')
      }
    }
  } catch (error) {
    console.error('查询客户失败:', error)
    alert('查询失败，请稍后重试')
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="relative flex h-full min-h-screen w-full flex-col overflow-x-hidden pb-24 bg-background-dark">
    <!-- Header -->
    <header class="sticky top-0 z-20 bg-background-dark/95 backdrop-blur-md border-b border-white/5">
      <div class="flex items-center justify-between p-4">
        <button
          class="flex items-center justify-center rounded-full size-10 text-text-secondary hover:bg-surface-dark-highlight transition-colors"
          @click="handleBack"
        >
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
          </svg>
        </button>
        <h1 class="text-base font-bold text-white">客户识别</h1>
        <button
          class="flex items-center justify-center rounded-full size-10 text-text-secondary hover:bg-surface-dark-highlight transition-colors"
          @click="handleHistory"
        >
          <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
        </button>
      </div>
    </header>

    <main class="flex flex-col gap-6 px-4 pt-6">
      <!-- 扫描证件/银行卡 -->
      <section>
        <button
          class="relative w-full overflow-hidden rounded-2xl bg-gradient-to-br from-primary to-[#0a4d91] shadow-xl shadow-primary/20 text-white group cursor-pointer active:scale-[0.99] transition-transform"
          @click="handleScan"
        >
          <div class="absolute top-0 right-0 -mr-12 -mt-12 size-48 rounded-full bg-white/10 blur-3xl"></div>
          <div class="absolute bottom-0 left-0 -ml-12 -mb-12 size-40 rounded-full bg-purple-500/30 blur-3xl"></div>
          <div class="relative p-6 flex flex-col items-center justify-center gap-4 py-8">
            <div class="size-16 rounded-2xl bg-white/20 backdrop-blur-md flex items-center justify-center border border-white/20 shadow-inner group-hover:bg-white/30 transition-colors">
              <svg class="w-9 h-9" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v1m6 11h2m-6 0h-2v4m0-11v3m0 0h.01M12 12h4.01M16 20h4M4 12h4m12 0h.01M5 8h2a1 1 0 001-1V5a1 1 0 00-1-1H5a1 1 0 00-1 1v2a1 1 0 001 1zm12 0h2a1 1 0 001-1V5a1 1 0 00-1-1h-2a1 1 0 00-1 1v2a1 1 0 001 1zM5 20h2a1 1 0 001-1v-2a1 1 0 00-1-1H5a1 1 0 00-1 1v2a1 1 0 001 1z" />
              </svg>
            </div>
            <div class="text-center">
              <h2 class="text-xl font-bold mb-1">扫描证件 / 银行卡</h2>
              <p class="text-blue-100 text-sm">AI 自动识别信息，秒级调取客户画像</p>
            </div>
          </div>
        </button>
      </section>

      <!-- 分隔线 -->
      <div class="relative flex items-center py-2">
        <div class="grow border-t border-white/10"></div>
        <span class="shrink-0 mx-4 text-xs font-medium text-gray-400 uppercase tracking-wider">或手动输入</span>
        <div class="grow border-t border-white/10"></div>
      </div>

      <!-- 手动输入表单 -->
      <section class="flex flex-col gap-4">
        <div class="rounded-2xl bg-surface-dark-highlight p-5 shadow-sm border border-white/5">
          <div class="flex flex-col gap-5">
            <!-- 手机号码 -->
            <div class="space-y-1.5">
              <label class="text-xs font-semibold text-text-secondary ml-1">手机号码</label>
              <div class="relative group">
                <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none text-gray-400 group-focus-within:text-primary transition-colors">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 18h.01M8 21h8a2 2 0 002-2V5a2 2 0 00-2-2H8a2 2 0 00-2 2v14a2 2 0 002 2z" />
                  </svg>
                </div>
                <input
                  v-model="phone"
                  class="block w-full rounded-xl border-white/10 bg-black/20 pl-10 pr-3 py-3 text-sm font-medium text-white placeholder:text-gray-400 focus:border-primary focus:ring-primary focus:ring-1 transition-all focus:outline-none"
                  placeholder="请输入客户手机号"
                  type="tel"
                />
              </div>
            </div>

            <!-- 身份证号/银行卡号 -->
            <div class="space-y-1.5">
              <label class="text-xs font-semibold text-text-secondary ml-1">身份证号 / 银行卡号</label>
              <div class="relative group">
                <div class="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none text-gray-400 group-focus-within:text-primary transition-colors">
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 6H5a2 2 0 00-2 2v9a2 2 0 002 2h14a2 2 0 002-2V8a2 2 0 00-2-2h-5m-4 0V5a2 2 0 114 0v1m-4 0a2 2 0 104 0m-5 8a2 2 0 100-4 2 2 0 000 4zm0 0c1.306 0 2.417.835 2.83 2M9 14a3.001 3.001 0 00-2.83 2M15 11h3m-3 4h2" />
                  </svg>
                </div>
                <input
                  v-model="idNumber"
                  class="block w-full rounded-xl border-white/10 bg-black/20 pl-10 pr-12 py-3 text-sm font-medium text-white placeholder:text-gray-400 focus:border-primary focus:ring-primary focus:ring-1 transition-all focus:outline-none"
                  placeholder="请输入证件号码"
                  type="text"
                />
                <button
                  class="absolute inset-y-0 right-0 pr-3 flex items-center cursor-pointer text-gray-400 hover:text-primary transition-colors"
                  @click="handleCamera"
                >
                  <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M3 9a2 2 0 012-2h.93a2 2 0 001.664-.89l.812-1.22A2 2 0 0110.07 4h3.86a2 2 0 011.664.89l.812 1.22A2 2 0 0018.07 7H19a2 2 0 012 2v9a2 2 0 01-2 2H5a2 2 0 01-2-2V9z" />
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 13a3 3 0 11-6 0 3 3 0 016 0z" />
                  </svg>
                </button>
              </div>
            </div>
          </div>

          <!-- 查询按钮 -->
          <div class="mt-6">
            <button
              class="w-full flex items-center justify-center gap-2 rounded-xl bg-primary text-white py-3.5 font-bold shadow-lg shadow-primary/20 hover:bg-primary-dark active:scale-[0.98] transition-all disabled:opacity-50 disabled:cursor-not-allowed"
              :disabled="loading"
              @click="handleSearch"
            >
              <!-- Loading 动画 -->
              <svg v-if="loading" class="animate-spin h-5 w-5" xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              <!-- 搜索图标 -->
              <svg v-else class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z" />
              </svg>
              {{ loading ? '查询中...' : '立即查询' }}
            </button>
          </div>
        </div>
      </section>

      <!-- 操作指引 -->
      <section class="px-2">
        <div class="flex items-start gap-2 p-3 rounded-lg bg-blue-500/10 border border-blue-500/20">
          <svg class="w-[18px] h-[18px] text-primary mt-0.5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 16h-1v-4h-1m1-4h.01M21 12a9 9 0 11-18 0 9 9 0 0118 0z" />
          </svg>
          <div class="flex flex-col gap-1">
            <h4 class="text-xs font-bold text-slate-200">操作指引</h4>
            <p class="text-[11px] leading-relaxed text-slate-400">
              请输入准确的客户信息以获取完整的画像分析。对于新客户，系统将自动引导至建档流程。所有查询记录将自动同步至 CRM 系统。
            </p>
          </div>
        </div>
      </section>
    </main>
  </div>
</template>

<style scoped>
/* 自定义样式 */
</style>
