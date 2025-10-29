import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerWorkApi } from '#/api/aicrm/customerwork';

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
      label: '客户ID',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID',
      },
    },
    {
      fieldName: 'workType',
      label: '工作类型',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择工作类型',
      },
    },
    {
      fieldName: 'employerName',
      label: '工作单位名称/经营主体名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作单位名称/经营主体名称',
      },
    },
    {
      fieldName: 'employerType',
      label: '单位性质',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择单位性质',
      },
    },
    {
      fieldName: 'industry',
      label: '所属行业',
      component: 'Input',
      componentProps: {
        placeholder: '请输入所属行业',
      },
    },
    {
      fieldName: 'position',
      label: '职位/职务',
      component: 'Input',
      componentProps: {
        placeholder: '请输入职位/职务',
      },
    },
    {
      fieldName: 'workYears',
      label: '工作年限/经营年限（单位:年）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作年限/经营年限（单位:年）',
      },
    },
    {
      fieldName: 'startDate',
      label: '入职日期/开始经营日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'endDate',
      label: '离职日期/结束经营日期（NULL表示在职）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'isCurrent',
      label: '是否当前工作',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'workAddressProvince',
      label: '工作地址-省',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作地址-省',
      },
    },
    {
      fieldName: 'workAddressCity',
      label: '工作地址-市',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作地址-市',
      },
    },
    {
      fieldName: 'workAddressDistrict',
      label: '工作地址-区',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作地址-区',
      },
    },
    {
      fieldName: 'workAddressDetail',
      label: '工作地址-详细地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作地址-详细地址',
      },
    },
    {
      fieldName: 'annualIncome',
      label: '年收入（单位:元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入年收入（单位:元）',
      },
    },
    {
      fieldName: 'monthlyIncome',
      label: '月收入（单位:元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入月收入（单位:元）',
      },
    },
    {
      fieldName: 'incomeSource',
      label: '收入来源说明',
      component: 'Input',
      componentProps: {
        placeholder: '请输入收入来源说明',
      },
    },
    {
      fieldName: 'businessScale',
      label: '经营规模',
      component: 'Input',
      componentProps: {
        placeholder: '请输入经营规模',
      },
    },
    {
      fieldName: 'businessStatus',
      label: '经营状态',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'productionCapacity',
      label: '生产能力',
      component: 'Input',
      componentProps: {
        placeholder: '请输入生产能力',
      },
    },
    {
      fieldName: 'businessLicenseNo',
      label: '营业执照号/经营许可证号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入营业执照号/经营许可证号',
      },
    },
    {
      fieldName: 'workPhone',
      label: '工作电话/单位电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入工作电话/单位电话',
      },
    },
    {
      fieldName: 'contactPerson',
      label: '单位联系人',
      component: 'Input',
      componentProps: {
        placeholder: '请输入单位联系人',
      },
    },
    {
      fieldName: 'contactPhone',
      label: '联系人电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系人电话',
      },
    },
    {
      fieldName: 'verificationStatus',
      label: '核验状态',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'verificationTime',
      label: '核验时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'verificationRemark',
      label: '核验备注',
      component: 'Input',
      componentProps: {
        placeholder: '请输入核验备注',
      },
    },
    {
      fieldName: 'attachmentUrls',
      label: '附件URL列表（JSON格式，如营业执照、工作证明等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入附件URL列表（JSON格式，如营业执照、工作证明等）',
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
      fieldName: 'extraData',
      label: '扩展数据',
      component: 'Input',
      componentProps: {
        placeholder: '请输入扩展数据',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID',
      },
    },
    {
      fieldName: 'workType',
      label: '工作类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择工作类型',
      },
    },
    {
      fieldName: 'employerName',
      label: '工作单位名称/经营主体名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作单位名称/经营主体名称',
      },
    },
    {
      fieldName: 'employerType',
      label: '单位性质',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择单位性质',
      },
    },
    {
      fieldName: 'industry',
      label: '所属行业',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入所属行业',
      },
    },
    {
      fieldName: 'position',
      label: '职位/职务',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入职位/职务',
      },
    },
    {
      fieldName: 'workYears',
      label: '工作年限/经营年限（单位:年）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作年限/经营年限（单位:年）',
      },
    },
    {
      fieldName: 'startDate',
      label: '入职日期/开始经营日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'endDate',
      label: '离职日期/结束经营日期（NULL表示在职）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'isCurrent',
      label: '是否当前工作',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否当前工作',
      },
    },
    {
      fieldName: 'workAddressProvince',
      label: '工作地址-省',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作地址-省',
      },
    },
    {
      fieldName: 'workAddressCity',
      label: '工作地址-市',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作地址-市',
      },
    },
    {
      fieldName: 'workAddressDistrict',
      label: '工作地址-区',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作地址-区',
      },
    },
    {
      fieldName: 'workAddressDetail',
      label: '工作地址-详细地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作地址-详细地址',
      },
    },
    {
      fieldName: 'annualIncome',
      label: '年收入（单位:元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入年收入（单位:元）',
      },
    },
    {
      fieldName: 'monthlyIncome',
      label: '月收入（单位:元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入月收入（单位:元）',
      },
    },
    {
      fieldName: 'incomeSource',
      label: '收入来源说明',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入收入来源说明',
      },
    },
    {
      fieldName: 'businessScale',
      label: '经营规模',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入经营规模',
      },
    },
    {
      fieldName: 'businessStatus',
      label: '经营状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择经营状态',
      },
    },
    {
      fieldName: 'productionCapacity',
      label: '生产能力',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入生产能力',
      },
    },
    {
      fieldName: 'businessLicenseNo',
      label: '营业执照号/经营许可证号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入营业执照号/经营许可证号',
      },
    },
    {
      fieldName: 'workPhone',
      label: '工作电话/单位电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入工作电话/单位电话',
      },
    },
    {
      fieldName: 'contactPerson',
      label: '单位联系人',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入单位联系人',
      },
    },
    {
      fieldName: 'contactPhone',
      label: '联系人电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系人电话',
      },
    },
    {
      fieldName: 'verificationStatus',
      label: '核验状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择核验状态',
      },
    },
    {
      fieldName: 'verificationTime',
      label: '核验时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'verificationRemark',
      label: '核验备注',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入核验备注',
      },
    },
    {
      fieldName: 'attachmentUrls',
      label: '附件URL列表（JSON格式，如营业执照、工作证明等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入附件URL列表（JSON格式，如营业执照、工作证明等）',
      },
    },
    {
      fieldName: 'remark',
      label: '备注信息',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入备注信息',
      },
    },
    {
      fieldName: 'extraData',
      label: '扩展数据',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入扩展数据',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerWorkApi.CustomerWork>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '工作信息主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID',
      minWidth: 120,
    },
    {
      field: 'workType',
      title: '工作类型',
      minWidth: 120,
    },
    {
      field: 'employerName',
      title: '工作单位名称/经营主体名称',
      minWidth: 120,
    },
    {
      field: 'employerType',
      title: '单位性质',
      minWidth: 120,
    },
    {
      field: 'industry',
      title: '所属行业',
      minWidth: 120,
    },
    {
      field: 'position',
      title: '职位/职务',
      minWidth: 120,
    },
    {
      field: 'workYears',
      title: '工作年限/经营年限（单位:年）',
      minWidth: 120,
    },
    {
      field: 'startDate',
      title: '入职日期/开始经营日期',
      minWidth: 120,
    },
    {
      field: 'endDate',
      title: '离职日期/结束经营日期（NULL表示在职）',
      minWidth: 120,
    },
    {
      field: 'isCurrent',
      title: '是否当前工作',
      minWidth: 120,
    },
    {
      field: 'workAddressProvince',
      title: '工作地址-省',
      minWidth: 120,
    },
    {
      field: 'workAddressCity',
      title: '工作地址-市',
      minWidth: 120,
    },
    {
      field: 'workAddressDistrict',
      title: '工作地址-区',
      minWidth: 120,
    },
    {
      field: 'workAddressDetail',
      title: '工作地址-详细地址',
      minWidth: 120,
    },
    {
      field: 'annualIncome',
      title: '年收入（单位:元）',
      minWidth: 120,
    },
    {
      field: 'monthlyIncome',
      title: '月收入（单位:元）',
      minWidth: 120,
    },
    {
      field: 'incomeSource',
      title: '收入来源说明',
      minWidth: 120,
    },
    {
      field: 'businessScale',
      title: '经营规模',
      minWidth: 120,
    },
    {
      field: 'businessStatus',
      title: '经营状态',
      minWidth: 120,
    },
    {
      field: 'productionCapacity',
      title: '生产能力',
      minWidth: 120,
    },
    {
      field: 'businessLicenseNo',
      title: '营业执照号/经营许可证号',
      minWidth: 120,
    },
    {
      field: 'workPhone',
      title: '工作电话/单位电话',
      minWidth: 120,
    },
    {
      field: 'contactPerson',
      title: '单位联系人',
      minWidth: 120,
    },
    {
      field: 'contactPhone',
      title: '联系人电话',
      minWidth: 120,
    },
    {
      field: 'verificationStatus',
      title: '核验状态',
      minWidth: 120,
    },
    {
      field: 'verificationTime',
      title: '核验时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'verificationRemark',
      title: '核验备注',
      minWidth: 120,
    },
    {
      field: 'attachmentUrls',
      title: '附件URL列表（JSON格式，如营业执照、工作证明等）',
      minWidth: 120,
    },
    {
      field: 'remark',
      title: '备注信息',
      minWidth: 120,
    },
    {
      field: 'extraData',
      title: '扩展数据',
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