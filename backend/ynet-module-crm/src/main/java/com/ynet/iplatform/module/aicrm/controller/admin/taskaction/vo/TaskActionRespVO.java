package com.ynet.iplatform.module.aicrm.controller.admin.taskaction.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 任务行动 Response VO")
@Data
@ExcelIgnoreUnannotated
public class TaskActionRespVO {

    @Schema(description = "行动ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("行动ID")
    private Long id;

    @Schema(description = "任务ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("任务ID")
    private Long taskId;

    @Schema(description = "行动类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行动类型")
    private String actionType;

    @Schema(description = "行动时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("行动时间")
    private LocalDateTime actionTime;

    @Schema(description = "行动人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("行动人ID")
    private Long actionUserId;

    @Schema(description = "行动人姓名")
    @ExcelProperty("行动人姓名")
    private String actionUserName;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
