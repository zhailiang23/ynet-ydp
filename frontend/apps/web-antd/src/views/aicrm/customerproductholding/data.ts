import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerProductHoldingApi } from '#/api/aicrm/customerproductholding';

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
      fieldName: 'productId',
      label: '产品ID（关联 crm_product_category.id，必须是叶子节点）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品ID（关联 crm_product_category.id，必须是叶子节点）',
      },
    },
    {
      fieldName: 'accountNo',
      label: '贷款账号/账号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款账号/账号',
      },
    },
    {
      fieldName: 'receiptNo',
      label: '借据编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入借据编号',
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
      fieldName: 'currencyCode',
      label: '币种代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入币种代码',
      },
    },
    {
      fieldName: 'loanDate',
      label: '放款日期（贷款产品）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'openDate',
      label: '开户日期（存款、理财等产品）',
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
      fieldName: 'contractDate',
      label: '合同日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'branchName',
      label: '开户网点名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入开户网点名称',
      },
    },
    {
      fieldName: 'branchId',
      label: '开户网点ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入开户网点ID（关联 system_dept.id）',
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
      fieldName: 'holdingAmount',
      label: '持有金额/余额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入持有金额/余额',
      },
    },
    {
      fieldName: 'originalAmount',
      label: '原始金额（初始投资/贷款金额）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入原始金额（初始投资/贷款金额）',
      },
    },
    {
      fieldName: 'interestRate',
      label: '利率/收益率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入利率/收益率（%）',
      },
    },
    {
      fieldName: 'holdingStatus',
      label: '持有状态（字典: aicrm_holding_status，holding=持有中，matured=已到期，redeemed=已赎回，settled=已结清）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'relatedAccountType',
      label: '关联账户类型（deposit=存款账户，loan=贷款账户，wealth=理财账户等）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择关联账户类型（deposit=存款账户，loan=贷款账户，wealth=理财账户等）',
      },
    },
    {
      fieldName: 'relatedAccountId',
      label: '关联账户ID（关联对应的账户表主键）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联账户ID（关联对应的账户表主键）',
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
      fieldName: 'productId',
      label: '产品ID（关联 crm_product_category.id，必须是叶子节点）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品ID（关联 crm_product_category.id，必须是叶子节点）',
      },
    },
    {
      fieldName: 'accountNo',
      label: '贷款账号/账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款账号/账号',
      },
    },
    {
      fieldName: 'receiptNo',
      label: '借据编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入借据编号',
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
      fieldName: 'currencyCode',
      label: '币种代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入币种代码',
      },
    },
    {
      fieldName: 'loanDate',
      label: '放款日期（贷款产品）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'openDate',
      label: '开户日期（存款、理财等产品）',
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
      fieldName: 'contractDate',
      label: '合同日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'branchName',
      label: '开户网点名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入开户网点名称',
      },
    },
    {
      fieldName: 'branchId',
      label: '开户网点ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入开户网点ID（关联 system_dept.id）',
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
      fieldName: 'holdingAmount',
      label: '持有金额/余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入持有金额/余额',
      },
    },
    {
      fieldName: 'originalAmount',
      label: '原始金额（初始投资/贷款金额）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入原始金额（初始投资/贷款金额）',
      },
    },
    {
      fieldName: 'interestRate',
      label: '利率/收益率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入利率/收益率（%）',
      },
    },
    {
      fieldName: 'holdingStatus',
      label: '持有状态（字典: aicrm_holding_status，holding=持有中，matured=已到期，redeemed=已赎回，settled=已结清）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择持有状态（字典: aicrm_holding_status，holding=持有中，matured=已到期，redeemed=已赎回，settled=已结清）',
      },
    },
    {
      fieldName: 'relatedAccountType',
      label: '关联账户类型（deposit=存款账户，loan=贷款账户，wealth=理财账户等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择关联账户类型（deposit=存款账户，loan=贷款账户，wealth=理财账户等）',
      },
    },
    {
      fieldName: 'relatedAccountId',
      label: '关联账户ID（关联对应的账户表主键）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联账户ID（关联对应的账户表主键）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerProductHoldingApi.CustomerProductHolding>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '产品持有主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'productId',
      title: '产品ID（关联 crm_product_category.id，必须是叶子节点）',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '贷款账号/账号',
      minWidth: 120,
    },
    {
      field: 'receiptNo',
      title: '借据编号',
      minWidth: 120,
    },
    {
      field: 'contractNo',
      title: '合同编号',
      minWidth: 120,
    },
    {
      field: 'currencyCode',
      title: '币种代码',
      minWidth: 120,
    },
    {
      field: 'loanDate',
      title: '放款日期（贷款产品）',
      minWidth: 120,
    },
    {
      field: 'openDate',
      title: '开户日期（存款、理财等产品）',
      minWidth: 120,
    },
    {
      field: 'matureDate',
      title: '到期日期',
      minWidth: 120,
    },
    {
      field: 'contractDate',
      title: '合同日期',
      minWidth: 120,
    },
    {
      field: 'branchName',
      title: '开户网点名称',
      minWidth: 120,
    },
    {
      field: 'branchId',
      title: '开户网点ID（关联 system_dept.id）',
      minWidth: 120,
    },
    {
      field: 'productName',
      title: '产品名称',
      minWidth: 120,
    },
    {
      field: 'holdingAmount',
      title: '持有金额/余额',
      minWidth: 120,
    },
    {
      field: 'originalAmount',
      title: '原始金额（初始投资/贷款金额）',
      minWidth: 120,
    },
    {
      field: 'interestRate',
      title: '利率/收益率（%）',
      minWidth: 120,
    },
    {
      field: 'holdingStatus',
      title: '持有状态（字典: aicrm_holding_status，holding=持有中，matured=已到期，redeemed=已赎回，settled=已结清）',
      minWidth: 120,
    },
    {
      field: 'relatedAccountType',
      title: '关联账户类型（deposit=存款账户，loan=贷款账户，wealth=理财账户等）',
      minWidth: 120,
    },
    {
      field: 'relatedAccountId',
      title: '关联账户ID（关联对应的账户表主键）',
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