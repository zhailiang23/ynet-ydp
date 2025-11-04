<script lang="ts" setup>
import type { AicrmCustomerClaimApi } from '#/api/aicrm/customerclaim';
import type { BpmProcessInstanceApi } from '#/api/bpm/processInstance';

import { computed, onMounted, ref, watch } from 'vue';

import { confirm, Page, useVbenForm } from '@vben/common-ui';
import { BpmCandidateStrategyEnum, BpmNodeIdEnum } from '@vben/constants';
import { IconifyIcon } from '@vben/icons';

import { Button, Card, message, Space } from 'ant-design-vue';

import { applyForClaim } from '#/api/aicrm/customerclaim';
import { getProcessDefinition } from '#/api/bpm/definition';
import { getApprovalDetail as getApprovalDetailApi } from '#/api/bpm/processInstance';
import { $t } from '#/locales';
import { router } from '#/router';
import ProcessInstanceTimeline from '#/views/bpm/processInstance/detail/modules/time-line.vue';

import { useApplyFormSchema } from './data';

const formLoading = ref(false); // 表单的加载中：1）修改时的数据加载；2）提交的按钮禁用

// 审批相关：变量
const processDefineKey = 'customer_claim'; // 流程定义 Key
const startUserSelectTasks = ref<any>([]); // 发起人需要选择审批人的用户任务列表
const startUserSelectAssignees = ref<any>({}); // 发起人选择审批人的数据
const tempStartUserSelectAssignees = ref<any>({}); // 历史发起人选择审批人的数据，用于每次表单变更时，临时保存
const activityNodes = ref<BpmProcessInstanceApi.ApprovalNodeInfo[]>([]); // 审批节点信息
const processDefinitionId = ref('');

const formData = ref<AicrmCustomerClaimApi.CustomerClaimApplicationApplyReq>({});
const getTitle = computed(() => {
  return '提交客户认领申请';
});

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
    formItemClass: 'col-span-2',
    labelWidth: 100,
  },
  layout: 'horizontal',
  schema: computed(() => useApplyFormSchema()),
  showDefaultActions: false,
});

/** 提交申请 */
async function onSubmit() {
  const { valid } = await formApi.validate();
  if (!valid) {
    return;
  }

  // 审批相关：校验指定审批人
  if (startUserSelectTasks.value?.length > 0) {
    for (const userTask of startUserSelectTasks.value) {
      if (
        Array.isArray(startUserSelectAssignees.value[userTask.id]) &&
        startUserSelectAssignees.value[userTask.id].length === 0
      ) {
        return message.warning(`请选择${userTask.name}的审批人`);
      }
    }
  }

  // 提交表单
  const data = (await formApi.getValues()) as AicrmCustomerClaimApi.CustomerClaimApplicationApplyReq;

  // 审批相关：设置指定审批人
  const submitData: AicrmCustomerClaimApi.CustomerClaimApplicationApplyReq = {
    ...data,
  };
  if (startUserSelectTasks.value?.length > 0) {
    submitData.startUserSelectAssignees = startUserSelectAssignees.value;
  }

  try {
    formLoading.value = true;
    await applyForClaim(submitData);

    // 提示成功
    message.success('提交认领申请成功，等待审批');

    // 跳转回列表页 - 使用 replace 避免返回时回到创建页
    router.replace('/crm/customerassign/claim');
  } catch (error: any) {
    message.error(error.message);
    formLoading.value = false;
  }
}

/** 返回上一页 */
function onBack() {
  confirm({
    content: '确定要返回上一页吗？请先保存您填写的信息！',
    icon: 'warning',
    beforeClose({ isConfirm }) {
      if (isConfirm) {
        router.back();
      }
      return Promise.resolve(true);
    },
  });
}

// ============================== 审核流程相关 ==============================

/** 审批相关：获取审批详情 */
async function getApprovalDetail() {
  try {
    const data = await getApprovalDetailApi({
      processDefinitionId: processDefinitionId.value,
      activityId: BpmNodeIdEnum.START_USER_NODE_ID,
      processVariablesStr: JSON.stringify(formData.value),
    });

    if (!data) {
      message.error('查询不到审批详情信息！');
      return;
    }
    // 获取审批节点，显示 Timeline 的数据
    activityNodes.value = data.activityNodes;

    // 获取发起人自选的任务
    startUserSelectTasks.value = data.activityNodes?.filter(
      (node: BpmProcessInstanceApi.ApprovalNodeInfo) =>
        BpmCandidateStrategyEnum.START_USER_SELECT === node.candidateStrategy,
    );
    // 恢复之前的选择审批人
    if (startUserSelectTasks.value?.length > 0) {
      for (const node of startUserSelectTasks.value) {
        startUserSelectAssignees.value[node.id] =
          tempStartUserSelectAssignees.value[node.id] &&
          tempStartUserSelectAssignees.value[node.id].length > 0
            ? tempStartUserSelectAssignees.value[node.id]
            : [];
      }
    }
  } finally {
    //
  }
}

/** 审批相关：选择发起人 */
function selectUserConfirm(id: string, userList: any[]) {
  startUserSelectAssignees.value[id] = userList?.map((item: any) => item.id);
}

/** 审批相关：监听表单变化,重新获取审批详情 */
watch(
  formData,
  (newValue, oldValue) => {
    if (!oldValue || Object.keys(oldValue).length === 0) {
      return;
    }
    if (newValue && Object.keys(newValue).length > 0) {
      // 记录之前的节点审批人
      tempStartUserSelectAssignees.value = startUserSelectAssignees.value;
      startUserSelectAssignees.value = {};
      // 加载最新的审批详情
      getApprovalDetail();
    }
  },
  {
    immediate: false,
    deep: true,
  },
);

// ============================== 生命周期 ==============================
onMounted(async () => {
  const processDefinitionDetail: any = await getProcessDefinition(
    undefined,
    processDefineKey,
  );

  if (!processDefinitionDetail) {
    message.error('客户认领的流程模型未配置，请检查！');
    return;
  }

  processDefinitionId.value = processDefinitionDetail.id;
  startUserSelectTasks.value = processDefinitionDetail.startUserSelectTasks;

  // 初始加载审批详情
  await getApprovalDetail();
});
</script>

<template>
  <Page>
    <div class="mx-auto w-[80vw] max-w-[920px]">
      <Card :title="getTitle" class="w-full">
        <template #extra>
          <Button type="default" @click="onBack">
            <IconifyIcon icon="lucide:arrow-left" />
            返回
          </Button>
        </template>

        <Form />
      </Card>

      <Card title="审批流程" class="mt-2 w-full">
        <ProcessInstanceTimeline
          :activity-nodes="activityNodes"
          :show-status-icon="false"
          @select-user-confirm="selectUserConfirm"
        />

        <template #actions>
          <Space warp :size="12" class="w-full px-6">
            <Button :loading="formLoading" type="primary" @click="onSubmit">
              提交申请
            </Button>
          </Space>
        </template>
      </Card>
    </div>
  </Page>
</template>
