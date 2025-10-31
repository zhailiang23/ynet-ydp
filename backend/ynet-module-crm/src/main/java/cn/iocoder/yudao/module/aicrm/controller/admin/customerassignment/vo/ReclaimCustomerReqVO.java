package cn.iocoder.yudao.module.aicrm.controller.admin.customerassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Schema(description = "管理后台 - 客户回收 Request VO")
@Data
public class ReclaimCustomerReqVO {

    @Schema(description = "客户ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "客户ID列表不能为空")
    private List<Long> customerIds;

    @Schema(description = "回收原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "客户经理离职")
    @NotBlank(message = "回收原因不能为空")
    @Size(max = 500, message = "回收原因长度不能超过500字符")
    private String reclaimReason;

}
