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
        options: getDictOptions('aicrm_customer_type'),
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
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_customer_level'),
        placeholder: '请选择客户等级',
      },
    },
    {
      fieldName: 'customerStatus',
      label: '客户状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_customer_status'),
        placeholder: '请选择客户状态',
      },
    },
    {
      fieldName: 'isHighQuality',
      label: '是否优质客户',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: '是', value: true },
          { label: '否', value: false },
        ],
        placeholder: '请选择',
      },
    },
    {
      fieldName: 'isImportant',
      label: '是否重要客户',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [
          { label: '是', value: true },
          { label: '否', value: false },
        ],
        placeholder: '请选择',
      },
    },
    {
      fieldName: 'creditLevel',
      label: '信用等级',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_credit_level'),
        placeholder: '请选择信用等级',
      },
    },
    {
      fieldName: 'customerSource',
      label: '客户来源',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_customer_source'),
        placeholder: '请选择客户来源',
      },
    },
    {
      fieldName: 'customerTag',
      label: '客户标签',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户标签(模糊搜索)',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerApi.Customer>['columns'] {
  return [
    {
      type: 'checkbox',
      width: 50,
      fixed: 'left',
    },
    {
      field: 'customerNo',
      title: '客户编号',
      minWidth: 140,
      fixed: 'left',
    },
    {
      field: 'customerType',
      title: '客户类型',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_customer_type' },
      },
    },
    {
      field: 'customerName',
      title: '客户名称',
      minWidth: 140,
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
      field: 'customerStatus',
      title: '客户状态',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_customer_status' },
      },
    },
    {
      field: 'isHighQuality',
      title: '优质客户',
      minWidth: 90,
      formatter: ({ cellValue }) => (cellValue ? '是' : '否'),
    },
    {
      field: 'isImportant',
      title: '重要客户',
      minWidth: 90,
      formatter: ({ cellValue }) => (cellValue ? '是' : '否'),
    },
    {
      field: 'creditLevel',
      title: '信用等级',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_credit_level' },
      },
    },
    {
      field: 'customerSource',
      title: '客户来源',
      minWidth: 120,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_customer_source' },
      },
    },
    {
      field: 'customerTag',
      title: '客户标签',
      minWidth: 140,
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
      width: 120,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}