import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button" // Import Button
import Link from "next/link" // Import Link

export function ParticipatedCourses() {
  // 模拟学员参与的课程/场景数据，增加 trainingId
  const participatedScenarios = [
    { trainingId: "training-001", name: "销售电话模拟", type: "预设", date: "2023-10-01", status: "已完成" },
    { trainingId: "training-002", name: "自定义：应对客户投诉", type: "自定义", date: "2023-10-15", status: "已完成" },
    { trainingId: "training-003", name: "异议处理实战", type: "预设", date: "2023-11-05", status: "进行中" },
    { trainingId: "training-004", name: "自定义：新产品发布会", type: "自定义", date: "2023-11-10", status: "进行中" },
    { trainingId: "training-005", name: "高价值理财产品推荐", type: "自定义", date: "2023-12-01", status: "已完成" }, // 新增记录
  ]

  return (
    <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">参与课程/场景</CardTitle>
      </CardHeader>
      <CardContent>
        <Table className="border border-gray-700">
          <TableHeader>
            <TableRow className="border-gray-700 bg-[#1a1a1a]">
              <TableHead className="text-gray-300">名称</TableHead>
              <TableHead className="text-gray-300">类型</TableHead>
              <TableHead className="text-gray-300">参与日期</TableHead>
              <TableHead className="text-gray-300">状态</TableHead>
              <TableHead className="text-gray-300">操作</TableHead> {/* 新增操作列 */}
            </TableRow>
          </TableHeader>
          <TableBody>
            {participatedScenarios.map((scenario, index) => (
              <TableRow key={index} className="border-gray-700">
                <TableCell>{scenario.name}</TableCell>
                <TableCell>
                  <Badge
                    className={
                      scenario.type === "预设" ? "bg-blue-600 hover:bg-blue-700" : "bg-purple-600 hover:bg-purple-700"
                    }
                  >
                    {scenario.type}
                  </Badge>
                </TableCell>
                <TableCell>{scenario.date}</TableCell>
                <TableCell>
                  <Badge
                    className={
                      scenario.status === "已完成" ? "bg-green-600 hover:bg-green-700" : "bg-blue-600 hover:bg-blue-700"
                    }
                  >
                    {scenario.status}
                  </Badge>
                </TableCell>
                <TableCell>
                  <Link href={`/training-results/${scenario.trainingId}`}>
                    <Button variant="ghost" size="sm" className="text-blue-400 hover:text-blue-300">
                      查看详情
                    </Button>
                  </Link>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </CardContent>
    </Card>
  )
}
