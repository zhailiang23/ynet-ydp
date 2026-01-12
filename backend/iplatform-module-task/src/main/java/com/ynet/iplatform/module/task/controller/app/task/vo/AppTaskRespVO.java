package com.ynet.iplatform.module.task.controller.app.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "移动端 - AI智能任务 Response VO")
@Data
public class AppTaskRespVO {

    @Schema(description = "任务编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "任务类型", example = "ASSET_UPGRADE")
    private String taskType;

    @Schema(description = "任务标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "基金持仓再平衡建议")
    private String title;

    @Schema(description = "任务描述", example = "监测到市场波动超过 2%，客户持有的 \"兴全合润\" 收益率已达预期")
    private String description;

    @Schema(description = "优先级（P0=紧急 P1=重要 P2=普通 P3=低）", requiredMode = Schema.RequiredMode.REQUIRED, example = "P0")
    private String priority;

    @Schema(description = "业务价值评分（0-10）", example = "9.8")
    private BigDecimal comprehensiveScore;

    @Schema(description = "任务分类", example = "资产提升")
    private String category;

    @Schema(description = "关联客户ID", example = "123")
    private Long customerId;

    @Schema(description = "客户姓名", example = "张先生")
    private String customerName;

    @Schema(description = "截止时间")
    private LocalDateTime deadline;

    @Schema(description = "完成时间")
    private LocalDateTime completedTime;

    @Schema(description = "业务价值", example = "HIGH")
    private String businessValue;

    @Schema(description = "预期收益（元）", example = "500000.00")
    private BigDecimal expectedRevenue;

    @Schema(description = "任务状态（0=待办 1=进行中 2=已完成 3=已取消）", requiredMode = Schema.RequiredMode.REQUIRED, example = "0")
    private Integer status;

    @Schema(description = "是否AI生成（0=否 1=是）", example = "1")
    private Integer aiGenerated;

    @Schema(description = "AI生成原因", example = "客户持仓波动超过阈值")
    private String aiReason;

    @Schema(description = "AI建议", example = "建议提示止盈或调仓")
    private String aiSuggestion;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
