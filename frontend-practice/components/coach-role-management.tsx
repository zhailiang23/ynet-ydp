import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"

export function CoachRoleManagement() {
  return (
    <Card className="mb-8 border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">教练角色管理</CardTitle>
      </CardHeader>
      <CardContent className="space-y-6">
        <p className="text-gray-400">
          配置多元虚拟客户角色（含年龄、职业、风险偏好等属性），支持自定义客户画像参数适配细分培训场景。
        </p>
        <div className="grid grid-cols-1 gap-4 md:grid-cols-2">
          <div>
            <Label htmlFor="customer-name" className="text-gray-300">
              客户名称
            </Label>
            <Input
              id="customer-name"
              placeholder="输入客户名称"
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            />
          </div>
          <div>
            <Label htmlFor="age" className="text-gray-300">
              年龄
            </Label>
            <Input
              id="age"
              type="number"
              placeholder="输入年龄"
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            />
          </div>
          <div>
            <Label htmlFor="occupation" className="text-gray-300">
              职业
            </Label>
            <Input id="occupation" placeholder="输入职业" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white" />
          </div>
          <div>
            <Label htmlFor="risk-preference" className="text-gray-300">
              风险偏好
            </Label>
            <Select>
              <SelectTrigger id="risk-preference" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                <SelectValue placeholder="选择风险偏好" />
              </SelectTrigger>
              <SelectContent className="bg-[#2a2a2a] text-white">
                <SelectItem value="low">保守型</SelectItem>
                <SelectItem value="medium">稳健型</SelectItem>
                <SelectItem value="high">激进型</SelectItem>
              </SelectContent>
            </Select>
          </div>
        </div>
        <div>
          <Label htmlFor="custom-params" className="text-gray-300">
            自定义参数 (JSON)
          </Label>
          <Textarea
            id="custom-params"
            placeholder='{"教育背景": "本科", "家庭状况": "已婚有子"}'
            className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            rows={4}
          />
        </div>
        <Button className="bg-blue-600 hover:bg-blue-700 text-white">保存客户画像</Button>
      </CardContent>
    </Card>
  )
}
