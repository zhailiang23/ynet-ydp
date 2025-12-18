import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace GridHuinongCustomerLoanApi {
  /** 惠农贷款客户扩展信息 */
  export interface HuinongCustomerLoan {
    id: number; // 扩展ID
    customerId?: number; // 客户ID (关联 grid_customer)
    customerName?: string; // 客户姓名 (关联 grid_customer)
    phone?: string; // 手机号码 (关联 grid_customer)
    customerCategory?: string; // 客户大类 (关联字典 aicrm_customer_category)
    subdivisionType: string; // 细分类型
    businessAddress: string; // 经营地址
    gender: string; // 性别: 男/女
    customerSituation: string; // 客户情况
    businessYears: number; // 经营年限
    currentFinancing: string; // 当前融资情况
    creditDemand: string; // 信贷产品需求
    demandMonth: string; // 需求月份 (1-12)
    demandPeriod: string; // 需求旬 (关联字典 aicrm_demand_period: 上旬/中旬/下旬)
    businessProgress: string; // 业务进展
    customerSource: string; // 客户来源 (关联字典 aicrm_customer_source: 行内客户/外拓客户)
    isApplied: boolean; // 是否进件
    applyTime: string | Dayjs; // 进件时间
    isApproved: boolean; // 是否审批通过
    approveTime: string | Dayjs; // 审批通过时间
    loanProductName: string; // 贷款产品名称
    loanAmount: number; // 贷款金额 (元)
    creditLimit: number; // 授信额度 (元)
    loanBalance: number; // 贷款余额 (元)
    overdueStatus: string; // 逾期状态
    creatorId: number; // 创建人ID
    updaterId: number; // 更新人ID
  }
}

/** 查询惠农贷款客户扩展分页 */
export function getHuinongCustomerLoanPage(params: PageParam) {
  return requestClient.get<PageResult<GridHuinongCustomerLoanApi.HuinongCustomerLoan>>(
    '/grid/huinong-customer-loan/page',
    { params },
  );
}

/** 查询惠农贷款客户扩展详情 */
export function getHuinongCustomerLoan(id: number) {
  return requestClient.get<GridHuinongCustomerLoanApi.HuinongCustomerLoan>(
    `/grid/huinong-customer-loan/get?id=${id}`,
  );
}

/** 新增惠农贷款客户扩展 */
export function createHuinongCustomerLoan(data: GridHuinongCustomerLoanApi.HuinongCustomerLoan) {
  return requestClient.post<number>('/grid/huinong-customer-loan/create', data);
}

/** 修改惠农贷款客户扩展 */
export function updateHuinongCustomerLoan(data: GridHuinongCustomerLoanApi.HuinongCustomerLoan) {
  return requestClient.put('/grid/huinong-customer-loan/update', data);
}

/** 删除惠农贷款客户扩展 */
export function deleteHuinongCustomerLoan(id: number) {
  return requestClient.delete(`/grid/huinong-customer-loan/delete?id=${id}`);
}

/** 批量删除惠农贷款客户扩展 */
export function deleteHuinongCustomerLoanList(ids: number[]) {
  return requestClient.delete(
    `/grid/huinong-customer-loan/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出惠农贷款客户扩展 */
export function exportHuinongCustomerLoan(params: any) {
  return requestClient.download('/grid/huinong-customer-loan/export-excel', { params });
}

// ==================== 热力图相关 ====================

/** 热力图请求参数 */
export interface HeatmapReqVO {
  metricType?: string; // 热力值类型: CUSTOMER_COUNT/APPLIED_COUNT/APPROVED_COUNT/LOAN_BALANCE
}

/** 热力图数据点 */
export interface HeatmapDataVO {
  longitude: number; // 经度
  latitude: number; // 纬度
  value: number; // 热力值
  gridId: number; // 网格ID
  stationName: string; // 站点名称
}

/** 客户标记 */
export interface CustomerMarkerVO {
  customerId: number; // 客户ID
  customerName: string; // 客户姓名
  longitude: number; // 经度
  latitude: number; // 纬度
  stationType: string; // 站点类型: REQUIRED/OPTIONAL
  isApplied: boolean; // 是否已申请
  isApproved: boolean; // 是否已批准
  loanBalance: number; // 贷款余额
  customerCategory: string; // 客户类别
}

/** 获取热力图数据 */
export function getHeatmapData(params: HeatmapReqVO) {
  return requestClient.get<HeatmapDataVO[]>('/grid/huinong-customer-loan/heatmap/data', {
    params,
  });
}

/** 获取客户标记列表 */
export function getCustomerMarkers() {
  return requestClient.get<CustomerMarkerVO[]>(
    '/grid/huinong-customer-loan/heatmap/customer-markers',
  );
}