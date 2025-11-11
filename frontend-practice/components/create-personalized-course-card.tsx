import { Card, CardContent } from "@/components/ui/card"
import { Sparkles } from "lucide-react"
import Link from "next/link"

export function CreatePersonalizedCourseCard() {
  return (
    <Link href="/create-personalized-course" className="block w-full max-w-xs flex-shrink-0">
      <Card className="w-full rounded-lg border-2 border-purple-700 bg-gradient-to-br from-purple-800 to-purple-600 text-white shadow-lg hover:shadow-purple-500/50 transition-shadow duration-300">
        <CardContent className="flex h-full flex-col items-center justify-center p-6 text-center">
          <div className="mb-4 rounded-full bg-purple-900 p-3">
            <Sparkles className="h-8 w-8 text-white" />
          </div>
          <h3 className="mb-2 text-lg font-semibold">创建个性化课程</h3>
          <p className="text-sm text-purple-100">设计您的专属虚拟客户与陪练场景</p>
        </CardContent>
      </Card>
    </Link>
  )
}
