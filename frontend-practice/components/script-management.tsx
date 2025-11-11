import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select" // Import Select components

export function ScriptManagement() {
  // 模拟案例库中的案例数据
  const availableCases = [
    { id: "case-001", name: "保险理赔争议案例" },
    { id: "case-002", name: "理财暴雷应对案例" },
    { id: "case-003", name: "客户投诉处理场景" },
    { id: "case-004", name: "新产品推介失败分析" },
  ]

  // 模拟虚拟客户角色数据
  const virtualCustomers = [
    { id: "vc-001", name: "张先生 (企业高管)" },
    { id: "vc-002", name: "李女士 (自由职业者)" },
    { id: "vc-003", name: "王先生 (技术工程师)" },
  ]

  // 模拟销售套路技巧数据
  const salesRoutines = [
    { id: "sr-001", name: "三步促成法" },
    { id: "sr-002", name: "风险置换话术模板" },
    { id: "sr-003", name: "FAB法则" },
  ]

  return (
    <Card className="mb-8 border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">创建剧本</CardTitle>
      </CardHeader>
      <CardContent className="space-y-6">
        <h3 className="text-lg font-semibold text-gray-200">剧本动态生成与编辑</h3>
        <p className="text-gray-400">整合多领域案例，结合虚拟客户画像自动生成对练剧本；支持手动调整剧情分支。</p>
        <div>
          <Label htmlFor="script-name" className="text-gray-300">
            剧本名称
          </Label>
          <Input id="script-name" placeholder="输入剧本名称" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white" />
        </div>
        <div>
          <Label htmlFor="virtual-customer-role" className="text-gray-300">
            关联虚拟客户角色
          </Label>
          <Select>
            <SelectTrigger id="virtual-customer-role" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
              <SelectValue placeholder="选择虚拟客户角色" />
            </SelectTrigger>
            <SelectContent className="bg-[#2a2a2a] text-white">
              {virtualCustomers.map((customer) => (
                <SelectItem key={customer.id} value={customer.id}>
                  {customer.name}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
        <div>
          <Label htmlFor="marketing-segment" className="text-gray-300">
            营销环节
          </Label>
          <Select>
            <SelectTrigger id="marketing-segment" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
              <SelectValue placeholder="选择营销环节" />
            </SelectTrigger>
            <SelectContent className="bg-[#2a2a2a] text-white">
              <SelectItem value="prospecting">拓客</SelectItem>
              <SelectItem value="needs-analysis">需求挖掘</SelectItem>
              <SelectItem value="objection-handling">异议处理</SelectItem>
              <SelectItem value="closing">促成签约</SelectItem>
            </SelectContent>
          </Select>
        </div>
        <div>
          <Label htmlFor="complexity-setting" className="text-gray-300">
            场景复杂度
          </Label>
          <Select>
            <SelectTrigger id="complexity-setting" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
              <SelectValue placeholder="选择场景复杂度" />
            </SelectTrigger>
            <SelectContent className="bg-[#2a2a2a] text-white">
              <SelectItem value="simple">简单场景</SelectItem>
              <SelectItem value="complex">复杂场景</SelectItem>
            </SelectContent>
          </Select>
        </div>
        <div>
          <Label htmlFor="upload-file" className="text-gray-300">
            上传文件 (作为剧本内容来源)
          </Label>
          <Input
            id="upload-file"
            type="file"
            className="mt-1 border-gray-600 bg-[#1a1a1a] text-white file:text-gray-300 file:bg-gray-700 file:border-0 file:rounded-md file:px-3 file:py-1 file:mr-2"
          />
        </div>
        <div>
          <Label htmlFor="related-cases" className="text-gray-300">
            关联案例
          </Label>
          <Select>
            <SelectTrigger id="related-cases" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
              <SelectValue placeholder="选择关联案例" />
            </SelectTrigger>
            <SelectContent className="bg-[#2a2a2a] text-white">
              {availableCases.map((caseItem) => (
                <SelectItem key={caseItem.id} value={caseItem.id}>
                  {caseItem.name}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
        <div>
          <Label htmlFor="sales-routine-skill" className="text-gray-300">
            关联销售套路技巧
          </Label>
          <Select>
            <SelectTrigger id="sales-routine-skill" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
              <SelectValue placeholder="选择销售套路技巧" />
            </SelectTrigger>
            <SelectContent className="bg-[#2a2a2a] text-white">
              {salesRoutines.map((routine) => (
                <SelectItem key={routine.id} value={routine.id}>
                  {routine.name}
                </SelectItem>
              ))}
            </SelectContent>
          </Select>
        </div>
        <div>
          <Label htmlFor="generated-script-content" className="text-gray-300">
            自动生成剧本内容
          </Label>
          <Textarea
            id="generated-script-content"
            placeholder="AI将根据配置自动生成剧本内容..."
            className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            rows={8}
            readOnly
          />
          <Button className="mt-2 bg-green-600 hover:bg-green-700 text-white">生成剧本</Button>
        </div>
        <div>
          <Label htmlFor="manual-adjustments" className="text-gray-300">
            手动调整剧情分支
          </Label>
          <Textarea
            id="manual-adjustments"
            placeholder="输入手动调整，如：客户突然离场，竞品对比提问"
            className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            rows={4}
          />
        </div>
        <Button className="bg-blue-600 hover:bg-blue-700 text-white">保存剧本</Button>
      </CardContent>
    </Card>
  )
}
