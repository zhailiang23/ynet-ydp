package com.ynet.iplatform.module.task.controller.admin.task.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 批量创建任务请求 VO
 *
 * @author iplatform
 */
@Schema(description = "管理后台 - 批量创建任务请求 VO")
@Data
public class TaskBatchCreateReqVO {

    @Schema(description = "任务名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "测试任务")
    @NotBlank(message = "任务名称不能为空")
    private String taskName;

    @Schema(description = "截止时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime deadline;

    @Schema(description = "CSV 文件名", requiredMode = Schema.RequiredMode.REQUIRED, example = "test.csv")
    @NotBlank(message = "CSV 文件名不能为空")
    private String fileName;

}
