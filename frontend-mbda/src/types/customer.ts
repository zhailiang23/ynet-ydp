/**
 * 客户相关类型定义
 */

/**
 * 客户类型枚举
 */
export enum CustomerType {
  RETAIL = 1, // 零售客户
  CORPORATE = 2, // 对公客户
}

/**
 * 客户状态枚举
 */
export enum CustomerStatus {
  NORMAL = 1, // 正常
  FROZEN = 2, // 冻结
  CANCELLED = 3, // 注销
}

/**
 * 分配状态枚举
 */
export enum AssignmentStatus {
  UNASSIGNED = 0, // 未分配
  ASSIGNED = 1, // 已分配
}

/**
 * 客户信息
 */
export interface Customer {
  /** 客户ID */
  id: number
  /** 客户编号 */
  customerNo: string
  /** 客户类型 */
  customerType: CustomerType
  /** 客户名称 */
  customerName: string
  /** 客户等级 */
  customerLevel?: string
  /** 客户状态 */
  customerStatus: CustomerStatus
  /** 分配状态 */
  assignmentStatus?: AssignmentStatus
  /** 是否有效客户 */
  isValid?: boolean
  /** 是否优质客户 */
  isHighQuality?: boolean
  /** 是否重要客户 */
  isImportant?: boolean
  /** 信用状态 */
  creditStatus?: string
  /** 信用等级 */
  creditLevel?: string
  /** 信用评分 */
  creditScore?: number
  /** 客户来源 */
  customerSource?: string
  /** 客户标签 */
  customerTag?: string
  /** 备注信息 */
  remark?: string
  /** 所属部门ID */
  deptId?: number
  /** 创建时间 */
  createTime: string
  /** 更新时间 */
  updateTime?: string
}

/**
 * 客户分页查询参数
 */
export interface CustomerPageParams {
  /** 页码 */
  pageNo: number
  /** 每页数量 */
  pageSize: number
  /** 客户编号 */
  customerNo?: string
  /** 客户类型 */
  customerType?: CustomerType
  /** 客户名称 */
  customerName?: string
  /** 客户等级 */
  customerLevel?: string
  /** 客户状态 */
  customerStatus?: CustomerStatus
  /** 分配状态 */
  assignmentStatus?: AssignmentStatus
  /** 是否有效客户 */
  isValid?: boolean
  /** 所属部门ID */
  deptId?: number
  /** 是否优质客户 */
  isHighQuality?: boolean
  /** 是否重要客户 */
  isImportant?: boolean
  /** 信用等级 */
  creditLevel?: string
  /** 客户来源 */
  customerSource?: string
  /** 客户标签 */
  customerTag?: string
}

/**
 * 分页结果
 */
export interface PageResult<T> {
  /** 数据列表 */
  list: T[]
  /** 总记录数 */
  total: number
}
