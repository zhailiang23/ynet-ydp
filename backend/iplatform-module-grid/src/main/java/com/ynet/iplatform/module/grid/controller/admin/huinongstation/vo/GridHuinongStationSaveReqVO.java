package com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 惠农站点信息新增/修改 Request VO")
@Data
public class GridHuinongStationSaveReqVO {

    @Schema(description = "站点ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30428")
    private Long id;

    @Schema(description = "站点编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "站点编号不能为空")
    private String stationCode;

    @Schema(description = "站点名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "站点名称不能为空")
    private String stationName;

    @Schema(description = "站点类型", example = "2")
    private String stationType;

    @Schema(description = "站点坐标", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "站点坐标不能为空")
    private byte[] location;

    @Schema(description = "站点地址")
    private String address;

    @Schema(description = "关联的网格ID", example = "13533")
    private Long gridId;

    @Schema(description = "网格营销站点标识: REQUIRED(必选)/OPTIONAL(可选)")
    private String gridMarketingFlag;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "负责惠农专员ID (关联 system_users)", example = "20186")
    private Long specialistId;

    @Schema(description = "站点状态: ACTIVE/INACTIVE", example = "2")
    private String status;

    @Schema(description = "数据来源: IMPORTED/MANUAL")
    private String dataSource;

    @Schema(description = "导入批次号")
    private String importBatch;

    @Schema(description = "导入时间")
    private LocalDateTime importTime;

    @Schema(description = "创建人ID", example = "16805")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "26847")
    private Long updaterId;

}