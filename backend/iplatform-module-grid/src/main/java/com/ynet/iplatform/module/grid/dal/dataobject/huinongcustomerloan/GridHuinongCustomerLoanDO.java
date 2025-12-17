package com.ynet.iplatform.module.grid.dal.dataobject.huinongcustomerloan;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.*;
import com.ynet.iplatform.framework.mybatis.core.dataobject.BaseDO;

/**
 * 惠农客户贷款 DO
 *
 * @author 易诚源码
 */
@TableName("grid_huinong_customer_loan")
@KeySequence("grid_huinong_customer_loan_seq")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GridHuinongCustomerLoanDO extends BaseDO {

    /**
     * ID
     */
    @TableId
    private Long id;
    /**
     * 客户ID
     */
    private Long customerId;
    /**
     * 客户类别
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
     * 性别
     */
    private String gender;
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
     * 信贷需求
     */
    private String creditDemand;
    /**
     * 需求月份
     */
    private String demandMonth;
    /**
     * 需求期限
     */
    private String demandPeriod;
    /**
     * 业务进度
     */
    private String businessProgress;
    /**
     * 客户来源
     */
    private String customerSource;
    /**
     * 是否已申请
     */
    private Boolean isApplied;
    /**
     * 申请时间
     */
    private LocalDateTime applyTime;
    /**
     * 是否已批准
     */
    private Boolean isApproved;
    /**
     * 批准时间
     */
    private LocalDateTime approveTime;
    /**
     * 贷款产品名称
     */
    private String loanProductName;
    /**
     * 贷款金额
     */
    private BigDecimal loanAmount;
    /**
     * 授信额度
     */
    private BigDecimal creditLimit;
    /**
     * 贷款余额
     */
    private BigDecimal loanBalance;
    /**
     * 逾期状态
     */
    private String overdueStatus;

}
