<script setup lang="ts">
import { onMounted, ref, watch } from 'vue';
import AMapLoader from '@amap/amap-jsapi-loader';
import { generateCirclePolygon, geoJsonToString } from '#/utils/geo-utils';

const emit = defineEmits(['update:modelValue', 'update:longitude', 'update:latitude', 'update:address']);

const props = defineProps({
  modelValue: String,  // GeoJSON 字符串
  longitude: Number,
  latitude: Number,
  address: String,  // 地址
  radiusMeters: {
    type: Number,
    default: 3000,  // 默认 3km
  },
});

const mapContainer = ref<HTMLDivElement | null>(null);
let map: any = null;
let marker: any = null;
let circle: any = null;
let geocoder: any = null;

onMounted(async () => {
  const AMap = await AMapLoader.load({
    key: import.meta.env.VITE_AMAP_KEY,  // 从环境变量读取
    version: '2.0',
    plugins: ['AMap.Geocoder'],  // 加载逆地理编码插件
  });

  map = new AMap.Map(mapContainer.value, {
    zoom: 13,
    center: [113.6234, 34.7490], // 郑州
  });

  // 初始化逆地理编码服务
  geocoder = new AMap.Geocoder({
    radius: 1000,  // 范围，单位：米
  });

  // 点击地图事件
  map.on('click', (e: any) => {
    const lng = e.lnglat.lng;
    const lat = e.lnglat.lat;

    updateMapPoint(lng, lat);

    // 逆地理编码：根据坐标获取地址
    geocoder.getAddress([lng, lat], (status: string, result: any) => {
      if (status === 'complete' && result.info === 'OK') {
        const address = result.regeocode.formattedAddress;
        emit('update:address', address);
      }
    });
  });

  // 如果有初始坐标，显示
  if (props.longitude && props.latitude) {
    updateMapPoint(props.longitude, props.latitude);
  }
});

// 监听半径变化
watch(() => props.radiusMeters, (_newRadius) => {
  if (props.longitude && props.latitude) {
    updateMapPoint(props.longitude, props.latitude);
  }
});

// 监听经纬度变化（编辑时数据异步加载）
watch(
  () => [props.longitude, props.latitude],
  ([lng, lat]) => {
    if (lng && lat && map) {
      console.log('📍 经纬度变化，更新地图:', lng, lat);
      updateMapPoint(lng, lat);
    }
  },
  { deep: true }
);

function updateMapPoint(lng: number, lat: number) {
  const AMap = (window as any).AMap;

  // 清除旧标记和圆形
  if (marker) map.remove(marker);
  if (circle) map.remove(circle);

  // 添加标记
  marker = new AMap.Marker({
    position: [lng, lat],
    title: '惠农站点位置',
  });
  map.add(marker);

  // 绘制圆形
  circle = new AMap.Circle({
    center: [lng, lat],
    radius: props.radiusMeters,
    strokeColor: '#52c41a',  // 绿色边框
    strokeWeight: 2,
    fillOpacity: 0.25,
    fillColor: '#52c41a',    // 绿色填充
    bubble: true,
    clickable: false,
  });
  map.add(circle);

  // 调整视野
  map.setFitView([circle]);

  // 生成 GeoJSON
  const geoJson = generateCirclePolygon(lng, lat, props.radiusMeters);
  const geoJsonString = geoJsonToString(geoJson);

  // 发送事件
  emit('update:longitude', lng);
  emit('update:latitude', lat);
  emit('update:modelValue', geoJsonString);
}
</script>

<template>
  <div ref="mapContainer" class="h-full w-full"></div>
</template>
