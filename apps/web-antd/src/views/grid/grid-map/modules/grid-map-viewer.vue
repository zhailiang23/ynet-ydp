<script lang="ts" setup>
import { onBeforeUnmount, ref, watch } from 'vue';
import { message, Spin } from 'ant-design-vue';
import AMapLoader from '@amap/amap-jsapi-loader';
import type { GridMapApi } from '#/api/grid/grid-map';

const props = defineProps<{
  grids: GridMapApi.GridData[];
  customers: GridMapApi.CustomerData[];
  communities: GridMapApi.CommunityData[];
  competitors: GridMapApi.CompetitorData[];
  loading?: boolean;
}>();

let map: any = null;
const gridPolygons: any[] = [];
const customerMarkers: any[] = [];
const communityMarkers: any[] = [];
const competitorMarkers: any[] = [];

// 颜色配置
const GRID_COLORS = {
  HUINONG: {
    stroke: '#1890ff',
    fill: '#1890ff',
    fillOpacity: 0.15,
    name: '惠农网格',
  },
  ZERODAI: {
    stroke: '#52c41a',
    fill: '#52c41a',
    fillOpacity: 0.15,
    name: '零贷网格',
  },
  COMMUNITY: {
    stroke: '#fa8c16',
    fill: '#fa8c16',
    fillOpacity: 0.15,
    name: '社区网格',
  },
  LOBBY: {
    stroke: '#722ed1',
    fill: '#722ed1',
    fillOpacity: 0.15,
    name: '厅堂网格',
  },
};

const CUSTOMER_COLORS = {
  HUINONG_LOAN: {
    color: '#1890ff',
    name: '惠农贷款客户',
  },
  ZERODAI: {
    color: '#52c41a',
    name: '零贷客户',
  },
  COMMUNITY: {
    color: '#fa8c16',
    name: '社区客户',
  },
  TINGTANG: {
    color: '#722ed1',
    name: '厅堂客户',
  },
};

const COMMUNITY_COLOR = {
  color: '#eb2f96',
  name: '社区信息',
};

const COMPETITOR_COLOR = {
  color: '#f5222d',
  name: '同业信息',
};

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
      plugins: ['AMap.Marker', 'AMap.Polygon', 'AMap.InfoWindow'],
    });

    // 创建地图实例(默认中心为郑州)
    map = new AMap.Map('comprehensive-map-container', {
      zoom: 12,
      center: [113.6254, 34.7466],
      viewMode: '2D',
    });

    // 添加图例
    addLegend();
  } catch (error) {
    console.error('地图初始化失败:', error);
    message.error('地图加载失败');
  }
}

/**
 * 绘制网格区域
 */
function drawGrids() {
  if (!map || !props.grids) {
    return;
  }

  const AMap = (window as any).AMap;

  // 清除旧的多边形
  gridPolygons.forEach((polygon) => map.remove(polygon));
  gridPolygons.length = 0;

  // 绘制网格边界
  for (const grid of props.grids) {
    if (!grid.boundaryGeometry) {
      continue;
    }

    try {
      const geoJSON = JSON.parse(grid.boundaryGeometry);
      let coordinates: any[] = [];

      if (geoJSON.type === 'Polygon') {
        coordinates = geoJSON.coordinates[0];
      } else if (geoJSON.type === 'Feature' && geoJSON.geometry?.type === 'Polygon') {
        coordinates = geoJSON.geometry.coordinates[0];
      }

      if (coordinates && coordinates.length > 0) {
        const color = GRID_COLORS[grid.gridType as keyof typeof GRID_COLORS];

        const polygon = new AMap.Polygon({
          path: coordinates.map((coord: any) => [coord[0], coord[1]]),
          strokeColor: color?.stroke || '#1890ff',
          strokeWeight: 2,
          strokeOpacity: 0.8,
          fillColor: color?.fill || '#1890ff',
          fillOpacity: color?.fillOpacity || 0.15,
          zIndex: 50,
        });

        // 添加点击事件
        polygon.on('click', () => {
          showGridInfo(grid);
        });

        map.add(polygon);
        gridPolygons.push(polygon);
      }
    } catch (error) {
      console.error('绘制网格失败:', error);
    }
  }
}

/**
 * 绘制客户标点
 */
