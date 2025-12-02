package com.ynet.iplatform.module.aicrm.dal.dataobject.customerworkinfo;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户工作信息表（精简版，只包含工作相关核心字段） DO
 *
 * @author 易诚源码
 */
@TableName("crm_customer_work_info")
@KeySequence("crm_customer_work_info_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerWorkInfoDO extends BaseDO {

    /**
     * 工作信息主键
     */
    @TableId
    private Long id;
    /**
     * 客户ID（关联 crm_customer 主表）
     */
    private Long customerId;
    /**
     * 工作单位名称
     */
    private String employerName;
    /**
     * 单位类型（字典: aicrm_employer_type）
     */
    private String employerType;
    /**
     * 所属行业
     */
    private String industry;
    /**
     * 职位/职务
     */
    private String position;
    /**
     * 职级（字典: aicrm_position_level）
     */
    private String positionLevel;
    /**
     * 所在部门
     */
    private String department;
    /**
     * 工作年限
     */
    private Integer workYears;
    /**
     * 年收入（元）
     */
    private BigDecimal annualIncome;
    /**
     * 月收入（元）
     */
    private BigDecimal monthlyIncome;
    /**
     * 是否有社保
     */
    private Boolean hasSocialInsurance;
    /**
     * 是否有公积金
     */
    private Boolean hasHousingFund;
    /**
     * 工作电话
     */
    private String workPhone;
    /**
     * 工作邮箱
     */
    private String workEmail;
    /**
     * 备注
     */
    private String remark;


}