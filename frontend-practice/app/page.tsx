import { SiteHeader } from "@/components/site-header"
import { CourseCarousel } from "@/components/course-carousel" // Use new carousel
import { CreatePersonalizedCourseCard } from "@/components/create-personalized-course-card" // Use new card
import { ScenarioOptionCard } from "@/components/scenario-option-card" // Keep this for "或选择" section
import { FooterControls } from "@/components/footer-controls"
import { Badge } from "@/components/ui/badge"
import { Users, Briefcase, ClipboardList, MessageSquare } from "lucide-react"

export default function Home() {
  const scenarioOptions = [
    {
      icon: <Users className="h-6 w-6" />,
      title: "绩效讨论",
      description: "练习给予或接受绩效反馈",
    },
    {
      icon: <MessageSquare className="h-6 w-6" />,
      title: "销售电话",
      description: "练习销售话术和处理异议",
    },
    {
      icon: <Briefcase className="h-6 w-6" />,
      title: "商业推介",
      description: "练习向潜在客户和高级主管推介",
    },
    {
      icon: <ClipboardList className="h-6 w-6" />,
      title: "面试练习",
      description: "练习作为面试官或候选人进行面试",
    },
  ]

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
        <div className="mb-8 text-center">
          <h1 className="text-2xl font-semibold text-gray-300">
            您的AI角色扮演引擎，用于高难度对话：{" "}
            <Badge className="bg-blue-600 px-3 py-1 text-sm font-normal text-white">销售辅导</Badge>
          </h1>
        </div>
        <div className="mb-8 flex justify-center space-x-4">
          <button className="flex items-center gap-1 rounded-full bg-green-500 px-4 py-2 text-sm font-medium text-white">
            <span className="h-2 w-2 rounded-full bg-white" />
            最新
          </button>
          <button className="rounded-full border border-gray-600 px-4 py-2 text-sm font-medium text-gray-300 hover:bg-gray-800">
            热门
          </button>
          <button className="rounded-full border border-gray-600 px-4 py-2 text-sm font-medium text-gray-300 hover:bg-gray-800">
            流行
          </button>
          <button className="rounded-full border border-gray-600 px-4 py-2 text-sm font-medium text-gray-300 hover:bg-gray-800">
            场景
          </button>
        </div>
        <CourseCarousel /> {/* Use the new CourseCarousel */}
        <div className="mt-12 flex flex-col items-center justify-center gap-8 md:flex-row md:items-start">
          <CreatePersonalizedCourseCard /> {/* Use the new CreatePersonalizedCourseCard */}
          <div className="text-lg font-semibold uppercase text-gray-500 md:-rotate-90 md:text-xl">或选择</div>
          <div className="grid grid-cols-1 gap-4 md:grid-cols-2 lg:grid-cols-2">
            {scenarioOptions.map((option, index) => (
              <ScenarioOptionCard
                key={index}
                icon={option.icon}
                title={option.title}
                description={option.description}
              />
            ))}
          </div>
        </div>
      </main>

      <FooterControls />
    </div>
  )
}
