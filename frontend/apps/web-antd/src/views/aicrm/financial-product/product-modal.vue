<script lang="ts" setup>
import type { CrmFinancialProductApi } from '#/api/crm/product/financial';
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { getDictOptions } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import {
  createFinancialProduct,
  updateFinancialProduct,
} from '#/api/crm/product/financial';
import { getSimpleProductCatalogList } from '#/api/crm/product/catalog';

const emit = defineEmits(['success']);
const isEdit = ref(false);
const productId = ref<number | undefined>(undefined);
const catalogOptions = ref<any[]>([]);

// 加载产品目录列表
async function loadCatalogList() {
  const catalogs = await getSimpleProductCatalogList();
  catalogOptions.value = catalogs
    .filter((c: any) => c.status === 1) // 只显示启用状态的目录
    .map((c: any) => ({
      label: c.name,
      value: c.id,
    }));
}

const formSchema = computed<VbenFormSchema[]>(() => [
  // 基本信息
  {
    fieldName: 'productCode',
    label: '产品代码',
    component: 'Input',
    rules: 'required|max:50',
    componentProps: {
      placeholder: '请输入产品代码',
      maxlength: 50,
      disabled: isEdit.value, // 编辑时不允许修改产品代码
    },
  },
  {
    fieldName: 'productName',
    label: '产品名称',
    component: 'Input',
    rules: 'required|max:100',
    componentProps: {
      placeholder: '请输入产品名称',
      maxlength: 100,
    },
  },
  {
    fieldName: 'catalogId',
    label: '产品目录',
    component: 'Select',
    rules: 'required',
    componentProps: {
      options: catalogOptions.value,
      placeholder: '请选择产品目录',
      showSearch: true,
      filterOption: (input: string, option: any) => {
        return option.label?.toLowerCase().includes(input.toLowerCase());
      },
    },
  },
  {
    fieldName: 'category',
    label: '产品分类',
    component: 'Select',
    rules: 'required',
    componentProps: {
      options: getDictOptions('aicrm_fin_product_category'),
      placeholder: '请选择产品分类',
    },
  },
  {
    fieldName: 'riskLevel',
    label: '风险等级',
    component: 'Select',
    rules: 'required',
    componentProps: {
      options: getDictOptions('aicrm_fin_risk_level'),
      placeholder: '请选择风险等级',
    },
  },

  // 收益信息
  {
    fieldName: 'expectedReturn',
    label: '预期收益率(%)',
    component: 'InputNumber',
    componentProps: {
      placeholder: '请输入预期收益率',
      min: 0,
      max: 100,
      precision: 2,
      class: 'w-full',
    },
  },
  {
    fieldName: 'returnType',
    label: '收益类型',
    component: 'Input',
    componentProps: {
      placeholder: '如：业绩比较基准、近一年收益、预定利率',
      maxlength: 50,
    },
  },

  // 期限信息
  {
    fieldName: 'duration',
    label: '产品期限',
    component: 'Input',
    componentProps: {
      placeholder: '如：360天、10年',
      maxlength: 50,
    },
  },
  {
    fieldName: 'durationDays',
    label: '期限天数',
    component: 'InputNumber',
    componentProps: {
      placeholder: '用于排序',
      min: 0,
      class: 'w-full',
    },
  },

  // 投资金额
  {
    fieldName: 'minimumInvestment',
    label: '起购金额',
    component: 'InputNumber',
    rules: 'required',
    componentProps: {
      placeholder: '请输入起购金额',
      min: 0,
      precision: 2,
      class: 'w-full',
    },
  },
  {
    fieldName: 'investmentUnit',
    label: '金额单位',
    component: 'Input',
    defaultValue: '元',
    componentProps: {
      placeholder: '默认：元',
      maxlength: 10,
    },
  },

  // 产品状态
  {
    fieldName: 'status',
    label: '产品状态',
    component: 'RadioGroup',
    rules: 'required',
    defaultValue: 1,
    componentProps: {
      options: getDictOptions('common_status', 'number'),
      optionType: 'button',
      buttonStyle: 'solid',
    },
  },
  {
    fieldName: 'isHot',
    label: '是否热销',
    component: 'RadioGroup',
    defaultValue: 0,
    componentProps: {
      options: [
        { label: '是', value: 1 },
        { label: '否', value: 0 },
      ],
      optionType: 'button',
      buttonStyle: 'solid',
    },
  },
  {
    fieldName: 'isNew',
    label: '是否新品',
    component: 'RadioGroup',
    defaultValue: 0,
    componentProps: {
      options: [
        { label: '是', value: 1 },
        { label: '否', value: 0 },
      ],
      optionType: 'button',
      buttonStyle: 'solid',
    },
  },

  // 产品详情
  {
    fieldName: 'description',
    label: '产品描述',
    component: 'Textarea',
    formItemClass: 'col-span-2',
    componentProps: {
      placeholder: '请输入产品描述',
      rows: 3,
      maxlength: 1000,
      showCount: true,
    },
  },
  {
    fieldName: 'features',
    label: '产品特色',
    component: 'Textarea',
    formItemClass: 'col-span-2',
    componentProps: {
      placeholder: '请输入产品特色',
      rows: 3,
      maxlength: 1000,
      showCount: true,
    },
  },
  {
    fieldName: 'saleChannel',
    label: '销售渠道',
    component: 'Input',
    componentProps: {
      placeholder: '如：自营、代销',
      maxlength: 50,
    },
  },

  // 标签
  {
    fieldName: 'tags',
    label: '产品标签',
    component: 'Select',
    formItemClass: 'col-span-2',
    componentProps: {
      mode: 'tags',
      placeholder: '输入标签后按回车添加，如：高收益、低风险、热销',
      maxTagCount: 10,
      tokenSeparators: [',', '，'],
    },
  },

  // AI 相关
  {
    fieldName: 'aiMatchScore',
    label: 'AI 匹配度',
    component: 'InputNumber',
    componentProps: {
      placeholder: '0-100',
      min: 0,
      max: 100,
      class: 'w-full',
    },
  },
  {
    fieldName: 'aiKeywords',
    label: 'AI 关键词',
    component: 'Input',
    componentProps: {
      placeholder: '多个关键词用逗号分隔',
      maxlength: 500,
    },
  },

  // 展示信息
  {
    fieldName: 'sort',
    label: '排序',
    component: 'InputNumber',
    defaultValue: 0,
    componentProps: {
      placeholder: '数字越小越靠前',
      min: 0,
      class: 'w-full',
    },
  },
  {
    fieldName: 'bannerImage',
    label: '轮播图片',
    component: 'Input',
    formItemClass: 'col-span-2',
    componentProps: {
      placeholder: '请输入图片URL',
      maxlength: 255,
    },
  },
]);

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
    formItemClass: 'col-span-1',
    labelWidth: 120,
  },
  layout: 'horizontal',
  wrapperClass: 'grid grid-cols-2 gap-x-4',
  schema: formSchema,
  showDefaultActions: false,
});

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const { valid } = await formApi.validate();
    if (!valid) {
      return;
    }
    modalApi.lock();
    try {
      const values = await formApi.getValues();

      // 处理标签字段 - 确保是数组类型
      if (values.tags && !Array.isArray(values.tags)) {
        values.tags = [];
      }

      if (isEdit.value && productId.value) {
        // 编辑
        await updateFinancialProduct({
          id: productId.value,
          ...values,
        } as CrmFinancialProductApi.FinancialProduct);
        message.success('更新成功');
      } else {
        // 新增
        await createFinancialProduct(values as CrmFinancialProductApi.FinancialProduct);
        message.success('创建成功');
      }

      await modalApi.close();
      emit('success');
    } finally {
      modalApi.unlock();
    }
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      // 关闭时重置表单
      isEdit.value = false;
      productId.value = undefined;
      formApi.resetForm();
      return;
    }

    // 打开时加载产品目录列表
    await loadCatalogList();

    // 获取传入的数据（编辑模式）
    const data = modalApi.getData<CrmFinancialProductApi.FinancialProduct>();
    if (data?.id) {
      isEdit.value = true;
      productId.value = data.id;

      // 设置表单值
      await formApi.setValues({
        productCode: data.productCode,
        productName: data.productName,
        catalogId: data.catalogId,
        category: data.category,
        riskLevel: data.riskLevel,
        expectedReturn: data.expectedReturn,
        returnType: data.returnType,
        duration: data.duration,
        durationDays: data.durationDays,
        minimumInvestment: data.minimumInvestment,
        investmentUnit: data.investmentUnit || '元',
        status: data.status,
        isHot: data.isHot || 0,
        isNew: data.isNew || 0,
        description: data.description,
        features: data.features,
        saleChannel: data.saleChannel,
        tags: data.tags || [],
        aiMatchScore: data.aiMatchScore,
        aiKeywords: data.aiKeywords,
        sort: data.sort || 0,
        bannerImage: data.bannerImage,
      });
    }
  },
});

defineExpose({ modalApi });
</script>

<template>
  <Modal :title="isEdit ? '编辑金融产品' : '新增金融产品'" class="!w-[900px]">
    <div class="mx-4 my-2">
      <Form />
    </div>
  </Modal>
</template>
