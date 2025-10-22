import type { VbenFormSchema } from '#/adapter/form';

import { AiModelTypeEnum } from '@vben/constants';

import { getModelSimpleList } from '#/api/ai/model/model';

export function useFormSchema(): VbenFormSchema[] {
  return [
    {
      component: 'Input',
      fieldName: 'id',
      dependencies: {
        triggerFields: [''],
        show: () => false,
      },
    },
    {
      fieldName: 'systemMessage',
      label: '角色设定',
      component: 'Textarea',
      componentProps: {
        rows: 4,
        placeholder: '请输入角色设定',
      },
    },
    {
      component: 'ApiSelect',
      fieldName: 'modelId',
      label: '模型',
      componentProps: {
        api: () => getModelSimpleList(AiModelTypeEnum.CHAT),
        labelField: 'name',
        valueField: 'id',
        allowClear: true,
        placeholder: '请选择模型',
      },
      rules: 'required',
    },
    {
      fieldName: 'temperature',
      label: '温度参数',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入温度参数',
        class: 'w-full',
        precision: 2,
        min: 0,
        max: 2,
      },
      rules: 'required',
    },
    {
      fieldName: 'maxTokens',
      label: '回复数 Token 数',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入回复数 Token 数',
        class: 'w-full',
        min: 0,
        max: 8192,
      },
      rules: 'required',
    },
    {
      fieldName: 'maxContexts',
      label: '上下文数量',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入上下文数量',
        class: 'w-full',
        min: 0,
        max: 20,
      },
      rules: 'required',
    },
  ];
}
