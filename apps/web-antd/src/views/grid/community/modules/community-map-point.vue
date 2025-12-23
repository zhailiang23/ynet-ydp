<script lang="ts" setup>
import { ref, watch, onBeforeUnmount } from 'vue';
import { message } from 'ant-design-vue';
import AMapLoader from '@amap/amap-jsapi-loader';

interface Coordinates {
  longitude: number | null;
  latitude: number | null;
}

const props = defineProps<{
  modelValue?: Coordinates;
}>();

const emit = defineEmits<{
  (e: 'update:modelValue', value: Coordinates): void;
}>();

let map: any = null;
let currentMarker: any = null;

// 初始化高德地图
async function initMap() {
  try {
    // 设置安全密钥
    (window as any)._AMapSecurityConfig = {
      securityJsCode: '6b249e3430044ff13e483020a9c7efb9',
    };

    // 加载高德地图
    const AMap = await AMapLoader.load({
      key: import.meta.env.VITE_AMAP_KEY,
      version: '2.0',
      plugins: ['AMap.Marker', 'AMap.Geocoder'],
    });

    // 创建地图实例(默认中心为郑州)
    map = new AMap.Map('community-map-container', {
      zoom: 13,
      center: [113.6254, 34.7466],
      viewMode: '2D',
    });

    // 监听地图点击事件
    map.on('click', (e: any) => {
      const lng = e.lnglat.getLng();
      const lat = e.lnglat.getLat();

      // 移除旧标记
      if (currentMarker) {
        map.remove(currentMarker);
      }

      // 创建新标记
      currentMarker = new AMap.Marker({
        position: [lng, lat],
        map: map,
        title: '社区位置',
      });

      // 更新坐标值
      emit('update:modelValue', {
        longitude: Number(lng.toFixed(6)),
        latitude: Number(lat.toFixed(6)),
      });

      message.success('已选择社区位置');
    });

    // 如果有初始值，添加标记
    if (props.modelValue?.longitude && props.modelValue?.latitude) {
      addMarker(props.modelValue.longitude, props.modelValue.latitude);
    }
  } catch (error) {
    console.error('地图初始化失败:', error);
    message.error('地图加载失败');
  }
}

// 添加标记
function addMarker(lng: number, lat: number) {
  if (!map) {
    return;
  }

  const AMap = (window as any).AMap;

  // 移除旧标记
  if (currentMarker) {
    map.remove(currentMarker);
    currentMarker = null;
  }

  // 创建标记
  currentMarker = new AMap.Marker({
    position: [lng, lat],
    map: map,
    title: '社区位置',
  });

  // 调整视野到标记
  map.setZoomAndCenter(15, [lng, lat]);
}

// 清除标记
function clearMarker() {
  if (!map) {
    return;
  }

  // 移除标记
  if (currentMarker) {
    map.remove(currentMarker);
    currentMarker = null;
  }

  emit('update:modelValue', { longitude: null, latitude: null });
  message.success('已清除社区位置');
}

// 监听外部值变化
watch(
  () => props.modelValue,
  (newValue) => {
    if (newValue?.longitude && newValue?.latitude && map) {
      addMarker(newValue.longitude, newValue.latitude);
    }
  },
  { deep: true }
);

// 组件卸载时清理
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

// 暴露方法给父组件
defineExpose({
  clearMarker,
});
</script>

<template>
  <div class="community-map-point">
    <div class="mb-2 flex items-center justify-between">
      <div class="text-sm text-gray-600">
        点击地图上的任意位置即可选择社区中心点
      </div>
      <div class="flex gap-2">
        <a-button size="small" @click="clearMarker">
          清除
        </a-button>
      </div>
    </div>
    <div id="community-map-container" class="h-full w-full rounded-lg border"></div>
  </div>
</template>

<style scoped>
.community-map-point {
  height: 100%;
  display: flex;
  flex-direction: column;
}

#community-map-container {
  flex: 1;
  min-height: 400px;
}
</style>
