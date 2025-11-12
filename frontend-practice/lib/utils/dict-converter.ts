import { getDictLabels } from "../api/dict"
import type { VirtualCustomer } from "../types/course"

/**
 * 虚拟客户字典类型映射
 */
const VIRTUAL_CUSTOMER_DICT_TYPES = {
  gender: "aicrm_gender", // 性别字典
  occupation: "aicrm_occupation", // 职业字典
  personalityType: "aicrm_personality_type", // 性格类型字典
  industry: "aicrm_industry", // 行业字典
  riskPreference: "aicrm_risk_preference", // 风险偏好字典
}

/**
 * 带字典标签的虚拟客户
 */
export interface VirtualCustomerWithLabels extends VirtualCustomer {
  genderLabel?: string
  occupationLabel?: string
  personalityTypeLabel?: string
  industryLabel?: string
  riskPreferenceLabel?: string
}

/**
 * 转换虚拟客户字典值为标签
 */
export async function convertVirtualCustomerDict(
  customer: VirtualCustomer,
): Promise<VirtualCustomerWithLabels> {
  // 收集需要转换的字段和值
  const dictTypes = new Set<string>()
  const valuesByType: { [dictType: string]: string[] } = {}

  // 性别
  if (customer.gender) {
    const dictType = VIRTUAL_CUSTOMER_DICT_TYPES.gender
    dictTypes.add(dictType)
    if (!valuesByType[dictType]) valuesByType[dictType] = []
    valuesByType[dictType].push(customer.gender)
  }

  // 职业
  if (customer.occupation) {
    const dictType = VIRTUAL_CUSTOMER_DICT_TYPES.occupation
    dictTypes.add(dictType)
    if (!valuesByType[dictType]) valuesByType[dictType] = []
    valuesByType[dictType].push(customer.occupation)
  }

  // 性格类型
  if (customer.personalityType) {
    const dictType = VIRTUAL_CUSTOMER_DICT_TYPES.personalityType
    dictTypes.add(dictType)
    if (!valuesByType[dictType]) valuesByType[dictType] = []
    valuesByType[dictType].push(customer.personalityType)
  }

  // 行业
  if (customer.industry) {
    const dictType = VIRTUAL_CUSTOMER_DICT_TYPES.industry
    dictTypes.add(dictType)
    if (!valuesByType[dictType]) valuesByType[dictType] = []
    valuesByType[dictType].push(customer.industry)
  }

  // 风险偏好
  if (customer.riskPreference) {
    const dictType = VIRTUAL_CUSTOMER_DICT_TYPES.riskPreference
    dictTypes.add(dictType)
    if (!valuesByType[dictType]) valuesByType[dictType] = []
    valuesByType[dictType].push(customer.riskPreference)
  }

  // 批量获取字典标签
  const labelsByType: { [dictType: string]: { [value: string]: string } } = {}
  await Promise.all(
    Array.from(dictTypes).map(async (dictType) => {
      labelsByType[dictType] = await getDictLabels(dictType, valuesByType[dictType])
    }),
  )

  // 构造结果
  return {
    ...customer,
    genderLabel: customer.gender
      ? labelsByType[VIRTUAL_CUSTOMER_DICT_TYPES.gender]?.[customer.gender]
      : undefined,
    occupationLabel: customer.occupation
      ? labelsByType[VIRTUAL_CUSTOMER_DICT_TYPES.occupation]?.[customer.occupation]
      : undefined,
    personalityTypeLabel: customer.personalityType
      ? labelsByType[VIRTUAL_CUSTOMER_DICT_TYPES.personalityType]?.[customer.personalityType]
      : undefined,
    industryLabel: customer.industry
      ? labelsByType[VIRTUAL_CUSTOMER_DICT_TYPES.industry]?.[customer.industry]
      : undefined,
    riskPreferenceLabel: customer.riskPreference
      ? labelsByType[VIRTUAL_CUSTOMER_DICT_TYPES.riskPreference]?.[customer.riskPreference]
      : undefined,
  }
}

/**
 * 批量转换虚拟客户字典值
 */
export async function convertVirtualCustomerDictBatch(
  customers: VirtualCustomer[],
): Promise<VirtualCustomerWithLabels[]> {
  // 收集所有需要转换的字段
  const allValues = {
    gender: new Set<string>(),
    occupation: new Set<string>(),
    personalityType: new Set<string>(),
    industry: new Set<string>(),
    riskPreference: new Set<string>(),
  }

  customers.forEach((customer) => {
    if (customer.gender) allValues.gender.add(customer.gender)
    if (customer.occupation) allValues.occupation.add(customer.occupation)
    if (customer.personalityType) allValues.personalityType.add(customer.personalityType)
    if (customer.industry) allValues.industry.add(customer.industry)
    if (customer.riskPreference) allValues.riskPreference.add(customer.riskPreference)
  })

  // 批量获取字典标签
  const [genderLabels, occupationLabels, personalityLabels, industryLabels, riskLabels] =
    await Promise.all([
      allValues.gender.size > 0
        ? getDictLabels(VIRTUAL_CUSTOMER_DICT_TYPES.gender, Array.from(allValues.gender))
        : Promise.resolve({}),
      allValues.occupation.size > 0
        ? getDictLabels(VIRTUAL_CUSTOMER_DICT_TYPES.occupation, Array.from(allValues.occupation))
        : Promise.resolve({}),
      allValues.personalityType.size > 0
        ? getDictLabels(
            VIRTUAL_CUSTOMER_DICT_TYPES.personalityType,
            Array.from(allValues.personalityType),
          )
        : Promise.resolve({}),
      allValues.industry.size > 0
        ? getDictLabels(VIRTUAL_CUSTOMER_DICT_TYPES.industry, Array.from(allValues.industry))
        : Promise.resolve({}),
      allValues.riskPreference.size > 0
        ? getDictLabels(
            VIRTUAL_CUSTOMER_DICT_TYPES.riskPreference,
            Array.from(allValues.riskPreference),
          )
        : Promise.resolve({}),
    ])

  // 为每个客户添加标签
  return customers.map((customer) => ({
    ...customer,
    genderLabel: customer.gender ? genderLabels[customer.gender] : undefined,
    occupationLabel: customer.occupation ? occupationLabels[customer.occupation] : undefined,
    personalityTypeLabel: customer.personalityType
      ? personalityLabels[customer.personalityType]
      : undefined,
    industryLabel: customer.industry ? industryLabels[customer.industry] : undefined,
    riskPreferenceLabel: customer.riskPreference
      ? riskLabels[customer.riskPreference]
      : undefined,
  }))
}
