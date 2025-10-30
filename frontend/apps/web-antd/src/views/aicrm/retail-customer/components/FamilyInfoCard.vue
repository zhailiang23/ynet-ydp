<script lang="ts" setup>
import type { AicrmCustomerFamilyApi } from '#/api/aicrm/customerfamily';

import { getDictLabel } from '@vben/hooks';

import { Descriptions } from 'ant-design-vue';

const props = defineProps<{
  familyInfo: AicrmCustomerFamilyApi.CustomerFamily;
}>();

// 获取字典标签
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
}

// 格式化布尔值
function formatBoolean(value?: boolean) {
  if (value === null || value === undefined) return '-';
  return value ? '是' : '否';
}

// 格式化金额
function formatMoney(value?: number) {
  if (value === null || value === undefined) return '-';
  return `${value.toLocaleString('zh-CN')} 万元`;
}
</script>

<template>
  <div class="space-y-6">
    <!-- 卡片 1: 家庭基本信息 -->
    <a-card title="家庭基本信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="家庭成员数">
          {{ familyInfo.familyMemberCount || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="赡养人数">
          {{ familyInfo.supportMemberCount || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="劳动力人数">
          {{ familyInfo.laborMemberCount || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="子女数量">
          {{ familyInfo.childrenCount || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="是否户主">
          {{ formatBoolean(familyInfo.isHouseHolder) }}
        </Descriptions.Item>
        <Descriptions.Item label="户主姓名">
          {{ familyInfo.houseHolderName || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭是否和睦">
          {{ formatBoolean(familyInfo.isHarmony) }}
        </Descriptions.Item>
        <Descriptions.Item label="是否有房有车">
          {{ formatBoolean(familyInfo.hasHomeCar) }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭实力">
          {{ getDict('aicrm_family_strength', familyInfo.familyStrength) }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭住址" :span="3">
          {{ familyInfo.familyAddress || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭电话" :span="3">
          {{ familyInfo.homeTel || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 2: 居住信息 -->
    <a-card title="居住信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="居住状况">
          {{ getDict('aicrm_residence_status', familyInfo.residenceStatus) }}
        </Descriptions.Item>
        <Descriptions.Item label="房屋状况">
          {{ getDict('aicrm_house_status', familyInfo.houseStatus) }}
        </Descriptions.Item>
        <Descriptions.Item label="居住地点描述">
          {{ familyInfo.residenceLocation || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 3: 收入资产信息 -->
    <a-card title="收入资产信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="家庭年收入">
          {{ formatMoney(familyInfo.familyAnnualIncome) }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭年收入范围">
          {{ getDict('aicrm_family_income_scope', familyInfo.familyAnnualIncomeScope) }}
        </Descriptions.Item>
        <Descriptions.Item label="主要收入来源">
          {{ getDict('aicrm_income_source', familyInfo.mainIncomeSource) }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭年支出">
          {{ formatMoney(familyInfo.familyAnnualExpenditure) }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭年支出范围">
          {{ getDict('aicrm_family_expenditure_scope', familyInfo.familyAnnualExpenditureScope) }}
        </Descriptions.Item>
        <Descriptions.Item label="经营业务及规模">
          {{ familyInfo.businessAndScale || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭总资产">
          {{ formatMoney(familyInfo.familyTotalAssets) }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭负债">
          {{ formatMoney(familyInfo.familyDebt) }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭负债范围">
          {{ getDict('aicrm_debt_scope', familyInfo.familyDebtScope) }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭资产信息" :span="3">
          {{ familyInfo.familyAssetsInfo || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 4: 信用信息 -->
    <a-card title="信用信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="是否信用家庭">
          {{ formatBoolean(familyInfo.isCreditFamily) }}
        </Descriptions.Item>
        <Descriptions.Item label="信用状况">
          {{ getDict('aicrm_credit_status', familyInfo.creditStatus) }}
        </Descriptions.Item>
        <Descriptions.Item label="授信金额">
          {{ formatMoney(familyInfo.creditAmount) }}
        </Descriptions.Item>
        <Descriptions.Item label="负债状况">
          {{ getDict('aicrm_debt_status', familyInfo.debtStatus) }}
        </Descriptions.Item>
        <Descriptions.Item label="家庭信息不良记录" :span="2">
          {{ familyInfo.familyAdverseRecords || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 5: 备注信息 -->
    <a-card v-if="familyInfo.remark" title="备注信息" :bordered="false">
      <Descriptions :column="1" bordered size="small">
        <Descriptions.Item label="备注">
          {{ familyInfo.remark }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>
  </div>
</template>

<style scoped>
.space-y-6 {
  display: flex;
  flex-direction: column;
  gap: 24px;
}
</style>
