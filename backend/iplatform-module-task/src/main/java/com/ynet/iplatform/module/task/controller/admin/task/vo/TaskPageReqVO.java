package com.ynet.iplatform.module.task.controller.admin.task.vo;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - AI智能任务分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskPageReqVO extends PageParam {

    @Schema(description = "任务标题", example = "跟进客户")
    private String title;

    @Schema(description = "任务类型", example = "FOLLOW_UP")
    private String taskType;

    @Schema(description = "优先级", example = "P1")
    private String priority;

    @Schema(description = "任务状态", example = "0")
    private Integer status;

    @Schema(description = "任务分类", example = "客户跟进")
    private String category;

    @Schema(description = "业务价值", example = "HIGH")
    private String businessValue;

    @Schema(description = "关联客户ID", example = "123")
    private Long customerId;

    @Schema(description = "截止时间开始", example = "2024-12-01 00:00:00")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime deadlineStart;

    @Schema(description = "截止时间结束", example = "2024-12-31 23:59:59")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime deadlineEnd;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "任务负责人ID", example = "1")
    private Long responsibleUserId;

}
