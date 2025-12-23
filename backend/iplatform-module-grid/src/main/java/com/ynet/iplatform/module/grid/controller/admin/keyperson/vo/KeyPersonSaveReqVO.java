package com.ynet.iplatform.module.grid.controller.admin.keyperson.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 关键人信息新增/修改 Request VO")
@Data
public class KeyPersonSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12326")
    private Long id;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "姓名不能为空")
    private String personName;

    @Schema(description = "单位/小区", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "单位/小区不能为空")
    private String organization;

    @Schema(description = "职务")
    private String position;

    @Schema(description = "联系方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系方式不能为空")
    private String contact;

    @Schema(description = "建立联系日期")
    private LocalDate establishDate;

    @Schema(description = "最新维护日期")
    private LocalDate lastMaintainDate;

    @Schema(description = "员工工号（系统自动填充，无需传入）", hidden = true)
    private String employeeCode;

    @Schema(description = "员工姓名（系统自动填充，无需传入）", hidden = true)
    private String employeeName;

}