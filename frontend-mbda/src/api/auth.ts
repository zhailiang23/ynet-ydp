/**
 * 认证相关 API
 */
import request from '@/utils/request'

/** 登录接口参数 */
export interface LoginParams {
  username: string
  password: string
  tenantId?: number
}

/** 登录接口返回值 */
export interface LoginResult {
  accessToken: string
  refreshToken: string
  userId: number
  expiresTime: number
}

/** 用户信息 */
export interface UserInfo {
  id: number
  username: string
  nickname: string
  avatar?: string
  email?: string
  mobile?: string
  deptId?: number
  postIds?: number[]
  roles?: any[]
  permissions?: string[]
}

/** 租户信息 */
export interface TenantInfo {
  id: number
  name: string
}

/**
 * 登录
 */
export function login(data: LoginParams): Promise<LoginResult> {
  return request({
    url: '/admin-api/system/auth/login',
    method: 'post',
    data,
  })
}

/**
 * 退出登录
 */
export function logout(): Promise<void> {
  return request({
    url: '/admin-api/system/auth/logout',
    method: 'post',
  })
}

/**
 * 获取用户信息和权限
 */
export function getUserInfo(): Promise<UserInfo> {
  return request({
    url: '/admin-api/system/auth/get-permission-info',
    method: 'get',
  })
}

/**
 * 刷新 Token
 */
export function refreshToken(refreshToken: string): Promise<LoginResult> {
  return request({
    url: `/admin-api/system/auth/refresh-token?refreshToken=${refreshToken}`,
    method: 'post',
  })
}

/**
 * 获取租户列表
 */
export function getTenantList(): Promise<TenantInfo[]> {
  return request({
    url: '/admin-api/system/tenant/simple-list',
    method: 'get',
  })
}
