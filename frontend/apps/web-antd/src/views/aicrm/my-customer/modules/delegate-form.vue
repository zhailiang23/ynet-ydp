<script lang="ts" setup>
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { delegateCustomers } from '#/api/aicrm/customerassignment';
import { getSimpleUserList } from '#/api/system/user';

const emit = defineEmits(['success']);
const customerIds = ref<number[]>([]);

const formSchema = computed<VbenFormSchema[]>(() => [
  {
    fieldName: 'delegateToUserId',
    label: '托管给',
    rules: 'required',
    component: 'ApiSelect',
    componentProps: {
      api: getSimpleUserList,
      labelField: 'nickname',
      valueField: 'id',
      placeholder: '请选择托管用户',
      showSearch: true,
      filterOption: (input: string, option: any) => {
        return option.nickname?.toLowerCase().includes(input.toLowerCase());
      },
    },
  },
  {
    fieldName: 'delegateStartDate',
    label: '托管开始日期',
    rules: 'required',
    component: 'DatePicker',
    componentProps: {
      placeholder: '请选择托管开始日期',
      class: 'w-full',
      format: 'YYYY-MM-DD',
      valueFormat: 'YYYY-MM-DD',
    },
  },
  {
    fieldName: 'delegateEndDate',
    label: '托管结束日期',
    rules: 'required',
    component: 'DatePicker',
    componentProps: {
      placeholder: '请选择托管结束日期',
      class: 'w-full',
      format: 'YYYY-MM-DD',
      valueFormat: 'YYYY-MM-DD',
    },
  },
  {
    fieldName: 'delegateReason',
    label: '托管原因',
    component: 'Textarea',
    componentProps: {
      placeholder: '请输入托管原因（可选）',
      rows: 4,
      maxlength: 500,
      showCount: true,
    },
  },
]);

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
    formItemClass: 'col-span-2',
    labelWidth: 120,
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
      await delegateCustomers({
        customerIds: customerIds.value,
        ...values,
      } as AicrmCustomerAssignmentApi.DelegateCustomerReq);
      await modalApi.close();
      emit('success');
      message.success('托管客户成功');
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
    const data = modalApi.getData<{ customerIds?: number[] }>();
    console.log('Delegate Modal opened with data:', data);
    if (data?.customerIds) {
      customerIds.value = data.customerIds;
    }
  },
});

defineExpose({ modalApi });
</script>

<template>
  <Modal title="托管客户" class="!w-[600px]">
    <div class="mx-4">
      <div class="mb-4 rounded bg-blue-50 p-3 text-sm dark:bg-blue-950/30">
        <div class="text-gray-600 dark:text-gray-400">
          将托管 <span class="font-bold text-blue-600 dark:text-blue-400">{{ customerIds.length }}</span> 个主办客户给其他用户，在托管期间由托管用户负责客户维护
        </div>
      </div>
      <Form />
    </div>
  </Modal>
</template>
