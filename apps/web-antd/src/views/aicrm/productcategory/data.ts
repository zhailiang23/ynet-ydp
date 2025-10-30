import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmProductCategoryApi } from '#/api/aicrm/productcategory';

import { getDictOptions } from '@vben/hooks';
import { getProductCategoryList } from '#/api/aicrm/productcategory';
import { handleTree } from '@vben/utils';

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
      fieldName: 'parentId',
      label: '上级产品类目表（树形结构）',
      component: 'ApiTreeSelect',
      componentProps: {
        allowClear: true,
        api: async () => {
          const data = await getProductCategoryList({});
          data.unshift({
            id: 0,
            categoryName: '顶级产品类目表（树形结构）',
          });
          return handleTree(data);
        },
        labelField: 'categoryName',
        valueField: 'id',
        childrenField: 'children',
        placeholder: '请选择上级产品类目表（树形结构）',
        treeDefaultExpandAll: true,
      },
      rules: 'selectRequired',
    },
    {
      fieldName: 'categoryCode',
      label: '类目编号',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入类目编号',
      },
    },
    {
      fieldName: 'categoryName',
      label: '类目名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入类目名称',
      },
    },
    {
      fieldName: 'categoryLevel',
      label: '类目层级（1=一级，2=二级，3=三级...）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入类目层级（1=一级，2=二级，3=三级...）',
      },
    },
    {
      fieldName: 'categoryPath',
      label: '类目路径（如：/1/2/3/）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入类目路径（如：/1/2/3/）',
      },
    },
    {
      fieldName: 'isLeaf',
      label: '是否叶子节点（0=分类目录，1=实际产品）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'productType',
      label: '产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）',
      },
    },
    {
      fieldName: 'productCode',
      label: '产品编号',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品编号',
      },
    },
    {
      fieldName: 'productDesc',
      label: '产品描述',
      component: 'Input',
      componentProps: {
        placeholder: '请输入产品描述',
      },
    },
    {
      fieldName: 'currencyType',
      label: '币种（字典: aicrm_currency_type）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择币种（字典: aicrm_currency_type）',
      },
    },
    {
      fieldName: 'interestRateMin',
      label: '最低利率/收益率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入最低利率/收益率（%）',
      },
    },
    {
      fieldName: 'interestRateMax',
      label: '最高利率/收益率（%）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入最高利率/收益率（%）',
      },
    },
    {
      fieldName: 'termMin',
      label: '最短期限（天）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入最短期限（天）',
      },
    },
    {
      fieldName: 'termMax',
      label: '最长期限（天）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入最长期限（天）',
      },
    },
    {
      fieldName: 'minAmount',
      label: '起购金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入起购金额',
      },
    },
    {
      fieldName: 'maxAmount',
      label: '最大金额',
      component: 'Input',
      componentProps: {
        placeholder: '请输入最大金额',
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
      fieldName: 'customerType',
      label: '适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）',
      component: 'Select',
      componentProps: {
        options: [],
        placeholder: '请选择适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）',
      },
    },
    {
      fieldName: 'status',
      label: '状态（0=停用，1=启用）',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'sortOrder',
      label: '排序',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入排序',
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
      fieldName: 'categoryCode',
      label: '类目编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入类目编号',
      },
    },
    {
      fieldName: 'categoryName',
      label: '类目名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入类目名称',
      },
    },
    {
      fieldName: 'parentId',
      label: '父类目ID（0表示顶级类目）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入父类目ID（0表示顶级类目）',
      },
    },
    {
      fieldName: 'categoryLevel',
      label: '类目层级（1=一级，2=二级，3=三级...）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入类目层级（1=一级，2=二级，3=三级...）',
      },
    },
    {
      fieldName: 'categoryPath',
      label: '类目路径（如：/1/2/3/）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入类目路径（如：/1/2/3/）',
      },
    },
    {
      fieldName: 'isLeaf',
      label: '是否叶子节点（0=分类目录，1=实际产品）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否叶子节点（0=分类目录，1=实际产品）',
      },
    },
    {
      fieldName: 'productType',
      label: '产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）',
      },
    },
    {
      fieldName: 'productCode',
      label: '产品编号',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品编号',
      },
    },
    {
      fieldName: 'productDesc',
      label: '产品描述',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入产品描述',
      },
    },
    {
      fieldName: 'currencyType',
      label: '币种（字典: aicrm_currency_type）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择币种（字典: aicrm_currency_type）',
      },
    },
    {
      fieldName: 'interestRateMin',
      label: '最低利率/收益率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入最低利率/收益率（%）',
      },
    },
    {
      fieldName: 'interestRateMax',
      label: '最高利率/收益率（%）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入最高利率/收益率（%）',
      },
    },
    {
      fieldName: 'termMin',
      label: '最短期限（天）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入最短期限（天）',
      },
    },
    {
      fieldName: 'termMax',
      label: '最长期限（天）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入最长期限（天）',
      },
    },
    {
      fieldName: 'minAmount',
      label: '起购金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入起购金额',
      },
    },
    {
      fieldName: 'maxAmount',
      label: '最大金额',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入最大金额',
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
      fieldName: 'customerType',
      label: '适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）',
      },
    },
    {
      fieldName: 'status',
      label: '状态（0=停用，1=启用）',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择状态（0=停用，1=启用）',
      },
    },
    {
      fieldName: 'sortOrder',
      label: '排序',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入排序',
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
export function useGridColumns(): VxeTableGridOptions<AicrmProductCategoryApi.ProductCategory>['columns'] {
  return [
    {
      field: 'id',
      title: '产品类目主键',
      minWidth: 120,
    },
    {
      field: 'categoryCode',
      title: '类目编号',
      minWidth: 120,
    },
    {
      field: 'categoryName',
      title: '类目名称',
      minWidth: 120,
      treeNode: true,
    },
    {
      field: 'parentId',
      title: '父类目ID（0表示顶级类目）',
      minWidth: 120,
    },
    {
      field: 'categoryLevel',
      title: '类目层级（1=一级，2=二级，3=三级...）',
      minWidth: 120,
    },
    {
      field: 'categoryPath',
      title: '类目路径（如：/1/2/3/）',
      minWidth: 120,
    },
    {
      field: 'isLeaf',
      title: '是否叶子节点（0=分类目录，1=实际产品）',
      minWidth: 120,
    },
    {
      field: 'productType',
      title: '产品类型（字典: aicrm_product_type，deposit=存款，loan=贷款，wealth=理财，fund=基金，insurance=保险，trust=信托，metal=贵金属，creditcard=信用卡）',
      minWidth: 120,
    },
    {
      field: 'productCode',
      title: '产品编号',
      minWidth: 120,
    },
    {
      field: 'productDesc',
      title: '产品描述',
      minWidth: 120,
    },
    {
      field: 'currencyType',
      title: '币种（字典: aicrm_currency_type）',
      minWidth: 120,
    },
    {
      field: 'interestRateMin',
      title: '最低利率/收益率（%）',
      minWidth: 120,
    },
    {
      field: 'interestRateMax',
      title: '最高利率/收益率（%）',
      minWidth: 120,
    },
    {
      field: 'termMin',
      title: '最短期限（天）',
      minWidth: 120,
    },
    {
      field: 'termMax',
      title: '最长期限（天）',
      minWidth: 120,
    },
    {
      field: 'minAmount',
      title: '起购金额',
      minWidth: 120,
    },
    {
      field: 'maxAmount',
      title: '最大金额',
      minWidth: 120,
    },
    {
      field: 'riskLevel',
      title: '风险等级（字典: aicrm_risk_level）',
      minWidth: 120,
    },
    {
      field: 'customerType',
      title: '适用客户类型（字典: aicrm_customer_type_scope，retail=零售，company=对公，all=全部）',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '状态（0=停用，1=启用）',
      minWidth: 120,
    },
    {
      field: 'sortOrder',
      title: '排序',
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