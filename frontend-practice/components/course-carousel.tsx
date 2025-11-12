"use client"

import { useRef, useEffect, useState } from "react"
import { CourseCard } from "./course-card"
import { ChevronLeft, ChevronRight } from "lucide-react"
import { Button } from "@/components/ui/button"
import { getCourseList } from "@/lib/api/course"
import { login } from "@/lib/api/auth"
import { getToken, setToken } from "@/lib/api/request"
import type { Course } from "@/lib/types/course"

export function CourseCarousel() {
  const scrollRef = useRef<HTMLDivElement>(null)
  const [courses, setCourses] = useState<Course[]>([])
  const [loading, setLoading] = useState(true)

  // 从后端加载课程数据
  useEffect(() => {
    async function loadCourses() {
      try {
        setLoading(true)

        // 检查是否已登录,如果没有则使用默认账号登录
        let token = getToken()
        if (!token) {
          console.log("未登录,使用默认账号登录...")
          const loginResult = await login({
            username: "admin",
            password: "admin123",
          })
          setToken(loginResult.accessToken)
          console.log("登录成功!")
        }

        // 加载课程列表
        const result = await getCourseList({ pageNo: 1, pageSize: 20 })
        setCourses(result.list)
      } catch (error) {
        console.error("加载课程失败:", error)
      } finally {
        setLoading(false)
      }
    }

    loadCourses()
  }, [])

  const scroll = (direction: "left" | "right") => {
    if (scrollRef.current) {
      const scrollAmount = 260 // Card width + margin
      if (direction === "left") {
        scrollRef.current.scrollBy({ left: -scrollAmount, behavior: "smooth" })
      } else {
        scrollRef.current.scrollBy({ left: scrollAmount, behavior: "smooth" })
      }
    }
  }

  // 转换后端数据格式为组件需要的格式
  const displayCourses = courses.map((course) => ({
    id: course.id.toString(),
    name: course.name,
    type: course.standard === 1 ? "standard" : "personalized",
    description: course.description || "",
  }))

  if (loading) {
    return (
      <div className="flex items-center justify-center p-8">
        <div className="text-gray-400">加载中...</div>
      </div>
    )
  }

  if (displayCourses.length === 0) {
    return (
      <div className="flex items-center justify-center p-8">
        <div className="text-gray-400">暂无课程</div>
      </div>
    )
  }

  return (
    <div className="relative flex items-center justify-center">
      <Button
        variant="ghost"
        size="icon"
        className="absolute left-0 z-10 rounded-full bg-gray-800/50 text-white hover:bg-gray-700/70"
        onClick={() => scroll("left")}
      >
        <ChevronLeft className="h-6 w-6" />
      </Button>
      <div
        ref={scrollRef}
        className="flex space-x-4 overflow-x-auto p-4 scrollbar-hide"
        style={{ scrollSnapType: "x mandatory" }}
      >
        {displayCourses.map((course) => (
          <div key={course.id} style={{ scrollSnapAlign: "start" }}>
            <CourseCard {...course} />
          </div>
        ))}
      </div>
      <Button
        variant="ghost"
        size="icon"
        className="absolute right-0 z-10 rounded-full bg-gray-800/50 text-white hover:bg-gray-700/70"
        onClick={() => scroll("right")}
      >
        <ChevronRight className="h-6 w-6" />
      </Button>
    </div>
  )
}
