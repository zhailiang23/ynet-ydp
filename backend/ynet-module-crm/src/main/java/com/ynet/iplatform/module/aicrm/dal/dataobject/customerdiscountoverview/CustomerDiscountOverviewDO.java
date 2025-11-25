package com.ynet.iplatform.module.aicrm.dal.dataobject.customerdiscountoverview;

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
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户贴现业务概览 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_overview_discount")
@KeySequence("crm_customer_overview_discount_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDiscountOverviewDO extends BaseDO {

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
     * 贴现类型
     */
    private String discountType;
    /**
     * 票据号码
     */
    private String billNo;
    /**
     * 票据类型
     */
    private String billType;
    /**
     * 币种
     */
    private String currency;
    /**
     * 票据金额
     */
    private BigDecimal billAmount;
    /**
     * 贴现金额
     */
    private BigDecimal discountAmount;
    /**
     * 贴现余额
     */
    private BigDecimal discountBalance;
    /**
     * 贴息金额
     */
    private BigDecimal interestAmount;
    /**
     * 实付金额
     */
    private BigDecimal actualAmount;
    /**
     * 贴现笔数
     */
    private Integer discountCount;
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
     * 本年累计贴现金额
     */
    private BigDecimal cumulativeYearAmount;
    /**
     * 上年累计贴现金额
     */
    private BigDecimal lastYearCumulativeAmount;
    /**
     * 本季度累计贴现金额
     */
    private BigDecimal cumulativeQuarterAmount;
    /**
     * 上年同期季度累计贴现金额
     */
    private BigDecimal lastYearCumulativeQuarterAmount;
    /**
     * 本月累计贴现金额
     */
    private BigDecimal cumulativeMonthAmount;
    /**
     * 上年同期月累计贴现金额
     */
    private BigDecimal lastYearCumulativeMonthAmount;
    /**
     * 贴现利率
     */
    private BigDecimal discountRate;
    /**
     * 贴现天数
     */
    private Integer discountDays;
    /**
     * 票据出票日期
     */
    private LocalDate billIssueDate;
    /**
     * 票据到期日期
     */
    private LocalDate billDueDate;
    /**
     * 贴现日期
     */
    private LocalDate discountDate;
    /**
     * 结算日期
     */
    private LocalDate settleDate;
    /**
     * 出票人
     */
    private String drawer;
    /**
     * 收款人
     */
    private String payee;
    /**
     * 承兑人
     */
    private String acceptor;
    /**
     * 承兑银行
     */
    private String acceptanceBank;
    /**
     * 贴现状态
     */
    private String discountStatus;
    /**
     * 结算状态
     */
    private String settlementStatus;
    /**
     * 利润贡献
     */
    private BigDecimal profitContribution;
    /**
     * 备注
     */
    private String remark;


}