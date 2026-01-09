package com.ynet.iplatform.module.task.controller.admin.factor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 任务评分条件 Response VO")
@Data
public class TaskScoringConditionRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "所属评分因子ID")
    private Long factorId;

    @Schema(description = "数据来源")
    private String dataSource;

    @Schema(description = "字段/属性名称")
    private String fieldName;

    @Schema(description = "操作符")
    private String operator;

    @Schema(description = "比较值")
    private String fieldValue;

    @Schema(description = "排序")
    private Integer sort;
}
