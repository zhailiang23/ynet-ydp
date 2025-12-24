<script lang="ts" setup>
import type { GridHuinongCustomerLoanApi } from '#/api/grid/huinongcustomerloan';

import { computed, ref, reactive } from 'vue';
import { useVbenModal } from '@vben/common-ui';
import { Form, Input, InputNumber, Select, DatePicker, message } from 'ant-design-vue';
import { createHuinongCustomerLoan, getHuinongCustomerLoan, updateHuinongCustomerLoan } from '#/api/grid/huinongcustomerloan';
import { createCustomer, updateCustomer } from '#/api/grid/customer';
import { useUserStore } from '@vben/stores';
import { $t } from '#/locales';
import VillageMapSelect from '../../huinongmarketing/modules/village-map-select.vue';
import dayjs from 'dayjs';

const emit = defineEmits(['success']);
const formData = ref<GridHuinongCustomerLoanApi.HuinongCustomerLoan>();
const userStore = useUserStore();

const getTitle = computed(() => {
  return formData.value?.id
    ? $t('ui.actionTitle.edit', ['惠农贷款客户'])
    : $t('ui.actionTitle.create', ['惠农贷款客户']);
});

// 客户大类选项
const customerCategoryOptions = [
  { label: '传统种植户', value: '传统种植户' },
  { label: '传统养殖户', value: '传统养殖户' },
  { label: '乡村新型经营主体', value: '乡村新型经营主体' },
  { label: '农资经营主体', value: '农资经营主体' },
  { label: '个体工商户', value: '个体工商户' },
  { label: '乡村服务业', value: '乡村服务业' },
  { label: '工薪阶层', value: '工薪阶层' },
  { label: '乡村特定需求', value: '乡村特定需求' },
  { label: '其他（返乡创业、电商等）', value: '其他（返乡创业、电商等）' },
];

// 性别选项
const genderOptions = [
  { label: '男', value: '男' },
  { label: '女', value: '女' },
];

// 月份选项
const monthOptions = Array.from({ length: 12 }, (_, i) => ({
  label: `${i + 1}月`,
  value: `${i + 1}`,
}));

// 旬选项
const periodOptions = [
  { label: '上旬', value: '上旬' },
  { label: '中旬', value: '中旬' },
  { label: '下旬', value: '下旬' },
];

// 客户来源选项
const customerSourceOptions = [
  { label: '行内客户', value: '行内客户' },
  { label: '外拓客户', value: '外拓客户' },
];

// 是否选项（暂未使用）
// const yesNoOptions = [
//   { label: '是', value: true },
//   { label: '否', value: false },
// ];

// 表单数据
const formState = reactive({
  id: undefined as number | undefined,
  customerId: undefined as number | undefined,

  // (1-2) 员工信息（从当前用户获取）
  employeeCode: userStore.userInfo?.username || '', // 员工工号
  employeeName: userStore.userInfo?.nickname || '', // 员工姓名

  // (3-15) 客户基本信息
  customerCategory: undefined as string | undefined, // 客户大类
  subdivisionType: '', // 细分类型
  businessAddress: '', // 经营地址
  customerName: '', // 负责人姓名
  gender: undefined as string | undefined, // 性别
  phone: '', // 手机号码
  customerSituation: '', // 客户情况
  businessYears: undefined as number | undefined, // 经营年限
  currentFinancing: '', // 当前融资情况
  creditDemand: '', // 信贷产品需求
  demandMonth: undefined as string | undefined, // 需求月份
  demandPeriod: undefined as string | undefined, // 需求旬
  businessProgress: '', // 业务进展
  customerSource: undefined as string | undefined, // 客户来源

  // (16-24) 贷款信息（Mock 数据）
  isApplied: true, // 是否进件
  applyTime: dayjs('2024-03-15'), // 进件时间
  isApproved: true, // 是否审批通过
  approveTime: dayjs('2024-03-20'), // 审批通过时间
  loanProductName: '惠农贷', // 贷款产品名称
  creditLimit: 50, // 授信额度（万元）
  loanAmount: 30, // 贷款金额（万元）
  loanBalance: 25, // 贷款余额（万元）
  overdueStatus: '正常', // 逾期状态
  createTime: undefined as any, // 录入日期（提交时自动获取）
});

