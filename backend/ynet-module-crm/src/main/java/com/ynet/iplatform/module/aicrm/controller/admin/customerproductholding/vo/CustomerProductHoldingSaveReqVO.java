package com.ynet.iplatform.module.aicrm.controller.admin.customerproductholding.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 客户产品持有情况表（客户与产品的多对多关系）新增/修改 Request VO")
@Data
public class CustomerProductHoldingSaveReqVO {

    @Schema(description = "产品持有主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "16185")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "17971")
    @NotNull(message = "客户ID（关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "产品ID（关联 crm_product_category.id，必须是叶子节点）", requiredMode = Schema.RequiredMode.REQUIRED, example = "19661")
    @NotNull(message = "产品ID（关联 crm_product_category.id，必须是叶子节点）不能为空")
    private Long productId;

    @Schema(description = "贷款账号/账号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "贷款账号/账号不能为空")
    private String accountNo;

    @Schema(description = "借据编号")
    private String receiptNo;

    @Schema(description = "合同编号")
    private String contractNo;

    @Schema(description = "币种代码")
    private String currencyCode;

    @Schema(description = "放款日期（贷款产品）")
    private LocalDate loanDate;

    @Schema(description = "开户日期（存款、理财等产品）")
    private LocalDate openDate;

    @Schema(description = "到期日期")
    private LocalDate matureDate;

    @Schema(description = "合同日期")
    private LocalDate contractDate;

    @Schema(description = "开户网点名称", example = "张三")
    private String branchName;

    @Schema(description = "开户网点ID（关联 system_dept.id）", example = "31951")
    private Long branchId;

    @Schema(description = "产品名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "产品名称不能为空")
    private String productName;

    @Schema(description = "持有金额/余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "持有金额/余额不能为空")
    private BigDecimal holdingAmount;

    @Schema(description = "原始金额（初始投资/贷款金额）")
    private BigDecimal originalAmount;

    @Schema(description = "利率/收益率（%）")
    private BigDecimal interestRate;

    @Schema(description = "持有状态（字典: aicrm_holding_status，holding=持有中，matured=已到期，redeemed=已赎回，settled=已结清）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "持有状态（字典: aicrm_holding_status，holding=持有中，matured=已到期，redeemed=已赎回，settled=已结清）不能为空")
    private String holdingStatus;

    @Schema(description = "关联账户类型（deposit=存款账户，loan=贷款账户，wealth=理财账户等）", example = "1")
    private String relatedAccountType;

    @Schema(description = "关联账户ID（关联对应的账户表主键）", example = "21439")
    private Long relatedAccountId;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}