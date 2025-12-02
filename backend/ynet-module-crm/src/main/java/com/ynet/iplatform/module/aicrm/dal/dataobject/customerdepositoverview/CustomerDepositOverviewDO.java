package com.ynet.iplatform.module.aicrm.dal.dataobject.customerdepositoverview;

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
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户存款业务概览 DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_overview_deposit")
@KeySequence("crm_customer_overview_deposit_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDepositOverviewDO extends BaseDO {

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
     * 存款类型
     */
    private String depositType;
    /**
     * 币种
     */
    private String currency;
    /**
     * 账号
     */
    private String accountNo;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 存款余额
     */
    private BigDecimal depositBalance;
    /**
     * 业务金额
     */
    private BigDecimal businessAmount;
    /**
     * 原币金额
     */
    private BigDecimal originalAmount;
    /**
     * 存款账户数
     */
    private Integer depositAccountCount;
    /**
     * 账户状态
     */
    private String accountStatus;
    /**
     * 科目代码
     */
    private String subjectCode;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 本年余额日均
     */
    private BigDecimal balanceYearAvg;
    /**
     * 本年实际余额日均
     */
    private BigDecimal realBalanceYearAvg;
    /**
     * 本年累计存款
     */
    private BigDecimal depositCumulativeYear;
    /**
     * 本季度余额日均
     */
    private BigDecimal balanceQuarterAvg;
    /**
     * 本季度实际余额日均
     */
    private BigDecimal realBalanceQuarterAvg;
    /**
     * 本季度累计存款
     */
    private BigDecimal depositCumulativeQuarter;
    /**
     * 本月余额日均
     */
    private BigDecimal balanceMonthAvg;
    /**
     * 本月实际余额日均
     */
    private BigDecimal realBalanceMonthAvg;
    /**
     * 本月累计存款
     */
    private BigDecimal depositCumulativeMonth;
    /**
     * 月度总流入
     */
    private BigDecimal monthTotalIn;
    /**
     * 月度总流出
     */
    private BigDecimal monthTotalOut;
    /**
     * 购买金额
     */
    private BigDecimal buyAmount;
    /**
     * 存款利率
     */
    private BigDecimal interestRate;
    /**
     * FTP定价
     */
    private BigDecimal ftpPrice;
    /**
     * 存款利润贡献
     */
    private BigDecimal depositProfit;
    /**
     * 开户日期
     */
    private LocalDate openDate;
    /**
     * 起息日期
     */
    private LocalDate startInterestDate;
    /**
     * 到期日期
     */
    private LocalDate matureDate;
    /**
     * 销户日期
     */
    private LocalDate logoutDate;
    /**
     * 机构编号
     */
    private String orgNo;
    /**
     * 机构名称
     */
    private String orgName;
    /**
     * 备注
     */
    private String remark;


}