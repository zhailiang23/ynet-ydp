import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerRatingApi } from '#/api/aicrm/customerrating';

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
      fieldName: 'approvalStatus',
      label: '审批状态（字典: aicrm_rating_approval_status）',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'ratingDate',
      label: '评级日期',
      rules: 'required',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'valueLevel',
      label: '价值等级（字典: aicrm_value_level）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入价值等级（字典: aicrm_value_level）',
      },
    },
    {
      fieldName: 'serviceLevel',
      label: '服务等级（字典: aicrm_service_level）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入服务等级（字典: aicrm_service_level）',
      },
    },
    {
      fieldName: 'serviceLevelBeforeRisk',
      label: '剔除风险前服务等级（字典: aicrm_service_level）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入剔除风险前服务等级（字典: aicrm_service_level）',
      },
    },
    {
      fieldName: 'riskFactors',
      label: '风险影响因子内容',
      component: 'Input',
      componentProps: {
        placeholder: '请输入风险影响因子内容',
      },
    },
    {
      fieldName: 'ratingMethod',
      label: '评级方式（字典: aicrm_rating_method，system=系统评级，manual=人工评级）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入评级方式（字典: aicrm_rating_method，system=系统评级，manual=人工评级）',
      },
    },
    {
      fieldName: 'custGradeId',
      label: '评级ID（老系统）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入评级ID（老系统）',
      },
    },
    {
      fieldName: 'effectiveDate',
      label: '生效日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'expiredDate',
      label: '失效日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'evaluateDate',
      label: '评估日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
      },
    },
    {
      fieldName: 'custGrade',
      label: '客户等级（老系统字段）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户等级（老系统字段）',
      },
    },
    {
      fieldName: 'custGradeType',
      label: '客户等级类型',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择客户等级类型',
      },
    },
    {
      fieldName: 'orgCode',
      label: '机构编码',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机构编码',
      },
    },
    {
      fieldName: 'orgName',
      label: '机构名称',
      component: 'Input',
      componentProps: {
        placeholder: '请输入机构名称',
      },
    },
    {
      fieldName: 'custCnt',
      label: '客户数量',
      component: 'Input',
      componentProps: {
        placeholder: '请输入客户数量',
      },
    },
    {
      fieldName: 'ratingScore',
      label: '评级分数',
      component: 'Input',
      componentProps: {
        placeholder: '请输入评级分数',
      },
    },
    {
      fieldName: 'ratingModelVersion',
      label: '评级模型版本',
      component: 'Input',
      componentProps: {
        placeholder: '请输入评级模型版本',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级（字典: aicrm_risk_level）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入风险等级（字典: aicrm_risk_level）',
      },
    },
    {
      fieldName: 'nextRatingDate',
      label: '下次评级日期',
      component: 'DatePicker',
      componentProps: {
        showTime: true,
        format: 'YYYY-MM-DD HH:mm:ss',
        valueFormat: 'x',
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
      fieldName: 'approvalStatus',
      label: '审批状态（字典: aicrm_rating_approval_status）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择审批状态（字典: aicrm_rating_approval_status）',
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
      fieldName: 'valueLevel',
      label: '价值等级（字典: aicrm_value_level）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入价值等级（字典: aicrm_value_level）',
      },
    },
    {
      fieldName: 'serviceLevel',
      label: '服务等级（字典: aicrm_service_level）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入服务等级（字典: aicrm_service_level）',
      },
    },
    {
      fieldName: 'serviceLevelBeforeRisk',
      label: '剔除风险前服务等级（字典: aicrm_service_level）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入剔除风险前服务等级（字典: aicrm_service_level）',
      },
    },
    {
      fieldName: 'riskFactors',
      label: '风险影响因子内容',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入风险影响因子内容',
      },
    },
    {
      fieldName: 'ratingMethod',
      label: '评级方式（字典: aicrm_rating_method，system=系统评级，manual=人工评级）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入评级方式（字典: aicrm_rating_method，system=系统评级，manual=人工评级）',
      },
    },
    {
      fieldName: 'custGradeId',
      label: '评级ID（老系统）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入评级ID（老系统）',
      },
    },
    {
      fieldName: 'effectiveDate',
      label: '生效日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'expiredDate',
      label: '失效日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'evaluateDate',
      label: '评估日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
      },
    },
    {
      fieldName: 'custGrade',
      label: '客户等级（老系统字段）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户等级（老系统字段）',
      },
    },
    {
      fieldName: 'custGradeType',
      label: '客户等级类型',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择客户等级类型',
      },
    },
    {
      fieldName: 'orgCode',
      label: '机构编码',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机构编码',
      },
    },
    {
      fieldName: 'orgName',
      label: '机构名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入机构名称',
      },
    },
    {
      fieldName: 'custCnt',
      label: '客户数量',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入客户数量',
      },
    },
    {
      fieldName: 'ratingScore',
      label: '评级分数',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入评级分数',
      },
    },
    {
      fieldName: 'ratingModelVersion',
      label: '评级模型版本',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入评级模型版本',
      },
    },
    {
      fieldName: 'riskLevel',
      label: '风险等级（字典: aicrm_risk_level）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入风险等级（字典: aicrm_risk_level）',
      },
    },
    {
      fieldName: 'nextRatingDate',
      label: '下次评级日期',
      component: 'RangePicker',
      componentProps: {
        ...getRangePickerDefaultProps(),
        allowClear: true,
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
export function useGridColumns(): VxeTableGridOptions<AicrmCustomerRatingApi.CustomerRating>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '评级主键',
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
      field: 'approvalStatus',
      title: '审批状态（字典: aicrm_rating_approval_status）',
      minWidth: 120,
    },
    {
      field: 'ratingDate',
      title: '评级日期',
      minWidth: 120,
    },
    {
      field: 'valueLevel',
      title: '价值等级（字典: aicrm_value_level）',
      minWidth: 120,
    },
    {
      field: 'serviceLevel',
      title: '服务等级（字典: aicrm_service_level）',
      minWidth: 120,
    },
    {
      field: 'serviceLevelBeforeRisk',
      title: '剔除风险前服务等级（字典: aicrm_service_level）',
      minWidth: 120,
    },
    {
      field: 'riskFactors',
      title: '风险影响因子内容',
      minWidth: 120,
    },
    {
      field: 'ratingMethod',
      title: '评级方式（字典: aicrm_rating_method，system=系统评级，manual=人工评级）',
      minWidth: 120,
    },
    {
      field: 'custGradeId',
      title: '评级ID（老系统）',
      minWidth: 120,
    },
    {
      field: 'effectiveDate',
      title: '生效日期',
      minWidth: 120,
    },
    {
      field: 'expiredDate',
      title: '失效日期',
      minWidth: 120,
    },
    {
      field: 'evaluateDate',
      title: '评估日期',
      minWidth: 120,
    },
    {
      field: 'custGrade',
      title: '客户等级（老系统字段）',
      minWidth: 120,
    },
    {
      field: 'custGradeType',
      title: '客户等级类型',
      minWidth: 120,
    },
    {
      field: 'orgCode',
      title: '机构编码',
      minWidth: 120,
    },
    {
      field: 'orgName',
      title: '机构名称',
      minWidth: 120,
    },
    {
      field: 'custCnt',
      title: '客户数量',
      minWidth: 120,
    },
    {
      field: 'ratingScore',
      title: '评级分数',
      minWidth: 120,
    },
    {
      field: 'ratingModelVersion',
      title: '评级模型版本',
      minWidth: 120,
    },
    {
      field: 'riskLevel',
      title: '风险等级（字典: aicrm_risk_level）',
      minWidth: 120,
    },
    {
      field: 'nextRatingDate',
      title: '下次评级日期',
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