package com.ynet.iplatform.module.aicrm.dal.dataobject.customercredit;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
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
 * 客户授信信息表（零售+对公共用） DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_credit")
@KeySequence("crm_customer_credit_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerCreditDO extends BaseDO {

    /**
     * 授信主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 统计日期
     */
    private LocalDate statisticsDate;
    /**
     * 授信协议号
     */
    private String creditAgreementNo;
    /**
     * 授信品种（字典: aicrm_credit_product_type）
     */
    private String creditProductType;
    /**
     * 授信币种（字典: aicrm_currency_type）
     */
    private String currencyCode;
    /**
     * 授信额度（元）
     */
    private BigDecimal creditLimit;
    /**
     * 已用额度（元）
     */
    private BigDecimal usedLimit;
    /**
     * 可用额度（元）
     */
    private BigDecimal availableLimit;
    /**
     * 使用比例（%）
     */
    private BigDecimal usageRatio;
    /**
     * 授信起始日
     */
    private LocalDate creditStartDate;
    /**
     * 授信到期日
     */
    private LocalDate creditEndDate;
    /**
     * 授信类型（字典: aicrm_credit_type，comprehensive=综合授信，single=单笔授信）
     */
    private String creditType;
    /**
     * 授信状态（字典: aicrm_credit_status，effective=生效中，expired=已到期，cancelled=已撤销，frozen=已冻结）
     */
    private String creditStatus;
    /**
     * 授信审批日期
     */
    private LocalDate approveDate;
    /**
     * 授信审批金额（元）
     */
    private BigDecimal approveAmount;
    /**
     * 授信利率（%）
     */
    private BigDecimal interestRate;
    /**
     * 担保方式（字典: aicrm_guarantee_type）
     */
    private String guaranteeType;
    /**
     * 授信用途
     */
    private String creditPurpose;
    /**
     * 审批人ID（关联 system_users.id）
     */
    private Long approverUserId;
    /**
     * 审批部门ID（关联 system_dept.id）
     */
    private Long approverDeptId;
    /**
     * 客户经理ID（关联 system_users.id）
     */
    private Long managerUserId;
    /**
     * 授信网点ID（关联 system_dept.id）
     */
    private Long branchId;
    /**
     * 备注
     */
    private String remark;


}