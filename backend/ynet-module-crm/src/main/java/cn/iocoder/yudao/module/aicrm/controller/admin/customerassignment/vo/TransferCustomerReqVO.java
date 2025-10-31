package cn.iocoder.yudao.module.aicrm.controller.admin.customerassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Schema(description = "管理后台 - 客户移交 Request VO")
@Data
public class TransferCustomerReqVO {

    @Schema(description = "客户ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "客户ID列表不能为空")
    private List<Long> customerIds;

    @Schema(description = "移交给客户经理ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @NotNull(message = "移交给客户经理ID不能为空")
    private Long toUserId;

    @Schema(description = "移交原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "客户经理调岗")
    @NotBlank(message = "移交原因不能为空")
    @Size(max = 500, message = "移交原因长度不能超过500字符")
    private String transferReason;

}
