"use client"

import type React from "react"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { ArrowLeft } from "lucide-react"
import Link from "next/link"
import { useState } from "react"
import { Badge } from "@/components/ui/badge"

interface CaseDetailProps {
  caseId: string
}

export function CaseDetail({ caseId }: CaseDetailProps) {
  // 模拟案例详情数据
  // 在实际应用中，这里会根据 caseId 从后端获取数据
  const mockCases = [
    {
      id: "case-001",
      title: "保险理赔争议案例",
      description: "详细描述一起复杂的保险理赔争议，涉及条款解释、责任认定和客户情绪安抚。",
      tags: ["保险", "理赔", "争议", "客户服务"],
    },
    {
      id: "case-002",
      title: "理财暴雷应对案例",
      description: "描述在理财产品出现风险事件时，如何向客户进行风险解释、安抚情绪并提供解决方案。",
      tags: ["理财", "风险", "应对", "危机处理"],
    },
  ]

  const caseDetail = mockCases.find((c) => c.id === caseId)

  const [isEditing, setIsEditing] = useState(false)
  const [formData, setFormData] = useState(caseDetail ? { ...caseDetail, tags: caseDetail.tags.join(", ") } : {})

  if (!caseDetail) {
    return (
      <div className="text-center py-20 text-gray-400">
        <p>案例 {caseId} 未找到。</p>
        <Link href="/management/case-library">
          <Button variant="link" className="mt-4 text-blue-400 hover:text-blue-300">
            返回案例列表
          </Button>
        </Link>
      </div>
    )
  }

  const handleInputChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { id, value } = e.target
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
        <Link href="/management/case-library">
          <Button variant="ghost" className="text-gray-300 hover:text-white">
            <ArrowLeft className="mr-2 h-4 w-4" />
            返回案例列表
          </Button>
        </Link>
        <h1 className="text-3xl font-bold text-gray-200">
          {isEditing ? "编辑" : "查看"}案例：{caseDetail.title}
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
          <CardTitle className="text-xl text-gray-200">案例信息</CardTitle>
        </CardHeader>
        <CardContent className="space-y-6">
          <div>
            <Label htmlFor="title" className="text-gray-300">
              案例标题
            </Label>
            <Input
              id="title"
              value={formData.title}
              onChange={handleInputChange}
              readOnly={!isEditing}
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            />
          </div>
          <div>
            <Label htmlFor="description" className="text-gray-300">
              案例描述
            </Label>
            <Textarea
              id="description"
              value={formData.description}
              onChange={handleInputChange}
              readOnly={!isEditing}
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
              rows={6}
            />
          </div>
          <div>
            <Label htmlFor="tags" className="text-gray-300">
              标签 (逗号分隔)
            </Label>
            <Input
              id="tags"
              value={formData.tags}
              onChange={handleInputChange}
              readOnly={!isEditing}
              placeholder="例如：银行理财, 老年客户, 保本需求"
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            />
            {!isEditing && (
              <div className="mt-2 flex flex-wrap gap-1">
                {caseDetail.tags.map((tag, index) => (
                  <Badge key={index} className="bg-gray-600 text-gray-200">
                    {tag}
                  </Badge>
                ))}
              </div>
            )}
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
