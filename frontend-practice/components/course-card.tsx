import { Card, CardContent } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { Play, Info } from "lucide-react"
import Link from "next/link"

interface CourseCardProps {
  id: string
  name: string
  type: "standard" | "personalized"
  description: string
}

export function CourseCard({ id, name, type, description }: CourseCardProps) {
  const typeLabel = type === "standard" ? "标准课程" : "个性化课程"
  const typeColor = type === "standard" ? "bg-blue-600" : "bg-purple-600"

  return (
    <Card className="relative w-[240px] flex-shrink-0 overflow-hidden rounded-lg border border-gray-700 bg-[#2a2a2a] text-white shadow-lg">
      <CardContent className="flex flex-col items-center p-4">
        <div className="mb-3 text-center">
          {/* Make the course name a clickable link */}
          <Link href={`/course/${id}`} className="hover:underline">
            <h3 className="text-lg font-semibold">{name}</h3>
          </Link>
          <Badge className={`${typeColor} mt-1 px-2 py-1 text-xs font-normal text-white`}>{typeLabel}</Badge>
        </div>
        <p className="mb-4 text-center text-sm text-gray-400 line-clamp-2">{description}</p>
        <div className="flex flex-col gap-2 w-full">
          <Link href={`/select-customer-for-course/${id}`} className="w-full">
            <Button className="flex items-center gap-2 rounded-full bg-blue-600 px-6 py-2 text-sm font-medium text-white hover:bg-blue-700 w-full">
              <Play className="h-4 w-4" />
              进入课程
            </Button>
          </Link>
          {/* Ensure the "查看详情" button is present and correctly linked */}
          <Link href={`/course/${id}`} className="w-full">
            <Button
              variant="outline"
              className="flex items-center gap-2 rounded-full border-blue-600 text-blue-600 hover:bg-blue-600 hover:text-white px-6 py-2 text-sm font-medium w-full bg-transparent"
            >
              <Info className="h-4 w-4" />
              查看详情
            </Button>
          </Link>
        </div>
      </CardContent>
    </Card>
  )
}
