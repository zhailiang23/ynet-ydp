package com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountdeposit;

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
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户存款账户信息表（零售+对公共用） DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_account_deposit")
@KeySequence("crm_customer_account_deposit_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountDepositDO extends BaseDO {

    /**
     * 存款账户主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 账号
     */
    private String accountNo;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 户名
     */
    private String accountName;
    /**
     * 开户日期
     */
    private LocalDate openDate;
    /**
     * 销户日期
     */
    private LocalDate closeDate;
    /**
     * 账户状态（字典: aicrm_account_status，normal=正常，closed=销户，frozen=冻结）
     */
    private String accountStatus;
    /**
     * 利率（%）
     */
    private BigDecimal interestRate;
    /**
     * 存期（如：1年、6个月、3个月、活期）
     */
    private String depositTerm;
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 协议号
     */
    private String agrNo;
    /**
     * 产品ID
     */
    private String productId;
    /**
     * 卡号
     */
    private String cardNo;
    /**
     * 存款类型（1=活期，2=定期）
     */
    private String depositType;
    /**
     * 币种（字典: aicrm_currency_type）
     */
    private String currencyType;
    /**
     * 原始金额（开户金额）
     */
    private BigDecimal originalAmount;
    /**
     * 到期日
     */
    private LocalDate matureDate;
    /**
     * 起息日
     */
    private LocalDate startInterestDate;
    /**
     * 开户机构ID（关联 system_dept.id）
     */
    private Long deptId;
    /**
     * 开户机构名称
     */
    private String deptName;
    /**
     * 客户经理用户ID（关联 system_users.id）
     */
    private Long managerUserId;
    /**
     * 月日均余额
     */
    private BigDecimal monthAvgBalance;
    /**
     * 季日均余额
     */
    private BigDecimal quarterAvgBalance;
    /**
     * 年日均余额
     */
    private BigDecimal yearAvgBalance;
    /**
     * 月累计转入
     */
    private BigDecimal monthTotalIn;
    /**
     * 月累计转出
     */
    private BigDecimal monthTotalOut;
    /**
     * 备注
     */
    private String remark;


}
