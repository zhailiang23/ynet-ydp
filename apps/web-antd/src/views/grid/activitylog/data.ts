import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridActivityLogApi } from '#/api/grid/activitylog';

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
      fieldName: 'customerId',
      label: '客户ID (可选)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID (可选)',
      },
    },
    {
      fieldName: 'activityType',
      label: '活动类型: VISIT/MARKETING/SURVEY/OTHER',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择活动类型: VISIT/MARKETING/SURVEY/OTHER',
      },
    },
    {
      fieldName: 'activityTitle',
      label: '活动标题',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动标题',
      },
    },
    {
      fieldName: 'activityContent',
      label: '活动内容',
      component: 'RichTextarea',
    },
    {
      fieldName: 'activityDate',
      label: '活动日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'location',
      label: '活动地点坐标',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动地点坐标',
      },
    },
    {
      fieldName: 'address',
      label: '活动地点地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动地点地址',
      },
    },
    {
      fieldName: 'staffId',
      label: '执行人ID (关联 system_users)',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入执行人ID (关联 system_users)',
      },
    },
    {
      fieldName: 'result',
      label: '活动结果: SUCCESS/FAILED/PENDING',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动结果: SUCCESS/FAILED/PENDING',
      },
    },
    {
      fieldName: 'attachments',
      label: '附件列表 [{url, name, type}]',
      component: 'Input',
      componentProps: {
        placeholder: '请输入附件列表 [{url, name, type}]',
      },
    },
    {
      fieldName: 'creatorId',
      label: '创建人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入创建人ID',
      },
    },
    {
      fieldName: 'updaterId',
      label: '更新人ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入更新人ID',
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
      fieldName: 'customerId',
      label: '客户ID (可选)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID (可选)',
      },
    },
    {
      fieldName: 'activityType',
      label: '活动类型: VISIT/MARKETING/SURVEY/OTHER',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择活动类型: VISIT/MARKETING/SURVEY/OTHER',
      },
    },
    {
      fieldName: 'activityTitle',
      label: '活动标题',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动标题',
      },
    },
    {
      fieldName: 'activityContent',
      label: '活动内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动内容',
      },
    },
    {
      fieldName: 'activityDate',
      label: '活动日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'location',
      label: '活动地点坐标',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动地点坐标',
      },
    },
    {
      fieldName: 'address',
      label: '活动地点地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动地点地址',
      },
    },
    {
      fieldName: 'staffId',
      label: '执行人ID (关联 system_users)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入执行人ID (关联 system_users)',
      },
    },
    {
      fieldName: 'result',
      label: '活动结果: SUCCESS/FAILED/PENDING',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动结果: SUCCESS/FAILED/PENDING',
      },
    },
    {
      fieldName: 'attachments',
      label: '附件列表 [{url, name, type}]',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入附件列表 [{url, name, type}]',
      },
    },
    {
      fieldName: 'creatorId',
      label: '创建人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入创建人ID',
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
    {
      fieldName: 'updaterId',
      label: '更新人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入更新人ID',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<GridActivityLogApi.ActivityLog>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '活动记录ID',
      minWidth: 120,
    },
    {
      field: 'gridId',
      title: '网格ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID (可选)',
      minWidth: 120,
    },
    {
      field: 'activityType',
      title: '活动类型: VISIT/MARKETING/SURVEY/OTHER',
      minWidth: 120,
    },
    {
      field: 'activityTitle',
      title: '活动标题',
      minWidth: 120,
    },
    {
      field: 'activityContent',
      title: '活动内容',
      minWidth: 120,
    },
    {
      field: 'activityDate',
      title: '活动日期',
      minWidth: 120,
    },
    {
      field: 'location',
      title: '活动地点坐标',
      minWidth: 120,
    },
    {
      field: 'address',
      title: '活动地点地址',
      minWidth: 120,
    },
    {
      field: 'staffId',
      title: '执行人ID (关联 system_users)',
      minWidth: 120,
    },
    {
      field: 'result',
      title: '活动结果: SUCCESS/FAILED/PENDING',
      minWidth: 120,
    },
    {
      field: 'attachments',
      title: '附件列表 [{url, name, type}]',
      minWidth: 120,
    },
    {
      field: 'creatorId',
      title: '创建人ID',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'updaterId',
      title: '更新人ID',
      minWidth: 120,
    },
    {
      title: '操作',
      width: 200,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}