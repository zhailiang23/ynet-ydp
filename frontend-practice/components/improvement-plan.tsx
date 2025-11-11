import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { ExternalLink } from "lucide-react"

export function ImprovementPlan() {
  const suggestions = [
    {
      id: 1,
      text: "异议处理话术需补充风险缓释逻辑，避免直接承诺收益。",
      resource: { label: "风险缓释课程", url: "#" },
    },
    {
      id: 2,
      text: "在需求挖掘阶段，尝试使用开放式问题引导客户深入表达。",
      resource: { label: "需求挖掘模板", url: "#" },
    },
    {
      id: 3,
      text: "提升产品知识应用能力，确保在客户提问时能迅速给出准确信息。",
      resource: { label: "产品知识库", url: "#" },
    },
  ]

  return (
    <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">个性化改进方案</CardTitle>
      </CardHeader>
      <CardContent className="space-y-4">
        <p className="text-gray-400">结合评估数据与行业最优案例，输出针对性改进建议，关联学习资源（课程、模板）。</p>
        {suggestions.map((suggestion) => (
          <div key={suggestion.id} className="rounded-md border border-gray-600 p-4">
            <p className="text-gray-300">{suggestion.text}</p>
            {suggestion.resource && (
              <Button variant="link" className="mt-2 p-0 text-blue-400 hover:text-blue-300">
                <ExternalLink className="mr-1 h-4 w-4" />
                <a href={suggestion.resource.url} target="_blank" rel="noopener noreferrer">
                  {suggestion.resource.label}
                </a>
              </Button>
            )}
          </div>
        ))}
      </CardContent>
    </Card>
  )
}
