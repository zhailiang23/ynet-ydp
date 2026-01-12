<script lang="ts" setup>
import type { ZerodaiGridApi } from '#/api/grid/zerodai-grid';

import { computed, ref, reactive, onMounted } from 'vue';
import { useVbenModal } from '@vben/common-ui';
import { Form, Input, Select, message } from 'ant-design-vue';
import { createZerodaiGrid, getZerodaiGrid, updateZerodaiGrid } from '#/api/grid/zerodai-grid';
import { useUserStore } from '@vben/stores';
import { $t } from '#/locales';
import GridMapDraw from '../../community-grid/modules/grid-map-draw.vue';
import { getSimpleDeptList } from '#/api/system/dept';
import { getDictOptions } from '@vben/hooks';

const emit = defineEmits(['success']);
const formData = ref<ZerodaiGridApi.ZerodaiGrid>();
const userStore = useUserStore();

// 网格边界数据
const gridBoundary = ref<string>('');

// 地图绘制组件引用
const gridMapDrawRef = ref<any>(null);

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['零贷网格'])
    : $t('ui.actionTitle.create', ['零贷网格']);
});

// 部门列表
const deptList = ref<any[]>([]);

// 资源标签选项
const resourceTagOptions = ref<any[]>([]);

// 组织类型选项
const orgTypeOptions = ref<any[]>([]);

// 加载部门列表
async function loadDeptList() {
  try {
    deptList.value = await getSimpleDeptList();
  } catch (error) {
    console.error('加载部门列表失败:', error);
    message.error('加载部门列表失败');
  }
}

// 加载字典选项
async function loadDictOptions() {
  try {
    resourceTagOptions.value = await getDictOptions('grid_resource_tag');
    orgTypeOptions.value = await getDictOptions('grid_org_type');
  } catch (error) {
    console.error('加载字典选项失败:', error);
    message.error('加载字典选项失败');
  }
}

// 组件挂载时加载数据
onMounted(() => {
  loadDeptList();
  loadDictOptions();
});

// 表单数据
const formState = reactive({
  id: undefined as number | undefined,
  gridCode: '', // 网格编号(后端自动生成,前端只读)
  gridName: '',
  orgId: undefined as number | undefined,
  resourceTags: [] as string[], // 资源标签(多选)
  orgType: undefined as string | undefined, // 组织类型
  boundaryGeometry: '',
});

// 表单验证规则
const rules = {
  gridName: [{ required: true, message: '请输入网格名称' }],
  orgId: [{ required: true, message: '请选择所属组织' }],
  resourceTags: [{ required: true, type: 'array', min: 1, message: '请至少选择一个资源标签' }],
  orgType: [{ required: true, message: '请选择组织类型' }],
  boundaryGeometry: [{ required: true, message: '请在地图上绘制网格边界' }],
};

// 初始化表单数据
async function initFormData(data?: ZerodaiGridApi.ZerodaiGrid) {
  if (!data || !data.id) {
    // 新增时,尝试获取用户的部门ID
    const userInfo = userStore.userInfo as any;
    if (userInfo?.deptId) {
      formState.orgId = userInfo.deptId;
    }
    return;
  }

  // 编辑时,加载数据
  try {
    const result = await getZerodaiGrid(data.id);
    formState.id = result.id;
    formState.gridCode = result.gridCode || '';
    formState.gridName = result.gridName || '';
    formState.orgId = result.orgId;
    formState.orgType = result.orgType;

    // 解析资源标签 JSON 数组
    if (result.resourceTags) {
      try {
        const tags = JSON.parse(result.resourceTags);
        if (Array.isArray(tags)) {
          formState.resourceTags = tags;
        }
      } catch (e) {
        console.error('解析资源标签失败:', e);
      }
    }

    // 设置网格边界
    if (result.boundaryGeometry) {
      formState.boundaryGeometry = result.boundaryGeometry;
      gridBoundary.value = result.boundaryGeometry;
    }
  } catch (error) {
    console.error('加载数据失败:', error);
    message.error('加载数据失败');
  }
}

