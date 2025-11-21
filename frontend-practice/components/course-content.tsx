"use client"

import { useEffect, useState } from "react"
import { CourseCard } from "@/components/course-card"
import { CreatePersonalizedCourseCard } from "@/components/create-personalized-course-card"
import { CourseCarousel } from "@/components/course-carousel"
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs"
import { ScrollArea, ScrollBar } from "@/components/ui/scroll-area"
import { getStandardCourses, getPersonalizedCourses } from "@/lib/api/course"
import type { Course } from "@/lib/types/course"

// 将后端课程数据转换为前端需要的格式
function transformCourse(course: Course, type: "standard" | "personalized" = "standard") {
  return {
    id: course.id.toString(),
    name: course.name,
    type,
    description: course.description || "",
  }
}

export function CourseContent() {
  const [standardCourses, setStandardCourses] = useState<any[]>([])
  const [personalizedCourses, setPersonalizedCourses] = useState<any[]>([])
  const [standardLoading, setStandardLoading] = useState(true)
  const [personalizedLoading, setPersonalizedLoading] = useState(true)
  const [standardError, setStandardError] = useState<string | null>(null)
  const [personalizedError, setPersonalizedError] = useState<string | null>(null)

  // 加载标准课程数据
  useEffect(() => {
    const loadStandardCourses = async () => {
      try {
        setStandardLoading(true)
        setStandardError(null)
        const data = await getStandardCourses()
        const transformedCourses = data.map((course) => transformCourse(course, "standard"))
        setStandardCourses(transformedCourses)
      } catch (err) {
        console.error("Failed to load standard courses:", err)
        setStandardError("加载标准课程失败,请稍后重试")
      } finally {
        setStandardLoading(false)
      }
    }

    loadStandardCourses()
  }, [])

  // 加载个性化课程数据
  useEffect(() => {
    const loadPersonalizedCourses = async () => {
      try {
        setPersonalizedLoading(true)
        setPersonalizedError(null)
        const data = await getPersonalizedCourses()
        const transformedCourses = data.map((course) => transformCourse(course, "personalized"))
        setPersonalizedCourses(transformedCourses)
      } catch (err) {
        console.error("Failed to load personalized courses:", err)
        setPersonalizedError("加载个性化课程失败,请稍后重试")
      } finally {
        setPersonalizedLoading(false)
      }
    }

    loadPersonalizedCourses()
  }, [])

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
          {standardLoading && (
            <div className="flex justify-center items-center py-12">
              <div className="text-white text-lg">加载中...</div>
            </div>
          )}

          {standardError && (
            <div className="flex justify-center items-center py-12">
              <div className="text-red-500 text-lg">{standardError}</div>
            </div>
          )}

          {!standardLoading && !standardError && (
            <>
              <h2 className="mb-4 text-2xl font-semibold text-white">热门标准课程</h2>
              <CourseCarousel courses={standardCourses} />
              <h2 className="mb-4 mt-8 text-2xl font-semibold text-white">所有标准课程</h2>
              <ScrollArea className="w-full whitespace-nowrap rounded-md border border-gray-700 p-4">
                <div className="flex w-max space-x-4 p-4">
                  {standardCourses.map((course) => (
                    <CourseCard key={course.id} {...course} />
                  ))}
                </div>
                <ScrollBar orientation="horizontal" />
              </ScrollArea>
            </>
          )}
        </TabsContent>

        <TabsContent value="personalized" className="mt-6">
          <h2 className="mb-4 text-2xl font-semibold text-white">我的个性化课程</h2>

          {personalizedLoading && (
            <div className="flex justify-center items-center py-12">
              <div className="text-white text-lg">加载中...</div>
            </div>
          )}

          {personalizedError && (
            <div className="flex justify-center items-center py-12">
              <div className="text-red-500 text-lg">{personalizedError}</div>
            </div>
          )}

          {!personalizedLoading && !personalizedError && (
            <ScrollArea className="w-full whitespace-nowrap rounded-md border border-gray-700 p-4">
              <div className="flex w-max space-x-4 p-4">
                <CreatePersonalizedCourseCard />
                {personalizedCourses.map((course) => (
                  <CourseCard key={course.id} {...course} />
                ))}
              </div>
              <ScrollBar orientation="horizontal" />
            </ScrollArea>
          )}
        </TabsContent>
      </Tabs>
    </div>
  )
}
