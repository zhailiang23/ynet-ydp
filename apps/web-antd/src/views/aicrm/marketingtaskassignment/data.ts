import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmMarketingTaskAssignmentApi } from '#/api/aicrm/marketingtaskassignment';

import { getDictOptions } from '@vben/hooks';

import { getRangePickerDefaultProps } from '#/utils';
import { getCustomerMarketingActivitySimpleList } from '#/api/aicrm/customermarketingactivity';
import { getSimpleUserList } from '#/api/system/user';
import { getCohortList } from '#/api/aicrm/cohort';

/** 新增/修改的表单 */
export function useFormSchema(): VbenFormSchema[] {
  return [
    // 隐藏字段 - ID
    {
      fieldName: 'id',
      component: 'Input',
      dependencies: {
        triggerFields: [''],
        show: () => false,
      },
    },

    // 基本信息
    {
      fieldName: 'taskName',
      label: '任务名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入任务名称',
      },
    },
    {
      fieldName: 'marketingActivityId',
      label: '关联活动',
      rules: 'required',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择关联活动',
        api: getCustomerMarketingActivitySimpleList,
        labelField: 'activityName',
        valueField: 'id',
        showSearch: true,
      },
    },

    // 时间范围
    {
      fieldName: 'startTime',
      label: '任务开始时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'endTime',
      label: '任务结束时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },

    // 任务目标
    {
      fieldName: 'targetType',
      label: '任务目标类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: getDictOptions('aicrm_task_target_type'),
        placeholder: '请选择任务目标类型',
      },
    },
    {
      fieldName: 'targetValue',
      label: '目标值',
      rules: 'required',
      component: 'InputNumber',
      componentProps: {
        min: 1,
        placeholder: '请输入目标值',
      },
    },

    // 任务内容
    {
      fieldName: 'taskScript',
      label: '任务话术',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入任务话术',
        rows: 5,
      },
    },
    {
      fieldName: 'posterUrl',
      label: '推广海报',
      component: 'ImageUpload',
    },

    // 派发类型
    {
      fieldName: 'assignmentType',
      label: '派发类型',
      rules: 'required',
      component: 'RadioGroup',
      defaultValue: 'customer',
      componentProps: {
        options: [
          { label: '客户', value: 'customer' },
          { label: '客群', value: 'cohort' },
        ],
      },
    },

    // 派发对象 - 客户
    {
      fieldName: 'assignedUserIds',
      label: '任务派发对象',
      component: 'ApiSelect',
      dependencies: {
        triggerFields: ['assignmentType'],
        show: (values) => values.assignmentType === 'customer',
        rules: (values) => (values.assignmentType === 'customer' ? 'required' : ''),
      },
      componentProps: {
        placeholder: '请选择任务派发对象（客户）',
        api: getSimpleUserList,
        labelField: 'nickname',
        valueField: 'id',
        mode: 'multiple',
        showSearch: true,
      },
    },

    // 派发对象 - 客群
    {
      fieldName: 'assignedCohortIds',
      label: '任务派发对象',
      component: 'ApiSelect',
      dependencies: {
        triggerFields: ['assignmentType'],
        show: (values) => values.assignmentType === 'cohort',
        rules: (values) => (values.assignmentType === 'cohort' ? 'required' : ''),
      },
      componentProps: {
        placeholder: '请选择任务派发对象（客群）',
        api: async () => {
          const cohorts = await getCohortList({ type: 0, pageNo: 1, pageSize: 100 });
          return cohorts.map((cohort) => ({
            label: cohort.labelName,
            value: cohort.labelId,
          }));
        },
        mode: 'multiple',
        showSearch: true,
      },
    },

    // 任务状态 - 隐藏字段，后端默认设置为 active
    {
      fieldName: 'status',
      component: 'Input',
      dependencies: {
        triggerFields: [''],
        show: () => false,
      },
    },

    // 备注
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入备注',
        rows: 3,
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'taskName',
      label: '任务名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入任务名称',
      },
    },
    {
      fieldName: 'marketingActivityId',
      label: '关联活动',
      component: 'ApiSelect',
      componentProps: {
        allowClear: true,
        placeholder: '请选择关联活动',
        api: getCustomerMarketingActivitySimpleList,
        labelField: 'activityName',
        valueField: 'id',
        showSearch: true,
      },
    },
    {
      fieldName: 'targetType',
      label: '任务目标类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_task_target_type'),
        placeholder: '请选择任务目标类型',
      },
    },
    {
      fieldName: 'status',
      label: '任务状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_task_assignment_status'),
        placeholder: '请选择任务状态',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmMarketingTaskAssignmentApi.MarketingTaskAssignment>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'taskName',
      title: '任务名称',
      minWidth: 200,
    },
    {
      field: 'marketingActivityName',
      title: '关联活动',
      minWidth: 200,
    },
    {
      field: 'startTime',
      title: '开始时间',
      minWidth: 150,
      formatter: 'formatDateTime',
    },
    {
      field: 'endTime',
      title: '结束时间',
      minWidth: 150,
      formatter: 'formatDateTime',
    },
    {
      field: 'targetType',
      title: '目标类型',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_task_target_type' } },
    },
    {
      field: 'targetValue',
      title: '目标值',
      minWidth: 100,
    },
    {
      field: 'assignmentType',
      title: '派发类型',
      minWidth: 100,
      slots: { default: 'assignmentType' },
    },
    {
      field: 'assignedUserCount',
      title: '派发数量',
      minWidth: 120,
      slots: { default: 'assignedCount' },
    },
    {
      field: 'status',
      title: '任务状态',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_task_assignment_status' } },
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 150,
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
