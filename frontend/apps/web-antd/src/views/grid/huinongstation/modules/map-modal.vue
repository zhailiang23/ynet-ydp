<script lang="ts" setup>
import { onBeforeUnmount, ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { getDictLabel } from '@vben/hooks';
import { Card, Descriptions, DescriptionsItem, message } from 'ant-design-vue';
import AMapLoader from '@amap/amap-jsapi-loader';

import {
  getHuinongStationMapDataApi,
  getMarketingMarkersApi,
  getCustomerMarkersApi,
  type GridHuinongStationMapApi,
} from '#/api/grid/huinong-station-map';

const stationData =
  ref<GridHuinongStationMapApi.HuinongStationMapVO | null>(null);
const marketingMarkers =
  ref<GridHuinongStationMapApi.MarketingMarkerVO[]>([]);
const customerMarkers = ref<GridHuinongStationMapApi.CustomerMarkerVO[]>([]);
let map: any = null;
let stationMarker: any = null;
let gridPolygon: any = null;
let marketingMarkersList: any[] = [];
let customerMarkersList: any[] = [];

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
    map = new AMap.Map('amap-container-modal', {
      zoom: 13,
      center: [113.625_4, 34.746_6], // 默认中心点
      viewMode: '2D',
    });
  } catch (error) {
    console.error('地图初始化失败:', error);
    message.error('地图加载失败');
  }
}

