"use client"

import { CourseCard } from "@/components/course-card"
import { CreatePersonalizedCourseCard } from "@/components/create-personalized-course-card"
import { CourseCarousel } from "@/components/course-carousel"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { ScrollArea, ScrollBar } from "@/components/ui/scroll-area"

export function CourseContent() {
  // Mock data for courses
  const courses = [
    {
      id: "course-001",
      name: "高价值理财产品推荐",
      type: "standard",
      description: "学习如何向客户推荐高价值理财产品，掌握产品特点和销售技巧。",
    },
    {
      id: "course-002",
      name: "客户需求深度挖掘",
      type: "standard",
      description: "通过案例分析和模拟演练，提升客户需求识别和挖掘能力。",
    },
    {
      id: "course-003",
      name: "异议处理与促成技巧",
      type: "standard",
      description: "掌握常见的客户异议处理方法，提升促成交易的成功率。",
    },
    {
      id: "course-004",
      name: "合规销售与风险提示",
      type: "standard",
      description: "了解金融产品销售的合规要求，有效进行风险提示。",
    },
    {
      id: "course-005",
      name: "财富管理规划实战",
      type: "standard",
      description: "结合客户实际情况，制定个性化财富管理方案。",
    },
    {
      id: "course-006",
      name: "保险产品销售策略",
      type: "standard",
      description: "深入了解各类保险产品，掌握其销售策略和话术。",
    },
  ]

  return (
    <div className="space-y-8">
      <h1 className="text-3xl font-bold text-white">课程中心</h1>

      <Tabs defaultValue="standard" className="w-full">
        <TabsList className="grid w-full grid-cols-2 bg-[#2a2a2a] text-white">
          <TabsTrigger value="standard" className="data-[state=active]:bg-blue-600 data-[state=active]:text-white">
            标准课程
          </TabsTrigger>
          <TabsTrigger
            value="personalized"
            className="data-[state=active]:bg-purple-600 data-[state=active]:text-white"
          >
            个性化课程
          </TabsTrigger>
        </TabsList>

        <TabsContent value="standard" className="mt-6">
          <h2 className="mb-4 text-2xl font-semibold text-white">热门标准课程</h2>
          <CourseCarousel courses={courses} />
          <h2 className="mb-4 mt-8 text-2xl font-semibold text-white">所有标准课程</h2>
          <ScrollArea className="w-full whitespace-nowrap rounded-md border border-gray-700 p-4">
            <div className="flex w-max space-x-4 p-4">
              {courses.map((course) => (
                <CourseCard key={course.id} {...course} />
              ))}
            </div>
            <ScrollBar orientation="horizontal" />
          </ScrollArea>
        </TabsContent>

        <TabsContent value="personalized" className="mt-6">
          <h2 className="mb-4 text-2xl font-semibold text-white">我的个性化课程</h2>
          <ScrollArea className="w-full whitespace-nowrap rounded-md border border-gray-700 p-4">
            <div className="flex w-max space-x-4 p-4">
              <CreatePersonalizedCourseCard />
              {/* Placeholder for personalized courses */}
              {courses.slice(0, 2).map((course) => (
                <CourseCard key={course.id} {...course} type="personalized" />
              ))}
            </div>
            <ScrollBar orientation="horizontal" />
          </ScrollArea>
        </TabsContent>
      </Tabs>
    </div>
  )
}
