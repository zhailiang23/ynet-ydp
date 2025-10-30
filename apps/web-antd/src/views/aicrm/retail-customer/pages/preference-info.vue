<script lang="ts" setup>
import { onMounted, ref, watch } from 'vue';

import { getDictOptions } from '@vben/hooks';

import { Checkbox, Collapse, Input, message, Spin } from 'ant-design-vue';

import {
  getCustomerPreferenceByCustomerId,
  saveCustomerPreference,
} from '#/api/aicrm/customerpreference';

defineOptions({
  name: 'RetailCustomerPreferenceInfo',
});

const props = defineProps<{
  customerId: number;
}>();

// 偏好数据
const preferenceData = ref<any>({
  electronicChannels: [],
  otherChannel: '',
  investmentTypes: [],
  otherInvestmentType: '',
  brandTypes: [],
  otherBrandType: '',
  financialServices: [],
  otherFinancialService: '',
  contactMethods: [],
  otherContactMethod: '',
  salonActivities: [],
  otherSalonActivity: '',
  interestHobbies: [],
  otherInterestHobby: '',
  contactTimes: [],
  otherContactTime: '',
  investmentPeriods: [],
  otherInvestmentPeriod: '',
});

const loading = ref(false);
const saving = ref(false);
const hasData = ref(false);

// 电子渠道选项（固定选项）
const electronicChannelOptions = [
  { label: '手机银行', value: 'mobile_bank' },
  { label: '电话银行', value: 'phone_bank' },
  { label: '网上银行', value: 'online_bank' },
  { label: '微信银行', value: 'wechat_bank' },
  { label: '短信服务', value: 'sms_service' },
  { label: '自助银行', value: 'atm' },
];

// 获取字典选项
const investmentTypeOptions = ref(getDictOptions('aicrm_investment_type'));
const brandTypeOptions = ref(getDictOptions('aicrm_brand_type'));
const financialServiceOptions = ref(getDictOptions('aicrm_financial_service'));
const contactMethodOptions = ref(getDictOptions('aicrm_contact_method'));
const salonActivityOptions = ref(getDictOptions('aicrm_salon_activity'));
const interestHobbyOptions = ref(getDictOptions('aicrm_interest_hobby'));
const contactTimeOptions = ref(getDictOptions('aicrm_contact_time'));
const investmentPeriodOptions = ref(getDictOptions('aicrm_investment_period'));

// 加载客户偏好数据
async function loadPreferenceData() {
  loading.value = true;
  try {
    const result = await getCustomerPreferenceByCustomerId(props.customerId);
    if (result) {
      preferenceData.value = {
        electronicChannels:
          result.electronicChannels?.split(',').filter(Boolean) || [],
        otherChannel: result.otherChannel || '',
        investmentTypes:
          result.investmentTypes?.split(',').filter(Boolean) || [],
        otherInvestmentType: result.otherInvestmentType || '',
        brandTypes: result.brandTypes?.split(',').filter(Boolean) || [],
        otherBrandType: result.otherBrandType || '',
        financialServices:
          result.financialServices?.split(',').filter(Boolean) || [],
        otherFinancialService: result.otherFinancialService || '',
        contactMethods: result.contactMethods?.split(',').filter(Boolean) || [],
        otherContactMethod: result.otherContactMethod || '',
        salonActivities:
          result.salonActivities?.split(',').filter(Boolean) || [],
        otherSalonActivity: result.otherSalonActivity || '',
        interestHobbies:
          result.interestHobbies?.split(',').filter(Boolean) || [],
        otherInterestHobby: result.otherInterestHobby || '',
        contactTimes: result.contactTimes?.split(',').filter(Boolean) || [],
        otherContactTime: result.otherContactTime || '',
        investmentPeriods:
          result.investmentPeriods?.split(',').filter(Boolean) || [],
        otherInvestmentPeriod: result.otherInvestmentPeriod || '',
      };
      hasData.value = true;
    } else {
      hasData.value = false;
    }
  } catch (error: any) {
    console.error('加载客户偏好失败:', error);
    message.error(error.message || '加载客户偏好失败');
  } finally {
    loading.value = false;
  }
}

