<script lang="ts" setup>
import type { AicrmPracticeVirtualCustomerApi } from '#/api/aicrm/practicevirtualcustomer';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { createPracticeVirtualCustomer, getPracticeVirtualCustomer, updatePracticeVirtualCustomer } from '#/api/aicrm/practicevirtualcustomer';
import { $t } from '#/locales';

import { useFormSchema } from '../data';

const emit = defineEmits(['success']);
const formData = ref<AicrmPracticeVirtualCustomerApi.PracticeVirtualCustomer>();
const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['CRM智能陪练-虚拟客户'])
    : $t('ui.actionTitle.create', ['CRM智能陪练-虚拟客户']);
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
    const data = (await formApi.getValues()) as AicrmPracticeVirtualCustomerApi.PracticeVirtualCustomer;
    try {
      await (formData.value?.id ? updatePracticeVirtualCustomer(data) : createPracticeVirtualCustomer(data));
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
    const data = modalApi.getData<AicrmPracticeVirtualCustomerApi.PracticeVirtualCustomer>();
    if (!data || !data.id) {
      return;
    }
    modalApi.lock();
    try {
      formData.value = await getPracticeVirtualCustomer(data.id);
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