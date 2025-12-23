package com.ynet.iplatform.module.grid.controller.admin.keyperson.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 关键人信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class KeyPersonRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "12326")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("姓名")
    private String personName;

    @Schema(description = "单位/小区", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("单位/小区")
    private String organization;

    @Schema(description = "职务")
    @ExcelProperty("职务")
    private String position;

    @Schema(description = "联系方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系方式")
    private String contact;

    @Schema(description = "建立联系日期")
    @ExcelProperty("建立联系日期")
    private LocalDate establishDate;

    @Schema(description = "最新维护日期")
    @ExcelProperty("最新维护日期")
    private LocalDate lastMaintainDate;

    @Schema(description = "员工工号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("员工工号")
    private String employeeCode;

    @Schema(description = "员工姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("员工姓名")
    private String employeeName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}