import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerWorkApi {
  /** 客户工作或经营信息表信息 */
  export interface CustomerWork {
    id: number; // 工作信息主键
    customerId?: number; // 客户ID
    workType?: string; // 工作类型
    employerName: string; // 工作单位名称/经营主体名称
    employerType: string; // 单位性质
    industry: string; // 所属行业
    position: string; // 职位/职务
    workYears: number; // 工作年限/经营年限（单位:年）
    startDate: string | Dayjs; // 入职日期/开始经营日期
    endDate: string | Dayjs; // 离职日期/结束经营日期（NULL表示在职）
    isCurrent?: boolean; // 是否当前工作
    workAddressProvince: string; // 工作地址-省
    workAddressCity: string; // 工作地址-市
    workAddressDistrict: string; // 工作地址-区
    workAddressDetail: string; // 工作地址-详细地址
    annualIncome: number; // 年收入（单位:元）
    monthlyIncome: number; // 月收入（单位:元）
    incomeSource: string; // 收入来源说明
    businessScale: string; // 经营规模
    businessStatus: string; // 经营状态
    productionCapacity: string; // 生产能力
    businessLicenseNo: string; // 营业执照号/经营许可证号
    workPhone: string; // 工作电话/单位电话
    contactPerson: string; // 单位联系人
    contactPhone: string; // 联系人电话
    verificationStatus?: number; // 核验状态
    verificationTime: string | Dayjs; // 核验时间
    verificationRemark: string; // 核验备注
    attachmentUrls: string; // 附件URL列表（JSON格式，如营业执照、工作证明等）
    remark: string; // 备注信息
    extraData: string; // 扩展数据
  }
}

/** 查询客户工作或经营信息表分页 */
export function getCustomerWorkPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerWorkApi.CustomerWork>>(
    '/aicrm/customer-work/page',
    { params },
  );
}

/** 查询客户工作或经营信息表详情 */
export function getCustomerWork(id: number) {
  return requestClient.get<AicrmCustomerWorkApi.CustomerWork>(
    `/aicrm/customer-work/get?id=${id}`,
  );
}

/** 新增客户工作或经营信息表 */
export function createCustomerWork(data: AicrmCustomerWorkApi.CustomerWork) {
  return requestClient.post('/aicrm/customer-work/create', data);
}

/** 修改客户工作或经营信息表 */
export function updateCustomerWork(data: AicrmCustomerWorkApi.CustomerWork) {
  return requestClient.put('/aicrm/customer-work/update', data);
}

/** 删除客户工作或经营信息表 */
export function deleteCustomerWork(id: number) {
  return requestClient.delete(`/aicrm/customer-work/delete?id=${id}`);
}

/** 批量删除客户工作或经营信息表 */
export function deleteCustomerWorkList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-work/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户工作或经营信息表 */
export function exportCustomerWork(params: any) {
  return requestClient.download('/aicrm/customer-work/export-excel', { params });
}