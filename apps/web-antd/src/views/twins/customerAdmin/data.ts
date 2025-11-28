import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { TwinsCustomerAdminApi } from '#/api/twins/customerAdmin';

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
      fieldName: 'userId',
      label: '选择客服',
      rules: 'required',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择客服',
        api: async () => {
          // 动态导入避免循环依赖
          const { getSimpleUserList } = await import('#/api/system/user');
          const users = await getSimpleUserList();
          return users.map(user => ({
            label: user.nickname,
            value: user.id,
          }));
        },
        showSearch: true,
        filterOption: (input: string, option: any) => {
          return option.label.toLowerCase().includes(input.toLowerCase());
        },
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'username',
      label: '用户名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入用户名',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<TwinsCustomerAdminApi.CustomerAdmin>['columns'] {
  return [
    { type: 'checkbox', width: 40 },
    {
      field: 'customerId',
      title: '用户 ID',
      minWidth: 100,
    },
    {
      field: 'username',
      title: '用户名',
      minWidth: 150,
    },
    {
      field: 'createdAt',
      title: '创建时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      field: 'updatedAt',
      title: '修改时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      field: 'action',
      title: '操作',
      width: 120,
      fixed: 'right',
      slots: { default: 'action' },
    },
  ];
}