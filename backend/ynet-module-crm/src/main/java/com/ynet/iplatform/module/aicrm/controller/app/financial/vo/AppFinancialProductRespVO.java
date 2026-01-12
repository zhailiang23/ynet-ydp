package com.ynet.iplatform.module.aicrm.controller.app.financial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "移动端 - 金融产品 Response VO")
@Data
public class AppFinancialProductRespVO {

    @Schema(description = "产品编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "产品代码", requiredMode = Schema.RequiredMode.REQUIRED, example = "FY001")
    private String productCode;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "富盈稳健1号")
    private String productName;

    @Schema(description = "产品目录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long catalogId;

    @Schema(description = "产品分类", requiredMode = Schema.RequiredMode.REQUIRED, example = "理财")
    private String category;

    @Schema(description = "风险等级", requiredMode = Schema.RequiredMode.REQUIRED, example = "R2")
    private String riskLevel;

    @Schema(description = "预期收益率（%）", example = "3.5")
    private BigDecimal expectedReturn;

    @Schema(description = "收益类型", example = "业绩比较基准")
    private String returnType;

    @Schema(description = "产品期限", example = "360天")
    private String duration;

    @Schema(description = "期限天数", example = "360")
    private Integer durationDays;

    @Schema(description = "起购金额", requiredMode = Schema.RequiredMode.REQUIRED, example = "10000")
    private BigDecimal minimumInvestment;

    @Schema(description = "金额单位", example = "元")
    private String investmentUnit;

    @Schema(description = "产品状态（0=停售 1=在售）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Integer status;

    @Schema(description = "是否热销", example = "1")
    private Integer isHot;

    @Schema(description = "是否新品", example = "0")
    private Integer isNew;

    @Schema(description = "产品描述", example = "稳健型理财产品")
    private String description;

    @Schema(description = "产品特色（JSON 数组）", example = "[\"低风险\", \"稳定收益\"]")
    private List<String> features;

    @Schema(description = "销售渠道", example = "自营")
    private String saleChannel;

    @Schema(description = "产品标签（JSON 数组）", example = "[\"高收益\", \"低风险\"]")
    private List<String> tags;

    @Schema(description = "AI 匹配度（0-100）", example = "85")
    private Integer aiMatchScore;

    @Schema(description = "AI 关键词", example = "稳健,低风险,固定收益")
    private String aiKeywords;

    @Schema(description = "显示顺序", example = "1")
    private Integer sort;

    @Schema(description = "轮播图片 URL", example = "https://example.com/banner.jpg")
    private String bannerImage;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    private LocalDateTime createTime;

}
