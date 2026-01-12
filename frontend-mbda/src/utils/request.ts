import axios, { AxiosInstance, AxiosRequestConfig, AxiosResponse, AxiosError } from 'axios'

// 创建 axios 实例
const request: AxiosInstance = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_URL || 'http://192.168.169.124:48080',
  timeout: 30000,
  headers: {
    'Content-Type': 'application/json;charset=UTF-8',
  },
})

// 请求拦截器
request.interceptors.request.use(
  (config) => {
    // 添加租户ID
    const tenantId = import.meta.env.VITE_APP_TENANT_ID || '1'
    if (config.headers) {
      config.headers['tenant-id'] = tenantId
    }

    // 添加 token（移动端和管理后台都需要）
    const token = localStorage.getItem('access_token')
    if (token && config.headers) {
      config.headers['Authorization'] = `Bearer ${token}`
    }

    return config
  },
  (error: AxiosError) => {
    console.error('请求错误:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  (response: AxiosResponse) => {
    const res = response.data

    // 如果返回的是 Blob 类型（文件下载），直接返回
    if (response.config.responseType === 'blob') {
      return response
    }

    // 后端统一返回格式：{ code: 0, data: any, msg: string }
    if (res.code === 0) {
      return res.data
    } else {
      // 业务错误
      const errorMsg = res.msg || '请求失败'
      console.error('业务错误:', errorMsg)

      // 可以根据 code 做特殊处理
      if (res.code === 401) {
        // Token 过期，清除本地存储
        localStorage.removeItem('access_token')
        // 跳转到登录页（这里简化处理）
        console.warn('Token 已过期，请重新登录')
      }

      return Promise.reject(new Error(errorMsg))
    }
  },
  (error: AxiosError) => {
    console.error('响应错误:', error)

    let errorMsg = '网络请求失败'

    if (error.response) {
      switch (error.response.status) {
        case 401:
          errorMsg = '未授权，请重新登录'
          localStorage.removeItem('access_token')
          break
        case 403:
          errorMsg = '拒绝访问'
          break
        case 404:
          errorMsg = '请求地址不存在'
          break
        case 500:
          errorMsg = '服务器内部错误'
          break
        default:
          errorMsg = `连接错误 ${error.response.status}`
      }
    } else if (error.request) {
      errorMsg = '网络连接失败'
    } else {
      errorMsg = error.message || '请求配置错误'
    }

    return Promise.reject(new Error(errorMsg))
  }
)

export default request
