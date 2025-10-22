import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';

import { DICT_TYPE } from '@vben/constants';
import { getDictOptions } from '@vben/hooks';
import { erpNumberFormatter, erpPriceInputFormatter } from '@vben/utils';

import { z } from '#/adapter/form';
import { getAccountSimpleList } from '#/api/erp/finance/account';
import { getProductSimpleList } from '#/api/erp/product/product';
import { getSupplierSimpleList } from '#/api/erp/purchase/supplier';
import { getWarehouseSimpleList } from '#/api/erp/stock/warehouse';
import { getSimpleUserList } from '#/api/system/user';
import { getRangePickerDefaultProps } from '#/utils';

/** 表单的配置项 */
export function useFormSchema(formType: string): VbenFormSchema[] {
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
      fieldName: 'no',
      label: '入库单号',
      component: 'Input',
      componentProps: {
        placeholder: '系统自动生成',
        disabled: true,
      },
    },
    {
      fieldName: 'inTime',
      label: '入库时间',
      component: 'DatePicker',
      componentProps: {
        disabled: formType === 'detail',
        placeholder: '选择入库时间',
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
      rules: 'required',
    },
    {
      fieldName: 'orderNo',
      label: '关联订单',
      component: 'Input',
      formItemClass: 'col-span-1',
      rules: 'required',
      componentProps: {
        placeholder: '请选择关联订单',
        disabled: formType === 'detail',
      },
    },
    {
      fieldName: 'supplierId',
      label: '供应商',
      component: 'ApiSelect',
      componentProps: {
        disabled: true,
        placeholder: '请选择供应商',
        allowClear: true,
        showSearch: true,
        api: getSupplierSimpleList,
        fieldNames: {
          label: 'name',
          value: 'id',
        },
      },
      rules: 'required',
    },
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Textarea',
      componentProps: {
        placeholder: '请输入备注',
        autoSize: { minRows: 1, maxRows: 1 },
        disabled: formType === 'detail',
      },
      formItemClass: 'col-span-2',
    },
    {
      fieldName: 'fileUrl',
      label: '附件',
      component: 'FileUpload',
      componentProps: {
        maxNumber: 1,
        maxSize: 10,
        accept: [
          'pdf',
          'doc',
          'docx',
          'xls',
          'xlsx',
          'txt',
          'jpg',
          'jpeg',
          'png',
        ],
        showDescription: formType !== 'detail',
        disabled: formType === 'detail',
      },
      formItemClass: 'col-span-3',
    },
    {
      fieldName: 'items',
      label: '入库产品清单',
      component: 'Input',
      formItemClass: 'col-span-3',
    },
    {
      fieldName: 'discountPercent',
      label: '优惠率(%)',
      component: 'InputNumber',
      componentProps: {
        placeholder: '请输入优惠率',
        min: 0,
        max: 100,
        precision: 2,
      },
      rules: z.number().min(0).optional(),
    },
    {
      fieldName: 'discountPrice',
      label: '付款优惠',
      component: 'InputNumber',
      componentProps: {
        placeholder: '付款优惠',
        precision: 2,
        formatter: erpPriceInputFormatter,
        disabled: true,
      },
    },
    {
      fieldName: 'discountedPrice',
      label: '优惠后金额',
      component: 'InputNumber',
      componentProps: {
        placeholder: '优惠后金额',
        precision: 2,
        formatter: erpPriceInputFormatter,
        disabled: true,
      },
      dependencies: {
        triggerFields: ['totalPrice', 'otherPrice'],
        componentProps: (values) => {
          const totalPrice = values.totalPrice || 0;
          const otherPrice = values.otherPrice || 0;
          values.discountedPrice = totalPrice - otherPrice;
          return {};
        },
      },
    },
    {
      fieldName: 'otherPrice',
      label: '其他费用',
      component: 'InputNumber',
      componentProps: {
        disabled: formType === 'detail',
        placeholder: '请输入其他费用',
        precision: 2,
        formatter: erpPriceInputFormatter,
      },
    },
    {
      fieldName: 'accountId',
      label: '结算账户',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择结算账户',
        allowClear: true,
        showSearch: true,
        api: getAccountSimpleList,
        fieldNames: {
          label: 'name',
          value: 'id',
        },
      },
    },
    {
      fieldName: 'totalPrice',
      label: '应付金额',
      component: 'InputNumber',
      componentProps: {
        precision: 2,
        min: 0,
        disabled: true,
      },
      rules: z.number().min(0).optional(),
    },
  ];
}

