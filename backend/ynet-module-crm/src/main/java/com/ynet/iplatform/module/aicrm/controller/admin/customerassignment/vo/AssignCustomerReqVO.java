package com.ynet.iplatform.module.aicrm.controller.admin.customerassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Schema(description = "管理后台 - 客户分配 Request VO")
@Data
public class AssignCustomerReqVO {

    @Schema(description = "客户ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "客户ID列表不能为空")
    private List<Long> customerIds;

    @Schema(description = "归属类型（1=主办，2=协办）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "归属类型不能为空")
    private Integer assignmentType;

    @Schema(description = "归属部门ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "归属部门ID不能为空")
    private Long deptId;

    @Schema(description = "客户经理用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2001")
    @NotNull(message = "客户经理用户ID不能为空")
    private Long userId;

    @Schema(description = "是否有查看权限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否有查看权限不能为空")
    private Boolean hasViewRight;

    @Schema(description = "是否有维护权限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否有维护权限不能为空")
    private Boolean hasMaintainRight;

    @Schema(description = "备注", example = "批量分配客户")
    @Size(max = 500, message = "备注长度不能超过500字符")
    private String remark;

}
