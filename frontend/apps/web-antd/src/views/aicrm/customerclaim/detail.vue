<script setup lang="ts">
import type { AicrmCustomerClaimApi } from '#/api/aicrm/customerclaim';

import { computed, onMounted, ref } from 'vue';
import { useRoute } from 'vue-router';

import { ContentWrap } from '@vben/common-ui';

import { getCustomerClaimApplication } from '#/api/aicrm/customerclaim';
import { Description } from '#/components/description';

import { useDetailFormSchema } from './data';

const props = defineProps<{
  id: string;
}>();
const detailLoading = ref(false);
const detailData = ref<AicrmCustomerClaimApi.CustomerClaimApplication>();

const { query } = useRoute();
const queryId = computed(() => query.id as string);

async function getDetailData() {
  try {
    detailLoading.value = true;
    detailData.value = await getCustomerClaimApplication(Number(props.id || queryId.value));
  } finally {
    detailLoading.value = false;
  }
}

onMounted(() => {
  getDetailData();
});
</script>

<template>
  <ContentWrap class="m-2">
    <Description
      :data="detailData"
      :loading="detailLoading"
      :schema="useDetailFormSchema()"
      :component-props="{
        column: 1,
        bordered: true,
        size: 'small',
      }"
    />
  </ContentWrap>
</template>

<style lang="scss" scoped>
:deep(.ant-descriptions-item-label) {
  width: 150px;
}
</style>