/** 表单的明细表格列 */
export function useFormItemColumns(
  formData?: any[],
): VxeTableGridOptions['columns'] {
  return [
    { type: 'seq', title: '序号', minWidth: 50, fixed: 'left' },
    {
      field: 'warehouseId',
      title: '仓库名称',
      minWidth: 200,
      slots: { default: 'warehouseId' },
    },
    {
      field: 'productId',
      title: '产品名称',
      minWidth: 200,
      slots: { default: 'productId' },
    },
    {
      field: 'stockCount',
      title: '库存',
      minWidth: 80,
    },
    {
      field: 'productBarCode',
      title: '条码',
      minWidth: 120,
    },
    {
      field: 'productUnitName',
      title: '单位',
      minWidth: 80,
    },
    {
      field: 'remark',
      title: '备注',
      minWidth: 150,
      slots: { default: 'remark' },
    },
    {
      field: 'totalCount',
      title: '原数量',
      formatter: 'formatAmount3',
      minWidth: 120,
      fixed: 'right',
      visible: formData && formData[0]?.inCount !== undefined,
    },
    {
      field: 'inCount',
      title: '已入库',
      formatter: 'formatAmount3',
      minWidth: 120,
      fixed: 'right',
      visible: formData && formData[0]?.returnCount !== undefined,
    },
    {
      field: 'count',
      title: '数量',
      minWidth: 120,
      fixed: 'right',
      slots: { default: 'count' },
    },
    {
      field: 'productPrice',
      title: '产品单价',
      fixed: 'right',
      minWidth: 120,
      slots: { default: 'productPrice' },
    },
    {
      field: 'totalProductPrice',
      fixed: 'right',
      title: '产品金额',
      minWidth: 120,
      formatter: 'formatAmount2',
    },
    {
      fixed: 'right',
      field: 'taxPercent',
      title: '税率(%)',
      minWidth: 105,
      slots: { default: 'taxPercent' },
    },
    {
      fixed: 'right',
      field: 'taxPrice',
      title: '税额',
      minWidth: 120,
      formatter: 'formatAmount2',
    },
    {
      field: 'totalPrice',
      fixed: 'right',
      title: '合计金额',
      minWidth: 120,
      formatter: 'formatAmount2',
    },
    {
      title: '操作',
      width: 50,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'no',
      label: '入库单号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入入库单号',
        allowClear: true,
      },
    },
    {
      fieldName: 'productId',
      label: '产品',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择产品',
        allowClear: true,
        showSearch: true,
        api: getProductSimpleList,
        fieldNames: {
          label: 'name',
          value: 'id',
        },
      },
    },
    {
      fieldName: 'inTime',
      label: '入库时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'supplierId',
      label: '供应商',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择供应商',
        allowClear: true,
        showSearch: true,
        api: getSupplierSimpleList,
        fieldNames: {
          label: 'name',
          value: 'id',
        },
      },
    },
    {
      fieldName: 'warehouseId',
      label: '仓库',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择仓库',
        allowClear: true,
        showSearch: true,
        api: getWarehouseSimpleList,
        labelField: 'name',
        valueField: 'id',
      },
    },
    {
      fieldName: 'creator',
      label: '创建人',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择创建人',
        allowClear: true,
        showSearch: true,
        api: getSimpleUserList,
        fieldNames: {
          label: 'nickname',
          value: 'id',
        },
      },
    },
    {
      fieldName: 'orderNo',
      label: '关联订单',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联订单号',
        allowClear: true,
      },
    },
    {
      fieldName: 'accountId',
      label: '结算账户',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择结算账户',
        allowClear: true,
        showSearch: true,
        api: getAccountSimpleList,
        fieldNames: {
          label: 'name',
          value: 'id',
        },
      },
    },
    {
      fieldName: 'paymentStatus',
      label: '付款状态',
      component: 'Select',
      componentProps: {
        options: [
          { label: '未付款', value: 0 },
          { label: '部分付款', value: 1 },
          { label: '全部付款', value: 2 },
        ],
        placeholder: '请选择付款状态',
        allowClear: true,
      },
    },
    {
      fieldName: 'status',
      label: '审批状态',
      component: 'Select',
      componentProps: {
        options: getDictOptions(DICT_TYPE.ERP_AUDIT_STATUS, 'number'),
        placeholder: '请选择审批状态',
        allowClear: true,
      },
    },
    {
      fieldName: 'remark',
      label: '备注',
      component: 'Input',
      componentProps: {
        placeholder: '请输入备注',
        allowClear: true,
      },
    },
  ];
}

