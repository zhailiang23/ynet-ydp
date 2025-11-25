package com.ynet.iplatform.module.aicrm.controller.admin.customerclaim.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.Map;

@Schema(description = "管理后台 - 客户认领申请提交 Request VO")
@Data
public class CustomerClaimApplicationApplyReqVO {

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    @Schema(description = "申请理由", requiredMode = Schema.RequiredMode.REQUIRED, example = "该客户属于我的潜在客户")
    @NotBlank(message = "申请理由不能为空")
    @Size(max = 500, message = "申请理由长度不能超过500字符")
    private String applyReason;

    @Schema(description = "审批人选择（BPM流程需要）")
    private Map<String, Long> startUserSelectAssignees;

}
