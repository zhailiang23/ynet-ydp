package com.ynet.iplatform.module.grid.controller.admin.competitorinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 同业信息新增/修改 Request VO")
@Data
public class CompetitorInfoSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5225")
    private Long id;

    @Schema(description = "同业网点名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "同业网点名称不能为空")
    private String competitorName;

    @Schema(description = "详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "详细地址不能为空")
    private String address;

    @Schema(description = "经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    private BigDecimal latitude;

    @Schema(description = "主要经营策略", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主要经营策略不能为空")
    private String businessStrategy;

    @Schema(description = "核心客户群", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "核心客户群不能为空")
    private String coreCustomers;

    @Schema(description = "产品优势", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "产品优势不能为空")
    private String productAdvantages;

    @Schema(description = "产品劣势", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "产品劣势不能为空")
    private String productDisadvantages;

    @Schema(description = "员工工号（系统自动填充，无需传入）", hidden = true)
    private String employeeCode;

    @Schema(description = "员工姓名（系统自动填充，无需传入）", hidden = true)
    private String employeeName;

}