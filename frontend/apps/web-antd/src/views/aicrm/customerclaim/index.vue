<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerClaimApi } from '#/api/aicrm/customerclaim';

import { Page } from '@vben/common-ui';

import { message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  cancelClaimApplication,
  getCustomerClaimApplicationPage,
} from '#/api/aicrm/customerclaim';
import { router } from '#/router';

import { useGridColumns, useGridFormSchema } from './data';

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 跳转到创建页面 */
function handleApply() {
  router.push({
    name: 'CustomerClaimCreate',
  });
}

/** 查看详情 */
function handleDetail(row: AicrmCustomerClaimApi.CustomerClaimApplication) {
  router.push({
    name: 'CustomerClaimDetail',
    query: { id: row.id },
  });
}

/** 取消申请 */
function handleCancel(row: AicrmCustomerClaimApi.CustomerClaimApplication) {
  Modal.confirm({
    title: '确认取消申请',
    content: '确定要取消该认领申请吗？此操作不可恢复。',
    okText: '确认',
    cancelText: '取消',
    onOk: async () => {
      try {
        await cancelClaimApplication(row.id);
        message.success('取消申请成功');
        handleRefresh();
      } catch (error) {
        console.error('取消申请失败:', error);
      }
    },
  });
}

/** 审批进度 */
function handleProgress(row: AicrmCustomerClaimApi.CustomerClaimApplication) {
  router.push({
    path: '/bpm/process-instance/detail',
    query: { id: row.processInstanceId },
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
          return await getCustomerClaimApplicationPage({
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
  } as VxeTableGridOptions<AicrmCustomerClaimApi.CustomerClaimApplication>,
});
</script>

<template>
  <Page auto-content-height>
    <Grid table-title="客户认领申请管理">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '提交申请',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:customer-claim:create'],
              onClick: handleApply,
            },
          ]"
        />
      </template>
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '查看详情',
              type: 'link',
              icon: ACTION_ICON.VIEW,
              auth: ['aicrm:customer-claim:query'],
              onClick: handleDetail.bind(null, row),
            },
            {
              label: '审批进度',
              type: 'link',
              icon: ACTION_ICON.VIEW,
              auth: ['aicrm:customer-claim:query'],
              onClick: handleProgress.bind(null, row),
            },
            {
              label: '取消申请',
              type: 'link',
              icon: ACTION_ICON.DELETE,
              danger: true,
              auth: ['aicrm:customer-claim:delete'],
              ifShow: row.processStatus === 1,
              onClick: handleCancel.bind(null, row),
            },
          ]"
        />
      </template>
    </Grid>
  </Page>
</template>