function drawCustomers() {
  if (!map || !props.customers) {
    return;
  }

  const AMap = (window as any).AMap;

  // 清除旧的标记
  customerMarkers.forEach((marker) => map.remove(marker));
  customerMarkers.length = 0;

  // 绘制客户标点
  for (const customer of props.customers) {
    if (!customer.longitude || !customer.latitude) {
      continue;
    }

    const color = CUSTOMER_COLORS[customer.customerType as keyof typeof CUSTOMER_COLORS];

    const marker = new AMap.Marker({
      position: [customer.longitude, customer.latitude],
      icon: createMarkerIcon(color?.color || '#1890ff'),
      title: customer.customerName,
      zIndex: 100,
    });

    // 添加点击事件
    marker.on('click', () => {
      showCustomerInfo(customer);
    });

    map.add(marker);
    customerMarkers.push(marker);
  }
}

/**
 * 绘制社区标点
 */
function drawCommunities() {
  if (!map || !props.communities) {
    return;
  }

  const AMap = (window as any).AMap;

  // 清除旧的标记
  communityMarkers.forEach((marker) => map.remove(marker));
  communityMarkers.length = 0;

  // 绘制社区标点
  for (const community of props.communities) {
    if (!community.longitude || !community.latitude) {
      continue;
    }

    const marker = new AMap.Marker({
      position: [community.longitude, community.latitude],
      icon: createMarkerIcon(COMMUNITY_COLOR.color, 'square'),
      title: community.communityName,
      zIndex: 100,
    });

    // 添加点击事件
    marker.on('click', () => {
      showCommunityInfo(community);
    });

    map.add(marker);
    communityMarkers.push(marker);
  }
}

/**
 * 绘制同业标点
 */
function drawCompetitors() {
  if (!map || !props.competitors) {
    return;
  }

  const AMap = (window as any).AMap;

  // 清除旧的标记
  competitorMarkers.forEach((marker) => map.remove(marker));
  competitorMarkers.length = 0;

  // 绘制同业标点
  for (const competitor of props.competitors) {
    if (!competitor.longitude || !competitor.latitude) {
      continue;
    }

    const marker = new AMap.Marker({
      position: [competitor.longitude, competitor.latitude],
      icon: createMarkerIcon(COMPETITOR_COLOR.color, 'triangle'),
      title: competitor.competitorName,
      zIndex: 100,
    });

    // 添加点击事件
    marker.on('click', () => {
      showCompetitorInfo(competitor);
    });

    map.add(marker);
    competitorMarkers.push(marker);
  }
}

/**
 * 创建标记图标
 */
function createMarkerIcon(color: string, shape: 'circle' | 'square' | 'triangle' = 'circle') {
  const size = 20;
  let path = '';

  if (shape === 'circle') {
    path = `M ${size / 2} ${size / 2} m -${size / 2}, 0 a ${size / 2},${size / 2} 0 1,0 ${size},0 a ${size / 2},${size / 2} 0 1,0 -${size},0`;
  } else if (shape === 'square') {
    path = `M 0 0 L ${size} 0 L ${size} ${size} L 0 ${size} Z`;
  } else if (shape === 'triangle') {
    path = `M ${size / 2} 0 L ${size} ${size} L 0 ${size} Z`;
  }

  const svg = `
    <svg xmlns="http://www.w3.org/2000/svg" width="${size}" height="${size}">
      <path d="${path}" fill="${color}" stroke="#fff" stroke-width="2"/>
    </svg>
  `;

  return `data:image/svg+xml;base64,${btoa(svg)}`;
}

/**
 * 显示网格信息
 */
function showGridInfo(grid: GridMapApi.GridData) {
  const AMap = (window as any).AMap;
  const infoWindow = new AMap.InfoWindow({
    content: `
      <div style="padding: 10px;">
        <h4 style="margin: 0 0 8px 0;">${grid.gridName}</h4>
        <p style="margin: 4px 0;">网格类型: ${GRID_COLORS[grid.gridType as keyof typeof GRID_COLORS]?.name || grid.gridType}</p>
      </div>
    `,
    anchor: 'bottom-center',
    offset: [0, -10],
  });

  infoWindow.open(map, [grid.longitude, grid.latitude]);
}

/**
 * 显示客户信息
 */
function showCustomerInfo(customer: GridMapApi.CustomerData) {
  const AMap = (window as any).AMap;
  const infoWindow = new AMap.InfoWindow({
    content: `
      <div style="padding: 10px;">
        <h4 style="margin: 0 0 8px 0;">${customer.customerName}</h4>
        <p style="margin: 4px 0;">类型: ${CUSTOMER_COLORS[customer.customerType as keyof typeof CUSTOMER_COLORS]?.name || customer.customerType}</p>
        <p style="margin: 4px 0;">地址: ${customer.address || '未知'}</p>
      </div>
    `,
    anchor: 'bottom-center',
    offset: [0, -10],
  });

  infoWindow.open(map, [customer.longitude, customer.latitude]);
}

/**
 * 显示社区信息
 */
