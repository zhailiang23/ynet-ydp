package com.ynet.iplatform.module.aicrm.controller.admin.practiceuserrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 练习评估结果 Response VO")
@Data
public class PracticeEvaluationRespVO {

    @Schema(description = "沟通逻辑得分 (0-100)", example = "64")
    private Integer communicationScore;

    @Schema(description = "专业能力得分 (0-100)", example = "79")
    private Integer professionalismScore;

    @Schema(description = "合规表现得分 (0-100)", example = "69")
    private Integer complianceScore;

    @Schema(description = "客户经理评估", example = "本次个性化培训表现中规中矩,建议针对薄弱环节加强练习。")
    private String managerFeedback;

    @Schema(description = "合规校验结果")
    private List<ComplianceIssue> complianceIssues;

    @Schema(description = "量化指标")
    private List<QuantifiedMetric> quantifiedMetrics;

    @Schema(description = "提升建议")
    private List<ImprovementSuggestion> improvementSuggestions;

    @Schema(description = "合规问题")
    @Data
    public static class ComplianceIssue {
        @Schema(description = "问题类型", example = "合规通过")
        private String type;

        @Schema(description = "问题描述", example = "本次演练合规表现良好。")
        private String description;

        @Schema(description = "状态", example = "通过")
        private String status; // "通过" 或 "需校准"
    }

    @Schema(description = "量化指标")
    @Data
    public static class QuantifiedMetric {
        @Schema(description = "评估维度", example = "自定义指标1")
        private String dimension;

        @Schema(description = "达标值", example = "≥70%")
        private String target;

        @Schema(description = "实测值", example = "65%")
        private String actual;

        @Schema(description = "偏差分析", example = "未达标")
        private String deviation;
    }

    @Schema(description = "提升建议")
    @Data
    public static class ImprovementSuggestion {
        @Schema(description = "建议文本", example = "根据个性化需求加强练习。")
        private String text;

        @Schema(description = "资源链接")
        private Resource resource;
    }

    @Schema(description = "学习资源")
    @Data
    public static class Resource {
        @Schema(description = "资源标签", example = "个性化学习路径")
        private String label;

        @Schema(description = "资源链接", example = "#")
        private String url;
    }
}
