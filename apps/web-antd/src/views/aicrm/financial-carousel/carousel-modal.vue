<script lang="ts" setup>
import type { CrmFinancialCarouselApi } from '#/api/crm/product/financial/carousel';
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { getDictOptions } from '@vben/hooks';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import {
  createCarousel,
  updateCarousel,
} from '#/api/crm/product/financial/carousel';

const emit = defineEmits(['success']);
const isEdit = ref(false);
const carouselId = ref<number | undefined>(undefined);

const formSchema = computed<VbenFormSchema[]>(() => [
  // 基本信息
  {
    fieldName: 'title',
    label: '轮播标题',
    component: 'Input',
    rules: 'required',
    componentProps: {
      placeholder: '请输入轮播标题',
      maxlength: 100,
    },
  },
  {
    fieldName: 'subtitle',
    label: '轮播副标题',
    component: 'Input',
    componentProps: {
      placeholder: '请输入轮播副标题',
      maxlength: 200,
    },
  },
  {
    fieldName: 'imageUrl',
    label: '轮播图片',
    component: 'ImageUpload',
    rules: 'required',
    formItemClass: 'col-span-2',
  },

  // 链接设置
  {
    fieldName: 'linkType',
    label: '链接类型',
    component: 'Select',
    componentProps: {
      allowClear: true,
      options: [
        { label: '产品详情', value: 'product' },
        { label: '外部链接', value: 'url' },
      ],
      placeholder: '请选择链接类型',
    },
  },
  {
    fieldName: 'linkId',
    label: '关联产品ID',
    component: 'InputNumber',
    componentProps: {
      placeholder: '当链接类型为产品详情时填写',
      min: 1,
      class: 'w-full',
    },
  },
  {
    fieldName: 'linkUrl',
    label: '外部链接URL',
    component: 'Input',
    formItemClass: 'col-span-2',
    componentProps: {
      placeholder: '当链接类型为外部链接时填写',
      maxlength: 500,
    },
  },

  // 角标设置
  {
    fieldName: 'badgeText',
    label: '角标文字',
    component: 'Input',
    componentProps: {
      placeholder: '如：AI 洞察、早报精选',
      maxlength: 20,
    },
  },
  {
    fieldName: 'badgeColor',
    label: '角标颜色',
    component: 'Input',
    componentProps: {
      type: 'color',
      placeholder: '请选择颜色',
      maxlength: 20,
    },
  },

  // 显示设置
  {
    fieldName: 'sort',
    label: '显示顺序',
    component: 'InputNumber',
    rules: 'required',
    defaultValue: 0,
    componentProps: {
      placeholder: '数字越小越靠前',
      min: 0,
      class: 'w-full',
    },
  },
  {
    fieldName: 'status',
    label: '状态',
    component: 'RadioGroup',
    rules: 'required',
    defaultValue: 1,
    componentProps: {
      options: getDictOptions('common_status', 'number'),
      optionType: 'button',
      buttonStyle: 'solid',
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

      if (isEdit.value && carouselId.value) {
        // 编辑
        await updateCarousel({
          id: carouselId.value,
          ...values,
        } as CrmFinancialCarouselApi.FinancialCarousel);
        message.success('更新成功');
      } else {
        // 新增
        await createCarousel(values as CrmFinancialCarouselApi.FinancialCarousel);
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
      carouselId.value = undefined;
      formApi.resetForm();
      return;
    }

    // 获取传入的数据（编辑模式）
    const data = modalApi.getData<CrmFinancialCarouselApi.FinancialCarousel>();
    if (data?.id) {
      isEdit.value = true;
      carouselId.value = data.id;

      // 设置表单值
      await formApi.setValues({
        title: data.title,
        subtitle: data.subtitle,
        imageUrl: data.imageUrl,
        linkType: data.linkType,
        linkId: data.linkId,
        linkUrl: data.linkUrl,
        badgeText: data.badgeText,
        badgeColor: data.badgeColor,
        sort: data.sort || 0,
        status: data.status,
      });
    }
  },
});

defineExpose({ modalApi });
</script>

<template>
  <Modal :title="isEdit ? '编辑轮播' : '新增轮播'" class="!w-[800px]">
    <div class="mx-4 my-2">
      <Form />
    </div>
  </Modal>
</template>
