import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Badge } from "@/components/ui/badge"
import Link from "next/link" // Import Link

export function ScenarioCreationManagement() {
  /* ---------- 模拟数据 ---------- */
  const allCourses = [
    {
      id: "c001",
      title: "销售电话模拟",
      type: "标准课程",
      creator: "系统",
      visibility: "公开",
      complexity: "简单场景",
      scriptName: "销售电话剧本A",
      scriptVersion: "V2.1",
      updatedAt: "2023-10-20",
    },
    {
      id: "c002",
      title: "高净值客户资产配置",
      type: "标准课程",
      creator: "系统",
      visibility: "公开",
      complexity: "复杂场景",
      scriptName: "高净值剧本B",
      scriptVersion: "V1.5",
      updatedAt: "2023-09-10",
    },
    {
      id: "c003",
      title: "自定义：应对客户投诉",
      type: "个性化课程",
      creator: "张三",
      visibility: "公开",
      complexity: "复杂场景",
      scriptName: "投诉处理剧本C",
      scriptVersion: "V1.0",
      updatedAt: "2023-11-01",
    },
    {
      id: "c004",
      title: "自定义：新产品发布会演练",
      type: "个性化课程",
      creator: "李四",
      visibility: "私有",
      complexity: "简单场景",
      scriptName: "发布会剧本D",
      scriptVersion: "V1.0",
      updatedAt: "2023-11-05",
    },
  ]

  /* ---------- 表头 & 单元格渲染 ---------- */
  const headTitles = [
    "课程标题",
    "课程类型",
    "创建者",
    "可见性",
    "场景复杂度",
    "关联的剧本名称",
    "剧本版本",
    "更新时间",
    "操作",
  ]

  const renderBadge = (label: string, color: string) => <Badge className={color}>{label}</Badge>

  return (
    <Card className="mb-8 border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">课程创建管理</CardTitle>
      </CardHeader>

      <CardContent className="space-y-6">
        <p className="text-gray-400">在此管理所有陪练课程，包括系统预设和用户自定义课程。</p>

        {/* ---------- 课程列表 ---------- */}
        <h3 className="text-lg font-semibold text-gray-200">所有课程列表</h3>
        <Table className="border border-gray-700">
          <TableHeader>
            <TableRow suppressHydrationWarning className="border-gray-700 bg-[#1a1a1a]">
              {headTitles.map((title) => (
                <TableHead key={title} className="text-gray-300">
                  {title}
                </TableHead>
              ))}
            </TableRow>
          </TableHeader>

          <TableBody>
            {allCourses.map((c) => (
              <TableRow key={c.id} suppressHydrationWarning className="border-gray-700">
                <TableCell className="font-medium">{c.title}</TableCell>

                <TableCell>
                  {renderBadge(
                    c.type,
                    c.type === "标准课程" ? "bg-blue-600 hover:bg-blue-700" : "bg-purple-600 hover:bg-purple-700",
                  )}
                </TableCell>

                <TableCell>{c.creator}</TableCell>

                <TableCell>
                  {renderBadge(
                    c.visibility,
                    c.visibility === "公开" ? "bg-green-600 hover:bg-green-700" : "bg-gray-600 hover:bg-gray-700",
                  )}
                </TableCell>

                <TableCell>
                  {renderBadge(
                    c.complexity,
                    c.complexity === "简单场景" ? "bg-green-600 hover:bg-green-700" : "bg-red-600 hover:bg-red-700",
                  )}
                </TableCell>

                <TableCell>{c.scriptName}</TableCell>
                <TableCell>{c.scriptVersion}</TableCell>
                <TableCell>{c.updatedAt}</TableCell>

                <TableCell>
                  <Link href={`/management/scenario-creation/${c.id}`}>
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

        {/* ---------- 创建 / 编辑课程表单（略） ---------- */}
        {/* 保留原有表单，无需改动 */}
      </CardContent>
    </Card>
  )
}