// 保存偏好数据
async function savePreferenceData() {
  if (saving.value) return;

  saving.value = true;
  try {
    const params = {
      customerId: props.customerId,
      electronicChannels: preferenceData.value.electronicChannels.join(','),
      otherChannel: preferenceData.value.otherChannel,
      investmentTypes: preferenceData.value.investmentTypes.join(','),
      otherInvestmentType: preferenceData.value.otherInvestmentType,
      brandTypes: preferenceData.value.brandTypes.join(','),
      otherBrandType: preferenceData.value.otherBrandType,
      financialServices: preferenceData.value.financialServices.join(','),
      otherFinancialService: preferenceData.value.otherFinancialService,
      contactMethods: preferenceData.value.contactMethods.join(','),
      otherContactMethod: preferenceData.value.otherContactMethod,
      salonActivities: preferenceData.value.salonActivities.join(','),
      otherSalonActivity: preferenceData.value.otherSalonActivity,
      interestHobbies: preferenceData.value.interestHobbies.join(','),
      otherInterestHobby: preferenceData.value.otherInterestHobby,
      contactTimes: preferenceData.value.contactTimes.join(','),
      otherContactTime: preferenceData.value.otherContactTime,
      investmentPeriods: preferenceData.value.investmentPeriods.join(','),
      otherInvestmentPeriod: preferenceData.value.otherInvestmentPeriod,
    };

    await saveCustomerPreference(params);
    hasData.value = true;
    message.success('保存成功');
  } catch (error: any) {
    console.error('保存客户偏好失败:', error);
    message.error(error.message || '保存客户偏好失败');
  } finally {
    saving.value = false;
  }
}

// 监听数据变化，自动保存（防抖）
let saveTimer: null | ReturnType<typeof setTimeout> = null;

watch(
  () => preferenceData.value,
  () => {
    if (saveTimer) {
      clearTimeout(saveTimer);
    }
    saveTimer = setTimeout(() => {
      savePreferenceData();
    }, 1000); // 1秒后自动保存
  },
  { deep: true },
);

// 组件挂载
onMounted(() => {
  loadPreferenceData();
});

// 暴露刷新方法
defineExpose({
  refresh: () => {
    loadPreferenceData();
  },
});
</script>

<template>
  <div class="preference-info-page">
    <Spin :spinning="loading">
      <Collapse
        v-if="!loading"
        :default-active-key="[
          'electronic-channel',
          'investment-type',
          'brand-type',
          'financial-service',
          'contact-method',
          'salon-activity',
          'interest-hobby',
          'contact-time',
          'investment-period',
        ]"
        expand-icon-position="end"
      >
        <!-- 1. 喜好的电子渠道 -->
        <Collapse.Panel key="electronic-channel" header="喜好的电子渠道">
          <div class="preference-section">
            <div class="checkbox-group">
              <Checkbox.Group
                v-model:value="preferenceData.electronicChannels"
                :options="electronicChannelOptions"
              />
            </div>
            <div class="other-input-wrapper">
              <span class="other-label">其他：</span>
              <Input
                v-model:value="preferenceData.otherChannel"
                placeholder="请输入其他电子渠道"
                class="other-input"
              />
            </div>
          </div>
        </Collapse.Panel>

        <!-- 2. 喜好投资类型 -->
        <Collapse.Panel key="investment-type" header="喜好投资类型">
          <div class="preference-section">
            <div class="checkbox-group">
              <Checkbox.Group
                v-model:value="preferenceData.investmentTypes"
                :options="investmentTypeOptions"
              />
            </div>
            <div class="other-input-wrapper">
              <span class="other-label">其他：</span>
              <Input
                v-model:value="preferenceData.otherInvestmentType"
                placeholder="请输入其他投资类型"
                class="other-input"
              />
            </div>
          </div>
        </Collapse.Panel>

        <!-- 3. 喜好品牌类型 -->
        <Collapse.Panel key="brand-type" header="喜好品牌类型">
          <div class="preference-section">
            <div class="checkbox-group">
              <Checkbox.Group
                v-model:value="preferenceData.brandTypes"
                :options="brandTypeOptions"
              />
            </div>
            <div class="other-input-wrapper">
              <span class="other-label">其他：</span>
              <Input
                v-model:value="preferenceData.otherBrandType"
                placeholder="请输入其他品牌类型"
                class="other-input"
              />
            </div>
          </div>
        </Collapse.Panel>

        <!-- 4. 希望得到的理财服务 -->
        <Collapse.Panel key="financial-service" header="希望得到的理财服务">
          <div class="preference-section">
            <div class="checkbox-group">
              <Checkbox.Group
                v-model:value="preferenceData.financialServices"
                :options="financialServiceOptions"
              />
            </div>
            <div class="other-input-wrapper">
              <span class="other-label">其他：</span>
              <Input
                v-model:value="preferenceData.otherFinancialService"
                placeholder="请输入其他理财服务"
                class="other-input"
              />
            </div>
          </div>
        </Collapse.Panel>

        <!-- 5. 希望理财经理的联系方式 -->
        <Collapse.Panel key="contact-method" header="希望理财经理的联系方式">
          <div class="preference-section">
            <div class="checkbox-group">
              <Checkbox.Group
                v-model:value="preferenceData.contactMethods"
                :options="contactMethodOptions"
              />
            </div>
            <div class="other-input-wrapper">
              <span class="other-label">其他：</span>
              <Input
                v-model:value="preferenceData.otherContactMethod"
                placeholder="请输入其他联系方式"
                class="other-input"
              />
            </div>
          </div>
        </Collapse.Panel>

        <!-- 6. 希望参加的沙龙活动 -->
        <Collapse.Panel key="salon-activity" header="希望参加的沙龙活动">
          <div class="preference-section">
            <div class="checkbox-group">
              <Checkbox.Group
                v-model:value="preferenceData.salonActivities"
                :options="salonActivityOptions"
              />
            </div>
            <div class="other-input-wrapper">
              <span class="other-label">其他：</span>
              <Input
                v-model:value="preferenceData.otherSalonActivity"
                placeholder="请输入其他沙龙活动"
                class="other-input"
              />
            </div>
          </div>
        </Collapse.Panel>

        <!-- 7. 个人兴趣爱好 -->
        <Collapse.Panel key="interest-hobby" header="个人兴趣爱好">
          <div class="preference-section">
            <div class="checkbox-group">
              <Checkbox.Group
                v-model:value="preferenceData.interestHobbies"
                :options="interestHobbyOptions"
              />
            </div>
            <div class="other-input-wrapper">
              <span class="other-label">其他：</span>
              <Input
                v-model:value="preferenceData.otherInterestHobby"
                placeholder="请输入其他兴趣爱好"
                class="other-input"
              />
            </div>
          </div>
        </Collapse.Panel>

        <!-- 8. 希望联系的时间 -->
        <Collapse.Panel key="contact-time" header="希望联系的时间">
          <div class="preference-section">
            <div class="checkbox-group">
              <Checkbox.Group
                v-model:value="preferenceData.contactTimes"
                :options="contactTimeOptions"
              />
            </div>
            <div class="other-input-wrapper">
              <span class="other-label">其他：</span>
              <Input
                v-model:value="preferenceData.otherContactTime"
                placeholder="请输入其他联系时间"
                class="other-input"
              />
            </div>
          </div>
        </Collapse.Panel>

        <!-- 9. 投资周期偏好 -->
        <Collapse.Panel key="investment-period" header="投资周期偏好">
          <div class="preference-section">
            <div class="checkbox-group">
              <Checkbox.Group
                v-model:value="preferenceData.investmentPeriods"
                :options="investmentPeriodOptions"
              />
            </div>
            <div class="other-input-wrapper">
              <span class="other-label">其他：</span>
              <Input
                v-model:value="preferenceData.otherInvestmentPeriod"
                placeholder="请输入其他投资周期"
                class="other-input"
              />
            </div>
          </div>
        </Collapse.Panel>
      </Collapse>

      <!-- 保存提示 -->
      <div v-if="saving" class="saving-indicator">正在保存...</div>
    </Spin>
  </div>
