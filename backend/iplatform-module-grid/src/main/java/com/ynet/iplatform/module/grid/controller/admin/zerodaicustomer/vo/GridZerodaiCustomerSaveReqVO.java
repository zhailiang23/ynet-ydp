package com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 零贷客户扩展新增/修改 Request VO")
@Data
public class GridZerodaiCustomerSaveReqVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25218")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "29250")
    @NotNull(message = "客户ID (关联 grid_customer)不能为空")
    private Long customerId;

    @Schema(description = "业务类型 (关联字典 aicrm_business_type)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "业务类型 (关联字典 aicrm_business_type)不能为空")
    private String businessType;

    @Schema(description = "年营业额 (元)")
    private BigDecimal annualRevenue;

    @Schema(description = "信用评级")
    private String creditRating;

    @Schema(description = "是否进件")
    private Boolean isApplied;

    @Schema(description = "是否完件")
    private Boolean isCompleted;

    @Schema(description = "贷款投放金额 (元)")
    private BigDecimal loanAmount;

    @Schema(description = "贷款余额 (元)")
    private BigDecimal loanBalance;

    @Schema(description = "是否受保护")
    private Boolean archiveProtected;

    @Schema(description = "创建人ID", example = "27862")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "27823")
    private Long updaterId;

}