import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerMarketingActivityApi } from '#/api/aicrm/customermarketingactivity';

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
      fieldName: 'activityName',
      label: '活动名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动名称',
      },
    },
    {
      fieldName: 'activityNo',
      label: '活动编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动编号',
      },
    },
    {
      fieldName: 'activityForm',
      label: '活动形式（字典: aicrm_activity_form，parent_child=亲子沙龙，salon=线下沙龙，visit=客户拜访，phone=电话营销等）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动形式（字典: aicrm_activity_form，parent_child=亲子沙龙，salon=线下沙龙，visit=客户拜访，phone=电话营销等）',
      },
    },
    {
      fieldName: 'activityStatus',
      label: '活动状态（字典: aicrm_activity_status，draft=草稿，pending=待执行，executing=执行中，completed=已完成，closed=已关闭）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'approvalStatus',
      label: '审批状态（字典: aicrm_approval_status，pending_submit=待提交，approving=审批中，approved=已通过，rejected=已驳回）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'activityOrderTime',
      label: '活动订购时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'activityEndTime',
      label: '活动结束时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isSendSms',
      label: '是否发送客户短信',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'opporId',
      label: '机会ID（老系统）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机会ID（老系统）',
      },
    },
    {
      fieldName: 'opporNm',
      label: '机会名称（老系统）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机会名称（老系统）',
      },
    },
    {
      fieldName: 'startDt',
      label: '开始日期',
      component: 'Input',
      componentProps: {
        placeholder: '请输入开始日期',
      },
    },
    {
      fieldName: 'endDt',
      label: '结束日期',
      component: 'Input',
      componentProps: {
        placeholder: '请输入结束日期',
      },
    },
    {
      fieldName: 'opporStcd',
      label: '机会状态代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机会状态代码',
      },
    },
    {
      fieldName: 'autoAcltRlcd',
      label: '自动分配规则代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入自动分配规则代码',
      },
    },
    {
      fieldName: 'opporBsnTpcd',
      label: '机会业务类型代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机会业务类型代码',
      },
    },
    {
      fieldName: 'opporAlctObjTpcd',
      label: '机会分配对象类型代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机会分配对象类型代码',
      },
    },
    {
      fieldName: 'opporCustTpcd',
      label: '机会客户类型代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机会客户类型代码',
      },
    },
    {
      fieldName: 'opporSrccd',
      label: '机会来源代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机会来源代码',
      },
    },
    {
      fieldName: 'opporCrtMthcd',
      label: '机会创建方式代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机会创建方式代码',
      },
    },
    {
      fieldName: 'crtOrgId',
      label: '创建机构ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入创建机构ID',
      },
    },
    {
      fieldName: 'opporDsc',
      label: '机会描述',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机会描述',
      },
    },
    {
      fieldName: 'activityType',
      label: '活动类型（字典: aicrm_activity_type，product_promotion=产品推广，customer_maintain=客户维护，market_research=市场调研，brand_publicity=品牌宣传等）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择活动类型（字典: aicrm_activity_type，product_promotion=产品推广，customer_maintain=客户维护，market_research=市场调研，brand_publicity=品牌宣传等）',
      },
    },
    {
      fieldName: 'activityLocation',
      label: '活动地点',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动地点',
      },
    },
    {
      fieldName: 'activityBudget',
      label: '活动预算（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动预算（元）',
      },
    },
    {
      fieldName: 'activityCost',
      label: '活动实际费用（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动实际费用（元）',
      },
    },
    {
      fieldName: 'targetCustomerCount',
      label: '目标客户数量',
      component: 'Input',
      componentProps: {
        placeholder: '请输入目标客户数量',
      },
    },
    {
      fieldName: 'actualCustomerCount',
      label: '实际参与客户数量',
      component: 'Input',
      componentProps: {
        placeholder: '请输入实际参与客户数量',
      },
    },
    {
      fieldName: 'expectedEffect',
      label: '预期效果',
      component: 'Input',
      componentProps: {
        placeholder: '请输入预期效果',
      },
    },
    {
      fieldName: 'actualEffect',
      label: '实际效果',
      component: 'Input',
      componentProps: {
        placeholder: '请输入实际效果',
      },
    },
    {
      fieldName: 'handlerUserId',
      label: '负责人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责人ID',
      },
    },
    {
      fieldName: 'handlerUserName',
      label: '负责人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责人姓名',
      },
    },
    {
      fieldName: 'handlerDeptId',
      label: '负责部门ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责部门ID',
      },
    },
    {
      fieldName: 'handlerDeptName',
      label: '负责部门名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责部门名称',
      },
    },
    {
      fieldName: 'approverUserId',
      label: '审批人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入审批人ID',
      },
    },
    {
      fieldName: 'approverUserName',
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
      fieldName: 'approvalComment',
      label: '审批意见',
      component: 'Input',
      componentProps: {
        placeholder: '请输入审批意见',
      },
    },
    {
      fieldName: 'smsSendTime',
      label: '短信发送时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'smsContent',
      label: '短信内容',
      component: 'RichTextarea',
    },
    {
      fieldName: 'activityScore',
      label: '活动评分（1-5星）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动评分（1-5星）',
      },
    },
    {
      fieldName: 'customerFeedback',
      label: '客户反馈',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户反馈',
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
      fieldName: 'activityName',
      label: '活动名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动名称',
      },
    },
    {
      fieldName: 'activityNo',
      label: '活动编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动编号',
      },
    },
    {
      fieldName: 'activityForm',
      label: '活动形式（字典: aicrm_activity_form，parent_child=亲子沙龙，salon=线下沙龙，visit=客户拜访，phone=电话营销等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动形式（字典: aicrm_activity_form，parent_child=亲子沙龙，salon=线下沙龙，visit=客户拜访，phone=电话营销等）',
      },
    },
    {
      fieldName: 'activityStatus',
      label: '活动状态（字典: aicrm_activity_status，draft=草稿，pending=待执行，executing=执行中，completed=已完成，closed=已关闭）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择活动状态（字典: aicrm_activity_status，draft=草稿，pending=待执行，executing=执行中，completed=已完成，closed=已关闭）',
      },
    },
    {
      fieldName: 'approvalStatus',
      label: '审批状态（字典: aicrm_approval_status，pending_submit=待提交，approving=审批中，approved=已通过，rejected=已驳回）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择审批状态（字典: aicrm_approval_status，pending_submit=待提交，approving=审批中，approved=已通过，rejected=已驳回）',
      },
    },
    {
      fieldName: 'activityOrderTime',
      label: '活动订购时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'activityEndTime',
      label: '活动结束时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'isSendSms',
      label: '是否发送客户短信',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否发送客户短信',
      },
    },
    {
      fieldName: 'opporId',
      label: '机会ID（老系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机会ID（老系统）',
      },
    },
    {
      fieldName: 'opporNm',
      label: '机会名称（老系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机会名称（老系统）',
      },
    },
    {
      fieldName: 'startDt',
      label: '开始日期',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入开始日期',
      },
    },
    {
      fieldName: 'endDt',
      label: '结束日期',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入结束日期',
      },
    },
    {
      fieldName: 'opporStcd',
      label: '机会状态代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机会状态代码',
      },
    },
    {
      fieldName: 'autoAcltRlcd',
      label: '自动分配规则代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入自动分配规则代码',
      },
    },
    {
      fieldName: 'opporBsnTpcd',
      label: '机会业务类型代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机会业务类型代码',
      },
    },
    {
      fieldName: 'opporAlctObjTpcd',
      label: '机会分配对象类型代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机会分配对象类型代码',
      },
    },
    {
      fieldName: 'opporCustTpcd',
      label: '机会客户类型代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机会客户类型代码',
      },
    },
    {
      fieldName: 'opporSrccd',
      label: '机会来源代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机会来源代码',
      },
    },
    {
      fieldName: 'opporCrtMthcd',
      label: '机会创建方式代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机会创建方式代码',
      },
    },
    {
      fieldName: 'crtOrgId',
      label: '创建机构ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入创建机构ID',
      },
    },
    {
      fieldName: 'opporDsc',
      label: '机会描述',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机会描述',
      },
    },
    {
      fieldName: 'activityType',
      label: '活动类型（字典: aicrm_activity_type，product_promotion=产品推广，customer_maintain=客户维护，market_research=市场调研，brand_publicity=品牌宣传等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择活动类型（字典: aicrm_activity_type，product_promotion=产品推广，customer_maintain=客户维护，market_research=市场调研，brand_publicity=品牌宣传等）',
      },
    },
    {
      fieldName: 'activityLocation',
      label: '活动地点',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动地点',
      },
    },
    {
      fieldName: 'activityBudget',
      label: '活动预算（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动预算（元）',
      },
    },
    {
      fieldName: 'activityCost',
      label: '活动实际费用（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动实际费用（元）',
      },
    },
    {
      fieldName: 'targetCustomerCount',
      label: '目标客户数量',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入目标客户数量',
      },
    },
    {
      fieldName: 'actualCustomerCount',
      label: '实际参与客户数量',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入实际参与客户数量',
      },
    },
    {
      fieldName: 'expectedEffect',
      label: '预期效果',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入预期效果',
      },
    },
    {
      fieldName: 'actualEffect',
      label: '实际效果',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入实际效果',
      },
    },
    {
      fieldName: 'handlerUserId',
      label: '负责人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责人ID',
      },
    },
    {
      fieldName: 'handlerUserName',
      label: '负责人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责人姓名',
      },
    },
    {
      fieldName: 'handlerDeptId',
      label: '负责部门ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责部门ID',
      },
    },
    {
      fieldName: 'handlerDeptName',
      label: '负责部门名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责部门名称',
      },
    },
    {
      fieldName: 'approverUserId',
      label: '审批人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入审批人ID',
      },
    },
    {
      fieldName: 'approverUserName',
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
      fieldName: 'approvalComment',
      label: '审批意见',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入审批意见',
      },
    },
    {
      fieldName: 'smsSendTime',
      label: '短信发送时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'smsContent',
      label: '短信内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入短信内容',
      },
    },
    {
      fieldName: 'activityScore',
      label: '活动评分（1-5星）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动评分（1-5星）',
      },
    },
    {
      fieldName: 'customerFeedback',
      label: '客户反馈',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户反馈',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerMarketingActivityApi.CustomerMarketingActivity>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '营销活动主键',
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
      field: 'activityName',
      title: '活动名称',
      minWidth: 120,
    },
    {
      field: 'activityNo',
      title: '活动编号',
      minWidth: 120,
    },
    {
      field: 'activityForm',
      title: '活动形式（字典: aicrm_activity_form，parent_child=亲子沙龙，salon=线下沙龙，visit=客户拜访，phone=电话营销等）',
      minWidth: 120,
    },
    {
      field: 'activityStatus',
      title: '活动状态（字典: aicrm_activity_status，draft=草稿，pending=待执行，executing=执行中，completed=已完成，closed=已关闭）',
      minWidth: 120,
    },
    {
      field: 'approvalStatus',
      title: '审批状态（字典: aicrm_approval_status，pending_submit=待提交，approving=审批中，approved=已通过，rejected=已驳回）',
      minWidth: 120,
    },
    {
      field: 'activityOrderTime',
      title: '活动订购时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'activityEndTime',
      title: '活动结束时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'isSendSms',
      title: '是否发送客户短信',
      minWidth: 120,
    },
    {
      field: 'opporId',
      title: '机会ID（老系统）',
      minWidth: 120,
    },
    {
      field: 'opporNm',
      title: '机会名称（老系统）',
      minWidth: 120,
    },
    {
      field: 'startDt',
      title: '开始日期',
      minWidth: 120,
    },
    {
      field: 'endDt',
      title: '结束日期',
      minWidth: 120,
    },
    {
      field: 'opporStcd',
      title: '机会状态代码',
      minWidth: 120,
    },
    {
      field: 'autoAcltRlcd',
      title: '自动分配规则代码',
      minWidth: 120,
    },
    {
      field: 'opporBsnTpcd',
      title: '机会业务类型代码',
      minWidth: 120,
    },
    {
      field: 'opporAlctObjTpcd',
      title: '机会分配对象类型代码',
      minWidth: 120,
    },
    {
      field: 'opporCustTpcd',
      title: '机会客户类型代码',
      minWidth: 120,
    },
    {
      field: 'opporSrccd',
      title: '机会来源代码',
      minWidth: 120,
    },
    {
      field: 'opporCrtMthcd',
      title: '机会创建方式代码',
      minWidth: 120,
    },
    {
      field: 'crtOrgId',
      title: '创建机构ID',
      minWidth: 120,
    },
    {
      field: 'opporDsc',
      title: '机会描述',
      minWidth: 120,
    },
    {
      field: 'activityType',
      title: '活动类型（字典: aicrm_activity_type，product_promotion=产品推广，customer_maintain=客户维护，market_research=市场调研，brand_publicity=品牌宣传等）',
      minWidth: 120,
    },
    {
      field: 'activityLocation',
      title: '活动地点',
      minWidth: 120,
    },
    {
      field: 'activityBudget',
      title: '活动预算（元）',
      minWidth: 120,
    },
    {
      field: 'activityCost',
      title: '活动实际费用（元）',
      minWidth: 120,
    },
    {
      field: 'targetCustomerCount',
      title: '目标客户数量',
      minWidth: 120,
    },
    {
      field: 'actualCustomerCount',
      title: '实际参与客户数量',
      minWidth: 120,
    },
    {
      field: 'expectedEffect',
      title: '预期效果',
      minWidth: 120,
    },
    {
      field: 'actualEffect',
      title: '实际效果',
      minWidth: 120,
    },
    {
      field: 'handlerUserId',
      title: '负责人ID',
      minWidth: 120,
    },
    {
      field: 'handlerUserName',
      title: '负责人姓名',
      minWidth: 120,
    },
    {
      field: 'handlerDeptId',
      title: '负责部门ID',
      minWidth: 120,
    },
    {
      field: 'handlerDeptName',
      title: '负责部门名称',
      minWidth: 120,
    },
    {
      field: 'approverUserId',
      title: '审批人ID',
      minWidth: 120,
    },
    {
      field: 'approverUserName',
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
      field: 'approvalComment',
      title: '审批意见',
      minWidth: 120,
    },
    {
      field: 'smsSendTime',
      title: '短信发送时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'smsContent',
      title: '短信内容',
      minWidth: 120,
    },
    {
      field: 'activityScore',
      title: '活动评分（1-5星）',
      minWidth: 120,
    },
    {
      field: 'customerFeedback',
      title: '客户反馈',
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