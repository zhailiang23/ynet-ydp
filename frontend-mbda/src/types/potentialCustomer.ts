/**
 * 潜客信息
 */
export interface PotentialCustomer {
  id: number
  customerName: string // 客户姓名
  avatar?: string // 客户头像URL
  riskLevel: string // 风险等级（AGGRESSIVE=激进型, BALANCED=稳健型, CONSERVATIVE=保守型）
  customerLevel?: string // 客户等级（A/B/C/D）
  aum: number // 资产管理规模
  potentialValue?: number // 潜在价值
  aiMatchScore: number // AI 匹配度（0-100）
  analysisConclusion?: string // AI 分析结论
  insightId?: number // 关联的洞察ID
  insightTitle?: string // 洞察标题
  status: string // 潜客状态（NEW, FOLLOWING, CONVERTED, LOST）
  source?: string // 来源
  assignedUserId?: number // 分配给的客户经理ID
  assignedUserName?: string // 客户经理姓名
  createTime?: string // 创建时间
}

/**
 * 洞察信息
 */
export interface Insight {
  id: number
  title: string
  description: string
  highIntentCustomerCount: number // 高意向客户数量
  potentialAssetValue: number // 预计资产撬动
}
