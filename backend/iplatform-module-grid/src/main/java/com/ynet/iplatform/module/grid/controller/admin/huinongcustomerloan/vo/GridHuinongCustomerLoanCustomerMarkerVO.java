package com.ynet.iplatform.module.grid.controller.admin.huinongcustomerloan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 惠农贷款目标客户标记 VO")
@Data
public class GridHuinongCustomerLoanCustomerMarkerVO {

    @Schema(description = "客户ID", example = "1")
    private Long customerId;

    @Schema(description = "客户姓名", example = "张三")
    private String customerName;

    @Schema(description = "经度", example = "113.625")
    private Double longitude;

    @Schema(description = "纬度", example = "34.746")
    private Double latitude;

    @Schema(description = "站点类型：REQUIRED(必选)/OPTIONAL(可选)", example = "REQUIRED")
    private String stationType;

    @Schema(description = "是否已申请（进件）", example = "true")
    private Boolean isApplied;

    @Schema(description = "是否已批准", example = "false")
    private Boolean isApproved;

    @Schema(description = "贷款余额", example = "50000.00")
    private BigDecimal loanBalance;

    @Schema(description = "客户类别", example = "传统种植户")
    private String customerCategory;

}
