package com.ynet.iplatform.module.aicrm.controller.admin.retailcustomer.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - CRM零售客户扩展表(零售客户特有基本信息)新增/修改 Request VO")
@Data
public class RetailCustomerSaveReqVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "客户ID(外键,关联crm_customer.id,唯一)", requiredMode = Schema.RequiredMode.REQUIRED, example = "24261")
    @NotNull(message = "客户ID(外键,关联crm_customer.id,唯一)不能为空")
    private Long customerId;

    @Schema(description = "昵称", example = "芋艿")
    private String nickname;

    @Schema(description = "性别(0=未知, 1=男, 2=女)")
    private Integer gender;

    @Schema(description = "手机号码", example = "13800138000")
    private String mobile;

    @Schema(description = "证件类型(如:身份证、护照等)")
    private String idCardType;

    @Schema(description = "证件号码")
    private String idCardNo;

    @Schema(description = "出生日期")
    private LocalDate birthday;

    @Schema(description = "年龄(可通过出生日期计算)")
    private Integer age;

    @Schema(description = "国籍")
    private String nationality;

    @Schema(description = "民族(如:汉族、回族等)")
    private String nation;

    @Schema(description = "籍贯")
    private String nativePlace;

    @Schema(description = "户籍类型(如:居民、非居民)", example = "2")
    private String residenceType;

    @Schema(description = "户口所在地(详细地址)")
    private String domicilePlace;

    @Schema(description = "职业(如:专业技术人员、公务员等)")
    private String occupation;

    @Schema(description = "职业类型分类", example = "1")
    private String occupationType;

    @Schema(description = "婚姻状态(1=未婚, 2=已婚, 3=离异, 4=丧偶)", example = "1")
    private Integer maritalStatus;

    @Schema(description = "宗教信仰")
    private String religion;

    @Schema(description = "受教育程度(如:本科、硕士、博士等)")
    private String education;

    @Schema(description = "最高学位(如:学士、硕士、博士)")
    private String degree;

    @Schema(description = "是否VIP客户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否VIP客户(0=否, 1=是)不能为空")
    private Boolean isVip;

    @Schema(description = "是否核心VIP客户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否核心VIP客户(0=否, 1=是)不能为空")
    private Boolean isCoreVip;

    @Schema(description = "VIP等级(如:金卡、银卡、钻石卡等)")
    private String vipLevel;

    @Schema(description = "是否高净值客户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否高净值客户(0=否, 1=是)不能为空")
    private Boolean isHighNetWorth;

    @Schema(description = "净值类型(如:个体经营户、工薪阶层等)", example = "2")
    private String netWorthType;

    @Schema(description = "收入水平(如:高、中、低)")
    private String incomeLevel;

    @Schema(description = "资产水平(如:高、中、低)")
    private String assetLevel;

    @Schema(description = "信誉状态(如:优秀、良好、一般、较差)", example = "2")
    private String reputationStatus;

    @Schema(description = "信誉级别")
    private String reputationLevel;

    @Schema(description = "信誉综合评分")
    private BigDecimal reputationScore;

    @Schema(description = "零售客户类型(如:正式客户、潜在客户等)", example = "1")
    private String retailCustomerType;

    @Schema(description = "是否高端户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否高端户(0=否, 1=是)不能为空")
    private Boolean isHighEndCustomer;

    @Schema(description = "是否综合户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否综合户(0=否, 1=是)不能为空")
    private Boolean isComprehensiveCustomer;

    @Schema(description = "客户归属网格编号")
    private String customerGridCode;

    @Schema(description = "资质/风险(如:低、中、高)")
    private String qualificationRisk;

    @Schema(description = "信誉水险等级(如:低、中、高)")
    private String creditRiskLevel;

    @Schema(description = "信誉水险评级")
    private String creditRiskRating;

    @Schema(description = "是否我行代发工资客户(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否我行代发工资客户(0=否, 1=是)不能为空")
    private Boolean isPayrollCustomer;

    @Schema(description = "代发工资单位名称", example = "张三")
    private String payrollCompanyName;

    @Schema(description = "成为我行客户时间(日期)")
    private LocalDate becomeCustomerDate;

    @Schema(description = "在我行建立信任关系时间(日期)")
    private LocalDate establishTrustDate;

    @Schema(description = "是否本行修改(0=否, 1=是)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否本行修改(0=否, 1=是)不能为空")
    private Boolean isBankModified;

    @Schema(description = "扩展字段1")
    private String extField1;

    @Schema(description = "扩展字段2")
    private String extField2;

    @Schema(description = "扩展字段3")
    private String extField3;

}