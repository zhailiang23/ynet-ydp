package com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 惠农站点地图数据 VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - 惠农站点地图数据 Response VO")
@Data
public class GridHuinongStationMapVO {

    @Schema(description = "站点ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "站点名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "金水路惠农服务站")
    private String stationName;

    @Schema(description = "站点编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "HN0001")
    private String stationCode;

    @Schema(description = "站点经度", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal longitude;

    @Schema(description = "站点纬度", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal latitude;

    @Schema(description = "地址")
    private String address;

    @Schema(description = "网格营销站点标识")
    private String gridMarketingFlag;

    @Schema(description = "惠农人员工号")
    private String specialistEmployeeNo;

    @Schema(description = "惠农人员姓名")
    private String specialistName;

    @Schema(description = "管理行名称")
    private String manageBranchName;

    @Schema(description = "二级支行名称")
    private String subBranchName;

    @Schema(description = "惠农人员经度")
    private BigDecimal specialistLongitude;

    @Schema(description = "惠农人员纬度")
    private BigDecimal specialistLatitude;

    @Schema(description = "所属网格信息")
    private GridInfo gridInfo;

    /**
     * 网格信息
     */
    @Schema(description = "网格信息")
    @Data
    public static class GridInfo {
        @Schema(description = "网格ID")
        private Long gridId;

        @Schema(description = "网格名称")
        private String gridName;

        @Schema(description = "网格编号")
        private String gridCode;

        @Schema(description = "网格中心经度")
        private BigDecimal centerLongitude;

        @Schema(description = "网格中心纬度")
        private BigDecimal centerLatitude;

        @Schema(description = "网格边界坐标 (GeoJSON格式)")
        private String boundaryGeometry;
    }
}
