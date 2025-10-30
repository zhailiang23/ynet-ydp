import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCompanyProjectApi {
  /** 对公客户项目信息信息 */
  export interface CompanyProject {
    id: number; // 主键ID
    customerId?: number; // 客户ID（关联crm_customer表）
    projectCode: string; // 项目编号
    projectName?: string; // 项目名称
    projectType?: string; // 项目类型（基建工程、房地产开发、技术改造、研发项目、并购重组、境外投资、产能扩张、环保治理等）
    projectCategory: string; // 项目类别（自建、合作、收购、PPP等）
    totalInvestment: number; // 总投资额（元）
    registeredCapital: number; // 注册资本（元）
    projectArea: number; // 项目占地面积（平方米）
    buildingArea: number; // 建筑面积（平方米）
    planStartDate: string | Dayjs; // 计划开工日期
    actualStartDate: string | Dayjs; // 实际开工日期
    planCompleteDate: string | Dayjs; // 计划完工日期
    actualCompleteDate: string | Dayjs; // 实际完工日期
    constructionPeriod: number; // 建设周期（月）
    projectStatus: number; // 项目状态（1=筹备中 2=在建 3=已完工 4=运营中 5=暂停 6=终止）
    progressRate: number; // 项目进度（%）
    projectProvince: string; // 项目所在省份
    projectCity: string; // 项目所在城市
    projectDistrict: string; // 项目所在区县
    projectAddress: string; // 项目详细地址
    selfFunding: number; // 自有资金（元）
    bankLoan: number; // 银行贷款（元）
    bondFinancing: number; // 债券融资（元）
    equityFinancing: number; // 股权融资（元）
    governmentSubsidy: number; // 政府补助（元）
    otherFunding: number; // 其他资金（元）
    accumulatedInvestment: number; // 累计完成投资（元）
    financingRequirement: number; // 融资需求金额（元）
    financingPurpose: string; // 融资用途
    financingStatus: number; // 融资状态（1=未申请 2=申请中 3=已批准 4=已放款 5=已拒绝）
    ourBankFinancing: number; // 我行融资金额（元）
    otherBankFinancing: number; // 他行融资金额（元）
    partners: string; // 合作方（多个合作方用逗号分隔）
    contractor: string; // 总承包商
    designer: string; // 设计单位
    supervisor: string; // 监理单位
    expectedRevenue: number; // 预计年收入（元）
    expectedProfit: number; // 预计年利润（元）
    paybackPeriod: number; // 投资回收期（年）
    irrRate: number; // 内部收益率（%）
    riskLevel: string; // 风险等级（低、中、高）
    riskFactors: string; // 主要风险因素
    riskMitigation: string; // 风险缓释措施
    isKeyProject: number; // 是否重点项目（0=否 1=市级 2=省级 3=国家级）
    governmentApproval: string; // 政府批文
    policySupport: string; // 政策支持
    environmentalApproval: number; // 环评批复（0=未申请 1=已批复 2=不需要）
    landApproval: number; // 用地批复（0=未批复 1=已批复 2=不需要）
    constructionPermit: number; // 施工许可（0=未办理 1=已办理 2=不需要）
    projectManager: string; // 项目负责人
    managerPhone: string; // 负责人电话
    managerEmail: string; // 负责人邮箱
    marketingPriority: number; // 营销优先级（1=高 2=中 3=低）
    marketingOpportunity: string; // 营销机会点（如贷款、结算、理财、投行等）
    followUpPlan: string; // 跟进计划
    lastFollowUpDate: string | Dayjs; // 最后跟进日期
    nextFollowUpDate: string | Dayjs; // 下次跟进日期
    dataSource: string; // 数据来源（客户经理录入、招标网、政府网站、企业公告等）
    remark: string; // 备注
  }
}

/** 查询对公客户项目信息分页 */
export function getCompanyProjectPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCompanyProjectApi.CompanyProject>>(
    '/aicrm/company-project/page',
    { params },
  );
}

/** 查询对公客户项目信息详情 */
export function getCompanyProject(id: number) {
  return requestClient.get<AicrmCompanyProjectApi.CompanyProject>(
    `/aicrm/company-project/get?id=${id}`,
  );
}

/** 新增对公客户项目信息 */
export function createCompanyProject(data: AicrmCompanyProjectApi.CompanyProject) {
  return requestClient.post('/aicrm/company-project/create', data);
}

/** 修改对公客户项目信息 */
export function updateCompanyProject(data: AicrmCompanyProjectApi.CompanyProject) {
  return requestClient.put('/aicrm/company-project/update', data);
}

/** 删除对公客户项目信息 */
export function deleteCompanyProject(id: number) {
  return requestClient.delete(`/aicrm/company-project/delete?id=${id}`);
}

/** 批量删除对公客户项目信息 */
export function deleteCompanyProjectList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/company-project/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出对公客户项目信息 */
export function exportCompanyProject(params: any) {
  return requestClient.download('/aicrm/company-project/export-excel', { params });
}