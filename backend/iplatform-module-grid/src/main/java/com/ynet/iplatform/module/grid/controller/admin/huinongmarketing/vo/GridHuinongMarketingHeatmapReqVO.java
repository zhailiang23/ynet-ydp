package com.ynet.iplatform.module.grid.controller.admin.huinongmarketing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 惠农网格营销热力图请求 VO")
@Data
public class GridHuinongMarketingHeatmapReqVO {

    @Schema(description = "站点类型筛选", example = "REQUIRED")
    private String stationType; // REQUIRED(必选) / OPTIONAL(可选) / null(全部)

    @Schema(description = "客群类型筛选（多选）", example = "[\"传统种植户\", \"传统养殖户\"]")
    private List<String> customerTypes; // 九类客群筛选

}
