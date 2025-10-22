import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';

import { CommonStatusEnum, DICT_TYPE } from '@vben/constants';
import { getDictOptions } from '@vben/hooks';

import { z } from '#/adapter/form';

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
      fieldName: 'name',
      label: '供应商名称',
      component: 'Input',
      rules: 'required',
      componentProps: {
        placeholder: '请输入供应商名称',
      },
    },
    {
      fieldName: 'contact',
      label: '联系人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系人',
      },
    },
    {
      fieldName: 'mobile',
      label: '手机号码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入手机号码',
      },
    },
    {
      fieldName: 'telephone',
      label: '联系电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系电话',
      },
    },
    {
      fieldName: 'email',
      label: '电子邮箱',
      component: 'Input',
      componentProps: {
        placeholder: '请输入电子邮箱',
      },
    },
    {
      fieldName: 'fax',
      label: '传真',
      component: 'Input',
      componentProps: {
        placeholder: '请输入传真',
      },
    },
    {
      fieldName: 'status',
      label: '开启状态',
      component: 'RadioGroup',
      componentProps: {
        options: getDictOptions(DICT_TYPE.COMMON_STATUS, 'number'),
        buttonStyle: 'solid',
        optionType: 'button',
      },
      rules: z.number().default(CommonStatusEnum.ENABLE),
    },
    {
      fieldName: 'sort',
      label: '排序',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入排序',
      },
      rules: 'required',
    },
    {
      fieldName: 'taxNo',
      label: '纳税人识别号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入纳税人识别号',
      },
    },
    {
      fieldName: 'taxPercent',
      label: '税率(%)',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入税率',
        min: 0,
        precision: 2,
      },
    },
    {
      fieldName: 'bankName',
      label: '开户行',
      component: 'Input',
      componentProps: {
        placeholder: '请输入开户行',
      },
    },
    {
      fieldName: 'bankAccount',
      label: '开户账号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入开户账号',
      },
    },
    {
      fieldName: 'bankAddress',
      label: '开户地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入开户地址',
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
      formItemClass: 'col-span-2',
    },
  ];
}

/** 搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
      label: '供应商名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入供应商名称',
        allowClear: true,
      },
    },
    {
      fieldName: 'mobile',
      label: '手机号码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入手机号码',
        allowClear: true,
      },
    },
    {
      fieldName: 'telephone',
      label: '联系电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系电话',
        allowClear: true,
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions['columns'] {
  return [
    {
      field: 'name',
      title: '供应商名称',
      minWidth: 150,
    },
    {
      field: 'contact',
      title: '联系人',
      minWidth: 120,
    },
    {
      field: 'mobile',
      title: '手机号码',
      minWidth: 130,
    },
    {
      field: 'telephone',
      title: '联系电话',
      minWidth: 130,
    },
    {
      field: 'email',
      title: '电子邮箱',
      minWidth: 180,
    },
    {
      field: 'status',
      title: '状态',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: DICT_TYPE.COMMON_STATUS },
      },
    },
    {
      field: 'sort',
      title: '排序',
      minWidth: 80,
    },
    {
      field: 'remark',
      title: '备注',
      minWidth: 150,
      showOverflow: 'tooltip',
    },
    {
      title: '操作',
      width: 130,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}
