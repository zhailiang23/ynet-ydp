package com.ynet.iplatform.module.task.controller.admin.factor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 任务评分因子 Response VO")
@Data
public class TaskScoringFactorRespVO {

    @Schema(description = "主键ID")
    private Long id;

    @Schema(description = "因子名称（中文）")
    private String factorName;

    @Schema(description = "因子英文名")
    private String factorNameEn;

    @Schema(description = "图标")
    private String icon;

    @Schema(description = "权重(%)")
    private Integer weight;

    @Schema(description = "因子说明")
    private String description;

    @Schema(description = "是否启用")
    private Boolean enabled;

    @Schema(description = "条件逻辑关系: AND/OR")
    private String logicType;

    @Schema(description = "影响方式: direct-直接设定评分, weight-调整评分权重, score-评分值加减")
    private String impactType;

    @Schema(description = "增加/减少分值 (-100到100)")
    private Integer scoreAdjustment;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "条件列表")
    private List<TaskScoringConditionRespVO> conditions;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}
