package com.ynet.iplatform.module.grid.controller.admin.huinongmarketing.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 惠农网格营销信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridHuinongMarketingRespVO {

    @Schema(description = "营销信息ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "186")
    @ExcelProperty("营销信息ID")
    private Long id;

    @Schema(description = "网格ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10367")
    @ExcelProperty("网格ID")
    private Long gridId;

    @Schema(description = "站点ID", example = "1")
    @ExcelProperty("站点ID")
    private Long stationId;

    @Schema(description = "站点名称", example = "金水路惠农服务站")
    @ExcelProperty("站点名称")
    private String stationName;

    @Schema(description = "站点编号", example = "HN0001")
    @ExcelProperty("站点编号")
    private String stationCode;

    @Schema(description = "行政村名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("行政村名称")
    private String villageName;

    @Schema(description = "行政村坐标（格式：经度,纬度）")
    @ExcelProperty("行政村坐标")
    private String villageLocation;

    @Schema(description = "行政村地址")
    @ExcelProperty("行政村地址")
    private String villageAddress;

    @Schema(description = "户籍人口数")
    @ExcelProperty("户籍人口数")
    private Integer registeredPopulation;

    @Schema(description = "常住人口数")
    @ExcelProperty("常住人口数")
    private Integer residentPopulation;

    @Schema(description = "产业情况描述")
    @ExcelProperty("产业情况描述")
    private String industrySituation;

    @Schema(description = "主要产业 (逗号分隔)")
    @ExcelProperty("主要产业 (逗号分隔)")
    private String mainIndustries;

    @Schema(description = "需求分析")
    @ExcelProperty("需求分析")
    private String demandAnalysis;

    @Schema(description = "营销计划")
    @ExcelProperty("营销计划")
    private String marketingPlan;

    @Schema(description = "客户类型（JSON数组）", example = "[\"传统种植户\",\"传统养殖户\"]")
    @ExcelProperty("客户类型")
    private String customerType;

    @Schema(description = "客户数量（JSON对象）", example = "{\"传统种植户\":100,\"传统养殖户\":50}")
    @ExcelProperty("客户数量")
    private String customerCount;

    @Schema(description = "负责惠农专员ID", example = "27890")
    @ExcelProperty("负责惠农专员ID")
    private Long specialistId;

    @Schema(description = "员工工号", example = "001234")
    @ExcelProperty("员工工号")
    private String employeeNo;

    @Schema(description = "创建人ID", example = "26174")
    @ExcelProperty("创建人ID")
    private Long creatorId;

    @Schema(description = "员工姓名（录入人）", example = "张三")
    @ExcelProperty("员工姓名（录入人）")
    private String creatorName;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新人ID", example = "748")
    @ExcelProperty("更新人ID")
    private Long updaterId;

}