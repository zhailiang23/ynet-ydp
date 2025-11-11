import Link from "next/link"
import { Home, Award, Book, Users, FileText, Globe, HelpCircle, LogIn } from "lucide-react"
import { Button } from "@/components/ui/button"

export function SiteHeader() {
  return (
    <header className="sticky top-0 z-40 w-full border-b border-gray-800 bg-[#1a1a1a] py-4">
      <div className="container mx-auto flex items-center justify-between px-4 md:px-6 lg:px-8">
        <nav className="flex items-center space-x-6">
          <Link href="/" className="flex items-center gap-2 text-sm font-medium text-gray-300 hover:text-white">
            <Home className="h-4 w-4" />
            首页
          </Link>
          <Link
            href="/training-results"
            className="flex items-center gap-2 text-sm font-medium text-gray-300 hover:text-white"
          >
            <Award className="h-4 w-4" />
            培训结果
          </Link>
          <Link href="/course" className="flex items-center gap-2 text-sm font-medium text-gray-300 hover:text-white">
            <Book className="h-4 w-4" />
            课程
          </Link>
          <Link href="/coach" className="flex items-center gap-2 text-sm font-medium text-gray-300 hover:text-white">
            <Users className="h-4 w-4" />
            虚拟客户
          </Link>
          <Link
            href="/training-files"
            className="flex items-center gap-2 text-sm font-medium text-gray-300 hover:text-white"
          >
            <FileText className="h-4 w-4" />
            文档
          </Link>
        </nav>
        <div className="flex items-center space-x-4">
          <Button variant="ghost" className="flex items-center gap-2 text-sm text-gray-300 hover:text-white">
            <Globe className="h-4 w-4" />
            ZH-CN
          </Button>
          <Button variant="ghost" className="flex items-center gap-2 text-sm text-gray-300 hover:text-white">
            <HelpCircle className="h-4 w-4" />
            帮助
          </Button>
          <Button variant="ghost" className="flex items-center gap-2 text-sm text-gray-300 hover:text-white">
            <LogIn className="h-4 w-4" />
            登录
          </Button>
        </div>
      </div>
    </header>
  )
}
