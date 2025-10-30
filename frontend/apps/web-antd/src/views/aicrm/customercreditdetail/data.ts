import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerCreditDetailApi } from '#/api/aicrm/customercreditdetail';

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
      fieldName: 'creditId',
      label: '授信ID（关联 crm_customer_credit.id）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信ID（关联 crm_customer_credit.id）',
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
      fieldName: 'loanNo',
      label: '贷款编号/借据号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款编号/借据号',
      },
    },
    {
      fieldName: 'contractNo',
      label: '合同编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入合同编号',
      },
    },
    {
      fieldName: 'loanDate',
      label: '放款日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'matureDate',
      label: '到期日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'loanAmount',
      label: '贷款金额（元）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款金额（元）',
      },
    },
    {
      fieldName: 'balance',
      label: '贷款余额（元）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款余额（元）',
      },
    },
    {
      fieldName: 'interestRate',
      label: '执行利率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入执行利率（%）',
      },
    },
    {
      fieldName: 'loanStatus',
      label: '贷款状态（字典: aicrm_loan_status，normal=正常，overdue=逾期，settled=已结清）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'settleDate',
      label: '结清日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'productName',
      label: '贷款产品名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款产品名称',
      },
    },
    {
      fieldName: 'productCode',
      label: '产品代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品代码',
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
      fieldName: 'creditId',
      label: '授信ID（关联 crm_customer_credit.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信ID（关联 crm_customer_credit.id）',
      },
    },
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
      fieldName: 'loanNo',
      label: '贷款编号/借据号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款编号/借据号',
      },
    },
    {
      fieldName: 'contractNo',
      label: '合同编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入合同编号',
      },
    },
    {
      fieldName: 'loanDate',
      label: '放款日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'matureDate',
      label: '到期日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'loanAmount',
      label: '贷款金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款金额（元）',
      },
    },
    {
      fieldName: 'balance',
      label: '贷款余额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款余额（元）',
      },
    },
    {
      fieldName: 'interestRate',
      label: '执行利率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入执行利率（%）',
      },
    },
    {
      fieldName: 'loanStatus',
      label: '贷款状态（字典: aicrm_loan_status，normal=正常，overdue=逾期，settled=已结清）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择贷款状态（字典: aicrm_loan_status，normal=正常，overdue=逾期，settled=已结清）',
      },
    },
    {
      fieldName: 'settleDate',
      label: '结清日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'productName',
      label: '贷款产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款产品名称',
      },
    },
    {
      fieldName: 'productCode',
      label: '产品代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品代码',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerCreditDetailApi.CustomerCreditDetail>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '授信明细主键',
      minWidth: 120,
    },
    {
      field: 'creditId',
      title: '授信ID（关联 crm_customer_credit.id）',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'loanNo',
      title: '贷款编号/借据号',
      minWidth: 120,
    },
    {
      field: 'contractNo',
      title: '合同编号',
      minWidth: 120,
    },
    {
      field: 'loanDate',
      title: '放款日期',
      minWidth: 120,
    },
    {
      field: 'matureDate',
      title: '到期日期',
      minWidth: 120,
    },
    {
      field: 'loanAmount',
      title: '贷款金额（元）',
      minWidth: 120,
    },
    {
      field: 'balance',
      title: '贷款余额（元）',
      minWidth: 120,
    },
    {
      field: 'interestRate',
      title: '执行利率（%）',
      minWidth: 120,
    },
    {
      field: 'loanStatus',
      title: '贷款状态（字典: aicrm_loan_status，normal=正常，overdue=逾期，settled=已结清）',
      minWidth: 120,
    },
    {
      field: 'settleDate',
      title: '结清日期',
      minWidth: 120,
    },
    {
      field: 'productName',
      title: '贷款产品名称',
      minWidth: 120,
    },
    {
      field: 'productCode',
      title: '产品代码',
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