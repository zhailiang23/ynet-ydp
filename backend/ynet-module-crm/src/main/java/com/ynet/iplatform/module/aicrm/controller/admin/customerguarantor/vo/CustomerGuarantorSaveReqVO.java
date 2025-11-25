package com.ynet.iplatform.module.aicrm.controller.admin.customerguarantor.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 客户担保人信息表（零售+对公共用）新增/修改 Request VO")
@Data
public class CustomerGuarantorSaveReqVO {

    @Schema(description = "担保人主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "21420")
    private Long id;

    @Schema(description = "客户ID（被担保人，关联 crm_customer 主表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "7130")
    @NotNull(message = "客户ID（被担保人，关联 crm_customer 主表）不能为空")
    private Long customerId;

    @Schema(description = "授信ID（关联 crm_customer_credit.id）", example = "11623")
    private Long creditId;

    @Schema(description = "合同号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "合同号不能为空")
    private String contractNo;

    @Schema(description = "合同类型（字典: aicrm_guarantor_contract_type）", example = "2")
    private String contractType;

    @Schema(description = "合同状态（字典: aicrm_guarantor_contract_status，effective=有效，expired=已到期，cancelled=已解除）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "合同状态（字典: aicrm_guarantor_contract_status，effective=有效，expired=已到期，cancelled=已解除）不能为空")
    private String contractStatus;

    @Schema(description = "签约日期", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "签约日期不能为空")
    private LocalDate signDate;

    @Schema(description = "担保类型（字典: aicrm_guarantee_method，guarantee=保证）", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "担保类型（字典: aicrm_guarantee_method，guarantee=保证）不能为空")
    private String guaranteeType;

    @Schema(description = "担保人编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "担保人编号不能为空")
    private String guarantorNo;

    @Schema(description = "担保人姓名/名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "担保人姓名/名称不能为空")
    private String guarantorName;

    @Schema(description = "币种（字典: aicrm_currency_type）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "币种（字典: aicrm_currency_type）不能为空")
    private String currencyCode;

    @Schema(description = "担保总金额（万元）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "担保总金额（万元）不能为空")
    private BigDecimal guaranteeTotalAmount;

    @Schema(description = "业务起始日期")
    private LocalDate businessStartDate;

    @Schema(description = "业务截止日期")
    private LocalDate businessEndDate;

    @Schema(description = "担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）不能为空")
    private String guarantorType;

    @Schema(description = "担保人证件类型（字典: aicrm_identity_type）", example = "2")
    private String guarantorIdType;

    @Schema(description = "担保人证件号码（加密）")
    private String guarantorIdNo;

    @Schema(description = "与被担保人关系（字典: aicrm_relation_type）")
    private String relationWithBorrower;

    @Schema(description = "担保方式（字典: aicrm_guarantee_style，joint=连带责任，general=一般保证）")
    private String guaranteeMethod;

    @Schema(description = "已用担保金额（万元）")
    private BigDecimal usedAmount;

    @Schema(description = "可用担保金额（万元）")
    private BigDecimal availableAmount;

    @Schema(description = "客户经理ID（关联 system_users.id）", example = "23298")
    private Long managerUserId;

    @Schema(description = "管理机构ID（关联 system_dept.id）", example = "25584")
    private Long branchId;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}