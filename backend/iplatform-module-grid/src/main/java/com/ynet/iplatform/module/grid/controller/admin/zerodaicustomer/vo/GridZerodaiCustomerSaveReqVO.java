package com.ynet.iplatform.module.grid.controller.admin.zerodaicustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 零贷客户扩展新增/修改 Request VO")
@Data
public class GridZerodaiCustomerSaveReqVO {

    @Schema(description = "扩展ID", example = "1")
    private Long id;

    // grid_customer 表字段（商户名称和经营地址）
    @Schema(description = "商户名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三超市")
    @NotEmpty(message = "商户名称不能为空")
    private String customerName;

    @Schema(description = "经营地址", example = "河南省郑州市管城回族区")
    private String address;

    @Schema(description = "经度", example = "113.625368")
    private Double longitude;

    @Schema(description = "纬度", example = "34.746611")
    private Double latitude;

    // grid_zerodai_customer 表字段
    @Schema(description = "负责人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "负责人姓名不能为空")
    private String principalName;

    @Schema(description = "性别", requiredMode = Schema.RequiredMode.REQUIRED, example = "男")
    @NotEmpty(message = "性别不能为空")
    private String gender;

    @Schema(description = "手机号", requiredMode = Schema.RequiredMode.REQUIRED, example = "13800138000")
    @NotEmpty(message = "手机号不能为空")
    private String phone;

    @Schema(description = "经营情况（近1年营业收入，实际经营年限，净利润、家庭资产等）")
    private String businessSituation;

    @Schema(description = "当前融资情况")
    private String financingSituation;

    @Schema(description = "对我行信贷等产品需求")
    private String creditDemand;

    @Schema(description = "客户号（信贷系统客户号）", example = "CUS202512190001")
    private String customerNo;

    @Schema(description = "客户分类", example = "潜力客户")
    private String customerClassification;

    @Schema(description = "授信金额（万元）", example = "50.00")
    private BigDecimal creditAmount;

    @Schema(description = "在贷金额（万元）", example = "30.00")
    private BigDecimal loanAmount;

    @Schema(description = "状态", example = "营销中")
    private String status;

    @Schema(description = "上传照片（JSON数组）", example = "[\"http://example.com/photo1.jpg\"]")
    private String photos;

    @Schema(description = "员工号", example = "EMP001")
    private String employeeNo;

    @Schema(description = "员工姓名", example = "李四")
    private String employeeName;

    @Schema(description = "客户经理ID", example = "1")
    private Long managerId;

    @Schema(description = "网格ID", example = "1")
    private Long gridId;

    // 保留原有字段用于后续逻辑
    @Schema(description = "是否进件")
    private Boolean isApplied;

    @Schema(description = "是否完件")
    private Boolean isCompleted;

}