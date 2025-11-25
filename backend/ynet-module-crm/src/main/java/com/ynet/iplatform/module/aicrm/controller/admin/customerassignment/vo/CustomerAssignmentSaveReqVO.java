package com.ynet.iplatform.module.aicrm.controller.admin.customerassignment.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Schema(description = "管理后台 - 客户归属关系表（零售+对公共用，支持主协办模式）新增/修改 Request VO")
@Data
public class CustomerAssignmentSaveReqVO {

    @Schema(description = "归属关系主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "9881")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "27081")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "归属类型（1=主办，2=协办）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "归属类型（1=主办，2=协办）不能为空")
    private Integer assignmentType;

    @Schema(description = "归属部门ID（关联 system_dept.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29683")
    @NotNull(message = "归属部门ID（关联 system_dept.id）不能为空")
    private Long deptId;

    @Schema(description = "客户经理用户ID（关联 system_users.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "9405")
    @NotNull(message = "客户经理用户ID（关联 system_users.id）不能为空")
    private Long userId;

    @Schema(description = "是否有查看权限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否有查看权限不能为空")
    private Boolean hasViewRight;

    @Schema(description = "是否有维护权限", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否有维护权限不能为空")
    private Boolean hasMaintainRight;

    @Schema(description = "分配日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "分配日期不能为空")
    private LocalDate assignDate;

    @Schema(description = "生效日期")
    private LocalDate effectiveDate;

    @Schema(description = "失效日期（NULL表示长期有效）")
    private LocalDate expiryDate;

    @Schema(description = "分配操作人用户ID（关联 system_users.id）", example = "20346")
    private Long assignOperatorId;

    @Schema(description = "归属状态（0=已失效，1=生效中，2=待生效）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotNull(message = "归属状态（0=已失效，1=生效中，2=待生效）不能为空")
    private Integer status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}