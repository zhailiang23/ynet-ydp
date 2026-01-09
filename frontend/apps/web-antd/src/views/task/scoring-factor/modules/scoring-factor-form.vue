<script lang="ts" setup>
import type { TaskScoringFactorApi } from '#/api/task/scoring-factor';

import { ref, watch } from 'vue';

import { useVbenModal } from '@vben/common-ui';

import { Button, Card, Input, InputNumber, Radio, Select, Switch, Textarea, message } from 'ant-design-vue';
import { MinusOutlined, PlusOutlined } from '@ant-design/icons-vue';

import {
  createScoringFactor,
  updateScoringFactor,
} from '#/api/task/scoring-factor';

// 导入条件配置组件
import ConditionConfiguration from './condition-configuration.vue';

const emit = defineEmits(['success']);
const isEdit = ref(false);
const factorId = ref<number | undefined>(undefined);

// 表单字段
const factorName = ref('');
const category = ref(''); // 所属评分因子
const description = ref('');
const enabled = ref(true);
const conditions = ref<TaskScoringFactorApi.TaskScoringCondition[]>([]);
const logicType = ref<'AND' | 'OR'>('AND');

// 所属评分因子字典选项
const categoryOptions = [
  { label: '业务价值', value: 'business_value' },
  { label: '紧急程度', value: 'urgency' },
  { label: '合规强制', value: 'compliance' },
  { label: '客户意向', value: 'customer_intent' },
];

// 结果配置
const impactType = ref<'direct' | 'weight' | 'score'>('score'); // 影响方式
const scoreAdjustment = ref(10); // 增加/减少分值

// 分值调整
const decreaseScore = () => {
  const step = impactType.value === 'weight' ? 5 : 1;
  const min = impactType.value === 'direct' ? 0 : -100;

  if (scoreAdjustment.value > min) {
    scoreAdjustment.value = Math.max(min, scoreAdjustment.value - step);
  }
};

const increaseScore = () => {
  const step = impactType.value === 'weight' ? 5 : 1;
  const max = 100;

  if (scoreAdjustment.value < max) {
    scoreAdjustment.value = Math.min(max, scoreAdjustment.value + step);
  }
};

// 监听影响方式变化，自动调整数值到合理范围
watch(impactType, (newType, oldType) => {
  if (oldType === undefined) return; // 初始化时不处理

  if (newType === 'direct') {
    // 直接设定评分：0-100，默认 80
    if (scoreAdjustment.value < 0 || scoreAdjustment.value > 100) {
      scoreAdjustment.value = 80;
    }
  } else if (newType === 'weight') {
    // 调整权重：-100 到 100，默认 20（+20%）
    scoreAdjustment.value = 20;
  } else if (newType === 'score') {
    // 评分值加减：-100 到 100，默认 10
    scoreAdjustment.value = 10;
  }
});

// 条件变更处理函数
const handleConditionsChange = (newConditions: TaskScoringFactorApi.TaskScoringCondition[]) => {
  conditions.value = newConditions;
};

// 表单验证
const validateForm = () => {
  if (!factorName.value.trim()) {
    message.error('请输入规则名称');
    return false;
  }
  if (!category.value) {
    message.error('请选择所属评分因子');
    return false;
  }
  return true;
};

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    if (!validateForm()) {
      return;
    }

    modalApi.lock();
    try {
      // 构建保存数据，使用默认值填充必填但隐藏的字段
      const saveData: TaskScoringFactorApi.TaskScoringFactorSaveReqVO = {
        factorName: factorName.value,
        factorNameEn: category.value, // 所属评分因子（使用 factorNameEn 字段存储）
        description: description.value,
        enabled: enabled.value,
        logicType: logicType.value,
        conditions: conditions.value,
        // 结果配置
        impactType: impactType.value,
        scoreAdjustment: scoreAdjustment.value,
        // 使用默认值填充隐藏的必填字段
        weight: 25, // 默认权重 25%
        sort: 0,    // 默认排序 0
        icon: '',   // 图标可选
      };

      if (isEdit.value && factorId.value) {
        // 编辑
        await updateScoringFactor({
          ...saveData,
          id: factorId.value,
        });
        message.success('更新成功');
      } else {
        // 新增
        await createScoringFactor(saveData);
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
      // 关闭时重置表单和条件数据
      isEdit.value = false;
      factorId.value = undefined;
      factorName.value = '';
      category.value = '';
      description.value = '';
      enabled.value = true;
      conditions.value = [];
      logicType.value = 'AND';
      impactType.value = 'score';
      scoreAdjustment.value = 10;
      return;
    }

    // 获取传入的数据（编辑模式）
    const data = modalApi.getData<TaskScoringFactorApi.TaskScoringFactor>();
    if (data?.id) {
      isEdit.value = true;
      factorId.value = data.id;

      // 设置表单值
      factorName.value = data.factorName;
      category.value = data.factorNameEn; // 从 factorNameEn 字段加载所属评分因子
      description.value = data.description || '';
      enabled.value = data.enabled;

      // 设置条件列表和逻辑类型
      logicType.value = data.logicType || 'AND';
      conditions.value = data.conditions || [];

      // 设置结果配置
      impactType.value = data.impactType || 'score';
      scoreAdjustment.value = data.scoreAdjustment || 10;
    }
  },
});

