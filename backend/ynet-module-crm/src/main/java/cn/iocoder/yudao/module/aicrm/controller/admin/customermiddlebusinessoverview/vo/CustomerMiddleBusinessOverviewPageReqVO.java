package cn.iocoder.yudao.module.aicrm.controller.admin.customermiddlebusinessoverview.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户中间业务概览分页 Request VO")
@Data
public class CustomerMiddleBusinessOverviewPageReqVO extends PageParam {

    @Schema(description = "客户ID", example = "30864")
    private Long customerId;

    @Schema(description = "客户编号")
    private String customerNo;

    @Schema(description = "统计日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] statisticsDate;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}