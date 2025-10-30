import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyAddressApi } from '#/api/aicrm/companyaddress';

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
      fieldName: 'addressType',
      label: '地址类型(公司地址、家庭地址、其他地址)',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择地址类型(公司地址、家庭地址、其他地址)',
      },
    },
    {
      fieldName: 'isPrimary',
      label: '是否首选项(否、是)',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'addressDetail',
      label: '详细地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入详细地址',
      },
    },
    {
      fieldName: 'postalCode',
      label: '邮编',
      component: 'Input',
      componentProps: {
        placeholder: '请输入邮编',
      },
    },
    {
      fieldName: 'sourceSystem',
      label: '来源系统(ECIF、CRM、零售CRM等)',
      component: 'Input',
      componentProps: {
        placeholder: '请输入来源系统(ECIF、CRM、零售CRM等)',
      },
    },
    {
      fieldName: 'countryOrRegion',
      label: '国家或地区',
      component: 'Input',
      componentProps: {
        placeholder: '请输入国家或地区',
      },
    },
    {
      fieldName: 'provinceCode',
      label: '省份代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入省份代码',
      },
    },
    {
      fieldName: 'cityCode',
      label: '城市代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入城市代码',
      },
    },
    {
      fieldName: 'countyCode',
      label: '区县代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入区县代码',
      },
    },
    {
      fieldName: 'townCode',
      label: '乡镇代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入乡镇代码',
      },
    },
    {
      fieldName: 'townName',
      label: '乡镇名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入乡镇名称',
      },
    },
    {
      fieldName: 'streetName',
      label: '街道名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入街道名称',
      },
    },
    {
      fieldName: 'villageNo',
      label: '村组编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入村组编号',
      },
    },
    {
      fieldName: 'villageName',
      label: '村组名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入村组名称',
      },
    },
    {
      fieldName: 'areaCode',
      label: '地区代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入地区代码',
      },
    },
    {
      fieldName: 'adminZone',
      label: '行政区域',
      component: 'Input',
      componentProps: {
        placeholder: '请输入行政区域',
      },
    },
    {
      fieldName: 'enAddress',
      label: '英文地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入英文地址',
      },
    },
    {
      fieldName: 'contactMethod',
      label: '联系方式类型',
      component: 'Input',
      componentProps: {
        placeholder: '请输入联系方式类型',
      },
    },
    {
      fieldName: 'addressLevel',
      label: '地址级别',
      component: 'Input',
      componentProps: {
        placeholder: '请输入地址级别',
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
      fieldName: 'etlDate',
      label: 'ETL导入日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'oldTxSeqNo',
      label: '老系统交易序列号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入老系统交易序列号',
      },
    },
    {
      fieldName: 'oldLastUpdateSys',
      label: '老系统最后更新系统',
      component: 'Input',
      componentProps: {
        placeholder: '请输入老系统最后更新系统',
      },
    },
    {
      fieldName: 'oldLastUpdateUser',
      label: '老系统最后更新用户',
      component: 'Input',
      componentProps: {
        placeholder: '请输入老系统最后更新用户',
      },
    },
    {
      fieldName: 'oldLastUpdateTm',
      label: '老系统最后更新时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'oldAddressId',
      label: '老系统地址ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入老系统地址ID',
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
      fieldName: 'addressType',
      label: '地址类型(公司地址、家庭地址、其他地址)',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择地址类型(公司地址、家庭地址、其他地址)',
      },
    },
    {
      fieldName: 'isPrimary',
      label: '是否首选项(否、是)',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否首选项(否、是)',
      },
    },
    {
      fieldName: 'addressDetail',
      label: '详细地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入详细地址',
      },
    },
    {
      fieldName: 'postalCode',
      label: '邮编',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入邮编',
      },
    },
    {
      fieldName: 'sourceSystem',
      label: '来源系统(ECIF、CRM、零售CRM等)',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入来源系统(ECIF、CRM、零售CRM等)',
      },
    },
    {
      fieldName: 'countryOrRegion',
      label: '国家或地区',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入国家或地区',
      },
    },
    {
      fieldName: 'provinceCode',
      label: '省份代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入省份代码',
      },
    },
    {
      fieldName: 'cityCode',
      label: '城市代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入城市代码',
      },
    },
    {
      fieldName: 'countyCode',
      label: '区县代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入区县代码',
      },
    },
    {
      fieldName: 'townCode',
      label: '乡镇代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入乡镇代码',
      },
    },
    {
      fieldName: 'townName',
      label: '乡镇名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入乡镇名称',
      },
    },
    {
      fieldName: 'streetName',
      label: '街道名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入街道名称',
      },
    },
    {
      fieldName: 'villageNo',
      label: '村组编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入村组编号',
      },
    },
    {
      fieldName: 'villageName',
      label: '村组名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入村组名称',
      },
    },
    {
      fieldName: 'areaCode',
      label: '地区代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入地区代码',
      },
    },
    {
      fieldName: 'adminZone',
      label: '行政区域',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入行政区域',
      },
    },
    {
      fieldName: 'enAddress',
      label: '英文地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入英文地址',
      },
    },
    {
      fieldName: 'contactMethod',
      label: '联系方式类型',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入联系方式类型',
      },
    },
    {
      fieldName: 'addressLevel',
      label: '地址级别',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入地址级别',
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
      fieldName: 'createTime',
      label: '创建时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'etlDate',
      label: 'ETL导入日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'oldTxSeqNo',
      label: '老系统交易序列号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入老系统交易序列号',
      },
    },
    {
      fieldName: 'oldLastUpdateSys',
      label: '老系统最后更新系统',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入老系统最后更新系统',
      },
    },
    {
      fieldName: 'oldLastUpdateUser',
      label: '老系统最后更新用户',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入老系统最后更新用户',
      },
    },
    {
      fieldName: 'oldLastUpdateTm',
      label: '老系统最后更新时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'oldAddressId',
      label: '老系统地址ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入老系统地址ID',
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCompanyAddressApi.CompanyAddress>['columns'] {
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
      field: 'addressType',
      title: '地址类型(公司地址、家庭地址、其他地址)',
      minWidth: 120,
    },
    {
      field: 'isPrimary',
      title: '是否首选项(否、是)',
      minWidth: 120,
    },
    {
      field: 'addressDetail',
      title: '详细地址',
      minWidth: 120,
    },
    {
      field: 'postalCode',
      title: '邮编',
      minWidth: 120,
    },
    {
      field: 'sourceSystem',
      title: '来源系统(ECIF、CRM、零售CRM等)',
      minWidth: 120,
    },
    {
      field: 'countryOrRegion',
      title: '国家或地区',
      minWidth: 120,
    },
    {
      field: 'provinceCode',
      title: '省份代码',
      minWidth: 120,
    },
    {
      field: 'cityCode',
      title: '城市代码',
      minWidth: 120,
    },
    {
      field: 'countyCode',
      title: '区县代码',
      minWidth: 120,
    },
    {
      field: 'townCode',
      title: '乡镇代码',
      minWidth: 120,
    },
    {
      field: 'townName',
      title: '乡镇名称',
      minWidth: 120,
    },
    {
      field: 'streetName',
      title: '街道名称',
      minWidth: 120,
    },
    {
      field: 'villageNo',
      title: '村组编号',
      minWidth: 120,
    },
    {
      field: 'villageName',
      title: '村组名称',
      minWidth: 120,
    },
    {
      field: 'areaCode',
      title: '地区代码',
      minWidth: 120,
    },
    {
      field: 'adminZone',
      title: '行政区域',
      minWidth: 120,
    },
    {
      field: 'enAddress',
      title: '英文地址',
      minWidth: 120,
    },
    {
      field: 'contactMethod',
      title: '联系方式类型',
      minWidth: 120,
    },
    {
      field: 'addressLevel',
      title: '地址级别',
      minWidth: 120,
    },
    {
      field: 'remark',
      title: '备注说明',
      minWidth: 120,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'etlDate',
      title: 'ETL导入日期',
      minWidth: 120,
    },
    {
      field: 'oldTxSeqNo',
      title: '老系统交易序列号',
      minWidth: 120,
    },
    {
      field: 'oldLastUpdateSys',
      title: '老系统最后更新系统',
      minWidth: 120,
    },
    {
      field: 'oldLastUpdateUser',
      title: '老系统最后更新用户',
      minWidth: 120,
    },
    {
      field: 'oldLastUpdateTm',
      title: '老系统最后更新时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'oldAddressId',
      title: '老系统地址ID',
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