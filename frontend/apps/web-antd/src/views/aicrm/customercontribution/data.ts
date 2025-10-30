import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerContributionApi } from '#/api/aicrm/customercontribution';

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
      label: '客户ID（关联 crm_customer 主表，1对1关系）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联 crm_customer 主表，1对1关系）',
      },
    },
    {
      fieldName: 'sequenceNo',
      label: '序号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入序号',
      },
    },
    {
      fieldName: 'totalContribution',
      label: '客户综合贡献度',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户综合贡献度',
      },
    },
    {
      fieldName: 'depositContribution',
      label: '存款贡献度',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入存款贡献度',
      },
    },
    {
      fieldName: 'loanContribution',
      label: '贷款贡献度',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款贡献度',
      },
    },
    {
      fieldName: 'intermediateContribution',
      label: '中间业务贡献度',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入中间业务贡献度',
      },
    },
    {
      fieldName: 'statisticsTime',
      label: '统计时间',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'custName',
      label: '客户名称（老系统字段）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户名称（老系统字段）',
      },
    },
    {
      fieldName: 'orgId',
      label: '机构ID（老系统）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机构ID（老系统）',
      },
    },
    {
      fieldName: 'contriDeposit',
      label: '存款贡献度（老系统字段名）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入存款贡献度（老系统字段名）',
      },
    },
    {
      fieldName: 'contributionLoan',
      label: '贷款贡献度（老系统字段名）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贷款贡献度（老系统字段名）',
      },
    },
    {
      fieldName: 'contributionMid',
      label: '中间业务贡献度（老系统字段名）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入中间业务贡献度（老系统字段名）',
      },
    },
    {
      fieldName: 'contributionCust',
      label: '客户综合贡献度（老系统字段名）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户综合贡献度（老系统字段名）',
      },
    },
    {
      fieldName: 'contriBillDiscount',
      label: '票据贴现贡献度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入票据贴现贡献度',
      },
    },
    {
      fieldName: 'contriInternation',
      label: '国际业务贡献度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入国际业务贡献度',
      },
    },
    {
      fieldName: 'etlDate',
      label: '数据加载日期（老系统）',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'wealthManagementContribution',
      label: '理财业务贡献度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入理财业务贡献度',
      },
    },
    {
      fieldName: 'cardContribution',
      label: '卡业务贡献度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入卡业务贡献度',
      },
    },
    {
      fieldName: 'settlementContribution',
      label: '结算业务贡献度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入结算业务贡献度',
      },
    },
    {
      fieldName: 'electronicBankingContribution',
      label: '电子银行贡献度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入电子银行贡献度',
      },
    },
    {
      fieldName: 'contributionRank',
      label: '贡献度排名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贡献度排名',
      },
    },
    {
      fieldName: 'contributionLevel',
      label: '贡献度等级（字典: aicrm_contribution_level）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入贡献度等级（字典: aicrm_contribution_level）',
      },
    },
    {
      fieldName: 'yearOverYearGrowth',
      label: '同比增长率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入同比增长率（%）',
      },
    },
    {
      fieldName: 'monthOverMonthGrowth',
      label: '环比增长率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入环比增长率（%）',
      },
    },
    {
      fieldName: 'statisticsPeriod',
      label: '统计周期（字典: aicrm_statistics_period，daily=日，monthly=月，quarterly=季，yearly=年）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入统计周期（字典: aicrm_statistics_period，daily=日，monthly=月，quarterly=季，yearly=年）',
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
      label: '客户ID（关联 crm_customer 主表，1对1关系）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联 crm_customer 主表，1对1关系）',
      },
    },
    {
      fieldName: 'sequenceNo',
      label: '序号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入序号',
      },
    },
    {
      fieldName: 'totalContribution',
      label: '客户综合贡献度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户综合贡献度',
      },
    },
    {
      fieldName: 'depositContribution',
      label: '存款贡献度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入存款贡献度',
      },
    },
    {
      fieldName: 'loanContribution',
      label: '贷款贡献度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款贡献度',
      },
    },
    {
      fieldName: 'intermediateContribution',
      label: '中间业务贡献度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入中间业务贡献度',
      },
    },
    {
      fieldName: 'statisticsTime',
      label: '统计时间',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'custName',
      label: '客户名称（老系统字段）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户名称（老系统字段）',
      },
    },
    {
      fieldName: 'orgId',
      label: '机构ID（老系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机构ID（老系统）',
      },
    },
    {
      fieldName: 'contriDeposit',
      label: '存款贡献度（老系统字段名）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入存款贡献度（老系统字段名）',
      },
    },
    {
      fieldName: 'contributionLoan',
      label: '贷款贡献度（老系统字段名）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贷款贡献度（老系统字段名）',
      },
    },
    {
      fieldName: 'contributionMid',
      label: '中间业务贡献度（老系统字段名）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入中间业务贡献度（老系统字段名）',
      },
    },
    {
      fieldName: 'contributionCust',
      label: '客户综合贡献度（老系统字段名）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户综合贡献度（老系统字段名）',
      },
    },
    {
      fieldName: 'contriBillDiscount',
      label: '票据贴现贡献度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入票据贴现贡献度',
      },
    },
    {
      fieldName: 'contriInternation',
      label: '国际业务贡献度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入国际业务贡献度',
      },
    },
    {
      fieldName: 'etlDate',
      label: '数据加载日期（老系统）',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'wealthManagementContribution',
      label: '理财业务贡献度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入理财业务贡献度',
      },
    },
    {
      fieldName: 'cardContribution',
      label: '卡业务贡献度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入卡业务贡献度',
      },
    },
    {
      fieldName: 'settlementContribution',
      label: '结算业务贡献度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入结算业务贡献度',
      },
    },
    {
      fieldName: 'electronicBankingContribution',
      label: '电子银行贡献度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入电子银行贡献度',
      },
    },
    {
      fieldName: 'contributionRank',
      label: '贡献度排名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贡献度排名',
      },
    },
    {
      fieldName: 'contributionLevel',
      label: '贡献度等级（字典: aicrm_contribution_level）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入贡献度等级（字典: aicrm_contribution_level）',
      },
    },
    {
      fieldName: 'yearOverYearGrowth',
      label: '同比增长率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入同比增长率（%）',
      },
    },
    {
      fieldName: 'monthOverMonthGrowth',
      label: '环比增长率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入环比增长率（%）',
      },
    },
    {
      fieldName: 'statisticsPeriod',
      label: '统计周期（字典: aicrm_statistics_period，daily=日，monthly=月，quarterly=季，yearly=年）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入统计周期（字典: aicrm_statistics_period，daily=日，monthly=月，quarterly=季，yearly=年）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerContributionApi.CustomerContribution>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '贡献度主键',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联 crm_customer 主表，1对1关系）',
      minWidth: 120,
    },
    {
      field: 'sequenceNo',
      title: '序号',
      minWidth: 120,
    },
    {
      field: 'totalContribution',
      title: '客户综合贡献度',
      minWidth: 120,
    },
    {
      field: 'depositContribution',
      title: '存款贡献度',
      minWidth: 120,
    },
    {
      field: 'loanContribution',
      title: '贷款贡献度',
      minWidth: 120,
    },
    {
      field: 'intermediateContribution',
      title: '中间业务贡献度',
      minWidth: 120,
    },
    {
      field: 'statisticsTime',
      title: '统计时间',
      minWidth: 120,
    },
    {
      field: 'custName',
      title: '客户名称（老系统字段）',
      minWidth: 120,
    },
    {
      field: 'orgId',
      title: '机构ID（老系统）',
      minWidth: 120,
    },
    {
      field: 'contriDeposit',
      title: '存款贡献度（老系统字段名）',
      minWidth: 120,
    },
    {
      field: 'contributionLoan',
      title: '贷款贡献度（老系统字段名）',
      minWidth: 120,
    },
    {
      field: 'contributionMid',
      title: '中间业务贡献度（老系统字段名）',
      minWidth: 120,
    },
    {
      field: 'contributionCust',
      title: '客户综合贡献度（老系统字段名）',
      minWidth: 120,
    },
    {
      field: 'contriBillDiscount',
      title: '票据贴现贡献度',
      minWidth: 120,
    },
    {
      field: 'contriInternation',
      title: '国际业务贡献度',
      minWidth: 120,
    },
    {
      field: 'etlDate',
      title: '数据加载日期（老系统）',
      minWidth: 120,
    },
    {
      field: 'wealthManagementContribution',
      title: '理财业务贡献度',
      minWidth: 120,
    },
    {
      field: 'cardContribution',
      title: '卡业务贡献度',
      minWidth: 120,
    },
    {
      field: 'settlementContribution',
      title: '结算业务贡献度',
      minWidth: 120,
    },
    {
      field: 'electronicBankingContribution',
      title: '电子银行贡献度',
      minWidth: 120,
    },
    {
      field: 'contributionRank',
      title: '贡献度排名',
      minWidth: 120,
    },
    {
      field: 'contributionLevel',
      title: '贡献度等级（字典: aicrm_contribution_level）',
      minWidth: 120,
    },
    {
      field: 'yearOverYearGrowth',
      title: '同比增长率（%）',
      minWidth: 120,
    },
    {
      field: 'monthOverMonthGrowth',
      title: '环比增长率（%）',
      minWidth: 120,
    },
    {
      field: 'statisticsPeriod',
      title: '统计周期（字典: aicrm_statistics_period，daily=日，monthly=月，quarterly=季，yearly=年）',
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