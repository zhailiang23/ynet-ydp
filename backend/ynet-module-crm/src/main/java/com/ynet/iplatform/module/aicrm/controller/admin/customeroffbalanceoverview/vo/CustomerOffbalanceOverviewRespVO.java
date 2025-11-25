package com.ynet.iplatform.module.aicrm.controller.admin.customeroffbalanceoverview.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户表外业务概览 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerOffbalanceOverviewRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21624")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12615")
    @ExcelProperty("客户ID")
    private Long customerId;

    @Schema(description = "客户编号")
    @ExcelProperty("客户编号")
    private String customerNo;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计日期")
    private LocalDate statisticsDate;

    @Schema(description = "业务类型", example = "1")
    @ExcelProperty("业务类型")
    private String businessType;

    @Schema(description = "业务代码")
    @ExcelProperty("业务代码")
    private String businessCode;

    @Schema(description = "业务名称", example = "赵六")
    @ExcelProperty("业务名称")
    private String businessName;

    @Schema(description = "币种")
    @ExcelProperty("币种")
    private String currency;

    @Schema(description = "业务金额")
    @ExcelProperty("业务金额")
    private BigDecimal businessAmount;

    @Schema(description = "业务余额")
    @ExcelProperty("业务余额")
    private BigDecimal businessBalance;

    @Schema(description = "担保金额")
    @ExcelProperty("担保金额")
    private BigDecimal guaranteeAmount;

    @Schema(description = "授信金额")
    @ExcelProperty("授信金额")
    private BigDecimal creditAmount;

    @Schema(description = "已用金额")
    @ExcelProperty("已用金额")
    private BigDecimal usedAmount;

    @Schema(description = "可用金额")
    @ExcelProperty("可用金额")
    private BigDecimal availableAmount;

    @Schema(description = "业务笔数", example = "18136")
    @ExcelProperty("业务笔数")
    private Integer businessCount;

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

    @Schema(description = "使用率")
    @ExcelProperty("使用率")
    private BigDecimal usageRate;

    @Schema(description = "起始日期")
    @ExcelProperty("起始日期")
    private LocalDate startDate;

    @Schema(description = "到期日期")
    @ExcelProperty("到期日期")
    private LocalDate endDate;

    @Schema(description = "生效日期")
    @ExcelProperty("生效日期")
    private LocalDate effectiveDate;

    @Schema(description = "费率")
    @ExcelProperty("费率")
    private BigDecimal feeRate;

    @Schema(description = "手续费金额")
    @ExcelProperty("手续费金额")
    private BigDecimal feeAmount;

    @Schema(description = "业务状态", example = "1")
    @ExcelProperty("业务状态")
    private String businessStatus;

    @Schema(description = "风险等级")
    @ExcelProperty("风险等级")
    private String riskLevel;

    @Schema(description = "利润贡献")
    @ExcelProperty("利润贡献")
    private BigDecimal profitContribution;

    @Schema(description = "手续费收入")
    @ExcelProperty("手续费收入")
    private BigDecimal feeIncome;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}