import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerApi } from '#/api/aicrm/customer';

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
      fieldName: 'customerNo',
      label: '客户编号ynet',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户编号ynet',
      },
    },
    {
      fieldName: 'customerType',
      label: '客户类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择客户类型',
      },
    },
    {
      fieldName: 'customerName',
      label: '客户名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户名称',
      },
    },
    {
      fieldName: 'customerLevel',
      label: '客户等级',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户等级',
      },
    },
    {
      fieldName: 'customerStatus',
      label: '客户状态',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'isHighQuality',
      label: '是否优质客户(0=否, 1=是)',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'isImportant',
      label: '是否重要客户(0=否, 1=是)',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'creditStatus',
      label: '信用状态(如:良好、一般、较差)',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'creditLevel',
      label: '信用等级(如:AAA、AA、A、BBB等)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信用等级(如:AAA、AA、A、BBB等)',
      },
    },
    {
      fieldName: 'creditScore',
      label: '信用评分',
      component: 'Input',
      componentProps: {
        placeholder: '请输入信用评分',
      },
    },
    {
      fieldName: 'customerSource',
      label: '客户来源(如:网点开发、电话营销、网络营销等)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户来源(如:网点开发、电话营销、网络营销等)',
      },
    },
    {
      fieldName: 'customerTag',
      label: '客户标签(多个标签用逗号分隔)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户标签(多个标签用逗号分隔)',
      },
    },
    {
      fieldName: 'remark',
      label: '备注信息',
      component: 'Input',
      componentProps: {
        placeholder: '请输入备注信息',
      },
    },
    {
      fieldName: 'deptId',
      label: '所属部门ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入所属部门ID',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerNo',
      label: '客户编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户编号',
      },
    },
    {
      fieldName: 'customerType',
      label: '客户类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择客户类型',
      },
    },
    {
      fieldName: 'customerName',
      label: '客户名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户名称',
      },
    },
    {
      fieldName: 'customerLevel',
      label: '客户等级',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户等级',
      },
    },
    {
      fieldName: 'customerStatus',
      label: '客户状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择客户状态',
      },
    },
    {
      fieldName: 'deptId',
      label: '所属部门ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入所属部门ID',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerApi.Customer>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '客户ID(主键)',
      minWidth: 120,
    },
    {
      field: 'customerNo',
      title: '客户编号ynet',
      minWidth: 120,
    },
    {
      field: 'customerType',
      title: '客户类型',
      minWidth: 120,
    },
    {
      field: 'customerName',
      title: '客户名称',
      minWidth: 120,
    },
    {
      field: 'customerLevel',
      title: '客户等级',
      minWidth: 120,
    },
    {
      field: 'customerStatus',
      title: '客户状态',
      minWidth: 120,
    },
    {
      field: 'deptId',
      title: '所属部门ID',
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