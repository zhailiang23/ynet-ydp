import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerCreditApi } from '#/api/aicrm/customercredit';

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
      fieldName: 'statisticsDate',
      label: '统计日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'creditAgreementNo',
      label: '授信协议号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信协议号',
      },
    },
    {
      fieldName: 'creditProductType',
      label: '授信品种（字典: aicrm_credit_product_type）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择授信品种（字典: aicrm_credit_product_type）',
      },
    },
    {
      fieldName: 'currencyCode',
      label: '授信币种（字典: aicrm_currency_type）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信币种（字典: aicrm_currency_type）',
      },
    },
    {
      fieldName: 'creditLimit',
      label: '授信额度（元）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信额度（元）',
      },
    },
    {
      fieldName: 'usedLimit',
      label: '已用额度（元）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入已用额度（元）',
      },
    },
    {
      fieldName: 'availableLimit',
      label: '可用额度（元）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入可用额度（元）',
      },
    },
    {
      fieldName: 'usageRatio',
      label: '使用比例（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入使用比例（%）',
      },
    },
    {
      fieldName: 'creditStartDate',
      label: '授信起始日',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'creditEndDate',
      label: '授信到期日',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'creditType',
      label: '授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）',
      },
    },
    {
      fieldName: 'creditStatus',
      label: '授信状态（字典: aicrm_credit_status，effective=生效中，expired=已到期，cancelled=已撤销，frozen=已冻结）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'approveDate',
      label: '授信审批日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'approveAmount',
      label: '授信审批金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信审批金额（元）',
      },
    },
    {
      fieldName: 'interestRate',
      label: '授信利率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信利率（%）',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保方式（字典: aicrm_guarantee_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择担保方式（字典: aicrm_guarantee_type）',
      },
    },
    {
      fieldName: 'creditPurpose',
      label: '授信用途',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信用途',
      },
    },
    {
      fieldName: 'approverUserId',
      label: '审批人ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入审批人ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'approverDeptId',
      label: '审批部门ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入审批部门ID（关联 system_dept.id）',
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
      label: '授信网点ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信网点ID（关联 system_dept.id）',
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
      fieldName: 'statisticsDate',
      label: '统计日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'creditAgreementNo',
      label: '授信协议号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信协议号',
      },
    },
    {
      fieldName: 'creditProductType',
      label: '授信品种（字典: aicrm_credit_product_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择授信品种（字典: aicrm_credit_product_type）',
      },
    },
    {
      fieldName: 'currencyCode',
      label: '授信币种（字典: aicrm_currency_type）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信币种（字典: aicrm_currency_type）',
      },
    },
    {
      fieldName: 'creditLimit',
      label: '授信额度（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信额度（元）',
      },
    },
    {
      fieldName: 'usedLimit',
      label: '已用额度（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入已用额度（元）',
      },
    },
    {
      fieldName: 'availableLimit',
      label: '可用额度（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入可用额度（元）',
      },
    },
    {
      fieldName: 'usageRatio',
      label: '使用比例（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入使用比例（%）',
      },
    },
    {
      fieldName: 'creditStartDate',
      label: '授信起始日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'creditEndDate',
      label: '授信到期日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'creditType',
      label: '授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）',
      },
    },
    {
      fieldName: 'creditStatus',
      label: '授信状态（字典: aicrm_credit_status，effective=生效中，expired=已到期，cancelled=已撤销，frozen=已冻结）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择授信状态（字典: aicrm_credit_status，effective=生效中，expired=已到期，cancelled=已撤销，frozen=已冻结）',
      },
    },
    {
      fieldName: 'approveDate',
      label: '授信审批日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'approveAmount',
      label: '授信审批金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信审批金额（元）',
      },
    },
    {
      fieldName: 'interestRate',
      label: '授信利率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信利率（%）',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保方式（字典: aicrm_guarantee_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择担保方式（字典: aicrm_guarantee_type）',
      },
    },
    {
      fieldName: 'creditPurpose',
      label: '授信用途',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信用途',
      },
    },
    {
      fieldName: 'approverUserId',
      label: '审批人ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入审批人ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'approverDeptId',
      label: '审批部门ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入审批部门ID（关联 system_dept.id）',
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
      label: '授信网点ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信网点ID（关联 system_dept.id）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerCreditApi.CustomerCredit>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '授信主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'statisticsDate',
      title: '统计日期',
      minWidth: 120,
    },
    {
      field: 'creditAgreementNo',
      title: '授信协议号',
      minWidth: 120,
    },
    {
      field: 'creditProductType',
      title: '授信品种（字典: aicrm_credit_product_type）',
      minWidth: 120,
    },
    {
      field: 'currencyCode',
      title: '授信币种（字典: aicrm_currency_type）',
      minWidth: 120,
    },
    {
      field: 'creditLimit',
      title: '授信额度（元）',
      minWidth: 120,
    },
    {
      field: 'usedLimit',
      title: '已用额度（元）',
      minWidth: 120,
    },
    {
      field: 'availableLimit',
      title: '可用额度（元）',
      minWidth: 120,
    },
    {
      field: 'usageRatio',
      title: '使用比例（%）',
      minWidth: 120,
    },
    {
      field: 'creditStartDate',
      title: '授信起始日',
      minWidth: 120,
    },
    {
      field: 'creditEndDate',
      title: '授信到期日',
      minWidth: 120,
    },
    {
      field: 'creditType',
      title: '授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）',
      minWidth: 120,
    },
    {
      field: 'creditStatus',
      title: '授信状态（字典: aicrm_credit_status，effective=生效中，expired=已到期，cancelled=已撤销，frozen=已冻结）',
      minWidth: 120,
    },
    {
      field: 'approveDate',
      title: '授信审批日期',
      minWidth: 120,
    },
    {
      field: 'approveAmount',
      title: '授信审批金额（元）',
      minWidth: 120,
    },
    {
      field: 'interestRate',
      title: '授信利率（%）',
      minWidth: 120,
    },
    {
      field: 'guaranteeType',
      title: '担保方式（字典: aicrm_guarantee_type）',
      minWidth: 120,
    },
    {
      field: 'creditPurpose',
      title: '授信用途',
      minWidth: 120,
    },
    {
      field: 'approverUserId',
      title: '审批人ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'approverDeptId',
      title: '审批部门ID（关联 system_dept.id）',
      minWidth: 120,
    },
    {
      field: 'managerUserId',
      title: '客户经理ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'branchId',
      title: '授信网点ID（关联 system_dept.id）',
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