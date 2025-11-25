package com.ynet.iplatform.module.aicrm.dal.dataobject.customeroffbalanceoverview;

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
 * 客户表外业务概览 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_overview_offbalance")
@KeySequence("crm_customer_overview_offbalance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerOffbalanceOverviewDO extends BaseDO {

    /**
     * 主键ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 客户编号
     */
    private String customerNo;
    /**
     * 统计日期
     */
    private LocalDate statisticsDate;
    /**
     * 业务类型
     */
    private String businessType;
    /**
     * 业务代码
     */
    private String businessCode;
    /**
     * 业务名称
     */
    private String businessName;
    /**
     * 币种
     */
    private String currency;
    /**
     * 业务金额
     */
    private BigDecimal businessAmount;
    /**
     * 业务余额
     */
    private BigDecimal businessBalance;
    /**
     * 担保金额
     */
    private BigDecimal guaranteeAmount;
    /**
     * 授信金额
     */
    private BigDecimal creditAmount;
    /**
     * 已用金额
     */
    private BigDecimal usedAmount;
    /**
     * 可用金额
     */
    private BigDecimal availableAmount;
    /**
     * 业务笔数
     */
    private Integer businessCount;
    /**
     * 本年余额日均
     */
    private BigDecimal balanceYearAvg;
    /**
     * 上年余额日均
     */
    private BigDecimal lastYearBalanceYearAvg;
    /**
     * 本季度余额日均
     */
    private BigDecimal balanceQuarterAvg;
    /**
     * 上年同期季度余额日均
     */
    private BigDecimal lastYearBalanceQuarterAvg;
    /**
     * 本月余额日均
     */
    private BigDecimal balanceMonthAvg;
    /**
     * 上年同期月余额日均
     */
    private BigDecimal lastYearBalanceMonthAvg;
    /**
     * 本年累计金额
     */
    private BigDecimal cumulativeYearAmount;
    /**
     * 上年累计金额
     */
    private BigDecimal lastYearCumulativeAmount;
    /**
     * 本季度累计金额
     */
    private BigDecimal cumulativeQuarterAmount;
    /**
     * 上年同期季度累计金额
     */
    private BigDecimal lastYearCumulativeQuarterAmount;
    /**
     * 本月累计金额
     */
    private BigDecimal cumulativeMonthAmount;
    /**
     * 上年同期月累计金额
     */
    private BigDecimal lastYearCumulativeMonthAmount;
    /**
     * 使用率
     */
    private BigDecimal usageRate;
    /**
     * 起始日期
     */
    private LocalDate startDate;
    /**
     * 到期日期
     */
    private LocalDate endDate;
    /**
     * 生效日期
     */
    private LocalDate effectiveDate;
    /**
     * 费率
     */
    private BigDecimal feeRate;
    /**
     * 手续费金额
     */
    private BigDecimal feeAmount;
    /**
     * 业务状态
     */
    private String businessStatus;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 利润贡献
     */
    private BigDecimal profitContribution;
    /**
     * 手续费收入
     */
    private BigDecimal feeIncome;
    /**
     * 备注
     */
    private String remark;


}