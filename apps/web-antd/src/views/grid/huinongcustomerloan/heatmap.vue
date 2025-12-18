<script setup lang="ts">
import { onMounted, onBeforeUnmount, ref, reactive, watch } from 'vue';
import { Page } from '@vben/common-ui';
import { Card, Row, Col, Form, FormItem, RadioGroup, RadioButton, Switch, message } from 'ant-design-vue';
import AMapLoader from '@amap/amap-jsapi-loader';
import {
  getHeatmapData,
  getCustomerMarkers,
  type HeatmapReqVO,
  type HeatmapDataVO,
  type CustomerMarkerVO,
} from '#/api/grid/huinongcustomerloan';

// 热力值类型选项
const metricTypeOptions = [
  { label: '客户数量', value: 'CUSTOMER_COUNT' },
  { label: '进件数量', value: 'APPLIED_COUNT' },
  { label: '审批通过数量', value: 'APPROVED_COUNT' },
  { label: '贷款余额', value: 'LOAN_BALANCE' },
];

// 状态
const heatmapData = ref<HeatmapDataVO[]>([]);
const customerMarkers = ref<CustomerMarkerVO[]>([]);
const filterForm = reactive<HeatmapReqVO>({
  metricType: 'CUSTOMER_COUNT',
});
const loading = ref(false);
const showMarkers = ref(false); // 是否显示客户标记（默认隐藏）

// 地图相关
let map: any = null;
let heatmap: any = null;
let markers: any[] = [];

// 初始化高德地图
async function initMap() {
  try {
    (window as any)._AMapSecurityConfig = {
      securityJsCode: '6b249e3430044ff13e483020a9c7efb9',
    };

    const AMap = await AMapLoader.load({
      key: '36b4c632e48e5d3a7112513fef24bb72',
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
      radius: 30,
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

    // 并行加载热力图数据和客户标记数据
    const [heatData, markerData] = await Promise.all([
      getHeatmapData(filterForm),
      getCustomerMarkers(),
    ]);

    heatmapData.value = heatData;
    customerMarkers.value = markerData;

    // 更新热力图
    updateHeatmap(heatData);

    // 更新客户标记
    updateCustomerMarkers(markerData);

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
    count: Number(d.value), // 确保是数字类型
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

// 更新客户标记
function updateCustomerMarkers(data: CustomerMarkerVO[]) {
  if (!map) return;

  // 清除旧标记
  markers.forEach((marker) => marker.setMap(null));
  markers = [];

  // 如果不显示标记，直接返回
  if (!showMarkers.value) return;

  if (!data || data.length === 0) return;

  const AMap = (window as any).AMap;

  // 创建新标记
  data.forEach((customer) => {
    // 根据站点类型确定颜色：REQUIRED=红色，OPTIONAL=蓝色
    const color = customer.stationType === 'REQUIRED' ? '#FF0000' : '#0000FF';

    const marker = new AMap.Marker({
      position: new AMap.LngLat(customer.longitude, customer.latitude),
      icon: new AMap.Icon({
        size: new AMap.Size(25, 34),
        image: `//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-${
          customer.stationType === 'REQUIRED' ? 'red' : 'default'
        }.png`,
        imageSize: new AMap.Size(25, 34),
      }),
      offset: new AMap.Pixel(-13, -30),
      title: customer.customerName,
    });

    // 添加信息窗口
    const infoWindow = new AMap.InfoWindow({
      content: `
        <div style="padding: 10px;">
          <h4 style="margin: 0 0 10px 0;">${customer.customerName}</h4>
          <p style="margin: 5px 0;"><strong>客户类别:</strong> ${customer.customerCategory || '未知'}</p>
          <p style="margin: 5px 0;"><strong>站点类型:</strong> ${customer.stationType === 'REQUIRED' ? '必选站点' : '可选站点'}</p>
          <p style="margin: 5px 0;"><strong>进件状态:</strong> ${customer.isApplied ? '已进件' : '未进件'}</p>
          <p style="margin: 5px 0;"><strong>审批状态:</strong> ${customer.isApproved ? '已批准' : '未批准'}</p>
          <p style="margin: 5px 0;"><strong>贷款余额:</strong> ¥${(customer.loanBalance || 0).toLocaleString()}</p>
        </div>
      `,
      offset: new AMap.Pixel(0, -30),
    });

    marker.on('click', () => {
      infoWindow.open(map, marker.getPosition());
    });

    marker.setMap(map);
    markers.push(marker);
  });
}

// 热力值类型变化
function handleMetricTypeChange() {
  loadData();
}

// 切换客户标记显示
function handleToggleMarkers() {
  updateCustomerMarkers(customerMarkers.value);
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
        <!-- 热力图区域（左侧，占 3/4 宽度） -->
        <Col :span="18">
          <Card style="min-height: 600px" title="惠农贷款目标客户热力图" :body-style="{ height: '600px' }">
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
                <div class="mt-4 border-t pt-2">
                  <div class="font-bold mb-1">客户标记</div>
                  <div class="flex items-center gap-2">
                    <div class="h-3 w-3 bg-red-600 rounded-full"></div>
                    <span class="text-sm">必选站点</span>
                  </div>
                  <div class="flex items-center gap-2">
                    <div class="h-3 w-3 bg-blue-600 rounded-full"></div>
                    <span class="text-sm">可选站点</span>
                  </div>
                </div>
              </div>
            </div>
          </Card>
        </Col>

        <!-- 筛选条件区域（右侧，占 1/4 宽度） -->
        <Col :span="6">
          <Card style="min-height: 600px" title="热力值设置">
            <Form layout="vertical">
              <FormItem label="热力值类型">
                <RadioGroup
                  v-model:value="filterForm.metricType"
                  @change="handleMetricTypeChange"
                  class="metric-type-radios"
                >
                  <RadioButton
                    v-for="option in metricTypeOptions"
                    :key="option.value"
                    :value="option.value"
                  >
                    {{ option.label }}
                  </RadioButton>
                </RadioGroup>
              </FormItem>

              <FormItem label="显示客户标记">
                <Switch
                  v-model:checked="showMarkers"
                  @change="handleToggleMarkers"
                  checked-children="显示"
                  un-checked-children="隐藏"
                />
              </FormItem>

              <FormItem>
                <div class="text-sm text-gray-500 dark:text-gray-400">
                  <p class="mb-2"><strong>说明：</strong></p>
                  <ul class="list-disc list-inside space-y-1">
                    <li>客户数量：目标客户总数</li>
                    <li>进件数量：已申请的客户数</li>
                    <li>审批通过数量：已批准的客户数</li>
                    <li>贷款余额：贷款余额总和</li>
                  </ul>
                  <p class="mt-4"><strong>客户标记：</strong></p>
                  <ul class="list-disc list-inside space-y-1">
                    <li>红色标记：必选站点客户</li>
                    <li>蓝色标记：可选站点客户</li>
                  </ul>
                </div>
              </FormItem>
            </Form>
          </Card>
        </Col>
      </Row>
    </Page>
  </div>
</template>

<style scoped>
#heatmap-container {
  position: relative;
}

.metric-type-radios {
  display: flex !important;
  flex-direction: column !important;
}

.metric-type-radios :deep(.ant-radio-button-wrapper) {
  margin-bottom: 8px;
  text-align: center;
}
</style>
