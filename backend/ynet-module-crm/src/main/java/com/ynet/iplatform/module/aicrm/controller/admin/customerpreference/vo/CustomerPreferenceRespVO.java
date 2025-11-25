package com.ynet.iplatform.module.aicrm.controller.admin.customerpreference.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import java.time.LocalDate;

@Schema(description = "管理后台 - 客户偏好 Response VO")
@Data
public class CustomerPreferenceRespVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "客户ID", example = "1001")
    private Long customerId;

    @Schema(description = "电子渠道（多选，逗号分隔）", example = "mobile_bank,online_bank")
    private String electronicChannels;

    @Schema(description = "其他电子渠道", example = "支付宝")
    private String otherChannel;

    @Schema(description = "投资类型（多选，逗号分隔）", example = "1,2,3")
    private String investmentTypes;

    @Schema(description = "其他投资类型", example = "数字货币")
    private String otherInvestmentType;

    @Schema(description = "品牌类型（多选，逗号分隔）", example = "1,2")
    private String brandTypes;

    @Schema(description = "其他品牌类型", example = "海外品牌")
    private String otherBrandType;

    @Schema(description = "理财服务（多选，逗号分隔）", example = "1,2,3")
    private String financialServices;

    @Schema(description = "其他理财服务", example = "投资咨询")
    private String otherFinancialService;

    @Schema(description = "联系方式（多选，逗号分隔）", example = "1,2")
    private String contactMethods;

    @Schema(description = "其他联系方式", example = "视频通话")
    private String otherContactMethod;

    @Schema(description = "沙龙活动（多选，逗号分隔）", example = "1,2,3")
    private String salonActivities;

    @Schema(description = "其他沙龙活动", example = "户外活动")
    private String otherSalonActivity;

    @Schema(description = "兴趣爱好（多选，逗号分隔）", example = "1,2,3")
    private String interestHobbies;

    @Schema(description = "其他兴趣爱好", example = "摄影")
    private String otherInterestHobby;

    @Schema(description = "联系时间（多选，逗号分隔）", example = "1,2")
    private String contactTimes;

    @Schema(description = "其他联系时间", example = "周末上午")
    private String otherContactTime;

    @Schema(description = "投资周期（多选，逗号分隔）", example = "1,2")
    private String investmentPeriods;

    @Schema(description = "其他投资周期", example = "5-10年")
    private String otherInvestmentPeriod;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
