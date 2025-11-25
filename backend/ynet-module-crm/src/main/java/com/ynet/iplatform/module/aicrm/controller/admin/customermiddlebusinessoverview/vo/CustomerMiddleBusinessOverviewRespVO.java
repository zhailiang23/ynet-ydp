package com.ynet.iplatform.module.aicrm.controller.admin.customermiddlebusinessoverview.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户中间业务概览 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerMiddleBusinessOverviewRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14308")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30864")
    @ExcelProperty("客户ID")
    private Long customerId;

    @Schema(description = "客户编号")
    @ExcelProperty("客户编号")
    private String customerNo;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计日期")
    private LocalDate statisticsDate;

    @Schema(description = "业务类型", example = "2")
    @ExcelProperty("业务类型")
    private String businessType;

    @Schema(description = "业务代码")
    @ExcelProperty("业务代码")
    private String businessCode;

    @Schema(description = "业务名称", example = "张三")
    @ExcelProperty("业务名称")
    private String businessName;

    @Schema(description = "币种")
    @ExcelProperty("币种")
    private String currency;

    @Schema(description = "业务金额")
    @ExcelProperty("业务金额")
    private BigDecimal businessAmount;

    @Schema(description = "交易金额")
    @ExcelProperty("交易金额")
    private BigDecimal transactionAmount;

    @Schema(description = "手续费金额")
    @ExcelProperty("手续费金额")
    private BigDecimal feeAmount;

    @Schema(description = "佣金金额")
    @ExcelProperty("佣金金额")
    private BigDecimal commissionAmount;

    @Schema(description = "交易笔数", example = "8109")
    @ExcelProperty("交易笔数")
    private Integer transactionCount;

    @Schema(description = "本年金额日均")
    @ExcelProperty("本年金额日均")
    private BigDecimal amountYearAvg;

    @Schema(description = "上年金额日均")
    @ExcelProperty("上年金额日均")
    private BigDecimal lastYearAmountYearAvg;

    @Schema(description = "本季度金额日均")
    @ExcelProperty("本季度金额日均")
    private BigDecimal amountQuarterAvg;

    @Schema(description = "上年同期季度金额日均")
    @ExcelProperty("上年同期季度金额日均")
    private BigDecimal lastYearAmountQuarterAvg;

    @Schema(description = "本月金额日均")
    @ExcelProperty("本月金额日均")
    private BigDecimal amountMonthAvg;

    @Schema(description = "上年同期月金额日均")
    @ExcelProperty("上年同期月金额日均")
    private BigDecimal lastYearAmountMonthAvg;

    @Schema(description = "本年累计金额")
    @ExcelProperty("本年累计金额")
    private BigDecimal cumulativeYearAmount;

    @Schema(description = "上年累计金额")
    @ExcelProperty("上年累计金额")
    private BigDecimal lastYearCumulativeAmount;

    @Schema(description = "本季度累计金额")
    @ExcelProperty("本季度累计金额")
    private BigDecimal cumulativeQuarterAmount;

    @Schema(description = "上年同期季度累计金额")
    @ExcelProperty("上年同期季度累计金额")
    private BigDecimal lastYearCumulativeQuarterAmount;

    @Schema(description = "本月累计金额")
    @ExcelProperty("本月累计金额")
    private BigDecimal cumulativeMonthAmount;

    @Schema(description = "上年同期月累计金额")
    @ExcelProperty("上年同期月累计金额")
    private BigDecimal lastYearCumulativeMonthAmount;

    @Schema(description = "利润贡献")
    @ExcelProperty("利润贡献")
    private BigDecimal profitContribution;

    @Schema(description = "手续费收入")
    @ExcelProperty("手续费收入")
    private BigDecimal feeIncome;

    @Schema(description = "佣金收入")
    @ExcelProperty("佣金收入")
    private BigDecimal commissionIncome;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}