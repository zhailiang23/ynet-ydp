<script lang="ts" setup>
import { onBeforeUnmount, ref, watch } from 'vue';
import { message } from 'ant-design-vue';
import AMapLoader from '@amap/amap-jsapi-loader';

const props = defineProps<{
  modelValue?: { lng: number; lat: number; address: string; villageName: string };
  gridBoundary?: string; // 网格边界（GeoJSON格式）
}>();

const emit = defineEmits<{
  (e: 'update:modelValue', value: { lng: number; lat: number; address: string; villageName: string }): void;
}>();

let map: any = null;
let marker: any = null;
let geocoder: any = null;
let gridPolygon: any = null; // 网格边界多边形

const selectedLocation = ref(props.modelValue);

// 初始化高德地图
async function initMap() {
  try {
    // 设置安全密钥
    (window as any)._AMapSecurityConfig = {
      securityJsCode: '6b249e3430044ff13e483020a9c7efb9',
    };

    // 加载高德地图
    const AMap = await AMapLoader.load({
      key: '36b4c632e48e5d3a7112513fef24bb72',
      version: '2.0',
      plugins: ['AMap.Marker', 'AMap.Geocoder', 'AMap.Polygon'],
    });

    // 初始化地理编码服务
    geocoder = new AMap.Geocoder();

    // 创建地图实例
    map = new AMap.Map('village-map-container', {
      zoom: 13,
      center: props.modelValue ? [props.modelValue.lng, props.modelValue.lat] : [113.6254, 34.7466],
      viewMode: '2D',
    });

    // 如果有初始值，添加标记
    if (props.modelValue) {
      addMarker(props.modelValue.lng, props.modelValue.lat);
    }

    // 监听地图点击事件
    map.on('click', (e: any) => {
      const { lng, lat } = e.lnglat;
      addMarker(lng, lat);
      // 逆地理编码获取地址信息
      getAddressFromLocation(lng, lat);
    });
  } catch (error) {
    console.error('地图初始化失败:', error);
    message.error('地图加载失败');
  }
}

// 添加或更新标记
function addMarker(lng: number, lat: number) {
  const AMap = (window as any).AMap;

  // 移除旧标记
  if (marker) {
    map.remove(marker);
  }

  // 创建新标记
  marker = new AMap.Marker({
    position: [lng, lat],
    title: '选定位置',
    draggable: true, // 可拖拽
  });

  map.add(marker);

  // 监听标记拖拽结束事件
  marker.on('dragend', (e: any) => {
    const position = marker.getPosition();
    getAddressFromLocation(position.lng, position.lat);
  });
}

// 逆地理编码：根据经纬度获取地址
function getAddressFromLocation(lng: number, lat: number) {
  geocoder.getAddress([lng, lat], (status: string, result: any) => {
    if (status === 'complete' && result.info === 'OK') {
      const addressComponent = result.regeocode.addressComponent;
      const formattedAddress = result.regeocode.formattedAddress;

      // 提取村名称（优先使用 township 乡镇名称）
      const villageName = addressComponent.township || addressComponent.district || '';

      const location = {
        lng,
        lat,
        address: formattedAddress,
        villageName,
      };

      selectedLocation.value = location;
      emit('update:modelValue', location);

      message.success(`已选择位置：${villageName || formattedAddress}`);
    } else {
      message.error('获取地址信息失败');
    }
  });
}

// 绘制网格边界
function drawGridBoundary(boundaryGeoJSON: string | undefined) {
  if (!map || !boundaryGeoJSON) {
    return;
  }

  const AMap = (window as any).AMap;

  // 移除旧的多边形
  if (gridPolygon) {
    map.remove(gridPolygon);
    gridPolygon = null;
  }

  try {
    // 解析 GeoJSON
    const geoJSON = JSON.parse(boundaryGeoJSON);

    // GeoJSON 格式可能是 Polygon 或 MultiPolygon
    let coordinates: any[] = [];

    if (geoJSON.type === 'Polygon') {
      coordinates = geoJSON.coordinates[0]; // Polygon的第一个环
    } else if (geoJSON.type === 'MultiPolygon') {
      // MultiPolygon取第一个多边形的第一个环
      coordinates = geoJSON.coordinates[0][0];
    } else if (geoJSON.type === 'Feature' && geoJSON.geometry) {
      // 如果是 Feature，提取 geometry
      if (geoJSON.geometry.type === 'Polygon') {
        coordinates = geoJSON.geometry.coordinates[0];
      } else if (geoJSON.geometry.type === 'MultiPolygon') {
        coordinates = geoJSON.geometry.coordinates[0][0];
      }
    }

    if (coordinates && coordinates.length > 0) {
      // 创建多边形
      gridPolygon = new AMap.Polygon({
        path: coordinates.map((coord: any) => [coord[0], coord[1]]), // [lng, lat]
        strokeColor: '#1890ff', // 边框颜色：蓝色
        strokeWeight: 2, // 边框宽度
        strokeOpacity: 0.8, // 边框透明度
        fillColor: '#1890ff', // 填充颜色：蓝色
        fillOpacity: 0.15, // 填充透明度
        zIndex: 50,
        bubble: true, // 允许事件冒泡，让地图点击事件正常触发
        clickable: false, // 多边形不可点击
      });

      map.add(gridPolygon);

      // 将地图视野调整到多边形区域
      map.setFitView([gridPolygon]);
    }
  } catch (error) {
    console.error('绘制网格边界失败:', error);
  }
}

// 监听外部值变化
watch(() => props.modelValue, (newValue) => {
  if (newValue && map) {
    addMarker(newValue.lng, newValue.lat);
    map.setCenter([newValue.lng, newValue.lat]);
  }
});

// 监听网格边界变化
watch(() => props.gridBoundary, (newBoundary) => {
  drawGridBoundary(newBoundary);
});

// 组件挂载时初始化地图
onBeforeUnmount(() => {
  if (map) {
    map.destroy();
    map = null;
  }
});

// 在组件挂载后初始化地图
setTimeout(() => {
  initMap();
}, 100);
</script>

<template>
  <div class="village-map-select">
    <div class="mb-2 text-sm text-gray-600">
      点击地图选择村庄位置，标记可拖动调整
    </div>
    <div id="village-map-container" class="h-full w-full rounded-lg border"></div>
  </div>
</template>

<style scoped>
.village-map-select {
  height: 100%;
  display: flex;
  flex-direction: column;
}

#village-map-container {
  flex: 1;
  min-height: 400px;
}
</style>
