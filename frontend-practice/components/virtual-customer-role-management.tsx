import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import { Input } from "@/components/ui/input"
import { Textarea } from "@/components/ui/textarea"
import { Button } from "@/components/ui/button"
import { Label } from "@/components/ui/label"
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select"
import { Table, TableBody, TableCell, TableHead, TableHeader, TableRow } from "@/components/ui/table"

export function VirtualCustomerRoleManagement() {
  // 模拟现有客户画像数据
  const existingCustomers = [
    {
      id: "c001",
      name: "张先生",
      age: 55,
      occupation: "企业高管",
      riskPreference: "稳健型",
      level: "VIP客户",
      personalityTraits: "理性, 注重细节",
    },
    {
      id: "c002",
      name: "李女士",
      age: 30,
      occupation: "自由职业者",
      riskPreference: "成长型",
      level: "普通客户",
      personalityTraits: "犹豫, 好奇",
    },
  ]

  return (
    <Card className="mb-8 border border-gray-700 bg-[#2a2a2a] text-white">
      <CardHeader>
        <CardTitle className="text-xl text-gray-200">虚拟客户角色管理</CardTitle>
      </CardHeader>
      <CardContent className="space-y-6">
        <p className="text-gray-400">
          配置多元虚拟客户角色（含年龄、职业、风险偏好、客户等级、性格特征等属性），支持自定义客户画像参数适配细分培训场景。
        </p>
        <h3 className="text-lg font-semibold text-gray-200">创建/编辑客户画像</h3>
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
          <div>
            <Label htmlFor="customer-level" className="text-gray-300">
              客户等级
            </Label>
            <Select>
              <SelectTrigger id="customer-level" className="mt-1 border-gray-600 bg-[#1a1a1a] text-white">
                <SelectValue placeholder="选择客户等级" />
              </SelectTrigger>
              <SelectContent className="bg-[#2a2a2a] text-white">
                <SelectItem value="vip">VIP客户</SelectItem>
                <SelectItem value="high-net-worth">高净值客户</SelectItem>
                <SelectItem value="standard">普通客户</SelectItem>
              </SelectContent>
            </Select>
          </div>
          <div>
            <Label htmlFor="personality-traits" className="text-gray-300">
              性格特征 (逗号分隔)
            </Label>
            <Input
              id="personality-traits"
              placeholder="例如：理性, 注重细节, 犹豫"
              className="mt-1 border-gray-600 bg-[#1a1a1a] text-white"
            />
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

        <h3 className="mt-8 text-lg font-semibold text-gray-200">现有客户画像列表</h3>
        <Table className="border border-gray-700">
          <TableHeader>
            <TableRow className="border-gray-700 bg-[#1a1a1a]">
              <TableHead className="text-gray-300">名称</TableHead>
              <TableHead className="text-gray-300">职业</TableHead>
              <TableHead className="text-gray-300">年龄</TableHead>
              <TableHead className="text-gray-300">风险偏好</TableHead>
              <TableHead className="text-gray-300">等级</TableHead>
              <TableHead className="text-gray-300">性格</TableHead>
              <TableHead className="text-gray-300">操作</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {existingCustomers.map((customer) => (
              <TableRow key={customer.id} className="border-gray-700">
                <TableCell>{customer.name}</TableCell>
                <TableCell>{customer.occupation}</TableCell>
                <TableCell>{customer.age}</TableCell>
                <TableCell>{customer.riskPreference}</TableCell>
                <TableCell>{customer.level}</TableCell>
                <TableCell>{customer.personalityTraits}</TableCell>
                <TableCell>
                  <Button variant="ghost" size="sm" className="text-blue-400 hover:text-blue-300">
                    编辑
                  </Button>
                  <Button variant="ghost" size="sm" className="text-red-400 hover:text-red-300">
                    删除
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
