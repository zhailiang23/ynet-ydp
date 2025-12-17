package com.ynet.iplatform.module.grid.controller.admin.activitylog.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 网格活动记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridActivityLogRespVO {

    @Schema(description = "活动记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6353")
    @ExcelProperty("活动记录ID")
    private Long id;

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11071")
    @ExcelProperty("网格ID")
    private Long gridId;

    @Schema(description = "客户ID (可选)", example = "14082")
    @ExcelProperty("客户ID (可选)")
    private Long customerId;

    @Schema(description = "活动类型: VISIT/MARKETING/SURVEY/OTHER", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("活动类型: VISIT/MARKETING/SURVEY/OTHER")
    private String activityType;

    @Schema(description = "活动标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("活动标题")
    private String activityTitle;

    @Schema(description = "活动内容")
    @ExcelProperty("活动内容")
    private String activityContent;

    @Schema(description = "活动日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("活动日期")
    private LocalDate activityDate;

    @Schema(description = "活动地点坐标")
    @ExcelProperty("活动地点坐标")
    private byte[] location;

    @Schema(description = "活动地点地址")
    @ExcelProperty("活动地点地址")
    private String address;

    @Schema(description = "执行人ID (关联 system_users)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2352")
    @ExcelProperty("执行人ID (关联 system_users)")
    private Long staffId;

    @Schema(description = "活动结果: SUCCESS/FAILED/PENDING")
    @ExcelProperty("活动结果: SUCCESS/FAILED/PENDING")
    private String result;

    @Schema(description = "附件列表 [{url, name, type}]")
    @ExcelProperty("附件列表 [{url, name, type}]")
    private String attachments;

    @Schema(description = "创建人ID", example = "6026")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "23196")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}