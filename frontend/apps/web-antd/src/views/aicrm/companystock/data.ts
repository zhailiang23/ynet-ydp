import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyStockApi } from '#/api/aicrm/companystock';

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
      label: '客户ID（关联crm_customer表）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联crm_customer表）',
      },
    },
    {
      fieldName: 'stockCode',
      label: '股票代码',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入股票代码',
      },
    },
    {
      fieldName: 'stockName',
      label: '股票简称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入股票简称',
      },
    },
    {
      fieldName: 'stockType',
      label: '股票类型（A股、B股、H股、港股、美股、科创板等）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择股票类型（A股、B股、H股、港股、美股、科创板等）',
      },
    },
    {
      fieldName: 'listingExchange',
      label: '上市交易所（上交所、深交所、港交所、纳斯达克、纽交所等）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上市交易所（上交所、深交所、港交所、纳斯达克、纽交所等）',
      },
    },
    {
      fieldName: 'listingDate',
      label: '上市日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'listingStatus',
      label: '上市状态（1=正常上市 2=暂停上市 3=终止上市 4=退市）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'issuePrice',
      label: '发行价格',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发行价格',
      },
    },
    {
      fieldName: 'issueQuantity',
      label: '发行数量（股）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发行数量（股）',
      },
    },
    {
      fieldName: 'issueAmount',
      label: '发行总额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发行总额（元）',
      },
    },
    {
      fieldName: 'issuePeRatio',
      label: '发行市盈率',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发行市盈率',
      },
    },
    {
      fieldName: 'currentPrice',
      label: '当前股价',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前股价',
      },
    },
    {
      fieldName: 'marketValue',
      label: '总市值（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入总市值（元）',
      },
    },
    {
      fieldName: 'circulatingMarketValue',
      label: '流通市值（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入流通市值（元）',
      },
    },
    {
      fieldName: 'totalShares',
      label: '总股本（股）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入总股本（股）',
      },
    },
    {
      fieldName: 'circulatingShares',
      label: '流通股本（股）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入流通股本（股）',
      },
    },
    {
      fieldName: 'industryCategory',
      label: '行业分类（申万行业、证监会行业等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入行业分类（申万行业、证监会行业等）',
      },
    },
    {
      fieldName: 'sector',
      label: '所属板块',
      component: 'Input',
      componentProps: {
        placeholder: '请输入所属板块',
      },
    },
    {
      fieldName: 'isSt',
      label: '是否ST股（0=否 1=是）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入是否ST股（0=否 1=是）',
      },
    },
    {
      fieldName: 'isStar',
      label: '是否*ST股（0=否 1=是）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入是否*ST股（0=否 1=是）',
      },
    },
    {
      fieldName: 'stockRating',
      label: '股票评级（买入、增持、中性、减持、卖出）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入股票评级（买入、增持、中性、减持、卖出）',
      },
    },
    {
      fieldName: 'priceUpdateTime',
      label: '价格更新时间',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'dataSource',
      label: '数据来源（手工录入、Wind、同花顺、东方财富等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入数据来源（手工录入、Wind、同花顺、东方财富等）',
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
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'customerId',
      label: '客户ID（关联crm_customer表）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联crm_customer表）',
      },
    },
    {
      fieldName: 'stockCode',
      label: '股票代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入股票代码',
      },
    },
    {
      fieldName: 'stockName',
      label: '股票简称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入股票简称',
      },
    },
    {
      fieldName: 'stockType',
      label: '股票类型（A股、B股、H股、港股、美股、科创板等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择股票类型（A股、B股、H股、港股、美股、科创板等）',
      },
    },
    {
      fieldName: 'listingExchange',
      label: '上市交易所（上交所、深交所、港交所、纳斯达克、纽交所等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上市交易所（上交所、深交所、港交所、纳斯达克、纽交所等）',
      },
    },
    {
      fieldName: 'listingDate',
      label: '上市日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'listingStatus',
      label: '上市状态（1=正常上市 2=暂停上市 3=终止上市 4=退市）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择上市状态（1=正常上市 2=暂停上市 3=终止上市 4=退市）',
      },
    },
    {
      fieldName: 'issuePrice',
      label: '发行价格',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发行价格',
      },
    },
    {
      fieldName: 'issueQuantity',
      label: '发行数量（股）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发行数量（股）',
      },
    },
    {
      fieldName: 'issueAmount',
      label: '发行总额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发行总额（元）',
      },
    },
    {
      fieldName: 'issuePeRatio',
      label: '发行市盈率',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发行市盈率',
      },
    },
    {
      fieldName: 'currentPrice',
      label: '当前股价',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前股价',
      },
    },
    {
      fieldName: 'marketValue',
      label: '总市值（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入总市值（元）',
      },
    },
    {
      fieldName: 'circulatingMarketValue',
      label: '流通市值（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入流通市值（元）',
      },
    },
    {
      fieldName: 'totalShares',
      label: '总股本（股）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入总股本（股）',
      },
    },
    {
      fieldName: 'circulatingShares',
      label: '流通股本（股）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入流通股本（股）',
      },
    },
    {
      fieldName: 'industryCategory',
      label: '行业分类（申万行业、证监会行业等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入行业分类（申万行业、证监会行业等）',
      },
    },
    {
      fieldName: 'sector',
      label: '所属板块',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入所属板块',
      },
    },
    {
      fieldName: 'isSt',
      label: '是否ST股（0=否 1=是）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入是否ST股（0=否 1=是）',
      },
    },
    {
      fieldName: 'isStar',
      label: '是否*ST股（0=否 1=是）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入是否*ST股（0=否 1=是）',
      },
    },
    {
      fieldName: 'stockRating',
      label: '股票评级（买入、增持、中性、减持、卖出）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入股票评级（买入、增持、中性、减持、卖出）',
      },
    },
    {
      fieldName: 'priceUpdateTime',
      label: '价格更新时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'dataSource',
      label: '数据来源（手工录入、Wind、同花顺、东方财富等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入数据来源（手工录入、Wind、同花顺、东方财富等）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCompanyStockApi.CompanyStock>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '主键ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联crm_customer表）',
      minWidth: 120,
    },
    {
      field: 'stockCode',
      title: '股票代码',
      minWidth: 120,
    },
    {
      field: 'stockName',
      title: '股票简称',
      minWidth: 120,
    },
    {
      field: 'stockType',
      title: '股票类型（A股、B股、H股、港股、美股、科创板等）',
      minWidth: 120,
    },
    {
      field: 'listingExchange',
      title: '上市交易所（上交所、深交所、港交所、纳斯达克、纽交所等）',
      minWidth: 120,
    },
    {
      field: 'listingDate',
      title: '上市日期',
      minWidth: 120,
    },
    {
      field: 'listingStatus',
      title: '上市状态（1=正常上市 2=暂停上市 3=终止上市 4=退市）',
      minWidth: 120,
    },
    {
      field: 'issuePrice',
      title: '发行价格',
      minWidth: 120,
    },
    {
      field: 'issueQuantity',
      title: '发行数量（股）',
      minWidth: 120,
    },
    {
      field: 'issueAmount',
      title: '发行总额（元）',
      minWidth: 120,
    },
    {
      field: 'issuePeRatio',
      title: '发行市盈率',
      minWidth: 120,
    },
    {
      field: 'currentPrice',
      title: '当前股价',
      minWidth: 120,
    },
    {
      field: 'marketValue',
      title: '总市值（元）',
      minWidth: 120,
    },
    {
      field: 'circulatingMarketValue',
      title: '流通市值（元）',
      minWidth: 120,
    },
    {
      field: 'totalShares',
      title: '总股本（股）',
      minWidth: 120,
    },
    {
      field: 'circulatingShares',
      title: '流通股本（股）',
      minWidth: 120,
    },
    {
      field: 'industryCategory',
      title: '行业分类（申万行业、证监会行业等）',
      minWidth: 120,
    },
    {
      field: 'sector',
      title: '所属板块',
      minWidth: 120,
    },
    {
      field: 'isSt',
      title: '是否ST股（0=否 1=是）',
      minWidth: 120,
    },
    {
      field: 'isStar',
      title: '是否*ST股（0=否 1=是）',
      minWidth: 120,
    },
    {
      field: 'stockRating',
      title: '股票评级（买入、增持、中性、减持、卖出）',
      minWidth: 120,
    },
    {
      field: 'priceUpdateTime',
      title: '价格更新时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'dataSource',
      title: '数据来源（手工录入、Wind、同花顺、东方财富等）',
      minWidth: 120,
    },
    {
      field: 'remark',
      title: '备注',
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