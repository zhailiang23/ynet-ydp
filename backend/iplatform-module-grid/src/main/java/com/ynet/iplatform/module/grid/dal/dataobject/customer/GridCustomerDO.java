package com.ynet.iplatform.module.grid.dal.dataobject.customer;

import lombok.*;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.tenant.core.db.TenantBaseDO;

/**
 * 网格客户 DO
 *
 * @author 易诚源码
 */
@TableName("grid_customer")
@KeySequence("grid_customer_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridCustomerDO extends TenantBaseDO {

    /**
     * 客户ID
     */
    @TableId
    private Long id;
    /**
     * 网格ID
     */
    private Long gridId;
    /**
     * 客户类型
     */
    private String customerType;
    /**
     * 客户姓名
     */
    private String customerName;
    /**
     * 性别（男、女）
     */
    private String gender;
    /**
     * 客群类型
     */
    private String customerGroup;
    /**
     * 身份证号
     */
    private String idCard;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 手机号是否验证
     */
    private Boolean phoneVerified;
    /**
     * 地址
     */
    private String address;
    /**
     * 位置坐标
     */
    private byte[] location;
    /**
     * 客户经理ID
     */
    private Long managerId;
    /**
     * 来源
     */
    private String source;
    /**
     * 状态
     */
    private String status;
    /**
     * 是否正式客户（通用字段，所有客户类型适用）
     */
    private Boolean isFormalCustomer;

    // ==================== 惠农贷款客户专属字段（仅 customerType='HUINONG_LOAN' 时使用） ====================

    /**
     * 客户大类（字典: aicrm_customer_category）
     */
    private String customerCategory;
    /**
     * 细分类型
     */
    private String subdivisionType;
    /**
     * 经营地址
     */
    private String businessAddress;
    /**
     * 客户情况
     */
    private String customerSituation;
    /**
     * 经营年限
     */
    private Integer businessYears;
    /**
     * 当前融资情况
     */
    private String currentFinancing;
    /**
     * 信贷产品需求
     */
    private String creditDemand;
    /**
     * 需求月份（1-12）
     */
    private String demandMonth;
    /**
     * 需求旬（字典: aicrm_demand_period）
     */
    private String demandPeriod;
    /**
     * 业务进展
     */
    private String businessProgress;
    /**
     * 客户来源（字典: aicrm_customer_source）
     */
    private String customerSource;
    /**
     * 是否进件
     */
    private Boolean isApplied;
    /**
     * 进件时间
     */
    private LocalDateTime applyTime;
    /**
     * 是否审批通过
     */
    private Boolean isApproved;
    /**
     * 审批通过时间
     */
    private LocalDateTime approveTime;
    /**
     * 贷款产品名称
     */
    private String loanProductName;
    /**
     * 贷款金额（元）
     */
    private java.math.BigDecimal loanAmount;
    /**
     * 授信额度（元）
     */
    private java.math.BigDecimal creditLimit;
    /**
     * 贷款余额（元）
     */
    private java.math.BigDecimal loanBalance;
    /**
     * 逾期状态
     */
    private String overdueStatus;

}
