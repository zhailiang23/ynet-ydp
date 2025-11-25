package com.ynet.iplatform.module.aicrm.controller.admin.customerpoints.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户积分信息分页 Request VO")
@Data
public class CustomerPointsPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "30355")
    private Long customerId;

    @Schema(description = "可用积分")
    private Long availablePoints;

    @Schema(description = "历史累计赠送积分")
    private Long historyTotalGiftPoints;

    @Schema(description = "历史累计扣减积分")
    private Long historyTotalDeductPoints;

    @Schema(description = "历史累计失效积分")
    private Long historyTotalExpirePoints;

    @Schema(description = "即将失效积分")
    private Long upcomingExpirePoints;

    @Schema(description = "即将失效积分日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] upcomingExpireDate;

    @Schema(description = "积分账户编号")
    private String pointsAccountNo;

    @Schema(description = "积分等级（字典: aicrm_points_level，bronze=铜卡，silver=银卡，gold=金卡，platinum=白金卡，diamond=钻石卡）")
    private String pointsLevel;

    @Schema(description = "累计获得积分（包含赠送和交易）")
    private Long totalEarnedPoints;

    @Schema(description = "累计使用积分")
    private Long totalUsedPoints;

    @Schema(description = "冻结积分")
    private Long frozenPoints;

    @Schema(description = "历史累计交易积分")
    private Long historyTotalTransactionPoints;

    @Schema(description = "历史累计兑换积分")
    private Long historyTotalRedeemPoints;

    @Schema(description = "积分余额（可用+冻结）")
    private Long pointsBalance;

    @Schema(description = "账户状态（字典: aicrm_points_account_status，active=正常，frozen=冻结，closed=关闭）", example = "2")
    private String accountStatus;

    @Schema(description = "开户日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] openDate;

    @Schema(description = "最后交易日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] lastTransactionDate;

    @Schema(description = "最后交易时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastTransactionTime;

    @Schema(description = "最后赠送日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] lastGiftDate;

    @Schema(description = "最后兑换日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] lastRedeemDate;

    @Schema(description = "积分有效期（月）")
    private Integer pointsValidMonths;

    @Schema(description = "是否自动失效")
    private Boolean isAutoExpire;

    @Schema(description = "自动失效提醒天数")
    private Integer autoExpireRemindDays;

    @Schema(description = "本年度获得积分")
    private Long yearEarnedPoints;

    @Schema(description = "本年度使用积分")
    private Long yearUsedPoints;

    @Schema(description = "本月获得积分")
    private Long monthEarnedPoints;

    @Schema(description = "本月使用积分")
    private Long monthUsedPoints;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}