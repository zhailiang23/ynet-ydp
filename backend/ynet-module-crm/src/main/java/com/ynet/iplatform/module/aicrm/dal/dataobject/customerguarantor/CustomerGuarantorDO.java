package com.ynet.iplatform.module.aicrm.dal.dataobject.customerguarantor;

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
 * 客户担保人信息表（零售+对公共用） DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_guarantor")
@KeySequence("crm_customer_guarantor_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGuarantorDO extends BaseDO {

    /**
     * 担保人主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（被担保人，关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 授信ID（关联 crm_customer_credit.id）
     */
    private Long creditId;
    /**
     * 合同号
     */
    private String contractNo;
    /**
     * 合同类型（字典: aicrm_guarantor_contract_type）
     */
    private String contractType;
    /**
     * 合同状态（字典: aicrm_guarantor_contract_status，effective=有效，expired=已到期，cancelled=已解除）
     */
    private String contractStatus;
    /**
     * 签约日期
     */
    private LocalDate signDate;
    /**
     * 担保类型（字典: aicrm_guarantee_method，guarantee=保证）
     */
    private String guaranteeType;
    /**
     * 担保人编号
     */
    private String guarantorNo;
    /**
     * 担保人姓名/名称
     */
    private String guarantorName;
    /**
     * 币种（字典: aicrm_currency_type）
     */
    private String currencyCode;
    /**
     * 担保总金额（万元）
     */
    private BigDecimal guaranteeTotalAmount;
    /**
     * 业务起始日期
     */
    private LocalDate businessStartDate;
    /**
     * 业务截止日期
     */
    private LocalDate businessEndDate;
    /**
     * 担保人类型（字典: aicrm_guarantor_type，person=个人，company=企业）
     */
    private String guarantorType;
    /**
     * 担保人证件类型（字典: aicrm_identity_type）
     */
    private String guarantorIdType;
    /**
     * 担保人证件号码（加密）
     */
    private String guarantorIdNo;
    /**
     * 与被担保人关系（字典: aicrm_relation_type）
     */
    private String relationWithBorrower;
    /**
     * 担保方式（字典: aicrm_guarantee_style，joint=连带责任，general=一般保证）
     */
    private String guaranteeMethod;
    /**
     * 已用担保金额（万元）
     */
    private BigDecimal usedAmount;
    /**
     * 可用担保金额（万元）
     */
    private BigDecimal availableAmount;
    /**
     * 客户经理ID（关联 system_users.id）
     */
    private Long managerUserId;
    /**
     * 管理机构ID（关联 system_dept.id）
     */
    private Long branchId;
    /**
     * 备注
     */
    private String remark;


}