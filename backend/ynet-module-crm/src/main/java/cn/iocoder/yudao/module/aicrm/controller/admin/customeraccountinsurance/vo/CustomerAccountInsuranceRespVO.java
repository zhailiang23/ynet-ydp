package cn.iocoder.yudao.module.aicrm.controller.admin.customeraccountinsurance.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户保险账户信息表（零售+对公共用） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerAccountInsuranceRespVO {

    @Schema(description = "保险账户主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "1864")
    @ExcelProperty("保险账户主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "16034")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "保单号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("保单号")
    private String accountNo;

    @Schema(description = "保险单号")
    @ExcelProperty("保险单号")
    private String policyNo;

    @Schema(description = "保险产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("保险产品名称")
    private String productName;

    @Schema(description = "投保人姓名", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("投保人姓名")
    private String accountName;

    @Schema(description = "投保日期（开户日期）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("投保日期（开户日期）")
    private LocalDate openDate;

    @Schema(description = "退保日期（销户日期）")
    @ExcelProperty("退保日期（销户日期）")
    private LocalDate closeDate;

    @Schema(description = "账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）")
    private String accountStatus;

    @Schema(description = "保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）", example = "2")
    @ExcelProperty("保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）")
    private String insuranceType;

    @Schema(description = "保险公司")
    @ExcelProperty("保险公司")
    private String insuranceCompany;

    @Schema(description = "保险期限（如：终身、20年、至70岁）")
    @ExcelProperty("保险期限（如：终身、20年、至70岁）")
    private String insuranceTerm;

    @Schema(description = "缴费期限（如：趸交、5年、10年、20年）")
    @ExcelProperty("缴费期限（如：趸交、5年、10年、20年）")
    private String paymentTerm;

    @Schema(description = "缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）")
    @ExcelProperty("缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）")
    private String paymentFrequency;

    @Schema(description = "保险金额（保额）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("保险金额（保额）")
    private BigDecimal insuredAmount;

    @Schema(description = "保费（年交保费）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("保费（年交保费）")
    private BigDecimal premium;

    @Schema(description = "已交保费")
    @ExcelProperty("已交保费")
    private BigDecimal paidPremium;

    @Schema(description = "现金价值")
    @ExcelProperty("现金价值")
    private BigDecimal cashValue;

    @Schema(description = "账户价值（万能险、投连险）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("账户价值（万能险、投连险）")
    private BigDecimal balance;

    @Schema(description = "币种", example = "1")
    @ExcelProperty("币种")
    private String currencyType;

    @Schema(description = "被保险人姓名", example = "王五")
    @ExcelProperty("被保险人姓名")
    private String insuredName;

    @Schema(description = "与投保人关系（字典: aicrm_insured_relation）")
    @ExcelProperty("与投保人关系（字典: aicrm_insured_relation）")
    private String insuredRelation;

    @Schema(description = "受益人姓名", example = "芋艿")
    @ExcelProperty("受益人姓名")
    private String beneficiaryName;

    @Schema(description = "生效日期")
    @ExcelProperty("生效日期")
    private LocalDate effectiveDate;

    @Schema(description = "到期日期")
    @ExcelProperty("到期日期")
    private LocalDate expireDate;

    @Schema(description = "下次缴费日期")
    @ExcelProperty("下次缴费日期")
    private LocalDate nextPaymentDate;

    @Schema(description = "销售机构ID", example = "15133")
    @ExcelProperty("销售机构ID")
    private Long deptId;

    @Schema(description = "销售机构名称", example = "张三")
    @ExcelProperty("销售机构名称")
    private String deptName;

    @Schema(description = "保险顾问用户ID", example = "32456")
    @ExcelProperty("保险顾问用户ID")
    private Long managerUserId;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}