package com.ynet.iplatform.module.aicrm.controller.admin.retailcustomer.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "管理后台 - 零售客户概况响应 VO")
@Data
public class RetailCustomerOverviewRespVO {

    @Schema(description = "财务指标")
    private FinancialMetricsVO financialMetrics;

    @Schema(description = "资产趋势数据")
    private List<AssetTrendVO> assetTrend;

    @Schema(description = "资产结构分布")
    private AssetStructureVO assetStructure;

    @Schema(description = "存款类型分布")
    private DepositTypeDistributionVO depositDistribution;

    @Schema(description = "客户评级信息")
    private CustomerRatingVO rating;

    @Schema(description = "客户贡献度")
    private CustomerContributionVO contribution;

    @Schema(description = "产品持有统计")
    private ProductHoldingStatVO productStat;

    @Schema(description = "最近重要事件")
    private List<CustomerEventVO> recentEvents;

    @Schema(description = "客户标签")
    private List<String> tags;

    @Schema(description = "产品持有数量趋势")
    private List<ProductHoldingTrendVO> productHoldingTrend;

    @Schema(description = "月度交易统计")
    private List<MonthlyTransactionVO> monthlyTransactions;

    @Schema(description = "收益分析")
    private List<RevenueAnalysisVO> revenueAnalysis;

    @Schema(description = "风险评估")
    private RiskAssessmentVO riskAssessment;

    @Schema(description = "客户活跃度热力图")
    private List<ActivityHeatmapVO> activityHeatmap;

    // 内部类定义

    @Schema(description = "财务指标 VO")
    @Data
    public static class FinancialMetricsVO {
        @Schema(description = "总资产")
        private BigDecimal totalAssets;

        @Schema(description = "总负债")
        private BigDecimal totalLiabilities;

        @Schema(description = "净资产")
        private BigDecimal netAssets;

        @Schema(description = "存款余额")
        private BigDecimal depositBalance;

        @Schema(description = "贷款余额")
        private BigDecimal loanBalance;

        @Schema(description = "理财金额")
        private BigDecimal wealthBalance;

        @Schema(description = "可用余额")
        private BigDecimal availableBalance;

        @Schema(description = "信用额度")
        private BigDecimal creditLimit;

        @Schema(description = "总资产增长率")
        private BigDecimal totalAssetsGrowth;

        @Schema(description = "存款增长率")
        private BigDecimal depositGrowth;

        @Schema(description = "理财增长率")
        private BigDecimal wealthGrowth;
    }

    @Schema(description = "资产趋势 VO")
    @Data
    public static class AssetTrendVO {
        @Schema(description = "月份 (YYYY-MM)")
        private String month;

        @Schema(description = "总资产")
        private BigDecimal totalAssets;

        @Schema(description = "存款")
        private BigDecimal deposits;

        @Schema(description = "理财")
        private BigDecimal wealth;

        @Schema(description = "贷款")
        private BigDecimal loans;
    }

    @Schema(description = "资产结构 VO")
    @Data
    public static class AssetStructureVO {
        @Schema(description = "存款金额")
        private BigDecimal depositAmount;

        @Schema(description = "理财金额")
        private BigDecimal wealthAmount;

        @Schema(description = "基金金额")
        private BigDecimal fundAmount;

        @Schema(description = "保险金额")
        private BigDecimal insuranceAmount;

        @Schema(description = "贵金属金额")
        private BigDecimal metalAmount;

        @Schema(description = "信托金额")
        private BigDecimal trustAmount;

        @Schema(description = "其他金额")
        private BigDecimal otherAmount;
    }

    @Schema(description = "存款类型分布 VO")
    @Data
    public static class DepositTypeDistributionVO {
        @Schema(description = "活期存款金额")
        private BigDecimal currentDepositAmount;

        @Schema(description = "定期存款金额")
        private BigDecimal timeDepositAmount;

