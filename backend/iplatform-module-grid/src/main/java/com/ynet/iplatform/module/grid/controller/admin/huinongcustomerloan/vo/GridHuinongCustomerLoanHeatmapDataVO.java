package com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 惠农贷款目标客户热力图数据 VO")
@Data
public class GridHuinongCustomerLoanHeatmapDataVO {

    @Schema(description = "经度", example = "113.625")
    private Double longitude;

    @Schema(description = "纬度", example = "34.746")
    private Double latitude;

    @Schema(description = "热力值（根据 metricType 不同含义不同）", example = "100")
    private BigDecimal value;

    @Schema(description = "网格ID", example = "1")
    private Long gridId;

    @Schema(description = "站点名称", example = "中原区三官庙站点")
    private String stationName;

}
