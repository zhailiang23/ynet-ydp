package com.ynet.iplatform.module.grid.controller.admin.competitorinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 同业信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CompetitorInfoRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "5225")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "同业网点名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("同业网点名称")
    private String competitorName;

    @Schema(description = "详细地址", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("详细地址")
    private String address;

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private BigDecimal latitude;

    @Schema(description = "主要经营策略", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主要经营策略")
    private String businessStrategy;

    @Schema(description = "核心客户群", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("核心客户群")
    private String coreCustomers;

    @Schema(description = "产品优势", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("产品优势")
    private String productAdvantages;

    @Schema(description = "产品劣势", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("产品劣势")
    private String productDisadvantages;

    @Schema(description = "员工工号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("员工工号")
    private String employeeCode;

    @Schema(description = "员工姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("员工姓名")
    private String employeeName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}