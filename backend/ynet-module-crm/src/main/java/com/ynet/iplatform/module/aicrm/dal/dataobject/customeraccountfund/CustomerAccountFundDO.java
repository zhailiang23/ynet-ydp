package com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountfund;

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
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户基金账户信息表（零售+对公共用） DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_account_fund")
@KeySequence("crm_customer_account_fund_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountFundDO extends BaseDO {

    /**
     * 基金账户主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 基金账号
     */
    private String accountNo;
    /**
     * 基金代码
     */
    private String fundCode;
    /**
     * 基金名称
     */
    private String productName;
    /**
     * 户名
     */
    private String accountName;
    /**
     * 开户日期（首次申购日期）
     */
    private LocalDate openDate;
    /**
     * 销户日期（全部赎回日期）
     */
    private LocalDate closeDate;
    /**
     * 账户状态（字典: aicrm_fund_account_status，holding=持有中，redeemed=已赎回）
     */
    private String accountStatus;
    /**
     * 基金类型（字典: aicrm_fund_type，stock=股票型，bond=债券型，mixed=混合型，money=货币型，index=指数型）
     */
    private String fundType;
    /**
     * 基金公司
     */
    private String fundCompany;
    /**
     * 风险等级（字典: aicrm_risk_level）
     */
    private String riskLevel;
    /**
     * 币种
     */
    private String currencyType;
    /**
     * 持有份额
     */
    private BigDecimal holdingShares;
    /**
     * 累计申购金额
     */
    private BigDecimal purchaseAmount;
    /**
     * 当前净值
     */
    private BigDecimal currentNav;
    /**
     * 当前市值
     */
    private BigDecimal currentValue;
    /**
     * 累计收益
     */
    private BigDecimal accumulatedIncome;
    /**
     * 账户余额（现金部分）
     */
    private BigDecimal balance;
    /**
     * 成本价
     */
    private BigDecimal costPrice;
    /**
     * 收益率（%）
     */
    private BigDecimal profitRate;
    /**
     * 今日收益
     */
    private BigDecimal todayIncome;
    /**
     * 昨日收益
     */
    private BigDecimal yesterdayIncome;
    /**
     * 销售机构ID
     */
    private Long deptId;
    /**
     * 销售机构名称
     */
    private String deptName;
    /**
     * 基金顾问用户ID
     */
    private Long managerUserId;
    /**
     * 备注
     */
    private String remark;


}
