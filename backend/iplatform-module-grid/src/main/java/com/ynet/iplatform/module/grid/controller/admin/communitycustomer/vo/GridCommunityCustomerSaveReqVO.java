package com.ynet.iplatform.module.grid.controller.admin.communitycustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 社区客户扩展新增/修改 Request VO")
@Data
public class GridCommunityCustomerSaveReqVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "859")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "7456")
    @NotNull(message = "客户ID (关联 grid_customer)不能为空")
    private Long customerId;

    @Schema(description = "家庭成员数")
    private Integer familyMembers;

    @Schema(description = "住房类型", example = "1")
    private String housingType;

    @Schema(description = "月收入 (元)")
    private BigDecimal monthlyIncome;

    @Schema(description = "创建人ID", example = "25814")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "18958")
    private Long updaterId;

}