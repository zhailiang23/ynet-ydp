"use client"

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Button } from "@/components/ui/button"
import { ArrowLeft, CheckCircle, XCircle, ExternalLink } from "lucide-react"
import Link from "next/link"
import { Badge } from "@/components/ui/badge"
import { Progress } from "@/components/ui/progress"
import { ScrollArea } from "@/components/ui/scroll-area"
import { Avatar, AvatarFallback, AvatarImage } from "@/components/ui/avatar"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table" // Import Table components

interface TrainingDetailProps {
  trainingId: string
}

export function TrainingDetail({ trainingId }: TrainingDetailProps) {
  // 模拟培训详情数据
  // 在实际应用中，这里会根据 trainingId 从后端获取数据
  const mockTrainingData = {
    "training-001": {
      courseInfo: {
        name: "销售电话模拟",
        type: "预设课程",
        date: "2023-10-01",
        duration: "15分钟",
        virtualCustomer: "张先生 (企业高管)",
        scriptVersion: "V1.2",
      },
      dialogueContent: [
        { id: "d1", sender: "ai", text: "您好！我是您的AI陪练教练。我们今天将进行销售电话模拟，请您开始您的开场白。" },
        { id: "d2", sender: "user", text: "张先生您好，我是小王，请问您现在方便接听电话吗？" },
        { id: "d3", sender: "ai", text: "嗯，方便。请说。" },
        {
          id: "d4",
          sender: "user",
          text: "我了解到贵公司在AI领域取得了显著成就，我们公司专注于提供AI驱动的销售工具，旨在帮助企业提升销售效率。",
        },
        { id: "d5", sender: "ai", text: "听起来不错，但价格方面有什么优势吗？" },
        {
          id: "d6",
          sender: "user",
          text: "我们的产品虽然初期投入较高，但长期来看能为您节省大量人力成本，并带来更高的转化率，投资回报率非常可观。",
        },
        { id: "d7", sender: "ai", text: "我考虑一下，谢谢您的介绍。" },
        {
          id: "d8",
          sender: "user",
          text: "好的，张先生，为了更好地了解贵公司需求，我希望能邀请您参加一个15分钟的线上演示，您看本周三下午2点或者周四上午10点，哪个时间更方便您？",
        },
        { id: "d9", sender: "ai", text: "周四上午10点吧。" },
        { id: "d10", sender: "user", text: "好的，我稍后会把会议链接发到您的邮箱。期待与您进一步交流！" },
      ],
      evaluationResult: {
        communicationScore: 85,
        professionalismScore: 90,
        complianceScore: 78,
        managerFeedback:
          "学员在开场白和产品介绍方面表现良好，但在处理价格异议时略显生硬，使用了“投资回报率非常可观”这种略带承诺的表述，需加强话术灵活性和合规性。",
        complianceIssues: [
          { type: "敏感词触发", description: "在提及收益时使用了“非常可观”等敏感词。", status: "需校准" },
          { type: "合规通过", description: "本次演练合规表现良好。", status: "通过" },
        ],
      },
      improvementSuggestions: [
        { text: "异议处理话术需补充风险缓释逻辑，避免直接承诺收益。", resource: { label: "风险缓释课程", url: "#" } },
        { text: "在需求挖掘阶段，尝试使用开放式问题引导客户深入表达。", resource: { label: "需求挖掘模板", url: "#" } },
        {
          text: "提升产品知识应用能力，确保在客户提问时能迅速给出准确信息。",
          resource: { label: "产品知识库", url: "#" },
        },
      ],
      quantifiedMetrics: [], // Not applicable for this training
    },
    "training-002": {
      courseInfo: {
        name: "自定义：应对客户投诉",
        type: "个性化课程",
        date: "2023-10-15",
        duration: "20分钟",
        virtualCustomer: "李女士 (自由职业者)",
        scriptVersion: "V1.0",
      },
      dialogueContent: [
        { id: "d1", sender: "ai", text: "我很生气！你们的服务太差了！" },
        {
          id: "d2",
          sender: "user",
          text: "李女士您好，非常抱歉让您有不愉快的体验。请您详细告诉我发生了什么，我在这里认真倾听。",
        },
        { id: "d3", sender: "ai", text: "我上周购买的那个理财产品，说好的收益，现在根本没达到！" },
        {
          id: "d4",
          sender: "user",
          text: "我完全理解您的感受，这确实让人很沮丧。请问您能告诉我您购买的产品名称和购买日期吗？",
        },
        { id: "d5", sender: "ai", text: "是XX基金，上个月10号买的！我要求退款！" },
        {
          id: "d6",
          sender: "user",
          text: "李女士，我查到您的产品是浮动收益型基金，其收益会受市场波动影响。我们合同中也明确说明了这一点。不过，我理解您对收益的期望，我们可以为您提供一份详细的市场分析报告，并重新评估您的资产配置方案。",
        },
        { id: "d7", sender: "ai", text: "那你们打算怎么解决这个问题？" },
        {
          id: "d8",
          sender: "user",
          text: "我们建议您先查看这份报告，了解市场情况。同时，如果您愿意，我们可以安排一次专业的理财规划师与您进行一对一沟通，根据您最新的风险承受能力和目标，调整您的投资组合。",
        },
        { id: "d9", sender: "ai", text: "好吧，我接受这个方案，希望你们能改进。" },
      ],
      evaluationResult: {
        communicationScore: 92,
        professionalismScore: 85,
        complianceScore: 90,
        managerFeedback:
          "学员在安抚客户情绪和解释产品特性方面表现出色，能够有效引用合规条款。但在提供解决方案时，可以更积极地引导客户接受现有方案的价值。",
        complianceIssues: [{ type: "合规通过", description: "本次演练合规表现良好。", status: "通过" }],
      },
      improvementSuggestions: [
        {
          text: "在解释浮动收益产品时，可以更强调长期投资的价值和市场周期性。",
          resource: { label: "基金知识进阶", url: "#" },
        },
        { text: "尝试在解决方案中加入更多个性化服务，提升客户满意度。", resource: { label: "客户关系管理", url: "#" } },
      ],
      quantifiedMetrics: [], // Not applicable for this training
    },
    "training-005": {
      courseInfo: {
        name: "高价值理财产品推荐",
        type: "个性化课程",
        date: "2023-12-01",
        duration: "30分钟",
        virtualCustomer: "李女士 (自由职业者)",
        scriptVersion: "V1.0",
      },
      dialogueContent: [
        { id: "d1", sender: "user", text: "李女士，根据大数据分析，您应该购买我们的明星理财组合" },
        { id: "d2", sender: "ai", text: "你们和暴雷的那家机构有什么区别？" },
        { id: "d3", sender: "user", text: "我们是正规银行，他们是不合规平台" },
        { id: "d4", sender: "ai", text: "能保证资金安全吗？" },
        { id: "d5", sender: "user", text: "我们的产品从没让客户亏过钱" },
        { id: "d6", sender: "ai", text: "收益率是怎么计算的？" },
        { id: "d7", sender: "user", text: "年化4.5%，比存款高很多" },
        { id: "d8", sender: "ai", text: "需要投资多久？" },
        { id: "d9", sender: "user", text: "最少1年起，时间越长收益越高" },
        { id: "d10", sender: "ai", text: "..." }, // Placeholder for intermediate dialogue
        { id: "d11", sender: "user", text: "..." }, // Placeholder for intermediate dialogue
        { id: "d12", sender: "ai", text: "这个风险等级R2是什么意思？" },
        { id: "d13", sender: "user", text: "就是中等风险，适合大部分人" },
        { id: "d14", sender: "ai", text: "如果急用钱怎么办？" },
        { id: "d15", sender: "user", text: "可以质押贷款，实时到账" },
        { id: "d16", sender: "ai", text: "我需要再考虑下..." },
        { id: "d17", sender: "user", text: "明天活动截止，错过就没有了" },
        { id: "d18", sender: "ai", text: "那先买1万试试吧" },
      ],
      evaluationResult: {
        communicationScore: 40, // Based on "连续3次打断客户提问", "命令式措辞"
        professionalismScore: 35, // Based on "未提供官方证明材料", "未说明浮动收益特性", "未解释具体波动范围", "未提示提前赎回费用"
        complianceScore: 25, // Based on "保本暗示", "虚假宣传", "风险隐瞒", "制造虚假紧迫感"
        managerFeedback:
          "本次培训存在多处合规漏洞和沟通技巧缺陷。话术违规、风险提示缺失、需求诊断错误。学员连续3次打断客户提问，使用了“您必须”、“应该要”等命令式措辞，且未回应客户提到的子女教育资金需求。",
        complianceIssues: [
          {
            type: "保本暗示",
            description: "多次使用“从没让客户亏过钱”、“历史100%兑付”等暗示保本的表述。",
            status: "需校准",
          },
          { type: "虚假宣传", description: "使用“同类收益最高”等夸大宣传。", status: "需校准" },
          { type: "风险隐瞒", description: "未说明产品底层资产、未解释风险等级具体波动范围。", status: "需校准" },
          { type: "制造虚假紧迫感", description: "使用“明天活动截止，错过就没有了”等虚假营销手段。", status: "需校准" },
        ],
      },
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
      quantifiedMetrics: [
        { dimension: "话术合规率", target: "≥98%", actual: "83%", deviation: "7次违规表述" },
        { dimension: "需求匹配度", target: "≥85%", actual: "62%", deviation: "未完成财务诊断问卷" },
        { dimension: "促成转化率", target: "≥40%", actual: "22%", deviation: "过早进入促成阶段" },
      ],
    },
  }

  const trainingData = mockTrainingData[trainingId as keyof typeof mockTrainingData]

  if (!trainingData) {
    return (
      <div className="text-center py-20 text-gray-400">
        <p>培训详情 {trainingId} 未找到。</p>
        <Link href="/training-results">
          <Button variant="link" className="mt-4 text-blue-400 hover:text-blue-300">
            返回培训结果列表
          </Button>
        </Link>
      </div>
    )
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
        <h1 className="text-3xl font-bold text-gray-200">培训详情：{trainingData.courseInfo.name}</h1>
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
            <p className="text-lg font-medium text-gray-200">{trainingData.courseInfo.name}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">课程类型</p>
            <Badge
              className={
                trainingData.courseInfo.type === "预设课程" ? "bg-blue-600 text-white" : "bg-purple-600 text-white"
              }
            >
              {trainingData.courseInfo.type}
            </Badge>
          </div>
          <div>
            <p className="text-sm text-gray-400">培训日期</p>
            <p className="text-lg font-medium text-gray-200">{trainingData.courseInfo.date}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">培训时长</p>
            <p className="text-lg font-medium text-gray-200">{trainingData.courseInfo.duration}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">虚拟客户</p>
            <p className="text-lg font-medium text-gray-200">{trainingData.courseInfo.virtualCustomer}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">剧本版本</p>
            <p className="text-lg font-medium text-gray-200">{trainingData.courseInfo.scriptVersion}</p>
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
              {trainingData.dialogueContent.map((message) => (
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
          {/* 核心得分 */}
          <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
              <h3 className="text-sm text-gray-400">沟通逻辑得分</h3>
              <div className="text-2xl font-bold text-blue-400">
                {trainingData.evaluationResult.communicationScore}%
              </div>
              <Progress
                value={trainingData.evaluationResult.communicationScore}
                className="h-2 bg-gray-700 [&>*]:bg-blue-500"
              />
            </Card>
            <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
              <h3 className="text-sm text-gray-400">专业能力得分</h3>
              <div className="text-2xl font-bold text-purple-400">
                {trainingData.evaluationResult.professionalismScore}%
              </div>
              <Progress
                value={trainingData.evaluationResult.professionalismScore}
                className="h-2 bg-gray-700 [&>*]:bg-purple-500"
              />
            </Card>
            <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
              <h3 className="text-sm text-gray-400">合规表现得分</h3>
              <div className="text-2xl font-bold text-green-400">{trainingData.evaluationResult.complianceScore}%</div>
              <Progress
                value={trainingData.evaluationResult.complianceScore}
                className="h-2 bg-gray-700 [&>*]:bg-green-500"
              />
            </Card>
          </div>

          {/* 客户经理评估 */}
          <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
            <h3 className="text-lg font-semibold text-gray-200 mb-2">客户经理评估</h3>
            <p className="text-gray-300 text-sm">{trainingData.evaluationResult.managerFeedback}</p>
          </Card>

          {/* 合规问题 */}
          <Card className="border border-gray-700 bg-[#1a1a1a] p-4">
            <h3 className="text-lg font-semibold text-gray-200 mb-2">合规校验结果</h3>
            <div className="space-y-3">
              {trainingData.evaluationResult.complianceIssues.map((issue, index) => (
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
        </CardContent>
      </Card>

      {/* 培训效果评估量化指标 (新增部分) */}
      {trainingData.quantifiedMetrics && trainingData.quantifiedMetrics.length > 0 && (
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
                {trainingData.quantifiedMetrics.map((metric, index) => (
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

      {/* 后续提升建议 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">后续提升建议</CardTitle>
        </CardHeader>
        <CardContent className="space-y-4">
          {trainingData.improvementSuggestions.map((suggestion, index) => (
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
    </div>
  )
}
