import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerPointsTransactionApi } from '#/api/aicrm/customerpointstransaction';

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
      label: '客户ID（关联 crm_customer 主表）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联 crm_customer 主表）',
      },
    },
    {
      fieldName: 'pointsAccountId',
      label: '积分账户ID（关联 crm_customer_points 表）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入积分账户ID（关联 crm_customer_points 表）',
      },
    },
    {
      fieldName: 'orderNo',
      label: '订单编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入订单编号',
      },
    },
    {
      fieldName: 'transactionDate',
      label: '交易日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'exchangeChannel',
      label: '兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）',
      },
    },
    {
      fieldName: 'giftExchangeMethod',
      label: '礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）',
      },
    },
    {
      fieldName: 'orderConsumedPoints',
      label: '订单消耗积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入订单消耗积分',
      },
    },
    {
      fieldName: 'orderStatus',
      label: '订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'orderApprovalStatus',
      label: '订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'exchangeUser',
      label: '兑换用户',
      component: 'Input',
      componentProps: {
        placeholder: '请输入兑换用户',
      },
    },
    {
      fieldName: 'transactionNo',
      label: '交易流水号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易流水号',
      },
    },
    {
      fieldName: 'transactionType',
      label: '交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）',
      },
    },
    {
      fieldName: 'transactionTime',
      label: '交易时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'pointsChange',
      label: '积分变动（正数为增加，负数为减少）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入积分变动（正数为增加，负数为减少）',
      },
    },
    {
      fieldName: 'pointsBefore',
      label: '交易前积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易前积分',
      },
    },
    {
      fieldName: 'pointsAfter',
      label: '交易后积分',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易后积分',
      },
    },
    {
      fieldName: 'transactionStatus',
      label: '交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'transactionDesc',
      label: '交易描述',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易描述',
      },
    },
    {
      fieldName: 'businessType',
      label: '业务类型（字典: aicrm_points_business_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择业务类型（字典: aicrm_points_business_type）',
      },
    },
    {
      fieldName: 'businessNo',
      label: '业务单号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务单号',
      },
    },
    {
      fieldName: 'giftCode',
      label: '礼品编码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入礼品编码',
      },
    },
    {
      fieldName: 'giftName',
      label: '礼品名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入礼品名称',
      },
    },
    {
      fieldName: 'giftQuantity',
      label: '礼品数量',
      component: 'Input',
      componentProps: {
        placeholder: '请输入礼品数量',
      },
    },
    {
      fieldName: 'giftValue',
      label: '礼品价值',
      component: 'Input',
      componentProps: {
        placeholder: '请输入礼品价值',
      },
    },
    {
      fieldName: 'deliveryAddress',
      label: '配送地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入配送地址',
      },
    },
    {
      fieldName: 'deliveryStatus',
      label: '配送状态（字典: aicrm_delivery_status，pending=待发货，shipped=已发货，delivered=已送达，rejected=拒收）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'deliveryTime',
      label: '配送时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'receiverName',
      label: '收货人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入收货人姓名',
      },
    },
    {
      fieldName: 'receiverPhone',
      label: '收货人电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入收货人电话',
      },
    },
    {
      fieldName: 'expireDate',
      label: '过期日期（对于有效期的积分）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'refundReason',
      label: '退款原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入退款原因',
      },
    },
    {
      fieldName: 'refundTime',
      label: '退款时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'refundPoints',
      label: '退款积分',
      component: 'Input',
      componentProps: {
        placeholder: '请输入退款积分',
      },
    },
    {
      fieldName: 'approvalUserId',
      label: '审批人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入审批人ID',
      },
    },
    {
      fieldName: 'approvalUserName',
      label: '审批人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入审批人姓名',
      },
    },
    {
      fieldName: 'approvalTime',
      label: '审批时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'approvalRemark',
      label: '审批备注',
      component: 'Input',
      componentProps: {
        placeholder: '请输入审批备注',
      },
    },
    {
      fieldName: 'operatorUserId',
      label: '操作人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入操作人ID',
      },
    },
    {
      fieldName: 'operatorUserName',
      label: '操作人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入操作人姓名',
      },
    },
    {
      fieldName: 'operatorDeptId',
      label: '操作部门ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入操作部门ID',
      },
    },
    {
      fieldName: 'operatorDeptName',
      label: '操作部门名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入操作部门名称',
      },
    },
    {
      fieldName: 'isReversed',
      label: '是否已冲正',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'reverseTransactionId',
      label: '冲正交易ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入冲正交易ID',
      },
    },
    {
      fieldName: 'reverseTime',
      label: '冲正时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
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
      label: '客户ID（关联 crm_customer 主表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联 crm_customer 主表）',
      },
    },
    {
      fieldName: 'pointsAccountId',
      label: '积分账户ID（关联 crm_customer_points 表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入积分账户ID（关联 crm_customer_points 表）',
      },
    },
    {
      fieldName: 'orderNo',
      label: '订单编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入订单编号',
      },
    },
    {
      fieldName: 'transactionDate',
      label: '交易日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'exchangeChannel',
      label: '兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）',
      },
    },
    {
      fieldName: 'giftExchangeMethod',
      label: '礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）',
      },
    },
    {
      fieldName: 'orderConsumedPoints',
      label: '订单消耗积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入订单消耗积分',
      },
    },
    {
      fieldName: 'orderStatus',
      label: '订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）',
      },
    },
    {
      fieldName: 'orderApprovalStatus',
      label: '订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）',
      },
    },
    {
      fieldName: 'exchangeUser',
      label: '兑换用户',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入兑换用户',
      },
    },
    {
      fieldName: 'transactionNo',
      label: '交易流水号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易流水号',
      },
    },
    {
      fieldName: 'transactionType',
      label: '交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）',
      },
    },
    {
      fieldName: 'transactionTime',
      label: '交易时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'pointsChange',
      label: '积分变动（正数为增加，负数为减少）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入积分变动（正数为增加，负数为减少）',
      },
    },
    {
      fieldName: 'pointsBefore',
      label: '交易前积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易前积分',
      },
    },
    {
      fieldName: 'pointsAfter',
      label: '交易后积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易后积分',
      },
    },
    {
      fieldName: 'transactionStatus',
      label: '交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）',
      },
    },
    {
      fieldName: 'transactionDesc',
      label: '交易描述',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易描述',
      },
    },
    {
      fieldName: 'businessType',
      label: '业务类型（字典: aicrm_points_business_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择业务类型（字典: aicrm_points_business_type）',
      },
    },
    {
      fieldName: 'businessNo',
      label: '业务单号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务单号',
      },
    },
    {
      fieldName: 'giftCode',
      label: '礼品编码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入礼品编码',
      },
    },
    {
      fieldName: 'giftName',
      label: '礼品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入礼品名称',
      },
    },
    {
      fieldName: 'giftQuantity',
      label: '礼品数量',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入礼品数量',
      },
    },
    {
      fieldName: 'giftValue',
      label: '礼品价值',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入礼品价值',
      },
    },
    {
      fieldName: 'deliveryAddress',
      label: '配送地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入配送地址',
      },
    },
    {
      fieldName: 'deliveryStatus',
      label: '配送状态（字典: aicrm_delivery_status，pending=待发货，shipped=已发货，delivered=已送达，rejected=拒收）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择配送状态（字典: aicrm_delivery_status，pending=待发货，shipped=已发货，delivered=已送达，rejected=拒收）',
      },
    },
    {
      fieldName: 'deliveryTime',
      label: '配送时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'receiverName',
      label: '收货人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入收货人姓名',
      },
    },
    {
      fieldName: 'receiverPhone',
      label: '收货人电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入收货人电话',
      },
    },
    {
      fieldName: 'expireDate',
      label: '过期日期（对于有效期的积分）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'refundReason',
      label: '退款原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入退款原因',
      },
    },
    {
      fieldName: 'refundTime',
      label: '退款时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'refundPoints',
      label: '退款积分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入退款积分',
      },
    },
    {
      fieldName: 'approvalUserId',
      label: '审批人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入审批人ID',
      },
    },
    {
      fieldName: 'approvalUserName',
      label: '审批人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入审批人姓名',
      },
    },
    {
      fieldName: 'approvalTime',
      label: '审批时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'approvalRemark',
      label: '审批备注',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入审批备注',
      },
    },
    {
      fieldName: 'operatorUserId',
      label: '操作人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入操作人ID',
      },
    },
    {
      fieldName: 'operatorUserName',
      label: '操作人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入操作人姓名',
      },
    },
    {
      fieldName: 'operatorDeptId',
      label: '操作部门ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入操作部门ID',
      },
    },
    {
      fieldName: 'operatorDeptName',
      label: '操作部门名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入操作部门名称',
      },
    },
    {
      fieldName: 'isReversed',
      label: '是否已冲正',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否已冲正',
      },
    },
    {
      fieldName: 'reverseTransactionId',
      label: '冲正交易ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入冲正交易ID',
      },
    },
    {
      fieldName: 'reverseTime',
      label: '冲正时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerPointsTransactionApi.CustomerPointsTransaction>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '交易主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'pointsAccountId',
      title: '积分账户ID（关联 crm_customer_points 表）',
      minWidth: 120,
    },
    {
      field: 'orderNo',
      title: '订单编号',
      minWidth: 120,
    },
    {
      field: 'transactionDate',
      title: '交易日期',
      minWidth: 120,
    },
    {
      field: 'exchangeChannel',
      title: '兑换渠道（字典: aicrm_exchange_channel，crm=CRM系统，website=网银，mobile_banking=手机银行，wechat=微信银行，counter=柜台）',
      minWidth: 120,
    },
    {
      field: 'giftExchangeMethod',
      title: '礼品兑换方式（字典: aicrm_gift_exchange_method，online=线上兑换，offline=线下兑换，mail=邮寄，pickup=自提）',
      minWidth: 120,
    },
    {
      field: 'orderConsumedPoints',
      title: '订单消耗积分',
      minWidth: 120,
    },
    {
      field: 'orderStatus',
      title: '订单状态（字典: aicrm_order_status，10=待处理，20=处理中，30=已完成，40=已取消，50=已退款）',
      minWidth: 120,
    },
    {
      field: 'orderApprovalStatus',
      title: '订单审批状态（字典: aicrm_order_approval_status，1=待审批，2=审批通过，3=审批拒绝）',
      minWidth: 120,
    },
    {
      field: 'exchangeUser',
      title: '兑换用户',
      minWidth: 120,
    },
    {
      field: 'transactionNo',
      title: '交易流水号',
      minWidth: 120,
    },
    {
      field: 'transactionType',
      title: '交易类型（字典: aicrm_points_transaction_type，earn_deposit=存款获取，earn_wealth=理财获取，earn_transaction=交易获取，earn_activity=活动获取，earn_gift=赠送获取，consume_redeem=兑换消费，consume_deduct=扣减，expire=过期失效，refund=退款，adjust=调整）',
      minWidth: 120,
    },
    {
      field: 'transactionTime',
      title: '交易时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'pointsChange',
      title: '积分变动（正数为增加，负数为减少）',
      minWidth: 120,
    },
    {
      field: 'pointsBefore',
      title: '交易前积分',
      minWidth: 120,
    },
    {
      field: 'pointsAfter',
      title: '交易后积分',
      minWidth: 120,
    },
    {
      field: 'transactionStatus',
      title: '交易状态（字典: aicrm_transaction_status，pending=待处理，processing=处理中，success=成功，failed=失败，cancelled=已取消）',
      minWidth: 120,
    },
    {
      field: 'transactionDesc',
      title: '交易描述',
      minWidth: 120,
    },
    {
      field: 'businessType',
      title: '业务类型（字典: aicrm_points_business_type）',
      minWidth: 120,
    },
    {
      field: 'businessNo',
      title: '业务单号',
      minWidth: 120,
    },
    {
      field: 'giftCode',
      title: '礼品编码',
      minWidth: 120,
    },
    {
      field: 'giftName',
      title: '礼品名称',
      minWidth: 120,
    },
    {
      field: 'giftQuantity',
      title: '礼品数量',
      minWidth: 120,
    },
    {
      field: 'giftValue',
      title: '礼品价值',
      minWidth: 120,
    },
    {
      field: 'deliveryAddress',
      title: '配送地址',
      minWidth: 120,
    },
    {
      field: 'deliveryStatus',
      title: '配送状态（字典: aicrm_delivery_status，pending=待发货，shipped=已发货，delivered=已送达，rejected=拒收）',
      minWidth: 120,
    },
    {
      field: 'deliveryTime',
      title: '配送时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'receiverName',
      title: '收货人姓名',
      minWidth: 120,
    },
    {
      field: 'receiverPhone',
      title: '收货人电话',
      minWidth: 120,
    },
    {
      field: 'expireDate',
      title: '过期日期（对于有效期的积分）',
      minWidth: 120,
    },
    {
      field: 'refundReason',
      title: '退款原因',
      minWidth: 120,
    },
    {
      field: 'refundTime',
      title: '退款时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'refundPoints',
      title: '退款积分',
      minWidth: 120,
    },
    {
      field: 'approvalUserId',
      title: '审批人ID',
      minWidth: 120,
    },
    {
      field: 'approvalUserName',
      title: '审批人姓名',
      minWidth: 120,
    },
    {
      field: 'approvalTime',
      title: '审批时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'approvalRemark',
      title: '审批备注',
      minWidth: 120,
    },
    {
      field: 'operatorUserId',
      title: '操作人ID',
      minWidth: 120,
    },
    {
      field: 'operatorUserName',
      title: '操作人姓名',
      minWidth: 120,
    },
    {
      field: 'operatorDeptId',
      title: '操作部门ID',
      minWidth: 120,
    },
    {
      field: 'operatorDeptName',
      title: '操作部门名称',
      minWidth: 120,
    },
    {
      field: 'isReversed',
      title: '是否已冲正',
      minWidth: 120,
    },
    {
      field: 'reverseTransactionId',
      title: '冲正交易ID',
      minWidth: 120,
    },
    {
      field: 'reverseTime',
      title: '冲正时间',
      minWidth: 120,
      formatter: 'formatDateTime',
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