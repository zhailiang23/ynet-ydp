<script lang="ts" setup>
import type { AicrmCustomerWorkApi } from '#/api/aicrm/customerwork';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { createCustomerWork, getCustomerWork, updateCustomerWork } from '#/api/aicrm/customerwork';
import { $t } from '#/locales';

import { useFormSchema } from '../data';

const emit = defineEmits(['success']);
const formData = ref<AicrmCustomerWorkApi.CustomerWork>();
const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['客户工作或经营信息表'])
    : $t('ui.actionTitle.create', ['客户工作或经营信息表']);
});

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
    formItemClass: 'col-span-2',
    labelWidth: 80,
  },
  layout: 'horizontal',
  schema: useFormSchema(),
  showDefaultActions: false,
});

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const { valid } = await formApi.validate();
    if (!valid) {
      return;
    }
    modalApi.lock();
    // 提交表单
    const data = (await formApi.getValues()) as AicrmCustomerWorkApi.CustomerWork;
    try {
      await (formData.value?.id ? updateCustomerWork(data) : createCustomerWork(data));
      // 关闭并提示
      await modalApi.close();
      emit('success');
      message.success($t('ui.actionMessage.operationSuccess'));
    } finally {
      modalApi.unlock();
    }
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      formData.value = undefined;
      return;
    }
    // 加载数据
    const data = modalApi.getData<AicrmCustomerWorkApi.CustomerWork>();
    if (!data || !data.id) {
      return;
    }
    modalApi.lock();
    try {
      formData.value = await getCustomerWork(data.id);
      // 设置到 values
      await formApi.setValues(formData.value);
    } finally {
      modalApi.unlock();
    }
  },
});
</script>

<template>
  <Modal :title="getTitle">
    <Form class="mx-4" />
  </Modal>
</template>