package com.ynet.iplatform.module.grid.controller.admin.customerarchiveprotection.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 客户档案保护记录新增/修改 Request VO")
@Data
public class GridCustomerArchiveProtectionSaveReqVO {

    @Schema(description = "保护记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26534")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "6654")
    @NotNull(message = "客户ID (关联 grid_customer)不能为空")
    private Long customerId;

    @Schema(description = "保护类型: CLAIMED/TIME_BASED", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "保护类型: CLAIMED/TIME_BASED不能为空")
    private String protectionType;

    @Schema(description = "原客户经理ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24793")
    @NotNull(message = "原客户经理ID不能为空")
    private Long originalManagerId;

    @Schema(description = "认领时间")
    private LocalDateTime claimTime;

    @Schema(description = "保护开始时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "保护开始时间不能为空")
    private LocalDateTime protectionStartTime;

    @Schema(description = "保护结束时间")
    private LocalDateTime protectionEndTime;

    @Schema(description = "是否仍受保护")
    private Boolean isProtected;

    @Schema(description = "解除保护原因", example = "不对")
    private String releaseReason;

    @Schema(description = "创建人ID", example = "2339")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "24865")
    private Long updaterId;

}