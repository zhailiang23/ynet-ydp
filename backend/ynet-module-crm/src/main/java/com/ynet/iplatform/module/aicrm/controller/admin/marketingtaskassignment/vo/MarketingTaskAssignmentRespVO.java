package com.ynet.iplatform.module.aicrm.controller.admin.marketingtaskassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 营销活动任务下发 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MarketingTaskAssignmentRespVO {

    @Schema(description = "任务下发主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("任务下发主键")
    private Long id;

    @Schema(description = "任务名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "双十一推广任务")
    @ExcelProperty("任务名称")
    private String taskName;

    @Schema(description = "关联营销活动ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("关联营销活动ID")
    private Long marketingActivityId;

    @Schema(description = "关联营销活动名称", example = "双十一大促活动")
    @ExcelProperty("关联营销活动名称")
    private String marketingActivityName;

    @Schema(description = "任务开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务开始时间")
    private LocalDateTime startTime;

    @Schema(description = "任务结束时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("任务结束时间")
    private LocalDateTime endTime;

    @Schema(description = "任务目标类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "view")
    @ExcelProperty("任务目标类型")
    private String targetType;

    @Schema(description = "目标值", requiredMode = Schema.RequiredMode.REQUIRED, example = "1000")
    @ExcelProperty("目标值")
    private Integer targetValue;

    @Schema(description = "任务话术")
    @ExcelProperty("任务话术")
    private String taskScript;

    @Schema(description = "推广海报URL", example = "https://example.com/poster.jpg")
    @ExcelProperty("推广海报URL")
    private String posterUrl;

    @Schema(description = "任务派发对象（用户ID集合）", requiredMode = Schema.RequiredMode.REQUIRED)
    private Set<Long> assignedUserIds;

    @Schema(description = "派发人数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @ExcelProperty("派发人数")
    private Integer assignedUserCount;

    @Schema(description = "任务状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "active")
    @ExcelProperty("任务状态")
    private String status;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
