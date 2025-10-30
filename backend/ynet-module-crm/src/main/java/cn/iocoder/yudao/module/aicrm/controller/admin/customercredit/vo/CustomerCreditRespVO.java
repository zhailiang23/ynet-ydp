package cn.iocoder.yudao.module.aicrm.controller.admin.customercredit.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 客户授信信息表（零售+对公共用） Response VO")
@Data
@ExcelIgnoreUnannotated
public class CustomerCreditRespVO {

    @Schema(description = "授信主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "20251")
    @ExcelProperty("授信主键")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "23175")
    @ExcelProperty("客户ID（关联 crm_customer 主表）")
    private Long customerId;

    @Schema(description = "统计日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("统计日期")
    private LocalDate statisticsDate;

    @Schema(description = "授信协议号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("授信协议号")
    private String creditAgreementNo;

    @Schema(description = "授信品种（字典: aicrm_credit_product_type）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("授信品种（字典: aicrm_credit_product_type）")
    private String creditProductType;

    @Schema(description = "授信币种（字典: aicrm_currency_type）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("授信币种（字典: aicrm_currency_type）")
    private String currencyCode;

    @Schema(description = "授信额度（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("授信额度（元）")
    private BigDecimal creditLimit;

    @Schema(description = "已用额度（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("已用额度（元）")
    private BigDecimal usedLimit;

    @Schema(description = "可用额度（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("可用额度（元）")
    private BigDecimal availableLimit;

    @Schema(description = "使用比例（%）")
    @ExcelProperty("使用比例（%）")
    private BigDecimal usageRatio;

    @Schema(description = "授信起始日", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("授信起始日")
    private LocalDate creditStartDate;

    @Schema(description = "授信到期日", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("授信到期日")
    private LocalDate creditEndDate;

    @Schema(description = "授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）", example = "1")
    @ExcelProperty("授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）")
    private String creditType;

    @Schema(description = "授信状态（字典: aicrm_credit_status，effective=生效中，expired=已到期，cancelled=已撤销，frozen=已冻结）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("授信状态（字典: aicrm_credit_status，effective=生效中，expired=已到期，cancelled=已撤销，frozen=已冻结）")
    private String creditStatus;

    @Schema(description = "授信审批日期")
    @ExcelProperty("授信审批日期")
    private LocalDate approveDate;

    @Schema(description = "授信审批金额（元）")
    @ExcelProperty("授信审批金额（元）")
    private BigDecimal approveAmount;

    @Schema(description = "授信利率（%）")
    @ExcelProperty("授信利率（%）")
    private BigDecimal interestRate;

    @Schema(description = "担保方式（字典: aicrm_guarantee_type）", example = "1")
    @ExcelProperty("担保方式（字典: aicrm_guarantee_type）")
    private String guaranteeType;

    @Schema(description = "授信用途")
    @ExcelProperty("授信用途")
    private String creditPurpose;

    @Schema(description = "审批人ID（关联 system_users.id）", example = "29872")
    @ExcelProperty("审批人ID（关联 system_users.id）")
    private Long approverUserId;

    @Schema(description = "审批部门ID（关联 system_dept.id）", example = "1836")
    @ExcelProperty("审批部门ID（关联 system_dept.id）")
    private Long approverDeptId;

    @Schema(description = "客户经理ID（关联 system_users.id）", example = "8477")
    @ExcelProperty("客户经理ID（关联 system_users.id）")
    private Long managerUserId;

    @Schema(description = "授信网点ID（关联 system_dept.id）", example = "13110")
    @ExcelProperty("授信网点ID（关联 system_dept.id）")
    private Long branchId;

    @Schema(description = "备注", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}