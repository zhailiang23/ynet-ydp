<script setup lang="ts">
import { onMounted, ref, watch } from 'vue';
import AMapLoader from '@amap/amap-jsapi-loader';
import { generateCirclePolygon, geoJsonToString } from '#/utils/geo-utils';
import { getCommunityGridPage } from '#/api/grid/community-grid';
import { getZerodaiGridPage } from '#/api/grid/zerodai-grid';

const emit = defineEmits(['update:modelValue', 'update:longitude', 'update:latitude', 'update:locationName']);

const props = defineProps({
  modelValue: String,  // GeoJSON 字符串
  longitude: Number,
  latitude: Number,
  locationName: String,  // 位置名称
  radiusMeters: {
    type: Number,
    default: 1000,  // 默认 1km
  },
});

const mapContainer = ref<HTMLDivElement | null>(null);
let map: any = null;
let marker: any = null;
let circle: any = null;
let geocoder: any = null;
let otherGridPolygons: any[] = [];  // 存储其他网格的多边形

onMounted(async () => {
  const AMap = await AMapLoader.load({
    key: import.meta.env.VITE_AMAP_KEY,  // 从环境变量读取
    version: '2.0',
    plugins: [],  // 不在这里加载插件
  });

  map = new AMap.Map(mapContainer.value, {
    zoom: 13,
    center: [113.6234, 34.7490], // 郑州
  });

  // 使用 AMap.plugin 动态加载 Geocoder 插件（官方推荐方式）
  AMap.plugin('AMap.Geocoder', function() {
    geocoder = new AMap.Geocoder({
      city: '郑州市',  // 指定城市，提高精度
      radius: 1000,    // 范围，默认 500 米
    });
    console.log('✅ Geocoder 插件加载成功:', geocoder);
  });

  // 点击地图事件
  map.on('click', (e: any) => {
    const lng = e.lnglat.lng;
    const lat = e.lnglat.lat;

    updateMapPoint(lng, lat);
  });

  // 如果有初始坐标，显示（编辑模式，跳过逆地理编码）
  if (props.longitude && props.latitude) {
    updateMapPoint(props.longitude, props.latitude, true);
  }

  // 加载社区网格和零贷网格
  await loadOtherGrids();
});

// 监听半径变化
watch(() => props.radiusMeters, (_newRadius) => {
  if (props.longitude && props.latitude) {
    updateMapPoint(props.longitude, props.latitude, true);  // 半径变化时不需要重新获取位置名称
  }
});

// 监听经纬度变化（编辑时数据异步加载）
watch(
  () => [props.longitude, props.latitude],
  ([lng, lat]) => {
    if (lng && lat && map) {
      console.log('📍 经纬度变化，更新地图:', lng, lat);
      updateMapPoint(lng, lat, true);  // skipGeocoding=true，编辑模式下不需要重新获取位置名称
    }
  },
  { deep: true }
);

