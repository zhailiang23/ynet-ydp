import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerTransactionMockApi } from '#/api/aicrm/customertransactionmock';

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
      fieldName: 'accountId',
      label: '账户ID（关联账户表）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账户ID（关联账户表）',
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
      fieldName: 'branchNo',
      label: '交易机构编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易机构编号',
      },
    },
    {
      fieldName: 'branchName',
      label: '交易机构名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易机构名称',
      },
    },
    {
      fieldName: 'originalTranCode',
      label: '原交易代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入原交易代码',
      },
    },
    {
      fieldName: 'cashFlag',
      label: '现转标志（字典: aicrm_cash_flag）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入现转标志（字典: aicrm_cash_flag）',
      },
    },
    {
      fieldName: 'subAccountNo',
      label: '子账户编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入子账户编号',
      },
    },
    {
      fieldName: 'accountNo',
      label: '账户编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账户编号',
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
      fieldName: 'tansNo',
      label: '交易流水号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易流水号',
      },
    },
    {
      fieldName: 'tradType',
      label: '交易类型（字典: aicrm_transaction_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择交易类型（字典: aicrm_transaction_type）',
      },
    },
    {
      fieldName: 'tradMoney',
      label: '交易金额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易金额',
      },
    },
    {
      fieldName: 'acctBal',
      label: '账户余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账户余额',
      },
    },
    {
      fieldName: 'tradAbs',
      label: '交易摘要',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易摘要',
      },
    },
    {
      fieldName: 'review',
      label: '审核标志',
      component: 'Input',
      componentProps: {
        placeholder: '请输入审核标志',
      },
    },
    {
      fieldName: 'tradChn',
      label: '交易渠道（字典: aicrm_transaction_channel）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易渠道（字典: aicrm_transaction_channel）',
      },
    },
    {
      fieldName: 'tradTeller',
      label: '交易柜员',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易柜员',
      },
    },
    {
      fieldName: 'handler',
      label: '经办人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入经办人',
      },
    },
    {
      fieldName: 'advsAcct',
      label: '对方账号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入对方账号',
      },
    },
    {
      fieldName: 'advsAcctName',
      label: '对方户名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入对方户名',
      },
    },
    {
      fieldName: 'contactType',
      label: '往来类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择往来类型',
      },
    },
    {
      fieldName: 'currTranFlag',
      label: '钞汇标志',
      component: 'Input',
      componentProps: {
        placeholder: '请输入钞汇标志',
      },
    },
    {
      fieldName: 'loanFlag',
      label: '贷款标志',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款标志',
      },
    },
    {
      fieldName: 'cost',
      label: '手续费',
      component: 'Input',
      componentProps: {
        placeholder: '请输入手续费',
      },
    },
    {
      fieldName: 'accountinDate',
      label: '记账日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'transactionStatus',
      label: '交易状态（字典: aicrm_transaction_status，success=成功，failed=失败，pending=处理中）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'direction',
      label: '交易方向（字典: aicrm_transaction_direction，in=转入，out=转出）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入交易方向（字典: aicrm_transaction_direction，in=转入，out=转出）',
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
      fieldName: 'accountId',
      label: '账户ID（关联账户表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账户ID（关联账户表）',
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
      fieldName: 'transactionTime',
      label: '交易时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'branchNo',
      label: '交易机构编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易机构编号',
      },
    },
    {
      fieldName: 'branchName',
      label: '交易机构名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易机构名称',
      },
    },
    {
      fieldName: 'originalTranCode',
      label: '原交易代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入原交易代码',
      },
    },
    {
      fieldName: 'cashFlag',
      label: '现转标志（字典: aicrm_cash_flag）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入现转标志（字典: aicrm_cash_flag）',
      },
    },
    {
      fieldName: 'subAccountNo',
      label: '子账户编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入子账户编号',
      },
    },
    {
      fieldName: 'accountNo',
      label: '账户编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账户编号',
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
      fieldName: 'tansNo',
      label: '交易流水号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易流水号',
      },
    },
    {
      fieldName: 'tradType',
      label: '交易类型（字典: aicrm_transaction_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择交易类型（字典: aicrm_transaction_type）',
      },
    },
    {
      fieldName: 'tradMoney',
      label: '交易金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易金额',
      },
    },
    {
      fieldName: 'acctBal',
      label: '账户余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账户余额',
      },
    },
    {
      fieldName: 'tradAbs',
      label: '交易摘要',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易摘要',
      },
    },
    {
      fieldName: 'review',
      label: '审核标志',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入审核标志',
      },
    },
    {
      fieldName: 'tradChn',
      label: '交易渠道（字典: aicrm_transaction_channel）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易渠道（字典: aicrm_transaction_channel）',
      },
    },
    {
      fieldName: 'tradTeller',
      label: '交易柜员',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易柜员',
      },
    },
    {
      fieldName: 'handler',
      label: '经办人',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入经办人',
      },
    },
    {
      fieldName: 'advsAcct',
      label: '对方账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入对方账号',
      },
    },
    {
      fieldName: 'advsAcctName',
      label: '对方户名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入对方户名',
      },
    },
    {
      fieldName: 'contactType',
      label: '往来类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择往来类型',
      },
    },
    {
      fieldName: 'currTranFlag',
      label: '钞汇标志',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入钞汇标志',
      },
    },
    {
      fieldName: 'loanFlag',
      label: '贷款标志',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款标志',
      },
    },
    {
      fieldName: 'cost',
      label: '手续费',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入手续费',
      },
    },
    {
      fieldName: 'accountinDate',
      label: '记账日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'transactionStatus',
      label: '交易状态（字典: aicrm_transaction_status，success=成功，failed=失败，pending=处理中）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择交易状态（字典: aicrm_transaction_status，success=成功，failed=失败，pending=处理中）',
      },
    },
    {
      fieldName: 'direction',
      label: '交易方向（字典: aicrm_transaction_direction，in=转入，out=转出）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入交易方向（字典: aicrm_transaction_direction，in=转入，out=转出）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerTransactionMockApi.CustomerTransactionMock>['columns'] {
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
      field: 'accountId',
      title: '账户ID（关联账户表）',
      minWidth: 120,
    },
    {
      field: 'transactionDate',
      title: '交易日期',
      minWidth: 120,
    },
    {
      field: 'transactionTime',
      title: '交易时间',
      minWidth: 120,
    },
    {
      field: 'branchNo',
      title: '交易机构编号',
      minWidth: 120,
    },
    {
      field: 'branchName',
      title: '交易机构名称',
      minWidth: 120,
    },
    {
      field: 'originalTranCode',
      title: '原交易代码',
      minWidth: 120,
    },
    {
      field: 'cashFlag',
      title: '现转标志（字典: aicrm_cash_flag）',
      minWidth: 120,
    },
    {
      field: 'subAccountNo',
      title: '子账户编号',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '账户编号',
      minWidth: 120,
    },
    {
      field: 'currencyCode',
      title: '币种（字典: aicrm_currency_type）',
      minWidth: 120,
    },
    {
      field: 'tansNo',
      title: '交易流水号',
      minWidth: 120,
    },
    {
      field: 'tradType',
      title: '交易类型（字典: aicrm_transaction_type）',
      minWidth: 120,
    },
    {
      field: 'tradMoney',
      title: '交易金额',
      minWidth: 120,
    },
    {
      field: 'acctBal',
      title: '账户余额',
      minWidth: 120,
    },
    {
      field: 'tradAbs',
      title: '交易摘要',
      minWidth: 120,
    },
    {
      field: 'review',
      title: '审核标志',
      minWidth: 120,
    },
    {
      field: 'tradChn',
      title: '交易渠道（字典: aicrm_transaction_channel）',
      minWidth: 120,
    },
    {
      field: 'tradTeller',
      title: '交易柜员',
      minWidth: 120,
    },
    {
      field: 'handler',
      title: '经办人',
      minWidth: 120,
    },
    {
      field: 'advsAcct',
      title: '对方账号',
      minWidth: 120,
    },
    {
      field: 'advsAcctName',
      title: '对方户名',
      minWidth: 120,
    },
    {
      field: 'contactType',
      title: '往来类型',
      minWidth: 120,
    },
    {
      field: 'currTranFlag',
      title: '钞汇标志',
      minWidth: 120,
    },
    {
      field: 'loanFlag',
      title: '贷款标志',
      minWidth: 120,
    },
    {
      field: 'cost',
      title: '手续费',
      minWidth: 120,
    },
    {
      field: 'accountinDate',
      title: '记账日期',
      minWidth: 120,
    },
    {
      field: 'transactionStatus',
      title: '交易状态（字典: aicrm_transaction_status，success=成功，failed=失败，pending=处理中）',
      minWidth: 120,
    },
    {
      field: 'direction',
      title: '交易方向（字典: aicrm_transaction_direction，in=转入，out=转出）',
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