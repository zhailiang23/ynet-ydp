"use client"

import { useRef } from "react"
import { CourseCard } from "./course-card"
import { ChevronLeft, ChevronRight } from "lucide-react"
import { Button } from "@/components/ui/button"

export function CourseCarousel() {
  const scrollRef = useRef<HTMLDivElement>(null)

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

  // 模拟课程数据
  const courses = [
    {
      id: "course-001",
      name: "销售电话模拟",
      type: "standard",
      description: "练习销售话术和处理异议，提升电话销售能力。",
    },
    {
      id: "course-002",
      name: "高净值客户资产配置",
      type: "standard",
      description: "学习如何向高净值客户推销复杂金融产品。",
    },
    {
      id: "course-003",
      name: "面试练习 - 软件工程师",
      type: "standard",
      description: "模拟软件工程师面试，练习技术和行为问题。",
    },
    {
      id: "course-004",
      name: "异议处理实战",
      type: "standard",
      description: "掌握多种异议处理技巧，有效化解客户疑虑。",
    },
    {
      id: "course-005",
      name: "新产品发布会演练",
      type: "personalized", // Example of a personalized course that might appear here
      description: "为公司内部新产品发布会进行模拟演练。",
    },
    {
      id: "course-006",
      name: "客户投诉处理",
      type: "standard",
      description: "学习如何专业、高效地处理客户投诉，维护客户关系。",
    },
  ]

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
        {courses.map((course) => (
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
