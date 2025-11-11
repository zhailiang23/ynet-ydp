"use client" // This component needs to be a client component to handle clicks
import { Users, LayoutDashboard, Settings, Book, Tag, GitBranch, FileText, GraduationCap } from "lucide-react"
import Link from "next/link"

interface ManagementSidebarProps {
  activeModule: string
  setActiveModule: (module: string) => void
}

export function ManagementSidebar({ activeModule, setActiveModule }: ManagementSidebarProps) {
  const navItems = [
    { id: "dashboard", label: "仪表盘", icon: LayoutDashboard },
    { id: "virtual-customer-roles", label: "虚拟客户角色管理", icon: Users },
    { id: "scenario-creation", label: "课程创建管理", icon: Book },
    { id: "sales-routines", label: "销售套路技巧管理", icon: Settings },
    { id: "script-management", label: "创建剧本", icon: FileText },
    { id: "script-versions", label: "剧本版本管理", icon: GitBranch, href: "/management/script-versions" }, // Added href for direct navigation
    { id: "case-library", label: "案例库管理", icon: Tag },
    { id: "trainee-management", label: "学员培训管理", icon: GraduationCap, href: "/management/trainees" },
  ]

  return (
    <aside className="w-64 border-r border-gray-800 bg-[#2a2a2a] p-6">
      <nav className="space-y-4">
        {navItems.map((item) => {
          const Icon = item.icon
          const isActive = activeModule === item.id

          if (item.href) {
            return (
              <Link
                key={item.id}
                href={item.href}
                className={`flex w-full items-center gap-3 rounded-md px-3 py-2 text-sm font-medium transition-colors ${
                  isActive ? "bg-gray-700 text-white" : "text-gray-300 hover:bg-gray-700 hover:text-white"
                }`}
                onClick={() => setActiveModule(item.id)} // Still set active module for visual feedback
              >
                <Icon className="h-5 w-5" />
                {item.label}
              </Link>
            )
          } else {
            return (
              <button
                key={item.id}
                onClick={() => setActiveModule(item.id)}
                className={`flex w-full items-center gap-3 rounded-md px-3 py-2 text-sm font-medium transition-colors ${
                  isActive ? "bg-gray-700 text-white" : "text-gray-300 hover:bg-gray-700 hover:text-white"
                }`}
              >
                <Icon className="h-5 w-5" />
                {item.label}
              </button>
            )
          }
        })}
      </nav>
    </aside>
  )
}
