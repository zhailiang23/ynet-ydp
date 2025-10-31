import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerWorkInfoApi } from '#/api/aicrm/customerworkinfo';

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
      fieldName: 'employerName',
      label: '工作单位名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作单位名称',
      },
    },
    {
      fieldName: 'employerType',
      label: '单位类型（字典: aicrm_employer_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择单位类型（字典: aicrm_employer_type）',
      },
    },
    {
      fieldName: 'industry',
      label: '所属行业',
      component: 'Input',
      componentProps: {
        placeholder: '请输入所属行业',
      },
    },
    {
      fieldName: 'position',
      label: '职位/职务',
      component: 'Input',
      componentProps: {
        placeholder: '请输入职位/职务',
      },
    },
    {
      fieldName: 'positionLevel',
      label: '职级（字典: aicrm_position_level）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入职级（字典: aicrm_position_level）',
      },
    },
    {
      fieldName: 'department',
      label: '所在部门',
      component: 'Input',
      componentProps: {
        placeholder: '请输入所在部门',
      },
    },
    {
      fieldName: 'workYears',
      label: '工作年限',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作年限',
      },
    },
    {
      fieldName: 'annualIncome',
      label: '年收入（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入年收入（元）',
      },
    },
    {
      fieldName: 'monthlyIncome',
      label: '月收入（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入月收入（元）',
      },
    },
    {
      fieldName: 'hasSocialInsurance',
      label: '是否有社保',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'hasHousingFund',
      label: '是否有公积金',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'workPhone',
      label: '工作电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作电话',
      },
    },
    {
      fieldName: 'workEmail',
      label: '工作邮箱',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作邮箱',
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
      fieldName: 'employerName',
      label: '工作单位名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作单位名称',
      },
    },
    {
      fieldName: 'employerType',
      label: '单位类型（字典: aicrm_employer_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择单位类型（字典: aicrm_employer_type）',
      },
    },
    {
      fieldName: 'industry',
      label: '所属行业',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入所属行业',
      },
    },
    {
      fieldName: 'position',
      label: '职位/职务',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入职位/职务',
      },
    },
    {
      fieldName: 'positionLevel',
      label: '职级（字典: aicrm_position_level）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入职级（字典: aicrm_position_level）',
      },
    },
    {
      fieldName: 'department',
      label: '所在部门',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入所在部门',
      },
    },
    {
      fieldName: 'workYears',
      label: '工作年限',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作年限',
      },
    },
    {
      fieldName: 'annualIncome',
      label: '年收入（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入年收入（元）',
      },
    },
    {
      fieldName: 'monthlyIncome',
      label: '月收入（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入月收入（元）',
      },
    },
    {
      fieldName: 'hasSocialInsurance',
      label: '是否有社保',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否有社保',
      },
    },
    {
      fieldName: 'hasHousingFund',
      label: '是否有公积金',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否有公积金',
      },
    },
    {
      fieldName: 'workPhone',
      label: '工作电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作电话',
      },
    },
    {
      fieldName: 'workEmail',
      label: '工作邮箱',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作邮箱',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerWorkInfoApi.CustomerWorkInfo>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '工作信息主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表）',
      minWidth: 120,
    },
    {
      field: 'employerName',
      title: '工作单位名称',
      minWidth: 120,
    },
    {
      field: 'employerType',
      title: '单位类型（字典: aicrm_employer_type）',
      minWidth: 120,
    },
    {
      field: 'industry',
      title: '所属行业',
      minWidth: 120,
    },
    {
      field: 'position',
      title: '职位/职务',
      minWidth: 120,
    },
    {
      field: 'positionLevel',
      title: '职级（字典: aicrm_position_level）',
      minWidth: 120,
    },
    {
      field: 'department',
      title: '所在部门',
      minWidth: 120,
    },
    {
      field: 'workYears',
      title: '工作年限',
      minWidth: 120,
    },
    {
      field: 'annualIncome',
      title: '年收入（元）',
      minWidth: 120,
    },
    {
      field: 'monthlyIncome',
      title: '月收入（元）',
      minWidth: 120,
    },
    {
      field: 'hasSocialInsurance',
      title: '是否有社保',
      minWidth: 120,
    },
    {
      field: 'hasHousingFund',
      title: '是否有公积金',
      minWidth: 120,
    },
    {
      field: 'workPhone',
      title: '工作电话',
      minWidth: 120,
    },
    {
      field: 'workEmail',
      title: '工作邮箱',
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