import { SiteHeader } from "@/components/site-header"
import { TrainingDetail } from "@/components/training-detail" // Import the new detail component
import { Suspense } from "react" // Import Suspense for useSearchParams

export default async function TrainingDetailPage({ params }: { params: Promise<{ id: string }> }) {
  const { id } = await params // Get the training ID from the URL

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
        {/* Use Suspense because TrainingDetail might internally use client hooks like useSearchParams */}
        <Suspense fallback={<div>加载培训详情...</div>}>
          <TrainingDetail trainingId={id} />
        </Suspense>
      </main>
    </div>
  )
}
