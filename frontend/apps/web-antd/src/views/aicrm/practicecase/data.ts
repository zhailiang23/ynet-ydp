import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeCaseApi } from '#/api/aicrm/practicecase';

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
      fieldName: 'title',
      label: '案例标题',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入案例标题',
      },
    },
    {
      fieldName: 'content',
      label: '案例详细内容（正文）',
      rules: 'required',
      component: 'RichTextarea',
    },
    {
      fieldName: 'tags',
      label: '标签（多个标签逗号分隔）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入标签（多个标签逗号分隔）',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'title',
      label: '案例标题',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入案例标题',
      },
    },
    {
      fieldName: 'tags',
      label: '标签',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入标签',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeCaseApi.PracticeCase>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '案例ID',
      minWidth: 120,
    },
    {
      field: 'title',
      title: '案例标题',
      minWidth: 200,
    },
    {
      field: 'tags',
      title: '标签',
      minWidth: 150,
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