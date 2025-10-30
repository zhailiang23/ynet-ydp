import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerCouponApi } from '#/api/aicrm/customercoupon';

import { getDictOptions } from '@vben/hooks';

import { getRangePickerDefaultProps } from '#/utils';

/** 新增/修改的表单 */
export function useFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'id',
      component: 'Input',
      dependencies: {
        triggerFields: [''],
        show: () => false,
      },
    },
    {
      fieldName: 'customerId',
      label: '客户ID（关联 crm_customer 主表，仅限零售客户）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联 crm_customer 主表，仅限零售客户）',
      },
    },
    {
      fieldName: 'couponNo',
      label: '卡券编号（唯一）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入卡券编号（唯一）',
      },
    },
    {
      fieldName: 'couponCode',
      label: '卡券代码（券模板编码）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入卡券代码（券模板编码）',
      },
    },
    {
      fieldName: 'couponName',
      label: '卡券名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入卡券名称',
      },
    },
    {
      fieldName: 'couponType',
      label: '卡券类型（字典: aicrm_coupon_type，discount=折扣券，cash=代金券，gift=礼品券，rate=利率券，fee_waiver=手续费减免券）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择卡券类型（字典: aicrm_coupon_type，discount=折扣券，cash=代金券，gift=礼品券，rate=利率券，fee_waiver=手续费减免券）',
      },
    },
    {
      fieldName: 'couponCategory',
      label: '卡券分类（字典: aicrm_coupon_category，deposit=存款类，wealth=理财类，loan=贷款类，payment=支付类，general=通用类）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入卡券分类（字典: aicrm_coupon_category，deposit=存款类，wealth=理财类，loan=贷款类，payment=支付类，general=通用类）',
      },
    },
    {
      fieldName: 'discountRate',
      label: '折扣率（如8.5表示8.5折，仅折扣券有效）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入折扣率（如8.5表示8.5折，仅折扣券有效）',
      },
    },
    {
      fieldName: 'discountAmount',
      label: '优惠金额（代金券金额或减免金额）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入优惠金额（代金券金额或减免金额）',
      },
    },
    {
      fieldName: 'giftName',
      label: '礼品名称（仅礼品券有效）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入礼品名称（仅礼品券有效）',
      },
    },
    {
      fieldName: 'rateDiscount',
      label: '利率优惠（如0.5表示利率下调0.5%，仅利率券有效）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入利率优惠（如0.5表示利率下调0.5%，仅利率券有效）',
      },
    },
    {
      fieldName: 'thresholdAmount',
      label: '使用门槛金额（满多少可用，0表示无门槛）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入使用门槛金额（满多少可用，0表示无门槛）',
      },
    },
    {
      fieldName: 'maxDiscountAmount',
      label: '最高优惠金额（封顶金额）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入最高优惠金额（封顶金额）',
      },
    },
    {
      fieldName: 'couponStatus',
      label: '卡券状态（字典: aicrm_coupon_status，unused=未使用，used=已使用，expired=已过期，cancelled=已作废，locked=已锁定）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'issueDate',
      label: '发放日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'issueTime',
      label: '发放时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'effectiveDate',
      label: '生效日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'expiryDate',
      label: '过期日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'useDate',
      label: '使用日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'useTime',
      label: '使用时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'cancelDate',
      label: '作废日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'cancelReason',
      label: '作废原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入作废原因',
      },
    },
    {
      fieldName: 'issueSource',
      label: '发放来源（字典: aicrm_coupon_issue_source，system_gift=系统赠送，activity_reward=活动奖励，birthday_gift=生日礼物，point_exchange=积分兑换，manual_issue=手动发放）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发放来源（字典: aicrm_coupon_issue_source，system_gift=系统赠送，activity_reward=活动奖励，birthday_gift=生日礼物，point_exchange=积分兑换，manual_issue=手动发放）',
      },
    },
    {
      fieldName: 'issueChannel',
      label: '发放渠道（字典: aicrm_coupon_channel，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，counter=柜台，crm=CRM系统）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发放渠道（字典: aicrm_coupon_channel，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，counter=柜台，crm=CRM系统）',
      },
    },
    {
      fieldName: 'issueActivityId',
      label: '关联活动ID（如果来源于营销活动）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联活动ID（如果来源于营销活动）',
      },
    },
    {
      fieldName: 'issueActivityName',
      label: '关联活动名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联活动名称',
      },
    },
    {
      fieldName: 'useScenario',
      label: '使用场景（字典: aicrm_coupon_use_scenario，online_purchase=线上购买，counter_purchase=柜台办理，app_purchase=APP办理，wealth_purchase=理财购买，loan_apply=贷款申请）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入使用场景（字典: aicrm_coupon_use_scenario，online_purchase=线上购买，counter_purchase=柜台办理，app_purchase=APP办理，wealth_purchase=理财购买，loan_apply=贷款申请）',
      },
    },
    {
      fieldName: 'useChannel',
      label: '使用渠道（字典: aicrm_coupon_channel）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入使用渠道（字典: aicrm_coupon_channel）',
      },
    },
    {
      fieldName: 'useProductCode',
      label: '使用产品代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入使用产品代码',
      },
    },
    {
      fieldName: 'useProductName',
      label: '使用产品名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入使用产品名称',
      },
    },
    {
      fieldName: 'useOrderNo',
      label: '使用订单号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入使用订单号',
      },
    },
    {
      fieldName: 'useTransactionAmount',
      label: '交易金额（使用卡券时的交易金额）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易金额（使用卡券时的交易金额）',
      },
    },
    {
      fieldName: 'actualDiscountAmount',
      label: '实际优惠金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入实际优惠金额',
      },
    },
    {
      fieldName: 'applicableProducts',
      label: '适用产品（JSON数组，如["PROD001","PROD002"]，null表示通用）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入适用产品（JSON数组，如["PROD001","PROD002"]，null表示通用）',
      },
    },
    {
      fieldName: 'applicableChannels',
      label: '适用渠道（JSON数组，null表示通用）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入适用渠道（JSON数组，null表示通用）',
      },
    },
    {
      fieldName: 'useLimitTimes',
      label: '可使用次数（1=单次使用，>1=可多次使用）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入可使用次数（1=单次使用，>1=可多次使用）',
      },
    },
    {
      fieldName: 'usedTimes',
      label: '已使用次数',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入已使用次数',
      },
    },
    {
      fieldName: 'isTransferable',
      label: '是否可转赠',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'isStackable',
      label: '是否可叠加使用',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'expireRemindDays',
      label: '过期提醒天数（提前几天提醒）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入过期提醒天数（提前几天提醒）',
      },
    },
    {
      fieldName: 'isReminded',
      label: '是否已提醒',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'remindTime',
      label: '提醒时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'description',
      label: '卡券描述（使用说明）',
      component: 'RichTextarea',
    },
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Input',
      componentProps: {
        placeholder: '请输入备注',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID（关联 crm_customer 主表，仅限零售客户）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联 crm_customer 主表，仅限零售客户）',
      },
    },
    {
      fieldName: 'couponNo',
      label: '卡券编号（唯一）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入卡券编号（唯一）',
      },
    },
    {
      fieldName: 'couponCode',
      label: '卡券代码（券模板编码）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入卡券代码（券模板编码）',
      },
    },
    {
      fieldName: 'couponName',
      label: '卡券名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入卡券名称',
      },
    },
    {
      fieldName: 'couponType',
      label: '卡券类型（字典: aicrm_coupon_type，discount=折扣券，cash=代金券，gift=礼品券，rate=利率券，fee_waiver=手续费减免券）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择卡券类型（字典: aicrm_coupon_type，discount=折扣券，cash=代金券，gift=礼品券，rate=利率券，fee_waiver=手续费减免券）',
      },
    },
    {
      fieldName: 'couponCategory',
      label: '卡券分类（字典: aicrm_coupon_category，deposit=存款类，wealth=理财类，loan=贷款类，payment=支付类，general=通用类）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入卡券分类（字典: aicrm_coupon_category，deposit=存款类，wealth=理财类，loan=贷款类，payment=支付类，general=通用类）',
      },
    },
    {
      fieldName: 'discountRate',
      label: '折扣率（如8.5表示8.5折，仅折扣券有效）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入折扣率（如8.5表示8.5折，仅折扣券有效）',
      },
    },
    {
      fieldName: 'discountAmount',
      label: '优惠金额（代金券金额或减免金额）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入优惠金额（代金券金额或减免金额）',
      },
    },
    {
      fieldName: 'giftName',
      label: '礼品名称（仅礼品券有效）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入礼品名称（仅礼品券有效）',
      },
    },
    {
      fieldName: 'rateDiscount',
      label: '利率优惠（如0.5表示利率下调0.5%，仅利率券有效）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入利率优惠（如0.5表示利率下调0.5%，仅利率券有效）',
      },
    },
    {
      fieldName: 'thresholdAmount',
      label: '使用门槛金额（满多少可用，0表示无门槛）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入使用门槛金额（满多少可用，0表示无门槛）',
      },
    },
    {
      fieldName: 'maxDiscountAmount',
      label: '最高优惠金额（封顶金额）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入最高优惠金额（封顶金额）',
      },
    },
    {
      fieldName: 'couponStatus',
      label: '卡券状态（字典: aicrm_coupon_status，unused=未使用，used=已使用，expired=已过期，cancelled=已作废，locked=已锁定）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择卡券状态（字典: aicrm_coupon_status，unused=未使用，used=已使用，expired=已过期，cancelled=已作废，locked=已锁定）',
      },
    },
    {
      fieldName: 'issueDate',
      label: '发放日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'issueTime',
      label: '发放时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'effectiveDate',
      label: '生效日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'expiryDate',
      label: '过期日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'useDate',
      label: '使用日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'useTime',
      label: '使用时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'cancelDate',
      label: '作废日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'cancelReason',
      label: '作废原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入作废原因',
      },
    },
    {
      fieldName: 'issueSource',
      label: '发放来源（字典: aicrm_coupon_issue_source，system_gift=系统赠送，activity_reward=活动奖励，birthday_gift=生日礼物，point_exchange=积分兑换，manual_issue=手动发放）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发放来源（字典: aicrm_coupon_issue_source，system_gift=系统赠送，activity_reward=活动奖励，birthday_gift=生日礼物，point_exchange=积分兑换，manual_issue=手动发放）',
      },
    },
    {
      fieldName: 'issueChannel',
      label: '发放渠道（字典: aicrm_coupon_channel，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，counter=柜台，crm=CRM系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发放渠道（字典: aicrm_coupon_channel，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，counter=柜台，crm=CRM系统）',
      },
    },
    {
      fieldName: 'issueActivityId',
      label: '关联活动ID（如果来源于营销活动）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联活动ID（如果来源于营销活动）',
      },
    },
    {
      fieldName: 'issueActivityName',
      label: '关联活动名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联活动名称',
      },
    },
    {
      fieldName: 'useScenario',
      label: '使用场景（字典: aicrm_coupon_use_scenario，online_purchase=线上购买，counter_purchase=柜台办理，app_purchase=APP办理，wealth_purchase=理财购买，loan_apply=贷款申请）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入使用场景（字典: aicrm_coupon_use_scenario，online_purchase=线上购买，counter_purchase=柜台办理，app_purchase=APP办理，wealth_purchase=理财购买，loan_apply=贷款申请）',
      },
    },
    {
      fieldName: 'useChannel',
      label: '使用渠道（字典: aicrm_coupon_channel）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入使用渠道（字典: aicrm_coupon_channel）',
      },
    },
    {
      fieldName: 'useProductCode',
      label: '使用产品代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入使用产品代码',
      },
    },
    {
      fieldName: 'useProductName',
      label: '使用产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入使用产品名称',
      },
    },
    {
      fieldName: 'useOrderNo',
      label: '使用订单号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入使用订单号',
      },
    },
    {
      fieldName: 'useTransactionAmount',
      label: '交易金额（使用卡券时的交易金额）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易金额（使用卡券时的交易金额）',
      },
    },
    {
      fieldName: 'actualDiscountAmount',
      label: '实际优惠金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入实际优惠金额',
      },
    },
    {
      fieldName: 'applicableProducts',
      label: '适用产品（JSON数组，如["PROD001","PROD002"]，null表示通用）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入适用产品（JSON数组，如["PROD001","PROD002"]，null表示通用）',
      },
    },
    {
      fieldName: 'applicableChannels',
      label: '适用渠道（JSON数组，null表示通用）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入适用渠道（JSON数组，null表示通用）',
      },
    },
    {
      fieldName: 'useLimitTimes',
      label: '可使用次数（1=单次使用，>1=可多次使用）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入可使用次数（1=单次使用，>1=可多次使用）',
      },
    },
    {
      fieldName: 'usedTimes',
      label: '已使用次数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入已使用次数',
      },
    },
    {
      fieldName: 'isTransferable',
      label: '是否可转赠',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否可转赠',
      },
    },
    {
      fieldName: 'isStackable',
      label: '是否可叠加使用',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否可叠加使用',
      },
    },
    {
      fieldName: 'expireRemindDays',
      label: '过期提醒天数（提前几天提醒）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入过期提醒天数（提前几天提醒）',
      },
    },
    {
      fieldName: 'isReminded',
      label: '是否已提醒',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否已提醒',
      },
    },
    {
      fieldName: 'remindTime',
      label: '提醒时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'description',
      label: '卡券描述（使用说明）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入卡券描述（使用说明）',
      },
    },
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入备注',
      },
    },
    {
      fieldName: 'createTime',
      label: '创建时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerCouponApi.CustomerCoupon>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '卡券主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表，仅限零售客户）',
      minWidth: 120,
    },
    {
      field: 'couponNo',
      title: '卡券编号（唯一）',
      minWidth: 120,
    },
    {
      field: 'couponCode',
      title: '卡券代码（券模板编码）',
      minWidth: 120,
    },
    {
      field: 'couponName',
      title: '卡券名称',
      minWidth: 120,
    },
    {
      field: 'couponType',
      title: '卡券类型（字典: aicrm_coupon_type，discount=折扣券，cash=代金券，gift=礼品券，rate=利率券，fee_waiver=手续费减免券）',
      minWidth: 120,
    },
    {
      field: 'couponCategory',
      title: '卡券分类（字典: aicrm_coupon_category，deposit=存款类，wealth=理财类，loan=贷款类，payment=支付类，general=通用类）',
      minWidth: 120,
    },
    {
      field: 'discountRate',
      title: '折扣率（如8.5表示8.5折，仅折扣券有效）',
      minWidth: 120,
    },
    {
      field: 'discountAmount',
      title: '优惠金额（代金券金额或减免金额）',
      minWidth: 120,
    },
    {
      field: 'giftName',
      title: '礼品名称（仅礼品券有效）',
      minWidth: 120,
    },
    {
      field: 'rateDiscount',
      title: '利率优惠（如0.5表示利率下调0.5%，仅利率券有效）',
      minWidth: 120,
    },
    {
      field: 'thresholdAmount',
      title: '使用门槛金额（满多少可用，0表示无门槛）',
      minWidth: 120,
    },
    {
      field: 'maxDiscountAmount',
      title: '最高优惠金额（封顶金额）',
      minWidth: 120,
    },
    {
      field: 'couponStatus',
      title: '卡券状态（字典: aicrm_coupon_status，unused=未使用，used=已使用，expired=已过期，cancelled=已作废，locked=已锁定）',
      minWidth: 120,
    },
    {
      field: 'issueDate',
      title: '发放日期',
      minWidth: 120,
    },
    {
      field: 'issueTime',
      title: '发放时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'effectiveDate',
      title: '生效日期',
      minWidth: 120,
    },
    {
      field: 'expiryDate',
      title: '过期日期',
      minWidth: 120,
    },
    {
      field: 'useDate',
      title: '使用日期',
      minWidth: 120,
    },
    {
      field: 'useTime',
      title: '使用时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'cancelDate',
      title: '作废日期',
      minWidth: 120,
    },
    {
      field: 'cancelReason',
      title: '作废原因',
      minWidth: 120,
    },
    {
      field: 'issueSource',
      title: '发放来源（字典: aicrm_coupon_issue_source，system_gift=系统赠送，activity_reward=活动奖励，birthday_gift=生日礼物，point_exchange=积分兑换，manual_issue=手动发放）',
      minWidth: 120,
    },
    {
      field: 'issueChannel',
      title: '发放渠道（字典: aicrm_coupon_channel，mobile_banking=手机银行，web_banking=网上银行，wechat=微信银行，counter=柜台，crm=CRM系统）',
      minWidth: 120,
    },
    {
      field: 'issueActivityId',
      title: '关联活动ID（如果来源于营销活动）',
      minWidth: 120,
    },
    {
      field: 'issueActivityName',
      title: '关联活动名称',
      minWidth: 120,
    },
    {
      field: 'useScenario',
      title: '使用场景（字典: aicrm_coupon_use_scenario，online_purchase=线上购买，counter_purchase=柜台办理，app_purchase=APP办理，wealth_purchase=理财购买，loan_apply=贷款申请）',
      minWidth: 120,
    },
    {
      field: 'useChannel',
      title: '使用渠道（字典: aicrm_coupon_channel）',
      minWidth: 120,
    },
    {
      field: 'useProductCode',
      title: '使用产品代码',
      minWidth: 120,
    },
    {
      field: 'useProductName',
      title: '使用产品名称',
      minWidth: 120,
    },
    {
      field: 'useOrderNo',
      title: '使用订单号',
      minWidth: 120,
    },
    {
      field: 'useTransactionAmount',
      title: '交易金额（使用卡券时的交易金额）',
      minWidth: 120,
    },
    {
      field: 'actualDiscountAmount',
      title: '实际优惠金额',
      minWidth: 120,
    },
    {
      field: 'applicableProducts',
      title: '适用产品（JSON数组，如["PROD001","PROD002"]，null表示通用）',
      minWidth: 120,
    },
    {
      field: 'applicableChannels',
      title: '适用渠道（JSON数组，null表示通用）',
      minWidth: 120,
    },
    {
      field: 'useLimitTimes',
      title: '可使用次数（1=单次使用，>1=可多次使用）',
      minWidth: 120,
    },
    {
      field: 'usedTimes',
      title: '已使用次数',
      minWidth: 120,
    },
    {
      field: 'isTransferable',
      title: '是否可转赠',
      minWidth: 120,
    },
    {
      field: 'isStackable',
      title: '是否可叠加使用',
      minWidth: 120,
    },
    {
      field: 'expireRemindDays',
      title: '过期提醒天数（提前几天提醒）',
      minWidth: 120,
    },
    {
      field: 'isReminded',
      title: '是否已提醒',
      minWidth: 120,
    },
    {
      field: 'remindTime',
      title: '提醒时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'description',
      title: '卡券描述（使用说明）',
      minWidth: 120,
    },
    {
      field: 'remark',
      title: '备注',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}