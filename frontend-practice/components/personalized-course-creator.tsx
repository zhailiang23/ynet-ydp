"use client"

import type React from "react"

import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Play, Upload } from "lucide-react" // Import Upload icon
import { useRouter } from "next/navigation"
import { useState } from "react"

export function PersonalizedCourseCreator() {
  const router = useRouter()
  const [selectedCustomerType, setSelectedCustomerType] = useState<"existing" | "new">("new")
  const [uploadedFileName, setUploadedFileName] = useState<string | null>(null)
  const [generatedContent, setGeneratedContent] = useState<string>("")

  const existingCustomers = [
    { id: "customer-001", name: "张先生 (企业高管)" },
    { id: "customer-002", name: "李女士 (自由职业者)" },
  ]

  const handleFileUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0]
    if (file) {
      setUploadedFileName(file.name)
      // Simulate file processing and content generation
      setTimeout(() => {
        setGeneratedContent(
          `根据文件 "${file.name}" 生成的训练内容：\n\n这是一个关于产品A的销售培训，重点是介绍其核心优势、目标客户群体以及常见的客户异议处理方法。请学员针对产品A的特点，向一位对价格敏感的客户进行推销。`,
        )
      }, 1500)
    } else {
      setUploadedFileName(null)
      setGeneratedContent("")
    }
  }

  const handleCreateAndStartPersonalizedCourse = () => {
    // In a real application, you would collect all form data (customer + course + generated content)
    // and send it to a backend to create the personalized course and potentially a new virtual customer.
    // Then, navigate to the practice page with the personalized course ID and customer ID.
    alert("个性化课程设计完成！即将开始陪练。")
    router.push("/practice?courseType=personalized") // Simulate starting personalized practice
  }

  return (
    <Card className="w-full max-w-3xl mx-auto border border-gray-700 bg-[#2a2a2a] text-white shadow-lg">
      <CardHeader className="border-b border-gray-700 text-center">
        <CardTitle className="text-3xl font-bold text-purple-400">设计您的个性化课程</CardTitle>
        <p className="text-gray-400 mt-2">结合虚拟客户与定制场景，打造专属陪练体验。</p>
      </CardHeader>
      <CardContent className="p-6 space-y-8">
        {/* 课程基本信息 */}
        <div className="space-y-4">
          <h3 className="text-xl font-semibold text-gray-200">1. 课程基本信息</h3>
          <div>
            <Label htmlFor="course-name" className="text-gray-300">
              课程名称
            </Label>
            <Input
              id="course-name"
              placeholder="例如：高难度客户异议处理实战"
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            />
          </div>
          <div>
            <Label htmlFor="course-description" className="text-gray-300">
              课程描述
            </Label>
            <Textarea
              id="course-description"
              placeholder="详细描述本次个性化课程的目标和内容。"
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
              rows={4}
            />
          </div>
          <div>
            <Label htmlFor="sales-stage" className="text-gray-300">
              主要销售环节
            </Label>
            <Select>
              <SelectTrigger id="sales-stage" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                <SelectValue placeholder="选择销售环节" />
              </SelectTrigger>
              <SelectContent className="bg-[#2a2a2a] text-white">
                <SelectItem value="prospecting">拓客</SelectItem>
                <SelectItem value="needs-analysis">需求挖掘</SelectItem>
                <SelectItem value="objection-handling">异议处理</SelectItem>
                <SelectItem value="closing">促成签约</SelectItem>
              </SelectContent>
            </Select>
          </div>
        </div>

        {/* 上传培训文件部分 */}
        <div className="space-y-4">
          <h3 className="text-xl font-semibold text-gray-200">2. 上传培训文件 (可选)</h3>
          <p className="text-gray-400">系统将根据您提供的文件内容自动生成训练内容。</p>
          <div className="flex items-center gap-3">
            <Label htmlFor="training-file-upload" className="sr-only">
              上传文件
            </Label>
            <Input
              id="training-file-upload"
              type="file"
              className="hidden"
              onChange={handleFileUpload}
              accept=".pdf,.doc,.docx,.txt"
            />
            <Button
              onClick={() => document.getElementById("training-file-upload")?.click()}
              className="flex items-center gap-2 bg-gray-700 text-white hover:bg-gray-600"
            >
              <Upload className="h-4 w-4" />
              选择文件
            </Button>
            {uploadedFileName && <span className="text-gray-300">{uploadedFileName}</span>}
          </div>
          {generatedContent && (
            <div className="mt-4">
              <Label htmlFor="generated-training-content" className="text-gray-300">
                根据文件生成的训练内容
              </Label>
              <Textarea
                id="generated-training-content"
                value={generatedContent}
                readOnly
                className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
                rows={6}
                placeholder="系统将在此处显示根据文件生成的训练内容..."
              />
            </div>
          )}
        </div>

        {/* 虚拟客户选择/创建 */}
        <div className="space-y-4">
          <h3 className="text-xl font-semibold text-gray-200">3. 选择或创建虚拟客户</h3>
          <Select
            value={selectedCustomerType}
            onValueChange={(value: "existing" | "new") => setSelectedCustomerType(value)}
          >
            <SelectTrigger className="w-full border-gray-600 bg-[#1a1a1a] text-white">
              <SelectValue placeholder="选择客户来源" />
            </SelectTrigger>
            <SelectContent className="bg-[#2a2a2a] text-white">
              <SelectItem value="existing">选择现有客户</SelectItem>
              <SelectItem value="new">创建新客户</SelectItem>
            </SelectContent>
          </Select>

          {selectedCustomerType === "existing" && (
            <div className="mt-4">
              <Label htmlFor="existing-customer" className="text-gray-300">
                选择现有虚拟客户
              </Label>
              <Select>
                <SelectTrigger id="existing-customer" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                  <SelectValue placeholder="选择客户" />
                </SelectTrigger>
                <SelectContent className="bg-[#2a2a2a] text-white">
                  {existingCustomers.map((customer) => (
                    <SelectItem key={customer.id} value={customer.id}>
                      {customer.name}
                    </SelectItem>
                  ))}
                </SelectContent>
              </Select>
            </div>
          )}

          {selectedCustomerType === "new" && (
            <div className="mt-4 space-y-4">
              <h4 className="text-lg font-semibold text-gray-300">创建新虚拟客户</h4>
              <div>
                <Label htmlFor="new-customer-name" className="text-gray-300">
                  客户名称
                </Label>
                <Input
                  id="new-customer-name"
                  placeholder="例如：张先生"
                  className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
                />
              </div>
              <div>
                <Label htmlFor="new-customer-occupation" className="text-gray-300">
                  职业
                </Label>
                <Input
                  id="new-customer-occupation"
                  placeholder="例如：企业高管"
                  className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
                />
              </div>
              <div>
                <Label htmlFor="new-customer-age" className="text-gray-300">
                  年龄
                </Label>
                <Input
                  id="new-customer-age"
                  type="number"
                  placeholder="例如：55"
                  className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
                />
              </div>
              <div>
                <Label htmlFor="new-risk-preference" className="text-gray-300">
                  风险偏好
                </Label>
                <Select>
                  <SelectTrigger id="new-risk-preference" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
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
                <Label htmlFor="new-personality-traits" className="text-gray-300">
                  性格特征 (逗号分隔)
                </Label>
                <Input
                  id="new-personality-traits"
                  placeholder="例如：理性, 注重细节"
                  className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
                />
              </div>
              <div>
                <Label htmlFor="new-customer-description" className="text-gray-300">
                  客户背景描述
                </Label>
                <Textarea
                  id="new-customer-description"
                  placeholder="详细描述客户背景和本次陪练的特殊情况。"
                  className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
                  rows={3}
                />
              </div>
            </div>
          )}
        </div>
      </CardContent>
      <CardFooter className="flex justify-center border-t border-gray-700 p-6">
        <Button
          onClick={handleCreateAndStartPersonalizedCourse}
          className="flex items-center gap-2 rounded-full bg-purple-600 px-8 py-3 text-lg font-medium text-white hover:bg-purple-700"
        >
          <Play className="h-5 w-5" />
          创建并开始个性化陪练
        </Button>
      </CardFooter>
    </Card>
  )
}
