<script setup lang="ts">
import type { AicrmPotentialCustomerApi } from '#/api/aicrm/potentialcustomer';

import { ref } from 'vue';

import { useVbenForm, useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import {
  createPotentialCustomer,
  updatePotentialCustomer,
} from '#/api/aicrm/potentialcustomer';

import { useFormSchema } from '../data';

const emit = defineEmits<{
  success: [];
}>();

const isEdit = ref(false);
const customerId = ref<number | undefined>(undefined);

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
    formItemClass: 'col-span-1',
    labelWidth: 120,
  },
  layout: 'horizontal',
  schema: useFormSchema(),
  wrapperClass: 'grid-cols-1',
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

      if (isEdit.value && customerId.value) {
        // 编辑
        await updatePotentialCustomer({
          id: customerId.value,
          ...values,
        } as AicrmPotentialCustomerApi.PotentialCustomer);
        message.success('更新成功');
      } else {
        // 新增
        await createPotentialCustomer(values as AicrmPotentialCustomerApi.PotentialCustomer);
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
      customerId.value = undefined;
      formApi.resetForm();
      return;
    }

    // 获取传入的数据（编辑模式）
    const data = modalApi.getData<AicrmPotentialCustomerApi.PotentialCustomer>();
    if (data?.id) {
      isEdit.value = true;
      customerId.value = data.id;

      // 设置表单值
      await formApi.setValues(data);
    }
  },
});

defineExpose({ modalApi });
</script>

<template>
  <Modal :title="isEdit ? '编辑潜客' : '新增潜客'" class="!w-[800px]">
    <div class="mx-4 my-2">
      <Form />
    </div>
  </Modal>
</template>