        @Schema(description = "通知存款金额")
        private BigDecimal noticeDepositAmount;

        @Schema(description = "其他存款金额")
        private BigDecimal otherDepositAmount;
    }

    @Schema(description = "客户评级 VO")
    @Data
    public static class CustomerRatingVO {
        @Schema(description = "价值等级")
        private String valueLevel;

        @Schema(description = "服务等级")
        private String serviceLevel;

        @Schema(description = "风险等级")
        private String riskLevel;

        @Schema(description = "评级分数")
        private Integer ratingScore;
    }

    @Schema(description = "客户贡献度 VO")
    @Data
    public static class CustomerContributionVO {
        @Schema(description = "综合贡献度等级 (高/中/低)")
        private String overallLevel;

        @Schema(description = "存款贡献度分数")
        private Integer depositScore;

        @Schema(description = "贷款贡献度分数")
        private Integer loanScore;

        @Schema(description = "中间业务贡献度分数")
        private Integer middleBusinessScore;
    }

    @Schema(description = "产品持有统计 VO")
    @Data
    public static class ProductHoldingStatVO {
        @Schema(description = "存款账户数")
        private Integer depositAccountCount;

        @Schema(description = "理财产品数")
        private Integer wealthProductCount;

        @Schema(description = "基金数")
        private Integer fundCount;

        @Schema(description = "信用卡数")
        private Integer creditcardCount;

        @Schema(description = "信托数")
        private Integer trustCount;

        @Schema(description = "保险数")
        private Integer insuranceCount;
    }

    @Schema(description = "客户事件 VO")
    @Data
    public static class CustomerEventVO {
        @Schema(description = "事件ID")
        private Long id;

        @Schema(description = "事件名称")
        private String eventName;

        @Schema(description = "事件类型")
        private String eventType;

        @Schema(description = "事件日期")
        private String eventDate;

        @Schema(description = "事件描述")
        private String eventDescription;
    }

    @Schema(description = "产品持有趋势 VO")
    @Data
    public static class ProductHoldingTrendVO {
        @Schema(description = "产品类型")
        private String productType;

        @Schema(description = "产品数量")
        private Integer productCount;
    }

    @Schema(description = "月度交易统计 VO")
    @Data
    public static class MonthlyTransactionVO {
        @Schema(description = "月份 (YYYY-MM)")
        private String month;

        @Schema(description = "交易笔数")
        private Integer transactionCount;

        @Schema(description = "交易金额")
        private BigDecimal transactionAmount;
    }

    @Schema(description = "收益分析 VO")
    @Data
    public static class RevenueAnalysisVO {
        @Schema(description = "月份 (YYYY-MM)")
        private String month;

        @Schema(description = "存款收益")
        private BigDecimal depositRevenue;

        @Schema(description = "理财收益")
        private BigDecimal wealthRevenue;

        @Schema(description = "基金收益")
        private BigDecimal fundRevenue;

        @Schema(description = "其他收益")
        private BigDecimal otherRevenue;
    }

    @Schema(description = "风险评估 VO")
    @Data
    public static class RiskAssessmentVO {
        @Schema(description = "信用风险评分 (0-100)")
        private Integer creditRiskScore;

        @Schema(description = "市场风险评分 (0-100)")
        private Integer marketRiskScore;

        @Schema(description = "流动性风险评分 (0-100)")
        private Integer liquidityRiskScore;

        @Schema(description = "操作风险评分 (0-100)")
        private Integer operationalRiskScore;

        @Schema(description = "合规风险评分 (0-100)")
        private Integer complianceRiskScore;
    }

    @Schema(description = "活跃度热力图 VO")
    @Data
    public static class ActivityHeatmapVO {
        @Schema(description = "星期几 (0-6, 0表示周日)")
        private Integer dayOfWeek;

        @Schema(description = "小时 (0-23)")
        private Integer hour;

        @Schema(description = "活跃度值")
        private Integer activityValue;
    }
}