/** 列表的字段 */
export function useGridColumns(): VxeTableGridOptions['columns'] {
  return [
    {
      type: 'checkbox',
      width: 50,
      fixed: 'left',
    },
    {
      field: 'no',
      title: '入库单号',
      width: 200,
      fixed: 'left',
    },
    {
      field: 'productNames',
      title: '产品信息',
      showOverflow: 'tooltip',
      minWidth: 120,
    },
    {
      field: 'supplierName',
      title: '供应商',
      minWidth: 120,
    },
    {
      field: 'inTime',
      title: '入库时间',
      width: 160,
      formatter: 'formatDate',
    },
    {
      field: 'creatorName',
      title: '创建人',
      minWidth: 120,
    },
    {
      field: 'totalCount',
      title: '总数量',
      formatter: 'formatAmount3',
      minWidth: 120,
    },
    {
      field: 'totalPrice',
      title: '应付金额',
      formatter: 'formatAmount2',
      minWidth: 120,
    },
    {
      field: 'paymentPrice',
      title: '已付金额',
      formatter: 'formatAmount2',
      minWidth: 120,
    },
    {
      field: 'unPaymentPrice',
      title: '未付金额',
      formatter: ({ row }) => {
        return `${erpNumberFormatter(row.totalPrice - row.paymentPrice, 2)}元`;
      },
      minWidth: 120,
    },
    {
      field: 'status',
      title: '审批状态',
      minWidth: 120,
      cellRender: {
        name: 'CellDict',
        props: { type: DICT_TYPE.ERP_AUDIT_STATUS },
      },
    },
    {
      title: '操作',
      width: 220,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}

/** 列表的搜索表单 */
export function useOrderGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'no',
      label: '订单单号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入订单单号',
        allowClear: true,
      },
    },
    {
      fieldName: 'productId',
      label: '产品',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择产品',
        allowClear: true,
        showSearch: true,
        api: getProductSimpleList,
        fieldNames: {
          label: 'name',
          value: 'id',
        },
      },
    },
    {
      fieldName: 'orderTime',
      label: '订单时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
  ];
}

/** 列表的字段 */
export function useOrderGridColumns(): VxeTableGridOptions['columns'] {
  return [
    {
      type: 'radio',
      width: 50,
      fixed: 'left',
    },
    {
      field: 'no',
      title: '订单单号',
      width: 200,
      fixed: 'left',
    },
    {
      field: 'productNames',
      title: '产品信息',
      showOverflow: 'tooltip',
      minWidth: 120,
    },
    {
      field: 'supplierName',
      title: '供应商',
      minWidth: 120,
    },
    {
      field: 'orderTime',
      title: '订单时间',
      width: 160,
      formatter: 'formatDate',
    },
    {
      field: 'creatorName',
      title: '创建人',
      minWidth: 120,
    },
    {
      field: 'totalCount',
      title: '总数量',
      formatter: 'formatAmount3',
      minWidth: 120,
    },
    {
      field: 'inCount',
      title: '入库数量',
      formatter: 'formatAmount3',
      minWidth: 120,
    },
    {
      field: 'totalProductPrice',
      title: '金额合计',
      formatter: 'formatAmount2',
      minWidth: 120,
    },
    {
      field: 'totalPrice',
      title: '含税金额',
      formatter: 'formatAmount2',
      minWidth: 120,
    },
  ];
}
