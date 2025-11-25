package com.ynet.iplatform.module.aicrm.controller.admin.customerfamily.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户家庭信息表（零售客户） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerFamilyRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3527")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "客户ID（关联零售客户，唯一）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13691")
    @ExcelProperty("客户ID（关联零售客户，唯一）")
    private Long customerId;

    @Schema(description = "家庭人口数", example = "26357")
    @ExcelProperty("家庭人口数")
    private Integer familyMemberCount;

    @Schema(description = "供养人口数", example = "6811")
    @ExcelProperty("供养人口数")
    private Integer supportMemberCount;

    @Schema(description = "劳动力人口数", example = "2628")
    @ExcelProperty("劳动力人口数")
    private Integer laborMemberCount;

    @Schema(description = "子女数量", example = "4486")
    @ExcelProperty("子女数量")
    private Integer childrenCount;

    @Schema(description = "家庭年收入（万元）")
    @ExcelProperty("家庭年收入（万元）")
    private BigDecimal familyAnnualIncome;

    @Schema(description = "家庭年收入范围（字典：aicrm_family_income_scope）")
    @ExcelProperty("家庭年收入范围（字典：aicrm_family_income_scope）")
    private String familyAnnualIncomeScope;

    @Schema(description = "家庭年支出（万元）")
    @ExcelProperty("家庭年支出（万元）")
    private BigDecimal familyAnnualExpenditure;

    @Schema(description = "家庭年支出范围（字典：aicrm_family_expenditure_scope）")
    @ExcelProperty("家庭年支出范围（字典：aicrm_family_expenditure_scope）")
    private String familyAnnualExpenditureScope;

    @Schema(description = "家庭负债（万元）")
    @ExcelProperty("家庭负债（万元）")
    private BigDecimal familyDebt;

    @Schema(description = "家庭总资产（万元）")
    @ExcelProperty("家庭总资产（万元）")
    private BigDecimal familyTotalAssets;

    @Schema(description = "家庭资产信息")
    @ExcelProperty("家庭资产信息")
    private String familyAssetsInfo;

    @Schema(description = "主要收入来源（字典：aicrm_income_source）")
    @ExcelProperty("主要收入来源（字典：aicrm_income_source）")
    private String mainIncomeSource;

    @Schema(description = "居住状况（字典：aicrm_residence_status）", example = "2")
    @ExcelProperty("居住状况（字典：aicrm_residence_status）")
    private String residenceStatus;

    @Schema(description = "住房状况（字典：aicrm_house_status）", example = "2")
    @ExcelProperty("住房状况（字典：aicrm_house_status）")
    private String houseStatus;

    @Schema(description = "是否有房有车（0-否，1-是）")
    @ExcelProperty("是否有房有车（0-否，1-是）")
    private Boolean hasHomeCar;

    @Schema(description = "是否户主（0-否，1-是）")
    @ExcelProperty("是否户主（0-否，1-是）")
    private Boolean isHouseHolder;

    @Schema(description = "户主姓名", example = "李四")
    @ExcelProperty("户主姓名")
    private String houseHolderName;

    @Schema(description = "所居住宅多(值)，即居住地点描述")
    @ExcelProperty("所居住宅多(值)，即居住地点描述")
    private String residenceLocation;

    @Schema(description = "家庭地址")
    @ExcelProperty("家庭地址")
    private String familyAddress;

    @Schema(description = "家庭电话")
    @ExcelProperty("家庭电话")
    private String homeTel;

    @Schema(description = "家庭是否和睦（0-否，1-是）")
    @ExcelProperty("家庭是否和睦（0-否，1-是）")
    private Boolean isHarmony;

    @Schema(description = "是否信用家庭（0-否，1-是）")
    @ExcelProperty("是否信用家庭（0-否，1-是）")
    private Boolean isCreditFamily;

    @Schema(description = "信用状况（字典：aicrm_credit_status）", example = "2")
    @ExcelProperty("信用状况（字典：aicrm_credit_status）")
    private String creditStatus;

    @Schema(description = "授信额度")
    @ExcelProperty("授信额度")
    private BigDecimal creditAmount;

    @Schema(description = "家庭负债范围（字典：aicrm_debt_scope）")
    @ExcelProperty("家庭负债范围（字典：aicrm_debt_scope）")
    private String familyDebtScope;

    @Schema(description = "负债状况（字典：aicrm_debt_status）", example = "1")
    @ExcelProperty("负债状况（字典：aicrm_debt_status）")
    private String debtStatus;

    @Schema(description = "家庭不良记录")
    @ExcelProperty("家庭不良记录")
    private String familyAdverseRecords;

    @Schema(description = "经营及规模")
    @ExcelProperty("经营及规模")
    private String businessAndScale;

    @Schema(description = "家庭实力（字典：aicrm_family_strength）")
    @ExcelProperty("家庭实力（字典：aicrm_family_strength）")
    private String familyStrength;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}