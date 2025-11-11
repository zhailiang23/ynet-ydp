import { SiteHeader } from "@/components/site-header"
import { TrainingResultsDashboard } from "@/components/training-results-dashboard" // Reuse dashboard for trainee details
import { Button } from "@/components/ui/button"
import { ArrowLeft } from "lucide-react"
import Link from "next/link"

export default function TraineeDetailPage({ params }: { params: { id: string } }) {
  const { id } = params // Get trainee ID from URL

  return (
    <div
      className="min-h-screen bg-[#1a1a1a] text-white"
      style={{
        backgroundImage:
          "linear-gradient(to right, #2a2a2a 1px, transparent 1px), linear-gradient(to bottom, #2a2a2a 1px, transparent 1px)",
        backgroundSize: "40px 40px",
      }}
    >
      <SiteHeader />
      <main className="container mx-auto px-4 py-8 md:px-6 lg:px-8">
        <div className="mb-6 flex items-center justify-between">
          <Link href="/management/trainees">
            <Button variant="ghost" className="text-gray-300 hover:text-white">
              <ArrowLeft className="mr-2 h-4 w-4" />
              返回学员列表
            </Button>
          </Link>
          <h1 className="text-3xl font-bold text-gray-200">学员 {id} 的培训详情</h1>
          <div>
            {/* Add management actions here, e.g., Edit Profile, Reset Progress */}
            <Button className="bg-blue-600 hover:bg-blue-700 text-white">编辑学员信息</Button>
          </div>
        </div>
        {/* Reuse the TrainingResultsDashboard to display trainee's data */}
        <TrainingResultsDashboard />
      </main>
    </div>
  )
}
