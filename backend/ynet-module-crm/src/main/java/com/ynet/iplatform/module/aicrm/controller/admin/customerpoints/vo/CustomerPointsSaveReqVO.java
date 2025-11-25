package com.ynet.iplatform.module.aicrm.controller.admin.customerpoints.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客户积分信息新增/修改 Request VO")
@Data
public class CustomerPointsSaveReqVO {

    @Schema(description = "积分主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "3936")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "30355")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "可用积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "可用积分不能为空")
    private Long availablePoints;

    @Schema(description = "历史累计赠送积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "历史累计赠送积分不能为空")
    private Long historyTotalGiftPoints;

    @Schema(description = "历史累计扣减积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "历史累计扣减积分不能为空")
    private Long historyTotalDeductPoints;

    @Schema(description = "历史累计失效积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "历史累计失效积分不能为空")
    private Long historyTotalExpirePoints;

    @Schema(description = "即将失效积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "即将失效积分不能为空")
    private Long upcomingExpirePoints;

    @Schema(description = "即将失效积分日期")
    private LocalDate upcomingExpireDate;

    @Schema(description = "积分账户编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "积分账户编号不能为空")
    private String pointsAccountNo;

    @Schema(description = "积分等级（字典: aicrm_points_level，bronze=铜卡，silver=银卡，gold=金卡，platinum=白金卡，diamond=钻石卡）")
    private String pointsLevel;

    @Schema(description = "累计获得积分（包含赠送和交易）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "累计获得积分（包含赠送和交易）不能为空")
    private Long totalEarnedPoints;

    @Schema(description = "累计使用积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "累计使用积分不能为空")
    private Long totalUsedPoints;

    @Schema(description = "冻结积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "冻结积分不能为空")
    private Long frozenPoints;

    @Schema(description = "历史累计交易积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "历史累计交易积分不能为空")
    private Long historyTotalTransactionPoints;

    @Schema(description = "历史累计兑换积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "历史累计兑换积分不能为空")
    private Long historyTotalRedeemPoints;

    @Schema(description = "积分余额（可用+冻结）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "积分余额（可用+冻结）不能为空")
    private Long pointsBalance;

    @Schema(description = "账户状态（字典: aicrm_points_account_status，active=正常，frozen=冻结，closed=关闭）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "账户状态（字典: aicrm_points_account_status，active=正常，frozen=冻结，closed=关闭）不能为空")
    private String accountStatus;

    @Schema(description = "开户日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "开户日期不能为空")
    private LocalDate openDate;

    @Schema(description = "最后交易日期")
    private LocalDate lastTransactionDate;

    @Schema(description = "最后交易时间")
    private LocalDateTime lastTransactionTime;

    @Schema(description = "最后赠送日期")
    private LocalDate lastGiftDate;

    @Schema(description = "最后兑换日期")
    private LocalDate lastRedeemDate;

    @Schema(description = "积分有效期（月）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "积分有效期（月）不能为空")
    private Integer pointsValidMonths;

    @Schema(description = "是否自动失效", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否自动失效不能为空")
    private Boolean isAutoExpire;

    @Schema(description = "自动失效提醒天数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "自动失效提醒天数不能为空")
    private Integer autoExpireRemindDays;

    @Schema(description = "本年度获得积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "本年度获得积分不能为空")
    private Long yearEarnedPoints;

    @Schema(description = "本年度使用积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "本年度使用积分不能为空")
    private Long yearUsedPoints;

    @Schema(description = "本月获得积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "本月获得积分不能为空")
    private Long monthEarnedPoints;

    @Schema(description = "本月使用积分", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "本月使用积分不能为空")
    private Long monthUsedPoints;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}