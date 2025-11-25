package com.ynet.iplatform.module.aicrm.controller.admin.customercontribution.vo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import com.ynet.iplatform.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static com.ynet.iplatform.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 客户贡献度信息表（与客户主表1对1关系）分页 Request VO")
@Data
public class CustomerContributionPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联 crm_customer 主表，1对1关系）", example = "8070")
    private Long customerId;

    @Schema(description = "序号")
    private Integer sequenceNo;

    @Schema(description = "客户综合贡献度")
    private BigDecimal totalContribution;

    @Schema(description = "存款贡献度")
    private BigDecimal depositContribution;

    @Schema(description = "贷款贡献度")
    private BigDecimal loanContribution;

    @Schema(description = "中间业务贡献度")
    private BigDecimal intermediateContribution;

    @Schema(description = "统计时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] statisticsTime;

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
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] etlDate;

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

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}