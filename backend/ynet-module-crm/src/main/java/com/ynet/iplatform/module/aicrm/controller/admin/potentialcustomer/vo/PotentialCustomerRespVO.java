package com.ynet.iplatform.module.aicrm.controller.admin.potentialcustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 潜客 Response VO")
@Data
public class PotentialCustomerRespVO {

    @Schema(description = "潜客编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "关联客户ID", example = "1")
    private Long customerId;

    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    private String customerName;

    @Schema(description = "客户头像URL", example = "https://...")
    private String avatar;

    @Schema(description = "手机号码", example = "13900139000")
    private String phone;

    @Schema(description = "身份证号", example = "110101199001011234")
    private String idCard;

    @Schema(description = "风险等级", requiredMode = Schema.RequiredMode.REQUIRED, example = "AGGRESSIVE")
    private String riskLevel;

    @Schema(description = "客户等级", example = "A")
    private String customerLevel;

    @Schema(description = "资产管理规模", requiredMode = Schema.RequiredMode.REQUIRED, example = "1250000.00")
    private BigDecimal aum;

    @Schema(description = "潜在价值", example = "300000.00")
    private BigDecimal potentialValue;

    @Schema(description = "AI 匹配度（0-100）", example = "98")
    private Integer aiMatchScore;

    @Schema(description = "AI 分析结论", example = "客户持有科技精选A目前浮亏5%...")
    private String analysisConclusion;

    @Schema(description = "洞察ID", example = "1")
    private Long insightId;

    @Schema(description = "洞察标题", example = "科技类基金回调机会")
    private String insightTitle;

    @Schema(description = "潜客状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "NEW")
    private String status;

    @Schema(description = "来源", example = "AI_RECOMMENDATION")
    private String source;

    @Schema(description = "分配给的客户经理ID", example = "1")
    private Long assignedUserId;

    @Schema(description = "客户经理姓名", example = "王经理")
    private String assignedUserName;

    @Schema(description = "最后联系时间")
    private LocalDateTime lastContactTime;

    @Schema(description = "下次跟进时间")
    private LocalDateTime nextFollowupTime;

    @Schema(description = "跟进次数", example = "3")
    private Integer followupCount;

    @Schema(description = "备注", example = "重点跟进客户")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime updateTime;

}
