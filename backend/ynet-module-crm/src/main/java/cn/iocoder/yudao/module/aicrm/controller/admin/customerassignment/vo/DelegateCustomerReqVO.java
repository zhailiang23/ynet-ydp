package cn.iocoder.yudao.module.aicrm.controller.admin.customerassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Schema(description = "管理后台 - 托管客户 Request VO")
@Data
public class DelegateCustomerReqVO {

    @Schema(description = "客户ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "客户ID列表不能为空")
    private List<Long> customerIds;

    @Schema(description = "托管给的用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "托管用户不能为空")
    private Long delegateToUserId;

    @Schema(description = "托管开始日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "托管开始日期不能为空")
    private LocalDate delegateStartDate;

    @Schema(description = "托管结束日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "托管结束日期不能为空")
    private LocalDate delegateEndDate;

    @Schema(description = "托管原因", example = "出差期间临时托管")
    private String delegateReason;

}
