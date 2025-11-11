import { SiteHeader } from "@/components/site-header"
import { VirtualCustomerCard } from "@/components/virtual-customer-card" // Import the new component name

export default function CoachSelectionPage() {
  const virtualCustomers = [
    {
      id: "customer-001",
      name: "张先生",
      avatarSrc: "/placeholder.svg?height=100&width=100",
      occupation: "企业高管",
      age: 55,
      riskPreference: "稳健型",
      level: "VIP客户",
      personalityTraits: ["理性", "注重细节", "有决策权"],
      description: "一位对投资回报和风险控制有高要求的资深客户。",
    },
    {
      id: "customer-002",
      name: "李女士",
      avatarSrc: "/placeholder.svg?height=100&width=100",
      occupation: "自由职业者",
      age: 30,
      riskPreference: "成长型",
      level: "普通客户",
      personalityTraits: ["犹豫", "对新事物好奇", "易受影响"],
      description: "对理财有初步兴趣，但缺乏经验，需要耐心引导。",
    },
    {
      id: "customer-003",
      name: "王先生",
      avatarSrc: "/placeholder.svg?height=100&width=100",
      occupation: "技术工程师",
      age: 40,
      riskPreference: "激进型",
      level: "高净值客户",
      personalityTraits: ["果断", "追求高收益", "有独立思考"],
      description: "对市场有一定了解，追求高风险高回报的投资机会。",
    },
    {
      id: "customer-004",
      name: "赵女士",
      avatarSrc: "/placeholder.svg?height=100&width=100",
      occupation: "退休教师",
      age: 68,
      riskPreference: "保守型",
      level: "普通客户",
      personalityTraits: ["谨慎", "注重保障", "信任专业人士"],
      description: "希望资产保值增值，对风险非常敏感，注重养老保障。",
    },
  ]

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
        <p className="mb-12 text-center text-lg text-gray-400">选择一位虚拟客户，与他们进行个性化的陪练课程。</p>

        <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3 xl:grid-cols-4">
          {virtualCustomers.map((customer) => (
            <VirtualCustomerCard key={customer.id} {...customer} />
          ))}
        </div>
      </main>
    </div>
  )
}
