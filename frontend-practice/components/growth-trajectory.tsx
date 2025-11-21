"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Line, LineChart, XAxis, YAxis, CartesianGrid, Legend, Tooltip } from "recharts"

export function GrowthTrajectory() {
  // 模拟数据，包含客户经理的核心项沟通逻辑得分、专业能力得分、合规表现得分
  const growthData = [
    { month: "月1", communication: 70, professional: 65, compliance: 90 },
    { month: "月2", communication: 75, professional: 70, compliance: 92 },
    { month: "月3", communication: 80, professional: 75, compliance: 95 },
    { month: "月4", communication: 82, professional: 78, compliance: 96 },
    { month: "月5", communication: 85, professional: 80, compliance: 97 },
    { month: "月6", communication: 88, professional: 83, compliance: 98 },
    { month: "月7", communication: 90, professional: 85, compliance: 99 },
    { month: "月8", communication: 92, professional: 87, compliance: 99 },
    { month: "月9", communication: 93, professional: 88, compliance: 100 },
    { month: "月10", communication: 94, professional: 89, compliance: 100 },
    { month: "月11", communication: 95, professional: 90, compliance: 100 },
    { month: "月12", communication: 96, professional: 91, compliance: 100 },
  ]

  return (
    <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">成长轨迹追溯</CardTitle>
      </CardHeader>
      <CardContent className="space-y-6">
        <p className="text-gray-400">
          记录历次对练数据（得分变化、高频问题类型），生成个人能力成长曲线，支持跨周期对比分析（月度/季度训练效果）。
        </p>
        <div className="flex items-center gap-4">
          <span className="text-gray-300">对比周期:</span>
          <Select defaultValue="monthly">
            <SelectTrigger className="w-[180px] border-gray-600 bg-[#1a1a1a] text-white">
              <SelectValue placeholder="选择周期" />
            </SelectTrigger>
            <SelectContent className="bg-[#2a2a2a] text-white">
              <SelectItem value="daily">每日</SelectItem>
              <SelectItem value="weekly">每周</SelectItem>
              <SelectItem value="monthly">每月</SelectItem>
              <SelectItem value="quarterly">每季度</SelectItem>
            </SelectContent>
          </Select>
        </div>
        <div className="w-full bg-[#1a1a1a] rounded-lg p-4">
        <LineChart width={800} height={350} data={growthData} margin={{ top: 5, right: 30, left: 20, bottom: 5 }}>
          <CartesianGrid strokeDasharray="3 3" stroke="#666" strokeOpacity={0.3} />
          <XAxis dataKey="month" stroke="#999" tick={{ fill: "#999" }} tickLine={false} axisLine={false} />
          <YAxis stroke="#999" tick={{ fill: "#999" }} tickLine={false} axisLine={false} />
          <Tooltip contentStyle={{ backgroundColor: "#333", border: "1px solid #666", color: "#ccc" }} />
          <Legend wrapperStyle={{ color: "#ccc" }} />
          <Line
            type="monotone"
            dataKey="communication"
            stroke="#8884d8"
            name="核心项沟通逻辑得分"
            activeDot={{ r: 8 }}
            strokeWidth={2}
          />
          <Line
            type="monotone"
            dataKey="professional"
            stroke="#82ca9d"
            name="专业能力得分"
            activeDot={{ r: 8 }}
            strokeWidth={2}
          />
          <Line
            type="monotone"
            dataKey="compliance"
            stroke="#ffc658"
            name="合规表现得分"
            activeDot={{ r: 8 }}
            strokeWidth={2}
          />
        </LineChart>
        </div>
        <div className="space-y-2 text-gray-300">
          <p>最近一次对练得分：85分</p>
          <p>高频问题类型：客户异议（价格、风险）</p>
        </div>
      </CardContent>
    </Card>
  )
}
