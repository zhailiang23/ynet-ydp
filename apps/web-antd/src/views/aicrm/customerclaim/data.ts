import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerClaimApi } from '#/api/aicrm/customerclaim';
import type { DescriptionItemSchema } from '#/components/description';

import { h } from 'vue';

import { getDictOptions } from '@vben/hooks';
import { formatDateTime } from '@vben/utils';

import { DictTag } from '#/components/dict-tag';
import { getUnassignedCustomers } from '#/api/aicrm/customer';
import { getRangePickerDefaultProps } from '#/utils';

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
      fieldName: 'applyUserId',
      label: '申请人ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入申请人ID',
      },
    },
    {
      fieldName: 'processStatus',
      label: '审批状态',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: getDictOptions('aicrm_process_status'),
        placeholder: '请选择审批状态',
      },
    },
    {
      fieldName: 'applyDate',
      label: '申请日期',
      component: 'RangePicker',
      componentProps: getRangePickerDefaultProps(),
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerClaimApi.CustomerClaimApplication>['columns'] {
  return [
    {
      field: 'id',
      title: '申请ID',
      minWidth: 80,
      fixed: 'left',
    },
    {
      field: 'customerId',
      title: '客户ID',
      minWidth: 100,
    },
    {
      field: 'applyUserId',
      title: '申请人ID',
      minWidth: 100,
    },
    {
      field: 'applyDate',
      title: '申请日期',
      minWidth: 120,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '';
        return new Date(cellValue).toLocaleDateString('zh-CN');
      },
    },
    {
      field: 'applyReason',
      title: '申请理由',
      minWidth: 180,
      showOverflow: 'tooltip',
    },
    {
      field: 'processStatus',
      title: '审批状态',
      minWidth: 100,
      cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_process_status' },
      },
    },
    {
      field: 'processInstanceId',
      title: '流程实例ID',
      minWidth: 180,
      showOverflow: 'tooltip',
    },
    {
      field: 'approverComment',
      title: '审批意见',
      minWidth: 160,
      showOverflow: 'tooltip',
    },
    {
      field: 'approveTime',
      title: '审批时间',
      minWidth: 160,
      formatter: ({ cellValue }) => {
        if (!cellValue) return '';
        return new Date(cellValue).toLocaleString('zh-CN');
      },
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
      field: 'action',
      title: '操作',
      width: 120,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}

/** 提交认领申请表单 */
export function useApplyFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '选择客户',
      rules: 'required',
      component: 'ApiSelect',
      componentProps: {
        api: getUnassignedCustomers,
        labelField: 'customerName',
        valueField: 'id',
        placeholder: '请选择要认领的客户',
        showSearch: true,
        filterOption: (input: string, option: any) => {
          const customerName = option.customerName || '';
          const customerNo = option.customerNo || '';
          const idCardNo = option.idCardNo || '';
          const searchText = input.toLowerCase();
          return (
            customerName.toLowerCase().includes(searchText) ||
            customerNo.toLowerCase().includes(searchText) ||
            idCardNo.toLowerCase().includes(searchText)
          );
        },
        // 自定义显示格式: 客户名称 - 客户编号 (证件号码)
        optionLabelRender: (option: any) => {
          const parts = [option.customerName];
          if (option.customerNo) {
            parts.push(option.customerNo);
          }
          if (option.idCardNo) {
            parts.push(`证件:${option.idCardNo}`);
          }
          return parts.join(' - ');
        },
      },
    },
    {
      fieldName: 'applyReason',
      label: '申请理由',
      rules: 'required',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入申请理由（必填）',
        rows: 4,
        maxlength: 500,
        showCount: true,
      },
    },
  ];
}

/** 详情页 Schema */
export function useDetailFormSchema(): DescriptionItemSchema[] {
  return [
    {
      label: '客户名称',
      field: 'customerName',
    },
    {
      label: '申请理由',
      field: 'applyReason',
    },
  ];
}
