package com.ynet.iplatform.module.task.controller.admin.factor.vo;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Schema(description = "管理后台 - 任务评分因子分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
public class TaskScoringFactorPageReqVO extends PageParam {

    @Schema(description = "因子名称（模糊匹配）")
    private String factorName;

    @Schema(description = "是否启用")
    private Boolean enabled;
}
