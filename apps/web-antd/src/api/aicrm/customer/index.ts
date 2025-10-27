import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerApi {
  /** CRM客户主表(零售+对公共用)信息 */
  export interface Customer {
    id: number; // 客户ID(主键)
    customerNo?: string; // 客户编号(唯一标识)
    customerType?: number; // 客户类型(1=零售客户, 2=对公客户)
    customerName?: string; // 客户名称(零售为姓名,对公为企业名称)
    customerLevel: string; // 客户等级(零售:普通/VIP/金卡/钻石卡等, 对公:普通/重点/战略客户等)
    customerStatus?: number; // 客户状态(1=正常, 2=冻结, 3=注销)
    isHighQuality?: boolean; // 是否优质客户(0=否, 1=是)
    isImportant?: boolean; // 是否重要客户(0=否, 1=是)
    creditStatus: string; // 信用状态(如:良好、一般、较差)
    creditLevel: string; // 信用等级(如:AAA、AA、A、BBB等)
    creditScore: number; // 信用评分
    customerSource: string; // 客户来源(如:网点开发、电话营销、网络营销等)
    customerTag: string; // 客户标签(多个标签用逗号分隔)
    remark: string; // 备注信息
    deptId: number; // 所属部门ID(数据权限)
  }
}

/** 查询CRM客户主表(零售+对公共用)分页 */
export function getCustomerPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerApi.Customer>>(
    '/aicrm/customer/page',
    { params },
  );
}

/** 查询CRM客户主表(零售+对公共用)详情 */
export function getCustomer(id: number) {
  return requestClient.get<AicrmCustomerApi.Customer>(
    `/aicrm/customer/get?id=${id}`,
  );
}

/** 新增CRM客户主表(零售+对公共用) */
export function createCustomer(data: AicrmCustomerApi.Customer) {
  return requestClient.post('/aicrm/customer/create', data);
}

/** 修改CRM客户主表(零售+对公共用) */
export function updateCustomer(data: AicrmCustomerApi.Customer) {
  return requestClient.put('/aicrm/customer/update', data);
}

/** 删除CRM客户主表(零售+对公共用) */
export function deleteCustomer(id: number) {
  return requestClient.delete(`/aicrm/customer/delete?id=${id}`);
}

/** 批量删除CRM客户主表(零售+对公共用) */
export function deleteCustomerList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出CRM客户主表(零售+对公共用) */
export function exportCustomer(params: any) {
  return requestClient.download('/aicrm/customer/export-excel', { params });
}