import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAccountInsuranceApi } from '#/api/aicrm/customeraccountinsurance';

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
      label: '保单号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入保单号',
      },
    },
    {
      fieldName: 'policyNo',
      label: '保险单号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入保险单号',
      },
    },
    {
      fieldName: 'productName',
      label: '保险产品名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入保险产品名称',
      },
    },
    {
      fieldName: 'accountName',
      label: '投保人姓名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入投保人姓名',
      },
    },
    {
      fieldName: 'openDate',
      label: '投保日期（开户日期）',
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
      label: '退保日期（销户日期）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'insuranceType',
      label: '保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）',
      },
    },
    {
      fieldName: 'insuranceCompany',
      label: '保险公司',
      component: 'Input',
      componentProps: {
        placeholder: '请输入保险公司',
      },
    },
    {
      fieldName: 'insuranceTerm',
      label: '保险期限（如：终身、20年、至70岁）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入保险期限（如：终身、20年、至70岁）',
      },
    },
    {
      fieldName: 'paymentTerm',
      label: '缴费期限（如：趸交、5年、10年、20年）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入缴费期限（如：趸交、5年、10年、20年）',
      },
    },
    {
      fieldName: 'paymentFrequency',
      label: '缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）',
      },
    },
    {
      fieldName: 'insuredAmount',
      label: '保险金额（保额）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入保险金额（保额）',
      },
    },
    {
      fieldName: 'premium',
      label: '保费（年交保费）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入保费（年交保费）',
      },
    },
    {
      fieldName: 'paidPremium',
      label: '已交保费',
      component: 'Input',
      componentProps: {
        placeholder: '请输入已交保费',
      },
    },
    {
      fieldName: 'cashValue',
      label: '现金价值',
      component: 'Input',
      componentProps: {
        placeholder: '请输入现金价值',
      },
    },
    {
      fieldName: 'balance',
      label: '账户价值（万能险、投连险）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账户价值（万能险、投连险）',
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
      fieldName: 'insuredName',
      label: '被保险人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入被保险人姓名',
      },
    },
    {
      fieldName: 'insuredRelation',
      label: '与投保人关系（字典: aicrm_insured_relation）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入与投保人关系（字典: aicrm_insured_relation）',
      },
    },
    {
      fieldName: 'beneficiaryName',
      label: '受益人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入受益人姓名',
      },
    },
    {
      fieldName: 'effectiveDate',
      label: '生效日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'expireDate',
      label: '到期日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'nextPaymentDate',
      label: '下次缴费日期',
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
      label: '保险顾问用户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入保险顾问用户ID',
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
      label: '保单号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入保单号',
      },
    },
    {
      fieldName: 'policyNo',
      label: '保险单号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入保险单号',
      },
    },
    {
      fieldName: 'productName',
      label: '保险产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入保险产品名称',
      },
    },
    {
      fieldName: 'accountName',
      label: '投保人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入投保人姓名',
      },
    },
    {
      fieldName: 'openDate',
      label: '投保日期（开户日期）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'closeDate',
      label: '退保日期（销户日期）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）',
      },
    },
    {
      fieldName: 'insuranceType',
      label: '保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）',
      },
    },
    {
      fieldName: 'insuranceCompany',
      label: '保险公司',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入保险公司',
      },
    },
    {
      fieldName: 'insuranceTerm',
      label: '保险期限（如：终身、20年、至70岁）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入保险期限（如：终身、20年、至70岁）',
      },
    },
    {
      fieldName: 'paymentTerm',
      label: '缴费期限（如：趸交、5年、10年、20年）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入缴费期限（如：趸交、5年、10年、20年）',
      },
    },
    {
      fieldName: 'paymentFrequency',
      label: '缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）',
      },
    },
    {
      fieldName: 'insuredAmount',
      label: '保险金额（保额）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入保险金额（保额）',
      },
    },
    {
      fieldName: 'premium',
      label: '保费（年交保费）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入保费（年交保费）',
      },
    },
    {
      fieldName: 'paidPremium',
      label: '已交保费',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入已交保费',
      },
    },
    {
      fieldName: 'cashValue',
      label: '现金价值',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入现金价值',
      },
    },
    {
      fieldName: 'balance',
      label: '账户价值（万能险、投连险）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账户价值（万能险、投连险）',
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
      fieldName: 'insuredName',
      label: '被保险人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入被保险人姓名',
      },
    },
    {
      fieldName: 'insuredRelation',
      label: '与投保人关系（字典: aicrm_insured_relation）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入与投保人关系（字典: aicrm_insured_relation）',
      },
    },
    {
      fieldName: 'beneficiaryName',
      label: '受益人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入受益人姓名',
      },
    },
    {
      fieldName: 'effectiveDate',
      label: '生效日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'expireDate',
      label: '到期日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'nextPaymentDate',
      label: '下次缴费日期',
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
      label: '保险顾问用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入保险顾问用户ID',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerAccountInsuranceApi.CustomerAccountInsurance>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '保险账户主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '保单号',
      minWidth: 120,
    },
    {
      field: 'policyNo',
      title: '保险单号',
      minWidth: 120,
    },
    {
      field: 'productName',
      title: '保险产品名称',
      minWidth: 120,
    },
    {
      field: 'accountName',
      title: '投保人姓名',
      minWidth: 120,
    },
    {
      field: 'openDate',
      title: '投保日期（开户日期）',
      minWidth: 120,
    },
    {
      field: 'closeDate',
      title: '退保日期（销户日期）',
      minWidth: 120,
    },
    {
      field: 'accountStatus',
      title: '账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）',
      minWidth: 120,
    },
    {
      field: 'insuranceType',
      title: '保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）',
      minWidth: 120,
    },
    {
      field: 'insuranceCompany',
      title: '保险公司',
      minWidth: 120,
    },
    {
      field: 'insuranceTerm',
      title: '保险期限（如：终身、20年、至70岁）',
      minWidth: 120,
    },
    {
      field: 'paymentTerm',
      title: '缴费期限（如：趸交、5年、10年、20年）',
      minWidth: 120,
    },
    {
      field: 'paymentFrequency',
      title: '缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）',
      minWidth: 120,
    },
    {
      field: 'insuredAmount',
      title: '保险金额（保额）',
      minWidth: 120,
    },
    {
      field: 'premium',
      title: '保费（年交保费）',
      minWidth: 120,
    },
    {
      field: 'paidPremium',
      title: '已交保费',
      minWidth: 120,
    },
    {
      field: 'cashValue',
      title: '现金价值',
      minWidth: 120,
    },
    {
      field: 'balance',
      title: '账户价值（万能险、投连险）',
      minWidth: 120,
    },
    {
      field: 'currencyType',
      title: '币种',
      minWidth: 120,
    },
    {
      field: 'insuredName',
      title: '被保险人姓名',
      minWidth: 120,
    },
    {
      field: 'insuredRelation',
      title: '与投保人关系（字典: aicrm_insured_relation）',
      minWidth: 120,
    },
    {
      field: 'beneficiaryName',
      title: '受益人姓名',
      minWidth: 120,
    },
    {
      field: 'effectiveDate',
      title: '生效日期',
      minWidth: 120,
    },
    {
      field: 'expireDate',
      title: '到期日期',
      minWidth: 120,
    },
    {
      field: 'nextPaymentDate',
      title: '下次缴费日期',
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
      title: '保险顾问用户ID',
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