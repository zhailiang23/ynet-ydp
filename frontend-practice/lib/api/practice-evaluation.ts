import { httpClient } from "./request"

export interface ComplianceIssue {
  type: string
  description: string
  status: string
}

export interface QuantifiedMetric {
  dimension: string
  target: string
  actual: string
  deviation: string
}

export interface Resource {
  label: string
  url: string
}

export interface ImprovementSuggestion {
  text: string
  resource: Resource
}

export interface PracticeEvaluation {
  communicationScore: number
  professionalismScore: number
  complianceScore: number
  managerFeedback: string
  complianceIssues: ComplianceIssue[]
  quantifiedMetrics: QuantifiedMetric[]
  improvementSuggestions: ImprovementSuggestion[]
}

/**
 * 评估练习结果
 * @param recordId 练习记录ID
 */
export async function evaluatePractice(recordId: number): Promise<PracticeEvaluation> {
  return await httpClient.get<PracticeEvaluation>(`/aicrm/practice-user-record/evaluate?recordId=${recordId}`)
}
