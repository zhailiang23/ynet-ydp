"use client"

import type React from "react"

import { useState, useRef, useEffect } from "react"
import { getCourseById } from "@/lib/api/course"
import { getVirtualCustomerById } from "@/lib/api/virtual-customer"
import { convertVirtualCustomerDict, type VirtualCustomerWithLabels } from "@/lib/utils/dict-converter"
import type { Course } from "@/lib/types/course"
import { getPracticeScriptById } from "@/lib/api/practice-script"
import type { PracticeScript } from "@/lib/api/practice-script"
import { createPracticeConversation, getConversationListByRecordId } from "@/lib/api/practice-conversation"
import { createPracticeUserRecord, findUnfinishedRecord, completePracticeUserRecord } from "@/lib/api/practice-user-record"
import { Card, CardContent, CardFooter, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Button } from "@/components/ui/button"
import { Mic, Send, Volume2, CheckCircle, XCircle, ExternalLink } from "lucide-react" // Added ExternalLink
import { ScrollArea } from "@/components/ui/scroll-area"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { useSearchParams } from "next/navigation"
import Link from "next/link"
import { Progress } from "@/components/ui/progress"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table" // Added Table components

interface Message {
  id: string
  sender: "user" | "ai"
  text: string
}

interface QuantifiedMetric {
  dimension: string
  target: string
  actual: string
  deviation: string
}

interface ImprovementSuggestion {
  text: string
  resource?: { label: string; url: string }
}

interface EvaluationResult {
  communicationScore: number
  professionalismScore: number
  complianceScore: number
  managerFeedback: string
  complianceIssues: { type: string; description: string; status: "通过" | "需校准" }[]
  quantifiedMetrics: QuantifiedMetric[] // Added
  improvementSuggestions: ImprovementSuggestion[] // Added
}

// ============ 演示场景:特定对话样例 (course-001 + customer-002) ============
// 以下固定对话内容仅用于演示特定场景,实际对话由 AI Agent 动态生成
// TODO: 未来可以考虑移除或改为从后端加载演示数据
const fixedDialogue_course001_customer002_initial: Message[] = [
  { id: "1", sender: "ai", text: "您好！我是您的AI陪练教练。我们今天将进行销售电话模拟，请您开始您的开场白。" },
  { id: "2", sender: "user", text: "李女士，根据大数据分析，您应该购买我们的明星理财组合 （× 未建立信任直接推销）" },
  { id: "3", sender: "ai", text: "你们和暴雷的那家机构有什么区别？" },
  { id: "4", sender: "user", text: "我们是正规银行，他们是不合规平台 （× 未提供官方证明材料）" },
  { id: "5", sender: "ai", text: "能保证资金安全吗？" },
  { id: "6", sender: "user", text: "我们的产品从没让客户亏过钱 （× 违规暗示保本）" },
  { id: "7", sender: "ai", text: "收益率是怎么计算的？" },
  { id: "8", sender: "user", text: "年化4.5%，比存款高很多 （× 未说明浮动收益特性）" },
  { id: "9", sender: "ai", text: "需要投资多久？" },
  { id: "10", sender: "user", text: "最少1年起，时间越长收益越高 （× 未提示提前赎回费用）" },
  { id: "middle-placeholder", sender: "ai", text: "..." }, // Placeholder for "中间20轮对话节选"
]

const fixedDialogue_course001_customer002_details: Message[] = [
  { id: "24", sender: "ai", text: "这个风险等级R2是什么意思？" },
  { id: "25", sender: "user", text: "就是中等风险，适合大部分人 （× 未解释具体波动范围）" },
  { id: "26", sender: "ai", text: "如果急用钱怎么办？" },
  { id: "27", sender: "user", text: "可以质押贷款，实时到账 （× 未说明贷款成本）" },
  { id: "28", sender: "ai", text: "我需要再考虑下..." },
  { id: "29", sender: "user", text: "明天活动截止，错过就没有了 （× 制造虚假紧迫感）" },
  { id: "30", sender: "ai", text: "那先买1万试试吧" },
]

