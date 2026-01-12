package com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 惠农站点信息的精简 Response VO")
@Data
public class GridHuinongStationSimpleRespVO {

    @Schema(description = "站点ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "站点名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "惠农站点1")
    private String stationName;

    @Schema(description = "站点编号", example = "HN001")
    private String stationCode;

}
