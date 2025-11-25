package com.ynet.iplatform.module.aicrm.controller.admin.customerbusinessinfo.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户经营信息表（精简版，只包含经营相关核心字段） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerBusinessInfoRespVO {

    @Schema(description = "经营信息主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "266")
    @ExcelProperty("经营信息主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20561")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "经营主体名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("经营主体名称")
    private String businessName;

    @Schema(description = "经营类型（字典: aicrm_business_type）", example = "1")
    @ExcelProperty("经营类型（字典: aicrm_business_type）")
    private String businessType;

    @Schema(description = "营业执照号/统一社会信用代码")
    @ExcelProperty("营业执照号/统一社会信用代码")
    private String businessLicenseNo;

    @Schema(description = "经营范围")
    @ExcelProperty("经营范围")
    private String businessScope;

    @Schema(description = "所属行业")
    @ExcelProperty("所属行业")
    private String industry;

    @Schema(description = "经营规模（字典: aicrm_business_scale）")
    @ExcelProperty("经营规模（字典: aicrm_business_scale）")
    private String businessScale;

    @Schema(description = "经营状态（字典: aicrm_business_status）", example = "2")
    @ExcelProperty("经营状态（字典: aicrm_business_status）")
    private String businessStatus;

    @Schema(description = "注册资本（元）")
    @ExcelProperty("注册资本（元）")
    private BigDecimal registeredCapital;

    @Schema(description = "员工人数", example = "12402")
    @ExcelProperty("员工人数")
    private Integer employeeCount;

    @Schema(description = "年营业额（元）")
    @ExcelProperty("年营业额（元）")
    private BigDecimal annualRevenue;

    @Schema(description = "月营业额（元）")
    @ExcelProperty("月营业额（元）")
    private BigDecimal monthlyRevenue;

    @Schema(description = "年利润（元）")
    @ExcelProperty("年利润（元）")
    private BigDecimal annualProfit;

    @Schema(description = "税务登记号")
    @ExcelProperty("税务登记号")
    private String taxRegistrationNo;

    @Schema(description = "是否一般纳税人")
    @ExcelProperty("是否一般纳税人")
    private Boolean isGeneralTaxpayer;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}