package com.ynet.iplatform.module.aicrm.controller.admin.customeraccountinsurance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(description = "管理后台 - 客户保险账户信息表（零售+对公共用）新增/修改 Request VO")
@Data
public class CustomerAccountInsuranceSaveReqVO {

    @Schema(description = "保险账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "1864")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "16034")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "保单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "保单号不能为空")
    private String accountNo;

    @Schema(description = "保险单号")
    private String policyNo;

    @Schema(description = "保险产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "保险产品名称不能为空")
    private String productName;

    @Schema(description = "投保人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "投保人姓名不能为空")
    private String accountName;

    @Schema(description = "投保日期（开户日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "投保日期（开户日期）不能为空")
    private LocalDate openDate;

    @Schema(description = "退保日期（销户日期）")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）不能为空")
    private String accountStatus;

    @Schema(description = "保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）", example = "2")
    private String insuranceType;

    @Schema(description = "保险公司")
    private String insuranceCompany;

    @Schema(description = "保险期限（如：终身、20年、至70岁）")
    private String insuranceTerm;

    @Schema(description = "缴费期限（如：趸交、5年、10年、20年）")
    private String paymentTerm;

    @Schema(description = "缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）")
    private String paymentFrequency;

    @Schema(description = "保险金额（保额）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "保险金额（保额）不能为空")
    private BigDecimal insuredAmount;

    @Schema(description = "保费（年交保费）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "保费（年交保费）不能为空")
    private BigDecimal premium;

    @Schema(description = "已交保费")
    private BigDecimal paidPremium;

    @Schema(description = "现金价值")
    private BigDecimal cashValue;

    @Schema(description = "账户价值（万能险、投连险）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "账户价值（万能险、投连险）不能为空")
    private BigDecimal balance;

    @Schema(description = "币种", example = "1")
    private String currencyType;

    @Schema(description = "被保险人姓名", example = "王五")
    private String insuredName;

    @Schema(description = "与投保人关系（字典: aicrm_insured_relation）")
    private String insuredRelation;

    @Schema(description = "受益人姓名", example = "芋艿")
    private String beneficiaryName;

    @Schema(description = "生效日期")
    private LocalDate effectiveDate;

    @Schema(description = "到期日期")
    private LocalDate expireDate;

    @Schema(description = "下次缴费日期")
    private LocalDate nextPaymentDate;

    @Schema(description = "销售机构ID", example = "15133")
    private Long deptId;

    @Schema(description = "销售机构名称", example = "张三")
    private String deptName;

    @Schema(description = "保险顾问用户ID", example = "32456")
    private Long managerUserId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}