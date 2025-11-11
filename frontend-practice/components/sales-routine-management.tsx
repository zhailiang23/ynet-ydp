import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import Link from "next/link" // Import Link

export function SalesRoutineManagement() {
  // 模拟现有套路与技巧数据
  const existingRoutines = [
    { id: "sr-001", name: "三步促成法", type: "销售套路" },
    { id: "sr-002", name: "风险置换话术模板", type: "销售技巧" },
  ]

  return (
    <Card className="mb-8 border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">销售套路技巧自定义配置</CardTitle>
      </CardHeader>
      <CardContent className="space-y-6">
        <p className="text-gray-400">
          支持上传/编辑公司专属销售套路（如“三步促成法”）、技巧（如“风险置换话术模板”），关联合规规则与产品知识，构建标准化技巧库。
        </p>
        <div className="grid grid-cols-1 gap-4 md:grid-cols-2">
          <div>
            <Label htmlFor="routine-name" className="text-gray-300">
              套路/技巧名称
            </Label>
            <Input id="routine-name" placeholder="输入名称" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white" />
          </div>
          <div>
            <Label htmlFor="type" className="text-gray-300">
              类型
            </Label>
            <select
              id="type"
              className="mt-1 block w-full rounded-md border border-gray-600 bg-[#1a1a1a] py-2 px-3 text-white focus:border-blue-500 focus:ring-blue-500"
            >
              <option value="routine">销售套路</option>
              <option value="skill">销售技巧</option>
            </select>
          </div>
        </div>
        <div>
          <Label htmlFor="content" className="text-gray-300">
            内容/话术模板
          </Label>
          <Textarea
            id="content"
            placeholder="输入套路或技巧的具体内容"
            className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            rows={6}
          />
        </div>
        <div>
          <Label htmlFor="compliance-rules" className="text-gray-300">
            关联合规规则
          </Label>
          <Input
            id="compliance-rules"
            placeholder="输入合规规则ID或名称"
            className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
          />
        </div>
        <div>
          <Label htmlFor="product-knowledge" className="text-gray-300">
            关联产品知识
          </Label>
          <Input
            id="product-knowledge"
            placeholder="输入产品知识ID或名称"
            className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
          />
        </div>
        <Button className="bg-blue-600 hover:bg-blue-700 text-white">保存配置</Button>

        <h4 className="mt-8 text-lg font-semibold text-gray-200">现有套路与技巧</h4>
        <Table className="border border-gray-700">
          <TableHeader>
            <TableRow className="border-gray-700 bg-[#1a1a1a]">
              <TableHead className="text-gray-300">名称</TableHead>
              <TableHead className="text-gray-300">类型</TableHead>
              <TableHead className="text-gray-300">操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {existingRoutines.map((routine) => (
              <TableRow key={routine.id} className="border-gray-700">
                <TableCell>{routine.name}</TableCell>
                <TableCell>{routine.type}</TableCell>
                <TableCell>
                  <Link href={`/management/sales-routines/${routine.id}`}>
                    <Button variant="ghost" size="sm" className="text-blue-400 hover:text-blue-300">
                      编辑
                    </Button>
                  </Link>
                  <Button variant="ghost" size="sm" className="text-red-400 hover:text-red-300">
                    删除
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </CardContent>
    </Card>
  )
}
