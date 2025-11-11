import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeCourseApi } from '#/api/aicrm/practicecourse';

import { getDictOptions } from '@vben/hooks';

import { getPracticeScriptPage } from '#/api/aicrm/practicescript';
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
      label: '课程名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入课程名称',
      },
    },
    {
      fieldName: 'scriptId',
      label: '关联剧本',
      rules: 'required',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择关联剧本',
        allowClear: true,
        api: async () => {
          const result = await getPracticeScriptPage({
            pageNo: 1,
            pageSize: 100,
          });
          return result.list.map((item: any) => ({
            label: item.name,
            value: item.id,
          }));
        },
      },
    },
    {
      fieldName: 'standard',
      label: '课程类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        placeholder: '请选择课程类型',
        options: [
          { label: '标准课程', value: 1 },
          { label: '个性化课程', value: 0 },
        ],
      },
    },
    {
      fieldName: 'hard',
      label: '课程复杂度',
      rules: 'required',
      component: 'Select',
      componentProps: {
        placeholder: '请选择课程复杂度',
        options: [
          { label: '复杂', value: 1 },
          { label: '简单', value: 0 },
        ],
      },
    },
    {
      fieldName: 'status',
      label: '课程状态',
      rules: 'required',
      component: 'Select',
      componentProps: {
        placeholder: '请选择课程状态',
        options: getDictOptions('aicrm_course_status'),
      },
    },
    {
      fieldName: 'description',
      label: '课程描述',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入课程描述',
        rows: 5,
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
      label: '课程名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入课程名称',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeCourseApi.PracticeCourse>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'name',
      title: '课程名称',
      minWidth: 200,
    },
    {
      field: 'standard',
      title: '课程类型',
      minWidth: 120,
      formatter: ({ cellValue }) => {
        return cellValue === 1 ? '标准课程' : cellValue === 0 ? '个性化课程' : '';
      },
    },
    {
      field: 'hard',
      title: '课程复杂度',
      minWidth: 120,
      formatter: ({ cellValue }) => {
        return cellValue === 1 ? '复杂' : cellValue === 0 ? '简单' : '';
      },
    },
    {
      field: 'status',
      title: '课程状态',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_course_status' } },
    },
    {
      field: 'scriptName',
      title: '关联剧本',
      minWidth: 180,
    },
    {
      field: 'scriptVersion',
      title: '剧本版本',
      minWidth: 120,
    },
    {
      title: '操作',
      width: 150,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}