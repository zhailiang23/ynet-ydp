package cn.iocoder.yudao.module.aicrm.controller.admin.customeroffbalanceoverview.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 客户表外业务概览新增/修改 Request VO")
@Data
public class CustomerOffbalanceOverviewSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "21624")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12615")
    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    @Schema(description = "客户编号")
    private String customerNo;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计日期不能为空")
    private LocalDate statisticsDate;

    @Schema(description = "业务类型", example = "1")
    private String businessType;

    @Schema(description = "业务代码")
    private String businessCode;

    @Schema(description = "业务名称", example = "赵六")
    private String businessName;

    @Schema(description = "币种")
    private String currency;

    @Schema(description = "业务金额")
    private BigDecimal businessAmount;

    @Schema(description = "业务余额")
    private BigDecimal businessBalance;

    @Schema(description = "担保金额")
    private BigDecimal guaranteeAmount;

    @Schema(description = "授信金额")
    private BigDecimal creditAmount;

    @Schema(description = "已用金额")
    private BigDecimal usedAmount;

    @Schema(description = "可用金额")
    private BigDecimal availableAmount;

    @Schema(description = "业务笔数", example = "18136")
    private Integer businessCount;

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

    @Schema(description = "使用率")
    private BigDecimal usageRate;

    @Schema(description = "起始日期")
    private LocalDate startDate;

    @Schema(description = "到期日期")
    private LocalDate endDate;

    @Schema(description = "生效日期")
    private LocalDate effectiveDate;

    @Schema(description = "费率")
    private BigDecimal feeRate;

    @Schema(description = "手续费金额")
    private BigDecimal feeAmount;

    @Schema(description = "业务状态", example = "1")
    private String businessStatus;

    @Schema(description = "风险等级")
    private String riskLevel;

    @Schema(description = "利润贡献")
    private BigDecimal profitContribution;

    @Schema(description = "手续费收入")
    private BigDecimal feeIncome;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}