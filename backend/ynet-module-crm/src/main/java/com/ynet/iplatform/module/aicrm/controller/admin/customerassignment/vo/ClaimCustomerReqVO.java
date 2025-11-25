package com.ynet.iplatform.module.aicrm.controller.admin.customerassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Schema(description = "管理后台 - 客户认领 Request VO")
@Data
public class ClaimCustomerReqVO {

    @Schema(description = "客户ID列表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "客户ID列表不能为空")
    private List<Long> customerIds;

    @Schema(description = "备注", example = "认领未分配客户")
    @Size(max = 500, message = "备注长度不能超过500字符")
    private String remark;

}
