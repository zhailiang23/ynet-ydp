"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { ArrowLeft, CheckCircle, XCircle, ExternalLink } from "lucide-react"
import Link from "next/link"
import { Badge } from "@/components/ui/badge"
import { Progress } from "@/components/ui/progress"
import { ScrollArea } from "@/components/ui/scroll-area"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"
import { useEffect, useState } from "react"
import { getPracticeUserRecordById, type PracticeUserRecord } from "@/lib/api/practice-user-record"
import { getConversationListByRecordId, type PracticeConversation } from "@/lib/api/practice-conversation"

interface TrainingDetailProps {
  trainingId: string
}

export function TrainingDetail({ trainingId }: TrainingDetailProps) {
  const [loading, setLoading] = useState(true)
  const [record, setRecord] = useState<PracticeUserRecord | null>(null)
  const [conversations, setConversations] = useState<PracticeConversation[]>([])
  const [error, setError] = useState<string | null>(null)

  useEffect(() => {
    const fetchData = async () => {
      try {
        setLoading(true)
        setError(null)

        // 获取培训记录详情
        const recordData = await getPracticeUserRecordById(Number(trainingId))
        setRecord(recordData)

        // 获取对话记录
        const conversationsData = await getConversationListByRecordId(Number(trainingId))
        setConversations(conversationsData)
      } catch (err) {
        console.error("加载培训详情失败:", err)
        setError("加载培训详情失败，请稍后重试")
      } finally {
        setLoading(false)
      }
    }

    fetchData()
  }, [trainingId])

  if (loading) {
    return (
      <div className="text-center py-20 text-gray-400">
        <p>加载中...</p>
      </div>
    )
  }

  if (error || !record) {
    return (
      <div className="text-center py-20 text-gray-400">
        <p>{error || `培训详情 ${trainingId} 未找到。`}</p>
        <Link href="/training-results">
          <Button variant="link" className="mt-4 text-blue-400 hover:text-blue-300">
            返回培训结果列表
          </Button>
        </Link>
      </div>
    )
  }

  // 将对话记录转换为显示格式(兼容新旧数据格式)
  const dialogueContent = conversations.map((conv) => {
    // 判断发送方:学员相关的role映射为user,其他映射为ai
    const isUser = conv.role === "student" || conv.role === "销售"
    return {
      id: conv.id?.toString() || "",
      sender: isUser ? ("user" as const) : ("ai" as const),
      text: conv.messageContent,
    }
  })

  // 解析 AI 总结 JSON
  const parseAiSummary = (aiSummaryStr?: string) => {
    if (!aiSummaryStr) return null
    try {
      return JSON.parse(aiSummaryStr)
    } catch {
      return null
    }
  }

  const aiSummaryData = parseAiSummary(record.aiSummary)

  // 解析维度分数 - 优先使用 aiSummary 中的分数,否则使用 dimensionScores 字段
  const parseDimensionScores = (scoresStr?: string) => {
    if (!scoresStr) return { communicationScore: 0, professionalismScore: 0, complianceScore: 0 }
    try {
      return JSON.parse(scoresStr)
    } catch {
      return { communicationScore: 0, professionalismScore: 0, complianceScore: 0 }
    }
  }

  const dimensionScores = aiSummaryData || parseDimensionScores(record.dimensionScores)

  // 格式化时间
  const formatDate = (dateStr?: string) => {
    if (!dateStr) return "-"
    return new Date(dateStr).toLocaleString("zh-CN")
  }

  // 计算并格式化培训时长
  const formatDuration = () => {
    // 如果没有开始时间,返回 -
    if (!record.startTime) return "-"

    // 如果培训未结束(状态不是 completed 或没有结束时间),返回 -
    if (record.status !== "completed" || !record.endTime) return "-"

    // 计算时长(毫秒差值)
    const startMs = new Date(record.startTime).getTime()
    const endMs = new Date(record.endTime).getTime()
    const durationMs = endMs - startMs

    // 转换为秒
    const totalSeconds = Math.floor(durationMs / 1000)

    // 格式化为分钟和秒
    const minutes = Math.floor(totalSeconds / 60)
    const remainingSeconds = totalSeconds % 60

    return `${minutes}分${remainingSeconds}秒`
  }

  // 获取状态文本
  const getStatusText = (status?: string) => {
    const statusMap: Record<string, string> = {
      in_progress: "进行中",
      completed: "已完成",
      cancelled: "已取消",
    }
    return statusMap[status || ""] || status || "-"
  }

  return (
    <div className="space-y-8">
      <div className="mb-6 flex items-center justify-between">
        <Link href="/training-results">
          <Button variant="ghost" className="text-gray-300 hover:text-white">
            <ArrowLeft className="mr-2 h-4 w-4" />
            返回培训结果列表
          </Button>
        </Link>
        <h1 className="text-3xl font-bold text-gray-200">培训详情：{record.courseName || "未知课程"}</h1>
        <div></div> {/* Spacer for alignment */}
      </div>

      {/* 培训课程信息 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">培训课程信息</CardTitle>
        </CardHeader>
        <CardContent className="grid grid-cols-1 gap-4 md:grid-cols-2">
          <div>
            <p className="text-sm text-gray-400">课程名称</p>
            <p className="text-lg font-medium text-gray-200">{record.courseName || "-"}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">培训状态</p>
            <Badge
              className={
                record.status === "completed"
                  ? "bg-green-600 text-white"
                  : record.status === "in_progress"
                    ? "bg-blue-600 text-white"
                    : "bg-gray-600 text-white"
              }
            >
              {getStatusText(record.status)}
            </Badge>
          </div>
          <div>
            <p className="text-sm text-gray-400">开始时间</p>
            <p className="text-lg font-medium text-gray-200">{formatDate(record.startTime)}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">结束时间</p>
            <p className="text-lg font-medium text-gray-200">{formatDate(record.endTime)}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">培训时长</p>
            <p className="text-lg font-medium text-gray-200">{formatDuration()}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">虚拟客户</p>
            <p className="text-lg font-medium text-gray-200">{record.vcustomerName || "-"}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">培训编号</p>
            <p className="text-lg font-medium text-gray-200">{record.recordNo || "-"}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">完成率</p>
            <p className="text-lg font-medium text-gray-200">{record.completionRate || 0}%</p>
          </div>
        </CardContent>
      </Card>

      {/* 培训对话内容 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">培训对话内容</CardTitle>
        </CardHeader>
        <CardContent className="p-4">
          <ScrollArea className="h-[400px] pr-4">
            <div className="space-y-4">
              {dialogueContent.length > 0 ? (
                dialogueContent.map((message) => (
                <div
                  key={message.id}
                  className={`flex items-start gap-3 ${message.sender === "user" ? "justify-end" : "justify-start"}`}
                >
                  {message.sender === "ai" && (
                    <Avatar className="h-8 w-8 border border-gray-600">
                      <AvatarImage src="/placeholder.svg?height=32&width=32" alt="AI Coach" />
                      <AvatarFallback>AI</AvatarFallback>
                    </Avatar>
                  )}
                  <div
                    className={`max-w-[70%] rounded-lg p-3 ${
                      message.sender === "user" ? "bg-blue-600 text-white" : "bg-gray-700 text-gray-200"
                    }`}
                  >
                    <p className="text-sm">{message.text}</p>
                  </div>
                  {message.sender === "user" && (
                    <Avatar className="h-8 w-8 border border-gray-600">
                      <AvatarImage src="/placeholder.svg?height=32&width=32" alt="User" />
                      <AvatarFallback>您</AvatarFallback>
                    </Avatar>
                  )}
                </div>
                ))
              ) : (
                <div className="text-center py-8 text-gray-400">
                  <p>暂无对话记录</p>
                </div>
              )}
            </div>
          </ScrollArea>
        </CardContent>
      </Card>

      {/* 培训结果评估 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">培训结果评估</CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          {/* 总分 */}
          {record.totalScore !== undefined && record.totalScore !== null && (
            <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
              <h3 className="text-sm text-gray-400">总得分</h3>
              <div className="text-3xl font-bold text-yellow-400">{record.totalScore}分</div>
              <Progress value={record.totalScore} className="h-2 bg-gray-700 [&>*]:bg-yellow-500" />
            </Card>
          )}

          {/* 核心得分 */}
          {record.dimensionScores && (
            <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
              <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
                <h3 className="text-sm text-gray-400">沟通逻辑得分</h3>
                <div className="text-2xl font-bold text-blue-400">{dimensionScores.communicationScore || 0}%</div>
                <Progress
                  value={dimensionScores.communicationScore || 0}
                  className="h-2 bg-gray-700 [&>*]:bg-blue-500"
                />
              </Card>
              <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
                <h3 className="text-sm text-gray-400">专业能力得分</h3>
                <div className="text-2xl font-bold text-purple-400">{dimensionScores.professionalismScore || 0}%</div>
                <Progress
                  value={dimensionScores.professionalismScore || 0}
                  className="h-2 bg-gray-700 [&>*]:bg-purple-500"
                />
              </Card>
              <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
                <h3 className="text-sm text-gray-400">合规表现得分</h3>
                <div className="text-2xl font-bold text-green-400">{dimensionScores.complianceScore || 0}%</div>
                <Progress
                  value={dimensionScores.complianceScore || 0}
                  className="h-2 bg-gray-700 [&>*]:bg-green-500"
                />
              </Card>
            </div>
          )}

          {/* AI 总结 */}
          {aiSummaryData?.managerFeedback && (
            <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
              <h3 className="text-lg font-semibold text-gray-200 mb-2">AI 评估总结</h3>
              <p className="text-gray-300 text-sm whitespace-pre-wrap">{aiSummaryData.managerFeedback}</p>
            </Card>
          )}

          {/* 优势 */}
          {record.strengths && (
            <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
              <h3 className="text-lg font-semibold text-gray-200 mb-2 flex items-center gap-2">
                <CheckCircle className="h-5 w-5 text-green-500" />
                表现优势
              </h3>
              <p className="text-gray-300 text-sm whitespace-pre-wrap">{record.strengths}</p>
            </Card>
          )}

          {/* 待改进点 */}
          {record.weaknesses && (
            <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
              <h3 className="text-lg font-semibold text-gray-200 mb-2 flex items-center gap-2">
                <XCircle className="h-5 w-5 text-red-500" />
                待改进点
              </h3>
              <p className="text-gray-300 text-sm whitespace-pre-wrap">{record.weaknesses}</p>
            </Card>
          )}

          {/* 后续提升建议 */}
          {(aiSummaryData?.improvementSuggestions || record.recommendations) && (
            <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
              <h3 className="text-lg font-semibold text-gray-200 mb-2">后续提升建议</h3>
              {aiSummaryData?.improvementSuggestions ? (
                <ul className="space-y-2 text-gray-300 text-sm">
                  {aiSummaryData.improvementSuggestions.map((suggestion: any, index: number) => (
                    <li key={index} className="flex items-start gap-2">
                      <span className="text-blue-400 mt-1">•</span>
                      <div className="flex-1">
                        <p className="font-medium">{suggestion.text || suggestion.suggestion || (typeof suggestion === 'string' ? suggestion : '')}</p>
                        {suggestion.resource && (
                          <a
                            href={suggestion.resource.url || '#'}
                            className="text-blue-400 hover:text-blue-300 text-xs mt-1 inline-block"
                            target={suggestion.resource.url !== '#' ? '_blank' : undefined}
                            rel={suggestion.resource.url !== '#' ? 'noopener noreferrer' : undefined}
                          >
                            📚 {suggestion.resource.label}
                          </a>
                        )}
                      </div>
                    </li>
                  ))}
                </ul>
              ) : (
                <p className="text-gray-300 text-sm whitespace-pre-wrap">{record.recommendations}</p>
              )}
            </Card>
          )}

          {/* 合规问题 */}
          {aiSummaryData?.complianceIssues && aiSummaryData.complianceIssues.length > 0 && (
            <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
              <h3 className="text-lg font-semibold text-gray-200 mb-2">合规检查</h3>
              <div className="space-y-3">
                {aiSummaryData.complianceIssues.map((issue: any, index: number) => (
                  <div key={index} className="flex items-start gap-3 p-3 rounded-lg bg-gray-800">
                    <div className={`mt-0.5 ${issue.status === "通过" ? "text-green-400" : "text-yellow-400"}`}>
                      {issue.status === "通过" ? <CheckCircle className="h-5 w-5" /> : <XCircle className="h-5 w-5" />}
                    </div>
                    <div className="flex-1">
                      <p className="font-medium text-gray-200">{issue.type}</p>
                      <p className="text-gray-400 text-sm mt-1">{issue.description}</p>
                      {issue.status && (
                        <Badge className={`mt-2 ${issue.status === "通过" ? "bg-green-600" : "bg-yellow-600"} text-white`}>
                          {issue.status}
                        </Badge>
                      )}
                    </div>
                  </div>
                ))}
              </div>
            </Card>
          )}

          {/* 量化指标 */}
          {aiSummaryData?.quantifiedMetrics && aiSummaryData.quantifiedMetrics.length > 0 && (
            <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
              <h3 className="text-lg font-semibold text-gray-200 mb-2">量化指标</h3>
              <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                {aiSummaryData.quantifiedMetrics.map((metric: any, index: number) => (
                  <div key={index} className="p-3 rounded-lg bg-gray-800">
                    <p className="text-sm text-gray-400">{metric.dimension || metric.metric}</p>
                    <div className="flex items-baseline gap-2 mt-1">
                      <p className="text-2xl font-bold text-blue-400">{metric.actual || metric.value}</p>
                      {(metric.deviation || metric.unit) && (
                        <p className="text-sm text-gray-400">{metric.deviation || metric.unit}</p>
                      )}
                    </div>
                    {(metric.target || metric.benchmark) && (
                      <p className="text-xs text-gray-500 mt-1">目标: {metric.target || metric.benchmark}</p>
                    )}
                  </div>
                ))}
              </div>
            </Card>
          )}
        </CardContent>
      </Card>
    </div>
  )
}
