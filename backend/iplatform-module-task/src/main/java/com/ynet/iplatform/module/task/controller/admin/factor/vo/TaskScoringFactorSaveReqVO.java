package com.ynet.iplatform.module.task.controller.admin.factor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 任务评分因子创建/更新 Request VO")
@Data
public class TaskScoringFactorSaveReqVO {

    @Schema(description = "主键ID（更新时必填）")
    private Long id;

    @Schema(description = "因子名称（中文）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "因子名称不能为空")
    @Length(max = 64, message = "因子名称长度不能超过64个字符")
    private String factorName;

    @Schema(description = "因子英文名", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "因子英文名不能为空")
    @Length(max = 64, message = "因子英文名长度不能超过64个字符")
    private String factorNameEn;

    @Schema(description = "图标")
    @Length(max = 64, message = "图标名称长度不能超过64个字符")
    private String icon;

    @Schema(description = "权重(%)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "权重不能为空")
    @Min(value = 0, message = "权重不能小于0")
    @Max(value = 100, message = "权重不能大于100")
    private Integer weight;

    @Schema(description = "因子说明")
    @Length(max = 500, message = "因子说明长度不能超过500个字符")
    private String description;

    @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否启用不能为空")
    private Boolean enabled;

    @Schema(description = "条件逻辑关系: AND/OR", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "条件逻辑关系不能为空")
    private String logicType;

    @Schema(description = "影响方式: direct-直接设定评分, weight-调整评分权重, score-评分值加减", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "影响方式不能为空")
    private String impactType;

    @Schema(description = "增加/减少分值 (-100到100)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分值调整不能为空")
    @Min(value = -100, message = "分值调整不能小于-100")
    @Max(value = 100, message = "分值调整不能大于100")
    private Integer scoreAdjustment;

    @Schema(description = "排序", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空")
    @Min(value = 0, message = "排序不能小于0")
    private Integer sort;

    @Schema(description = "条件列表")
    private List<TaskScoringConditionSaveReqVO> conditions;
}
