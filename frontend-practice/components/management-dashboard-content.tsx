import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Users, Book, FileText, TrendingUp, CheckCircle, AlertCircle } from "lucide-react"
import { Progress } from "@/components/ui/progress"

export function ManagementDashboardContent() {
  return (
    <div className="space-y-8">
      <h2 className="text-2xl font-bold text-gray-200">仪表盘概览</h2>

      {/* 核心数据概览 */}
      <div className="grid grid-cols-1 gap-6 md:grid-cols-2 lg:grid-cols-3">
        <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
          <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
            <CardTitle className="text-sm font-medium text-gray-300">总学员数</CardTitle>
            <Users className="h-4 w-4 text-gray-400" />
          </CardHeader>
          <CardContent>
            <div className="text-2xl font-bold">1,234</div>
            <p className="text-xs text-gray-400">+20.1% from last month</p>
          </CardContent>
        </Card>
        <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
          <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
            <CardTitle className="text-sm font-medium text-gray-300">总课程数</CardTitle>
            <Book className="h-4 w-4 text-gray-400" />
          </CardHeader>
          <CardContent>
            <div className="text-2xl font-bold">89</div>
            <p className="text-xs text-gray-400">+5 new courses</p>
          </CardContent>
        </Card>
        <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
          <CardHeader className="flex flex-row items-center justify-between space-y-0 pb-2">
            <CardTitle className="text-sm font-medium text-gray-300">总剧本数</CardTitle>
            <FileText className="h-4 w-4 text-gray-400" />
          </CardHeader>
          <CardContent>
            <div className="text-2xl font-bold">156</div>
            <p className="text-xs text-gray-400">+12 new scripts</p>
          </CardContent>
        </Card>
      </div>

      {/* 培训效果概览 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">培训效果概览</CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          <div className="flex items-center justify-between">
            <span className="text-gray-300">平均合规得分</span>
            <span className="text-lg font-bold text-green-400">88%</span>
          </div>
          <Progress value={88} className="h-2 bg-gray-700 [&>*]:bg-green-500" />

          <div className="flex items-center justify-between">
            <span className="text-gray-300">平均沟通逻辑得分</span>
            <span className="text-lg font-bold text-blue-400">91%</span>
          </div>
          <Progress value={91} className="h-2 bg-gray-700 [&>*]:bg-blue-500" />

          <div className="flex items-center justify-between">
            <span className="text-gray-300">平均专业能力得分</span>
            <span className="text-lg font-bold text-purple-400">85%</span>
          </div>
          <Progress value={85} className="h-2 bg-gray-700 [&>*]:bg-purple-500" />
        </CardContent>
      </Card>

      {/* 最新活动 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">最新活动</CardTitle>
        </CardHeader>
        <CardContent>
          <ul className="space-y-3 text-gray-300">
            <li className="flex items-center gap-2">
              <CheckCircle className="h-4 w-4 text-green-500" />
              <span className="font-semibold">张三</span> 完成了 "销售电话模拟" 课程。
              <span className="ml-auto text-xs text-gray-500">2分钟前</span>
            </li>
            <li className="flex items-center gap-2">
              <AlertCircle className="h-4 w-4 text-yellow-500" />
              <span className="font-semibold">李四</span> 在 "高净值客户资产配置" 课程中触发了合规警告。
              <span className="ml-auto text-xs text-gray-500">1小时前</span>
            </li>
            <li className="flex items-center gap-2">
              <TrendingUp className="h-4 w-4 text-blue-500" />
              新剧本 "应对客户异议V2.0" 已发布。
              <span className="ml-auto text-xs text-gray-500">1天前</span>
            </li>
          </ul>
        </CardContent>
      </Card>
    </div>
  )
}
