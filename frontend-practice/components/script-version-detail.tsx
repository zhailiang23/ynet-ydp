"use client" // This component needs to be a client component for interactivity

import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Badge } from "@/components/ui/badge"
import { Button } from "@/components/ui/button"
import { ArrowLeft } from "lucide-react"
import Link from "next/link"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"

interface ScriptVersionDetailProps {
  scriptVersionId: string
}

export function ScriptVersionDetail({ scriptVersionId }: ScriptVersionDetailProps) {
  // 模拟剧本详情数据和所有版本数据
  // 在实际应用中，这里会根据 scriptVersionId 从后端获取数据
  // 假设后端会返回当前版本详情以及该剧本的所有历史版本
  const allScriptVersions = [
    {
      id: "s001-v1.2",
      scriptName: "销售电话剧本A",
      version: "V1.2",
      updatedAt: "2023-10-26 14:30",
      updateNotes: "新增客户离场分支，优化了开场白和产品优势介绍的话术，提升了对价格异议的应对策略。",
      status: "启用中",
      marketingSegment: "拓客",
      complexity: "简单场景",
      virtualCustomerRole: "张先生 (企业高管)",
      uploadedFile: "产品A销售手册.pdf",
      content: `
**剧本名称：销售电话模拟 - 产品A**

**版本：V1.2**

**营销环节：拓客**

**虚拟客户：张先生 (企业高管)**

---

**场景描述：**
您作为销售代表，首次致电张先生，他是一位企业高管，时间宝贵，对陌生电话有一定戒备心。您的目标是引起他的兴趣，并争取一次更深入的线上会议。

---

**对话流程：**

**1. 开场白与建立信任 (销售代表)**
*   "张先生您好，我是[您的公司名称]的[您的姓名]，冒昧打扰，请问您现在方便接听电话吗？"
*   (若客户表示不方便，尝试预约下次通话时间)
*   "我了解到贵公司在[行业/领域]取得了显著成就，我们[您的公司名称]专注于提供[您的产品/服务核心价值]，旨在帮助企业在[具体方面]提升效率/降低成本/增加收益。"

**2. 引起兴趣与初步需求探索 (销售代表)**
*   "我们最近推出了一款[产品A]，它在[核心功能1]和[核心功能2]方面表现卓越，许多像贵公司这样的客户都在使用后取得了[具体成果]。"
*   "不知道张先生目前在[相关业务领域]是否有遇到一些挑战，或者对[某个痛点]比较关注？"

**3. 客户异议处理 (张先生可能提出的异议)**
*   **异议1：没时间/不感兴趣**
    *   "不好意思，我现在很忙，对这个不感兴趣。"
    *   **销售代表应对：** "我理解您时间宝贵，我只占用您2分钟，简单介绍一下我们如何帮助贵公司在[具体方面]节省[数字]成本/提升[数字]效率。如果觉得不合适，您随时可以挂断。"
*   **异议2：价格太贵**
    *   "你们的产品听起来不错，但价格肯定很贵吧？"
    *   **销售代表应对：** "张先生，我们产品的价值远超其价格。它能帮助您在[具体方面]实现[具体收益]，从长远来看，这笔投资是非常划算的。我们也有不同的方案，可以根据您的预算和需求进行定制。"

**4. 争取下一步行动 (销售代表)**
*   "为了更好地了解贵公司的具体需求，并为您提供一个定制化的解决方案，我希望能邀请您参加一个15分钟的线上演示，您看本周三下午2点或者周四上午10点，哪个时间更方便您？"

---

**剧本结束**
      `,
    },
    {
      id: "s001-v1.1",
      scriptName: "销售电话剧本A",
      version: "V1.1",
      updatedAt: "2023-09-15 10:00",
      updateNotes: "初始版本，基础异议处理。",
      status: "已停用",
      marketingSegment: "拓客",
      complexity: "简单场景",
      virtualCustomerRole: "张先生 (企业高管)",
      uploadedFile: "产品A销售手册.pdf",
      content: `
**剧本名称：销售电话模拟 - 产品A**

**版本：V1.1**

**营销环节：拓客**

**虚拟客户：张先生 (企业高管)**

---

**场景描述：**
您作为销售代表，首次致电张先生，他是一位企业高管，时间宝贵，对陌生电话有一定戒备心。您的目标是引起他的兴趣，并争取一次更深入的线上会议。

---

**对话流程：**

**1. 开场白与建立信任 (销售代表)**
*   "张先生您好，我是[您的公司名称]的[您的姓名]，冒昧打扰，请问您现在方便接听电话吗？"
*   (若客户表示不方便，尝试预约下次通话时间)
*   "我了解到贵公司在[行业/领域]取得了显著成就，我们[您的公司名称]专注于提供[您的产品/服务核心价值]，旨在帮助企业在[具体方面]提升效率/降低成本/增加收益。"

**2. 引起兴趣与初步需求探索 (销售代表)**
*   "我们最近推出了一款[产品A]，它在[核心功能1]和[核心功能2]方面表现卓越，许多像贵公司这样的客户都在使用后取得了[具体成果]。"
*   "不知道张先生目前在[相关业务领域]是否有遇到一些挑战，或者对[某个痛点]比较关注？"

**3. 客户异议处理 (张先生可能提出的异议)**
*   **异议1：没时间/不感兴趣**
    *   "不好意思，我现在很忙，对这个不感兴趣。"
    *   **销售代表应对：** "我理解您时间宝贵，我只占用您2分钟，简单介绍一下我们如何帮助贵公司在[具体方面]节省[数字]成本/提升[数字]效率。如果觉得不合适，您随时可以挂断。"

**4. 争取下一步行动 (销售代表)**
*   "为了更好地了解贵公司的具体需求，并为您提供一个定制化的解决方案，我希望能邀请您参加一个15分钟的线上演示，您看本周三下午2点或者周四上午10点，哪个时间更方便您？"

---

**剧本结束**
      `,
    },
    {
      id: "s001-v1.0",
      scriptName: "销售电话剧本A",
      version: "V1.0",
      updatedAt: "2023-08-01 09:00",
      updateNotes: "剧本初稿。",
      status: "已停用",
      marketingSegment: "拓客",
      complexity: "简单场景",
      virtualCustomerRole: "张先生 (企业高管)",
      uploadedFile: "产品A销售手册.pdf",
      content: `
**剧本名称：销售电话模拟 - 产品A**

**版本：V1.0**

**营销环节：拓客**

**虚拟客户：张先生 (企业高管)**

---

**场景描述：**
您作为销售代表，首次致电张先生，他是一位企业高管，时间宝贵，对陌生电话有一定戒备心。您的目标是引起他的兴趣，并争取一次更深入的线上会议。

---

**对话流程：**

**1. 开场白与建立信任 (销售代表)**
*   "张先生您好，我是[您的公司名称]的[您的姓名]，冒昧打扰，请问您现在方便接听电话吗？"
*   (若客户表示不方便，尝试预约下次通话时间)
*   "我了解到贵公司在[行业/领域]取得了显著成就，我们[您的公司名称]专注于提供[您的产品/服务核心价值]，旨在帮助企业在[具体方面]提升效率/降低成本/增加收益。"

**2. 引起兴趣与初步需求探索 (销售代表)**
*   "我们最近推出了一款[产品A]，它在[核心功能1]和[核心功能2]方面表现卓越，许多像贵公司这样的客户都在使用后取得了[具体成果]。"
*   "不知道张先生目前在[相关业务领域]是否有遇到一些挑战，或者对[某个痛点]比较关注？"

**3. 争取下一步行动 (销售代表)**
*   "为了更好地了解贵公司的具体需求，并为您提供一个定制化的解决方案，我希望能邀请您参加一个15分钟的线上演示，您看本周三下午2点或者周四上午10点，哪个时间更方便您？"

---

**剧本结束**
      `,
    },
    {
      id: "s002-v1.5",
      scriptName: "高净值剧本B",
      version: "V1.5",
      updatedAt: "2023-11-01 11:00",
      updateNotes: "更新产品信息，增加风险提示。",
      status: "启用中",
      marketingSegment: "促成签约",
      complexity: "复杂场景",
      virtualCustomerRole: "李女士 (自由职业者)",
      uploadedFile: "高净值产品说明.pdf",
      content: `
**剧本名称：高净值客户资产配置**

**版本：V1.5**

**营销环节：促成签约**

**虚拟客户：李女士 (自由职业者)**

---

**场景描述：**
您作为理财顾问，与李女士进行第二次会面，她对高净值资产配置有兴趣，但对风险较为敏感。您的目标是根据她的风险偏好，推荐合适的资产配置方案并促成签约。

---

**对话流程：**

**1. 回顾与确认需求 (理财顾问)**
*   "李女士，上次我们讨论了您的财务目标和风险偏好，您提到希望在保证一定流动性的前提下，实现资产的稳健增值。我根据您的需求，为您准备了一份初步的资产配置方案。"
*   "您看，我们先回顾一下上次的讨论，确认一下您的核心需求是否有新的变化？"

**2. 方案介绍与价值阐述 (理财顾问)**
*   "基于您的稳健型风险偏好，我建议您考虑一个多元化的配置方案，其中包含[产品A]、[产品B]和[产品C]。"
*   "产品A具有[特点1]，能提供[收益特点]；产品B侧重于[特点2]，可以[风险控制特点]；产品C则能帮助您实现[特点3]，带来[长期收益特点]。"
*   "这个组合的优势在于，它能在分散风险的同时，为您带来[预期收益率]的年化收益。"

**3. 风险披露与异议处理 (李女士可能提出的异议)**
*   **异议1：风险担忧**
    *   "听起来不错，但投资总是有风险的，我担心市场波动会影响我的本金。"
    *   **理财顾问应对：** "李女士，您的担忧非常合理。任何投资都伴随风险，但我们的方案已经充分考虑了您的风险承受能力。例如，产品B就是为了在市场波动时提供缓冲，降低整体组合的风险。同时，我们会定期为您进行资产检视和调整，确保方案始终符合您的风险偏好。"
*   **异议2：收益不够高**
    *   "这个收益率好像不是很高，有没有更高收益的产品？"
    *   **理财顾问应对：** "李女士，追求高收益往往伴随着高风险。我们的目标是为您提供一个在您风险承受范围内，能够实现稳健增值的方案。如果您对更高收益有兴趣，我们可以探讨一些小比例的激进配置，但需要您充分了解并接受其潜在风险。"

**4. 促成签约 (理财顾问)**
*   "李女士，您看这份方案是否符合您的预期？如果您觉得可以，我们可以现在就启动开户流程，尽快让您的资金开始运作。"
*   "我们有专业的团队为您提供后续服务，确保您的投资旅程顺利。您看，我们现在就可以开始办理手续吗？"

---

**剧本结束**
      `,
    },
    {
      id: "s003-v1.0",
      scriptName: "投诉处理剧本C",
      version: "V1.0",
      updatedAt: "2023-11-10 09:00",
      updateNotes: "首次发布，处理客户投诉流程。",
      status: "启用中",
      marketingSegment: "客户服务",
      complexity: "复杂场景",
      virtualCustomerRole: "赵女士 (退休教师)",
      uploadedFile: "投诉处理流程.pdf",
      content: `
**剧本名称：客户投诉处理**

**版本：V1.0**

**营销环节：客户服务**

**虚拟客户：赵女士 (退休教师)**

---

**场景描述：**
您作为客服代表，接到赵女士的投诉电话，她对某项服务流程或产品使用体验非常不满，情绪激动。您的目标是安抚客户情绪，了解问题，并提供解决方案。

---

**对话流程：**

**1. 倾听与安抚 (客服代表)**
*   "赵女士您好，我是[您的公司名称]的[您的姓名]，非常抱歉让您有不愉快的体验。请您详细告诉我发生了什么，我在这里认真倾听。"
*   (让客户充分表达，不要打断，适时表示理解和同情)
*   "我完全理解您的感受，这确实让人很生气/沮丧。请您放心，我一定会尽力帮助您解决这个问题。"

**2. 确认问题与收集信息 (客服代表)**
*   "赵女士，我理解您对[具体问题]感到不满。为了更好地帮助您，请问您能告诉我[关键信息，如订单号/产品型号/发生时间]吗？"
*   "您希望我们如何解决这个问题呢？"

**3. 提供解决方案 (客服代表)**
*   (根据问题性质，提供可行方案)
*   "赵女士，针对您反映的[问题]，我们有以下几种解决方案：
    *   方案A：[具体措施]，预计[时间]完成。
    *   方案B：[具体措施]，可以为您提供[补偿/优惠]。"
*   "您看哪种方案更符合您的期望？"

**4. 确认与跟进 (客服代表)**
*   "好的，赵女士，我们确认选择方案[X]。我将立即为您处理。预计在[时间]内，您会收到[结果]。"
*   "在处理过程中，我会随时与您保持沟通。您看还有其他需要我帮助的吗？"
*   "再次感谢您的反馈，我们会不断改进服务，避免类似问题再次发生。祝您生活愉快！"

---

**剧本结束**
      `,
    },
  ]

  // 查找当前剧本的详情
  const scriptDetail = allScriptVersions.find((v) => v.id === scriptVersionId)

  // 查找当前剧本的所有版本（基于 scriptName）
  const currentScriptAllVersions = allScriptVersions
    .filter((v) => v.scriptName === scriptDetail?.scriptName)
    .sort((a, b) => new Date(b.updatedAt).getTime() - new Date(a.updatedAt).getTime()) // 按更新时间倒序

  if (!scriptDetail) {
    return (
      <div className="text-center py-20 text-gray-400">
        <p>剧本版本 {scriptVersionId} 未找到。</p>
        <Link href="/management/script-versions">
          <Button variant="link" className="mt-4 text-blue-400 hover:text-blue-300">
            返回剧本版本列表
          </Button>
        </Link>
      </div>
    )
  }

  const handleRestoreVersion = (versionId: string) => {
    // 模拟恢复旧版本操作
    alert(`已请求恢复剧本版本：${versionId}。在实际应用中，这里会调用后端API。`)
    // 实际应用中，这里可能需要刷新数据或更新UI状态
  }

  return (
    <div className="space-y-8">
      <div className="mb-6 flex items-center justify-between">
        <Link href="/management/script-versions">
          <Button variant="ghost" className="text-gray-300 hover:text-white">
            <ArrowLeft className="mr-2 h-4 w-4" />
            返回剧本版本列表
          </Button>
        </Link>
        <h1 className="text-3xl font-bold text-gray-200">剧本详情：{scriptDetail.scriptName}</h1>
        <div></div> {/* Spacer for alignment */}
      </div>

      {/* 剧本基本信息 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">基本信息</CardTitle>
        </CardHeader>
        <CardContent className="grid grid-cols-1 gap-4 md:grid-cols-2">
          <div>
            <p className="text-sm text-gray-400">剧本名称</p>
            <p className="text-lg font-medium text-gray-200">{scriptDetail.scriptName}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">当前查看版本</p>
            <p className="text-lg font-medium text-gray-200">{scriptDetail.version}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">更新时间</p>
            <p className="text-lg font-medium text-gray-200">{scriptDetail.updatedAt}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">状态</p>
            <Badge className={scriptDetail.status === "启用中" ? "bg-green-600 text-white" : "bg-gray-600 text-white"}>
              {scriptDetail.status}
            </Badge>
          </div>
          <div>
            <p className="text-sm text-gray-400">营销环节</p>
            <p className="text-lg font-medium text-gray-200">{scriptDetail.marketingSegment}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">场景复杂度</p>
            <p className="text-lg font-medium text-gray-200">{scriptDetail.complexity}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">关联虚拟客户</p>
            <p className="text-lg font-medium text-gray-200">{scriptDetail.virtualCustomerRole}</p>
          </div>
          <div>
            <p className="text-sm text-gray-400">上传文件</p>
            <p className="text-lg font-medium text-blue-400">{scriptDetail.uploadedFile}</p>
          </div>
          <div className="md:col-span-2">
            <p className="text-sm text-gray-400">版本更新说明</p>
            <p className="text-base text-gray-300">{scriptDetail.updateNotes}</p>
          </div>
        </CardContent>
      </Card>

      {/* 剧本内容展示 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">剧本内容</CardTitle>
        </CardHeader>
        <CardContent>
          <div className="prose prose-invert max-w-none text-gray-300">
            <pre className="whitespace-pre-wrap font-sans text-sm leading-relaxed">{scriptDetail.content}</pre>
          </div>
        </CardContent>
      </Card>

      {/* 所有版本迭代记录 */}
      <Card className="border border-gray-700 bg-[#2a2a2a] text-white">
        <CardHeader>
          <CardTitle className="text-xl text-gray-200">所有版本迭代记录</CardTitle>
        </CardHeader>
        <CardContent>
          <Table className="border border-gray-700">
            <TableHeader>
              <TableRow className="border-gray-700 bg-[#1a1a1a]">
                <TableHead className="text-gray-300">版本</TableHead>
                <TableHead className="text-gray-300">更新时间</TableHead>
                <TableHead className="text-gray-300">更新说明</TableHead>
                <TableHead className="text-gray-300">状态</TableHead>
                <TableHead className="text-gray-300">操作</TableHead>
              </TableRow>
            </TableHeader>
            <TableBody>
              {currentScriptAllVersions.map((version) => (
                <TableRow key={version.id} className="border-gray-700">
                  <TableCell className="font-medium">{version.version}</TableCell>
                  <TableCell>{version.updatedAt}</TableCell>
                  <TableCell>{version.updateNotes}</TableCell>
                  <TableCell>
                    <Badge
                      className={version.status === "启用中" ? "bg-green-600 text-white" : "bg-gray-600 text-white"}
                    >
                      {version.status}
                    </Badge>
                  </TableCell>
                  <TableCell>
                    <Link href={`/management/script-versions/${version.id}`}>
                      <Button variant="ghost" size="sm" className="text-blue-400 hover:text-blue-300">
                        查看详情
                      </Button>
                    </Link>
                    <Button
                      variant="ghost"
                      size="sm"
                      className="text-purple-400 hover:text-purple-300"
                      onClick={() => handleRestoreVersion(version.id)}
                      disabled={version.status === "启用中"} // 禁用当前启用中的版本
                    >
                      恢复旧版本
                    </Button>
                  </TableCell>
                </TableRow>
              ))}
            </TableBody>
          </Table>
        </CardContent>
      </Card>
    </div>
  )
}
