import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerContactApi } from '#/api/aicrm/customercontact';

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
      fieldName: 'evaluationTime',
      label: '评估时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'contactType',
      label: '接触类型（字典: aicrm_contact_type，customer_callback=客户反馈，phone=电话，visit=拜访，email=邮件，wechat=微信，meeting=会议等）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择接触类型（字典: aicrm_contact_type，customer_callback=客户反馈，phone=电话，visit=拜访，email=邮件，wechat=微信，meeting=会议等）',
      },
    },
    {
      fieldName: 'accountManager',
      label: '客户经理',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户经理',
      },
    },
    {
      fieldName: 'contactPurpose',
      label: '接触目的',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触目的',
      },
    },
    {
      fieldName: 'contactFeedback',
      label: '接触反馈',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触反馈',
      },
    },
    {
      fieldName: 'cstVstId',
      label: '接触ID（老系统）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触ID（老系统）',
      },
    },
    {
      fieldName: 'cstId',
      label: '客户ID（老系统）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（老系统）',
      },
    },
    {
      fieldName: 'vstMthCd',
      label: '接触方式代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触方式代码',
      },
    },
    {
      fieldName: 'vstTm',
      label: '接触时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'cstInintCd',
      label: '客户意向代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户意向代码',
      },
    },
    {
      fieldName: 'vstPsnLst',
      label: '接触人员（我方参与人员）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触人员（我方参与人员）',
      },
    },
    {
      fieldName: 'cstPsnLst',
      label: '客户人员（客户方参与人员）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户人员（客户方参与人员）',
      },
    },
    {
      fieldName: 'vstAdr',
      label: '接触地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触地址',
      },
    },
    {
      fieldName: 'vstRsltDsc',
      label: '接触结果',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触结果',
      },
    },
    {
      fieldName: 'fuaTm',
      label: '跟进时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'toDoDsc',
      label: '待办事项',
      component: 'Input',
      componentProps: {
        placeholder: '请输入待办事项',
      },
    },
    {
      fieldName: 'othDsc',
      label: '其他描述',
      component: 'Input',
      componentProps: {
        placeholder: '请输入其他描述',
      },
    },
    {
      fieldName: 'crtUsrId',
      label: '创建用户ID（老系统）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入创建用户ID（老系统）',
      },
    },
    {
      fieldName: 'crtTm',
      label: '创建时间（老系统）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'udtUsrId',
      label: '更新用户ID（老系统）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入更新用户ID（老系统）',
      },
    },
    {
      fieldName: 'udtTm',
      label: '更新时间（老系统）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'contactNo',
      label: '接触编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触编号',
      },
    },
    {
      fieldName: 'contactStatus',
      label: '接触状态（字典: aicrm_contact_status，planned=已计划，completed=已完成，cancelled=已取消）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'contactChannel',
      label: '接触渠道（字典: aicrm_contact_channel，active=主动接触，passive=被动接触）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触渠道（字典: aicrm_contact_channel，active=主动接触，passive=被动接触）',
      },
    },
    {
      fieldName: 'contactResult',
      label: '接触结果（字典: aicrm_contact_result，success=成功，partial=部分成功，failed=失败）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触结果（字典: aicrm_contact_result，success=成功，partial=部分成功，failed=失败）',
      },
    },
    {
      fieldName: 'customerIntention',
      label: '客户意向（字典: aicrm_customer_intention，high=意向强烈，medium=意向一般，low=意向较弱，none=无意向）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户意向（字典: aicrm_customer_intention，high=意向强烈，medium=意向一般，low=意向较弱，none=无意向）',
      },
    },
    {
      fieldName: 'contactDuration',
      label: '接触时长（分钟）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触时长（分钟）',
      },
    },
    {
      fieldName: 'contactLocation',
      label: '接触地点',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触地点',
      },
    },
    {
      fieldName: 'contactSubject',
      label: '接触主题',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触主题',
      },
    },
    {
      fieldName: 'contactSummary',
      label: '接触摘要',
      component: 'Input',
      componentProps: {
        placeholder: '请输入接触摘要',
      },
    },
    {
      fieldName: 'nextContactTime',
      label: '下次接触时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'nextContactPlan',
      label: '下次接触计划',
      component: 'Input',
      componentProps: {
        placeholder: '请输入下次接触计划',
      },
    },
    {
      fieldName: 'isNeedFollowup',
      label: '是否需要跟进',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'followupUserId',
      label: '跟进人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入跟进人ID',
      },
    },
    {
      fieldName: 'followupUserName',
      label: '跟进人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入跟进人姓名',
      },
    },
    {
      fieldName: 'followupStatus',
      label: '跟进状态（字典: aicrm_followup_status，pending=待跟进，following=跟进中，completed=已完成）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'relatedActivityId',
      label: '关联营销活动ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联营销活动ID',
      },
    },
    {
      fieldName: 'relatedActivityName',
      label: '关联营销活动名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联营销活动名称',
      },
    },
    {
      fieldName: 'relatedProduct',
      label: '关联产品',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联产品',
      },
    },
    {
      fieldName: 'expectedAmount',
      label: '预期金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入预期金额（元）',
      },
    },
    {
      fieldName: 'actualAmount',
      label: '实际成交金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入实际成交金额（元）',
      },
    },
    {
      fieldName: 'isConverted',
      label: '是否已转化',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'convertTime',
      label: '转化时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'customerSatisfaction',
      label: '客户满意度（1-5星）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户满意度（1-5星）',
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
      fieldName: 'handlerDeptId',
      label: '处理部门ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理部门ID',
      },
    },
    {
      fieldName: 'handlerDeptName',
      label: '处理部门名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入处理部门名称',
      },
    },
    {
      fieldName: 'attachments',
      label: '附件列表（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入附件列表（JSON格式）',
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
      fieldName: 'evaluationTime',
      label: '评估时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'contactType',
      label: '接触类型（字典: aicrm_contact_type，customer_callback=客户反馈，phone=电话，visit=拜访，email=邮件，wechat=微信，meeting=会议等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择接触类型（字典: aicrm_contact_type，customer_callback=客户反馈，phone=电话，visit=拜访，email=邮件，wechat=微信，meeting=会议等）',
      },
    },
    {
      fieldName: 'accountManager',
      label: '客户经理',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户经理',
      },
    },
    {
      fieldName: 'contactPurpose',
      label: '接触目的',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触目的',
      },
    },
    {
      fieldName: 'contactFeedback',
      label: '接触反馈',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触反馈',
      },
    },
    {
      fieldName: 'cstVstId',
      label: '接触ID（老系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触ID（老系统）',
      },
    },
    {
      fieldName: 'cstId',
      label: '客户ID（老系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（老系统）',
      },
    },
    {
      fieldName: 'vstMthCd',
      label: '接触方式代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触方式代码',
      },
    },
    {
      fieldName: 'vstTm',
      label: '接触时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'cstInintCd',
      label: '客户意向代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户意向代码',
      },
    },
    {
      fieldName: 'vstPsnLst',
      label: '接触人员（我方参与人员）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触人员（我方参与人员）',
      },
    },
    {
      fieldName: 'cstPsnLst',
      label: '客户人员（客户方参与人员）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户人员（客户方参与人员）',
      },
    },
    {
      fieldName: 'vstAdr',
      label: '接触地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触地址',
      },
    },
    {
      fieldName: 'vstRsltDsc',
      label: '接触结果',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触结果',
      },
    },
    {
      fieldName: 'fuaTm',
      label: '跟进时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'toDoDsc',
      label: '待办事项',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入待办事项',
      },
    },
    {
      fieldName: 'othDsc',
      label: '其他描述',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入其他描述',
      },
    },
    {
      fieldName: 'crtUsrId',
      label: '创建用户ID（老系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入创建用户ID（老系统）',
      },
    },
    {
      fieldName: 'crtTm',
      label: '创建时间（老系统）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'udtUsrId',
      label: '更新用户ID（老系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入更新用户ID（老系统）',
      },
    },
    {
      fieldName: 'udtTm',
      label: '更新时间（老系统）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'contactNo',
      label: '接触编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触编号',
      },
    },
    {
      fieldName: 'contactStatus',
      label: '接触状态（字典: aicrm_contact_status，planned=已计划，completed=已完成，cancelled=已取消）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择接触状态（字典: aicrm_contact_status，planned=已计划，completed=已完成，cancelled=已取消）',
      },
    },
    {
      fieldName: 'contactChannel',
      label: '接触渠道（字典: aicrm_contact_channel，active=主动接触，passive=被动接触）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触渠道（字典: aicrm_contact_channel，active=主动接触，passive=被动接触）',
      },
    },
    {
      fieldName: 'contactResult',
      label: '接触结果（字典: aicrm_contact_result，success=成功，partial=部分成功，failed=失败）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触结果（字典: aicrm_contact_result，success=成功，partial=部分成功，failed=失败）',
      },
    },
    {
      fieldName: 'customerIntention',
      label: '客户意向（字典: aicrm_customer_intention，high=意向强烈，medium=意向一般，low=意向较弱，none=无意向）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户意向（字典: aicrm_customer_intention，high=意向强烈，medium=意向一般，low=意向较弱，none=无意向）',
      },
    },
    {
      fieldName: 'contactDuration',
      label: '接触时长（分钟）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触时长（分钟）',
      },
    },
    {
      fieldName: 'contactLocation',
      label: '接触地点',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触地点',
      },
    },
    {
      fieldName: 'contactSubject',
      label: '接触主题',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触主题',
      },
    },
    {
      fieldName: 'contactSummary',
      label: '接触摘要',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入接触摘要',
      },
    },
    {
      fieldName: 'nextContactTime',
      label: '下次接触时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'nextContactPlan',
      label: '下次接触计划',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入下次接触计划',
      },
    },
    {
      fieldName: 'isNeedFollowup',
      label: '是否需要跟进',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否需要跟进',
      },
    },
    {
      fieldName: 'followupUserId',
      label: '跟进人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入跟进人ID',
      },
    },
    {
      fieldName: 'followupUserName',
      label: '跟进人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入跟进人姓名',
      },
    },
    {
      fieldName: 'followupStatus',
      label: '跟进状态（字典: aicrm_followup_status，pending=待跟进，following=跟进中，completed=已完成）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择跟进状态（字典: aicrm_followup_status，pending=待跟进，following=跟进中，completed=已完成）',
      },
    },
    {
      fieldName: 'relatedActivityId',
      label: '关联营销活动ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联营销活动ID',
      },
    },
    {
      fieldName: 'relatedActivityName',
      label: '关联营销活动名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联营销活动名称',
      },
    },
    {
      fieldName: 'relatedProduct',
      label: '关联产品',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联产品',
      },
    },
    {
      fieldName: 'expectedAmount',
      label: '预期金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入预期金额（元）',
      },
    },
    {
      fieldName: 'actualAmount',
      label: '实际成交金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入实际成交金额（元）',
      },
    },
    {
      fieldName: 'isConverted',
      label: '是否已转化',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否已转化',
      },
    },
    {
      fieldName: 'convertTime',
      label: '转化时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'customerSatisfaction',
      label: '客户满意度（1-5星）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户满意度（1-5星）',
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
      fieldName: 'handlerDeptId',
      label: '处理部门ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理部门ID',
      },
    },
    {
      fieldName: 'handlerDeptName',
      label: '处理部门名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入处理部门名称',
      },
    },
    {
      fieldName: 'attachments',
      label: '附件列表（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入附件列表（JSON格式）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerContactApi.CustomerContact>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '接触主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'evaluationTime',
      title: '评估时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'contactType',
      title: '接触类型（字典: aicrm_contact_type，customer_callback=客户反馈，phone=电话，visit=拜访，email=邮件，wechat=微信，meeting=会议等）',
      minWidth: 120,
    },
    {
      field: 'accountManager',
      title: '客户经理',
      minWidth: 120,
    },
    {
      field: 'contactPurpose',
      title: '接触目的',
      minWidth: 120,
    },
    {
      field: 'contactFeedback',
      title: '接触反馈',
      minWidth: 120,
    },
    {
      field: 'cstVstId',
      title: '接触ID（老系统）',
      minWidth: 120,
    },
    {
      field: 'cstId',
      title: '客户ID（老系统）',
      minWidth: 120,
    },
    {
      field: 'vstMthCd',
      title: '接触方式代码',
      minWidth: 120,
    },
    {
      field: 'vstTm',
      title: '接触时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'cstInintCd',
      title: '客户意向代码',
      minWidth: 120,
    },
    {
      field: 'vstPsnLst',
      title: '接触人员（我方参与人员）',
      minWidth: 120,
    },
    {
      field: 'cstPsnLst',
      title: '客户人员（客户方参与人员）',
      minWidth: 120,
    },
    {
      field: 'vstAdr',
      title: '接触地址',
      minWidth: 120,
    },
    {
      field: 'vstRsltDsc',
      title: '接触结果',
      minWidth: 120,
    },
    {
      field: 'fuaTm',
      title: '跟进时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'toDoDsc',
      title: '待办事项',
      minWidth: 120,
    },
    {
      field: 'othDsc',
      title: '其他描述',
      minWidth: 120,
    },
    {
      field: 'crtUsrId',
      title: '创建用户ID（老系统）',
      minWidth: 120,
    },
    {
      field: 'crtTm',
      title: '创建时间（老系统）',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'udtUsrId',
      title: '更新用户ID（老系统）',
      minWidth: 120,
    },
    {
      field: 'udtTm',
      title: '更新时间（老系统）',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'contactNo',
      title: '接触编号',
      minWidth: 120,
    },
    {
      field: 'contactStatus',
      title: '接触状态（字典: aicrm_contact_status，planned=已计划，completed=已完成，cancelled=已取消）',
      minWidth: 120,
    },
    {
      field: 'contactChannel',
      title: '接触渠道（字典: aicrm_contact_channel，active=主动接触，passive=被动接触）',
      minWidth: 120,
    },
    {
      field: 'contactResult',
      title: '接触结果（字典: aicrm_contact_result，success=成功，partial=部分成功，failed=失败）',
      minWidth: 120,
    },
    {
      field: 'customerIntention',
      title: '客户意向（字典: aicrm_customer_intention，high=意向强烈，medium=意向一般，low=意向较弱，none=无意向）',
      minWidth: 120,
    },
    {
      field: 'contactDuration',
      title: '接触时长（分钟）',
      minWidth: 120,
    },
    {
      field: 'contactLocation',
      title: '接触地点',
      minWidth: 120,
    },
    {
      field: 'contactSubject',
      title: '接触主题',
      minWidth: 120,
    },
    {
      field: 'contactSummary',
      title: '接触摘要',
      minWidth: 120,
    },
    {
      field: 'nextContactTime',
      title: '下次接触时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'nextContactPlan',
      title: '下次接触计划',
      minWidth: 120,
    },
    {
      field: 'isNeedFollowup',
      title: '是否需要跟进',
      minWidth: 120,
    },
    {
      field: 'followupUserId',
      title: '跟进人ID',
      minWidth: 120,
    },
    {
      field: 'followupUserName',
      title: '跟进人姓名',
      minWidth: 120,
    },
    {
      field: 'followupStatus',
      title: '跟进状态（字典: aicrm_followup_status，pending=待跟进，following=跟进中，completed=已完成）',
      minWidth: 120,
    },
    {
      field: 'relatedActivityId',
      title: '关联营销活动ID',
      minWidth: 120,
    },
    {
      field: 'relatedActivityName',
      title: '关联营销活动名称',
      minWidth: 120,
    },
    {
      field: 'relatedProduct',
      title: '关联产品',
      minWidth: 120,
    },
    {
      field: 'expectedAmount',
      title: '预期金额（元）',
      minWidth: 120,
    },
    {
      field: 'actualAmount',
      title: '实际成交金额（元）',
      minWidth: 120,
    },
    {
      field: 'isConverted',
      title: '是否已转化',
      minWidth: 120,
    },
    {
      field: 'convertTime',
      title: '转化时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'customerSatisfaction',
      title: '客户满意度（1-5星）',
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
      field: 'handlerDeptId',
      title: '处理部门ID',
      minWidth: 120,
    },
    {
      field: 'handlerDeptName',
      title: '处理部门名称',
      minWidth: 120,
    },
    {
      field: 'attachments',
      title: '附件列表（JSON格式）',
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