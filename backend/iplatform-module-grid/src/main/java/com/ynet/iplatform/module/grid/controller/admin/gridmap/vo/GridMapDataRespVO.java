package com.ynet.iplatform.module.grid.controller.admin.gridmap.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 网格营销综合地图数据响应 VO")
@Data
public class GridMapDataRespVO {

    @Schema(description = "网格数据列表")
    private List<GridData> grids;

    @Schema(description = "客户数据列表")
    private List<CustomerData> customers;

    @Schema(description = "社区数据列表")
    private List<CommunityData> communities;

    @Schema(description = "同业数据列表")
    private List<CompetitorData> competitors;

    @Schema(description = "网格数据")
    @Data
    public static class GridData {
        @Schema(description = "网格ID", example = "1")
        private Long id;

        @Schema(description = "网格名称", example = "惠农站点1")
        private String gridName;

        @Schema(description = "网格类型", example = "HUINONG")
        private String gridType;

        @Schema(description = "边界几何（GeoJSON格式）", example = "{\"type\":\"Polygon\",\"coordinates\":[[[113.6254,34.7466]]]}")
        private String boundaryGeometry;

        @Schema(description = "中心点经度", example = "113.6254")
        private BigDecimal longitude;

        @Schema(description = "中心点纬度", example = "34.7466")
        private BigDecimal latitude;

        @Schema(description = "责任人用户ID", example = "1")
        private Long managerUserId;
    }

    @Schema(description = "客户数据")
    @Data
    public static class CustomerData {
        @Schema(description = "客户ID", example = "1")
        private Long id;

        @Schema(description = "客户姓名", example = "张三")
        private String customerName;

        @Schema(description = "客户类型", example = "HUINONG_LOAN")
        private String customerType;

        @Schema(description = "经度", example = "113.6254")
        private BigDecimal longitude;

        @Schema(description = "纬度", example = "34.7466")
        private BigDecimal latitude;

        @Schema(description = "地址", example = "郑州市金水区")
        private String address;
    }

    @Schema(description = "社区数据")
    @Data
    public static class CommunityData {
        @Schema(description = "社区ID", example = "1")
        private Long id;

        @Schema(description = "社区名称", example = "XX社区")
        private String communityName;

        @Schema(description = "经度", example = "113.6254")
        private BigDecimal longitude;

        @Schema(description = "纬度", example = "34.7466")
        private BigDecimal latitude;

        @Schema(description = "地址", example = "郑州市金水区")
        private String address;
    }

    @Schema(description = "同业数据")
    @Data
    public static class CompetitorData {
        @Schema(description = "同业ID", example = "1")
        private Long id;

        @Schema(description = "同业名称", example = "XX银行")
        private String competitorName;

        @Schema(description = "经度", example = "113.6254")
        private BigDecimal longitude;

        @Schema(description = "纬度", example = "34.7466")
        private BigDecimal latitude;

        @Schema(description = "地址", example = "郑州市金水区")
        private String address;
    }

}
