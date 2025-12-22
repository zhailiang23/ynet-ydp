<script lang="ts" setup>
import { onMounted, ref, watch } from 'vue';
import AMapLoader from '@amap/amap-jsapi-loader';

// 定义 props 和 emits
const props = defineProps<{
  modelValue?: {
    longitude: number | null;
    latitude: number | null;
    address?: string | null;
  };
}>();

const emit = defineEmits<{
  'update:modelValue': [
    value: {
      longitude: number | null;
      latitude: number | null;
      address?: string | null;
    },
  ];
}>();

// 地图容器
const mapContainer = ref<HTMLDivElement>();
let map: any = null;
let marker: any = null;
let geocoder: any = null;

// 初始化地图
onMounted(async () => {
  try {
    // 设置安全密钥
    (window as any)._AMapSecurityConfig = {
      securityJsCode: '6b249e3430044ff13e483020a9c7efb9',
    };

    const AMap = await AMapLoader.load({
      key: '36b4c632e48e5d3a7112513fef24bb72',
      version: '2.0',
      plugins: ['AMap.Geocoder'],
    });

    // 创建地图实例
    map = new AMap.Map(mapContainer.value, {
      zoom: 12,
      center: props.modelValue?.longitude && props.modelValue?.latitude 
        ? [props.modelValue.longitude, props.modelValue.latitude]
        : [113.625368, 34.746611], // 郑州市中心
    });

    // 创建地理编码实例
    geocoder = new AMap.Geocoder();

    // 如果有初始坐标，添加标记
    if (props.modelValue?.longitude && props.modelValue?.latitude) {
      addMarker([props.modelValue.longitude, props.modelValue.latitude]);
    }

    // 监听地图点击事件
    map.on('click', async (e: any) => {
      const { lng, lat } = e.lnglat;
      addMarker([lng, lat]);
      
      // 获取地址信息
      const address = await getAddress([lng, lat]);
      
      emit('update:modelValue', {
        longitude: lng,
        latitude: lat,
        address: address,
      });
    });
  } catch (error) {
    console.error('地图加载失败:', error);
  }
});

// 添加标记
function addMarker(position: [number, number]) {
  // 移除旧标记
  if (marker) {
    map.remove(marker);
  }

  const AMap = (window as any).AMap;
  
  // 创建新标记
  marker = new AMap.Marker({
    position: position,
    title: '客户位置',
  });

  map.add(marker);
  map.setCenter(position);
}

// 获取地址信息
async function getAddress(position: [number, number]): Promise<string> {
  return new Promise((resolve) => {
    geocoder.getAddress(position, (status: string, result: any) => {
      if (status === 'complete' && result.info === 'OK') {
        resolve(result.regeocode.formattedAddress);
      } else {
        resolve('');
      }
    });
  });
}

// 监听外部坐标变化
watch(
  () => props.modelValue,
  (newVal) => {
    if (newVal?.longitude && newVal?.latitude && map) {
      addMarker([newVal.longitude, newVal.latitude]);
    }
  },
  { deep: true }
);
</script>

<template>
  <div class="customer-map-point">
    <div ref="mapContainer" class="map-container"></div>
    <div class="map-tip">
      点击地图选择客户位置
    </div>
  </div>
</template>

<style scoped>
.customer-map-point {
  position: relative;
  width: 100%;
  height: 100%;
}

.map-container {
  width: 100%;
  height: 100%;
}

.map-tip {
  position: absolute;
  top: 10px;
  left: 50%;
  transform: translateX(-50%);
  background: rgba(0, 0, 0, 0.7);
  color: white;
  padding: 8px 16px;
  border-radius: 4px;
  font-size: 14px;
  z-index: 999;
}
</style>
