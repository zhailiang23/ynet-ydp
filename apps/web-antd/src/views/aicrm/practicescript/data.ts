import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeScriptApi } from '#/api/aicrm/practicescript';

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
      fieldName: 'scriptNo',
      label: '剧本编号（标识同一个剧本的不同版本）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入剧本编号（标识同一个剧本的不同版本）',
      },
    },
    {
      fieldName: 'version',
      label: '版本号（如 v1.0, v1.1, v2.0）',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入版本号（如 v1.0, v1.1, v2.0）',
      },
    },
    {
      fieldName: 'isLatest',
      label: '是否最新版本',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'parentVersionId',
      label: '父版本ID（用于追溯版本历史）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入父版本ID（用于追溯版本历史）',
      },
    },
    {
      fieldName: 'versionDescription',
      label: '版本说明（本次修改的内容）',
      component: 'RichTextarea',
    },
    {
      fieldName: 'status',
      label: '版本状态：字典 aicrm_script_status',
      rules: 'required',
      component: 'RadioGroup',
      componentProps: {
        options: [],
        buttonStyle: 'solid',
        optionType: 'button',
      },
    },
    {
      fieldName: 'name',
      label: '剧本名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入剧本名称',
      },
    },
    {
      fieldName: 'description',
      label: '剧本描述',
      component: 'RichTextarea',
    },
    {
      fieldName: 'difficultyLevel',
      label: '难度等级：字典 aicrm_difficulty_level',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入难度等级：字典 aicrm_difficulty_level',
      },
    },
    {
      fieldName: 'marketingStep',
      label: '营销环节：字典 aicrm_marketing_step',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入营销环节：字典 aicrm_marketing_step',
      },
    },
    {
      fieldName: 'caseId',
      label: '关联销售案例ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联销售案例ID',
      },
    },
    {
      fieldName: 'skillId',
      label: '关联销售技巧ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联销售技巧ID',
      },
    },
    {
      fieldName: 'virtualCustomerId',
      label: '关联虚拟客户ID',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联虚拟客户ID',
      },
    },
    {
      fieldName: 'materialIds',
      label: '关联培训文件ID列表（多个ID逗号分隔）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入关联培训文件ID列表（多个ID逗号分隔）',
      },
    },
    {
      fieldName: 'content',
      label: '剧本内容（AI生成）',
      component: 'RichTextarea',
    },
    {
      fieldName: 'contentEdit',
      label: '手工调整内容（用户编辑）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入手工调整内容（用户编辑）',
      },
    },
    {
      fieldName: 'usageCount',
      label: '使用次数（该版本被使用的次数）',
      component: 'Input',
      componentProps: {
        placeholder: '请输入使用次数（该版本被使用的次数）',
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'scriptNo',
      label: '剧本编号（标识同一个剧本的不同版本）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入剧本编号（标识同一个剧本的不同版本）',
      },
    },
    {
      fieldName: 'version',
      label: '版本号（如 v1.0, v1.1, v2.0）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入版本号（如 v1.0, v1.1, v2.0）',
      },
    },
    {
      fieldName: 'isLatest',
      label: '是否最新版本',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择是否最新版本',
      },
    },
    {
      fieldName: 'parentVersionId',
      label: '父版本ID（用于追溯版本历史）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入父版本ID（用于追溯版本历史）',
      },
    },
    {
      fieldName: 'versionDescription',
      label: '版本说明（本次修改的内容）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入版本说明（本次修改的内容）',
      },
    },
    {
      fieldName: 'status',
      label: '版本状态：字典 aicrm_script_status',
      component: 'Select',
      componentProps: {
        allowClear: true,
        options: [],
        placeholder: '请选择版本状态：字典 aicrm_script_status',
      },
    },
    {
      fieldName: 'name',
      label: '剧本名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入剧本名称',
      },
    },
    {
      fieldName: 'description',
      label: '剧本描述',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入剧本描述',
      },
    },
    {
      fieldName: 'difficultyLevel',
      label: '难度等级：字典 aicrm_difficulty_level',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入难度等级：字典 aicrm_difficulty_level',
      },
    },
    {
      fieldName: 'marketingStep',
      label: '营销环节：字典 aicrm_marketing_step',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入营销环节：字典 aicrm_marketing_step',
      },
    },
    {
      fieldName: 'caseId',
      label: '关联销售案例ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联销售案例ID',
      },
    },
    {
      fieldName: 'skillId',
      label: '关联销售技巧ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联销售技巧ID',
      },
    },
    {
      fieldName: 'virtualCustomerId',
      label: '关联虚拟客户ID',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联虚拟客户ID',
      },
    },
    {
      fieldName: 'materialIds',
      label: '关联培训文件ID列表（多个ID逗号分隔）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入关联培训文件ID列表（多个ID逗号分隔）',
      },
    },
    {
      fieldName: 'content',
      label: '剧本内容（AI生成）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入剧本内容（AI生成）',
      },
    },
    {
      fieldName: 'contentEdit',
      label: '手工调整内容（用户编辑）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入手工调整内容（用户编辑）',
      },
    },
    {
      fieldName: 'usageCount',
      label: '使用次数（该版本被使用的次数）',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入使用次数（该版本被使用的次数）',
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
export function useGridColumns(): VxeTableGridOptions<AicrmPracticeScriptApi.PracticeScript>['columns'] {
  return [
  { type: 'checkbox', width: 40 },
    {
      field: 'id',
      title: '剧本版本ID（主键）',
      minWidth: 120,
    },
    {
      field: 'scriptNo',
      title: '剧本编号（标识同一个剧本的不同版本）',
      minWidth: 120,
    },
    {
      field: 'version',
      title: '版本号（如 v1.0, v1.1, v2.0）',
      minWidth: 120,
    },
    {
      field: 'isLatest',
      title: '是否最新版本',
      minWidth: 120,
    },
    {
      field: 'parentVersionId',
      title: '父版本ID（用于追溯版本历史）',
      minWidth: 120,
    },
    {
      field: 'versionDescription',
      title: '版本说明（本次修改的内容）',
      minWidth: 120,
    },
    {
      field: 'status',
      title: '版本状态：字典 aicrm_script_status',
      minWidth: 120,
    },
    {
      field: 'name',
      title: '剧本名称',
      minWidth: 120,
    },
    {
      field: 'description',
      title: '剧本描述',
      minWidth: 120,
    },
    {
      field: 'difficultyLevel',
      title: '难度等级：字典 aicrm_difficulty_level',
      minWidth: 120,
    },
    {
      field: 'marketingStep',
      title: '营销环节：字典 aicrm_marketing_step',
      minWidth: 120,
    },
    {
      field: 'caseId',
      title: '关联销售案例ID',
      minWidth: 120,
    },
    {
      field: 'skillId',
      title: '关联销售技巧ID',
      minWidth: 120,
    },
    {
      field: 'virtualCustomerId',
      title: '关联虚拟客户ID',
      minWidth: 120,
    },
    {
      field: 'materialIds',
      title: '关联培训文件ID列表（多个ID逗号分隔）',
      minWidth: 120,
    },
    {
      field: 'content',
      title: '剧本内容（AI生成）',
      minWidth: 120,
    },
    {
      field: 'contentEdit',
      title: '手工调整内容（用户编辑）',
      minWidth: 120,
    },
    {
      field: 'usageCount',
      title: '使用次数（该版本被使用的次数）',
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