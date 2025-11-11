"use client"

import { Card, CardContent, CardFooter } from "@/components/ui/card"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { useRouter } from "next/navigation"
import { Plus, Play } from "lucide-react"
import { useState } from "react"

interface SelectCustomerForCourseProps {
  courseId: string
}

export function SelectCustomerForCourse({ courseId }: SelectCustomerForCourseProps) {
  const router = useRouter()
  const [showCustomCustomerForm, setShowCustomCustomerForm] = useState(false)

  const virtualCustomers = [
    {
      id: "customer-001",
      name: "张先生",
      avatarSrc: "/placeholder.svg?height=100&width=100",
      occupation: "企业高管",
      age: 55,
      riskPreference: "稳健型",
      level: "VIP客户",
      personalityTraits: ["理性", "注重细节"],
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
      personalityTraits: ["犹豫", "对新事物好奇"],
      description: "对理财有初步兴趣，但缺乏经验，需要耐心引导。",
    },
  ]

  const handleStartPractice = (customerId: string) => {
    // In a real app, you'd pass both courseId and customerId to the practice page
    router.push(`/practice?courseId=${courseId}&customerId=${customerId}`)
  }

  const handleCreateAndStartCustomCustomer = () => {
    // Simulate creating a custom customer and starting practice
    alert("自定义客户创建成功！即将开始陪练。")
    router.push(`/practice?courseId=${courseId}&customerType=custom`)
  }

  return (
    <div className="space-y-8">
      <h2 className="text-2xl font-semibold text-gray-200 text-center">选择现有虚拟客户</h2>
      <div className="grid grid-cols-1 gap-6 sm:grid-cols-2 lg:grid-cols-3">
        {virtualCustomers.map((customer) => (
          <Card
            key={customer.id}
            className="flex flex-col items-center justify-between rounded-lg border border-gray-700 bg-[#2a2a2a] p-6 text-center text-white shadow-lg"
          >
            <div className="flex flex-col items-center">
              <Avatar className="mb-4 h-20 w-20 border-2 border-gray-600">
                <AvatarImage src={customer.avatarSrc || "/placeholder.svg"} alt={customer.name} />
                <AvatarFallback>{customer.name.substring(0, 1)}</AvatarFallback>
              </Avatar>
              <h3 className="mb-2 text-xl font-semibold text-gray-200">{customer.name}</h3>
              <div className="mb-1 text-sm text-gray-400">
                {customer.occupation} | {customer.age}岁 | {customer.riskPreference}
              </div>
              <div className="mb-3 flex flex-wrap justify-center gap-2">
                <Badge className="bg-green-600 text-white">{customer.level}</Badge>
                {customer.personalityTraits.map((trait, index) => (
                  <Badge key={index} className="bg-blue-600 text-white">
                    {trait}
                  </Badge>
                ))}
              </div>
              <p className="text-sm text-gray-400">{customer.description}</p>
            </div>
            <CardFooter className="mt-6 w-full p-0">
              <Button
                onClick={() => handleStartPractice(customer.id)}
                className="w-full rounded-full bg-purple-600 text-white hover:bg-purple-700"
              >
                选择此客户并开始
              </Button>
            </CardFooter>
          </Card>
        ))}
      </div>

      <div className="relative flex items-center py-5">
        <div className="flex-grow border-t border-gray-700" />
        <span className="mx-4 flex-shrink text-lg font-semibold text-gray-500">或</span>
        <div className="flex-grow border-t border-gray-700" />
      </div>

      <h2 className="text-2xl font-semibold text-gray-200 text-center">自定义虚拟客户</h2>
      <p className="text-gray-400 text-center mb-6">为本次陪练会话临时创建一个新的虚拟客户。</p>

      {!showCustomCustomerForm ? (
        <div className="flex justify-center">
          <Button
            onClick={() => setShowCustomCustomerForm(true)}
            className="flex items-center gap-2 rounded-full bg-blue-600 px-6 py-3 text-white hover:bg-blue-700"
          >
            <Plus className="h-5 w-5" />
            创建临时虚拟客户
          </Button>
        </div>
      ) : (
        <Card className="w-full max-w-2xl mx-auto border border-gray-700 bg-[#2a2a2a] text-white shadow-lg">
          <CardContent className="p-6 space-y-4">
            <div>
              <Label htmlFor="temp-customer-name" className="text-gray-300">
                客户名称
              </Label>
              <Input
                id="temp-customer-name"
                placeholder="例如：张先生"
                className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
              />
            </div>
            <div>
              <Label htmlFor="temp-customer-occupation" className="text-gray-300">
                职业
              </Label>
              <Input
                id="temp-customer-occupation"
                placeholder="例如：企业高管"
                className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
              />
            </div>
            <div>
              <Label htmlFor="temp-customer-age" className="text-gray-300">
                年龄
              </Label>
              <Input
                id="temp-customer-age"
                type="number"
                placeholder="例如：55"
                className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
              />
            </div>
            <div>
              <Label htmlFor="temp-risk-preference" className="text-gray-300">
                风险偏好
              </Label>
              <Select>
                <SelectTrigger id="temp-risk-preference" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                  <SelectValue placeholder="选择风险偏好" />
                </SelectTrigger>
                <SelectContent className="bg-[#2a2a2a] text-white">
                  <SelectItem value="low">保守型</SelectItem>
                  <SelectItem value="medium">稳健型</SelectItem>
                  <SelectItem value="high">激进型</SelectItem>
                </SelectContent>
              </Select>
            </div>
            <div>
              <Label htmlFor="temp-personality-traits" className="text-gray-300">
                性格特征 (逗号分隔)
              </Label>
              <Input
                id="temp-personality-traits"
                placeholder="例如：理性, 注重细节"
                className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
              />
            </div>
            <div>
              <Label htmlFor="temp-description" className="text-gray-300">
                客户背景描述
              </Label>
              <Textarea
                id="temp-description"
                placeholder="详细描述客户背景和本次陪练的特殊情况。"
                className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
                rows={3}
              />
            </div>
            <Button
              onClick={handleCreateAndStartCustomCustomer}
              className="flex items-center gap-2 rounded-full bg-purple-600 px-6 py-3 text-white hover:bg-purple-700"
            >
              <Play className="h-5 w-5" />
              创建并开始陪练
            </Button>
          </CardContent>
        </Card>
      )}
    </div>
  )
}
