package com.ynet.iplatform.module.aicrm.dal.dataobject.customeraccountinsurance;

import lombok.*;
import java.util.*;
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
 * 客户保险账户信息表（零售+对公共用） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_account_insurance")
@KeySequence("crm_customer_account_insurance_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAccountInsuranceDO extends BaseDO {

    /**
     * 保险账户主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 保单号
     */
    private String accountNo;
    /**
     * 保险单号
     */
    private String policyNo;
    /**
     * 保险产品名称
     */
    private String productName;
    /**
     * 投保人姓名
     */
    private String accountName;
    /**
     * 投保日期（开户日期）
     */
    private LocalDate openDate;
    /**
     * 退保日期（销户日期）
     */
    private LocalDate closeDate;
    /**
     * 账户状态（字典: aicrm_insurance_status，valid=有效，expired=已过期，surrendered=已退保）
     */
    private String accountStatus;
    /**
     * 保险类型（字典: aicrm_insurance_type，life=寿险，health=健康险，accident=意外险，property=财产险）
     */
    private String insuranceType;
    /**
     * 保险公司
     */
    private String insuranceCompany;
    /**
     * 保险期限（如：终身、20年、至70岁）
     */
    private String insuranceTerm;
    /**
     * 缴费期限（如：趸交、5年、10年、20年）
     */
    private String paymentTerm;
    /**
     * 缴费频率（字典: aicrm_payment_frequency，once=趸交，year=年交，halfyear=半年交，quarter=季交，month=月交）
     */
    private String paymentFrequency;
    /**
     * 保险金额（保额）
     */
    private BigDecimal insuredAmount;
    /**
     * 保费（年交保费）
     */
    private BigDecimal premium;
    /**
     * 已交保费
     */
    private BigDecimal paidPremium;
    /**
     * 现金价值
     */
    private BigDecimal cashValue;
    /**
     * 账户价值（万能险、投连险）
     */
    private BigDecimal balance;
    /**
     * 币种
     */
    private String currencyType;
    /**
     * 被保险人姓名
     */
    private String insuredName;
    /**
     * 与投保人关系（字典: aicrm_insured_relation）
     */
    private String insuredRelation;
    /**
     * 受益人姓名
     */
    private String beneficiaryName;
    /**
     * 生效日期
     */
    private LocalDate effectiveDate;
    /**
     * 到期日期
     */
    private LocalDate expireDate;
    /**
     * 下次缴费日期
     */
    private LocalDate nextPaymentDate;
    /**
     * 销售机构ID
     */
    private Long deptId;
    /**
     * 销售机构名称
     */
    private String deptName;
    /**
     * 保险顾问用户ID
     */
    private Long managerUserId;
    /**
     * 备注
     */
    private String remark;


}
