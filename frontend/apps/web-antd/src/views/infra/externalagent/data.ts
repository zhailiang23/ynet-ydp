import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { InfraExternalAgentApi } from '#/api/infra/externalagent';

import { DICT_TYPE } from '@vben/constants';
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
      fieldName: 'code',
      label: '智能体编码',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入智能体编码',
      },
    },
    {
      fieldName: 'name',
      label: '智能体名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入智能体名称',
      },
    },
    {
      fieldName: 'description',
      label: '智能体描述',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入智能体描述',
      },
    },
    {
      fieldName: 'platformType',
      label: '平台类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: getDictOptions(DICT_TYPE.AI_AGENT_PLATFORM_TYPE, 'string'),
        placeholder: '请选择平台类型',
      },
    },
    {
      fieldName: 'accessUrl',
      label: '访问URL',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入访问URL',
      },
    },
    {
      fieldName: 'apiKey',
      label: 'API密钥',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入API密钥',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      rules: 'required',
      component: 'RadioGroup',
      defaultValue: 0,
      componentProps: {
        options: getDictOptions(DICT_TYPE.COMMON_STATUS, 'number'),
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
      fieldName: 'code',
      label: '智能体编码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入智能体编码',
      },
    },
    {
      fieldName: 'name',
      label: '智能体名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入智能体名称',
      },
    },
    {
      fieldName: 'platformType',
      label: '平台类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions(DICT_TYPE.AI_AGENT_PLATFORM_TYPE, 'string'),
        placeholder: '请选择平台类型',
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions(DICT_TYPE.COMMON_STATUS, 'number'),
        placeholder: '请选择状态',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<InfraExternalAgentApi.ExternalAgent>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'code',
      title: '智能体编码',
      minWidth: 120,
    },
    {
      field: 'name',
      title: '智能体名称',
      minWidth: 120,
    },
    {
      field: 'description',
      title: '智能体描述',
      minWidth: 120,
    },
    {
      field: 'platformType',
      title: '平台类型',
      minWidth: 120,
    },
    {
      field: 'accessUrl',
      title: '访问URL',
      minWidth: 120,
    },
    {
      field: 'apiKey',
      title: 'API密钥',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '状态',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: DICT_TYPE.COMMON_STATUS } },
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