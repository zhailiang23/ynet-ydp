package com.ynet.iplatform.module.task.controller.admin.factor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "管理后台 - 任务评分条件创建/更新 Request VO")
@Data
public class TaskScoringConditionSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "数据来源", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "数据来源不能为空")
    private String dataSource;

    @Schema(description = "字段/属性名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "字段名称不能为空")
    private String fieldName;

    @Schema(description = "操作符", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "操作符不能为空")
    private String operator;

    @Schema(description = "比较值", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "比较值不能为空")
    private String fieldValue;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    @Min(value = 0, message = "排序不能小于0")
    private Integer sort;
}
