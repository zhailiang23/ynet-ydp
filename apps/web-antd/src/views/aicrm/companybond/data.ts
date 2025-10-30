import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCompanyBondApi } from '#/api/aicrm/companybond';

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
      fieldName: 'bondCode',
      label: '债券代码',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入债券代码',
      },
    },
    {
      fieldName: 'bondName',
      label: '债券名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入债券名称',
      },
    },
    {
      fieldName: 'bondType',
      label: '债券类型（国债、地方政府债、金融债、企业债、公司债、中期票据、短期融资券、可转债等）',
      rules: 'required',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择债券类型（国债、地方政府债、金融债、企业债、公司债、中期票据、短期融资券、可转债等）',
      },
    },
    {
      fieldName: 'bondSubtype',
      label: '债券子类型（一般企业债、高收益债、绿色债券、资产证券化等）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择债券子类型（一般企业债、高收益债、绿色债券、资产证券化等）',
      },
    },
    {
      fieldName: 'issueDate',
      label: '发行日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'issueAmount',
      label: '发行金额（元）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发行金额（元）',
      },
    },
    {
      fieldName: 'issuePrice',
      label: '发行价格（面值百元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发行价格（面值百元）',
      },
    },
    {
      fieldName: 'parValue',
      label: '面值（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入面值（元）',
      },
    },
    {
      fieldName: 'issueScale',
      label: '发行规模（张）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入发行规模（张）',
      },
    },
    {
      fieldName: 'maturityDate',
      label: '到期日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'termYears',
      label: '债券期限（年）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入债券期限（年）',
      },
    },
    {
      fieldName: 'couponRate',
      label: '票面利率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入票面利率（%）',
      },
    },
    {
      fieldName: 'interestType',
      label: '计息方式（固定利率、浮动利率、零息、累进利率等）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择计息方式（固定利率、浮动利率、零息、累进利率等）',
      },
    },
    {
      fieldName: 'paymentFrequency',
      label: '付息频率（年付、半年付、季付、月付等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入付息频率（年付、半年付、季付、月付等）',
      },
    },
    {
      fieldName: 'creditRating',
      label: '债券信用评级（AAA、AA+、AA等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入债券信用评级（AAA、AA+、AA等）',
      },
    },
    {
      fieldName: 'ratingAgency',
      label: '评级机构（中诚信、联合资信、大公国际等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入评级机构（中诚信、联合资信、大公国际等）',
      },
    },
    {
      fieldName: 'ratingDate',
      label: '评级日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'underwriter',
      label: '主承销商',
      component: 'Input',
      componentProps: {
        placeholder: '请输入主承销商',
      },
    },
    {
      fieldName: 'listingExchange',
      label: '上市交易所（上交所、深交所、银行间市场等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入上市交易所（上交所、深交所、银行间市场等）',
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
      fieldName: 'bondStatus',
      label: '债券状态（1=正常 2=暂停交易 3=提前赎回 4=违约 5=已到期）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保方式（无担保、抵押、质押、保证担保等）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择担保方式（无担保、抵押、质押、保证担保等）',
      },
    },
    {
      fieldName: 'guarantor',
      label: '担保方',
      component: 'Input',
      componentProps: {
        placeholder: '请输入担保方',
      },
    },
    {
      fieldName: 'enhancementMeasures',
      label: '增信措施',
      component: 'Input',
      componentProps: {
        placeholder: '请输入增信措施',
      },
    },
    {
      fieldName: 'isConvertible',
      label: '是否可转债（0=否 1=是）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入是否可转债（0=否 1=是）',
      },
    },
    {
      fieldName: 'conversionPrice',
      label: '转股价格',
      component: 'Input',
      componentProps: {
        placeholder: '请输入转股价格',
      },
    },
    {
      fieldName: 'conversionStartDate',
      label: '转股起始日',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'conversionEndDate',
      label: '转股截止日',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'conversionStockCode',
      label: '转股代码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入转股代码',
      },
    },
    {
      fieldName: 'currentPrice',
      label: '当前价格',
      component: 'Input',
      componentProps: {
        placeholder: '请输入当前价格',
      },
    },
    {
      fieldName: 'yieldToMaturity',
      label: '到期收益率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入到期收益率（%）',
      },
    },
    {
      fieldName: 'outstandingAmount',
      label: '未偿还余额（元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入未偿还余额（元）',
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
      fieldName: 'useOfProceeds',
      label: '募集资金用途',
      component: 'Input',
      componentProps: {
        placeholder: '请输入募集资金用途',
      },
    },
    {
      fieldName: 'isGreenBond',
      label: '是否绿色债券（0=否 1=是）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入是否绿色债券（0=否 1=是）',
      },
    },
    {
      fieldName: 'specialClause',
      label: '特殊条款（回售条款、赎回条款、调整票面利率条款等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入特殊条款（回售条款、赎回条款、调整票面利率条款等）',
      },
    },
    {
      fieldName: 'dataSource',
      label: '数据来源（手工录入、Wind、中债网、中证网等）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入数据来源（手工录入、Wind、中债网、中证网等）',
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
      fieldName: 'bondCode',
      label: '债券代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入债券代码',
      },
    },
    {
      fieldName: 'bondName',
      label: '债券名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入债券名称',
      },
    },
    {
      fieldName: 'bondType',
      label: '债券类型（国债、地方政府债、金融债、企业债、公司债、中期票据、短期融资券、可转债等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择债券类型（国债、地方政府债、金融债、企业债、公司债、中期票据、短期融资券、可转债等）',
      },
    },
    {
      fieldName: 'bondSubtype',
      label: '债券子类型（一般企业债、高收益债、绿色债券、资产证券化等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择债券子类型（一般企业债、高收益债、绿色债券、资产证券化等）',
      },
    },
    {
      fieldName: 'issueDate',
      label: '发行日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'issueAmount',
      label: '发行金额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发行金额（元）',
      },
    },
    {
      fieldName: 'issuePrice',
      label: '发行价格（面值百元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发行价格（面值百元）',
      },
    },
    {
      fieldName: 'parValue',
      label: '面值（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入面值（元）',
      },
    },
    {
      fieldName: 'issueScale',
      label: '发行规模（张）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入发行规模（张）',
      },
    },
    {
      fieldName: 'maturityDate',
      label: '到期日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'termYears',
      label: '债券期限（年）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入债券期限（年）',
      },
    },
    {
      fieldName: 'couponRate',
      label: '票面利率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入票面利率（%）',
      },
    },
    {
      fieldName: 'interestType',
      label: '计息方式（固定利率、浮动利率、零息、累进利率等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择计息方式（固定利率、浮动利率、零息、累进利率等）',
      },
    },
    {
      fieldName: 'paymentFrequency',
      label: '付息频率（年付、半年付、季付、月付等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入付息频率（年付、半年付、季付、月付等）',
      },
    },
    {
      fieldName: 'creditRating',
      label: '债券信用评级（AAA、AA+、AA等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入债券信用评级（AAA、AA+、AA等）',
      },
    },
    {
      fieldName: 'ratingAgency',
      label: '评级机构（中诚信、联合资信、大公国际等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入评级机构（中诚信、联合资信、大公国际等）',
      },
    },
    {
      fieldName: 'ratingDate',
      label: '评级日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'underwriter',
      label: '主承销商',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入主承销商',
      },
    },
    {
      fieldName: 'listingExchange',
      label: '上市交易所（上交所、深交所、银行间市场等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入上市交易所（上交所、深交所、银行间市场等）',
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
      fieldName: 'bondStatus',
      label: '债券状态（1=正常 2=暂停交易 3=提前赎回 4=违约 5=已到期）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择债券状态（1=正常 2=暂停交易 3=提前赎回 4=违约 5=已到期）',
      },
    },
    {
      fieldName: 'guaranteeType',
      label: '担保方式（无担保、抵押、质押、保证担保等）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择担保方式（无担保、抵押、质押、保证担保等）',
      },
    },
    {
      fieldName: 'guarantor',
      label: '担保方',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入担保方',
      },
    },
    {
      fieldName: 'enhancementMeasures',
      label: '增信措施',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入增信措施',
      },
    },
    {
      fieldName: 'isConvertible',
      label: '是否可转债（0=否 1=是）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入是否可转债（0=否 1=是）',
      },
    },
    {
      fieldName: 'conversionPrice',
      label: '转股价格',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入转股价格',
      },
    },
    {
      fieldName: 'conversionStartDate',
      label: '转股起始日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'conversionEndDate',
      label: '转股截止日',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'conversionStockCode',
      label: '转股代码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入转股代码',
      },
    },
    {
      fieldName: 'currentPrice',
      label: '当前价格',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入当前价格',
      },
    },
    {
      fieldName: 'yieldToMaturity',
      label: '到期收益率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入到期收益率（%）',
      },
    },
    {
      fieldName: 'outstandingAmount',
      label: '未偿还余额（元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入未偿还余额（元）',
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
      fieldName: 'useOfProceeds',
      label: '募集资金用途',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入募集资金用途',
      },
    },
    {
      fieldName: 'isGreenBond',
      label: '是否绿色债券（0=否 1=是）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入是否绿色债券（0=否 1=是）',
      },
    },
    {
      fieldName: 'specialClause',
      label: '特殊条款（回售条款、赎回条款、调整票面利率条款等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入特殊条款（回售条款、赎回条款、调整票面利率条款等）',
      },
    },
    {
      fieldName: 'dataSource',
      label: '数据来源（手工录入、Wind、中债网、中证网等）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入数据来源（手工录入、Wind、中债网、中证网等）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCompanyBondApi.CompanyBond>['columns'] {
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
      field: 'bondCode',
      title: '债券代码',
      minWidth: 120,
    },
    {
      field: 'bondName',
      title: '债券名称',
      minWidth: 120,
    },
    {
      field: 'bondType',
      title: '债券类型（国债、地方政府债、金融债、企业债、公司债、中期票据、短期融资券、可转债等）',
      minWidth: 120,
    },
    {
      field: 'bondSubtype',
      title: '债券子类型（一般企业债、高收益债、绿色债券、资产证券化等）',
      minWidth: 120,
    },
    {
      field: 'issueDate',
      title: '发行日期',
      minWidth: 120,
    },
    {
      field: 'issueAmount',
      title: '发行金额（元）',
      minWidth: 120,
    },
    {
      field: 'issuePrice',
      title: '发行价格（面值百元）',
      minWidth: 120,
    },
    {
      field: 'parValue',
      title: '面值（元）',
      minWidth: 120,
    },
    {
      field: 'issueScale',
      title: '发行规模（张）',
      minWidth: 120,
    },
    {
      field: 'maturityDate',
      title: '到期日期',
      minWidth: 120,
    },
    {
      field: 'termYears',
      title: '债券期限（年）',
      minWidth: 120,
    },
    {
      field: 'couponRate',
      title: '票面利率（%）',
      minWidth: 120,
    },
    {
      field: 'interestType',
      title: '计息方式（固定利率、浮动利率、零息、累进利率等）',
      minWidth: 120,
    },
    {
      field: 'paymentFrequency',
      title: '付息频率（年付、半年付、季付、月付等）',
      minWidth: 120,
    },
    {
      field: 'creditRating',
      title: '债券信用评级（AAA、AA+、AA等）',
      minWidth: 120,
    },
    {
      field: 'ratingAgency',
      title: '评级机构（中诚信、联合资信、大公国际等）',
      minWidth: 120,
    },
    {
      field: 'ratingDate',
      title: '评级日期',
      minWidth: 120,
    },
    {
      field: 'underwriter',
      title: '主承销商',
      minWidth: 120,
    },
    {
      field: 'listingExchange',
      title: '上市交易所（上交所、深交所、银行间市场等）',
      minWidth: 120,
    },
    {
      field: 'listingDate',
      title: '上市日期',
      minWidth: 120,
    },
    {
      field: 'bondStatus',
      title: '债券状态（1=正常 2=暂停交易 3=提前赎回 4=违约 5=已到期）',
      minWidth: 120,
    },
    {
      field: 'guaranteeType',
      title: '担保方式（无担保、抵押、质押、保证担保等）',
      minWidth: 120,
    },
    {
      field: 'guarantor',
      title: '担保方',
      minWidth: 120,
    },
    {
      field: 'enhancementMeasures',
      title: '增信措施',
      minWidth: 120,
    },
    {
      field: 'isConvertible',
      title: '是否可转债（0=否 1=是）',
      minWidth: 120,
    },
    {
      field: 'conversionPrice',
      title: '转股价格',
      minWidth: 120,
    },
    {
      field: 'conversionStartDate',
      title: '转股起始日',
      minWidth: 120,
    },
    {
      field: 'conversionEndDate',
      title: '转股截止日',
      minWidth: 120,
    },
    {
      field: 'conversionStockCode',
      title: '转股代码',
      minWidth: 120,
    },
    {
      field: 'currentPrice',
      title: '当前价格',
      minWidth: 120,
    },
    {
      field: 'yieldToMaturity',
      title: '到期收益率（%）',
      minWidth: 120,
    },
    {
      field: 'outstandingAmount',
      title: '未偿还余额（元）',
      minWidth: 120,
    },
    {
      field: 'priceUpdateTime',
      title: '价格更新时间',
      minWidth: 120,
      formatter: 'formatDateTime',
    },
    {
      field: 'useOfProceeds',
      title: '募集资金用途',
      minWidth: 120,
    },
    {
      field: 'isGreenBond',
      title: '是否绿色债券（0=否 1=是）',
      minWidth: 120,
    },
    {
      field: 'specialClause',
      title: '特殊条款（回售条款、赎回条款、调整票面利率条款等）',
      minWidth: 120,
    },
    {
      field: 'dataSource',
      title: '数据来源（手工录入、Wind、中债网、中证网等）',
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