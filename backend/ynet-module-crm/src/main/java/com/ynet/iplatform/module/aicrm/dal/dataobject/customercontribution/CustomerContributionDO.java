package com.ynet.iplatform.module.aicrm.dal.dataobject.customercontribution;

import java.time.LocalDate;

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
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户贡献度信息表（与客户主表1对1关系） DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_contribution")
@KeySequence("crm_customer_contribution_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerContributionDO extends BaseDO {

    /**
     * 贡献度主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表，1对1关系）
     */
    private Long customerId;
    /**
     * 序号
     */
    private Integer sequenceNo;
    /**
     * 客户综合贡献度
     */
    private BigDecimal totalContribution;
    /**
     * 存款贡献度
     */
    private BigDecimal depositContribution;
    /**
     * 贷款贡献度
     */
    private BigDecimal loanContribution;
    /**
     * 中间业务贡献度
     */
    private BigDecimal intermediateContribution;
    /**
     * 统计时间
     */
    private LocalDate statisticsTime;
    /**
     * 客户名称（老系统字段）
     */
    private String custName;
    /**
     * 机构ID（老系统）
     */
    private String orgId;
    /**
     * 存款贡献度（老系统字段名）
     */
    private BigDecimal contriDeposit;
    /**
     * 贷款贡献度（老系统字段名）
     */
    private BigDecimal contributionLoan;
    /**
     * 中间业务贡献度（老系统字段名）
     */
    private BigDecimal contributionMid;
    /**
     * 客户综合贡献度（老系统字段名）
     */
    private BigDecimal contributionCust;
    /**
     * 票据贴现贡献度
     */
    private BigDecimal contriBillDiscount;
    /**
     * 国际业务贡献度
     */
    private BigDecimal contriInternation;
    /**
     * 数据加载日期（老系统）
     */
    private LocalDate etlDate;
    /**
     * 理财业务贡献度
     */
    private BigDecimal wealthManagementContribution;
    /**
     * 卡业务贡献度
     */
    private BigDecimal cardContribution;
    /**
     * 结算业务贡献度
     */
    private BigDecimal settlementContribution;
    /**
     * 电子银行贡献度
     */
    private BigDecimal electronicBankingContribution;
    /**
     * 贡献度排名
     */
    private Integer contributionRank;
    /**
     * 贡献度等级（字典: aicrm_contribution_level）
     */
    private String contributionLevel;
    /**
     * 同比增长率（%）
     */
    private BigDecimal yearOverYearGrowth;
    /**
     * 环比增长率（%）
     */
    private BigDecimal monthOverMonthGrowth;
    /**
     * 统计周期（字典: aicrm_statistics_period，daily=日，monthly=月，quarterly=季，yearly=年）
     */
    private String statisticsPeriod;
    /**
     * 备注
     */
    private String remark;


}