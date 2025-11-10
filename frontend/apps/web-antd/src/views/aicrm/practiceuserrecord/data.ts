import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeUserRecordApi } from '#/api/aicrm/practiceuserrecord';

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
      fieldName: 'courseId',
      label: '关联陪练课程ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联陪练课程ID',
      },
    },
    {
      fieldName: 'userId',
      label: '参与用户ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入参与用户ID',
      },
    },
    {
      fieldName: 'vcustomerId',
      label: '虚拟用户ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入虚拟用户ID',
      },
    },
    {
      fieldName: 'recordNo',
      label: '记录编号（唯一）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入记录编号（唯一）',
      },
    },
    {
      fieldName: 'status',
      label: '记录状态：字典 aicrm_record_status',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'startTime',
      label: '开始时间',
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
      label: '结束时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'duration',
      label: '实际时长（分钟）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入实际时长（分钟）',
      },
    },
    {
      fieldName: 'totalScore',
      label: '总评分',
      component: 'Input',
      componentProps: {
        placeholder: '请输入总评分',
      },
    },
    {
      fieldName: 'dimensionScores',
      label: '各维度得分（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入各维度得分（JSON格式）',
      },
    },
    {
      fieldName: 'completionRate',
      label: '完成进度（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入完成进度（%）',
      },
    },
    {
      fieldName: 'aiSummary',
      label: 'AI 总结评价',
      component: 'Input',
      componentProps: {
        placeholder: '请输入AI 总结评价',
      },
    },
    {
      fieldName: 'strengths',
      label: '优点总结（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入优点总结（JSON格式）',
      },
    },
    {
      fieldName: 'weaknesses',
      label: '待改进点（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入待改进点（JSON格式）',
      },
    },
    {
      fieldName: 'recommendations',
      label: '改进建议（JSON格式）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入改进建议（JSON格式）',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'courseId',
      label: '关联陪练课程ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联陪练课程ID',
      },
    },
    {
      fieldName: 'userId',
      label: '参与用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入参与用户ID',
      },
    },
    {
      fieldName: 'vcustomerId',
      label: '虚拟用户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入虚拟用户ID',
      },
    },
    {
      fieldName: 'recordNo',
      label: '记录编号（唯一）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入记录编号（唯一）',
      },
    },
    {
      fieldName: 'status',
      label: '记录状态：字典 aicrm_record_status',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择记录状态：字典 aicrm_record_status',
      },
    },
    {
      fieldName: 'startTime',
      label: '开始时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'endTime',
      label: '结束时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'duration',
      label: '实际时长（分钟）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入实际时长（分钟）',
      },
    },
    {
      fieldName: 'totalScore',
      label: '总评分',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入总评分',
      },
    },
    {
      fieldName: 'dimensionScores',
      label: '各维度得分（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入各维度得分（JSON格式）',
      },
    },
    {
      fieldName: 'completionRate',
      label: '完成进度（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入完成进度（%）',
      },
    },
    {
      fieldName: 'aiSummary',
      label: 'AI 总结评价',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入AI 总结评价',
      },
    },
    {
      fieldName: 'strengths',
      label: '优点总结（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入优点总结（JSON格式）',
      },
    },
    {
      fieldName: 'weaknesses',
      label: '待改进点（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入待改进点（JSON格式）',
      },
    },
    {
      fieldName: 'recommendations',
      label: '改进建议（JSON格式）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入改进建议（JSON格式）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeUserRecordApi.PracticeUserRecord>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '记录ID',
      minWidth: 120,
    },
    {
      field: 'courseId',
      title: '关联陪练课程ID',
      minWidth: 120,
    },
    {
      field: 'userId',
      title: '参与用户ID',
      minWidth: 120,
    },
    {
      field: 'vcustomerId',
      title: '虚拟用户ID',
      minWidth: 120,
    },
    {
      field: 'recordNo',
      title: '记录编号（唯一）',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '记录状态：字典 aicrm_record_status',
      minWidth: 120,
    },
    {
      field: 'startTime',
      title: '开始时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'endTime',
      title: '结束时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'duration',
      title: '实际时长（分钟）',
      minWidth: 120,
    },
    {
      field: 'totalScore',
      title: '总评分',
      minWidth: 120,
    },
    {
      field: 'dimensionScores',
      title: '各维度得分（JSON格式）',
      minWidth: 120,
    },
    {
      field: 'completionRate',
      title: '完成进度（%）',
      minWidth: 120,
    },
    {
      field: 'aiSummary',
      title: 'AI 总结评价',
      minWidth: 120,
    },
    {
      field: 'strengths',
      title: '优点总结（JSON格式）',
      minWidth: 120,
    },
    {
      field: 'weaknesses',
      title: '待改进点（JSON格式）',
      minWidth: 120,
    },
    {
      field: 'recommendations',
      title: '改进建议（JSON格式）',
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