"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Separator } from "@/components/ui/separator"
import { Button } from "@/components/ui/button"
import { ArrowLeft } from "lucide-react"
import Link from "next/link"

interface CourseContentDetailProps {
  courseId: string
}

export default function CourseContentDetail({ courseId }: CourseContentDetailProps) {
  // Mock data for course content based on the provided structure
  // In a real application, you would fetch this data based on courseId
  const courseContent = {
    "course-001": {
      title: "高价值理财产品推荐",
      sections: [
        {
          title: "一、剧本核心框架阶段",
          subsections: [
            {
              title: "目标",
              content: "提升客户经理在理财产品推荐中的专业性和转化率。",
            },
            {
              title: "关键动作",
              content: "破冰、需求挖掘、方案呈现。",
            },
            {
              title: "风险控制点",
              content: "避免过度承诺、规避隐私敏感问题、明确风险提示。",
            },
          ],
          details: [
            {
              heading: "破冰",
              items: ["建立信任", "① 生活场景切入", "② 共情市场担忧", "避免过度承诺"],
            },
            {
              heading: "需求挖掘",
              items: ["定位痛点", "① 收入波动分析", "② 资金沉淀诊断", "规避隐私敏感问题"],
            },
            {
              heading: "方案呈现",
              items: ["消除疑虑", "① 可视化收益演示", "② 阶梯式产品组合", "明确风险提示"],
            },
          ],
        },
        {
          title: "二、场景模拟演练",
          details: [
            {
              heading: "场景1：电话邀约",
              items: ["模拟电话沟通，练习邀约技巧。"],
            },
            {
              heading: "场景2：面谈促成",
              items: [
                "1. 需求确认：再次确认客户需求，确保方案匹配。",
                "2. 方案演示：详细演示产品方案，解答客户疑问。",
                "3. 异议处理：针对客户异议提供应对策略。",
                "应对策略：",
                "▸ 限时方案：'本周签约可锁定当前收益率'",
                "▸ 降低门槛：'支持1000元起投随时调整'",
              ],
            },
          ],
        },
        {
          title: "三、培训考核指标",
          details: [
            {
              heading: "",
              items: ["✅ 话术合规率 ≥98%", "✅ 客户需求诊断准确率 ≥85%", "✅ 首次促成转化率 ≥40%"],
            },
          ],
        },
      ],
    },
    // Add more course content here if needed for other course IDs
  }

  // Use a default course ID for demonstration if the provided one doesn't match mock data
  const currentCourse = courseContent[courseId as keyof typeof courseContent] || courseContent["course-001"]

  if (!currentCourse) {
    return (
      <div className="text-center py-20 text-gray-400">
        <p>课程内容 {courseId} 未找到。</p>
        <Link href="/course">
          <Button variant="link" className="mt-4 text-blue-400 hover:text-blue-300">
            返回课程列表
          </Button>
        </Link>
      </div>
    )
  }

  return (
    <div className="space-y-8">
      <div className="mb-6 flex items-center justify-between">
        <Link href="/course">
          <Button variant="ghost" className="text-gray-300 hover:text-white">
            <ArrowLeft className="mr-2 h-4 w-4" />
            返回课程列表
          </Button>
        </Link>
        <h1 className="text-3xl font-bold text-gray-200">课程详情：{currentCourse.title}</h1>
        <div></div> {/* Spacer for alignment */}
      </div>

      <Card className="w-full max-w-4xl mx-auto bg-[#2a2a2a] text-white border border-gray-700 shadow-lg">
        <CardHeader>
          <CardTitle className="text-3xl font-bold text-center text-blue-400">{currentCourse.title}</CardTitle>
        </CardHeader>
        <CardContent className="space-y-8 p-6">
          {currentCourse.sections.map((section, sectionIndex) => (
            <div key={sectionIndex} className="space-y-4">
              <h2 className="text-2xl font-semibold text-blue-300">{section.title}</h2>
              <Separator className="bg-gray-600" />
              {section.subsections && (
                <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                  {section.subsections.map((sub, subIndex) => (
                    <Card key={subIndex} className="bg-[#3a3a3a] border border-gray-600">
                      <CardHeader className="pb-2">
                        <CardTitle className="text-lg text-blue-200">{sub.title}</CardTitle>
                      </CardHeader>
                      <CardContent className="text-sm text-gray-300">{sub.content}</CardContent>
                    </Card>
                  ))}
                </div>
              )}
              {section.details && (
                <div className="space-y-4">
                  {section.details.map((detail, detailIndex) => (
                    <div key={detailIndex}>
                      {detail.heading && <h3 className="text-xl font-medium text-blue-100 mb-2">{detail.heading}</h3>}
                      <ul className="list-disc list-inside space-y-1 text-gray-300">
                        {detail.items.map((item, itemIndex) => (
                          <li key={itemIndex}>{item}</li>
                        ))}
                      </ul>
                    </div>
                  ))}
                </div>
              )}
            </div>
          ))}
        </CardContent>
      </Card>
    </div>
  )
}
