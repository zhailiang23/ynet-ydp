package com.ynet.iplatform.module.aicrm.controller.admin.customeraccountinsurance.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户保险账户信息表（零售+对公共用）分页 Request VO")
@Data
public class CustomerAccountInsurancePageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表）", example = "16034")
    private Long customerId;

    @Schema(description = "保单号")
    private String accountNo;

    @Schema(description = "保险单号")
    private String policyNo;

    @Schema(description = "保险产品名称", example = "芋艿")
    private String productName;

    @Schema(description = "投保人姓名", example = "李四")
    private String accountName;

    @Schema(description = "投保日期（开户日期）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] openDate;

    @Schema(description = "退保日期（销户日期）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] closeDate;

    @Schema(description = "账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）", example = "1")
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

    @Schema(description = "保险金额（保额）")
    private BigDecimal insuredAmount;

    @Schema(description = "保费（年交保费）")
    private BigDecimal premium;

    @Schema(description = "已交保费")
    private BigDecimal paidPremium;

    @Schema(description = "现金价值")
    private BigDecimal cashValue;

    @Schema(description = "账户价值（万能险、投连险）")
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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] effectiveDate;

    @Schema(description = "到期日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] expireDate;

    @Schema(description = "下次缴费日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] nextPaymentDate;

    @Schema(description = "销售机构ID", example = "15133")
    private Long deptId;

    @Schema(description = "销售机构名称", example = "张三")
    private String deptName;

    @Schema(description = "保险顾问用户ID", example = "32456")
    private Long managerUserId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
