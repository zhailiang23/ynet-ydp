import { SiteHeader } from "@/components/site-header"
import { ManagementSidebar } from "@/components/management-sidebar"
import { ScriptVersionDetail } from "@/components/script-version-detail" // Import the new detail component

export default function ScriptDetailPage({ params }: { params: { id: string } }) {
  const { id } = params // Get the script version ID from the URL

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
          <ScriptVersionDetail scriptVersionId={id} />
        </main>
      </div>
    </div>
  )
}
