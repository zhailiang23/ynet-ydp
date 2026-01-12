package com.ynet.iplatform.module.aicrm.controller.app.potentialcustomer.vo;

import com.ynet.iplatform.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Schema(description = "移动端 - 潜客分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AppPotentialCustomerPageReqVO extends PageParam {

    @Schema(description = "洞察ID", example = "1")
    private Long insightId;

    @Schema(description = "潜客状态", example = "NEW")
    private String status;

    @Schema(description = "最小AI匹配度", example = "80")
    private Integer minAiMatchScore;

}