</template>

<style scoped lang="less">
.preference-info-page {
  padding: 16px;
  background: #fff;

  :deep(.ant-collapse) {
    background: #fff;
    border: 1px solid #d9d9d9;
    border-radius: 2px;

    .ant-collapse-item {
      border-bottom: 1px solid #d9d9d9;

      &:last-child {
        border-bottom: none;
      }
    }

    .ant-collapse-header {
      padding: 12px 16px;
      font-weight: 500;
      color: rgba(0, 0, 0, 0.85);
    }

    .ant-collapse-content {
      border-top: 1px solid #d9d9d9;
    }

    .ant-collapse-content-box {
      padding: 16px;
    }
  }

  .preference-section {
    .checkbox-group {
      margin-bottom: 16px;

      :deep(.ant-checkbox-group) {
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        gap: 12px 16px;

        .ant-checkbox-wrapper {
          margin: 0;
        }
      }
    }

    .other-input-wrapper {
      display: flex;
      align-items: center;
      padding-top: 12px;
      border-top: 1px dashed #e8e8e8;

      .other-label {
        flex-shrink: 0;
        min-width: 60px;
        margin-right: 12px;
        color: rgba(0, 0, 0, 0.85);
        font-weight: 500;
      }

      .other-input {
        flex: 1;
        max-width: 400px;
      }
    }
  }

  .saving-indicator {
    position: fixed;
    top: 80px;
    right: 24px;
    z-index: 1000;
    padding: 8px 16px;
    color: #fff;
    background: rgba(0, 0, 0, 0.75);
    border-radius: 4px;
    animation: fadeIn 0.3s;
  }

  @keyframes fadeIn {
    from {
      opacity: 0;
      transform: translateY(-10px);
    }

    to {
      opacity: 1;
      transform: translateY(0);
    }
  }
}
</style>
