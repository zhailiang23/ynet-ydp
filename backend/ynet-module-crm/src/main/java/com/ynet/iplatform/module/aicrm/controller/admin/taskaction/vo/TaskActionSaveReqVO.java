package com.ynet.iplatform.module.aicrm.controller.admin.taskaction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 任务行动新增/修改 Request VO")
@Data
public class TaskActionSaveReqVO {

    @Schema(description = "行动ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "1")
    private Long id;

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "任务ID不能为空")
    private Long taskId;

    @Schema(description = "行动类型（CALL-打电话、SMS-发短信、EMAIL-发邮件、VISIT-拜访、MEETING-会议、OTHER-其他）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行动类型不能为空")
    private String actionType;

    @Schema(description = "行动时间（格式：yyyy-MM-dd HH:mm:ss）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行动时间不能为空")
    private String actionTime;

    @Schema(description = "行动人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "行动人ID不能为空")
    private Long actionUserId;

    @Schema(description = "行动人姓名")
    private String actionUserName;

    @Schema(description = "备注", example = "客户表示需要考虑")
    private String remark;

}
