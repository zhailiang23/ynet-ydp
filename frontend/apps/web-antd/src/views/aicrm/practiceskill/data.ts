import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeSkillApi } from '#/api/aicrm/practiceskill';

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
      label: '技巧名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入技巧名称',
      },
    },
    {
      fieldName: 'category',
      label: '技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧',
      },
    },
    {
      fieldName: 'scriptTemplate',
      label: '话术模板',
      component: 'Input',
      componentProps: {
        placeholder: '请输入话术模板',
      },
    },
    {
      fieldName: 'complianceRules',
      label: '合规规则说明',
      component: 'Input',
      componentProps: {
        placeholder: '请输入合规规则说明',
      },
    },
    {
      fieldName: 'relatedProducts',
      label: '关联产品知识',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联产品知识',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
      label: '技巧名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入技巧名称',
      },
    },
    {
      fieldName: 'category',
      label: '技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧',
      },
    },
    {
      fieldName: 'scriptTemplate',
      label: '话术模板',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入话术模板',
      },
    },
    {
      fieldName: 'complianceRules',
      label: '合规规则说明',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入合规规则说明',
      },
    },
    {
      fieldName: 'relatedProducts',
      label: '关联产品知识',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联产品知识',
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
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeSkillApi.PracticeSkill>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '技巧ID',
      minWidth: 120,
    },
    {
      field: 'name',
      title: '技巧名称',
      minWidth: 120,
    },
    {
      field: 'category',
      title: '技巧分类：字典 aicrm_skill_category 值： 1.套路. 2.技巧',
      minWidth: 120,
    },
    {
      field: 'scriptTemplate',
      title: '话术模板',
      minWidth: 120,
    },
    {
      field: 'complianceRules',
      title: '合规规则说明',
      minWidth: 120,
    },
    {
      field: 'relatedProducts',
      title: '关联产品知识',
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