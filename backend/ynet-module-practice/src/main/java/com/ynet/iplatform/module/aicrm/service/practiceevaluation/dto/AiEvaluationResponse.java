package com.ynet.iplatform.module.aicrm.service.practiceevaluation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * AI 评估响应 DTO
 *
 * @author 系统
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiEvaluationResponse {

    /**
     * 沟通逻辑得分 (0-100)
     */
    @JsonProperty("communication_score")
    private Integer communicationScore;

    /**
     * 专业能力得分 (0-100)
     */
    @JsonProperty("professionalism_score")
    private Integer professionalismScore;

    /**
     * 合规表现得分 (0-100)
     */
    @JsonProperty("compliance_score")
    private Integer complianceScore;

    /**
     * 客户经理评估反馈
     */
    @JsonProperty("manager_feedback")
    private String managerFeedback;

    /**
     * 合规问题列表
     */
    @JsonProperty("compliance_issues")
    private List<ComplianceIssue> complianceIssues;

    /**
     * 量化指标列表
     */
    @JsonProperty("quantified_metrics")
    private List<QuantifiedMetric> quantifiedMetrics;

    /**
     * 提升建议列表
     */
    @JsonProperty("improvement_suggestions")
    private List<ImprovementSuggestion> improvementSuggestions;

    /**
     * 合规问题
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ComplianceIssue {
        private String type;
        private String description;
        private String status;
    }

    /**
     * 量化指标
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class QuantifiedMetric {
        private String dimension;
        private String target;
        private String actual;
        private String deviation;
    }

    /**
     * 提升建议
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ImprovementSuggestion {
        private String text;
        private Resource resource;
    }

    /**
     * 资源链接
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Resource {
        private String label;
        private String url;
    }
}
