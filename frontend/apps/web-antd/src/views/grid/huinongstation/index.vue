<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { GridHuinongStationApi } from '#/api/grid/huinongstation';

import { ref } from 'vue';

import { confirm, Page, useVbenModal } from '@vben/common-ui';
import { downloadFileFromBlobPart, isEmpty } from '@vben/utils';

import { message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteHuinongStation,
  deleteHuinongStationList,
  downloadHuinongStationTemplate,
  exportHuinongStation,
  getHuinongStationPage,
  importHuinongStation,
  updateHuinongStation,
} from '#/api/grid/huinongstation';
import { $t } from '#/locales';

import { useGridColumns, useGridFormSchema } from './data';
import Form from './modules/form.vue';
import MapModal from './modules/map-modal.vue';

const [FormModal, formModalApi] = useVbenModal({
  connectedComponent: Form,
  destroyOnClose: true,
});

const [StationMapModal, mapModalApi] = useVbenModal({
  connectedComponent: MapModal,
  destroyOnClose: true,
  modalProps: {
    title: '站点地图',
    width: '80%',
  },
});

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 创建惠农站点信息 */
function handleCreate() {
  formModalApi.setData(null).open();
}

/** 编辑惠农站点信息 */
function handleEdit(row: GridHuinongStationApi.HuinongStation) {
  formModalApi.setData(row).open();
}

/** 删除惠农站点信息 */
async function handleDelete(row: GridHuinongStationApi.HuinongStation) {
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deleting', [row.id]),
    duration: 0,
  });
  try {
    await deleteHuinongStation(row.id!);
    message.success($t('ui.actionMessage.deleteSuccess', [row.id]));
    handleRefresh();
  } finally {
    hideLoading();
  }
}

/** 批量删除惠农站点信息 */
async function handleDeleteBatch() {
  await confirm($t('ui.actionMessage.deleteBatchConfirm'));
  const hideLoading = message.loading({
    content: $t('ui.actionMessage.deletingBatch'),
    duration: 0,
  });
  try {
    await deleteHuinongStationList(checkedIds.value);
    checkedIds.value = [];
    message.success($t('ui.actionMessage.deleteSuccess'));
    handleRefresh();
  } finally {
    hideLoading();
  }
}

const checkedIds = ref<number[]>([]);
function handleRowCheckboxChange({
  records,
}: {
  records: GridHuinongStationApi.HuinongStation[];
}) {
  checkedIds.value = records.map((item) => item.id!);
}

/** 导出表格 */
async function handleExport() {
  const data = await exportHuinongStation(await gridApi.formApi.getValues());
  downloadFileFromBlobPart({ fileName: '惠农站点信息.xls', source: data });
}

/** 下载导入模板 */
async function handleDownloadTemplate() {
  const hideLoading = message.loading({
    content: '正在下载导入模板...',
    duration: 0,
  });
  try {
    const data = await downloadHuinongStationTemplate();
    downloadFileFromBlobPart({
      fileName: '惠农站点信息导入模板.xls',
      source: data,
    });
    message.success('导入模板下载成功');
  } catch (error) {
    message.error('导入模板下载失败');
  } finally {
    hideLoading();
  }
}

/** 导入站点信息 */
async function handleImport() {
  // 创建一个隐藏的文件输入元素
  const input = document.createElement('input');
  input.type = 'file';
  input.accept = '.xls,.xlsx';
  input.onchange = async (e: Event) => {
    const target = e.target as HTMLInputElement;
    const file = target.files?.[0];
    if (!file) return;

    // 询问是否支持更新
    const updateSupport = await new Promise<boolean>((resolve) => {
      Modal.confirm({
        title: '导入确认',
        content: '是否支持更新已存在的站点？\n\n• 选择"支持更新"：已存在的站点将被更新\n• 选择"仅创建新站点"：已存在的站点将跳过导入',
        okText: '支持更新',
        cancelText: '仅创建新站点',
        onOk: () => resolve(true),
        onCancel: () => resolve(false),
      });
    });

    const hideLoading = message.loading({
      content: '正在导入站点信息...',
      duration: 0,
    });
    try {
      const result = await importHuinongStation(file, updateSupport);

      // 显示导入结果
      let resultMessage = `导入完成！\n`;
      if (result.createStationCodes && result.createStationCodes.length > 0) {
        resultMessage += `创建成功: ${result.createStationCodes.length} 个站点\n`;
      }
      if (result.updateStationCodes && result.updateStationCodes.length > 0) {
        resultMessage += `更新成功: ${result.updateStationCodes.length} 个站点\n`;
      }
      if (result.createdGridCount > 0) {
        resultMessage += `自动创建网格: ${result.createdGridCount} 个\n`;
      }
      if (result.failureStationCodes && Object.keys(result.failureStationCodes).length > 0) {
        resultMessage += `失败: ${Object.keys(result.failureStationCodes).length} 个站点\n\n失败原因:\n`;
        for (const [code, reason] of Object.entries(result.failureStationCodes)) {
          resultMessage += `${code}: ${reason}\n`;
        }
      }

      message.success({
        content: resultMessage,
        duration: 5,
      });
      handleRefresh();
    } catch (error: any) {
      message.error(`导入失败: ${error.message || '未知错误'}`);
    } finally {
      hideLoading();
    }
  };
  input.click();
}

/** 地图显示 */
function handleShowMap(row: GridHuinongStationApi.HuinongStation) {
  // 打开地图弹窗
  mapModalApi
    .setData({
      stationId: row.id,
    })
    .open();
}

/** 处理网格营销标志变化 */
async function handleMarketingFlagChange(
  newFlag: string,
  row: GridHuinongStationApi.HuinongStation,
) {
  const hideLoading = message.loading({
    content: '正在更新网格营销标志...',
    duration: 0,
  });
  try {
    await updateHuinongStation({
      ...row,
      gridMarketingFlag: newFlag,
    });
    message.success('网格营销标志更新成功');
    return true;
  } catch (error) {
    message.error('网格营销标志更新失败');
    return false;
  } finally {
    hideLoading();
  }
}

const [Grid, gridApi] = useVbenVxeGrid({
  formOptions: {
    schema: useGridFormSchema(),
  },
  gridOptions: {
    columns: useGridColumns(handleMarketingFlagChange),
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }, formValues) => {
          return await getHuinongStationPage({
            pageNo: page.currentPage,
            pageSize: page.pageSize,
            ...formValues,
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
      search: true,
    },
  } as VxeTableGridOptions<GridHuinongStationApi.HuinongStation>,
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});
</script>

<template>
  <Page auto-content-height>
    <FormModal @success="handleRefresh" />
    <StationMapModal />
    <Grid table-title="惠农站点信息列表">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '下载导入模板',
              type: 'default',
              icon: ACTION_ICON.DOWNLOAD,
              auth: ['grid:huinong-station:import'],
              onClick: handleDownloadTemplate,
            },
            {
              label: '导入站点',
              type: 'default',
              icon: ACTION_ICON.UPLOAD,
              auth: ['grid:huinong-station:import'],
              onClick: handleImport,
            },
            {
              label: $t('ui.actionTitle.deleteBatch'),
              type: 'primary',
              danger: true,
              icon: ACTION_ICON.DELETE,
              auth: ['grid:huinong-station:delete'],
              disabled: isEmpty(checkedIds),
              onClick: handleDeleteBatch,
            },
          ]"
        />
      </template>
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '地图显示',
              type: 'link',
              icon: ACTION_ICON.VIEW,
              auth: ['grid:huinong-station:query'],
              onClick: handleShowMap.bind(null, row),
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>