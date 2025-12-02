package com.ynet.iplatform.module.aicrm.dal.dataobject.customerloanoverview;

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
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户贷款业务概览 DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_overview_loan")
@KeySequence("crm_customer_overview_loan_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerLoanOverviewDO extends BaseDO {

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
     * 贷款类型
     */
    private String loanType;
    /**
     * 币种
     */
    private String currency;
    /**
     * 贷款余额
     */
    private BigDecimal loanAmount;
    /**
     * 贷款账户数
     */
    private Integer loanAccountCount;
    /**
     * 贷款客户数
     */
    private Integer loanCustomerCount;
    /**
     * 正常业务余额
     */
    private BigDecimal normalBusinessBalance;
    /**
     * 逾期余额
     */
    private BigDecimal overdueBalance;
    /**
     * 不良贷款余额
     */
    private BigDecimal badLoanBalance;
    /**
     * 业务余额
     */
    private BigDecimal businessBalance;
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
     * 贷款金额
     */
    private BigDecimal loanAmountTotal;
    /**
     * 上年贷款余额
     */
    private BigDecimal lastYearLoanAmount;
    /**
     * 业务金额
     */
    private BigDecimal businessAmount;
    /**
     * 授信总额
     */
    private BigDecimal creditTotalAmount;
    /**
     * 本年金额日均
     */
    private BigDecimal amountYearAvg;
    /**
     * 上年金额日均
     */
    private BigDecimal lastYearAmountYearAvg;
    /**
     * 本季度金额日均
     */
    private BigDecimal amountQuarterAvg;
    /**
     * 上年同期季度金额日均
     */
    private BigDecimal lastYearAmountQuarterAvg;
    /**
     * 本月金额日均
     */
    private BigDecimal amountMonthAvg;
    /**
     * 上年同期月金额日均
     */
    private BigDecimal lastYearAmountMonthAvg;
    /**
     * 表外贷款使用率
     */
    private BigDecimal outsideLoanUsage;
    /**
     * 表内贷款使用率
     */
    private BigDecimal insideLoanUsage;
    /**
     * 票据贷款使用率
     */
    private BigDecimal billLoanUsage;
    /**
     * 贷款利润贡献
     */
    private BigDecimal loanProfit;
    /**
     * 贷款利率
     */
    private BigDecimal interestRate;
    /**
     * 逾期天数
     */
    private Integer overdueDays;
    /**
     * 风险等级
     */
    private String riskLevel;
    /**
     * 备注
     */
    private String remark;


}