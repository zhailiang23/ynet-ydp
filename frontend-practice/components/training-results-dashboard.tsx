import { ParticipatedCourses } from "./participated-courses"
import { EvaluationSection } from "./evaluation-section"
import { ComplianceFeedback } from "./compliance-feedback"
import { ImprovementPlan } from "./improvement-plan"
import { GrowthTrajectory } from "./growth-trajectory"

export function TrainingResultsDashboard() {
  return (
    <div className="space-y-8">
      <h1 className="text-3xl font-bold text-gray-200 text-center">我的培训成果</h1>

      <ParticipatedCourses />
      <EvaluationSection />
      <ComplianceFeedback />
      <ImprovementPlan />
      <GrowthTrajectory />
    </div>
  )
}
