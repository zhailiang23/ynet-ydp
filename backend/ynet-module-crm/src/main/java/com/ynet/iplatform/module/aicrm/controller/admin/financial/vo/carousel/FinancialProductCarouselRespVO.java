package com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * CRM 金融产品轮播 Response VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - CRM 金融产品轮播 Response VO")
@Data
public class FinancialProductCarouselRespVO {

    @Schema(description = "轮播编号", example = "1")
    private Long id;

    @Schema(description = "轮播标题", example = "早报精选")
    private String title;

    @Schema(description = "轮播副标题", example = "今日理财推荐")
    private String subtitle;

    @Schema(description = "轮播图片URL")
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

    @Schema(description = "显示顺序", example = "1")
    private Integer sort;

    @Schema(description = "状态", example = "1")
    private Integer status;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

}
