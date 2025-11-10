import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeVirtualCustomerApi } from '#/api/aicrm/practicevirtualcustomer';

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
      fieldName: 'name',
      label: '客户姓名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户姓名',
      },
    },
    {
      fieldName: 'gender',
      label: '性别：字典 aicrm_gender',
      component: 'Input',
      componentProps: {
        placeholder: '请输入性别：字典 aicrm_gender',
      },
    },
    {
      fieldName: 'age',
      label: '年龄',
      component: 'Input',
      componentProps: {
        placeholder: '请输入年龄',
      },
    },
    {
      fieldName: 'occupation',
      label: '职业',
      component: 'Input',
      componentProps: {
        placeholder: '请输入职业',
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
      fieldName: 'personalityType',
      label: '性格类型：字典 aicrm_personality_type（如理性型/感性型）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择性格类型：字典 aicrm_personality_type（如理性型/感性型）',
      },
    },
    {
      fieldName: 'riskPreference',
      label: '风险偏好',
      component: 'Input',
      componentProps: {
        placeholder: '请输入风险偏好',
      },
    },
    {
      fieldName: 'memo',
      label: '自定义参数（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入自定义参数（JSON格式）',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
      label: '客户姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户姓名',
      },
    },
    {
      fieldName: 'gender',
      label: '性别：字典 aicrm_gender',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入性别：字典 aicrm_gender',
      },
    },
    {
      fieldName: 'age',
      label: '年龄',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入年龄',
      },
    },
    {
      fieldName: 'occupation',
      label: '职业',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入职业',
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
      fieldName: 'personalityType',
      label: '性格类型：字典 aicrm_personality_type（如理性型/感性型）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择性格类型：字典 aicrm_personality_type（如理性型/感性型）',
      },
    },
    {
      fieldName: 'riskPreference',
      label: '风险偏好',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入风险偏好',
      },
    },
    {
      fieldName: 'memo',
      label: '自定义参数（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入自定义参数（JSON格式）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeVirtualCustomerApi.PracticeVirtualCustomer>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '虚拟客户ID',
      minWidth: 120,
    },
    {
      field: 'name',
      title: '客户姓名',
      minWidth: 120,
    },
    {
      field: 'gender',
      title: '性别：字典 aicrm_gender',
      minWidth: 120,
    },
    {
      field: 'age',
      title: '年龄',
      minWidth: 120,
    },
    {
      field: 'occupation',
      title: '职业',
      minWidth: 120,
    },
    {
      field: 'industry',
      title: '所属行业',
      minWidth: 120,
    },
    {
      field: 'personalityType',
      title: '性格类型：字典 aicrm_personality_type（如理性型/感性型）',
      minWidth: 120,
    },
    {
      field: 'riskPreference',
      title: '风险偏好',
      minWidth: 120,
    },
    {
      field: 'memo',
      title: '自定义参数（JSON格式）',
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