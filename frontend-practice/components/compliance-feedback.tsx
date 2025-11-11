import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { AlertCircle, CheckCircle } from "lucide-react"

export function ComplianceFeedback() {
  const complianceIssues = [
    {
      id: 1,
      type: "敏感词触发",
      description: "在提及收益时使用了“保证收益”等敏感词。",
      status: "需校准",
      suggestion: "请使用“预期收益”或“历史业绩”等合规表述。",
    },
    {
      id: 2,
      type: "风险隐瞒",
      description: "未充分披露产品潜在风险。",
      status: "需校准",
      suggestion: "请在介绍产品时，明确告知客户所有相关风险。",
    },
    {
      id: 3,
      type: "合规通过",
      description: "本次演练合规表现良好。",
      status: "通过",
      suggestion: "",
    },
  ]

  return (
    <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">合规实时校验与压力测试</CardTitle>
      </CardHeader>
      <CardContent className="space-y-4">
        <p className="text-gray-400">
          植入合规知识库，实时校验话术（如收益承诺、风险隐瞒），模拟极端场景（客户投诉、监管问询）进行压力测试，标记敏感表述并提示校准方案。
        </p>
        {complianceIssues.map((issue) => (
          <div
            key={issue.id}
            className={`flex items-start gap-3 rounded-md p-3 ${
              issue.status === "通过" ? "bg-green-900/20" : "bg-red-900/20"
            }`}
          >
            {issue.status === "通过" ? (
              <CheckCircle className="h-5 w-5 flex-shrink-0 text-green-500" />
            ) : (
              <AlertCircle className="h-5 w-5 flex-shrink-0 text-red-500" />
            )}
            <div>
              <h4 className="font-semibold text-gray-200">{issue.type}</h4>
              <p className="text-sm text-gray-300">{issue.description}</p>
              {issue.suggestion && <p className="mt-1 text-sm text-gray-400">建议：{issue.suggestion}</p>}
            </div>
          </div>
        ))}
      </CardContent>
    </Card>
  )
}