defineExpose({ modalApi });
</script>

<template>
  <Modal :title="isEdit ? '编辑评分因子' : '新增评分因子'" class="!w-[1000px]">
    <div class="space-y-4 p-4">
      <!-- 基础信息卡片 -->
      <Card title="规则基础信息" class="shadow-sm">
        <template #extra>
          <div class="flex items-center gap-2">
            <span class="text-sm text-gray-600 dark:text-gray-400">状态：</span>
            <Switch
              v-model:checked="enabled"
              checked-children="生效"
              un-checked-children="禁用"
            />
          </div>
        </template>

        <!-- 第一行：规则名称 + 所属评分因子 -->
        <div class="grid grid-cols-2 gap-4 mb-4">
          <div>
            <label class="mb-2 block text-sm font-medium">
              规则名称 <span class="text-red-500">*</span>
            </label>
            <Input
              v-model:value="factorName"
              placeholder="例如：高净值客户优先策略"
              size="large"
            />
          </div>
          <div>
            <label class="mb-2 block text-sm font-medium">
              所属评分因子 <span class="text-red-500">*</span>
            </label>
            <Select
              v-model:value="category"
              placeholder="请选择所属评分因子"
              size="large"
              class="w-full"
            >
              <Select.Option
                v-for="opt in categoryOptions"
                :key="opt.value"
                :value="opt.value"
              >
                {{ opt.label }}
              </Select.Option>
            </Select>
          </div>
        </div>

        <!-- 第二行：规则描述 -->
        <div>
          <label class="mb-2 block text-sm font-medium">规则描述</label>
          <Textarea
            v-model:value="description"
            :rows="4"
            :maxlength="500"
            show-count
            placeholder="请描述该规则的计算逻辑和用途..."
          />
        </div>
      </Card>

      <!-- 条件配置卡片 -->
      <Card title="条件配置" class="shadow-sm">
        <template #extra>
          <div class="flex items-center gap-2">
            <span class="text-sm text-gray-500 dark:text-gray-400">逻辑关系：</span>
            <Select
              v-model:value="logicType"
              size="small"
              style="width: 200px"
            >
              <Select.Option value="AND">满足所有条件 (AND)</Select.Option>
              <Select.Option value="OR">满足任一条件 (OR)</Select.Option>
            </Select>
          </div>
        </template>

        <!-- 条件配置组件 -->
        <ConditionConfiguration
          :conditions="conditions"
          @update:conditions="handleConditionsChange"
        />
      </Card>

      <!-- 结果配置卡片 -->
      <Card title="结果配置" class="shadow-sm">
        <div class="space-y-4">
          <!-- 影响方式 -->
          <div>
            <label class="mb-3 block text-sm font-medium">影响方式</label>
            <Radio.Group v-model:value="impactType" class="w-full">
              <div class="grid grid-cols-3 gap-3">
                <!-- 直接设定评分 -->
                <div
                  :class="[
                    'cursor-pointer rounded-lg border-2 p-4 transition-all',
                    impactType === 'direct'
                      ? 'border-primary bg-primary/5'
                      : 'border-gray-200 hover:border-gray-300 dark:border-gray-700 dark:hover:border-gray-600',
                  ]"
                  @click="impactType = 'direct'"
                >
                  <Radio value="direct" class="mb-2">
                    <span class="font-semibold">直接设定评分</span>
                  </Radio>
                  <div class="ml-6 text-xs text-gray-500 dark:text-gray-400">
                    满屏原有评分，强制设定为指定值。
                  </div>
                </div>

                <!-- 调整评分权重 -->
                <div
                  :class="[
                    'cursor-pointer rounded-lg border-2 p-4 transition-all',
                    impactType === 'weight'
                      ? 'border-primary bg-primary/5'
                      : 'border-gray-200 hover:border-gray-300 dark:border-gray-700 dark:hover:border-gray-600',
                  ]"
                  @click="impactType = 'weight'"
                >
                  <Radio value="weight" class="mb-2">
                    <span class="font-semibold">调整评分权重</span>
                  </Radio>
                  <div class="ml-6 text-xs text-gray-500 dark:text-gray-400">
                    基于当前评分类强调系数。
                  </div>
                </div>

                <!-- 评分值加减 -->
                <div
                  :class="[
                    'cursor-pointer rounded-lg border-2 p-4 transition-all',
                    impactType === 'score'
                      ? 'border-primary bg-primary/5'
                      : 'border-gray-200 hover:border-gray-300 dark:border-gray-700 dark:hover:border-gray-600',
                  ]"
                  @click="impactType = 'score'"
                >
                  <Radio value="score" class="mb-2">
                    <span class="font-semibold">评分值加减</span>
                  </Radio>
                  <div class="ml-6 text-xs text-gray-500 dark:text-gray-400">
                    在基础评分上增加或减少分值。
                  </div>
                </div>
              </div>
            </Radio.Group>
          </div>

          <!-- 直接设定评分 -->
          <div v-if="impactType === 'direct'">
            <label class="mb-3 block text-sm font-medium">设定评分值</label>
            <div class="flex items-center gap-3">
              <Button
                size="large"
                @click="decreaseScore"
                :disabled="scoreAdjustment <= 0"
              >
                <MinusOutlined />
              </Button>
              <InputNumber
                v-model:value="scoreAdjustment"
                :min="0"
                :max="100"
                :step="1"
                size="large"
                class="w-32 text-center"
              />
              <Button
                size="large"
                @click="increaseScore"
                :disabled="scoreAdjustment >= 100"
              >
                <PlusOutlined />
              </Button>
              <span class="text-sm text-gray-600 dark:text-gray-400">分</span>
            </div>
            <div class="mt-2 text-xs text-gray-500 dark:text-gray-400">
              满足条件时，任务评分将被强制设定为此值。
            </div>
          </div>

          <!-- 调整评分权重 -->
          <div v-else-if="impactType === 'weight'">
            <label class="mb-3 block text-sm font-medium">权重系数</label>
            <div class="flex items-center gap-3">
              <Button
                size="large"
                @click="decreaseScore"
                :disabled="scoreAdjustment <= -100"
              >
                <MinusOutlined />
              </Button>
              <InputNumber
                v-model:value="scoreAdjustment"
                :min="-100"
                :max="100"
                :step="5"
                size="large"
                class="w-32 text-center"
              />
              <Button
                size="large"
                @click="increaseScore"
                :disabled="scoreAdjustment >= 100"
              >
                <PlusOutlined />
              </Button>
              <span class="text-sm text-gray-600 dark:text-gray-400">%</span>
            </div>
            <div class="mt-2 text-xs text-gray-500 dark:text-gray-400">
              基于当前评分按百分比调整。例如: +20% 表示原评分的 120%，-20% 表示原评分的 80%。
            </div>
          </div>

          <!-- 评分值加减 -->
          <div v-else-if="impactType === 'score'">
            <label class="mb-3 block text-sm font-medium">增加/减少分值</label>
            <div class="flex items-center gap-3">
              <Button
                size="large"
                @click="decreaseScore"
                :disabled="scoreAdjustment <= -100"
              >
                <MinusOutlined />
              </Button>
              <InputNumber
                v-model:value="scoreAdjustment"
                :min="-100"
                :max="100"
                :step="1"
                size="large"
                class="w-32 text-center"
              />
              <Button
                size="large"
                @click="increaseScore"
                :disabled="scoreAdjustment >= 100"
              >
                <PlusOutlined />
              </Button>
              <span class="text-sm text-gray-600 dark:text-gray-400">分</span>
            </div>
            <div class="mt-2 text-xs text-gray-500 dark:text-gray-400">
              正值代表增加加分，负值代表降低优先级。
            </div>
          </div>
        </div>
      </Card>
    </div>
  </Modal>
</template>
