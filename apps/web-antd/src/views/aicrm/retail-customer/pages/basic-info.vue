<script lang="ts" setup>
import type { RetailCustomerApi } from '#/api/aicrm/retail-customer';

import { computed } from 'vue';

import { getDictLabel } from '@vben/hooks';

import { Descriptions } from 'ant-design-vue';

import { DictTag } from '#/components/dict-tag';

const props = defineProps<{
  customer: RetailCustomerApi.RetailCustomer;
}>();

// 获取字典标签的包装函数
function getDict(dictType: string, value: any) {
  if (value === null || value === undefined) return '-';
  return getDictLabel(dictType, value) || value;
}

// 格式化布尔值
function formatBoolean(value?: boolean) {
  if (value === null || value === undefined) return '-';
  return value ? '是' : '否';
}

// 格式化日期
function formatDate(value?: string) {
  if (!value) return '-';
  return new Date(value).toLocaleDateString('zh-CN');
}

// 格式化日期时间
function formatDateTime(value?: string) {
  if (!value) return '-';
  return new Date(value).toLocaleString('zh-CN');
}

// 格式化金额
function formatMoney(value?: number) {
  if (value === null || value === undefined) return '-';
  return `¥${value.toLocaleString('zh-CN')}`;
}
</script>

<template>
  <div class="space-y-6">
    <!-- 卡片 1: 客户共有信息 -->
    <a-card title="客户关键信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="客户编号">
          {{ customer.customerNo || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="客户类型">
          <DictTag type="aicrm_customer_type" :value="customer.customerType" />
        </Descriptions.Item>
        <Descriptions.Item label="客户名称">
          {{ customer.customerName || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="客户等级">
          <DictTag type="aicrm_customer_level" :value="customer.customerLevel" />
        </Descriptions.Item>
        <Descriptions.Item label="客户状态">
          <DictTag type="aicrm_customer_status" :value="customer.customerStatus" />
        </Descriptions.Item>
        <Descriptions.Item label="优质客户">
          {{ formatBoolean(customer.isHighQuality) }}
        </Descriptions.Item>
        <Descriptions.Item label="重要客户">
          {{ formatBoolean(customer.isImportant) }}
        </Descriptions.Item>
        <Descriptions.Item label="信用状态">
          {{ customer.creditStatus || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="信用等级">
          <DictTag type="aicrm_credit_level" :value="customer.creditLevel" />
        </Descriptions.Item>
        <Descriptions.Item label="信用评分">
          {{ customer.creditScore || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="客户来源" :span="2">
          <DictTag type="aicrm_customer_source" :value="customer.customerSource" />
        </Descriptions.Item>
        <Descriptions.Item label="客户标签" :span="3">
          {{ customer.customerTag || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="备注信息" :span="3">
          {{ customer.remark || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 2: 个人基本信息 -->
    <a-card title="个人基本信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="昵称/别名">
          {{ customer.nickname || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="曾用名">
          {{ customer.usedName || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="姓氏">
          {{ customer.surname || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="拼音全名">
          {{ customer.pinyinName || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="拼音缩写">
          {{ customer.pinyinAbbr || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="人员称谓">
          <DictTag type="crm_person_title" :value="customer.personTitle" />
        </Descriptions.Item>
        <Descriptions.Item label="性别">
          {{
            customer.gender === 1
              ? '男'
              : customer.gender === 2
                ? '女'
                : customer.gender === 3
                  ? '其他'
                  : '-'
          }}
        </Descriptions.Item>
        <Descriptions.Item label="出生日期">
          {{ formatDate(customer.birthday) }}
        </Descriptions.Item>
        <Descriptions.Item label="出生地">
          {{ customer.birthLocale || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="年龄">
          {{ customer.age || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="年龄段">
          {{ customer.ageRange || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="国籍">
          {{ customer.nationality || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="公民身份">
          {{ customer.citizenship || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="民族">
          {{ customer.nation || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="籍贯">
          {{ customer.nativePlace || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="户籍类型">
          {{ customer.residenceType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="居住时长类型">
          <DictTag type="crm_residence_duration_type" :value="customer.residenceDurationType" />
        </Descriptions.Item>
        <Descriptions.Item label="户口所在地" :span="2">
          {{ customer.domicilePlace || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="户口类型">
          <DictTag type="crm_household_type" :value="customer.householdType" />
        </Descriptions.Item>
        <Descriptions.Item label="婚姻状况">
          {{ customer.maritalStatus || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="健康状况">
          <DictTag type="crm_health_status" :value="customer.healthStatus" />
        </Descriptions.Item>
        <Descriptions.Item label="宗教信仰">
          <DictTag type="crm_religion" :value="customer.religion" />
        </Descriptions.Item>
        <Descriptions.Item label="政治面貌" :span="2">
          <DictTag type="crm_political_status" :value="customer.politicalStatus" />
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 3: 证件信息 -->
    <a-card title="证件信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="证件类型">
          {{ customer.idCardType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="证件号码" :span="2">
          {{ customer.idCardNo || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 4: 教育信息 -->
    <a-card title="教育信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="学历">
          {{ customer.education || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="学位">
          {{ customer.degree || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="毕业学校">
          {{ customer.graduateSchool || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="专业">
          {{ customer.major || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="毕业日期" :span="2">
          {{ formatDate(customer.graduationDate) }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 5: 工作信息 -->
    <a-card title="工作信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="职业">
          {{ customer.occupation || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="职业类型">
          {{ customer.occupationType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="职业状态">
          <DictTag type="crm_career_status" :value="customer.careerStatus" />
        </Descriptions.Item>
        <Descriptions.Item label="职业开始日期">
          {{ formatDate(customer.careerStartDate) }}
        </Descriptions.Item>
        <Descriptions.Item label="工作单位">
          {{ customer.employerName || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="单位性质">
          <DictTag type="crm_employer_type" :value="customer.employerType" />
        </Descriptions.Item>
        <Descriptions.Item label="职位">
          {{ customer.position || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="技术职称">
          {{ customer.technicalTitle || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="年收入">
          {{ formatMoney(customer.annualIncome) }}
        </Descriptions.Item>
        <Descriptions.Item label="年收入范围">
          {{ customer.annualIncomeRange || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="月收入">
          {{ formatMoney(customer.monthlyIncome) }}
        </Descriptions.Item>
        <Descriptions.Item label="收入来源">
          {{ customer.sourceOfIncome || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="纳税情况">
          {{ customer.taxPayment || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="工资账户银行" :span="2">
          {{ customer.salaryAccountBank || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="简历" :span="3">
          {{ customer.resume || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 5: VIP信息 -->
    <a-card title="VIP信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="是否VIP">
          {{ formatBoolean(customer.isVip) }}
        </Descriptions.Item>
        <Descriptions.Item label="VIP等级">
          {{ customer.vipLevel || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="VIP积分">
          {{ customer.vipPoints || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="VIP开始日期">
          {{ formatDate(customer.vipStartDate) }}
        </Descriptions.Item>
        <Descriptions.Item label="VIP到期日期" :span="2">
          {{ formatDate(customer.vipEndDate) }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 7: 资产与投资信息 -->
    <a-card title="资产与投资信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="资产总额">
          {{ formatMoney(customer.assets) }}
        </Descriptions.Item>
        <Descriptions.Item label="负债总额">
          {{ formatMoney(customer.liabilities) }}
        </Descriptions.Item>
        <Descriptions.Item label="基金持有">
          {{ formatMoney(customer.fundHoldings) }}
        </Descriptions.Item>
        <Descriptions.Item label="总投资">
          {{ formatMoney(customer.totalInvestment) }}
        </Descriptions.Item>
        <Descriptions.Item label="投资性质">
          <DictTag type="crm_investment_nature" :value="customer.investmentNature" />
        </Descriptions.Item>
        <Descriptions.Item label="持股金额">
          {{ formatMoney(customer.stockHoldings) }}
        </Descriptions.Item>
        <Descriptions.Item label="有房产">
          {{ formatBoolean(customer.hasHouse) }}
        </Descriptions.Item>
        <Descriptions.Item label="有车">
          {{ formatBoolean(customer.hasCar) }}
        </Descriptions.Item>
        <Descriptions.Item label="有保险">
          {{ formatBoolean(customer.hasInsurance) }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 8: 信用信息 -->
    <a-card title="信用信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="有贷款记录">
          {{ formatBoolean(customer.hasLoanRecord) }}
        </Descriptions.Item>
        <Descriptions.Item label="有逾期记录">
          {{ formatBoolean(customer.hasOverdueRecord) }}
        </Descriptions.Item>
        <Descriptions.Item label="黑名单标志">
          {{ formatBoolean(customer.blacklistFlag) }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 9: 客户关系信息 -->
    <a-card title="客户关系信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="零售客户类型">
          {{ customer.retailCustomerType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="个人客户类型">
          {{ customer.personCustomerType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="个体类型">
          {{ customer.individualType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="个人行为评价">
          {{ customer.personConductEval || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="客户银行关系">
          {{ customer.customerBankRelation || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="公司关联度">
          {{ customer.companyRelationDegree || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 10: 登记信息 -->
    <a-card title="登记信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="登记类型">
          <DictTag type="crm_registration_type" :value="customer.registrationType" />
        </Descriptions.Item>
        <Descriptions.Item label="登记号" :span="2">
          {{ customer.registrationNo || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="开始日期">
          {{ formatDate(customer.startDate) }}
        </Descriptions.Item>
        <Descriptions.Item label="登记开始日期">
          {{ formatDate(customer.registrationStartDate) }}
        </Descriptions.Item>
        <Descriptions.Item label="登记结束日期">
          {{ formatDate(customer.registrationEndDate) }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 11: 系统信息 -->
    <a-card title="系统信息" :bordered="false">
      <Descriptions :column="2" bordered size="small">
        <Descriptions.Item label="创建时间">
          {{ formatDateTime(customer.createTime) }}
        </Descriptions.Item>
        <Descriptions.Item label="更新时间">
          {{ formatDateTime(customer.updateTime) }}
        </Descriptions.Item>
        <Descriptions.Item label="ETL导入日期">
          {{ formatDate(customer.etlDate) }}
        </Descriptions.Item>
        <Descriptions.Item label="老系统交易序列号">
          {{ customer.oldTxSeqNo || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="老系统客户ID">
          {{ customer.oldCustId || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="老系统最后更新系统">
          {{ customer.oldLastUpdateSys || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>
  </div>
</template>

<style scoped>
.space-y-6 > * + * {
  margin-top: 1.5rem;
}
</style>
