package cn.iocoder.yudao.module.aicrm.controller.admin.customerfamily.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 客户家庭信息表（零售客户）新增/修改 Request VO")
@Data
public class CustomerFamilySaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "3527")
    private Long id;

    @Schema(description = "客户ID（关联零售客户，唯一）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13691")
    @NotNull(message = "客户ID（关联零售客户，唯一）不能为空")
    private Long customerId;

    @Schema(description = "家庭人口数", example = "26357")
    private Integer familyMemberCount;

    @Schema(description = "供养人口数", example = "6811")
    private Integer supportMemberCount;

    @Schema(description = "劳动力人口数", example = "2628")
    private Integer laborMemberCount;

    @Schema(description = "子女数量", example = "4486")
    private Integer childrenCount;

    @Schema(description = "家庭年收入（万元）")
    private BigDecimal familyAnnualIncome;

    @Schema(description = "家庭年收入范围（字典：aicrm_family_income_scope）")
    private String familyAnnualIncomeScope;

    @Schema(description = "家庭年支出（万元）")
    private BigDecimal familyAnnualExpenditure;

    @Schema(description = "家庭年支出范围（字典：aicrm_family_expenditure_scope）")
    private String familyAnnualExpenditureScope;

    @Schema(description = "家庭负债（万元）")
    private BigDecimal familyDebt;

    @Schema(description = "家庭总资产（万元）")
    private BigDecimal familyTotalAssets;

    @Schema(description = "家庭资产信息")
    private String familyAssetsInfo;

    @Schema(description = "主要收入来源（字典：aicrm_income_source）")
    private String mainIncomeSource;

    @Schema(description = "居住状况（字典：aicrm_residence_status）", example = "2")
    private String residenceStatus;

    @Schema(description = "住房状况（字典：aicrm_house_status）", example = "2")
    private String houseStatus;

    @Schema(description = "是否有房有车（0-否，1-是）")
    private Boolean hasHomeCar;

    @Schema(description = "是否户主（0-否，1-是）")
    private Boolean isHouseHolder;

    @Schema(description = "户主姓名", example = "李四")
    private String houseHolderName;

    @Schema(description = "所居住宅多(值)，即居住地点描述")
    private String residenceLocation;

    @Schema(description = "家庭地址")
    private String familyAddress;

    @Schema(description = "家庭电话")
    private String homeTel;

    @Schema(description = "家庭是否和睦（0-否，1-是）")
    private Boolean isHarmony;

    @Schema(description = "是否信用家庭（0-否，1-是）")
    private Boolean isCreditFamily;

    @Schema(description = "信用状况（字典：aicrm_credit_status）", example = "2")
    private String creditStatus;

    @Schema(description = "授信额度")
    private BigDecimal creditAmount;

    @Schema(description = "家庭负债范围（字典：aicrm_debt_scope）")
    private String familyDebtScope;

    @Schema(description = "负债状况（字典：aicrm_debt_status）", example = "1")
    private String debtStatus;

    @Schema(description = "家庭不良记录")
    private String familyAdverseRecords;

    @Schema(description = "经营及规模")
    private String businessAndScale;

    @Schema(description = "家庭实力（字典：aicrm_family_strength）")
    private String familyStrength;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}