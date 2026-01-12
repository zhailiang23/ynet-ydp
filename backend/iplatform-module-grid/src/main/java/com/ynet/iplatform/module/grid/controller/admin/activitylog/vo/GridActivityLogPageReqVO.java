package com.ynet.iplatform.module.grid.controller.admin.activitylog.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 网格活动记录分页 Request VO")
@Data
public class GridActivityLogPageReqVO extends PageParam {

    @Schema(description = "网格ID", example = "11071")
    private Long gridId;

    @Schema(description = "客户ID (可选)", example = "14082")
    private Long customerId;

    @Schema(description = "活动类型: VISIT/MARKETING/SURVEY/OTHER", example = "1")
    private String activityType;

    @Schema(description = "活动标题")
    private String activityTitle;

    @Schema(description = "活动内容")
    private String activityContent;

    @Schema(description = "活动日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] activityDate;

    @Schema(description = "活动地点坐标")
    private byte[] location;

    @Schema(description = "活动地点地址")
    private String address;

    @Schema(description = "执行人ID (关联 system_users)", example = "2352")
    private Long staffId;

    @Schema(description = "活动结果: SUCCESS/FAILED/PENDING")
    private String result;

    @Schema(description = "附件列表 [{url, name, type}]")
    private String attachments;

    @Schema(description = "创建人ID", example = "6026")
    private Long creatorId;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人ID", example = "23196")
    private Long updaterId;

}