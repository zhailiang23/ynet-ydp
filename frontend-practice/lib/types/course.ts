// 课程响应类型
export interface Course {
  id: number
  name: string
  description?: string
  scriptId: number
  scriptName?: string
  scriptVersion?: string
  standard?: number // 1.标准 0.个人
  hard?: number // 1.复杂 0.简单
  status: string
  createTime: string
}

// 分页请求参数
export interface PageParam {
  pageNo: number
  pageSize: number
}

// 分页响应
export interface PageResult<T> {
  list: T[]
  total: number
}

// API响应格式
export interface ApiResponse<T = any> {
  code: number
  data: T
  msg: string
}

// 登录请求
export interface LoginReq {
  username: string
  password: string
}

// 登录响应
export interface LoginResp {
  userId: number
  accessToken: string
  refreshToken: string
  expiresTime: number
}

// 虚拟客户响应类型
export interface VirtualCustomer {
  id: number
  name: string
  gender?: string
  age?: number
  occupation?: string
  industry?: string
  personalityType?: string
  riskPreference?: string
  memo?: string // 描述信息
  createTime?: string
}
