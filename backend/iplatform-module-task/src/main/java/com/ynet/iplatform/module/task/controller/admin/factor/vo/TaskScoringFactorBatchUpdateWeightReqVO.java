package com.ynet.iplatform.module.task.controller.admin.factor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "管理后台 - 任务评分因子批量更新权重 Request VO")
@Data
public class TaskScoringFactorBatchUpdateWeightReqVO {

    @Schema(description = "因子权重列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "因子权重列表不能为空")
    private List<FactorWeightItem> factors;

    @Schema(description = "因子权重项")
    @Data
    public static class FactorWeightItem {

        @Schema(description = "因子ID", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "因子ID不能为空")
        private Long id;

        @Schema(description = "是否启用", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "是否启用不能为空")
        private Boolean enabled;

        @Schema(description = "权重(%)", requiredMode = Schema.RequiredMode.REQUIRED)
        @NotNull(message = "权重不能为空")
        @Min(value = 0, message = "权重不能小于0")
        @Max(value = 100, message = "权重不能大于100")
        private Integer weight;
    }
}
