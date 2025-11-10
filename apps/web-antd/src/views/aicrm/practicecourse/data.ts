import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeCourseApi } from '#/api/aicrm/practicecourse';

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
      label: '课程名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入课程名称',
      },
    },
    {
      fieldName: 'description',
      label: '课程描述',
      component: 'RichTextarea',
    },
    {
      fieldName: 'scriptId',
      label: '关联陪练剧本ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联陪练剧本ID',
      },
    },
    {
      fieldName: 'standard',
      label: '课程类型 1.标准.0.个人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入课程类型 1.标准.0.个人',
      },
    },
    {
      fieldName: 'hard',
      label: '课程复杂度 1.复杂.0.简单',
      component: 'Input',
      componentProps: {
        placeholder: '请输入课程复杂度 1.复杂.0.简单',
      },
    },
    {
      fieldName: 'status',
      label: '课程状态：字典 aicrm_course_status',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
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
    {
      fieldName: 'description',
      label: '课程描述',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入课程描述',
      },
    },
    {
      fieldName: 'scriptId',
      label: '关联陪练剧本ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联陪练剧本ID',
      },
    },
    {
      fieldName: 'standard',
      label: '课程类型 1.标准.0.个人',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入课程类型 1.标准.0.个人',
      },
    },
    {
      fieldName: 'hard',
      label: '课程复杂度 1.复杂.0.简单',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入课程复杂度 1.复杂.0.简单',
      },
    },
    {
      fieldName: 'status',
      label: '课程状态：字典 aicrm_course_status',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择课程状态：字典 aicrm_course_status',
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
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeCourseApi.PracticeCourse>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '课程ID',
      minWidth: 120,
    },
    {
      field: 'name',
      title: '课程名称',
      minWidth: 120,
    },
    {
      field: 'description',
      title: '课程描述',
      minWidth: 120,
    },
    {
      field: 'scriptId',
      title: '关联陪练剧本ID',
      minWidth: 120,
    },
    {
      field: 'standard',
      title: '课程类型 1.标准.0.个人',
      minWidth: 120,
    },
    {
      field: 'hard',
      title: '课程复杂度 1.复杂.0.简单',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '课程状态：字典 aicrm_course_status',
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