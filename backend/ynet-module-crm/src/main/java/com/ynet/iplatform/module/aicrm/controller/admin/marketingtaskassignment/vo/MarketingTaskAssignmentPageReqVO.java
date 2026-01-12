package com.ynet.iplatform.module.aicrm.controller.admin.marketingtaskassignment.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 营销活动任务下发分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class MarketingTaskAssignmentPageReqVO extends PageParam {

    @Schema(description = "任务名称", example = "双十一推广任务")
    private String taskName;

    @Schema(description = "关联营销活动ID", example = "1")
    private Long marketingActivityId;

    @Schema(description = "任务开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] startTime;

    @Schema(description = "任务结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] endTime;

    @Schema(description = "任务目标类型", example = "view")
    private String targetType;

    @Schema(description = "任务状态", example = "active")
    private String status;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    // 以下字段用于自定义 SQL 查询
    @Schema(hidden = true)
    private Integer offset;

    @Schema(hidden = true)
    private Long tenantId;

}
