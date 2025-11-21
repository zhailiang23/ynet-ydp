"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import Link from "next/link"
import { useEffect, useState } from "react"
import { getPracticeUserRecordPage, PracticeUserRecord } from "@/lib/api/practice-user-record"

export function ParticipatedCourses() {
  const [records, setRecords] = useState<PracticeUserRecord[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    loadPracticeRecords()
  }, [])

  const loadPracticeRecords = async () => {
    try {
      setLoading(true)
      setError(null)

      // 获取当前用户的练习记录
      // TODO: 获取实际用户ID，这里先用固定值
      const userId = 1 // 应该从用户状态或token中获取

      const response = await getPracticeUserRecordPage({
        pageNo: 1,
        pageSize: 10,
        userId,
      })

      setRecords(response.list || [])
    } catch (err) {
      console.error("加载练习记录失败:", err)
      setError("加载练习记录失败，请稍后重试")
    } finally {
      setLoading(false)
    }
  }

  const formatRecordType = (courseType: number | undefined | null) => {
    // 1标准课程 0个性化课程
    if (courseType === 1) {
      return "标准课程"
    } else if (courseType === 0) {
      return "个性化课程"
    }
    // courseType 为 null 或 undefined 时默认显示 "未知"
    return "未知"
  }

  const formatDate = (timestamp: number | string | null | undefined) => {
    if (!timestamp) return "-"
    // 处理时间戳（毫秒）或字符串格式
    const date = typeof timestamp === "number"
      ? new Date(timestamp)
      : new Date(timestamp)
    if (isNaN(date.getTime())) return "-"
    return date.toLocaleDateString("zh-CN")
  }

  const formatStatus = (status: string) => {
    switch (status) {
      case "completed":
        return "已完成"
      case "in_progress":
        return "进行中"
      case "paused":
        return "已暂停"
      default:
        return "未开始"
    }
  }

  if (loading) {
    return (
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">参与课程/场景</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="flex items-center justify-center py-8">
            <div className="text-gray-400">加载中...</div>
          </div>
        </CardContent>
      </Card>
    )
  }

  if (error) {
    return (
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">参与课程/场景</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="flex items-center justify-center py-8">
            <div className="text-red-400">{error}</div>
          </div>
        </CardContent>
      </Card>
    )
  }

  return (
    <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">参与课程/场景</CardTitle>
      </CardHeader>
      <CardContent>
        <Table className="border border-gray-700">
          <TableHeader>
            <TableRow className="border-gray-700 bg-[#1a1a1a]">
              <TableHead className="text-gray-300">课程名称</TableHead>
              <TableHead className="text-gray-300">类型</TableHead>
              <TableHead className="text-gray-300">参与日期</TableHead>
              <TableHead className="text-gray-300">状态</TableHead>
              <TableHead className="text-gray-300">得分</TableHead>
              <TableHead className="text-gray-300">操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {records.length === 0 ? (
              <TableRow>
                <TableCell colSpan={6} className="text-center text-gray-400 py-8">
                  暂无练习记录
                </TableCell>
              </TableRow>
            ) : (
              records.map((record) => (
                <TableRow key={record.id} className="border-gray-700">
                  <TableCell className="font-medium">
                    {record.courseName || `课程 ${record.courseId}`}
                  </TableCell>
                  <TableCell>
                    <Badge
                      className={
                        formatRecordType(record.courseType) === "标准课程"
                          ? "bg-blue-600 hover:bg-blue-700"
                          : "bg-purple-600 hover:bg-purple-700"
                      }
                    >
                      {formatRecordType(record.courseType)}
                    </Badge>
                  </TableCell>
                  <TableCell>
                    {formatDate(record.startTime)}
                  </TableCell>
                  <TableCell>
                    <Badge
                      className={
                        record.status === "completed"
                          ? "bg-green-600 hover:bg-green-700"
                          : "bg-blue-600 hover:bg-blue-700"
                      }
                    >
                      {formatStatus(record.status)}
                    </Badge>
                  </TableCell>
                  <TableCell>
                    {record.totalScore ? `${record.totalScore}分` : "-"}
                  </TableCell>
                  <TableCell>
                    <Link href={`/training-results/${record.id}`}>
                      <Button variant="ghost" size="sm" className="text-blue-400 hover:text-blue-300">
                        查看详情
                      </Button>
                    </Link>
                  </TableCell>
                </TableRow>
              ))
            )}
          </TableBody>
        </Table>
      </CardContent>
    </Card>
  )
}
