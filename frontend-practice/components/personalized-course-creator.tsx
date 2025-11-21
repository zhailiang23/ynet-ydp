"use client"

import React, { useState, useEffect } from "react"

import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Dialog, DialogContent, DialogDescription, DialogHeader, DialogTitle } from "@/components/ui/dialog"
import { Play, Upload, Loader2 } from "lucide-react"
import { useRouter } from "next/navigation"
import { createPersonalizedCourse, getVirtualCustomers, type CreatePersonalizedCourseReq, type VirtualCustomer } from "@/lib/api/personalized-course"
import { getDictData, type DictData } from "@/lib/api/dict"
import { getPracticeCasesForSelect, type PracticeCase } from "@/lib/api/practice-case"
import { getPracticeSkillsForSelect, type PracticeSkill } from "@/lib/api/practice-skill"

export function PersonalizedCourseCreator() {
  const router = useRouter()
  const [selectedCustomerType, setSelectedCustomerType] = useState<"existing" | "new">("new")
  const [uploadedFileName, setUploadedFileName] = useState<string | null>(null)
  const [generatedContent, setGeneratedContent] = useState<string>("")
  const [isCreating, setIsCreating] = useState(false)
  const [existingCustomers, setExistingCustomers] = useState<VirtualCustomer[]>([])
  const [selectedCustomerId, setSelectedCustomerId] = useState<number | null>(null)
  const [uploadedFile, setUploadedFile] = useState<File | null>(null)

  // 新增状态变量
  const [marketingSteps, setMarketingSteps] = useState<DictData[]>([])
  const [practiceCases, setPracticeCases] = useState<PracticeCase[]>([])
  const [practiceSkills, setPracticeSkills] = useState<PracticeSkill[]>([])
  const [selectedCaseId, setSelectedCaseId] = useState<number | null>(null)
  const [selectedSkillId, setSelectedSkillId] = useState<number | null>(null)

  // 虚拟客户字典数据
  const [genderOptions, setGenderOptions] = useState<DictData[]>([])
  const [occupationOptions, setOccupationOptions] = useState<DictData[]>([])
  const [industryOptions, setIndustryOptions] = useState<DictData[]>([])
  const [personalityTypeOptions, setPersonalityTypeOptions] = useState<DictData[]>([])
  const [riskPreferenceOptions, setRiskPreferenceOptions] = useState<DictData[]>([])

  // 新客户表单状态
  const [newCustomerGender, setNewCustomerGender] = useState<string>("")
  const [newCustomerOccupation, setNewCustomerOccupation] = useState<string>("")
  const [newCustomerIndustry, setNewCustomerIndustry] = useState<string>("")
  const [newCustomerPersonalityType, setNewCustomerPersonalityType] = useState<string>("")
  const [newCustomerRiskPreference, setNewCustomerRiskPreference] = useState<string>("")

  // 加载初始数据
  useEffect(() => {
    const loadData = async () => {
      try {
        // 并行加载所有数据
        const [customers, steps, cases, skills, gender, occupation, industry, personalityType, riskPreference] = await Promise.allSettled([
          getVirtualCustomers(),
          getDictData('aicrm_marketing_step'),
          getPracticeCasesForSelect(),
          getPracticeSkillsForSelect(),
          getDictData('aicrm_gender'),
          getDictData('aicrm_occupation'),
          getDictData('aicrm_industry'),
          getDictData('aicrm_personality_type'),
          getDictData('aicrm_risk_preference')
        ])

        // 处理虚拟客户数据
        if (customers.status === 'fulfilled') {
          setExistingCustomers(customers.value)
        } else {
          console.error("加载虚拟客户列表失败:", customers.reason)
          setExistingCustomers([
            { id: 1, name: "张先生 (企业高管)" },
            { id: 2, name: "李女士 (自由职业者)" },
          ] as VirtualCustomer[])
        }

        // 处理营销环节数据
        if (steps.status === 'fulfilled') {
          setMarketingSteps(steps.value)
        } else {
          console.error("加载营销环节失败:", steps.reason)
        }

        // 处理案例数据
        if (cases.status === 'fulfilled') {
          setPracticeCases(cases.value)
        } else {
          console.error("加载案例数据失败:", cases.reason)
        }

        // 处理销售技巧数据
        if (skills.status === 'fulfilled') {
          setPracticeSkills(skills.value)
        } else {
          console.error("加载销售技巧数据失败:", skills.reason)
        }

        // 处理虚拟客户字典数据
        if (gender.status === 'fulfilled') {
          setGenderOptions(gender.value)
        }
        if (occupation.status === 'fulfilled') {
          setOccupationOptions(occupation.value)
        }
        if (industry.status === 'fulfilled') {
          setIndustryOptions(industry.value)
        }
        if (personalityType.status === 'fulfilled') {
          setPersonalityTypeOptions(personalityType.value)
        }
        if (riskPreference.status === 'fulfilled') {
          setRiskPreferenceOptions(riskPreference.value)
        }
      } catch (error) {
        console.error("加载数据失败:", error)
      }
    }
    loadData()
  }, [])

  const handleFileUpload = (event: React.ChangeEvent<HTMLInputElement>) => {
    const file = event.target.files?.[0]
    if (file) {
      setUploadedFileName(file.name)
      setUploadedFile(file)
      setGeneratedContent("show") // 只是为了触发textarea显示
    } else {
      setUploadedFileName(null)
      setUploadedFile(null)
      setGeneratedContent("")
    }
  }

  const handleCreateAndStartPersonalizedCourse = async () => {
    setIsCreating(true)

    try {
      // 收集表单数据
      const courseName = (document.getElementById("course-name") as HTMLInputElement)?.value
      const courseDescription = (document.getElementById("course-description") as HTMLTextAreaElement)?.value
      const marketingLink = (document.getElementById("sales-stage") as HTMLSelectElement)?.value || "objection_handling"
      const trainingRequirements = (document.getElementById("training-requirements") as HTMLTextAreaElement)?.value

      if (!courseName) {
        alert("请填写课程名称")
        return
      }

      // 构建请求数据
      const requestData: CreatePersonalizedCourseReq = {
        courseName,
        courseDescription: courseDescription || undefined,
        marketingLink,
        createNewCustomer: selectedCustomerType === "new",
        trainingFile: uploadedFile || undefined,
        trainingRequirements: trainingRequirements || undefined,
        caseId: selectedCaseId || undefined,
        skillId: selectedSkillId || undefined,
      }

      // 如果选择现有客户
      if (selectedCustomerType === "existing" && selectedCustomerId) {
        requestData.existingCustomerId = selectedCustomerId
      }

      // 如果创建新客户
      if (selectedCustomerType === "new") {
        const customerName = (document.getElementById("new-customer-name") as HTMLInputElement)?.value
        const customerAge = (document.getElementById("new-customer-age") as HTMLInputElement)?.value

        if (!customerName) {
          alert("请填写新客户名称")
          return
        }

        requestData.customerInfo = {
          name: customerName,
          gender: newCustomerGender || undefined,
          age: customerAge ? parseInt(customerAge) : undefined,
          occupation: newCustomerOccupation || undefined,
          industry: newCustomerIndustry || undefined,
          personalityType: newCustomerPersonalityType || undefined,
          riskPreference: newCustomerRiskPreference || undefined,
        }
      }

      // 调用API创建个性化课程
      const result = await createPersonalizedCourse(requestData)

      alert(`个性化课程创建成功！课程ID: ${result.courseId}`)

      // 跳转到练习页面
      router.push(`/practice?courseType=personalized&courseId=${result.courseId}&scriptId=${result.scriptId}&customerId=${result.virtualCustomerId}`)

    } catch (error) {
      console.error("创建个性化课程失败:", error)
      alert(`创建失败: ${error instanceof Error ? error.message : "未知错误"}`)
    } finally {
      setIsCreating(false)
    }
  }

  return (
    <>
      {/* Loading Dialog */}
      <Dialog open={isCreating} onOpenChange={() => {}}>
        <DialogContent className="sm:max-w-md bg-[#2a2a2a] border-gray-700 text-white">
          <DialogHeader>
            <DialogTitle className="text-center text-2xl font-bold text-purple-400">
              正在生成剧本
            </DialogTitle>
            <DialogDescription asChild className="text-center text-gray-300 pt-4">
              <div className="flex flex-col items-center justify-center space-y-4">
                <Loader2 className="h-16 w-16 animate-spin text-purple-500" />
                <p className="text-lg">
                  AI 正在根据您的需求生成个性化剧本...
                </p>
                <p className="text-sm text-gray-400">
                  这可能需要一到三分钟，请耐心等待
                </p>
              </div>
            </DialogDescription>
          </DialogHeader>
        </DialogContent>
      </Dialog>

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
              <SelectContent className="bg-[#2a2a2a] text-white border-gray-600">
                {marketingSteps.map((step) => (
                  <SelectItem key={step.value} value={step.value} className="text-white hover:bg-[#3a3a3a] focus:bg-[#3a3a3a]">
                    {step.label}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>
          <div>
            <Label htmlFor="related-case" className="text-gray-300">
              关联案例
            </Label>
            <Select value={selectedCaseId ? String(selectedCaseId) : "none"} onValueChange={(value) => setSelectedCaseId(value === "none" ? null : parseInt(value))}>
              <SelectTrigger id="related-case" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                <SelectValue placeholder="选择关联案例（可选）" />
              </SelectTrigger>
              <SelectContent className="bg-[#2a2a2a] text-white border-gray-600">
                <SelectItem value="none" className="text-white hover:bg-[#3a3a3a] focus:bg-[#3a3a3a]">无关联案例</SelectItem>
                {practiceCases.map((case_) => (
                  <SelectItem key={case_.id} value={String(case_.id)} className="text-white hover:bg-[#3a3a3a] focus:bg-[#3a3a3a]">
                    {case_.title}
                  </SelectItem>
                ))}
              </SelectContent>
            </Select>
          </div>
          <div>
            <Label htmlFor="related-skill" className="text-gray-300">
              关联销售技巧
            </Label>
            <Select value={selectedSkillId ? String(selectedSkillId) : "none"} onValueChange={(value) => setSelectedSkillId(value === "none" ? null : parseInt(value))}>
              <SelectTrigger id="related-skill" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                <SelectValue placeholder="选择关联销售技巧（可选）" />
              </SelectTrigger>
              <SelectContent className="bg-[#2a2a2a] text-white border-gray-600">
                <SelectItem value="none" className="text-white hover:bg-[#3a3a3a] focus:bg-[#3a3a3a]">无关联技巧</SelectItem>
                {practiceSkills.map((skill) => (
                  <SelectItem key={skill.id} value={String(skill.id)} className="text-white hover:bg-[#3a3a3a] focus:bg-[#3a3a3a]">
                    {skill.name}
                  </SelectItem>
                ))}
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
          {(uploadedFileName || generatedContent) && (
            <div className="mt-4">
              <Label htmlFor="training-requirements" className="text-gray-300">
                培训要求
              </Label>
              <Textarea
                id="training-requirements"
                defaultValue=""
                className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
                rows={6}
                placeholder="请输入具体的培训要求和目标..."
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
              <Select onValueChange={(value) => setSelectedCustomerId(parseInt(value))}>
                <SelectTrigger id="existing-customer" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                  <SelectValue placeholder="选择客户" />
                </SelectTrigger>
                <SelectContent className="bg-[#2a2a2a] text-white">
                  {existingCustomers.map((customer) => (
                    <SelectItem key={customer.id} value={String(customer.id)}>
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
                  客户姓名 <span className="text-red-500">*</span>
                </Label>
                <Input
                  id="new-customer-name"
                  placeholder="请输入客户姓名"
                  className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
                />
              </div>
              <div>
                <Label htmlFor="new-customer-gender" className="text-gray-300">
                  性别
                </Label>
                <Select value={newCustomerGender} onValueChange={setNewCustomerGender}>
                  <SelectTrigger id="new-customer-gender" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                    <SelectValue placeholder="请选择性别" />
                  </SelectTrigger>
                  <SelectContent className="bg-[#2a2a2a] text-white border-gray-600">
                    {genderOptions.map((option) => (
                      <SelectItem key={option.value} value={option.value} className="text-white hover:bg-[#3a3a3a] focus:bg-[#3a3a3a]">
                        {option.label}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="new-customer-age" className="text-gray-300">
                  年龄
                </Label>
                <Input
                  id="new-customer-age"
                  type="number"
                  placeholder="请输入年龄"
                  className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
                />
              </div>
              <div>
                <Label htmlFor="new-customer-occupation" className="text-gray-300">
                  职业
                </Label>
                <Select value={newCustomerOccupation} onValueChange={setNewCustomerOccupation}>
                  <SelectTrigger id="new-customer-occupation" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                    <SelectValue placeholder="请选择职业" />
                  </SelectTrigger>
                  <SelectContent className="bg-[#2a2a2a] text-white border-gray-600">
                    {occupationOptions.map((option) => (
                      <SelectItem key={option.value} value={option.value} className="text-white hover:bg-[#3a3a3a] focus:bg-[#3a3a3a]">
                        {option.label}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="new-customer-industry" className="text-gray-300">
                  所属行业
                </Label>
                <Select value={newCustomerIndustry} onValueChange={setNewCustomerIndustry}>
                  <SelectTrigger id="new-customer-industry" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                    <SelectValue placeholder="请选择所属行业" />
                  </SelectTrigger>
                  <SelectContent className="bg-[#2a2a2a] text-white border-gray-600">
                    {industryOptions.map((option) => (
                      <SelectItem key={option.value} value={option.value} className="text-white hover:bg-[#3a3a3a] focus:bg-[#3a3a3a]">
                        {option.label}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="new-customer-personality-type" className="text-gray-300">
                  性格类型
                </Label>
                <Select value={newCustomerPersonalityType} onValueChange={setNewCustomerPersonalityType}>
                  <SelectTrigger id="new-customer-personality-type" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                    <SelectValue placeholder="请选择性格类型" />
                  </SelectTrigger>
                  <SelectContent className="bg-[#2a2a2a] text-white border-gray-600">
                    {personalityTypeOptions.map((option) => (
                      <SelectItem key={option.value} value={option.value} className="text-white hover:bg-[#3a3a3a] focus:bg-[#3a3a3a]">
                        {option.label}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
              <div>
                <Label htmlFor="new-customer-risk-preference" className="text-gray-300">
                  风险偏好
                </Label>
                <Select value={newCustomerRiskPreference} onValueChange={setNewCustomerRiskPreference}>
                  <SelectTrigger id="new-customer-risk-preference" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                    <SelectValue placeholder="请选择风险偏好" />
                  </SelectTrigger>
                  <SelectContent className="bg-[#2a2a2a] text-white border-gray-600">
                    {riskPreferenceOptions.map((option) => (
                      <SelectItem key={option.value} value={option.value} className="text-white hover:bg-[#3a3a3a] focus:bg-[#3a3a3a]">
                        {option.label}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
            </div>
          )}
        </div>
      </CardContent>
      <CardFooter className="flex justify-center border-t border-gray-700 p-6">
        <Button
          onClick={handleCreateAndStartPersonalizedCourse}
          disabled={isCreating}
          className="flex items-center gap-2 rounded-full bg-purple-600 px-8 py-3 text-lg font-medium text-white hover:bg-purple-700 disabled:opacity-50 disabled:cursor-not-allowed"
        >
          {isCreating ? (
            <>
              <Loader2 className="h-5 w-5 animate-spin" />
              创建中...
            </>
          ) : (
            <>
              <Play className="h-5 w-5" />
              创建并开始个性化陪练
            </>
          )}
        </Button>
      </CardFooter>
    </Card>
    </>
  )
}