// 加载站点数据
async function loadStationData(stationId: number) {
  if (!stationId) return;

  try {
    // 如果地图还未初始化,先初始化
    if (!map) {
      await initMap();
    }

    const data = await getHuinongStationMapDataApi(stationId);
    stationData.value = data;

    if (!data || !map) return;

    // 加载高德地图
    const AMap = (window as any).AMap;

    // 清除旧标记
    if (stationMarker) {
      map.remove(stationMarker);
    }
    if (gridPolygon) {
      map.remove(gridPolygon);
    }
    if (marketingMarkersList.length > 0) {
      map.remove(marketingMarkersList);
      marketingMarkersList = [];
    }
    if (customerMarkersList.length > 0) {
      map.remove(customerMarkersList);
      customerMarkersList = [];
    }

    // 1. 添加站点标注和网格边界（需要坐标转换）
    if (data.longitude && data.latitude) {
      // WGS84 坐标转换为高德地图 GCJ-02 坐标
      AMap.convertFrom(
        [data.longitude, data.latitude],
        'gps',
        (status: string, result: any) => {
          if (result.info === 'ok') {
            const convertedPosition = result.locations[0];

            // 创建标记点
            stationMarker = new AMap.Marker({
              position: convertedPosition,
              title: data.stationName,
              icon: new AMap.Icon({
                size: new AMap.Size(32, 32),
                image:
                  '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-red.png',
                imageSize: new AMap.Size(32, 32),
              }),
            });

            map.add(stationMarker);
            map.setCenter(convertedPosition);

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

            // 2. 绘制网格边界（在标记创建后）
            if (data.gridInfo?.boundaryGeometry) {
              try {
                const geoJSON = JSON.parse(data.gridInfo.boundaryGeometry);
                const wgs84Coordinates = geoJSON.coordinates[0].map(
                  (coord: number[]) => [coord[0], coord[1]],
                );

                // 批量转换边界坐标点
                AMap.convertFrom(
                  wgs84Coordinates,
                  'gps',
                  (status: string, result: any) => {
                    if (result.info === 'ok') {
                      const gcj02Coordinates = result.locations;

                      gridPolygon = new AMap.Polygon({
                        path: gcj02Coordinates,
                        fillColor: '#3b82f6',
                        fillOpacity: 0.2,
                        strokeColor: '#3b82f6',
                        strokeWeight: 2,
                        strokeStyle: 'solid',
                      });

                      map.add(gridPolygon);

                      // 调整地图视野以包含网格和标记
                      map.setFitView([stationMarker, gridPolygon], false, [
                        50, 50, 50, 50,
                      ]);
                    }
                  },
                );
              } catch (error) {
                console.error('绘制网格边界失败:', error);
              }
            }
          }
        },
      );
    }

    // 3. 加载并显示营销信息标记
    try {
      const marketingData = await getMarketingMarkersApi(stationId);
      marketingMarkers.value = marketingData;

      if (marketingData && marketingData.length > 0) {
        // 批量转换营销信息坐标
        const marketingCoords = marketingData.map((m) => [
          m.longitude,
          m.latitude,
        ]);

        AMap.convertFrom(
          marketingCoords,
          'gps',
          (status: string, result: any) => {
            if (result.info === 'ok') {
              marketingData.forEach((marker, index) => {
                const convertedPos = result.locations[index];

                // 创建营销信息标记（绿色图标）
                const marketingMarker = new AMap.Marker({
                  position: convertedPos,
                  title: marker.villageName,
                  icon: new AMap.Icon({
                    size: new AMap.Size(28, 28),
                    image:
                      '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-default.png',
                    imageSize: new AMap.Size(28, 28),
                  }),
                });

                // 添加信息窗体
                const infoContent = `
                  <div class="p-2" style="min-width: 200px;">
                    <h3 class="font-bold text-base mb-2">${marker.villageName}</h3>
                    ${marker.villageAddress ? `<p class="text-sm text-gray-600 mb-1">地址: ${marker.villageAddress}</p>` : ''}
                    ${marker.registeredPopulation ? `<p class="text-sm">户籍人口: ${marker.registeredPopulation}人</p>` : ''}
                    ${marker.residentPopulation ? `<p class="text-sm">常住人口: ${marker.residentPopulation}人</p>` : ''}
                    ${marker.mainIndustries ? `<p class="text-sm">主要产业: ${marker.mainIndustries}</p>` : ''}
                    ${marker.industrySituation ? `<p class="text-sm mt-1">${marker.industrySituation}</p>` : ''}
                  </div>
                `;

                const infoWindow = new AMap.InfoWindow({
                  content: infoContent,
                  offset: new AMap.Pixel(0, -28),
                });

                marketingMarker.on('click', () => {
                  infoWindow.open(map, marketingMarker.getPosition());
                });

                marketingMarkersList.push(marketingMarker);
                map.add(marketingMarker);
              });
            }
          },
        );
      }
    } catch (error) {
      console.error('加载营销信息标记失败:', error);
    }

    // 4. 加载并显示客户标记
    try {
      const customerData = await getCustomerMarkersApi(stationId);
      customerMarkers.value = customerData;

      if (customerData && customerData.length > 0) {
        // 批量转换客户坐标
        const customerCoords = customerData.map((c) => [
          c.longitude,
          c.latitude,
        ]);

        AMap.convertFrom(
          customerCoords,
          'gps',
          (status: string, result: any) => {
            if (result.info === 'ok') {
              customerData.forEach((marker, index) => {
                const convertedPos = result.locations[index];

                // 创建客户标记（橙色图标）
                const customerMarker = new AMap.Marker({
                  position: convertedPos,
                  title: marker.customerName,
                  icon: new AMap.Icon({
                    size: new AMap.Size(28, 28),
                    image:
                      '//a.amap.com/jsapi_demos/static/demo-center/icons/poi-marker-orange.png',
                    imageSize: new AMap.Size(28, 28),
                  }),
                });

                // 添加信息窗体
                const infoContent = `
                  <div class="p-2" style="min-width: 200px;">
                    <h3 class="font-bold text-base mb-2">${marker.customerName}</h3>
                    <p class="text-sm text-gray-600 mb-1">电话: ${marker.phone}</p>
                    ${marker.customerCategory ? `<p class="text-sm">客户类型: ${marker.customerCategory}</p>` : ''}
                    ${marker.subdivisionType ? `<p class="text-sm">细分类型: ${marker.subdivisionType}</p>` : ''}
                    ${marker.businessAddress ? `<p class="text-sm">经营地址: ${marker.businessAddress}</p>` : ''}
                    ${marker.loanAmount ? `<p class="text-sm font-semibold text-blue-600">贷款金额: ¥${(marker.loanAmount / 10000).toFixed(2)}万</p>` : ''}
                    ${marker.overdueStatus ? `<p class="text-sm ${marker.overdueStatus === '正常' ? 'text-green-600' : 'text-red-600'}">逾期状态: ${marker.overdueStatus}</p>` : ''}
                  </div>
                `;

                const infoWindow = new AMap.InfoWindow({
                  content: infoContent,
                  offset: new AMap.Pixel(0, -28),
                });

                customerMarker.on('click', () => {
                  infoWindow.open(map, customerMarker.getPosition());
                });

                customerMarkersList.push(customerMarker);
                map.add(customerMarker);
              });
            }
          },
        );
      }
    } catch (error) {
      console.error('加载客户标记失败:', error);
    }
  } catch (error) {
    console.error('加载站点数据失败:', error);
    message.error('加载站点数据失败');
  }
}

