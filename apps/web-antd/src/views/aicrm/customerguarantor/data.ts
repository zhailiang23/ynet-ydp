import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerGuarantorApi } from '#/api/aicrm/customerguarantor';

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
      label: '客户ID（被担保人，关联 crm_customer 主表）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（被担保人，关联 crm_customer 主表）',
      },
    },
    {
      fieldName: 'creditId',
      label: '授信ID（关联 crm_customer_credit.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信ID（关联 crm_customer_credit.id）',
      },
    },
    {
      fieldName: 'contractNo',
      label: '合同号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入合同号',
      },
    },
    {
      fieldName: 'contractType',
      label: '合同类型（字典: aicrm_guarantor_contract_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择合同类型（字典: aicrm_guarantor_contract_type）',
      },
    },
    {
      fieldName: 'contractStatus',
      label: '合同状态（字典: aicrm_guarantor_contract_status，effective=有效，expired=已到期，cancelled=已解除）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'signDate',
      label: '签约日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保类型（字典: aicrm_guarantee_method，guarantee=保证）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择担保类型（字典: aicrm_guarantee_method，guarantee=保证）',
      },
    },
    {
      fieldName: 'guarantorNo',
      label: '担保人编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入担保人编号',
      },
    },
    {
      fieldName: 'guarantorName',
      label: '担保人姓名/名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入担保人姓名/名称',
      },
    },
    {
      fieldName: 'currencyCode',
      label: '币种（字典: aicrm_currency_type）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入币种（字典: aicrm_currency_type）',
      },
    },
    {
      fieldName: 'guaranteeTotalAmount',
      label: '担保总金额（万元）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入担保总金额（万元）',
      },
    },
    {
      fieldName: 'businessStartDate',
      label: '业务起始日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'businessEndDate',
      label: '业务截止日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'guarantorType',
      label: '担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      },
    },
    {
      fieldName: 'guarantorIdType',
      label: '担保人证件类型（字典: aicrm_identity_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择担保人证件类型（字典: aicrm_identity_type）',
      },
    },
    {
      fieldName: 'guarantorIdNo',
      label: '担保人证件号码（加密）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入担保人证件号码（加密）',
      },
    },
    {
      fieldName: 'relationWithBorrower',
      label: '与被担保人关系（字典: aicrm_relation_type）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入与被担保人关系（字典: aicrm_relation_type）',
      },
    },
    {
      fieldName: 'guaranteeMethod',
      label: '担保方式（字典: aicrm_guarantee_style，joint=连带责任，general=一般保证）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入担保方式（字典: aicrm_guarantee_style，joint=连带责任，general=一般保证）',
      },
    },
    {
      fieldName: 'usedAmount',
      label: '已用担保金额（万元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入已用担保金额（万元）',
      },
    },
    {
      fieldName: 'availableAmount',
      label: '可用担保金额（万元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入可用担保金额（万元）',
      },
    },
    {
      fieldName: 'managerUserId',
      label: '客户经理ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户经理ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'branchId',
      label: '管理机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入管理机构ID（关联 system_dept.id）',
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
      label: '客户ID（被担保人，关联 crm_customer 主表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（被担保人，关联 crm_customer 主表）',
      },
    },
    {
      fieldName: 'creditId',
      label: '授信ID（关联 crm_customer_credit.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信ID（关联 crm_customer_credit.id）',
      },
    },
    {
      fieldName: 'contractNo',
      label: '合同号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入合同号',
      },
    },
    {
      fieldName: 'contractType',
      label: '合同类型（字典: aicrm_guarantor_contract_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择合同类型（字典: aicrm_guarantor_contract_type）',
      },
    },
    {
      fieldName: 'contractStatus',
      label: '合同状态（字典: aicrm_guarantor_contract_status，effective=有效，expired=已到期，cancelled=已解除）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择合同状态（字典: aicrm_guarantor_contract_status，effective=有效，expired=已到期，cancelled=已解除）',
      },
    },
    {
      fieldName: 'signDate',
      label: '签约日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保类型（字典: aicrm_guarantee_method，guarantee=保证）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择担保类型（字典: aicrm_guarantee_method，guarantee=保证）',
      },
    },
    {
      fieldName: 'guarantorNo',
      label: '担保人编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入担保人编号',
      },
    },
    {
      fieldName: 'guarantorName',
      label: '担保人姓名/名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入担保人姓名/名称',
      },
    },
    {
      fieldName: 'currencyCode',
      label: '币种（字典: aicrm_currency_type）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入币种（字典: aicrm_currency_type）',
      },
    },
    {
      fieldName: 'guaranteeTotalAmount',
      label: '担保总金额（万元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入担保总金额（万元）',
      },
    },
    {
      fieldName: 'businessStartDate',
      label: '业务起始日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'businessEndDate',
      label: '业务截止日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'guarantorType',
      label: '担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      },
    },
    {
      fieldName: 'guarantorIdType',
      label: '担保人证件类型（字典: aicrm_identity_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择担保人证件类型（字典: aicrm_identity_type）',
      },
    },
    {
      fieldName: 'guarantorIdNo',
      label: '担保人证件号码（加密）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入担保人证件号码（加密）',
      },
    },
    {
      fieldName: 'relationWithBorrower',
      label: '与被担保人关系（字典: aicrm_relation_type）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入与被担保人关系（字典: aicrm_relation_type）',
      },
    },
    {
      fieldName: 'guaranteeMethod',
      label: '担保方式（字典: aicrm_guarantee_style，joint=连带责任，general=一般保证）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入担保方式（字典: aicrm_guarantee_style，joint=连带责任，general=一般保证）',
      },
    },
    {
      fieldName: 'usedAmount',
      label: '已用担保金额（万元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入已用担保金额（万元）',
      },
    },
    {
      fieldName: 'availableAmount',
      label: '可用担保金额（万元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入可用担保金额（万元）',
      },
    },
    {
      fieldName: 'managerUserId',
      label: '客户经理ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户经理ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'branchId',
      label: '管理机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入管理机构ID（关联 system_dept.id）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerGuarantorApi.CustomerGuarantor>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '担保人主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（被担保人，关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'creditId',
      title: '授信ID（关联 crm_customer_credit.id）',
      minWidth: 120,
    },
    {
      field: 'contractNo',
      title: '合同号',
      minWidth: 120,
    },
    {
      field: 'contractType',
      title: '合同类型（字典: aicrm_guarantor_contract_type）',
      minWidth: 120,
    },
    {
      field: 'contractStatus',
      title: '合同状态（字典: aicrm_guarantor_contract_status，effective=有效，expired=已到期，cancelled=已解除）',
      minWidth: 120,
    },
    {
      field: 'signDate',
      title: '签约日期',
      minWidth: 120,
    },
    {
      field: 'guaranteeType',
      title: '担保类型（字典: aicrm_guarantee_method，guarantee=保证）',
      minWidth: 120,
    },
    {
      field: 'guarantorNo',
      title: '担保人编号',
      minWidth: 120,
    },
    {
      field: 'guarantorName',
      title: '担保人姓名/名称',
      minWidth: 120,
    },
    {
      field: 'currencyCode',
      title: '币种（字典: aicrm_currency_type）',
      minWidth: 120,
    },
    {
      field: 'guaranteeTotalAmount',
      title: '担保总金额（万元）',
      minWidth: 120,
    },
    {
      field: 'businessStartDate',
      title: '业务起始日期',
      minWidth: 120,
    },
    {
      field: 'businessEndDate',
      title: '业务截止日期',
      minWidth: 120,
    },
    {
      field: 'guarantorType',
      title: '担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      minWidth: 120,
    },
    {
      field: 'guarantorIdType',
      title: '担保人证件类型（字典: aicrm_identity_type）',
      minWidth: 120,
    },
    {
      field: 'guarantorIdNo',
      title: '担保人证件号码（加密）',
      minWidth: 120,
    },
    {
      field: 'relationWithBorrower',
      title: '与被担保人关系（字典: aicrm_relation_type）',
      minWidth: 120,
    },
    {
      field: 'guaranteeMethod',
      title: '担保方式（字典: aicrm_guarantee_style，joint=连带责任，general=一般保证）',
      minWidth: 120,
    },
    {
      field: 'usedAmount',
      title: '已用担保金额（万元）',
      minWidth: 120,
    },
    {
      field: 'availableAmount',
      title: '可用担保金额（万元）',
      minWidth: 120,
    },
    {
      field: 'managerUserId',
      title: '客户经理ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'branchId',
      title: '管理机构ID（关联 system_dept.id）',
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