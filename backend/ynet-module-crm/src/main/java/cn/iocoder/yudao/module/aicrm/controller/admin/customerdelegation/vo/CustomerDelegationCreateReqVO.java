package cn.iocoder.yudao.module.aicrm.controller.admin.customerdelegation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Schema(description = "管理后台 - 客户托管创建 Request VO")
@Data
public class CustomerDelegationCreateReqVO {

    @Schema(description = "客户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "客户ID不能为空")
    private Long customerId;

    @Schema(description = "受托方客户经理ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3001")
    @NotNull(message = "受托方客户经理ID不能为空")
    private Long toUserId;

    @Schema(description = "托管开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "托管开始日期不能为空")
    private LocalDate startDate;

    @Schema(description = "托管结束日期（计划）")
    private LocalDate endDate;

    @Schema(description = "托管原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "临时出差")
    @NotBlank(message = "托管原因不能为空")
    @Size(max = 500, message = "托管原因长度不能超过500字符")
    private String delegationReason;

}
