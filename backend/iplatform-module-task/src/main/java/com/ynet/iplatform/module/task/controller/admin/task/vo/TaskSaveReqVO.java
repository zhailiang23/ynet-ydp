package com.ynet.iplatform.module.task.controller.admin.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - AI智能任务新增/修改 Request VO")
@Data
public class TaskSaveReqVO {

    @Schema(description = "任务编号", example = "1")
    private Long id;

    @Schema(description = "任务类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "FOLLOW_UP")
    @NotBlank(message = "任务类型不能为空")
    private String taskType;

    @Schema(description = "任务标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "跟进重点客户")
    @NotBlank(message = "任务标题不能为空")
    private String title;

    @Schema(description = "任务描述", example = "需要联系客户讨论新产品方案")
    private String description;

    @Schema(description = "优先级", requiredMode = Schema.RequiredMode.REQUIRED, example = "P1")
    @NotBlank(message = "优先级不能为空")
    private String priority;

    @Schema(description = "综合评分", example = "85.5")
    private BigDecimal comprehensiveScore;

    @Schema(description = "任务分类", example = "客户跟进")
    private String category;

    @Schema(description = "关联客户ID", example = "123")
    private Long customerId;

    @Schema(description = "客户姓名", example = "张三")
    private String customerName;

    @Schema(description = "截止时间", example = "2024-12-31 18:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime deadline;

    @Schema(description = "完成时间", example = "2024-12-30 15:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime completedTime;

    @Schema(description = "业务价值", example = "HIGH")
    private String businessValue;

    @Schema(description = "预期收益", example = "50000.00")
    private BigDecimal expectedRevenue;

    @Schema(description = "任务状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    @NotNull(message = "任务状态不能为空")
    private Integer status;

    @Schema(description = "是否AI生成", example = "0")
    private Integer aiGenerated;

    @Schema(description = "AI生成原因", example = "客户近期访问频繁，建议跟进")
    private String aiReason;

    @Schema(description = "AI建议", example = "建议在本周内完成首次沟通")
    private String aiSuggestion;

}
