<script setup lang="ts">
import { onMounted, onBeforeUnmount, ref, reactive } from 'vue';
import { Page } from '@vben/common-ui';
import {
  Card,
  Row,
  Col,
  Statistic,
  Divider,
  Form,
  FormItem,
  RadioGroup,
  RadioButton,
  CheckboxGroup,
  Button,
  Drawer,
  Modal,
  Descriptions,
  DescriptionsItem,
  message,
} from 'ant-design-vue';
import {
  CheckCircleOutlined,
  StarFilled,
  StarOutlined,
} from '@ant-design/icons-vue';
import AMapLoader from '@amap/amap-jsapi-loader';
import {
  getHeatmapStatistics,
  getHeatmapData,
  type HeatmapStatisticsVO,
  type HeatmapDataVO,
  type HeatmapReqVO,
} from '#/api/grid/huinongmarketing';

// 九类客群选项
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

// 状态
const statistics = ref<HeatmapStatisticsVO>();
const heatmapData = ref<HeatmapDataVO[]>([]);
const filterForm = reactive<HeatmapReqVO>({
  stationType: '',
  customerTypes: [],
});
const customerDrawerVisible = ref(false);
const statisticsModalVisible = ref(false);
const loading = ref(false);

// 地图相关
let map: any = null;
let heatmap: any = null;

// 初始化高德地图
async function initMap() {
  try {
    (window as any)._AMapSecurityConfig = {
      securityJsCode: '6b249e3430044ff13e483020a9c7efb9',
    };

    const AMap = await AMapLoader.load({
      key: import.meta.env.VITE_AMAP_KEY,
      version: '2.0',
      plugins: ['AMap.HeatMap'],
    });

    map = new AMap.Map('heatmap-container', {
      zoom: 10,
      center: [113.6254, 34.7466],
      viewMode: '2D',
    });

    // 创建热力图图层
    heatmap = new AMap.HeatMap(map, {
      radius: 25,
      opacity: [0, 0.8],
      gradient: {
        0.5: 'blue',
        0.65: 'rgb(117,211,248)',
        0.7: 'rgb(0,255,0)',
        0.9: '#ffea00',
        1.0: 'red',
      },
    });

    // 加载数据
    await loadData();
  } catch (error) {
    console.error('地图初始化失败:', error);
    message.error('地图加载失败');
  }
}

// 加载数据
async function loadData() {
  try {
    loading.value = true;

    // 并行加载统计数据和热力图数据
    const [statsData, mapData] = await Promise.all([
      getHeatmapStatistics(filterForm),
      getHeatmapData(filterForm),
    ]);

    statistics.value = statsData;
    heatmapData.value = mapData;

    // 更新热力图
    updateHeatmap(mapData);

    message.success('数据加载成功');
  } catch (error) {
    console.error('加载数据失败:', error);
    message.error('加载数据失败');
  } finally {
    loading.value = false;
  }
}

// 更新热力图
function updateHeatmap(data: HeatmapDataVO[]) {
  if (!heatmap || !data || data.length === 0) return;

  // 转换数据格式
  const points = data.map((d) => ({
    lng: d.longitude,
    lat: d.latitude,
    count: d.count,
  }));

  // 计算最大权重
  const maxCount = Math.max(...points.map((p) => p.count));

  // 设置热力图数据
  heatmap.setDataSet({
    data: points,
    max: maxCount,
  });

  // 调整地图视野
  if (points.length > 0) {
    const AMap = (window as any).AMap;
    const bounds = new AMap.Bounds(
      new AMap.LngLat(points[0].lng, points[0].lat),
      new AMap.LngLat(points[0].lng, points[0].lat),
    );
    points.forEach((p) => {
      bounds.extend(new AMap.LngLat(p.lng, p.lat));
    });
    map.setBounds(bounds, false, [50, 50, 50, 50]);
  }
}

// 筛选条件变化
function handleFilterChange() {
  // 防抖：延迟 500ms 后自动加载
  setTimeout(() => {
    loadData();
  }, 500);
}

// 重置筛选
function handleReset() {
  filterForm.stationType = '';
  filterForm.customerTypes = [];
  loadData();
}

// 显示统计信息
function showStatistics() {
  statisticsModalVisible.value = true;
}

// 显示客群明细
function showCustomerDetail() {
  customerDrawerVisible.value = true;
}

// 生命周期
onMounted(() => {
  initMap();
});

onBeforeUnmount(() => {
  if (map) {
    map.destroy();
  }
});
</script>

