import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAccountWealthApi } from '#/api/aicrm/customeraccountwealth';

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
      label: '理财账号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入理财账号',
      },
    },
    {
      fieldName: 'productCode',
      label: '理财产品代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入理财产品代码',
      },
    },
    {
      fieldName: 'productName',
      label: '理财产品名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入理财产品名称',
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
      label: '开户日期（购买日期）',
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
      label: '销户日期（赎回/到期日期）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_wealth_account_status，holding=持有中，matured=已到期，redeemed=已赎回）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'productType',
      label: '理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级（字典: aicrm_risk_level，R1-R5）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入风险等级（字典: aicrm_risk_level，R1-R5）',
      },
    },
    {
      fieldName: 'expectedReturnRate',
      label: '预期收益率（年化%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入预期收益率（年化%）',
      },
    },
    {
      fieldName: 'actualReturnRate',
      label: '实际收益率（年化%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入实际收益率（年化%）',
      },
    },
    {
      fieldName: 'wealthTerm',
      label: '理财期限（如：90天、180天、1年）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入理财期限（如：90天、180天、1年）',
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
      fieldName: 'purchaseAmount',
      label: '购买金额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入购买金额',
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
      label: '持有份额/余额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入持有份额/余额',
      },
    },
    {
      fieldName: 'valueDate',
      label: '起息日',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
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
      fieldName: 'nextOpenDate',
      label: '下次开放日（开放式理财）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
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
      label: '理财顾问用户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入理财顾问用户ID',
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
      label: '理财账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入理财账号',
      },
    },
    {
      fieldName: 'productCode',
      label: '理财产品代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入理财产品代码',
      },
    },
    {
      fieldName: 'productName',
      label: '理财产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入理财产品名称',
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
      label: '开户日期（购买日期）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'closeDate',
      label: '销户日期（赎回/到期日期）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_wealth_account_status，holding=持有中，matured=已到期，redeemed=已赎回）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择账户状态（字典: aicrm_wealth_account_status，holding=持有中，matured=已到期，redeemed=已赎回）',
      },
    },
    {
      fieldName: 'productType',
      label: '理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级（字典: aicrm_risk_level，R1-R5）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入风险等级（字典: aicrm_risk_level，R1-R5）',
      },
    },
    {
      fieldName: 'expectedReturnRate',
      label: '预期收益率（年化%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入预期收益率（年化%）',
      },
    },
    {
      fieldName: 'actualReturnRate',
      label: '实际收益率（年化%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入实际收益率（年化%）',
      },
    },
    {
      fieldName: 'wealthTerm',
      label: '理财期限（如：90天、180天、1年）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入理财期限（如：90天、180天、1年）',
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
      fieldName: 'purchaseAmount',
      label: '购买金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入购买金额',
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
      label: '持有份额/余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入持有份额/余额',
      },
    },
    {
      fieldName: 'valueDate',
      label: '起息日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
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
      fieldName: 'nextOpenDate',
      label: '下次开放日（开放式理财）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
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
      label: '理财顾问用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入理财顾问用户ID',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerAccountWealthApi.CustomerAccountWealth>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '理财账户主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '理财账号',
      minWidth: 120,
    },
    {
      field: 'productCode',
      title: '理财产品代码',
      minWidth: 120,
    },
    {
      field: 'productName',
      title: '理财产品名称',
      minWidth: 120,
    },
    {
      field: 'accountName',
      title: '户名',
      minWidth: 120,
    },
    {
      field: 'openDate',
      title: '开户日期（购买日期）',
      minWidth: 120,
    },
    {
      field: 'closeDate',
      title: '销户日期（赎回/到期日期）',
      minWidth: 120,
    },
    {
      field: 'accountStatus',
      title: '账户状态（字典: aicrm_wealth_account_status，holding=持有中，matured=已到期，redeemed=已赎回）',
      minWidth: 120,
    },
    {
      field: 'productType',
      title: '理财类型（字典: aicrm_wealth_product_type，fixed=固定收益，floating=浮动收益，structured=结构性）',
      minWidth: 120,
    },
    {
      field: 'riskLevel',
      title: '风险等级（字典: aicrm_risk_level，R1-R5）',
      minWidth: 120,
    },
    {
      field: 'expectedReturnRate',
      title: '预期收益率（年化%）',
      minWidth: 120,
    },
    {
      field: 'actualReturnRate',
      title: '实际收益率（年化%）',
      minWidth: 120,
    },
    {
      field: 'wealthTerm',
      title: '理财期限（如：90天、180天、1年）',
      minWidth: 120,
    },
    {
      field: 'currencyType',
      title: '币种',
      minWidth: 120,
    },
    {
      field: 'purchaseAmount',
      title: '购买金额',
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
      title: '持有份额/余额',
      minWidth: 120,
    },
    {
      field: 'valueDate',
      title: '起息日',
      minWidth: 120,
    },
    {
      field: 'matureDate',
      title: '到期日',
      minWidth: 120,
    },
    {
      field: 'nextOpenDate',
      title: '下次开放日（开放式理财）',
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
      title: '理财顾问用户ID',
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