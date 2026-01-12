import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPotentialCustomerApi } from '#/api/aicrm/potentialcustomer';

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
      fieldName: 'customerId',
      label: '关联客户ID',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入关联客户ID',
      },
    },
    {
      fieldName: 'customerName',
      label: '客户姓名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户姓名',
      },
    },
    {
      fieldName: 'avatar',
      label: '客户头像URL',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户头像URL',
      },
    },
    {
      fieldName: 'phone',
      label: '手机号码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入手机号码',
      },
    },
    {
      fieldName: 'idCard',
      label: '身份证号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入身份证号',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [
          { label: '激进型', value: 'AGGRESSIVE' },
          { label: '稳健型', value: 'BALANCED' },
          { label: '保守型', value: 'CONSERVATIVE' },
        ],
        placeholder: '请选择风险等级',
      },
    },
    {
      fieldName: 'customerLevel',
      label: '客户等级',
      component: 'Select',
      componentProps: {
        options: getDictOptions('aicrm_customer_level'),
        placeholder: '请选择客户等级',
      },
    },
    {
      fieldName: 'aum',
      label: '资产管理规模',
      rules: 'required',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入资产管理规模',
        min: 0,
        precision: 2,
      },
    },
    {
      fieldName: 'potentialValue',
      label: '潜在价值',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入潜在价值',
        min: 0,
        precision: 2,
      },
    },
    {
      fieldName: 'aiMatchScore',
      label: 'AI 匹配度（0-100）',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入AI匹配度',
        min: 0,
        max: 100,
      },
    },
    {
      fieldName: 'analysisConclusion',
      label: 'AI 分析结论',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入AI分析结论',
        rows: 4,
      },
    },
    {
      fieldName: 'insightId',
      label: '洞察ID',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入洞察ID',
      },
    },
    {
      fieldName: 'insightTitle',
      label: '洞察标题',
      component: 'Input',
      componentProps: {
        placeholder: '请输入洞察标题',
      },
    },
    {
      fieldName: 'status',
      label: '潜客状态',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [
          { label: '新建', value: 'NEW' },
          { label: '跟进中', value: 'FOLLOWING' },
          { label: '已转化', value: 'CONVERTED' },
          { label: '已流失', value: 'LOST' },
        ],
        placeholder: '请选择潜客状态',
      },
    },
    {
      fieldName: 'source',
      label: '来源',
      component: 'Select',
      componentProps: {
        options: [
          { label: 'AI推荐', value: 'AI_RECOMMENDATION' },
          { label: '手动添加', value: 'MANUAL' },
          { label: '导入', value: 'IMPORT' },
        ],
        placeholder: '请选择来源',
      },
    },
    {
      fieldName: 'assignedUserId',
      label: '分配客户经理ID',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入分配客户经理ID',
      },
    },
    {
      fieldName: 'lastContactTime',
      label: '最后联系时间',
      component: 'DatePicker',
      componentProps: {
        placeholder: '请选择最后联系时间',
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
      },
    },
    {
      fieldName: 'nextFollowupTime',
      label: '下次跟进时间',
      component: 'DatePicker',
      componentProps: {
        placeholder: '请选择下次跟进时间',
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
      },
    },
    {
      fieldName: 'followupCount',
      label: '跟进次数',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入跟进次数',
        min: 0,
      },
    },
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
      fieldName: 'customerName',
      label: '客户姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户姓名',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: '激进型', value: 'AGGRESSIVE' },
          { label: '稳健型', value: 'BALANCED' },
          { label: '保守型', value: 'CONSERVATIVE' },
        ],
        placeholder: '请选择风险等级',
      },
    },
    {
      fieldName: 'customerLevel',
      label: '客户等级',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_customer_level'),
        placeholder: '请选择客户等级',
      },
    },
    {
      fieldName: 'minAiMatchScore',
      label: '最小AI匹配度',
      component: 'InputNumber',
      componentProps: {
        allowClear: true,
        placeholder: '请输入最小AI匹配度',
        min: 0,
        max: 100,
      },
    },
    {
      fieldName: 'status',
      label: '潜客状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: '新建', value: 'NEW' },
          { label: '跟进中', value: 'FOLLOWING' },
          { label: '已转化', value: 'CONVERTED' },
          { label: '已流失', value: 'LOST' },
        ],
        placeholder: '请选择潜客状态',
      },
    },
    {
      fieldName: 'source',
      label: '来源',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: 'AI推荐', value: 'AI_RECOMMENDATION' },
          { label: '手动添加', value: 'MANUAL' },
          { label: '导入', value: 'IMPORT' },
        ],
        placeholder: '请选择来源',
      },
    },
    {
      fieldName: 'insightId',
      label: '洞察ID',
      component: 'InputNumber',
      componentProps: {
        allowClear: true,
        placeholder: '请输入洞察ID',
      },
    },
    {
      fieldName: 'assignedUserId',
      label: '客户经理ID',
      component: 'InputNumber',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户经理ID',
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
export function useGridColumns(): VxeTableGridOptions<AicrmPotentialCustomerApi.PotentialCustomer>['columns'] {
  return [
    {
      type: 'checkbox',
      width: 50,
      fixed: 'left',
    },
    {
      field: 'id',
      title: 'ID',
      width: 80,
      fixed: 'left',
    },
    {
      field: 'customerName',
      title: '客户姓名',
      minWidth: 120,
      fixed: 'left',
    },
    {
      field: 'phone',
      title: '手机号码',
      minWidth: 130,
    },
    {
      field: 'riskLevel',
      title: '风险等级',
      minWidth: 100,
      formatter: ({ cellValue }) => {
        const map: Record<string, string> = {
          AGGRESSIVE: '激进型',
          BALANCED: '稳健型',
          CONSERVATIVE: '保守型',
        };
        return map[cellValue] || cellValue;
      },
    },
    {
      field: 'customerLevel',
      title: '客户等级',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_customer_level' },
      },
    },
    {
      field: 'aum',
      title: '资产管理规模',
      minWidth: 140,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '-';
        return `¥${Number(cellValue).toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}`;
      },
    },
    {
      field: 'potentialValue',
      title: '潜在价值',
      minWidth: 130,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '-';
        return `¥${Number(cellValue).toLocaleString('zh-CN', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}`;
      },
    },
    {
      field: 'aiMatchScore',
      title: 'AI匹配度',
      minWidth: 110,
      formatter: ({ cellValue }) => {
        if (cellValue === null || cellValue === undefined) return '-';
        return `${cellValue}%`;
      },
    },
    {
      field: 'analysisConclusion',
      title: 'AI分析结论',
      minWidth: 200,
      showOverflow: 'tooltip',
    },
    {
      field: 'insightTitle',
      title: '洞察标题',
      minWidth: 150,
      showOverflow: 'tooltip',
    },
    {
      field: 'status',
      title: '潜客状态',
      minWidth: 100,
      formatter: ({ cellValue }) => {
        const map: Record<string, string> = {
          NEW: '新建',
          FOLLOWING: '跟进中',
          CONVERTED: '已转化',
          LOST: '已流失',
        };
        return map[cellValue] || cellValue;
      },
    },
    {
      field: 'source',
      title: '来源',
      minWidth: 100,
      formatter: ({ cellValue }) => {
        const map: Record<string, string> = {
          AI_RECOMMENDATION: 'AI推荐',
          MANUAL: '手动添加',
          IMPORT: '导入',
        };
        return map[cellValue] || cellValue;
      },
    },
    {
      field: 'assignedUserName',
      title: '客户经理',
      minWidth: 120,
    },
    {
      field: 'followupCount',
      title: '跟进次数',
      minWidth: 100,
      formatter: ({ cellValue }) => cellValue || 0,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 160,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '';
        return new Date(cellValue).toLocaleString('zh-CN');
      },
    },
    {
      title: '操作',
      width: 180,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}
