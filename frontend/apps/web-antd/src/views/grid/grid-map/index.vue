<template>
  <div class="grid-map-page h-full w-full flex">
    <!-- 左侧地图区域 (75%) -->
    <div class="map-container flex-[3]">
      <GridMapViewer
        :grids="mapData.grids"
        :customers="mapData.customers"
        :communities="mapData.communities"
        :competitors="mapData.competitors"
        :loading="loading"
      />
    </div>

    <!-- 右侧筛选面板 (25%) -->
    <div class="filter-panel flex-1 bg-white p-4 border-l overflow-y-auto">
      <MapFilterPanel
        v-model:gridTypes="filterParams.gridTypes"
        v-model:customerTypes="filterParams.customerTypes"
        v-model:includeCommunity="filterParams.includeCommunity"
        v-model:includeCompetitor="filterParams.includeCompetitor"
        @change="loadMapData"
      />
    </div>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue';
import { message } from 'ant-design-vue';
import GridMapViewer from './modules/grid-map-viewer.vue';
import MapFilterPanel from './modules/map-filter-panel.vue';
import { getMapDataApi } from '#/api/grid/grid-map';
import type { GridMapApi } from '#/api/grid/grid-map';

defineOptions({
  name: 'GridMap',
});

// 筛选参数
const filterParams = reactive<GridMapApi.MapDataRequest>({
  gridTypes: ['HUINONG', 'ZERODAI', 'COMMUNITY', 'LOBBY'],
  customerTypes: ['HUINONG_LOAN', 'ZERODAI', 'COMMUNITY', 'TINGTANG'],
  includeCommunity: true,
  includeCompetitor: true,
});

// 地图数据
const mapData = reactive<GridMapApi.MapDataResponse>({
  grids: [],
  customers: [],
  communities: [],
  competitors: [],
});

// 加载状态
const loading = ref(false);

/**
 * 加载地图数据
 */
async function loadMapData() {
  try {
    loading.value = true;
    const response = await getMapDataApi(filterParams);

    // 更新地图数据
    Object.assign(mapData, response);

    message.success('地图数据加载成功');
  } catch (error) {
    console.error('加载地图数据失败:', error);
    message.error('加载地图数据失败');
  } finally {
    loading.value = false;
  }
}

// 组件挂载后加载数据
onMounted(() => {
  loadMapData();
});
</script>

<style scoped>
.grid-map-page {
  height: calc(100vh - 120px);
}

.map-container {
  position: relative;
}

.filter-panel {
  min-width: 300px;
  max-width: 400px;
}
</style>
