import { SiteHeader } from "@/components/site-header"
import { ManagementSidebar } from "@/components/management-sidebar"
import { ScriptVersionList } from "@/components/script-version-list" // Import the new component

export default function ScriptVersionsManagementPage() {
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
      <div className="flex min-h-[calc(100vh-64px)]">
        {/* ManagementSidebar will handle active module state for navigation */}
        <ManagementSidebar activeModule="script-versions" setActiveModule={() => {}} />
        <main className="flex-1 p-8">
          <h1 className="mb-8 text-3xl font-bold text-gray-200">剧本版本管理</h1>
          <ScriptVersionList />
        </main>
      </div>
    </div>
  )
}
