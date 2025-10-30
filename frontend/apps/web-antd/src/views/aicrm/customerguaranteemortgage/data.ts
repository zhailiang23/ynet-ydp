import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerGuaranteeMortgageApi } from '#/api/aicrm/customerguaranteemortgage';

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
      fieldName: 'collateralName',
      label: '押品名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入押品名称',
      },
    },
    {
      fieldName: 'collateralType',
      label: '押品类型（字典: aicrm_mortgage_type）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择押品类型（字典: aicrm_mortgage_type）',
      },
    },
    {
      fieldName: 'certificateNo',
      label: '权证号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入权证号',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保类型（字典: aicrm_guarantee_method，mortgage=抵押）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择担保类型（字典: aicrm_guarantee_method，mortgage=抵押）',
      },
    },
    {
      fieldName: 'mortgagorName',
      label: '抵押人姓名/名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入抵押人姓名/名称',
      },
    },
    {
      fieldName: 'mortgagorType',
      label: '抵押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择抵押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
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
      fieldName: 'managementBranchId',
      label: '押品管理机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入押品管理机构ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'managementBranchName',
      label: '押品管理机构名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入押品管理机构名称',
      },
    },
    {
      fieldName: 'mortgagorIdType',
      label: '抵押人证件类型（字典: aicrm_identity_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择抵押人证件类型（字典: aicrm_identity_type）',
      },
    },
    {
      fieldName: 'mortgagorIdNo',
      label: '抵押人证件号码（加密）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入抵押人证件号码（加密）',
      },
    },
    {
      fieldName: 'collateralAddress',
      label: '抵押物地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入抵押物地址',
      },
    },
    {
      fieldName: 'collateralArea',
      label: '抵押物面积（平方米）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入抵押物面积（平方米）',
      },
    },
    {
      fieldName: 'evaluationValue',
      label: '评估价值（万元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入评估价值（万元）',
      },
    },
    {
      fieldName: 'evaluationDate',
      label: '评估日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'evaluationAgency',
      label: '评估机构',
      component: 'Input',
      componentProps: {
        placeholder: '请输入评估机构',
      },
    },
    {
      fieldName: 'mortgageRate',
      label: '抵押率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入抵押率（%）',
      },
    },
    {
      fieldName: 'mortgageStatus',
      label: '抵押状态（字典: aicrm_mortgage_status，effective=有效，released=已解押，invalid=无效）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'mortgageDate',
      label: '抵押登记日期',
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
      fieldName: 'collateralName',
      label: '押品名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入押品名称',
      },
    },
    {
      fieldName: 'collateralType',
      label: '押品类型（字典: aicrm_mortgage_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择押品类型（字典: aicrm_mortgage_type）',
      },
    },
    {
      fieldName: 'certificateNo',
      label: '权证号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入权证号',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保类型（字典: aicrm_guarantee_method，mortgage=抵押）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择担保类型（字典: aicrm_guarantee_method，mortgage=抵押）',
      },
    },
    {
      fieldName: 'mortgagorName',
      label: '抵押人姓名/名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入抵押人姓名/名称',
      },
    },
    {
      fieldName: 'mortgagorType',
      label: '抵押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择抵押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
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
      fieldName: 'managementBranchId',
      label: '押品管理机构ID（关联 system_dept.id）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入押品管理机构ID（关联 system_dept.id）',
      },
    },
    {
      fieldName: 'managementBranchName',
      label: '押品管理机构名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入押品管理机构名称',
      },
    },
    {
      fieldName: 'mortgagorIdType',
      label: '抵押人证件类型（字典: aicrm_identity_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择抵押人证件类型（字典: aicrm_identity_type）',
      },
    },
    {
      fieldName: 'mortgagorIdNo',
      label: '抵押人证件号码（加密）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入抵押人证件号码（加密）',
      },
    },
    {
      fieldName: 'collateralAddress',
      label: '抵押物地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入抵押物地址',
      },
    },
    {
      fieldName: 'collateralArea',
      label: '抵押物面积（平方米）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入抵押物面积（平方米）',
      },
    },
    {
      fieldName: 'evaluationValue',
      label: '评估价值（万元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入评估价值（万元）',
      },
    },
    {
      fieldName: 'evaluationDate',
      label: '评估日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'evaluationAgency',
      label: '评估机构',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入评估机构',
      },
    },
    {
      fieldName: 'mortgageRate',
      label: '抵押率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入抵押率（%）',
      },
    },
    {
      fieldName: 'mortgageStatus',
      label: '抵押状态（字典: aicrm_mortgage_status，effective=有效，released=已解押，invalid=无效）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择抵押状态（字典: aicrm_mortgage_status，effective=有效，released=已解押，invalid=无效）',
      },
    },
    {
      fieldName: 'mortgageDate',
      label: '抵押登记日期',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerGuaranteeMortgageApi.CustomerGuaranteeMortgage>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '抵押物主键',
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
      field: 'collateralName',
      title: '押品名称',
      minWidth: 120,
    },
    {
      field: 'collateralType',
      title: '押品类型（字典: aicrm_mortgage_type）',
      minWidth: 120,
    },
    {
      field: 'certificateNo',
      title: '权证号',
      minWidth: 120,
    },
    {
      field: 'guaranteeType',
      title: '担保类型（字典: aicrm_guarantee_method，mortgage=抵押）',
      minWidth: 120,
    },
    {
      field: 'mortgagorName',
      title: '抵押人姓名/名称',
      minWidth: 120,
    },
    {
      field: 'mortgagorType',
      title: '抵押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）',
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
      field: 'managementBranchId',
      title: '押品管理机构ID（关联 system_dept.id）',
      minWidth: 120,
    },
    {
      field: 'managementBranchName',
      title: '押品管理机构名称',
      minWidth: 120,
    },
    {
      field: 'mortgagorIdType',
      title: '抵押人证件类型（字典: aicrm_identity_type）',
      minWidth: 120,
    },
    {
      field: 'mortgagorIdNo',
      title: '抵押人证件号码（加密）',
      minWidth: 120,
    },
    {
      field: 'collateralAddress',
      title: '抵押物地址',
      minWidth: 120,
    },
    {
      field: 'collateralArea',
      title: '抵押物面积（平方米）',
      minWidth: 120,
    },
    {
      field: 'evaluationValue',
      title: '评估价值（万元）',
      minWidth: 120,
    },
    {
      field: 'evaluationDate',
      title: '评估日期',
      minWidth: 120,
    },
    {
      field: 'evaluationAgency',
      title: '评估机构',
      minWidth: 120,
    },
    {
      field: 'mortgageRate',
      title: '抵押率（%）',
      minWidth: 120,
    },
    {
      field: 'mortgageStatus',
      title: '抵押状态（字典: aicrm_mortgage_status，effective=有效，released=已解押，invalid=无效）',
      minWidth: 120,
    },
    {
      field: 'mortgageDate',
      title: '抵押登记日期',
      minWidth: 120,
    },
    {
      field: 'releaseDate',
      title: '解押日期',
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