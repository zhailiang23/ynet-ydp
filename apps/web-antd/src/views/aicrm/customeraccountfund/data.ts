import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAccountFundApi } from '#/api/aicrm/customeraccountfund';

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
      label: '基金账号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入基金账号',
      },
    },
    {
      fieldName: 'fundCode',
      label: '基金代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入基金代码',
      },
    },
    {
      fieldName: 'productName',
      label: '基金名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入基金名称',
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
      label: '开户日期（首次申购日期）',
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
      label: '销户日期（全部赎回日期）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'fundType',
      label: '基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）',
      },
    },
    {
      fieldName: 'fundCompany',
      label: '基金公司',
      component: 'Input',
      componentProps: {
        placeholder: '请输入基金公司',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级（字典: aicrm_risk_level）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入风险等级（字典: aicrm_risk_level）',
      },
    },
    {
      fieldName: 'currencyType',
      label: '币种',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择币种',
      },
    },
    {
      fieldName: 'holdingShares',
      label: '持有份额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入持有份额',
      },
    },
    {
      fieldName: 'purchaseAmount',
      label: '累计申购金额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计申购金额',
      },
    },
    {
      fieldName: 'currentNav',
      label: '当前净值',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前净值',
      },
    },
    {
      fieldName: 'currentValue',
      label: '当前市值',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前市值',
      },
    },
    {
      fieldName: 'accumulatedIncome',
      label: '累计收益',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计收益',
      },
    },
    {
      fieldName: 'balance',
      label: '账户余额（现金部分）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账户余额（现金部分）',
      },
    },
    {
      fieldName: 'costPrice',
      label: '成本价',
      component: 'Input',
      componentProps: {
        placeholder: '请输入成本价',
      },
    },
    {
      fieldName: 'profitRate',
      label: '收益率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入收益率（%）',
      },
    },
    {
      fieldName: 'todayIncome',
      label: '今日收益',
      component: 'Input',
      componentProps: {
        placeholder: '请输入今日收益',
      },
    },
    {
      fieldName: 'yesterdayIncome',
      label: '昨日收益',
      component: 'Input',
      componentProps: {
        placeholder: '请输入昨日收益',
      },
    },
    {
      fieldName: 'deptId',
      label: '销售机构ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入销售机构ID',
      },
    },
    {
      fieldName: 'deptName',
      label: '销售机构名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入销售机构名称',
      },
    },
    {
      fieldName: 'managerUserId',
      label: '基金顾问用户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入基金顾问用户ID',
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
      label: '基金账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入基金账号',
      },
    },
    {
      fieldName: 'fundCode',
      label: '基金代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入基金代码',
      },
    },
    {
      fieldName: 'productName',
      label: '基金名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入基金名称',
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
      label: '开户日期（首次申购日期）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'closeDate',
      label: '销户日期（全部赎回日期）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）',
      },
    },
    {
      fieldName: 'fundType',
      label: '基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）',
      },
    },
    {
      fieldName: 'fundCompany',
      label: '基金公司',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入基金公司',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级（字典: aicrm_risk_level）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入风险等级（字典: aicrm_risk_level）',
      },
    },
    {
      fieldName: 'currencyType',
      label: '币种',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择币种',
      },
    },
    {
      fieldName: 'holdingShares',
      label: '持有份额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入持有份额',
      },
    },
    {
      fieldName: 'purchaseAmount',
      label: '累计申购金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计申购金额',
      },
    },
    {
      fieldName: 'currentNav',
      label: '当前净值',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前净值',
      },
    },
    {
      fieldName: 'currentValue',
      label: '当前市值',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前市值',
      },
    },
    {
      fieldName: 'accumulatedIncome',
      label: '累计收益',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计收益',
      },
    },
    {
      fieldName: 'balance',
      label: '账户余额（现金部分）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账户余额（现金部分）',
      },
    },
    {
      fieldName: 'costPrice',
      label: '成本价',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入成本价',
      },
    },
    {
      fieldName: 'profitRate',
      label: '收益率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入收益率（%）',
      },
    },
    {
      fieldName: 'todayIncome',
      label: '今日收益',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入今日收益',
      },
    },
    {
      fieldName: 'yesterdayIncome',
      label: '昨日收益',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入昨日收益',
      },
    },
    {
      fieldName: 'deptId',
      label: '销售机构ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入销售机构ID',
      },
    },
    {
      fieldName: 'deptName',
      label: '销售机构名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入销售机构名称',
      },
    },
    {
      fieldName: 'managerUserId',
      label: '基金顾问用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入基金顾问用户ID',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerAccountFundApi.CustomerAccountFund>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '基金账户主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '基金账号',
      minWidth: 120,
    },
    {
      field: 'fundCode',
      title: '基金代码',
      minWidth: 120,
    },
    {
      field: 'productName',
      title: '基金名称',
      minWidth: 120,
    },
    {
      field: 'accountName',
      title: '户名',
      minWidth: 120,
    },
    {
      field: 'openDate',
      title: '开户日期（首次申购日期）',
      minWidth: 120,
    },
    {
      field: 'closeDate',
      title: '销户日期（全部赎回日期）',
      minWidth: 120,
    },
    {
      field: 'accountStatus',
      title: '账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）',
      minWidth: 120,
    },
    {
      field: 'fundType',
      title: '基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）',
      minWidth: 120,
    },
    {
      field: 'fundCompany',
      title: '基金公司',
      minWidth: 120,
    },
    {
      field: 'riskLevel',
      title: '风险等级（字典: aicrm_risk_level）',
      minWidth: 120,
    },
    {
      field: 'currencyType',
      title: '币种',
      minWidth: 120,
    },
    {
      field: 'holdingShares',
      title: '持有份额',
      minWidth: 120,
    },
    {
      field: 'purchaseAmount',
      title: '累计申购金额',
      minWidth: 120,
    },
    {
      field: 'currentNav',
      title: '当前净值',
      minWidth: 120,
    },
    {
      field: 'currentValue',
      title: '当前市值',
      minWidth: 120,
    },
    {
      field: 'accumulatedIncome',
      title: '累计收益',
      minWidth: 120,
    },
    {
      field: 'balance',
      title: '账户余额（现金部分）',
      minWidth: 120,
    },
    {
      field: 'costPrice',
      title: '成本价',
      minWidth: 120,
    },
    {
      field: 'profitRate',
      title: '收益率（%）',
      minWidth: 120,
    },
    {
      field: 'todayIncome',
      title: '今日收益',
      minWidth: 120,
    },
    {
      field: 'yesterdayIncome',
      title: '昨日收益',
      minWidth: 120,
    },
    {
      field: 'deptId',
      title: '销售机构ID',
      minWidth: 120,
    },
    {
      field: 'deptName',
      title: '销售机构名称',
      minWidth: 120,
    },
    {
      field: 'managerUserId',
      title: '基金顾问用户ID',
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