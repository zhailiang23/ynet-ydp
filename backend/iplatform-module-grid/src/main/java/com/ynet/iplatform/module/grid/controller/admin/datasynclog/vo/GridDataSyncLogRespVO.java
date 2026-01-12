package com.ynet.iplatform.module.grid.controller.admin.datasynclog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 数据同步记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridDataSyncLogRespVO {

    @Schema(description = "同步记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11712")
    @ExcelProperty("同步记录ID")
    private Long id;

    @Schema(description = "同步类型: ESB_LINGXI/DW_BATCH", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("同步类型: ESB_LINGXI/DW_BATCH")
    private String syncType;

    @Schema(description = "同步方向: PULL/PUSH", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("同步方向: PULL/PUSH")
    private String syncDirection;

    @Schema(description = "实体类型: GRID/CUSTOMER/STATION/ACTIVITY", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("实体类型: GRID/CUSTOMER/STATION/ACTIVITY")
    private String entityType;

    @Schema(description = "实体ID", example = "14209")
    @ExcelProperty("实体ID")
    private Long entityId;

    @Schema(description = "同步状态: SUCCESS/FAILED/PENDING", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("同步状态: SUCCESS/FAILED/PENDING")
    private String syncStatus;

    @Schema(description = "同步时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("同步时间")
    private LocalDateTime syncTime;

    @Schema(description = "请求数据")
    @ExcelProperty("请求数据")
    private String requestData;

    @Schema(description = "响应数据")
    @ExcelProperty("响应数据")
    private String responseData;

    @Schema(description = "错误信息")
    @ExcelProperty("错误信息")
    private String errorMessage;

    @Schema(description = "重试次数", example = "21107")
    @ExcelProperty("重试次数")
    private Integer retryCount;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}