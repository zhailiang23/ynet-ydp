package com.ynet.iplatform.module.aicrm.dal.dataobject.customerchanneloverview;

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
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户渠道业务概览 DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_overview_channel")
@KeySequence("crm_customer_overview_channel_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerChannelOverviewDO extends BaseDO {

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
     * 渠道类型
     */
    private String channelType;
    /**
     * 渠道代码
     */
    private String channelCode;
    /**
     * 渠道名称
     */
    private String channelName;
    /**
     * 访问次数
     */
    private Integer accessCount;
    /**
     * 登录次数
     */
    private Integer loginCount;
    /**
     * 交易笔数
     */
    private Integer transactionCount;
    /**
     * 活跃天数
     */
    private Integer activeDays;
    /**
     * 交易金额
     */
    private BigDecimal transactionAmount;
    /**
     * 本年交易金额日均
     */
    private BigDecimal amountYearAvg;
    /**
     * 上年交易金额日均
     */
    private BigDecimal lastYearAmountYearAvg;
    /**
     * 本季度交易金额日均
     */
    private BigDecimal amountQuarterAvg;
    /**
     * 上年同期季度交易金额日均
     */
    private BigDecimal lastYearAmountQuarterAvg;
    /**
     * 本月交易金额日均
     */
    private BigDecimal amountMonthAvg;
    /**
     * 上年同期月交易金额日均
     */
    private BigDecimal lastYearAmountMonthAvg;
    /**
     * 本年累计交易金额
     */
    private BigDecimal cumulativeYearAmount;
    /**
     * 上年累计交易金额
     */
    private BigDecimal lastYearCumulativeAmount;
    /**
     * 本季度累计交易金额
     */
    private BigDecimal cumulativeQuarterAmount;
    /**
     * 上年同期季度累计交易金额
     */
    private BigDecimal lastYearCumulativeQuarterAmount;
    /**
     * 本月累计交易金额
     */
    private BigDecimal cumulativeMonthAmount;
    /**
     * 上年同期月累计交易金额
     */
    private BigDecimal lastYearCumulativeMonthAmount;
    /**
     * 首次访问时间
     */
    private LocalDateTime firstAccessTime;
    /**
     * 最近访问时间
     */
    private LocalDateTime lastAccessTime;
    /**
     * 最近交易时间
     */
    private LocalDateTime lastTransactionTime;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 设备型号
     */
    private String deviceModel;
    /**
     * 操作系统版本
     */
    private String osVersion;
    /**
     * 应用版本
     */
    private String appVersion;
    /**
     * 偏好业务
     */
    private String preferredBusiness;
    /**
     * 使用频率
     */
    private String usageFrequency;
    /**
     * 备注
     */
    private String remark;


}