// 提交表单
async function handleSubmit() {
  // 验证必填字段
  if (!formState.gridName) {
    message.error('请输入网格名称');
    return false;
  }
  if (!formState.orgId) {
    message.error('请选择所属组织');
    return false;
  }
  if (!formState.resourceTags || formState.resourceTags.length === 0) {
    message.error('请至少选择一个资源标签');
    return false;
  }
  if (!formState.orgType) {
    message.error('请选择组织类型');
    return false;
  }
  if (!formState.boundaryGeometry) {
    message.error('请在地图上绘制网格边界');
    return false;
  }

  // 构建提交数据
  const submitData: any = {
    id: formState.id,
    gridName: formState.gridName,
    orgId: formState.orgId,
    resourceTags: JSON.stringify(formState.resourceTags), // 转换为 JSON 字符串
    orgType: formState.orgType,
    boundaryGeometry: formState.boundaryGeometry,
  };

  try {
    await (formState.id ? updateZerodaiGrid(submitData) : createZerodaiGrid(submitData));
    return true;
  } catch (error) {
    console.error('提交失败:', error);
    return false;
  }
}

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const success = await handleSubmit();
    if (!success) {
      return;
    }
    await modalApi.close();
    emit('success');
    message.success($t('ui.actionMessage.operationSuccess'));
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      // 关闭时重置数据
      formData.value = undefined;
      formState.id = undefined;
      formState.gridCode = '';
      formState.gridName = '';
      formState.orgId = undefined;
      formState.resourceTags = [];
      formState.orgType = undefined;
      formState.boundaryGeometry = '';
      gridBoundary.value = '';
      return;
    }
    // 打开时加载数据
    const data = modalApi.getData<ZerodaiGridApi.ZerodaiGrid>();
    formData.value = data;
    modalApi.lock();
    try {
      await initFormData(data);
    } finally {
      modalApi.unlock();
    }
  },
});
</script>

<template>
  <Modal :title="getTitle" class="!w-[90vw]" :footer-props="{ okText: '确定', cancelText: '取消' }">
    <div class="flex h-[700px] gap-4">
      <!-- 左侧:地图(2/3宽度) -->
      <div class="flex-[2]">
        <GridMapDraw
          ref="gridMapDrawRef"
          v-model="formState.boundaryGeometry"
        />
      </div>

      <!-- 右侧:表单(1/3宽度) -->
      <div class="flex-[1] overflow-y-auto pr-2">
        <Form layout="vertical" :model="formState">
          <Form.Item v-if="formState.id" label="网格编号">
            <Input v-model:value="formState.gridCode" disabled placeholder="由系统自动生成" />
          </Form.Item>

          <Form.Item label="网格名称" name="gridName" :rules="rules.gridName">
            <Input v-model:value="formState.gridName" placeholder="请输入网格名称" />
          </Form.Item>

          <Form.Item label="所属组织" name="orgId" :rules="rules.orgId">
            <Select
              v-model:value="formState.orgId"
              placeholder="请选择所属组织"
              show-search
              :filter-option="(input: string, option: any) =>
                option.name?.toLowerCase().includes(input.toLowerCase())
              "
            >
              <Select.Option
                v-for="dept in deptList"
                :key="dept.id"
                :value="dept.id"
                :name="dept.name"
              >
                {{ dept.name }}
              </Select.Option>
            </Select>
          </Form.Item>

          <Form.Item label="资源标签" name="resourceTags" :rules="rules.resourceTags">
            <Select
              v-model:value="formState.resourceTags"
              mode="multiple"
              placeholder="请选择资源标签(可多选)"
              :options="resourceTagOptions"
            />
          </Form.Item>

          <Form.Item label="组织类型" name="orgType" :rules="rules.orgType">
            <Select
              v-model:value="formState.orgType"
              placeholder="请选择组织类型"
              :options="orgTypeOptions"
            />
          </Form.Item>

          <Form.Item label="网格范围" name="boundaryGeometry" :rules="rules.boundaryGeometry">
            <div class="text-sm text-gray-500">
              请在左侧地图上点击【开始绘制】按钮,绘制网格边界
            </div>
          </Form.Item>
        </Form>
      </div>
    </div>
  </Modal>
</template>
