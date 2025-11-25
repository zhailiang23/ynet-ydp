package com.ynet.iplatform.module.aicrm.controller.admin.customertransfer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.Map;

@Schema(description = "管理后台 - 客户移交申请提交 Request VO")
@Data
public class CustomerTransferApplicationApplyReqVO {

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    @Schema(description = "接收方客户经理ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3001")
    @NotNull(message = "接收方客户经理ID不能为空")
    private Long toUserId;

    @Schema(description = "移交原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "业务调整需要移交")
    @NotBlank(message = "移交原因不能为空")
    @Size(max = 500, message = "移交原因长度不能超过500字符")
    private String transferReason;

    @Schema(description = "审批人选择（BPM流程需要）")
    private Map<String, Long> startUserSelectAssignees;

}
