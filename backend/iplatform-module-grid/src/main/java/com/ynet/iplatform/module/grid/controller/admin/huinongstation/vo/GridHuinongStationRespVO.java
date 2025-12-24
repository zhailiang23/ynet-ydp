package com.ynet.iplatform.module.grid.controller.admin.huinongstation.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 惠农站点信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridHuinongStationRespVO {

    @Schema(description = "站点ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30428")
    @ExcelProperty("站点ID")
    private Long id;

    @Schema(description = "站点编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("站点编号")
    private String stationCode;

    @Schema(description = "站点名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "郑州惠农站")
    @ExcelProperty("站点名称")
    private String stationName;

    @Schema(description = "所属机构ID", example = "1")
    @ExcelProperty("所属机构ID")
    private Long orgId;

    @Schema(description = "所属机构名称")
    @ExcelProperty("所属机构名称")
    private String orgName;

    @Schema(description = "站点类型", example = "惠农")
    @ExcelProperty("站点类型")
    private String stationType;

    @Schema(description = "站点地址")
    @ExcelProperty("站点地址")
    private String address;

    @Schema(description = "经度", example = "113.6234")
    private BigDecimal longitude;

    @Schema(description = "纬度", example = "34.7490")
    private BigDecimal latitude;

    @Schema(description = "圆形半径（米）", example = "3000")
    private Integer radiusMeters;

    @Schema(description = "关联的网格ID", example = "13533")
    @ExcelProperty("关联的网格ID")
    private Long gridId;

    @Schema(description = "网格营销站点标识: REQUIRED(必选)/OPTIONAL(可选)")
    @ExcelProperty("网格营销站点")
    private String gridMarketingFlag;

    @Schema(description = "管理行名称")
    @ExcelProperty("管理行名称")
    private String manageBranchName;

    @Schema(description = "二级支行名称")
    @ExcelProperty("二级支行名称")
    private String subBranchName;

    @Schema(description = "惠农人员工号")
    @ExcelProperty("惠农人员工号")
    private String specialistEmployeeNo;

    @Schema(description = "惠农人员姓名")
    @ExcelProperty("惠农人员姓名")
    private String specialistName;

    @Schema(description = "联系人")
    @ExcelProperty("联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    @ExcelProperty("联系电话")
    private String contactPhone;

    @Schema(description = "负责惠农专员ID (关联 system_users)", example = "20186")
    @ExcelProperty("负责惠农专员ID (关联 system_users)")
    private Long specialistId;

    @Schema(description = "站点状态: ACTIVE/INACTIVE", example = "2")
    @ExcelProperty("站点状态: ACTIVE/INACTIVE")
    private String status;

    @Schema(description = "数据来源: IMPORTED/MANUAL")
    @ExcelProperty("数据来源: IMPORTED/MANUAL")
    private String dataSource;

    @Schema(description = "导入批次号")
    @ExcelProperty("导入批次号")
    private String importBatch;

    @Schema(description = "导入时间")
    @ExcelProperty("导入时间")
    private LocalDateTime importTime;

    @Schema(description = "创建人ID", example = "16805")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "26847")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}