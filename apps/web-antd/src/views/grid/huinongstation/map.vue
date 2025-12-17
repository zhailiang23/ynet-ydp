<template>
  <div class="h-full w-full">
    <Page
      content-full-height
      fixed-header
      :header-props="{
        title: `站点地图 - ${stationData?.stationName || '加载中...'}`,
      }"
    >
      <div class="relative h-full w-full">
        <!-- 地图容器 -->
        <div id="amap-container" class="h-full w-full"></div>

        <!-- 信息面板 -->
        <Card class="absolute left-4 top-4 w-80 shadow-lg">
          <template #title>站点信息</template>
          <Descriptions v-if="stationData" :column="1" size="small">
            <DescriptionsItem label="站点名称">
              {{ stationData.stationName }}
            </DescriptionsItem>
            <DescriptionsItem label="站点编号">
              {{ stationData.stationCode }}
            </DescriptionsItem>
            <DescriptionsItem label="地址">
              {{ stationData.address || '-' }}
            </DescriptionsItem>
            <DescriptionsItem label="网格营销">
              {{ stationData.gridMarketingFlag || '-' }}
            </DescriptionsItem>
            <DescriptionsItem label="惠农人员">
              {{ stationData.specialistName || '-' }}
              ({{ stationData.specialistEmployeeNo || '-' }})
            </DescriptionsItem>
            <DescriptionsItem
              v-if="stationData.gridInfo"
              label="所属网格"
            >
              {{ stationData.gridInfo.gridName }}
              ({{ stationData.gridInfo.gridCode }})
            </DescriptionsItem>
          </Descriptions>
        </Card>

        <!-- 图例 -->
        <Card class="absolute bottom-4 left-4 shadow-lg">
          <template #title>图例</template>
          <div class="space-y-2">
            <div class="flex items-center gap-2">
              <div class="h-4 w-4 rounded-full bg-red-500"></div>
              <span class="text-sm">惠农站点</span>
            </div>
            <div v-if="stationData?.gridInfo" class="flex items-center gap-2">
              <div class="h-4 w-4 border-2 border-blue-500 bg-blue-500/20"></div>
              <span class="text-sm">网格边界</span>
            </div>
            <div class="flex items-center gap-2">
              <div class="h-4 w-4 rounded-full bg-green-500"></div>
              <span class="text-sm">营销信息 ({{ marketingMarkers.length }})</span>
            </div>
            <div class="flex items-center gap-2">
              <div class="h-4 w-4 rounded-full bg-orange-500"></div>
              <span class="text-sm">贷款客户 ({{ customerMarkers.length }})</span>
            </div>
          </div>
        </Card>
      </div>
    </Page>
  </div>
</template>

<script lang="ts" setup>
import { onBeforeUnmount, onMounted, ref } from 'vue';

import { Page } from '@vben/common-ui';

import { Card, Descriptions, DescriptionsItem, message } from 'ant-design-vue';
import AMapLoader from '@amap/amap-jsapi-loader';

import {
  getHuinongStationMapDataApi,
  getMarketingMarkersApi,
  getCustomerMarkersApi,
  type GridHuinongStationMapApi,
} from '#/api/grid/huinong-station-map';

// 获取路由参数
const props = defineProps<{
  id: string;
}>();

const stationData =
  ref<GridHuinongStationMapApi.HuinongStationMapVO | null>(null);
const marketingMarkers = ref<GridHuinongStationMapApi.MarketingMarkerVO[]>([]);
const customerMarkers = ref<GridHuinongStationMapApi.CustomerMarkerVO[]>([]);
let map: any = null;
let stationMarker: any = null;
let gridPolygon: any = null;
let marketingMarkerInstances: any[] = [];
let customerMarkerInstances: any[] = [];

// 初始化高德地图
async function initMap() {
  try {
    // 设置安全密钥（2021年12月02日之后申请的key必须配置）
    (window as any)._AMapSecurityConfig = {
      securityJsCode: '6b249e3430044ff13e483020a9c7efb9',
    };

    // 加载高德地图
    const AMap = await AMapLoader.load({
      key: '36b4c632e48e5d3a7112513fef24bb72',
      version: '2.0',
      plugins: ['AMap.Marker', 'AMap.Polygon', 'AMap.InfoWindow'],
    });

    // 创建地图实例
    map = new AMap.Map('amap-container', {
      zoom: 13,
      center: [113.625_4, 34.746_6], // 默认中心点
      viewMode: '2D',
    });

    // 加载站点数据
    await loadStationData();
  } catch (error) {
    console.error('地图初始化失败:', error);
    message.error('地图加载失败');
  }
}

