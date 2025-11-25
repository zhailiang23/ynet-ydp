package com.ynet.iplatform.module.aicrm.controller.admin.companycustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - CRM对公客户扩展表(对公客户特有基本信息)新增/修改 Request VO")
@Data
public class CompanyCustomerSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29689")
    private Long id;

    @Schema(description = "客户ID(外键,关联crm_customer.id,唯一)", requiredMode = Schema.RequiredMode.REQUIRED, example = "26736")
    @NotNull(message = "客户ID(外键,关联crm_customer.id,唯一)不能为空")
    private Long customerId;

    @Schema(description = "证件类型(通常为营业执照)", example = "2")
    private String licenseType;

    @Schema(description = "证件号码(营业执照号)")
    private String licenseNo;

    @Schema(description = "统一社会信用代码(18位)")
    private String creditCode;

    @Schema(description = "组织机构代码")
    private String organizationCode;

    @Schema(description = "纳税人识别号")
    private String taxNo;

    @Schema(description = "贷款卡号")
    private String loanCardNo;

    @Schema(description = "企业类型(如:有限责任公司、股份有限公司等)", example = "2")
    private String enterpriseType;

    @Schema(description = "企业性质(如:国有企业、民营企业、外资企业等)")
    private String enterpriseNature;

    @Schema(description = "企业控股类型(如:国有控股、私人控股、外资控股等)", example = "2")
    private String ownershipType;

    @Schema(description = "企业经济性质(如:其它企业、国有企业等)", example = "1")
    private String economicType;

    @Schema(description = "企业规模(如:大型、中型、小型、微型)")
    private String enterpriseScale;

    @Schema(description = "注册资本(单位:万元)")
    private BigDecimal registeredCapital;

    @Schema(description = "注册资本币种(默认人民币)")
    private String registeredCapitalCurrency;

    @Schema(description = "企业成立日期")
    private LocalDate establishDate;

    @Schema(description = "营业期限")
    private String businessTerm;

    @Schema(description = "行业分类一级(如:批发业、制造业等)")
    private String industryCategoryL1;

    @Schema(description = "行业分类二级")
    private String industryCategoryL2;

    @Schema(description = "行业分类三级")
    private String industryCategoryL3;

    @Schema(description = "行业分类四级(如:金属及金属矿批发)")
    private String industryCategoryL4;

    @Schema(description = "行业代码(国标行业代码)")
    private String industryCode;

    @Schema(description = "是否上市公司(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否上市公司(0=否, 1=是)不能为空")
    private Boolean isListed;

    @Schema(description = "是否小微企业(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否小微企业(0=否, 1=是)不能为空")
    private Boolean isSmallEnterprise;

    @Schema(description = "是否集团客户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否集团客户(0=否, 1=是)不能为空")
    private Boolean isGroupCustomer;

    @Schema(description = "是否有进出口权(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否有进出口权(0=否, 1=是)不能为空")
    private Boolean isImportExport;

    @Schema(description = "是否我行关联方(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否我行关联方(0=否, 1=是)不能为空")
    private Boolean isRelatedParty;

    @Schema(description = "是否网银签约客户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否网银签约客户(0=否, 1=是)不能为空")
    private Boolean isEbankSigned;

    @Schema(description = "是否涉农企业(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否涉农企业(0=否, 1=是)不能为空")
    private Boolean isAgricultureRelated;

    @Schema(description = "基本账户开户行")
    private String basicAccountBank;

    @Schema(description = "基本账户账号")
    private String basicAccountNo;

    @Schema(description = "法定代表人/负责人姓名", example = "芋艿")
    private String legalPersonName;

    @Schema(description = "法定代表人证件类型", example = "2")
    private String legalPersonIdType;

    @Schema(description = "法定代表人证件号码(加密存储)")
    private String legalPersonIdNo;

    @Schema(description = "法定代表人联系电话(加密存储)")
    private String legalPersonPhone;

    @Schema(description = "企业资质(多个资质用逗号分隔)")
    private String enterpriseQualification;

    @Schema(description = "管理部门")
    private String managementDept;

    @Schema(description = "监管部门")
    private String superviseDept;

    @Schema(description = "企业评级(如:AAA、AA等)")
    private String companyRating;

    @Schema(description = "评级机构")
    private String ratingAgency;

    @Schema(description = "评级日期")
    private LocalDate ratingDate;

    @Schema(description = "扩展字段1")
    private String extField1;

    @Schema(description = "扩展字段2")
    private String extField2;

    @Schema(description = "扩展字段3")
    private String extField3;

}