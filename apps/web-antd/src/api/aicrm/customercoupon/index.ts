import type { PageParam, PageResult } from '@vben/request';
import type { Dayjs } from 'dayjs';

import { requestClient } from '#/api/request';

export namespace AicrmCustomerCouponApi {
  /** 客户卡券信息表（零售客户特有）信息 */
  export interface CustomerCoupon {
    id: number; // 卡券主键
    customerId?: number; // 客户ID（关联 crm_customer 主表，仅限零售客户）
    couponNo?: string; // 卡券编号（唯一）
    couponCode?: string; // 卡券代码（券模板编码）
    couponName?: string; // 卡券名称
    couponType?: string; // 卡券类型（字典: aicrm_coupon_type，discount=折扣券，cash=代金券，gift=礼品券，rate=利率券，fee_waiver=手续费减免券）
    couponCategory?: string; // 卡券分类（字典: aicrm_coupon_category，deposit=存款类，wealth=理财类，loan=贷款类，payment=支付类，general=通用类）
    discountRate: number; // 折扣率（如8.5表示8.5折，仅折扣券有效）
    discountAmount: number; // 优惠金额（代金券金额或减免金额）
    giftName: string; // 礼品名称（仅礼品券有效）
    rateDiscount: number; // 利率优惠（如0.5表示利率下调0.5%，仅利率券有效）
    thresholdAmount: number; // 使用门槛金额（满多少可用，0表示无门槛）
    maxDiscountAmount: number; // 最高优惠金额（封顶金额）
    couponStatus?: string; // 卡券状态（字典: aicrm_coupon_status，unused=未使用，used=已使用，expired=已过期，cancelled=已作废，locked=已锁定）
    issueDate?: string | Dayjs; // 发放日期
    issueTime?: string | Dayjs; // 发放时间
    effectiveDate?: string | Dayjs; // 生效日期
    expiryDate?: string | Dayjs; // 过期日期
    useDate: string | Dayjs; // 使用日期
    useTime: string | Dayjs; // 使用时间
    cancelDate: string | Dayjs; // 作废日期
    cancelReason: string; // 作废原因
    issueSource?: string; // 发放来源（字典: aicrm_coupon_issue_source，system_gift=系统赠送，activity_reward=活动奖励，birthday_gift=生日礼物，point_exchange=积分兑换，manual_issue=手动发放）
    issueChannel?: string; // 发放渠道（字典: aicrm_coupon_channel，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，counter=柜台，crm=CRM系统）
    issueActivityId: number; // 关联活动ID（如果来源于营销活动）
    issueActivityName: string; // 关联活动名称
    useScenario: string; // 使用场景（字典: aicrm_coupon_use_scenario，online_purchase=线上购买，counter_purchase=柜台办理，app_purchase=APP办理，wealth_purchase=理财购买，loan_apply=贷款申请）
    useChannel: string; // 使用渠道（字典: aicrm_coupon_channel）
    useProductCode: string; // 使用产品代码
    useProductName: string; // 使用产品名称
    useOrderNo: string; // 使用订单号
    useTransactionAmount: number; // 交易金额（使用卡券时的交易金额）
    actualDiscountAmount: number; // 实际优惠金额
    applicableProducts: string; // 适用产品（JSON数组，如["PROD001","PROD002"]，null表示通用）
    applicableChannels: string; // 适用渠道（JSON数组，null表示通用）
    useLimitTimes?: number; // 可使用次数（1=单次使用，>1=可多次使用）
    usedTimes?: number; // 已使用次数
    isTransferable?: boolean; // 是否可转赠
    isStackable?: boolean; // 是否可叠加使用
    expireRemindDays?: number; // 过期提醒天数（提前几天提醒）
    isReminded?: boolean; // 是否已提醒
    remindTime: string | Dayjs; // 提醒时间
    description: string; // 卡券描述（使用说明）
    remark: string; // 备注
  }
}

/** 查询客户卡券信息表（零售客户特有）分页 */
export function getCustomerCouponPage(params: PageParam) {
  return requestClient.get<PageResult<AicrmCustomerCouponApi.CustomerCoupon>>(
    '/aicrm/customer-coupon/page',
    { params },
  );
}

/** 查询客户卡券信息表（零售客户特有）详情 */
export function getCustomerCoupon(id: number) {
  return requestClient.get<AicrmCustomerCouponApi.CustomerCoupon>(
    `/aicrm/customer-coupon/get?id=${id}`,
  );
}

/** 新增客户卡券信息表（零售客户特有） */
export function createCustomerCoupon(data: AicrmCustomerCouponApi.CustomerCoupon) {
  return requestClient.post('/aicrm/customer-coupon/create', data);
}

/** 修改客户卡券信息表（零售客户特有） */
export function updateCustomerCoupon(data: AicrmCustomerCouponApi.CustomerCoupon) {
  return requestClient.put('/aicrm/customer-coupon/update', data);
}

/** 删除客户卡券信息表（零售客户特有） */
export function deleteCustomerCoupon(id: number) {
  return requestClient.delete(`/aicrm/customer-coupon/delete?id=${id}`);
}

/** 批量删除客户卡券信息表（零售客户特有） */
export function deleteCustomerCouponList(ids: number[]) {
  return requestClient.delete(
    `/aicrm/customer-coupon/delete-list?ids=${ids.join(',')}`,
  );
}

/** 导出客户卡券信息表（零售客户特有） */
export function exportCustomerCoupon(params: any) {
  return requestClient.download('/aicrm/customer-coupon/export-excel', { params });
}