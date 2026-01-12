package com.ynet.iplatform.module.grid.controller.admin.customerarchiveprotection.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户档案保护记录分页 Request VO")
@Data
public class GridCustomerArchiveProtectionPageReqVO extends PageParam {

    @Schema(description = "客户ID (关联 grid_customer)", example = "6654")
    private Long customerId;

    @Schema(description = "保护类型: CLAIMED/TIME_BASED", example = "2")
    private String protectionType;

    @Schema(description = "原客户经理ID", example = "24793")
    private Long originalManagerId;

    @Schema(description = "认领时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] claimTime;

    @Schema(description = "保护开始时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] protectionStartTime;

    @Schema(description = "保护结束时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] protectionEndTime;

    @Schema(description = "是否仍受保护")
    private Boolean isProtected;

    @Schema(description = "解除保护原因", example = "不对")
    private String releaseReason;

    @Schema(description = "创建人ID", example = "2339")
    private Long creatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID", example = "24865")
    private Long updaterId;

}