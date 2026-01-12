<script setup lang="ts">
import { ref, onMounted } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useUserStore } from '@/store/user'
import { getTenantList, type TenantInfo } from '@/api/auth'

const router = useRouter()
const route = useRoute()
const userStore = useUserStore()

// 表单数据
const formData = ref({
  tenantId: 1,
  username: 'admin',
  password: 'admin123'
})

// 租户列表
const tenantList = ref<TenantInfo[]>([])

// 加载状态
const loading = ref(false)

// 错误信息
const errorMessage = ref('')

// 获取租户列表
async function fetchTenantList() {
  try {
    tenantList.value = await getTenantList()
    // 默认选中第一个租户
    if (tenantList.value.length > 0) {
      formData.value.tenantId = tenantList.value[0].id
    }
  } catch (error: any) {
    console.error('获取租户列表失败:', error)
  }
}

// 处理登录
async function handleLogin() {
  errorMessage.value = ''

  // 表单验证
  if (!formData.value.username) {
    errorMessage.value = '请输入用户名'
    return
  }
  if (!formData.value.password) {
    errorMessage.value = '请输入密码'
    return
  }

  try {
    loading.value = true
    await userStore.login(formData.value)

    // 登录成功，跳转到原始路径或首页
    const redirect = route.query.redirect as string
    router.push(redirect || '/')
  } catch (error: any) {
    errorMessage.value = error.message || '登录失败，请检查用户名和密码'
  } finally {
    loading.value = false
  }
}

// 组件挂载时获取租户列表
onMounted(() => {
  fetchTenantList()
})
</script>

<template>
  <div class="min-h-screen flex items-center justify-center bg-gradient-to-br from-blue-50 to-indigo-100 px-4">
    <div class="w-full max-w-md">
      <!-- Logo 和标题 -->
      <div class="text-center mb-8">
        <div class="inline-flex items-center justify-center w-16 h-16 bg-blue-600 rounded-2xl mb-4 shadow-lg">
          <svg class="w-10 h-10 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 11c0 3.517-1.009 6.799-2.753 9.571m-3.44-2.04l.054-.09A13.916 13.916 0 008 11a4 4 0 118 0c0 1.017-.07 2.019-.203 3m-2.118 6.844A21.88 21.88 0 0015.171 17m3.839 1.132c.645-2.266.99-4.659.99-7.132A8 8 0 008 4.07M3 15.364c.64-1.319 1-2.8 1-4.364 0-1.457.39-2.823 1.07-4"></path>
          </svg>
        </div>
        <h1 class="text-3xl font-bold text-gray-900 mb-2">金融产品展业平台</h1>
        <p class="text-gray-600">欢迎登录</p>
      </div>

      <!-- 登录表单卡片 -->
      <div class="bg-white rounded-2xl shadow-xl p-8">
        <form @submit.prevent="handleLogin" class="space-y-6">
          <!-- 租户选择 -->
          <div v-if="tenantList.length > 0">
            <label for="tenantId" class="block text-sm font-medium text-gray-700 mb-2">
              租户
            </label>
            <select
              id="tenantId"
              v-model="formData.tenantId"
              class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all"
            >
              <option v-for="tenant in tenantList" :key="tenant.id" :value="tenant.id">
                {{ tenant.name }}
              </option>
            </select>
          </div>

          <!-- 用户名 -->
          <div>
            <label for="username" class="block text-sm font-medium text-gray-700 mb-2">
              用户名
            </label>
            <input
              id="username"
              v-model="formData.username"
              type="text"
              autocomplete="username"
              placeholder="请输入用户名"
              class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all"
            />
          </div>

          <!-- 密码 -->
          <div>
            <label for="password" class="block text-sm font-medium text-gray-700 mb-2">
              密码
            </label>
            <input
              id="password"
              v-model="formData.password"
              type="password"
              autocomplete="current-password"
              placeholder="请输入密码"
              class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-blue-500 focus:border-transparent outline-none transition-all"
            />
          </div>

          <!-- 错误信息 -->
          <div v-if="errorMessage" class="bg-red-50 border border-red-200 text-red-800 px-4 py-3 rounded-lg text-sm">
            {{ errorMessage }}
          </div>

          <!-- 登录按钮 -->
          <button
            type="submit"
            :disabled="loading"
            class="w-full bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 px-4 rounded-lg transition-colors duration-200 disabled:opacity-50 disabled:cursor-not-allowed shadow-lg"
          >
            <span v-if="!loading">登录</span>
            <span v-else class="flex items-center justify-center">
              <svg class="animate-spin -ml-1 mr-3 h-5 w-5 text-white" fill="none" viewBox="0 0 24 24">
                <circle class="opacity-25" cx="12" cy="12" r="10" stroke="currentColor" stroke-width="4"></circle>
                <path class="opacity-75" fill="currentColor" d="M4 12a8 8 0 018-8V0C5.373 0 0 5.373 0 12h4zm2 5.291A7.962 7.962 0 014 12H0c0 3.042 1.135 5.824 3 7.938l3-2.647z"></path>
              </svg>
              登录中...
            </span>
          </button>
        </form>

        <!-- 提示信息 -->
        <div class="mt-6 text-center text-sm text-gray-500">
          <p>演示账号：admin / admin123</p>
        </div>
      </div>

      <!-- 底部版权信息 -->
      <div class="text-center mt-8 text-sm text-gray-600">
        <p>&copy; 2024 金融产品展业平台. All rights reserved.</p>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* 自定义动画 */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}

.bg-white {
  animation: fadeIn 0.5s ease-out;
}
</style>
