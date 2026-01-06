import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerMarketingActivityApi {
  /** 客户营销活动信息信息 */
  export interface CustomerMarketingActivity {
    id: number; // 营销活动主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    sequenceNo: number; // 序号
    activityName?: string; // 活动名称
    activityNo?: string; // 活动编号
    activityForm?: string; // 活动形式（字典: aicrm_activity_form，parent_child=亲子沙龙，salon=线下沙龙，visit=客户拜访，phone=电话营销等）
    activityStatus?: string; // 活动状态（字典: aicrm_activity_status，draft=草稿，pending=待执行，executing=执行中，completed=已完成，closed=已关闭）
    approvalStatus?: string; // 审批状态（字典: aicrm_approval_status，pending_submit=待提交，approving=审批中，approved=已通过，rejected=已驳回）
    activityOrderTime: string | Dayjs; // 活动订购时间
    activityEndTime: string | Dayjs; // 活动结束时间
    isSendSms?: boolean; // 是否发送客户短信
    opporId: string; // 机会ID（老系统）
    opporNm: string; // 机会名称（老系统）
    startDt: string | Dayjs; // 开始日期
    endDt: string | Dayjs; // 结束日期
    opporStcd: string; // 机会状态代码
    autoAcltRlcd: string; // 自动分配规则代码
    opporBsnTpcd: string; // 机会业务类型代码
    opporAlctObjTpcd: string; // 机会分配对象类型代码
    opporCustTpcd: string; // 机会客户类型代码
    opporSrccd: string; // 机会来源代码
    opporCrtMthcd: string; // 机会创建方式代码
    crtOrgId: string; // 创建机构ID
    opporDsc: string; // 机会描述
    activityType?: string; // 活动类型（字典: aicrm_activity_type，product_promotion=产品推广，customer_maintain=客户维护，market_research=市场调研，brand_publicity=品牌宣传等）
    activityLocation: string; // 活动地点
    activityBudget: number; // 活动预算（元）
    activityCost: number; // 活动实际费用（元）
    targetCustomerCount: number; // 目标客户数量
    actualCustomerCount: number; // 实际参与客户数量
    expectedEffect: string; // 预期效果
    actualEffect: string; // 实际效果
    handlerUserId: number; // 负责人ID
    handlerUserName: string; // 负责人姓名
    handlerDeptId: number; // 负责部门ID
    handlerDeptName: string; // 负责部门名称
    approverUserId: number; // 审批人ID
    approverUserName: string; // 审批人姓名
    approvalTime: string | Dayjs; // 审批时间
    approvalComment: string; // 审批意见
    smsSendTime: string | Dayjs; // 短信发送时间
    smsContent: string; // 短信内容
    activityScore: number; // 活动评分（1-5星）
    customerFeedback: string; // 客户反馈
    isCancelled?: boolean; // 是否已取消
    cancelTime: string | Dayjs; // 取消时间
    cancelReason: string; // 取消原因
    remark: string; // 备注
  }
}

/** 查询客户营销活动信息分页 */
export function getCustomerMarketingActivityPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerMarketingActivityApi.CustomerMarketingActivity>>(
    '/aicrm/customer-marketing-activity/page',
    { params },
  );
}

/** 查询客户营销活动信息详情 */
export function getCustomerMarketingActivity(id: number) {
  return requestClient.get<AicrmCustomerMarketingActivityApi.CustomerMarketingActivity>(
    `/aicrm/customer-marketing-activity/get?id=${id}`,
  );
}

/** 新增客户营销活动信息 */
export function createCustomerMarketingActivity(data: AicrmCustomerMarketingActivityApi.CustomerMarketingActivity) {
  return requestClient.post('/aicrm/customer-marketing-activity/create', data);
}

/** 修改客户营销活动信息 */
export function updateCustomerMarketingActivity(data: AicrmCustomerMarketingActivityApi.CustomerMarketingActivity) {
  return requestClient.put('/aicrm/customer-marketing-activity/update', data);
}

/** 删除客户营销活动信息 */
export function deleteCustomerMarketingActivity(id: number) {
  return requestClient.delete(`/aicrm/customer-marketing-activity/delete?id=${id}`);
}

/** 批量删除客户营销活动信息 */
export function deleteCustomerMarketingActivityList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-marketing-activity/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户营销活动信息 */
export function exportCustomerMarketingActivity(params: any) {
  return requestClient.download('/aicrm/customer-marketing-activity/export-excel', { params });
}

/** 获取营销活动简化列表（用于下拉选择） */
export function getCustomerMarketingActivitySimpleList() {
  return requestClient.get<{ id: number; activityName: string }[]>(
    '/aicrm/customer-marketing-activity/simple-list',
  );
}