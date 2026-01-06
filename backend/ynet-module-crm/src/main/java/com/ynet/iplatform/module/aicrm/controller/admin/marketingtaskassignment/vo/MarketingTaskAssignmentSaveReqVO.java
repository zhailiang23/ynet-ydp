package com.ynet.iplatform.module.aicrm.controller.admin.marketingtaskassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "管理后台 - 营销活动任务下发新增/修改 Request VO")
@Data
public class MarketingTaskAssignmentSaveReqVO {

    @Schema(description = "任务下发主键", example = "1")
    private Long id;

    @Schema(description = "任务名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "双十一推广任务")
    @NotEmpty(message = "任务名称不能为空")
    private String taskName;

    @Schema(description = "关联营销活动ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "关联营销活动ID不能为空")
    private Long marketingActivityId;

    @Schema(description = "任务开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务开始时间不能为空")
    private LocalDateTime startTime;

    @Schema(description = "任务结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "任务结束时间不能为空")
    private LocalDateTime endTime;

    @Schema(description = "任务目标类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "view")
    @NotEmpty(message = "任务目标类型不能为空")
    private String targetType;

    @Schema(description = "目标值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    @NotNull(message = "目标值不能为空")
    @Min(value = 1, message = "目标值必须大于0")
    private Integer targetValue;

    @Schema(description = "任务话术")
    private String taskScript;

    @Schema(description = "推广海报URL", example = "https://example.com/poster.jpg")
    private String posterUrl;

    @Schema(description = "任务派发对象（用户ID集合）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "任务派发对象不能为空")
    private Set<Long> assignedUserIds;

    @Schema(description = "任务状态", example = "active")
    private String status;

    @Schema(description = "备注")
    private String remark;

}
