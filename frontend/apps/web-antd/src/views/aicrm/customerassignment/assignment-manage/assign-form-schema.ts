import type { VbenFormSchema } from '#/adapter/form';

/** 分配客户表单 Schema */
export function useAssignFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerIds',
      label: '客户ID',
      component: 'Input',
      dependencies: {
        triggerFields: [''],
        show: () => false,
      },
    },
    {
      fieldName: 'deptId',
      label: '归属部门ID',
      component: 'Input',
      rules: 'required',
      componentProps: {
        placeholder: '请输入归属部门ID',
      },
    },
    {
      fieldName: 'userId',
      label: '客户经理ID',
      component: 'Input',
      rules: 'required',
      componentProps: {
        placeholder: '请输入客户经理ID',
      },
    },
    {
      fieldName: 'remark',
      label: '分配备注',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入分配备注（选填）',
        rows: 3,
        maxlength: 500,
        showCount: true,
      },
    },
  ];
}
