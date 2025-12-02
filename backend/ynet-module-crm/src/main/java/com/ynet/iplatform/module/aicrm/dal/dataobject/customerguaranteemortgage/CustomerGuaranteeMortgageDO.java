package com.ynet.iplatform.module.aicrm.dal.dataobject.customerguaranteemortgage;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户抵押物信息表（零售+对公共用） DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_guarantee_mortgage")
@KeySequence("crm_customer_guarantee_mortgage_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGuaranteeMortgageDO extends BaseDO {

    /**
     * 抵押物主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 授信ID（关联 crm_customer_credit.id）
     */
    private Long creditId;
    /**
     * 押品编号
     */
    private String collateralNo;
    /**
     * 押品名称
     */
    private String collateralName;
    /**
     * 押品类型（字典: aicrm_mortgage_type）
     */
    private String collateralType;
    /**
     * 权证号
     */
    private String certificateNo;
    /**
     * 担保类型（字典: aicrm_guarantee_method，mortgage=抵押）
     */
    private String guaranteeType;
    /**
     * 抵押人姓名/名称
     */
    private String mortgagorName;
    /**
     * 抵押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）
     */
    private String mortgagorType;
    /**
     * 与被担保人关系（字典: aicrm_relation_type）
     */
    private String relationWithBorrower;
    /**
     * 担保本金限权金额（万元）
     */
    private BigDecimal guaranteeAmount;
    /**
     * 押品管理机构ID（关联 system_dept.id）
     */
    private Long managementBranchId;
    /**
     * 押品管理机构名称
     */
    private String managementBranchName;
    /**
     * 抵押人证件类型（字典: aicrm_identity_type）
     */
    private String mortgagorIdType;
    /**
     * 抵押人证件号码（加密）
     */
    private String mortgagorIdNo;
    /**
     * 抵押物地址
     */
    private String collateralAddress;
    /**
     * 抵押物面积（平方米）
     */
    private BigDecimal collateralArea;
    /**
     * 评估价值（万元）
     */
    private BigDecimal evaluationValue;
    /**
     * 评估日期
     */
    private LocalDate evaluationDate;
    /**
     * 评估机构
     */
    private String evaluationAgency;
    /**
     * 抵押率（%）
     */
    private BigDecimal mortgageRate;
    /**
     * 抵押状态（字典: aicrm_mortgage_status，effective=有效，released=已解押，invalid=无效）
     */
    private String mortgageStatus;
    /**
     * 抵押登记日期
     */
    private LocalDate mortgageDate;
    /**
     * 解押日期
     */
    private LocalDate releaseDate;
    /**
     * 备注
     */
    private String remark;


}