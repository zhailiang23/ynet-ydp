import { SiteHeader } from "@/components/site-header"
import { TrainingFileList } from "@/components/training-file-list"
import { Button } from "@/components/ui/button"
import { ArrowLeft } from "lucide-react"
import Link from "next/link"

export default function TrainingFilesPage() {
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
          <Link href="/">
            <Button variant="ghost" className="text-gray-300 hover:text-white">
              <ArrowLeft className="mr-2 h-4 w-4" />
              返回首页
            </Button>
          </Link>
          <h1 className="text-3xl font-bold text-gray-200">培训文件列表</h1>
          <div></div> {/* Spacer for alignment */}
        </div>
        <TrainingFileList />
      </main>
    </div>
  )
}
