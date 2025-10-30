import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerAccountTrustApi } from '#/api/aicrm/customeraccounttrust';

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
      label: '信托账号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信托账号',
      },
    },
    {
      fieldName: 'trustContractNo',
      label: '信托合同号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信托合同号',
      },
    },
    {
      fieldName: 'productName',
      label: '信托产品名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信托产品名称',
      },
    },
    {
      fieldName: 'accountName',
      label: '委托人姓名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入委托人姓名',
      },
    },
    {
      fieldName: 'openDate',
      label: '成立日期（开户日期）',
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
      label: '终止日期（销户日期）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'trustType',
      label: '信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）',
      },
    },
    {
      fieldName: 'trustCompany',
      label: '信托公司',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信托公司',
      },
    },
    {
      fieldName: 'trustPurpose',
      label: '信托目的',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信托目的',
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
      fieldName: 'trustTerm',
      label: '信托期限（如：2年、3年、5年）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信托期限（如：2年、3年、5年）',
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
      fieldName: 'trustAmount',
      label: '信托金额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信托金额',
      },
    },
    {
      fieldName: 'paidAmount',
      label: '已支付金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入已支付金额',
      },
    },
    {
      fieldName: 'currentValue',
      label: '当前价值',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前价值',
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
      label: '账户余额',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入账户余额',
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
      fieldName: 'beneficiaryRelation',
      label: '与委托人关系',
      component: 'Input',
      componentProps: {
        placeholder: '请输入与委托人关系',
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
      fieldName: 'nextDistributionDate',
      label: '下次分配日',
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
      label: '信托顾问用户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信托顾问用户ID',
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
      label: '信托账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信托账号',
      },
    },
    {
      fieldName: 'trustContractNo',
      label: '信托合同号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信托合同号',
      },
    },
    {
      fieldName: 'productName',
      label: '信托产品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信托产品名称',
      },
    },
    {
      fieldName: 'accountName',
      label: '委托人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入委托人姓名',
      },
    },
    {
      fieldName: 'openDate',
      label: '成立日期（开户日期）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'closeDate',
      label: '终止日期（销户日期）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'accountStatus',
      label: '账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）',
      },
    },
    {
      fieldName: 'trustType',
      label: '信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）',
      },
    },
    {
      fieldName: 'trustCompany',
      label: '信托公司',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信托公司',
      },
    },
    {
      fieldName: 'trustPurpose',
      label: '信托目的',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信托目的',
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
      fieldName: 'trustTerm',
      label: '信托期限（如：2年、3年、5年）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信托期限（如：2年、3年、5年）',
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
      fieldName: 'trustAmount',
      label: '信托金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信托金额',
      },
    },
    {
      fieldName: 'paidAmount',
      label: '已支付金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入已支付金额',
      },
    },
    {
      fieldName: 'currentValue',
      label: '当前价值',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前价值',
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
      label: '账户余额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入账户余额',
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
      fieldName: 'beneficiaryRelation',
      label: '与委托人关系',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入与委托人关系',
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
      fieldName: 'matureDate',
      label: '到期日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'nextDistributionDate',
      label: '下次分配日',
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
      label: '信托顾问用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入信托顾问用户ID',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerAccountTrustApi.CustomerAccountTrust>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '信托账户主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '信托账号',
      minWidth: 120,
    },
    {
      field: 'trustContractNo',
      title: '信托合同号',
      minWidth: 120,
    },
    {
      field: 'productName',
      title: '信托产品名称',
      minWidth: 120,
    },
    {
      field: 'accountName',
      title: '委托人姓名',
      minWidth: 120,
    },
    {
      field: 'openDate',
      title: '成立日期（开户日期）',
      minWidth: 120,
    },
    {
      field: 'closeDate',
      title: '终止日期（销户日期）',
      minWidth: 120,
    },
    {
      field: 'accountStatus',
      title: '账户状态（字典: aicrm_trust_status，valid=有效，matured=已到期，terminated=已终止）',
      minWidth: 120,
    },
    {
      field: 'trustType',
      title: '信托类型（字典: aicrm_trust_type，single=单一信托，collective=集合信托，property=财产权信托）',
      minWidth: 120,
    },
    {
      field: 'trustCompany',
      title: '信托公司',
      minWidth: 120,
    },
    {
      field: 'trustPurpose',
      title: '信托目的',
      minWidth: 120,
    },
    {
      field: 'expectedReturnRate',
      title: '预期收益率（年化%）',
      minWidth: 120,
    },
    {
      field: 'trustTerm',
      title: '信托期限（如：2年、3年、5年）',
      minWidth: 120,
    },
    {
      field: 'currencyType',
      title: '币种',
      minWidth: 120,
    },
    {
      field: 'trustAmount',
      title: '信托金额',
      minWidth: 120,
    },
    {
      field: 'paidAmount',
      title: '已支付金额',
      minWidth: 120,
    },
    {
      field: 'currentValue',
      title: '当前价值',
      minWidth: 120,
    },
    {
      field: 'accumulatedIncome',
      title: '累计收益',
      minWidth: 120,
    },
    {
      field: 'balance',
      title: '账户余额',
      minWidth: 120,
    },
    {
      field: 'beneficiaryName',
      title: '受益人姓名',
      minWidth: 120,
    },
    {
      field: 'beneficiaryRelation',
      title: '与委托人关系',
      minWidth: 120,
    },
    {
      field: 'effectiveDate',
      title: '生效日期',
      minWidth: 120,
    },
    {
      field: 'matureDate',
      title: '到期日',
      minWidth: 120,
    },
    {
      field: 'nextDistributionDate',
      title: '下次分配日',
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
      title: '信托顾问用户ID',
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