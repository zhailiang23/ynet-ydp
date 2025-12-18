package com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 惠农贷款目标客户热力图请求 VO")
@Data
public class GridHuinongCustomerLoanHeatmapReqVO {

    @Schema(description = "热力值指标类型", example = "CUSTOMER_COUNT")
    private String metricType; // CUSTOMER_COUNT/APPLIED_COUNT/APPROVED_COUNT/LOAN_BALANCE

}
