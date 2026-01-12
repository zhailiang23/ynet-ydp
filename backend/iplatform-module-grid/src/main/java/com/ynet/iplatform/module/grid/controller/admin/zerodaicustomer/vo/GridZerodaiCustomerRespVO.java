package com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 零贷客户扩展 Response VO")
@Data
@ExcelIgnoreUnannotated
public class GridZerodaiCustomerRespVO {

    @Schema(description = "扩展ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("扩展ID")
    private Long id;

    @Schema(description = "客户ID (关联 grid_customer)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("客户ID")
    private Long customerId;

    // 从 grid_customer 表关联查询的字段
    @Schema(description = "商户名称", example = "张三超市")
    @ExcelProperty("商户名称")
    private String customerName;

    @Schema(description = "经营地址", example = "河南省郑州市管城回族区")
    @ExcelProperty("经营地址")
    private String address;

    @Schema(description = "经度", example = "113.625368")
    private Double longitude;

    @Schema(description = "纬度", example = "34.746611")
    private Double latitude;

    // grid_zerodai_customer 表字段
    @Schema(description = "负责人姓名", example = "张三")
    @ExcelProperty("负责人姓名")
    private String principalName;

    @Schema(description = "性别", example = "男")
    @ExcelProperty("性别")
    private String gender;

    @Schema(description = "手机号", example = "13800138000")
    @ExcelProperty("手机号")
    private String phone;

    @Schema(description = "经营情况")
    private String businessSituation;

    @Schema(description = "当前融资情况")
    private String financingSituation;

    @Schema(description = "对我行信贷等产品需求")
    private String creditDemand;

    @Schema(description = "客户号（信贷系统客户号）", example = "CUS202512190001")
    @ExcelProperty("客户号")
    private String customerNo;

    @Schema(description = "客户分类", example = "潜力客户")
    @ExcelProperty("客户分类")
    private String customerClassification;

    @Schema(description = "授信金额（万元）", example = "50.00")
    @ExcelProperty("授信金额（万元）")
    private BigDecimal creditAmount;

    @Schema(description = "在贷金额（万元）", example = "30.00")
    @ExcelProperty("在贷金额（万元）")
    private BigDecimal loanAmount;

    @Schema(description = "状态", example = "营销中")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "上传照片（JSON数组）")
    private String photos;

    @Schema(description = "员工号", example = "EMP001")
    @ExcelProperty("员工号")
    private String employeeNo;

    @Schema(description = "员工姓名", example = "李四")
    @ExcelProperty("员工姓名")
    private String employeeName;

    // 保留原有字段用于后续逻辑
    @Schema(description = "是否进件")
    @ExcelProperty("是否进件")
    private Boolean isApplied;

    @Schema(description = "是否完件")
    @ExcelProperty("是否完件")
    private Boolean isCompleted;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}