package com.ynet.iplatform.module.grid.controller.admin.community.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 社区信息 Response VO")
@Data
public class CommunityRespVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "社区编号", example = "COMM_1234567890")
    private String communityCode;

    @Schema(description = "社区名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "阳光社区")
    private String communityName;

    @Schema(description = "团队人数", example = "10")
    private Integer teamCount;

    @Schema(description = "责任人工号", example = "1")
    private Long managerUserId;

    @Schema(description = "责任人姓名", example = "张三")
    private String managerUserName;

    @Schema(description = "人口（户数）", example = "1000")
    private Integer householdCount;

    @Schema(description = "个体工商户数", example = "50")
    private Integer individualBusinessCount;

    @Schema(description = "小微企业数", example = "20")
    private Integer smallEnterpriseCount;

    @Schema(description = "商业综合体数", example = "2")
    private Integer commercialComplexCount;

    @Schema(description = "优质单位数", example = "5")
    private Integer qualityUnitCount;

    @Schema(description = "评分", example = "85.50")
    private BigDecimal score;

    @Schema(description = "校正评分", example = "90.00")
    private BigDecimal adjustedScore;

    @Schema(description = "校正原因", example = "根据实地调研情况调整")
    private String adjustmentReason;

    @Schema(description = "经度", example = "113.625368")
    private BigDecimal longitude;

    @Schema(description = "纬度", example = "34.746611")
    private BigDecimal latitude;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "ACTIVE")
    private String status;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;
}
