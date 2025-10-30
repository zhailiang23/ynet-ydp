package cn.iocoder.yudao.module.aicrm.controller.admin.customercontribution.vo;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Schema(description = "管理后台 - 客户贡献度信息表（与客户主表1对1关系）新增/修改 Request VO")
@Data
public class CustomerContributionSaveReqVO {

    @Schema(description = "贡献度主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "3607")
    private Long id;

    @Schema(description = "客户ID（关联 crm_customer 主表，1对1关系）", requiredMode = Schema.RequiredMode.REQUIRED, example = "8070")
    @NotNull(message = "客户ID（关联 crm_customer 主表，1对1关系）不能为空")
    private Long customerId;

    @Schema(description = "序号")
    private Integer sequenceNo;

    @Schema(description = "客户综合贡献度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "客户综合贡献度不能为空")
    private BigDecimal totalContribution;

    @Schema(description = "存款贡献度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "存款贡献度不能为空")
    private BigDecimal depositContribution;

    @Schema(description = "贷款贡献度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "贷款贡献度不能为空")
    private BigDecimal loanContribution;

    @Schema(description = "中间业务贡献度", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "中间业务贡献度不能为空")
    private BigDecimal intermediateContribution;

    @Schema(description = "统计时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "统计时间不能为空")
    private LocalDate statisticsTime;

    @Schema(description = "客户名称（老系统字段）", example = "张三")
    private String custName;

    @Schema(description = "机构ID（老系统）", example = "13497")
    private String orgId;

    @Schema(description = "存款贡献度（老系统字段名）")
    private BigDecimal contriDeposit;

    @Schema(description = "贷款贡献度（老系统字段名）")
    private BigDecimal contributionLoan;

    @Schema(description = "中间业务贡献度（老系统字段名）", example = "9722")
    private BigDecimal contributionMid;

    @Schema(description = "客户综合贡献度（老系统字段名）")
    private BigDecimal contributionCust;

    @Schema(description = "票据贴现贡献度", example = "15048")
    private BigDecimal contriBillDiscount;

    @Schema(description = "国际业务贡献度")
    private BigDecimal contriInternation;

    @Schema(description = "数据加载日期（老系统）")
    private LocalDate etlDate;

    @Schema(description = "理财业务贡献度")
    private BigDecimal wealthManagementContribution;

    @Schema(description = "卡业务贡献度")
    private BigDecimal cardContribution;

    @Schema(description = "结算业务贡献度")
    private BigDecimal settlementContribution;

    @Schema(description = "电子银行贡献度")
    private BigDecimal electronicBankingContribution;

    @Schema(description = "贡献度排名")
    private Integer contributionRank;

    @Schema(description = "贡献度等级（字典: aicrm_contribution_level）")
    private String contributionLevel;

    @Schema(description = "同比增长率（%）")
    private BigDecimal yearOverYearGrowth;

    @Schema(description = "环比增长率（%）")
    private BigDecimal monthOverMonthGrowth;

    @Schema(description = "统计周期（字典: aicrm_statistics_period，daily=日，monthly=月，quarterly=季，yearly=年）")
    private String statisticsPeriod;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

}