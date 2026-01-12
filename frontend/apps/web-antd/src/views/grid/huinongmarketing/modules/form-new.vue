<script lang="ts" setup>
import type { GridHuinongMarketingApi } from '#/api/grid/huinongmarketing';

import { computed, ref, reactive, onMounted } from 'vue';
import { useVbenModal } from '@vben/common-ui';
import { Form, Input, InputNumber, Checkbox, Select, message } from 'ant-design-vue';
import { createHuinongMarketing, getHuinongMarketing, updateHuinongMarketing } from '#/api/grid/huinongmarketing';
import { getSimpleHuinongStationList, getHuinongStationMapData } from '#/api/grid/huinongstation';
import { $t } from '#/locales';
import VillageMapSelect from './village-map-select.vue';

const emit = defineEmits(['success']);
const formData = ref<GridHuinongMarketingApi.HuinongMarketing>();

// 网格边界数据
const gridBoundary = ref<string>();

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['惠农网格营销信息'])
    : $t('ui.actionTitle.create', ['惠农网格营销信息']);
});

// 客户类型选项
const customerTypeOptions = [
  { label: '传统种植户', value: '传统种植户' },
  { label: '传统养殖户', value: '传统养殖户' },
  { label: '乡村新型经营主体', value: '乡村新型经营主体' },
  { label: '农资经营主体', value: '农资经营主体' },
  { label: '个体工商户', value: '个体工商户' },
  { label: '乡村服务业', value: '乡村服务业' },
  { label: '工薪阶层', value: '工薪阶层' },
  { label: '乡村特定需求', value: '乡村特定需求' },
  { label: '其他（返乡创业、电商等）', value: '其他（返乡创业、电商等）' },
];

// 站点列表
const stationList = ref<any[]>([]);

// 加载站点列表
async function loadStationList() {
  try {
    stationList.value = await getSimpleHuinongStationList();
  } catch (error) {
    console.error('加载站点列表失败:', error);
    message.error('加载站点列表失败');
  }
}

// 组件挂载时加载站点列表
onMounted(() => {
  loadStationList();
});

// 表单数据
const formState = reactive({
  id: undefined as number | undefined,
  stationId: undefined as number | undefined,
  villageName: '',
  villageLocation: '',
  villageAddress: '',
  registeredPopulation: undefined as number | undefined,
  industrySituation: '',
  customerTypes: [] as string[],
  customerCounts: {} as Record<string, number>,
});

// 地图选择的位置
const mapLocation = ref<{ lng: number; lat: number; address: string; villageName: string }>();

// 监听地图位置变化
function handleMapLocationChange(location: { lng: number; lat: number; address: string; villageName: string }) {
  mapLocation.value = location;
  formState.villageName = location.villageName;
  formState.villageLocation = `${location.lng},${location.lat}`;
  formState.villageAddress = location.address;
}

// 监听站点选择，加载网格边界
async function handleStationChange(stationId: number) {
  if (!stationId) {
    gridBoundary.value = undefined;
    return;
  }

  try {
    const mapData = await getHuinongStationMapData(stationId);
    if (mapData.gridInfo && mapData.gridInfo.boundaryGeometry) {
      gridBoundary.value = mapData.gridInfo.boundaryGeometry;
    } else {
      gridBoundary.value = undefined;
      message.warning('该站点未配置网格边界信息');
    }
  } catch (error) {
    console.error('加载网格边界失败:', error);
    gridBoundary.value = undefined;
  }
}

// 表单验证规则
const rules = {
  stationId: [{ required: true, message: '请选择惠农站点' }],
  villageName: [{ required: true, message: '请选择行政村名称' }],
  villageLocation: [{ required: true, message: '请在地图上选择位置' }],
  registeredPopulation: [
    { required: true, message: '请输入户籍人口' },
    { type: 'number', message: '户籍人口必须为数字' },
  ],
  industrySituation: [
    { required: true, message: '请输入产业情况' },
    { max: 300, message: '产业情况最多300字' },
  ],
  customerTypes: [{ required: true, message: '请选择客户类型' }],
};

// 初始化表单数据
async function initFormData(data?: GridHuinongMarketingApi.HuinongMarketing) {
  if (!data || !data.id) {
    // 新增时，清空表单
    return;
  }

  // 编辑时，加载数据
  try {
    const result = await getHuinongMarketing(data.id);
    formState.id = result.id;
    formState.stationId = result.stationId;
    formState.villageName = result.villageName || '';
    formState.villageLocation = result.villageLocation || '';
    formState.villageAddress = result.villageAddress || '';
    formState.registeredPopulation = result.registeredPopulation;
    formState.industrySituation = result.industrySituation || '';

    // 解析客户类型和数量
    if (result.customerType) {
      try {
        formState.customerTypes = JSON.parse(result.customerType);
      } catch (e) {
        formState.customerTypes = [];
      }
    }

    if (result.customerCount) {
      try {
        formState.customerCounts = JSON.parse(result.customerCount);
      } catch (e) {
        formState.customerCounts = {};
      }
    }

    // 解析地图位置
    if (result.villageLocation) {
      const [lng, lat] = result.villageLocation.split(',').map(Number);
      mapLocation.value = {
        lng,
        lat,
        address: result.villageAddress || '',
        villageName: result.villageName || '',
      };
    }

    // 加载站点的网格边界
    if (result.stationId) {
      await handleStationChange(result.stationId);
    }
  } catch (error) {
    console.error('加载数据失败:', error);
    message.error('加载数据失败');
  }
}

