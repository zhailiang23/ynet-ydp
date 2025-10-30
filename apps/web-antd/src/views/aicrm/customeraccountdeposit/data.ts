import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAccountDepositApi } from '#/api/aicrm/customeraccountdeposit';

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
      fieldName: 'accountNo',
      label: '账号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账号',
      },
    },
    {
      fieldName: 'productName',
      label: '产品名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品名称',
      },
    },
    {
      fieldName: 'accountName',
      label: '户名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入户名',
      },
    },
    {
      fieldName: 'openDate',
      label: '开户日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'closeDate',
      label: '销户日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_account_status，normal=正常，closed=销户，frozen=冻结）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'interestRate',
      label: '利率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入利率（%）',
      },
    },
    {
      fieldName: 'depositTerm',
      label: '存期（如：1年、6个月、3个月、活期）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入存期（如：1年、6个月、3个月、活期）',
      },
    },
    {
      fieldName: 'balance',
      label: '余额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入余额',
      },
    },
    {
      fieldName: 'agrNo',
      label: '协议号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入协议号',
      },
    },
    {
      fieldName: 'productId',
      label: '产品ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品ID',
      },
    },
    {
      fieldName: 'cardNo',
      label: '卡号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入卡号',
      },
    },
    {
      fieldName: 'depositType',
      label: '存款类型（1=活期，2=定期）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择存款类型（1=活期，2=定期）',
      },
    },
    {
      fieldName: 'currencyType',
      label: '币种（字典: aicrm_currency_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择币种（字典: aicrm_currency_type）',
      },
    },
    {
      fieldName: 'originalAmount',
      label: '原始金额（开户金额）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入原始金额（开户金额）',
      },
    },
    {
      fieldName: 'matureDate',
      label: '到期日',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'startInterestDate',
      label: '起息日',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'deptId',
      label: '开户机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入开户机构ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'deptName',
      label: '开户机构名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入开户机构名称',
      },
    },
    {
      fieldName: 'managerUserId',
      label: '客户经理用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户经理用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'monthAvgBalance',
      label: '月日均余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入月日均余额',
      },
    },
    {
      fieldName: 'quarterAvgBalance',
      label: '季日均余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入季日均余额',
      },
    },
    {
      fieldName: 'yearAvgBalance',
      label: '年日均余额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入年日均余额',
      },
    },
    {
      fieldName: 'monthTotalIn',
      label: '月累计转入',
      component: 'Input',
      componentProps: {
        placeholder: '请输入月累计转入',
      },
    },
    {
      fieldName: 'monthTotalOut',
      label: '月累计转出',
      component: 'Input',
      componentProps: {
        placeholder: '请输入月累计转出',
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
      fieldName: 'accountNo',
      label: '账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账号',
      },
    },
    {
      fieldName: 'productName',
      label: '产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品名称',
      },
    },
    {
      fieldName: 'accountName',
      label: '户名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入户名',
      },
    },
    {
      fieldName: 'openDate',
      label: '开户日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'closeDate',
      label: '销户日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_account_status，normal=正常，closed=销户，frozen=冻结）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择账户状态（字典: aicrm_account_status，normal=正常，closed=销户，frozen=冻结）',
      },
    },
    {
      fieldName: 'interestRate',
      label: '利率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入利率（%）',
      },
    },
    {
      fieldName: 'depositTerm',
      label: '存期（如：1年、6个月、3个月、活期）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入存期（如：1年、6个月、3个月、活期）',
      },
    },
    {
      fieldName: 'balance',
      label: '余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入余额',
      },
    },
    {
      fieldName: 'agrNo',
      label: '协议号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入协议号',
      },
    },
    {
      fieldName: 'productId',
      label: '产品ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品ID',
      },
    },
    {
      fieldName: 'cardNo',
      label: '卡号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入卡号',
      },
    },
    {
      fieldName: 'depositType',
      label: '存款类型（1=活期，2=定期）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择存款类型（1=活期，2=定期）',
      },
    },
    {
      fieldName: 'currencyType',
      label: '币种（字典: aicrm_currency_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择币种（字典: aicrm_currency_type）',
      },
    },
    {
      fieldName: 'originalAmount',
      label: '原始金额（开户金额）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入原始金额（开户金额）',
      },
    },
    {
      fieldName: 'matureDate',
      label: '到期日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'startInterestDate',
      label: '起息日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'deptId',
      label: '开户机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入开户机构ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'deptName',
      label: '开户机构名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入开户机构名称',
      },
    },
    {
      fieldName: 'managerUserId',
      label: '客户经理用户ID（关联 system_users.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户经理用户ID（关联 system_users.id）',
      },
    },
    {
      fieldName: 'monthAvgBalance',
      label: '月日均余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入月日均余额',
      },
    },
    {
      fieldName: 'quarterAvgBalance',
      label: '季日均余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入季日均余额',
      },
    },
    {
      fieldName: 'yearAvgBalance',
      label: '年日均余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入年日均余额',
      },
    },
    {
      fieldName: 'monthTotalIn',
      label: '月累计转入',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入月累计转入',
      },
    },
    {
      fieldName: 'monthTotalOut',
      label: '月累计转出',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入月累计转出',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerAccountDepositApi.CustomerAccountDeposit>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '存款账户主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '账号',
      minWidth: 120,
    },
    {
      field: 'productName',
      title: '产品名称',
      minWidth: 120,
    },
    {
      field: 'accountName',
      title: '户名',
      minWidth: 120,
    },
    {
      field: 'openDate',
      title: '开户日期',
      minWidth: 120,
    },
    {
      field: 'closeDate',
      title: '销户日期',
      minWidth: 120,
    },
    {
      field: 'accountStatus',
      title: '账户状态（字典: aicrm_account_status，normal=正常，closed=销户，frozen=冻结）',
      minWidth: 120,
    },
    {
      field: 'interestRate',
      title: '利率（%）',
      minWidth: 120,
    },
    {
      field: 'depositTerm',
      title: '存期（如：1年、6个月、3个月、活期）',
      minWidth: 120,
    },
    {
      field: 'balance',
      title: '余额',
      minWidth: 120,
    },
    {
      field: 'agrNo',
      title: '协议号',
      minWidth: 120,
    },
    {
      field: 'productId',
      title: '产品ID',
      minWidth: 120,
    },
    {
      field: 'cardNo',
      title: '卡号',
      minWidth: 120,
    },
    {
      field: 'depositType',
      title: '存款类型（1=活期，2=定期）',
      minWidth: 120,
    },
    {
      field: 'currencyType',
      title: '币种（字典: aicrm_currency_type）',
      minWidth: 120,
    },
    {
      field: 'originalAmount',
      title: '原始金额（开户金额）',
      minWidth: 120,
    },
    {
      field: 'matureDate',
      title: '到期日',
      minWidth: 120,
    },
    {
      field: 'startInterestDate',
      title: '起息日',
      minWidth: 120,
    },
    {
      field: 'deptId',
      title: '开户机构ID（关联 system_dept.id）',
      minWidth: 120,
    },
    {
      field: 'deptName',
      title: '开户机构名称',
      minWidth: 120,
    },
    {
      field: 'managerUserId',
      title: '客户经理用户ID（关联 system_users.id）',
      minWidth: 120,
    },
    {
      field: 'monthAvgBalance',
      title: '月日均余额',
      minWidth: 120,
    },
    {
      field: 'quarterAvgBalance',
      title: '季日均余额',
      minWidth: 120,
    },
    {
      field: 'yearAvgBalance',
      title: '年日均余额',
      minWidth: 120,
    },
    {
      field: 'monthTotalIn',
      title: '月累计转入',
      minWidth: 120,
    },
    {
      field: 'monthTotalOut',
      title: '月累计转出',
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