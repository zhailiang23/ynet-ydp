package com.ynet.iplatform.module.aicrm.controller.admin.customercreditdetail.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 客户授信使用明细表（零售+对公共用）新增/修改 Request VO")
@Data
public class CustomerCreditDetailSaveReqVO {

    @Schema(description = "授信明细主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "21747")
    private Long id;

    @Schema(description = "授信ID（关联 crm_customer_credit.id）", requiredMode = Schema.RequiredMode.REQUIRED, example = "19070")
    @NotNull(message = "授信ID（关联 crm_customer_credit.id）不能为空")
    private Long creditId;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "29649")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "贷款编号/借据号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "贷款编号/借据号不能为空")
    private String loanNo;

    @Schema(description = "合同编号")
    private String contractNo;

    @Schema(description = "放款日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "放款日期不能为空")
    private LocalDate loanDate;

    @Schema(description = "到期日期")
    private LocalDate matureDate;

    @Schema(description = "贷款金额（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "贷款金额（元）不能为空")
    private BigDecimal loanAmount;

    @Schema(description = "贷款余额（元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "贷款余额（元）不能为空")
    private BigDecimal balance;

    @Schema(description = "执行利率（%）")
    private BigDecimal interestRate;

    @Schema(description = "贷款状态（字典: aicrm_loan_status，normal=正常，overdue=逾期，settled=已结清）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "贷款状态（字典: aicrm_loan_status，normal=正常，overdue=逾期，settled=已结清）不能为空")
    private String loanStatus;

    @Schema(description = "结清日期")
    private LocalDate settleDate;

    @Schema(description = "贷款产品名称", example = "张三")
    private String productName;

    @Schema(description = "产品代码")
    private String productCode;

    @Schema(description = "备注", example = "随便")
    private String remark;

}