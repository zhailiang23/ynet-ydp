<script lang="ts" setup>
import type { VxeGridInstance, VxeGridProps } from '#/adapter/vxe-table';
import type { AicrmPracticeScriptApi } from '#/api/aicrm/practicescript';

import { ref } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { VxeGrid } from 'vxe-table';

import { getVersionHistory } from '#/api/aicrm/practicescript';

// 版本历史列表数据
const versionList = ref<AicrmPracticeScriptApi.PracticeScript[]>([]);
const loading = ref(false);
const gridRef = ref<VxeGridInstance>();

/** 加载版本历史 */
async function loadVersionHistory(scriptNo: string) {
  try {
    loading.value = true;
    versionList.value = await getVersionHistory(scriptNo);
  } finally {
    loading.value = false;
  }
}

// Grid 列配置
const columns: VxeGridProps['columns'] = [
  {
    field: 'version',
    title: '版本号',
    width: 100,
  },
  {
    field: 'isLatest',
    title: '最新版本',
    width: 100,
    slots: { default: 'isLatest' },
  },
  {
    field: 'createTime',
    title: '创建时间',
    width: 180,
    formatter: 'formatDateTime',
  },
  {
    field: 'updateTime',
    title: '更新时间',
    width: 180,
    formatter: 'formatDateTime',
  },
  {
    field: 'creator',
    title: '创建人',
    width: 120,
  },
];

const [Modal, modalApi] = useVbenModal({
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      versionList.value = [];
      return;
    }

    // 打开时加载版本历史
    const data = modalApi.getData<{ scriptNo: string }>();
    if (data?.scriptNo) {
      await loadVersionHistory(data.scriptNo);
    }
  },
});
</script>

<template>
  <Modal title="版本历史" width="900px" :footer="false">
    <VxeGrid ref="gridRef" :data="versionList" :columns="columns" :loading="loading" height="auto">
      <template #isLatest="{ row }">
        <span v-if="row.isLatest" class="text-green-600">✓ 是</span>
        <span v-else class="text-gray-400">否</span>
      </template>
    </VxeGrid>
  </Modal>
</template>
