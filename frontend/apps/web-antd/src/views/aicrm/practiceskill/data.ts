import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeSkillApi } from '#/api/aicrm/practiceskill';

import { computed } from 'vue';

import { getDictOptions } from '@vben/hooks';

import { getPracticeMaterialPage } from '#/api/aicrm/practicematerial';
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
      label: '技巧分类',
      rules: 'required',
      component: 'Select',
      componentProps: {
        placeholder: '请选择技巧分类',
        options: getDictOptions('aicrm_skill_category'),
      },
    },
    {
      fieldName: 'scriptTemplate',
      label: '话术模板',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入话术模板',
        rows: 4,
      },
    },
    {
      fieldName: 'complianceRules',
      label: '合规规则',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择合规规则文件',
        allowClear: true,
        api: async () => {
          const result = await getPracticeMaterialPage({
            pageNo: 1,
            pageSize: 100,
            fileType: 'rules_and_regulations',
          });
          return result.list.map((item: any) => ({
            label: item.name,
            value: item.id,
          }));
        },
      },
    },
    {
      fieldName: 'relatedProducts',
      label: '产品知识',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择产品知识文件',
        allowClear: true,
        api: async () => {
          const result = await getPracticeMaterialPage({
            pageNo: 1,
            pageSize: 100,
            fileType: 'product_information',
          });
          return result.list.map((item: any) => ({
            label: item.name,
            value: item.id,
          }));
        },
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
      label: '技巧分类',
      component: 'Select',
      componentProps: {
        allowClear: true,
        placeholder: '请选择技巧分类',
        options: getDictOptions('aicrm_skill_category'),
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
      minWidth: 100,
    },
    {
      field: 'name',
      title: '技巧名称',
      minWidth: 200,
    },
    {
      field: 'category',
      title: '技巧分类',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_skill_category' } },
    },
    {
      field: 'complianceRulesName',
      title: '合规规则',
      minWidth: 180,
    },
    {
      field: 'relatedProductsName',
      title: '产品知识',
      minWidth: 180,
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