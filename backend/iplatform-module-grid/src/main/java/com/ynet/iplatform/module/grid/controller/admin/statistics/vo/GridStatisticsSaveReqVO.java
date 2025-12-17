package com.ynet.iplatform.module.grid.controller.admin.statistics.vo;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 网格统计报表新增/修改 Request VO")
@Data
public class GridStatisticsSaveReqVO {

    @Schema(description = "统计ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16071")
    private Long id;

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20719")
    @NotNull(message = "网格ID不能为空")
    private Long gridId;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计日期不能为空")
    private LocalDate statDate;

    @Schema(description = "统计类型: DAILY/WEEKLY/MONTHLY", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "统计类型: DAILY/WEEKLY/MONTHLY不能为空")
    private String statType;

    @Schema(description = "客户总数", example = "18721")
    private Integer customerCount;

    @Schema(description = "新增客户数", example = "6553")
    private Integer newCustomerCount;

    @Schema(description = "活跃客户数", example = "2202")
    private Integer activeCustomerCount;

    @Schema(description = "活动次数", example = "28189")
    private Integer activityCount;

    @Schema(description = "贷款申请数", example = "8428")
    private Integer loanApplicationCount;

    @Schema(description = "贷款批准数", example = "4642")
    private Integer loanApprovalCount;

    @Schema(description = "贷款金额")
    private BigDecimal loanAmount;

    @Schema(description = "存款金额")
    private BigDecimal depositAmount;

    @Schema(description = "绩效评分")
    private BigDecimal performanceScore;

}