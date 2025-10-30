import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerPointsTransactionApi {
  /** 客户积分消费明细表（积分交易流水表）信息 */
  export interface CustomerPointsTransaction {
    id: number; // 交易主键
    customerId?: number; // 客户ID（关联 crm_customer 主表）
    pointsAccountId?: number; // 积分账户ID（关联 crm_customer_points 表）
    orderNo?: string; // 订单编号
    transactionDate?: string | Dayjs; // 交易日期
    exchangeChannel?: string; // 兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）
    giftExchangeMethod: string; // 礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）
    orderConsumedPoints?: number; // 订单消耗积分
    orderStatus?: string; // 订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）
    orderApprovalStatus?: string; // 订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）
    exchangeUser: string; // 兑换用户
    transactionNo?: string; // 交易流水号
    transactionType?: string; // 交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）
    transactionTime?: string | Dayjs; // 交易时间
    pointsChange?: number; // 积分变动（正数为增加，负数为减少）
    pointsBefore?: number; // 交易前积分
    pointsAfter?: number; // 交易后积分
    transactionStatus?: string; // 交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）
    transactionDesc: string; // 交易描述
    businessType: string; // 业务类型（字典: aicrm_points_business_type）
    businessNo: string; // 业务单号
    giftCode: string; // 礼品编码
    giftName: string; // 礼品名称
    giftQuantity: number; // 礼品数量
    giftValue: number; // 礼品价值
    deliveryAddress: string; // 配送地址
    deliveryStatus: string; // 配送状态（字典: aicrm_delivery_status，pending=待发货，shipped=已发货，delivered=已送达，rejected=拒收）
    deliveryTime: string | Dayjs; // 配送时间
    receiverName: string; // 收货人姓名
    receiverPhone: string; // 收货人电话
    expireDate: string | Dayjs; // 过期日期（对于有效期的积分）
    refundReason: string; // 退款原因
    refundTime: string | Dayjs; // 退款时间
    refundPoints: number; // 退款积分
    approvalUserId: number; // 审批人ID
    approvalUserName: string; // 审批人姓名
    approvalTime: string | Dayjs; // 审批时间
    approvalRemark: string; // 审批备注
    operatorUserId: number; // 操作人ID
    operatorUserName: string; // 操作人姓名
    operatorDeptId: number; // 操作部门ID
    operatorDeptName: string; // 操作部门名称
    isReversed?: boolean; // 是否已冲正
    reverseTransactionId: number; // 冲正交易ID
    reverseTime: string | Dayjs; // 冲正时间
    remark: string; // 备注
  }
}

/** 查询客户积分消费明细表（积分交易流水表）分页 */
export function getCustomerPointsTransactionPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerPointsTransactionApi.CustomerPointsTransaction>>(
    '/aicrm/customer-points-transaction/page',
    { params },
  );
}

/** 查询客户积分消费明细表（积分交易流水表）详情 */
export function getCustomerPointsTransaction(id: number) {
  return requestClient.get<AicrmCustomerPointsTransactionApi.CustomerPointsTransaction>(
    `/aicrm/customer-points-transaction/get?id=${id}`,
  );
}

/** 新增客户积分消费明细表（积分交易流水表） */
export function createCustomerPointsTransaction(data: AicrmCustomerPointsTransactionApi.CustomerPointsTransaction) {
  return requestClient.post('/aicrm/customer-points-transaction/create', data);
}

/** 修改客户积分消费明细表（积分交易流水表） */
export function updateCustomerPointsTransaction(data: AicrmCustomerPointsTransactionApi.CustomerPointsTransaction) {
  return requestClient.put('/aicrm/customer-points-transaction/update', data);
}

/** 删除客户积分消费明细表（积分交易流水表） */
export function deleteCustomerPointsTransaction(id: number) {
  return requestClient.delete(`/aicrm/customer-points-transaction/delete?id=${id}`);
}

/** 批量删除客户积分消费明细表（积分交易流水表） */
export function deleteCustomerPointsTransactionList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-points-transaction/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户积分消费明细表（积分交易流水表） */
export function exportCustomerPointsTransaction(params: any) {
  return requestClient.download('/aicrm/customer-points-transaction/export-excel', { params });
}