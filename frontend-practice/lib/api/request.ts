import type { ApiResponse, LoginResp } from "../types/course"

const API_BASE_URL = process.env.NEXT_PUBLIC_API_BASE_URL || "http://localhost:48080/admin-api"
const TENANT_ID = process.env.NEXT_PUBLIC_TENANT_ID || "1"

// Token 存储 key
const TOKEN_KEY = "access_token"

// 登录锁，防止多个请求同时尝试登录
let loginPromise: Promise<string> | null = null

// 获取 token
export function getToken(): string | null {
  if (typeof window === "undefined") return null
  return localStorage.getItem(TOKEN_KEY)
}

// 设置 token
export function setToken(token: string): void {
  if (typeof window === "undefined") return
  localStorage.setItem(TOKEN_KEY, token)
}

// 清除 token
export function clearToken(): void {
  if (typeof window === "undefined") return
  localStorage.removeItem(TOKEN_KEY)
}

// 自动登录函数
async function autoLogin(): Promise<string> {
  // 如果已经有登录请求在进行中，直接返回该 Promise
  if (loginPromise) {
    return loginPromise
  }

  loginPromise = (async () => {
    try {
      console.log("Token已过期或未登录，自动使用默认账号登录...")
      const response = await fetch(`${API_BASE_URL}/system/auth/login`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          "tenant-id": TENANT_ID,
        },
        body: JSON.stringify({
          username: "admin",
          password: "admin123",
        }),
      })

      const result: ApiResponse<LoginResp> = await response.json()

      if (result.code !== 0) {
        throw new Error(result.msg || "登录失败")
      }

      const token = result.data.accessToken
      setToken(token)
      console.log("自动登录成功！")
      return token
    } finally {
      // 清除登录锁
      loginPromise = null
    }
  })()

  return loginPromise
}

// 获取默认请求头
function getHeaders(): Record<string, string> {
  const headers: Record<string, string> = {
    "Content-Type": "application/json",
    "tenant-id": TENANT_ID,
  }

  const token = getToken()
  if (token) {
    headers["Authorization"] = `Bearer ${token}`
  }

  return headers
}

// HTTP 请求工具
async function request<T = any>(url: string, options: RequestInit = {}, retryCount = 0): Promise<T> {
  const fullUrl = `${API_BASE_URL}${url}`

  const response = await fetch(fullUrl, {
    ...options,
    headers: {
      ...getHeaders(),
      ...options.headers,
    },
  })

  if (!response.ok) {
    throw new Error(`HTTP error! status: ${response.status}`)
  }

  const result: ApiResponse<T> = await response.json()

  // 如果返回401未登录错误，且还没有重试过，则自动登录后重试
  if (result.code === 401 && retryCount === 0) {
    console.log("检测到401错误，尝试自动登录后重试...")
    // 清除旧token
    clearToken()
    // 自动登录获取新token
    await autoLogin()
    // 使用新token重试请求（只重试一次）
    return request<T>(url, options, retryCount + 1)
  }

  if (result.code !== 0) {
    throw new Error(result.msg || "请求失败")
  }

  return result.data
}

export const httpClient = {
  get: <T = any>(url: string, options?: RequestInit) => request<T>(url, { ...options, method: "GET" }),

  post: <T = any>(url: string, data?: any, options?: RequestInit) =>
    request<T>(url, {
      ...options,
      method: "POST",
      body: JSON.stringify(data),
    }),

  put: <T = any>(url: string, data?: any, options?: RequestInit) =>
    request<T>(url, {
      ...options,
      method: "PUT",
      body: JSON.stringify(data),
    }),

  delete: <T = any>(url: string, options?: RequestInit) => request<T>(url, { ...options, method: "DELETE" }),
}
