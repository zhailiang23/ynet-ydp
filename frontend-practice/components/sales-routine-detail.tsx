"use client"

import type React from "react"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { ArrowLeft } from "lucide-react"
import Link from "next/link"
import { useState } from "react"

interface SalesRoutineDetailProps {
  routineId: string
}

export function SalesRoutineDetail({ routineId }: SalesRoutineDetailProps) {
  // 模拟销售套路/技巧数据
  // 在实际应用中，这里会根据 routineId 从后端获取数据
  const mockSalesRoutines = [
    {
      id: "sr-001",
      name: "三步促成法",
      type: "routine",
      content: `
**第一步：建立信任与关系**
*   主动问候，微笑，眼神交流。
*   寻找共同点，进行简短的寒暄。
*   倾听客户需求，表达理解和同情。

**第二步：价值呈现与解决痛点**
*   清晰介绍产品/服务的核心优势。
*   将产品特点与客户痛点关联，提供解决方案。
*   使用案例或数据支持您的观点。

**第三步：促成与跟进**
*   提出明确的下一步行动建议（如试用、签约、预约）。
*   处理客户异议，提供额外价值。
*   确认客户意向，并及时跟进。
      `,
      complianceRules: "合规规则ID: CR001, CR005",
      productKnowledge: "产品知识ID: PK010, PK012",
    },
    {
      id: "sr-002",
      name: "风险置换话术模板",
      type: "skill",
      content: `
**场景：客户担忧投资风险**

**客户：** "这个产品听起来风险很高，我担心会亏损。"

**销售：** "我非常理解您的担忧，任何投资都伴随风险。但我们并非要您承担额外的风险，而是帮助您将现有资产面临的[某种风险，如通胀风险、单一投资风险]置换为[另一种可控的风险，如市场波动风险]，并通过[多元化配置/专业管理]来降低整体风险。您看，与其让资金躺在银行贬值，不如通过科学的配置，在可控风险下实现增值，您觉得呢？"
      `,
      complianceRules: "合规规则ID: CR002",
      productKnowledge: "产品知识ID: PK005",
    },
  ]

  const routineDetail = mockSalesRoutines.find((r) => r.id === routineId)

  const [isEditing, setIsEditing] = useState(false)
  const [formData, setFormData] = useState(routineDetail || {})

  if (!routineDetail) {
    return (
      <div className="text-center py-20 text-gray-400">
        <p>销售套路/技巧 {routineId} 未找到。</p>
        <Link href="/management/sales-routines">
          <Button variant="link" className="mt-4 text-blue-400 hover:text-blue-300">
            返回套路技巧列表
          </Button>
        </Link>
      </div>
    )
  }

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { id, value } = e.target
    setFormData((prev) => ({ ...prev, [id]: value }))
  }

  const handleSelectChange = (id: string, value: string) => {
    setFormData((prev) => ({ ...prev, [id]: value }))
  }

  const handleSave = () => {
    // 模拟保存操作
    alert("保存成功！在实际应用中，这里会调用后端API更新数据。")
    setIsEditing(false)
    // 实际应用中，这里可能需要刷新数据或更新UI状态
  }

  return (
    <div className="space-y-8">
      <div className="mb-6 flex items-center justify-between">
        <Link href="/management/sales-routines">
          <Button variant="ghost" className="text-gray-300 hover:text-white">
            <ArrowLeft className="mr-2 h-4 w-4" />
            返回套路技巧列表
          </Button>
        </Link>
        <h1 className="text-3xl font-bold text-gray-200">
          {isEditing ? "编辑" : "查看"}套路/技巧：{routineDetail.name}
        </h1>
        <div>
          <Button onClick={() => setIsEditing(!isEditing)} className="bg-blue-600 hover:bg-blue-700 text-white">
            {isEditing ? "取消编辑" : "编辑"}
          </Button>
          {isEditing && (
            <Button onClick={handleSave} className="ml-2 bg-green-600 hover:bg-green-700 text-white">
              保存
            </Button>
          )}
        </div>
      </div>

      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">基本信息</CardTitle>
        </CardHeader>
        <CardContent className="space-y-6">
          <div>
            <Label htmlFor="name" className="text-gray-300">
              套路/技巧名称
            </Label>
            <Input
              id="name"
              value={formData.name}
              onChange={handleInputChange}
              readOnly={!isEditing}
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            />
          </div>
          <div>
            <Label htmlFor="type" className="text-gray-300">
              类型
            </Label>
            <Select
              value={formData.type}
              onValueChange={(value) => handleSelectChange("type", value)}
              disabled={!isEditing}
            >
              <SelectTrigger id="type" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                <SelectValue placeholder="选择类型" />
              </SelectTrigger>
              <SelectContent className="bg-[#2a2a2a] text-white">
                <SelectItem value="routine">销售套路</SelectItem>
                <SelectItem value="skill">销售技巧</SelectItem>
              </SelectContent>
            </Select>
          </div>
          <div>
            <Label htmlFor="content" className="text-gray-300">
              内容/话术模板
            </Label>
            <Textarea
              id="content"
              value={formData.content}
              onChange={handleInputChange}
              readOnly={!isEditing}
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
              rows={10}
            />
          </div>
          <div>
            <Label htmlFor="complianceRules" className="text-gray-300">
              关联合规规则
            </Label>
            <Input
              id="complianceRules"
              value={formData.complianceRules}
              onChange={handleInputChange}
              readOnly={!isEditing}
              placeholder="输入合规规则ID或名称"
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            />
          </div>
          <div>
            <Label htmlFor="productKnowledge" className="text-gray-300">
              关联产品知识
            </Label>
            <Input
              id="productKnowledge"
              value={formData.productKnowledge}
              onChange={handleInputChange}
              readOnly={!isEditing}
              placeholder="输入产品知识ID或名称"
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            />
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
