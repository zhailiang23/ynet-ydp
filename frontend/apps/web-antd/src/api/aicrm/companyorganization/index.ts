import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCompanyOrganizationApi {
  /** CRM对公客户组织架构信息信息 */
  export interface CompanyOrganization {
    id: number; // 主键ID
    customerId?: number; // 客户ID(关联crm_company_customer.customer_id)
    parentId: number; // 父级组织ID(顶级组织为NULL)
    orgCode: string; // 组织编码
    orgName?: string; // 组织名称
    orgFullName: string; // 组织全称
    orgLevel: number; // 组织层级(1-一级,2-二级,3-三级...)
    orgType: string; // 组织类型(总部、分公司、事业部、部门等)
    orgStatus: string; // 组织状态(active-启用,inactive-停用,dissolved-解散)
    leaderName: string; // 负责人姓名
    leaderPosition: string; // 负责人职位
    leaderPhone: string; // 负责人电话
    leaderEmail: string; // 负责人邮箱
    employeeCount: number; // 员工人数
    establishedDate: string | Dayjs; // 成立日期
    contactPhone: string; // 联系电话
    contactEmail: string; // 联系邮箱
    contactAddress: string; // 办公地址
    businessScope: string; // 业务范围
    remark: string; // 备注说明
    sortOrder: number; // 排序号(同级组织排序)
  }
}

/** 查询CRM对公客户组织架构信息分页 */
export function getCompanyOrganizationPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCompanyOrganizationApi.CompanyOrganization>>(
    '/aicrm/company-organization/page',
    { params },
  );
}

/** 查询CRM对公客户组织架构信息详情 */
export function getCompanyOrganization(id: number) {
  return requestClient.get<AicrmCompanyOrganizationApi.CompanyOrganization>(
    `/aicrm/company-organization/get?id=${id}`,
  );
}

/** 新增CRM对公客户组织架构信息 */
export function createCompanyOrganization(data: AicrmCompanyOrganizationApi.CompanyOrganization) {
  return requestClient.post('/aicrm/company-organization/create', data);
}

/** 修改CRM对公客户组织架构信息 */
export function updateCompanyOrganization(data: AicrmCompanyOrganizationApi.CompanyOrganization) {
  return requestClient.put('/aicrm/company-organization/update', data);
}

/** 删除CRM对公客户组织架构信息 */
export function deleteCompanyOrganization(id: number) {
  return requestClient.delete(`/aicrm/company-organization/delete?id=${id}`);
}

/** 批量删除CRM对公客户组织架构信息 */
export function deleteCompanyOrganizationList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/company-organization/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM对公客户组织架构信息 */
export function exportCompanyOrganization(params: any) {
  return requestClient.download('/aicrm/company-organization/export-excel', { params });
}