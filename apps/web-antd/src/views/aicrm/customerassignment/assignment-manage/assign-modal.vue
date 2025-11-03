<script lang="ts" setup>
import type { AicrmCustomerAssignmentApi } from '#/api/aicrm/customerassignment';
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref, watch } from 'vue';

import { useVbenModal } from '@vben/common-ui';
import { handleTree } from '@vben/utils';

import { message } from 'ant-design-vue';

import { useVbenForm } from '#/adapter/form';
import { assignCustomers } from '#/api/aicrm/customerassignment';
import { getDeptList } from '#/api/system/dept';
import { getSimpleUserList } from '#/api/system/user';

const emit = defineEmits(['success']);
const customerIds = ref<number[]>([]);
const selectedDeptId = ref<number | undefined>(undefined);
const deptUsers = ref<any[]>([]);
const allDepts = ref<any[]>([]);

// 递归获取部门及其所有子部门的ID列表
function getDeptIdsRecursive(deptId: number, depts: any[]): number[] {
  const result = [deptId];
  const children = depts.filter((d: any) => d.parentId === deptId);
  for (const child of children) {
    result.push(...getDeptIdsRecursive(child.id, depts));
  }
  return result;
}

// 当选择部门后,加载该部门及其子部门的所有用户
watch(selectedDeptId, async (deptId) => {
  if (deptId) {
    // 获取所有部门列表(用于查找子部门)
    if (allDepts.value.length === 0) {
      allDepts.value = await getDeptList();
    }

    // 获取选中部门及其所有子部门的ID
    const deptIds = getDeptIdsRecursive(deptId, allDepts.value);

    // 获取这些部门下的所有用户
    const allUsers = await getSimpleUserList();
    deptUsers.value = allUsers.filter((user: any) => deptIds.includes(user.deptId));
  } else {
    deptUsers.value = [];
  }
});

const formSchema = computed<VbenFormSchema[]>(() => [
  {
    fieldName: 'assignmentType',
    label: '归属类型',
    component: 'RadioGroup',
    rules: 'required',
    defaultValue: 1,
    componentProps: {
      options: [
        { label: '主办', value: 1 },
        { label: '协办', value: 2 },
      ],
      buttonStyle: 'solid',
      optionType: 'button',
    },
  },
  {
    fieldName: 'deptId',
    label: '归属部门',
    component: 'ApiTreeSelect',
    rules: 'required',
    componentProps: {
      api: async () => {
        const data = await getDeptList();
        return handleTree(data);
      },
      labelField: 'name',
      valueField: 'id',
      childrenField: 'children',
      placeholder: '请选择归属部门',
      treeDefaultExpandAll: true,
      onChange: (value: number) => {
        selectedDeptId.value = value;
        // 当部门改变时,清空用户选择
        formApi.setFieldValue('userId', undefined);
      },
    },
  },
  {
    fieldName: 'userId',
    label: '客户经理',
    component: 'Select',
    rules: 'required',
    componentProps: {
      options: deptUsers.value.map((user: any) => ({
        label: user.nickname,
        value: user.id,
      })),
      labelField: 'label',
      valueField: 'value',
      placeholder: selectedDeptId.value ? '请选择客户经理' : '请先选择归属部门',
      showSearch: true,
      disabled: !selectedDeptId.value,
      filterOption: (input: string, option: any) => {
        return option.label?.toLowerCase().includes(input.toLowerCase());
      },
    },
  },
  {
    fieldName: 'remark',
    label: '分配备注',
    component: 'Textarea',
    componentProps: {
      placeholder: '请输入分配备注（选填）',
      rows: 3,
      maxlength: 500,
      showCount: true,
    },
  },
]);

const [Form, formApi] = useVbenForm({
  commonConfig: {
    componentProps: {
      class: 'w-full',
    },
    formItemClass: 'col-span-2',
    labelWidth: 100,
  },
  layout: 'horizontal',
  schema: formSchema,
  showDefaultActions: false,
});

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const { valid } = await formApi.validate();
    if (!valid) {
      return;
    }
    modalApi.lock();
    // 提交表单
    const values = await formApi.getValues();
    try {
      await assignCustomers({
        customerIds: customerIds.value,
        hasViewRight: true,
        hasMaintainRight: true,
        ...values,
      } as AicrmCustomerAssignmentApi.AssignCustomerReq);
      // 关闭并提示
      await modalApi.close();
      emit('success');
      message.success('分配客户成功');
    } finally {
      modalApi.unlock();
    }
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      customerIds.value = [];
      selectedDeptId.value = undefined;
      deptUsers.value = [];
      formApi.resetForm();
      return;
    }
    // 加载数据 - open方法传入的data会在这里获取
    const data = modalApi.getData<{ customerIds?: number[] }>();
    console.log('Modal opened with data:', data);
    if (data?.customerIds) {
      customerIds.value = data.customerIds;
    }
  },
});

defineExpose({ modalApi });
</script>

<template>
  <Modal title="分配客户" class="!w-[600px]">
    <div class="mx-4">
      <div class="mb-4 rounded bg-blue-50 p-3 text-sm dark:bg-blue-950/30">
        <div class="text-gray-600 dark:text-gray-400">
          将为 <span class="font-bold text-blue-600 dark:text-blue-400">{{ customerIds.length }}</span> 个客户分配归属关系
        </div>
      </div>
      <Form />
    </div>
  </Modal>
</template>
