<script lang="ts" setup>
import { computed, onMounted, ref } from 'vue';

import dayjs from 'dayjs';

import { getCustomerIdentityPage } from '#/api/aicrm/customeridentity';
import type { AicrmCustomerIdentityApi } from '#/api/aicrm/customeridentity';
import { getValidityStatus, maskIdNumber } from '#/views/aicrm/utils/identity';

defineOptions({
  name: 'RetailCustomerIdentityList',
});

// 接收 props
const props = defineProps<{
  customerId: number;
}>();

// 数据
const loading = ref(false);
const dataSource = ref<AicrmCustomerIdentityApi.CustomerIdentity[]>([]);
const pagination = ref({
  current: 1,
  pageSize: 10,
  total: 0,
});

// 表格列配置
const columns = computed(() => [
  {
    title: '序号',
    dataIndex: 'index',
    width: 70,
    align: 'center' as const,
    customRender: ({ index }: { index: number }) => {
      return (pagination.value.current - 1) * pagination.value.pageSize + index + 1;
    },
  },
  {
    title: '证件类型',
    dataIndex: 'identityType',
    width: 120,
  },
  {
    title: '证件名称',
    dataIndex: 'identityName',
    width: 120,
  },
  {
    title: '证件号码',
    dataIndex: 'identityNo',
    width: 180,
    customRender: ({ text }: { text: string }) => {
      return maskIdNumber(text);
    },
  },
  {
    title: '签发日期',
    dataIndex: 'issueDate',
    width: 120,
    customRender: ({ text }: { text: string }) => {
      return text ? dayjs(text).format('YYYY-MM-DD') : '-';
    },
  },
  {
    title: '失效日期',
    dataIndex: 'expiryDate',
    width: 120,
    customRender: ({ text }: { text: string }) => {
      return text ? dayjs(text).format('YYYY-MM-DD') : '-';
    },
  },
  {
    title: '有效性',
    dataIndex: 'validity',
    width: 100,
    align: 'center' as const,
  },
  {
    title: '发证机关',
    dataIndex: 'issueAuthority',
    width: 180,
    ellipsis: true,
  },
  {
    title: '证件更新时间',
    dataIndex: 'updateTime',
    width: 180,
    customRender: ({ text }: { text: string }) => {
      return text ? dayjs(text).format('YYYY-MM-DD HH:mm:ss') : '-';
    },
  },
  {
    title: '证件更新人',
    dataIndex: 'updater',
    width: 120,
  },
]);

// 加载数据
async function loadData() {
  try {
    loading.value = true;
    const { data } = await getCustomerIdentityPage({
      customerId: props.customerId,
      pageNo: pagination.value.current,
      pageSize: pagination.value.pageSize,
    });
    dataSource.value = data?.list || [];
    pagination.value.total = data?.total || 0;
  } catch (error) {
    console.error('加载证件信息失败:', error);
  } finally {
    loading.value = false;
  }
}

// 分页变化
function handleTableChange(pag: any) {
  pagination.value.current = pag.current;
  pagination.value.pageSize = pag.pageSize;
  loadData();
}

// 获取Tag颜色
function getTagColor(record: AicrmCustomerIdentityApi.CustomerIdentity): string {
  const status = getValidityStatus(record.expiryDate as string);
  if (status.status === 'success') return 'green';
  if (status.status === 'processing') return 'blue';
  return 'default';
}

// 挂载时加载数据
onMounted(() => {
  loadData();
});

// 暴露方法供父组件调用
defineExpose({
  refresh: loadData,
});
</script>

<template>
  <div class="identity-list-container">
    <a-table
      :columns="columns"
      :data-source="dataSource"
      :loading="loading"
      :pagination="pagination"
      :scroll="{ x: 1400 }"
      row-key="id"
      size="middle"
      @change="handleTableChange"
    >
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'validity'">
          <a-tag :color="getTagColor(record)">
            {{ getValidityStatus(record.expiryDate).text }}
          </a-tag>
        </template>
      </template>
      <template #empty>
        <div style="text-align: center; padding: 40px 0; color: #999;">
          暂无证件信息
        </div>
      </template>
    </a-table>
  </div>
</template>

<style scoped lang="less">
.identity-list-container {
  padding: 16px;
}
</style>
