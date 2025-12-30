package com.ynet.iplatform.module.aicrm.controller.app.financial.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "移动端 - 轮播推荐 Response VO")
@Data
public class AppCarouselRespVO {

    @Schema(description = "轮播编号", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "轮播标题", requiredMode = Schema.RequiredMode.REQUIRED, example = "早报精选")
    private String title;

    @Schema(description = "轮播副标题", example = "今日理财推荐")
    private String subtitle;

    @Schema(description = "轮播图片URL", requiredMode = Schema.RequiredMode.REQUIRED, example = "https://example.com/carousel1.jpg")
    private String imageUrl;

    @Schema(description = "链接类型（product=产品详情 url=外部链接）", example = "product")
    private String linkType;

    @Schema(description = "关联产品ID", example = "1")
    private Long linkId;

    @Schema(description = "外部链接URL", example = "https://example.com/promotion")
    private String linkUrl;

    @Schema(description = "角标文字", example = "早报精选")
    private String badgeText;

    @Schema(description = "角标颜色", example = "#FF6B6B")
    private String badgeColor;

    @Schema(description = "显示顺序", example = "1")
    private Integer sort;

}
