import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerContactApi {
  /** 客户接触信息信息 */
  export interface CustomerContact {
    id: number; // 接触主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    evaluationTime?: string | Dayjs; // 评估时间
    contactType?: string; // 接触类型（字典: aicrm_contact_type，customer_callback=客户反馈，phone=电话，visit=拜访，email=邮件，wechat=微信，meeting=会议等）
    accountManager?: string; // 客户经理
    contactPurpose?: string; // 接触目的
    contactFeedback?: string; // 接触反馈
    cstVstId: number; // 接触ID（老系统）
    cstId: string; // 客户ID（老系统）
    vstMthCd: string; // 接触方式代码
    vstTm: string | Dayjs; // 接触时间
    cstInintCd: string; // 客户意向代码
    vstPsnLst: string; // 接触人员（我方参与人员）
    cstPsnLst: string; // 客户人员（客户方参与人员）
    vstAdr: string; // 接触地址
    vstRsltDsc: string; // 接触结果
    fuaTm: string | Dayjs; // 跟进时间
    toDoDsc: string; // 待办事项
    othDsc: string; // 其他描述
    crtUsrId: string; // 创建用户ID（老系统）
    crtTm: string | Dayjs; // 创建时间（老系统）
    udtUsrId: string; // 更新用户ID（老系统）
    udtTm: string | Dayjs; // 更新时间（老系统）
    contactNo?: string; // 接触编号
    contactStatus?: string; // 接触状态（字典: aicrm_contact_status，planned=已计划，completed=已完成，cancelled=已取消）
    contactChannel?: string; // 接触渠道（字典: aicrm_contact_channel，active=主动接触，passive=被动接触）
    contactResult: string; // 接触结果（字典: aicrm_contact_result，success=成功，partial=部分成功，failed=失败）
    customerIntention: string; // 客户意向（字典: aicrm_customer_intention，high=意向强烈，medium=意向一般，low=意向较弱，none=无意向）
    contactDuration: number; // 接触时长（分钟）
    contactLocation: string; // 接触地点
    contactSubject: string; // 接触主题
    contactSummary: string; // 接触摘要
    nextContactTime: string | Dayjs; // 下次接触时间
    nextContactPlan: string; // 下次接触计划
    isNeedFollowup?: boolean; // 是否需要跟进
    followupUserId: number; // 跟进人ID
    followupUserName: string; // 跟进人姓名
    followupStatus: string; // 跟进状态（字典: aicrm_followup_status，pending=待跟进，following=跟进中，completed=已完成）
    relatedActivityId: number; // 关联营销活动ID
    relatedActivityName: string; // 关联营销活动名称
    relatedProduct: string; // 关联产品
    expectedAmount: number; // 预期金额（元）
    actualAmount: number; // 实际成交金额（元）
    isConverted?: boolean; // 是否已转化
    convertTime: string | Dayjs; // 转化时间
    customerSatisfaction: number; // 客户满意度（1-5星）
    handlerUserId: number; // 处理人ID
    handlerUserName: string; // 处理人姓名
    handlerDeptId: number; // 处理部门ID
    handlerDeptName: string; // 处理部门名称
    attachments: string; // 附件列表（JSON格式）
    remark: string; // 备注
  }
}

/** 查询客户接触信息分页 */
export function getCustomerContactPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerContactApi.CustomerContact>>(
    '/aicrm/customer-contact/page',
    { params },
  );
}

/** 查询客户接触信息详情 */
export function getCustomerContact(id: number) {
  return requestClient.get<AicrmCustomerContactApi.CustomerContact>(
    `/aicrm/customer-contact/get?id=${id}`,
  );
}

/** 新增客户接触信息 */
export function createCustomerContact(data: AicrmCustomerContactApi.CustomerContact) {
  return requestClient.post('/aicrm/customer-contact/create', data);
}

/** 修改客户接触信息 */
export function updateCustomerContact(data: AicrmCustomerContactApi.CustomerContact) {
  return requestClient.put('/aicrm/customer-contact/update', data);
}

/** 删除客户接触信息 */
export function deleteCustomerContact(id: number) {
  return requestClient.delete(`/aicrm/customer-contact/delete?id=${id}`);
}

/** 批量删除客户接触信息 */
export function deleteCustomerContactList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-contact/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户接触信息 */
export function exportCustomerContact(params: any) {
  return requestClient.download('/aicrm/customer-contact/export-excel', { params });
}