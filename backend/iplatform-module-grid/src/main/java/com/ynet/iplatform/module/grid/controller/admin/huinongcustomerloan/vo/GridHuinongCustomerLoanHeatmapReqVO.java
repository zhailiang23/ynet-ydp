package com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "管理后台 - 惠农贷款目标客户热力图请求 VO")
@Data
public class GridHuinongCustomerLoanHeatmapReqVO {

    @Schema(description = "热力值指标类型", example = "CUSTOMER_COUNT")
    private String metricType; // CUSTOMER_COUNT/APPLIED_COUNT/APPROVED_COUNT/LOAN_BALANCE

    @Schema(description = "筛选的网格ID列表", example = "[53, 54, 55]")
    private List<Long> gridIds;

    @Schema(description = "筛选的客群类型列表", example = "[\"种植养殖户\", \"个体工商户\"]")
    private List<String> customerCategories;

    @Schema(description = "网格营销标记筛选", example = "REQUIRED")
    private String gridMarketingFlag; // REQUIRED（必选）/ OPTIONAL（可选）

}
