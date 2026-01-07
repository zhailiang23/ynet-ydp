import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmTaskApi } from '#/api/aicrm/task';

import { getDictOptions } from '@vben/hooks';

import { getSimpleCustomerList } from '#/api/aicrm/customer';
import { getSimpleUserList } from '#/api/system/user';
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
      fieldName: 'taskType',
      label: '任务类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: getDictOptions('aicrm_task_type'),
        placeholder: '请选择任务类型',
      },
    },
    {
      fieldName: 'title',
      label: '任务标题',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入任务标题',
      },
    },
    {
      fieldName: 'description',
      label: '任务描述',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入任务描述',
        rows: 3,
      },
    },
    {
      fieldName: 'priority',
      label: '优先级',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: getDictOptions('aicrm_task_priority'),
        placeholder: '请选择优先级',
      },
    },
    {
      fieldName: 'comprehensiveScore',
      label: '综合评分',
      rules: 'required',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入综合评分（0-100）',
        min: 0,
        max: 100,
        precision: 2,
      },
    },
    {
      fieldName: 'category',
      label: '任务分类',
      component: 'Input',
      componentProps: {
        placeholder: '请输入任务分类',
      },
    },
    {
      fieldName: 'businessValue',
      label: '业务价值',
      component: 'Select',
      componentProps: {
        options: getDictOptions('aicrm_task_business_value'),
        placeholder: '请选择业务价值',
      },
    },
    {
      fieldName: 'customerId',
      label: '关联客户',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择关联客户',
        api: getSimpleCustomerList,
        labelField: 'customerName',
        valueField: 'id',
        immediate: true,
        showSearch: true,
      },
    },
    {
      fieldName: 'customerName',
      label: '客户姓名',
      component: 'Input',
      dependencies: {
        triggerFields: ['customerId'],
        // 隐藏该字段
        show: () => false,
      },
      componentProps: {
        placeholder: '客户姓名（自动填充）',
      },
    },
    {
      fieldName: 'responsibleUserId',
      label: '任务负责人',
      rules: 'required',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择任务负责人',
        api: getSimpleUserList,
        labelField: 'nickname',
        valueField: 'id',
        immediate: true,
      },
    },
    {
      fieldName: 'deadline',
      label: '截止时间',
      component: 'DatePicker',
      componentProps: {
        placeholder: '请选择截止时间',
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x', // 使用时间戳格式（毫秒）
      },
    },
    {
      fieldName: 'expectedRevenue',
      label: '预期收益',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入预期收益',
        min: 0,
        precision: 2,
      },
    },
    {
      fieldName: 'status',
      label: '任务状态',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: getDictOptions('aicrm_task_status').map((opt) => ({
          ...opt,
          value: Number(opt.value),
        })),
        placeholder: '请选择任务状态',
      },
    },
    {
      fieldName: 'aiGenerated',
      label: '是否AI生成',
      component: 'Select',
      componentProps: {
        options: getDictOptions('infra_boolean_string').map((opt) => ({
          ...opt,
          value: Number(opt.value),
        })),
        placeholder: '请选择是否AI生成',
      },
    },
    {
      fieldName: 'aiReason',
      label: 'AI生成理由',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入AI生成理由',
        rows: 2,
      },
    },
    {
      fieldName: 'aiSuggestion',
      label: 'AI建议方案',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入AI建议方案',
        rows: 3,
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'title',
      label: '任务标题',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入任务标题',
      },
    },
    {
      fieldName: 'taskType',
      label: '任务类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_task_type'),
        placeholder: '请选择任务类型',
      },
    },
    {
      fieldName: 'priority',
      label: '优先级',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_task_priority'),
        placeholder: '请选择优先级',
      },
    },
    {
      fieldName: 'status',
      label: '任务状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_task_status'),
        placeholder: '请选择任务状态',
      },
    },
    {
      fieldName: 'category',
      label: '任务分类',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入任务分类',
      },
    },
    {
      fieldName: 'businessValue',
      label: '业务价值',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_task_business_value'),
        placeholder: '请选择业务价值',
      },
    },
    {
      fieldName: 'responsibleUserId',
      label: '任务负责人',
      component: 'ApiSelect',
      componentProps: {
        allowClear: true,
        placeholder: '请选择任务负责人',
        api: getSimpleUserList,
        labelField: 'nickname',
        valueField: 'id',
        immediate: true,
      },
    },
    {
      fieldName: 'createTime',
      label: '创建时间',
      component: 'RangePicker',
      componentProps: getRangePickerDefaultProps(),
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmTaskApi.Task>['columns'] {
  return [
    {
      type: 'checkbox',
      width: 50,
      fixed: 'left',
    },
    {
      field: 'title',
      title: '任务标题',
      minWidth: 200,
      fixed: 'left',
    },
    {
      field: 'taskType',
      title: '任务类型',
      minWidth: 120,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_task_type' },
      },
    },
    {
      field: 'priority',
      title: '优先级',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_task_priority' },
      },
    },
    {
      field: 'comprehensiveScore',
      title: '综合评分',
      minWidth: 100,
    },
    {
      field: 'status',
      title: '任务状态',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_task_status' },
      },
    },
    {
      field: 'category',
      title: '任务分类',
      minWidth: 120,
    },
    {
      field: 'businessValue',
      title: '业务价值',
      minWidth: 120,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_task_business_value' },
      },
    },
    {
      field: 'customerName',
      title: '关联客户',
      minWidth: 120,
    },
    {
      field: 'responsibleUserName',
      title: '任务负责人',
      minWidth: 120,
    },
    {
      field: 'deadline',
      title: '截止时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      field: 'expectedRevenue',
      title: '预期收益',
      minWidth: 120,
    },
    {
      field: 'aiGenerated',
      title: 'AI生成',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'infra_boolean_string' },
      },
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      field: 'actions',
      width: 220,
      fixed: 'right',
      slots: {
        default: 'actions',
      },
    },
  ];
}
