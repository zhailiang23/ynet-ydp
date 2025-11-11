"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { ArrowLeft } from "lucide-react"
import Link from "next/link"
import { Badge } from "@/components/ui/badge"
import { Progress } from "@/components/ui/progress"

interface CourseDetailProps {
  courseId: string
}

export function CourseDetail({ courseId }: CourseDetailProps) {
  // 模拟课程详情数据
  // 在实际应用中，这里会根据 courseId 从后端获取数据
  const mockCourses = [
    {
      id: "c001",
      title: "销售电话模拟",
      type: "标准课程",
      creator: "系统",
      visibility: "公开",
      complexity: "简单场景",
      scriptName: "销售电话剧本A",
      scriptVersion: "V2.1",
      updatedAt: "2023-10-20",
      participants: 1200,
      value: "¥999",
      effectiveness: 85,
    },
    {
      id: "c002",
      title: "高净值客户资产配置",
      type: "标准课程",
      creator: "系统",
      visibility: "公开",
      complexity: "复杂场景",
      scriptName: "高净值剧本B",
      scriptVersion: "V1.5",
      updatedAt: "2023-09-10",
      participants: 850,
      value: "¥1999",
      effectiveness: 92,
    },
    {
      id: "c003",
      title: "自定义：应对客户投诉",
      type: "个性化课程",
      creator: "张三",
      visibility: "公开",
      complexity: "复杂场景",
      scriptName: "投诉处理剧本C",
      scriptVersion: "V1.0",
      updatedAt: "2023-11-01",
      participants: 1, // For individual user
      value: "已创建",
      effectiveness: 78,
    },
    {
      id: "c004",
      title: "自定义：新产品发布会演练",
      type: "个性化课程",
      creator: "李四",
      visibility: "私有",
      complexity: "简单场景",
      scriptName: "发布会剧本D",
      scriptVersion: "V1.0",
      updatedAt: "2023-11-05",
      participants: 1,
      value: "已创建",
      effectiveness: 90,
    },
  ]

  const courseDetail = mockCourses.find((c) => c.id === courseId)

  if (!courseDetail) {
    return (
      <div className="text-center py-20 text-gray-400">
        <p>课程 {courseId} 未找到。</p>
        <Link href="/management/scenario-creation">
          <Button variant="link" className="mt-4 text-blue-400 hover:text-blue-300">
            返回课程列表
          </Button>
        </Link>
      </div>
    )
  }

  const renderBadge = (label: string, color: string) => <Badge className={color}>{label}</Badge>

  return (
    <div className="space-y-8">
      <div className="mb-6 flex items-center justify-between">
        <Link href="/management/scenario-creation">
          <Button variant="ghost" className="text-gray-300 hover:text-white">
            <ArrowLeft className="mr-2 h-4 w-4" />
            返回课程列表
          </Button>
        </Link>
        <h1 className="text-3xl font-bold text-gray-200">课程详情：{courseDetail.title}</h1>
        <div></div> {/* Spacer for alignment */}
      </div>

      {/* 课程基本信息 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">基本信息</CardTitle>
        </CardHeader>
        <CardContent className="grid grid-cols-1 gap-4 md:grid-cols-2">
          <div>
            <p className="text-sm text-gray-400">课程标题</p>
            <p className="text-lg font-medium text-gray-200">{courseDetail.title}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">课程类型</p>
            {renderBadge(
              courseDetail.type,
              courseDetail.type === "标准课程" ? "bg-blue-600 text-white" : "bg-purple-600 text-white",
            )}
          </div>
          <div>
            <p className="text-sm text-gray-400">创建者</p>
            <p className="text-lg font-medium text-gray-200">{courseDetail.creator}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">可见性</p>
            {renderBadge(
              courseDetail.visibility,
              courseDetail.visibility === "公开" ? "bg-green-600 text-white" : "bg-gray-600 text-white",
            )}
          </div>
          <div>
            <p className="text-sm text-gray-400">场景复杂度</p>
            {renderBadge(
              courseDetail.complexity,
              courseDetail.complexity === "简单场景" ? "bg-green-600 text-white" : "bg-red-600 text-white",
            )}
          </div>
          <div>
            <p className="text-sm text-gray-400">关联的剧本名称</p>
            <p className="text-lg font-medium text-gray-200">{courseDetail.scriptName}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">剧本版本</p>
            <p className="text-lg font-medium text-gray-200">{courseDetail.scriptVersion}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">更新时间</p>
            <p className="text-lg font-medium text-gray-200">{courseDetail.updatedAt}</p>
          </div>
        </CardContent>
      </Card>

      {/* 课程数据概览 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">课程数据概览</CardTitle>
        </CardHeader>
        <CardContent className="grid grid-cols-1 gap-4 md:grid-cols-3">
          <div>
            <p className="text-sm text-gray-400">参与人数</p>
            <p className="text-2xl font-bold text-gray-200">{courseDetail.participants}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">课程价值</p>
            <p className="text-2xl font-bold text-gray-200">{courseDetail.value}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">课程效果</p>
            <div className="flex items-center gap-2">
              <Progress value={courseDetail.effectiveness} className="h-2 flex-1 bg-gray-700 [&>*]:bg-blue-500" />
              <span className="text-lg font-bold text-gray-200">{courseDetail.effectiveness}%</span>
            </div>
          </div>
        </CardContent>
      </Card>
    </div>
  )
}
