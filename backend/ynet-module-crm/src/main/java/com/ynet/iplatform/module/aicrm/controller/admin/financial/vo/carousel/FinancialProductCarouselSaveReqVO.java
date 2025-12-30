package com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * CRM 金融产品轮播 新增/修改 Request VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - CRM 金融产品轮播新增/修改 Request VO")
@Data
public class FinancialProductCarouselSaveReqVO {

    @Schema(description = "轮播编号", example = "1")
    private Long id;

    @Schema(description = "轮播标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "早报精选")
    @NotBlank(message = "轮播标题不能为空")
    private String title;

    @Schema(description = "轮播副标题", example = "今日理财推荐")
    private String subtitle;

    @Schema(description = "轮播图片URL", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "轮播图片URL不能为空")
    private String imageUrl;

    @Schema(description = "链接类型", example = "product")
    private String linkType;

    @Schema(description = "关联产品ID", example = "1")
    private Long linkId;

    @Schema(description = "外部链接URL")
    private String linkUrl;

    @Schema(description = "角标文字", example = "AI 洞察")
    private String badgeText;

    @Schema(description = "角标颜色", example = "#FF6B6B")
    private String badgeColor;

    @Schema(description = "显示顺序", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "显示顺序不能为空")
    private Integer sort;

    @Schema(description = "状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "状态不能为空")
    private Integer status;

}
