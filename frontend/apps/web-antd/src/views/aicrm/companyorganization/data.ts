import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyOrganizationApi } from '#/api/aicrm/companyorganization';

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
      label: '客户ID(关联crm_company_customer.customer_id)',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID(关联crm_company_customer.customer_id)',
      },
    },
    {
      fieldName: 'parentId',
      label: '父级组织ID(顶级组织为NULL)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入父级组织ID(顶级组织为NULL)',
      },
    },
    {
      fieldName: 'orgCode',
      label: '组织编码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入组织编码',
      },
    },
    {
      fieldName: 'orgName',
      label: '组织名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入组织名称',
      },
    },
    {
      fieldName: 'orgFullName',
      label: '组织全称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入组织全称',
      },
    },
    {
      fieldName: 'orgLevel',
      label: '组织层级(1-一级,2-二级,3-三级...)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入组织层级(1-一级,2-二级,3-三级...)',
      },
    },
    {
      fieldName: 'orgType',
      label: '组织类型(总部、分公司、事业部、部门等)',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择组织类型(总部、分公司、事业部、部门等)',
      },
    },
    {
      fieldName: 'orgStatus',
      label: '组织状态(active-启用,inactive-停用,dissolved-解散)',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'leaderName',
      label: '负责人姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责人姓名',
      },
    },
    {
      fieldName: 'leaderPosition',
      label: '负责人职位',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责人职位',
      },
    },
    {
      fieldName: 'leaderPhone',
      label: '负责人电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责人电话',
      },
    },
    {
      fieldName: 'leaderEmail',
      label: '负责人邮箱',
      component: 'Input',
      componentProps: {
        placeholder: '请输入负责人邮箱',
      },
    },
    {
      fieldName: 'employeeCount',
      label: '员工人数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入员工人数',
      },
    },
    {
      fieldName: 'establishedDate',
      label: '成立日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'contactPhone',
      label: '联系电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系电话',
      },
    },
    {
      fieldName: 'contactEmail',
      label: '联系邮箱',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系邮箱',
      },
    },
    {
      fieldName: 'contactAddress',
      label: '办公地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入办公地址',
      },
    },
    {
      fieldName: 'businessScope',
      label: '业务范围',
      component: 'Input',
      componentProps: {
        placeholder: '请输入业务范围',
      },
    },
    {
      fieldName: 'remark',
      label: '备注说明',
      component: 'Input',
      componentProps: {
        placeholder: '请输入备注说明',
      },
    },
    {
      fieldName: 'sortOrder',
      label: '排序号(同级组织排序)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入排序号(同级组织排序)',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID(关联crm_company_customer.customer_id)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID(关联crm_company_customer.customer_id)',
      },
    },
    {
      fieldName: 'parentId',
      label: '父级组织ID(顶级组织为NULL)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入父级组织ID(顶级组织为NULL)',
      },
    },
    {
      fieldName: 'orgCode',
      label: '组织编码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入组织编码',
      },
    },
    {
      fieldName: 'orgName',
      label: '组织名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入组织名称',
      },
    },
    {
      fieldName: 'orgFullName',
      label: '组织全称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入组织全称',
      },
    },
    {
      fieldName: 'orgLevel',
      label: '组织层级(1-一级,2-二级,3-三级...)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入组织层级(1-一级,2-二级,3-三级...)',
      },
    },
    {
      fieldName: 'orgType',
      label: '组织类型(总部、分公司、事业部、部门等)',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择组织类型(总部、分公司、事业部、部门等)',
      },
    },
    {
      fieldName: 'orgStatus',
      label: '组织状态(active-启用,inactive-停用,dissolved-解散)',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择组织状态(active-启用,inactive-停用,dissolved-解散)',
      },
    },
    {
      fieldName: 'leaderName',
      label: '负责人姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责人姓名',
      },
    },
    {
      fieldName: 'leaderPosition',
      label: '负责人职位',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责人职位',
      },
    },
    {
      fieldName: 'leaderPhone',
      label: '负责人电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责人电话',
      },
    },
    {
      fieldName: 'leaderEmail',
      label: '负责人邮箱',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入负责人邮箱',
      },
    },
    {
      fieldName: 'employeeCount',
      label: '员工人数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入员工人数',
      },
    },
    {
      fieldName: 'establishedDate',
      label: '成立日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'contactPhone',
      label: '联系电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系电话',
      },
    },
    {
      fieldName: 'contactEmail',
      label: '联系邮箱',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系邮箱',
      },
    },
    {
      fieldName: 'contactAddress',
      label: '办公地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入办公地址',
      },
    },
    {
      fieldName: 'businessScope',
      label: '业务范围',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入业务范围',
      },
    },
    {
      fieldName: 'remark',
      label: '备注说明',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入备注说明',
      },
    },
    {
      fieldName: 'sortOrder',
      label: '排序号(同级组织排序)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入排序号(同级组织排序)',
      },
    },
    {
      fieldName: 'createTime',
      label: '创建时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCompanyOrganizationApi.CompanyOrganization>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '主键ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID(关联crm_company_customer.customer_id)',
      minWidth: 120,
    },
    {
      field: 'parentId',
      title: '父级组织ID(顶级组织为NULL)',
      minWidth: 120,
    },
    {
      field: 'orgCode',
      title: '组织编码',
      minWidth: 120,
    },
    {
      field: 'orgName',
      title: '组织名称',
      minWidth: 120,
    },
    {
      field: 'orgFullName',
      title: '组织全称',
      minWidth: 120,
    },
    {
      field: 'orgLevel',
      title: '组织层级(1-一级,2-二级,3-三级...)',
      minWidth: 120,
    },
    {
      field: 'orgType',
      title: '组织类型(总部、分公司、事业部、部门等)',
      minWidth: 120,
    },
    {
      field: 'orgStatus',
      title: '组织状态(active-启用,inactive-停用,dissolved-解散)',
      minWidth: 120,
    },
    {
      field: 'leaderName',
      title: '负责人姓名',
      minWidth: 120,
    },
    {
      field: 'leaderPosition',
      title: '负责人职位',
      minWidth: 120,
    },
    {
      field: 'leaderPhone',
      title: '负责人电话',
      minWidth: 120,
    },
    {
      field: 'leaderEmail',
      title: '负责人邮箱',
      minWidth: 120,
    },
    {
      field: 'employeeCount',
      title: '员工人数',
      minWidth: 120,
    },
    {
      field: 'establishedDate',
      title: '成立日期',
      minWidth: 120,
    },
    {
      field: 'contactPhone',
      title: '联系电话',
      minWidth: 120,
    },
    {
      field: 'contactEmail',
      title: '联系邮箱',
      minWidth: 120,
    },
    {
      field: 'contactAddress',
      title: '办公地址',
      minWidth: 120,
    },
    {
      field: 'businessScope',
      title: '业务范围',
      minWidth: 120,
    },
    {
      field: 'remark',
      title: '备注说明',
      minWidth: 120,
    },
    {
      field: 'sortOrder',
      title: '排序号(同级组织排序)',
      minWidth: 120,
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