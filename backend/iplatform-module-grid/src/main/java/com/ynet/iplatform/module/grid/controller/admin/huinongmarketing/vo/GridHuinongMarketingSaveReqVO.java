package com.ynet.iplatform.module.grid.controller.admin.huinongmarketing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 惠农网格营销信息新增/修改 Request VO")
@Data
public class GridHuinongMarketingSaveReqVO {

    @Schema(description = "营销信息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "186")
    private Long id;

    @Schema(description = "网格ID", example = "10367")
    private Long gridId;

    @Schema(description = "站点ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "站点ID不能为空")
    private Long stationId;

    @Schema(description = "行政村名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "行政村名称不能为空")
    private String villageName;

    @Schema(description = "行政村坐标（格式：经度,纬度）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "行政村坐标不能为空")
    private String villageLocation;

    @Schema(description = "行政村地址")
    private String villageAddress;

    @Schema(description = "户籍人口数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "户籍人口数不能为空")
    private Integer registeredPopulation;

    @Schema(description = "常住人口数")
    private Integer residentPopulation;

    @Schema(description = "产业情况描述", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "产业情况不能为空")
    private String industrySituation;

    @Schema(description = "主要产业 (逗号分隔)")
    private String mainIndustries;

    @Schema(description = "需求分析")
    private String demandAnalysis;

    @Schema(description = "营销计划")
    private String marketingPlan;

    @Schema(description = "客户类型（JSON数组）", requiredMode = Schema.RequiredMode.REQUIRED, example = "[\"传统种植户\",\"传统养殖户\"]")
    @NotEmpty(message = "客户类型不能为空")
    private String customerType;

    @Schema(description = "客户数量（JSON对象）", requiredMode = Schema.RequiredMode.REQUIRED, example = "{\"传统种植户\":100,\"传统养殖户\":50}")
    @NotEmpty(message = "客户数量不能为空")
    private String customerCount;

    @Schema(description = "负责惠农专员ID", example = "27890")
    private Long specialistId;

    @Schema(description = "员工工号")
    private String employeeNo;

    @Schema(description = "创建人ID", example = "26174")
    private Long creatorId;

    @Schema(description = "更新人ID", example = "748")
    private Long updaterId;

}