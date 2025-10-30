import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerGuaranteePledgeApi } from '#/api/aicrm/customerguaranteepledge';

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
      fieldName: 'creditId',
      label: '授信ID（关联 crm_customer_credit.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信ID（关联 crm_customer_credit.id）',
      },
    },
    {
      fieldName: 'collateralNo',
      label: '押品编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入押品编号',
      },
    },
    {
      fieldName: 'collateralType',
      label: '押品类型（字典: aicrm_pledge_type）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择押品类型（字典: aicrm_pledge_type）',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保类型（字典: aicrm_guarantee_method，pledge=质押）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择担保类型（字典: aicrm_guarantee_method，pledge=质押）',
      },
    },
    {
      fieldName: 'pledgorIdType',
      label: '质押人证件类型（字典: aicrm_identity_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择质押人证件类型（字典: aicrm_identity_type）',
      },
    },
    {
      fieldName: 'pledgorIdNo',
      label: '质押人证件号码（加密）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入质押人证件号码（加密）',
      },
    },
    {
      fieldName: 'pledgorName',
      label: '质押人姓名/名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入质押人姓名/名称',
      },
    },
    {
      fieldName: 'pledgorType',
      label: '质押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择质押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      },
    },
    {
      fieldName: 'relationWithBorrower',
      label: '与被担保人关系（字典: aicrm_relation_type）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入与被担保人关系（字典: aicrm_relation_type）',
      },
    },
    {
      fieldName: 'guaranteeAmount',
      label: '担保本金限权金额（万元）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入担保本金限权金额（万元）',
      },
    },
    {
      fieldName: 'pledgeRate',
      label: '质押率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入质押率（%）',
      },
    },
    {
      fieldName: 'collateralName',
      label: '质押物名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入质押物名称',
      },
    },
    {
      fieldName: 'collateralValue',
      label: '质押物价值（万元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入质押物价值（万元）',
      },
    },
    {
      fieldName: 'pledgeStatus',
      label: '质押状态（字典: aicrm_pledge_status，effective=有效，released=已解押，invalid=无效）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'pledgeDate',
      label: '质押登记日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'releaseDate',
      label: '解押日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'managementBranchId',
      label: '管理机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入管理机构ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'managementBranchName',
      label: '管理机构名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入管理机构名称',
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
      fieldName: 'creditId',
      label: '授信ID（关联 crm_customer_credit.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信ID（关联 crm_customer_credit.id）',
      },
    },
    {
      fieldName: 'collateralNo',
      label: '押品编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入押品编号',
      },
    },
    {
      fieldName: 'collateralType',
      label: '押品类型（字典: aicrm_pledge_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择押品类型（字典: aicrm_pledge_type）',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保类型（字典: aicrm_guarantee_method，pledge=质押）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择担保类型（字典: aicrm_guarantee_method，pledge=质押）',
      },
    },
    {
      fieldName: 'pledgorIdType',
      label: '质押人证件类型（字典: aicrm_identity_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择质押人证件类型（字典: aicrm_identity_type）',
      },
    },
    {
      fieldName: 'pledgorIdNo',
      label: '质押人证件号码（加密）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入质押人证件号码（加密）',
      },
    },
    {
      fieldName: 'pledgorName',
      label: '质押人姓名/名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入质押人姓名/名称',
      },
    },
    {
      fieldName: 'pledgorType',
      label: '质押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择质押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      },
    },
    {
      fieldName: 'relationWithBorrower',
      label: '与被担保人关系（字典: aicrm_relation_type）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入与被担保人关系（字典: aicrm_relation_type）',
      },
    },
    {
      fieldName: 'guaranteeAmount',
      label: '担保本金限权金额（万元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入担保本金限权金额（万元）',
      },
    },
    {
      fieldName: 'pledgeRate',
      label: '质押率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入质押率（%）',
      },
    },
    {
      fieldName: 'collateralName',
      label: '质押物名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入质押物名称',
      },
    },
    {
      fieldName: 'collateralValue',
      label: '质押物价值（万元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入质押物价值（万元）',
      },
    },
    {
      fieldName: 'pledgeStatus',
      label: '质押状态（字典: aicrm_pledge_status，effective=有效，released=已解押，invalid=无效）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择质押状态（字典: aicrm_pledge_status，effective=有效，released=已解押，invalid=无效）',
      },
    },
    {
      fieldName: 'pledgeDate',
      label: '质押登记日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'releaseDate',
      label: '解押日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'managementBranchId',
      label: '管理机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入管理机构ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'managementBranchName',
      label: '管理机构名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入管理机构名称',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerGuaranteePledgeApi.CustomerGuaranteePledge>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '质押物主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'creditId',
      title: '授信ID（关联 crm_customer_credit.id）',
      minWidth: 120,
    },
    {
      field: 'collateralNo',
      title: '押品编号',
      minWidth: 120,
    },
    {
      field: 'collateralType',
      title: '押品类型（字典: aicrm_pledge_type）',
      minWidth: 120,
    },
    {
      field: 'guaranteeType',
      title: '担保类型（字典: aicrm_guarantee_method，pledge=质押）',
      minWidth: 120,
    },
    {
      field: 'pledgorIdType',
      title: '质押人证件类型（字典: aicrm_identity_type）',
      minWidth: 120,
    },
    {
      field: 'pledgorIdNo',
      title: '质押人证件号码（加密）',
      minWidth: 120,
    },
    {
      field: 'pledgorName',
      title: '质押人姓名/名称',
      minWidth: 120,
    },
    {
      field: 'pledgorType',
      title: '质押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      minWidth: 120,
    },
    {
      field: 'relationWithBorrower',
      title: '与被担保人关系（字典: aicrm_relation_type）',
      minWidth: 120,
    },
    {
      field: 'guaranteeAmount',
      title: '担保本金限权金额（万元）',
      minWidth: 120,
    },
    {
      field: 'pledgeRate',
      title: '质押率（%）',
      minWidth: 120,
    },
    {
      field: 'collateralName',
      title: '质押物名称',
      minWidth: 120,
    },
    {
      field: 'collateralValue',
      title: '质押物价值（万元）',
      minWidth: 120,
    },
    {
      field: 'pledgeStatus',
      title: '质押状态（字典: aicrm_pledge_status，effective=有效，released=已解押，invalid=无效）',
      minWidth: 120,
    },
    {
      field: 'pledgeDate',
      title: '质押登记日期',
      minWidth: 120,
    },
    {
      field: 'releaseDate',
      title: '解押日期',
      minWidth: 120,
    },
    {
      field: 'managementBranchId',
      title: '管理机构ID（关联 system_dept.id）',
      minWidth: 120,
    },
    {
      field: 'managementBranchName',
      title: '管理机构名称',
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