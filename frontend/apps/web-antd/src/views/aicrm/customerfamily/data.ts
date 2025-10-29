import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerFamilyApi } from '#/api/aicrm/customerfamily';

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
      label: '客户ID（关联零售客户，唯一）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户ID（关联零售客户，唯一）',
      },
    },
    {
      fieldName: 'familyMemberCount',
      label: '家庭人口数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭人口数',
      },
    },
    {
      fieldName: 'supportMemberCount',
      label: '供养人口数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入供养人口数',
      },
    },
    {
      fieldName: 'laborMemberCount',
      label: '劳动力人口数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入劳动力人口数',
      },
    },
    {
      fieldName: 'childrenCount',
      label: '子女数量',
      component: 'Input',
      componentProps: {
        placeholder: '请输入子女数量',
      },
    },
    {
      fieldName: 'familyAnnualIncome',
      label: '家庭年收入（万元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭年收入（万元）',
      },
    },
    {
      fieldName: 'familyAnnualIncomeScope',
      label: '家庭年收入范围（字典：aicrm_family_income_scope）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭年收入范围（字典：aicrm_family_income_scope）',
      },
    },
    {
      fieldName: 'familyAnnualExpenditure',
      label: '家庭年支出（万元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭年支出（万元）',
      },
    },
    {
      fieldName: 'familyAnnualExpenditureScope',
      label: '家庭年支出范围（字典：aicrm_family_expenditure_scope）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭年支出范围（字典：aicrm_family_expenditure_scope）',
      },
    },
    {
      fieldName: 'familyDebt',
      label: '家庭负债（万元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭负债（万元）',
      },
    },
    {
      fieldName: 'familyTotalAssets',
      label: '家庭总资产（万元）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭总资产（万元）',
      },
    },
    {
      fieldName: 'familyAssetsInfo',
      label: '家庭资产信息',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭资产信息',
      },
    },
    {
      fieldName: 'mainIncomeSource',
      label: '主要收入来源（字典：aicrm_income_source）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入主要收入来源（字典：aicrm_income_source）',
      },
    },
    {
      fieldName: 'residenceStatus',
      label: '居住状况（字典：aicrm_residence_status）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'houseStatus',
      label: '住房状况（字典：aicrm_house_status）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'hasHomeCar',
      label: '是否有房有车（0-否，1-是）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'isHouseHolder',
      label: '是否户主（0-否，1-是）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'houseHolderName',
      label: '户主姓名',
      component: 'Input',
      componentProps: {
        placeholder: '请输入户主姓名',
      },
    },
    {
      fieldName: 'residenceLocation',
      label: '所居住宅多(值)，即居住地点描述',
      component: 'Input',
      componentProps: {
        placeholder: '请输入所居住宅多(值)，即居住地点描述',
      },
    },
    {
      fieldName: 'familyAddress',
      label: '家庭地址',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭地址',
      },
    },
    {
      fieldName: 'homeTel',
      label: '家庭电话',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭电话',
      },
    },
    {
      fieldName: 'isHarmony',
      label: '家庭是否和睦（0-否，1-是）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'isCreditFamily',
      label: '是否信用家庭（0-否，1-是）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'creditStatus',
      label: '信用状况（字典：aicrm_credit_status）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'creditAmount',
      label: '授信额度',
      component: 'Input',
      componentProps: {
        placeholder: '请输入授信额度',
      },
    },
    {
      fieldName: 'familyDebtScope',
      label: '家庭负债范围（字典：aicrm_debt_scope）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭负债范围（字典：aicrm_debt_scope）',
      },
    },
    {
      fieldName: 'debtStatus',
      label: '负债状况（字典：aicrm_debt_status）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'familyAdverseRecords',
      label: '家庭不良记录',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭不良记录',
      },
    },
    {
      fieldName: 'businessAndScale',
      label: '经营及规模',
      component: 'Input',
      componentProps: {
        placeholder: '请输入经营及规模',
      },
    },
    {
      fieldName: 'familyStrength',
      label: '家庭实力（字典：aicrm_family_strength）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入家庭实力（字典：aicrm_family_strength）',
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
      label: '客户ID（关联零售客户，唯一）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户ID（关联零售客户，唯一）',
      },
    },
    {
      fieldName: 'familyMemberCount',
      label: '家庭人口数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭人口数',
      },
    },
    {
      fieldName: 'supportMemberCount',
      label: '供养人口数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入供养人口数',
      },
    },
    {
      fieldName: 'laborMemberCount',
      label: '劳动力人口数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入劳动力人口数',
      },
    },
    {
      fieldName: 'childrenCount',
      label: '子女数量',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入子女数量',
      },
    },
    {
      fieldName: 'familyAnnualIncome',
      label: '家庭年收入（万元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭年收入（万元）',
      },
    },
    {
      fieldName: 'familyAnnualIncomeScope',
      label: '家庭年收入范围（字典：aicrm_family_income_scope）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭年收入范围（字典：aicrm_family_income_scope）',
      },
    },
    {
      fieldName: 'familyAnnualExpenditure',
      label: '家庭年支出（万元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭年支出（万元）',
      },
    },
    {
      fieldName: 'familyAnnualExpenditureScope',
      label: '家庭年支出范围（字典：aicrm_family_expenditure_scope）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭年支出范围（字典：aicrm_family_expenditure_scope）',
      },
    },
    {
      fieldName: 'familyDebt',
      label: '家庭负债（万元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭负债（万元）',
      },
    },
    {
      fieldName: 'familyTotalAssets',
      label: '家庭总资产（万元）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭总资产（万元）',
      },
    },
    {
      fieldName: 'familyAssetsInfo',
      label: '家庭资产信息',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭资产信息',
      },
    },
    {
      fieldName: 'mainIncomeSource',
      label: '主要收入来源（字典：aicrm_income_source）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入主要收入来源（字典：aicrm_income_source）',
      },
    },
    {
      fieldName: 'residenceStatus',
      label: '居住状况（字典：aicrm_residence_status）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择居住状况（字典：aicrm_residence_status）',
      },
    },
    {
      fieldName: 'houseStatus',
      label: '住房状况（字典：aicrm_house_status）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择住房状况（字典：aicrm_house_status）',
      },
    },
    {
      fieldName: 'hasHomeCar',
      label: '是否有房有车（0-否，1-是）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否有房有车（0-否，1-是）',
      },
    },
    {
      fieldName: 'isHouseHolder',
      label: '是否户主（0-否，1-是）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否户主（0-否，1-是）',
      },
    },
    {
      fieldName: 'houseHolderName',
      label: '户主姓名',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入户主姓名',
      },
    },
    {
      fieldName: 'residenceLocation',
      label: '所居住宅多(值)，即居住地点描述',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入所居住宅多(值)，即居住地点描述',
      },
    },
    {
      fieldName: 'familyAddress',
      label: '家庭地址',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭地址',
      },
    },
    {
      fieldName: 'homeTel',
      label: '家庭电话',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭电话',
      },
    },
    {
      fieldName: 'isHarmony',
      label: '家庭是否和睦（0-否，1-是）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择家庭是否和睦（0-否，1-是）',
      },
    },
    {
      fieldName: 'isCreditFamily',
      label: '是否信用家庭（0-否，1-是）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否信用家庭（0-否，1-是）',
      },
    },
    {
      fieldName: 'creditStatus',
      label: '信用状况（字典：aicrm_credit_status）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择信用状况（字典：aicrm_credit_status）',
      },
    },
    {
      fieldName: 'creditAmount',
      label: '授信额度',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入授信额度',
      },
    },
    {
      fieldName: 'familyDebtScope',
      label: '家庭负债范围（字典：aicrm_debt_scope）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭负债范围（字典：aicrm_debt_scope）',
      },
    },
    {
      fieldName: 'debtStatus',
      label: '负债状况（字典：aicrm_debt_status）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择负债状况（字典：aicrm_debt_status）',
      },
    },
    {
      fieldName: 'familyAdverseRecords',
      label: '家庭不良记录',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭不良记录',
      },
    },
    {
      fieldName: 'businessAndScale',
      label: '经营及规模',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入经营及规模',
      },
    },
    {
      fieldName: 'familyStrength',
      label: '家庭实力（字典：aicrm_family_strength）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入家庭实力（字典：aicrm_family_strength）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerFamilyApi.CustomerFamily>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '主键ID',
      minWidth: 120,
    },
    {
      field: 'customerId',
      title: '客户ID（关联零售客户，唯一）',
      minWidth: 120,
    },
    {
      field: 'familyMemberCount',
      title: '家庭人口数',
      minWidth: 120,
    },
    {
      field: 'supportMemberCount',
      title: '供养人口数',
      minWidth: 120,
    },
    {
      field: 'laborMemberCount',
      title: '劳动力人口数',
      minWidth: 120,
    },
    {
      field: 'childrenCount',
      title: '子女数量',
      minWidth: 120,
    },
    {
      field: 'familyAnnualIncome',
      title: '家庭年收入（万元）',
      minWidth: 120,
    },
    {
      field: 'familyAnnualIncomeScope',
      title: '家庭年收入范围（字典：aicrm_family_income_scope）',
      minWidth: 120,
    },
    {
      field: 'familyAnnualExpenditure',
      title: '家庭年支出（万元）',
      minWidth: 120,
    },
    {
      field: 'familyAnnualExpenditureScope',
      title: '家庭年支出范围（字典：aicrm_family_expenditure_scope）',
      minWidth: 120,
    },
    {
      field: 'familyDebt',
      title: '家庭负债（万元）',
      minWidth: 120,
    },
    {
      field: 'familyTotalAssets',
      title: '家庭总资产（万元）',
      minWidth: 120,
    },
    {
      field: 'familyAssetsInfo',
      title: '家庭资产信息',
      minWidth: 120,
    },
    {
      field: 'mainIncomeSource',
      title: '主要收入来源（字典：aicrm_income_source）',
      minWidth: 120,
    },
    {
      field: 'residenceStatus',
      title: '居住状况（字典：aicrm_residence_status）',
      minWidth: 120,
    },
    {
      field: 'houseStatus',
      title: '住房状况（字典：aicrm_house_status）',
      minWidth: 120,
    },
    {
      field: 'hasHomeCar',
      title: '是否有房有车（0-否，1-是）',
      minWidth: 120,
    },
    {
      field: 'isHouseHolder',
      title: '是否户主（0-否，1-是）',
      minWidth: 120,
    },
    {
      field: 'houseHolderName',
      title: '户主姓名',
      minWidth: 120,
    },
    {
      field: 'residenceLocation',
      title: '所居住宅多(值)，即居住地点描述',
      minWidth: 120,
    },
    {
      field: 'familyAddress',
      title: '家庭地址',
      minWidth: 120,
    },
    {
      field: 'homeTel',
      title: '家庭电话',
      minWidth: 120,
    },
    {
      field: 'isHarmony',
      title: '家庭是否和睦（0-否，1-是）',
      minWidth: 120,
    },
    {
      field: 'isCreditFamily',
      title: '是否信用家庭（0-否，1-是）',
      minWidth: 120,
    },
    {
      field: 'creditStatus',
      title: '信用状况（字典：aicrm_credit_status）',
      minWidth: 120,
    },
    {
      field: 'creditAmount',
      title: '授信额度',
      minWidth: 120,
    },
    {
      field: 'familyDebtScope',
      title: '家庭负债范围（字典：aicrm_debt_scope）',
      minWidth: 120,
    },
    {
      field: 'debtStatus',
      title: '负债状况（字典：aicrm_debt_status）',
      minWidth: 120,
    },
    {
      field: 'familyAdverseRecords',
      title: '家庭不良记录',
      minWidth: 120,
    },
    {
      field: 'businessAndScale',
      title: '经营及规模',
      minWidth: 120,
    },
    {
      field: 'familyStrength',
      title: '家庭实力（字典：aicrm_family_strength）',
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