package cn.iocoder.yudao.module.aicrm.controller.admin.customerchanneloverview.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客户渠道业务概览新增/修改 Request VO")
@Data
public class CustomerChannelOverviewSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12151")
    private Long id;

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2362")
    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    @Schema(description = "客户编号")
    private String customerNo;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计日期不能为空")
    private LocalDate statisticsDate;

    @Schema(description = "渠道类型", example = "1")
    private String channelType;

    @Schema(description = "渠道代码")
    private String channelCode;

    @Schema(description = "渠道名称", example = "张三")
    private String channelName;

    @Schema(description = "访问次数", example = "22444")
    private Integer accessCount;

    @Schema(description = "登录次数", example = "7354")
    private Integer loginCount;

    @Schema(description = "交易笔数", example = "16156")
    private Integer transactionCount;

    @Schema(description = "活跃天数")
    private Integer activeDays;

    @Schema(description = "交易金额")
    private BigDecimal transactionAmount;

    @Schema(description = "本年交易金额日均")
    private BigDecimal amountYearAvg;

    @Schema(description = "上年交易金额日均")
    private BigDecimal lastYearAmountYearAvg;

    @Schema(description = "本季度交易金额日均")
    private BigDecimal amountQuarterAvg;

    @Schema(description = "上年同期季度交易金额日均")
    private BigDecimal lastYearAmountQuarterAvg;

    @Schema(description = "本月交易金额日均")
    private BigDecimal amountMonthAvg;

    @Schema(description = "上年同期月交易金额日均")
    private BigDecimal lastYearAmountMonthAvg;

    @Schema(description = "本年累计交易金额")
    private BigDecimal cumulativeYearAmount;

    @Schema(description = "上年累计交易金额")
    private BigDecimal lastYearCumulativeAmount;

    @Schema(description = "本季度累计交易金额")
    private BigDecimal cumulativeQuarterAmount;

    @Schema(description = "上年同期季度累计交易金额")
    private BigDecimal lastYearCumulativeQuarterAmount;

    @Schema(description = "本月累计交易金额")
    private BigDecimal cumulativeMonthAmount;

    @Schema(description = "上年同期月累计交易金额")
    private BigDecimal lastYearCumulativeMonthAmount;

    @Schema(description = "首次访问时间")
    private LocalDateTime firstAccessTime;

    @Schema(description = "最近访问时间")
    private LocalDateTime lastAccessTime;

    @Schema(description = "最近交易时间")
    private LocalDateTime lastTransactionTime;

    @Schema(description = "设备类型", example = "2")
    private String deviceType;

    @Schema(description = "设备型号")
    private String deviceModel;

    @Schema(description = "操作系统版本")
    private String osVersion;

    @Schema(description = "应用版本")
    private String appVersion;

    @Schema(description = "偏好业务")
    private String preferredBusiness;

    @Schema(description = "使用频率")
    private String usageFrequency;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}