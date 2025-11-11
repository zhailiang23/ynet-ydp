import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeVirtualCustomerApi } from '#/api/aicrm/practicevirtualcustomer';

import { getDictOptions } from '@vben/hooks';

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
      label: '性别',
      component: 'Select',
      componentProps: {
        placeholder: '请选择性别',
        options: getDictOptions('aicrm_gender'),
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
      component: 'Select',
      componentProps: {
        placeholder: '请选择职业',
        options: getDictOptions('aicrm_occupation'),
      },
    },
    {
      fieldName: 'industry',
      label: '所属行业',
      component: 'Select',
      componentProps: {
        placeholder: '请选择所属行业',
        options: getDictOptions('aicrm_industry'),
      },
    },
    {
      fieldName: 'personalityType',
      label: '性格类型',
      component: 'Select',
      componentProps: {
        placeholder: '请选择性格类型',
        options: getDictOptions('aicrm_personality_type'),
      },
    },
    {
      fieldName: 'riskPreference',
      label: '风险偏好',
      component: 'Select',
      componentProps: {
        placeholder: '请选择风险偏好',
        options: getDictOptions('aicrm_risk_preference'),
      },
    },
    {
      fieldName: 'memo',
      label: '自定义参数（JSON格式）',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入自定义参数（JSON格式）',
        rows: 4,
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
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeVirtualCustomerApi.PracticeVirtualCustomer>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '虚拟客户ID',
      minWidth: 100,
    },
    {
      field: 'name',
      title: '客户姓名',
      minWidth: 120,
    },
    {
      field: 'gender',
      title: '性别',
      minWidth: 80,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_gender' } },
    },
    {
      field: 'age',
      title: '年龄',
      minWidth: 80,
    },
    {
      field: 'occupation',
      title: '职业',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_occupation' } },
    },
    {
      field: 'industry',
      title: '所属行业',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_industry' } },
    },
    {
      field: 'personalityType',
      title: '性格类型',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_personality_type' } },
    },
    {
      field: 'riskPreference',
      title: '风险偏好',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_risk_preference' } },
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      width: 150,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}