// 提交表单
async function handleSubmit() {
  // 验证必填字段
  if (!formState.stationId) {
    message.error('请选择惠农站点');
    return false;
  }
  if (!formState.villageName) {
    message.error('请在地图上选择位置');
    return false;
  }
  if (!formState.registeredPopulation) {
    message.error('请输入户籍人口');
    return false;
  }
  if (!formState.industrySituation) {
    message.error('请输入产业情况');
    return false;
  }
  if (formState.customerTypes.length === 0) {
    message.error('请选择客户类型');
    return false;
  }

  // 验证每个客户类型都有对应的数量
  for (const type of formState.customerTypes) {
    if (!formState.customerCounts[type] || formState.customerCounts[type] <= 0) {
      message.error(`请输入"${type}"的客户数量`);
      return false;
    }
  }

  // 构建提交数据
  const submitData: any = {
    id: formState.id,
    stationId: formState.stationId,
    villageName: formState.villageName,
    villageLocation: formState.villageLocation,
    villageAddress: formState.villageAddress,
    registeredPopulation: formState.registeredPopulation,
    industrySituation: formState.industrySituation,
    customerType: JSON.stringify(formState.customerTypes),
    customerCount: JSON.stringify(formState.customerCounts),
  };

  try {
    await (formState.id ? updateHuinongMarketing(submitData) : createHuinongMarketing(submitData));
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
      formState.stationId = undefined;
      formState.villageName = '';
      formState.villageLocation = '';
      formState.villageAddress = '';
      formState.registeredPopulation = undefined;
      formState.industrySituation = '';
      formState.customerTypes = [];
      formState.customerCounts = {};
      mapLocation.value = undefined;
      return;
    }
    // 打开时加载数据
    const data = modalApi.getData<GridHuinongMarketingApi.HuinongMarketing>();
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
      <!-- 左侧：地图（2/3宽度） -->
      <div class="flex-[2]">
        <VillageMapSelect v-model="mapLocation" :grid-boundary="gridBoundary" @update:model-value="handleMapLocationChange" />
      </div>

      <!-- 右侧：表单（1/3宽度） -->
      <div class="flex-[1] overflow-y-auto pr-2">
        <Form layout="vertical" :model="formState">
          <Form.Item label="站点信息" name="stationId" :rules="rules.stationId">
            <Select
              v-model:value="formState.stationId"
              placeholder="请选择惠农站点"
              show-search
              :filter-option="(input: string, option: any) =>
                option.stationName?.toLowerCase().includes(input.toLowerCase()) ||
                option.stationCode?.toLowerCase().includes(input.toLowerCase())
              "
              @change="handleStationChange"
            >
              <Select.Option
                v-for="station in stationList"
                :key="station.id"
                :value="station.id"
                :stationName="station.stationName"
                :stationCode="station.stationCode"
              >
                {{ station.stationName }} ({{ station.stationCode }})
              </Select.Option>
            </Select>
          </Form.Item>

          <Form.Item label="行政村名称" name="villageName" :rules="rules.villageName">
            <Input v-model:value="formState.villageName" disabled placeholder="在地图上选择位置后自动填充" />
          </Form.Item>

          <Form.Item label="行政村地址" name="villageAddress">
            <Input v-model:value="formState.villageAddress" disabled placeholder="在地图上选择位置后自动填充" />
          </Form.Item>

          <Form.Item label="户籍人口（人）" name="registeredPopulation" :rules="rules.registeredPopulation">
            <InputNumber v-model:value="formState.registeredPopulation" :min="1" class="w-full" placeholder="请输入户籍人口" />
          </Form.Item>

          <Form.Item label="产业情况" name="industrySituation" :rules="rules.industrySituation">
            <Input.TextArea
              v-model:value="formState.industrySituation"
              :maxlength="300"
              :rows="3"
              show-count
              placeholder="请输入产业情况（最多300字）"
            />
          </Form.Item>

          <Form.Item label="客户类型" name="customerTypes" :rules="rules.customerTypes">
            <div class="flex flex-col gap-3">
              <div v-for="option in customerTypeOptions" :key="option.value" class="flex items-center gap-2">
                <Checkbox
                  :checked="formState.customerTypes.includes(option.value)"
                  @change="(e: any) => {
                    if (e.target.checked) {
                      formState.customerTypes.push(option.value);
                    } else {
                      const index = formState.customerTypes.indexOf(option.value);
                      if (index > -1) {
                        formState.customerTypes.splice(index, 1);
                        delete formState.customerCounts[option.value];
                      }
                    }
                  }"
                >
                  {{ option.label }}
                </Checkbox>
                <InputNumber
                  v-if="formState.customerTypes.includes(option.value)"
                  v-model:value="formState.customerCounts[option.value]"
                  :min="1"
                  class="w-32"
                  :placeholder="`数量（户）`"
                />
              </div>
            </div>
          </Form.Item>
        </Form>
      </div>
    </div>
  </Modal>
</template>
