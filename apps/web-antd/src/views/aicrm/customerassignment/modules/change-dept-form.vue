<script lang="ts" setup>
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';
import type { VbenFormSchema } from '#/adapter/form';

import { ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { changeDept } from '#/api/aicrm/customerassignment';

const emit = defineEmits(['success']);
const customerIds = ref<number[]>([]);

const formSchema: VbenFormSchema[] = [
  {
    fieldName: 'newDeptId',
    label: '新部门ID',
    rules: 'required',
    component: 'Input',
    componentProps: {
      placeholder: '请输入新部门ID',
    },
  },
  {
    fieldName: 'newUserId',
    label: '新客户经理ID',
    rules: 'required',
    component: 'Input',
    componentProps: {
      placeholder: '请输入新客户经理ID',
    },
  },
  {
    fieldName: 'changeReason',
    label: '变更原因',
    rules: 'required',
    component: 'Textarea',
    componentProps: {
      placeholder: '请输入变更原因（必填）',
      rows: 4,
      maxlength: 500,
      showCount: true,
    },
  },
];

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
    formItemClass: 'col-span-2',
    labelWidth: 130,
  },
  layout: 'horizontal',
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
    const values = await formApi.getValues();
    try {
      await changeDept({
        customerIds: customerIds.value,
        ...values,
      } as AicrmCustomerAssignmentApi.ChangeDeptReq);
      await modalApi.close();
      emit('success');
      message.success('批量变更主办部门成功');
    } finally {
      modalApi.unlock();
    }
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      customerIds.value = [];
      formApi.resetForm();
      return;
    }
    const data = modalApi.getData<{ customerIds: number[] }>();
    if (data && data.customerIds) {
      customerIds.value = data.customerIds;
    }
  },
});
</script>

<template>
  <Modal title="批量变更主办部门" class="!w-[600px]">
    <div class="mx-4">
      <div class="mb-4 rounded bg-blue-50 p-3 text-sm dark:bg-blue-950/30">
        <div class="text-gray-600 dark:text-gray-400">
          将变更 <span class="font-bold text-blue-600 dark:text-blue-400">{{ customerIds.length }}</span> 个客户的主办部门
        </div>
      </div>
      <Form />
    </div>
  </Modal>
</template>
