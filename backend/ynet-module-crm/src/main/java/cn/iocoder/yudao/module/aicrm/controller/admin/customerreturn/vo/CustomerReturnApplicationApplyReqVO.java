package cn.iocoder.yudao.module.aicrm.controller.admin.customerreturn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.Map;

@Schema(description = "管理后台 - 客户退回申请提交 Request VO")
@Data
public class CustomerReturnApplicationApplyReqVO {

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    @Schema(description = "退回给主管ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3001")
    @NotNull(message = "主管ID不能为空")
    private Long returnToUserId;

    @Schema(description = "退回原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "无法继续维护该客户")
    @NotBlank(message = "退回原因不能为空")
    @Size(max = 500, message = "退回原因长度不能超过500字符")
    private String returnReason;

    @Schema(description = "审批人选择（BPM流程需要）")
    private Map<String, Long> startUserSelectAssignees;

}
