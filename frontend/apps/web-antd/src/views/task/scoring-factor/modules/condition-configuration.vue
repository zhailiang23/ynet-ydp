<script lang="ts" setup>
import type { TaskScoringFactorApi } from '#/api/task/scoring-factor';

import { ref, watch } from 'vue';

import { Button, Input, Select } from 'ant-design-vue';
import { DeleteOutlined, PlusOutlined } from '@ant-design/icons-vue';

// Props
const props = defineProps<{
  conditions: TaskScoringFactorApi.TaskScoringCondition[];
}>();

// Emits
const emit = defineEmits<{
  'update:conditions': [conditions: TaskScoringFactorApi.TaskScoringCondition[]];
}>();

// 本地条件列表
const localConditions = ref<TaskScoringFactorApi.TaskScoringCondition[]>([]);

// 监听 props 变化
watch(
  () => props.conditions,
  (newConditions) => {
    localConditions.value = JSON.parse(JSON.stringify(newConditions));
  },
  { immediate: true },
);

// 添加条件
const handleAddCondition = () => {
  const newCondition: TaskScoringFactorApi.TaskScoringCondition = {
    dataSource: '',
    fieldName: '',
    operator: '',
    fieldValue: '',
    sort: localConditions.value.length + 1,
  };
  localConditions.value.push(newCondition);
  emitUpdate();
};

// 删除条件
const handleDeleteCondition = (index: number) => {
  localConditions.value.splice(index, 1);
  // 重新排序
  localConditions.value.forEach((condition, idx) => {
    condition.sort = idx + 1;
  });
  emitUpdate();
};

// 发送更新事件
const emitUpdate = () => {
  emit('update:conditions', localConditions.value);
};

// 数据来源选项（从字典加载）
const dataSourceOptions = ref([
  { label: '客户画像', value: 'customer_profile' },
  { label: '任务属性', value: 'task_attribute' },
  { label: '交易历史', value: 'transaction_history' },
]);

// 操作符选项
const operatorOptions = ref([
  { label: '等于 (=)', value: 'EQUAL' },
  { label: '不等于 (!=)', value: 'NOT_EQUAL' },
  { label: '大于 (>)', value: 'GREATER_THAN' },
  { label: '小于 (<)', value: 'LESS_THAN' },
  { label: '大于等于 (>=)', value: 'GREATER_EQUAL' },
  { label: '小于等于 (<=)', value: 'LESS_EQUAL' },
  { label: '包含 (IN)', value: 'IN' },
  { label: '模糊匹配 (LIKE)', value: 'LIKE' },
]);

// 字段选项（根据数据来源动态加载）
const getFieldOptions = (dataSource: string) => {
  if (dataSource === 'customer_profile') {
    return [
      { label: '账户余额', value: 'account_balance' },
      { label: '客户等级', value: 'customer_level' },
      { label: '风险评级', value: 'risk_rating' },
      { label: '开户时长(天)', value: 'account_age_days' },
    ];
  }
  if (dataSource === 'task_attribute') {
    return [
      { label: '任务类型', value: 'task_type' },
      { label: '任务优先级', value: 'task_priority' },
      { label: '截止天数', value: 'deadline_days' },
    ];
  }
  if (dataSource === 'transaction_history') {
    return [
      { label: '交易金额', value: 'transaction_amount' },
      { label: '交易次数', value: 'transaction_count' },
    ];
  }
  return [];
};

// 数据来源变化时，清空字段选择
const handleDataSourceChange = (index: number) => {
  localConditions.value[index].fieldName = '';
  emitUpdate();
};
</script>

<template>
  <div class="condition-configuration">
    <!-- 条件列表 -->
    <div v-if="localConditions.length > 0" class="space-y-3">
      <div
        v-for="(condition, index) in localConditions"
        :key="index"
        class="rounded-lg border border-gray-200 bg-white p-4 dark:border-gray-700 dark:bg-gray-900"
      >
        <!-- 条件行 -->
        <div class="grid grid-cols-12 gap-3">
          <!-- 数据来源 -->
          <div class="col-span-3">
            <label class="mb-1.5 block text-xs font-medium text-gray-600 dark:text-gray-400">
              数据来源
            </label>
            <Select
              v-model:value="condition.dataSource"
              class="w-full"
              placeholder="选择数据来源"
              size="large"
              @change="() => handleDataSourceChange(index)"
            >
              <Select.Option
                v-for="opt in dataSourceOptions"
                :key="opt.value"
                :value="opt.value"
              >
                {{ opt.label }}
              </Select.Option>
            </Select>
          </div>

          <!-- 字段/属性 -->
          <div class="col-span-3">
            <label class="mb-1.5 block text-xs font-medium text-gray-600 dark:text-gray-400">
              字段/属性
            </label>
            <Select
              v-model:value="condition.fieldName"
              class="w-full"
              placeholder="选择字段"
              size="large"
              :disabled="!condition.dataSource"
              @change="emitUpdate"
            >
              <Select.Option
                v-for="opt in getFieldOptions(condition.dataSource)"
                :key="opt.value"
                :value="opt.value"
              >
                {{ opt.label }}
              </Select.Option>
            </Select>
          </div>

          <!-- 操作符 -->
          <div class="col-span-2">
            <label class="mb-1.5 block text-xs font-medium text-gray-600 dark:text-gray-400">
              操作符
            </label>
            <Select
              v-model:value="condition.operator"
              class="w-full"
              placeholder="操作符"
              size="large"
              @change="emitUpdate"
            >
              <Select.Option
                v-for="opt in operatorOptions"
                :key="opt.value"
                :value="opt.value"
              >
                {{ opt.label }}
              </Select.Option>
            </Select>
          </div>

          <!-- 值 -->
          <div class="col-span-3">
            <label class="mb-1.5 block text-xs font-medium text-gray-600 dark:text-gray-400">
              值
            </label>
            <Input
              v-model:value="condition.fieldValue"
              placeholder="比较值"
              size="large"
              @change="emitUpdate"
            />
          </div>

          <!-- 删除按钮 -->
          <div class="col-span-1 flex items-end">
            <Button
              type="text"
              danger
              size="large"
              class="w-full"
              @click="handleDeleteCondition(index)"
            >
              <DeleteOutlined class="text-lg" />
            </Button>
          </div>
        </div>
      </div>
    </div>

    <!-- 添加条件按钮 -->
    <Button
      type="dashed"
      block
      size="large"
      class="mt-4"
      @click="handleAddCondition"
    >
      <PlusOutlined /> 添加新条件
    </Button>
  </div>
</template>