// 模拟评估结果生成函数
const simulateEvaluation = (scriptId: string, customerId: string): EvaluationResult => {
  if (scriptId === "course-001" && customerId === "customer-002") {
    return {
      communicationScore: 65,
      professionalismScore: 70,
      complianceScore: 50,
      managerFeedback:
        "本次模拟对话中，客户经理在建立信任、提供证明、合规表述和风险提示方面存在多处问题，未能有效引导客户并制造了虚假紧迫感。需加强合规培训和销售话术的专业性。",
      complianceIssues: [
        { type: "未建立信任", description: "直接推销，未进行破冰。", status: "需校准" },
        { type: "未提供证明", description: "未提供官方证明材料。", status: "需校准" },
        { type: "违规暗示保本", description: "暗示产品保本或无风险。", status: "需校准" },
        { type: "未说明浮动收益", description: "未解释浮动收益特性。", status: "需校准" },
        { type: "未提示提前赎回费用", description: "未提示提前赎回可能产生的费用。", status: "需校准" },
        { type: "未解释波动范围", description: "未解释风险等级的具体波动范围。", status: "需校准" },
        { type: "未说明贷款成本", description: "未说明质押贷款的成本。", status: "需校准" },
        { type: "制造虚假紧迫感", description: "通过虚假截止日期制造紧迫感。", status: "需校准" },
      ],
      quantifiedMetrics: [
        { dimension: "话术合规率", target: "≥98%", actual: "83%", deviation: "7次违规表述" },
        { dimension: "需求匹配度", target: "≥85%", actual: "62%", deviation: "未完成财务诊断问卷" },
        { dimension: "促成转化率", target: "≥40%", actual: "22%", deviation: "过早进入促成阶段" },
      ],
      improvementSuggestions: [
        {
          text: "话术优化：将“保证收益”改为“历史平均收益”，消除保本暗示。",
          resource: { label: "合规话术指南", url: "#" },
        },
        {
          text: "话术优化：将“最后一天”改为“本季度优惠”，避免虚假营销。",
          resource: { label: "营销合规培训", url: "#" },
        },
        {
          text: "加强合规话术学习，避免保本暗示、虚假宣传和风险隐瞒。",
          resource: { label: "合规培训课程", url: "#" },
        },
        {
          text: "在销售初期充分进行需求诊断，完成财务诊断问卷，提升需求匹配度。",
          resource: { label: "需求挖掘技巧", url: "#" },
        },
        {
          text: "在客户充分理解和接受产品价值后再进行促成，避免过早进入促成阶段。",
          resource: { label: "销售流程优化", url: "#" },
        },
      ],
    }
  }

  let communicationScore = 0
  let professionalismScore = 0
  let complianceScore = 0
  let managerFeedback = ""
  let complianceIssues = []
  let quantifiedMetrics: QuantifiedMetric[] = []
  let improvementSuggestions: ImprovementSuggestion[] = []

  switch (scriptId) {
    case "course-001": // 销售电话模拟
      communicationScore = Math.floor(Math.random() * (95 - 70 + 1)) + 70
      professionalismScore = Math.floor(Math.random() * (90 - 65 + 1)) + 65
      complianceScore = Math.floor(Math.random() * (85 - 70 + 1)) + 70
      managerFeedback = "学员在开场白和产品介绍方面表现良好，但在处理价格异议时略显生硬，需加强话术灵活性。"
      complianceIssues = [
        { type: "敏感词触发", description: "在提及收益时使用了“保证收益”等敏感词。", status: "需校准" },
        { type: "合规通过", description: "本次演练合规表现良好。", status: "通过" },
      ]
      quantifiedMetrics = [
        { dimension: "沟通流畅度", target: "≥90%", actual: "88%", deviation: "偶尔卡顿" },
        { dimension: "产品介绍完整度", target: "≥95%", actual: "92%", deviation: "遗漏部分细节" },
      ]
      improvementSuggestions = [
        { text: "加强产品知识熟练度。", resource: { label: "产品手册", url: "#" } },
        { text: "练习流畅的表达。", resource: { label: "沟通技巧课程", url: "#" } },
      ]
      break
    case "course-002": // 高净值客户资产配置
      communicationScore = Math.floor(Math.random() * (98 - 80 + 1)) + 80
      professionalismScore = Math.floor(Math.random() * (95 - 80 + 1)) + 80
      complianceScore = Math.floor(Math.random() * (90 - 75 + 1)) + 75
      managerFeedback = "学员对高净值客户需求把握精准，方案介绍清晰，风险披露到位。但在促成签约环节可以更主动。"
      complianceIssues = [{ type: "合规通过", description: "本次演练合规表现良好。", status: "通过" }]
      quantifiedMetrics = [
        { dimension: "方案匹配度", target: "≥90%", actual: "95%", deviation: "无" },
        { dimension: "风险披露充分性", target: "≥90%", actual: "90%", deviation: "无" },
      ]
      improvementSuggestions = [
        { text: "提升促成技巧。", resource: { label: "促成技巧课程", url: "#" } },
        { text: "学习更多高净值客户案例。", resource: { label: "高净值案例库", url: "#" } },
      ]
      break
    case "course-006": // 客户投诉处理
      communicationScore = Math.floor(Math.random() * (90 - 75 + 1)) + 75
      professionalismScore = Math.floor(Math.random() * (85 - 70 + 1)) + 70
      complianceScore = Math.floor(Math.random() * (92 - 80 + 1)) + 80
      managerFeedback = "学员在安抚客户情绪方面做得很好，但解决问题时可以提供更多元化的方案选择。"
      complianceIssues = [
        { type: "风险隐瞒", description: "未充分披露产品潜在风险。", status: "需校准" },
        { type: "合规通过", description: "本次演练合规表现良好。", status: "通过" },
      ]
      quantifiedMetrics = [
        { dimension: "情绪安抚有效性", target: "≥80%", actual: "85%", deviation: "无" },
        { dimension: "问题解决效率", target: "≥70%", actual: "75%", deviation: "可提供更多方案" },
      ]
      improvementSuggestions = [
        { text: "学习更多投诉处理案例。", resource: { label: "投诉处理案例库", url: "#" } },
        { text: "提升问题解决能力。", resource: { label: "问题解决课程", url: "#" } },
      ]
      break
    case "personalized": // 个性化课程
    default:
      communicationScore = Math.floor(Math.random() * (90 - 60 + 1)) + 60
      professionalismScore = Math.floor(Math.random() * (88 - 60 + 1)) + 60
      complianceScore = Math.floor(Math.random() * (80 - 60 + 1)) + 60
      managerFeedback = "本次个性化培训表现中规中矩，建议针对薄弱环节加强练习。"
      complianceIssues = [{ type: "合规通过", description: "本次演练合规表现良好。", status: "通过" }]
      quantifiedMetrics = [
        { dimension: "自定义指标1", target: "≥70%", actual: "65%", deviation: "未达标" },
        { dimension: "自定义指标2", target: "≥75%", actual: "70%", deviation: "有待提升" },
      ]
      improvementSuggestions = [{ text: "根据个性化需求加强练习。", resource: { label: "个性化学习路径", url: "#" } }]
      break
  }

  return {
    communicationScore,
    professionalismScore,
    complianceScore,
    managerFeedback,
    complianceIssues,
    quantifiedMetrics,
    improvementSuggestions,
  }
}

