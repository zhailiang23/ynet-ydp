package cn.iocoder.yudao.module.aicrm.controller.admin.companyproject.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import java.time.LocalDate;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 对公客户项目信息分页 Request VO")
@Data
public class CompanyProjectPageReqVO extends PageParam {

    @Schema(description = "客户ID（关联crm_customer表）", example = "19203")
    private Long customerId;

    @Schema(description = "项目编号")
    private String projectCode;

    @Schema(description = "项目名称", example = "李四")
    private String projectName;

    @Schema(description = "项目类型（基建工程、房地产开发、技术改造、研发项目、并购重组、境外投资、产能扩张、环保治理等）", example = "1")
    private String projectType;

    @Schema(description = "项目类别（自建、合作、收购、PPP等）")
    private String projectCategory;

    @Schema(description = "总投资额（元）")
    private BigDecimal totalInvestment;

    @Schema(description = "注册资本（元）")
    private BigDecimal registeredCapital;

    @Schema(description = "项目占地面积（平方米）")
    private BigDecimal projectArea;

    @Schema(description = "建筑面积（平方米）")
    private BigDecimal buildingArea;

    @Schema(description = "计划开工日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] planStartDate;

    @Schema(description = "实际开工日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] actualStartDate;

    @Schema(description = "计划完工日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] planCompleteDate;

    @Schema(description = "实际完工日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] actualCompleteDate;

    @Schema(description = "建设周期（月）")
    private Integer constructionPeriod;

    @Schema(description = "项目状态（1=筹备中 2=在建 3=已完工 4=运营中 5=暂停 6=终止）", example = "2")
    private Integer projectStatus;

    @Schema(description = "项目进度（%）")
    private BigDecimal progressRate;

    @Schema(description = "项目所在省份")
    private String projectProvince;

    @Schema(description = "项目所在城市")
    private String projectCity;

    @Schema(description = "项目所在区县")
    private String projectDistrict;

    @Schema(description = "项目详细地址")
    private String projectAddress;

    @Schema(description = "自有资金（元）")
    private BigDecimal selfFunding;

    @Schema(description = "银行贷款（元）")
    private BigDecimal bankLoan;

    @Schema(description = "债券融资（元）")
    private BigDecimal bondFinancing;

    @Schema(description = "股权融资（元）")
    private BigDecimal equityFinancing;

    @Schema(description = "政府补助（元）")
    private BigDecimal governmentSubsidy;

    @Schema(description = "其他资金（元）")
    private BigDecimal otherFunding;

    @Schema(description = "累计完成投资（元）")
    private BigDecimal accumulatedInvestment;

    @Schema(description = "融资需求金额（元）")
    private BigDecimal financingRequirement;

    @Schema(description = "融资用途")
    private String financingPurpose;

    @Schema(description = "融资状态（1=未申请 2=申请中 3=已批准 4=已放款 5=已拒绝）", example = "1")
    private Integer financingStatus;

    @Schema(description = "我行融资金额（元）")
    private BigDecimal ourBankFinancing;

    @Schema(description = "他行融资金额（元）")
    private BigDecimal otherBankFinancing;

    @Schema(description = "合作方（多个合作方用逗号分隔）")
    private String partners;

    @Schema(description = "总承包商")
    private String contractor;

    @Schema(description = "设计单位")
    private String designer;

    @Schema(description = "监理单位")
    private String supervisor;

    @Schema(description = "预计年收入（元）")
    private BigDecimal expectedRevenue;

    @Schema(description = "预计年利润（元）")
    private BigDecimal expectedProfit;

    @Schema(description = "投资回收期（年）")
    private BigDecimal paybackPeriod;

    @Schema(description = "内部收益率（%）")
    private BigDecimal irrRate;

    @Schema(description = "风险等级（低、中、高）")
    private String riskLevel;

    @Schema(description = "主要风险因素")
    private String riskFactors;

    @Schema(description = "风险缓释措施")
    private String riskMitigation;

    @Schema(description = "是否重点项目（0=否 1=市级 2=省级 3=国家级）")
    private Integer isKeyProject;

    @Schema(description = "政府批文")
    private String governmentApproval;

    @Schema(description = "政策支持")
    private String policySupport;

    @Schema(description = "环评批复（0=未申请 1=已批复 2=不需要）")
    private Integer environmentalApproval;

    @Schema(description = "用地批复（0=未批复 1=已批复 2=不需要）")
    private Integer landApproval;

    @Schema(description = "施工许可（0=未办理 1=已办理 2=不需要）")
    private Integer constructionPermit;

    @Schema(description = "项目负责人")
    private String projectManager;

    @Schema(description = "负责人电话")
    private String managerPhone;

    @Schema(description = "负责人邮箱")
    private String managerEmail;

    @Schema(description = "营销优先级（1=高 2=中 3=低）")
    private Integer marketingPriority;

    @Schema(description = "营销机会点（如贷款、结算、理财、投行等）")
    private String marketingOpportunity;

    @Schema(description = "跟进计划")
    private String followUpPlan;

    @Schema(description = "最后跟进日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] lastFollowUpDate;

    @Schema(description = "下次跟进日期")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDate[] nextFollowUpDate;

    @Schema(description = "数据来源（客户经理录入、招标网、政府网站、企业公告等）")
    private String dataSource;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}