<template>
  <div class="h-full w-full flex flex-col">
    <Page content-full-height fixed-header>
      <Row :gutter="16">
        <!-- 热力图区域（左侧，占3/4宽度） -->
        <Col :span="18">
          <Card style="min-height: 600px" title="热力图" :body-style="{ height: '600px' }">
            <div id="heatmap-container" class="h-full w-full"></div>

            <!-- 图例 -->
            <div class="absolute bottom-4 left-4 bg-white dark:bg-gray-800 p-4 rounded shadow z-10">
              <div class="font-bold mb-2">图例</div>
              <div class="space-y-1">
                <div class="flex items-center gap-2">
                  <div class="h-4 w-4 bg-blue-500"></div>
                  <span class="text-sm">低密度</span>
                </div>
                <div class="flex items-center gap-2">
                  <div class="h-4 w-4 bg-yellow-500"></div>
                  <span class="text-sm">中密度</span>
                </div>
                <div class="flex items-center gap-2">
                  <div class="h-4 w-4 bg-red-500"></div>
                  <span class="text-sm">高密度</span>
                </div>
              </div>
            </div>
          </Card>
        </Col>

        <!-- 筛选条件区域（右侧，占1/4宽度） -->
        <Col :span="6">
          <Card style="min-height: 600px" title="筛选条件">
            <template #extra>
              <Button type="primary" class="mr-2" @click="showStatistics">统计</Button>
              <Button type="primary" @click="showCustomerDetail">客群</Button>
            </template>
            <Form layout="vertical">
              <FormItem label="站点类型">
                <RadioGroup v-model:value="filterForm.stationType" @change="handleFilterChange">
                  <RadioButton value="">全部</RadioButton>
                  <RadioButton value="REQUIRED">必选开展</RadioButton>
                  <RadioButton value="OPTIONAL">可选开展</RadioButton>
                </RadioGroup>
              </FormItem>

              <FormItem label="客群类型">
                <CheckboxGroup
                  v-model:value="filterForm.customerTypes"
                  :options="customerTypeOptions"
                  @change="handleFilterChange"
                  class="customer-type-checkboxes"
                />
              </FormItem>

              <FormItem>
                <Button type="primary" :loading="loading" @click="loadData" block>查询</Button>
                <Button class="mt-2" @click="handleReset" block>重置</Button>
              </FormItem>
            </Form>
          </Card>
        </Col>
      </Row>
    </Page>

    <!-- 统计信息弹窗 -->
    <Modal
      v-model:open="statisticsModalVisible"
      title="统计信息"
      width="1000"
      :footer="null"
    >
      <Row :gutter="16" class="mb-4">
        <!-- 站点统计 -->
        <Col :span="8">
          <Card title="站点统计" size="small">
            <Statistic
              title="已开展站点数"
              :value="statistics?.totalStations || 0"
              :value-style="{ color: '#3f8600' }"
              class="mb-4"
            >
              <template #prefix>
                <CheckCircleOutlined />
              </template>
            </Statistic>
            <Statistic
              title="必选开展站点"
              :value="statistics?.requiredStations || 0"
              :value-style="{ color: '#cf1322' }"
              class="mb-4"
            >
              <template #prefix>
                <StarFilled />
              </template>
            </Statistic>
            <Statistic
              title="可选开展站点"
              :value="statistics?.optionalStations || 0"
              :value-style="{ color: '#1890ff' }"
            >
              <template #prefix>
                <StarOutlined />
              </template>
            </Statistic>
          </Card>
        </Col>

        <!-- 必选站点统计 -->
        <Col :span="8">
          <Card title="必选站点统计" size="small">
            <Statistic
              title="覆盖村数"
              :value="statistics?.requiredStatistics?.villageCount || 0"
              class="mb-4"
            />
            <Statistic
              title="户籍人口"
              :value="statistics?.requiredStatistics?.totalPopulation || 0"
              class="mb-4"
            />
            <Statistic
              title="客群总数"
              :value="statistics?.requiredStatistics?.totalCustomerCount || 0"
            />
          </Card>
        </Col>

        <!-- 可选站点统计 -->
        <Col :span="8">
          <Card title="可选站点统计" size="small">
            <Statistic
              title="覆盖村数"
              :value="statistics?.optionalStatistics?.villageCount || 0"
              class="mb-4"
            />
            <Statistic
              title="户籍人口"
              :value="statistics?.optionalStatistics?.totalPopulation || 0"
              class="mb-4"
            />
            <Statistic
              title="客群总数"
              :value="statistics?.optionalStatistics?.totalCustomerCount || 0"
            />
          </Card>
        </Col>
      </Row>
    </Modal>

    <!-- 九类客群明细抽屉 -->
    <Drawer
      v-model:open="customerDrawerVisible"
      title="九类客群明细"
      width="500"
      placement="right"
    >
      <Descriptions :column="1" bordered>
        <DescriptionsItem
          v-for="(count, type) in statistics?.customerTypeStatistics"
          :key="type"
          :label="type"
        >
          {{ count }}
        </DescriptionsItem>
      </Descriptions>
    </Drawer>
  </div>
</template>

<style scoped>
#heatmap-container {
  position: relative;
}

.customer-type-checkboxes {
  display: flex !important;
  flex-direction: column !important;
}

.customer-type-checkboxes :deep(.ant-checkbox-wrapper) {
  margin-bottom: 8px;
}
</style>
