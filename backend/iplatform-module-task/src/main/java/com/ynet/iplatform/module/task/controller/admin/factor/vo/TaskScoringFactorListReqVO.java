package com.ynet.iplatform.module.task.controller.admin.factor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 任务评分因子列表 Request VO")
@Data
public class TaskScoringFactorListReqVO {

    @Schema(description = "因子名称（模糊匹配）")
    private String factorName;

    @Schema(description = "是否启用")
    private Boolean enabled;
}
