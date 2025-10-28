<script lang="ts" setup>
import type { CompanyCustomerApi } from '#/api/aicrm/company-customer';

import { getDictLabel } from '@vben/hooks';

import { Descriptions } from 'ant-design-vue';

const props = defineProps<{
  customer: CompanyCustomerApi.CompanyCustomer;
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
          {{ getDict('aicrm_customer_type', customer.customerType) }}
        </Descriptions.Item>
        <Descriptions.Item label="客户名称">
          {{ customer.customerName || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="客户等级">
          {{ getDict('aicrm_customer_level', customer.customerLevel) }}
        </Descriptions.Item>
        <Descriptions.Item label="客户状态">
          {{ getDict('aicrm_customer_status', customer.customerStatus) }}
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
          {{ getDict('aicrm_credit_level', customer.creditLevel) }}
        </Descriptions.Item>
        <Descriptions.Item label="信用评分">
          {{ customer.creditScore || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="客户来源" :span="2">
          {{ getDict('aicrm_customer_source', customer.customerSource) }}
        </Descriptions.Item>
        <Descriptions.Item label="客户标签" :span="3">
          {{ customer.customerTag || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="备注信息" :span="3">
          {{ customer.remark || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 2: 证照信息 -->
    <a-card title="证照信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="证照类型">
          {{ customer.licenseType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="统一社会信用代码">
          {{ customer.creditCode || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="证照编号">
          {{ customer.licenseNo || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="组织机构代码">
          {{ customer.organizationCode || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="税务登记号">
          {{ customer.taxNo || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="贷款卡编号">
          {{ customer.loanCardNo || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 3: 企业基本信息 -->
    <a-card title="企业基本信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="企业类型">
          {{ customer.enterpriseType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="企业性质">
          {{ customer.enterpriseNature || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="所有制类型">
          {{ customer.ownershipType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="经济类型">
          {{ customer.economicType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="企业规模">
          {{ customer.enterpriseScale || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="注册资本">
          {{ formatMoney(customer.registeredCapital) }}
        </Descriptions.Item>
        <Descriptions.Item label="注册资本币种">
          {{ customer.registeredCapitalCurrency || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="成立日期">
          {{ formatDate(customer.establishDate) }}
        </Descriptions.Item>
        <Descriptions.Item label="营业期限">
          {{ customer.businessTerm || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="组织形式">
          {{ getDict('crm_org_form', customer.orgForm) }}
        </Descriptions.Item>
        <Descriptions.Item label="治理结构">
          {{ getDict('crm_governance_structure', customer.governanceStructure) }}
        </Descriptions.Item>
        <Descriptions.Item label="控股类型">
          {{ getDict('crm_holding_type', customer.holdingType) }}
        </Descriptions.Item>
        <Descriptions.Item label="企业归属">
          {{ customer.enterpriseBelong || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="上级部门">
          {{ customer.superiorDept || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="公司机构">
          {{ customer.companyOrganization || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="国家代码">
          {{ customer.nationCode || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 4: 行业分类 -->
    <a-card title="行业分类" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="行业一级分类">
          {{ customer.industryCategoryL1 || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="行业二级分类">
          {{ customer.industryCategoryL2 || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="行业三级分类">
          {{ customer.industryCategoryL3 || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="行业四级分类">
          {{ customer.industryCategoryL4 || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="行业代码" :span="2">
          {{ customer.industryCode || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 4.5: 业务经营信息 -->
    <a-card title="业务经营信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="经营模式">
          {{ getDict('crm_business_mode', customer.businessMode) }}
        </Descriptions.Item>
        <Descriptions.Item label="营业开始日期">
          {{ formatDate(customer.businessStartDate) }}
        </Descriptions.Item>
        <Descriptions.Item label="行业特征">
          {{ getDict('crm_industry_char', customer.industryCharacter) }}
        </Descriptions.Item>
        <Descriptions.Item label="行业发展前景">
          {{ getDict('crm_industry_prospect', customer.industryDevelopmentProspect) }}
        </Descriptions.Item>
        <Descriptions.Item label="地区代码" :span="2">
          {{ customer.areaCode || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="行业地位" :span="3">
          {{ customer.industryPosition || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="主营业务" :span="3">
          {{ customer.mainBusiness || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="辅营业务" :span="3">
          {{ customer.minorBusiness || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 5: 企业特征 -->
    <a-card title="企业特征" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="是否上市">
          {{ formatBoolean(customer.isListed) }}
        </Descriptions.Item>
        <Descriptions.Item label="是否小微企业">
          {{ formatBoolean(customer.isSmallEnterprise) }}
        </Descriptions.Item>
        <Descriptions.Item label="是否集团客户">
          {{ formatBoolean(customer.isGroupCustomer) }}
        </Descriptions.Item>
        <Descriptions.Item label="是否进出口企业">
          {{ formatBoolean(customer.isImportExport) }}
        </Descriptions.Item>
        <Descriptions.Item label="是否关联方">
          {{ formatBoolean(customer.isRelatedParty) }}
        </Descriptions.Item>
        <Descriptions.Item label="是否签约网银">
          {{ formatBoolean(customer.isEbankSigned) }}
        </Descriptions.Item>
        <Descriptions.Item label="是否涉农企业">
          {{ formatBoolean(customer.isAgricultureRelated) }}
        </Descriptions.Item>
        <Descriptions.Item label="员工规模">
          {{ getDict('crm_employee_scale', customer.employeeScale) }}
        </Descriptions.Item>
        <Descriptions.Item label="资产规模">
          {{ customer.assetsScale || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="生产能力">
          {{ getDict('crm_production_capacity', customer.productionCapacity) }}
        </Descriptions.Item>
        <Descriptions.Item label="企业性质">
          {{ customer.enterpriseProperty || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="是否股东">
          {{ formatBoolean(customer.isStockHolder) }}
        </Descriptions.Item>
        <Descriptions.Item label="持股金额">
          {{ formatMoney(customer.holdStockAmt) }}
        </Descriptions.Item>
        <Descriptions.Item label="投资类型">
          {{ getDict('crm_investment_type', customer.investmentType) }}
        </Descriptions.Item>
        <Descriptions.Item label="人行企业规模">
          {{ customer.enterpriseScalePboc || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="存款企业规模">
          {{ customer.enterpriseScaleDeposit || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 6: 银行账户信息 -->
    <a-card title="银行账户信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="基本账户开户行" :span="2">
          {{ customer.basicAccountBank || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="基本账户账号">
          {{ customer.basicAccountNo || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 7: 法人信息 -->
    <a-card title="法人信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="法人姓名">
          {{ customer.legalPersonName || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="法人证件类型">
          {{ customer.legalPersonIdType || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="法人证件号码">
          {{ customer.legalPersonIdNo || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="法人联系电话" :span="3">
          {{ customer.legalPersonPhone || '-' }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 7.5: 财务信息 -->
    <a-card title="财务信息" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="资产总额">
          {{ formatMoney(customer.totalAssets) }}
        </Descriptions.Item>
        <Descriptions.Item label="负债总额">
          {{ formatMoney(customer.totalDebt) }}
        </Descriptions.Item>
        <Descriptions.Item label="年收入">
          {{ formatMoney(customer.annualIncome) }}
        </Descriptions.Item>
        <Descriptions.Item label="年利润">
          {{ formatMoney(customer.annualProfit) }}
        </Descriptions.Item>
        <Descriptions.Item label="财务报表类型" :span="2">
          {{ getDict('crm_fin_report_type', customer.finReportType) }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 8: 资质与评级 -->
    <a-card title="资质与评级" :bordered="false">
      <Descriptions :column="3" bordered size="small">
        <Descriptions.Item label="主管部门">
          {{ customer.managementDept || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="监管部门">
          {{ customer.superviseDept || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="公司评级">
          {{ customer.companyRating || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="评级机构">
          {{ customer.ratingAgency || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="评级日期">
          {{ formatDate(customer.ratingDate) }}
        </Descriptions.Item>
        <Descriptions.Item label="企业资质">
          {{ customer.enterpriseQualification || '-' }}
        </Descriptions.Item>
        <Descriptions.Item label="贷款卡标志">
          {{ formatBoolean(customer.loanCardFlag) }}
        </Descriptions.Item>
        <Descriptions.Item label="贷款卡状态">
          {{ getDict('crm_loan_card_status', customer.loanCardStatus) }}
        </Descriptions.Item>
        <Descriptions.Item label="贷款卡审核日期">
          {{ formatDate(customer.loanCardAuditDate) }}
        </Descriptions.Item>
      </Descriptions>
    </a-card>

    <!-- 卡片 9: 系统信息 -->
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