// 地图选择的位置
const mapLocation = ref<{ lng: number; lat: number; address: string; villageName: string }>();

// 监听地图位置变化
function handleMapLocationChange(location: { lng: number; lat: number; address: string; villageName: string }) {
  mapLocation.value = location;
  formState.businessAddress = location.address;
}

// 表单验证规则
const rules = {
  customerCategory: [{ required: true, message: '客户大类请选择' }],
  subdivisionType: [{ required: true, message: '细分类型请输入' }],
  businessAddress: [{ required: true, message: '经营地址请输入' }],
  customerName: [{ required: true, message: '负责人姓名请输入' }],
  gender: [{ required: true, message: '性别请选择' }],
  phone: [
    { required: true, message: '手机号码请输入' },
    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号码' },
  ],
  customerSituation: [{ required: true, message: '客户情况请输入' }],
  businessYears: [{ required: true, message: '经营年限请输入' }],
  currentFinancing: [{ required: true, message: '当前融资情况请输入' }],
  creditDemand: [{ required: true, message: '信贷产品需求请输入' }],
  demandMonth: [{ required: true, message: '需求月份请选择' }],
  demandPeriod: [{ required: true, message: '需求旬请选择' }],
  businessProgress: [{ required: true, message: '业务进展请输入' }],
  customerSource: [{ required: true, message: '客户来源请选择' }],
};

// 初始化表单数据
async function initFormData(data?: GridHuinongCustomerLoanApi.HuinongCustomerLoan) {
  // 重置员工信息
  formState.employeeCode = userStore.userInfo?.username || '';
  formState.employeeName = userStore.userInfo?.nickname || '';

  if (!data || !data.id) {
    // 新增时，清空表单
    return;
  }

  // 编辑时，加载数据
  try {
    const result = await getHuinongCustomerLoan(data.id);
    formState.id = result.id;
    formState.customerId = result.customerId;

    formState.customerCategory = result.customerCategory;
    formState.subdivisionType = result.subdivisionType || '';
    formState.businessAddress = result.businessAddress || '';
    formState.customerName = result.customerName || '';
    formState.gender = result.gender;
    formState.phone = result.phone || '';
    formState.customerSituation = result.customerSituation || '';
    formState.businessYears = result.businessYears;
    formState.currentFinancing = result.currentFinancing || '';
    formState.creditDemand = result.creditDemand || '';
    formState.demandMonth = result.demandMonth;
    formState.demandPeriod = result.demandPeriod;
    formState.businessProgress = result.businessProgress || '';
    formState.customerSource = result.customerSource;

    // Mock 数据字段（金额从元转换为万元显示）
    formState.isApplied = result.isApplied ?? true;
    formState.applyTime = result.applyTime ? dayjs(result.applyTime) : undefined;
    formState.isApproved = result.isApproved ?? true;
    formState.approveTime = result.approveTime ? dayjs(result.approveTime) : undefined;
    formState.loanProductName = result.loanProductName || '';
    formState.creditLimit = result.creditLimit ? result.creditLimit / 10000 : 0;
    formState.loanAmount = result.loanAmount ? result.loanAmount / 10000 : 0;
    formState.loanBalance = result.loanBalance ? result.loanBalance / 10000 : 0;
    formState.overdueStatus = result.overdueStatus || '';

    // 设置地图位置（如果有经纬度数据）
    if (result.longitude && result.latitude) {
      mapLocation.value = {
        lng: result.longitude,
        lat: result.latitude,
        address: result.businessAddress || '',
        villageName: '',
      };
    }
  } catch (error) {
    console.error('加载数据失败:', error);
    message.error('加载数据失败');
  }
}