// 加载站点数据
async function loadStationData() {
  try {
    const data = await getHuinongStationMapDataApi(Number(props.id));
    stationData.value = data;

    if (!data || !map) return;

    // 加载高德地图
    const AMap = (window as any).AMap;

    // 1. 添加站点标注
    if (data.longitude && data.latitude) {
      stationMarker = new AMap.Marker({
        position: [data.longitude, data.latitude],
        title: data.stationName,
        icon: new AMap.Icon({
          size: new AMap.Size(32, 32),
          image: '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-red.png',
          imageSize: new AMap.Size(32, 32),
        }),
      });

      map.add(stationMarker);

      // 设置地图中心
      map.setCenter([data.longitude, data.latitude]);

      // 添加信息窗体
      const infoWindow = new AMap.InfoWindow({
        content: `
          <div class="p-2">
            <h3 class="font-bold">${data.stationName}</h3>
            <p class="text-sm text-gray-600">${data.address || ''}</p>
          </div>
        `,
        offset: new AMap.Pixel(0, -32),
      });

      stationMarker.on('click', () => {
        infoWindow.open(map, stationMarker.getPosition());
      });
    }

    // 2. 绘制网格边界
    if (data.gridInfo?.boundaryGeometry) {
      try {
        const geoJSON = JSON.parse(data.gridInfo.boundaryGeometry);
        const coordinates = geoJSON.coordinates[0].map((coord: number[]) => [
          coord[0],
          coord[1],
        ]);

        gridPolygon = new AMap.Polygon({
          path: coordinates,
          fillColor: '#3b82f6',
          fillOpacity: 0.2,
          strokeColor: '#3b82f6',
          strokeWeight: 2,
          strokeStyle: 'solid',
        });

        map.add(gridPolygon);

        // 调整地图视野以包含网格
        map.setFitView([stationMarker, gridPolygon], false, [50, 50, 50, 50]);
      } catch (error) {
        console.error('绘制网格边界失败:', error);
      }
    }

    // 3. 加载营销信息标记
    try {
      const marketingData = await getMarketingMarkersApi(Number(props.id));
      marketingMarkers.value = marketingData;
      console.log('🟢 营销信息标记数据:', marketingData);

      marketingData.forEach((marker) => {
        console.log('🟢 处理营销标记:', marker);
        if (marker.longitude && marker.latitude) {
          // 使用 icon 属性创建绿色标记
          const markerInstance = new AMap.Marker({
            position: [marker.longitude, marker.latitude],
            title: marker.villageName,
            icon: new AMap.Icon({
              size: new AMap.Size(25, 34),
              image: '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',
              imageSize: new AMap.Size(25, 34),
            }),
          });

          map.add(markerInstance);
          marketingMarkerInstances.push(markerInstance);
          console.log('🟢 营销标记已添加到地图，总数:', marketingMarkerInstances.length);

          // 添加点击事件
          const infoWindow = new AMap.InfoWindow({
            content: `
              <div class="p-2">
                <h3 class="font-bold text-green-600">营销信息</h3>
                <p class="text-sm"><strong>村名:</strong> ${marker.villageName}</p>
                <p class="text-sm"><strong>地址:</strong> ${marker.villageAddress || '-'}</p>
                <p class="text-sm"><strong>户籍人口:</strong> ${marker.registeredPopulation || '-'}</p>
                <p class="text-sm"><strong>常住人口:</strong> ${marker.residentPopulation || '-'}</p>
              </div>
            `,
            offset: new AMap.Pixel(0, -32),
          });

          markerInstance.on('click', () => {
            infoWindow.open(map, markerInstance.getPosition());
          });
        }
      });
    } catch (error) {
      console.error('加载营销信息标记失败:', error);
    }

    // 4. 加载客户标记
    try {
      const customerData = await getCustomerMarkersApi(Number(props.id));
      customerMarkers.value = customerData;
      console.log('🟠 贷款客户标记数据:', customerData);

      customerData.forEach((marker) => {
        console.log('🟠 处理客户标记:', marker);
        if (marker.longitude && marker.latitude) {
          // 使用 icon 属性创建橙色标记
          const markerInstance = new AMap.Marker({
            position: [marker.longitude, marker.latitude],
            title: marker.customerName,
            icon: new AMap.Icon({
              size: new AMap.Size(25, 34),
              image: '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-red.png',
              imageSize: new AMap.Size(25, 34),
            }),
          });

          map.add(markerInstance);
          customerMarkerInstances.push(markerInstance);
          console.log('🟠 客户标记已添加到地图，总数:', customerMarkerInstances.length);

          // 添加点击事件
          const infoWindow = new AMap.InfoWindow({
            content: `
              <div class="p-2">
                <h3 class="font-bold text-orange-600">贷款客户</h3>
                <p class="text-sm"><strong>客户:</strong> ${marker.customerName}</p>
                <p class="text-sm"><strong>电话:</strong> ${marker.phone}</p>
                <p class="text-sm"><strong>类别:</strong> ${marker.customerCategory || '-'}</p>
                <p class="text-sm"><strong>地址:</strong> ${marker.businessAddress || '-'}</p>
                ${marker.loanAmount ? `<p class="text-sm"><strong>贷款金额:</strong> ¥${(marker.loanAmount / 10000).toFixed(2)}万</p>` : ''}
              </div>
            `,
            offset: new AMap.Pixel(0, -32),
          });

          markerInstance.on('click', () => {
            infoWindow.open(map, markerInstance.getPosition());
          });
        }
      });
    } catch (error) {
      console.error('加载客户标记失败:', error);
    }
  } catch (error) {
    console.error('加载站点数据失败:', error);
    message.error('加载站点数据失败');
  }
}

onMounted(() => {
  initMap();
});

onBeforeUnmount(() => {
  // 清理地图资源
  if (map) {
    // 清理所有标记实例
    marketingMarkerInstances.forEach((marker) => map.remove(marker));
    customerMarkerInstances.forEach((marker) => map.remove(marker));
    marketingMarkerInstances = [];
    customerMarkerInstances = [];

    map.destroy();
  }
});
</script>
