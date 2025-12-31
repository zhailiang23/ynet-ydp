/**
 * 用户状态管理
 */
import { defineStore } from 'pinia'
import { ref } from 'vue'
import * as authApi from '@/api/auth'
import type { LoginParams, UserInfo } from '@/api/auth'

export const useUserStore = defineStore('user', () => {
  // State
  const token = ref<string>(localStorage.getItem('access_token') || '')
  const refreshToken = ref<string>(localStorage.getItem('refresh_token') || '')
  const userInfo = ref<UserInfo | null>(null)
  const tenantId = ref<number>(Number(localStorage.getItem('tenant_id') || '1'))

  // Actions
  const setToken = (newToken: string) => {
    token.value = newToken
    localStorage.setItem('access_token', newToken)
  }

  const setRefreshToken = (newRefreshToken: string) => {
    refreshToken.value = newRefreshToken
    localStorage.setItem('refresh_token', newRefreshToken)
  }

  const clearToken = () => {
    token.value = ''
    refreshToken.value = ''
    localStorage.removeItem('access_token')
    localStorage.removeItem('refresh_token')
  }

  const setUserInfo = (info: UserInfo) => {
    userInfo.value = info
    localStorage.setItem('user_info', JSON.stringify(info))
  }

  const clearUserInfo = () => {
    userInfo.value = null
    localStorage.removeItem('user_info')
  }

  const setTenantId = (id: number) => {
    tenantId.value = id
    localStorage.setItem('tenant_id', id.toString())
  }

  /**
   * 登录
   */
  const login = async (params: LoginParams) => {
    try {
      // 设置租户ID
      if (params.tenantId) {
        setTenantId(params.tenantId)
      }

      // 调用登录接口
      const result = await authApi.login(params)

      // 保存 token
      setToken(result.accessToken)
      setRefreshToken(result.refreshToken)

      // 获取用户信息
      await fetchUserInfo()

      return result
    } catch (error) {
      // 登录失败，清除 token
      clearToken()
      throw error
    }
  }

  /**
   * 获取用户信息
   */
  const fetchUserInfo = async () => {
    try {
      const info = await authApi.getUserInfo()
      setUserInfo(info)
      return info
    } catch (error) {
      console.error('获取用户信息失败:', error)
      throw error
    }
  }

  /**
   * 退出登录
   */
  const logout = async () => {
    try {
      // 调用退出接口
      if (token.value) {
        await authApi.logout()
      }
    } catch (error) {
      console.error('退出登录失败:', error)
    } finally {
      // 清除本地数据
      clearToken()
      clearUserInfo()
    }
  }

  /**
   * 刷新 Token
   */
  const refreshAccessToken = async () => {
    try {
      if (!refreshToken.value) {
        throw new Error('没有 refreshToken')
      }

      const result = await authApi.refreshToken(refreshToken.value)
      setToken(result.accessToken)
      setRefreshToken(result.refreshToken)

      return result
    } catch (error) {
      // 刷新失败，清除 token
      clearToken()
      clearUserInfo()
      throw error
    }
  }

  /**
   * 从本地存储恢复用户信息
   */
  const restoreUserInfo = () => {
    const userInfoStr = localStorage.getItem('user_info')
    if (userInfoStr) {
      try {
        userInfo.value = JSON.parse(userInfoStr)
      } catch (error) {
        console.error('恢复用户信息失败:', error)
      }
    }
  }

  // 初始化时恢复用户信息
  restoreUserInfo()

  return {
    token,
    refreshToken,
    userInfo,
    tenantId,
    setToken,
    setRefreshToken,
    clearToken,
    setUserInfo,
    clearUserInfo,
    setTenantId,
    login,
    fetchUserInfo,
    logout,
    refreshAccessToken,
  }
})
