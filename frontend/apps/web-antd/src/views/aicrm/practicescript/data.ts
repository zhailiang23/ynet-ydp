import type { VbenFormSchema } from '#/adapter/form';
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmPracticeScriptApi } from '#/api/aicrm/practicescript';

import { h } from 'vue';

import { getDictOptions } from '@vben/hooks';
import { Button } from 'ant-design-vue';

import { getPracticeCasePage } from '#/api/aicrm/practicecase';
import { getPracticeMaterialPage } from '#/api/aicrm/practicematerial';
import { generateScriptContent, updatePracticeScript } from '#/api/aicrm/practicescript';
import { getPracticeSkillPage } from '#/api/aicrm/practiceskill';
import { getPracticeVirtualCustomerPage } from '#/api/aicrm/practicevirtualcustomer';
import { getRangePickerDefaultProps } from '#/utils';
import { message } from 'ant-design-vue';

/** 新增/修改的表单 */
export function useFormSchema(formApi?: any, isEdit?: boolean): VbenFormSchema[] {
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
      fieldName: 'name',
      label: '剧本名称',
      rules: 'required',
      component: 'Input',
      componentProps: {
        placeholder: '请输入剧本名称',
      },
    },
    {
      fieldName: 'marketingStep',
      label: '营销环节',
      rules: 'required',
      component: 'Select',
      componentProps: {
        placeholder: '请选择营销环节',
        options: getDictOptions('aicrm_marketing_step'),
      },
    },
    {
      fieldName: 'difficultyLevel',
      label: '场景复杂度',
      rules: 'required',
      component: 'Select',
      componentProps: {
        placeholder: '请选择场景复杂度',
        options: getDictOptions('aicrm_difficulty_level'),
      },
    },
    {
      fieldName: 'materialIds',
      label: '关联培训文件',
      rules: 'required',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择关联培训文件',
        allowClear: true,
        api: async () => {
          const result = await getPracticeMaterialPage({
            pageNo: 1,
            pageSize: 100,
            fileType: 'training_manual',
          });
          return result.list.map((item: any) => ({
            label: item.name,
            value: String(item.id),
          }));
        },
      },
    },
    {
      fieldName: 'caseId',
      label: '关联案例',
      rules: 'required',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择关联案例',
        allowClear: true,
        api: async () => {
          const result = await getPracticeCasePage({
            pageNo: 1,
            pageSize: 100,
          });
          return result.list.map((item: any) => ({
            label: item.title,
            value: item.id,
          }));
        },
      },
    },
    {
      fieldName: 'skillId',
      label: '关联销售技巧',
      rules: 'required',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择关联销售技巧',
        allowClear: true,
        api: async () => {
          const result = await getPracticeSkillPage({
            pageNo: 1,
            pageSize: 100,
          });
          return result.list.map((item: any) => ({
            label: item.name,
            value: item.id,
          }));
        },
      },
    },
    {
      fieldName: 'virtualCustomerId',
      label: '关联虚拟客户',
      component: 'ApiSelect',
      componentProps: {
        placeholder: '请选择关联虚拟客户',
        allowClear: true,
        api: async () => {
          const result = await getPracticeVirtualCustomerPage({
            pageNo: 1,
            pageSize: 100,
          });
          return result.list.map((item: any) => ({
            label: item.name,
            value: item.id,
          }));
        },
      },
    },
    {
      fieldName: 'content',
      label: '剧本内容',
      component: 'Textarea',
      componentProps: {
        placeholder: '可以手工编辑内容，或点击生成按钮重新生成',
        rows: 10,
      },
      // 仅在编辑模式下显示
      dependencies: {
        triggerFields: ['id'],
        show: () => isEdit,
      },
    },
    {
      fieldName: 'contentAction',
      label: ' ',
      component: 'DefaultButton',
      formItemClass: 'col-span-2',
      // 仅在编辑模式下显示
      dependencies: {
        triggerFields: ['id'],
        show: () => isEdit,
      },
      renderComponentContent: () => {
        return {
          default: () =>
            h(
              Button,
              {
                type: 'primary',
                loading: false,
                onClick: async () => {
                  if (!formApi) {
                    message.error('表单未初始化');
                    return;
                  }

                  try {
                    // 验证必填字段
                    const { valid } = await formApi.validate();
                    if (!valid) {
                      message.error('请先填写完整表单信息');
                      return;
                    }

                    const values = await formApi.getValues();

                    // 显示加载提示
                    const hideLoading = message.loading('正在生成剧本内容...', 0);

                    try {
                      // 直接调用 API 生成剧本内容（不先保存）
                      // 因为保存会创建新版本导致版本号冲突
                      const content = await generateScriptContent({
                        scriptId: values.id,
                        caseId: values.caseId,
                        materialIds: values.materialIds,
                        skillId: values.skillId,
                        marketingStep: values.marketingStep,
                        difficultyLevel: values.difficultyLevel,
                        scriptDescription: values.description || '个性化陪练剧本',
                      });

                      // 更新表单中的 content 字段
                      await formApi.setFieldValue('content', content);
                      message.success('剧本内容生成成功！点击确定保存');
                    } catch (error: any) {
                      console.error('生成剧本内容失败:', error);
                      message.error(error.message || '生成剧本内容失败，请重试');
                    } finally {
                      hideLoading();
                    }
                  } catch (error: any) {
                    console.error('表单验证失败:', error);
                  }
                },
              },
              () => '生成',
            ),
        };
      },
    },
  ];
}

/** 列表的搜索表单 */
export function useGridFormSchema(): VbenFormSchema[] {
  return [
    {
      fieldName: 'name',
      label: '剧本名称',
      component: 'Input',
      componentProps: {
        allowClear: true,
        placeholder: '请输入剧本名称',
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
      title: '剧本ID',
      minWidth: 100,
    },
    {
      field: 'name',
      title: '剧本名称',
      minWidth: 200,
    },
    {
      field: 'version',
      title: '版本号',
      minWidth: 100,
    },
    {
      field: 'marketingStep',
      title: '营销环节',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_marketing_step' } },
    },
    {
      field: 'difficultyLevel',
      title: '难度等级',
      minWidth: 120,
      cellRender: { name: 'CellDict', props: { type: 'aicrm_difficulty_level' } },
    },
    {
      field: 'caseName',
      title: '关联销售案例',
      minWidth: 180,
    },
    {
      field: 'skillName',
      title: '关联销售技巧',
      minWidth: 180,
    },
    {
      field: 'materialNames',
      title: '关联培训文件',
      minWidth: 200,
    },
    {
      field: 'virtualCustomerName',
      title: '关联虚拟客户',
      minWidth: 180,
    },
    {
      field: 'createTime',
      title: '创建时间',
      minWidth: 180,
      formatter: 'formatDateTime',
    },
    {
      title: '操作',
      width: 150,
      fixed: 'right',
      slots: { default: 'actions' },
    },
  ];
}