package com.ynet.iplatform.module.task.controller.app.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "移动端 - 任务统计信息 Response VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppTaskStatsRespVO {

    @Schema(description = "今日潜在价值", requiredMode = Schema.RequiredMode.REQUIRED, example = "¥500w")
    private String totalValue;

    @Schema(description = "价值变化百分比", requiredMode = Schema.RequiredMode.REQUIRED, example = "+12%")
    private String valueChange;

    @Schema(description = "高优先级任务数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "5")
    private Integer highPriorityCount;

    @Schema(description = "已完成任务数量", requiredMode = Schema.RequiredMode.REQUIRED, example = "12")
    private Integer completedCount;

}
