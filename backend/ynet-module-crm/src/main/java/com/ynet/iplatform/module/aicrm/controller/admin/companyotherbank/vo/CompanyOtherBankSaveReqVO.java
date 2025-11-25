package com.ynet.iplatform.module.aicrm.controller.admin.companyotherbank.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 对公客户他行信息新增/修改 Request VO")
@Data
public class CompanyOtherBankSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14194")
    private Long id;

    @Schema(description = "客户ID（关联crm_customer表）", requiredMode = Schema.RequiredMode.REQUIRED, example = "18661")
    @NotNull(message = "客户ID（关联crm_customer表）不能为空")
    private Long customerId;

    @Schema(description = "银行名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "银行名称不能为空")
    private String bankName;

    @Schema(description = "银行类型（国有大行、股份制银行、城商行、农商行、外资银行、政策性银行等）", example = "2")
    private String bankType;

    @Schema(description = "开户支行名称", example = "李四")
    private String branchName;

    @Schema(description = "他行客户经理姓名")
    private String relationshipManager;

    @Schema(description = "他行客户经理电话")
    private String managerPhone;

    @Schema(description = "他行客户经理邮箱")
    private String managerEmail;

    @Schema(description = "合作类型（主办行、协办行、一般合作）", example = "2")
    private String cooperationType;

    @Schema(description = "合作开始日期")
    private LocalDate cooperationStartDate;

    @Schema(description = "合作年限")
    private Integer relationshipDuration;

    @Schema(description = "合作状态（1=合作中 2=已终止 3=暂停合作）", example = "1")
    private Integer cooperationStatus;

    @Schema(description = "是否有结算账户（0=否 1=是）", example = "17775")
    private Integer hasSettlementAccount;

    @Schema(description = "结算账户账号")
    private String settlementAccountNo;

    @Schema(description = "账户余额（元）")
    private BigDecimal accountBalance;

    @Schema(description = "是否主结算行（0=否 1=是）")
    private Integer isMainSettlementBank;

    @Schema(description = "日均存款（元）")
    private BigDecimal dailyAverageBalance;

    @Schema(description = "授信总额度（元）")
    private BigDecimal totalCreditLimit;

    @Schema(description = "已用授信额度（元）")
    private BigDecimal usedCreditLimit;

    @Schema(description = "贷款余额（元）")
    private BigDecimal loanBalance;

    @Schema(description = "存款余额（元）")
    private BigDecimal depositBalance;

    @Schema(description = "理财余额（元）")
    private BigDecimal wealthManagementBalance;

    @Schema(description = "业务类型（多选，用逗号分隔：对公存款、流动资金贷款、项目贷款、贸易融资、票据业务、保理业务、供应链金融、投行业务、财务顾问、外汇业务、代发工资等）")
    private String businessTypes;

    @Schema(description = "主要业务")
    private String mainBusiness;

    @Schema(description = "贷款产品名称", example = "赵六")
    private String loanProductName;

    @Schema(description = "贷款金额（元）")
    private BigDecimal loanAmount;

    @Schema(description = "贷款利率（%）")
    private BigDecimal loanRate;

    @Schema(description = "贷款起始日")
    private LocalDate loanStartDate;

    @Schema(description = "贷款到期日")
    private LocalDate loanMaturityDate;

    @Schema(description = "担保方式（信用、抵押、质押、保证等）", example = "2")
    private String guaranteeType;

    @Schema(description = "抵押物信息")
    private String collateralInfo;

    @Schema(description = "服务满意度（1-5星）")
    private Integer serviceSatisfaction;

    @Schema(description = "价格水平（优惠、市场价、偏高）")
    private String pricingLevel;

    @Schema(description = "响应速度（快、一般、慢）")
    private String responseSpeed;

    @Schema(description = "客户评价")
    private String customerComment;

    @Schema(description = "他行优势（为什么客户选择他行）")
    private String competitorAdvantage;

    @Schema(description = "他行劣势（客户不满意的地方）")
    private String competitorDisadvantage;

    @Schema(description = "我行机会点（可以从哪些方面切入）")
    private String ourOpportunity;

    @Schema(description = "竞争策略（如何争取客户份额）")
    private String competitiveStrategy;

    @Schema(description = "目标业务（希望从他行抢占的业务）")
    private String targetBusiness;

    @Schema(description = "营销优先级（1=高 2=中 3=低）")
    private Integer marketingPriority;

    @Schema(description = "合同编号")
    private String contractNo;

    @Schema(description = "合同到期日")
    private LocalDate contractExpiryDate;

    @Schema(description = "是否即将到期（0=否 1=是，3个月内到期）")
    private Integer isDueSoon;

    @Schema(description = "跟进计划")
    private String followUpPlan;

    @Schema(description = "风险提示（如他行抽贷风险、担保风险等）")
    private String riskWarning;

    @Schema(description = "信息来源（客户经理调研、客户提供、公开信息、第三方渠道等）")
    private String infoSource;

    @Schema(description = "信息可靠性（1=高 2=中 3=低）")
    private Integer infoReliability;

    @Schema(description = "信息最后更新日期")
    private LocalDate lastUpdateDate;

    @Schema(description = "备注", example = "你猜")
    private String remark;

}