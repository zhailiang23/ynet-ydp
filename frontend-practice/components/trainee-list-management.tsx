import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Button } from "@/components/ui/button"
import Link from "next/link"

export function TraineeListManagement() {
  const trainees = [
    { id: "trainee-001", name: "张三", totalCourses: 5, lastScore: 88 },
    { id: "trainee-002", name: "李四", totalCourses: 3, lastScore: 75 },
    { id: "trainee-003", name: "王五", totalCourses: 8, lastScore: 92 },
  ]

  return (
    <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">学员列表</CardTitle>
      </CardHeader>
      <CardContent>
        <Table className="border border-gray-700">
          <TableHeader>
            <TableRow className="border-gray-700 bg-[#1a1a1a]">
              <TableHead className="text-gray-300">学员ID</TableHead>
              <TableHead className="text-gray-300">姓名</TableHead>
              <TableHead className="text-gray-300">总课程数</TableHead>
              <TableHead className="text-gray-300">最近得分</TableHead>
              <TableHead className="text-gray-300">操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {trainees.map((trainee) => (
              <TableRow key={trainee.id} className="border-gray-700">
                <TableCell>{trainee.id}</TableCell>
                <TableCell>{trainee.name}</TableCell>
                <TableCell>{trainee.totalCourses}</TableCell>
                <TableCell>{trainee.lastScore}</TableCell>
                <TableCell>
                  <Link href={`/management/trainees/${trainee.id}`}>
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
