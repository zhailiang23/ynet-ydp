package com.ynet.iplatform.module.grid.controller.admin.datasynclog.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 数据同步记录分页 Request VO")
@Data
public class GridDataSyncLogPageReqVO extends PageParam {

    @Schema(description = "同步类型: ESB_LINGXI/DW_BATCH", example = "1")
    private String syncType;

    @Schema(description = "同步方向: PULL/PUSH")
    private String syncDirection;

    @Schema(description = "实体类型: GRID/CUSTOMER/STATION/ACTIVITY", example = "1")
    private String entityType;

    @Schema(description = "实体ID", example = "14209")
    private Long entityId;

    @Schema(description = "同步状态: SUCCESS/FAILED/PENDING", example = "2")
    private String syncStatus;

    @Schema(description = "同步时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] syncTime;

    @Schema(description = "请求数据")
    private String requestData;

    @Schema(description = "响应数据")
    private String responseData;

    @Schema(description = "错误信息")
    private String errorMessage;

    @Schema(description = "重试次数", example = "21107")
    private Integer retryCount;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}