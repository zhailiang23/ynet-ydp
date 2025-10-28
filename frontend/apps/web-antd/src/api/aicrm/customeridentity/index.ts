import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerIdentityApi {
  /** 客户证件信息表（零售客户特有，1对多关系，支持多证件）信息 */
  export interface CustomerIdentity {
    id: number; // 证件信息主键
    customerId?: number; // 客户ID
    identityType?: string; // 证件类型
    identityNo?: string; // 证件号码
    identityName: string; // 证件上的姓名
    issueAuthority: string; // 发证机关
    issueDate: string | Dayjs; // 发证日期
    expiryDate: string | Dayjs; // 有效期至
    isPrimary?: boolean; // 是否主证件
    identityAddress: string; // 证件地址
    identityFrontUrl: string; // 证件正面照片URL
    identityBackUrl: string; // 证件反面照片URL
    verificationStatus?: number; // 核验状态
    verificationTime: string | Dayjs; // 核验时间
    verificationRemark: string; // 核验备注
    remark: string; // 备注
    createTime?: string | Dayjs; // 创建时间
    updateTime?: string | Dayjs; // 更新时间
    updater?: string; // 更新人
  }
}

/** 查询客户证件信息表（零售客户特有，1对多关系，支持多证件）分页 */
export function getCustomerIdentityPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerIdentityApi.CustomerIdentity>>(
    '/aicrm/customer-identity/page',
    { params },
  );
}

/** 查询客户证件信息表（零售客户特有，1对多关系，支持多证件）详情 */
export function getCustomerIdentity(id: number) {
  return requestClient.get<AicrmCustomerIdentityApi.CustomerIdentity>(
    `/aicrm/customer-identity/get?id=${id}`,
  );
}

/** 新增客户证件信息表（零售客户特有，1对多关系，支持多证件） */
export function createCustomerIdentity(data: AicrmCustomerIdentityApi.CustomerIdentity) {
  return requestClient.post('/aicrm/customer-identity/create', data);
}

/** 修改客户证件信息表（零售客户特有，1对多关系，支持多证件） */
export function updateCustomerIdentity(data: AicrmCustomerIdentityApi.CustomerIdentity) {
  return requestClient.put('/aicrm/customer-identity/update', data);
}

/** 删除客户证件信息表（零售客户特有，1对多关系，支持多证件） */
export function deleteCustomerIdentity(id: number) {
  return requestClient.delete(`/aicrm/customer-identity/delete?id=${id}`);
}

/** 批量删除客户证件信息表（零售客户特有，1对多关系，支持多证件） */
export function deleteCustomerIdentityList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-identity/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户证件信息表（零售客户特有，1对多关系，支持多证件） */
export function exportCustomerIdentity(params: any) {
  return requestClient.download('/aicrm/customer-identity/export-excel', { params });
}