import { Button } from "@/components/ui/button"
import { Mic, MoreHorizontal, Square, Zap, BookOpen, Play } from "lucide-react"
import Link from "next/link" // Import Link

export function FooterControls() {
  return (
    <footer className="fixed bottom-0 left-0 z-50 w-full bg-[#1a1a1a] py-4">
      <div className="container mx-auto flex items-center justify-center space-x-4 px-4 md:px-6 lg:px-8">
        <Button variant="ghost" size="icon" className="rounded-full bg-gray-800 text-white hover:bg-gray-700">
          <Mic className="h-5 w-5" />
        </Button>
        <Button variant="ghost" size="icon" className="rounded-full bg-gray-800 text-white hover:bg-gray-700">
          <MoreHorizontal className="h-5 w-5" />
        </Button>
        <Button variant="ghost" size="icon" className="rounded-full bg-gray-800 text-white hover:bg-gray-700">
          <Square className="h-5 w-5" />
        </Button>
        <Button variant="ghost" size="icon" className="rounded-full bg-gray-800 text-white hover:bg-gray-700">
          <Zap className="h-5 w-5" />
        </Button>
        <Button variant="ghost" size="icon" className="rounded-full bg-gray-800 text-white hover:bg-gray-700">
          <BookOpen className="h-5 w-5" />
        </Button>
        <Link href="/practice">
          {" "}
          {/* Added Link component */}
          <Button className="ml-4 flex items-center gap-2 rounded-full bg-blue-600 px-6 py-3 text-base font-medium text-white hover:bg-blue-700">
            开始
            <Play className="h-5 w-5" />
          </Button>
        </Link>
      </div>
    </footer>
  )
}
