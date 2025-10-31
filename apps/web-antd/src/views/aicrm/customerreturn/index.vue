<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';
import type { AicrmCustomerReturnApi } from '#/api/aicrm/customerreturn';

import { Page } from '@vben/common-ui';
import { useVbenModal } from '@vben/common-ui';

import { message, Modal } from 'ant-design-vue';

import { ACTION_ICON, TableAction, useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  applyForReturn,
  cancelReturnApplication,
  getCustomerReturnApplicationPage,
} from '#/api/aicrm/customerreturn';
import { $t } from '#/locales';

import { useApplyFormSchema, useGridColumns, useGridFormSchema } from './data';

/** 刷新表格 */
function handleRefresh() {
  gridApi.query();
}

/** 提交申请对话框 */
const [ApplyModal, applyModalApi] = useVbenModal({
  onConfirm: async () => {
    try {
      const values = await applyModalApi.form?.validate();
      await applyForReturn(values as AicrmCustomerReturnApi.CustomerReturnApplicationApplyReq);
      message.success('提交退回申请成功，等待审批');
      applyModalApi.close();
      handleRefresh();
    } catch (error) {
      console.error('提交退回申请失败:', error);
    }
  },
});

/** 打开提交申请对话框 */
function handleApply() {
  applyModalApi.setState({
    isOpen: true,
    title: '提交客户退回申请',
  });
}

/** 取消申请 */
function handleCancel(row: AicrmCustomerReturnApi.CustomerReturnApplication) {
  Modal.confirm({
    title: '确认取消申请',
    content: '确定要取消该退回申请吗？此操作不可恢复。',
    okText: '确认',
    cancelText: '取消',
    onOk: async () => {
      try {
        await cancelReturnApplication(row.id);
        message.success('取消申请成功');
        handleRefresh();
      } catch (error) {
        console.error('取消申请失败:', error);
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
          return await getCustomerReturnApplicationPage({
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
  } as VxeTableGridOptions<AicrmCustomerReturnApi.CustomerReturnApplication>,
});
</script>

<template>
  <Page auto-content-height>
    <Grid table-title="客户退回申请管理">
      <template #toolbar-tools>
        <TableAction
          :actions="[
            {
              label: '提交申请',
              type: 'primary',
              icon: ACTION_ICON.ADD,
              auth: ['aicrm:customer-return:create'],
              onClick: handleApply,
            },
          ]"
        />
      </template>
      <template #actions="{ row }">
        <TableAction
          :actions="[
            {
              label: '取消申请',
              type: 'link',
              icon: ACTION_ICON.DELETE,
              danger: true,
              auth: ['aicrm:customer-return:delete'],
              ifShow: row.processStatus === 1,
              onClick: handleCancel.bind(null, row),
            },
          ]"
        />
      </template>
    </Grid>

    <!-- 提交申请对话框 -->
    <ApplyModal
      :form-schema="useApplyFormSchema()"
      class="!w-[600px]"
      confirm-button-text="提交申请"
    />
  </Page>
</template>
