<script lang="ts" setup>
import type { AicrmCustomerApi } from '#/api/aicrm/customer';

import { ref, watch } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { useVbenVxeGrid } from '#/adapter/vxe-table';

import { createAssignmentGridOptions } from '../shared/assignment-grid-config';

const customerInfo = ref<AicrmCustomerApi.Customer>();
const currentCustomerId = ref<number>(0);

// 归属关系列表 VxeTable 配置
const [AssignmentGrid, assignmentGridApi] = useVbenVxeGrid({
  gridOptions: {
    ...createAssignmentGridOptions(currentCustomerId.value, 500),
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          if (!currentCustomerId.value) {
            return { list: [], total: 0 };
          }
          const { getCustomerAssignmentPage } = await import('#/api/aicrm/customerassignment');
          return await getCustomerAssignmentPage({
            customerId: currentCustomerId.value,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          });
        },
      },
    },
  },
});

// 当客户信息变化时，刷新表格
watch(
  () => customerInfo.value?.id,
  (customerId) => {
    if (customerId) {
      currentCustomerId.value = customerId;
      // 刷新表格
      assignmentGridApi.query();
    }
  },
);

const [Modal, modalApi] = useVbenModal({
  onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      customerInfo.value = undefined;
      return;
    }
    // 加载数据
    const data = modalApi.getData<AicrmCustomerApi.Customer>();
    if (data) {
      customerInfo.value = data;
    }
  },
});

defineExpose({ modalApi });
</script>

<template>
  <Modal :title="`客户归属关系 - ${customerInfo?.customerName || ''}`" class="!w-[1200px]">
    <div class="mx-4">
      <AssignmentGrid />
    </div>
  </Modal>
</template>
