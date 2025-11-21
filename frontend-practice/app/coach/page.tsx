"use client"

import { useEffect, useState } from "react"
import { SiteHeader } from "@/components/site-header"
import { VirtualCustomerCard } from "@/components/virtual-customer-card"
import { getVirtualCustomerList } from "@/lib/api/virtual-customer"
import type { VirtualCustomer } from "@/lib/types/course"

// 风险偏好映射
const riskPreferenceMap: Record<string, string> = {
  conservative: "保守型",
  moderate: "稳健型",
  growth: "成长型",
  aggressive: "激进型",
}

// 性格类型映射
const personalityTypeMap: Record<string, string> = {
  rational: "理性",
  emotional: "感性",
  decisive: "果断",
  hesitant: "犹豫",
  innovative: "创新",
  traditional: "传统",
}

// 职业映射
const occupationMap: Record<string, string> = {
  employee: "企业职员",
  business_owner: "企业主",
  professional: "专业人士",
  freelancer: "自由职业者",
  teacher: "教师",
  doctor: "医生",
  engineer: "工程师",
  designer: "设计师",
  retired: "退休人员",
  other: "其他",
}

// 将后端数据转换为前端组件需要的格式
function transformVirtualCustomer(customer: VirtualCustomer) {
  // 构建性格特征数组
  const personalityTraits: string[] = []
  if (customer.personalityType) {
    const trait = personalityTypeMap[customer.personalityType] || customer.personalityType
    personalityTraits.push(trait)
  }
  if (customer.riskPreference) {
    const risk = riskPreferenceMap[customer.riskPreference]
    if (risk) personalityTraits.push(risk)
  }

  return {
    id: customer.id.toString(),
    name: customer.name,
    avatarSrc: "/placeholder.svg?height=100&width=100",
    occupation: customer.occupation ? occupationMap[customer.occupation] || customer.occupation : "未设置",
    age: customer.age || 0,
    riskPreference: customer.riskPreference
      ? riskPreferenceMap[customer.riskPreference] || customer.riskPreference
      : "未设置",
    level: "虚拟客户", // 后端没有这个字段,使用固定值
    personalityTraits,
    description: customer.memo || "一位需要专业理财服务的客户。",
  }
}

export default function CoachSelectionPage() {
  const [virtualCustomers, setVirtualCustomers] = useState<any[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    const loadVirtualCustomers = async () => {
      try {
        setLoading(true)
        setError(null)
        const result = await getVirtualCustomerList({ pageNo: 1, pageSize: 100 })
        const transformedCustomers = result.list.map(transformVirtualCustomer)
        setVirtualCustomers(transformedCustomers)
      } catch (err) {
        console.error("Failed to load virtual customers:", err)
        setError("加载虚拟客户失败,请稍后重试")
      } finally {
        setLoading(false)
      }
    }

    loadVirtualCustomers()
  }, [])

  return (
    <div
      className="min-h-screen bg-[#1a1a1a] text-white"
      style={{
        backgroundImage:
          "linear-gradient(to right, #2a2a2a 1px, transparent 1px), linear-gradient(to bottom, #2a2a2a 1px, transparent 1px)",
        backgroundSize: "40px 40px",
      }}
    >
      <SiteHeader />
      <main className="container mx-auto px-4 py-8 md:px-6 lg:px-8">
        <h1 className="mb-8 text-center text-3xl font-bold text-gray-200">选择您的虚拟客户</h1>
        <p className="mb-12 text-center text-lg text-gray-400">选择一位虚拟客户,与他们进行个性化的陪练课程。</p>

        {loading && (
          <div className="flex justify-center items-center py-20">
            <div className="text-white text-lg">加载中...</div>
          </div>
        )}

        {error && (
          <div className="flex justify-center items-center py-20">
            <div className="text-red-500 text-lg">{error}</div>
          </div>
        )}

        {!loading && !error && (
          <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
            {virtualCustomers.map((customer) => (
              <VirtualCustomerCard key={customer.id} {...customer} />
            ))}
          </div>
        )}

        {!loading && !error && virtualCustomers.length === 0 && (
          <div className="flex justify-center items-center py-20">
            <div className="text-gray-400 text-lg">暂无虚拟客户数据</div>
          </div>
        )}
      </main>
    </div>
  )
}