const [Modal, modalApi] = useVbenModal({
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      // 关闭时清理数据
      stationData.value = null;
      marketingMarkers.value = [];
      customerMarkers.value = [];
      return;
    }
    // 打开时加载数据
    const data = modalApi.getData<{ stationId: number }>();
    if (data?.stationId) {
      modalApi.lock();
      try {
        await loadStationData(data.stationId);
      } finally {
        modalApi.unlock();
      }
    }
  },
});

onBeforeUnmount(() => {
  // 清理地图资源
  if (map) {
    map.destroy();
    map = null;
  }
});
</script>

<template>
  <Modal title="站点地图" class="w-4/5">
    <div class="flex h-[600px] w-full gap-4">
      <!-- 左侧信息面板 -->
      <div class="w-80 flex-shrink-0">
        <Card title="站点信息" class="h-full">
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
              {{ getDictLabel('grid_marketing_flag', stationData.gridMarketingFlag) || '-' }}
            </DescriptionsItem>
            <DescriptionsItem label="管理行名称">
              {{ stationData.manageBranchName || '-' }}
            </DescriptionsItem>
            <DescriptionsItem label="二级支行名称">
              {{ stationData.subBranchName || '-' }}
            </DescriptionsItem>
            <DescriptionsItem label="惠农人员">
              {{ stationData.specialistName || '-' }}
              ({{ stationData.specialistEmployeeNo || '-' }})
            </DescriptionsItem>
            <DescriptionsItem v-if="stationData.gridInfo" label="所属网格">
              {{ stationData.gridInfo.gridName }}
              ({{ stationData.gridInfo.gridCode }})
            </DescriptionsItem>
          </Descriptions>

          <!-- 图例 -->
          <div class="mt-4 space-y-2 border-t pt-4">
            <div class="mb-2 font-medium">图例</div>
            <div class="flex items-center gap-2">
              <div class="h-4 w-4 rounded-full bg-red-500"></div>
              <span class="text-sm">惠农站点</span>
            </div>
            <div v-if="stationData?.gridInfo" class="flex items-center gap-2">
              <div
                class="h-4 w-4 border-2 border-blue-500 bg-blue-500/20"
              ></div>
              <span class="text-sm">网格边界</span>
            </div>
            <div v-if="marketingMarkers.length > 0" class="flex items-center gap-2">
              <div class="h-4 w-4 rounded-full bg-green-500"></div>
              <span class="text-sm">营销信息 ({{ marketingMarkers.length }})</span>
            </div>
            <div v-if="customerMarkers.length > 0" class="flex items-center gap-2">
              <div class="h-4 w-4 rounded-full bg-orange-500"></div>
              <span class="text-sm">贷款客户 ({{ customerMarkers.length }})</span>
            </div>
          </div>
        </Card>
      </div>

      <!-- 右侧地图容器 -->
      <div class="flex-1">
        <div id="amap-container-modal" class="h-full w-full rounded-lg"></div>
      </div>
    </div>
  </Modal>
</template>
