import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerContractApi } from '#/api/aicrm/customercontract';

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
      fieldName: 'contractTypeId',
      label: '签约类型ID（关联 crm_contract_type.id）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入签约类型ID（关联 crm_contract_type.id）',
      },
    },
    {
      fieldName: 'contractNo',
      label: '签约账号/签约编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入签约账号/签约编号',
      },
    },
    {
      fieldName: 'contractDate',
      label: '签约日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'branchId',
      label: '签约机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入签约机构ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'branchName',
      label: '签约机构名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入签约机构名称',
      },
    },
    {
      fieldName: 'contractStatus',
      label: '签约状态（字典: aicrm_contract_status，signed=已签约，cancelled=已解约，expired=已过期，frozen=已冻结）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'contractSituation',
      label: '签约情况（具体的签约渠道或产品名称）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入签约情况（具体的签约渠道或产品名称）',
      },
    },
    {
      fieldName: 'identityType',
      label: '证件类型（字典: aicrm_identity_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择证件类型（字典: aicrm_identity_type）',
      },
    },
    {
      fieldName: 'identityNo',
      label: '证件号码（加密存储）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入证件号码（加密存储）',
      },
    },
    {
      fieldName: 'accountNo',
      label: '关联账号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联账号',
      },
    },
    {
      fieldName: 'startDate',
      label: '生效日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'endDate',
      label: '失效日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'signChannel',
      label: '签约渠道（字典: aicrm_sign_channel，counter=柜面，mobile=手机银行，online=网上银行，wechat=微信，other=其他）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入签约渠道（字典: aicrm_sign_channel，counter=柜面，mobile=手机银行，online=网上银行，wechat=微信，other=其他）',
      },
    },
    {
      fieldName: 'cancelDate',
      label: '解约日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'cancelReason',
      label: '解约原因',
      component: 'Input',
      componentProps: {
        placeholder: '请输入解约原因',
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
      fieldName: 'contractTypeId',
      label: '签约类型ID（关联 crm_contract_type.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入签约类型ID（关联 crm_contract_type.id）',
      },
    },
    {
      fieldName: 'contractNo',
      label: '签约账号/签约编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入签约账号/签约编号',
      },
    },
    {
      fieldName: 'contractDate',
      label: '签约日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'branchId',
      label: '签约机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入签约机构ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'branchName',
      label: '签约机构名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入签约机构名称',
      },
    },
    {
      fieldName: 'contractStatus',
      label: '签约状态（字典: aicrm_contract_status，signed=已签约，cancelled=已解约，expired=已过期，frozen=已冻结）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择签约状态（字典: aicrm_contract_status，signed=已签约，cancelled=已解约，expired=已过期，frozen=已冻结）',
      },
    },
    {
      fieldName: 'contractSituation',
      label: '签约情况（具体的签约渠道或产品名称）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入签约情况（具体的签约渠道或产品名称）',
      },
    },
    {
      fieldName: 'identityType',
      label: '证件类型（字典: aicrm_identity_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择证件类型（字典: aicrm_identity_type）',
      },
    },
    {
      fieldName: 'identityNo',
      label: '证件号码（加密存储）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入证件号码（加密存储）',
      },
    },
    {
      fieldName: 'accountNo',
      label: '关联账号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联账号',
      },
    },
    {
      fieldName: 'startDate',
      label: '生效日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'endDate',
      label: '失效日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'signChannel',
      label: '签约渠道（字典: aicrm_sign_channel，counter=柜面，mobile=手机银行，online=网上银行，wechat=微信，other=其他）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入签约渠道（字典: aicrm_sign_channel，counter=柜面，mobile=手机银行，online=网上银行，wechat=微信，other=其他）',
      },
    },
    {
      fieldName: 'cancelDate',
      label: '解约日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'cancelReason',
      label: '解约原因',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入解约原因',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerContractApi.CustomerContract>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '签约主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'contractTypeId',
      title: '签约类型ID（关联 crm_contract_type.id）',
      minWidth: 120,
    },
    {
      field: 'contractNo',
      title: '签约账号/签约编号',
      minWidth: 120,
    },
    {
      field: 'contractDate',
      title: '签约日期',
      minWidth: 120,
    },
    {
      field: 'branchId',
      title: '签约机构ID（关联 system_dept.id）',
      minWidth: 120,
    },
    {
      field: 'branchName',
      title: '签约机构名称',
      minWidth: 120,
    },
    {
      field: 'contractStatus',
      title: '签约状态（字典: aicrm_contract_status，signed=已签约，cancelled=已解约，expired=已过期，frozen=已冻结）',
      minWidth: 120,
    },
    {
      field: 'contractSituation',
      title: '签约情况（具体的签约渠道或产品名称）',
      minWidth: 120,
    },
    {
      field: 'identityType',
      title: '证件类型（字典: aicrm_identity_type）',
      minWidth: 120,
    },
    {
      field: 'identityNo',
      title: '证件号码（加密存储）',
      minWidth: 120,
    },
    {
      field: 'accountNo',
      title: '关联账号',
      minWidth: 120,
    },
    {
      field: 'startDate',
      title: '生效日期',
      minWidth: 120,
    },
    {
      field: 'endDate',
      title: '失效日期',
      minWidth: 120,
    },
    {
      field: 'signChannel',
      title: '签约渠道（字典: aicrm_sign_channel，counter=柜面，mobile=手机银行，online=网上银行，wechat=微信，other=其他）',
      minWidth: 120,
    },
    {
      field: 'cancelDate',
      title: '解约日期',
      minWidth: 120,
    },
    {
      field: 'cancelReason',
      title: '解约原因',
      minWidth: 120,
    },
    {
      field: 'managerUserId',
      title: '客户经理ID（关联 system_users.id）',
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