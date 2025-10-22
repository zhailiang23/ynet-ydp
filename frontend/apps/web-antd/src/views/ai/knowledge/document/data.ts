import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';

import { AiModelTypeEnum, CommonStatusEnum, DICT_TYPE } from '@vben/constants';
import { getDictOptions } from '@vben/hooks';

import { z } from '#/adapter/form';
import { getModelSimpleList } from '#/api/ai/model/model';
/** 新增/修改的表单 */
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
      component: 'Input',
      fieldName: 'name',
      label: '知识库名称',
      rules: 'required',
    },
    {
      fieldName: 'description',
      label: '知识库描述',
      component: 'Textarea',
      componentProps: {
        rows: 3,
        placeholder: '请输入知识库描述',
      },
    },
    {
      component: 'ApiSelect',
      fieldName: 'embeddingModelId',
      label: '向量模型',
      componentProps: {
        api: () => getModelSimpleList(AiModelTypeEnum.EMBEDDING),
        labelField: 'name',
        valueField: 'id',
        allowClear: true,
        placeholder: '请选择向量模型',
      },
      rules: 'required',
    },
    {
      fieldName: 'topK',
      label: '检索 topK',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入检索 topK',
        class: 'w-full',
        min: 0,
        max: 10,
      },
      rules: 'required',
    },
    {
      fieldName: 'similarityThreshold',
      label: '检索相似度阈值',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入检索相似度阈值',
        class: 'w-full',
        min: 0,
        max: 1,
        step: 0.01,
        precision: 2,
      },
      rules: 'required',
    },
    {
      fieldName: 'status',
      label: '是否启用',
      component: 'RadioGroup',
      componentProps: {
        options: getDictOptions(DICT_TYPE.COMMON_STATUS, 'number'),
        buttonStyle: 'solid',
        optionType: 'button',
      },
      rules: z.number().default(CommonStatusEnum.ENABLE),
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
      label: '文件名称',
      component: 'Input',
    },
    {
      fieldName: 'status',
      label: '是否启用',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions(DICT_TYPE.COMMON_STATUS, 'number'),
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions['columns'] {
  return [
    {
      field: 'id',
      title: '文档编号',
    },
    {
      field: 'name',
      title: '文件名称',
    },
    {
      field: 'contentLength',
      title: '字符数',
    },
    {
      field: 'tokens',
      title: 'Token 数',
    },
    {
      field: 'segmentMaxTokens',
      title: '分片最大 Token 数',
    },
    {
      field: 'retrievalCount',
      title: '召回次数',
    },
    {
      field: 'status',
      title: '是否启用',
      slots: { default: 'status' },
    },
    {
      field: 'createTime',
      title: '上传时间',
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      width: 150,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}
