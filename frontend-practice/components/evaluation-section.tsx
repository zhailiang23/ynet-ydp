import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Progress } from "@/components/ui/progress"

export function EvaluationSection() {
  // 雷达图占位符 - 使用 SVG 模拟雷达图的视觉效果
  const RadarChartPlaceholder = () => (
    <div className="flex h-48 items-center justify-center rounded-md bg-gray-800 text-gray-400">
      <svg width="100%" height="100%" viewBox="0 0 200 200" className="text-blue-500">
        {/* Concentric circles */}
        <circle cx="100" cy="100" r="30" stroke="currentColor" strokeOpacity="0.3" fill="none" />
        <circle cx="100" cy="100" r="60" stroke="currentColor" strokeOpacity="0.3" fill="none" />
        <circle cx="100" cy="100" r="90" stroke="currentColor" strokeOpacity="0.3" fill="none" />

        {/* Axes */}
        <line x1="100" y1="10" x2="100" y2="190" stroke="currentColor" strokeOpacity="0.5" />
        <line x1="25" y1="150" x2="175" y2="50" stroke="currentColor" strokeOpacity="0.5" />
        <line x1="25" y1="50" x2="175" y2="150" stroke="currentColor" strokeOpacity="0.5" />

        {/* Example data points (static for placeholder) */}
        <polygon
          points="100,10 160,60 160,140 100,190 40,140 40,60"
          fill="currentColor"
          fillOpacity="0.2"
          stroke="currentColor"
          strokeWidth="2"
        />
        <circle cx="100" cy="10" r="3" fill="currentColor" />
        <circle cx="160" cy="60" r="3" fill="currentColor" />
        <circle cx="160" cy="140" r="3" fill="currentColor" />
        <circle cx="100" cy="190" r="3" fill="currentColor" />
        <circle cx="40" cy="140" r="3" fill="currentColor" />
        <circle cx="40" cy="60" r="3" fill="currentColor" />

        {/* Labels (simplified) */}
        <text x="95" y="5" fill="currentColor" fontSize="10" textAnchor="middle">
          沟通
        </text>
        <text x="185" y="55" fill="currentColor" fontSize="10" textAnchor="middle">
          专业
        </text>
        <text x="185" y="145" fill="currentColor" fontSize="10" textAnchor="middle">
          合规
        </text>
        <text x="95" y="195" fill="currentColor" fontSize="10" textAnchor="middle">
          需求
        </text>
        <text x="15" y="145" fill="currentColor" fontSize="10" textAnchor="middle">
          方案
        </text>
        <text x="15" y="55" fill="currentColor" fontSize="10" textAnchor="middle">
          其他
        </text>
      </svg>
    </div>
  )

  const scores = [
    { dimension: "沟通逻辑", score: 85 },
    { dimension: "专业能力", score: 90 },
    { dimension: "合规表现", score: 78 },
    { dimension: "需求捕捉精度", score: 88 },
    { dimension: "方案合理性", score: 92 },
  ]

  return (
    <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">培训结果评估</CardTitle>
      </CardHeader>
      <CardContent className="space-y-6">
        <p className="text-gray-400">
          从沟通逻辑（话术连贯性、需求捕捉精度）、专业能力（产品知识应用、方案合理性）、合规表现（敏感词触发率）等维度实时打分，生成可视化评估雷达图。
        </p>
        <RadarChartPlaceholder />
        <div className="space-y-4">
          {scores.map((item, index) => (
            <div key={index}>
              <div className="mb-1 flex justify-between text-sm text-gray-300">
                <span>{item.dimension}</span>
                <span>{item.score}%</span>
              </div>
              <Progress value={item.score} className="h-2 bg-gray-700 [&>*]:bg-blue-500" />
            </div>
          ))}
        </div>
      </CardContent>
    </Card>
  )
}
