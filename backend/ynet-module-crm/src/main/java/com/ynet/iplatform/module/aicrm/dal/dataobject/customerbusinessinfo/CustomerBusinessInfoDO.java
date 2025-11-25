package com.ynet.iplatform.module.aicrm.dal.dataobject.customerbusinessinfo;

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
 * 客户经营信息表（精简版，只包含经营相关核心字段） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_business_info")
@KeySequence("crm_customer_business_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerBusinessInfoDO extends BaseDO {

    /**
     * 经营信息主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 经营主体名称
     */
    private String businessName;
    /**
     * 经营类型（字典: aicrm_business_type）
     */
    private String businessType;
    /**
     * 营业执照号/统一社会信用代码
     */
    private String businessLicenseNo;
    /**
     * 经营范围
     */
    private String businessScope;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 经营规模（字典: aicrm_business_scale）
     */
    private String businessScale;
    /**
     * 经营状态（字典: aicrm_business_status）
     */
    private String businessStatus;
    /**
     * 注册资本（元）
     */
    private BigDecimal registeredCapital;
    /**
     * 员工人数
     */
    private Integer employeeCount;
    /**
     * 年营业额（元）
     */
    private BigDecimal annualRevenue;
    /**
     * 月营业额（元）
     */
    private BigDecimal monthlyRevenue;
    /**
     * 年利润（元）
     */
    private BigDecimal annualProfit;
    /**
     * 税务登记号
     */
    private String taxRegistrationNo;
    /**
     * 是否一般纳税人
     */
    private Boolean isGeneralTaxpayer;
    /**
     * 备注
     */
    private String remark;


}