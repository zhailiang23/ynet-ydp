"use client"

import { useEffect, useState } from "react"
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { Badge } from "@/components/ui/badge"
import { Progress } from "@/components/ui/progress"
import { getPracticeMaterialList, type PracticeMaterial } from "@/lib/api/practice-material"

interface TrainingFile {
  id: string
  name: string
  courses: string[]
  trainees: number
  effectiveness: number
  effectivenessDescription: string
}

export function TrainingFileList() {
  const [trainingFiles, setTrainingFiles] = useState<TrainingFile[]>([])
  const [loading, setLoading] = useState(true)

  useEffect(() => {
    const fetchData = async () => {
      try {
        const result = await getPracticeMaterialList({ pageNo: 1, pageSize: 100 })

        // 将后端数据转换为前端显示格式
        const files: TrainingFile[] = result.list.map((material) => ({
          id: material.id.toString(),
          name: material.name,
          courses: material.courseName ? [material.courseName] : [], // 使用后端返回的课程名称
          trainees: material.trainingCount || 0, // 使用后端返回的培训人数
          effectiveness: 0, // 后端暂无效果数据,使用默认值
          effectivenessDescription: "暂无数据",
        }))

        setTrainingFiles(files)
      } catch (error) {
        console.error("获取培训文件列表失败:", error)
      } finally {
        setLoading(false)
      }
    }

    fetchData()
  }, [])

  if (loading) {
    return (
      <Card className="mb-8 border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">培训文件列表</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="flex items-center justify-center py-8">
            <p className="text-gray-400">加载中...</p>
          </div>
        </CardContent>
      </Card>
    )
  }

  return (
    <Card className="mb-8 border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">培训文件列表</CardTitle>
      </CardHeader>
      <CardContent>
        {trainingFiles.length === 0 ? (
          <div className="flex items-center justify-center py-8">
            <p className="text-gray-400">暂无培训文件</p>
          </div>
        ) : (
          <Table className="border border-gray-700">
            <TableHeader>
              <TableRow className="border-gray-700 bg-[#1a1a1a]">
                <TableHead className="text-gray-300">培训文件</TableHead>
                <TableHead className="text-gray-300">关联课程</TableHead>
                <TableHead className="text-gray-300">训练人数</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {trainingFiles.map((file) => (
                <TableRow key={file.id} className="border-gray-700">
                  <TableCell className="font-medium">{file.name}</TableCell>
                  <TableCell>
                    {file.courses.length > 0 ? (
                      <div className="flex flex-wrap gap-1">
                        {file.courses.map((course, index) => (
                          <Badge key={index} className="bg-blue-600 text-white hover:bg-blue-700">
                            {course}
                          </Badge>
                        ))}
                      </div>
                    ) : (
                      <span className="text-gray-500 text-sm">暂无关联课程</span>
                    )}
                  </TableCell>
                  <TableCell>{file.trainees ?? <span className="text-gray-500">-</span>}</TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        )}
      </CardContent>
    </Card>
  )
}
