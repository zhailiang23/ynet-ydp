<script lang="ts" setup>
import type { TaskScoringFactorApi } from '#/api/task/scoring-factor';

import { computed, onMounted, ref } from 'vue';

import { Page, useVbenModal } from '@vben/common-ui';

import {
  Alert,
  Button,
  InputNumber,
  message,
  Modal,
  Slider,
  Space,
  Switch,
  Table,
} from 'ant-design-vue';
import type { ColumnType } from 'ant-design-vue/es/table';

import {
  batchUpdateWeight,
  deleteScoringFactor,
  getScoringFactor,
  getScoringFactorList,
} from '#/api/task/scoring-factor';

import ScoringFactorForm from './modules/scoring-factor-form.vue';

defineOptions({
  name: 'TaskScoringFactor',
});

const [FactorModal, factorModalApi] = useVbenModal({
  connectedComponent: ScoringFactorForm,
  destroyOnClose: true,
});

// 表格数据
const loading = ref(false);
const dataSource = ref<TaskScoringFactorApi.TaskScoringFactor[]>([]);

// 计算总权重
const totalWeight = computed(() => {
  return dataSource.value
    .filter((item) => item.enabled)
    .reduce((sum, item) => sum + (item.weight || 0), 0);
});

// 权重总和是否合法
const isWeightValid = computed(() => totalWeight.value === 100);

// 加载数据
async function loadData() {
  loading.value = true;
  try {
    const data = await getScoringFactorList();
    dataSource.value = data;
  } catch (error) {
    message.error('加载数据失败');
  } finally {
    loading.value = false;
  }
}

// 新增因子
function handleCreate() {
  factorModalApi.setData({}).open();
}

// 编辑因子
async function handleEdit(record: TaskScoringFactorApi.TaskScoringFactor) {
  try {
    // 调用详情接口获取完整数据（包括 conditions）
    const detail = await getScoringFactor(record.id);
    factorModalApi.setData(detail).open();
  } catch (error) {
    message.error('获取详情失败');
  }
}

// 删除因子
function handleDelete(record: TaskScoringFactorApi.TaskScoringFactor) {
  Modal.confirm({
    title: '确认删除',
    content: `确定要删除评分因子"${record.factorName}"吗？`,
    onOk: async () => {
      await deleteScoringFactor(record.id);
      message.success('删除成功');
      await loadData();
    },
  });
}

// 权重变化
function handleWeightChange() {
  // 权重变化时自动计算总和
}

// 批量保存
async function handleBatchSave() {
  if (!isWeightValid.value) {
    message.error('启用因子的权重总和必须等于100%');
    return;
  }

  try {
    await batchUpdateWeight({
      factors: dataSource.value.map((item) => ({
        id: item.id,
        enabled: item.enabled,
        weight: item.weight,
      })),
    });
    message.success('保存成功');
    await loadData();
  } catch (error) {
    message.error('保存失败');
  }
}

// 表格列定义
const columns: ColumnType<TaskScoringFactorApi.TaskScoringFactor>[] = [
  {
    title: '排序',
    dataIndex: 'sort',
    width: 80,
    align: 'center',
  },
  {
    title: '因子名称',
    dataIndex: 'factorName',
    width: 150,
  },
  {
    title: '权重(%)',
    dataIndex: 'weight',
    width: 300,
  },
  {
    title: '说明',
    dataIndex: 'description',
    ellipsis: true,
  },
  {
    title: '启用',
    dataIndex: 'enabled',
    width: 80,
    align: 'center',
  },
  {
    title: '操作',
    key: 'action',
    width: 150,
    align: 'center',
    fixed: 'right',
  },
];

onMounted(() => {
  loadData();
});
</script>

<template>
  <Page
    content-class="flex flex-col"
    description="配置任务评分因子及权重，所有启用因子的权重总和必须等于100%"
    title="评分因子配置"
  >
    <template #extra>
      <Space>
        <Button type="primary" @click="handleCreate">新增因子</Button>
        <Button type="primary" :disabled="!isWeightValid" @click="handleBatchSave">
          批量保存
        </Button>
      </Space>
    </template>

    <!-- 权重总和提示 -->
    <Alert
      :message="`当前启用因子的权重总和：${totalWeight}%`"
      :type="isWeightValid ? 'success' : 'warning'"
      show-icon
      class="mb-4"
    >
      <template #description>
        <span v-if="isWeightValid">权重配置正确，可以保存</span>
        <span v-else>权重总和必须等于100%，请调整后再保存</span>
      </template>
    </Alert>

    <!-- 表格 -->
    <Table
      :columns="columns"
      :data-source="dataSource"
      :loading="loading"
      :pagination="false"
      :scroll="{ x: 1200 }"
      row-key="id"
      class="flex-1"
    >
      <!-- 权重列：Slider + InputNumber -->
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'weight'">
          <div class="flex items-center gap-2">
            <Slider
              v-model:value="record.weight"
              :max="100"
              :min="0"
              :step="1"
              class="flex-1"
              @change="handleWeightChange"
            />
            <InputNumber
              v-model:value="record.weight"
              :max="100"
              :min="0"
              :step="1"
              class="w-20"
              @change="handleWeightChange"
            />
          </div>
        </template>

        <!-- 启用列：Switch -->
        <template v-else-if="column.dataIndex === 'enabled'">
          <Switch v-model:checked="record.enabled" @change="handleWeightChange" />
        </template>

        <!-- 操作列 -->
        <template v-else-if="column.key === 'action'">
          <Space>
            <Button type="link" size="small" @click="handleEdit(record)">编辑</Button>
            <Button
              type="link"
              size="small"
              danger
              @click="handleDelete(record)"
            >
              删除
            </Button>
          </Space>
        </template>
      </template>
    </Table>

    <!-- 添加/编辑弹窗 -->
    <FactorModal @success="loadData" />
  </Page>
</template>
