<script lang="ts" setup>
import { onBeforeUnmount, ref, watch } from 'vue';
import { message } from 'ant-design-vue';
import AMapLoader from '@amap/amap-jsapi-loader';

const props = defineProps<{
  modelValue?: string; // GeoJSON格式的网格边界
}>();

const emit = defineEmits<{
  (e: 'update:modelValue', value: string): void;
}>();

let map: any = null;
let mouseTool: any = null;
let currentPolygon: any = null;

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
      plugins: ['AMap.MouseTool', 'AMap.Polygon'],
    });

    // 创建地图实例(默认中心为郑州)
    map = new AMap.Map('grid-map-container', {
      zoom: 13,
      center: [113.6254, 34.7466],
      viewMode: '2D',
    });

    // 创建鼠标工具
    mouseTool = new AMap.MouseTool(map);

    // 监听绘制完成事件
    mouseTool.on('draw', (e: any) => {
      // 获取多边形路径
      const path = e.obj.getPath();

      // 保存当前多边形
      currentPolygon = e.obj;

      // 转换为GeoJSON格式
      const coordinates = path.map((point: any) => [point.lng, point.lat]);
      // 闭合多边形
      coordinates.push(coordinates[0]);

      const geoJSON = {
        type: 'Polygon',
        coordinates: [coordinates]
      };

      const geoJSONString = JSON.stringify(geoJSON);
      emit('update:modelValue', geoJSONString);

      // 绘制完成后立即关闭绘制工具，防止继续绘制
      mouseTool.close(true);

      message.success('网格边界绘制完成，如需重新绘制请先清除');
    });

    // 如果有初始值,绘制多边形
    if (props.modelValue) {
      drawPolygon(props.modelValue);
    }
  } catch (error) {
    console.error('地图初始化失败:', error);
    message.error('地图加载失败');
  }
}

// 绘制多边形
function drawPolygon(geoJSONString: string) {
  if (!map || !geoJSONString) {
    return;
  }

  const AMap = (window as any).AMap;

  // 移除旧的多边形
  if (currentPolygon) {
    map.remove(currentPolygon);
    currentPolygon = null;
  }

  try {
    // 解析 GeoJSON
    const geoJSON = JSON.parse(geoJSONString);

    let coordinates: any[] = [];

    if (geoJSON.type === 'Polygon') {
      coordinates = geoJSON.coordinates[0];
    } else if (geoJSON.type === 'Feature' && geoJSON.geometry?.type === 'Polygon') {
      coordinates = geoJSON.geometry.coordinates[0];
    }

    if (coordinates && coordinates.length > 0) {
      // 创建多边形
      currentPolygon = new AMap.Polygon({
        path: coordinates.map((coord: any) => [coord[0], coord[1]]),
        strokeColor: '#1890ff',
        strokeWeight: 2,
        strokeOpacity: 0.8,
        fillColor: '#1890ff',
        fillOpacity: 0.15,
        zIndex: 50,
      });

      map.add(currentPolygon);

      // 调整视野到多边形
      map.setFitView([currentPolygon]);
    }
  } catch (error) {
    console.error('绘制多边形失败:', error);
  }
}

// 开始绘制多边形
function startDraw() {
  if (!mouseTool) {
    return;
  }

  // 如果已经有多边形，提示用户先清除
  if (currentPolygon) {
    message.warning('已存在网格边界，请先清除后再绘制新的边界');
    return;
  }

  // 开始绘制多边形
  mouseTool.polygon({
    strokeColor: '#1890ff',
    strokeWeight: 2,
    fillColor: '#1890ff',
    fillOpacity: 0.15,
  });

  message.info('请在地图上点击多个点绘制网格边界，双击完成绘制');
}

// 清除绘制
function clearDraw() {
  if (!mouseTool) {
    return;
  }

  // 关闭绘制工具
  mouseTool.close(true);

  // 移除多边形
  if (currentPolygon) {
    map.remove(currentPolygon);
    currentPolygon = null;
  }

  emit('update:modelValue', '');
  message.success('已清除网格边界');
}

// 监听外部值变化
watch(() => props.modelValue, (newValue) => {
  if (newValue && map) {
    drawPolygon(newValue);
  }
});

// 组件卸载时清理
onBeforeUnmount(() => {
  if (mouseTool) {
    mouseTool.close(true);
  }
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
  startDraw,
  clearDraw,
});
</script>

<template>
  <div class="grid-map-draw">
    <div class="mb-2 flex items-center justify-between">
      <div class="text-sm text-gray-600">
        点击【开始绘制】后，在地图上点击多个点形成网格边界，双击完成绘制（只能绘制一个边界）
      </div>
      <div class="flex gap-2">
        <a-button size="small" type="primary" @click="startDraw">
          开始绘制
        </a-button>
        <a-button size="small" @click="clearDraw">
          清除
        </a-button>
      </div>
    </div>
    <div id="grid-map-container" class="h-full w-full rounded-lg border"></div>
  </div>
</template>

<style scoped>
.grid-map-draw {
  height: 100%;
  display: flex;
  flex-direction: column;
}

#grid-map-container {
  flex: 1;
  min-height: 400px;
}
</style>
