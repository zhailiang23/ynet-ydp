package com.ynet.iplatform.module.aicrm.dal.dataobject.customercreditdetail;

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
 * 客户授信使用明细表（零售+对公共用） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_credit_detail")
@KeySequence("crm_customer_credit_detail_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreditDetailDO extends BaseDO {

    /**
     * 授信明细主键
     */
    @TableId
    private Long id;
    /**
     * 授信ID（关联 crm_customer_credit.id）
     */
    private Long creditId;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 贷款编号/借据号
     */
    private String loanNo;
    /**
     * 合同编号
     */
    private String contractNo;
    /**
     * 放款日期
     */
    private LocalDate loanDate;
    /**
     * 到期日期
     */
    private LocalDate matureDate;
    /**
     * 贷款金额（元）
     */
    private BigDecimal loanAmount;
    /**
     * 贷款余额（元）
     */
    private BigDecimal balance;
    /**
     * 执行利率（%）
     */
    private BigDecimal interestRate;
    /**
     * 贷款状态（字典: aicrm_loan_status，normal=正常，overdue=逾期，settled=已结清）
     */
    private String loanStatus;
    /**
     * 结清日期
     */
    private LocalDate settleDate;
    /**
     * 贷款产品名称
     */
    private String productName;
    /**
     * 产品代码
     */
    private String productCode;
    /**
     * 备注
     */
    private String remark;


}