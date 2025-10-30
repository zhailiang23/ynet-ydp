package cn.iocoder.yudao.module.aicrm.dal.dataobject.customerguaranteepledge;

import java.time.LocalDate;

import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import cn.iocoder.yudao.framework.mybatis.core.dataobject.BaseDO;

/**
 * 客户质押物信息表（零售+对公共用） DO
 *
 * @author 芋道源码
 */
@TableName("crm_customer_guarantee_pledge")
@KeySequence("crm_customer_guarantee_pledge_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerGuaranteePledgeDO extends BaseDO {

    /**
     * 质押物主键
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
     * 押品类型（字典: aicrm_pledge_type）
     */
    private String collateralType;
    /**
     * 担保类型（字典: aicrm_guarantee_method，pledge=质押）
     */
    private String guaranteeType;
    /**
     * 质押人证件类型（字典: aicrm_identity_type）
     */
    private String pledgorIdType;
    /**
     * 质押人证件号码（加密）
     */
    private String pledgorIdNo;
    /**
     * 质押人姓名/名称
     */
    private String pledgorName;
    /**
     * 质押人类型（字典: aicrm_guarantor_type，person=个人，company=企业）
     */
    private String pledgorType;
    /**
     * 与被担保人关系（字典: aicrm_relation_type）
     */
    private String relationWithBorrower;
    /**
     * 担保本金限权金额（万元）
     */
    private BigDecimal guaranteeAmount;
    /**
     * 质押率（%）
     */
    private BigDecimal pledgeRate;
    /**
     * 质押物名称
     */
    private String collateralName;
    /**
     * 质押物价值（万元）
     */
    private BigDecimal collateralValue;
    /**
     * 质押状态（字典: aicrm_pledge_status，effective=有效，released=已解押，invalid=无效）
     */
    private String pledgeStatus;
    /**
     * 质押登记日期
     */
    private LocalDate pledgeDate;
    /**
     * 解押日期
     */
    private LocalDate releaseDate;
    /**
     * 管理机构ID（关联 system_dept.id）
     */
    private Long managementBranchId;
    /**
     * 管理机构名称
     */
    private String managementBranchName;
    /**
     * 备注
     */
    private String remark;


}