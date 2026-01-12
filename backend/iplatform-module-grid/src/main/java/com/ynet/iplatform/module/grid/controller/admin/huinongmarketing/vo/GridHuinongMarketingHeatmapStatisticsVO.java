package com.ynet.iplatform.module.grid.controller.admin.huinongmarketing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Schema(description = "管理后台 - 惠农网格营销热力图统计 VO")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridHuinongMarketingHeatmapStatisticsVO {

    @Schema(description = "已开展网格营销的站点数量", example = "15")
    private Integer totalStations;

    @Schema(description = "必选开展的站点数量", example = "8")
    private Integer requiredStations;

    @Schema(description = "可选开展的站点数量", example = "7")
    private Integer optionalStations;

    @Schema(description = "必选站点统计数据")
    private StationStatistics requiredStatistics;

    @Schema(description = "可选站点统计数据")
    private StationStatistics optionalStatistics;

    @Schema(description = "九类客群统计明细", example = "{\"传统种植户\": 100, \"传统养殖户\": 50}")
    private Map<String, Integer> customerTypeStatistics;

    @Schema(description = "站点统计数据")
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class StationStatistics {

        @Schema(description = "覆盖的行政村数量", example = "25")
        private Integer villageCount;

        @Schema(description = "户籍人口总数", example = "5000")
        private Integer totalPopulation;

        @Schema(description = "九类客群总数", example = "150")
        private Integer totalCustomerCount;
    }

}
