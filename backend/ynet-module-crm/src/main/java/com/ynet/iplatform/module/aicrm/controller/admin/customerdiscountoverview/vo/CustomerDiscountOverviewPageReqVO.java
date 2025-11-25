package com.ynet.iplatform.module.aicrm.controller.admin.customerdiscountoverview.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户贴现业务概览分页 Request VO")
@Data
public class CustomerDiscountOverviewPageReqVO extends PageParam {

    @Schema(description = "客户ID", example = "5161")
    private Long customerId;

    @Schema(description = "客户编号")
    private String customerNo;

    @Schema(description = "统计日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] statisticsDate;

    @Schema(description = "贴现类型", example = "1")
    private String discountType;

    @Schema(description = "票据号码")
    private String billNo;

    @Schema(description = "票据类型", example = "2")
    private String billType;

    @Schema(description = "币种")
    private String currency;

    @Schema(description = "票据金额")
    private BigDecimal billAmount;

    @Schema(description = "贴现金额")
    private BigDecimal discountAmount;

    @Schema(description = "贴现余额")
    private BigDecimal discountBalance;

    @Schema(description = "贴息金额")
    private BigDecimal interestAmount;

    @Schema(description = "实付金额")
    private BigDecimal actualAmount;

    @Schema(description = "贴现笔数", example = "32533")
    private Integer discountCount;

    @Schema(description = "本年余额日均")
    private BigDecimal balanceYearAvg;

    @Schema(description = "上年余额日均")
    private BigDecimal lastYearBalanceYearAvg;

    @Schema(description = "本季度余额日均")
    private BigDecimal balanceQuarterAvg;

    @Schema(description = "上年同期季度余额日均")
    private BigDecimal lastYearBalanceQuarterAvg;

    @Schema(description = "本月余额日均")
    private BigDecimal balanceMonthAvg;

    @Schema(description = "上年同期月余额日均")
    private BigDecimal lastYearBalanceMonthAvg;

    @Schema(description = "本年累计贴现金额")
    private BigDecimal cumulativeYearAmount;

    @Schema(description = "上年累计贴现金额")
    private BigDecimal lastYearCumulativeAmount;

    @Schema(description = "本季度累计贴现金额")
    private BigDecimal cumulativeQuarterAmount;

    @Schema(description = "上年同期季度累计贴现金额")
    private BigDecimal lastYearCumulativeQuarterAmount;

    @Schema(description = "本月累计贴现金额")
    private BigDecimal cumulativeMonthAmount;

    @Schema(description = "上年同期月累计贴现金额")
    private BigDecimal lastYearCumulativeMonthAmount;

    @Schema(description = "贴现利率")
    private BigDecimal discountRate;

    @Schema(description = "贴现天数")
    private Integer discountDays;

    @Schema(description = "票据出票日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] billIssueDate;

    @Schema(description = "票据到期日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] billDueDate;

    @Schema(description = "贴现日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] discountDate;

    @Schema(description = "结算日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] settleDate;

    @Schema(description = "出票人")
    private String drawer;

    @Schema(description = "收款人")
    private String payee;

    @Schema(description = "承兑人")
    private String acceptor;

    @Schema(description = "承兑银行")
    private String acceptanceBank;

    @Schema(description = "贴现状态", example = "1")
    private String discountStatus;

    @Schema(description = "结算状态", example = "1")
    private String settlementStatus;

    @Schema(description = "利润贡献")
    private BigDecimal profitContribution;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}