import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerApi {
  /** CRM客户主表(零售+对公共用)信息 */
  export interface Customer {
    id: number; // 主键ID
    customerNo?: string; // 客户编号(唯一标识)
    customerType?: number; // 客户类型(1=零售客户, 2=对公客户)
    customerName?: string; // 客户名称(零售为姓名,对公为企业名称)
    customerLevel?: string; // 客户等级
    customerStatus?: number; // 客户状态(1=正常, 2=冻结, 3=注销)
    assignmentStatus?: number; // 分配状态(0=未分配, 1=已分配)
    isValid?: boolean; // 是否有效客户
    isHighQuality?: boolean; // 是否优质客户
    isImportant?: boolean; // 是否重要客户
    creditStatus?: string; // 信用状态
    creditLevel?: string; // 信用等级
    creditScore?: number; // 信用评分
    customerSource?: string; // 客户来源
    customerTag?: string; // 客户标签(多个标签用逗号分隔)
    remark?: string; // 备注信息
    deptId?: number; // 所属部门ID(数据权限)
    // 扩展字段(从零售客户表关联查询)
    idCardType?: string; // 证件类型
    idCardNo?: string; // 证件号码
    createTime?: string; // 创建时间
    updateTime?: string; // 更新时间
  }

  /** 客户分页查询参数 */
  export interface CustomerPageReqVO extends PageParam {
    customerNo?: string; // 客户编号
    customerType?: number; // 客户类型
    customerName?: string; // 客户名称
    customerLevel?: string; // 客户等级
    customerStatus?: number; // 客户状态
    assignmentStatus?: number; // 分配状态
    isValid?: boolean; // 是否有效客户
    isHighQuality?: boolean; // 是否优质客户
    isImportant?: boolean; // 是否重要客户
    creditLevel?: string; // 信用等级
    customerSource?: string; // 客户来源
    customerTag?: string; // 客户标签
    createTime?: [Dayjs, Dayjs]; // 创建时间范围
    deptId?: number; // 所属部门ID
  }
}

/** 查询CRM客户主表(零售+对公共用)分页 */
export function getCustomerPage(params: AicrmCustomerApi.CustomerPageReqVO) {
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

/** 获取未分配的客户列表(用于下拉选择) */
export function getUnassignedCustomers() {
  return requestClient.get<PageResult<AicrmCustomerApi.Customer>>(
    '/aicrm/customer/page',
    {
      params: {
        assignmentStatus: 0,  // 0=未分配
        pageSize: 100,        // 每页最大100条
      }
    },
  ).then(res => res.list || []);
}