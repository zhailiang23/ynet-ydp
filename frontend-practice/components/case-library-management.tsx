import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Badge } from "@/components/ui/badge"

export function CaseLibraryManagement() {
  return (
    <Card className="mb-8 border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">案例库标签化管理</CardTitle>
      </CardHeader>
      <CardContent className="space-y-6">
        <p className="text-gray-400">
          对多领域案例打标签（场景类型、客户画像、应对策略），支持按标签检索（如“银行理财→老年客户→保本需求”），关联剧本生成逻辑。
        </p>
        <div>
          <Label htmlFor="case-title" className="text-gray-300">
            案例标题
          </Label>
          <Input id="case-title" placeholder="输入案例标题" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white" />
        </div>
        <div>
          <Label htmlFor="case-description" className="text-gray-300">
            案例描述
          </Label>
          <Textarea
            id="case-description"
            placeholder="详细描述案例内容"
            className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            rows={4}
          />
        </div>
        <div>
          <Label htmlFor="tags" className="text-gray-300">
            标签 (逗号分隔)
          </Label>
          <Input
            id="tags"
            placeholder="例如：银行理财, 老年客户, 保本需求, 保险理赔争议"
            className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
          />
        </div>
        <Button className="bg-blue-600 hover:bg-blue-700 text-white">添加案例</Button>

        <h4 className="mt-8 text-lg font-semibold text-gray-200">现有案例</h4>
        <Table className="border border-gray-700">
          <TableHeader>
            <TableRow className="border-gray-700 bg-[#1a1a1a]">
              <TableHead className="text-gray-300">标题</TableHead>
              <TableHead className="text-gray-300">标签</TableHead>
              <TableHead className="text-gray-300">操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            <TableRow className="border-gray-700">
              <TableCell>保险理赔争议案例</TableCell>
              <TableCell>
                <Badge variant="secondary" className="mr-1 bg-gray-600 text-gray-200">
                  保险
                </Badge>
                <Badge variant="secondary" className="mr-1 bg-gray-600 text-gray-200">
                  理赔
                </Badge>
                <Badge variant="secondary" className="bg-gray-600 text-gray-200">
                  争议
                </Badge>
              </TableCell>
              <TableCell>
                <Button variant="ghost" size="sm" className="text-blue-400 hover:text-blue-300">
                  编辑
                </Button>
                <Button variant="ghost" size="sm" className="text-red-400 hover:text-red-300">
                  删除
                </Button>
              </TableCell>
            </TableRow>
            <TableRow className="border-gray-700">
              <TableCell>理财暴雷应对案例</TableCell>
              <TableCell>
                <Badge variant="secondary" className="mr-1 bg-gray-600 text-gray-200">
                  理财
                </Badge>
                <Badge variant="secondary" className="mr-1 bg-gray-600 text-gray-200">
                  风险
                </Badge>
                <Badge variant="secondary" className="bg-gray-600 text-gray-200">
                  应对
                </Badge>
              </TableCell>
              <TableCell>
                <Button variant="ghost" size="sm" className="text-blue-400 hover:text-blue-300">
                  编辑
                </Button>
                <Button variant="ghost" size="sm" className="text-red-400 hover:text-red-300">
                  删除
                </Button>
              </TableCell>
            </TableRow>
          </TableBody>
        </Table>
      </CardContent>
    </Card>
  )
}