function updateMapPoint(lng: number, lat: number, skipGeocoding = false) {
  const AMap = (window as any).AMap;

  // 清除旧标记和圆形
  if (marker) map.remove(marker);
  if (circle) map.remove(circle);

  // 添加标记
  marker = new AMap.Marker({
    position: [lng, lat],
    title: '网点位置',
  });
  map.add(marker);

  // 绘制圆形
  circle = new AMap.Circle({
    center: [lng, lat],
    radius: props.radiusMeters,
    strokeColor: '#1890ff',
    strokeWeight: 2,
    fillOpacity: 0.25,
    fillColor: '#1890ff',
    bubble: true,  // 允许事件冒泡，这样点击圆形内部时地图的点击事件仍然会触发
    clickable: false,  // 圆形本身不可点击
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

  // 如果跳过逆地理编码（编辑模式下已有位置名称），直接返回
  if (skipGeocoding) {
    console.log('⏭️  跳过逆地理编码（编辑模式）');
    return;
  }

  // 逆地理编码获取位置名称（带重试机制）
  const tryGeocoding = (retryCount = 0) => {
    if (geocoder) {
      console.log('📍 调用逆地理编码，经纬度:', lng, lat);

      geocoder.getAddress([lng, lat], (status: string, result: any) => {
        console.log('=== 逆地理编码回调 ===');
        console.log('status:', status);
        console.log('result:', result);

        if (status === 'complete' && result.info === 'OK') {
          const addressComponent = result.regeocode.addressComponent;
          // 构建位置名称：省 + 市 + 区 + 街道
          const locationName = `${addressComponent.province}${addressComponent.city}${addressComponent.district}${addressComponent.township || ''}`;
          console.log('✅ 生成的位置名称:', locationName);
          emit('update:locationName', locationName);
        } else {
          console.error('❌ 逆地理编码失败');
          console.error('错误状态:', status);
          console.error('错误信息:', result?.message || result?.info || '未知错误');
        }
      });
    } else {
      // Geocoder 可能还未初始化完成，等待后重试
      if (retryCount < 5) {
        console.log(`⏳ Geocoder 未初始化，等待后重试 (${retryCount + 1}/5)...`);
        setTimeout(() => tryGeocoding(retryCount + 1), 200);
      } else {
        console.error('❌ Geocoder 初始化超时，请检查网络或 API Key');
      }
    }
  };

  tryGeocoding();
}

// 加载社区网格和零贷网格
async function loadOtherGrids() {
  const AMap = (window as any).AMap;
  if (!AMap || !map) {
    console.error('地图未初始化');
    return;
  }

  try {
    // 清除旧的多边形
    if (otherGridPolygons.length > 0) {
      map.remove(otherGridPolygons);
      otherGridPolygons = [];
    }

    // 获取社区网格数据（支持分页加载所有数据）
    let communityPage = 1;
    let communityTotal = 0;
    const allCommunityGrids: any[] = [];

    do {
      const communityGrids = await getCommunityGridPage({ pageNo: communityPage, pageSize: 100 });
      if (communityGrids.list && communityGrids.list.length > 0) {
        allCommunityGrids.push(...communityGrids.list);
      }
      communityTotal = communityGrids.total || 0;
      communityPage++;
    } while (allCommunityGrids.length < communityTotal);

    const communityWithBoundary = allCommunityGrids.filter(g => g.boundaryGeometry);
    console.log('📍 加载社区网格:', allCommunityGrids.length, '个，其中', communityWithBoundary.length, '个有边界数据');

    // 获取零贷网格数据（支持分页加载所有数据）
    let zerodaiPage = 1;
    let zerodaiTotal = 0;
    const allZerodaiGrids: any[] = [];

    do {
      const zerodaiGrids = await getZerodaiGridPage({ pageNo: zerodaiPage, pageSize: 100 });
      if (zerodaiGrids.list && zerodaiGrids.list.length > 0) {
        allZerodaiGrids.push(...zerodaiGrids.list);
      }
      zerodaiTotal = zerodaiGrids.total || 0;
      zerodaiPage++;
    } while (allZerodaiGrids.length < zerodaiTotal);

    const zerodaiWithBoundary = allZerodaiGrids.filter(g => g.boundaryGeometry);
    console.log('📍 加载零贷网格:', allZerodaiGrids.length, '个，其中', zerodaiWithBoundary.length, '个有边界数据');

    // 绘制社区网格（洋红色）
    for (const grid of communityWithBoundary) {
      drawPolygonFromGeoJSON(grid.boundaryGeometry, grid.gridName || '社区网格');
    }

    // 绘制零贷网格（洋红色）
    for (const grid of zerodaiWithBoundary) {
      drawPolygonFromGeoJSON(grid.boundaryGeometry, grid.gridName || '零贷网格');
    }

    // 提示信息
    if (communityWithBoundary.length === 0 && zerodaiWithBoundary.length === 0) {
      console.warn('⚠️ 所有社区网格和零贷网格都没有边界数据，请先在对应管理页面为网格绘制边界');
    }
  } catch (error) {
    console.error('加载其他网格失败:', error);
  }
}

// 从 GeoJSON 字符串绘制多边形
function drawPolygonFromGeoJSON(geoJsonString: string, title: string) {
  const AMap = (window as any).AMap;
  if (!AMap || !map) return;

  try {
    const geoJson = JSON.parse(geoJsonString);

    // GeoJSON Polygon 格式: { type: "Polygon", coordinates: [[[lng, lat], ...]] }
    if (geoJson.type === 'Polygon' && geoJson.coordinates && geoJson.coordinates[0]) {
      const path = geoJson.coordinates[0].map((coord: number[]) => [coord[0], coord[1]]);

      const polygon = new AMap.Polygon({
        path: path,
        strokeColor: '#FF33FF',  // 洋红色边框
        strokeWeight: 2,
        fillColor: '#FF33FF',    // 洋红色填充
        fillOpacity: 0.15,
        bubble: true,
        clickable: false,
      });

      map.add(polygon);
      otherGridPolygons.push(polygon);
    }
  } catch (error) {
    console.error('绘制多边形失败:', error);
  }
}
</script>

<template>
  <div ref="mapContainer" class="h-full w-full"></div>
</template>
