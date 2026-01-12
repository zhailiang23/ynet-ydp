package com.ynet.iplatform.module.grid.controller.admin.gridmap.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 网格营销综合地图数据请求 VO")
@Data
public class GridMapDataReqVO {

    @Schema(description = "网格类型列表（HUINONG/ZERODAI/COMMUNITY/LOBBY）", example = "[\"HUINONG\",\"ZERODAI\"]")
    private List<String> gridTypes;

    @Schema(description = "客户类型列表（HUINONG_LOAN/ZERODAI/COMMUNITY/TINGTANG）", example = "[\"HUINONG_LOAN\",\"ZERODAI\"]")
    private List<String> customerTypes;

    @Schema(description = "是否包含社区信息", example = "true")
    private Boolean includeCommunity;

    @Schema(description = "是否包含同业信息", example = "true")
    private Boolean includeCompetitor;

}
