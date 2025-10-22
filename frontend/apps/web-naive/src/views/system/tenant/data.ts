import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { SystemTenantPackageApi } from '#/api/system/tenant-package';

import { CommonStatusEnum, DICT_TYPE } from '@vben/constants';
import { getDictOptions } from '@vben/hooks';

import { z } from '#/adapter/form';
import { getTenantPackageList } from '#/api/system/tenant-package';
import { getRangePickerDefaultProps } from '#/utils';

// TODO @xingyu：这个不用 ref 么？
let tenantPackageList: SystemTenantPackageApi.TenantPackage[] = [];

async function getTenantPackageData() {
  tenantPackageList = await getTenantPackageList();
}

getTenantPackageData();

/** 新增/修改的表单 */
export function useFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'id',
      component: 'InputNumber',
      dependencies: {
        triggerFields: [''],
        show: () => false,
      },
    },
    {
      fieldName: 'name',
      label: '租户名称',
      component: 'Input',
      rules: 'required',
    },
    {
      fieldName: 'packageId',
      label: '租户套餐',
      component: 'ApiSelect',
      componentProps: {
        api: () => getTenantPackageList(),
        labelField: 'name',
        valueField: 'id',
        placeholder: '请选择租户套餐',
      },
      rules: 'required',
    },
    {
      fieldName: 'contactName',
      label: '联系人',
      component: 'Input',
      rules: 'required',
    },
    {
      fieldName: 'contactMobile',
      label: '联系手机',
      component: 'Input',
      rules: 'mobile',
    },
    {
      label: '用户名称',
      fieldName: 'username',
      component: 'Input',
      rules: 'required',
      dependencies: {
        triggerFields: ['id'],
        show: (values) => !values.id,
      },
    },
    {
      label: '用户密码',
      fieldName: 'password',
      component: 'Input',
      componentProps: {
        type: 'password',
      },
      rules: 'required',
      dependencies: {
        triggerFields: ['id'],
        show: (values) => !values.id,
      },
    },
    {
      label: '账号额度',
      fieldName: 'accountCount',
      component: 'InputNumber',
      rules: 'required',
    },
    {
      label: '过期时间',
      fieldName: 'expireTime',
      component: 'DatePicker',
      componentProps: {
        type: 'datetime',
        valueFormat: 'YYYY-MM-dd HH:mm:ss',
        placeholder: '请选择过期时间',
      },
      rules: 'required',
    },
    {
      label: '绑定域名',
      fieldName: 'websites',
      component: 'Select',
      componentProps: {
        placeholder: '请输入绑定域名',
        tag: true,
        multiple: true,
        filterable: true,
      },
    },
    {
      fieldName: 'status',
      label: '租户状态',
      component: 'RadioGroup',
      componentProps: {
        options: getDictOptions(DICT_TYPE.COMMON_STATUS, 'number'),
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
      label: '租户名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入租户名',
        clearable: true,
      },
    },
    {
      fieldName: 'contactName',
      label: '联系人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系人',
        clearable: true,
      },
    },
    {
      fieldName: 'contactMobile',
      label: '联系手机',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系手机',
        clearable: true,
      },
    },
    {
      fieldName: 'status',
      label: '状态',
      component: 'Select',
      componentProps: {
        placeholder: '请选择状态',
        clearable: true,
        options: getDictOptions(DICT_TYPE.COMMON_STATUS, 'number'),
      },
    },
    {
      fieldName: 'createTime',
      label: '创建时间',
      component: 'DatePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        clearable: true,
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '租户编号',
      minWidth: 100,
    },
    {
      field: 'name',
      title: '租户名',
      minWidth: 180,
    },
    {
      field: 'packageId',
      title: '租户套餐',
      minWidth: 180,
      formatter: ({ cellValue }) => {
        return cellValue === 0
          ? '系统租户'
          : tenantPackageList.find((pkg) => pkg.id === cellValue)?.name || '-';
      },
    },
    {
      field: 'contactName',
      title: '联系人',
      minWidth: 100,
    },
    {
      field: 'contactMobile',
      title: '联系手机',
      minWidth: 180,
    },
    {
      field: 'accountCount',
      title: '账号额度',
      minWidth: 100,
    },
    {
      field: 'expireTime',
      title: '过期时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      field: 'websites',
      title: '绑定域名',
      minWidth: 180,
    },
    {
      field: 'status',
      title: '租户状态',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: DICT_TYPE.COMMON_STATUS },
      },
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      width: 130,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}
