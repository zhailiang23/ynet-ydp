package com.ynet.iplatform.module.aicrm.controller.admin.taskaction.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 任务行动分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TaskActionPageReqVO extends PageParam {

    @Schema(description = "任务ID", example = "1")
    private Long taskId;

    @Schema(description = "行动类型（CALL-打电话、SMS-发短信、EMAIL-发邮件、VISIT-拜访、MEETING-会议、OTHER-其他）")
    private String actionType;

    @Schema(description = "行动人ID", example = "1")
    private Long actionUserId;

    @Schema(description = "行动时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] actionTime;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
