import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAccountMetalApi } from '#/api/aicrm/customeraccountmetal';

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
      label: '贵金属账号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贵金属账号',
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
      label: '账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'metalType',
      label: '贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）',
      },
    },
    {
      fieldName: 'metalCategory',
      label: '产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）',
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
      fieldName: 'holdingWeight',
      label: '持有重量（克）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入持有重量（克）',
      },
    },
    {
      fieldName: 'averageCost',
      label: '平均成本（元/克）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入平均成本（元/克）',
      },
    },
    {
      fieldName: 'currentPrice',
      label: '当前价格（元/克）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前价格（元/克）',
      },
    },
    {
      fieldName: 'currentValue',
      label: '当前市值（元）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前市值（元）',
      },
    },
    {
      fieldName: 'accumulatedIncome',
      label: '累计收益（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计收益（元）',
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
      fieldName: 'balance',
      label: '账户余额（元）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账户余额（元）',
      },
    },
    {
      fieldName: 'totalBuyWeight',
      label: '累计买入重量（克）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计买入重量（克）',
      },
    },
    {
      fieldName: 'totalBuyAmount',
      label: '累计买入金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计买入金额（元）',
      },
    },
    {
      fieldName: 'totalSellWeight',
      label: '累计卖出重量（克）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计卖出重量（克）',
      },
    },
    {
      fieldName: 'totalSellAmount',
      label: '累计卖出金额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入累计卖出金额（元）',
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
      fieldName: 'deptId',
      label: '开户机构ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入开户机构ID',
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
      label: '客户经理用户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户经理用户ID',
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
      label: '贵金属账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贵金属账号',
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
      label: '账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）',
      },
    },
    {
      fieldName: 'metalType',
      label: '贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）',
      },
    },
    {
      fieldName: 'metalCategory',
      label: '产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）',
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
      fieldName: 'holdingWeight',
      label: '持有重量（克）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入持有重量（克）',
      },
    },
    {
      fieldName: 'averageCost',
      label: '平均成本（元/克）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入平均成本（元/克）',
      },
    },
    {
      fieldName: 'currentPrice',
      label: '当前价格（元/克）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前价格（元/克）',
      },
    },
    {
      fieldName: 'currentValue',
      label: '当前市值（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前市值（元）',
      },
    },
    {
      fieldName: 'accumulatedIncome',
      label: '累计收益（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计收益（元）',
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
      fieldName: 'balance',
      label: '账户余额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账户余额（元）',
      },
    },
    {
      fieldName: 'totalBuyWeight',
      label: '累计买入重量（克）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计买入重量（克）',
      },
    },
    {
      fieldName: 'totalBuyAmount',
      label: '累计买入金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计买入金额（元）',
      },
    },
    {
      fieldName: 'totalSellWeight',
      label: '累计卖出重量（克）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计卖出重量（克）',
      },
    },
    {
      fieldName: 'totalSellAmount',
      label: '累计卖出金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入累计卖出金额（元）',
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
      fieldName: 'deptId',
      label: '开户机构ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入开户机构ID',
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
      label: '客户经理用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户经理用户ID',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerAccountMetalApi.CustomerAccountMetal>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '贵金属账户主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '贵金属账号',
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
      title: '账户状态（字典: aicrm_metal_account_status，normal=正常，closed=已销户）',
      minWidth: 120,
    },
    {
      field: 'metalType',
      title: '贵金属类型（字典: aicrm_metal_type，gold=黄金，silver=白银，platinum=铂金，palladium=钯金）',
      minWidth: 120,
    },
    {
      field: 'metalCategory',
      title: '产品类别（字典: aicrm_metal_category，physical=实物，paper=纸黄金，td=T+D，futures=期货）',
      minWidth: 120,
    },
    {
      field: 'productCode',
      title: '产品代码',
      minWidth: 120,
    },
    {
      field: 'holdingWeight',
      title: '持有重量（克）',
      minWidth: 120,
    },
    {
      field: 'averageCost',
      title: '平均成本（元/克）',
      minWidth: 120,
    },
    {
      field: 'currentPrice',
      title: '当前价格（元/克）',
      minWidth: 120,
    },
    {
      field: 'currentValue',
      title: '当前市值（元）',
      minWidth: 120,
    },
    {
      field: 'accumulatedIncome',
      title: '累计收益（元）',
      minWidth: 120,
    },
    {
      field: 'profitRate',
      title: '收益率（%）',
      minWidth: 120,
    },
    {
      field: 'balance',
      title: '账户余额（元）',
      minWidth: 120,
    },
    {
      field: 'totalBuyWeight',
      title: '累计买入重量（克）',
      minWidth: 120,
    },
    {
      field: 'totalBuyAmount',
      title: '累计买入金额（元）',
      minWidth: 120,
    },
    {
      field: 'totalSellWeight',
      title: '累计卖出重量（克）',
      minWidth: 120,
    },
    {
      field: 'totalSellAmount',
      title: '累计卖出金额（元）',
      minWidth: 120,
    },
    {
      field: 'currencyType',
      title: '币种',
      minWidth: 120,
    },
    {
      field: 'deptId',
      title: '开户机构ID',
      minWidth: 120,
    },
    {
      field: 'deptName',
      title: '开户机构名称',
      minWidth: 120,
    },
    {
      field: 'managerUserId',
      title: '客户经理用户ID',
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