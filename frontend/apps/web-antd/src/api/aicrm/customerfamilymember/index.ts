import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerFamilyMemberApi {
  /** 客户家庭成员信息表（零售客户）信息 */
  export interface CustomerFamilyMember {
    id: number; // 主键ID
    customerId?: number; // 客户ID（关联家庭信息表）
    memberName?: string; // 成员姓名
    relationType?: string; // 家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）
    gender: number; // 性别（1-男，2-女）
    birthday: string | Dayjs; // 生日
    age: number; // 年龄
    identityType: string; // 证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）
    identityNo: string; // 证件号码（加密存储）
    educationLevel: string; // 学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）
    company: string; // 工作单位
    position: string; // 职务
    address: string; // 家庭地址
    mobile: string; // 联系方式（手机号）
    tel: string; // 固定电话
    email: string; // 邮箱
    isMainMember: boolean; // 是否主要成员（0-否，1-是）
    remark: string; // 备注
    memberId: string; // 成员ID（老系统）
    managerId: string; // 客户经理ID（老系统）
    oldCustId: string; // 老系统客户ID
  }
}

/** 查询客户家庭成员信息表（零售客户）分页 */
export function getCustomerFamilyMemberPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerFamilyMemberApi.CustomerFamilyMember>>(
    '/aicrm/customer-family-member/page',
    { params },
  );
}

/** 查询客户家庭成员信息表（零售客户）详情 */
export function getCustomerFamilyMember(id: number) {
  return requestClient.get<AicrmCustomerFamilyMemberApi.CustomerFamilyMember>(
    `/aicrm/customer-family-member/get?id=${id}`,
  );
}

/** 新增客户家庭成员信息表（零售客户） */
export function createCustomerFamilyMember(data: AicrmCustomerFamilyMemberApi.CustomerFamilyMember) {
  return requestClient.post('/aicrm/customer-family-member/create', data);
}

/** 修改客户家庭成员信息表（零售客户） */
export function updateCustomerFamilyMember(data: AicrmCustomerFamilyMemberApi.CustomerFamilyMember) {
  return requestClient.put('/aicrm/customer-family-member/update', data);
}

/** 删除客户家庭成员信息表（零售客户） */
export function deleteCustomerFamilyMember(id: number) {
  return requestClient.delete(`/aicrm/customer-family-member/delete?id=${id}`);
}

/** 批量删除客户家庭成员信息表（零售客户） */
export function deleteCustomerFamilyMemberList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-family-member/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户家庭成员信息表（零售客户） */
export function exportCustomerFamilyMember(params: any) {
  return requestClient.download('/aicrm/customer-family-member/export-excel', { params });
}