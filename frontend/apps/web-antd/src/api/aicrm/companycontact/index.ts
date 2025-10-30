import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCompanyContactApi {
  /** CRM对公客户联系人信息信息 */
  export interface CompanyContact {
    id: number; // 主键ID
    customerId?: number; // 客户ID(关联crm_company_customer.customer_id)
    contactType: string; // 联系方式类型(手机、座机、QQ、微信、邮箱等)
    contactPerson: string; // 联系人姓名
    contactMethod: string; // 联系方式(电话号码、QQ号、微信号等)
    isPrimary: boolean; // 是否首选项(否、是)
    sourceSystem: string; // 来源系统(ECIF、CRM、零售CRM等)
    contactSeq: number; // 联系方式序号
    contactDesc: string; // 联系方式描述
    status: string; // 状态
    remark: string; // 备注说明
    etlDate: string | Dayjs; // ETL导入日期
    oldTxSeqNo: string; // 老系统交易序列号
    oldLastUpdateSys: string; // 老系统最后更新系统
    oldLastUpdateUser: string; // 老系统最后更新用户
    oldLastUpdateTm: string | Dayjs; // 老系统最后更新时间
    oldContactId: number; // 老系统联系方式ID
  }
}

/** 查询CRM对公客户联系人信息分页 */
export function getCompanyContactPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCompanyContactApi.CompanyContact>>(
    '/aicrm/company-contact/page',
    { params },
  );
}

/** 查询CRM对公客户联系人信息详情 */
export function getCompanyContact(id: number) {
  return requestClient.get<AicrmCompanyContactApi.CompanyContact>(
    `/aicrm/company-contact/get?id=${id}`,
  );
}

/** 新增CRM对公客户联系人信息 */
export function createCompanyContact(data: AicrmCompanyContactApi.CompanyContact) {
  return requestClient.post('/aicrm/company-contact/create', data);
}

/** 修改CRM对公客户联系人信息 */
export function updateCompanyContact(data: AicrmCompanyContactApi.CompanyContact) {
  return requestClient.put('/aicrm/company-contact/update', data);
}

/** 删除CRM对公客户联系人信息 */
export function deleteCompanyContact(id: number) {
  return requestClient.delete(`/aicrm/company-contact/delete?id=${id}`);
}

/** 批量删除CRM对公客户联系人信息 */
export function deleteCompanyContactList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/company-contact/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM对公客户联系人信息 */
export function exportCompanyContact(params: any) {
  return requestClient.download('/aicrm/company-contact/export-excel', { params });
}