// 提交表单
async function handleSubmit() {
  // 验证必填字段
  if (!formState.customerCategory) {
    message.error('客户大类请选择');
    return false;
  }
  if (!formState.subdivisionType) {
    message.error('细分类型请输入');
    return false;
  }
  if (!formState.businessAddress) {
    message.error('经营地址请输入');
    return false;
  }
  if (!formState.customerName) {
    message.error('负责人姓名请输入');
    return false;
  }
  if (!formState.gender) {
    message.error('性别请选择');
    return false;
  }
  if (!formState.phone) {
    message.error('手机号码请输入');
    return false;
  }
  if (!/^1[3-9]\d{9}$/.test(formState.phone)) {
    message.error('请输入正确的手机号码');
    return false;
  }
  if (!formState.customerSituation) {
    message.error('客户情况请输入');
    return false;
  }
  if (!formState.businessYears) {
    message.error('经营年限请输入');
    return false;
  }
  if (!formState.currentFinancing) {
    message.error('当前融资情况请输入');
    return false;
  }
  if (!formState.creditDemand) {
    message.error('信贷产品需求请输入');
    return false;
  }
  if (!formState.demandMonth) {
    message.error('需求月份请选择');
    return false;
  }
  if (!formState.demandPeriod) {
    message.error('需求旬请选择');
    return false;
  }
  if (!formState.businessProgress) {
    message.error('业务进展请输入');
    return false;
  }
  if (!formState.customerSource) {
    message.error('客户来源请选择');
    return false;
  }

  try {
    // 1. 先保存 grid_customer 表
    let customerId = formState.customerId;

    const customerData: any = {
      id: customerId,
      customerName: formState.customerName,
      phone: formState.phone,
      address: formState.businessAddress,
      customerType: 'HUINONG_LOAN', // 惠农贷款客户
      source: formState.customerSource,
      status: 'NORMAL',
      // 添加经纬度信息
      longitude: mapLocation.value?.lng,
      latitude: mapLocation.value?.lat,
    };

    if (customerId) {
      // 更新客户
      await updateCustomer(customerData);
    } else {
      // 创建客户，获取客户 ID
      const result = await createCustomer(customerData);
      customerId = result;
      formState.customerId = customerId;
    }

    // 2. 再保存 grid_huinong_customer_loan 表
    const submitData: any = {
      id: formState.id,
      customerId: customerId,
      customerCategory: formState.customerCategory,
      subdivisionType: formState.subdivisionType,
      businessAddress: formState.businessAddress,
      gender: formState.gender,
      customerSituation: formState.customerSituation,
      businessYears: formState.businessYears,
      currentFinancing: formState.currentFinancing,
      creditDemand: formState.creditDemand,
      demandMonth: formState.demandMonth,
      demandPeriod: formState.demandPeriod,
      businessProgress: formState.businessProgress,
      customerSource: formState.customerSource,

      // Mock 数据字段（金额从万元转换为元）
      isApplied: formState.isApplied,
      applyTime: formState.applyTime ? formState.applyTime.format('YYYY-MM-DD HH:mm:ss') : undefined,
      isApproved: formState.isApproved,
      approveTime: formState.approveTime ? formState.approveTime.format('YYYY-MM-DD HH:mm:ss') : undefined,
      loanProductName: formState.loanProductName,
      creditLimit: formState.creditLimit ? formState.creditLimit * 10000 : undefined,
      loanAmount: formState.loanAmount ? formState.loanAmount * 10000 : undefined,
      loanBalance: formState.loanBalance ? formState.loanBalance * 10000 : undefined,
      overdueStatus: formState.overdueStatus,
    };

    await (formState.id ? updateHuinongCustomerLoan(submitData) : createHuinongCustomerLoan(submitData));
    return true;
  } catch (error) {
    console.error('提交失败:', error);
    return false;
  }
}

