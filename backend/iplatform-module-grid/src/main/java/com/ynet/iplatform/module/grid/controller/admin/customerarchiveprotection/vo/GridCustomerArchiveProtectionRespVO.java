package com.ynet.iplatform.module.grid.controller.admin.customerarchiveprotection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户档案保护记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridCustomerArchiveProtectionRespVO {

    @Schema(description = "保护记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26534")
    @ExcelProperty("保护记录ID")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "6654")
    @ExcelProperty("客户ID (关联 grid_customer)")
    private Long customerId;

    @Schema(description = "保护类型: CLAIMED/TIME_BASED", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("保护类型: CLAIMED/TIME_BASED")
    private String protectionType;

    @Schema(description = "原客户经理ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24793")
    @ExcelProperty("原客户经理ID")
    private Long originalManagerId;

    @Schema(description = "认领时间")
    @ExcelProperty("认领时间")
    private LocalDateTime claimTime;

    @Schema(description = "保护开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("保护开始时间")
    private LocalDateTime protectionStartTime;

    @Schema(description = "保护结束时间")
    @ExcelProperty("保护结束时间")
    private LocalDateTime protectionEndTime;

    @Schema(description = "是否仍受保护")
    @ExcelProperty("是否仍受保护")
    private Boolean isProtected;

    @Schema(description = "解除保护原因", example = "不对")
    @ExcelProperty("解除保护原因")
    private String releaseReason;

    @Schema(description = "创建人ID", example = "2339")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "24865")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}