package com.ynet.iplatform.module.aicrm.controller.admin.potentialcustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 潜客新增/修改 Request VO")
@Data
public class PotentialCustomerSaveReqVO {

    @Schema(description = "潜客编号", example = "1")
    private Long id;

    @Schema(description = "客户姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotBlank(message = "客户姓名不能为空")
    private String customerName;

    @Schema(description = "客户头像URL", example = "https://...")
    private String avatar;

    @Schema(description = "手机号码", example = "13900139000")
    private String phone;

    @Schema(description = "风险等级", requiredMode = Schema.RequiredMode.REQUIRED, example = "AGGRESSIVE")
    @NotBlank(message = "风险等级不能为空")
    private String riskLevel;

    @Schema(description = "客户等级", example = "A")
    private String customerLevel;

    @Schema(description = "资产管理规模", requiredMode = Schema.RequiredMode.REQUIRED, example = "1250000.00")
    @NotNull(message = "资产管理规模不能为空")
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

    @Schema(description = "潜客状态", example = "NEW")
    private String status;

    @Schema(description = "来源", example = "AI_RECOMMENDATION")
    private String source;

    @Schema(description = "分配给的客户经理ID", example = "1")
    private Long assignedUserId;

    @Schema(description = "下次跟进时间")
    private LocalDateTime nextFollowupTime;

    @Schema(description = "备注", example = "重点跟进客户")
    private String remark;

}
