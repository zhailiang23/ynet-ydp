package com.ynet.iplatform.module.grid.controller.admin.datasynclog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 数据同步记录新增/修改 Request VO")
@Data
public class GridDataSyncLogSaveReqVO {

    @Schema(description = "同步记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11712")
    private Long id;

    @Schema(description = "同步类型: ESB_LINGXI/DW_BATCH", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "同步类型: ESB_LINGXI/DW_BATCH不能为空")
    private String syncType;

    @Schema(description = "同步方向: PULL/PUSH", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "同步方向: PULL/PUSH不能为空")
    private String syncDirection;

    @Schema(description = "实体类型: GRID/CUSTOMER/STATION/ACTIVITY", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "实体类型: GRID/CUSTOMER/STATION/ACTIVITY不能为空")
    private String entityType;

    @Schema(description = "实体ID", example = "14209")
    private Long entityId;

    @Schema(description = "同步状态: SUCCESS/FAILED/PENDING", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "同步状态: SUCCESS/FAILED/PENDING不能为空")
    private String syncStatus;

    @Schema(description = "同步时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "同步时间不能为空")
    private LocalDateTime syncTime;

    @Schema(description = "请求数据")
    private String requestData;

    @Schema(description = "响应数据")
    private String responseData;

    @Schema(description = "错误信息")
    private String errorMessage;

    @Schema(description = "重试次数", example = "21107")
    private Integer retryCount;

}