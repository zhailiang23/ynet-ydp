package com.ynet.iplatform.module.aicrm.controller.admin.customermiddlebusinessoverview.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 客户中间业务概览新增/修改 Request VO")
@Data
public class CustomerMiddleBusinessOverviewSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14308")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30864")
    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    @Schema(description = "客户编号")
    private String customerNo;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计日期不能为空")
    private LocalDate statisticsDate;

    @Schema(description = "业务类型", example = "2")
    private String businessType;

    @Schema(description = "业务代码")
    private String businessCode;

    @Schema(description = "业务名称", example = "张三")
    private String businessName;

    @Schema(description = "币种")
    private String currency;

    @Schema(description = "业务金额")
    private BigDecimal businessAmount;

    @Schema(description = "交易金额")
    private BigDecimal transactionAmount;

    @Schema(description = "手续费金额")
    private BigDecimal feeAmount;

    @Schema(description = "佣金金额")
    private BigDecimal commissionAmount;

    @Schema(description = "交易笔数", example = "8109")
    private Integer transactionCount;

    @Schema(description = "本年金额日均")
    private BigDecimal amountYearAvg;

    @Schema(description = "上年金额日均")
    private BigDecimal lastYearAmountYearAvg;

    @Schema(description = "本季度金额日均")
    private BigDecimal amountQuarterAvg;

    @Schema(description = "上年同期季度金额日均")
    private BigDecimal lastYearAmountQuarterAvg;

    @Schema(description = "本月金额日均")
    private BigDecimal amountMonthAvg;

    @Schema(description = "上年同期月金额日均")
    private BigDecimal lastYearAmountMonthAvg;

    @Schema(description = "本年累计金额")
    private BigDecimal cumulativeYearAmount;

    @Schema(description = "上年累计金额")
    private BigDecimal lastYearCumulativeAmount;

    @Schema(description = "本季度累计金额")
    private BigDecimal cumulativeQuarterAmount;

    @Schema(description = "上年同期季度累计金额")
    private BigDecimal lastYearCumulativeQuarterAmount;

    @Schema(description = "本月累计金额")
    private BigDecimal cumulativeMonthAmount;

    @Schema(description = "上年同期月累计金额")
    private BigDecimal lastYearCumulativeMonthAmount;

    @Schema(description = "利润贡献")
    private BigDecimal profitContribution;

    @Schema(description = "手续费收入")
    private BigDecimal feeIncome;

    @Schema(description = "佣金收入")
    private BigDecimal commissionIncome;

    @Schema(description = "备注", example = "随便")
    private String remark;

}