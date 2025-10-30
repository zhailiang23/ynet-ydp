import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerReminderApi } from '#/api/aicrm/customerreminder';

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
      fieldName: 'sequenceNo',
      label: '序号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入序号',
      },
    },
    {
      fieldName: 'reminderCategoryName',
      label: '提醒类别名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入提醒类别名称',
      },
    },
    {
      fieldName: 'reminderGenerateDate',
      label: '提醒生成日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'reminderDueDate',
      label: '提醒到期日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'reminderContent',
      label: '提醒内容',
      rules: 'required',
      component: 'RichTextarea',
    },
    {
      fieldName: 'reminderNo',
      label: '提醒编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入提醒编号',
      },
    },
    {
      fieldName: 'reminderType',
      label: '提醒类型（字典: aicrm_reminder_type，account_balance=账户余额变动，credit_expire=授信到期，customer_contact=客户接触，customer_birthday=客户生日，loan_expire=贷款到期，product_expire=产品到期，payment_remind=还款提醒等）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择提醒类型（字典: aicrm_reminder_type，account_balance=账户余额变动，credit_expire=授信到期，customer_contact=客户接触，customer_birthday=客户生日，loan_expire=贷款到期，product_expire=产品到期，payment_remind=还款提醒等）',
      },
    },
    {
      fieldName: 'reminderLevel',
      label: '提醒级别（字典: aicrm_reminder_level，urgent=紧急，important=重要，normal=普通，info=提示）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入提醒级别（字典: aicrm_reminder_level，urgent=紧急，important=重要，normal=普通，info=提示）',
      },
    },
    {
      fieldName: 'reminderStatus',
      label: '提醒状态（字典: aicrm_reminder_status，pending=待处理，processing=处理中，completed=已完成，expired=已过期，cancelled=已取消）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'reminderSource',
      label: '提醒来源（字典: aicrm_reminder_source，system_auto=系统自动，manual_create=手动创建，rule_trigger=规则触发，data_analysis=数据分析）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入提醒来源（字典: aicrm_reminder_source，system_auto=系统自动，manual_create=手动创建，rule_trigger=规则触发，data_analysis=数据分析）',
      },
    },
    {
      fieldName: 'isSent',
      label: '是否已发送',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'sendTime',
      label: '发送时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'sendChannel',
      label: '发送渠道（字典: aicrm_reminder_channel，sms=短信，email=邮件，wechat=微信，system=系统消息，phone=电话）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发送渠道（字典: aicrm_reminder_channel，sms=短信，email=邮件，wechat=微信，system=系统消息，phone=电话）',
      },
    },
    {
      fieldName: 'recipientUserId',
      label: '接收人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接收人ID',
      },
    },
    {
      fieldName: 'recipientUserName',
      label: '接收人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接收人姓名',
      },
    },
    {
      fieldName: 'recipientDeptId',
      label: '接收部门ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接收部门ID',
      },
    },
    {
      fieldName: 'recipientDeptName',
      label: '接收部门名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接收部门名称',
      },
    },
    {
      fieldName: 'handlerUserId',
      label: '处理人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理人ID',
      },
    },
    {
      fieldName: 'handlerUserName',
      label: '处理人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理人姓名',
      },
    },
    {
      fieldName: 'handleTime',
      label: '处理时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'handleResult',
      label: '处理结果',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理结果',
      },
    },
    {
      fieldName: 'isRepeat',
      label: '是否重复提醒',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'repeatRule',
      label: '重复规则（如：每天、每周、每月等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入重复规则（如：每天、每周、每月等）',
      },
    },
    {
      fieldName: 'nextRemindTime',
      label: '下次提醒时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isRead',
      label: '是否已读',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'readTime',
      label: '阅读时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'priority',
      label: '优先级（数字越大优先级越高）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入优先级（数字越大优先级越高）',
      },
    },
    {
      fieldName: 'relatedBusinessType',
      label: '关联业务类型（字典: aicrm_business_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择关联业务类型（字典: aicrm_business_type）',
      },
    },
    {
      fieldName: 'relatedBusinessId',
      label: '关联业务ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联业务ID',
      },
    },
    {
      fieldName: 'expireTime',
      label: '过期时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isExpired',
      label: '是否已过期',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'isCancelled',
      label: '是否已取消',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'cancelTime',
      label: '取消时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'cancelReason',
      label: '取消原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入取消原因',
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
      fieldName: 'sequenceNo',
      label: '序号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入序号',
      },
    },
    {
      fieldName: 'reminderCategoryName',
      label: '提醒类别名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入提醒类别名称',
      },
    },
    {
      fieldName: 'reminderGenerateDate',
      label: '提醒生成日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'reminderDueDate',
      label: '提醒到期日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'reminderContent',
      label: '提醒内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入提醒内容',
      },
    },
    {
      fieldName: 'reminderNo',
      label: '提醒编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入提醒编号',
      },
    },
    {
      fieldName: 'reminderType',
      label: '提醒类型（字典: aicrm_reminder_type，account_balance=账户余额变动，credit_expire=授信到期，customer_contact=客户接触，customer_birthday=客户生日，loan_expire=贷款到期，product_expire=产品到期，payment_remind=还款提醒等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择提醒类型（字典: aicrm_reminder_type，account_balance=账户余额变动，credit_expire=授信到期，customer_contact=客户接触，customer_birthday=客户生日，loan_expire=贷款到期，product_expire=产品到期，payment_remind=还款提醒等）',
      },
    },
    {
      fieldName: 'reminderLevel',
      label: '提醒级别（字典: aicrm_reminder_level，urgent=紧急，important=重要，normal=普通，info=提示）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入提醒级别（字典: aicrm_reminder_level，urgent=紧急，important=重要，normal=普通，info=提示）',
      },
    },
    {
      fieldName: 'reminderStatus',
      label: '提醒状态（字典: aicrm_reminder_status，pending=待处理，processing=处理中，completed=已完成，expired=已过期，cancelled=已取消）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择提醒状态（字典: aicrm_reminder_status，pending=待处理，processing=处理中，completed=已完成，expired=已过期，cancelled=已取消）',
      },
    },
    {
      fieldName: 'reminderSource',
      label: '提醒来源（字典: aicrm_reminder_source，system_auto=系统自动，manual_create=手动创建，rule_trigger=规则触发，data_analysis=数据分析）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入提醒来源（字典: aicrm_reminder_source，system_auto=系统自动，manual_create=手动创建，rule_trigger=规则触发，data_analysis=数据分析）',
      },
    },
    {
      fieldName: 'isSent',
      label: '是否已发送',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否已发送',
      },
    },
    {
      fieldName: 'sendTime',
      label: '发送时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'sendChannel',
      label: '发送渠道（字典: aicrm_reminder_channel，sms=短信，email=邮件，wechat=微信，system=系统消息，phone=电话）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发送渠道（字典: aicrm_reminder_channel，sms=短信，email=邮件，wechat=微信，system=系统消息，phone=电话）',
      },
    },
    {
      fieldName: 'recipientUserId',
      label: '接收人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接收人ID',
      },
    },
    {
      fieldName: 'recipientUserName',
      label: '接收人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接收人姓名',
      },
    },
    {
      fieldName: 'recipientDeptId',
      label: '接收部门ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接收部门ID',
      },
    },
    {
      fieldName: 'recipientDeptName',
      label: '接收部门名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接收部门名称',
      },
    },
    {
      fieldName: 'handlerUserId',
      label: '处理人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理人ID',
      },
    },
    {
      fieldName: 'handlerUserName',
      label: '处理人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理人姓名',
      },
    },
    {
      fieldName: 'handleTime',
      label: '处理时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'handleResult',
      label: '处理结果',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理结果',
      },
    },
    {
      fieldName: 'isRepeat',
      label: '是否重复提醒',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否重复提醒',
      },
    },
    {
      fieldName: 'repeatRule',
      label: '重复规则（如：每天、每周、每月等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入重复规则（如：每天、每周、每月等）',
      },
    },
    {
      fieldName: 'nextRemindTime',
      label: '下次提醒时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'isRead',
      label: '是否已读',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否已读',
      },
    },
    {
      fieldName: 'readTime',
      label: '阅读时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'priority',
      label: '优先级（数字越大优先级越高）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入优先级（数字越大优先级越高）',
      },
    },
    {
      fieldName: 'relatedBusinessType',
      label: '关联业务类型（字典: aicrm_business_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择关联业务类型（字典: aicrm_business_type）',
      },
    },
    {
      fieldName: 'relatedBusinessId',
      label: '关联业务ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联业务ID',
      },
    },
    {
      fieldName: 'expireTime',
      label: '过期时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'isExpired',
      label: '是否已过期',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否已过期',
      },
    },
    {
      fieldName: 'isCancelled',
      label: '是否已取消',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否已取消',
      },
    },
    {
      fieldName: 'cancelTime',
      label: '取消时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'cancelReason',
      label: '取消原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入取消原因',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerReminderApi.CustomerReminder>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '提醒主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'sequenceNo',
      title: '序号',
      minWidth: 120,
    },
    {
      field: 'reminderCategoryName',
      title: '提醒类别名称',
      minWidth: 120,
    },
    {
      field: 'reminderGenerateDate',
      title: '提醒生成日期',
      minWidth: 120,
    },
    {
      field: 'reminderDueDate',
      title: '提醒到期日期',
      minWidth: 120,
    },
    {
      field: 'reminderContent',
      title: '提醒内容',
      minWidth: 120,
    },
    {
      field: 'reminderNo',
      title: '提醒编号',
      minWidth: 120,
    },
    {
      field: 'reminderType',
      title: '提醒类型（字典: aicrm_reminder_type，account_balance=账户余额变动，credit_expire=授信到期，customer_contact=客户接触，customer_birthday=客户生日，loan_expire=贷款到期，product_expire=产品到期，payment_remind=还款提醒等）',
      minWidth: 120,
    },
    {
      field: 'reminderLevel',
      title: '提醒级别（字典: aicrm_reminder_level，urgent=紧急，important=重要，normal=普通，info=提示）',
      minWidth: 120,
    },
    {
      field: 'reminderStatus',
      title: '提醒状态（字典: aicrm_reminder_status，pending=待处理，processing=处理中，completed=已完成，expired=已过期，cancelled=已取消）',
      minWidth: 120,
    },
    {
      field: 'reminderSource',
      title: '提醒来源（字典: aicrm_reminder_source，system_auto=系统自动，manual_create=手动创建，rule_trigger=规则触发，data_analysis=数据分析）',
      minWidth: 120,
    },
    {
      field: 'isSent',
      title: '是否已发送',
      minWidth: 120,
    },
    {
      field: 'sendTime',
      title: '发送时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'sendChannel',
      title: '发送渠道（字典: aicrm_reminder_channel，sms=短信，email=邮件，wechat=微信，system=系统消息，phone=电话）',
      minWidth: 120,
    },
    {
      field: 'recipientUserId',
      title: '接收人ID',
      minWidth: 120,
    },
    {
      field: 'recipientUserName',
      title: '接收人姓名',
      minWidth: 120,
    },
    {
      field: 'recipientDeptId',
      title: '接收部门ID',
      minWidth: 120,
    },
    {
      field: 'recipientDeptName',
      title: '接收部门名称',
      minWidth: 120,
    },
    {
      field: 'handlerUserId',
      title: '处理人ID',
      minWidth: 120,
    },
    {
      field: 'handlerUserName',
      title: '处理人姓名',
      minWidth: 120,
    },
    {
      field: 'handleTime',
      title: '处理时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'handleResult',
      title: '处理结果',
      minWidth: 120,
    },
    {
      field: 'isRepeat',
      title: '是否重复提醒',
      minWidth: 120,
    },
    {
      field: 'repeatRule',
      title: '重复规则（如：每天、每周、每月等）',
      minWidth: 120,
    },
    {
      field: 'nextRemindTime',
      title: '下次提醒时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'isRead',
      title: '是否已读',
      minWidth: 120,
    },
    {
      field: 'readTime',
      title: '阅读时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'priority',
      title: '优先级（数字越大优先级越高）',
      minWidth: 120,
    },
    {
      field: 'relatedBusinessType',
      title: '关联业务类型（字典: aicrm_business_type）',
      minWidth: 120,
    },
    {
      field: 'relatedBusinessId',
      title: '关联业务ID',
      minWidth: 120,
    },
    {
      field: 'expireTime',
      title: '过期时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'isExpired',
      title: '是否已过期',
      minWidth: 120,
    },
    {
      field: 'isCancelled',
      title: '是否已取消',
      minWidth: 120,
    },
    {
      field: 'cancelTime',
      title: '取消时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'cancelReason',
      title: '取消原因',
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