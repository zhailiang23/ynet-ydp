import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerReminderApi {
  /** 客户提醒信息信息 */
  export interface CustomerReminder {
    id: number; // 提醒主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    sequenceNo: number; // 序号
    reminderCategoryName?: string; // 提醒类别名称
    reminderGenerateDate?: string | Dayjs; // 提醒生成日期
    reminderDueDate?: string | Dayjs; // 提醒到期日期
    reminderContent?: string; // 提醒内容
    reminderNo?: string; // 提醒编号
    reminderType?: string; // 提醒类型（字典: aicrm_reminder_type，account_balance=账户余额变动，credit_expire=授信到期，customer_contact=客户接触，customer_birthday=客户生日，loan_expire=贷款到期，product_expire=产品到期，payment_remind=还款提醒等）
    reminderLevel?: string; // 提醒级别（字典: aicrm_reminder_level，urgent=紧急，important=重要，normal=普通，info=提示）
    reminderStatus?: string; // 提醒状态（字典: aicrm_reminder_status，pending=待处理，processing=处理中，completed=已完成，expired=已过期，cancelled=已取消）
    reminderSource?: string; // 提醒来源（字典: aicrm_reminder_source，system_auto=系统自动，manual_create=手动创建，rule_trigger=规则触发，data_analysis=数据分析）
    isSent?: boolean; // 是否已发送
    sendTime: string | Dayjs; // 发送时间
    sendChannel: string; // 发送渠道（字典: aicrm_reminder_channel，sms=短信，email=邮件，wechat=微信，system=系统消息，phone=电话）
    recipientUserId: number; // 接收人ID
    recipientUserName: string; // 接收人姓名
    recipientDeptId: number; // 接收部门ID
    recipientDeptName: string; // 接收部门名称
    handlerUserId: number; // 处理人ID
    handlerUserName: string; // 处理人姓名
    handleTime: string | Dayjs; // 处理时间
    handleResult: string; // 处理结果
    isRepeat?: boolean; // 是否重复提醒
    repeatRule: string; // 重复规则（如：每天、每周、每月等）
    nextRemindTime: string | Dayjs; // 下次提醒时间
    isRead?: boolean; // 是否已读
    readTime: string | Dayjs; // 阅读时间
    priority?: number; // 优先级（数字越大优先级越高）
    relatedBusinessType: string; // 关联业务类型（字典: aicrm_business_type）
    relatedBusinessId: number; // 关联业务ID
    expireTime: string | Dayjs; // 过期时间
    isExpired?: boolean; // 是否已过期
    isCancelled?: boolean; // 是否已取消
    cancelTime: string | Dayjs; // 取消时间
    cancelReason: string; // 取消原因
    remark: string; // 备注
  }
}

/** 查询客户提醒信息分页 */
export function getCustomerReminderPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerReminderApi.CustomerReminder>>(
    '/aicrm/customer-reminder/page',
    { params },
  );
}

/** 查询客户提醒信息详情 */
export function getCustomerReminder(id: number) {
  return requestClient.get<AicrmCustomerReminderApi.CustomerReminder>(
    `/aicrm/customer-reminder/get?id=${id}`,
  );
}

/** 新增客户提醒信息 */
export function createCustomerReminder(data: AicrmCustomerReminderApi.CustomerReminder) {
  return requestClient.post('/aicrm/customer-reminder/create', data);
}

/** 修改客户提醒信息 */
export function updateCustomerReminder(data: AicrmCustomerReminderApi.CustomerReminder) {
  return requestClient.put('/aicrm/customer-reminder/update', data);
}

/** 删除客户提醒信息 */
export function deleteCustomerReminder(id: number) {
  return requestClient.delete(`/aicrm/customer-reminder/delete?id=${id}`);
}

/** 批量删除客户提醒信息 */
export function deleteCustomerReminderList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-reminder/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户提醒信息 */
export function exportCustomerReminder(params: any) {
  return requestClient.download('/aicrm/customer-reminder/export-excel', { params });
}