package com.ynet.iplatform.module.grid.controller.admin.activitylog.vo;
import java.time.LocalDate;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 网格活动记录新增/修改 Request VO")
@Data
public class GridActivityLogSaveReqVO {

    @Schema(description = "活动记录ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6353")
    private Long id;

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11071")
    @NotNull(message = "网格ID不能为空")
    private Long gridId;

    @Schema(description = "客户ID (可选)", example = "14082")
    private Long customerId;

    @Schema(description = "活动类型: VISIT/MARKETING/SURVEY/OTHER", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "活动类型: VISIT/MARKETING/SURVEY/OTHER不能为空")
    private String activityType;

    @Schema(description = "活动标题", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "活动标题不能为空")
    private String activityTitle;

    @Schema(description = "活动内容")
    private String activityContent;

    @Schema(description = "活动日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "活动日期不能为空")
    private LocalDate activityDate;

    @Schema(description = "活动地点坐标")
    private byte[] location;

    @Schema(description = "活动地点地址")
    private String address;

    @Schema(description = "执行人ID (关联 system_users)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2352")
    @NotNull(message = "执行人ID (关联 system_users)不能为空")
    private Long staffId;

    @Schema(description = "活动结果: SUCCESS/FAILED/PENDING")
    private String result;

    @Schema(description = "附件列表 [{url, name, type}]")
    private String attachments;

    @Schema(description = "创建人ID", example = "6026")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "23196")
    private Long updaterId;

}