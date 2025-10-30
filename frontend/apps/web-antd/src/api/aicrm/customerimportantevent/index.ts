import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerImportantEventApi {
  /** 客户重要事件表（零售+对公共用）信息 */
  export interface CustomerImportantEvent {
    id: number; // 主键ID
    customerId?: number; // 客户ID（关联客户主表）
    eventName?: string; // 事件名称
    eventStatus?: string; // 事件状态（字典：aicrm_event_status，如：未发生、进行中、已完成）
    eventDate?: string | Dayjs; // 事件发生日期
    eventContent: string; // 事件内容
    maintainerId: number; // 维护人ID（关联用户表）
    maintainerName: string; // 维护人姓名
    maintainTime: string | Dayjs; // 最近维护日期
    eventType: string; // 事件类型（字典：aicrm_event_type，如：生日、结婚、生子、升职、购房等）
    eventLevel: string; // 事件级别（字典：aicrm_event_level，如：重要、一般、普通）
    eventSource: string; // 事件来源（字典：aicrm_event_source，如：客户告知、系统识别、客户经理录入）
    remindFlag: boolean; // 是否提醒（0-否，1-是）
    remindTime: string | Dayjs; // 提醒时间
    attachmentUrl: string; // 附件地址
    remark: string; // 备注
  }
}

/** 查询客户重要事件表（零售+对公共用）分页 */
export function getCustomerImportantEventPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerImportantEventApi.CustomerImportantEvent>>(
    '/aicrm/customer-important-event/page',
    { params },
  );
}

/** 查询客户重要事件表（零售+对公共用）详情 */
export function getCustomerImportantEvent(id: number) {
  return requestClient.get<AicrmCustomerImportantEventApi.CustomerImportantEvent>(
    `/aicrm/customer-important-event/get?id=${id}`,
  );
}

/** 新增客户重要事件表（零售+对公共用） */
export function createCustomerImportantEvent(data: AicrmCustomerImportantEventApi.CustomerImportantEvent) {
  return requestClient.post('/aicrm/customer-important-event/create', data);
}

/** 修改客户重要事件表（零售+对公共用） */
export function updateCustomerImportantEvent(data: AicrmCustomerImportantEventApi.CustomerImportantEvent) {
  return requestClient.put('/aicrm/customer-important-event/update', data);
}

/** 删除客户重要事件表（零售+对公共用） */
export function deleteCustomerImportantEvent(id: number) {
  return requestClient.delete(`/aicrm/customer-important-event/delete?id=${id}`);
}

/** 批量删除客户重要事件表（零售+对公共用） */
export function deleteCustomerImportantEventList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-important-event/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户重要事件表（零售+对公共用） */
export function exportCustomerImportantEvent(params: any) {
  return requestClient.download('/aicrm/customer-important-event/export-excel', { params });
}