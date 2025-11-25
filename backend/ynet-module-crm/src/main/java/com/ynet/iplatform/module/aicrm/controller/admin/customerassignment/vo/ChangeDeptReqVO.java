package com.ynet.iplatform.module.aicrm.controller.admin.customerassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Schema(description = "管理后台 - 主办变更 Request VO")
@Data
public class ChangeDeptReqVO {

    @Schema(description = "客户ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "客户ID列表不能为空")
    private List<Long> customerIds;

    @Schema(description = "新的主办部门ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "新的主办部门ID不能为空")
    private Long newDeptId;

    @Schema(description = "新的客户经理ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @NotNull(message = "新的客户经理ID不能为空")
    private Long newUserId;

    @Schema(description = "变更原因", requiredMode = Schema.RequiredMode.REQUIRED, example = "业务调整")
    @NotBlank(message = "变更原因不能为空")
    @Size(max = 500, message = "变更原因长度不能超过500字符")
    private String changeReason;

}
