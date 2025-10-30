import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerFamilyMemberApi } from '#/api/aicrm/customerfamilymember';

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
      label: '客户ID（关联家庭信息表）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联家庭信息表）',
      },
    },
    {
      fieldName: 'memberName',
      label: '成员姓名',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入成员姓名',
      },
    },
    {
      fieldName: 'relationType',
      label: '家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）',
      },
    },
    {
      fieldName: 'gender',
      label: '性别（1-男，2-女）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入性别（1-男，2-女）',
      },
    },
    {
      fieldName: 'birthday',
      label: '生日',
      component: 'Input',
      componentProps: {
        placeholder: '请输入生日',
      },
    },
    {
      fieldName: 'age',
      label: '年龄',
      component: 'Input',
      componentProps: {
        placeholder: '请输入年龄',
      },
    },
    {
      fieldName: 'identityType',
      label: '证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）',
      },
    },
    {
      fieldName: 'identityNo',
      label: '证件号码（加密存储）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入证件号码（加密存储）',
      },
    },
    {
      fieldName: 'educationLevel',
      label: '学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）',
      },
    },
    {
      fieldName: 'company',
      label: '工作单位',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作单位',
      },
    },
    {
      fieldName: 'position',
      label: '职务',
      component: 'Input',
      componentProps: {
        placeholder: '请输入职务',
      },
    },
    {
      fieldName: 'address',
      label: '家庭地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭地址',
      },
    },
    {
      fieldName: 'mobile',
      label: '联系方式（手机号）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系方式（手机号）',
      },
    },
    {
      fieldName: 'tel',
      label: '固定电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入固定电话',
      },
    },
    {
      fieldName: 'email',
      label: '邮箱',
      component: 'Input',
      componentProps: {
        placeholder: '请输入邮箱',
      },
    },
    {
      fieldName: 'isMainMember',
      label: '是否主要成员（0-否，1-是）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Input',
      componentProps: {
        placeholder: '请输入备注',
      },
    },
    {
      fieldName: 'memberId',
      label: '成员ID（老系统）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入成员ID（老系统）',
      },
    },
    {
      fieldName: 'managerId',
      label: '客户经理ID（老系统）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户经理ID（老系统）',
      },
    },
    {
      fieldName: 'oldCustId',
      label: '老系统客户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入老系统客户ID',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID（关联家庭信息表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联家庭信息表）',
      },
    },
    {
      fieldName: 'memberName',
      label: '成员姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入成员姓名',
      },
    },
    {
      fieldName: 'relationType',
      label: '家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）',
      },
    },
    {
      fieldName: 'gender',
      label: '性别（1-男，2-女）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入性别（1-男，2-女）',
      },
    },
    {
      fieldName: 'birthday',
      label: '生日',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入生日',
      },
    },
    {
      fieldName: 'age',
      label: '年龄',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入年龄',
      },
    },
    {
      fieldName: 'identityType',
      label: '证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）',
      },
    },
    {
      fieldName: 'identityNo',
      label: '证件号码（加密存储）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入证件号码（加密存储）',
      },
    },
    {
      fieldName: 'educationLevel',
      label: '学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）',
      },
    },
    {
      fieldName: 'company',
      label: '工作单位',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作单位',
      },
    },
    {
      fieldName: 'position',
      label: '职务',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入职务',
      },
    },
    {
      fieldName: 'address',
      label: '家庭地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭地址',
      },
    },
    {
      fieldName: 'mobile',
      label: '联系方式（手机号）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系方式（手机号）',
      },
    },
    {
      fieldName: 'tel',
      label: '固定电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入固定电话',
      },
    },
    {
      fieldName: 'email',
      label: '邮箱',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入邮箱',
      },
    },
    {
      fieldName: 'isMainMember',
      label: '是否主要成员（0-否，1-是）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否主要成员（0-否，1-是）',
      },
    },
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入备注',
      },
    },
    {
      fieldName: 'memberId',
      label: '成员ID（老系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入成员ID（老系统）',
      },
    },
    {
      fieldName: 'managerId',
      label: '客户经理ID（老系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户经理ID（老系统）',
      },
    },
    {
      fieldName: 'oldCustId',
      label: '老系统客户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入老系统客户ID',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerFamilyMemberApi.CustomerFamilyMember>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '主键ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联家庭信息表）',
      minWidth: 120,
    },
    {
      field: 'memberName',
      title: '成员姓名',
      minWidth: 120,
    },
    {
      field: 'relationType',
      title: '家庭关系（字典：aicrm_family_relation，如：配偶、父亲、母亲、子女等）',
      minWidth: 120,
    },
    {
      field: 'gender',
      title: '性别（1-男，2-女）',
      minWidth: 120,
    },
    {
      field: 'birthday',
      title: '生日',
      minWidth: 120,
    },
    {
      field: 'age',
      title: '年龄',
      minWidth: 120,
    },
    {
      field: 'identityType',
      title: '证件类型（字典：aicrm_identity_type，如：居民身份证、护照等）',
      minWidth: 120,
    },
    {
      field: 'identityNo',
      title: '证件号码（加密存储）',
      minWidth: 120,
    },
    {
      field: 'educationLevel',
      title: '学历（字典：aicrm_education_level，如：大学本科、大专、研究生等）',
      minWidth: 120,
    },
    {
      field: 'company',
      title: '工作单位',
      minWidth: 120,
    },
    {
      field: 'position',
      title: '职务',
      minWidth: 120,
    },
    {
      field: 'address',
      title: '家庭地址',
      minWidth: 120,
    },
    {
      field: 'mobile',
      title: '联系方式（手机号）',
      minWidth: 120,
    },
    {
      field: 'tel',
      title: '固定电话',
      minWidth: 120,
    },
    {
      field: 'email',
      title: '邮箱',
      minWidth: 120,
    },
    {
      field: 'isMainMember',
      title: '是否主要成员（0-否，1-是）',
      minWidth: 120,
    },
    {
      field: 'remark',
      title: '备注',
      minWidth: 120,
    },
    {
      field: 'memberId',
      title: '成员ID（老系统）',
      minWidth: 120,
    },
    {
      field: 'managerId',
      title: '客户经理ID（老系统）',
      minWidth: 120,
    },
    {
      field: 'oldCustId',
      title: '老系统客户ID',
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