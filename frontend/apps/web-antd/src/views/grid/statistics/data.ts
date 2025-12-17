import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridStatisticsApi } from '#/api/grid/statistics';

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
      fieldName: 'gridId',
      label: '网格ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入网格ID',
      },
    },
    {
      fieldName: 'statDate',
      label: '统计日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'statType',
      label: '统计类型: DAILY/WEEKLY/MONTHLY',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择统计类型: DAILY/WEEKLY/MONTHLY',
      },
    },
    {
      fieldName: 'customerCount',
      label: '客户总数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户总数',
      },
    },
    {
      fieldName: 'newCustomerCount',
      label: '新增客户数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入新增客户数',
      },
    },
    {
      fieldName: 'activeCustomerCount',
      label: '活跃客户数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活跃客户数',
      },
    },
    {
      fieldName: 'activityCount',
      label: '活动次数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动次数',
      },
    },
    {
      fieldName: 'loanApplicationCount',
      label: '贷款申请数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款申请数',
      },
    },
    {
      fieldName: 'loanApprovalCount',
      label: '贷款批准数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款批准数',
      },
    },
    {
      fieldName: 'loanAmount',
      label: '贷款金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款金额',
      },
    },
    {
      fieldName: 'depositAmount',
      label: '存款金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入存款金额',
      },
    },
    {
      fieldName: 'performanceScore',
      label: '绩效评分',
      component: 'Input',
      componentProps: {
        placeholder: '请输入绩效评分',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'gridId',
      label: '网格ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入网格ID',
      },
    },
    {
      fieldName: 'statDate',
      label: '统计日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'statType',
      label: '统计类型: DAILY/WEEKLY/MONTHLY',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择统计类型: DAILY/WEEKLY/MONTHLY',
      },
    },
    {
      fieldName: 'customerCount',
      label: '客户总数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户总数',
      },
    },
    {
      fieldName: 'newCustomerCount',
      label: '新增客户数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入新增客户数',
      },
    },
    {
      fieldName: 'activeCustomerCount',
      label: '活跃客户数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活跃客户数',
      },
    },
    {
      fieldName: 'activityCount',
      label: '活动次数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动次数',
      },
    },
    {
      fieldName: 'loanApplicationCount',
      label: '贷款申请数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款申请数',
      },
    },
    {
      fieldName: 'loanApprovalCount',
      label: '贷款批准数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款批准数',
      },
    },
    {
      fieldName: 'loanAmount',
      label: '贷款金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款金额',
      },
    },
    {
      fieldName: 'depositAmount',
      label: '存款金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入存款金额',
      },
    },
    {
      fieldName: 'performanceScore',
      label: '绩效评分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入绩效评分',
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
export function useGridColumns(): VxeTableGridOptions<GridStatisticsApi.Statistics>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '统计ID',
      minWidth: 120,
    },
    {
      field: 'gridId',
      title: '网格ID',
      minWidth: 120,
    },
    {
      field: 'statDate',
      title: '统计日期',
      minWidth: 120,
    },
    {
      field: 'statType',
      title: '统计类型: DAILY/WEEKLY/MONTHLY',
      minWidth: 120,
    },
    {
      field: 'customerCount',
      title: '客户总数',
      minWidth: 120,
    },
    {
      field: 'newCustomerCount',
      title: '新增客户数',
      minWidth: 120,
    },
    {
      field: 'activeCustomerCount',
      title: '活跃客户数',
      minWidth: 120,
    },
    {
      field: 'activityCount',
      title: '活动次数',
      minWidth: 120,
    },
    {
      field: 'loanApplicationCount',
      title: '贷款申请数',
      minWidth: 120,
    },
    {
      field: 'loanApprovalCount',
      title: '贷款批准数',
      minWidth: 120,
    },
    {
      field: 'loanAmount',
      title: '贷款金额',
      minWidth: 120,
    },
    {
      field: 'depositAmount',
      title: '存款金额',
      minWidth: 120,
    },
    {
      field: 'performanceScore',
      title: '绩效评分',
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