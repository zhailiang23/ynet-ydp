import { SiteHeader } from "@/components/site-header"
import { TrainingResultsDashboard } from "@/components/training-results-dashboard"

export default function TrainingResultsPage() {
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
        <TrainingResultsDashboard />
      </main>
    </div>
  )
}
