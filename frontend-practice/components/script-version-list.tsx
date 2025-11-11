import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Button } from "@/components/ui/button"
import { Badge } from "@/components/ui/badge"
import Link from "next/link"

export function ScriptVersionList() {
  // 模拟剧本版本数据
  const scriptVersions = [
    {
      id: "s001-v1.2",
      scriptName: "销售电话剧本A",
      version: "V1.2",
      updatedAt: "2023-10-26",
      status: "启用中", // '启用中' | '已停用'
      description: "新增客户离场分支，优化话术。",
    },
    {
      id: "s001-v1.1",
      scriptName: "销售电话剧本A",
      version: "V1.1",
      updatedAt: "2023-09-15",
      status: "已停用",
      description: "初始版本，基础异议处理。",
    },
    {
      id: "s002-v1.5",
      scriptName: "高净值剧本B",
      version: "V1.5",
      updatedAt: "2023-11-01",
      status: "启用中",
      description: "更新产品信息，增加风险提示。",
    },
    {
      id: "s003-v1.0",
      scriptName: "投诉处理剧本C",
      version: "V1.0",
      updatedAt: "2023-11-10",
      status: "启用中",
      description: "首次发布，处理客户投诉流程。",
    },
  ]

  return (
    <Card className="mb-8 border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">剧本版本管理</CardTitle>
      </CardHeader>
      <CardContent>
        <p className="text-gray-400 mb-4">在此管理所有剧本的不同版本，并查看其状态和操作。</p>
        <Table className="border border-gray-700">
          <TableHeader>
            <TableRow className="border-gray-700 bg-[#1a1a1a]">
              <TableHead className="text-gray-300">剧本名称</TableHead>
              <TableHead className="text-gray-300">版本</TableHead>
              <TableHead className="text-gray-300">更新时间</TableHead>
              <TableHead className="text-gray-300">状态</TableHead>
              <TableHead className="text-gray-300">操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {scriptVersions.map((script) => (
              <TableRow key={script.id} className="border-gray-700">
                <TableCell className="font-medium">{script.scriptName}</TableCell>
                <TableCell>{script.version}</TableCell>
                <TableCell>{script.updatedAt}</TableCell>
                <TableCell>
                  <Badge className={script.status === "启用中" ? "bg-green-600 text-white" : "bg-gray-600 text-white"}>
                    {script.status}
                  </Badge>
                </TableCell>
                <TableCell>
                  <Link href={`/management/script-versions/${script.id}`}>
                    <Button variant="ghost" size="sm" className="text-blue-400 hover:text-blue-300">
                      查看详情
                    </Button>
                  </Link>
                  <Button
                    variant="ghost"
                    size="sm"
                    className={
                      script.status === "启用中"
                        ? "text-orange-400 hover:text-orange-300"
                        : "text-green-400 hover:text-green-300"
                    }
                  >
                    {script.status === "启用中" ? "停用" : "启用"}
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
