<script lang="ts" setup>
import { computed } from 'vue';
import { Checkbox, Divider, Button } from 'ant-design-vue';

const props = defineProps<{
  gridTypes?: string[];
  customerTypes?: string[];
  includeCommunity?: boolean;
  includeCompetitor?: boolean;
}>();

const emit = defineEmits<{
  (e: 'update:gridTypes', value: string[]): void;
  (e: 'update:customerTypes', value: string[]): void;
  (e: 'update:includeCommunity', value: boolean): void;
  (e: 'update:includeCompetitor', value: boolean): void;
  (e: 'change'): void;
}>();

// 网格类型选项
const gridTypeOptions = [
  { label: '惠农网格', value: 'HUINONG', color: '#1890ff' },
  { label: '零贷网格', value: 'ZERODAI', color: '#52c41a' },
  { label: '社区网格', value: 'COMMUNITY', color: '#fa8c16' },
  { label: '厅堂网格', value: 'LOBBY', color: '#722ed1' },
];

// 客户类型选项
const customerTypeOptions = [
  { label: '惠农贷款客户', value: 'HUINONG_LOAN', color: '#1890ff' },
  { label: '零贷客户', value: 'ZERODAI', color: '#52c41a' },
  { label: '社区客户', value: 'COMMUNITY', color: '#fa8c16' },
  { label: '厅堂客户', value: 'TINGTANG', color: '#722ed1' },
];

// 计算属性
const localGridTypes = computed({
  get: () => props.gridTypes || [],
  set: (value) => {
    emit('update:gridTypes', value);
  },
});

const localCustomerTypes = computed({
  get: () => props.customerTypes || [],
  set: (value) => {
    emit('update:customerTypes', value);
  },
});

const localIncludeCommunity = computed({
  get: () => props.includeCommunity || false,
  set: (value) => {
    emit('update:includeCommunity', value);
  },
});

const localIncludeCompetitor = computed({
  get: () => props.includeCompetitor || false,
  set: (value) => {
    emit('update:includeCompetitor', value);
  },
});

/**
 * 全选/取消全选网格类型
 */
function toggleAllGridTypes() {
  if (localGridTypes.value.length === gridTypeOptions.length) {
    localGridTypes.value = [];
  } else {
    localGridTypes.value = gridTypeOptions.map((option) => option.value);
  }
}

/**
 * 全选/取消全选客户类型
 */
function toggleAllCustomerTypes() {
  if (localCustomerTypes.value.length === customerTypeOptions.length) {
    localCustomerTypes.value = [];
  } else {
    localCustomerTypes.value = customerTypeOptions.map((option) => option.value);
  }
}

/**
 * 应用筛选
 */
function applyFilter() {
  emit('change');
}
</script>

<template>
  <div class="map-filter-panel">
    <div class="panel-header mb-4">
      <h3 class="text-lg font-semibold">数据筛选</h3>
    </div>

    <!-- 网格类型筛选 -->
    <div class="filter-section mb-6">
      <div class="section-header flex items-center justify-between mb-3">
        <h4 class="text-sm font-medium">网格类型</h4>
        <a-button size="small" type="link" @click="toggleAllGridTypes">
          {{ localGridTypes.length === gridTypeOptions.length ? '取消全选' : '全选' }}
        </a-button>
      </div>
      <a-checkbox-group v-model:value="localGridTypes" class="w-full">
        <div
          v-for="option in gridTypeOptions"
          :key="option.value"
          class="flex items-center mb-2"
        >
          <a-checkbox :value="option.value">
            <div class="flex items-center">
              <div
                class="color-indicator mr-2"
                :style="{ background: option.color }"
              ></div>
              <span>{{ option.label }}</span>
            </div>
          </a-checkbox>
        </div>
      </a-checkbox-group>
    </div>

    <a-divider />

    <!-- 客户类型筛选 -->
    <div class="filter-section mb-6">
      <div class="section-header flex items-center justify-between mb-3">
        <h4 class="text-sm font-medium">客户类型</h4>
        <a-button size="small" type="link" @click="toggleAllCustomerTypes">
          {{ localCustomerTypes.length === customerTypeOptions.length ? '取消全选' : '全选' }}
        </a-button>
      </div>
      <a-checkbox-group v-model:value="localCustomerTypes" class="w-full">
        <div
          v-for="option in customerTypeOptions"
          :key="option.value"
          class="flex items-center mb-2"
        >
          <a-checkbox :value="option.value">
            <div class="flex items-center">
              <div
                class="color-indicator circle mr-2"
                :style="{ background: option.color }"
              ></div>
              <span>{{ option.label }}</span>
            </div>
          </a-checkbox>
        </div>
      </a-checkbox-group>
    </div>

    <a-divider />

    <!-- 其他数据筛选 -->
    <div class="filter-section mb-6">
      <h4 class="text-sm font-medium mb-3">其他数据</h4>
      <div class="flex items-center mb-2">
        <a-checkbox v-model:checked="localIncludeCommunity">
          <div class="flex items-center">
            <div
              class="color-indicator square mr-2"
              style="background: #eb2f96"
            ></div>
            <span>社区信息</span>
          </div>
        </a-checkbox>
      </div>
      <div class="flex items-center mb-2">
        <a-checkbox v-model:checked="localIncludeCompetitor">
          <div class="flex items-center">
            <div
              class="color-indicator triangle mr-2"
              style="
                width: 0;
                height: 0;
                border-left: 6px solid transparent;
                border-right: 6px solid transparent;
                border-bottom: 12px solid #f5222d;
              "
            ></div>
            <span>同业信息</span>
          </div>
        </a-checkbox>
      </div>
    </div>

    <a-divider />

    <!-- 应用按钮 -->
    <div class="filter-actions">
      <a-button type="primary" block @click="applyFilter">
        应用筛选
      </a-button>
    </div>
  </div>
</template>

<style scoped>
.map-filter-panel {
  padding: 16px;
}

.color-indicator {
  width: 16px;
  height: 16px;
  border: 1px solid #d9d9d9;
  display: inline-block;
}

.color-indicator.circle {
  border-radius: 50%;
}

.color-indicator.square {
  border-radius: 2px;
}

.filter-section :deep(.ant-checkbox-wrapper) {
  margin-left: 0;
}
</style>
