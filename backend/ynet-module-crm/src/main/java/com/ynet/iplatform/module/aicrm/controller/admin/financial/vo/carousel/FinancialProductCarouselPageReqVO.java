package com.ynet.iplatform.module.aicrm.controller.admin.financial.vo.carousel;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * CRM 金融产品轮播 分页 Request VO
 *
 * @author 易诚源码
 */
@Schema(description = "管理后台 - CRM 金融产品轮播分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class FinancialProductCarouselPageReqVO extends PageParam {

    @Schema(description = "轮播标题", example = "早报精选")
    private String title;

    @Schema(description = "状态", example = "1")
    private Integer status;

}