const [Modal, modalApi] = useVbenModal({
  async onConfirm() {
    const success = await handleSubmit();
    if (!success) {
      return;
    }
    await modalApi.close();
    emit('success');
    message.success($t('ui.actionMessage.operationSuccess'));
  },
  async onOpenChange(isOpen: boolean) {
    if (!isOpen) {
      // 关闭时重置数据
      formData.value = undefined;
      formState.id = undefined;
      formState.customerId = undefined;
      formState.customerCategory = undefined;
      formState.subdivisionType = '';
      formState.businessAddress = '';
      formState.customerName = '';
      formState.gender = undefined;
      formState.phone = '';
      formState.customerSituation = '';
      formState.businessYears = undefined;
      formState.currentFinancing = '';
      formState.creditDemand = '';
      formState.demandMonth = undefined;
      formState.demandPeriod = undefined;
      formState.businessProgress = '';
      formState.customerSource = undefined;
      mapLocation.value = undefined;
      return;
    }
    // 打开时加载数据
    const data = modalApi.getData<GridHuinongCustomerLoanApi.HuinongCustomerLoan>();
    formData.value = data;
    modalApi.lock();
    try {
      await initFormData(data);
    } finally {
      modalApi.unlock();
    }
  },
});
</script>

<template>
  <Modal :title="getTitle" class="!w-[90vw]" :footer-props="{ okText: '确定', cancelText: '取消' }">
    <div class="flex h-[700px] gap-4">
      <!-- 左侧：地图（2/3宽度） -->
      <div class="flex-[2]">
        <VillageMapSelect v-model="mapLocation" @update:model-value="handleMapLocationChange" />
      </div>

      <!-- 右侧：表单（1/3宽度） -->
      <div class="flex-[1] overflow-y-auto pr-2">
        <Form layout="vertical" :model="formState">
          <!-- (1) 员工工号 -->
          <Form.Item label="员工工号">
            <Input v-model:value="formState.employeeCode" disabled />
          </Form.Item>

          <!-- (2) 员工姓名 -->
          <Form.Item label="员工姓名">
            <Input v-model:value="formState.employeeName" disabled />
          </Form.Item>

          <!-- (3) 客户大类 -->
          <Form.Item label="客户大类" name="customerCategory" :rules="rules.customerCategory">
            <Select v-model:value="formState.customerCategory" placeholder="请选择客户大类">
              <Select.Option v-for="option in customerCategoryOptions" :key="option.value" :value="option.value">
                {{ option.label }}
              </Select.Option>
            </Select>
          </Form.Item>

          <!-- (4) 细分类型 -->
          <Form.Item label="细分类型" name="subdivisionType" :rules="rules.subdivisionType">
            <Input v-model:value="formState.subdivisionType" placeholder="请输入细分类型" />
          </Form.Item>

          <!-- (5) 经营地址 -->
          <Form.Item label="经营地址" name="businessAddress" :rules="rules.businessAddress">
            <Input v-model:value="formState.businessAddress" placeholder="在地图上选择位置后自动填充" />
          </Form.Item>

          <!-- (6) 负责人姓名 -->
          <Form.Item label="负责人姓名" name="customerName" :rules="rules.customerName">
            <Input v-model:value="formState.customerName" placeholder="请输入负责人姓名" />
          </Form.Item>

          <!-- (7) 性别 -->
          <Form.Item label="性别" name="gender" :rules="rules.gender">
            <Select v-model:value="formState.gender" placeholder="请选择性别">
              <Select.Option v-for="option in genderOptions" :key="option.value" :value="option.value">
                {{ option.label }}
              </Select.Option>
            </Select>
          </Form.Item>

          <!-- (8) 手机号码 -->
          <Form.Item label="手机号码" name="phone" :rules="rules.phone">
            <Input v-model:value="formState.phone" placeholder="请输入手机号码" />
          </Form.Item>

          <!-- (9) 客户情况 -->
          <Form.Item label="客户情况" name="customerSituation" :rules="rules.customerSituation">
            <Input.TextArea v-model:value="formState.customerSituation" :rows="2" placeholder="请输入客户情况" />
          </Form.Item>

          <!-- (10) 经营年限 -->
          <Form.Item label="经营年限（年）" name="businessYears" :rules="rules.businessYears">
            <InputNumber v-model:value="formState.businessYears" :min="0" class="w-full" placeholder="请输入经营年限" />
          </Form.Item>

          <!-- (11) 当前融资情况 -->
          <Form.Item label="当前融资情况" name="currentFinancing" :rules="rules.currentFinancing">
            <Input.TextArea v-model:value="formState.currentFinancing" :rows="2" placeholder="请输入当前融资情况" />
          </Form.Item>

          <!-- (12) 信贷产品需求 -->
          <Form.Item label="信贷产品需求" name="creditDemand" :rules="rules.creditDemand">
            <Input.TextArea v-model:value="formState.creditDemand" :rows="2" placeholder="请输入信贷产品需求" />
          </Form.Item>

          <!-- (13) 需求窗口 -->
          <Form.Item label="需求月份" name="demandMonth" :rules="rules.demandMonth">
            <Select v-model:value="formState.demandMonth" placeholder="请选择需求月份">
              <Select.Option v-for="option in monthOptions" :key="option.value" :value="option.value">
                {{ option.label }}
              </Select.Option>
            </Select>
          </Form.Item>

          <Form.Item label="需求旬" name="demandPeriod" :rules="rules.demandPeriod">
            <Select v-model:value="formState.demandPeriod" placeholder="请选择需求旬">
              <Select.Option v-for="option in periodOptions" :key="option.value" :value="option.value">
                {{ option.label }}
              </Select.Option>
            </Select>
          </Form.Item>

          <!-- (14) 业务进展 -->
          <Form.Item label="业务进展" name="businessProgress" :rules="rules.businessProgress">
            <Input.TextArea v-model:value="formState.businessProgress" :rows="2" placeholder="请输入业务进展" />
          </Form.Item>

          <!-- (15) 客户来源 -->
          <Form.Item label="客户来源" name="customerSource" :rules="rules.customerSource">
            <Select v-model:value="formState.customerSource" placeholder="请选择客户来源">
              <Select.Option v-for="option in customerSourceOptions" :key="option.value" :value="option.value">
                {{ option.label }}
              </Select.Option>
            </Select>
          </Form.Item>

          <!-- (16-24) Mock 数据字段 - 显示为禁用状态 -->
          <div class="mt-4 border-t border-gray-200 pt-4">
            <div class="mb-2 text-sm font-medium text-gray-500">贷款信息（系统获取）</div>

            <!-- (16) 是否进件 -->
            <Form.Item label="是否进件">
              <Input :value="formState.isApplied ? '是' : '否'" disabled />
            </Form.Item>

            <!-- (17) 进件时间 -->
            <Form.Item label="进件时间">
              <DatePicker v-model:value="formState.applyTime" disabled class="w-full" />
            </Form.Item>

            <!-- (18) 是否审批通过 -->
            <Form.Item label="是否审批通过">
              <Input :value="formState.isApproved ? '是' : '否'" disabled />
            </Form.Item>

            <!-- (19) 审批通过时间 -->
            <Form.Item label="审批通过时间">
              <DatePicker v-model:value="formState.approveTime" disabled class="w-full" />
            </Form.Item>

            <!-- (20) 贷款产品名称 -->
            <Form.Item label="贷款产品名称">
              <Input v-model:value="formState.loanProductName" disabled />
            </Form.Item>

            <!-- (21) 授信额度 -->
            <Form.Item label="授信额度（万元）">
              <InputNumber v-model:value="formState.creditLimit" disabled class="w-full" />
            </Form.Item>

            <!-- 贷款金额 -->
            <Form.Item label="贷款金额（万元）">
              <InputNumber v-model:value="formState.loanAmount" disabled class="w-full" />
            </Form.Item>

            <!-- (22) 贷款余额 -->
            <Form.Item label="贷款余额（万元）">
              <InputNumber v-model:value="formState.loanBalance" disabled class="w-full" />
            </Form.Item>

            <!-- (23) 逾期状态 -->
            <Form.Item label="逾期状态">
              <Input v-model:value="formState.overdueStatus" disabled />
            </Form.Item>
          </div>
        </Form>
      </div>
    </div>
  </Modal>
</template>
