import { SiteHeader } from "@/components/site-header"
import { PracticeSession } from "@/components/practice-session"
import { Suspense } from "react" // Import Suspense for useSearchParams

export default function PracticePage() {
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
      <main className="container mx-auto flex flex-col items-center justify-center px-4 py-8 md:px-6 lg:px-8">
        {/* Use Suspense because useSearchParams is a client hook */}
        <Suspense fallback={<div>加载陪练会话...</div>}>
          <PracticeSession />
        </Suspense>
      </main>
    </div>
  )
}
