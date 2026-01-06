<script setup lang="ts">
import { RouterView } from 'vue-router'
import { onMounted } from 'vue'
import { useUserStore } from '@/store/user'

const userStore = useUserStore()

// 应用初始化时，如果有 token 但没有用户信息，则获取用户信息
onMounted(async () => {
  if (userStore.token && !userStore.userInfo) {
    try {
      await userStore.fetchUserInfo()
      console.log('用户信息已加载:', userStore.userInfo)
    } catch (error) {
      console.error('获取用户信息失败:', error)
    }
  }
})
</script>

<template>
  <div id="app" class="dark min-h-screen">
    <RouterView />
  </div>
</template>

<style scoped>
#app {
  width: 100%;
  height: 100%;
}
</style>
