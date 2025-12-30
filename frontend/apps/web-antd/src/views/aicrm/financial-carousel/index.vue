<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { CrmFinancialCarouselApi } from '#/api/crm/product/financial/carousel';

import { ref } from 'vue';

import { Page, useVbenModal } from '@vben/common-ui';
import { isEmpty } from '@vben/utils';

import { Image, message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteCarousel,
  deleteCarouselList,
  getCarouselPage,
} from '#/api/crm/product/financial/carousel';

import CarouselModal from './carousel-modal.vue';
import { useGridColumns, useGridFormSchema } from './data';

const [CarouselFormModal, carouselFormModalApi] = useVbenModal({
  connectedComponent: CarouselModal,
  destroyOnClose: true,
});

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 新增轮播 */
function handleCreate() {
  carouselFormModalApi.open();
}

/** 编辑轮播 */
function handleEdit(row: CrmFinancialCarouselApi.FinancialCarousel) {
  carouselFormModalApi.setData(row).open();
}

/** 删除轮播 */
async function handleDelete(id: number) {
  Modal.confirm({
    title: '确认删除',
    content: '确定要删除该轮播吗？',
    okText: '确认',
    cancelText: '取消',
    async onOk() {
      try {
        await deleteCarousel(id);
        message.success('删除成功');
        handleRefresh();
      } catch (error) {
        message.error('删除失败');
      }
    },
  });
}

const checkedIds = ref<number[]>([]);

function handleRowCheckboxChange({
  records,
}: {
  records: CrmFinancialCarouselApi.FinancialCarousel[];
}) {
  checkedIds.value = records.map((item) => item.id!);
}

/** 批量删除 */
function handleDeleteBatch() {
  if (isEmpty(checkedIds.value)) {
    message.warning('请先选择要删除的轮播');
    return;
  }

  Modal.confirm({
    title: '确认删除',
    content: `确定要删除选中的 ${checkedIds.value.length} 个轮播吗？`,
    okText: '确认',
    cancelText: '取消',
    async onOk() {
      try {
        await deleteCarouselList(checkedIds.value);
        message.success('批量删除成功');
        handleRefresh();
      } catch (error) {
        message.error('批量删除失败');
      }
    },
  });
}

const [Grid, gridApi] = useVbenVxeGrid({
  formOptions: {
    schema: useGridFormSchema(),
  },
  gridOptions: {
    columns: useGridColumns(),
    height: 'auto',
    keepSource: true,
    proxyConfig: {
      ajax: {
        query: async ({ page }, formValues) => {
          return await getCarouselPage({
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
  } as VxeTableGridOptions<CrmFinancialCarouselApi.FinancialCarousel>,
  gridEvents: {
    checkboxAll: handleRowCheckboxChange,
    checkboxChange: handleRowCheckboxChange,
  },
});
</script>

<template>
  <Page auto-content-height>
    <CarouselFormModal @success="handleRefresh" />
    <Grid table-title="轮播管理">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '新增',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['crm:financial-carousel:create'],
              onClick: handleCreate,
            },
            {
              label: '批量删除',
              type: 'default',
              icon: ACTION_ICON.DELETE,
              auth: ['crm:financial-carousel:delete'],
              disabled: isEmpty(checkedIds),
              onClick: handleDeleteBatch,
            },
          ]"
        />
      </template>

      <!-- 轮播图片自定义渲染 -->
      <template #imageUrl="{ row }">
        <Image
          v-if="row.imageUrl"
          :src="row.imageUrl"
          :width="100"
          :height="60"
          style="object-fit: cover"
        />
        <span v-else>-</span>
      </template>

      <!-- 角标颜色自定义渲染 -->
      <template #badgeColor="{ row }">
        <div
          v-if="row.badgeColor"
          :style="{ display: 'flex', alignItems: 'center', gap: '8px' }"
        >
          <div
            :style="{
              width: '20px',
              height: '20px',
              backgroundColor: row.badgeColor,
              borderRadius: '4px',
              border: '1px solid #d9d9d9',
            }"
          />
          <span>{{ row.badgeColor }}</span>
        </div>
        <span v-else>-</span>
      </template>

      <!-- 操作列 -->
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '编辑',
              type: 'link',
              auth: ['crm:financial-carousel:update'],
              onClick: () => handleEdit(row),
            },
            {
              label: '删除',
              type: 'link',
              color: 'error',
              auth: ['crm:financial-carousel:delete'],
              onClick: () => handleDelete(row.id!),
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
