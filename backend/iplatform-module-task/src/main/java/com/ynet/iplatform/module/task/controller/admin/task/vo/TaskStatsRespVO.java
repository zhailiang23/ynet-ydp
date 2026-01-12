package com.ynet.iplatform.module.task.controller.admin.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 任务统计信息 Response VO
 *
 * @author iplatform
 */
@Schema(description = "管理后台 - 任务统计信息 Response VO")
@Data
public class TaskStatsRespVO {

    @Schema(description = "今日潜在价值", example = "¥125,000")
    private String totalValue;

    @Schema(description = "价值变化百分比", example = "+12.5%")
    private String valueChange;

    @Schema(description = "高优先级任务数量", example = "8")
    private Integer highPriorityCount;

    @Schema(description = "已完成任务数量", example = "5")
    private Integer completedCount;

}
