package com.ynet.iplatform.module.aicrm.dal.dataobject.customermiddlebusinessoverview;

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
 * 客户中间业务概览 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_overview_middlebusiness")
@KeySequence("crm_customer_overview_middlebusiness_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerMiddleBusinessOverviewDO extends BaseDO {

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
     * 交易金额
     */
    private BigDecimal transactionAmount;
    /**
     * 手续费金额
     */
    private BigDecimal feeAmount;
    /**
     * 佣金金额
     */
    private BigDecimal commissionAmount;
    /**
     * 交易笔数
     */
    private Integer transactionCount;
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
     * 利润贡献
     */
    private BigDecimal profitContribution;
    /**
     * 手续费收入
     */
    private BigDecimal feeIncome;
    /**
     * 佣金收入
     */
    private BigDecimal commissionIncome;
    /**
     * 备注
     */
    private String remark;


}