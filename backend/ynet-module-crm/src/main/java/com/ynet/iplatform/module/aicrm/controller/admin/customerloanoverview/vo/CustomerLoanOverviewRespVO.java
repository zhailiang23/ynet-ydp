package com.ynet.iplatform.module.aicrm.controller.admin.customerloanoverview.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户贷款业务概览 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerLoanOverviewRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26679")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31985")
    @ExcelProperty("客户ID")
    private Long customerId;

    @Schema(description = "客户编号")
    @ExcelProperty("客户编号")
    private String customerNo;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计日期")
    private LocalDate statisticsDate;

    @Schema(description = "贷款类型", example = "2")
    @ExcelProperty("贷款类型")
    private String loanType;

    @Schema(description = "币种")
    @ExcelProperty("币种")
    private String currency;

    @Schema(description = "贷款余额")
    @ExcelProperty("贷款余额")
    private BigDecimal loanAmount;

    @Schema(description = "贷款账户数", example = "10435")
    @ExcelProperty("贷款账户数")
    private Integer loanAccountCount;

    @Schema(description = "贷款客户数", example = "15186")
    @ExcelProperty("贷款客户数")
    private Integer loanCustomerCount;

    @Schema(description = "正常业务余额")
    @ExcelProperty("正常业务余额")
    private BigDecimal normalBusinessBalance;

    @Schema(description = "逾期余额")
    @ExcelProperty("逾期余额")
    private BigDecimal overdueBalance;

    @Schema(description = "不良贷款余额")
    @ExcelProperty("不良贷款余额")
    private BigDecimal badLoanBalance;

    @Schema(description = "业务余额")
    @ExcelProperty("业务余额")
    private BigDecimal businessBalance;

    @Schema(description = "本年余额日均")
    @ExcelProperty("本年余额日均")
    private BigDecimal balanceYearAvg;

    @Schema(description = "上年余额日均")
    @ExcelProperty("上年余额日均")
    private BigDecimal lastYearBalanceYearAvg;

    @Schema(description = "本季度余额日均")
    @ExcelProperty("本季度余额日均")
    private BigDecimal balanceQuarterAvg;

    @Schema(description = "上年同期季度余额日均")
    @ExcelProperty("上年同期季度余额日均")
    private BigDecimal lastYearBalanceQuarterAvg;

    @Schema(description = "本月余额日均")
    @ExcelProperty("本月余额日均")
    private BigDecimal balanceMonthAvg;

    @Schema(description = "上年同期月余额日均")
    @ExcelProperty("上年同期月余额日均")
    private BigDecimal lastYearBalanceMonthAvg;

    @Schema(description = "贷款金额")
    @ExcelProperty("贷款金额")
    private BigDecimal loanAmountTotal;

    @Schema(description = "上年贷款余额")
    @ExcelProperty("上年贷款余额")
    private BigDecimal lastYearLoanAmount;

    @Schema(description = "业务金额")
    @ExcelProperty("业务金额")
    private BigDecimal businessAmount;

    @Schema(description = "授信总额")
    @ExcelProperty("授信总额")
    private BigDecimal creditTotalAmount;

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

    @Schema(description = "表外贷款使用率")
    @ExcelProperty("表外贷款使用率")
    private BigDecimal outsideLoanUsage;

    @Schema(description = "表内贷款使用率")
    @ExcelProperty("表内贷款使用率")
    private BigDecimal insideLoanUsage;

    @Schema(description = "票据贷款使用率")
    @ExcelProperty("票据贷款使用率")
    private BigDecimal billLoanUsage;

    @Schema(description = "贷款利润贡献")
    @ExcelProperty("贷款利润贡献")
    private BigDecimal loanProfit;

    @Schema(description = "贷款利率")
    @ExcelProperty("贷款利率")
    private BigDecimal interestRate;

    @Schema(description = "逾期天数")
    @ExcelProperty("逾期天数")
    private Integer overdueDays;

    @Schema(description = "风险等级")
    @ExcelProperty("风险等级")
    private String riskLevel;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}