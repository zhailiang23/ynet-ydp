import { httpClient } from "./request"

export interface VirtualCustomerCreateReq {
  name: string
  gender?: string
  age?: number
  occupation?: string
  industry?: string
  personalityType?: string
  riskPreference?: string
  memo?: string
}

export interface VirtualCustomer {
  id: number
  name: string
  gender?: string
  age?: number
  occupation?: string
  industry?: string
  personalityType?: string
  riskPreference?: string
  memo?: string
  createTime?: string
}

export interface TrainingMaterial {
  id: number
  name: string
  fileUrl?: string
  content?: string
  materialType: string
  description: string
  createTime?: string
}

export interface CreatePersonalizedCourseReq {
  courseName: string
  courseDescription?: string
  marketingLink: string
  createNewCustomer: boolean
  existingCustomerId?: number
  customerInfo?: VirtualCustomerCreateReq
  trainingFile?: File
  trainingRequirements?: string
  caseId?: number
  skillId?: number
}

export interface PersonalizedCourseResp {
  courseId: number
  courseName: string
  courseDescription?: string
  courseType: string
  complexityLevel: string
  status: string
  scriptId: number
  scriptName: string
  scriptVersion: string
  virtualCustomerId: number
  virtualCustomerName: string
  materialId?: number
  materialName?: string
  materialUrl?: string
  contentExtracted: boolean
  createTime: string
  warningMessage?: string
}

/**
 * 创建个性化课程
 */
export async function createPersonalizedCourse(
  data: CreatePersonalizedCourseReq,
): Promise<PersonalizedCourseResp> {
  // 创建 FormData 用于文件上传
  const formData = new FormData()

  // 添加基本字段
  formData.append("courseName", data.courseName)
  if (data.courseDescription) {
    formData.append("courseDescription", data.courseDescription)
  }
  formData.append("marketingLink", data.marketingLink)
  formData.append("createNewCustomer", String(data.createNewCustomer))

  if (data.existingCustomerId) {
    formData.append("existingCustomerId", String(data.existingCustomerId))
  }

  // 添加新客户信息
  if (data.customerInfo) {
    formData.append("customerInfo.name", data.customerInfo.name)
    if (data.customerInfo.gender) {
      formData.append("customerInfo.gender", data.customerInfo.gender)
    }
    if (data.customerInfo.age) {
      formData.append("customerInfo.age", String(data.customerInfo.age))
    }
    if (data.customerInfo.occupation) {
      formData.append("customerInfo.occupation", data.customerInfo.occupation)
    }
    if (data.customerInfo.industry) {
      formData.append("customerInfo.industry", data.customerInfo.industry)
    }
    if (data.customerInfo.personalityType) {
      formData.append("customerInfo.personalityType", data.customerInfo.personalityType)
    }
    if (data.customerInfo.riskPreference) {
      formData.append("customerInfo.riskPreference", data.customerInfo.riskPreference)
    }
    if (data.customerInfo.memo) {
      formData.append("customerInfo.memo", data.customerInfo.memo)
    }
  }

  // 添加培训文件
  if (data.trainingFile) {
    formData.append("trainingFile", data.trainingFile)
  }

  // 添加培训要求
  if (data.trainingRequirements) {
    formData.append("trainingRequirements", data.trainingRequirements)
  }

  // 添加关联案例ID
  if (data.caseId) {
    formData.append("caseId", String(data.caseId))
  }

  // 添加关联销售技巧ID
  if (data.skillId) {
    formData.append("skillId", String(data.skillId))
  }

  const TENANT_ID = process.env.NEXT_PUBLIC_TENANT_ID || "1"

  // 获取token并设置正确的headers
  const headers: Record<string, string> = {
    "tenant-id": TENANT_ID,
  }

  const token = localStorage.getItem("access_token")
  if (token) {
    headers["Authorization"] = `Bearer ${token}`
  }

  // 使用相对路径，让 Next.js 的 rewrites 配置处理代理
  // 使用 AbortController 实现15分钟超时 (AI生成剧本可能需要较长时间)
  const controller = new AbortController()
  const timeoutId = setTimeout(() => controller.abort(), 15 * 60 * 1000) // 15分钟

  try {
    const response = await fetch("/admin-api/aicrm/practice-course/create-personalized", {
      method: "POST",
      headers,
      body: formData,
      signal: controller.signal,
    })

    clearTimeout(timeoutId)

    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }

    const result = await response.json()

    if (result.code !== 0) {
      throw new Error(result.msg || "创建个性化课程失败")
    }

    return result.data
  } catch (error: any) {
    clearTimeout(timeoutId)
    if (error.name === 'AbortError') {
      throw new Error('请求超时,AI生成剧本时间超过15分钟,请稍后重试')
    }
    throw error
  }
}

/**
 * 获取支持的文件类型
 */
export async function getSupportedFileTypes(): Promise<string[]> {
  return httpClient.get<string[]>("/aicrm/practice-course/supported-file-types")
}

/**
 * 获取虚拟客户列表
 */
export async function getVirtualCustomers(): Promise<VirtualCustomer[]> {
  // 使用/page接口获取所有虚拟客户，设置页面大小为100（后端限制最大100）
  const response = await httpClient.get<{
    total: number;
    list: VirtualCustomer[];
  }>("/aicrm/practice-virtual-customer/page?pageNo=1&pageSize=100")
  return response.list || []
}