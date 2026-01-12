package com.ynet.iplatform.module.grid.controller.admin.huinongmarketing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "管理后台 - 惠农网格营销热力图数据点 VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridHuinongMarketingHeatmapDataVO {

    @Schema(description = "经度", example = "113.625368", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double longitude;

    @Schema(description = "纬度", example = "34.746599", requiredMode = Schema.RequiredMode.REQUIRED)
    private Double latitude;

    @Schema(description = "热力权重（营销信息数量）", example = "5", requiredMode = Schema.RequiredMode.REQUIRED)
    private Integer count;

    @Schema(description = "行政村名称", example = "五里堡街道")
    private String villageName;

}
