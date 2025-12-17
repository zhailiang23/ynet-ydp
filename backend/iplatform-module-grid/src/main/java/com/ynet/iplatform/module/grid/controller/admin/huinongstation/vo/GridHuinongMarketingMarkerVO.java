package com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 惠农营销信息地图标记 VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 惠农营销信息地图标记 Response VO")
@Data
public class GridHuinongMarketingMarkerVO {

    @Schema(description = "营销信息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "村名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张村")
    private String villageName;

    @Schema(description = "村地址", example = "金水区张村镇")
    private String villageAddress;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal latitude;

    @Schema(description = "户籍人口", example = "1000")
    private Integer registeredPopulation;

    @Schema(description = "常住人口", example = "800")
    private Integer residentPopulation;

    @Schema(description = "主要产业", example = "种植业、养殖业")
    private String mainIndustries;

    @Schema(description = "产业情况")
    private String industrySituation;
}