function showCommunityInfo(community: GridMapApi.CommunityData) {
  const AMap = (window as any).AMap;
  const infoWindow = new AMap.InfoWindow({
    content: `
      <div style="padding: 10px;">
        <h4 style="margin: 0 0 8px 0;">${community.communityName}</h4>
        <p style="margin: 4px 0;">类型: ${COMMUNITY_COLOR.name}</p>
        <p style="margin: 4px 0;">地址: ${community.address || '未知'}</p>
      </div>
    `,
    anchor: 'bottom-center',
    offset: [0, -10],
  });

  infoWindow.open(map, [community.longitude, community.latitude]);
}

/**
 * 显示同业信息
 */
function showCompetitorInfo(competitor: GridMapApi.CompetitorData) {
  const AMap = (window as any).AMap;
  const infoWindow = new AMap.InfoWindow({
    content: `
      <div style="padding: 10px;">
        <h4 style="margin: 0 0 8px 0;">${competitor.competitorName}</h4>
        <p style="margin: 4px 0;">类型: ${COMPETITOR_COLOR.name}</p>
        <p style="margin: 4px 0;">地址: ${competitor.address || '未知'}</p>
      </div>
    `,
    anchor: 'bottom-center',
    offset: [0, -10],
  });

  infoWindow.open(map, [competitor.longitude, competitor.latitude]);
}

/**
 * 添加图例
 */
function addLegend() {
  const legend = document.createElement('div');
  legend.style.cssText = `
    position: absolute;
    top: 10px;
    right: 10px;
    background: white;
    padding: 12px;
    border-radius: 4px;
    box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
    z-index: 999;
    max-width: 200px;
  `;

  legend.innerHTML = `
    <h4 style="margin: 0 0 10px 0; font-size: 14px; font-weight: 600;">图例</h4>
    <div style="margin-bottom: 8px; font-weight: 500;">网格类型:</div>
    ${Object.entries(GRID_COLORS)
      .map(
        ([key, value]) => `
      <div style="display: flex; align-items: center; margin: 4px 0;">
        <div style="width: 20px; height: 12px; background: ${value.fill}; border: 1px solid ${value.stroke}; margin-right: 8px;"></div>
        <span style="font-size: 12px;">${value.name}</span>
      </div>
    `,
      )
      .join('')}
    <div style="margin: 12px 0 8px 0; font-weight: 500;">客户类型:</div>
    ${Object.entries(CUSTOMER_COLORS)
      .map(
        ([key, value]) => `
      <div style="display: flex; align-items: center; margin: 4px 0;">
        <div style="width: 12px; height: 12px; background: ${value.color}; border-radius: 50%; border: 1px solid #fff; margin-right: 8px;"></div>
        <span style="font-size: 12px;">${value.name}</span>
      </div>
    `,
      )
      .join('')}
    <div style="margin: 12px 0 8px 0; font-weight: 500;">其他:</div>
    <div style="display: flex; align-items: center; margin: 4px 0;">
      <div style="width: 12px; height: 12px; background: ${COMMUNITY_COLOR.color}; border: 1px solid #fff; margin-right: 8px;"></div>
      <span style="font-size: 12px;">${COMMUNITY_COLOR.name}</span>
    </div>
    <div style="display: flex; align-items: center; margin: 4px 0;">
      <div style="width: 0; height: 0; border-left: 6px solid transparent; border-right: 6px solid transparent; border-bottom: 12px solid ${COMPETITOR_COLOR.color}; margin-right: 8px;"></div>
      <span style="font-size: 12px;">${COMPETITOR_COLOR.name}</span>
    </div>
  `;

  document.getElementById('comprehensive-map-container')?.appendChild(legend);
}

// 监听数据变化
watch(
  () => props.grids,
  () => {
    drawGrids();
  },
  { deep: true },
);

watch(
  () => props.customers,
  () => {
    drawCustomers();
  },
  { deep: true },
);

watch(
  () => props.communities,
  () => {
    drawCommunities();
  },
  { deep: true },
);

watch(
  () => props.competitors,
  () => {
    drawCompetitors();
  },
  { deep: true },
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
</script>

<template>
  <div class="grid-map-viewer h-full w-full relative">
    <Spin :spinning="loading" tip="加载中...">
      <div id="comprehensive-map-container" class="h-full w-full"></div>
    </Spin>
  </div>
</template>

<style scoped>
.grid-map-viewer {
  min-height: 600px;
}

/* 确保 Spin 组件及其容器继承完整高度 */
.grid-map-viewer :deep(.ant-spin-nested-loading),
.grid-map-viewer :deep(.ant-spin-container) {
  height: 100%;
}

#comprehensive-map-container {
  height: 100%;
  width: 100%;
}
</style>