export function PracticeSession() {
  const searchParams = useSearchParams()
  const courseId = searchParams.get("courseId")
  const customerId = searchParams.get("customerId")
  const courseType = searchParams.get("courseType")

  // 常量配置
  const AI_AGENT_URL = "http://localhost:8000/chat"

  // 状态管理
  const [course, setCourse] = useState<Course | null>(null)
  const [customer, setCustomer] = useState<VirtualCustomerWithLabels | null>(null)
  const [script, setScript] = useState<PracticeScript | null>(null)
  const [recordId, setRecordId] = useState<number | null>(null)
  const [sending, setSending] = useState(false)

  // 对话消息状态
  const isSpecificScenario = courseId === "course-001" && customerId === "customer-002"
  const [messages, setMessages] = useState<Message[]>(
    isSpecificScenario
      ? fixedDialogue_course001_customer002_initial
      : [],
  )
  const [input, setInput] = useState("")
  const [isTrainingCompleted, setIsTrainingCompleted] = useState(false)
  const [evaluationResults, setEvaluationResults] = useState<EvaluationResult | null>(null)
  const messagesEndRef = useRef<HTMLDivElement>(null)

  const scrollToBottom = () => {
    messagesEndRef.current?.scrollIntoView({ behavior: "smooth" })
  }

  useEffect(scrollToBottom, [messages])

  // 加载课程、虚拟客户、剧本数据,并检查/创建练习记录
  useEffect(() => {
    async function loadData() {
      // 如果是演示场景,跳过数据加载
      if (isSpecificScenario) {
        console.log("演示场景,使用固定对话数据")
        return
      }

      try {
        let courseData: Course | null = null
        let customerWithLabels: VirtualCustomerWithLabels | null = null
        const currentUserId = 1 // TODO: 从登录信息获取真实用户ID

        // 1. 加载课程数据
        if (courseId) {
          courseData = await getCourseById(Number(courseId))
          setCourse(courseData)

          // 2. 如果课程有关联剧本,加载剧本内容
          if (courseData.scriptId) {
            try {
              const scriptData = await getPracticeScriptById(courseData.scriptId)
              setScript(scriptData)
              console.log("✓ 剧本加载成功:", scriptData.name)
            } catch (error) {
              console.error("✗ 加载剧本失败:", error)
            }
          }
        }

        // 3. 加载虚拟客户数据
        if (customerId) {
          const customerData = await getVirtualCustomerById(Number(customerId))
          customerWithLabels = await convertVirtualCustomerDict(customerData)
          setCustomer(customerWithLabels)
          console.log("✓ 虚拟客户加载成功:", customerWithLabels.name)
        }

        // 4. 检查是否存在未完成的练习记录
        if (courseId && customerId) {
          try {
            // 4.1 查询未完成的记录
            const existingRecord = await findUnfinishedRecord(
              Number(courseId),
              Number(customerId),
              currentUserId,
            )

            if (existingRecord) {
              // 4.2 存在未完成记录,加载历史会话
              console.log("✓ 找到未完成的练习记录, ID:", existingRecord.id)
              setRecordId(existingRecord.id!)

              // 加载历史对话
              const conversations = await getConversationListByRecordId(existingRecord.id!)
              console.log("✓ 加载历史对话,共", conversations.length, "条")

              // 转换为前端消息格式
              const historyMessages: Message[] = conversations.map((conv) => ({
                id: conv.id?.toString() || `msg-${conv.sequenceNo}`,
                sender: conv.role === "student" ? "user" : "ai",
                text: conv.messageContent,
              }))

              setMessages(historyMessages)
              console.log("✓ 历史会话已恢复")
            } else {
              // 4.3 不存在未完成记录,创建新记录
              console.log("未找到未完成记录,创建新的练习记录")
              const newRecordId = await createPracticeUserRecord({
                courseId: Number(courseId),
                vcustomerId: Number(customerId),
                userId: currentUserId,
                startTime: new Date().toISOString(),
                status: "in_progress",
              })
              setRecordId(newRecordId)
              console.log("✓ 创建练习记录成功, ID:", newRecordId)

              // 添加初始欢迎消息
              const welcomeMessage: Message = {
                id: "welcome",
                sender: "ai",
                text: `您好!我是 ${customerWithLabels?.name || "虚拟客户"}。我们现在开始 ${courseData?.name || "陪练课程"}。请开始您的话术练习吧!`,
              }
              setMessages([welcomeMessage])
            }
          } catch (error) {
            console.error("✗ 处理练习记录失败:", error)
          }
        }
      } catch (error) {
        console.error("✗ 加载数据失败:", error)
      }
    }
    loadData()
  }, [courseId, customerId, isSpecificScenario])

  const handleSendMessage = async () => {
    if (isSpecificScenario) {
      console.log("This is a fixed scenario, input is disabled.")
      return
    }
    if (!input.trim() || !recordId) {
      console.log("输入为空或无记录ID,跳过发送")
      return
    }

    const userMessage = input.trim()
    const newUserMessage: Message = { id: Date.now().toString(), sender: "user", text: userMessage }
    setMessages((prevMessages) => [...prevMessages, newUserMessage])
    setInput("")
    setSending(true)

    // 创建一个临时的 AI 消息用于流式更新
    const aiMessageId = (Date.now() + 1).toString()
    const aiMessage: Message = {
      id: aiMessageId,
      sender: "ai",
      text: "",
    }
    setMessages((prevMessages) => [...prevMessages, aiMessage])

    try {
      // 1. 保存用户消息到数据库
      const nextSequenceNo = messages.length + 1
      await createPracticeConversation({
        recordId,
        sequenceNo: nextSequenceNo,
        role: "student",
        messageContent: userMessage,
        messageTime: new Date().toISOString(),
      })

      // 2. 构建历史对话 (转换为 AI 需要的格式)
      const history = messages.map((msg) => ({
        role: msg.sender === "user" ? "user" : "assistant",
        content: msg.text,
      }))

      // 3. 构建虚拟客户画像
      const profile: string[] = []
      if (customer) {
        if (customer.age) profile.push(`${customer.age}岁`)
        if (customer.occupationLabel || customer.occupation)
          profile.push(customer.occupationLabel || customer.occupation || "")
        if (customer.industryLabel || customer.industry)
          profile.push(`${customer.industryLabel || customer.industry}行业`)
        if (customer.personalityTypeLabel || customer.personalityType)
          profile.push(`性格:${customer.personalityTypeLabel || customer.personalityType}`)
        if (customer.riskPreferenceLabel || customer.riskPreference)
          profile.push(`风险偏好:${customer.riskPreferenceLabel || customer.riskPreference}`)
      }

      // 4. 调用 AI Agent (流式输出)
      const response = await fetch(AI_AGENT_URL, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          message: userMessage,
          stream: true, // 启用流式输出
          // 动态提示词参数
          course_script: script?.contentEdit || script?.content || "",
          virtual_customer_name: customer?.name || "客户",
          virtual_customer_profile: profile.join(", "),
          // 历史对话
          history: history,
        }),
      })

      if (!response.ok) {
        throw new Error(`AI 响应失败: ${response.statusText}`)
      }

      // 5. 处理流式响应
      const reader = response.body?.getReader()
      const decoder = new TextDecoder()
      let aiResponseText = ""

      if (reader) {
        while (true) {
          const { done, value } = await reader.read()
          if (done) break

          const chunk = decoder.decode(value, { stream: true })
          const lines = chunk.split("\n")

          for (const line of lines) {
            if (line.startsWith("data: ")) {
              const data = line.slice(6).trim()
              if (!data) continue // 跳过空数据

              try {
                const parsed = JSON.parse(data)

                // 根据后端返回的事件类型处理
                if (parsed.type === "data" && parsed.content) {
                  // 接收到文本片段
                  aiResponseText += parsed.content
                  // 实时更新 AI 消息
                  setMessages((prevMessages) =>
                    prevMessages.map((msg) =>
                      msg.id === aiMessageId ? { ...msg, text: aiResponseText } : msg,
                    ),
                  )
                } else if (parsed.type === "done") {
                  // 流式传输完成
                  console.log("流式响应完成")
                  break
                } else if (parsed.type === "error") {
                  // 发生错误
                  console.error("AI 响应错误:", parsed.message)
                  aiResponseText = `错误: ${parsed.message}`
                  setMessages((prevMessages) =>
                    prevMessages.map((msg) =>
                      msg.id === aiMessageId ? { ...msg, text: aiResponseText } : msg,
                    ),
                  )
                  break
                }
              } catch (e) {
                // 忽略解析错误
                console.warn("解析 SSE 数据失败:", line, e)
              }
            }
          }
        }
      }

      if (!aiResponseText) {
        aiResponseText = "抱歉,我无法回答"
        setMessages((prevMessages) =>
          prevMessages.map((msg) => (msg.id === aiMessageId ? { ...msg, text: aiResponseText } : msg)),
        )
      }

      // 6. 保存 AI 响应到数据库
      await createPracticeConversation({
        recordId,
        sequenceNo: nextSequenceNo + 1,
        role: "virtual_customer",
        messageContent: aiResponseText,
        messageTime: new Date().toISOString(),
      })

      console.log("消息发送成功")
    } catch (error) {
      console.error("发送消息失败:", error)
      alert("发送消息失败,请检查 AI 服务是否启动")
      // 移除失败的 AI 消息
      setMessages((prevMessages) => prevMessages.filter((msg) => msg.id !== aiMessageId))
    } finally {
      setSending(false)
    }
  }

  const handleKeyDown = (e: React.KeyboardEvent<HTMLInputElement>) => {
    if (e.key === "Enter" && !isSpecificScenario) {
      handleSendMessage()
    }
  }

  const handleCompleteTraining = async () => {
    if (!recordId || !courseId || !customerId) {
      console.error("无法完成培训: 缺少必要参数", { recordId, courseId, customerId })
      return
    }

    try {
      const currentUserId = 1 // TODO: 从登录信息获取真实用户ID

      // 1. 调用后端 API 更新练习记录状态为已完成
      console.log("正在完成练习记录, ID:", recordId)
      await completePracticeUserRecord(
        recordId,
        Number(courseId),
        Number(customerId),
        currentUserId
      )
      console.log("✓ 练习记录已标记为完成")

      // 2. 使用模拟评估显示结果 (TODO: 未来可接入真实的 AI 评估服务)
      const results = simulateEvaluation(courseId || "", customerId || "")
      setEvaluationResults(results)
      setIsTrainingCompleted(true)
    } catch (error) {
      console.error("✗ 完成培训失败:", error)
      alert("完成培训失败,请稍后重试")
    }
  }

  return (
    <Card className="w-full max-w-3xl border border-gray-700 bg-[#2a2a2a] text-white shadow-lg">
      <CardHeader className="border-b border-gray-700">
        <CardTitle className="text-xl text-gray-200">
          陪练会话: {course?.name || "课程加载中..."}
        </CardTitle>
        <p className="text-sm text-gray-400">
          当前虚拟客户: {customer
            ? `${customer.name} | ${customer.occupationLabel || customer.occupation || '未知职业'} | ${customer.age || '未知'}岁 | ${customer.genderLabel || ''} ${customer.riskPreferenceLabel || customer.riskPreference || '未知'}`
            : "客户加载中..."}
        </p>
      </CardHeader>
      <CardContent className="p-4">
        {!isTrainingCompleted ? (
          <ScrollArea className="h-[400px] pr-4">
            <div className="space-y-4">
              {messages.map((message) => (
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
              ))}
              {isSpecificScenario && (
                <details className="mt-4 p-2 rounded-lg bg-gray-800 text-gray-300">
                  <summary className="cursor-pointer font-semibold text-gray-200">📌 展开完整对话</summary>
                  <div className="space-y-4 mt-2">
                    {fixedDialogue_course001_customer002_details.map((message) => (
                      <div
                        key={message.id}
                        className={`flex items-start gap-3 ${
                          message.sender === "user" ? "justify-end" : "justify-start"
                        }`}
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
                    ))}
                  </div>
                </details>
              )}
              <div ref={messagesEndRef} />
            </div>
          </ScrollArea>
        ) : (
          // Training Results and Evaluation Section
          <div className="space-y-6 py-4">
            <h2 className="text-2xl font-bold text-gray-200 text-center">培训结果评估</h2>
            {evaluationResults && (
              <div className="space-y-4">
                {/* 核心得分 */}
                <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                  <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
                    <h3 className="text-sm text-gray-400">沟通逻辑得分</h3>
                    <div className="text-2xl font-bold text-blue-400">{evaluationResults.communicationScore}%</div>
                    <Progress
                      value={evaluationResults.communicationScore}
                      className="h-2 bg-gray-700 [&>*]:bg-blue-500"
                    />
                  </Card>
                  <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
                    <h3 className="text-sm text-gray-400">专业能力得分</h3>
                    <div className="text-2xl font-bold text-purple-400">{evaluationResults.professionalismScore}%</div>
                    <Progress
                      value={evaluationResults.professionalismScore}
                      className="h-2 bg-gray-700 [&>*]:bg-purple-500"
                    />
                  </Card>
                  <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
                    <h3 className="text-sm text-gray-400">合规表现得分</h3>
                    <div className="text-2xl font-bold text-green-400">{evaluationResults.complianceScore}%</div>
                    <Progress
                      value={evaluationResults.complianceScore}
                      className="h-2 bg-gray-700 [&>*]:bg-green-500"
                    />
                  </Card>
                </div>

                {/* 客户经理评估 */}
                <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
                  <h3 className="text-lg font-semibold text-gray-200 mb-2">客户经理评估</h3>
                  <p className="text-gray-300 text-sm">{evaluationResults.managerFeedback}</p>
                </Card>

                {/* 合规问题 */}
                <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
                  <h3 className="text-lg font-semibold text-gray-200 mb-2">合规校验结果</h3>
                  <div className="space-y-3">
                    {evaluationResults.complianceIssues.map((issue, index) => (
                      <div
                        key={index}
                        className={`flex items-start gap-3 rounded-md p-2 ${
                          issue.status === "通过" ? "bg-green-900/20" : "bg-red-900/20"
                        }`}
                      >
                        {issue.status === "通过" ? (
                          <CheckCircle className="h-5 w-5 flex-shrink-0 text-green-500" />
                        ) : (
                          <XCircle className="h-5 w-5 flex-shrink-0 text-red-500" />
                        )}
                        <div>
                          <h4 className="font-semibold text-gray-200">{issue.type}</h4>
                          <p className="text-sm text-gray-300">{issue.description}</p>
                        </div>
                      </div>
                    ))}
                  </div>
                </Card>

                {/* 培训效果评估量化指标 (新增部分) */}
                {evaluationResults.quantifiedMetrics && evaluationResults.quantifiedMetrics.length > 0 && (
                  <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
                    <CardHeader>
                      <CardTitle className="text-xl text-gray-200">培训效果评估量化指标</CardTitle>
                    </CardHeader>
                    <CardContent>
                      <Table className="border border-gray-700">
                        <TableHeader>
                          <TableRow className="border-gray-700 bg-[#1a1a1a]">
                            <TableHead className="text-gray-300">评估维度</TableHead>
                            <TableHead className="text-gray-300">达标值</TableHead>
                            <TableHead className="text-gray-300">实测值</TableHead>
                            <TableHead className="text-gray-300">偏差分析</TableHead>
                          </TableRow>
                        </TableHeader>
                        <TableBody>
                          {evaluationResults.quantifiedMetrics.map((metric, index) => (
                            <TableRow key={index} className="border-gray-700">
                              <TableCell className="font-medium">{metric.dimension}</TableCell>
                              <TableCell>{metric.target}</TableCell>
                              <TableCell>{metric.actual}</TableCell>
                              <TableCell>{metric.deviation}</TableCell>
                            </TableRow>
                          ))}
                        </TableBody>
                      </Table>
                    </CardContent>
                  </Card>
                )}

                {/* 后续提升建议 (新增部分) */}
                {evaluationResults.improvementSuggestions && evaluationResults.improvementSuggestions.length > 0 && (
                  <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
                    <CardHeader>
                      <CardTitle className="text-xl text-gray-200">后续提升建议</CardTitle>
                    </CardHeader>
                    <CardContent className="space-y-4">
                      {evaluationResults.improvementSuggestions.map((suggestion, index) => (
                        <div key={index} className="rounded-md border border-gray-600 p-4">
                          <p className="text-gray-300">{suggestion.text}</p>
                          {suggestion.resource && (
                            <Button variant="link" className="mt-2 p-0 text-blue-400 hover:text-blue-300">
                              <ExternalLink className="mr-1 h-4 w-4" />
                              <a href={suggestion.resource.url} target="_blank" rel="noopener noreferrer">
                                {suggestion.resource.label}
                              </a>
                            </Button>
                          )}
                        </div>
                      ))}
                    </CardContent>
                  </Card>
                )}
              </div>
            )}
          </div>
        )}
      </CardContent>
      <CardFooter className="flex items-center gap-2 border-t border-gray-700 p-4">
        {!isTrainingCompleted ? (
          <>
            <Button
              variant="ghost"
              size="icon"
              className="rounded-full bg-gray-800 text-white hover:bg-gray-700"
              disabled={isSpecificScenario}
            >
              <Mic className="h-5 w-5" />
            </Button>
            <Button
              variant="ghost"
              size="icon"
              className="rounded-full bg-gray-800 text-white hover:bg-gray-700"
              disabled={isSpecificScenario}
            >
              <Volume2 className="h-5 w-5" />
            </Button>
            <Input
              placeholder="输入您的话术..."
              className="flex-1 rounded-full border border-gray-600 bg-[#1a1a1a] px-4 py-2 text-white placeholder:text-gray-400 focus:border-blue-500 focus:ring-blue-500"
              value={input}
              onChange={(e) => setInput(e.target.value)}
              onKeyDown={handleKeyDown}
              disabled={isSpecificScenario || sending}
            />
            <Button
              onClick={handleSendMessage}
              className="rounded-full bg-blue-600 text-white hover:bg-blue-700"
              size="icon"
              disabled={isSpecificScenario || sending}
            >
              <Send className="h-5 w-5" />
            </Button>
            <Button
              onClick={handleCompleteTraining}
              className="ml-4 rounded-full bg-purple-600 text-white hover:bg-purple-700"
            >
              完成培训
            </Button>
          </>
        ) : (
          <Link href="/">
            <Button className="w-full rounded-full bg-blue-600 text-white hover:bg-blue-700">返回首页</Button>
          </Link>
        )}
      </CardFooter>
    </Card>
  )
}
