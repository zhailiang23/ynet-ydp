package com.ynet.iplatform.module.aicrm.dal.dataobject.companyproject;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 对公客户项目信息 DO
 *
 * @author 易诚源码
 */
@TableName("crm_company_project")
@KeySequence("crm_company_project_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProjectDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联crm_customer表）
     */
    private Long customerId;
    /**
     * 项目编号
     */
    private String projectCode;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 项目类型（基建工程、房地产开发、技术改造、研发项目、并购重组、境外投资、产能扩张、环保治理等）
     */
    private String projectType;
    /**
     * 项目类别（自建、合作、收购、PPP等）
     */
    private String projectCategory;
    /**
     * 总投资额（元）
     */
    private BigDecimal totalInvestment;
    /**
     * 注册资本（元）
     */
    private BigDecimal registeredCapital;
    /**
     * 项目占地面积（平方米）
     */
    private BigDecimal projectArea;
    /**
     * 建筑面积（平方米）
     */
    private BigDecimal buildingArea;
    /**
     * 计划开工日期
     */
    private LocalDate planStartDate;
    /**
     * 实际开工日期
     */
    private LocalDate actualStartDate;
    /**
     * 计划完工日期
     */
    private LocalDate planCompleteDate;
    /**
     * 实际完工日期
     */
    private LocalDate actualCompleteDate;
    /**
     * 建设周期（月）
     */
    private Integer constructionPeriod;
    /**
     * 项目状态（1=筹备中 2=在建 3=已完工 4=运营中 5=暂停 6=终止）
     */
    private Integer projectStatus;
    /**
     * 项目进度（%）
     */
    private BigDecimal progressRate;
    /**
     * 项目所在省份
     */
    private String projectProvince;
    /**
     * 项目所在城市
     */
    private String projectCity;
    /**
     * 项目所在区县
     */
    private String projectDistrict;
    /**
     * 项目详细地址
     */
    private String projectAddress;
    /**
     * 自有资金（元）
     */
    private BigDecimal selfFunding;
    /**
     * 银行贷款（元）
     */
    private BigDecimal bankLoan;
    /**
     * 债券融资（元）
     */
    private BigDecimal bondFinancing;
    /**
     * 股权融资（元）
     */
    private BigDecimal equityFinancing;
    /**
     * 政府补助（元）
     */
    private BigDecimal governmentSubsidy;
    /**
     * 其他资金（元）
     */
    private BigDecimal otherFunding;
    /**
     * 累计完成投资（元）
     */
    private BigDecimal accumulatedInvestment;
    /**
     * 融资需求金额（元）
     */
    private BigDecimal financingRequirement;
    /**
     * 融资用途
     */
    private String financingPurpose;
    /**
     * 融资状态（1=未申请 2=申请中 3=已批准 4=已放款 5=已拒绝）
     */
    private Integer financingStatus;
    /**
     * 我行融资金额（元）
     */
    private BigDecimal ourBankFinancing;
    /**
     * 他行融资金额（元）
     */
    private BigDecimal otherBankFinancing;
    /**
     * 合作方（多个合作方用逗号分隔）
     */
    private String partners;
    /**
     * 总承包商
     */
    private String contractor;
    /**
     * 设计单位
     */
    private String designer;
    /**
     * 监理单位
     */
    private String supervisor;
    /**
     * 预计年收入（元）
     */
    private BigDecimal expectedRevenue;
    /**
     * 预计年利润（元）
     */
    private BigDecimal expectedProfit;
    /**
     * 投资回收期（年）
     */
    private BigDecimal paybackPeriod;
    /**
     * 内部收益率（%）
     */
    private BigDecimal irrRate;
    /**
     * 风险等级（低、中、高）
     */
    private String riskLevel;
    /**
     * 主要风险因素
     */
    private String riskFactors;
    /**
     * 风险缓释措施
     */
    private String riskMitigation;
    /**
     * 是否重点项目（0=否 1=市级 2=省级 3=国家级）
     */
    private Integer isKeyProject;
    /**
     * 政府批文
     */
    private String governmentApproval;
    /**
     * 政策支持
     */
    private String policySupport;
    /**
     * 环评批复（0=未申请 1=已批复 2=不需要）
     */
    private Integer environmentalApproval;
    /**
     * 用地批复（0=未批复 1=已批复 2=不需要）
     */
    private Integer landApproval;
    /**
     * 施工许可（0=未办理 1=已办理 2=不需要）
     */
    private Integer constructionPermit;
    /**
     * 项目负责人
     */
    private String projectManager;
    /**
     * 负责人电话
     */
    private String managerPhone;
    /**
     * 负责人邮箱
     */
    private String managerEmail;
    /**
     * 营销优先级（1=高 2=中 3=低）
     */
    private Integer marketingPriority;
    /**
     * 营销机会点（如贷款、结算、理财、投行等）
     */
    private String marketingOpportunity;
    /**
     * 跟进计划
     */
    private String followUpPlan;
    /**
     * 最后跟进日期
     */
    private LocalDate lastFollowUpDate;
    /**
     * 下次跟进日期
     */
    private LocalDate nextFollowUpDate;
    /**
     * 数据来源（客户经理录入、招标网、政府网站、企业公告等）
     */
    private String dataSource;
    /**
     * 备注
     */
    private String remark;


}