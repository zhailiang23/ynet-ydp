import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerMarketingActivityApi } from '#/api/aicrm/customermarketingactivity';

import { getDictOptions } from '@vben/hooks';

import { getRangePickerDefaultProps } from '#/utils';

/** 格式化金额 */
function formatMoney(value?: number) {
  if (value === null || value === undefined) {
    return '　';
  }
  return value.toLocaleString('zh-CN', {
    minimumFractionDigits: 2,
    maximumFractionDigits: 2,
  });
}

/** 新增/修改的表单（精简为23个核心字段） */
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

    // 第一组：基本信息（6个字段）
    {
      fieldName: 'activityName',
      label: '活动名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动名称',
      },
    },
    {
      fieldName: 'activityType',
      label: '活动类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: getDictOptions('aicrm_activity_type'),
        placeholder: '请选择活动类型',
      },
    },
    {
      fieldName: 'activityForm',
      label: '活动形式',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: getDictOptions('aicrm_activity_form'),
        placeholder: '请选择活动形式',
      },
    },
    {
      fieldName: 'activityStatus',
      label: '活动状态',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: getDictOptions('aicrm_activity_status'),
        placeholder: '请选择活动状态',
      },
    },
    {
      fieldName: 'handlerUserName',
      label: '主办方/负责人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入主办方或负责人',
      },
    },
    {
      fieldName: 'activityLocation',
      label: '活动地点',
      component: 'Input',
      componentProps: {
        placeholder: '请输入活动地点（线下活动）',
      },
    },

    // 第二组：时间管理（4个字段）
    {
      fieldName: 'activityOrderTime',
      label: '活动开始时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'activityEndTime',
      label: '活动结束时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'startDt',
      label: '报名开始时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'endDt',
      label: '报名截止时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },

    // 第三组：参与和预算（5个字段）
    {
      fieldName: 'expectedEffect',
      label: '目标受众描述',
      component: 'Input',
      componentProps: {
        placeholder: '请描述目标受众',
      },
    },
    {
      fieldName: 'targetCustomerCount',
      label: '预期参与人数',
      component: 'InputNumber',
      componentProps: {
        min: 0,
        placeholder: '请输入预期参与人数',
      },
    },
    {
      fieldName: 'actualCustomerCount',
      label: '实际参与人数',
      component: 'InputNumber',
      componentProps: {
        min: 0,
        placeholder: '请输入实际参与人数',
      },
    },
    {
      fieldName: 'activityBudget',
      label: '活动预算（元）',
      component: 'InputNumber',
      componentProps: {
        min: 0,
        precision: 2,
        placeholder: '请输入活动预算',
      },
    },
    {
      fieldName: 'activityCost',
      label: '实际成本（元）',
      component: 'InputNumber',
      componentProps: {
        min: 0,
        precision: 2,
        placeholder: '请输入实际成本',
      },
    },
    {
      fieldName: 'handlerDeptName',
      label: '成本中心/部门',
      component: 'Input',
      componentProps: {
        placeholder: '请输入成本中心或部门',
      },
    },

    // 第四组：效果评估（3个字段）
    {
      fieldName: 'activityScore',
      label: '满意度评分（1-10分）',
      component: 'InputNumber',
      componentProps: {
        min: 1,
        max: 10,
        precision: 1,
        placeholder: '请输入满意度评分',
      },
    },
    {
      fieldName: 'customerFeedback',
      label: '转化客户数',
      component: 'InputNumber',
      componentProps: {
        min: 0,
        placeholder: '请输入转化客户数',
      },
    },
    {
      fieldName: 'remark',
      label: '备注说明',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入备注说明',
        rows: 5,
      },
    },

    // 第五组：活动描述（1个字段）
    {
      fieldName: 'actualEffect',
      label: '活动描述',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入活动描述',
        rows: 5,
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'activityName',
      label: '活动名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入活动名称',
      },
    },
  ];
}

/** 列表的字段（精简为14列：13个数据列 + 1个操作列） */
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerMarketingActivityApi.CustomerMarketingActivity>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: 'ID',
      minWidth: 80,
    },
    {
      field: 'activityName',
      title: '活动名称',
      minWidth: 200,
    },
    {
      field: 'activityType',
      title: '活动类型',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_activity_type' } },
    },
    {
      field: 'activityForm',
      title: '活动形式',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_activity_form' } },
    },
    {
      field: 'activityStatus',
      title: '活动状态',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_activity_status' } },
    },
    {
      field: 'activityOrderTime',
      title: '开始时间',
      minWidth: 150,
      formatter: 'formatDateTime',
    },
    {
      field: 'activityEndTime',
      title: '结束时间',
      minWidth: 150,
      formatter: 'formatDateTime',
    },
    {
      field: 'activityBudget',
      title: '预算（元）',
      minWidth: 120,
      formatter: ({ cellValue }) => formatMoney(cellValue),
    },
    {
      field: 'activityCost',
      title: '实际成本（元）',
      minWidth: 120,
      formatter: ({ cellValue }) => formatMoney(cellValue),
    },
    {
      field: 'targetCustomerCount',
      title: '预期人数',
      minWidth: 100,
    },
    {
      field: 'actualCustomerCount',
      title: '实际人数',
      minWidth: 100,
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