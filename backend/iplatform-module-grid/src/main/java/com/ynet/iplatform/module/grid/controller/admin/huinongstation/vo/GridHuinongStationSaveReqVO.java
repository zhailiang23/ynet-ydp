package com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 惠农站点信息新增/修改 Request VO")
@Data
public class GridHuinongStationSaveReqVO {

    @Schema(description = "站点ID", example = "30428")
    private Long id;

    @Schema(description = "站点编号（新建时自动生成）")
    private String stationCode;

    @Schema(description = "站点名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "郑州惠农站")
    @NotEmpty(message = "站点名称不能为空")
    private String stationName;

    @Schema(description = "所属机构ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属机构不能为空")
    private Long orgId;

    @Schema(description = "站点类型", example = "惠农")
    private String stationType;

    @Schema(description = "站点地址", example = "河南省郑州市金水区")
    private String address;

    @Schema(description = "经度", requiredMode = Schema.RequiredMode.REQUIRED, example = "113.6234")
    @NotNull(message = "经度不能为空")
    private BigDecimal longitude;

    @Schema(description = "纬度", requiredMode = Schema.RequiredMode.REQUIRED, example = "34.7490")
    @NotNull(message = "纬度不能为空")
    private BigDecimal latitude;

    @Schema(description = "圆形半径（米）", requiredMode = Schema.RequiredMode.REQUIRED, example = "3000")
    @NotNull(message = "圆形半径不能为空")
    private Integer radiusMeters;

    @Schema(description = "惠农人员工号（系统自动填充，无需传入）", hidden = true)
    private String specialistEmployeeCode;

    @Schema(description = "惠农人员姓名（系统自动填充，无需传入）", hidden = true)
    private String specialistName;

    @Schema(description = "关联的网格ID", example = "13533")
    private Long gridId;

    @Schema(description = "网格营销站点标识: REQUIRED(必选)/OPTIONAL(可选)")
    private String gridMarketingFlag;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "负责惠农专员ID（系统自动填充）", hidden = true)
    private Long specialistId;

    @Schema(description = "站点状态: ACTIVE/INACTIVE", example = "ACTIVE")
    private String status;

    @Schema(description = "数据来源: IMPORTED/MANUAL", hidden = true)
    private String dataSource;

    @Schema(description = "导入批次号", hidden = true)
    private String importBatch;

    @Schema(description = "导入时间", hidden = true)
    private LocalDateTime importTime;

}