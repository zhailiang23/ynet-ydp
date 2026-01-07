package com.ynet.iplatform.module.aicrm.controller.admin.cohort.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 客群信息 Response VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 客群信息 Response VO")
@Data
public class CohortRespVO {

    @Schema(description = "客群ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "CH20260106112344986807842")
    @JsonProperty("labelId")
    private String id;

    @Schema(description = "客群名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "高净值客户群")
    @JsonProperty("labelName")
    private String cohortName;

    @Schema(description = "客群编码", example = "HNWC001")
    @JsonProperty("cohortCode")
    private String cohortCode;

    @Schema(description = "客群描述", example = "资产超过100万的高净值客户")
    @JsonProperty("labelExplain")
    private String description;

    @Schema(description = "更新周期", example = "每天")
    @JsonProperty("updateCycle")
    private String updateCycle;

    @Schema(description = "状态", example = "启用")
    @JsonProperty("state")
    private String state;

    @Schema(description = "覆盖人数", example = "1000")
    @JsonProperty("coverNum")
    private Integer coverNum;

}
