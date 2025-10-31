<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerImportantEventApi } from '#/api/aicrm/customerimportantevent';

import { getDictLabel } from '@vben/hooks';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { getCustomerImportantEventPage } from '#/api/aicrm/customerimportantevent';

defineOptions({
  name: 'RetailCustomerTimelineInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// 格式化布尔值
function formatBoolean(value?: boolean) {
  if (value === null || value === undefined) return '-';
  return value ? '是' : '否';
}

// 获取字典标签
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  const label = getDictLabel(dictType, value);
  return label || value;
}

// 客户大事记列表 VxeTable 配置
const [TimelineGrid, timelineGridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: [
      {
        type: 'seq',
        title: '序号',
        width: 70,
        fixed: 'left',
      },
      {
        field: 'eventName',
        title: '事件名称',
        minWidth: 150,
      },
      {
        field: 'eventType',
        title: '事件类型',
        minWidth: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_event_type' },
      },
      },
      {
        field: 'eventStatus',
        title: '事件状态',
        minWidth: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_event_status' },
      },
      },
      {
        field: 'eventLevel',
        title: '事件级别',
        minWidth: 100,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_event_level' },
      },
      },
      {
        field: 'eventDate',
        title: '事件日期',
        minWidth: 120,
        fixed: 'left',
      },
      {
        field: 'eventSource',
        title: '事件来源',
        minWidth: 120,
        cellRender: {
        name: 'CellDict',
        props: { type: 'aicrm_event_source' },
      },
      },
      {
        field: 'maintainerName',
        title: '维护人',
        minWidth: 100,
      },
      {
        field: 'maintainTime',
        title: '最近维护日期',
        minWidth: 170,
      },
      {
        field: 'remindFlag',
        title: '是否提醒',
        minWidth: 100,
        formatter: ({ cellValue }) => formatBoolean(cellValue),
      },
      {
        field: 'remindTime',
        title: '提醒时间',
        minWidth: 170,
        formatter: ({ cellValue }) => cellValue || '-',
      },
      {
        field: 'eventContent',
        title: '事件内容',
        minWidth: 250,
        showOverflow: 'tooltip',
      },
      {
        field: 'remark',
        title: '备注',
        minWidth: 200,
        showOverflow: 'tooltip',
      },
    ],
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }) => {
          return await getCustomerImportantEventPage({
            customerId: props.customerId,
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          });
        },
      },
    },
    rowConfig: {
      keyField: 'id',
      isHover: true,
    },
    toolbarConfig: {
      refresh: true,
    },
  } as VxeTableGridOptions<AicrmCustomerImportantEventApi.CustomerImportantEvent>,
});

// 暴露刷新方法
defineExpose({
  refresh: () => {
    timelineGridApi.query();
  },
});
</script>

<template>
  <div class="timeline-info-page">
    <TimelineGrid table-title="客户大事记信息" />
  </div>
</template>

<style scoped lang="less">
.timeline-info-page {
  height: 100%;
  display: flex;
  flex-direction: column;

  :deep(.vxe-grid) {
    height: 100%;
  }
}
</style>
