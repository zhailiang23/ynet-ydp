<script lang="ts" setup>
import { computed } from 'vue';

import { Tag } from 'ant-design-vue';
import { getDictLabel } from '@vben/hooks';

interface Props {
  customerName?: string; // 客户名称
  customerType?: number; // 客户类型 (1=零售, 2=对公)
  customerLevel?: string; // 客户等级
  customerStatus?: number; // 客户状态
}

const props = defineProps<Props>();

// 客户类型映射
const customerTypeText = computed(() => {
  switch (props.customerType) {
    case 1:
      return '零售客户';
    case 2:
      return '对公客户';
    default:
      return '未知类型';
  }
});

// 客户类型标签颜色
const customerTypeColor = computed(() => {
  switch (props.customerType) {
    case 1:
      return 'blue';
    case 2:
      return 'green';
    default:
      return 'default';
  }
});

// 客户状态映射
const customerStatusText = computed(() => {
  switch (props.customerStatus) {
    case 0:
      return '潜在客户';
    case 1:
      return '正常';
    case 2:
      return '流失';
    case 3:
      return '休眠';
    default:
      return '未知';
  }
});

// 客户状态标签颜色
const customerStatusColor = computed(() => {
  switch (props.customerStatus) {
    case 0:
      return 'orange';
    case 1:
      return 'success';
    case 2:
      return 'error';
    case 3:
      return 'default';
    default:
      return 'default';
  }
});

// 客户等级 - 使用字典转换
const customerLevelText = computed(() => {
  return getDictLabel('aicrm_customer_level', props.customerLevel) || props.customerLevel || '未设置';
});

// 客户等级标签颜色
const customerLevelColor = computed(() => {
  const level = props.customerLevel?.toLowerCase();
  if (level === 'vip' || level === '高级' || level === 'strategic') return 'purple';
  if (level === '中级' || level === 'important') return 'blue';
  if (level === '普通' || level === 'normal') return 'default';
  return 'cyan';
});
</script>

<template>
  <div class="customer-header">
    <div class="customer-header-content">
      <div class="customer-name">
        {{ customerName || '客户详情' }}
      </div>
      <div class="customer-tags">
        <Tag :color="customerTypeColor">
          {{ customerTypeText }}
        </Tag>
        <Tag v-if="customerLevel" :color="customerLevelColor">
          {{ customerLevelText }}
        </Tag>
        <Tag :color="customerStatusColor">
          {{ customerStatusText }}
        </Tag>
      </div>
    </div>
  </div>
</template>

<style scoped>
.customer-header {
  position: sticky;
  top: 0;
  z-index: 100;
  padding: 16px 24px;
  background: #fff;
  border-bottom: 1px solid #e8e8e8;
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.08);
}

.dark .customer-header {
  background: rgb(20 22 26);
  border-bottom-color: rgba(255, 255, 255, 0.1);
  box-shadow: 0 1px 4px rgba(0, 0, 0, 0.3);
}

.customer-header-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.customer-name {
  font-size: 18px;
  font-weight: 600;
  color: #1a1a1a;
}

.dark .customer-name {
  color: #fff;
}

.customer-tags {
  display: flex;
  gap: 8px;
  align-items: center;
}

.customer-tags :deep(.ant-tag) {
  margin: 0;
  padding: 4px 12px;
  font-size: 13px;
  border-radius: 4px;
}
</style>
