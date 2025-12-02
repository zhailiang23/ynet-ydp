package com.ynet.iplatform.module.aicrm.dal.dataobject.customertransactionmock;

import java.time.LocalTime;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户交易明细信息表（Mock数据） DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_transaction_mock")
@KeySequence("crm_customer_transaction_mock_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerTransactionMockDO extends BaseDO {

    /**
     * 交易主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 账户ID（关联账户表）
     */
    private Long accountId;
    /**
     * 交易日期
     */
    private LocalDate transactionDate;
    /**
     * 交易时间
     */
    private LocalTime transactionTime;
    /**
     * 交易机构编号
     */
    private String branchNo;
    /**
     * 交易机构名称
     */
    private String branchName;
    /**
     * 原交易代码
     */
    private String originalTranCode;
    /**
     * 现转标志（字典: aicrm_cash_flag）
     */
    private String cashFlag;
    /**
     * 子账户编号
     */
    private String subAccountNo;
    /**
     * 账户编号
     */
    private String accountNo;
    /**
     * 币种（字典: aicrm_currency_type）
     */
    private String currencyCode;
    /**
     * 交易流水号
     */
    private String tansNo;
    /**
     * 交易类型（字典: aicrm_transaction_type）
     */
    private String tradType;
    /**
     * 交易金额
     */
    private BigDecimal tradMoney;
    /**
     * 账户余额
     */
    private BigDecimal acctBal;
    /**
     * 交易摘要
     */
    private String tradAbs;
    /**
     * 审核标志
     */
    private String review;
    /**
     * 交易渠道（字典: aicrm_transaction_channel）
     */
    private String tradChn;
    /**
     * 交易柜员
     */
    private String tradTeller;
    /**
     * 经办人
     */
    private String handler;
    /**
     * 对方账号
     */
    private String advsAcct;
    /**
     * 对方户名
     */
    private String advsAcctName;
    /**
     * 往来类型
     */
    private String contactType;
    /**
     * 钞汇标志
     */
    private String currTranFlag;
    /**
     * 贷款标志
     */
    private String loanFlag;
    /**
     * 手续费
     */
    private BigDecimal cost;
    /**
     * 记账日期
     */
    private LocalDate accountinDate;
    /**
     * 交易状态（字典: aicrm_transaction_status，success=成功，failed=失败，pending=处理中）
     */
    private String transactionStatus;
    /**
     * 交易方向（字典: aicrm_transaction_direction，in=转入，out=转出）
     */
    private String direction;
    /**
     * 备注
     */
    private String remark;


}