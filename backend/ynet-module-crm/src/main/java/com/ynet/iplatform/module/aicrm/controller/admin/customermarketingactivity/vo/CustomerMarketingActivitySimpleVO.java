package com.ynet.iplatform.module.aicrm.controller.admin.customermarketingactivity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Schema(description = "管理后台 - 客户营销活动简化信息 Response VO")
@Data
public class CustomerMarketingActivitySimpleVO {

    @Schema(description = "营销活动主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    private Long id;

    @Schema(description = "活动名称", example = "春季产品推广活动")
    private String activityName;

}
