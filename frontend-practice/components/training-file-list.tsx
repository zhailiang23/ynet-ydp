import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Badge } from "@/components/ui/badge"
import { Progress } from "@/components/ui/progress"

export function TrainingFileList() {
  const trainingFiles = [
    {
      id: "file-001",
      name: "产品A销售手册.pdf",
      courses: ["产品A销售模拟", "异议处理进阶"],
      trainees: 120,
      effectiveness: 85, // 百分比
      effectivenessDescription: "销售转化率提升8%",
    },
    {
      id: "file-002",
      name: "客户服务规范V2.0.docx",
      courses: ["客户投诉处理", "服务流程优化"],
      trainees: 80,
      effectiveness: 70,
      effectivenessDescription: "客户满意度提升5%",
    },
    {
      id: "file-003",
      name: "新员工入职培训资料.pptx",
      courses: ["基础销售话术", "公司文化介绍"],
      trainees: 25,
      effectiveness: 90,
      effectivenessDescription: "新员工上手速度加快15%",
    },
    {
      id: "file-004",
      name: "风险管理与合规指南.txt",
      courses: ["合规风险识别", "反洗钱培训"],
      trainees: 60,
      effectiveness: 78,
      effectivenessDescription: "合规违规率下降3%",
    },
  ]

  return (
    <Card className="mb-8 border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">培训文件列表</CardTitle>
      </CardHeader>
      <CardContent>
        <Table className="border border-gray-700">
          <TableHeader>
            <TableRow className="border-gray-700 bg-[#1a1a1a]">
              <TableHead className="text-gray-300">培训文件</TableHead>
              <TableHead className="text-gray-300">关联课程</TableHead>
              <TableHead className="text-gray-300">训练人数</TableHead>
              <TableHead className="text-gray-300">培训效果体现</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {trainingFiles.map((file) => (
              <TableRow key={file.id} className="border-gray-700">
                <TableCell className="font-medium">{file.name}</TableCell>
                <TableCell>
                  <div className="flex flex-wrap gap-1">
                    {file.courses.map((course, index) => (
                      <Badge key={index} className="bg-blue-600 text-white hover:bg-blue-700">
                        {course}
                      </Badge>
                    ))}
                  </div>
                </TableCell>
                <TableCell>{file.trainees}</TableCell>
                <TableCell>
                  <div className="flex flex-col gap-1">
                    <div className="flex items-center gap-2">
                      <Progress value={file.effectiveness} className="h-2 flex-1 bg-gray-700 [&>*]:bg-green-500" />
                      <span className="text-sm text-gray-300">{file.effectiveness}%</span>
                    </div>
                    <p className="text-xs text-gray-400">{file.effectivenessDescription}</p>
                  </div>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </CardContent>
    </Card>
  )
}
